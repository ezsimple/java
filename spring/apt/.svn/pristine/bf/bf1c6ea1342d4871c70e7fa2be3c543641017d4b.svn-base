<%@tag import="java.util.List"%>
<%@tag import="java.util.Map"%>
<%@tag import="java.util.HashMap"%>
<%@tag import="java.util.ArrayList"%>
<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fm" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ attribute name="list" type="java.util.List" required="true"  %>
<%@ attribute name="key_field" type="java.lang.String" required="true"  %>
<%@ attribute name="var" type="java.lang.String" required="true"  %>
<%
	Map<String,Map<String,Object>> map = new HashMap<String,Map<String,Object>>();
	
	for(Object obj : list){
		Map<String,Object> row = (Map<String,Object>)obj;
		map.put(String.valueOf(row.get(key_field)), row);
	}
    // 변수명으로 저장하기
	request.getSession().setAttribute(var, map);
%>