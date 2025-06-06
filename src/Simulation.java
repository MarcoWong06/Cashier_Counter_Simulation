import java.util.Scanner;

/**
 * Copyright (c) 2025, WONG Sai Lung. All rights reserved.
 *
 * @author WONG, Sai Lung
 * @version v1.0.1
 */
public class Simulation {
    private final int simulationLength;
    private final int counter;
    private final Supermarket supermarket;
    private final Scanner scanner;

    private int totalCustomerServed;
    private double totalCustomerInQueue;
    private int maxCustomerInQueue;

    /**
     * Initialize the simulation, create a new supermarket and ready to start.
     *
     * @param simulationLength Integer of simulation length in minutes
     * @param counter          Integer of counter in the new supermarket
     */
    public Simulation(int simulationLength, int counter) {
        this.simulationLength = simulationLength;
        this.counter = counter;
        supermarket = new Supermarket(counter);
        scanner = new Scanner(System.in);
        totalCustomerServed = 0;
        totalCustomerInQueue = 0;
        maxCustomerInQueue = 0;
    }

    /**
     * Start the simulation.
     * This method runs the simulation for the specified length of time.
     * It prompts the user to serve time for each new customer and processes the supermarket state.
     * It also updates the total number of customers served, the total number of customers in the queue, and the maximum number of customers in the queue.
     */
    public void runSimulation() {
        for (int i = 0; i < simulationLength; i++) {

            System.out.println("At the beginning of iteration " + (i + 1) + "...");
            int servingTime = Input.getInput(scanner, "Input serving time for a new customer:", "Serving time cannot be negative");

            if (servingTime > 0) {
                supermarket.checkOut(new Customer(servingTime));
            }
            if (supermarket.process(i + 1)) {
                ++totalCustomerServed;
            }

            System.out.println("After " + (i + 1) + " minute ##");
            for (int j = 0; j < counter; j++) {
                System.out.print("    Teller_" + (j + 1) + "[ " + supermarket.getCustomerLeaveTime(j) + " ]    ");
            }
            System.out.print("  Waiting Queue:" + supermarket.getWaitingQueue() + "\n\n");

            totalCustomerInQueue += supermarket.getWaitingQueueSize();
            if (supermarket.getWaitingQueueSize() > maxCustomerInQueue) {
                maxCustomerInQueue = supermarket.getWaitingQueueSize();
            }
        }
    }

    public int getTotalCustomerServed() {
        return totalCustomerServed;
    }

    public double getAverageCustomerInQueue() {
        return totalCustomerInQueue / simulationLength;
    }

    public int getMaxCustomerInQueue() {
        return maxCustomerInQueue;
    }

}
