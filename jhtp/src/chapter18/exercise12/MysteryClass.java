package chapter18.exercise12;

public class MysteryClass {

    public static int mistery(int[] array2, int size) {
        if (size == 1) {
            return array2[0];
        }
        else {
            return array2[size - 1] + mistery(array2, size - 1);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int result = mistery(array, array.length);
        System.out.printf("Result is: %d%n", result);
    }

}
