package chapter23.exercise13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CircularBuffer implements Buffer {

    private static final int MINUTES = 1;
    private final Lock accessLock;
    private final Condition canWrite;
    private final Condition canRead;
    private final int[] elements = {-1, -1, -1};
    private int occupiedCells;
    private int writeIndex;
    private int readIndex;

    public CircularBuffer() {
        accessLock = new ReentrantLock();
        canWrite = accessLock.newCondition();
        canRead = accessLock.newCondition();
        occupiedCells = 0;
        writeIndex = 0;
        readIndex = 0;
    }

    @Override
    public void blockingPut(int value) throws InterruptedException {
        accessLock.lock();
        try {
            while (occupiedCells == elements.length) {
                System.out.printf("Buffer is full. Producer waits.%n");
                canWrite.await();
            }
            elements[writeIndex] = value;
            writeIndex = (writeIndex + 1) % elements.length;
            ++occupiedCells;
            displayState("Producer writes " + value);
            canRead.signalAll();
        }
        finally {
            accessLock.unlock();
        }
    }

    @Override
    public int blockingGet() throws InterruptedException {
        accessLock.lock();
        int readValue;
        try {
            while (occupiedCells == 0) {
                System.out.printf("Buffer is empty. Consumer waits.%n");
                canRead.await();
            }
            readValue = elements[readIndex];
            readIndex = (readIndex + 1) % elements.length;
            --occupiedCells;
            displayState("Consumer reads " + readValue);
            canWrite.signalAll();
        }
        finally {
            accessLock.unlock();
        }
        return readValue;
    }


    private void displayState(String operation) {
        accessLock.lock();
        try {
            System.out.printf("%s%s%d)%n%s", operation, " (buffer cells occupied: ", occupiedCells, "buffer cells:  ");
            for (int element : elements) {
                System.out.printf(" %2d  ", element);
            }
            System.out.printf("%n               ");
            for (int i = 0; i < elements.length; i++) {
                System.out.print("---- ");
            }
            System.out.printf("%n               ");
            for (int i = 0; i < elements.length; i++) {
                if (i == writeIndex && i == readIndex) {
                    System.out.print(" WR");
                }
                else if (i == writeIndex) {
                    System.out.print(" W   ");
                }
                else if (i == readIndex) {
                    System.out.print("  R  ");
                }
                else {
                    System.out.print("     ");
                }
            }
            System.out.printf("%n%n");
        }
        finally {
            accessLock.unlock();
        }
    }

    public static void main(String [] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final CircularBuffer circularBuffer = new CircularBuffer();
        circularBuffer.displayState("Initial State");
        executorService.execute(new Producer(circularBuffer));
        executorService.execute(new Consumer(circularBuffer));
        executorService.shutdown();
        executorService.awaitTermination(MINUTES, TimeUnit.MINUTES);
    }
}
