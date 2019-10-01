package chapter21.exercise15;

public class Customer {

    private int customerNumber;
    private int customerArrivalTime;
    private int customerServiceTime;

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getCustomerArrivalTime() {
        return customerArrivalTime;
    }

    public void setCustomerArrivalTime(int customerArrivalTime) {
        this.customerArrivalTime = customerArrivalTime;
    }

    public int getCustomerServiceTime() {
        return customerServiceTime;
    }

    public void setCustomerServiceTime(int customerServiceTime) {
        this.customerServiceTime = customerServiceTime;
    }

    @Override
    public String toString() {
        return String.valueOf(customerNumber);
    }
}
