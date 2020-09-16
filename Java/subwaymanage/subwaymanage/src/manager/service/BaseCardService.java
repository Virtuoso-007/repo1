package manager.service;

import manager.domain.CardInfo;

public interface BaseCardService {


    public boolean addCard(CardInfo cardInfo);



    public boolean isExist(String id);

    public boolean isLogin(String id);



    public CardInfo getCardInfo(String id);



    public void deleteCard(String delId);



    public boolean recharge(String rechargeID,String rechargeMoney);

    public void modifyCardRemainingMoney(String id,String remainingMoney);

    public void modifyCardFlag(String id, boolean flag);

    public void modifyCardName(String id, String name);

    public void modifyCardPassword(String id, String password1);

    public void exit();
}
