package ca.jrvs.practice.codingChallenge;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OddEvenUnitTest {

    @BeforeClass
    public static void classeSetUp() throws Exception {
        System.out.println("--BeforeClass static method runs once before class is instantiate");
    }

    @AfterClass
    public static void classTTearDown() throws Exception {
        System.out.println("--@AfterClass static method runs once at the end of all methods");
    }

    private OddEven oddEven;

    @Before
    public void SetUp() throws Exception {
        System.out.println("--@Before method runs before each @Test method");
        oddEven = new OddEven();
    }

    @After
    public void tearDowns() throws Exception {
        System.out.println("--@After method runs after each @Test method");
    }

    @Test
    public void oddEvenMod() {
        System.out.println("Test case: test oddEveMod method from the test class");
        String expected = "odd";
        Assert.assertEquals(expected, oddEven.oddEvenMod(3));
        expected = "even";
        Assert.assertEquals(expected, oddEven.oddEvenMod(10));
    }

    @Test
    public void oddEvenBit() {
        System.out.println("Test case oddEventBit method from the test class");
        Object expected = null;
        Assert.assertNull(oddEven.oddEvenBit(5));
    }
}
