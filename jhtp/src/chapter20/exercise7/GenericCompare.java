package chapter20.exercise7;

public class GenericCompare {

    public static <T> boolean isEqualTo(T first, T second) {
        return first.equals(second);
    }

    public static void main(String [] args) {
        final Integer firstInteger = 1;
        final Integer secondInteger = 1;
        System.out.printf("%d is equal to %d: %b%n", firstInteger, secondInteger, GenericCompare.isEqualTo(firstInteger, secondInteger));
        final Double firstDouble = 1d;
        final Double secondDouble = 2d;
        System.out.printf("%.2f is equal to %.2f: %b%n", firstDouble, secondDouble, GenericCompare.isEqualTo(firstDouble, secondDouble));
        final String firstString = "first";
        final String secondString = "second";
        System.out.printf("%s is equal to %s: %b%n", firstString, secondString, GenericCompare.isEqualTo(firstString, secondString));
    }

}
