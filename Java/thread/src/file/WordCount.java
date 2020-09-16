package file;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WordCount {
    public WordCount() {
    }

    @Test
    public void testWordCount() {
        FileReader fr = null;
        BufferedWriter bw = null;

        try {
            Map<Character, Integer> map = new HashMap();
            fr = new FileReader("C:\\Users\\Administrator\\Desktop\\heiheihei.txt");
            boolean var4 = false;

            int c;
            while((c = fr.read()) != -1) {
                char ch = (char)c;
                if (map.get(ch) == null) {
                    map.put(ch, 1);
                } else {
                    map.put(ch, (Integer)map.get(ch) + 1);
                }
            }

            bw = new BufferedWriter(new FileWriter("C:\\Users\\Administrator\\Desktop\\wordcount.txt"));
            Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();

            for(Iterator var6 = entrySet.iterator(); var6.hasNext(); bw.newLine()) {
                Map.Entry<Character, Integer> entry = (Map.Entry)var6.next();
                switch((Character)entry.getKey()) {
                    case '\t':
                        bw.write("tab键=" + entry.getValue());
                        break;
                    case '\n':
                        bw.write("换行=" + entry.getValue());
                        break;
                    case '\r':
                        bw.write("回车=" + entry.getValue());
                        break;
                    case ' ':
                        bw.write("空格=" + entry.getValue());
                        break;
                    default:
                        bw.write(entry.getKey() + "=" + entry.getValue());
                }
            }
        } catch (IOException var20) {
            var20.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException var19) {
                    var19.printStackTrace();
                }
            }

            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException var18) {
                    var18.printStackTrace();
                }
            }

        }

    }
}
