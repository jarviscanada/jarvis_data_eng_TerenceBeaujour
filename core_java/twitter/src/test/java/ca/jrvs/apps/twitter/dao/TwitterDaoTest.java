package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utils.JsonUtil;
import ca.jrvs.apps.twitter.utils.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwitterDaoTest {

    private TwitterDao dao;

    @Before
    public void setUp() throws Exception {
        String consumerKey= System.getenv("CONSUMER_KEY");
        String consumerSecret = System.getenv("CONSUMER_SECRET");
        String accessToken = System.getenv("ACCESS_TOKEN");
        String tokenSecret = System.getenv("TOKEN_SECRET");

        // Set up dependency
        TwitterHttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

        // Pass dependency
        this.dao = new TwitterDao(httpHelper);
    }

    @Test
    public void create() throws JsonProcessingException {
        String text = TweetUtil.getRandomText(false);
        float lat = 1f;
        float lon = -1f;
        Tweet postTweet = TweetUtil.buildTweet(text, lat, lon);
        System.out.println(JsonUtil.toJson(postTweet, true, false));

        Tweet tweet = dao.create(postTweet);

        System.out.println(JsonUtil.toJson(tweet, true, false));

        assertEquals(text, tweet.getText());

        assertNotNull(tweet.getCoordinates());
        assertEquals(lat, tweet.getCoordinates().getCoordinates()[0], 100);
        assertEquals(lon, tweet.getCoordinates().getCoordinates()[1], 100);

    }

    @Test
    public void findById() throws JsonProcessingException {
        Tweet tweetResponse = postTweet();
        String id = tweetResponse.getId_str();
        assertNotNull(id);
        Tweet tweet = this.dao.findById(id);

        assertNotNull(tweet);
        System.out.println(JsonUtil.toJson(tweet, true, false));
    }

    @Test
    public void deleteById() throws JsonProcessingException{

        Tweet tweetResponse = postTweet();
        String id = tweetResponse.getId_str();
        assertNotNull(id);

        Tweet tweet = this.dao.deleteById(id);

        assertNotNull(tweet);;
        System.out.println(JsonUtil.toJson(tweet, true, false));
    }

    public Tweet postTweet() {
        Tweet postTweet = TweetUtil.buildTestTweet();
        Tweet tweetResponse = this.dao.create(postTweet);
        assertNotNull(tweetResponse);
        return tweetResponse;
    }
}