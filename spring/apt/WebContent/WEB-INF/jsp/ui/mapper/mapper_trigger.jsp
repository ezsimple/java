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
	<span id="${uuid}" class="title" style="display:none;"><tag:el source="매퍼 상세정보(${'$'}{mapper_id} )" param="${def_rows[0]}"/></span>


	<div class="unit_page" type="page" >
		<form id="form_" action="" method="post" enctype="multipart/form-data">
		
			<input type="hidden" name="ui_id" value="mapper_trigger">
			<input type="hidden" name="action_type" value="">
			<!-- // -->
<%//------------------------------------------------------------------------
//                     현재 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="rows" value="${UI_RESULT.rows}"/>

<%//------------------------------------------------------------------------%>
			<table class="lst" border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 10px;">
						<tr><th class="" style="" label="trigger_id" width="*">트리거 아이디</th><th class="" style="display: none;" label="mapper_id" width="*">매퍼 아이디</th><th class="" style="" label="trigger_xpath" width="*">노드경로</th><th class="" style="" label="qyery_path" width="*">Qyery path</th><th class="" style="" label="query_action" width="*">Query action</th><th class="" style="" label="delete_value" width="*">노드값삭제</th><th class="" style="" label="trigger_desc" width="*">설명</th></tr><%%>
			<c:forEach var="row" items="${rows }" varStatus="status">
	<tr class="row_${status.index + 1}">
		<td class="" style="" align="">
							<src:mk_field name="trigger_id" values="${row }" isSearch="false" type="text" link=""  link_type="linkLoad" valid="notempty"  keyValid="numeric" maxlength="11" label="트리거 아이디" /> 
						</td>
					<td class="" style="display: none;" align="">
							<src:mk_field name="mapper_id" values="${row }" isSearch="false" type="hidden" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="20" label="매퍼 아이디" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="trigger_xpath" values="${row }" isSearch="false" type="label" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="100" label="노드경로" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="qyery_path" values="${row }" isSearch="false" type="label" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="30" label="Qyery path" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="query_action" values="${row }" isSearch="false" type="label" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="20" label="Query action" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="delete_value" values="${row }" isSearch="false" type="label" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="1" label="노드값삭제" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="trigger_desc" values="${row }" isSearch="false" type="label" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="255" label="설명" /> 
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
<%//[[END_AUTO_GENERATED_SRC]]1906078022[[HASHCODE]]%>
<%//이곳에 아래로 개발자 정의 소스를 추가하세요%>
