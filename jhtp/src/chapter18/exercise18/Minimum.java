package chapter18.exercise18;

import java.util.Arrays;

public class Minimum {

    public static void main(String args[]) {
        final Minimum minimum = new Minimum();
        final int array[] = {10, 5, 7, 8, 1, 6, 4};
        final int index = minimum.find(array);
        System.out.printf("The minimum value of array %s is %s at index %s", Arrays.toString(array), array[index], index);
    }

    public int find(int array[]) {
        return findMinimumFrom(array, 0, 0);
    }

    private int findMinimumFrom(int[] array, int minimumIndex, int index) {
        if (index < array.length) {
            if (array[index] < array[minimumIndex]) {
                minimumIndex = index;
            }
            return findMinimumFrom(array, minimumIndex, ++index);
        } else {
            return minimumIndex;
        }
    }
}
