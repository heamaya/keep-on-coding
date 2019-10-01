package chapter19.exercise9;

import chapter19.exercise10.QuickSort;

import java.security.SecureRandom;
import java.util.Arrays;

public class RecursiveBinarySearch {

    private static final int STREAM_SIZE = 10;
    private static final int RANDOM_NUMBER_ORIGIN = 1;
    private static final int RANDOM_NUMBER_BOUND = 101;

    public static int search(int data[], int element, int start, int end) {
        int middle = (start + end + 1) / 2;
        if (start > end) {
            return -1;
        } else if (data[middle] == element) {
            return middle;
        } else if (element < data[middle]) {
            end = middle - 1;
        } else if (element > data[middle]) {
            start = middle + 1;
        }
        return search(data, element, start, end);
    }

    public static void main(String args[]) {
        SecureRandom secureRandom = new SecureRandom();
        int data[] = secureRandom.ints(STREAM_SIZE, RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND).toArray();
        QuickSort.sort(data, 0, data.length -1);
        System.out.printf("array: %s%n%n", Arrays.toString(data));
        int elementToSearch = data[secureRandom.nextInt(STREAM_SIZE - 1)];
        System.out.printf("element to search: %d%n", elementToSearch);
        int searchIndex = search(data, elementToSearch, 0, data.length - 1);
        System.out.printf("Element index: %d%n%n", searchIndex);
        int elementNotPresentToSearch = 105;
        System.out.printf("element to search: %d%n", elementNotPresentToSearch);
        int notFoundIndex = search(data, elementNotPresentToSearch, 0, data.length - 1);
        System.out.printf("Element index: %d%n", notFoundIndex);
    }
}
