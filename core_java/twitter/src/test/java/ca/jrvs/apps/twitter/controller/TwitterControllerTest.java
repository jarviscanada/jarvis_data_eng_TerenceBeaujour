package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.CrDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.utils.TweetUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TwitterControllerTest {

    private Controller controller;
    private Service service;
    private CrDao dao;

    private String COORD_SEP = ":";

    @Before
    public void setUp() throws Exception {
        // Set up keys for OAuth
        String consumerKey = System.getenv("CONSUMER_KEY");
        String consumerSecret = System.getenv("CONSUMER_SECRET");
        String accessToken = System.getenv("ACCESS_TOKEN");
        String tokenSecret = System.getenv("TOKEN_SECRET");

        // Set up and pass dependencies
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        this.dao = new TwitterDao(httpHelper);
        this.service = new TwitterService(this.dao);

        // Set up Controller
        this.controller = new TwitterController(this.service);
    }

    @Test
    public void postTweet() {
        // Initialization
        String[] args = new String[3];
        String method = "post";
        String text = TweetUtil.getRandomText(false);
        float latFloat = TweetUtil.getRandomLat(false);
        float lonFloat = TweetUtil.getRandomLon(false);
        String lat = Float.toString(latFloat);
        String lon = Float.toString(lonFloat);
        args[0] = method;
        args[1] = text;
        args[2] = lat + COORD_SEP + lon;

        // Actual test
        Tweet tweet = this.controller.postTweet(args);
        assertNotNull(tweet);
        assertEquals(text, tweet.getText());
        assertEquals(latFloat, tweet.getCoordinates().getCoordinates()[0], 100);
        assertEquals(lonFloat, tweet.getCoordinates().getCoordinates()[1], 100);
    }

    @Test
    public void showTweet() {
        // Initialization
        Tweet generatedTweet = TweetUtil.buildTestTweet();
        Tweet postedTweet = this.service.postTweet(generatedTweet);

        assertNotNull(postedTweet);

        String id = postedTweet.getId_str();
        String method = "get";

        // Actual test
        Tweet tweet = this.controller.showTweet(new String[]{method, id});
        assertNotNull(tweet);
        assertEquals(id, tweet.getId_str());
    }

    @Test
    public void deleteTweet() throws InterruptedException {
        // Initialization
        Tweet generatedTweet = TweetUtil.buildTestTweet();
        Tweet postedTweet = this.service.postTweet(generatedTweet);

        assertNotNull(postedTweet);

        String id = postedTweet.getId_str();
        String method = "delete";

        // Actual test
        List<Tweet> tweets = this.controller.deleteTweet(new String[]{method, id});

        assertNotNull(tweets);

        assertEquals(id, tweets.get(0).getId_str());
    }
}