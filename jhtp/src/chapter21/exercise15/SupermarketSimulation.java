package chapter21.exercise15;

import chapter21.datastructures.Queue;

import java.security.SecureRandom;

public class SupermarketSimulation {

    private static final int MAX_SIMULATION_TIME = 720;
    private static final int CUSTOMER_ARRIVAL_MEAN_TIME = 3;
    private static final int CUSTOMER_SERVICE_MEAN_TIME = 3;
    private final SecureRandom secureRandom;
    private int currentTime;
    private final Queue<Customer> customersQueue;

    public SupermarketSimulation() {
        secureRandom = new SecureRandom();
        currentTime = 0;
        customersQueue = new Queue<>();
    }

    public void simulate() {
        int customerArrivalTime = secureRandom.nextInt(CUSTOMER_ARRIVAL_MEAN_TIME) + 1;
        int customerCounter = 0;
        int customerServiceTime = -1;
        int queueCount = 0;
        int maxWaitTime = 0;
        while (currentTime < MAX_SIMULATION_TIME) {
            if (currentTime == customerArrivalTime && customerCounter == 0) {
                System.out.printf(
                    "Customer %d arrived and is being served at time: %d%n",
                    ++customerCounter,
                    currentTime
                );
                customerServiceTime = currentTime + secureRandom.nextInt(CUSTOMER_SERVICE_MEAN_TIME) + 1;
                customerArrivalTime = currentTime + secureRandom.nextInt(CUSTOMER_ARRIVAL_MEAN_TIME) + 1;
            }
            else if (currentTime == customerArrivalTime) {
                if (customersQueue.isEmpty() && currentTime >= customerServiceTime) {
                    System.out.printf(
                        "Customer %d arrived and is being served at time: %d%n",
                        ++customerCounter,
                        currentTime
                    );
                } else {
                    addAwaitingCustomer(++customerCounter, customerArrivalTime, customerServiceTime);
                    customersQueue.print();
                }
                customerServiceTime = currentTime + secureRandom.nextInt(CUSTOMER_SERVICE_MEAN_TIME) + 1;
                customerArrivalTime = currentTime + secureRandom.nextInt(CUSTOMER_ARRIVAL_MEAN_TIME) + 1;
            }
            if (!customersQueue.isEmpty() && currentTime >= customersQueue.poll().getCustomerServiceTime()) {
                final Customer customer = customersQueue.dequeue();
                System.out.printf(
                    "Customer %d was served and gone at time: %d%n",
                    customer.getCustomerNumber(),
                    currentTime
                );
                int waitTime = currentTime - customer.getCustomerArrivalTime();
                if (waitTime > maxWaitTime) {
                    maxWaitTime = waitTime;
                }
                customersQueue.print();
            } else if(customersQueue.isEmpty() && currentTime >= customerServiceTime){
                int waitTime = currentTime - customerArrivalTime;
                if (waitTime > maxWaitTime) {
                    maxWaitTime = waitTime;
                }
                System.out.printf("Customer %d was served and gone at time: %d%n", customerCounter, currentTime);
            }
            queueCount += customersQueue.count();
            currentTime++;
        }
        System.out.printf(
            "The maximum number of customers in the queue at any time is: %.2f%n",
            (double) queueCount / (double) MAX_SIMULATION_TIME
        );
        System.out.printf("The maximum wait time is: %d%n", maxWaitTime);
    }

    private void addAwaitingCustomer(int customerCounter, int customerArrivalTime, int customerServiceTime) {
        System.out.printf("Customer %d arrived and is waiting to be served at time: %d%n", customerCounter, currentTime);
        final Customer customer = new Customer();
        customer.setCustomerNumber(customerCounter);
        customer.setCustomerArrivalTime(customerArrivalTime);
        customer.setCustomerServiceTime(customerServiceTime);
        customersQueue.enqueue(customer);
    }

    public static void main(String [] args) {
        new SupermarketSimulation().simulate();
    }
}
