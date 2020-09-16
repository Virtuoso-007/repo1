package reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args)  {
        FileInputStream fis= null;
        try {
            Properties pro = new Properties();
            fis = new FileInputStream("jdbc.properties");
            pro.load(fis);
            String name = pro.getProperty("name");
            String password = pro.getProperty("password");
            System.out.println(name);
            System.out.println(password);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
