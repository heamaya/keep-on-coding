package chapter14.exercise14;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WordsEndingWithEd {

    private static final String ED = "ed";
    private static final String COMMA = ", ";

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        final String sentence = scanner.nextLine();
        final WordsEndingWithEd wordsEndingWithEd = new WordsEndingWithEd();
        System.out.printf("Words ending with ed or ED are: %s", wordsEndingWithEd.filter(sentence));
    }

    public String filter(String sentence) {
        return Arrays.asList(sentence.split("\\s+"))
            .stream()
            .map(String::toLowerCase)
            .filter(word -> word.endsWith(ED))
            .collect(Collectors.joining(COMMA));
    }

}
