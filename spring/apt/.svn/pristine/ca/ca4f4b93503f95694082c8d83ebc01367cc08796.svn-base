package kr.or.voj.quartz.job;

import kr.or.voj.webapp.processor.ProcessorServiceFactory;
import kr.or.voj.webapp.utils.*;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class IsmartRcvJob extends ScheduleImpJob {
	private static final Logger logger = Logger.getLogger(IsmartRcvJob.class);
	private static DrProperties drProperties = (DrProperties)ProcessorServiceFactory.getBean(DrProperties.class);

	@Override
	public void executeJob(JobExecutionContext arg0) throws Exception {
		File file = null;
		boolean isError = false;
		Exception ex = null;


		logger.info("KEP 전력사용량 수신 시작");
		
		try {
			SimpleDateFormat folderFormat = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();

			String xml = "<energyParamType "
					+ "venid=\"" + JCEUtils.encrypt(drProperties.VEN_ID)
					+ "\" meterid=\"" + JCEUtils.encrypt(drProperties.METERID)
					+ "\" dataType=\"ismart\" "
					+ "dateStr=\"" + folderFormat.format(date) + "\"/>";
			//수신임시파일
			file = File.createTempFile("event_", ".xml");
			//자료 수신
			OadrHttpUtils.pull(drProperties.VTN_ADDERSS_ISMAMT_POWER, xml, file);
			//저장소에 저장
			fileId = RepositoryUtils.save("ISMAT", file);

			logger.info("KEP 전력사용량 수신 파일 : " + fileId);
			String resXml = FileUtils.readFileToString(file, "UTF-8");
			logger.info(resXml);

			String[][] paths = {
					{"power_consumption", "energyDataType/dataList/item/@energy"},
					{"measure_date", "energyDataType/dataList/item/@ttime"},
			};

			CaseInsensitiveMap params = new CaseInsensitiveMap();
			params.put("kep_cu_id", drProperties.VEN_ID);
			params.put("device_id", drProperties.METERID);
			params.put("duration", 15);

			List<Map<String, Object>> list = XpathUtils.read(file, paths, null);

			for (Map<String, Object> param : list) {
				try {
					//params.putAll(param);
					params.put("measure_date", TimeUtils.getDate((String) param.get("measure_date")));
					params.put("power_consumption", JCEUtils.decrypt((String) param.get("power_consumption")));
//					logger.info((String)param.get("measure_date"));
//					logger.info((String)param.get("power_consumption"));
					//DB처리
					ProcessorServiceFactory.executeQuery("drApi", "power", params);
				} catch (Exception e) {
					logger.error("KEP 전력사용량 DB 처리 오류", e);
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
		logger.info("KEP 전력사용량 수신 완료");
	}

}
