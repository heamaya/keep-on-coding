package chapter23.exercise16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.TreeMap;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StreamOfLines {

    public static void main(String[] args) throws IOException {
        final Pattern pattern = Pattern.compile("[A-Z]|[a-z]");
        final Instant sequentialStartInstant = Instant.now();
        sequential(pattern);
        final Instant sequentialEndInstant = Instant.now();
        final long sequentialTime = Duration.between(sequentialStartInstant, sequentialEndInstant).toMillis();
        System.out.printf("%n%n%nSequential time: %d%n%n%n", sequentialTime);
        final Instant parallelStartInstant = Instant.now();
        parallel(pattern);
        final Instant parallelEndInstant = Instant.now();
        final long parallelTime = Duration.between(parallelStartInstant, parallelEndInstant).toMillis();
        System.out.printf("%n%n%nParallel time: %d%n%n%n", parallelTime);
    }

    public static void sequential(Pattern pattern) throws IOException {
        Files.lines(Paths.get("src/chapter17/exercise10/Chapter2Paragraph.txt"))
            .flatMap(line -> pattern.matcher(line).results())
            .map(MatchResult::group)
            .collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()))
            .entrySet()
            .forEach(entry -> System.out.printf("%13s: %d%n", entry.getKey(), entry.getValue()));
    }

    public static void parallel(Pattern pattern) throws IOException {
        Files.lines(Paths.get("src/chapter17/exercise10/Chapter2Paragraph.txt"))
            .parallel()
            .flatMap(line -> pattern.matcher(line).results())
            .map(MatchResult::group)
            .collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()))
            .entrySet()
            .forEach(entry -> System.out.printf("%13s: %d%n", entry.getKey(), entry.getValue()));
    }

}
