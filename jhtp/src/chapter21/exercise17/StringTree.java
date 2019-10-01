package chapter21.exercise17;

import chapter21.exercise16.Tree;

import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class StringTree {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        final String sentence = scanner.nextLine();
        final Tree<String> tree = new Tree<>();
        Pattern.compile("[A-Z]?[a-z]*[']?[a-z]+")
            .matcher(sentence)
            .results()
            .map(MatchResult::group)
            .forEach(word -> tree.insert(word));
        System.out.println("Pre Order Traversal:");
        tree.preOrderTraversal();
        System.out.println();
        System.out.println("In Order Traversal:");
        tree.inOrderTraversal();
        System.out.println();
        System.out.println("Pos Order Traversal:");
        tree.posOrderTraversal();
        System.out.println();
    }
}
