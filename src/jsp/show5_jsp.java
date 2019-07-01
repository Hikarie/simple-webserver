package jsp;
import http.*;
import java.io.*;
import course.MyServlet;

public class show5_jsp extends MyServlet {
@Override
public void doPost(HttpRequest request,HttpResponse response){
PrintWriter out = response.getWriter();
out.print("<!DOCTYPE html><html><head><title>Testing for Filter</title><body> <h1>Testing for Filter</h1> <p>The site have been visited ");
out.print(course.AccessFilter.nNum);
out.print(" times.<p></body> </html>");
}
@Override
public void doGet(HttpRequest request,HttpResponse response){
}
}