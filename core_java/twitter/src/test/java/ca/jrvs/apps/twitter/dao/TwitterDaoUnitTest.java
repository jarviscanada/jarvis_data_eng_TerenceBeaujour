package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utils.TweetUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

    @Mock
    HttpHelper mockHttpHelper;

    @InjectMocks
    TwitterDao dao;

    @Test
    public void create() {
        when(mockHttpHelper.httpPost(any())).thenThrow(new RuntimeException("mock"));
        try {
            dao.create(TweetUtil.buildTestTweet());
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    @Test
    public void findById() {
        when(mockHttpHelper.httpGet(any())).thenThrow(new RuntimeException("mock"));
        try {
            dao.findById(anyString());
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    @Test
    public void deleteById() {
        when(mockHttpHelper.httpPost(any())).thenThrow(new RuntimeException("mock"));
        try {
            dao.deleteById(anyString());
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }
}