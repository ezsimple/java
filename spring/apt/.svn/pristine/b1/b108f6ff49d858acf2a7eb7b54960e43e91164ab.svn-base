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
<%//이곳에 위로 개발자 정의 소스를 추가하세요%>
<%//[[START_AUTO_GENERATED_SRC]]%>
<c:set var="uuid" value="${sp:uuid()}"/>
<%//------------------------------------------------------------------------
//                     기본 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="def_rows" value="${UI_RESULT['rows']}"/>

<%//------------------------------------------------------------------------%>
	<span id="${uuid}" class="title" style="display:none;"><tag:el source="구룹코드" param="${def_rows[0]}"/></span>


	<div class="unit_page" type="page" >
		<form id="form_" action="" method="post" enctype="multipart/form-data">
		
			<input type="hidden" name="ui_id" value="code_group_list">
			<input type="hidden" name="action_type" value="">
			<!-- // -->
<%//------------------------------------------------------------------------
//                     현재 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="rows" value="${UI_RESULT.rows}"/>

<%//------------------------------------------------------------------------%>
			<table class="lst" border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 10px;">
						<tr><th class="" style="" label="code_name" width="10">코드명</th><th class="" style="" label="code_value" width="10">코드값</th><th class="" style="" label="cnt" width="10">cnt</th></tr><%%>
			<c:forEach var="row" items="${rows }" varStatus="status">
	<tr class="row_${status.index + 1}">
		<td class="" style="" align="">
							<src:mk_field name="code_name" values="${row }" isSearch="false" type="text_view" link="'code_list',{group_id: '${'$'}{code_value}'}, 1"  link_type="linkLoad" valid="notempty"  keyValid="alpa" maxlength="100" label="코드명" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="code_value" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="50" label="코드값" /> 
						</td>
					<td class="" style="" align="right">
							<src:mk_field name="cnt" values="${row }" isSearch="false" type="number_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="numeric" maxlength="21" label="cnt" /> 
						</td>
					</tr>
</c:forEach></table></form>
		<div style="clear: both; height: 25px; margin-top: 10px;padding:3px; ">
		</div><iframe name="submit_frame" style="width: 0px; height: 0px; display: none;"></iframe>
		
	</div>
	<script type="text/javascript">
		$(function() {	
			setTitle('#${uuid}');
		});
	</script>
<%//[[END_AUTO_GENERATED_SRC]]582399963[[HASHCODE]]%>
<%//이곳에 아래로 개발자 정의 소스를 추가하세요%>
