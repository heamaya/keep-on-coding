package chapter19.exercise10;

import java.security.SecureRandom;
import java.util.Arrays;

public class QuickSort {

    private static final int STREAM_SIZE = 10;
    private static final int RANDOM_NUMBER_ORIGIN = 1;
    private static final int RANDOM_NUMBER_BOUND = 101;

    public static void sort(int data[], int start, int end) {
        if(start < end) {
            int partitionStart = start;
            int partitionEnd = end;
            while (partitionStart < partitionEnd) {
                while (data[partitionEnd] > data[partitionStart]) {
                    partitionEnd--;
                }
                swap(data, partitionStart, partitionEnd);
                partitionStart++;
                while (data[partitionStart] < data[partitionEnd]) {
                    partitionStart++;
                }
                swap(data, partitionStart, partitionEnd);
                partitionEnd--;
            }
            sort(data, start, partitionEnd);
            sort(data, partitionEnd + 1, end);
        }
    }

    private static void swap(int data[], int from, int to) {
        int temp = data[to];
        data[to] = data[from];
        data[from] = temp;
    }

    public static void main(String args[]) {
        SecureRandom secureRandom = new SecureRandom();
        int[] data = secureRandom.ints(STREAM_SIZE, RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND).toArray();
        System.out.printf("Unsorted array: %s%n%n", Arrays.toString(data));
        sort(data, 0, data.length - 1);
        System.out.printf("%nSorted array: %s%n", Arrays.toString(data));
    }
}
