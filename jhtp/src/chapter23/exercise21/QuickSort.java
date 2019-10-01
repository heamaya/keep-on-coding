package chapter23.exercise21;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSort<E extends Comparable<E>> extends RecursiveAction {

    private static final int STREAM_SIZE = 10;
    private static final int RANDOM_NUMBER_ORIGIN = 1;
    private static final int RANDOM_NUMBER_BOUND = 100;
    private E [] data;
    private int start;
    private int end;

    public QuickSort(E[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public void compute() {
        if(start < end) {
            int partitionStart = start;
            int partitionEnd = end;
            while (partitionStart < partitionEnd) {
                while (data[partitionEnd].compareTo(data[partitionStart]) > 0) {
                    partitionEnd--;
                }
                swap(data, partitionStart, partitionEnd);
                partitionStart++;
                while (data[partitionStart].compareTo(data[partitionEnd]) < 0) {
                    partitionStart++;
                }
                swap(data, partitionStart, partitionEnd);
                partitionEnd--;
            }
            invokeAll(new QuickSort<>(data, start, partitionEnd), new QuickSort<>(data, partitionEnd + 1, end));
        }
    }

    private void swap(E [] data, int from, int to) {
        E temp = data[to];
        data[to] = data[from];
        data[from] = temp;
    }

    public static void main(String args[]) {
        final SecureRandom secureRandom = new SecureRandom();
        final Long [] data = secureRandom.longs(STREAM_SIZE, RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND)
            .boxed()
            .toArray(Long[]::new);
        final ForkJoinPool pool = new ForkJoinPool();
        final QuickSort quickSort = new QuickSort(data, 0, data.length - 1);
        System.out.printf("Unsorted array: %s%n%n", Arrays.toString(data));
        pool.invoke(quickSort);
        System.out.printf("%nSorted array: %s%n", Arrays.toString(data));
    }
}
