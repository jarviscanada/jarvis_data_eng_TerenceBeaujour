package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(Quote.class);

//    private QuoteDao quoteDao;
    private MarketDataDao marketDataDao;

    public QuoteService(MarketDataDao marketDataDao) {
//        this.quoteDao = quoteDao;
        this.marketDataDao = marketDataDao;
    }

    /**
     * Find an IexQuote
     * @param ticker id
     * @return IexQuote object
     */
    public IexQuote findIexQuoteByTicker(String ticker) {
        return marketDataDao.findById(ticker)
                .orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
    }
}
