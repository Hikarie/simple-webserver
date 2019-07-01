package http;

import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.TextArea;
import java.io.*;

public class HttpConnect {
	Socket sock;
	InputStream in = null;
	PrintWriter out = null;
	
	HttpConnect(Socket sock) throws Exception{ 
		this.sock = sock;
		in = sock.getInputStream();
		out = new PrintWriter(sock.getOutputStream());
	}
	
//	String getRequest(TextArea ta) throws Exception {
//		String s=null;
//		while ((s=in.)!=null) {
//			printLog("got: "+s,ta);
//			if(s.equals(""))break;
//		}
//		return s;
//	}
}
