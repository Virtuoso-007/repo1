package model;

public class Customer {
    String name; //客户姓名
    char gender;  //：性别
    int age ;         //：年龄


    //构造器
    public Customer(){

    }

    public Customer(String name,char gender,int age){
        this.name=name;
        this.gender=gender;
        this.age=age;
    }

    //get set方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
