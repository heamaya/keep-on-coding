package chapter21.datastructures;

public class Stack<E> {

    private final List<E> stackList;

    public Stack() {
        stackList = new List<>("Stack");
    }

    public void push(E element) {
        stackList.insertAtFront(element);
    }

    public E pop() {
        return stackList.removeFromFront();
    }

    public E peek() {
        return stackList.getFirstNode().getElement();
    }

    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    public void print() {
        stackList.print();
    }

    public static void main(String [] args) {
        final Stack<Double> stack = new Stack<>();
        stack.push(1d);
        stack.print();
        stack.push(2d);
        stack.print();
        stack.pop();
        stack.print();
        stack.pop();
        stack.print();
    }
}
