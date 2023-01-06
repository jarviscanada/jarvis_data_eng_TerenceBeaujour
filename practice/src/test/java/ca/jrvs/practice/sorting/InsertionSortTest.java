package ca.jrvs.practice.sorting;

import org.junit.Test;
import org.junit.Assert;

public class InsertionSortTest {

    @Test
    public void insertSortImperative() {
        int[] array = {5, 2, 8, 3};
        int[] expected = {2, 3, 5, 8};

        InsertionSort.insertSortImperative(array);
        Assert.assertArrayEquals(expected, array);
    }
}