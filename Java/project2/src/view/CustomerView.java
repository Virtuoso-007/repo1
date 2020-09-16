package view;

import controller.CMUtility;
import controller.CustomerList;
import model.Customer;


public class CustomerView {
    CustomerList customerList = new CustomerList(10);
    public void enterMainMenu(){
        System.out.println("  -----------------客户信息管理软件-----------------\n");
        System.out.println("1 添 加 客 户\n");
        System.out.println("2 修 改 客 户\n");
        System.out.println("3 删 除 客 户\n");
        System.out.println("4 客 户 列 表\n");
        System.out.println("5 退       出\n");
        System.out.println("请选择(1-5)：_\n");
    }
    private void addNewCustomer(){
        System.out.println("姓名");
        String name= CMUtility.readString(5);
        System.out.println("性别");
        char gender= CMUtility.readChar();
        System.out.println("年龄");
        int age=CMUtility.readInt();
        Customer cus=new Customer(name,gender,age);
        System.out.println(customerList.addCustomer(cus));
    }
    private void modifyCustomer(){
        System.out.println("选择需要修改的编号");
        int num=CMUtility.readInt();
        System.out.println("姓名");
        String name= CMUtility.readString(5);
        System.out.println("性别");
        char gender= CMUtility.readChar();
        System.out.println("年龄");
        int age=CMUtility.readInt();
        Customer cus=new Customer(name,gender,age);
        System.out.println(customerList.replaceCustomer(num,cus));
    }
    private void deleteCustomer(){
        System.out.println("选择需要删除的编号");
        int num=CMUtility.readInt();
        System.out.println(num);
    }

    private void listAllCustomers(){
        System.out.println("---------------------------客户列表---------------------------");
        System.out.println("编号  姓名       性别    年龄");
        Customer[] customers=customerList.getAllCustomers();
        int total=customerList.getTotal();
        for (int i = 0; i <total ; i++) {
            System.out.println(i+"  "+customers[i].getName()+"  "+customers[i].getGender()+"  "+customers[i].getAge());
        }
    }

    public static void main(String[] args) {
        CustomerView cv=new CustomerView();
        while(true){
            cv.enterMainMenu();
            char select=CMUtility.readMenuSelection();
            switch(select){
                case '1':cv.addNewCustomer();
                break;
                case '2':cv.modifyCustomer();
                break;
                case '3':cv.deleteCustomer();
                break;
                case '4':cv.listAllCustomers();
                break;
                case '5':break;
            }

        }
    }


}
