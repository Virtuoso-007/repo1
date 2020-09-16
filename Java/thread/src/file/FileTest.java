package file;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FileTest {
    HashMap<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        FileTest ft = new FileTest();
        ft.read();
        ft.write();
    }

    public void read() {

        FileReader fr = null;
        try {
            fr = new FileReader("C:\\Users\\Administrator\\Desktop\\what.txt");
            int l;
            char[] data = new char[1024];
            while ((l = fr.read()) != -1) {
                char c = (char) l;
                System.out.print(c);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void write() {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\学习计划123.txt");
            osw = new OutputStreamWriter(fos);
            Set<Map.Entry<Character, Integer>> entries = map.entrySet();
            Iterator<Map.Entry<Character, Integer>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<Character, Integer> next = iterator.next();
                String str = next.toString();
                osw.write(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        try {
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
