package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utils.JsonUtil;
import ca.jrvs.apps.twitter.utils.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwitterServiceTest {

    private TwitterService service;
    private TwitterDao dao;

    @Before
    public void setUp() throws Exception {

        // Get the keys
        String consumerKey= System.getenv("CONSUMER_KEY");
        String consumerSecret = System.getenv("CONSUMER_SECRET");
        String accessToken = System.getenv("ACCESS_TOKEN");
        String tokenSecret = System.getenv("TOKEN_SECRET");

        // Set up dependency
        TwitterHttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

        // Pass dependency
        this.dao = new TwitterDao(httpHelper);

        // Set up service
        this.service = new TwitterService(this.dao);
    }

    @Test
    public void postTweet() throws JsonProcessingException {
        Tweet postTweet = TweetUtil.buildTestTweet();
        String text = postTweet.getText();
        float[] coordinates = postTweet.getCoordinates().getCoordinates();
        System.out.println(JsonUtil.toJson(postTweet, true, false));

        Tweet tweet  = this.service.postTweet(postTweet);

        System.out.println(JsonUtil.toJson(tweet, true, false));

        assertEquals(text, tweet.getText());
    }

    @Test
    public void showTweet() {
    }

    @Test
    public void deleteTweets() {
    }
}