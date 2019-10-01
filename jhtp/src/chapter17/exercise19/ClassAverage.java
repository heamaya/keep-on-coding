package chapter17.exercise19;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClassAverage {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Integer> grades = new ArrayList<Integer>();
		
		for(int i = 1; i <= 10; i++) {
			System.out.print("Enter grade " + i + ": ");
			grades.add(scanner.nextInt());
		}
		
		scanner.close();
		
		System.out.printf("%s: %d%n", "Grades sum is", 
				grades.stream()
					.collect(Collectors.summingInt(Integer::intValue)));
		
		System.out.printf("%s: %.2f%n", "Grades average is", 
			grades.stream()
				.collect(Collectors.averagingDouble(Integer::intValue)));

	}

}
