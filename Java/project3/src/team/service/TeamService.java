package team.service;

import team.domain.Architect;
import team.domain.Designer;
import team.domain.Employee;
import team.domain.Programmer;

public class TeamService {
    int counter=1;
    final int MAX_MEMBER=5;
    Programmer[] team=new Programmer[MAX_MEMBER];
    int total=0;
    public Programmer[] getTeam(){

        return team;
    }
    public void addMember(Employee e) throws TeamException{
        if(e instanceof Programmer) {
            Programmer ep = (Programmer) e;
            if (ep.status == Status.VOCATION)
                throw new TeamException("休假中！");
            if (ep.status == Status.BUSY)
                throw new TeamException("已经在里面拉！");
            if (total > MAX_MEMBER)
                throw new TeamException("人太多拉！");
            int numofPro = 0, numofDes = 0, numofArc = 0;
            for (int i = 0; i < team.length; i++) {
                if (team[i] instanceof Programmer)
                    numofPro++;
                if (team[i] instanceof Designer)
                    numofDes++;
                if (team[i] instanceof Architect)
                    numofArc++;
            }
            if (ep instanceof Programmer)
                if (numofPro > 2)
                    throw new TeamException("只能有三名程序员！");
            if (ep instanceof Designer)
                if (numofDes > 1)
                    throw new TeamException("只能有两名设计师！");
            if (ep instanceof Architect)
                if (numofArc > 0)
                    throw new TeamException("只能有一名架构师！");
            ep.status = Status.BUSY;
            ep.memberId = counter++;
            team[total++] = ep;

        }else throw new TeamException("级别不够！");
    }
    public void removeMember(int memberId) throws TeamException{
        boolean j=true;
        for (int i = 0; i <team.length; i++) {
            if(team[i]!=null) {
                if (team[i].memberId == memberId) {
                    team[i].status = Status.FREE;
                    team[i] = null;
                    total--;
                    j = false;
                    break;
                }
            }
        }
        if(j){
            throw new TeamException("没有这个人哦！");
        }

    }
}
