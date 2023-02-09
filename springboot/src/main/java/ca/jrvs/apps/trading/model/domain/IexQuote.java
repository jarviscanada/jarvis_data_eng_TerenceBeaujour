package ca.jrvs.apps.trading.model.domain;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "avgTotalVolume",
        "calculationPrice",
        "change",
        "changePercent",
        "close",
        "closeTime",
        "companyName",
        "delayedPrice",
        "delayedPriceTime",
        "extendedChange",
        "extendedChangePercent",
        "extendedPrice",
        "extendedPriceTime",
        "high",
        "iexAskPrice",
        "iexAskSize",
        "iexBidPrice",
        "iexBidSize",
        "iexLasUpdated",
        "iexMarketPercent",
        "iexRealtimePrice",
        "iexRealtimeSize",
        "iexVolume",
        "latestPrice",
        "latestSource",
        "latestTime",
        "latestUpdate",
        "latestVolume",
        "low",
        "marketCap",
        "open",
        "openTime",
        "peRatio",
        "previousClose",
        "primaryExchange",
        "sector",
        "symbol",
        "week52High",
        "week52Low",
        "ytdChange"
})
@Generated("jsonschema2pojo")
public class IexQuote {

    @JsonProperty("avgTotalVolume")
    private long avgTotalVolume;
    @JsonProperty("calculationPrice")
    private String calculationPrice;
    @JsonProperty("change")
    private float change;
    @JsonProperty("changePercent")
    private float changePercent;
    @JsonProperty("close")
    private float close;
    @JsonProperty("closeTime")
    private long closeTime;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("delayedPrice")
    private float delayedPrice;
    @JsonProperty("delayedPriceTime")
    private long delayedPriceTime;
    @JsonProperty("extendedChange")
    private float extendedChange;
    @JsonProperty("extendedChangePercent")
    private float extendedChangePercent;
    @JsonProperty("extendedPrice")
    private float extendedPrice;
    @JsonProperty("extendedPriceTime")
    private long extendedPriceTime;
    @JsonProperty("high")
    private float high;
    @JsonProperty("iexAskPrice")
    private String iexAskPrice;
    @JsonProperty("iexAskSize")
    private int iexAskSize;
    @JsonProperty("iexBidPrice")
    private int iexBidPrice;
    @JsonProperty("iexBidSize")
    private int iexBidSize;
    @JsonProperty("iexLasUpdated")
    private long iexLasUpdated;
    @JsonProperty("iexMarketPercent")
    private float iexMarketPercent;
    @JsonProperty("iexRealtimePrice")
    private float iexRealtimePrice;
    @JsonProperty("iexRealtimeSize")
    private int iexRealtimeSize;
    @JsonProperty("iexVolume")
    private long iexVolume;
    @JsonProperty("latestPrice")
    private float latestPrice;
    @JsonProperty("latestSource")
    private String latestSource;
    @JsonProperty("latestTime")
    private String latestTime;
    @JsonProperty("latestUpdate")
    private long latestUpdate;
    @JsonProperty("latestVolume")
    private long latestVolume;
    @JsonProperty("low")
    private float low;
    @JsonProperty("marketCap")
    private long marketCap;
    @JsonProperty("open")
    private float open;
    @JsonProperty("openTime")
    private long openTime;
    @JsonProperty("peRatio")
    private float peRatio;
    @JsonProperty("previousClose")
    private float previousClose;
    @JsonProperty("primaryExchange")
    private String primaryExchange;
    @JsonProperty("sector")
    private String sector;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("week52High")
    private float week52High;
    @JsonProperty("week52Low")
    private float week52Low;
    @JsonProperty("ytdChange")
    private float ytdChange;

    @JsonProperty("avgTotalVolume")
    public long getAvgTotalVolume() {
        return avgTotalVolume;
    }

    @JsonProperty("avgTotalVolume")
    public void setAvgTotalVolume(long avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    @JsonProperty("calculationPrice")
    public String getCalculationPrice() {
        return calculationPrice;
    }

    @JsonProperty("calculationPrice")
    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    @JsonProperty("change")
    public float getChange() {
        return change;
    }

    @JsonProperty("change")
    public void setChange(float change) {
        this.change = change;
    }

    @JsonProperty("changePercent")
    public float getChangePercent() {
        return changePercent;
    }

    @JsonProperty("changePercent")
    public void setChangePercent(float changePercent) {
        this.changePercent = changePercent;
    }

    @JsonProperty("close")
    public float getClose() {
        return close;
    }

    @JsonProperty("close")
    public void setClose(float close) {
        this.close = close;
    }

    @JsonProperty("closeTime")
    public long getCloseTime() {
        return closeTime;
    }

    @JsonProperty("closeTime")
    public void setCloseTime(long closeTime) {
        this.closeTime = closeTime;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("delayedPrice")
    public float getDelayedPrice() {
        return delayedPrice;
    }

    @JsonProperty("delayedPrice")
    public void setDelayedPrice(float delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    @JsonProperty("delayedPriceTime")
    public long getDelayedPriceTime() {
        return delayedPriceTime;
    }

    @JsonProperty("delayedPriceTime")
    public void setDelayedPriceTime(long delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    @JsonProperty("extendedChange")
    public float getExtendedChange() {
        return extendedChange;
    }

    @JsonProperty("extendedChange")
    public void setExtendedChange(float extendedChange) {
        this.extendedChange = extendedChange;
    }

    @JsonProperty("extendedChangePercent")
    public float getExtendedChangePercent() {
        return extendedChangePercent;
    }

    @JsonProperty("extendedChangePercent")
    public void setExtendedChangePercent(float extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    @JsonProperty("extendedPrice")
    public float getExtendedPrice() {
        return extendedPrice;
    }

    @JsonProperty("extendedPrice")
    public void setExtendedPrice(float extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    @JsonProperty("extendedPriceTime")
    public long getExtendedPriceTime() {
        return extendedPriceTime;
    }

    @JsonProperty("extendedPriceTime")
    public void setExtendedPriceTime(long extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    @JsonProperty("high")
    public float getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(float high) {
        this.high = high;
    }

    @JsonProperty("iexAskPrice")
    public String getIexAskPrice() {
        return iexAskPrice;
    }

    @JsonProperty("iexAskPrice")
    public void setIexAskPrice(String iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    @JsonProperty("iexAskSize")
    public int getIexAskSize() {
        return iexAskSize;
    }

    @JsonProperty("iexAskSize")
    public void setIexAskSize(int iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    @JsonProperty("iexBidPrice")
    public int getIexBidPrice() {
        return iexBidPrice;
    }

    @JsonProperty("iexBidPrice")
    public void setIexBidPrice(int iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    @JsonProperty("iexBidSize")
    public int getIexBidSize() {
        return iexBidSize;
    }

    @JsonProperty("iexBidSize")
    public void setIexBidSize(int iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    @JsonProperty("iexLasUpdated")
    public long getIexLasUpdated() {
        return iexLasUpdated;
    }

    @JsonProperty("iexLasUpdated")
    public void setIexLasUpdated(long iexLasUpdated) {
        this.iexLasUpdated = iexLasUpdated;
    }

    @JsonProperty("iexMarketPercent")
    public float getIexMarketPercent() {
        return iexMarketPercent;
    }

    @JsonProperty("iexMarketPercent")
    public void setIexMarketPercent(float iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    @JsonProperty("iexRealtimePrice")
    public float getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    @JsonProperty("iexRealtimePrice")
    public void setIexRealtimePrice(float iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    @JsonProperty("iexRealtimeSize")
    public int getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    @JsonProperty("iexRealtimeSize")
    public void setIexRealtimeSize(int iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    @JsonProperty("iexVolume")
    public long getIexVolume() {
        return iexVolume;
    }

    @JsonProperty("iexVolume")
    public void setIexVolume(long iexVolume) {
        this.iexVolume = iexVolume;
    }

    @JsonProperty("latestPrice")
    public float getLatestPrice() {
        return latestPrice;
    }

    @JsonProperty("latestPrice")
    public void setLatestPrice(float latestPrice) {
        this.latestPrice = latestPrice;
    }

    @JsonProperty("latestSource")
    public String getLatestSource() {
        return latestSource;
    }

    @JsonProperty("latestSource")
    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    @JsonProperty("latestTime")
    public String getLatestTime() {
        return latestTime;
    }

    @JsonProperty("latestTime")
    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    @JsonProperty("latestUpdate")
    public long getLatestUpdate() {
        return latestUpdate;
    }

    @JsonProperty("latestUpdate")
    public void setLatestUpdate(long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    @JsonProperty("latestVolume")
    public long getLatestVolume() {
        return latestVolume;
    }

    @JsonProperty("latestVolume")
    public void setLatestVolume(long latestVolume) {
        this.latestVolume = latestVolume;
    }

    @JsonProperty("low")
    public float getLow() {
        return low;
    }

    @JsonProperty("low")
    public void setLow(float low) {
        this.low = low;
    }

    @JsonProperty("marketCap")
    public long getMarketCap() {
        return marketCap;
    }

    @JsonProperty("marketCap")
    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    @JsonProperty("open")
    public float getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(float open) {
        this.open = open;
    }

    @JsonProperty("openTime")
    public long getOpenTime() {
        return openTime;
    }

    @JsonProperty("openTime")
    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    @JsonProperty("peRatio")
    public float getPeRatio() {
        return peRatio;
    }

    @JsonProperty("peRatio")
    public void setPeRatio(float peRatio) {
        this.peRatio = peRatio;
    }

    @JsonProperty("previousClose")
    public float getPreviousClose() {
        return previousClose;
    }

    @JsonProperty("previousClose")
    public void setPreviousClose(float previousClose) {
        this.previousClose = previousClose;
    }

    @JsonProperty("primaryExchange")
    public String getPrimaryExchange() {
        return primaryExchange;
    }

    @JsonProperty("primaryExchange")
    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    @JsonProperty("sector")
    public String getSector() {
        return sector;
    }

    @JsonProperty("sector")
    public void setSector(String sector) {
        this.sector = sector;
    }

    @JsonProperty("symbol")
    public String getSymbol() { return symbol; }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) { this.symbol = symbol; }

    @JsonProperty("week52High")
    public float getWeek52High() {
        return week52High;
    }

    @JsonProperty("week52High")
    public void setWeek52High(float week52High) {
        this.week52High = week52High;
    }

    @JsonProperty("week52Low")
    public float getWeek52Low() {
        return week52Low;
    }

    @JsonProperty("week52Low")
    public void setWeek52Low(float week52Low) {
        this.week52Low = week52Low;
    }

    @JsonProperty("ytdChange")
    public float getYtdChange() {
        return ytdChange;
    }

    @JsonProperty("ytdChange")
    public void setYtdChange(float ytdChange) {
        this.ytdChange = ytdChange;
    }

    @Override
    public String toString() {
        return "IexQuote{" +
                "avgTotalVolume=" + avgTotalVolume +
                ", calculationPrice='" + calculationPrice + '\'' +
                ", change=" + change +
                ", changePercent=" + changePercent +
                ", close=" + close +
                ", closeTime=" + closeTime +
                ", companyName='" + companyName + '\'' +
                ", delayedPrice=" + delayedPrice +
                ", delayedPriceTime=" + delayedPriceTime +
                ", extendedChange=" + extendedChange +
                ", extendedChangePercent=" + extendedChangePercent +
                ", extendedPrice=" + extendedPrice +
                ", extendedPriceTime=" + extendedPriceTime +
                ", high=" + high +
                ", iexAskPrice='" + iexAskPrice + '\'' +
                ", iexAskSize=" + iexAskSize +
                ", iexBidPrice=" + iexBidPrice +
                ", iexBidSize=" + iexBidSize +
                ", iexLasUpdated=" + iexLasUpdated +
                ", iexMarketPercent=" + iexMarketPercent +
                ", iexRealtimePrice=" + iexRealtimePrice +
                ", iexRealtimeSize=" + iexRealtimeSize +
                ", iexVolume=" + iexVolume +
                ", latestPrice=" + latestPrice +
                ", latestSource='" + latestSource + '\'' +
                ", latestTime='" + latestTime + '\'' +
                ", latestUpdate=" + latestUpdate +
                ", latestVolume=" + latestVolume +
                ", low=" + low +
                ", marketCap=" + marketCap +
                ", open=" + open +
                ", openTime=" + openTime +
                ", peRatio=" + peRatio +
                ", previousClose=" + previousClose +
                ", primaryExchange='" + primaryExchange + '\'' +
                ", sector='" + sector + '\'' +
                ", symbol='" + symbol + '\'' +
                ", week52High=" + week52High +
                ", week52Low=" + week52Low +
                ", ytdChange=" + ytdChange +
                '}';
    }
}