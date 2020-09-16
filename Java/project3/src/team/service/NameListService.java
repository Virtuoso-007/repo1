package team.service;
import team.domain.*;
import static team.service.Data.*;

public class NameListService {
    Employee[] employees=new Employee[EMPLOYEES.length];

    public NameListService(){
        employees=new Employee[EMPLOYEES.length];
        for (int i = 0; i <EMPLOYEES.length; i++) {
            int type=Integer.parseInt(EMPLOYEES[i][0]);
            int id=Integer.parseInt(EMPLOYEES[i][1]);
            String name=EMPLOYEES[i][2];
            int age=Integer.parseInt(EMPLOYEES[i][3]);
            double salary=Double.parseDouble(EMPLOYEES[i][4]);
            switch (type){
                case EMPLOYEE:employees[i]=new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:employees[i]=new Programmer(id, name, age, salary,getEquipment(i));
                    break;
                case DESIGNER:employees[i]=new Designer(id, name, age, salary,getEquipment(i),Double.parseDouble(EMPLOYEES[i][5]));
                    break;
                case ARCHITECT:employees[i]=new Architect(id, name, age, salary,getEquipment(i),Double.parseDouble(EMPLOYEES[i][5]),Integer.parseInt(EMPLOYEES[i][6]));
                    break;
            }
        }
    }
    public Employee[] getAllEmployees(){

        return employees;
    }
    public Equipment getEquipment(int i) {
        Equipment equipment;
        switch (EQUIPMENTS[i][0]) {
            case "21":
                equipment = new PC(EQUIPMENTS[i][1], EQUIPMENTS[i][2]);
                break;
            case "22":
                equipment = new NoteBook(EQUIPMENTS[i][1], Double.parseDouble(EQUIPMENTS[i][2]));
                break;
            case "23":
                equipment = new Printer(EQUIPMENTS[i][1], EQUIPMENTS[i][2]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + EQUIPMENTS[i][0]);
        }
        return equipment;
    }
    public Employee getEmployee(int id) throws TeamException{
        if(id>0&&id<employees.length+1){
            return employees[id-1];
        }
        else throw new TeamException("没有此id");

    }

}
