package chapter17.exercise13;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DuplicateWordRemoval {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of lines the text will have: ");
		int linesNumber = Integer.valueOf(input.nextLine());
		
		String[] text = new String[linesNumber];
		
		int linesCount = 1;
		
		while(linesCount <= linesNumber) {
			System.out.print("Enter line " + linesCount + ": ");
			text[linesCount - 1] = input.nextLine();
			linesCount++;
		}

		input.close();
		
		uniqueWordsAlphabetically(text);
	}
	
	private static void uniqueWordsAlphabetically(String[] text) {
		Pattern pattern = Pattern.compile("\\s+");
		
		System.out.println("Distinct words sorted alphabetically are: ");
		
		Arrays.stream(text)
			.flatMap(line -> pattern.splitAsStream(line))
			.map(String::toLowerCase)
			.distinct()
			.sorted()
			.forEach(System.out::println);
	}

}
