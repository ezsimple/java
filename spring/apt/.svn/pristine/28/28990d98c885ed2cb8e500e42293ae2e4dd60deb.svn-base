<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag"  tagdir="/WEB-INF/tags/tag" %> 
<%@ taglib prefix="src"  tagdir="/WEB-INF/tags/src" %> 
<c:set var="html">
	<c:import url="${UI_ID }"/>
</c:set>
<c:choose>
	<c:when test="${PIECE_TYPE=='f' }">
		${'<form'} ${sp:substringBetween(html,'<form','</form>')}${'</form>'}
	</c:when>
	<c:when test="${PIECE_TYPE=='nf' }">
	</c:when>
	<c:when test="${PIECE_TYPE=='t' }">
		${'<table'} ${sp:substringBetween(html,'<table','</table>')}${'</table>'}
	</c:when>
	<c:when test="${PIECE_TYPE=='trh' }">
		${'<tr'} ${sp:substringBetween(html,'<tr','</table>')}
	</c:when>
	<c:when test="${PIECE_TYPE=='tr' }">
		${sp:substringBetween(html,'</tr>','</table>')}
	</c:when>
	<c:otherwise>
		${html}
	</c:otherwise>
</c:choose>
