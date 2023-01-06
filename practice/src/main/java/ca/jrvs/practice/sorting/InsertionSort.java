package ca.jrvs.practice.sorting;

public class InsertionSort {

    public static void insertSortImperative(int[] input) {
        for (int i = 1; i < input.length; i++) {
            int current = input[i];
            int j = i-1;
            while (j>=0 && input[j] > current) {
                input[j+1] = input[j];
                j -= 1;
            }
            input[j+1] = current;
        }
    }
}
