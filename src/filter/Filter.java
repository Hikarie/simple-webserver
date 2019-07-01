package filter;

import java.io.IOException;

import http.HttpRequest;
import http.HttpResponse;

public interface Filter {
	public void init(FilterConfig fConfig);
	public void destroy();
	public void doFilter(HttpRequest req, HttpResponse resp, FilterChain chain)throws IOException;
}
