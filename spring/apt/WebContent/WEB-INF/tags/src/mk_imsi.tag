<%@tag import="kr.or.voj.webapp.utils.XmlUtil"%>
<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"%>
<%@ tag trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag"  tagdir="/WEB-INF/tags/tag" %> 
<%@ taglib prefix="src"  tagdir="/WEB-INF/tags/src"%> 
<%@ attribute name="chartType" required="true" type="java.lang.String" description="차트 종류"%>
<%@ attribute name="rows" required="false" type="java.util.List" description="리스트 레코드(key value set)"%>
<%@ attribute name="ui_id" required="true" type="java.lang.String" description="ui_id"%>

${'<'}c:set var="chartId" value="${'$'}{sp:uuid()}"/>
<c:forEach var="info" items="${rows[0] }" >
	<c:set var="key">${info.key}</c:set>
	<c:set var="type">${key}_type</c:set>
	
	<c:choose>
		<c:when test="${ui_field[type]=='xFld' }">
			<c:set var="xFld">${key}</c:set>
		</c:when>
		<c:when test="${ui_field[type]=='yFld' }">
			<c:set var="yFld">${key}</c:set>
		</c:when>
		<c:when test="${ui_field[type]=='zFld' }">
			<c:set var="zFld">${key}</c:set>
		</c:when>
		<c:when test="${ui_field[type]=='lblFld' }">
			<c:set var="lblFld">${key}</c:set>
		</c:when>
	</c:choose>
</c:forEach>
<script type="text/javascript">
	//차트옵션설정변경시 아래와 같이 설정할수 있습니다.
	//$.chart_option['${ui_id}'] = {};
	
	$(function() {
		
		var chartData = ${'${'}sp:list2chart(rows, '${lblFld}', '${xFld}', '${yFld}', '${zFld}')  };
		var  chartType = '${"$"}{empty(import_params["chart_type"]) ?  (empty(req.chart_type) ? "${chartType}" : req.chart_type) : import_params["chart_type"]}';
		drawChart(chartType, '#${"$"}{chartId}', chartData, '${ui_id}');

	});
</script>

<div style="position: relative;top: 3px;left:3px;" class="button sheet_toggle_button" icon_primary="ui-icon-document" text="false" onclick="$(this).closest('.unit_page').find('.sheet').slideToggle()">데이타 보기</div>
<div class="chart_canvas" id="${'$'}{chartId }" style="margin:0px auto; height: 100%; width: 85%; max-height: 400px; min-height: 200px;"></div>
