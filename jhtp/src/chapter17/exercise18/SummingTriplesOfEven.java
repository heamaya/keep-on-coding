package chapter17.exercise18;

import java.util.stream.IntStream;

public class SummingTriplesOfEven {

	public static void main(String[] args) {
		
		System.out.printf("%s: %d", "Sum of triples of even from 2 to 10", 
			IntStream.rangeClosed(2, 10)
				.map(x -> x % 2 == 0 ? 3 * x : 0)
				.sum()
		);

	}

}
 