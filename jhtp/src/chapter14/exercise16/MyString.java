package chapter14.exercise16;

public class MyString {

    private String string;

    public static void main(String [] args) {
        final MyString myString = new MyString("banana");
        System.out.printf("indexOf('a') for banana is: %d%n", myString.indexOf('a'));
        System.out.printf("lastIndexOf('a') for banana is: %d", myString.lastIndexOf('a'));
    }

    public MyString(String string) {
        this.string = string;
    }

    public int indexOf(char c) {
        final char [] charArray = string.toCharArray();
        int index = -1;
        boolean found = false;
        int i = 0;
        while (!found && i < charArray.length) {
            if (charArray[i] == c) {
                index = i;
                found = true;
            }
            i++;
        }
        return index;
    }

    public int lastIndexOf(char c) {
        final char [] charArray = string.toCharArray();
        int index = -1;
        boolean found = false;
        int i = charArray.length - 1;
        while (!found && i >= 0) {
            if (charArray[i] == c) {
                index = i;
                found = true;
            }
            i--;
        }
        return index;
    }

}
