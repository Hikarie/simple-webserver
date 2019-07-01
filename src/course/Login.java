package course;

import java.io.IOException;

import http.HttpRequest;
import http.HttpResponse;
import ui.ServerFrame;

/**
 * Servlet implementation class Login
 */
public class Login extends MyServlet{
	private static final long serialVersionUID = 1L;
       
	
	public void service(HttpRequest request, HttpResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			doPost(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpRequest request, HttpResponse response) throws Exception, IOException {
		// TODO Auto-generated method stub
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		
		ServerFrame.printLog("userName:"+userName+"\t password:"+password);
		if("admin".equals(userName) && "admin".equals(password))
				  request.getSession().setAttribute("username","admin");
		else
			 request.getSession().setAttribute("username","Unknown User");
		
		ServerFrame.printLog("senRedirect¡ª¡ªtext6.jsp");
		response.sendRedirect("test6.jsp");
			
	//	doGet(request, response);
	}
	@Override
	protected void doGet(HttpRequest req, HttpResponse res) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
