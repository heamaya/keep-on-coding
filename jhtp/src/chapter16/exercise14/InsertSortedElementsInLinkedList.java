package chapter16.exercise14;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class InsertSortedElementsInLinkedList {

    public static final int NUMBERS_SIZE = 25;
    public static final int NUMBER_ORIGIN = 0;
    public static final int NUMBER_BOUND = 100;
    final private SecureRandom secureRandom;

    public InsertSortedElementsInLinkedList() {
        secureRandom = new SecureRandom();
    }

    public static void main(String [] args) {
        final InsertSortedElementsInLinkedList insertSortedElementsInLinkedList = new InsertSortedElementsInLinkedList();
        System.out.println("Sorted order insert insto a linked list:%n");
        insertSortedElementsInLinkedList.init();
    }

    private void init() {
        final List<Integer> numbers = new LinkedList<>();
        double sum = 0;
        for (int i = NUMBER_ORIGIN; i < NUMBERS_SIZE; i++) {
            final Integer number = secureRandom.nextInt(NUMBER_BOUND);
            if(numbers.size() == 0) {
                numbers.add(number);
            }
            else {
                final ListIterator<Integer> listIterator = numbers.listIterator();
                Integer currentNumber;
                do {
                    currentNumber = listIterator.next();
                } while (listIterator.hasNext() && number > currentNumber);
                if (listIterator.hasNext() && number < currentNumber) {
                    listIterator.previous();
                    listIterator.add(number);
                }
                else if (listIterator.hasNext()) {
                    listIterator.add(number);
                }
                else if (listIterator.hasPrevious() && number < listIterator.previous()) {
                    listIterator.add(number);
                } else {
                    numbers.add(number);
                }
            }
            sum += number;
            System.out.printf("Current number: %d%nCurrent linked list: %s%n", number, numbers);
        }
        System.out.printf("Average: %.2f", sum / (double) NUMBERS_SIZE);
    }

}
