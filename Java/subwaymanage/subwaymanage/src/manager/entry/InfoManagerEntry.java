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
            System.out.println("��ӭ������������!");
            System.out.println("1����������");
            System.out.println("2������������");
            System.out.println("3���˳�ϵͳ");
            System.out.println("������Ҫѡ��Ĳ�����1~3����");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
//                        ����������·ϵͳ
                        RouteController routeController = new RouteController();
                        routeController.start();
                        break lo;
                    case "2":
//                        ������ϵͳ
                        CardController cardController = new CardController();
                        cardController.start();
                        break lo;
                    case "3":
                        System.out.println("�˳��ɹ�����ӭ�´�ʹ�ã�");
//                        �˳���ǰ�������е�JVM�����
                        System.exit(0);
                    default:
                        System.out.print("���������������������롣");
                        break lo1;
                }
            }
        }
    }
}
