package kr.grid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.grid.service.ExamService;

@RestController
public class HelloController {
	
	@Autowired
	private ExamService svc;
	
	@RequestMapping("/")
	public String hello() {
		String Hello = "Hello World at ";
		return Hello + svc.getNow();
	}

}
