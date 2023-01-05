package ca.jrvs.practice.search;

import org.junit.Test;
import org.junit.Assert;

import java.util.Optional;

public class BinarySearchTest {

    private BinarySearch bs = new BinarySearch();

    @Test
    public void binarySearchIteration() {
        Integer[] array = {1,2,3,4,5,6,7,8,9};
        Optional<Integer> index;
        BinarySearch bs = new BinarySearch();

        index = bs.binarySearchIteration(array, 5);
        Assert.assertTrue(index.isPresent());
        Assert.assertEquals(4, (int)index.get());

        index = bs.binarySearchIteration(array, 50);
        Assert.assertFalse(index.isPresent());
    }
}