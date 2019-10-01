package chapter14.exercise4;

import java.util.Scanner;

public class CompareStringPortions {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first string: ");
        final String string1 = scanner.next();
        System.out.print("Enter second string: ");
        final String string2 = scanner.next();
        System.out.print("Enter start index for comparison: ");
        final int startIndex = scanner.nextInt();
        System.out.print("Enter number of characters to compare: Banana");
        final int charactersToCompare = scanner.nextInt();
        final CompareStringPortions compareStringPortions = new CompareStringPortions();
        compareStringPortions.compare(string1, string2, startIndex, charactersToCompare);
    }

    public void compare(String string1, String string2, int startIndex, int charactersToCompare) {
        if (string1.regionMatches(true, startIndex, string2, startIndex, charactersToCompare)) {
            System.out.println("string1 and string2 portions are equal");
        }
        else {
            System.out.println("string1 and string2 portions are not equal");
        }
    }

}
