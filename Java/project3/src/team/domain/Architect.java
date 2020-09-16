package team.domain;

public class Architect extends Designer {
    int stock;

    public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary, equipment, bonus);
        this.stock = stock;
    }
    @Override
    public String toString() {
        return  " "+id + "  "+"\t" + name + "\t" + age +"  \t" + salary+"\t"+"    架构师     "+status.toString()+"     "+bonus+"    "+stock+"     "+equipment.getDescription();
    }
}
