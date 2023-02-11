package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    private Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public MarketDataConfig marketDataConfig() {
        MarketDataConfig config = new MarketDataConfig();
        String host = "https://cloud.iexapis.com/v1";
        String token = System.getenv("IEX_PUB_TOKEN");
        config.setToken(token);
        config.setHost(host);
        return config;
    }

    @Bean
    public HttpClientConnectionManager connectionManager() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);;
        cm.setDefaultMaxPerRoute(50);
        return cm;
    }
}
