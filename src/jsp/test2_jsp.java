package jsp;
import http.*;
import java.io.*;
import course.MyServlet;

public class test2_jsp extends MyServlet {
@Override
public void doPost(HttpRequest request,HttpResponse response){
PrintWriter out = response.getWriter();
out.print("<b>Testing for first JSP</b><br><b> current time is:     ");
out.print(  new java.util.Date() );
out.print(" </b> ");
}
@Override
public void doGet(HttpRequest request,HttpResponse response){
}
}