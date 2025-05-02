package cz.spse.projektant_pata.view;

import cz.spse.projektant_pata.shared.model.Customer;
import cz.spse.projektant_pata.shared.model.Table;

import java.util.HashSet;
import java.util.Set;

public class StatsReview {
    //singleton
    private static StatsReview instance = new StatsReview();

    private Set<Customer> customers = new HashSet<>();

    private StatsReview() {

    }

    public static StatsReview getInstance() {
        return instance;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }


    public void printStats(Set<Table> tables) {
        Customer longestWaitingCustomer = null;
        int numberOfCustomers = customers.size();
        int numberOfSoups = 0;
        int numberOfDeserts = 0;
        long avgEatingTime = 0;
        long avgWaitingTime = 0;

        for (Customer customer : customers) {
            if (longestWaitingCustomer == null || customer.getTimeStampCreated() > longestWaitingCustomer.getTimeStampCreated())
                longestWaitingCustomer = customer;

            if (customer.hasSoup())
                numberOfSoups++;
            if (customer.hasDesert())
                numberOfDeserts++;

            avgEatingTime += customer.getTimeStampEaten();
            avgWaitingTime += (customer.getTimeStampUpdated() - customer.getTimeStampCreated()) / 1000;
        }
        avgEatingTime /= numberOfCustomers * 1000;
        avgWaitingTime /= numberOfCustomers;

        System.out.println("Customer stats:");
        System.out.println("Number of customers: " + numberOfCustomers);
        System.out.println("Number of soups: " + numberOfSoups + " (" + String.format("%.1f", numberOfSoups / (double) numberOfCustomers * 100) + "%)");
        System.out.println("Number of deserts: " + numberOfDeserts + " (" + String.format("%.1f", numberOfDeserts / (double) numberOfCustomers * 100) + "%)");
        System.out.println("Average eating time: " + avgEatingTime  + " seconds");
        System.out.println("Average waiting time: " + avgWaitingTime + " seconds");
        System.out.println("Longest waiting customer: " + longestWaitingCustomer.getName());
        System.out.println("Time waited: " + (System.currentTimeMillis() - longestWaitingCustomer.getTimeStampCreated()) / 1000 + " seconds");

        System.out.println("Table stats:");
        for (Table table : tables) {
            System.out.println("Table " + table.getTableNumber() + ":");
            System.out.println("Workload: " + String.format("%.1f", table.getWorkload() / table.getTime()) + "%");        }
    }
}
