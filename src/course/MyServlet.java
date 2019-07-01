package course;

import java.awt.TextArea;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import http.*;

public abstract class MyServlet {
	public void service(HttpRequest req, HttpResponse res) throws Exception{
		this.doGet(req,res);
		this.doPost(req,res);
	}
	@SuppressWarnings("deprecation")
	public static void printLog(String text, TextArea ta) {
		DateFormat curFormat = new SimpleDateFormat("HH:mm:ss");
		ta.append(curFormat.format(new Date())+" "+text+"\n");
	}
	protected abstract void doGet(HttpRequest req, HttpResponse res) throws Exception;
	protected abstract void doPost(HttpRequest req, HttpResponse res) throws Exception;
}
