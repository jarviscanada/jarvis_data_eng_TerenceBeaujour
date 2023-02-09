package ca.jrvs.apps.trading.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "quote"
})
public class Quote {
    @JsonProperty("quote")
    private IexQuote quote;

    @JsonProperty("quote")
    public IexQuote getQuote() {
        return quote;
    }

    @JsonProperty("quote")
    public void setQuote(IexQuote quote) {
        this.quote = quote;
    }
}
