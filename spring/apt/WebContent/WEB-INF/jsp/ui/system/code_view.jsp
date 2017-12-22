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
	<span id="${uuid}" class="title" style="display:none;"><tag:el source="코드정보" param="${def_rows}"/></span>


	<div class="unit_page" type="page" >
		<form id="form_" action="" method="post" enctype="multipart/form-data">
		
			<input type="hidden" name="ui_id" value="code_view">
			<input type="hidden" name="action_type" value="">
			<!-- // -->
<%//------------------------------------------------------------------------
//                     현재 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="rows" value="${UI_RESULT.row}"/>

<%//------------------------------------------------------------------------%>
			<div class="detail_info">
					<table id="default_info" class="vw" border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 10px;">
						<%%>
			<colgroup>
	<col width="${isMobile ? '100' : '150' }">
	<col width="*">
</colgroup>
<tr style="">
				<th label="rid"><span class="field_names" name="rid">RID</span></th>
				<td><span class="fields" name="rid"><src:mk_field name="rid" isSearch="false" values="${rows}" type="read"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="numeric" maxlength="10" label="RID"/></span></td>
			</tr>
		<tr style="">
				<th label="group_id"><span class="field_names" name="group_id">그룹코드</span></th>
				<td><span class="fields" name="group_id"><src:mk_field name="group_id" isSearch="false" values="${rows}" type="read"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="20" label="그룹코드"/></span></td>
			</tr>
		<tr style="">
				<th label="depth"><span class="field_names" name="depth">코드구분</span></th>
				<td><span class="fields" name="depth"><src:mk_field name="depth" isSearch="false" values="${rows}" type="select_view"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="10" label="코드구분"/></span></td>
			</tr>
		<tr style="">
				<th label="code_value"><span class="field_names" name="code_value">코드값</span></th>
				<td><span class="fields" name="code_value"><src:mk_field name="code_value" isSearch="false" values="${rows}" type="read"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="50" label="코드값"/></span></td>
			</tr>
		<tr style="">
				<th label="code_name"><span class="field_names" name="code_name">코드명</span></th>
				<td><span class="fields" name="code_name"><src:mk_field name="code_name" isSearch="false" values="${rows}" type="text"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="100" label="코드명"/></span></td>
			</tr>
		<tr style="">
				<th label="reference_value"><span class="field_names" name="reference_value">참조값</span></th>
				<td><span class="fields" name="reference_value"><src:mk_field name="reference_value" isSearch="false" values="${rows}" type="text"  link="" link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="200" label="참조값"/></span></td>
			</tr>
		<tr style="">
				<th label="order_no"><span class="field_names" name="order_no">노출순서</span></th>
				<td><span class="fields" name="order_no"><src:mk_field name="order_no" isSearch="false" values="${rows}" type="number"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="10" label="노출순서"/></span></td>
			</tr>
		<tr style="">
				<th label="display_info"><span class="field_names" name="display_info">디스플레이 정보</span></th>
				<td><span class="fields" name="display_info"><src:mk_field name="display_info" isSearch="false" values="${rows}" type="text"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="200" label="디스플레이 정보"/></span></td>
			</tr>
		<tr style="">
				<th label="use_cd"><span class="field_names" name="use_cd">사용구분</span></th>
				<td><span class="fields" name="use_cd"><src:mk_field name="use_cd" isSearch="false" values="${rows}" type="radio"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="1" label="사용구분"/></span></td>
			</tr>
		<tr style="">
				<th label="reg_dt"><span class="field_names" name="reg_dt">등록일</span></th>
				<td><span class="fields" name="reg_dt"><src:mk_field name="reg_dt" isSearch="false" values="${rows}" type="datetime_view"  link="" link_type="linkLoad" valid="date" valid2=""  keyValid="" maxlength="19" label="등록일"/></span></td>
			</tr>
		<tr style="">
				<th label="mod_dt"><span class="field_names" name="mod_dt">수정일시</span></th>
				<td><span class="fields" name="mod_dt"><src:mk_field name="mod_dt" isSearch="false" values="${rows}" type="datetime_view"  link="" link_type="linkLoad" valid="date" valid2=""  keyValid="" maxlength="19" label="수정일시"/></span></td>
			</tr>
		<tr style="">
				<th label="mod_user"><span class="field_names" name="mod_user">수정자</span></th>
				<td><span class="fields" name="mod_user"><src:mk_field name="mod_user" isSearch="false" values="${rows}" type="text_view"  link="" link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="20" label="수정자"/></span></td>
			</tr></table>
					</div>
				<c:if test="${isDev}">
				<div style="position: absolute;">code_view</div>
			</c:if></form>
		<div class="button_groups" style="clear: both; height: 25px; margin-top: 10px;padding:3px; ">
		<div class="cancel_btn button f_r m_5" icon_primary="ui-icon-locked" style=" display: none;" onclick="cancel(this)">취소</div>
				<div class="save_btn button f_r m_5" icon_primary="ui-icon-disk" style=" display: none;" onclick="form_submit(this)">저장</div>
				<div class="edit_btn button f_r m_5" icon_primary="ui-icon-unlocked" style="" onclick="edit(this)">수정</div>
				<div class="del_btn button f_r m_5" icon_primary="ui-icon-trash" style=" display: none;" onclick="del(this)">삭제</div>
				</div><iframe name="submit_frame" style="width: 0px; height: 0px; display: none;"></iframe>
		
	</div>
	<script type="text/javascript">
		$(function() {	
			setTitle('#${uuid}');
		});
	</script>
<%//[[END_AUTO_GENERATED_SRC]]-737065576[[HASHCODE]]%>
<%//이곳에 아래로 개발자 정의 소스를 추가하세요%>
