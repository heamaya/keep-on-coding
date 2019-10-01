package chapter21.exercise20;

import chapter21.datastructures.ListNode;
import chapter21.datastructures.TreeNode;

import java.util.NoSuchElementException;

public class List<E> {

    private String name;
    private ListNode<E> firstNode;
    private ListNode<E> lastNode;

    public List(String name) {
        this.name = name;
    }

    public boolean isEmpty() {
        return firstNode == null;
    }

    public void insertAtFront(E element) {
        final ListNode<E> newNode = new ListNode<>(element);
        if (isEmpty()) {
            firstNode = lastNode = newNode;
        }
        else {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
        }
    }

    public void insertAtBack(E element) {
        final ListNode<E> newNode = new ListNode<>(element);
        if (isEmpty()) {
            firstNode = lastNode = newNode;
        }
        else {
            lastNode.setNextNode(newNode);
            lastNode = newNode;
        }
    }

    public E removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final E element = firstNode.getElement();
        if (firstNode == lastNode) {
            firstNode = lastNode = null;
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
        if (firstNode == lastNode) {
            firstNode = lastNode = null;
        }
        else {
            ListNode<E> currentNode = firstNode;
            while (currentNode.getNextNode() != lastNode) {
                currentNode = currentNode.getNextNode();
            }
            lastNode = currentNode;
            lastNode.setNextNode(null);
        }
        return element;
    }

    public void print() {
        if (!isEmpty()) {
            System.out.printf("%s: ", name);
            if(firstNode == lastNode) {
                System.out.printf("[%s]%n", firstNode.getElement());
            }
            else {
                System.out.printf("[%s, ", firstNode.getElement());
                ListNode<E> currentNode = firstNode;
                while (currentNode.getNextNode() != lastNode) {
                    currentNode = currentNode.getNextNode();
                    System.out.printf("%s, ", currentNode.getElement());
                }
                System.out.printf("%s]%n", lastNode.getElement());
            }
        }
    }

    public void printBackward() {
        printBackward(getFirstNode());
    }

    private void printBackward(ListNode<E> node) {
        if(node == getLastNode()) {
            System.out.printf("Backward %s: [%s, ", getName(), node.getElement());
        }
        else if (node == getFirstNode()) {
            printBackward(node.getNextNode());
            System.out.printf("%s] ", node.getElement());
        } else {
            printBackward(node.getNextNode());
            System.out.printf("%s, ", node.getElement());
        }
    }

    public ListNode<E> getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(ListNode<E> firstNode) {
        this.firstNode = firstNode;
    }

    public ListNode<E> getLastNode() {
        return lastNode;
    }

    public void setLastNode(ListNode<E> lastNode) {
        this.lastNode = lastNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String [] args) {
        final List<Integer> list = new List<>("LinkedList");
        list.insertAtFront(2);
        list.insertAtBack(3);
        list.insertAtBack(4);
        list.insertAtFront(1);
        list.print();
        list.printBackward();
    }
}
