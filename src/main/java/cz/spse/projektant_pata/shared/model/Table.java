package cz.spse.projektant_pata.shared.model;

import java.util.*;

public class Table {
    //colors for terminal
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";   // příchod
    public static final String BLUE = "\u001B[34m";    // ke stolu
    public static final String RED = "\u001B[31m";     // odchod

    //other stuff
    private final Queue<Customer> queue = new LinkedList<>();
    private final LinkedList<Customer> atTableCusomers = new LinkedList<>();
    private final int tableNumber;
    private final int tableSize;
    private double workload = 0;
    private int time = 0;

    public Table(int tableNumber, int tableSize) {
        this.tableNumber = tableNumber;
        this.tableSize = tableSize;
    }

    //getters
    public int getTableNumber() {
        return tableNumber;
    }
    public int getTableSize() {
        return tableSize;
    }
    synchronized public boolean isFull() {
        return atTableCusomers.size() >= tableSize;
    }
    synchronized public int waitingCustomers() {
        return queue.size();
    }
    synchronized public double getWorkload() {
        return workload;
    }
    synchronized public int getTime() {
        return time;
    }

    //functions
    synchronized public void addCustomer(Customer customer) {
        //if table !full, add customer to table
        //if table full, add customer to queue
        if (!isFull()) {
            customer.setTimeStampUpdated();
            atTableCusomers.add(customer);
            System.out.printf(BLUE + "🍽️ Customer %s added to table %d\n" + RESET, customer.getName(), tableNumber);
        } else {
            queue.add(customer);
            System.out.printf(BLUE + "🕓 Customer %s added to queue of table %d\n" + RESET, customer.getName(), tableNumber);
        }
        customer.setTable(this);
        System.out.println("ℹ️ Table " + tableNumber + " has " + atTableCusomers.size() + " customers at the table and " + queue.size() + " customers in the queue.");
    }

    synchronized public void anyoneEaten() {
        //check if any customer at the table has eaten
        //if yes, remove him from the table and add the next customer from the queue
        Iterator<Customer> iterator = atTableCusomers.iterator();
        ArrayList<Customer> toAdd = new ArrayList<>();
        while (iterator.hasNext()) {
            Customer c = iterator.next();
            if (c.getTimeStampUpdated() + c.getTimeStampEaten() < System.currentTimeMillis()) {
                System.out.printf(RED + "❌ Customer %s eaten at table %d for %d\n" + RESET, c.getName(), tableNumber, c.getTimeStampEaten());
                c.setTimeStampDeleted();
                iterator.remove();

                if (!queue.isEmpty()) {
                    Customer nextCustomer = queue.poll();
                    nextCustomer.setTimeStampUpdated();
                    toAdd.add(nextCustomer);
                }

            }
        }
        for (Customer c : toAdd) {
            atTableCusomers.add(c);
            System.out.printf(BLUE + "🍽️ Customer %s added to table %d\n" + RESET, c.getName(), tableNumber);
        }
    }

    synchronized public void giveStats() {
        workload += (double) atTableCusomers.size() / (double) tableSize * 100;
        time++;
    }

}
