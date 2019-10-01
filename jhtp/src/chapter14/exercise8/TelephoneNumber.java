package chapter14.exercise8;

import java.util.Scanner;

public class TelephoneNumber {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter phone number: ");
        final String aTelephoneNumber = scanner.nextLine();
        final TelephoneNumber telephoneNumber = new TelephoneNumber();
        telephoneNumber.print(aTelephoneNumber);
    }

    public void print(String telephoneNumber) {
        if (!telephoneNumber.matches("[(][0-9]{3}[)]\\s{1}[0-9]{3}[-][0-9]{4}")) {
            System.out.println("Invalid format telephone number. It should be for instance (012) 345-6789");
        }
        else {
            final var tokens = telephoneNumber.replaceAll("[(]", "")
                .replaceAll("[)]", "")
                .replaceAll("[-]", "")
                .split("\\s{1}");
            System.out.printf("Area code is: %s%nNumber: %s%n", tokens[0], tokens[1]);
        }
    }

}
