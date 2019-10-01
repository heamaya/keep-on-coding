package chapter19.exercise7;

import java.security.SecureRandom;
import java.util.Arrays;

public class BucketSort {

    private static final int STREAM_SIZE = 10;
    private static final int RANDOM_NUMBER_ORIGIN = 1;
    private static final int RANDOM_NUMBER_BOUND = 101;
    private static final int MAX = 100;

    public static void sort(int data[]) {
        for(int tensDigit = 1; tensDigit <= MAX; tensDigit *= 10) {
            int bucket[][] = new int[10][data.length];
            for (int value : data) {
                int row = value / tensDigit % 10;
                int column = 0;
                while (bucket[row][column] != 0) {
                    column++;
                }
                bucket[row][column] = value;
            }
            int index = 0;
            for (int i = 0; i < bucket.length; i++) {
                for (int j = 0; j < bucket[i].length; j++) {
                    if (bucket[i][j] != 0) {
                        data[index++] = bucket[i][j];
                    }
                }
            }
        }

    }

    public static void main(String args[]) {
        SecureRandom secureRandom = new SecureRandom();
        int[] data = secureRandom.ints(STREAM_SIZE, RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND).toArray();
        System.out.printf("Unsorted array: %s%n%n", Arrays.toString(data));
        sort(data);
        System.out.printf("%nSorted array: %s%n", Arrays.toString(data));
    }
}
