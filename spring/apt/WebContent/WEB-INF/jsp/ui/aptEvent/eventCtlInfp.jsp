<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/tag"%>
<%@ taglib prefix="src" tagdir="/WEB-INF/tags/src"%>
<c:set var="uuid" value="${sp:uuid()}"/>
<c:set var="def_rows" value="${UI_RESULT['list']}"/>
<c:set var="rows" value="${UI_RESULT.list}"/>
<%// <tag:el source="기기제어 정보(${'$'}{event_id})" param="${def_rows[0]}"/>%>

<form id="form_" action="" method="post" enctype="multipart/form-data">
	<input type="hidden" name="ui_id" value="eventCtlInfp">
	<input type="hidden" name="action_type" value="">
	<table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
		<tr>
<!-- 			<th>고객번호</th> -->
			<th>검침기No</th>
			<th>제어값</th>
			<th>연결기기</th>
			<th>동</th>
			<th>호</th>
		</tr>
		<c:forEach var="row" items="${rows }" varStatus="status">
			<tr>
<!-- 				<td> -->
<%-- 					<src:mk_field name="cu_id" values="${row }" isSearch="false" type="number" link=""  link_type="linkLoad" valid="notempty" valid2=""  keyValid="numeric" maxlength="11" label="고객번호" /> --%>
<!-- 				</td> -->
				<td>
					<src:mk_field name="device_id" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="30" label="검침기No" />
				</td>
				<td>
					<src:mk_field name="ctl_value" values="${row }" isSearch="false" type="select_view" link=""  link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="1" label="제어값" />
				</td>
				<td>
					<src:mk_field name="device_cd" values="${row }" isSearch="false" type="select_view" link=""  link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="10" label="연결기기" />
				</td>
				<td>
					<src:mk_field name="dong" values="${row }" isSearch="false" type="number_view" link=""  link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="6" label="동" />
				</td>
				<td>
					<src:mk_field name="ho" values="${row }" isSearch="false" type="number_view" link=""  link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="6" label="호" />
				</td>
			</tr>
		</c:forEach>
	</table>
</form>
<script type="text/javascript">
	// Set Modal Title
	var _modal_title = "기기제어 정보";
</script>