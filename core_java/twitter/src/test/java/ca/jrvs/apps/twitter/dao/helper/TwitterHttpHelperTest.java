package ca.jrvs.apps.twitter.dao.helper;

import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.*;

public class TwitterHttpHelperTest {

    @Test
    public void httpPost() throws Exception{
        String consumerKey = System.getenv("CONSUMER_KEY");
        String consumerSecret = System.getenv("CONSUMER_SECRET");
        String accessToken = System.getenv("ACCESS_TOKEN");
        String tokenSecret = System.getenv("TOKEN_SECRET");

        TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

        String status = "Another random tweet";
        PercentEscaper percentEscaper = new PercentEscaper("", false);

        HttpResponse response = twitterHttpHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(status)));
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    public void httpGet() throws Exception{
        String consumerKey = System.getenv("CONSUMER_KEY");
        String consumerSecret = System.getenv("CONSUMER_SECRET");
        String accessToken = System.getenv("ACCESS_TOKEN");
        String tokenSecret = System.getenv("TOKEN_SECRET");

        TwitterHttpHelper twitterHttpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

        HttpResponse response = twitterHttpHelper.httpGet(new URI("https://api.twitter.com/1.1/statuses/show.json?id=210462857140252672"));
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}