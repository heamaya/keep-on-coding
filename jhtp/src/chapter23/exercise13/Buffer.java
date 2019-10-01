package chapter23.exercise13;

public interface Buffer {

    void blockingPut(int value) throws InterruptedException;

    int blockingGet() throws InterruptedException;
}
