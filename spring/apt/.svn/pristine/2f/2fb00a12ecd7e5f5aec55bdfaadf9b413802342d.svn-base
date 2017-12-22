<%@tag import="org.apache.poi.util.SystemOutLogger"%>
<%@tag import="java.util.Map"%>
<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag"  tagdir="/WEB-INF/tags/tag" %> 
<%@ taglib prefix="src"  tagdir="/WEB-INF/tags/src" %> 
<%@ attribute name="uiId" type="java.lang.String" description="UI ID"%>
<%@ attribute name="type" required="true" type="java.lang.String" description="bf=버튼&폼, f=폼, nf=폼없음, t=테이블, trh=헤더포함tr단위, tr=tr단위"%>
<%@ attribute name="ui_type" required="false" type="java.lang.String" description="uiType을 동적으로 설정한다. unuse,use,tree,chart_.."%>
<c:set var="UI_ID" value="${empty(uiId) ? UI_ID : uiId}"/>

<sp:sp var="ui_info" queryPath="ui" action="design" processorList="mybatis" exception="false">{ui_id:'${UI_ID}'}</sp:sp>
<c:set scope="request" var="ui_design" value="${ui.UI_DESIGN }"/>
<c:set scope="request" var="ui_field" value="${sp:str2jsonObj(ui.UI_FIELD) }"/>

<sp:sp var="UI_RESULT" queryPath="${fn:substringBefore(ui.query_path,'.') }" action="${fn:substringAfter(ui.query_path,'.' ) }" processorList="mybatis" exception="true"/>
<c:set var="_$">${'$'}{'$'}</c:set>

<%//소스생성 %>
<c:forEach var="map" items="${UI_RESULT }">
	<c:set var="use_set" value="use_${map.key}"/>
	
	<c:if test="${map.key!='success' && map.key!='JSON'}">
		<c:set scope="request" var="title" value=""/>
		<c:set scope="request" var="search_html" value=""/>
		<c:set var="isList" value="${sp:isType(map.value,'list') }"/>
		<c:set var="uiType" value="${empty(ui_type) ? ui_field[use_set] : ui_type }"/>
		<c:set var="src"><%// 타입별 ui생성 %>${'<%'}%>
			<c:choose>
				<c:when test="${uiType=='unuse'}">
					<src:mk_paging rcd_key="${map.key }" rows="${map.value }"/>
				</c:when>
				<c:when test="${uiType=='tree'}"><%//TREE인 경우 %>
					<c:set var="default_key" value="${map.key }"/>
					<c:set var="ui_title">${'<'}tag:el source="${fn:replace(ui.UI_TITLE, '$', _$)}" param="${'$'}{def_rows[0]}"/></c:set>
					<c:set var="add_param">${'<'}tag:el source="${fn:replace(ui.ADD_PARAM, '$', _$)}" param="${'$'}{def_rows[0]}"/></c:set>
					<src:mk_tree rows="${map.value }"/>
				</c:when>
				<c:when test="${isList}"><%//리스트인 경우 %>
					<c:set var="default_key" value="${map.key }"/>
					<c:set var="ui_title">${'<'}tag:el source="${fn:replace(ui.UI_TITLE, '$', _$)}" param="${'$'}{def_rows[0]}"/></c:set>
					<c:set var="add_param">${'<'}tag:el source="${fn:replace(ui.ADD_PARAM, '$', _$)}" param="${'$'}{def_rows[0]}"/></c:set>
					<src:mk_list rcd_key="${map.key }" rows="${map.value}"/>
				</c:when>
				<c:otherwise><%//상세페이지인 경우%>
					<c:set var="default_key" value="${map.key }"/>
					<c:set var="ui_title">${'<'}tag:el source="${fn:replace(ui.UI_TITLE, '$', _$)}" param="${'$'}{def_rows}"/></c:set>
					<c:set var="add_param">${'<'}tag:el source="${fn:replace(ui.ADD_PARAM, '$', _$)}" param="${'$'}{def_rows}"/></c:set>
					<src:mk_view rcd_key="${map.key }" rows="${map.value}"/>
				</c:otherwise>
			</c:choose>
		</c:set>

		<c:if test="${fn:startsWith(uiType,'chart_')}"><%//그래프인 경우 그래프 생성(그래프는 그래프와 표를 둘다 생성한다.)%>
			<c:set var="graphSrc">
				<c:set var="type">etc</c:set>
				<c:set var="ui_title">${'<'}tag:el source="${fn:replace(ui.UI_TITLE, '$', _$)}" param="${'$'}{rows[0]}"/></c:set>
				
				<src:mk_chart chartType="${uiType}" rows="${map.value }" ui_id="${UI_ID}"/>
			</c:set>
		</c:if>
	
		<c:set var="html">
			${html }
<!-- // -->
${'<'}%//------------------------------------------------------------------------
//                     현재 레코드 설정                                     
//------------------------------------------------------------------------%>

${'<'}c:set var="rows" value="${'$'}{UI_RESULT.${map.key}}"/>

${'<'}%//------------------------------------------------------------------------%>
			<c:choose>
				<c:when test="${uiType=='tree'}"><%//TREE인 경우 %>
					${src }
				</c:when>
				<c:when test="${fn:startsWith(uiType,'chart_')}"><%//그래프 경우 %>
					${graphSrc }
					<div class="sheet" style="margin: 10px auto; display: none;">
						${search_html }
						<table class="${isList ? 'lst' : 'vw' }" border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 10px;">
							${title }
							${src }
						</table>
					</div>
				</c:when>
				<c:when test="${uiType!='unuse'}">
					${search_html }
					<div class="detail_info">
					<table id="default_info" class="${isList ? 'lst' : 'vw' }" border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 10px;">
						${title }
						${src }
					</table>
					</div>
				</c:when>
			</c:choose>
			${'<' }c:if test="${'$' }{isDev}">
				<div style="position: absolute;">${UI_ID }</div>
			${'<' }/c:if>

		</c:set>
	</c:if>
</c:forEach>

<c:set var="button_Html">
	<div class="button_groups" style="clear: both; height: 25px; margin-top: 10px;padding:3px; ">
		<c:if test="${!empty(ui.add_param) }">
			<div class="button f_r m_5" icon_primary="ui-icon-plus" type="add" onclick="${ui.add_type}(this,${add_param})">등록</div>
		</c:if>
		<c:forEach var="btn" items="${fn:split(ui.use_btn,',') }">
			<c:choose>
				<c:when test="${btn=='L' }">
					<div class="button f_r m_5" icon_primary="ui-icon-circle-close" style=" " onclick="closePop(this)">닫기</div>
				</c:when>
				<c:when test="${btn=='C' }">
					<div class="cancel_btn button f_r m_5" icon_primary="ui-icon-locked" style=" display: none;" onclick="cancel(this)">취소</div>
				</c:when>
				
				<c:when test="${btn=='U' }">
					<div class="edit_btn button f_r m_5" icon_primary="ui-icon-unlocked" style="" onclick="edit(this)">수정</div>
				</c:when>
				<c:when test="${btn=='D' }">
					<div class="del_btn button f_r m_5" icon_primary="ui-icon-trash" style=" display: none;" onclick="del(this)">삭제</div>
				</c:when>
				<c:when test="${btn=='S' }">
					<div class="save_btn button f_r m_5" icon_primary="ui-icon-disk" style=" display: none;" onclick="form_submit(this)">저장</div>
				</c:when>
			</c:choose>
		</c:forEach>
	</div>
</c:set>

<c:set var="script">
	<script type="text/javascript">
		$(function() {	
			setTitle('#${'$'}{uuid}');
		});
	</script> 
</c:set>

<%-- 페이지 출력 --%>
<c:set var="jsp_source">
${'<' }c:set var="uuid" value="${'$'}{sp:uuid()}"/>
${'<'}%//------------------------------------------------------------------------
//                     기본 레코드 설정                                     
//------------------------------------------------------------------------%>

${'<'}c:set var="def_rows" value="${'$'}{UI_RESULT['${default_key}']}"/>

${'<'}%//------------------------------------------------------------------------%>
	<span id="${'$'}{uuid}" class="title" style="display:none;">${ui_title}</span>


	<div class="unit_page" type="page" >
		<form id="form_${page_id}" action="" method="post" enctype="multipart/form-data">
		
			<input type="hidden" name="ui_id" value="${UI_ID }">
			<input type="hidden" name="action_type" value="">
			${html }
		
			${paging }
		</form>
		${type=='bf' ? button_Html : '' }
		<iframe name="submit_frame" style="width: 0px; height: 0px; display: none;"></iframe>
		
	</div>
	${script }
</c:set>

${sp:makeJsp(UI_ID, fn:substringBefore(ui.query_path,'.'), jsp_source)}
