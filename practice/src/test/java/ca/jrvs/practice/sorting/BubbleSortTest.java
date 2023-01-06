package ca.jrvs.practice.sorting;

import org.junit.Assert;
import org.junit.Test;

public class BubbleSortTest {

    private BubbleSort bbs = new BubbleSort();

    @Test
    public void bubbleSort() {
        int[] array = {5, 2, 8, 3};
        int[] expected = {2, 3, 5, 8};
        bbs.bubbleSort(array);
        Assert.assertArrayEquals(expected, array);
    }
}