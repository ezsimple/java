<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/tag"%>
<%@ taglib prefix="src" tagdir="/WEB-INF/tags/src"%>
<%// 자동생성된 파일이 아닙니다. %>
<c:set var="uuid" value="${sp:uuid()}"/>
<c:set var="def_rows" value="${UI_RESULT['list']}"/>
<tag:el source="" param="${rows[0]}"/>
<c:set var="rows" value="${UI_RESULT.list}"/>
<c:set var="lastday" value="${UI_RESULT['last'][0].day}"/>
<fmt:formatDate pattern="yyyy-MM-dd"	value="${lastday }" var="lastday"/>
<c:set var="today" value="${sp:today() }"/>
<c:set var="cblData" value="datetime,당일,전일,CBL\n" scope="request" />
<c:forEach var="row" items="${rows}" varStatus="status">
	<c:if test="${!empty row }">
		<c:set var="cblData" value="${cblData}${row.measure_date },${row.today },${row.yesterday },${row.cbl }\n"	scope="request" />
	</c:if>
</c:forEach>