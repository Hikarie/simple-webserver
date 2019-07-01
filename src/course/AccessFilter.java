package course;

import java.io.IOException;

import filter.Filter;
import filter.FilterChain;
import filter.FilterConfig;
import http.HttpRequest;
import http.HttpResponse;
import ui.ServerFrame;
/**
 * Servlet Filter implementation class AccessFilter
 */
public class AccessFilter implements Filter {
	public static int nNum=0; 

    /**
     * Default constructor. 
     */
    public AccessFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) throws IOException {
		// TODO Auto-generated method stub
		// place your code here

		AccessFilter.nNum++;
		ServerFrame.printLog("AccessFilter is working... nNum = "+String.valueOf(AccessFilter.nNum));
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) {
		// TODO Auto-generated method stub
	}
		

}
