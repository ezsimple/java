package kr.or.voj.webapp.controller;


import kr.or.voj.webapp.service.weather.WeatherSyncService;
import kr.or.voj.webapp.processor.ProcessorServiceFactory;
import kr.or.voj.webapp.utils.HttpUtils;
import kr.or.voj.webapp.utils.ProxyUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AutoController {
	protected static final Logger LOGGER = Logger.getLogger(AutoController.class);
	final private String mainView = "sa_main";
	final private String loginView = "sa_login_form";

//	@RequestMapping(value = "-{tplPath}/-{uiId}.sh")
//	public ModelAndView autoMain(HttpServletRequest request, HttpServletResponse response, @PathVariable("tplPath") String tplPath, @PathVariable("uiId") String uiId) throws Exception {
//		tplPath = tplPath.replace('-', '/');
//		LOGGER.info("tplPath:"+tplPath);
//		
//		ModelAndView mv = new ModelAndView(mainView);
//		try {
//			mv.addObject("UI_TPL", tplPath + ".jsp");
//			Map<String, Object> resultSet = executeResultDataByUiId(uiId, request, response);
//			mv.addObject("UI_RESULT", resultSet);
//			mv.addObject("UI_ID", resultSet.get("_JSP_"));
//			mv.addObject("menu_id", uiId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return mv;
//	}
	@RequestMapping(value = "{tplPath}/-{uiId}.sh")
	public ModelAndView autoTpl(HttpServletRequest request, HttpServletResponse response, @PathVariable("tplPath") String tplPath, @PathVariable("uiId") String uiId) throws Exception {
		LOGGER.info(tplPath+"/-"+uiId+".sh");
		tplPath = tplPath.replace('-', '/');
		ModelAndView mv = new ModelAndView(tplPath);
		Map<String, Object> resultSet = executeResultDataByUiId(uiId, request, response);
		mv.addObject("UI_RESULT", resultSet);
		mv.addObject("UI_ID", resultSet.get("_JSP_"));
		mv.addObject("menu_id", uiId);
		return mv;
	}
	@RequestMapping(value = "piece/-{uiId}-{type}.sh")
	public ModelAndView autoPiece(HttpServletRequest request, HttpServletResponse response, @PathVariable("uiId") String uiId, @PathVariable("type") String type) throws Exception {
		LOGGER.info("piece/-"+uiId+"-"+type+".sh");
		ModelAndView mv = new ModelAndView("at/piece");
		
		try {
			LOGGER.info(getParams(request));
			Map<String, Object> resultSet = executeResultDataByUiId(uiId, request, response);
			mv.addObject("UI_RESULT", resultSet);
			mv.addObject("UI_ID", "../" + resultSet.get("_JSP_"));
			mv.addObject("PIECE_TYPE", type);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@Autowired
	private WeatherSyncService weatherSyncService;

	@RequestMapping(value = "action/-{uiId}.sh")
	@ResponseBody
	public Map<String, Object> action(HttpServletRequest request, HttpServletResponse response, @PathVariable("uiId") String uiId, @RequestParam("action_type") String actionType) throws Exception {
		LOGGER.info("action/-"+uiId+".sh");
		Map<String, Object> resultSet = null;
		boolean success = true;
		String loopFieldName = request.getParameter("loop_field_name");
		
		try {
			resultSet = executeActionByUiId(uiId, actionType, loopFieldName, request, response);
		} catch (Exception e) {
			resultSet = new HashMap<String, Object>();
			success = false;
			resultSet.put("message", "처리중 오류가 발생하였습니다.");
			resultSet.put("error_message", e.toString());
			e.printStackTrace();
		}
		resultSet.put("success", success);
        if("custView".equals(uiId)) {
            weatherSyncService.run();
        }
		return resultSet;
	}
	
//	@RequestMapping(value = "-{tplPath}/{page}.sh")
//	public ModelAndView mainPage(HttpServletRequest request, HttpServletResponse response, @PathVariable("tplPath") String tplPath, @PathVariable("page") String page) throws Exception {
//		tplPath = tplPath.replace('-', '/');
//		LOGGER.info("tplPath:"+tplPath);
//		ModelAndView mv = new ModelAndView(mainView);
//		mv.addObject("UI_TPL", tplPath + ".jsp");
//		mv.addObject("IMPORT_PAGE", tplPath + "/../" + page.replace('-', '/') + ".jsp");
//		mv.addObject("menu_id", page);
//		return mv;
//	}
	
	// For handling SmartAdmin pages
	@RequestMapping(value = "-{tplPath}/{page}.sh")
	public ModelAndView commonPage(HttpServletRequest request, HttpServletResponse response, @PathVariable("tplPath") String tplPath, @PathVariable("page") String page) throws Exception {
		LOGGER.info("-"+tplPath+"/"+page+".sh");
		LOGGER.info(getParams(request));
		ModelMap modelMap = new ModelMap();
		modelMap.addAllAttributes(request.getParameterMap());
		if (tplPath.equals("dr-home")) {
			return new ModelAndView("common_tiles", modelMap);
		}
		if (tplPath.equals("dr-admin")) {
			return new ModelAndView("admin_tiles", modelMap);
		}
		return new ModelAndView("login_tiles");
	}
	@RequestMapping(value = "-{tplPath}/-{uiId}.sh")
	public ModelAndView menuPage(HttpServletRequest request, HttpServletResponse response, @PathVariable("tplPath") String tplPath, @PathVariable("uiId") String uiId) throws Exception {
		LOGGER.info("-"+tplPath+"/-"+uiId+".sh");
		if (tplPath.equals("at-lo-pg") || tplPath.equals("at-lo-h3")) {
			if ("home".equals(uiId))
				return new ModelAndView("dr/start");
			if ("admin".equals(uiId))
				return new ModelAndView("admin/src/main");
			try {
				LOGGER.info(getParams(request));
				Map<String, Object> resultSet = executeResultDataByUiId(uiId, request, response);
				String jsp = (String) resultSet.get("_JSP_");
				if (jsp != null && !StringUtils.isEmpty(jsp))
					jsp = jsp.replaceAll(".jsp", "");
				LOGGER.info("jsp ==> " + jsp);
				ModelAndView mv = new ModelAndView(jsp);
				mv.addObject("UI_RESULT", resultSet);
				mv.addObject("menu_id", uiId);
				return mv;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("common/error");
	}

	
	@RequestMapping(value = "{path}/{page}.sh")
	public ModelAndView page(HttpServletRequest request, HttpServletResponse response, @PathVariable("path") String path, @PathVariable("page") String page) throws Exception {
		LOGGER.info(path+"/"+page+".sh");
		path = path + "/"+ page;
		path = path.replace('-', '/');
		ModelAndView mv = new ModelAndView(path);
		mv.addObject("menu_id", path);
		return mv;
	}


	@RequestMapping(value = "/ai/{path}.{action}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> api(HttpServletRequest request, HttpServletResponse response, @PathVariable("path") String path, @PathVariable("action") String action) throws Exception{
		// LOGGER.info("/ai/"+path+"."+action);
		//String[] paths =path.split(".");
		
		Map<String, Object> result = new HashMap<String, Object>();		
		
		List<String> processorList = new ArrayList<String>();
		processorList.add("mybatis");
		
		CaseInsensitiveMap params = new CaseInsensitiveMap();
		
		try {
			Map<String, Object> resultSet = ProcessorServiceFactory.executeMainTransaction(processorList, params, path, action, null, request, response);			
			result.putAll(resultSet);
			result.put("success ", true);
		} catch (Exception e) {
			result.put("error_message ", e.toString());
			result.put("success ", false);
			e.printStackTrace();
		}
		
		
		return result;
	}
	/**
	 * 다운로드
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "dl.sh")
	public ModelAndView dowonload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LOGGER.info("dl.sh");
		List<String> processorList = new ArrayList<String>();
		processorList.add("mybatis");
		processorList.add("download");
		
		ProcessorServiceFactory.executeMainTransaction(processorList, new CaseInsensitiveMap(), "attach", "dowonload", null, request, response);
			
		
		return null;
	}
	
	@RequestMapping(value = "proxy.sh")
	public ModelAndView proxy(HttpServletRequest request, HttpServletResponse response, @RequestParam("url") String url, @RequestParam("charset") String charset) throws Exception {
		LOGGER.info("proxy.sh");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("charset", charset);
		LOGGER.info("url : "+url);
		String html = HttpUtils.getString(url, parameterMap);
		ModelAndView mv = new ModelAndView("proxy/page");
		mv.addObject("html", html);
		return mv;
	}

	@RequestMapping(value = "parse.sh")
	public ModelAndView parse(HttpServletRequest request, HttpServletResponse response, @RequestParam("url") String url) throws Exception {
		LOGGER.info("url:"+url);
		String html = ProxyUtils.getHtml(url);

		ModelAndView mv = new ModelAndView("proxy/page");
		mv.addObject("html", html);
		return mv;
	}
	
	private Map<String, Object> executeActionByUiId(String uiId, String actionType, String loopFieldName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<String> processorList = new ArrayList<String>();
		processorList.add("attach");
		processorList.add("mybatis");
		
		CaseInsensitiveMap params = new CaseInsensitiveMap();
		params.put("ui_id", uiId);
		Map<String, String> row = ((Map<String, Map<String, String>>)ProcessorServiceFactory.executeQuery("ui", "info", params)).get("row");
		
		String actionQuery = null;
		
		if("D".equalsIgnoreCase(actionType)){
			actionQuery = row.get("delete_path");
		}else if("U".equalsIgnoreCase(actionType)){
			actionQuery = row.get("update_path");
		}else if("I".equalsIgnoreCase(actionType)){
			actionQuery = row.get("insert_path");
		}else{
			throw new RuntimeException("action 호출이 잘못되었습니다.");
		}
		
		String queryPath = StringUtils.substringBefore(actionQuery, ".");
		String action = StringUtils.substringAfter(actionQuery, ".");
			
		Map<String, Object> resultSet = null;
		resultSet = ProcessorServiceFactory.executeMainTransaction(processorList, params, queryPath, action, null, request, response);			
		return resultSet;
	}

	private static Map<String, Object> executeResultDataByUiId(String uiId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Map<String, Object>> row=null;
		List<String> processorList = new ArrayList<String>();
		processorList.add("mybatis");
		
		CaseInsensitiveMap params = new CaseInsensitiveMap();
		params.put("ui_id", uiId);
		try {
			 row = (Map<String, Map<String, Object>>)ProcessorServiceFactory.executeQuery("ui", "info", params);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} 
		String queryPath = StringUtils.substringBefore((String)row.get("row").get("query_path"), ".");
		String action = StringUtils.substringAfter((String)row.get("row").get("query_path"), ".");
			
		JSONObject jsonResult = new JSONObject();
		Map<String, Object> resultSet = null;
		resultSet = ProcessorServiceFactory.executeMainTransaction(processorList, params, queryPath, action, null, request, response);			
		jsonResult.putAll(resultSet);
		jsonResult.put("success", true);
		
		resultSet.put("JSON", jsonResult);

		String jsp ="ui/" + queryPath + "/" + uiId + ".jsp";
		resultSet.put("_JSP_", jsp);
		return resultSet;
	}
//	
//	@SuppressWarnings("unused")
//	private void debugParams(HttpServletRequest request) {
//		Map<String, String[]> pm = request.getParameterMap();
//		if(pm==null || pm.isEmpty()) LOGGER.info("ParameterMap is null or Empty");
//		for(String key :  pm.keySet()){
//			String[] v = pm.get(key);
//			for(int i=0; i<v.length; i++){
//				LOGGER.info(v[i]);
//			}
//		}
//	}
//
	@SuppressWarnings("unused")
	private String getParams(HttpServletRequest request) {
		Map<String, String[]> pm = request.getParameterMap();
		if(pm==null || pm.isEmpty()) return "ParameterMap is null or Empty";
		StringBuilder sb = new StringBuilder();
		for(String key :  pm.keySet()){
			if(sb.length()>0) sb.append("&");
			String[] v = pm.get(key);
			sb.append(key+"="+v[0]);
		}
		return sb.toString();
	}


	public WeatherSyncService getWeatherSyncService() {
		return weatherSyncService;
	}

	public void setWeatherSyncService(WeatherSyncService weatherSyncService) {
		this.weatherSyncService = weatherSyncService;
	}
}
