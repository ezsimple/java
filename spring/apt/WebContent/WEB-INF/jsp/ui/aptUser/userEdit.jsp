<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/tag"%>
<%@ taglib prefix="src" tagdir="/WEB-INF/tags/src"%>
<script type="text/javascript">
	function submitCallback(){
		document.location.href = '../-at-lo-pg/-userList.sh';
	}
</script>
<c:set var="uuid" value="${sp:uuid()}"/>
<c:set var="def_rows" value="${UI_RESULT['row']}"/>
<tag:el source="" param="${def_rows}"/>
<c:set var="rows" value="${UI_RESULT.row}"/>

<form class="smart-form" id="form_" action="" method="post" enctype="multipart/form-data">
	<input type="hidden" name="ui_id" value="userEdit">
	<input type="hidden" name="action_type" value="">
	<table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<tr>
			<th>UUID</th>
			<td>
				<label class="input">
					<c:set var="user_uuid">
                        <src:mk_field name="user_uuid" isSearch="false" values="${rows}" type="text_view" link="" link_type="linkLoad" valid="notempty" valid2="" keyValid="" maxlength="40" label="유저UUID"/>
                    </c:set>
                    ${user_uuid }
                    <input type="hidden" name="user_uuid" value="${user_uuid }"/>
				</label>
			</td>
		</tr>
		<tr>
			<th>사용자 아이디</th>
			<td><label class="input"><src:mk_field name="user_id" isSearch="false" values="${rows}" type="text_view" link="" link_type="linkLoad" valid="notempty" valid2="" keyValid="" maxlength="20" label="사용자 아이디"/></label></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><label class="input"><src:mk_field name="user_name" isSearch="false" values="${rows}" type="text_view" link="" link_type="linkLoad" valid="notempty" valid2="" keyValid="" maxlength="50" label="이름"/></label></td>
		</tr>
		<tr>
			<th>사용자구룹</th>
			<td><src:mk_field name="access_cls" isSearch="false" values="${rows}" type="radio" link="" link_type="linkLoad" valid="notempty" valid2="" keyValid="" maxlength="1" label="사용자구룹"/></td>
		</tr>
		<tr>
			<th>핸드폰</th>
			<td><label class="input"><src:mk_field name="user_mb" isSearch="false" values="${rows}" type="text_view" link="" link_type="linkLoad" valid="notempty" valid2="" keyValid="" maxlength="20" label="핸드폰"/></label></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><label class="input"><src:mk_field name="user_email" isSearch="false" values="${rows}" type="text_view" link="" link_type="linkLoad" valid="notempty" valid2="" keyValid="" maxlength="50" label="이메일"/></label></td>
		</tr>
	</table>
</form>
