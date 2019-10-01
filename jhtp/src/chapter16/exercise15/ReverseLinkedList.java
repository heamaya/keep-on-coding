package chapter16.exercise15;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ReverseLinkedList {

    private static final int SIZE = 10;
    private static final int ORIGIN = 97;
    private static final int BOUND = 122;

    public static void main(String [] args) {
        final SecureRandom secureRandom = new SecureRandom();
        final List<Character> characters = secureRandom.ints(SIZE, ORIGIN, BOUND)
            .mapToObj(i -> Character.valueOf((char) i))
            .collect(Collectors.toCollection(LinkedList::new));
        final ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        reverseLinkedList.run(characters);
    }

    public void run(List<Character> characters) {
        final List<Character> reversedCharacters = new LinkedList<>(characters);
        Collections.reverse(reversedCharacters);
        System.out.printf("The original list is: %s%n", characters);
        System.out.printf("The reversed list is: %s", reversedCharacters);
    }

}
