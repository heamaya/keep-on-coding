package chapter16.exercise13;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DuplicateWords {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        final String sentence = scanner.nextLine();
        final DuplicateWords duplicateWords = new DuplicateWords();
        System.out.printf("The number of duplicate words in sentence is: %d", duplicateWords.count(sentence));
    }

    public long count(String sentence) {
        final Pattern wordPattern = Pattern.compile("[A-Za-z][a-z]*|[A-Za-z]['][a-z]+|['][a-z]+");
        final Matcher wordMatcher = wordPattern.matcher(sentence);
        return wordMatcher.results()
            .map(MatchResult::group)
            .collect(Collectors.groupingBy(String::toLowerCase, HashMap::new, Collectors.counting()))
            .entrySet().stream()
            .filter(e -> e.getValue() == 2l)
            .count();
    }

}
