package kr.grid.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component("myBean")
public class ScheduledTasks {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	// @Scheduled(fixedRate = 5000)
	public void reportCurrentTime() throws InterruptedException {
		logger.info("[fixedRate] The time is now {}",dateFormat.format(new Date()));
	}
	
	// @Scheduled(cron = "*/3 * * * * *")
	public void reportCurrentTime2(){
		logger.info("[Cron] The time is now {}",dateFormat.format(new Date()));
	}

}