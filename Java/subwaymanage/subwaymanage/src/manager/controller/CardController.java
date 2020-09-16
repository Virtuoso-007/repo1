package manager.controller;

import manager.domain.CardInfo;
import manager.service.BaseCardService;
import manager.service.CardService;
import manager.service.OtherCardService;

import java.util.Scanner;



    //系统开始的接口
    public  class CardController {

        //多态创建对象，可以切换CardService类和OtherCardService类
        private OtherCardService cardService = new OtherCardService();

        private Scanner sc = new Scanner(System.in);

        private String idLogin;

        //系统开始的接口
        public void start() {
            Loop:
            while (true) {
                System.out.println("--------欢迎来到 <地铁卡> 管理系统--------");
                System.out.println("请输入您的选择: 1.注册地铁卡  2.登录  0.退出地铁卡管理系统");
                String choice = sc.next();
                switch (choice) {
                    case "1":
                        //注册地铁卡
                        addCard();
                        break;
                    case "2":
                        //登录
                        idLogin = login();
                        if (idLogin != null) {
                            set();
                        }
                        break;
                    case "0":
                        //退出地铁卡管理系统
                        System.out.println("感谢您使用地铁卡管理系统，再见！");
                        if (idLogin != null) {
                            logOut(idLogin);
                        }
                        exit();
                        break Loop;
                    default:
                        System.out.println("您的输入有误，请重新输入");
                }
            }
        }

        public void set() {
            Loop:
            while (true) {
                System.out.println("请输入您的选择: 1.查询余额  2.充值  3.修改用户名  4.修改密码  5.注销地铁卡  0.登出并返回上一层");
                String choice = sc.next();
                switch (choice) {
                    case "1":
                        //查询余额
                        getRemainingMoney(idLogin);
                        break;
                    case "2":
                        //充值
                        recharge(idLogin);
                        break;
                    case "3":
                        //修改用户名
                        modifyCardName(idLogin);
                        break;
                    case "4":
                        //修改密码
                        modifyCardPassword(idLogin);
                        logOut(idLogin);
                        try {
                            Thread.currentThread().sleep(5000);
                            break Loop;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        break;
                    case "5":
                        //注销卡
                        deleteCard(idLogin);
                        break Loop;
                    case "0":
                        //退出地铁卡管理系统
                        System.out.println("该账户已登出！");
                        if (idLogin != null) {
                            logOut(idLogin);
                        }
                        exit();
                        break Loop;
                    default:
                        System.out.println("您的输入有误，请重新输入");
                }
            }
        }

        //查询用户信息业务逻辑
        public CardInfo getCardInfo(String id) {
            CardInfo cardInfo = cardService.getCardInfo(id);

            if (cardInfo == null) {
                System.out.println("查无信息，请确认ID后重试。");
                return null;
            } else {
                return cardInfo;
            }
        }

        //面向用户
        public void getRemainingMoney(String id) {
            if (cardService.isExist(id)) {
                if (isLogin(id)) {
                    System.out.println(getCardInfo(id).getName() + "先生/女士：" + "\r\n" + "您的余额为：" + getCardInfo(id).getRemainingMoney());
                    if (Integer.parseInt(getCardInfo(id).getRemainingMoney()) <= 1) {
                        System.out.println("您的余额不足，请及时充值");
                    }
                } else {
                    System.out.println("该账号还未登录，请您先登录");
                }
            } else {
                System.out.println("您输入的ID不存在");
            }
        }


        //办理新卡业务逻辑
        //面向用户
        public void addCard() {
//        局部变量addId
            String addId = null;
            l:
            while (true) {
                System.out.println("请输入您要注册的地铁卡ID：（ID长度为5，首位为小写字母，后面的为0~9数字）");
                addId = sc.next();

                //判断ID是否符合输入规则：5位，第一位为小写字母，第2-5位为：0~9的数字。
                l1:
                while (true) {

                    //长度是否为5
                    if (addId.length() == 5) {

                        //将ID分解成char类型字符并存入数组，用于对字符类型判断
                        char[] chars = addId.toCharArray();

                        boolean flag = true;

                        if (!(chars[0] >= 97 && chars[0] <= 122)) {
                            flag = false;
                        }

                        for (int i = 1; i < chars.length; i++) {
                            if (!(chars[i] >= 48 && chars[i] <= 57)) {
                                flag = false;
                            }
                        }

                        if (flag) {
                            if (cardService.isExist(addId)) {
                                System.out.println("您输入的ID已存在，退出注册界面请按0，重新注册请按其他任意键");

                                String choose = sc.next();

                                if (choose.equals("0")) {
                                    break l;
                                } else {
                                    break l1;
                                }

                            } else {
                                CardInfo cardInfo = inputCardInfo(addId);

                                if (cardService.addCard(cardInfo)) {
                                    System.out.println("地铁卡注册成功");
                                    System.out.println(getCardInfo(addId).getName() + "先生/女士您好！欢迎办理北京地铁卡" +
                                            "您的卡号为：" + getCardInfo(addId).getId() + "；  现有余额：" + getCardInfo(addId).
                                            getRemainingMoney() + "");
                                    System.out.println("**********************************************************************");
                                    System.out.println("卡号为唯一识别ID，请牢记您的卡号和密码!");
                                    System.out.println("**********************************************************************");
                                    System.out.println("如需任何地铁卡相关帮助，请联系我们的工作人员，联系方式：xxx-xxxx-xxxx");
                                    break l;
                                } else {
                                    System.out.println("注册失败，请联系管理员或重新注册");
                                    break l;
                                }

                            }

                        } else {
                            System.out.println("您输入的ID格式有误，退出注册界面请按0，重新注册请按其他任意键");

                            String choose = sc.next();

                            if (choose.equals("0")) {
                                break l;
                            } else {
                                break l1;
                            }
                        }

                    } else {
                        System.out.println("您输入的ID长度有误，退出注册界面请按0，重新注册请按其他任意键");

                        String choose = sc.next();

                        if (choose.equals("0")) {
                            break l;
                        } else {
                            break l1;
                        }
                    }
                }
            }
        }

        public CardInfo inputCardInfo(String id) {
            //            根据唯一标识ID
            System.out.println("请输入用户姓名：");
            String name = sc.next();

            String password = setPassword();

            String remainingMoney = "0";

            CardInfo newcard = new CardInfo(id, name, password, remainingMoney);

            return newcard;
        }

        public String setPassword() {
            String password = null;
            l:
            while (true) {
                System.out.println("请输入地铁卡密码：（6位数字0~9）");

                password = sc.next();

                //判断password是否符合输入规则：6位，0~9的数字。

                if (cardService.setPassword(password)!=null) {
                    System.out.println("请再次输入密码：");
                    String password2 = sc.next();
                    if (password.equals(password2)) {
                        return password;
                    } else {
                        System.out.println("前后两次密码不一致，请重新输入");
                    }
                }else{
                    System.out.println("您输入的密码格式或长度不正确，请重新输入");
                }

            }
        }


        //登录业务逻辑
        //面向用户
        public String login() {
            l0:
            while (true) {
                System.out.println("请输入ID：");
                String loginId = sc.next();

                if (cardService.isExist(loginId)) {
                    int count = 3;

                    for (int i = 3; i >= 1; i--) {
                        System.out.println("请输入密码：（剩余输入机会" + i + "次）");
                        String loginPassword = sc.next();
                        if (loginPassword.equals(cardService.getCardInfo(loginId).getPassword())) {
                            System.out.println("登录成功");
                            modifyCardFlag(loginId, true);

                            return getCardInfo(loginId).getId();

                        } else {
                            System.out.println("密码不正确，请重新输入");
                        }
                        count--;
                    }

                    if (count == 0) {
                        System.out.println("密码多次不正确，请确认好id和密码");
                    }
                    return null;


                } else {
                    System.out.println("您输入的ID不存在,注册用户请按1，继续其他业务请按2，重新输入请按其他任意键");
                    String choice = sc.next();
                    if ("1".equals(choice)) {
                        addCard();
                    } else if ("2".equals(choice)) {
                        return null;
                    }
                }
            }
        }

        public boolean isLogin(String id) {
            return cardService.isLogin(id);
        }

        public void logOut(String id) {
            modifyCardFlag(id, false);
        }

        public void modifyCardFlag(String id, boolean flag) {
            cardService.modifyCardFlag(id, flag);
        }


        //充值和修改卡信息逻辑
        //面向用户
        public void recharge(String rechargeId) {
            if (cardService.isExist(rechargeId)) {
                while (true) {
                    if (isLogin(rechargeId)) {
                        System.out.println("请输入你要充值的金额：（20起充，整数金额）");
                        String rechargeMoney = sc.next();

                        if (cardService.recharge(rechargeId, rechargeMoney)) {
                            System.out.println("充值成功");
                            System.out.println(getCardInfo(rechargeId).getName() + "先生/女士：" + "\r\n" + "您的余额为：" + getCardInfo(rechargeId).getRemainingMoney());
                            break;
                        } else {
                            System.out.println("您输入的充值金额有误，请重新输入");
                        }
                    } else {
                        System.out.println("该账号还未登录，请您先登录");
                        break;
                    }
                }
            } else {
                System.out.println("您输入的ID不存在");
            }
        }

        public void modifyCardRemainingMoney(String id, String remainingMoney) {
            cardService.modifyCardRemainingMoney(id, remainingMoney);
        }

        //面向用户
        public void modifyCardName(String id) {
            if (cardService.isExist(id)) {
                if (isLogin(id)) {
                    System.out.println("请输入您的用户名：");
                    String name = sc.next();
                    cardService.modifyCardName(id, name);
                    System.out.println("用户名修改成功！你好，" + getCardInfo(id).getName() + "先生/女士");
                } else {
                    System.out.println("该账号还未登录，请您先登录");
                }
            } else {
                System.out.println("您输入的ID不存在");
            }
        }

        //面向用户
        public void modifyCardPassword(String id) {
            if (cardService.isExist(id)) {
                if (isLogin(id)) {
                    while (true) {
                        System.out.print("请输入您要设置的新密码，");
                        String password1 = sc.next();
                        if (cardService.setPassword(password1)!=null) {
                            System.out.println("请再次输入密码：");
                            String password2 = sc.next();
                            if (password1.equals(password2)) {
                                cardService.modifyCardPassword(id, password1);
                                System.out.println("密码修改成功！请记住您的密码。系统将于5s后自动登出");
                                break;
                            } else {
                                System.out.println("前后两次密码不一致，请重新输入");
                            }
                        }
                    }
                } else {
                    System.out.println("该账号还未登录，请您先登录");
                }
            } else {
                System.out.println("您输入的ID不存在");
            }
        }


        //删除卡业务逻辑
        //面向用户
        public void deleteCard(String delId) {
            if (cardService.isExist(delId)) {
                System.out.println("请确认是否注销该账号：按Y确认，按其它任意键取返回");
                String answer = sc.next();
                if ("Y".equals(answer) || "y".equals(answer)) {
                    cardService.deleteCard(delId);
                    System.out.println("账户注销成功！欢迎您再次使用北京地铁卡");
                } else {
                    return;
                }
            } else {
                System.out.println("您输入的ID不存在");
            }
        }

        public void exit() {
            cardService.exit();
        }

        public boolean isExist(String id) {
            return cardService.isExist(id);
        }
    }
