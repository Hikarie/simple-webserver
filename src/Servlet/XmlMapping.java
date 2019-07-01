package Servlet;

import java.util.ArrayList;
import java.util.List;

public class XmlMapping {
    private String name;
    
    private List<String> url_pattern;
    
    void setName(String name) {
    	this.name = name;
    	url_pattern = new ArrayList<String>();
    }
    
    String getName() {
    	return name;
    }
    
    List<String> getUrlPattern() {
    	return url_pattern;
    }
}
