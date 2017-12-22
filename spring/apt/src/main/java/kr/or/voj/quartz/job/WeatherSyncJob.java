package kr.or.voj.quartz.job;

import kr.or.voj.webapp.service.weather.WeatherSyncService;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;


public class WeatherSyncJob extends ScheduleImpJob {

	private static final Logger logger = Logger.getLogger(WeatherSyncJob.class);

	@Autowired
	private WeatherSyncService weatherSyncService;
	
	@Override
	public void executeJob(JobExecutionContext arg0) throws Exception {
		logger.info("날씨 수신 시작");
		weatherSyncService.run();
		logger.info("날씨 수신 완료");
	}

}
