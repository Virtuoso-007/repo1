package secret;

import java.io.*;

public class Secret {
    private static String path1 = "D:\\360Downloads\\mimi.jpg";
    private static String path2 = "D:\\360Downloads\\mimimi.jpg";

    public static void main(String[] args) {
        doSecret(path1, path2);//º”√‹Ω‚√‹
    }

    public static void doSecret(String path1, String path2) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(path1);
            fos = new FileOutputStream(path2);
            byte[] b = new byte[1024];
            int len;
            while ((len = fis.read(b)) != -1) {
                for (int i = 0; i < len; i++) {
                    b[i] = (byte) (b[i] ^ 5);
                }
                fos.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void noSecret(String path1, String path2) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(path1);
            fos = new FileOutputStream(path2);
            byte[] b = new byte[1024];
            int len;
            while ((len = fis.read(b)) != -1) {
                for (int i = 0; i < len; i++) {
                    b[i] = (byte) (b[i] ^ 5);
                }
                fos.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        try {
            fis.close();
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
