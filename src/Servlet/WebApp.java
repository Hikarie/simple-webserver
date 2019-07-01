package Servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.omg.CORBA.Environment;
import org.xml.sax.SAXException;

import course.MyServlet;

public class WebApp {
	
	private static ServletContext context;
	
	static {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = null;
			try {
				saxParser = saxParserFactory.newSAXParser();
			} catch (ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ReadXML reader = new ReadXML();
			try {
				saxParser.parse(new File("WebContent/WEB-INF/web.xml"), reader);
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			context = new ServletContext();
			
			List<XmlEntity> entity_list = reader.getEntityList();
			List<XmlMapping> map_list = reader.getMapList();
			
			Map<String,String> servlet_class_map = context.getServletClass();
			Map<String,String> servlet_url_map = context.getUrlMapping();
			
			for(XmlEntity entity:entity_list) {
				servlet_class_map.put(entity.getName(), entity.getCla());
			}
			
			for(XmlMapping map:map_list) {
				List<String> urls = map.getUrlPattern();
				for(String url:urls) {
				servlet_url_map.put(url, map.getName());
				}			
			}
			
			List<XmlEntity> filter_list = reader.getFilterList();
			List<XmlMapping> filtermap_list = reader.getFilterMapList();
			
			Map<String,String> filter_class_map = context.getFilterClass();
			Map<String,String> filter_url_map = context.getFilterUrl();
			
			for(XmlEntity filter:filter_list) {
				filter_class_map.put(filter.getName(),filter.getCla());
			}
			
			for(XmlMapping filtermap:filtermap_list) {
				List<String> urls = filtermap.getUrlPattern();
				for(String url:urls) {
					filter_url_map.put(url, filtermap.getName());
				}
			}
	}
	
	public static ServletContext getContext() {
		return context;
	}
	
	
	public static MyServlet generic(String url) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> servlet = Class.forName(context.getServletClass().get(context.getUrlMapping().get(url)));
		MyServlet s = (MyServlet) servlet.newInstance();
		return s;
	}
	
//	public static GenericServlet

}
