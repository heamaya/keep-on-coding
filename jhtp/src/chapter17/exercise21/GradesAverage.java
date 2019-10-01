package chapter17.exercise21;

import java.util.Arrays;

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

		System.out.printf("%s: %.2f", "The average is", 
			Arrays.stream(studentsGrades)
				.flatMapToInt(Arrays::stream)
				.average()
				.getAsDouble()
		);
	}

}
