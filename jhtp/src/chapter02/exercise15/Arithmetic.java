package chapter02.exercise15;

import java.util.Scanner;

public class Arithmetic {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        final int firstInt = scanner.nextInt();
        System.out.print("Enter another integer: ");
        final int secondInt = scanner.nextInt();
        System.out.printf("The sum is: %d%n", firstInt + secondInt);
        System.out.printf("The subtraction is: %d%n", firstInt - secondInt);
        System.out.printf("The product is: %d%n", firstInt * secondInt);
        System.out.printf("The division is: %s%n", firstInt / secondInt);
    }
}
