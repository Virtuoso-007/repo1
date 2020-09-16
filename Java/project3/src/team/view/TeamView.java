package team.view;

import team.domain.Employee;
import team.domain.Programmer;
import team.service.NameListService;
import team.service.TeamException;
import team.service.TeamService;

public class TeamView {
    NameListService listSvc = new NameListService();
    TeamService teamSvc = new TeamService();

    public void enterMainMenu() {

        boolean exit = true;
        while (exit) {
            listAllEmployees();
            char select = TSUtility.readMenuSelection();
            switch (select) {
                case '1':
                    getTeam();
                    TSUtility.readReturn();
                    break;
                case '2':
                    addMember();
                    TSUtility.readReturn();
                    break;
                case '3':
                    deleteMember();
                    TSUtility.readReturn();
                    break;
                case '4':
                    System.out.println("是否退出？Y/N");
                    if(TSUtility.readConfirmSelection()=='Y'){
                        exit = false;
                        System.out.println("bye!");}
                    break;
            }
        }

    }

    private void listAllEmployees() {
        System.out.println("-------------------------------------开发团队调度软件--------------------------------------\n");
        System.out.println("ID     姓名      年龄    工资         职位      状态      奖金        股票     领用设备");
        Employee[] employees = listSvc.getAllEmployees();
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i].toString());
        }
        System.out.println("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
    }

    private void getTeam() {
        Programmer[] programmers = teamSvc.getTeam();
        System.out.println("TID     ID     姓名      年龄    工资         职位      状态      奖金        股票     领用设备");
        for (int i = 0; i < programmers.length; i++) {
            if (programmers[i] != null) {
                System.out.print(programmers[i].memberId + "       ");
                System.out.println(programmers[i].toString());
            }
        }
    }

    private void addMember() {
        System.out.println("请输入添加的序号");
        try{
            teamSvc.addMember(listSvc.getEmployee(TSUtility.readInt()));
            System.out.println("添加成功");
        }catch (TeamException e){
            System.out.println(e.getMessage());
        }

    }

    private void deleteMember() {
        System.out.println("请输入删除的序号");
        try{
            teamSvc.removeMember(TSUtility.readInt());
        System.out.println("删除成功");
        }catch (TeamException e){
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();


    }
}
