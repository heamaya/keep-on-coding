package chapter14.exercise18;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextAnalysis {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        final String sentence = scanner.nextLine();
        final TextAnalysis textAnalysis = new TextAnalysis();
        System.out.println();
        textAnalysis.printLettersCount(sentence);
        System.out.println();
        textAnalysis.printWordsGroupedByLetterCountNumber(sentence);
        System.out.println();
        textAnalysis.printWordsCount(sentence);
    }

    public void printLettersCount(String sentence) {
        final Pattern letterPattern = Pattern.compile("[A-Za-z]");
        final Matcher letterMatcher = letterPattern.matcher(sentence);
        letterMatcher.results()
            .map(MatchResult::group)
            .collect(Collectors.groupingBy(letter -> letter.charAt(0), TreeMap::new, Collectors.counting()))
            .entrySet().stream()
            .forEach(e -> System.out.printf("%s: %d%n", e.getKey(), e.getValue()));
    }

    public void printWordsGroupedByLetterCountNumber(String sentence) {
        final Pattern wordPattern = Pattern.compile("[A-Za-z][a-z]*|[A-Za-z]['][a-z]+|['][a-z]+");
        final Matcher wordMatcher = wordPattern.matcher(sentence);
        wordMatcher.results()
            .map(matchResult -> matchResult.group().toLowerCase())
            .collect(Collectors.groupingBy(String::length, HashMap::new, Collectors.toSet()))
            .entrySet()
            .stream()
            .forEach(e -> System.out.printf("%d: %s%n", e.getKey(), e.getValue().stream().collect(Collectors.joining(", "))));
    }

    public void printWordsCount(String sentence) {
        final Pattern wordPattern = Pattern.compile("[A-Za-z][a-z]*|[A-Za-z]['][a-z]+|['][a-z]+");
        final Matcher wordMatcher = wordPattern.matcher(sentence);
        wordMatcher.results()
            .map(MatchResult::group)
            .collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()))
            .entrySet().stream()
            .forEach(e -> System.out.printf("%s: %d%n", e.getKey(), e.getValue()));
    }
}
