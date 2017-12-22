<%@tag import="org.apache.poi.util.SystemOutLogger"%>
<%@tag import="java.util.Map"%>
<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag"  tagdir="/WEB-INF/tags/tag" %> 
<%@ taglib prefix="src"  tagdir="/WEB-INF/tags/src" %> 
<%@ attribute name="uiId" type="java.lang.String" description="UI ID"%>
<sp:sp var="ui_info" queryPath="ui" action="info" processorList="mybatis" exception="true">{ui_id:'${uiId}'}</sp:sp>
<%
%>
<sp:sp var="UI_RESULT" queryPath="${fn:substringBefore(row.query_path,'.') }" action="${fn:substringAfter(row.query_path,'.' ) }" processorList="mybatis" exception="true"/>
<c:set var="UI_RESULT" scope="request" value="${UI_RESULT }"/>
<c:set var="import_params" scope="request"><jsp:doBody/></c:set>
<c:set var="import_params" scope="request" value="${sp:str2jsonObj(import_params) }"/>
<c:import url="../ui/${fn:substringBefore(row.query_path,'.') }/${uiId }.jsp"/>
<c:set var="import_params" scope="request" value="${null }"/>
