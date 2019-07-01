package Servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXML extends DefaultHandler{
	
    private XmlEntity entity;	// servlet-name servlet-class
    
    private XmlMapping map; 	// servlet-name url-parttern
    
    private List<XmlEntity> entity_list ;
    
    private List<XmlMapping> map_list ;
    
    private String tag;
    
    private boolean isServletMap;
    
    private boolean isFilterMap;
    
    private List<XmlEntity> filter_list ;
    
    private List<XmlMapping> filtermap_list ;
    
    List<XmlEntity> getEntityList(){
    	return entity_list;
    }
    
    List<XmlMapping> getMapList(){
    	return map_list;
    }
    
    List<XmlEntity> getFilterList(){
    	return filter_list;
    }
    
    List<XmlMapping> getFilterMapList(){
    	return filtermap_list;
    }
    
    @Override
    public void startDocument() throws SAXException {
        entity_list = new ArrayList<XmlEntity>();
        map_list = new ArrayList<XmlMapping>();
        filter_list = new ArrayList<XmlEntity>();
        filtermap_list = new ArrayList<XmlMapping>();
    }
    
    
    /*
     * @param qnanme:标签名
     * @param attributes:属性集合
     */
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if(qName!=null){
            tag = qName;
            if(qName.equals("servlet")){
            	entity = new XmlEntity();
                isServletMap = false;
                isFilterMap = false;
            }else if(qName.equals("servlet-mapping")){
            	map = new XmlMapping();
                isServletMap = true;
                isFilterMap = false;
            }
            else if(qName.equals("filter")) {
            	entity = new XmlEntity();
            	isFilterMap = false;
            	isServletMap = false;
            }
            else if(qName.equals("filter-mapping")) {
            	map = new XmlMapping();
                isFilterMap = true;
                isServletMap = false;
            }
        }
        
    }
    
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if(tag != null){
            String info = new String(ch, start, length);
            if(isServletMap || isFilterMap){
                if(tag.equals("servlet-name")||tag.equals("filter-name")){
                	map.setName(info);
                }
                else if(tag.equals("url-pattern")){
                	map.getUrlPattern().add(info);
                }
            }
            else if(!isServletMap||!isFilterMap){
                if(tag.equals("servlet-name")||tag.equals("filter-name")){
                	entity.setName(info);
                }else if(tag.equals("servlet-class")||tag.equals("filter-class")){
                	entity.setClass(info);
                }
            }
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if(null!=qName){
            if(qName.equals("servlet")){
                entity_list.add(entity);
            }
            else if(qName.equals("servlet-mapping")){
                map_list.add(map);
            }
            else if(qName.equals("filter")) {
            	filter_list.add(entity);
            }
            else if(qName.equals("filter-mapping")) {
            	filtermap_list.add(map);
            }
        }
        tag = null;
    }

    @Override
    public void endDocument() throws SAXException {
        // end
    }
}
