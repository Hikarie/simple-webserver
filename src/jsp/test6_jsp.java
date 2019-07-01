package jsp;
import http.*;
import java.io.*;
import course.MyServlet;

public class test6_jsp extends MyServlet {
@Override
public void doPost(HttpRequest request,HttpResponse response){
PrintWriter out = response.getWriter();
out.print("<html> <body><b>Login to System</B>Current user is:");
out.print(request.getSession().getAttribute("username"));
out.print(" </br> </hr><form  action=\"Login\" method=\"post\">    <h4> User Name: </h4>        <input type=\"text\"  name=\"username\"  size=\"10\">   <h4> Password: </h4>   <input type=\"text\"  name=\"password\"  size=\"10\">        <p>    <input type=\"submit\" value=\"Login\" >    </p></body></form>");
}
@Override
public void doGet(HttpRequest request,HttpResponse response){
}
}