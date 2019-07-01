package Servlet;

import java.util.HashMap;
import java.util.Map;

public class ServletContext {
	private Map<String,String> servlet_class_map;
	private Map<String,String> servlet_url_map;
	
	private Map<String, String> filter_class_map;
	private Map<String, String> filter_url_map;
	
	public ServletContext(){
		this.servlet_class_map = new HashMap<String, String>();
		this.servlet_url_map = new HashMap<String, String>();
		this.filter_class_map = new HashMap<String, String>();
		this.filter_url_map = new HashMap<String, String>();
	}


	public Map<String, String> getServletClass() {
		return servlet_class_map;
	}


	public void setServletClass(Map<String, String> class_map) {
		this.servlet_class_map = class_map;
	}


	public Map<String, String> getUrlMapping() {
		return servlet_url_map;
	}


	public void setUrlMapping(Map<String, String> url_map) {
		this.servlet_url_map = url_map;
	}
	
	public Map<String, String> getFilterClass(){
		return filter_class_map;
	}
	
	public void setFilterClass(Map<String, String> class_map) {
		this.filter_class_map = class_map;
	}
	
	public Map<String,String> getFilterUrl(){
		return filter_url_map;
	}
	
	public void setFilterUrl(Map<String, String> url_map) {
		this.servlet_url_map = url_map;
	}
}
