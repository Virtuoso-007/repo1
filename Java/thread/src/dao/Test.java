package dao;

import java.util.HashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        DAO<User> d=new DAO();
        d.map=new HashMap<String, User>();
        d.save("123",new User(12,"zhao"));
        User uu = d.get("123");
        System.out.println(uu);
        d.update("123",new User(13,"yuan"));
        uu = d.get("123");
        System.out.println(uu);
        d.save("13",new User(15,"tom"));
        d.save("23",new User(17,"jerry"));
        d.delete("123");
        List<User> list = d.list();
        System.out.println(list);

    }



}
