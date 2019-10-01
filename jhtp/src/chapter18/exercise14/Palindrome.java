package chapter18.exercise14;

public class Palindrome {

	public static void main(String[] args) {
		final Palindrome palindrome = new Palindrome();
		final String word1 = "oso";
		final String word2 = "ojo";
		final String word3 = "anana";
		final String word4 = "manzana";
		System.out.println("La palabra " + word1 + (palindrome.is(word1) ? " es" : " no es") + " un palíndromo");
		System.out.println("La palabra " + word2 + (palindrome.is(word2) ? " es" : " no es") + " un palíndromo");
		System.out.println("La palabra " + word3 + (palindrome.is(word3) ? " es" : " no es") + " un palíndromo");
		System.out.println("La palabra " + word4 + (palindrome.is(word4) ? " es" : " no es") + " un palíndromo");
	}

	public boolean is(String word) {
		return check(word.toCharArray(), 0);
	}

	private boolean check(char [] letters, int index) {
		boolean result = false;
		int mirrorIndex = letters.length - index - 1;
		if (letters[index] == letters[mirrorIndex]) {
			if (index < mirrorIndex) {
				return check(letters, ++index);
			}
			else {
				result = true;
			}
		}
		return result;
	}
}
