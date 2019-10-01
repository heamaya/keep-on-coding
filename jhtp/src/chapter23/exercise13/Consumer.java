package chapter23.exercise13;

import java.security.SecureRandom;

public class Consumer implements Runnable {

    private static final int BOUND = 3000;
    private final SecureRandom secureRandom;
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        secureRandom = new SecureRandom();
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int count = 1; count <= 10; count++) {
            try {
                Thread.sleep(secureRandom.nextInt(BOUND));
                sum += buffer.blockingGet();
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.printf("%n%s %d%n%s%n", "Consumer read values totaling", sum, "Terminating Consumer");
    }
}
