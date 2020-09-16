package manager.dao;

import manager.domain.CardInfo;

import java.io.*;
import java.util.HashMap;

public class OtherCardDao{
    private static HashMap<String,CardInfo> hm=new HashMap<>();

    static {
        try {
            ObjectInputStream ois= new ObjectInputStream(new FileInputStream("subwaymanage\\cards.txt"));
            if(ois!=null){
                hm  = (HashMap<String, CardInfo>) ois.readObject();
            }

            ois.close();
        } catch (IOException e) {

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String,CardInfo> findAllCards(){
        return hm;
    }

    public void restore(){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("subwaymanage\\cards.txt"));

            oos.writeObject(hm);

            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
