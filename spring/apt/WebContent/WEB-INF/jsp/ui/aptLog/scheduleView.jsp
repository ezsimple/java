<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/tag"%>
<%@ taglib prefix="src" tagdir="/WEB-INF/tags/src"%>
<script type="text/javascript">
function dwnLoad(id){
	document.location.href = '../dl.sh?file_id='+id;
}
</script>
<c:set var="uuid" value="${sp:uuid()}"/>
<c:set var="def_rows" value="${UI_RESULT['row']}"/>
<tag:el source="" param="${def_rows}"/>
<c:set var="rows" value="${UI_RESULT.row}"/>

<form id="form_" action="" method="post" enctype="multipart/form-data">
	<input type="hidden" name="ui_id" value="scheduleView">
	<input type="hidden" name="action_type" value="">
	<table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tr>
			<th>스케줄아이디</th>
			<td><src:mk_field name="scd_id" isSearch="false" values="${rows}" type="text_view"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="20" label="스케줄아이디"/></td>
		</tr>
		<tr>
			<th>스케줄명</th>
			<td><src:mk_field name="scd_name" isSearch="false" values="${rows}" type="text_view"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="100" label="스케줄명"/></td>
		</tr>
		<tr>
			<th>실행서버IP</th>
			<td><src:mk_field name="scd_run_ip" isSearch="false" values="${rows}" type="text"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="20" label="실행서버IP"/></td>
		</tr>
		<tr>
			<th>설명</th>
			<td><src:mk_field name="dsc" isSearch="false" values="${rows}" type="text_view"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="200" label="설명"/></td>
		</tr>
		<tr>
			<th>사용여부</th>
			<td><src:mk_field name="use_yn" isSearch="false" values="${rows}" type="text"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="1" label="사용여부"/></td>
		</tr>
		<tr>
			<th>작업 시작일시</th>
			<td><src:mk_field name="scd_str_dt" isSearch="false" values="${rows}" type="datetime_view"  link="" link_type="linkLoad" valid="notempty,date" valid2=""  keyValid="" maxlength="19" label="작업 시작일시"/></td>
		</tr>
		<tr>
			<th>작업 종료일시</th>
			<td><src:mk_field name="scd_end_dt" isSearch="false" values="${rows}" type="datetime_view"  link="" link_type="linkLoad" valid="notempty,date" valid2=""  keyValid="" maxlength="19" label="작업 종료일시"/></td>
		</tr>
<!-- 		<tr> -->
<!-- 			<th>에러발생 일시</th> -->
<%-- 			<td><src:mk_field name="err_dt" isSearch="false" values="${rows}" type="datetime_view"  link="" link_type="linkLoad" valid="notempty,date" valid2=""  keyValid="" maxlength="19" label="에러발생 일시"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<th>오류메세지</th> -->
<%-- 			<td><src:mk_field name="err_msg" isSearch="false" values="${rows}" type="text_view"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="4000" label="오류메세지"/></td> --%>
<!-- 		</tr> -->
		<tr>
			<th>파일아이디</th>
			<td><src:mk_field name="file_id" isSearch="false" values="${rows}" type="text_view"  link="dwnLoad('${'$'}{file_id}')" link_type="linkFnc" valid="notempty" valid2=""  keyValid="" maxlength="40" label="파일아이디"/></td>
		</tr>
	</table>
</form>
<iframe name="submit_frame" style="width: 0px; height: 0px; display: none;"></iframe>
