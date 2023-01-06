package ca.jrvs.practice.sorting;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest {

    private QuickSort quickSort;

    @Test
    public void quickSort() {
        quickSort = new QuickSort();
        int[] array = {9,7,10,15,13};
        int[] expected = {7,9,10,13,15};

        quickSort.quickSort(array, 0, array.length-1);
        assertArrayEquals(expected, array);
    }
}