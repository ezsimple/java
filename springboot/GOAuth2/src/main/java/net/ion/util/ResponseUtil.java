package net.ion.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseUtil {

	private static Log logger = LogFactory.getLog(ResponseUtil.class);
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getResponseAsMap(String responseBody)  {

		Map<String, Object> values = new HashMap<String, Object> ();
		ObjectMapper mapper = new ObjectMapper();

		try {
			values = mapper.readValue(responseBody, Map.class);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}		
		return values;
	}

}