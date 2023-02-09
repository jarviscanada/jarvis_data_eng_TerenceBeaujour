package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuoteServiceTest {
    private QuoteService quoteService;
    private MarketDataDao marketDataDao;

    @Before
    public void setUp() throws Exception {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);;
        cm.setDefaultMaxPerRoute(50);

        MarketDataConfig config = new MarketDataConfig();
        config.setHost("https://cloud.iexapis.com/v1");
        config.setToken(System.getenv("IEX_PUB_TOKEN"));

        marketDataDao = new MarketDataDao(cm, config);
        quoteService = new QuoteService(marketDataDao);
    }

    @Test
    public void findIexQuoteByTicker() {
        IexQuote iexQuote = quoteService.findIexQuoteByTicker("AAPL");
        assertEquals(iexQuote.getSymbol().toLowerCase(), "aapl");
    }
}