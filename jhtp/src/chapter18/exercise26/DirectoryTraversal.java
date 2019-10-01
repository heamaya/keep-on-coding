package chapter18.exercise26;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DirectoryTraversal {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a directory path: ");
        final DirectoryTraversal directoryTraversal = new DirectoryTraversal();
        final Map<String, Map<String, Integer>> directoryMap = new TreeMap<>();
        directoryTraversal.walk(scanner.next(), directoryMap);
        directoryMap.entrySet().forEach(directoryEntry -> {
            System.out.println(directoryEntry.getKey());
            var typesMap = directoryEntry.getValue();
            typesMap.entrySet().forEach(typeEntry -> {
                System.out.printf("%s: %d%n", typeEntry.getKey(), typeEntry.getValue());
            });
        });
    }

    public void walk(String directory, Map<String, Map<String, Integer>> directoryMap) {
        final Path path = Paths.get(directory);
        if (Files.exists(path) && Files.isDirectory(path)) {
            try {
                var typesMap = Files.walk(path)
                    .filter(Files::isRegularFile)
                    .map(p -> p.toFile().getName())
                    .collect(Collectors.groupingBy(
                        f -> {
                            final int indexOfDot = f.indexOf(".");
                            final String type;
                            if (indexOfDot == -1) {
                                type = "none";
                            } else {
                                type = f.substring(indexOfDot + 1);
                            }
                            return type.toLowerCase();
                        },
                        TreeMap::new,
                        Collectors.counting()
                    ));
                directoryMap.put(path.toFile().getAbsolutePath(), (Map) typesMap);
            } catch (IOException ioException) {
                System.out.println(ioException);
            }
        }
        else if (Files.exists(path)) {
            System.out.println("The path is a regular file!");
        } else {
            System.out.println("The path does not exist!");
        }
    }

}
