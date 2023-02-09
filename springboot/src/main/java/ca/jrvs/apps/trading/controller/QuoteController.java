package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.service.QuoteService;

public class QuoteController {

    private QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    public IexQuote getQuote(String ticker) {
        try {
            return quoteService.findIexQuoteByTicker(ticker);
        } catch (Exception e) {
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }
}
