package chapter23.exercise19;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntStreamOperations {

   public static void main(String[] args) {
      int[] values = {3, 10, 6, 1, 4, 8, 2, 5, 9, 7};
      System.out.print("Original values: ");
      System.out.println(
          IntStream.of(values)
              .mapToObj(String::valueOf)
              .collect(Collectors.joining(" ")));
      System.out.printf("Sum of squares via map and sum: %d%n%n",
          IntStream.of(values)
              .map(x -> x * x)
              .sum());
      System.out.printf("Product via sequential reduce method: %d%n",
          IntStream.of(values)
              .reduce((x, y) -> x + y * y).getAsInt());
      System.out.printf("Product via parallel reduce method: %d%n",
          IntStream.of(values)
              .parallel()
              .reduce((x, y) -> x + y * y).getAsInt());
   }

}
