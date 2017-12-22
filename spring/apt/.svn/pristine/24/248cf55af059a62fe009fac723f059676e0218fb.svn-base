package kr.or.voj.quartz.job;

import kr.or.voj.webapp.processor.ProcessorServiceFactory;
import kr.or.voj.webapp.utils.JCEUtils;
import kr.or.voj.webapp.utils.OadrHttpUtils;
import kr.or.voj.webapp.utils.RepositoryUtils;
import kr.or.voj.webapp.utils.XpathUtils;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class CBLRcvJob extends ScheduleImpJob {
	private static final Logger logger = Logger.getLogger(CBLRcvJob.class);
	private static DrProperties drProperties = (DrProperties)ProcessorServiceFactory.getBean(DrProperties.class);

	SimpleDateFormat cblFormat = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public void executeJob(JobExecutionContext arg0) throws Exception {		
		//this.test();
		
		logger.info("한전CBL 수신 시작");
		
		Date date = new Date();
		//당일자료
		run(date);
		
		String cDate = cblFormat.format(date);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, -6);
		date = calendar.getTime();
		
		String bDate = cblFormat.format(date);
		
		//날짜가 변경되는 일정시간 동안은 전일자료 조회
		//사간차에 의해 경계에 있는 자료를 못 받을 수 있고 야간 서버 점검으로 못 받는 경우가 없도록 하기위해
		if(!StringUtils.equals(cDate, bDate)){
			run(date);
		}
		
		logger.info("한전CBL 수신 완료");
	}
	protected void run(Date date) throws Exception {
		File file = null;
		boolean isError = false;
		Exception ex = null;
		
		try {

			String xml = "<cblParamType "
					+ "venid=\"" + JCEUtils.encrypt(drProperties.VEN_ID)
					+ "\" dataType=\"ismart\" "
					+ "dateStr=\"" + cblFormat.format(date) + "\"/>";
			//수신임시파일
			file = File.createTempFile("event_", ".xml");
			//자료 수신
			OadrHttpUtils.pull(drProperties.VTN_ADDERSS_CBL, xml, file);
			//저장소에 저장
			fileId = RepositoryUtils.save("CBL", file);

			logger.info("한전CBL 수신 파일 : " + fileId);
			String resXml = FileUtils.readFileToString(file, "UTF-8");
			logger.info(resXml);

			String[][] paths = {
					{"cbl", "cblDataType/cblList/item/@cbl"},
					{"cbl_std_hour", "cblDataType/cblList/item/@hour"},
			};

			CaseInsensitiveMap params = new CaseInsensitiveMap();
			params.put("kep_cu_id", drProperties.VEN_ID);
			params.put("cbl_std_date", dateFormat.format(date));

			List<Map<String, Object>> list = XpathUtils.read(file, paths, null);

			for (Map<String, Object> param : list) {
				try {
					//params.putAll(param);
					params.put("cbl", JCEUtils.decrypt((String) param.get("cbl")));
					params.put("cbl_std_hour", (String) param.get("cbl_std_hour"));
					//DB처리
					ProcessorServiceFactory.executeQuery("drApi", "cbl", params);
				} catch (Exception e) {
					logger.error("한전CBL DB 처리 오류", e);
					isError = true;
					ex = e;
				}
			}

			if (isError) {
				throw ex;
			}

		}finally{
			if(file!=null){
				try {
					file.delete();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}
	
}
