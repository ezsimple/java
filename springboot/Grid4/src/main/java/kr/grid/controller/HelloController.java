package kr.grid.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.grid.entities.User;
import kr.grid.services.UserService;

@Controller
public class HelloController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired UserService userService;

	/*
	 * If you are using Spring 4.1 and Java 8 you can use java.util.Optional 
	 * which is supported in @RequestParam, @PathVariable, @RequestHeader 
	 * and @MatrixVariable in Spring MVC
	 */
	@RequestMapping(value={"/", "/{page}.do", "{path}/{page}.do"}, method=RequestMethod.GET)
	public ModelAndView getPage(@PathVariable Optional<String> path ,@PathVariable Optional<String> page) {
		ModelAndView mv = new ModelAndView();
		final String startPage = "index";
		String target = "";
		
		if (path.isPresent() && page.isPresent()) {
			target = path.get()+"/"+page.get();
		}
		
		if (path.isPresent() && !page.isPresent()) {
			target = path.get()+"/"+startPage;
		}
		
		if (!path.isPresent() && page.isPresent()) {
			target = page.get();
		}
		
		if (!path.isPresent() && !page.isPresent()) {
			target = startPage;
		}

		User user = userService.getCurrentUser();
		if (user!=null) {
			final String currentUser = user.getFullname();
			mv.addObject("currentUser", currentUser);
			logger.debug("currentUser : "+currentUser);
		}

		logger.debug(target);
		mv.setViewName(target);

		return mv;
	}

}