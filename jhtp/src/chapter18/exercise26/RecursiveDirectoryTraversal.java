package chapter18.exercise26;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RecursiveDirectoryTraversal {

    public static void main(String [] main) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a directory: ");
        final String fileName = scanner.next();
        final RecursiveDirectoryTraversal recursiveDirectoryTraversal = new RecursiveDirectoryTraversal();
        final Map<String, Map<String, Long>> directoryMap = new TreeMap<>();
        recursiveDirectoryTraversal.listFiles(fileName, 0, directoryMap);
        directoryMap.entrySet().forEach(directoryEntry -> {
            System.out.println(directoryEntry.getKey());
            var typesMap = directoryEntry.getValue();
            typesMap.entrySet().forEach(typeEntry -> {
                System.out.printf("%s: %d%n", typeEntry.getKey(), typeEntry.getValue());
            });
        });
    }

    public void listFiles(String fileName, int level, Map<String, Map<String, Long>> directoryMap) {
        final File file = new File(fileName);
        if (file.exists() && file.isDirectory()) {
            final File [] files = file.listFiles();
            final var typesMap = Arrays.stream(files)
                .filter(File::isFile)
                .collect(Collectors.groupingBy(
                    f -> {
                        final int indexOfDot = f.getName().indexOf(".");
                        return indexOfDot == -1 ? "none" : f.getName().substring(indexOfDot + 1).toLowerCase();
                    },
                    TreeMap::new,
                    Collectors.counting())
                );
            File parent = file;
            for (int l = 1; l <= level; l++) {
                parent = parent.getParentFile();
            }
            directoryMap.compute(
                parent.getAbsolutePath(),
                (key, value) -> {
                    if (value == null) {
                        value = typesMap;
                    }
                    else {
                        for (String k : typesMap.keySet()) {
                            value.merge(k, typesMap.get(k), (value1, value2) -> value1 + value2);
                        }
                    }
                    return value;
                });
            final int nextLevel = level + 1;
            Arrays.stream(files)
                .filter(File::isDirectory)
                .forEach(f -> listFiles(f.getAbsolutePath(), nextLevel, directoryMap));
        }
        else if (file.exists()) {
            System.out.println("The path is a regular file!");
        } else {
            System.out.println("The path does not exist!");
        }
    }

}
