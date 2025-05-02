package cz.spse.projektant_pata.data;

import cz.spse.projektant_pata.shared.model.Customer;

public interface IDoors {
    Customer[] getNewCustomers();
    double getNextInterval();
}
