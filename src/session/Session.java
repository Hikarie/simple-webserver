package session;

import java.util.HashMap;
import java.util.Map;

public class Session {
	
	private Map<String, Object> attribute = new HashMap<>();
	
	public Session() {
		attribute = new HashMap<>();
		attribute.put("username", "admin");
		attribute.put("password", "admin");
	}
	
	public void setAttribute(String name, Object o) {
		attribute.put(name, o);
	}
	
	public Object getAttribute(String name) {
		return attribute.get(name);
	}

}
