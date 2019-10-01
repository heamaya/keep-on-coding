package chapter17.exercise22;

import java.util.Arrays;
import java.util.stream.Stream;

public class GradesAverage {

	public static void main(String[] args) {
		int [][] studentsGrades = {
			{100, 75, 90},
			{60, 80, 95},
			{50, 50, 50},
			{40, 60, 70},
			{100, 80, 90},
			{80, 80, 90},
			{70, 50, 90},
			{95, 78, 88},
			{100, 100, 100},
			{100, 80, 90},
		};

		Arrays.stream(studentsGrades)
			.flatMap(studentGrades -> 
				Stream.of(Arrays.stream(studentGrades).average()))
			.forEach(studentGradesAverage -> 
				System.out.printf("%.2f%n", studentGradesAverage.getAsDouble()));
	}

}
