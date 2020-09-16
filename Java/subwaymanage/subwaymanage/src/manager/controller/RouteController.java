package manager.controller;

import manager.domain.RouteInfo;
import manager.service.CardService;
import manager.service.RouteService;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class RouteController {
    CardController cardController = new CardController();
    private Scanner sc = new Scanner(System.in);
    RouteService routeService = new RouteService();

    public RouteController() throws FileNotFoundException {
    }


    public void start() {

        l:
        while (true) {
            System.out.println("欢迎乘坐北京地铁!");
            System.out.println("1：查看路线站点列表");
            System.out.println("2：购票乘车");
            System.out.println("3：乘车路线查看");
            System.out.println("4：返回上一层");
            System.out.print("请输入要选择的操作（1~4）：");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
                        findAllRoutes();
                        break lo;
                    case "2":
                        //判断登录状态，如果为true，则进行购票操作,否则进行登录操作
                        //登录完成循环继续判断，如果登录成功开始买票，完毕跳出循环
                        while (true) {
                            System.out.println("请输入购票id，输入0登录(注意：每次购票前都需要重新登录)。");
                            Scanner sc = new Scanner(System.in);
                            String id = sc.next();
                            if (cardController.isLogin(id)) {
                                buyTickets(id);
                                break;
                            } else if (id.equals("0")) {
                                cardController.login();



                            } else {
                                System.out.println("该用户id未登录或不存在");
                            }

//                            if (cardController.isExist(id)) {
//                                System.out.println("请先登录");
//                                String s;
//                                if((s=cardController.login())!=null){
//                                    buyTickets(s);
//                                    break;
//                                }else  {
//
//                                    System.out.println("登录失败");
//                                    //cardController.addCard();
//                                }
//                            } else if(id.equals("0")){
//                                cardController.addCard();
//                            }
//                            else {
//                                System.out.println("ID不存在");
//
//                            }

                        }
                        break lo;
                    case "3":
                        searchroute();
                        break lo;
                    case "4":
                        System.out.println("返回上一层，成功！");
//                        退出当前正在运行的JVM虚拟机
                        break l;
                    default:
                        System.out.print("您的输入有误，请重新输入。");
                        break lo1;
                }
            }
        }
    }

    private void searchroute() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入起始站,或者输入EXIT退出：");
            String start = sc.next();
            if (start.equals("EXIT")) {
                break;
            }
            System.out.println("请输入终点站,或者输入EXIT退出：");
            String end = sc.next();
            if (end.equals("EXIT")) {
                break;
            }
            routeService.searchroute(start,end);
            break;
        }
    }

    private void buyTickets(String id) {
        //需要修改，输入起始站和终点站，并给出票价，进行完整购买
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入起始站,或者输入EXIT退出：");
            String start = sc.next();
            if (start.equals("EXIT")) {
                break;
            }
            System.out.println("请输入终点站,或者输入EXIT退出：");
            String end = sc.next();
            if (end.equals("EXIT")) {
                break;
            }
            //调用routeService里面的buyTickerts方法，将起始站和终点站作为参数，
            // 可以返回一个长度为2的数组，数组第一项为所花费的价格，第二项为判断是否需要转线
            int[] buyinfomation = routeService.buyTickets(start, end);
            int price = buyinfomation[0];
            if (price == 0) {
                //如果价格为0，说明终点站就是起始站，输入有误
                System.out.println("您的输入有误，请重新输入!或者输入EXIT退出");
            } else {
                routeService.searchroute(start, end);
                int remainmoney = Integer.valueOf(cardController.getCardInfo(id).getRemainingMoney());
                if (remainmoney >= price) {
                    System.out.println("购买成功，总共花费：" + price + "元。");
                    //获取的数组第二项如果为1那么则是需要转线
                    if (buyinfomation[1] == 1) {
                        System.out.println("请在西单进行转线");
                    }
                    int result = remainmoney - price;
                    cardController.modifyCardRemainingMoney(id, Integer.toString(result));
                    System.out.println("剩余金额为：" + Integer.valueOf(cardController.getCardInfo(id).getRemainingMoney()) + "元");
                    cardController.exit();
                } else {
                    System.out.println("余额不足，购买失败。"+"\r\n"+"请问是否充值：（Y/N）");
                    String s=sc.next();
                    if("y".equalsIgnoreCase(s)){
                        cardController.recharge(id);
                        routeService.searchroute(start, end);
                        int remainmoney1 = Integer.valueOf(cardController.getCardInfo(id).getRemainingMoney());
                        if (remainmoney1 >= price) {
                            System.out.println("购买成功，总共花费：" + price + "元。");
                            //获取的数组第二项如果为1那么则是需要转线
                            if (buyinfomation[1] == 1) {
                                System.out.println("请在西单进行转线");
                            }
                            int result = remainmoney1 - price;
                            cardController.modifyCardRemainingMoney(id, Integer.toString(result));
                            System.out.println("剩余金额为：" + Integer.valueOf(cardController.getCardInfo(id).getRemainingMoney()) + "元");
                            cardController.exit();
                        }
                    }

                }
                cardController.logOut(id);
                break;
            }

        }
    }


    public void findAllRoutes() {
        //请选择所查看的线路
        try {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("请输入查看的线路(输入0即可查看所有线路)：");
                int route = sc.nextInt();
                //通过输入线路获取路线数组
                //调用routeService里的findAllRoute方法，将输入线路作为参数传进去，
                //在这里findAllRoute是展示单条线路的功能，方法名为了方便没换
                //输入0展示所有线路的方法名则是findAll方法
                RouteInfo[] routeInfos = routeService.findAllRoute(route);
                if (routeInfos != null) {
                    for (int i = 0; i < routeInfos.length; i++) {
                        RouteInfo routeInfo = routeInfos[i];
                        if (i == routeInfos.length - 1) {
                            System.out.print(routeInfo.getName());
                        } else {
                            System.out.print(routeInfo.getName() + "-->");
                        }

                    }
                    break;
                    //对获取的数组进行判断，若数组不为空，且线路不为0则便利数组，获取制定线路
                } else if (routeInfos == null && route != 0) {
                    System.out.println("您的输入有误，请重新选择线路！");
                    //若输入线路为0，通过findAll方法来获取所有线路，所有线路使用一个二维数组装载
                } else if (route == 0) {
                    RouteInfo[][] routeInfos1 = routeService.findAll();
                    //遍历二维数组，获取所有线路
                    for (int i = 0; i < routeInfos1.length; i++) {
                        RouteInfo[] routeInfos2 = routeInfos1[i];
                        System.out.print((i + 1) + "号线：");
                        for (int i1 = 0; i1 < routeInfos2.length; i1++) {
                            RouteInfo routeInfo = routeInfos2[i1];
                            if (i1 == routeInfos2.length - 1) {
                                System.out.print(routeInfo.getName());
                            } else {
                                System.out.print(routeInfo.getName() + "-->");
                            }
                        }
                        System.out.println();
                    }
                    break;
                }
            }
            //为防止用户输入线路不为数组，设置异常处理。
        } catch (Exception e) {
            System.out.println("您的输入有误");
        }


//        需要修改，增加展示所有路线的功能，并可以继续输入路线号来单独查看
//        CardInfo[] cardInfos =studentService.findAllStudent();
////        判断数组是否为空
//        if(cardInfos ==null){
//            System.out.println("查无信息，请添加后重试。");
//            return;
//        }
////        遍历数组打印学生信息
//        System.out.println("学号\t\t姓名\t年龄\t\t生日");
//        for (int i = 0; i < cardInfos.length; i++) {
//            CardInfo cardInfo = cardInfos[i];
//            if(cardInfo !=null){
//                System.out.println(cardInfo.getId()+"\t\t"+ cardInfo.getName()+"\t"+ cardInfo.getAge()+"\t\t");
//            }
//        }
    }

//    录入站点ID，可以用来查询站点是否存在
    /*public String inputStudentId(){
        String Id=null;
        l:
        while (true){
            System.out.println("请输入学生的ID：");
            Id=sc.next();
//            判断ID是否存在
            l1:
            while (true){
                boolean exists = cardService.isExists(Id);
                if(!exists){//exists为负,则执行
                    System.out.println("ID不存在，退出请输入0，不退出请重新输入ID：");
                    int exit;
                    exit=sc.nextInt();
                    if(exit==0){
                        Id=null;
                        System.out.println("退出成功！");
                        break l;
                    }else {
                        Id= String.valueOf(exit);
                    }
                }else {
                    break l;
                }
            }
        }
        return Id;
    }*/

}
