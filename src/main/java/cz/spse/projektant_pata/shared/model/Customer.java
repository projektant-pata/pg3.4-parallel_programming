package cz.spse.projektant_pata.shared.model;

import java.util.Random;

public class Customer {
    //time stamps
    private final long TIME_STAMP_CREATED;
    private long TIME_STAMP_UPDATED;
    private long TIME_STAMP_DELETED;

    //customer data
    private final String name;
    private final boolean hasSoup;
    private final boolean hasDesert;
    private final long TIME_STAMP_EATEN;

    //for stats
    private Table table;

    public Customer(String name) {
        this.TIME_STAMP_CREATED = System.currentTimeMillis();
        this.hasSoup = Math.random() <= 0.4;
        this.hasDesert = Math.random() <= 0.6;
        this.name = name;
        this.TIME_STAMP_EATEN = setTimeStampEaten();
    }

    //getters
    public String getName() {
        return name;
    }

    public Table getTable() {
        return table;
    }

    public boolean hasSoup() {
        return hasSoup;
    }

    public boolean hasDesert() {
        return hasDesert;
    }

    public long getTimeStampEaten() {
        return TIME_STAMP_EATEN;
    }

    public long getTimeStampCreated() {
        return TIME_STAMP_CREATED;
    }

    public long getTimeStampUpdated() {
        return TIME_STAMP_UPDATED;
    }

    public long getTimeStampDeleted() {
        return TIME_STAMP_DELETED;
    }


    public void setTable(Table table) {
        this.table = table;
    }
    public void setTimeStampUpdated() {
        TIME_STAMP_UPDATED = System.currentTimeMillis();
    }
    public void setTimeStampDeleted() {
        TIME_STAMP_DELETED = System.currentTimeMillis();
    }

    private int setTimeStampEaten() {
        Random random = new Random();
        int lunch = 0;
        int soup = 0;
        int desert = 0;
        do {
            lunch = (int) (7 + (random.nextGaussian() * 0.15 + 0.5) * (14));
        } while (lunch > 20 || lunch < 7);
        if (hasSoup){
            do {
                soup = (int) (3 + (random.nextGaussian() * 0.15 + 0.5) * (3));
            }while (soup > 5 || soup < 3);
        }
        if (hasDesert){
            do {
                desert = (int) (1 + (random.nextGaussian() * 0.15 + 0.5) * (3));
            }while (desert > 4 || desert < 1);
        }
        return (lunch + soup + desert) * 1000;
    }
}
