package demo;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.TimeZone;


public class NtpServerDemo {
	public static void main(String[] args){
		try {
			// establish server socket 
			ServerSocket s = new ServerSocket(8192);
			System.out.println("Ntp Server started!");
			// wait for client connection 
			Socket incoming = s.accept(); 
			try {
				OutputStream outStream = incoming.getOutputStream();
				PrintWriter out = new PrintWriter(outStream, true/* autoFlush */);
				java.util.Calendar now=java.util.Calendar.getInstance();
				DateFormat gmtFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss"); 
				TimeZone gmtTime = TimeZone.getTimeZone("UTC"); 
				gmtFormat.setTimeZone(gmtTime);
				out.println("UTCTime: " +gmtFormat.format(now.getTime()));
				}
				finally{
					incoming.close();
					}
				}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
