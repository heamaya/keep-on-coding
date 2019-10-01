package chapter23.exercise18;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RandomIntStream {

   public static void main(String[] args) {
      final SecureRandom secureRandom = new SecureRandom();
      final Instant parallelStartInstant = Instant.now();
      parallel(secureRandom);
      final Instant parallelEndInstant = Instant.now();
      final long parallelTime = Duration.between(parallelStartInstant, parallelEndInstant).toMillis();
      System.out.printf("%n%n%nParallel time: %d%n%n%n", parallelTime);
      final Instant sequentialStartInstant = Instant.now();
      sequential(secureRandom);
      final Instant sequentialEndInstant = Instant.now();
      final long sequentialTime = Duration.between(sequentialStartInstant, sequentialEndInstant).toMillis();
      System.out.printf("%n%n%nSequential time: %d%n%n%n", sequentialTime);
   }

   public static void sequential(SecureRandom random) {
      System.out.printf("%-6s%s%n", "Face", "Frequency");
      random.ints(60_000_000, 1, 7)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(),
               Collectors.counting()))
            .forEach((face, frequency) ->
               System.out.printf("%-6d%d%n", face, frequency));
   }

   public static void parallel(SecureRandom random) {
      System.out.printf("%-6s%s%n", "Face", "Frequency");
      random.ints(60_000_000, 1, 7)
          .boxed()
          .parallel()
          .collect(Collectors.groupingBy(Function.identity(),
              Collectors.counting()))
          .forEach((face, frequency) ->
              System.out.printf("%-6d%d%n", face, frequency));
   }
}

