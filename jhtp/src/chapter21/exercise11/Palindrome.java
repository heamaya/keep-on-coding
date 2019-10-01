package chapter21.exercise11;

import chapter21.datastructures.Stack;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Palindrome {

    private static boolean isWord(String word) {
        final List<Character> characters = Pattern.compile("[A-Z]|[a-z]|[']")
            .matcher(word)
            .results()
            .map(matchResult -> Character.valueOf(matchResult.group().toLowerCase().charAt(0)))
            .collect(Collectors.toList());
        final Stack<Character> stack = new Stack<>();
        characters.forEach(character -> stack.push(character));
        return characters.stream()
            .allMatch(character -> character.equals(stack.pop()));
    }

    public static boolean isSentence(String sentence) {
        return Pattern.compile("[A-Z]?[a-z]*[']?[a-z]*")
            .matcher(sentence)
            .results()
            .map(matchResult -> matchResult.group().toLowerCase())
            .allMatch(Palindrome::isWord);
    }

    public static void main(String [] args) {
        final String string1 = "Anana, eye; noon";
        final String string2 = "To be, or not to be: that is the question";
        System.out.printf("%s is palindrome? %b%n", string1, Palindrome.isSentence(string1));
        System.out.printf("%s is palindrome? %b%n", string2, Palindrome.isSentence(string2));
    }

}
