package team.domain;

import team.service.Status;

public class Programmer extends Employee{
    public int memberId;
    public Status status=Status.FREE;
    Equipment equipment;
    public Programmer(){}
    public Programmer(int id,String name,int age,double salary,Equipment equipment){
        this.id=id;
        this.name=name;
        this.age=age;
        this.salary=salary;
        this.equipment=equipment;
    }
    @Override
    public String toString() {
        return  " "+id + "  "+"\t" + name + "\t" + age +"  \t" + salary+"      程序猿     "+status.toString()+"                         "+equipment.getDescription();
    }

}
