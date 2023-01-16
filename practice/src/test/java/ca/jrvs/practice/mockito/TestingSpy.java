package ca.jrvs.practice.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestingSpy {

    @Spy
    List<String> spyList = new ArrayList<>();

    @Test
    public void testingSpy() {
        doReturn("Hello").when(spyList).get(1000000000);
        String result = spyList.get(1000000000);
        assertTrue(result instanceof String);
        assertEquals("Hello", result);
    }
}
