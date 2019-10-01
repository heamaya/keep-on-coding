package chapter21.exercise27;

import chapter21.datastructures.ListNode;

import java.util.NoSuchElementException;

public class List<E> {

    private String name;
    private ListNode<E> firstNode;

    public List(String name) {
        this.name = name;
    }

    public boolean isEmpty() {
        return firstNode == null;
    }

    public void insertAtFront(E element) {
        final ListNode<E> newNode = new ListNode<>(element);
        if (isEmpty()) {
            firstNode = newNode;
        }
        else {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
        }
    }

    public void insertAtBack(E element) {
        final ListNode<E> newNode = new ListNode<>(element);
        if (isEmpty()) {
            firstNode = newNode;
        }
        else {
            ListNode<E> currentNode = firstNode;
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(newNode);
        }
    }

    public E removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final E element = firstNode.getElement();
        if (firstNode.getNextNode() == null) {
            firstNode = null;
        }
        else {
            final ListNode<E> nextNode = firstNode.getNextNode();
            firstNode.setNextNode(null);
            firstNode = nextNode;
        }
        return element;
    }

    public E removeFromLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final E element = firstNode.getElement();
        if (firstNode.getNextNode() == null) {
            firstNode = null;
        }
        else {
            ListNode<E> currentNode = firstNode;
            ListNode<E> previousNode = firstNode;
            while (currentNode.getNextNode() != null) {
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();
            }
            previousNode.setNextNode(null);
        }
        return element;
    }

    public void print() {
        System.out.printf("%s: ", name);
        if (!isEmpty()) {
            if(firstNode.getNextNode() == null) {
                System.out.printf("[%s]%n", firstNode.getElement());
            }
            else {
                System.out.print("[");
                ListNode<E> currentNode = firstNode;
                ListNode<E> lastNode = firstNode;
                while (currentNode.getNextNode() != null) {
                    lastNode = currentNode;
                    currentNode = currentNode.getNextNode();
                    System.out.printf("%s, ", lastNode.getElement());
                }
                System.out.printf("%s]%n", currentNode.getElement());
            }
        }
        else {
            System.out.println("[]");
        }
    }

    public ListNode<E> getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(ListNode<E> firstNode) {
        this.firstNode = firstNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String [] args) {
        final List<Integer> list = new List<>("List");
        list.insertAtFront(2);
        list.print();
        list.insertAtBack(3);
        list.print();
        list.insertAtBack(4);
        list.print();
        list.insertAtFront(1);
        list.print();
        list.removeFromLast();
        list.print();
        list.removeFromFront();
        list.print();
        list.removeFromFront();
        list.print();
        list.removeFromFront();
        list.print();
    }
}
