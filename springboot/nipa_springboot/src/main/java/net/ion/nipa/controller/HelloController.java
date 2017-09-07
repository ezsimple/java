package net.ion.nipa.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import net.ion.nipa.common.resolver.CommandMap;
import net.ion.nipa.service.ExamService;
import net.ion.util.RequestUtil;

@Slf4j
@RestController
public class HelloController extends AbstractController {

    @Autowired
	private ExamService svc;
	
	@RequestMapping(value={"/", "/{page}","{path}/{page}"})
	public ModelAndView something(@PathVariable Optional<String> path, @PathVariable Optional<String> page,
			HttpServletRequest request, Model model, CommandMap commandMap) throws Exception {
			
		// 1. 로그인 여부 확인
		
		// 2. 서비스 호출 (예제)
    	svc.getTestList(model, commandMap);
		
		// 3. View 경로 판단
		String target = viewPath(path, page);
		log.debug("request path/page : {}, params : {}",
				target, RequestUtil.getParameterToJson(request));	

		ModelAndView mv = new ModelAndView();
		mv.setViewName(target);

		return mv;
		
	}

}
