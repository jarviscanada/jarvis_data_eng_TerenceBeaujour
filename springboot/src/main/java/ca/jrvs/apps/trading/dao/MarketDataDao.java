package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.utils.JsonUtil;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * MarketDataDao is responsible for getting Quotes from IEX
 */
public class MarketDataDao implements CrudRepository<IexQuote, String> {
    private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;

    private static Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private HttpClientConnectionManager httpClientConnectionManager;

    public static void main(String[] args) throws IOException{
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);;
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1");
        marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));

        MarketDataDao dao = new MarketDataDao(cm, marketDataConfig);

        List<IexQuote> quoteList = dao.findAllById(Arrays.asList("fb", "aapl"));

        IexQuote quote = dao.findById("fb").get();
        logger.debug(quote.getCompanyName());
    }

    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig) {
        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }

    /**
     * Get an IexQuote (helper method which class finAllById)
     * @param ticker
     * @return
     */
    @Override
    public Optional<IexQuote> findById(String ticker) {
        Optional<IexQuote> iexQuote;
        List<IexQuote> quotes = findAllById(Collections.singleton(ticker));

        if (quotes.size() == 0) {
            return Optional.empty();
        } else if (quotes.size() ==1) {
            iexQuote = Optional.of(quotes.get(0));
        } else {
            throw new DataRetrievalFailureException("Unexpected number of quotes");
        }
        return iexQuote;
    }

    /**
     * Get quotes from IEX
     * @param ticker
     * @return a list of IexQuote models
     * @throws IllegalArgumentException if any ticker is invalid or tickers is empty
     * @throws DataRetrievalFailureException if HTTP request failed
     */
    @Override
    public List<IexQuote> findAllById(Iterable<String> ticker) {
        List<IexQuote> iexQuotes = new ArrayList<>();

        // Convert the Iterable to a comma separated string
        String tickers = StreamSupport
                .stream(ticker.spliterator(), false)
                .collect(Collectors.joining(","));

        String url = String.format(IEX_BATCH_URL, tickers);
        Optional<String> jsonResponse;
        String stringResponse;
        try {
            jsonResponse = this.executeHTTPGet(url);

            // Get rid of the Optional, to just have the string ({"AAPL":{"quote"..}})
            stringResponse = jsonResponse.orElse("");

            JSONObject jo = new JSONObject(stringResponse);
            Iterator<String> assets = jo.keys();
            while (assets.hasNext()) {
                String asset = assets.next();
                logger.debug(asset + ": " + jo.get(asset).toString());
                String quote = jo.get(asset).toString();
                Quote quoteModel = JsonUtil.toObjectFromJson(quote, Quote.class);
                iexQuotes.add(quoteModel.getQuote());
            }
            return iexQuotes;
        } catch (IOException e) {
            logger.error("Error in the findAllById method", e);
        }

        return null;
    }

    /**
     * Execute a get and return http entity/body as a string
     * Note: Use EntityUtils.toString to process HTTP entity
     * @param url resource URL
     * @return http response body or Optional.empty for 404 response
     * @throws DataRetrievalFailureException if HTTP failed or status code is unexpected
     */
    private Optional<String> executeHTTPGet(String url) throws IOException {
        Optional<String> finalResponse;
        CloseableHttpClient httpClient = this.getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        RequestConfig config = this.getHttpconfig();
        httpGet.setConfig(config);

        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            throw new IOException("Http method failed", e);
        }

        String jsonString  = parseResponseBody(response, 200);
        if (jsonString.isEmpty()) return Optional.empty();

        finalResponse = Optional.of(jsonString);
        return finalResponse;
    }

    /**
     * Borrow a HTTP client from the httpClientConnectionManager
     * @return a httpClient
     */
    private CloseableHttpClient getHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(httpClientConnectionManager)
                // prevent connectionManager shutdown when calling httpClient.close()
                .setConnectionManagerShared(true)
                .build();
    }

    /**
     * Get a basic configuration for HTTP request
     * @return The configuration object
     */
    private RequestConfig getHttpconfig() {
        return RequestConfig.custom()
                .setConnectTimeout(1000)
                .setSocketTimeout(1000)
                .build();
    }

    private String parseResponseBody(CloseableHttpResponse httpResponse, int expectedStatusCode) {

        // Check status code
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != expectedStatusCode) {
            try {
                logger.debug(EntityUtils.toString(httpResponse.getEntity()));
            } catch (IOException e) {
                logger.debug("Response has no entity");
            }
            throw new RuntimeException("Unexpected HTTP status: " + statusCode);
        }

        if (httpResponse.getEntity() == null) {
            throw new RuntimeException("Empty response body");
        }

        logger.debug("Correct status code: " + String.valueOf(statusCode));

        // Convert response entity to string
        String jsonStr;
        try {
            jsonStr = EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Unable to convert Object to JSON str", e);
        }
        return jsonStr;
    }

    public IexQuote DeserializeString(String s) throws IOException{
        return JsonUtil.toObjectFromJson(s, IexQuote.class);
    }

    @Override
    public boolean existsById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Iterable<IexQuote> findAll() {
        throw new UnsupportedOperationException("Not implemented");
    }


    @Override
    public long count() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(IexQuote entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> S save(S entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
