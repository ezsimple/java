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
	<span id="${uuid}" class="title" style="display:none;"><tag:el source="매퍼 목록" param="${def_rows[0]}"/></span>


	<div class="unit_page" type="page" >
		<form id="form_" action="" method="post" enctype="multipart/form-data">
		
			<input type="hidden" name="ui_id" value="mapper_list">
			<input type="hidden" name="action_type" value="">
			<!-- // -->
<%//------------------------------------------------------------------------
//                     현재 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="rows" value="${UI_RESULT.rows}"/>

<%//------------------------------------------------------------------------%>
			<table class="lst" border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 10px;">
						<tr><th class="" style="" label="mapper_id" width="40">아이디</th><th class="" style="" label="source_path" width="150">Source Path</th><th class="" style="" label="sucess_path" width="150">Sucess Path</th><th class="" style="" label="error_path" width="150">Error Path</th><th class="" style="display: none;" label="mapper_adapter_class" width="10">mapper_adapter_class</th><th class="" style="" label="transactional" width="60">트랜잭션</th><th class="" style="" label="check_date" width="100">스케쥴체크시간</th><th class="" style="" label="start_date" width="100">스케쥴시작</th><th class="" style="" label="end_date" width="100">스케쥴종료</th><th class="" style="display: none;" label="process_message" width="10">process_message</th><th class="" style="" label="message_date" width="100">메세지시간</th><th class="" style="" label="desc" width="*">설명</th><th class="" style="" label="edit" width="60">수정</th></tr><%%>
			<c:forEach var="row" items="${rows }" varStatus="status">
	<tr class="row_${status.index + 1}">
		<td class="" style="" align="">
							<src:mk_field name="mapper_id" values="${row }" isSearch="false" type="text" link="'mapper_trigger',{mapper_id: '${'$'}{mapper_id}'}, 1"  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="20" label="아이디" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="source_path" values="${row }" isSearch="false" type="text" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="255" label="Source Path" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="sucess_path" values="${row }" isSearch="false" type="text" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="255" label="Sucess Path" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="error_path" values="${row }" isSearch="false" type="text" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="255" label="Error Path" /> 
						</td>
					<td class="" style="display: none;" align="">
							<src:mk_field name="mapper_adapter_class" values="${row }" isSearch="false" type="hidden" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="30" label="mapper_adapter_class" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="transactional" values="${row }" isSearch="false" type="text" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="1" label="트랜잭션" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="check_date" values="${row }" isSearch="false" type="datetime_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="19" label="스케쥴체크시간" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="start_date" values="${row }" isSearch="false" type="datetime_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="19" label="스케쥴시작" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="end_date" values="${row }" isSearch="false" type="datetime_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="19" label="스케쥴종료" /> 
						</td>
					<td class="" style="display: none;" align="">
							<src:mk_field name="process_message" values="${row }" isSearch="false" type="hidden" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="5592405" label="process_message" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="message_date" values="${row }" isSearch="false" type="datetime_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="19" label="메세지시간" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="desc" values="${row }" isSearch="false" type="text" link=""  link_type="linkPage" valid="notempty"  keyValid="" maxlength="255" label="설명" /> 
						</td>
					<td class="" style="" align="center">
							<src:mk_field name="edit" values="${row }" isSearch="false" type="button" link="'',{mapper_id:'${'$'}{mapper_id}'},'../-admin-mapper-main/trigger_mapping.sh'"  link_type="linkPage" valid="notempty"  keyValid="" maxlength="5" label="수정" /> 
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
<%//[[END_AUTO_GENERATED_SRC]]-1620278900[[HASHCODE]]%>
<%//이곳에 아래로 개발자 정의 소스를 추가하세요%>
