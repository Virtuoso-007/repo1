package manager.dao;

import manager.domain.RouteInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class OtherRouteDao   {
    //        创建站点数组
//IO流实现以后，数组改为集合
    private static RouteInfo[] routeInfos1 = new RouteInfo[23];//地铁1号线的对象数组
    private static RouteInfo[] routeInfos2 = new RouteInfo[24];//地铁2号线的对象数组
    private static String[] line1;// 地铁1号线的名称数组
    private static String[] line2;//地铁2号线的名称数组
    private static ArrayList<String> linea = new ArrayList<>();
    private static ArrayList<String> lineb = new ArrayList<>();
//    static {
//        BufferedReader br1 = null;
//        BufferedReader br2 = null;
//        try {
//            br1 = new BufferedReader(new FileReader("subway\\M1.txt"));
//            br2 = new BufferedReader(new FileReader("subway\\M2.txt"));
//        }catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            String s;
//
//            while ((s = br1.readLine()) != null) {
//                linea.add(s);
//            }
//            br1.close();
//
//            while ((s = br2.readLine()) != null) {
//                lineb.add(s);
//            }
//            br2.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    //        需要修改，用IO流存取包含线路1的route1.txt
    static{
        File file = new File("subwaymanage\\src\\subway.txt");//文件对象
        BufferedReader br = null;//字符流
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = null;//读取文件的中间变量
        ArrayList<String> subName = new ArrayList<>(); //转存地铁站信息的中间转换集合
        while(true){
            try {
                s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(s == null){
                break;
            }
            subName.add(s);
        }
        line1 = subName.get(0).split(",");//把地铁站的信息存放到数组当中 1号线
        line2 = subName.get(1).split(",");//把地铁站的信息存放到数组当中 1号线

        for (int i = 0; i < routeInfos1.length; i++) {//循环创建对象添加到对象数组中 1号线
            String money = "1";
            RouteInfo routea=new RouteInfo(i+1+"",line1[i],money);
            routeInfos1[i] =  routea;
        }
        for (int i = 0; i < routeInfos2.length; i++) {//循环创建对象添加到对象数组中 2号线
            String money = "1";
            RouteInfo routea=new RouteInfo(i+1+"",line2[i],money);
            routeInfos2[i] = routea;
        }
    for (String s1 : line1) {
        linea.add(s1);
    }
    for (String s2 : line2) {
        lineb.add(s2);
    }


    }

    public OtherRouteDao() throws FileNotFoundException {
    }

    public RouteInfo[] getRouteInfos1() {
        return routeInfos1;
    }

    public RouteInfo[] getRouteInfos2() {
        return routeInfos2;
    }

    public String[] getLine1() {
        return line1;
    }

    public String[] getLine2() {
        return line2;
    }

    public void setRouteInfos1(RouteInfo[] routeInfos1) {
        OtherRouteDao.routeInfos1 = routeInfos1;
    }

    public void setRouteInfos2(RouteInfo[] routeInfos2) {
        OtherRouteDao.routeInfos2 = routeInfos2;
    }

    public void setLine1(String[] line1) {
        OtherRouteDao.line1 = line1;
    }

    public void setLine2(String[] line2) {
        OtherRouteDao.line2 = line2;
    }

    public boolean addRoute(RouteInfo routeInfo) {
//需要修改
////        添加老师到数组，判断老师是否存在
//        int index = -1;
//        for (int i = 0; i < routeInfos.length; i++) {
//            Route route1 = routeInfos[i];
////            如果ID不存在，用index记录ID
//            if (route1 == null) {
//                index = i;
//                break;
//            }
//        }
//        if (index == -1) {//装满了
//            return false;
//        } else {//没装满，正常添加
//            routeInfos[index] = route;
        return true;
//        }
    }

    public RouteInfo[] findAllRoute(int route) {
        //通过用户输入所要查看的线路，来判断所要返回的线路数组
        if (route == 1) {

            return routeInfos1;

        }else if(route == 2){
            return routeInfos2;
        }else{
            return null;
        }
    }


    public int getIndex(String id) {
//        需要修改
        int index = -1;
//        for (int i = 0; i < routeInfos.length; i++) {
//            Route route = routeInfos[i];
//            if (route != null && route.getId().equals(id)) {
//                index = i;
//                break;
//            }
//        }
        return index;
    }

    public void updateRoute(String updateId, RouteInfo newroute) {
//        需要修改
////        查找ID所在索引位置
//        int index = getIndex(updateId);
////        该索引位置替换
//        routeInfos[index] = newroute;
    }

    public String[][] buyTickets(String start, String end) {
        //创建二维数组，存放起始站和终点站的信息
        String[][] result = new String[2][2];
        String[] startarea = new String[2];
        String[] endarea = new String[2];
        //开始判断，如果起始站为中转站西单，那么判断终点站所在线路
        if (start.equals("西单")){
            //遍历一号线数组，通过站名判断，来获取终点站的站点id以及所在线路
            for (int i = 0; i < routeInfos1.length; i++) {
                RouteInfo routeInfo = routeInfos1[i];
                if(routeInfo.getName().equals(end)){
                    endarea[0] = routeInfo.getId();
                    endarea[1] = "一号线";
                }
            }

            for (int i = 0; i < routeInfos2.length; i++) {
                RouteInfo routeInfo = routeInfos2[i];
                if(routeInfo.getName().equals(end)){
                    endarea[0] = routeInfo.getId();
                    endarea[1] = "二号线";
                }
            }
            //因为起始站是中转站西单，所以将起始站所在线路设置为终点站所在线路，以便于金额计算
            if (endarea[1].equals("一号线")){
                startarea[0] = "13";
                startarea[1] = "一号线";
            }else{
                startarea[0] = "17";
                startarea[1] = "二号线";
            }
            //如果终点站为西单，进行以上相同判断，来将起始站和终点站所在线路设置相同。
        }else if (end.equals("西单")){
            for (int i = 0; i < routeInfos1.length; i++) {
                RouteInfo routeInfo = routeInfos1[i];
                if(routeInfo.getName().equals(start)){
                    startarea[0] = routeInfo.getId();
                    startarea[1] = "一号线";
                }
            }
            for (int i = 0; i < routeInfos2.length; i++) {
                RouteInfo routeInfo = routeInfos2[i];
                if(routeInfo.getName().equals(start)){
                    startarea[0] = routeInfo.getId();
                    startarea[1] = "二号线";
                }
            }
            if (startarea[1].equals("一号线")){
                endarea[0] = "13";
                endarea[1] = "一号线";
            }else{
                endarea[0] = "17";
                endarea[1] = "二号线";
            }
        }else{
            //如果起始站和终点站都不是西单，那么通过线路遍历，来获取起始站以及终点站的id和所在线路
            //并且将这些信息传入数组
            for (int i = 0; i < routeInfos1.length; i++) {
                RouteInfo routeInfo = routeInfos1[i];
                if (routeInfo.getName().equals(start)){
                    startarea[0] = routeInfo.getId();
                    startarea[1] = "一号线";
                }else if(routeInfo.getName().equals(end)){
                    endarea[0] = routeInfo.getId();
                    endarea[1] = "一号线";
                }
            }
            for (int i = 0; i < routeInfos2.length; i++) {
                RouteInfo routeInfo = routeInfos2[i];
                if (routeInfo.getName().equals(start)){
                    startarea[0] = routeInfo.getId();
                    startarea[1] = "二号线";
                }else if(routeInfo.getName().equals(end)){
                    endarea[0] = routeInfo.getId();
                    endarea[1] = "二号线";
                }
            }
        }
        //将起始站信息数组和终点站信息数组传入result二维数组，并且返回
        result[0] = startarea;
        result[1] = endarea;
        return result;
    }
    /*static {
//        用数组设置路程，不容易变动
//        两条线路每条13站手动添加
        RouteInfo route1a=new RouteInfo("1","苹果园","1");
        RouteInfo route2a=new RouteInfo("2","古城路","1");
        RouteInfo route3a=new RouteInfo("3","八角游乐园","1");
        RouteInfo route4a=new RouteInfo("4","八宝山","1");
        RouteInfo route5a=new RouteInfo("5","玉泉路","1");
        RouteInfo route6a=new RouteInfo("6","五棵松","2");
        RouteInfo route7a=new RouteInfo("7","万寿路","2");
        RouteInfo route8a=new RouteInfo("8","公主坟","2");
        RouteInfo route9a=new RouteInfo("9","军事博物馆","2");
        RouteInfo route10a=new RouteInfo("10","木樨地","2");
        RouteInfo route11a=new RouteInfo("11","南礼士路","1");
        RouteInfo route12a=new RouteInfo("12","复兴门","3");
        RouteInfo route13a=new RouteInfo("13","西单","1");
        RouteInfo route14a=new RouteInfo("14","天安门西","3");
        RouteInfo route15a=new RouteInfo("15","天安门东","4");
        RouteInfo route16a=new RouteInfo("16","王府井","2");
        RouteInfo route17a=new RouteInfo("17","东单","2");
        RouteInfo route18a=new RouteInfo("18","建国门","1");
        RouteInfo route19a=new RouteInfo("19","永安里","1");
        RouteInfo route20a=new RouteInfo("20","国贸","1");
        RouteInfo route21a=new RouteInfo("21","大望西","1");
        RouteInfo route22a=new RouteInfo("22","四惠","1");
        RouteInfo route23a=new RouteInfo("23","四惠东","1");


        //需要修改，这里没办法传入递增的变量
//        for (int i = 0; i < routeInfos1[].length; i++) {
//            String route="route"+i+"a";
//            routeInfos1[i]=route;
//        }
        routeInfos1[0] = route1a;
        routeInfos1[1] = route2a;
        routeInfos1[2] = route3a;
        routeInfos1[3] = route4a;
        routeInfos1[4] = route5a;
        routeInfos1[5] = route6a;
        routeInfos1[6] = route7a;
        routeInfos1[7] = route8a;
        routeInfos1[8] = route9a;
        routeInfos1[9] = route10a;
        routeInfos1[10] = route11a;
        routeInfos1[11] = route12a;
        routeInfos1[12] = route13a;
        routeInfos1[13] = route14a;
        routeInfos1[14] = route15a;
        routeInfos1[15] = route16a;
        routeInfos1[16] = route17a;
        routeInfos1[17] = route18a;
        routeInfos1[18] = route19a;
        routeInfos1[19] = route20a;
        routeInfos1[20] = route21a;
        routeInfos1[21] = route22a;
        routeInfos1[22] = route23a;


//        需要修改，用IO流存取包含线路4的route4.txt
        RouteInfo route1b=new RouteInfo("1","安河桥北","1");
        RouteInfo route2b=new RouteInfo("2","北宫门","1");
        RouteInfo route3b=new RouteInfo("3","西苑","1");
        RouteInfo route4b=new RouteInfo("4","圆明园","1");
        RouteInfo route5b=new RouteInfo("5","北京大学东门","1");
        RouteInfo route6b=new RouteInfo("6","中关村","1");
        RouteInfo route7b=new RouteInfo("7","海淀黄庄","1");
        RouteInfo route8b=new RouteInfo("8","人民大学","1");
        RouteInfo route9b=new RouteInfo("9","魏公村","1");
        RouteInfo route10b=new RouteInfo("10","国家图书馆","1");
        RouteInfo route11b=new RouteInfo("11","动物园","1");
        RouteInfo route12b=new RouteInfo("12","西直门","1");
        RouteInfo route13b=new RouteInfo("13","新街口","1");
        RouteInfo route14b=new RouteInfo("14","平安里","1");
        RouteInfo route15b=new RouteInfo("15","西四","1");
        RouteInfo route16b=new RouteInfo("16","灵魂胡同","1");
        RouteInfo route17b=new RouteInfo("17","西单","1");
        RouteInfo route18b=new RouteInfo("18","宣武门","1");
        RouteInfo route19b=new RouteInfo("19","莱市口","1");
        RouteInfo route20b=new RouteInfo("20","陶然亭","1");
        RouteInfo route21b=new RouteInfo("21","北京南站","1");
        RouteInfo route22b=new RouteInfo("22","马家堡","1");
        RouteInfo route23b=new RouteInfo("23","角门西","1");
        RouteInfo route24b=new RouteInfo("24","公益西桥","1");
        //需要修改，循环添加递增变量名的变量
        routeInfos2[0] = route1b;
        routeInfos2[1] = route2b;
        routeInfos2[2] = route3b;
        routeInfos2[3] = route4b;
        routeInfos2[4] = route5b;
        routeInfos2[5] = route6b;
        routeInfos2[6] = route7b;
        routeInfos2[7] = route8b;
        routeInfos2[8] = route9b;
        routeInfos2[9] = route10b;
        routeInfos2[10] = route11b;
        routeInfos2[11] = route12b;
        routeInfos2[12] = route13b;
        routeInfos2[13] = route14b;
        routeInfos2[14] = route15b;
        routeInfos2[15] = route16b;
        routeInfos2[16] = route17b;
        routeInfos2[17] = route18b;
        routeInfos2[18] = route19b;
        routeInfos2[19] = route20b;
        routeInfos2[20] = route21b;
        routeInfos2[21] = route22b;
        routeInfos2[22] = route23b;
        routeInfos2[23] = route24b;
    }*/
    //获取所有线路数组


    public RouteInfo[][] findAll() {
        //新建一个二维数组，将所有线路添加到数组中并返回
        RouteInfo[][] routeInfos = new RouteInfo[2][2];
        routeInfos[0] = routeInfos1;
        routeInfos[1] = routeInfos2;
        return routeInfos;
    }

    public ArrayList<String> searchroute1() {
        return linea;
    }
    public ArrayList<String> searchroute2() {
        return lineb;
    }
}
