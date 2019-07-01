package Servlet;

import http.HttpRequest;
import http.HttpResponse;

public class RequestDispatcher {
	private String name;
	
	public RequestDispatcher(String name) {
		this.name = name;
	}
	
	public void forward(HttpRequest req, HttpResponse resp) {
		try {
			resp.send(resp.getDic(), name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
