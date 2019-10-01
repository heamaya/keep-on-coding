package chapter16.exercise18;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {

    public static void main(String [] args ) {
        final PriorityQueue<Double> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        priorityQueue.offer(3.2d);
        priorityQueue.offer(9.8d);
        priorityQueue.offer(5.4d);
        System.out.println("Polling from the priority queue: ");
        while (priorityQueue.size() > 0) {
            System.out.printf("%.1f ", priorityQueue.peek());
            priorityQueue.poll();
        }
    }

}
