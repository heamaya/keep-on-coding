package chapter23.exercise15;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StreamOfLines {

    private static final String PATH = "src"
        .concat(File.separator)
        .concat("chapter23")
        .concat(File.separator)
        .concat("exercise15")
        .concat(File.separator)
        .concat("Chapter2Paragraph.txt");

    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("\\s+");
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
        final Map<String, Long> wordCounts =
           Files.lines(Paths.get(PATH))
                .flatMap(line -> pattern.splitAsStream(line))
                .collect(Collectors.groupingBy(String::toLowerCase,
                   TreeMap::new, Collectors.counting()));
        wordCounts.entrySet()
           .stream()
           .collect(
              Collectors.groupingBy(entry -> entry.getKey().charAt(0),
                 TreeMap::new, Collectors.toList()))
           .forEach((letter, wordList) -> {
              System.out.printf("%n%C%n", letter);
              wordList.stream().forEach(word -> System.out.printf(
                 "%13s: %d%n", word.getKey(), word.getValue()));
           });
    }

    public static void parallel(Pattern pattern) throws IOException {
        final Map<String, Long> wordCounts =
            Files.lines(Paths.get(PATH))
                .parallel()
                .flatMap(line -> pattern.splitAsStream(line))
                .collect(Collectors.groupingBy(String::toLowerCase,
                    TreeMap::new, Collectors.counting()));
        wordCounts.entrySet()
            .stream()
            .parallel()
            .collect(
                Collectors.groupingBy(entry -> entry.getKey().charAt(0),
                    TreeMap::new, Collectors.toList()))
            .forEach((letter, wordList) -> {
                System.out.printf("%n%C%n", letter);
                wordList.stream().forEach(word -> System.out.printf(
                    "%13s: %d%n", word.getKey(), word.getValue()));
            });
    }
}

