package chapter21.datastructures;

public class Queue<E> {
    private final List<E> queueList;

    public Queue() {
        queueList = new List<>("Queue");
    }

    public void enqueue(E element) {
        queueList.insertAtBack(element);
    }

    public E dequeue() {
        return queueList.removeFromFront();
    }

    public E poll() {
        return queueList.getFirstNode().getElement();
    }

    public boolean isEmpty() {
        return queueList.isEmpty();
    }

    public void print() {
        queueList.print();
    }

    public long count() {
        long count = 0;
        ListNode<E> currentNode = queueList.getFirstNode();
        while(currentNode != null) {
            count++;
            currentNode = currentNode.getNextNode();
        }
        return count;
    }

    public static void main(String [] args) {
        final Queue<String> queue = new Queue<>();
        queue.enqueue("Jai");
        queue.print();
        queue.enqueue("Guru");
        queue.print();
        queue.enqueue("Deva");
        queue.print();
        queue.enqueue("Om");
        queue.print();
        queue.enqueue("nothing");
        queue.print();
        queue.enqueue("is");
        queue.print();
        queue.enqueue("gonna");
        queue.print();
        queue.enqueue("change");
        queue.print();
        queue.enqueue("my");
        queue.print();
        queue.enqueue("world");
        while (!queue.isEmpty()) {
            queue.dequeue();
            queue.print();
        }
    }

}
