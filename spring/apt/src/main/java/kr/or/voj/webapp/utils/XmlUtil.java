package kr.or.voj.webapp.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONArray;

import org.apache.commons.io.IOUtils;
import org.apache.commons.jxpath.xml.DOMParser;
import org.apache.commons.lang.StringUtils;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import org.w3c.dom.Document;

public class XmlUtil {

	private Map<String, Boolean> pathMap = new HashMap<String, Boolean>();
	private String iconPath = "../../../images/icon/";

	public static String format(String xml) throws UnsupportedEncodingException {

		DOMParser parser = new DOMParser();
		parser.setNamespaceAware(false);
		Document document = (Document) parser.parseXML(new ByteArrayInputStream(xml.getBytes("utf-8")));

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer;
		Writer writer = new StringWriter();
		try {
			transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(new DOMSource(document), new StreamResult(writer));
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return writer.toString();
	}

	public String getXml2tree(String xml, boolean hideCheckbox) throws Exception{
		//String path = "D:/temp/test.xml";

		Map<String, Object> json = new HashMap<String, Object>();

		InputStream is = IOUtils.toInputStream(xml);
        DOMBuilder domBuilder = new DOMBuilder();

			
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(is);
	
      
        Element el = domBuilder.build(doc).getRootElement();
        String name = el.getName();
        json.put("id",name);
        json.put("title",name);
        json.put("type", "path");
        json.put("path", name);
        json.put("hideCheckbox", true);
        json.put("expand", true);
     
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		addChildren(name, el.getChildren(), json, list, hideCheckbox);
		
		JSONArray ja = new JSONArray();
		ja.add(json);
		
		return ja.toString();
	}
	private void addChildren(String path, List<Element> list, Map<String, Object> parentObj, List<Map<String,Object>> children, boolean hideCheckbox){
		
		if(list==null || list.size()<1){
			parentObj.put("icon", iconPath+"node.png");
			return;
		}
		
		parentObj.put("isFolder", true);
		
		for(Element el : list){
			String name = el.getName();
			String newPath = path + "/" + name;
			
			Map<String, Object> elObj = new HashMap<String, Object>();
			//System.out.println(el.getName() + " = " + el.getTextTrim());
			elObj.put("id",name);
			elObj.put("path", newPath);

	        String val = el.getTextTrim();
	        
	        if(StringUtils.isEmpty(val)){
	        	elObj.put("title",name);
	        	elObj.put("type", "path");
	        	elObj.put("hideCheckbox", hideCheckbox);
	        }else{
	        	elObj.put("title",name + " - " + val);
	        	elObj.put("type", "node");
	        }
	        
	        elObj.put("value", val);
	        
			if(pathMap.containsKey(newPath)){
				elObj.put("hideCheckbox", hideCheckbox);				
			}else{
				parentObj.put("expand", true);
			}
			
			pathMap.put(newPath, true);
			
			List<Map<String,Object>> elChildren = new ArrayList<Map<String,Object>>();
			List<Attribute> ats =  el.getAttributes();
			if(ats!=null){
				
				for(Attribute at : ats){
					Map<String, Object> attObj = new HashMap<String, Object>();
					String atName = at.getName();
					String attPath = newPath + "@" + atName;
					attObj.put("id", atName);
					attObj.put("title", atName + "-" + at.getValue());
					attObj.put("value", at.getValue());
					attObj.put("type", "attr");
					attObj.put("icon", iconPath+"attr.png");
					attObj.put("path", attPath);
					
					if(pathMap.containsKey(attPath)){
						attObj.put("hideCheckbox", hideCheckbox);				
					}else{
						elObj.put("expand", true);
					}
					
					pathMap.put(attPath, true);

					elChildren.add(attObj);
				}
				elObj.put("children", elChildren);
			}

			children.add(elObj);
			
			addChildren(newPath, el.getChildren(), elObj, elChildren, hideCheckbox);
		}
		
		parentObj.put("children", children);
	}
	
}
