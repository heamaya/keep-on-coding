package chapter14.exercise12;

import chapter14.exercise11.SearchCharacterInString;
import java.util.Scanner;
import java.util.stream.IntStream;

public class NumberOfAlphabetLetters {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter sentence: ");
        final String sentence = scanner.nextLine();
        final NumberOfAlphabetLetters numberOfAlphabetLetters = new NumberOfAlphabetLetters();
        System.out.println(numberOfAlphabetLetters.count(sentence));
    }

    public String count(String sentence) {
        final StringBuilder result = new StringBuilder();
        final String s = sentence.toLowerCase();
        final SearchCharacterInString searchCharacterInString = new SearchCharacterInString();
        IntStream.rangeClosed(97, 122)
            .forEach(index -> {
                char letter = (char) index;
                result.append(String.format("%s: %d%n", letter, searchCharacterInString.count(s, letter)));
            });
        return result.toString();
    }
}
