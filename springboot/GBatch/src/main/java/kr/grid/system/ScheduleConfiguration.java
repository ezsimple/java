package kr.grid.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

@Configuration
@EnableScheduling
@ImportResource("classpath:tasks.xml")
public class ScheduleConfiguration {
	
	@Bean 
	public ScheduledExecutorFactoryBean scheduledExecutorService() { 
		ScheduledExecutorFactoryBean bean = new ScheduledExecutorFactoryBean(); 
		bean.setPoolSize(5); 
		return bean; 
	}

}