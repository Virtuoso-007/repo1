package manager.dao;

import manager.domain.CardInfo;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public class CardDao{
    private static HashMap<String,CardInfo> hm=new HashMap<>();
    static{
        File file = new File("subwaymanage\\src\\cards");
        if(!file.exists()){
            file.mkdirs();
        }
    }

    public boolean addCard(CardInfo cardInfo) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("subwaymanage\\src\\cards\\" + UUID.randomUUID() + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (cardInfo != null) {
            try {
                oos.writeObject(cardInfo);
                hm.put(cardInfo.getId(), cardInfo);
                oos.flush();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

    public HashMap<String, CardInfo> findAllCards() {
        File filePackage = new File("src\\cards");

        File[] files = filePackage.listFiles();

        if (files != null) {
            for (File file : files) {
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(new FileInputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while (true) {
                    try {
                        CardInfo cardInfo = (CardInfo) ois.readObject();
                        hm.put(cardInfo.getId(), cardInfo);
                        ois.close();
                    } catch (IOException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

//                try {
//                    ois.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }

        return hm;
    }

    public void deleteCard(String delId) {
        File filePackage = new File("subwaymanage\\src\\cards");

        File[] files = filePackage.listFiles();

        if (files != null) {
            for (File file : files) {
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(new FileInputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while (true) {
                    try {
                        CardInfo cardInfo = (CardInfo) ois.readObject();
                        if (delId.equals(cardInfo.getId())) {
                            ois.close();            //特别注意：此处如果不关流就无法删除文件！！！
                            file.delete();

                            hm.remove(delId);
                            break;
                        }
                    } catch (IOException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

//                try {
//                    ois.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }


    public void recharge(String rechargeID, String rechargeMoney) {
        CardInfo cardInfo = findAllCards().get(rechargeID);

        String remainingMoney = cardInfo.getRemainingMoney();

        String remainingMoney1 = String.valueOf((Integer.parseInt(remainingMoney) + Integer.parseInt(rechargeMoney)));

        cardInfo.setRemainingMoney(remainingMoney1);

        modifyCardFile(rechargeID, cardInfo);
    }

    public void modifyCardRemainingMoney(String id, String remainingMoney) {
        CardInfo cardInfo = findAllCards().get(id);

        cardInfo.setRemainingMoney(remainingMoney);

        modifyCardFile(id, cardInfo);
    }

    public void modifyCardFlag(String id, boolean flag) {
        CardInfo cardInfo = findAllCards().get(id);

        cardInfo.setFlag(flag);

        modifyCardFile(id, cardInfo);
    }

    public void modifyCardName(String id, String name) {
        CardInfo cardInfo = findAllCards().get(id);

        cardInfo.setName(name);

        modifyCardFile(id, cardInfo);
    }

    public void modifyCardFile(String id, CardInfo cardInfo) {
        File filePackage = new File("subwaymanage\\src\\cards");

        File[] files = filePackage.listFiles();

        if (files != null) {
            for (File file : files) {
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(new FileInputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while (true) {
                    try {
                        CardInfo cardInfo2 = (CardInfo) ois.readObject();
                        if (id.equals(cardInfo2.getId())) {
                            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

                            oos.writeObject(cardInfo);
                            oos.flush();
                            oos.close();

                            ois.close();
                            break;
                        }
                    } catch (IOException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void modifyCardPassword(String id, String password1) {
        CardInfo cardInfo = findAllCards().get(id);

        cardInfo.setPassword(password1);

        modifyCardFile(id, cardInfo);
    }
}
