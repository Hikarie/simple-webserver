package jsp;
import http.*;
import java.io.*;
import course.MyServlet;

public class test4_jsp extends MyServlet {
@Override
public void doPost(HttpRequest request,HttpResponse response){
PrintWriter out = response.getWriter();
out.print(" <meta http-equiv=Content-Type content=\"text/html;charset=utf-8\"><html> 此工作属于提高要�?<body bgcolor=\"white\"> <h1>The Echo JSP - Testing for Jsp tasks</h1> ");
   java.util.Enumeration eh = request.getHeaderNames(); 
     while (eh.hasMoreElements()) { 
         String h = (String) eh.nextElement(); 
         out.print("<br> header: " + h ); 
         out.println(" value: " + request.getHeader(h)); 
     } 

out.print(" </body> </html> ");
}
@Override
public void doGet(HttpRequest request,HttpResponse response){
}
}