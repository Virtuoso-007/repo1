package controller;

import model.Customer;

public class CustomerList {
    Customer[] customers;  //：用来保存客户对象的数组
    int total = 0;                 //：记录已保存客户对象的数量
    public CustomerList(int totalCustomer){
        customers=new Customer[totalCustomer];
    }
    public boolean addCustomer(Customer customer){
        customers[total]=customer;
        total++;
        return true;
    }
    public boolean replaceCustomer(int index, Customer cust){
        customers[index]=cust;
        return index<=total;
    }
    public boolean deleteCustomer(int index){
        customers[index]=null;
        for (int i = index; i <total ; i++) {
            customers[i]=customers[i+1];
        }
        return index<=total;
    }
    public Customer[] getAllCustomers(){
        return customers;

        }
    public Customer getCustomer(int index) {
        return customers[index];
    }
    public int getTotal(){
        return total;
    }



}
