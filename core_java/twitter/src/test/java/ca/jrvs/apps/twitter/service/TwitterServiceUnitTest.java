package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utils.TweetUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

    @Mock
    CrDao mockDao;

    @InjectMocks
    TwitterService service;

    @Test
    public void postTweet() {
        when(mockDao.create(any())).thenReturn(new Tweet());
        Tweet tweet  = service.postTweet(TweetUtil.buildTestTweet());
        assertNull(tweet.getId_str());
    }

    @Test
    public void showTweet() {
        when(mockDao.findById(anyString())).thenReturn(new Tweet());
        Tweet tweet = service.showTweet("123");
        assertNull(tweet.getId_str());
    }

    @Test
    public void deleteTweets() throws InterruptedException {
        when(mockDao.deleteById(anyString())).thenReturn(new Tweet());
        List<Tweet> tweets = service.deleteTweets(new String[]{"123", "456"});
        assertNull(tweets.get(0).getId_str());
    }
}