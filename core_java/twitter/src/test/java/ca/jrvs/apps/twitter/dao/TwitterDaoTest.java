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
        String text = "I love programming";
        float lat = 1f;
        float lon = -1f;
        Tweet postTweet = TweetUtil.buildTweet(text, lat, lon);
        System.out.println(JsonUtil.toJson(postTweet, true, false));

        Tweet tweet = dao.create(postTweet);

        System.out.println(JsonUtil.toJson(tweet, true, false));

        assertEquals(text, tweet.getText());
    }

    @Test
    public void findById() throws JsonProcessingException {
        String id = "1613962713004113923";
        Tweet tweet = this.dao.findById(id);

        assertNotNull(tweet);
        System.out.println(JsonUtil.toJson(tweet, true, false));
    }

    @Test
    public void deleteById() throws JsonProcessingException{
        String id = "1613962713004113923";
        Tweet tweet = this.dao.deleteById(id);

        assertNotNull(tweet);;
        System.out.println(JsonUtil.toJson(tweet, true, false));
    }
}