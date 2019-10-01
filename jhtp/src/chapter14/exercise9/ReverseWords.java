package chapter14.exercise9;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReverseWords {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter sentence: ");
        final String sentence = scanner.nextLine();
        final ReverseWords reverseWords = new ReverseWords();
        System.out.printf("Reversed sentence:%n%s", reverseWords.transform(sentence));
    }

    public String transform(String sentence) {
        final var words = Arrays.asList(sentence.split("\\s+"));
        Collections.reverse(words);
        return words.stream()
            .collect(Collectors.joining(" "));
    }

}
