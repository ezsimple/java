package kr.or.voj.webapp.service.weather;

import kr.or.voj.webapp.processor.ProcessorServiceFactory;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@DisallowConcurrentExecution
public class WeatherSyncService {

	private static final Logger logger = Logger.getLogger(WeatherSyncService.class);
	public static void run(){
		WeatherGathering weather = new KmaWeatherGatheringImpl();
		
		try {
			Map result  = (Map) ProcessorServiceFactory.executeQuery("aptCust", "custView", new CaseInsensitiveMap());
			// 날씨정보가 필요한 지역
			
			if(result.get("row") != null){
				Map<String, Object> row = (Map<String, Object>) result.get("row");
				String kmaZone = row.get("kma_zone").toString();
				String gridX = row.get("gridx").toString();
				String gridY = row.get("gridy").toString();
				
				List<Map<String, Object>> weatherList = weather.getWeather(gridX, gridY);
				
				for(Map<String, Object> weatherMap : weatherList){
					// 임시처리
					weatherMap.put("kma_zone", kmaZone);
					weatherMap.put("gridx", gridX);
					weatherMap.put("gridy", gridY);
					
					// insert
					ProcessorServiceFactory.executeQuery("weather", "insert", new CaseInsensitiveMap(weatherMap));				
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			logger.error("날씨 수신 에러발생! ", e);
		}
	}
}
