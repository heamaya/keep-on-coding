package chapter21.exercise7;

import chapter21.datastructures.List;
import chapter21.datastructures.ListNode;

public class SortedList<E extends Comparable<E>> extends List<E> {

    public SortedList() {
        super("SortedList");
    }

    public SortedList(String name) {
        super(name);
    }

    public void insert(E element) {
        final ListNode<E> newNode = new ListNode<>(element);
        if(isEmpty()) {
            setFirstNode(newNode);
            setLastNode(newNode);
        }
        else if (getFirstNode().getElement().compareTo(element) > 0) {
            newNode.setNextNode(getFirstNode());
            setFirstNode(newNode);
        }
        else if (getLastNode().getElement().compareTo(element) <= 0) {
            getLastNode().setNextNode(newNode);
            setLastNode(newNode);
        }
        else {
            ListNode<E> currentNode = getFirstNode();
            while(currentNode.getNextNode().getElement().compareTo(element) < 0) {
                currentNode = currentNode.getNextNode();
            }
            final ListNode<E> nextNode = currentNode.getNextNode();
            currentNode.setNextNode(newNode);
            newNode.setNextNode(nextNode);
        }
    }

    public static void main(String [] args) {
        final SortedList<Integer> sortedList1 = new SortedList<>("SortedList1");
        sortedList1.insert(2);
        sortedList1.insert(0);
        sortedList1.insert(1);
        sortedList1.insert(4);
        sortedList1.insert(3);
        sortedList1.print();
        System.out.println();
        final SortedList<Double> sortedList2 = new SortedList<>("SortedList2");
        sortedList2.insert(2.1d);
        sortedList2.insert(3.1d);
        sortedList2.insert(1.5d);
        sortedList2.insert(4.8d);
        sortedList2.insert(3.9d);
        sortedList2.print();
    }
}
