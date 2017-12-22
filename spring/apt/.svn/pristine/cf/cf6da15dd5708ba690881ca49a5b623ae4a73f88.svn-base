<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/tag"%>
<%@ taglib prefix="src" tagdir="/WEB-INF/tags/src"%>
<script type="text/javascript">

</script>
<%//이곳에 위로 개발자 정의 소스를 추가하세요
//자동 생성된 소스에 공백이나 탭 또는 줄 바꿈은 해도 됩니다.%>
<%//[[START_AUTO_GENERATED_SRC]]%>
<c:set var="uuid" value="${sp:uuid()}"/>
<%//------------------------------------------------------------------------
//                     기본 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="def_rows" value="${UI_RESULT['list']}"/>

<%//------------------------------------------------------------------------%>
	<span id="${uuid}" class="title" style="display:none;"><tag:el source="" param="${rows[0]}"/></span>


	<div class="unit_page" type="page" >
		<form id="form_" action="" method="post" enctype="multipart/form-data">
		
			<input type="hidden" name="ui_id" value="powerKep_list">
			<input type="hidden" name="action_type" value="">
			<!-- // -->
<%//------------------------------------------------------------------------
//                     현재 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="rows" value="${UI_RESULT.list}"/>

<%//------------------------------------------------------------------------%>
			<c:set var="chartId" value="${sp:uuid()}"/>
<script type="text/javascript">
	$(function() {
		
		var chartData = ${sp:list2chart(rows, 'gbn', 'measure_date_x', 'power_consumption_', '')  };
		
		drawChart('${empty(import_params["chart_type"]) ?  (empty(req.chart_type) ? "chart_line_ixy" : req.chart_type) : import_params["chart_type"]}', '#${chartId}', chartData);

	});
</script>

<div style="position: relative;top: 3px;left:3px;" class="button sheet_toggle_button" icon_primary="ui-icon-document" text="false" onclick="$(this).closest('.unit_page').find('.sheet').slideToggle()">데이타 보기</div>
<div id="${chartId }" style="margin:0px auto; height: 100%; width: 85%; max-height: 400px; min-height: 200px;"></div><div class="sheet" style="margin: 10px auto; display: none;">
						<table class="lst" border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 10px;">
							<tr><th class="" style="" label="gbn" width="10">구분</th><th class="" style="" label="measure_date" width="10">측정일</th><th class="hide_mb hide_web" style="" label="measure_date_x" width="10">measure_date_x</th><th class="" style="" label="power_consumption" width="10">전력량(kWh)</th><th class="hide_mb hide_web" style="" label="power_consumption_" width="10">power_consumption_</th></tr><%%>
			<c:forEach var="row" items="${rows }" varStatus="status">
	<tr class="row_${status.index + 1}">
		<td class="" style="" align="">
							<src:mk_field name="gbn" values="${row }" isSearch="false" type="lblFld" link=""  link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="2" label="구분" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="measure_date" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="10" label="측정일" /> 
						</td>
					<td class="hide_mb hide_web" style="" align="">
							<src:mk_field name="measure_date_x" values="${row }" isSearch="false" type="xFld" link=""  link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="21" label="measure_date_x" /> 
						</td>
					<td class="" style="" align="right">
							<src:mk_field name="power_consumption" values="${row }" isSearch="false" type="number_view" link=""  link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="42" label="전력량(kWh)" /> 
						</td>
					<td class="hide_mb hide_web" style="" align="">
							<src:mk_field name="power_consumption_" values="${row }" isSearch="false" type="yFld" link=""  link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="48" label="power_consumption_" /> 
						</td>
					</tr>
</c:forEach></table>
					</div></form>
		<iframe name="submit_frame" style="width: 0px; height: 0px; display: none;"></iframe>
		
	</div>
	<script type="text/javascript">
		$(function() {	
			setTitle('#${uuid}');
		});
	</script>
<%//[[END_AUTO_GENERATED_SRC]]-1621081171[[HASHCODE]]%>
<%//이곳에 아래로 개발자 정의 소스를 추가하세요%>
