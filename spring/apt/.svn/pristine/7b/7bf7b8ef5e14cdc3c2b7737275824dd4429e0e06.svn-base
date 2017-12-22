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
	<span id="${uuid}" class="title" style="display:none;"><tag:el source="프로그램 목록 " param="${def_rows[0]}"/></span>


	<div class="unit_page" type="page" >
		<form id="form_" action="" method="post" enctype="multipart/form-data">
		
			<input type="hidden" name="ui_id" value="menu_list">
			<input type="hidden" name="action_type" value="">
			<!-- // -->
<%//------------------------------------------------------------------------
//                     현재 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="rows" value="${UI_RESULT.rows}"/>

<%//------------------------------------------------------------------------%>
			<table class="lst" border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 10px;">
						<tr><th class="" style="" label="ui_id" width="10">ui_id</th><th class="" style="" label="query_path" width="10">query_path</th><th class="" style="" label="ui_title" width="10">ui_title</th></tr><%%>
			<c:forEach var="row" items="${rows }" varStatus="status">
	<tr class="row_${status.index + 1}">
		<td class="" style="" align="">
							<src:mk_field name="ui_id" values="${row }" isSearch="false" type="text_view" link="openPage('${'$'}{ui_id}','${'$'}{tpl_path}')"  link_type="linkFnc" valid="notempty"  keyValid="" maxlength="30" label="ui_id" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="query_path" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkFnc" valid="notempty"  keyValid="" maxlength="255" label="query_path" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="ui_title" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="100" label="ui_title" /> 
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
<%//[[END_AUTO_GENERATED_SRC]]-928815227[[HASHCODE]]%>
<%//이곳에 아래로 개발자 정의 소스를 추가하세요%>
