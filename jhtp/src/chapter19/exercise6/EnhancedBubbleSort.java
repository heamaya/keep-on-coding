package chapter19.exercise6;

import java.security.SecureRandom;
import java.util.Arrays;

public class EnhancedBubbleSort {

   public static void sort(int[] data) {
	   for(int pass = 1; pass <= data.length; pass++) {
		   int swapCount = 0;
		   for(int bubble = 1; bubble <= data.length - pass; bubble++) {
			   if(data[bubble] < data[bubble - 1]) {
				   swap(data, bubble, bubble - 1);
				   swapCount++;
			   }
		   }
		   if(swapCount == 0) {
			   break;
		   } else {
			   swapCount = 0;
		   }
		   printPass(data, pass, pass - 1);
	   }
   } 

   private static void swap(int[] data, int first, int second) {
      int temporary = data[first];
      data[first] = data[second];
      data[second] = temporary;
   } 

   private static void printPass(int[] data, int pass, int index) {
      System.out.printf("after pass %2d: ", pass);
      for (int i = 0; i < index; i++) {
         System.out.printf("%d  ", data[i]);
      }
      System.out.printf("%d* ", data[index]);
      for (int i = index + 1; i < data.length; i++) {
         System.out.printf("%d  ", data[i]);
      }
      System.out.printf("%n               ");
      for (int j = 0; j < pass; j++) {
         System.out.print("--  ");
      }
      System.out.println(); 
   }

   public static void main(String[] args) {
      SecureRandom generator = new SecureRandom();
      int[] data = generator.ints(10, 1, 101).toArray();
      System.out.printf("Unsorted array: %s%n%n", Arrays.toString(data));
      sort(data);
      System.out.printf("%nSorted array: %s%n", Arrays.toString(data));
   }   
}
