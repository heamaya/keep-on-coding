package chapter19.exercise8;

import java.security.SecureRandom;
import java.util.Arrays;

public class RecursiveLinearSearch {

    private static final int STREAM_SIZE = 10;
    private static final int RANDOM_NUMBER_ORIGIN = 1;
    private static final int RANDOM_NUMBER_BOUND = 101;

    public static int search(int data[], int element, int index) {
        if(index == data.length){
            return -1;
        } else if(data[index] == element) {
            return index;
        }
        return search(data, element, ++index);
    }

    public static void main(String args[]) {
        SecureRandom secureRandom = new SecureRandom();
        int data[] = secureRandom.ints(STREAM_SIZE, RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND).toArray();
        System.out.printf("array: %s%n%n", Arrays.toString(data));
        int elementToSearch = data[secureRandom.nextInt(STREAM_SIZE - 1)];
        System.out.printf("element to search: %d%n", elementToSearch);
        int searchIndex = search(data, elementToSearch, 0);
        System.out.printf("Element index: %d%n%n", searchIndex);
        int elementNotPresentToSearch = 105;
        System.out.printf("element to search: %d%n", elementNotPresentToSearch);
        int notFoundIndex = search(data, elementNotPresentToSearch, 0);
        System.out.printf("Element index: %d%n", notFoundIndex);
    }
}
