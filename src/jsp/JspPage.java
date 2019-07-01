package jsp;

import java.io.IOException;

import course.MyServlet;
import http.HttpRequest;
import http.HttpResponse;

public abstract class JspPage extends MyServlet{
	public abstract void jspService(HttpRequest req,HttpResponse resp) throws IOException, Exception;
}
