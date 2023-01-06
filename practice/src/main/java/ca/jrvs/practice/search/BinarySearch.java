package ca.jrvs.practice.search;

import javax.swing.text.html.Option;
import java.util.Optional;

public class BinarySearch {

    /**
     * find the target index in a sorted array
     *
     * @param arr input arry is sorted
     * @param target value to be searched
     * @return target index or Optional.empty() if not ound
     */
    public <E> Optional<Integer> binarySearchRecursion(E[] arr, E target) {
        return Optional.empty();
    }

    /**
     * find the target index in a sorted array
     *
     * @param arr input array is sorted
     * @param target value to be searched
     * @return target index or Optional.empty() if not ound
     */
    public <E extends Comparable<E>> Optional<Integer> binarySearchIteration(E[] arr, E target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int comparison = target.compareTo(arr[mid]);

            if (comparison == 0) {
                return Optional.of(mid);
            } else if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return Optional.empty();
    }
}
