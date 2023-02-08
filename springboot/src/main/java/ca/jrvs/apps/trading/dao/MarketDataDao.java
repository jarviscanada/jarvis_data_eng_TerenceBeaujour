package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * MarketDataDao is responsible for getting Quotes from IEX
 */
public class MarketDataDao implements CrudRepository<IexQuote, String> {
    private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;

    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private HttpClientConnectionManager httpClientConnectionManager;

    public static void main(String[] args) throws IOException{
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);;
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/v1/");
        marketDataConfig.setToken(System.getenv("IEX_PUB_TOKEN"));

        MarketDataDao dao = new MarketDataDao(cm, marketDataConfig);
        String url = String.format(dao.IEX_BATCH_URL, "appl");
        dao.executeHTTPGet(url);
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
        CloseableHttpClient httpClient = this.getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        RequestConfig config = this.getHttpconfig();
        httpGet.setConfig(config);

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
        } catch (IOException e) {
            throw new IOException("Http method failed", e);
        }
        return null;
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
