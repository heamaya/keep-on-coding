package chapter14.exercise7;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PigLatin {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a paragraph: ");
        final String paragraph = scanner.nextLine();
        final PigLatin pigLatin = new PigLatin();
        System.out.printf("The paragraph in Pig Latin is:%n%s", pigLatin.translate(paragraph));
    }

    public String translate(String paragraph) {
        final var words = paragraph.split("\\s+");
        return Arrays.asList(words)
            .stream()
            .filter(word -> word.length() > 1)
            .map(word -> {
                final StringBuilder pigLatinBuilder = new StringBuilder(word);
                final char firstLetter = word.charAt(0);
                for (int c = 1; c < word.length(); c++) {
                    pigLatinBuilder.setCharAt(c - 1, pigLatinBuilder.charAt(c));
                }
                pigLatinBuilder.setCharAt(word.length() - 1, firstLetter);
                pigLatinBuilder.append("ay");
                return pigLatinBuilder.toString();
            })
            .collect(Collectors.joining(" "));
    }

}
