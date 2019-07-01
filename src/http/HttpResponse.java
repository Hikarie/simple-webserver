package http;

import java.io.*;
import java.net.Socket;
import java.util.Map;

import Servlet.ServletContext;
import Servlet.WebApp;
import course.MyServlet;
import filter.Filter;
import filter.FilterChain;
import jsp.JspContext;
import jsp.JspPage;
import ui.ServerFrame;


public class HttpResponse extends HttpRequest{
//	private HashMap<String, String> headers = new HashMap<String, String>();
	private String ContentType="text/html";
	private String dic;
	HttpResponse(Socket sock) throws Exception{
		super(sock);
	}
	
    public void send(String dic, String fname) throws Exception {
    	this.dic = dic;
    	if(super.getMethod().equals("GET")) {
    		out.print("HTTP-1.1 200 OK\r\n");
    		if(fname.endsWith(".jsp")) {
        		out.print("Content-length:" + "1024" + "\r\n");
                out.print("Content-Type:"+ContentType+"\r\n\r\n");
    			JspContext jc = new JspContext(super.getReq(), this);
    			MyServlet jsp = jc.instance();
    			
    			filt(jsp);
    			jsp.service(super.getReq(), this);
    		}
    		else{
    			String where = dic+fname; // create dir if necessary
                ServerFrame.printLog("looking for " + where + "...");
                File f = new File(where);
                String context;
                if(f.exists()) {
    	            int len = (int) f.length();
    	            byte[] buf = null;
    	            if(f.isDirectory()) {
    	            	StringBuilder s = new StringBuilder();
    	            	s.append("<html><head><title>目录信息</title></head><body><h1>目录信息</h1>");
    	            	String[] filelist = f.list();
    	            	for(String file:filelist) {
    	            		s.append("<p><a href="+file+">"+file+"</a>");
    	            	}
    	            	s.append("</body></html>");
    	            	context = s.toString();
//    	            	buf = s.toString().getBytes();
    	
    	            }
    	            else {
    	            	buf = new byte[len];
    	            	FileInputStream fin = new FileInputStream(f);
    	                fin.read(buf);
    	                fin.close();
    	                context = new String(buf);
    	                
    	            }
    	            out.print("Content-length:" + 1024 + "\r\n");
    	            out.print("Content-Type:"+ContentType+"\r\n\r\n");
    	            out.print(context);
                	}
                else {
                	out.print("HTTP-1.1 404 NOT FOUND\r\n");
    	            out.print("Content-length:" + "1024" + "\r\n");
    	            out.print("Content-Type:"+ContentType+"\r\n\r\n");
    	            out.print("<h1>404 NOT FOUND</h1>\r\n");
                }
    		}
    	}
    	else if(super.getMethod().equals("POST")) {
    		if(fname.endsWith(".jsp")) {
    			JspContext jc = new JspContext(super.getReq(), this);
    			MyServlet jsp = jc.instance();
    			jsp.service(super.getReq(), this);
    			filt(jsp);
    		}
    		else {
    			out.print("HTTP-1.1 200 OK\r\n");
    			out.print("Content-length:" + "1024" + "\r\n");
                out.print("Content-Type:"+ContentType+"\r\n\r\n");
        		MyServlet servlet = WebApp.generic(fname);
          	  	servlet.service(super.getReq(), this);
          	  	
          	  	
          	  	// Filter
          	  	filt(servlet);
    		}
    	}
    	out.flush();
    }

//	public void sendRedirect(String s) {
//		File f = new File(s);
//		headers.put("Content-Length", String.valueOf(f.length()));
//		String ext = s.substring(s.lastIndexOf(".")+1);
//		String line = getMimeType(ext);
//		headers.put("Context-Type",line);	
//	}
    
    public String getDic() {
    	return dic;
    }

	public void setContentType(String Type) {
		this.ContentType=Type;
	}

	public PrintWriter getWriter() {
		return this.out;
	}
	
	private void filt(MyServlet servlet) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		FilterChain chain = new FilterChain();
  	  	chain.setServlet(servlet);
  	  	
  	  	ServletContext context = WebApp.getContext();
  	  	Map<String,String> urlmap = context.getFilterUrl();
  	  	for(String url:urlmap.keySet()) {
  	  		Class<?> c = Class.forName(context.getFilterClass().get(context.getFilterUrl().get(url)));
  	  		Filter filter = (Filter) c.newInstance();
  	  		chain.addFilter(filter);
  	  	}
  	  	chain.doFilter(super.getReq(), this);
	}

	public void sendRedirect(String fname) throws Exception {
		// TODO Auto-generated method stub
		super.setUrl("/"+fname);
		send(this.dic, "/"+fname);
	}

}
