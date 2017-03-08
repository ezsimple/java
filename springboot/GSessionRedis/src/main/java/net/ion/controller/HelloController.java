package net.ion.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private Log logger = LogFactory.getLog(this.getClass());

	@GetMapping("/")
	String uid(HttpSession session) {
		String sessionId = session.getId();
		logger.debug(sessionId);
		return sessionId;
	}
}
