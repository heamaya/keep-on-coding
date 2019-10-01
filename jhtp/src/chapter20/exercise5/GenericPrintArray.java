package chapter20.exercise5;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenericPrintArray {

   private static final String DELIMITER = ", ";
   private static final String PREFIX = "[";
   private static final String SUFFIX = "]";

   public static void main(String[] args) {
      final Integer[] integerArray = {1, 2, 3, 4, 5};
      final Double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
      final Character[] characterArray = {'H', 'E', 'L', 'L', 'O'};
      System.out.printf("Array integerArray contains: ");
      print(integerArray);
      System.out.printf("Array doubleArray contains: ");
      print(doubleArray);
      System.out.printf("Array characterArray contains: ");
      print(characterArray);
      printSubscript(integerArray, -1, 3);
      printSubscript(doubleArray, 1, 10);
      printSubscript(characterArray, 3, 1);
      printSubscript(integerArray, 2, 4);
      printSubscript(doubleArray, 2, 5);
      printSubscript(characterArray, 3, 4);
   }

   public static <T> void printSubscript(T[] inputArray, int lowSubscript, int highSubscript) {
      try {
          System.out.printf("Array inputArray(%d, %d) contains: ", lowSubscript, highSubscript);
          print(inputArray, lowSubscript, highSubscript);
      } catch (InvalidSubscriptException invalidSubscriptException) {
         System.out.println(invalidSubscriptException);
      }
   }


   public static <T> void print(T[] inputArray) {
      System.out.println(Arrays.stream(inputArray)
          .map(Object::toString)
          .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX))
      );
   }

   public static <T> void print(T[] inputArray, int lowSubscript, int highSubscript) throws InvalidSubscriptException {
      if (lowSubscript < 0 || lowSubscript > inputArray.length - 1) {
         throw new InvalidSubscriptException("Low subscript is out of bound");
      }
      else if (highSubscript < 0 || highSubscript > inputArray.length - 1) {
         throw new InvalidSubscriptException("High subscript is out of bound");
      }
      else if (lowSubscript > highSubscript) {
         throw new InvalidSubscriptException("Low subscript is greater than High subscript");
      }
      System.out.println(IntStream.rangeClosed(lowSubscript, highSubscript)
          .mapToObj(i -> inputArray[i].toString())
          .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX))
      );
   }
}
