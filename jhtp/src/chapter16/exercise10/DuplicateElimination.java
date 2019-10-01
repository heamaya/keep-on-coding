package chapter16.exercise10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class DuplicateElimination {

    private static final String EMPTY = "";
    private static final String QUIT = "q";
    private final SortedSet<String> names;

    public DuplicateElimination() {
        names = new TreeSet<>();
    }

    public static void main(String [] args) {
        new DuplicateElimination().start();
    }

    public void start() {
        final Scanner scanner = new Scanner(System.in);
        String name = EMPTY;
        while (!name.equals(QUIT)) {
            System.out.print("Enter a name (q to quit): ");
            name = scanner.next();
            if (!name.equals(QUIT)) {
                names.add(name);
            }
        }
        String searchedName = "";
        while (!searchedName.equals(QUIT)) {
            System.out.print("Enter a name to search (q to quit): ");
            searchedName = scanner.next();
            if (!searchedName.equals(QUIT)) {
                final int index = Collections.binarySearch(new ArrayList<>(names), searchedName);
                System.out.printf("Name: %s has index: %d%n", searchedName, index);
            }
        }
    }
}
