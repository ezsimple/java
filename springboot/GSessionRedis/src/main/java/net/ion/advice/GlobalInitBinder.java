package net.ion.advice;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalInitBinder {

	private Log logger = LogFactory.getLog(this.getClass());
	
	@InitBinder
	public void binder(WebDataBinder binder) { }
	
	@ModelAttribute("sessionId")
	public String getSessionId(HttpServletRequest request) {
		String sessionId = request.getSession().getId();
		logger.debug(sessionId);
		return sessionId;
	}
	
	@ModelAttribute("_ts") 
	public Long getTS() {
		return System.currentTimeMillis();
	}

}
