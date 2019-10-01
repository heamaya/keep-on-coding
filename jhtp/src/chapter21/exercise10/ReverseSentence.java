package chapter21.exercise10;

import chapter21.datastructures.Stack;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class ReverseSentence {

    public static void print(String sentence) {
        final Stack<String> stack = new Stack<>();
        Pattern.compile("[A-Z]?[a-z]*[']?[a-z]*")
            .matcher(sentence)
            .results()
            .map(MatchResult::group)
            .forEach(word -> stack.push(word));
        while (!stack.isEmpty()) {
            System.out.printf("%s ", stack.pop());
        }
    }

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        ReverseSentence.print(scanner.nextLine());
    }

}
