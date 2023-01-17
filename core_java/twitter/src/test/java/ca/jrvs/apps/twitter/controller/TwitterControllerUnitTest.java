package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

    @Mock
    Service mockService;

    @InjectMocks
    TwitterController controller;

    @Test
    public void postTweet() {
        when(mockService.postTweet(any())).thenReturn(new Tweet());
        String[] args = {"POST", "random", "15:15"};
        Tweet tweet = controller.postTweet(args);
        assertNull(tweet.getId_str());
    }

    @Test
    public void showTweet() {
        when(mockService.showTweet(anyString())).thenReturn(new Tweet());
        String[] args = {"GET", "random"};
        Tweet tweet = controller.showTweet(args);
        assertNull(tweet.getId_str());
    }

    @Test
    public void deleteTweet() throws InterruptedException {
        when(mockService.deleteTweets(any())).thenReturn(any());
        String[] args = {"DELETE", "1"};
        List<Tweet> tweets = controller.deleteTweet(args);
        assertNull(tweets);
    }
}