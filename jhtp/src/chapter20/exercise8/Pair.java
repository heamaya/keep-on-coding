package chapter20.exercise8;

public class Pair<F,S> {
    private F first;
    private S second;

    public Pair() {
    }

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return String.format("{first=%s, second=%s}", first, second);
    }

    public static void main(String [] args) {
        final Pair<Integer, String> firstPair = new Pair<>(1, "Hello");
        System.out.println(firstPair);
        final Pair<String, String> secondPair = new Pair<>("Good Bye", "Hello");
        System.out.println(secondPair);
        final Pair<Long, Double> thirdPair = new Pair<>(123456789l, 7.56d);
        System.out.println(thirdPair);
    }
}
