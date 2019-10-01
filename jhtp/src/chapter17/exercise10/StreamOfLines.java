package chapter17.exercise10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StreamOfLines {

    public static void main(String[] args) throws IOException {
        final Pattern pattern = Pattern.compile("[A-Z]|[a-z]");
        Files.lines(Paths.get("src/chapter17/exercise10/Chapter2Paragraph.txt"))
            .flatMap(line -> pattern.matcher(line).results())
            .map(MatchResult::group)
            .collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()))
            .entrySet()
            .forEach(entry -> System.out.printf("%13s: %d%n", entry.getKey(), entry.getValue()));
    }

}
