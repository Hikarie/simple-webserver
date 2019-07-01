package jsp;
import http.*;
import java.io.*;
import course.MyServlet;

public class show3_jsp extends MyServlet {
@Override
public void doPost(HttpRequest request,HttpResponse response){
PrintWriter out = response.getWriter();
out.print("\r\n");out.print("<!DOCTYPE html><html><head><title>Testing for Servlet-MVC</title><body> <h1>Recommended Pet - Testing for Web-MVC</h1> <p>You want a ");
out.print(request.getParameter("legs"));
out.print("-legged pet weighing ");
out.print(request.getParameter("weight"));
out.print("lbs.</p><p> We recommend getting <b>");
out.print(request.getAttribute("pet"));
out.print("</b></p></body> </html>");
}
@Override
public void doGet(HttpRequest request,HttpResponse response){
}
}