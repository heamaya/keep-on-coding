package chapter17.exercise24;

import java.util.Scanner;
import java.util.stream.LongStream;

public class PrimeNumbers {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter n: ");
		
		final long n = scanner.nextLong();
		
		scanner.close();
		
		System.out.println("Prime numbers are: ");
		
		LongStream.iterate(0, i -> i + 1)
			.limit(n)
			.filter(PrimeNumbers::isPrime)
			.forEach(System.out::println);
	}
	
	private static boolean isPrime(final long number) {
		boolean isPrime = false;
		
		if(number == 0 || number == 1) {
			isPrime = false;
		} else if(number == 2) {
			isPrime = true;
		} else if(number > 2 && number % 2 == 0) {
			isPrime = false;
		} else {
			long top = (long) Math.sqrt(number) + 1;
			
			long count = LongStream.iterate(3, i -> i <= top, i -> i + 2)
				.filter(i -> number % i == 0)
				.count();
			
			isPrime = count == 0;
		}
		
		return isPrime;
	}

}
