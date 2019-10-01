package chapter16.exercise16;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumber {

    public static void main(String [] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        final Integer number = scanner.nextInt();
        final PrimeNumber primeNumber = new PrimeNumber();
        if (primeNumber.is(number)) {
            System.out.printf("%d is a prime number", number);
        } else {
            final Set<Integer> primeFactors = new HashSet<>();
            primeNumber.primeFactors(number, primeFactors);
            System.out.printf(
                "%d is not a prime number; its prime factors are: %s",
                number,
                primeFactors.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "))
            );
        }
    }

    public boolean is(int number) {
        boolean isPrime;
        if(number == 1) {
            isPrime = false;
        } else if(number == 2) {
            isPrime = true;
        } else if(number > 2 && number % 2 == 0) {
            isPrime = false;
        } else {
            int top = (int) Math.sqrt(number) + 1;
            long count = IntStream.iterate(3, n -> n < top, n -> n + 2)
                .filter(n -> number % n == 0)
                .boxed()
                .count();
            isPrime = (count == 0);
        }
        return isPrime;
    }

    public void primeFactors(int number, Set<Integer> primeFactors) {
        final Set<Integer> divisors = getDivisors(number);
        if(divisors.size() > 0) {
            divisors.forEach(d -> primeFactors(d, primeFactors));
        } else {
            primeFactors.add(number);
        }
    }

    private Set<Integer> getDivisors(int number) {
        return IntStream.range(2, number)
            .filter(n -> number % n == 0)
            .boxed()
            .collect(Collectors.toSet());
    }

}
