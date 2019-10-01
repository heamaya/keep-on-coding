package chapter14.exercise3;

import java.util.Scanner;

public class CompareStrings {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first string: ");
        final String string1 = scanner.next();
        System.out.print("Enter second string: ");
        final String string2 = scanner.next();
        final CompareStrings compareStrings = new CompareStrings();
        compareStrings.compare(string1, string2);
    }

    public void compare(String string1, String string2) {
        if (string1.compareTo(string2) == 0) {
            System.out.println("string1 and string2 are equal");
        }
        else if (string1.compareTo(string2) > 0) {
            System.out.println("string1 is greater than string2");
        }
        else {
            System.out.println("string1 is less than string2");
        }
    }

}
