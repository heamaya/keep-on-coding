package chapter18.exercise17;

public class PrintArrayBackward {

    public static void main(String args[]) {
        final String array[] = {"?", "doing", "you", "are", "how", "Hello,"};
        final PrintArrayBackward printArrayBackward = new PrintArrayBackward();
        printArrayBackward.printBackward(array);
    }

    public <E> void printBackward(E array[]) {
        printBackwardFrom(array, array.length - 1);
    }

    private <E> void printBackwardFrom(E array[], int index) {
        if (index >= 0) {
            System.out.printf("%s ", array[index]);
            printBackwardFrom(array, index - 1);
        }
    }

}
