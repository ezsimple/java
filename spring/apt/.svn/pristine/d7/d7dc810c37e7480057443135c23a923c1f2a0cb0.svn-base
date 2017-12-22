package kr.or.voj.quartz.job;

import kr.or.voj.webapp.service.oadr2.EventMonitorService;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

@DisallowConcurrentExecution
public class OpenAdrEventRcvJob extends ScheduleImpJob {
	private static final Logger logger = Logger.getLogger(OpenAdrEventRcvJob.class);

	@Autowired
	EventMonitorService eventMonitorService;

    @Override
    public void executeJob(JobExecutionContext arg0) throws Exception {
        try {
            eventMonitorService.run();
        } finally {

        }
    }

}
