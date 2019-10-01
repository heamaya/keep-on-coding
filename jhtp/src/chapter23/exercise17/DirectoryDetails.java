package chapter23.exercise17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DirectoryDetails {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a directory: ");
        final Path path = Paths.get(scanner.next());
        final Instant sequentialStartInstant = Instant.now();
        print(path, false);
        final Instant sequentialEndInstant = Instant.now();
        final long sequentialTime = Duration.between(sequentialStartInstant, sequentialEndInstant).toMillis();
        System.out.printf("%n%n%nSequential time: %d%n%n%n", sequentialTime);
        final Instant parallelStartInstant = Instant.now();
        print(path, true);
        final Instant parallelEndInstant = Instant.now();
        final long parallelTime = Duration.between(parallelStartInstant, parallelEndInstant).toMillis();
        System.out.printf("%n%n%nParallel time: %d%n%n%n", parallelTime);
    }

    public static void print(Path path, boolean parallel) {
        if (Files.isDirectory(path)) {
            try {
                StreamSupport.stream(Files.newDirectoryStream(path).spliterator(), parallel)
                    .map(p -> {
                        final String fileName = p.toFile().getName();
                        final int indexOfDot = fileName.indexOf('.');
                        return indexOfDot != -1 ? fileName.substring(indexOfDot + 1).toLowerCase() : "None";
                    })
                    .collect(Collectors.groupingBy(type -> type, TreeMap::new, Collectors.counting()))
                    .entrySet().forEach(
                        typeEntry -> System.out.printf("%s: %d%n", typeEntry.getKey(), typeEntry.getValue())
                );
            } catch (IOException ioException) {
                System.out.println("IO error reading directory");
            }
        } else {
            System.out.println("File is not a directory");
        }
    }
}
