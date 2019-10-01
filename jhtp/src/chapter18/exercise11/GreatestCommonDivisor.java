package chapter18.exercise11;

public class GreatestCommonDivisor {

	public static void main(String[] args) {
		final long x = 27l;
		final long y= 21l;
		final GreatestCommonDivisor greatestCommonDivisor = new GreatestCommonDivisor();
		final long result = greatestCommonDivisor.calculate(x, y);
		System.out.printf("x = %d%ny = %d%nGCD = %d", x, y, result);
	}

	public long calculate(long x, long y) {
		if (y == 0) {
			return x;
		}
		return calculate(y, x % y);
	}
}
