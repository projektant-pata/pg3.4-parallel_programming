package cz.spse.projektant_pata.data;

import cz.spse.projektant_pata.shared.api.ApiGetter;
import cz.spse.projektant_pata.shared.model.Customer;

//1 customer in a shorter interval
public class Variant1a implements IDoors {
    @Override
    public Customer[] getNewCustomers() {
        String[] names = ApiGetter.getNames(1);
        return new Customer[]{new Customer(names[0])};
    }

    @Override
    public double getNextInterval() {
        return 2 + -Math.log(1 - Math.random());
    }
}
