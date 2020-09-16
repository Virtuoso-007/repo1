package manager.service;

import manager.dao.OtherCardDao;
import manager.domain.CardInfo;

import java.util.HashMap;

public class OtherCardService implements BaseCardService {
    OtherCardDao otherCardDao = new OtherCardDao();

    private HashMap<String, CardInfo> hm = otherCardDao.findAllCards();

    public boolean addCard(CardInfo cardInfo) {
        if (cardInfo != null) {
            hm.put(cardInfo.getId(), cardInfo);
            return true;
        } else {
            return false;
        }
    }

    public boolean isExist(String id) {

//        假设id不存在
        boolean exists = false;

        if (hm != null) {
            exists = hm.containsKey(id);
        }

        return exists;
    }

    public boolean isLogin(String id) {
        if (isExist(id)) {
            return getCardInfo(id).isFlag();
        } else {
            return false;
        }
    }

    public CardInfo getCardInfo(String id) {
        if (isExist(id)) {
            return hm.get(id);
        } else {
            return null;
        }
    }

    public String setPassword(String password) {
        if (password.length() == 6) {

            //将password分解成char类型字符并存入数组，用于对字符类型判断
            char[] chars = password.toCharArray();

            boolean flag = true;

            for (int i = 0; i < chars.length; i++) {
                if (!(chars[i] >= 48 && chars[i] <= 57)) {
                    flag = false;
                }

            }
            if(flag){
                return password;
            }
        }

        return null;
    }

    public void deleteCard(String delId) {
        hm.remove(delId);
    }

    public boolean recharge(String rechargeID, String rechargeMoney) {
        try {
            if (Integer.parseInt(rechargeMoney) >= 20) {
                CardInfo cardInfo = hm.get(rechargeID);

                String remainingMoney = cardInfo.getRemainingMoney();
                String money = String.valueOf(Integer.parseInt(remainingMoney) + Integer.parseInt(rechargeMoney));

                cardInfo.setRemainingMoney(money);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void modifyCardRemainingMoney(String id, String remainingMoney) {
        hm.get(id).setRemainingMoney(remainingMoney);
    }

    public void modifyCardFlag(String id, boolean flag) {
        if (isExist(id)) {
            hm.get(id).setFlag(flag);
        }
    }

    public void modifyCardName(String id, String name) {
        hm.get(id).setName(name);
    }

    public void modifyCardPassword(String id, String password1) {
        hm.get(id).setPassword(password1);
    }

    public void exit() {
        otherCardDao.restore();
    }
}