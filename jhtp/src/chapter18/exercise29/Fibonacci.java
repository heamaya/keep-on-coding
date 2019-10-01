package chapter18.exercise29;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.stream.Stream;

public class Fibonacci {

    private final HashMap<BigInteger, BigInteger> resultsMap = new HashMap<>();
    private BigInteger lastIndex = BigInteger.TWO;

    public static void main(String [] args) {
        final Fibonacci fibonacci = new Fibonacci();
        Stream.iterate(BigInteger.ZERO, n -> n.compareTo(BigInteger.valueOf(100)) <= 0, n -> n.add(BigInteger.ONE))
            .forEach(number -> System.out.printf("fibonacci[%d] = %d%n", number, fibonacci.calculate(number)));

    }

    public Fibonacci() {
        resultsMap.put(BigInteger.ZERO, BigInteger.ZERO);
        resultsMap.put(BigInteger.ONE, BigInteger.ONE);
    }

    public BigInteger calculate(BigInteger number) {
        if (resultsMap.get(number) == null) {
            Stream.iterate(lastIndex, n -> n.compareTo(number) <= 0, n -> n.add(BigInteger.ONE))
                .forEach(n ->
                    resultsMap.put(n, resultsMap.get(n.subtract(BigInteger.ONE))
                        .add(resultsMap.get(n.subtract(BigInteger.TWO)))));
            lastIndex = number;
        }
        return resultsMap.get(number);
    }

}
