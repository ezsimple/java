package kr.grid.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.grid.entities.Group;
import kr.grid.entities.User;
import kr.grid.repositories.UserRepository;
import kr.grid.services.UserService;

@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private UserRepository userDao;
	@Autowired private UserService userService;

	User user = null;
	Group group = null;

	
	public String getParamValue(HttpServletRequest req,final String key) {
		Map<String, String[]> parameterMap = req.getParameterMap();
		if (parameterMap.get(key) instanceof String[])
			return parameterMap.get(key)[0];
		return "";
		
// ========================================================
//		for(String k: parameterMap.keySet()) {
//			String[] values = parameterMap.get(k);
//			for(String v : values) {
//				logger.debug(key+"="+v);
//			}
//		}
// ========================================================

	}
	
	/*
	 * 없는(비어있는) 파라미터를 가지고 있으면 true
	 */
	public Boolean hasEmptyParam(HttpServletRequest req) {
		Map<String, String[]> parameterMap = req.getParameterMap();
		for(String key : parameterMap.keySet()) {
			// logger.debug(""+parameterMap.get(key).getClass());
			if (parameterMap.get(key) instanceof String[]) {
				String value = parameterMap.get(key)[0];
				logger.debug(key+"="+value);
				if (value.isEmpty()) {
					logger.debug(key+" is empty");
					return true;
				}

			}
		}
		return false;
	}
	
	// 리다이렉트는 전송된 데이타가 모두 사라지는데 .... 
	// 스프링 컨트롤러단에서의 history.back 기능은 불가능 한가?
	// forward: 의 경우는 브라우저 주소줄 불일치가 발생한다.
	// 역시 코딩을 해봐야 뭐가 중요한지 알수 있는 것 같다.
	public String historyBack(HttpServletRequest req) {
		return "redirect:"+req.getHeader("Referer");
	}
	
	@RequestMapping(value="/user/list.do", method=RequestMethod.GET)
	public ModelAndView list(Model model, 
			@PageableDefault(sort = { "username" }, direction = Direction.DESC, size = 10) Pageable pageable) {
		ModelAndView mv = new ModelAndView();
		String viewName = "/user/list";
		
		Page<User> users = userService.listUser(pageable);
		mv.addObject("list", users);

		mv.setViewName(viewName);
		return mv;
	}

	@RequestMapping(value="/user/register.do", method=RequestMethod.GET)
	public ModelAndView registerForm(@ModelAttribute("userForm") User data) {
		final String viewName = "/user/register";
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		return mv;
	}
	
	@RequestMapping(value="/user/register.do", method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("userForm") @Validated User data,
			BindingResult result,
			Model model,
			final RedirectAttributes redirectAttributes,
			HttpServletRequest req) {
		
		final String back = "/user/register.do";
		final String success = "redirect:/user/register.do";
		
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<>();
		map.put("user", data); // Neither BindingResult nor plain target object for bean name
		mv.addAllObjects(map);
		mv.setViewName(back);

//		if (result.hasErrors()) {
//			return mv;
//		}
		
		if(hasEmptyParam(req)) {
			return mv;
		}
		
		if(!data.getPassword().trim().equals(getParamValue(req,"password2").trim())) {
			logger.debug("password do not equal");
			return mv;
		}

		// checkbox의 경우 선택하지 않으면, request객체에 아예 실려오지 않는다.
		if(req.getParameter("agree")==null) {
			logger.debug("agree do not checked");
			mv.addObject("message", "동의가 필요합니다.");
			return mv;
		}

		logger.warn(data.getUsername());
		user = userDao.findByUsername(data.getUsername());
		if(user != null) {
			logger.debug(user.getUsername() + " is already exist");
			mv.addObject("message", "이미 존재하는 이메일 입니다.");
			return mv;
		}
		
//		Page<Group> groups = groupDao.findAll();
//		if(groups == null || !groups.hasContent()) {
//			logger.debug("groups is null");
//			return mv;
//		}
//		
//		List<Group> userGroups = new ArrayList<Group>();
//		for(Group g : groups) {
//			// admin group은 제거한다. (1, 99 가 존재한다)
//			if(g.getId() == 1  || g.getId() == 99) 
//				userGroups.add(g);
//		}
//		String userGroupList = StringUtils.join(userGroups);
//		logger.debug(userGroupList);
		
		user = userService.createUser(data.getUsername(), data.getFullname(), data.getPassword());
		if(user == null)
			return mv;

		logger.warn("{} created",user.getUsername());
		
		// Add message to flash scope
		redirectAttributes.addFlashAttribute("css","success");
		
		// Exception : Neither BindingResult nor plain target object for bean name 'userForm' available as request attribute
		// GET(View:userForm) -> POST(Data:userForm) -(redirect:userForm)-> GET(View:userForm)
		mv.addObject("userForm",new User());  // redirect시 비어있는 객체를 전달해야 한다.
		// mv.addObject("message", user.getUsername() + "로 등록되었습니다."); // redirect시 GET 파라미터로 노출됩니다.
		mv.setViewName(success);

		return mv;
	}
	
//  스프링 시큐리티에서 대신 처리함.
//	@RequestMapping(value="/user/login", method=RequestMethod.POST)
//	public ModelAndView login(HttpServletRequest req) {
//
//		final String back = "/user/login";
//		final String success = "redirect:/";
//
//		logger.warn("로그인 요청");
//
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName(back);
//
//		return mv;
//	}
	
//  @Component Initialization 에서 부팅할 때 시작하게 됩니다
//	@RequestMapping("/user/init")
//	public String initUser() {
//		userService.initUser();
//		return "/";
//	}
	
}
