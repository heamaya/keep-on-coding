package chapter20.exercise4;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SelectionSort {

    private static final long SIZE = 10l;
    private static final int INT_NUMBER_ORIGIN = 10;
    private static final int INT_NUMBER_BOUND = 99;
    private static final double DOUBLE_NUMBER_ORIGIN = 10d;
    private static final double DOUBLE_NUMBER_BOUND = 99d;
    private static final long LONG_NUMBER_ORIGIN = 10l;
    private static final long LONG_NUMBER_BOUND = 99l;
    private static final String COMMA = ", ";
    private static final String OPENING_BRACKET = "[";
    private static final String CLOSING_BRACKET = "]";

    public <T extends Comparable<T>> void start(T [] elements) {
        for (int pass = 0; pass < elements.length - 1; pass++) {
            int smallest = pass;
            for (int index = pass + 1; index < elements.length; index++) {
                if (elements[index].compareTo(elements[smallest]) < 0) {
                    smallest = index;
                }
            }
            swap(elements, pass, smallest);
            printPass(elements, pass + 1, smallest);
        }
    }

    private <T> void swap(T [] elements, int first, int second) {
        final T auxiliar = elements[first];
        elements[first] = elements[second];
        elements[second] = auxiliar;
    }

    private <T> void printPass(T [] elements, int pass, int index) {
        System.out.printf("after pass %2d: ", pass);
        for (int i = 0; i < index; i++) {
            printElement(elements[i], false);
        }
        printElement(elements[index], true);
        for (int i = index + 1; i < elements.length; i++) {
            printElement(elements[1], false);
        }
        System.out.printf("%n               ");
        for (int j = 0; j < pass; j++) {
            if (elements[j] instanceof Double) {
                System.out.print("--      ");
            }
            else {
                System.out.print("--  ");
            }
        }
        System.out.println();
    }

    private <T> void printElement(T element, boolean starred) {
        if (element instanceof Double && starred) {
            System.out.printf("%.2f*  ", element);
        }
        else if (element instanceof Double) {
            System.out.printf("%.2f*  ", element);
        }
        else if(starred) {
            System.out.printf("%s*  ", element);
        } else {
            System.out.printf("%s  ", element);
        }
    }

    public static void main(String[] args) {
        final SelectionSort selectionSort = new SelectionSort();
        final SecureRandom secureRandom = new SecureRandom();
        final Integer [] elements1 = secureRandom.ints(SIZE, INT_NUMBER_ORIGIN, INT_NUMBER_BOUND)
            .boxed()
            .toArray(Integer[]::new);
        System.out.printf("Unsorted Integer array: %s%n%n", Arrays.toString(elements1));
        selectionSort.start(elements1);
        System.out.printf("%nSorted Integer array: %s%n", Arrays.toString(elements1));
        System.out.println("_______________________________________________________________________________________________");
        final Double [] elements2 = secureRandom.doubles(SIZE, DOUBLE_NUMBER_ORIGIN, DOUBLE_NUMBER_BOUND)
            .boxed()
            .toArray(Double[]::new);
        System.out.printf(
            "Unsorted Double array: %s%n%n",
            Arrays.stream(elements2).map(e -> String.format("%.2f", e))
                .collect(Collectors.joining(COMMA, OPENING_BRACKET, CLOSING_BRACKET))
        );
        selectionSort.start(elements2);
        System.out.printf("%nSorted Double array: %s%n",
            Arrays.stream(elements2).map(e -> String.format("%.2f", e))
                .collect(Collectors.joining(COMMA, OPENING_BRACKET, CLOSING_BRACKET))
        );
        System.out.println("_______________________________________________________________________________________________");
        final Long [] elements3 = secureRandom.longs(SIZE, LONG_NUMBER_ORIGIN, LONG_NUMBER_BOUND)
            .boxed()
            .toArray(Long[]::new);
        System.out.printf("Unsorted Long array: %s%n%n", Arrays.toString(elements3));
        selectionSort.start(elements3);
        System.out.printf("%nSorted Long array: %s%n", Arrays.toString(elements3));
        System.out.println("_______________________________________________________________________________________________");
    }

}
