package chapter21.exercise26;

import chapter21.datastructures.List;
import chapter21.datastructures.ListNode;

public class LinkedList<E> extends List<E> {

    public LinkedList(String name) {
        super(name);
    }

    public LinkedList() {
        super("LinkedList");
    }

    public void insert(int index, E element) {
        final ListNode<E> newNode = new ListNode<>(element);
        if(index == 0) {
            if(getFirstNode() == null) {
                setFirstNode(newNode);
                setLastNode(newNode);
            }
            else {
                newNode.setNextNode(getFirstNode());
                setFirstNode(newNode);
            }
        }
        else {
            ListNode<E> currentNode = getFirstNode();
            for (int i = 1; i < index && currentNode != null; i++) {
                currentNode = currentNode.getNextNode();
            }
            if (currentNode != null) {
                newNode.setNextNode(currentNode.getNextNode());
                currentNode.setNextNode(newNode);
                if(newNode.getNextNode() == null) {
                    setLastNode(newNode);
                }
            }
            else {
                throw new IndexOutOfBoundsException("The index is out of bound");
            }
        }
    }

    public void delete(int index) {
        if(index == 0) {
            if (getFirstNode() == getLastNode()) {
                setFirstNode(null);
                setLastNode(null);
            }
            else {
                final ListNode<E> nextNode = getFirstNode().getNextNode();
                getFirstNode().setNextNode(null);
                setFirstNode(nextNode);
            }
        }
        else {
            ListNode<E> currentNode = getFirstNode();
            for (int i = 0; i < index - 1 && currentNode != null; i++) {
                currentNode = currentNode.getNextNode();
            }
            if (currentNode != null) {
                final ListNode<E> nodeToDelete = currentNode.getNextNode();
                currentNode.setNextNode(nodeToDelete.getNextNode());
                if(nodeToDelete == getLastNode()) {
                    setLastNode(currentNode);
                }
                if(nodeToDelete == getFirstNode()) {
                    setFirstNode(null);
                }
            }
            else {
                throw new IndexOutOfBoundsException("The index is out of bound");
            }
        }
    }

    public static void main(String [] args) {
        final LinkedList linkedList = new LinkedList();
        linkedList.insert(0, 5);
        linkedList.print();
        linkedList.insert(0, 1);
        linkedList.print();
        linkedList.insert(1, 3);
        linkedList.print();
        linkedList.insert(1, 2);
        linkedList.print();
        linkedList.insert(3, 4);
        linkedList.print();
        linkedList.delete(4);
        linkedList.print();
        linkedList.delete(2);
        linkedList.print();
        linkedList.delete(0);
        linkedList.print();
        linkedList.delete(0);
        linkedList.print();
        linkedList.delete(0);
        linkedList.print();

    }
}
