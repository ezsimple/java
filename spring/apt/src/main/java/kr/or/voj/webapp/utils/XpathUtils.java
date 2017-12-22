package kr.or.voj.webapp.utils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import kr.or.voj.webapp.processor.ProcessorServiceFactory;

import org.apache.commons.jxpath.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;

@Service
public class XpathUtils {
	private static final Logger logger = Logger.getLogger(XpathUtils.class);

	/**
	 * 소스 <uf:job id="xml" jobId="xml" singleRow="false"> src:
	 * 'd:/temp/test.xml',
	 * xpath:['/result/meta/author/text()','/result/meta/author/@val',
	 * '/result/meta/subj/text()'] </uf:job>
	 * 
	 * 결과 {"xml":[ {"result_meta_subj":"subj1","result_meta_author":"author1",
	 * "result_meta_author_val":"dsfdsf"},
	 * {"result_meta_subj":"subj2","result_meta_author"
	 * :"author2","result_meta_author_val":"dsfdsf"},
	 * {"result_meta_subj":"subj3"
	 * ,"result_meta_author":"author3","result_meta_author_val":"dsfdsf"} }]
	 * 
	 * 참고 XPath로 접근한 노드의 갯수가 불일치 하면 오류를 발생 시킨다. 단 노드의 수가 1개이면 다른 레코드에 동일 값으로
	 * 채운다. 노드갯수가 1개인 XPath를 많은 노드의 XPath보다 뒤에 놓아야 한다.
	 */
	public static void main() throws Exception {

		String src = "D:/oadr/2015/03/09_182000_abd55cbb-789d-44ca-af37-1a10e6260855.xml";

		String[][] paths = { 
				{"a", "ns7:oadrPayload/ns7:oadrSignedObject/ns7:oadrDistributeEvent/ns7:oadrEvent/ns2:eiEvent/ns2:eventDescriptor/ns2:eventID/text()"},
				{"b", "ns7:oadrPayload/ns7:oadrSignedObject/ns7:oadrDistributeEvent/ns7:oadrEvent/ns2:eiEvent/ns2:eiActivePeriod/ns5:properties/ns5:dtstart/ns5:date-time/text()"}
		};
		List<Map<String, Object>> list = read(new File(src), paths, null);

		System.out.println(list);

	}

	public static List<Map<String, Object>> read(File file, String[][] xPaths, Node node) throws Exception {
		
		logger.debug(file.exists());
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xmlDoc = builder.parse(file);

		return read(xmlDoc, xPaths, node);
	}
	public static NodeList readNodes(File file, String path, Node node) throws Exception {
		
		logger.debug(file.exists());
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xmlDoc = builder.parse(file);

		return readNodes(xmlDoc, path, node);
	}

	public static List<Map<String, Object>> read(Document xmlDoc, String[][] xPaths, Node pnode) throws Exception {

		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();

		xPath.setNamespaceContext(new UniversalNamespaceCache(xmlDoc, true));

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (String[] path : xPaths) {
			XPathExpression expr = xPath.compile(path[1]);

			NodeList nodes = (NodeList) expr.evaluate(pnode==null ? xmlDoc : pnode, XPathConstants.NODESET);
			//String newPath = StringUtils.replace(path[1], "/text()", "").replaceAll("@", "").replace('/', '_');
			//newPath = newPath.startsWith("_") ? newPath.substring(1) : newPath;
			String val = "";

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				if (list.size() <= i) {
					list.add(new HashMap<String, Object>());
				}
				Map<String, Object> data = list.get(i);
				val = node.getNodeValue();
				data.put(path[0], val);
			}
			// 없는 패스의 값을 마지막 노드의 값으로 채워 필드 갯수를 맞춘다.
			if (nodes.getLength() != list.size()) {
				if (nodes.getLength() == 1) {
					for (int i = 1; i < list.size(); i++) {
						Map<String, Object> data = list.get(i);
						data.put(path[0], val);
					}
				} else {
					throw new Exception((new StringBuffer("XML 데이타 갯수 불일치 : path=")).append(path).append(" 갯수 : ").append(nodes.getLength()).append(" != ").append(list.size()).toString());
				}
			}
		}

		return list;
	}
	public static NodeList readNodes(Document xmlDoc, String path, Node pnode) throws Exception {
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();

		xPath.setNamespaceContext(new UniversalNamespaceCache(xmlDoc, true));

		XPathExpression expr = xPath.compile(path);
		NodeList nodes = (NodeList) expr.evaluate(pnode==null ? xmlDoc : pnode, XPathConstants.NODESET);
		
		return nodes;
	}

}

