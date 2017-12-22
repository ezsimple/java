package kr.or.voj.webapp.interceptor;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.voj.webapp.exception.UserSecurityException;
import kr.or.voj.webapp.session.SessionService;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter
{	
	protected static final Logger LOGGER = Logger.getLogger(LoginInterceptor.class);
	SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception  {
		boolean hasAccess = true;
		
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("X-Frame-Option", "DENY");
		String path = request.getServletPath();
		
		if("/login.sh".equals(path) || "/ai".equals(path) || "/dr".equals(path) || "/-login-login_form/.sh".equals(path)){
			LOGGER.debug(path);
			return true;
		}
		//로그인 여부 체크
		hasAccess = SessionService.isLogin(request);
		
		if(!hasAccess){
			//response.sendRedirect(request.getContextPath() + "/login/login_form.jsp");
			LOGGER.debug(hasAccess);
			throw new UserSecurityException("");
		}
		return hasAccess;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	

}
