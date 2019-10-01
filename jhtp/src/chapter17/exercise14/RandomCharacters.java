package chapter17.exercise14;

import java.security.SecureRandom;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RandomCharacters {
	
	public static void main(String[] args) {
		SecureRandom random = new SecureRandom();
		
		List<Character> characters1 = random.ints(30, 97, 123)
			.boxed()
			.map(i -> (char) i.intValue())
			.sorted()
			.collect(Collectors.toList());
		
		System.out.println(characters1);
		
		List<Character> characters2 = random.ints(30, 97, 123)
				.boxed()
				.map(i -> (char) i.intValue())
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
			
		System.out.println(characters2);
		
		List<Character> characters3 = random.ints(30, 97, 123)
				.distinct()
				.boxed()
				.map(i -> (char) i.intValue())
				.sorted()
				.collect(Collectors.toList());
			
		System.out.println(characters3);
	}

}