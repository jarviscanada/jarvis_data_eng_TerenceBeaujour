package ca.jrvs.practice.mockito;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class ConfigureThreadingUtilTest {

    @Test
    public void ensureThatThreadPoolCanBeConfigured() {
        // mock MyApplication
        MyApplication app = mock(MyApplication.class);
        // call ConfigureThreadingUtil.configureThreadPool
        ConfigureThreadingUtil.configureThreadPool(app);
        // verify that getNumberOfThreads was the only one called on app
        verify(app).getNumberOfThreads();
        verifyNoMoreInteractions(app);
    }

}