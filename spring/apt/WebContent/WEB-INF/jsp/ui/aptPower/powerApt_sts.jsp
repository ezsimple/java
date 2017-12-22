<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/tag"%>
<%@ taglib prefix="src" tagdir="/WEB-INF/tags/src"%>
<c:set var="uuid" value="${sp:uuid()}"/>
<c:set var="def_rows" value="${UI_RESULT['list']}"/>
<tag:el source="" param="${rows[0]}"/>
<c:set var="rows" value="${UI_RESULT.list}"/>
<c:set var="meterData" value="datetime,당일,전일\n" scope="request"/>
<c:forEach var="row" items="${rows}" varStatus="status">
	<c:if test="${!empty row }">
		<c:set var="meterData" value="${meterData }${row.measure_time },${row.today },${row.yesterday }\n" scope="request" />
	</c:if>
</c:forEach>