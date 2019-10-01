package chapter14.exercise11;

import java.util.Scanner;

public class SearchCharacterInString {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter sentence: ");
        final String sentence = scanner.nextLine();
        System.out.print("Enter character to search: ");
        final char character = Character.valueOf(scanner.nextLine().charAt(0));
        final SearchCharacterInString searchCharacterInString = new SearchCharacterInString();
        System.out.printf("Number of count of character: %s in %s is %d", character, sentence, searchCharacterInString.count(sentence, character));
    }

    public int count(String sentence, char character) {
        int currentIndex = 0;
        int count = 0;
        while (currentIndex != -1) {
            currentIndex = sentence.indexOf(character, currentIndex);
            if (currentIndex != -1) {
                currentIndex++;
                count++;
            }
        }
        return count;
    }

}
