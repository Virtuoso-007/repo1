package manager.entry;

//import manager.controller.CardControllerCopy;
import manager.controller.RouteController;
import manager.controller.CardController;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class InfoManagerEntry {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        l:
        while (true) {
            System.out.println("欢迎乘坐北京地铁!");
            System.out.println("1：乘坐地铁");
            System.out.println("2：地铁卡服务");
            System.out.println("3：退出系统");
            System.out.println("请输入要选择的操作（1~3）：");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
//                        开启地铁线路系统
                        RouteController routeController = new RouteController();
                        routeController.start();
                        break lo;
                    case "2":
//                        地铁卡系统
                        CardController cardController = new CardController();
                        cardController.start();
                        break lo;
                    case "3":
                        System.out.println("退出成功，欢迎下次使用！");
//                        退出当前正在运行的JVM虚拟机
                        System.exit(0);
                    default:
                        System.out.print("您的输入有误，请重新输入。");
                        break lo1;
                }
            }
        }
    }
}
