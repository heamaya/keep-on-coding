package chapter23.exercise13;

import java.security.SecureRandom;

public class Producer implements Runnable {

    private static final int BOUND = 3000;
    private final SecureRandom secureRandom;
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        secureRandom = new SecureRandom();
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int count = 1; count <= 10; count++) {
            try {
                Thread.sleep(secureRandom.nextInt(BOUND));
                buffer.blockingPut(count);
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.printf("Producer done producing%nTerminating Producer%n");
    }
}
