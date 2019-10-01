package chapter14.exercise15;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.stream.IntStream;

public class CharactersFromIntegers {

    public static void main(String [] args) {
        try (PrintStream out = new PrintStream(System.out, true, "UTF-8")){
            IntStream.rangeClosed(0, 255)
                .forEach(i -> out.printf("%d: %c%n", i, (char) i));
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        }
    }

}
