package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utils.JsonUtil;
import ca.jrvs.apps.twitter.utils.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;

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
    public void showTweet() throws IOException {
        // Create and post a tweet to test the id
        Tweet postTweet = TweetUtil.buildTestTweet();
        System.out.println(JsonUtil.toJson(postTweet, true, false));
        Tweet tweet = this.dao.create(postTweet);
        System.out.println(JsonUtil.toJson(tweet, true, false));
        String id = tweet.getId_str();

        // Actual testing
        Tweet showTweet = this.service.showTweet(id);
        System.out.println(JsonUtil.toJson(showTweet, true, false));

        assertEquals(id, showTweet.getId_str());
    }

    @Test
    public void deleteTweets() throws InterruptedException, IllegalArgumentException{

        List<Tweet> tweetList = new ArrayList<>();

        //  Create an array of size 3 to get the ids.
        String[] ids = new String[3];

        // Post 3 tweets
        for (int i = 0; i<3; i++) {
            Tweet postTweet = TweetUtil.buildTestTweet();
            Thread.sleep(500);
            Tweet tweet = this.service.postTweet(postTweet);
            tweetList.add(tweet);
            ids[i] = tweet.getId_str();
        }

        assertNotNull(ids);

        // Actual test
        List<Tweet> deletedTweets = this.service.deleteTweets(ids);

        assertEquals(tweetList.size(), deletedTweets.size());

        for (int i = 0; i < deletedTweets.size(); i++) {
            String expected = tweetList.get(i).getId_str();
            String result = deletedTweets.get(i).getId_str();
            assertEquals(expected, result);
        }
    }
}