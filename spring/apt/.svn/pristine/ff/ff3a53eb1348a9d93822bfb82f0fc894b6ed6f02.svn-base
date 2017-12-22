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
	<span id="${uuid}" class="title" style="display:none;"><tag:el source="" param="${def_rows[0]}"/></span>


	<div class="unit_page" type="page" >
		<form id="form_" action="" method="post" enctype="multipart/form-data">
		
			<input type="hidden" name="ui_id" value="noti_list">
			<input type="hidden" name="action_type" value="">
			<!-- // -->
<%//------------------------------------------------------------------------
//                     현재 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="rows" value="${UI_RESULT.rows}"/>

<%//------------------------------------------------------------------------%>
			<table class="lst" border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 10px;">
						<tr><th class="" style="" label="notice_id" width="40">공지번호</th><th class="" style="" label="subject" width="*">제목</th><th class="" style="" label="noti_dt" width="160">noti_dt</th><th class="" style="" label="use_yn" width="40">사용여부</th><th class="" style="" label="qry_cnt" width="40">qry_cnt</th><th class="" style="" label="reg_id" width="100">등록자</th><th class="" style="" label="reg_dt" width="100">등록일</th><th class="" style="" label="chg_id" width="100">수정자</th><th class="" style="" label="chg_dt" width="100">수정일</th><th class="" style="" label="attach" width="100">attach</th></tr><%%>
			<c:forEach var="row" items="${rows }" varStatus="status">
	<tr class="row_${status.index + 1}">
		<td class="" style="" align="right">
							<src:mk_field name="notice_id" values="${row }" isSearch="false" type="number_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="numeric" maxlength="11" label="공지번호" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="subject" values="${row }" isSearch="false" type="label" link="'noti_view',{notice_id:${'$'}{notice_id}}"  link_type="linkPopup" valid="notempty"  keyValid="" maxlength="200" label="제목" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="noti_dt" values="${row }" isSearch="false" type="label" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="30" label="noti_dt" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="use_yn" values="${row }" isSearch="false" type="label" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="1" label="사용여부" /> 
						</td>
					<td class="" style="" align="right">
							<src:mk_field name="qry_cnt" values="${row }" isSearch="false" type="number_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="numeric" maxlength="11" label="qry_cnt" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="reg_id" values="${row }" isSearch="false" type="label" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="20" label="등록자" /> 
						</td>
					<td class="" style="" align="center">
							<src:mk_field name="reg_dt" values="${row }" isSearch="false" type="date_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="19" label="등록일" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="chg_id" values="${row }" isSearch="false" type="label" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="20" label="수정자" /> 
						</td>
					<td class="" style="" align="center">
							<src:mk_field name="chg_dt" values="${row }" isSearch="false" type="date_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="19" label="수정일" /> 
						</td>
					<td class="" style="" align="right">
							<src:mk_field name="attach" values="${row }" isSearch="false" type="number_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="numeric" maxlength="21" label="attach" /> 
						</td>
					</tr>
</c:forEach></table><!-- // -->
<%//------------------------------------------------------------------------
//                     현재 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="rows" value="${UI_RESULT.cnt}"/>

<%//------------------------------------------------------------------------%></form>
		<div style="clear: both; height: 25px; margin-top: 10px;padding:3px; ">
		<div class="button f_r m_5" icon_primary="ui-icon-plus" type="add" onclick="linkLoad(this,<tag:el source="'noti_view',{}" param="${def_rows[0]}"/>)">등록</div>
		</div><iframe name="submit_frame" style="width: 0px; height: 0px; display: none;"></iframe>
		
	</div>
	<script type="text/javascript">
		$(function() {	
			setTitle('#${uuid}');
		});
	</script>
<%//[[END_AUTO_GENERATED_SRC]]1986275377[[HASHCODE]]%>
<%//이곳에 아래로 개발자 정의 소스를 추가하세요%>
