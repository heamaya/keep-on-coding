package chapter16.exercise17;

import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SortWords {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        final SortWords sortWords = new SortWords();
        sortWords.print(scanner.nextLine());
    }

    public void print(String sentence) {
        final Pattern wordsPattern = Pattern.compile("[A-Z]?[a-z]*[']?[a-z]*");
        wordsPattern.matcher(sentence)
            .results()
            .map(matchResult -> matchResult.group().toLowerCase())
            .collect(Collectors.toCollection(TreeSet::new))
            .stream()
            .forEach(System.out::println);
    }

}
