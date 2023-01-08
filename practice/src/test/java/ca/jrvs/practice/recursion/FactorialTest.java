package ca.jrvs.practice.recursion;

import org.junit.Test;

import static org.junit.Assert.*;

public class FactorialTest {

    @Test
    public void recursiveFactorial() {
        Factorial fact = new Factorial();
        int expected = 6;
        int result = fact.recursiveFactorial(3);
        assertEquals(expected, result);
    }

    @Test
    public void iterativeFactorial() {
        Factorial fact = new Factorial();
        int expected = 24;
        int result = fact.iterativeFactorial(4);
        assertEquals(expected, result);
    }
}