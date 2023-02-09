package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.service.QuoteService;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuoteControllerTest {
    private MarketDataDao dao;
    private QuoteService service;
    private QuoteController controller;

    @Before
    public void setUp() throws Exception {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);;
        cm.setDefaultMaxPerRoute(50);
        MarketDataConfig config = new MarketDataConfig();
        String host = "https://cloud.iexapis.com/v1";
        String token = System.getenv("IEX_PUB_TOKEN");
        config.setToken(token);
        config.setHost(host);

        dao = new MarketDataDao(cm, config);
        service = new QuoteService(dao);
        controller = new QuoteController(service);
    }

    @Test
    public void getQuote() {
        IexQuote quote = controller.getQuote("tsla");
        assertEquals("tsla", quote.getSymbol().toLowerCase());
    }
}