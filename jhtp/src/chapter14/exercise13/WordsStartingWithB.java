package chapter14.exercise13;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WordsStartingWithB {

    private static final char B = 'b';
    private static final String COMMA = ", ";

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        final String sentence = scanner.nextLine();
        final WordsStartingWithB wordsStartingWithB = new WordsStartingWithB();
        System.out.printf("Words starting with b or B are: %s", wordsStartingWithB.filter(sentence));
    }

    public String filter(String sentence) {
        return Arrays.asList(sentence.split("\\s+"))
            .stream()
            .map(String::toLowerCase)
            .filter(word -> word.charAt(0) == B)
            .collect(Collectors.joining(COMMA));
    }

}
