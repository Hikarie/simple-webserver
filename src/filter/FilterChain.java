package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import course.MyServlet;
import http.HttpRequest;
import http.HttpResponse;

public class FilterChain {
	private MyServlet servlet;
	private int index = 0;
	private List<Filter> filterList = new ArrayList<>();
	
	public void doFilter(HttpRequest req, HttpResponse resp) {
		if(index < filterList.size()) {
			try {
				filterList.get(index++).doFilter(req, resp, this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addFilter(Filter filter) {
		filterList.add(filter);
	}
	
	public void setServlet(MyServlet servlet) {
		this.servlet = servlet;
	}
	
	public MyServlet getServlet() {
		return servlet;
	}
}
