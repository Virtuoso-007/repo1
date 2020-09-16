package internet;

import org.junit.Test;

import java.io.*;
import java.net.*;

public class Internet {
    @Test
    public void shenm(){
        System.out.println("为什么");
    }
    @Test
    public void client() throws MalformedURLException {
        URL url =new URL("");
        Socket socket = null;
        OutputStream ops = null;
        FileInputStream fis = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 7288);
            ops = socket.getOutputStream();
            fis = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\pic1.png"));
            byte[] b = new byte[1024];
            int len;
            while ((len=fis.read(b))!=-1){
                ops.write(b,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("传输完成！");
            try {if(socket!=null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ops.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void server() {
        Socket socket=new Socket();
        ServerSocket ss = null;
        Socket accept = null;
        InputStream ips = null;
        FileOutputStream fos= null;
        try {
            ss = new ServerSocket(7288);
            accept = ss.accept();
            ips = accept.getInputStream();
            fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\pic1didi.png"));
            byte[] bytes=new byte[1024];
            int in;
            while ((in=ips.read(bytes))!=-1){
                fos.write(bytes,0,in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("收到啦！");
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                accept.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ips.close();
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
}
