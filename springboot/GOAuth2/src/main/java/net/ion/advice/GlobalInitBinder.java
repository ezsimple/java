package net.ion.advice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalInitBinder {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
