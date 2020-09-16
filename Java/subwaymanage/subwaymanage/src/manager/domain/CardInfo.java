package manager.domain;

import java.io.Serializable;

public class CardInfo implements Serializable {
    private String id;          //用户<唯一>识别标志，
    private String name;        //用户姓名，可重复，无实际作用，仅在注册时需要填写。登录或买票成功后可以用于温馨提示
    private String password;    //密码，用户登录账户安全保障，6位数字
    private boolean flag = false;       //标志，可以记录用户是否是登录成功状态，默认false，表示未登录状态
    private String remainingMoney; //余额，整数类型变量，不可以小于0，小于20元时，系统给予充值提示
    private static final long serialVersionNumber = 10086L; //序列号

    public CardInfo() {
    }

    public CardInfo(String id, String name, String password, String remainingMoney) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.remainingMoney = remainingMoney;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getRemainingMoney() {
        return remainingMoney;
    }

    public void setRemainingMoney(String remainingMoney) {
        this.remainingMoney = remainingMoney;
    }

    @Override
    public String toString() {
        return "CardInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", flag=" + flag +
                ", remainingMoney=" + remainingMoney +
                '}';
    }
}
