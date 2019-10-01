package chapter18.exercise28;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Factorial {

    public static void main(String [] args) {
        final Factorial factorial = new Factorial();
        IntStream.rangeClosed(0, 50)
            .mapToObj(BigInteger::valueOf)
            .forEach(n -> System.out.printf("%d! = %d%n", n, factorial.calculate(n)));
    }

    public BigInteger calculate(BigInteger number) {
        final BigInteger result;
        if (number.equals(BigInteger.ZERO)) {
            result = BigInteger.ZERO;
        }
        else {
            result = Stream.iterate(BigInteger.ONE, n -> n.compareTo(number) <= 0, n -> n.add(BigInteger.ONE))
                .reduce(BigInteger.ONE, BigInteger::multiply);
        }
        return result;
    }
}
