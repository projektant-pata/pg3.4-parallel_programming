package cz.spse.projektant_pata;

import cz.spse.projektant_pata.app.DoorsManager;
import cz.spse.projektant_pata.view.StatsReview;
import cz.spse.projektant_pata.app.TablesManager;
import cz.spse.projektant_pata.c301.TimerDaemon;
import cz.spse.projektant_pata.data.IDoors;
import cz.spse.projektant_pata.data.*;
import cz.spse.projektant_pata.shared.model.Customer;
import cz.spse.projektant_pata.shared.model.Table;

import java.util.*;

public class Main {
    //colors for terminal
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";   // příchod
    public static final String BLUE = "\u001B[34m";    // ke stolu
    public static final String RED = "\u001B[31m";     // odchod

    //other stuff
    private static IDoors doors;
    private final static Scanner sc = new Scanner(System.in);

    //managers
    private final static TablesManager tablesManager = new TablesManager(1000);
    private static DoorsManager doorsManager;
    private final static StatsReview statsReview = StatsReview.getInstance();
    private final static TimerDaemon timer = new TimerDaemon();

    public static void main(String[] args) {
        //start of the program
        System.out.println("Starting the restaurant simulation...");

        chooseVariant();
        prepareManagers();

        //create threads
        Thread doorsThread = new Thread(doorsManager);
        Thread tablesThread = new Thread(tablesManager);
        Thread timerDaemon = new Thread(timer);

        //the program
        timerDaemon.setDaemon(true);

        doorsThread.start();
        tablesThread.start();
        timerDaemon.start();

        //the end of the program
        System.out.println("Press enter to stop the simulation...");
        sc.nextLine();
        doorsThread.interrupt(); //interupt, protoze by se cekalo v Variant1b 10 sekund
        tablesManager.stop();


        statsReview.printStats(tablesManager.getTables());
        System.out.println("Ending the restaurant simulation!");
    }

    private static void chooseVariant(){
        System.out.println("Choose variant (1, 2): ");
        String variant = sc.nextLine();
        switch (variant) {
            case "1" -> doors = new Variant1a();
            case "2" -> doors = new Variant1b();
            default -> {
                System.out.println("Invalid variant, using default (1a)");
                doors = new Variant1a();
            }
        }
        doorsManager = new DoorsManager(doors);
    }

    private static void addCustomers() {
        //get new customers from doors
        Customer[] newCustomers = doors.getNewCustomers();
        System.out.printf(GREEN + "🟢 New customers arrived: %d\n" + RESET, newCustomers.length);

        //just terminal output, outside to not block the main thread
        for (Customer c : newCustomers) {
            System.out.printf(GREEN + "👤 New customer %s arrived\n" + RESET, c.getName());
        }
        //add customers to tables
        synchronized (tablesManager) {
            for (Customer c : newCustomers) {
                tablesManager.newCustomer(c);
                statsReview.addCustomer(c);
            }
        }
    }

    private static void prepareManagers() {
        //doors manager
        doorsManager.registerHandler(doors -> {
            addCustomers();
        });

        //table manager
        int i = 1;
        int size = 0;
        System.out.println("Enter size of tables (0 to stop): ");
        while (true) {
            System.out.printf("Enter size of table %d: ", i);
            size = sc.nextInt();
            sc.nextLine();

            if(size == 0 && i != 1) {
                break;
            }

            tablesManager.addTable(new Table(i, size));
            i++;
        }

        //if some tables were predefined
        Set<Table> tables = tablesManager.getTables();

        for (Table table : tables) {
            tablesManager.registerHandler(Table::anyoneEaten);
            tablesManager.registerHandler(Table::giveStats);
        }

    }

    private static void printStats() {
        statsReview.printStats(tablesManager.getTables());
    }


}