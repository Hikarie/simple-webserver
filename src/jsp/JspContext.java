package jsp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import course.MyServlet;
import http.HttpRequest;
import http.HttpResponse;

public class JspContext{
	
	HttpRequest req;
	HttpResponse resp;
	String fname;
//	StringBuilder jsp;
	
	public JspContext(HttpRequest req,HttpResponse resp) {
		this.req = req;
		this.resp = resp;
		try {
			toServlet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void toServlet() throws IOException {
		fname = req.getUrl();
		BufferedReader buffer = new BufferedReader(new FileReader(new File(resp.getDic()+fname)));
		fname = fname.substring(1,fname.indexOf("."))+"_jsp"; 
		File fServlet = new File("src/jsp/"+fname+".java");
		if(!fServlet.exists()) {
			StringBuilder jsp = new StringBuilder();
			
			// package import class
			jsp.append("package jsp;\n"
					+ "import http.*;\n"
					+ "import java.io.*;\n"
					+ "import course.MyServlet;\n\n"
					+ "public class "+fname+" extends MyServlet {\n"
							+ "@Override\n"
							+ "public void doPost(HttpRequest request,HttpResponse response){\n"
							+ "PrintWriter out = response.getWriter();\n");
			char []data = new char[10240];
			int len = buffer.read(data);
			String info = new String(data,0,len);
			
			if(info.startsWith("<%@")) {
				// jsp指令
				/*
				 * page 定义JSP页面的各种属性
				 */
				String page = info.substring(info.indexOf("%>")+2);
			}
			
			boolean inhtml=false;
			for(int i=0;i<info.length();i++) {
				if(i+4<info.length()&&info.substring(i,i+4).equals("<%--")) {
					if(inhtml == true) {
						jsp.append("\");\n");
						inhtml = false;
					}
					// JSP页面中的注释
					i = info.indexOf("--%>",i)+1;
				}
				else if(i+3<info.length()&&info.substring(i,i+3).equals("<%@")) {
					// JSP页面中的编译指令
					if(inhtml == true) {
						jsp.append("\");\n");
						inhtml = false;
					}
					i = info.indexOf("%>",i)+1;
				}
				else if(i+3<info.length()&&info.substring(i,i+3).equals("<%=")) {
					// 表达式指令
					if(inhtml == true) {
						jsp.append("\");\n");
						inhtml = false;
					}
					jsp.append("out.print("+info.substring(i+3,info.indexOf("%>",i))+");\n");
					i = info.indexOf("%>",i)+1;
				}
				else if(i+2<info.length()&&info.substring(i,i+2).equals("<%")) {
					// 嵌入脚本
					if(inhtml == true) {
						jsp.append("\");\n");
						inhtml = false;
					}
					jsp.append(info.substring(i+2,info.indexOf("%>",i))+"\n");
					i = info.indexOf("%>",i)+1;
				}
				else {
					// HTML文本
//					if(i+3<=info.length()&&info.substring(i,i+3).equals(">\r\n")) {
//						jsp.append(">");
//						i = i+2;
//					}
					if(i+2<=info.length()&&info.substring(i,i+2).equals("\r\n")) {
						i = i+1;
					}
					else {
						if(inhtml==false) {
							jsp.append("out.print(\""+info.substring(i,i+1));
							inhtml = true;
						}
						else {
							if(info.substring(i,i+1).equals("\""))jsp.append("\\");
							jsp.append(info.substring(i,i+1));
						}
					}
				}
			}
			
			jsp.append("\");\n}\n"
					+ "@Override\n"
					+ "public void doGet(HttpRequest request,HttpResponse response){\n"
					+ "}\n}");
			
			fServlet.createNewFile();
			FileWriter fw = new FileWriter(fServlet.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(jsp.toString());
			bw.close();
			

		}
	}
	
	public MyServlet instance() throws IOException {
//		String path = (new File(fname+".java")).getAbsolutePath();
//		Process p = Runtime.getRuntime().exec("javac "+path);
//		JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
//		javac.run(null, null, null, fname+".java");
		// 动态编译
	    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	    StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
	    File file = new File("src/jsp/+fname"+".java");
	    Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(file);
	    JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);

	    task.call();
		try {
			Class c = Class.forName("jsp."+fname);
			MyServlet jp = (MyServlet)c.newInstance();
			return jp;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
