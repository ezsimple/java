package net.ion.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/")
	String uid(HttpSession session) {
		String sessionId = session.getId();
		return sessionId;
	}
}
