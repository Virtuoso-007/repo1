package manager.service;

import manager.dao.OtherRouteDao;
import manager.domain.RouteInfo;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RouteService {
    private OtherRouteDao otherRouteDao = new OtherRouteDao();

    public RouteService() throws FileNotFoundException {
    }

    public boolean addRoute(RouteInfo routeInfo) {
//        老师对象交给RouteDao库管
//        RouteDao routeDao=new RouteDao();
//库管来找老师是否存在
        return otherRouteDao.addRoute(routeInfo);
    }

    public boolean isExists(String id) {
//        RouteDao routeDao=new RouteDao();
        RouteInfo[] routeInfos = otherRouteDao.findAllRoute(1);
//        假设id不存在
        boolean exists = false;
//        遍历数组
        for (int i = 0; i < routeInfos.length; i++) {
            RouteInfo routeInfo = routeInfos[i];
            if (routeInfo != null && routeInfo.getId().equals(id)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public RouteInfo[] findAllRoute(int route) {
//        通过输入线路判断，来设置flag的值
        RouteInfo[] allRouteInfo = otherRouteDao.findAllRoute(route);
        boolean flag = false;
        //如果设置传入线路编号的线路数组不为空，那么将flag修改为true
        lo1:
        if (allRouteInfo != null) {
            for (int i = 0; i < allRouteInfo.length; i++) {
                RouteInfo routeInfo = allRouteInfo[i];
                if (routeInfo != null) {
                    flag = true;
                    break lo1;
                }
            }
        }
        //flag为true就传入相应数组，否则传入空
        if (flag == true) {
            return allRouteInfo;
        } else {
            return null;
        }
    }

    public void updateRoute(String updateId, RouteInfo newroute) {
        otherRouteDao.updateRoute(updateId, newroute);
    }

    //买票方法。返回一个长度为2的数组，第一项为花费金额，第二项为转线状态
    public int[] buyTickets(String start, String end) {
        int[] infomation = new int[2];
        int price = 0;
        //如果起始站和终点站相等，则输入有误返回金额为0
        if (start.equals(end)) {
            infomation[0] = price;
            infomation[1] = 0;
            return infomation;
        } else {
            //otherRouteDao里buyTickets方法返回的是一个二维数组，其中包含起始站和终点站的信息
            //起始站和终点站里又包含站点ID以及线路名称
            try {
                String[][] result = otherRouteDao.buyTickets(start, end);
                //设置二维数组第一项为起始站信息数组，第二项为终点站信息数组
                String[] startarea = result[0];
                String[] endarea = result[1];
                //通过判断。如果起始站和终点站线路相同

                if (startarea[1].equals(endarea[1])) {
                    //通过站点ID来计算金额
                    price = Math.abs(Integer.valueOf(startarea[0]) - Integer.valueOf(endarea[0]));
                    infomation[0] = price;
                    //无需转线，将转线状态设置为0
                    infomation[1] = 0;

                } else {   //如果不相同，则必须要转线
                    //判断起始线路 ，再计算金额
                    //转线需要通过中转站来计算所花费的金额
                    if (startarea[1].equals("一号线")) {
                        int price1 = Math.abs(13 - Integer.valueOf(startarea[0]));
                        int price2 = Math.abs(17 - Integer.valueOf(endarea[0]));
                        price = price1 + price2;
                    } else {
                        int price1 = Math.abs(13 - Integer.valueOf(endarea[0]));
                        int price2 = Math.abs(17 - Integer.valueOf(startarea[0]));
                        price = price1 + price2;
                    }
                    infomation[0] = price;
                    //因为需要转线，所以设置转线状态为1
                    infomation[1] = 1;
                }
            } catch (Exception e) {

            }

            return infomation;
        }

    }

    //获取所有线路数组
    public RouteInfo[][] findAll() {
        return otherRouteDao.findAll();
    }

    public void searchroute(String start, String end) {
        ArrayList<String> line1 = otherRouteDao.searchroute1();
        ArrayList<String> line2 = otherRouteDao.searchroute2();

        int index1 = 0;     //找到中转站在1号线中的索引
        int index2 = 0;     //找到中转站在2号线中的索引

        int indexStart = -1;     //确认起始站在对应线路中的索引
        int indexEnd = -1;       //确认终点站在对应线路中的索引

        int lineStart = -1;      //确认起始站的线路
        int lineEnd = -1;        //确认终点站的线路

        int count = 0;        //累计站数

        for (int i = 0; i < line1.size(); i++) {
            if ("西单".equals(line1.get(i))) {
                index1 = i;
                break;
            }
        }

        for (int i = 0; i < line2.size(); i++) {
            if ("西单".equals(line2.get(i))) {
                index2 = i;
                break;
            }
        }

        for (int i = 0; i < line1.size(); i++) {
            if (start.equals(line1.get(i))) {
                lineStart = 1;
                indexStart = i;
            } else if (end.equals(line1.get(i))) {
                lineEnd = 1;
                indexEnd = i;
            }
        }

        for (int i = 0; i < line2.size(); i++) {
            if (start.equals(line2.get(i))) {
                lineStart = 2;
                indexStart = i;
            } else if (end.equals(line2.get(i))) {
                lineEnd = 2;
                indexEnd = i;
            }
        }
        if (indexStart == -1 || indexEnd == -1) {
            System.out.println("您的输入有误，请重新选择！");
        } else {
            if (lineStart == lineEnd) {
                if (lineStart == 1) {
                    if (indexStart < indexEnd) {
                        for (int i = indexStart; i <= indexEnd; i++) {
                            if (i == indexEnd) {
                                System.out.print(line1.get(i) + "\r\n");
                                count++;
                            } else {
                                System.out.print(line1.get(i) + "->");
                                count++;
                            }
                        }
                    } else {
                        for (int i = indexStart; i >= indexEnd; i--) {
                            if (i == indexEnd) {
                                System.out.print(line1.get(i) + "\r\n");
                                count++;
                            } else {
                                System.out.print(line1.get(i) + "->");
                                count++;
                            }
                        }
                    }
                    System.out.println("总站为" + (count - 1) + "站！");
                }
                if (lineStart == 2) {
                    if (indexStart < indexEnd) {
                        for (int i = indexStart; i <= indexEnd; i++) {
                            if (i == indexEnd) {
                                System.out.print(line2.get(i) + "\r\n");
                                count++;
                            } else {
                                System.out.print(line2.get(i) + "->");
                                count++;
                            }
                        }
                    } else {
                        for (int i = indexStart; i >= indexEnd; i--) {
                            if (i == indexEnd) {
                                System.out.print(line2.get(i) + "\r\n");
                                count++;
                            } else {
                                System.out.print(line2.get(i) + "->");
                                count++;
                            }
                        }
                    }
                    System.out.println("总站为" + (count - 1) + "站！");
                }
            } else {
                if (lineStart == 1) {
                    if (indexStart > index1) {
                        for (int i = indexStart; i >= index1; i--) {
                            if (!line1.get(i).equals("西单")) {
                                System.out.print(line1.get(i) + "->");
                                count++;
                            }
                        }
                    } else {
                        for (int i = indexStart; i <= index1; i++) {
                            if (!line1.get(i).equals("西单")) {
                                System.out.print(line1.get(i) + "->");
                                count++;
                            }
                        }
                    }

                    if (indexEnd > index2) {
                        for (int i = index2; i <= indexEnd; i++) {
                            if (i == indexEnd) {
                                System.out.print(line2.get(i) + "\r\n");
                                count++;
                            } else {
                                System.out.print(line2.get(i) + "->");
                                count++;
                            }
                        }
                    } else {
                        for (int i = index2; i >= indexEnd; i--) {
                            if (i == indexEnd) {
                                System.out.print(line2.get(i) + "\r\n");
                                count++;
                            } else {
                                System.out.print(line2.get(i) + "->");
                                count++;
                            }
                        }
                    }
                    System.out.println("总站为" + (count - 1) + "站！");
                }
                if (lineStart == 2) {
                    if (indexStart > index2) {
                        for (int i = indexStart; i >= index2; i--) {
                            if (!line2.get(i).equals("西单")) {
                                System.out.print(line2.get(i) + "->");
                                count++;
                            }
                        }
                    } else {
                        for (int i = indexStart; i <= index2; i++) {
                            if (!line2.get(i).equals("西单")) {
                                System.out.print(line2.get(i) + "->");
                                count++;
                            }
                        }
                    }

                    if (indexEnd > index1) {
                        for (int i = index1; i <= indexEnd; i++) {
                            if (i == indexEnd) {
                                System.out.print(line1.get(i) + "\r\n");
                                count++;
                            } else {
                                System.out.print(line1.get(i) + "->");
                                count++;
                            }
                        }
                    } else {
                        for (int i = index1; i >= indexEnd; i--) {
                            if (i == indexEnd) {
                                System.out.print(line1.get(i) + "\r\n");
                                count++;
                            } else {
                                System.out.print(line1.get(i) + "->");
                                count++;
                            }
                        }
                    }
                    System.out.println("总站为" + (count - 1) + "站！");
                }
            }
        }
    }
}
