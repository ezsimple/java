package kr.or.voj.webapp.service.weather;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.voj.quartz.job.EventCtlData2IOEmJob;
import kr.or.voj.webapp.utils.HttpUtils;
import kr.or.voj.webapp.utils.XpathUtils;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class KmaWeatherGatheringImpl implements WeatherGathering {
	private static final Logger logger = Logger.getLogger(KmaWeatherGatheringImpl.class);
	
	public List<Map<String, Object>> getWeather(String gridX, String gridY) throws Exception {
		File xmlFile = new File("./kma.xml");		
		String url = "http://www.kma.go.kr/wid/queryDFS.jsp?gridx=" + gridX + "&gridy=" + gridY;
		
		Map<String, Object> param = new HashMap<String, Object>();		
		xmlFile = HttpUtils.getFile(url, param, xmlFile);
		
		String xpath = "wid";
		
		NodeList nodes = XpathUtils.readNodes(xmlFile, xpath, null);
		
		/*
			<hour>15</hour>
			<day>0</day>
			<temp>14.4</temp>
			<tmx>15.0</tmx>
			<tmn>-999.0</tmn>
			<sky>1</sky>
			<pty>0</pty>
			<wfKor>맑음</wfKor>
			<wfEn>Clear</wfEn>
			<pop>0</pop>
			<r12>0.0</r12>
			<s12>0.0</s12>
			<ws>2.4000000000000004</ws>
			<wd>6</wd>
			<wdKor>서</wdKor>
			<wdEn>W</wdEn>
			<reh>14</reh>
			<r06>0.0</r06>
			<s06>0.0</s06> 
		 */
		
		String[][] headerPaths = {
				{"tm", "wid/header/tm/text()"},
		};
		
		String[][] bodyPaths = {
			{"seq", "wid/body/data/@seq"},
			{"hour", "wid/body/data/hour/text()"},
			{"day", "wid/body/data/day/text()"},
			{"temp", "wid/body/data/temp/text()"},
			{"tmx", "wid/body/data/tmx/text()"},
			{"tmn", "wid/body/data/tmn/text()"},
			{"sky","wid/body/data/sky/text()"},				
			{"pty", "wid/body/data/pty/text()"},
			{"wfKor", "wid/body/data/wfKor/text()"},
			{"wfEn", "wid/body/data/wfEn/text()"},
			{"pop", "wid/body/data/pop/text()"},
			{"reh", "wid/body/data/reh/text()"},
			{"ws", "wid/body/data/ws/text()"},
			{"wd", "wid/body/data/wd/text()"},
			{"wdKor", "wid/body/data/wdKor/text()"},
			{"wdEn", "wid/body/data/wdEn/text()"}
		};		
		
		List<Map<String, Object>> headerList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> bodyList = new ArrayList<Map<String, Object>>();
		
		headerList = XpathUtils.read(xmlFile, headerPaths, null);
		bodyList = XpathUtils.read(xmlFile, bodyPaths, null);
				
		String wtime = headerList.get(0).get("tm").toString().substring(0, 8);
		
		for(Map<String, Object> data : bodyList){
			data.put("wtime", getWeatherDate(wtime, 
					toInteger(data.get("day").toString()), 
					toInteger(data.get("hour").toString())
					));
						
			print(data);
		}
			
		return bodyList;
	}

	private Date getWeatherDate(String dateString, int day, int hour) throws ParseException{
		Date date = new SimpleDateFormat("yyyyMMdd").parse(dateString);
		date = DateUtils.addDays(date, day);
		date = DateUtils.addHours(date, hour);
		
		return date;
	}
	
	private int toInteger(String str){
		return Integer.valueOf(str);
	}
	
	private void print(Object args){
		logger.info(args.toString());
	}
}
