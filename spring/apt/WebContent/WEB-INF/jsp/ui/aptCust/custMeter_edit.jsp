<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/tag"%>
<%@ taglib prefix="src" tagdir="/WEB-INF/tags/src"%>

<c:set var="uuid" value="${sp:uuid()}"/>
<c:set var="def_rows" value="${UI_RESULT['row']}"/>
<tag:el source="" param="${def_rows}"/>
<c:set var="rows" value="${UI_RESULT.row}"/>

<form class="smart-form" id="form_" action="" method="post" enctype="multipart/form-data">

	<input type="hidden" name="ui_id" value="custMeter_edit">
	<input type="hidden" name="action_type" value="">

    <table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tr style="display: none;">
			<th>고객번호</th>
			<td><src:mk_field name="cu_id" isSearch="false" values="${rows}" type="hidden" link="" link_type="linkLoad" valid="notempty" valid2="" keyValid="numeric" maxlength="11" label="고객번호"/></td>
		</tr>
		<tr>
			<th>검침기No</th>
			<td>
				<label class="input">
				<src:mk_field name="device_id" isSearch="false" values="${rows}" type="read" link="" link_type="linkLoad" valid="notempty" valid2="" keyValid="" maxlength="30" label="검침기No"/>
				</label>
			</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<th>검침기종류</th> -->
<%-- 			<td><src:mk_field name="device_type" isSearch="false" values="${rows}" type="radio" link="" link_type="linkLoad" valid="notempty" valid2="" keyValid="" maxlength="10" label="검침기종류"/> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
		<tr>
			<th>device_ip</th>
			<td><src:mk_field name="device_ip" isSearch="false" values="${rows}" type="text_view" link="" link_type="linkLoad" valid="" valid2="" keyValid="" maxlength="30" label="device_ip"/></td> </tr>
		<tr>
			<th>연결기기</th>
			<td><src:mk_field name="device_cd" isSearch="false" values="${rows}" type="select_view" link="" link_type="linkLoad" valid="notempty" valid2="" keyValid="" maxlength="10" label="연결기기"/></td>
		</tr>
		<tr>
			<th>이벤트참여</th>
			<td><src:mk_field name="optin" isSearch="false" values="${rows}" type="radio" link="" link_type="linkLoad" valid="notempty" valid2="" keyValid="" maxlength="1" label="이벤트참여"/></td>
		</tr>
		<tr>
			<th>동</th>
			<td>
				<c:set var="dong">
					<src:mk_field name="dong" isSearch="false" values="${rows}" type="number" link="" link_type="linkLoad" valid="" valid2="" keyValid="numeric" maxlength="6" label="동"/>
				</c:set>
				<input class="form-control" id="spinner-dong" name="dong" value="${dong }"/>
			</td>
		</tr>
		<tr>
			<th>호</th>
			<td>
				<c:set var="ho">
					<src:mk_field name="ho" isSearch="false" values="${rows}" type="number" link="" link_type="linkLoad" valid="" valid2="" keyValid="numeric" maxlength="6" label="호"/>
				</c:set>
				<input class="form-control" id="spinner-ho" name="ho" value="${ho }"/>
			</td>
		</tr>
	</table>
</form>
<iframe name="submit_frame" style="width: 0px; height: 0px; display: none;"></iframe>

<script type="text/javascript">
	function submitCallback(){
		document.location.href = '../-at-lo-pg/-custMeter_list.sh';
	}
	$("#spinner-dong").spinner({
		min : 0,
		max : 10000,
		step : 1,
		numberFormat : "n"
	});
	$("#spinner-ho").spinner({
		min : 0,
		max : 10000,
		step : 1,
		numberFormat : "n"
	});
</script>
