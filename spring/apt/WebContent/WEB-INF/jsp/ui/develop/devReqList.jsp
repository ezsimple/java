<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/tag"%>
<%@ taglib prefix="src" tagdir="/WEB-INF/tags/src"%>
<script type="text/javascript">
function submitCallback(form){
	closePop();
	var target = $('form');
	search(target);
}

$.valid_fnc["enddt"] = function(ctl, val, opt){

	if(val==''){
		return true;
	}
	if(val > $('input[name=exp_end_dt]').val().trim()){
		alert(getFieldName(ctl) + '이 종료일보다 클 수 없습니다.');
		ctl.focus();
		return false;
	}
	return true;
};
$.valid_fnc["schdt"] = $.valid_fnc["enddt"];

$(function() {	
	var reqTypes = $('[name=req_type]');
	for(var i=0;i<reqTypes.length;i++){
		var reqType = $(reqTypes[i]);
		reqType.closest('tr').find('[name=subject]').css({ 'text-decoration':'underline'});
	}
});

</script>
<%//이곳에 위로 개발자 정의 소스를 추가하세요%>
<%//[[START_AUTO_GENERATED_SRC]]%>
<c:set var="uuid" value="${sp:uuid()}"/>
<%//------------------------------------------------------------------------
//                     기본 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="def_rows" value="${UI_RESULT['rows']}"/>

<%//------------------------------------------------------------------------%>
	<span id="${uuid}" class="title" style="display:none;"><tag:el source="요구사항 리스트" param="${def_rows[0]}"/></span>


	<div class="unit_page" type="page" >
		<form id="form_" action="" method="post" enctype="multipart/form-data">
		
			<input type="hidden" name="ui_id" value="devReqList">
			<input type="hidden" name="action_type" value="">
			<!-- // -->
<%//------------------------------------------------------------------------
//                     현재 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="rows" value="${UI_RESULT.rows}"/>

<%//------------------------------------------------------------------------%>
			<div style="border:1px solid #cccccc;margin-bottom: 3px;">
		<table style=" padding : 5px;"><tr>
			<td width="*"><div class="f_l">
					<table><tr>
						<th style="white-space:nowrap; ">종류 :</th>
						<td>
							<src:mk_field name="req_type" values="${req }" isSearch="true" type="radio" valid=""  keyValid="" maxlength="" label="종류" />&nbsp;
							${isMobile ? '' : ''}
						</td>
					</tr></table>
				</div><div class="f_l">
					<table><tr>
						<th style="white-space:nowrap; ">제목 :</th>
						<td>
							<src:mk_field name="subject" values="${req }" isSearch="true" type="text" valid=""  keyValid="" maxlength="" label="제목" />&nbsp;
							${isMobile ? '' : ''}
						</td>
					</tr></table>
				</div><div class="f_l">
					<table><tr>
						<th style="white-space:nowrap; ">상태 :</th>
						<td>
							<src:mk_field name="req_status" values="${req }" isSearch="true" type="radio" valid=""  keyValid="" maxlength="" label="상태" />&nbsp;
							${isMobile ? '' : ''}
						</td>
					</tr></table>
				</div></td><td><div  class="button" icon_primary="ui-icon-search" text="true" onclick="search(this)">검색</div></td>
		</tr></table>
		</div><table class="lst" border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 10px;">
						<tr><th class="" style="" label="req_id" width="40">번호</th><th class="hide_mb" style="" label="req_type" width="40">종류</th><th class="" style="" label="subject" width="*">제목</th><th class="hide_mb" style="" label="sch_end_dt" width="80">완료예정일</th><th class="hide_mb" style="" label="exp_end_dt" width="80">종료일</th><th class="" style="" label="req_status" width="40">상태</th><th class="hide_mb" style="" label="req_name" width="80">요청자</th><th class="hide_mb" style="" label="developer_id" width="80">개발담당자</th><th class="" style="" label="attach" width="40">첨부</th></tr><%%>
			<c:forEach var="row" items="${rows }" varStatus="status">
	<tr class="row_${status.index + 1}">
		<td class="" style="" align="right">
							<src:mk_field name="req_id" values="${row }" isSearch="false" type="number_view" link=""  link_type="linkLoad" valid=""  keyValid="" maxlength="11" label="번호" /> 
						</td>
					<td class="hide_mb" style="" align="center">
							<src:mk_field name="req_type" values="${row }" isSearch="false" type="radio_view" link=""  link_type="linkLoad" valid=""  keyValid="" maxlength="10" label="종류" /> 
						</td>
					<td class="" style="" align="">
							<src:mk_field name="subject" values="${row }" isSearch="false" type="text_view" link="'devReqView',{req_id:'${'$'}{req_id}'},1"  link_type="linkLoad" valid=""  keyValid="" maxlength="200" label="제목" /> 
						</td>
					<td class="hide_mb" style="" align="center">
							<src:mk_field name="sch_end_dt" values="${row }" isSearch="false" type="date_view" link=""  link_type="linkLoad" valid=""  keyValid="" maxlength="19" label="완료예정일" /> 
						</td>
					<td class="hide_mb" style="" align="center">
							<src:mk_field name="exp_end_dt" values="${row }" isSearch="false" type="date_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="19" label="종료일" /> 
						</td>
					<td class="" style="" align="center">
							<src:mk_field name="req_status" values="${row }" isSearch="false" type="radio_view" link=""  link_type="linkLoad" valid=""  keyValid="" maxlength="10" label="상태" /> 
						</td>
					<td class="hide_mb" style="" align="">
							<src:mk_field name="req_name" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="50" label="요청자" /> 
						</td>
					<td class="hide_mb" style="" align="">
							<src:mk_field name="developer_id" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="20" label="개발담당자" /> 
						</td>
					<td class="" style="" align="right">
							<src:mk_field name="attach" values="${row }" isSearch="false" type="number_view" link=""  link_type="linkLoad" valid="notempty"  keyValid="" maxlength="21" label="첨부" /> 
						</td>
					</tr>
</c:forEach></table><!-- // -->
<%//------------------------------------------------------------------------
//                     현재 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="rows" value="${UI_RESULT.cnt}"/>

<%//------------------------------------------------------------------------%></form>
		<div style="clear: both; height: 25px; margin-top: 10px;padding:3px; ">
		<div class="button f_r m_5" icon_primary="ui-icon-plus" type="add" onclick="linkPopup(this,<tag:el source="'devReqEdit',{}" param="${def_rows[0]}"/>)">등록</div>
		</div><iframe name="submit_frame" style="width: 0px; height: 0px; display: none;"></iframe>
		
	</div>
	<script type="text/javascript">
		$(function() {	
			setTitle('#${uuid}');
		});
	</script>
<%//[[END_AUTO_GENERATED_SRC]]1245177200[[HASHCODE]]%>
<%//이곳에 아래로 개발자 정의 소스를 추가하세요%>
