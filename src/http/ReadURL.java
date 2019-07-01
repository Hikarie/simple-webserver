package http;

import java.io. BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadURL {
    public static void main(String args[]) throws Exception {
        try {
               URL url = new URL("http://www.baidu.com");
               InputStreamReader isr = new InputStreamReader(url.openStream());
               BufferedReader br = new BufferedReader(isr);

               String str;
               while ((str= br.readLine()) !=null) {
                      System.out.println(str);
           }

               br.close();
               isr.close();
        } catch (Exception e) {
               System.out.println(e);
               }
        }
}
