package chapter18.exercise9;

public class Pow {

    public static void main(String args[]) {
        final long base = 2l;
        final long exponent = 3l;
        final Pow pow = new Pow();
        final long result = pow.calculate(base, exponent);
        System.out.printf("%d^%d = %d", base, exponent, result);
    }

    public long calculate(long base, long exponent) {
        if (exponent == 1) {
            return base;
        } else {
            return base * calculate(base, exponent - 1);
        }
    }

}
