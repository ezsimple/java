package kr.or.voj.quartz.job;

import kr.or.voj.webapp.processor.ProcessorServiceFactory;
import kr.or.voj.webapp.utils.RepositoryUtils;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EventCtlData2IOEmJob extends ScheduleImpJob {
	private static final Logger logger = Logger.getLogger(EventCtlData2IOEmJob.class);

	public void executeJob(JobExecutionContext arg0) throws Exception {
		String word = "무궁화 꽃이 피었습니다.";
		logger.info("START : Event Ctrl Data Send to IoE.m Server "+word);
		run();
		logger.info("END : Event Ctrl Data Send to IoE.m Server");
	}
	protected void run() throws Exception {

		File file = null;
		
		try {			
			//전송 대상 데이타 조회
			Map<String,Object> rowSet = (Map<String,Object>)ProcessorServiceFactory.executeQuery("aptEvent", "ctlEvent", new CaseInsensitiveMap());

			Map<String, Object> evtRow = (Map<String, Object>)rowSet.get("row");
			//이벤트가 없는 경우 종료
			if(evtRow.size()<1){
				return;
			}
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.putAll(evtRow);
			
			for(Map<String, Object> row : (List<Map<String, Object>>)rowSet.get("list")){
				for(String key : row.keySet()){
					List<String> list = (List<String>)parameterMap.get(key);
					
					if(list==null){
						list = new ArrayList<String>();
					}
					list.add(row.get(key).toString());
					parameterMap.put(key, list);
				}
				
			}
			//전송
			file = File.createTempFile("event_",".xml");
			// HttpUtils.getFile(drProperties.CTL_DEV_URL, parameterMap, file); // ==> IoE.m에서 IoE Server를 폴링하도록 제어방향 변경 2016.01.13
			//저장소에 저장
			fileId = RepositoryUtils.save("CTL_DATA", file, "txt");
			String resTxt = FileUtils.readFileToString(file, "UTF-8");
			logger.info(resTxt);

			CaseInsensitiveMap map = new CaseInsensitiveMap();
			map.putAll(evtRow);
			Object obj = (Map<String,List<Map<String, Object>>>)ProcessorServiceFactory.executeQuery("drApi", "evenControl", map);
			logger.info("이벤트에 의한 기기 제어 정보 IoE.m에 송신 후 DB저장 결과 : " + obj);
			
		}catch (Exception e) {
			logger.error("이벤트에 의한 기기 제어 정보 IoE.m에 송신 중 오류 ", e);
			file = File.createTempFile("event_",".xml");
			FileUtils.writeStringToFile(file, e.toString());
			//저장소에 저장
			fileId = RepositoryUtils.save("CTL_DATA", file, "txt");
			
			throw e;
		}finally{
			FileUtils.deleteQuietly(file);
		}
	}
	
	
}
