package chapter21.datastructures;

public class ListNode<E> {

    E element;
    private ListNode<E> nextNode;

    public ListNode() {
    }

    public ListNode(E element) {
        this.element = element;
    }

    public ListNode(ListNode<E> nextNode) {
        this.nextNode = nextNode;
    }

    public ListNode(E element, ListNode<E> nextNode) {
        this.element = element;
        this.nextNode = nextNode;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public ListNode<E> getNextNode() {
        return nextNode;
    }

    public void setNextNode(ListNode<E> nextNode) {
        this.nextNode = nextNode;
    }
}
