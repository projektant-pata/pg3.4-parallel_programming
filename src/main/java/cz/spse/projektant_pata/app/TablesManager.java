package cz.spse.projektant_pata.app;

import cz.spse.projektant_pata.shared.model.Customer;
import cz.spse.projektant_pata.shared.model.Table;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class TablesManager implements Runnable {
    //stuff
    private final long interval;
    private final Set<Consumer<Table>> consumers = new HashSet<>();
    private final Set<Table> tables = new HashSet<>();
    private boolean isRunning = true;

    public TablesManager(long interval) {
        this.interval = interval;
    }

    //handlers
    public void registerHandler(Consumer<Table> consumer) {
        consumers.add(consumer);
    }
    public void unregisterHandler(Consumer<Table> consumer) {
        consumers.remove(consumer);
    }
    private void notifyHandlers(Table table) {
        for (Consumer<Table> consumer : consumers) {
            new Thread(() -> consumer.accept(table)).start();
        }
    }

    //stop
    public void stop() {
        isRunning = false;
    }

    //tables management
    public void addTable(Table table) {
        tables.add(table);
    }
    public void removeTable(Table table) {
        tables.remove(table);
    }
    public Set<Table> getTables() {
        return tables;
    }

    //customer management
    public void newCustomer(Customer customer) {
        for (Table table : tables) {
            if (!table.isFull()) {
                table.addCustomer(customer);
                notifyHandlers(table);
                return;
            }
        }
        int leastWaiting = Integer.MAX_VALUE;
        Table leastWaitingTable = null;
        for (Table table : tables) {
            if (table.waitingCustomers() < leastWaiting) {
                leastWaiting = table.waitingCustomers();
                leastWaitingTable = table;
            }
        }
        if (leastWaitingTable != null) {
            leastWaitingTable.addCustomer(customer);
            notifyHandlers(leastWaitingTable);
            return;
        }
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                for (Table table : tables) {
                    table.anyoneEaten();
                    notifyHandlers(table);
                }
                Thread.sleep(interval);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
