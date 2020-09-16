package banking;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;
    private Bank() {
        customers=new ArrayList();
    }
    public void addCustomer(String f,String l){
        customers.add(new Customer(f,l));
    }

    public Customer getCustomer(int index) {
        return customers.get(index);
    }
    public int getNumofCustomer(){
        return customers.size();
    }
}
