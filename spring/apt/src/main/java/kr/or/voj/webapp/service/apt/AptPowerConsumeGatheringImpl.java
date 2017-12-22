package kr.or.voj.webapp.service.apt;

import kr.or.voj.webapp.utils.XpathUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
 */
/**
 * Created by Administrator on 2016-02-22.
 * http://www.tutorialspoint.com/java_xml/java_xpath_parse_document.htm
 */
// 옴니에서 제공하는 수색동 아파트(490세대) 전력소모량 수집 서비스
@Service
@DisallowConcurrentExecution
public class AptPowerConsumeGatheringImpl implements PowerConsumeGathering{
    private static final Logger logger = Logger.getLogger(AptPowerConsumeGatheringImpl.class);

    public void save2(String xml) throws Exception {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xml);
            doc.getDocumentElement().normalize();
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/class/student";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                System.out.println("\nCurrent Element :"
                        + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    logger.info("Student roll no : "
                            + eElement.getAttribute("rollno"));
                    /*
                    logger.info("First Name : "
                            + eElement
                            .getElementsByTagName("firstname")
                            .item(0)
                            .getTextContent());
                    logger.info("Last Name : "
                            + eElement
                            .getElementsByTagName("lastname")
                            .item(0)
                            .getTextContent());
                    logger.info("Nick Name : "
                            + eElement
                            .getElementsByTagName("nickname")
                            .item(0)
                            .getTextContent());
                    logger.info("Marks : "
                            + eElement
                            .getElementsByTagName("marks")
                            .item(0)
                            .getTextContent());
                    */
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public void save(String xml) throws Exception {
        File temp = File.createTempFile("AMISys", ".tmp");
        FileUtils.writeStringToFile(temp,xml);
        String xpath = "/class/student";
        NodeList nodes = XpathUtils.readNodes(temp, xpath, null);
        List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
        String[][] infoPaths = {
                {"seq", "class/student/@seq"},
                {"firstname", "class/student/firstname/text()"},
                {"lastname", "class/student/lastname/text()"},
                {"nickname", "class/student/nickname/text()"},
                {"marks", "class/student/marks/text()"}
        };
        List<Map<String, Object>> csList = XpathUtils.read(temp, infoPaths, null);
        for(Map<String, Object> data : csList){
            print(data);
        }
    }
    private void print(Object args){
        logger.info(args.toString());
    }
}
