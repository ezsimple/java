package kr.or.voj.webapp.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.voj.webapp.processor.ProcessorServiceFactory;
import kr.or.voj.webapp.session.SessionService;
import kr.or.voj.webapp.utils.CookieUtils;
import kr.or.voj.webapp.utils.HttpUtils;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	protected static final Logger LOGGER = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "login.sh")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response, @RequestParam("uid") String uId, @RequestParam("pwd") String pwd) throws Exception {
		Map<String, Object> resultSet = new HashMap<String, Object>();
		boolean success = true;

		try {
			
			CaseInsensitiveMap params = new CaseInsensitiveMap();
			params.put("uId", uId);
			params.put("pwd", pwd);
			Object rtn = ProcessorServiceFactory.executeQuery("aptUser", "login", params);
			Map<String, Object> row = ((Map<String, Map<String, Object>>)rtn).get("row");

			if(row.size() > 0){
				SessionService.saveUserInSession(request, response, row);
				//로그인 쿠키 설정
				//PagePermission.setLoginInfo(request, response);


				if("Y".equals(request.getParameter("svuid"))) {
					CookieUtils.setCookie(response, "uid", request.getParameter("uid"), 30*24*60*60);
				}else{
					CookieUtils.removeCookie(response, "uid");
				}
			
			}else{
				success = false;
			}
		} catch (Exception e) {
			success = false;
			resultSet.put("message", "처리중 오류가 발생하였습니다.");
			resultSet.put("error_message", e.toString());
			e.printStackTrace();
		}
		resultSet.put("success", success);
		return resultSet;
	}
	

	@RequestMapping(value = "logout.sh")
	@ResponseBody
	public Map<String, Object> api(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//String[] paths =path.split(".");
		
		Map<String, Object> result = new HashMap<String, Object>();		
	
		SessionService.logOut(request, response);
	
		result.put("success ", true);
		
		return result;
	}
}
