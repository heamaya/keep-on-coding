package chapter17.exercise20;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class GradesToLetters {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final List<Integer> grades = new ArrayList<>();
		
		for(int i = 1; i <= 10; i++) {
			System.out.printf("%s %d: ", "Enter grade", i);
			grades.add(scanner.nextInt());
		}
		
		scanner.close();
		
		grades.stream()
			.flatMap(map(grades))
			.forEach(action());
	}

	private static Function<Integer, Stream<Character>> map(final List<Integer> grades) {
		final Function<Integer, Stream<Character>> mappingFunction = (Integer grade) -> {
			Character letter = 'F';
			
			switch(grade / 10) {
				case 10:
				case 9: 
					letter = 'A';
					break;
				case 8:
					letter = 'B';
					break;
				case 7:
					letter = 'C';
					break;
				case 6:
					letter = 'D';
					break;
				default:
					letter = 'F';
					break;
			}
			
			return Stream.of(letter);
		};
		
		return mappingFunction;
	}
	
	private static Consumer<Character> action() {
		final Consumer<Character> actionConsumer = 
			letter -> System.out.printf("%s: %C%n", "Grade", letter);
		
		return actionConsumer;
	}
}
