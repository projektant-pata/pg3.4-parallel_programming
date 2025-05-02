package cz.spse.projektant_pata.data;

import cz.spse.projektant_pata.shared.api.ApiGetter;
import cz.spse.projektant_pata.shared.model.Customer;

//more customers in a longer interval
public class Variant1b implements IDoors{

    @Override
    public Customer[] getNewCustomers() {
        int random = (int) (Math.random() * 100);
        if (random <= 10){
            String[] names = ApiGetter.getNames(1);
            return new Customer[]{new Customer(names[0])};
        }else if (random <= 30){
            String[] names = ApiGetter.getNames(2);
            return new Customer[]{new Customer(names[0]), new Customer(names[1])};
        }else if (random <= 60){
            String[] names = ApiGetter.getNames(3);
            return new Customer[]{new Customer(names[0]), new Customer(names[1]), new Customer(names[2])};
        }else if (random <= 80){
            String[] names = ApiGetter.getNames(4);
            return new Customer[]{new Customer(names[0]), new Customer(names[1]), new Customer(names[2]), new Customer(names[3])};
        }else if (random <= 90){
            String[] names = ApiGetter.getNames(5);
            return new Customer[]{new Customer(names[0]), new Customer(names[1]), new Customer(names[2]), new Customer(names[3]), new Customer(names[4])};
        }else{
            String[] names = ApiGetter.getNames(6);
            return new Customer[]{new Customer(names[0]), new Customer(names[1]), new Customer(names[2]), new Customer(names[3]), new Customer(names[4]), new Customer(names[5])};
        }
    }

    @Override
    public double getNextInterval() {
        return 10;
    }
}
