package cz.spse.projektant_pata.app;

import cz.spse.projektant_pata.data.IDoors;
import cz.spse.projektant_pata.shared.model.Customer;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class DoorsManager implements Runnable {
    //stuff
    private final Set<Consumer<IDoors>> consumers = new HashSet<>();
    private final IDoors doors;
    private boolean isRunning = true;

    public DoorsManager(IDoors doors) {
        this.doors = doors;
    }

    //handlers
    public void registerHandler(Consumer<IDoors> consumer) {
        consumers.add(consumer);
    }

    public void unregisterHandler(Consumer<IDoors> consumer) {
        consumers.remove(consumer);
    }

    private void notifyHandlers(IDoors doors) {
        for (Consumer<IDoors> consumer : consumers) {
            new Thread(() -> consumer.accept(doors)).start();
        }
    }

    //stop
    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                notifyHandlers(doors);
                Thread.sleep((long)(doors.getNextInterval() * 1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
