package chapter23.exercise20;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSort<E extends Comparable<E>> extends RecursiveAction {

   private final E [] data;
   private int low;
   private int high;

   public MergeSort(E [] data, int low, int high) {
      this.data = data;
      this.low = low;
      this.high = high;
   }

   @Override
   protected void compute() {
      if ((high - low) >= 1) {
         int middle1 = (low + high) / 2;
         int middle2 = middle1 + 1;
         System.out.printf("split:   %s%n", subarrayString(data, low, high));
         System.out.printf("         %s%n", subarrayString(data, low, middle1));
         System.out.printf("         %s%n%n", subarrayString(data, middle2, high));
         invokeAll(new MergeSort<>(data, low, middle1), new MergeSort<>(data, middle2, high));
         merge(data, low, middle1, middle2, high);
      }
   }

   private void merge(E [] data, int left, int middle1, int middle2, int right) {
      int leftIndex = left;
      int rightIndex = middle2;
      int combinedIndex = left;
      E [] combined = Arrays.copyOf(data, data.length);
      Arrays.fill(combined, null);
      System.out.printf("merge:   %s%n", subarrayString(data, left, middle1));
      System.out.printf("         %s%n", subarrayString(data, middle2, right));
      while (leftIndex <= middle1 && rightIndex <= right) {
         if (data[leftIndex].compareTo(data[rightIndex]) <= 0) {
            combined[combinedIndex++] = data[leftIndex++];
         }
         else {
            combined[combinedIndex++] = data[rightIndex++];
         }
      }
      if (leftIndex == middle2) {
         while (rightIndex <= right) {
            combined[combinedIndex++] = data[rightIndex++];
         }
      }
      else {
         while (leftIndex <= middle1) {
            combined[combinedIndex++] = data[leftIndex++];
         }
      }
      for (int i = left; i <= right; i++) {
         data[i] = combined[i];
      }
      System.out.printf("         %s%n%n", subarrayString(data, left, right));
   }

   private String subarrayString(E [] data, int low, int high) {
      StringBuilder temporary = new StringBuilder();
      for (int i = 0; i < low; i++) {
         temporary.append("   ");
      }
      for (int i = low; i <= high; i++) {
         temporary.append(" " + data[i]);
      }
      return temporary.toString();
   }

   public static void main(String[] args) {
      final SecureRandom generator = new SecureRandom();
      final Long [] data = generator.longs(10, 10, 91)
          .boxed()
          .toArray(Long[]::new);
      final MergeSort mergeSort = new MergeSort(data, 0, data.length -1);
      final ForkJoinPool pool = new ForkJoinPool();
      System.out.printf("Unsorted array: %s%n%n", Arrays.toString(data));
      pool.invoke(mergeSort);
      System.out.printf("Sorted array: %s%n", Arrays.toString(data));
   }

}
