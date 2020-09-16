package team.domain;

public class Designer extends Programmer {
    double bonus;
    public Designer(int id,String name,int age,double salary,Equipment equipment,double bonus){
        this.id=id;
        this.name=name;
        this.age=age;
        this.salary=salary;
        this.equipment=equipment;
        this.bonus=bonus;
    }

    @Override
    public String toString() {
        return  " "+id + "  "+"\t" + name + "\t" + age +"  \t" + salary+"\t"+"    设计师     "+status.toString()+"     "+bonus+"              "+equipment.getDescription();
    }
}
