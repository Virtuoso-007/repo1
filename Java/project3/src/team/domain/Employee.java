package team.domain;

public class Employee {
    int id;
    String name;
    int age;
    double salary;
    Employee(){}
    public Employee(int id,String name,int age,double salary){
        this.id=id;
        this.name=name;
        this.age=age;
        this.salary=salary;
    }

    @Override
    public String toString() {
        return  " "+id + "  "+"\t" + name + "\t" + age +"  \t" + salary ;
    }
}
