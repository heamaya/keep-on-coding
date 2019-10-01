package chapter14.exercise10;

import java.util.Scanner;

public class UppercaseLowercaseString {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter sentence: ");
        final String sentence = scanner.nextLine();
        final UppercaseLowercaseString uppercaseLowercaseString = new UppercaseLowercaseString();
        uppercaseLowercaseString.display(sentence);
    }

    public void display(String sentence) {
        System.out.printf("Sentence in uppercase: %s%nSentence in lowercase: %s", sentence.toUpperCase(), sentence.toLowerCase());
    }

}
