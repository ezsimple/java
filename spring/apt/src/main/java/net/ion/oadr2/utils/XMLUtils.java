package net.ion.oadr2.utils;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.jxpath.xml.DOMParser;
import org.w3c.dom.Document;

public  final class XMLUtils {

	private  XMLUtils() {

	}
	
	public static String format(String xml) throws UnsupportedEncodingException{
		
		DOMParser parser = new DOMParser();
		parser.setNamespaceAware(false);
		Document document =  (Document) parser.parseXML(new ByteArrayInputStream(xml.getBytes("utf-8")));
		
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
	
	
	public static Document toDocument(String xml) throws UnsupportedEncodingException{
		
		DOMParser parser = new DOMParser();
		parser.setNamespaceAware(false);
		Document document =  (Document) parser.parseXML(new ByteArrayInputStream(xml.getBytes("utf-8")));
		
		return document;
	}

	
	public static String nodeToString(final org.w3c.dom.Node node) {
		final boolean indent = true;
		
		String xml = null;

		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			if (indent) {
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			}

			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(node);
			transformer.transform(source, result);
			Writer writer = result.getWriter();
			xml = writer.toString().trim();
		} catch (TransformerException e) {
			throw new IllegalStateException("Failed to transform node.");
		}

		return xml;
	}
}
