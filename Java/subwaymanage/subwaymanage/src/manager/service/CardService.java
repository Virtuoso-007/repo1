package manager.service;

import manager.dao.CardDao;
import manager.domain.CardInfo;

import java.util.HashMap;

public class CardService implements BaseCardService{
    CardDao cardDao = new CardDao();

    private HashMap<String, CardInfo> hm = cardDao.findAllCards();

    @Override
    public boolean addCard(CardInfo cardInfo) {
        return cardDao.addCard(cardInfo);
    }


    @Override
    public boolean isExist(String id) {

//        假设id不存在
        boolean exists = false;

        if (hm != null) {
            exists = hm.containsKey(id);
        }

        return exists;
    }

    @Override
    public boolean isLogin(String id) {
        if (isExist(id)) {
            return getCardInfo(id).isFlag();
        } else {
            return false;
        }
    }


    @Override
    public CardInfo getCardInfo(String id) {
        if (isExist(id)) {
            return hm.get(id);
        } else {
            return null;
        }
    }


    @Override
    public void deleteCard(String delId) {
        cardDao.deleteCard(delId);
    }


    @Override
    public boolean recharge(String rechargeID,String rechargeMoney) {
        try {
            if (Integer.parseInt(rechargeMoney) >= 20) {
                cardDao.recharge(rechargeID,rechargeMoney);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void modifyCardRemainingMoney(String id,String remainingMoney) {
        cardDao.modifyCardRemainingMoney(id,remainingMoney);
    }

    @Override
    public void modifyCardFlag(String id, boolean flag) {
        if (isExist(id)) {
            cardDao.modifyCardFlag(id, flag);
        }
    }

    @Override
    public void modifyCardName(String id, String name) {
        cardDao.modifyCardName(id,name);
    }

    @Override
    public void modifyCardPassword(String id, String password1) {
        cardDao.modifyCardPassword(id,password1);
    }

    @Override
    public void exit(){}
}