package chapter20.exercise6;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PrintStringArray {

   private static final String DELIMITER = ", ";
   private static final String PREFIX = "[";
   private static final String SUFFIX = "]";

   public static void main(String[] args) {
      final String [] stringArray = {"Hello", "how", "are", "you", "doing", "right", "now", "?"};
      System.out.printf("Array stringArray contains: ");
      print(stringArray);
   }

   public static <T> void print(T[] inputArray) {
      System.out.println(Arrays.stream(inputArray)
          .map(Object::toString)
          .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX))
      );
   }

   public static void print(String [] inputArray) {
      System.out.println();
      for (int i = 0; i < inputArray.length; i++) {
         System.out.printf("%-10s", inputArray[i]);
         if ((i + 1) % 4 == 0) {
            System.out.println();
         }
      }
   }

}
