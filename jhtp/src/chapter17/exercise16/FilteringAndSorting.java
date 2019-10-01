package chapter17.exercise16;

import java.security.SecureRandom;

public class FilteringAndSorting {
	
	public static void main(String [] args) {
		SecureRandom random = new SecureRandom();
		
		random.ints(50, 1, 1000)
			.filter(i -> i % 2 != 0)
			.sorted()
			.forEach(System.out::println);
	}

}
