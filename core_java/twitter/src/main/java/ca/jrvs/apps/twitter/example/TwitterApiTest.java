package ca.jrvs.apps.twitter.example;

import com.google.gdata.util.common.base.PercentEscaper;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class TwitterApiTest {

    private static String CONSUMER_KEY = System.getenv("CONSUMER_KEY");
    private static String CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
    private static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
    private static String TOKEN_SECRET = System.getenv("TOKEN_SECRET");

    public static void main(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IOException {
        // Setup oauth
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        // Create an HTTP GET request
        String status = "today is a good day to practice Java";
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        HttpPost request = new HttpPost("https://api.twitter.com/101/statuses/update.json?status=" + percentEscaper.escape(status));

        // Sign the request (add headers)
        consumer.sign(request);

        System.out.println("Http Request Headers: ");
        Arrays.stream(request.getAllHeaders())
                .forEach(System.out::println);

        // Send the request
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(request);

        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}