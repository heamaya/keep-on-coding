package chapter17.exercise23;

import java.util.Arrays;

public class TestPerson {
	private static final String JONES = "Jones";
	
	public static void main(String[] args) {
		Person persons[] = {
			new Person("Hernán", "Amaya"),
			new Person("Laura", "Zapata"),
			new Person("Norah", "Jones"),
			new Person("John", "Jones"),
			new Person("Laura", "Jones"),
		};
		
		System.out.println(
			Arrays.stream(persons)
				.filter(p -> JONES.equals(p.getLastName()))
				.findFirst()
				.get()
		);

	}

}
