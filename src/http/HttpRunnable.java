package http;

import java.awt.TextArea;
import java.net.Socket;

import ui.ServerFrame;

public class HttpRunnable extends HttpResponse implements Runnable{
	
	private String dictory;
	
	HttpRunnable(Socket sock, String dic) throws Exception{
		super(sock);
		dictory = dic;
	}
    public void run(){
        try {
           String filename = getRequest();
           send(dictory, filename);
        } catch (Exception e) {
           ServerFrame.printLog("Excpn: " + e);
           }
    }
}
