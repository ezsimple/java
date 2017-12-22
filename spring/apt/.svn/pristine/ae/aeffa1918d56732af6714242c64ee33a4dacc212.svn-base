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

<c:set var="def_rows" value="${UI_RESULT['row']}"/>

<%//------------------------------------------------------------------------%>
	<span id="${uuid}" class="title" style="display:none;"><tag:el source="요구사항 등록" param="${def_rows}"/></span>


	<div class="unit_page" type="page" >
		<form id="form_" action="" method="post" enctype="multipart/form-data">
		
			<input type="hidden" name="ui_id" value="devReqEdit">
			<input type="hidden" name="action_type" value="">
			<!-- // -->
<%//------------------------------------------------------------------------
//                     현재 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="rows" value="${UI_RESULT.row}"/>

<%//------------------------------------------------------------------------%>
			<table class="vw" border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 10px;">
						<%%>
			<colgroup>
	<col width="${isMobile ? '100' : '150' }">
	<col width="*">
</colgroup>
<tr style="">
				<th label="req_type"><span class="field_names" name="req_type">종류</span></th>
				<td><span class="fields" name="req_type"><src:mk_field name="req_type" isSearch="false" values="${rows}" type="radio"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="10" label="종류"/></span></td>
			</tr>
		<tr style="">
				<th label="subject"><span class="field_names" name="subject">제목</span></th>
				<td><span class="fields" name="subject"><src:mk_field name="subject" isSearch="false" values="${rows}" type="text"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="200" label="제목"/></span></td>
			</tr>
		<tr style="">
				<th label="contents"><span class="field_names" name="contents">내용</span></th>
				<td><span class="fields" name="contents"><src:mk_field name="contents" isSearch="false" values="${rows}" type="textarea"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="5592405" label="내용"/></span></td>
			</tr>
		<tr style="">
				<th label="end_dt"><span class="field_names" name="end_dt">완료일</span></th>
				<td><span class="fields" name="end_dt"><src:mk_field name="end_dt" isSearch="false" values="${rows}" type="date"  link="" link_type="linkLoad" valid="date" valid2="enddt"  keyValid="" maxlength="19" label="완료일"/></span></td>
			</tr>
		<tr style="">
				<th label="sch_end_dt"><span class="field_names" name="sch_end_dt">완료예정일</span></th>
				<td><span class="fields" name="sch_end_dt"><src:mk_field name="sch_end_dt" isSearch="false" values="${rows}" type="date"  link="" link_type="linkLoad" valid="date" valid2="schdt"  keyValid="" maxlength="19" label="완료예정일"/></span></td>
			</tr>
		<tr style="">
				<th label="exp_end_dt"><span class="field_names" name="exp_end_dt">종료일</span></th>
				<td><span class="fields" name="exp_end_dt"><src:mk_field name="exp_end_dt" isSearch="false" values="${rows}" type="date"  link="" link_type="linkLoad" valid="notempty,date" valid2=""  keyValid="" maxlength="19" label="종료일"/></span></td>
			</tr>
		<tr style="">
				<th label="req_status"><span class="field_names" name="req_status">상태</span></th>
				<td><span class="fields" name="req_status"><src:mk_field name="req_status" isSearch="false" values="${rows}" type="radio"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="10" label="상태"/></span></td>
			</tr>
		<tr style="">
				<th label="req_name"><span class="field_names" name="req_name">요청자</span></th>
				<td><span class="fields" name="req_name"><src:mk_field name="req_name" isSearch="false" values="${rows}" type="text"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="50" label="요청자"/></span></td>
			</tr>
		<tr style="">
				<th label="developer_id"><span class="field_names" name="developer_id">개발담당자</span></th>
				<td><span class="fields" name="developer_id"><src:mk_field name="developer_id" isSearch="false" values="${rows}" type="text"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="20" label="개발담당자"/></span></td>
			</tr></table></form>
		<div style="clear: both; height: 25px; margin-top: 10px;padding:3px; ">
		<div class="save_btn button f_r m_5" icon_primary="ui-icon-disk" style=" display: none;" onclick="form_submit(this)">저장</div>
				<div class="edit_btn button f_r m_5" icon_primary="ui-icon-unlocked" style="" onclick="edit(this)">수정</div>
				<div class="button f_r m_5" icon_primary="ui-icon-circle-close" style=" " onclick="closePop(this)">닫기</div>
				</div><iframe name="submit_frame" style="width: 0px; height: 0px; display: none;"></iframe>
		
	</div>
	<script type="text/javascript">
		$(function() {	
			setTitle('#${uuid}');
		});
	</script>
<%//[[END_AUTO_GENERATED_SRC]]1920204205[[HASHCODE]]%>
<%//이곳에 아래로 개발자 정의 소스를 추가하세요%>
