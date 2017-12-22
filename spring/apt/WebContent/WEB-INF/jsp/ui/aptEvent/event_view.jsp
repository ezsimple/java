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
<%--<c:out value="${rows }"/>--%>

<form id="form_" action="" method="post" enctype="multipart/form-data">
	<input type="hidden" name="ui_id" value="event_view">
	<input type="hidden" name="action_type" value="">
	<table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
		<tr>
			<th>이벤트ID</th>
			<td><src:mk_field name="event_id" isSearch="false" values="${rows}" type="text_view"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="30" label="이벤트ID"/></td>
		</tr>
		<tr>
			<th>생성일</th>
			<td><src:mk_field name="created_date" isSearch="false" values="${rows}" type="datetime_view"  link="" link_type="linkLoad" valid="notempty,date" valid2=""  keyValid="" maxlength="19" label="생성일"/></td>
		</tr>
		<tr>
            <th>이벤트일시</th>
            <td><src:mk_field name="start_date" isSearch="false" values="${rows}" type="datetime_view"  link="" link_type="linkLoad" valid="notempty,date" valid2=""  keyValid="" maxlength="19" label="이벤트일시"/></td>
		</tr>
		<tr>
            <th>지속시간(분)</th>
            <td><src:mk_field name="tot_duration" isSearch="false" values="${rows}" type="number_view"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="numeric" maxlength="11" label="지속시간(분)"/></td>
		</tr>
		<tr>
            <th label="tot_reduction">감축량</th>
            <td><src:mk_field name="tot_reduction" isSearch="false" values="${rows}" type="number_view"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="numeric" maxlength="11" label="감축량"/></td>
		</tr>
		<tr>
            <th label="event_status">상태</th>
            <td><src:mk_field name="event_status" isSearch="false" values="${rows}" type="select_view"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="10" label="상태"/></td>
		</tr>
		<tr>
            <th label="test_event">테스트</th>
            <td><src:mk_field name="test_event" isSearch="false" values="${rows}" type="select_view"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="1" label="테스트"/></td>
		</tr>
		<tr>
            <th label="signal_name">Signal Name</th>
            <td><src:mk_field name="signal_name" isSearch="false" values="${rows}" type="text_view"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="30" label="Signal Name"/></td>
		</tr>
		<tr>
            <th label="signal_type">signal_type</th>
            <td><src:mk_field name="signal_type" isSearch="false" values="${rows}" type="text_view"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="10" label="signal_type"/></td>
		</tr>
		<tr>
            <th label="signal_id">Signal Id</th>
            <td><src:mk_field name="signal_id" isSearch="false" values="${rows}" type="text_view"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="30" label="Signal Id"/></td>
		</tr>
		<tr>
            <th label="meter_ctl_snd_date">제어신호전송일</th>
            <td><src:mk_field name="meter_ctl_snd_date" isSearch="false" values="${rows}" type="text"  link="" link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="19" label="제어신호전송일"/></td>
		</tr>
		<tr>
            <th label="file_id">파일아이디</th>
            <td><src:mk_field name="file_id" isSearch="false" values="${rows}" type="text_view"  link="dwnLoad('${'$'}{file_id}')" link_type="linkFnc" valid="notempty" valid2=""  keyValid="" maxlength="40" label="파일아이디"/></td>
		</tr>
	</table>
</form>
