package http;

import java.awt.TextArea;
import java.net.Socket;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

import Servlet.RequestDispatcher;
import session.Session;
import ui.ServerFrame;

public class HttpRequest extends HttpConnect{
	
	private String protocol;
	private String method;
	private String url;
	
	private Session session = new Session();
	
	private HashMap<String,String> headers = new HashMap<>();
	private HashMap<String,String> parameters = new HashMap<>();
	private HashMap<String, Object> attribute = new HashMap<>();
	HttpRequest(Socket sock) throws Exception{
		super(sock);
	}
	
	   String getRequest() throws Exception {
		   	  // parse request line
		   byte []data = new byte[10240];
		   int len = in.read(data);
		   String info = new String(data,0,len);
		   String requestline = info.substring(0,info.indexOf("\r\n")).trim();
		   
	       String []arr = requestline.split(" ");
		   method = arr[0];
		   url = arr[1];
		   protocol = arr[2];
		   ServerFrame.printLog("request line: "+requestline);
		   info = info.substring(info.indexOf("\r\n"),len);
		   
		   int i = info.indexOf("\r\n")+2;
		   while(true) {
			   String line = info.substring(i,info.indexOf("\r\n",i));
			   if(line.equals(""))break;
			   String []head = line.split(" ",2);
			   headers.put(head[0],head[1]);
			   i = info.indexOf("\r\n",i)+2;
		   }
		   ServerFrame.printLog("headers: "+ info);
           if (method.equalsIgnoreCase("GET")) {
//        	   out.write("HTTP-1.1 200 OK\r\n");
     	      ServerFrame.printLog("request for file:"+ url);
           }
           else if(method.equalsIgnoreCase("POST")){
//        	   out.write("HTTP-1.1 200 OK\r\n");
         	  ServerFrame.printLog("request for servlet:"+ url);
         	  parseInfo(info.substring(info.lastIndexOf("\r\n")));
           }
           return url;
	   }
	   public HttpRequest getReq() {
		   return this;
	   }
	   public String getProtocol() {
		   return protocol;
	   }
	   public String getMethod() {
		   return method;
	   }
	   public String getUrl() {
		   return url;
	   }
	   
	   public void setUrl(String url) {
		   this.url = url;
	   }
	   
	   public String getParameter(String name) {
		   return parameters.get(name);
	   }
	   private void parseURL() {
		   String path;
		   if(url.indexOf("?")!=-1) {
			   String[] data = url.split("\\?");
			   path = data[0];
			   if(data.length>1) {
				   String temp = data[1];
				   data = temp.split("&");
				   for(String paraLine : data) {
					   String[] paras = paraLine.split("=");
					   if(paras.length>1) {
						   parameters.put(paras[0],paras[1]);
					   }
					   else {
						   parameters.put(paras[0],null);
					   }
				   }
			   }
		   }
		   else {
			   path = url;
		   }
	   }
	   
	   private void parseInfo(String paramline) {
		   String[] params = paramline.split("&");
		   for(String param : params) {
			   String []values = param.split("=");
			   if(values.length==1)parameters.put(values[0].trim(), null);
			   else parameters.put(values[0].trim(), values[1].trim());
			   ServerFrame.printLog("parameters: "+values[0].trim()+" "+values[1].trim());
		   }
	   }

	public Session getSession() {
		return session;
	}

	public void setAttribute(String str, Object obj) {
		attribute.put(str,obj);
	}
	
	public Object getAttribute(String str) {
		return attribute.get(str);
	}

	public RequestDispatcher getRequestDispatcher(String fJsp) {
		RequestDispatcher rd = new RequestDispatcher(fJsp);
		url = "/"+fJsp;
		return rd;
	}

	public String getHeader(String h) {
		return headers.get(h);
	}

	public Enumeration getHeaderNames() {
		Enumeration<String> names = Collections.enumeration(headers.keySet());
		return names;
	}
}
