package http;

import java.awt.TextArea;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HttpServer implements Runnable{
	
	private int port;
	private String dictory;
	private ServerSocket ssock;
	TextArea ta;
	
	public HttpServer(String url, int p, TextArea t)  {
		port = p;
		dictory = url;
		ta = t;
	}
	
	@SuppressWarnings("deprecation")
	public static void printLog(String text, TextArea ta) {
		DateFormat curFormat = new SimpleDateFormat("HH:mm:ss");
		ta.append(curFormat.format(new Date())+" "+text+"\n");
	}
	
	public void run() {
		try {
			ssock = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printLog("The server has been launched",ta);
		printLog("have opened port 80 locally",ta);
		while(true) {
			Socket sock = null;
			try {
				sock = ssock.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			printLog("client has made socket connection", ta);
			HttpRunnable c = null;
			try {
				c = new HttpRunnable(sock, dictory);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new Thread(c).start();
		}
	}
	
	public static void main(String a[]) { 
//		HttpServer server = new HttpServer("src",80);
	}
}
