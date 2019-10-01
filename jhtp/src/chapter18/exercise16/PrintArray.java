package chapter18.exercise16;

public class PrintArray {

    public static void main(String args[]) {
        final PrintArray printArray = new PrintArray();
        final Integer array[] = {1, 2, 3, 4, 5};
        printArray.print(array);
    }

    public <E> void print(E array[]) {
        printFrom(array, 0);
    }

    private <E> void printFrom(E array[], int index) {
        if (index < array.length) {
            System.out.printf("%s ", array[index]);
            printFrom(array, index + 1);
        }
    }

}
