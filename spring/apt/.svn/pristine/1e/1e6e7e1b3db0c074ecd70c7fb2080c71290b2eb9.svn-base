<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"%>
<%@ tag trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag"  tagdir="/WEB-INF/tags/tag" %> 
<%@ taglib prefix="src"  tagdir="/WEB-INF/tags/src" %> 
<%@ attribute name="rcd_key" required="true" type="java.lang.String" description="리스트 레코드(key value set))"%>
<%@ attribute name="rows" required="true" type="java.util.List" description="리스트 레코드(key value set))"%>

	
<c:set var="align" >{date:'center',date_view:'center',datetime_view:'center',select:'center',select_view:'center',check:'center',check_view:'center',radio:'center',radio_view:'center',code_view:'center',button:'center',number_view:'right'}</c:set>
<c:set var="align" value="${sp:str2jsonObj(align)}"/>
<c:set var="isStar" value="${false }"/>
<c:set var="infos" value="${sp:sortField(__META__UI_RESULT[rcd_key], ui_field['field_names_all']) }"/>

<%//컬럼폭 합계계산%>
<c:forEach var="info" items="${infos}"  >

	<c:set var="key">${info.key}</c:set>
	<c:set var="type">${ui_field[sp:concat(key, '_type')]}</c:set>
	
	<c:if test="${type!='unuse' }">
		<c:set var="width">${ui_field[sp:concat(key, '_width')]}</c:set>
		<c:if test="${type!='hidden' }">
			<c:set var="tot_width">${width=='*' ? tot_width : tot_width+width}</c:set>
		</c:if>
		<c:if test="${width=='*' }">
			<c:set var="isStar" value="${true }"/>
		</c:if>
	</c:if>
</c:forEach>
<c:set var="w_unit">${ui_field['w_unit']}</c:set>
<c:if test="${isStar}">
	<c:set var="tot_width" value="${tot_width + tot_width*0.1 }"/>
</c:if>
<c:set var="tot_width">${w_unit=='%' ? 100/tot_width : 1}</c:set>
<%//컬럼 타이틀 및 검색 조건 생성%>
<c:forEach var="info" items="${infos}" >
	<c:set var="key">${info.key}</c:set>
	<c:set var="type">${ui_field[sp:concat(key, '_type')]}</c:set>

	<c:set var="label">${ui_field[sp:concat(key, '_label')]}</c:set>
	<c:set var="valid">${ui_field[sp:concat(key, '_valid')]}</c:set>
	<c:set var="valid2">${ui_field[sp:concat(key, '_valid2')]}</c:set>
	<c:set var="width">${ui_field[sp:concat(key, '_width')]}</c:set>
	<c:set var="keyValid">${ui_field[sp:concat(key, '_key_valid')]}</c:set>
	<c:set var="hide">${ui_field[sp:concat(key, '_hide')]}</c:set>
	<c:set var="hide_web">${ui_field[sp:concat(key, '_hide_web')]}</c:set>
	<c:set var="search">${ui_field[sp:concat(key, '_search')]}</c:set>
	<%//컬럼 타이틀 생성%>
	<c:if test="${type!='unuse' }">
		<c:set scope="request" var="title">${title }<th class="${hide=='hide' ? 'hide_mb ' : '' } ${hide_web=='hide_web' ? 'hide_web' : '' }" style="${type=='hidden' ? 'display: none;' : ''}" label="${key}" width="${width=='*' ? '*' : width*tot_width }${width=='*' ? '' : w_unit}">${label }</th></c:set>
	</c:if>
	
	<%//조건 생성%>
	<c:if test="${search=='search' }">
		<c:set scope="request" var="search_html">
			${search_html }
			<div class="f_l">
				<table><tr>
					<th align="right" class="search_label" style="white-space:nowrap; ">${label } :</th>
					<td>
						<c:set var="fType">${fn:endsWith(type,'time_view') ? fn:substringBefore(type,'time_view') : type }</c:set>
						<c:set var="fType">${fn:endsWith(fType,'_view') ? fn:substringBefore(fType,'_view') : fType }</c:set>
						<c:set var="fType">${fType=='unuse' || fType=='hidden' ? 'text' : fType }</c:set>
						
						${'<' }src:mk_field name="${key }" values="${'$' }{req }" isSearch="true" type="${fType }" valid="${valid }" valid2="${valid2 }"  keyValid="${keyValid }" maxlength="${maxlength }" label="${label }" />
					</td>
				</tr></table>
			</div>
		</c:set>
		<c:set var="isSearch" value="${true }"/>
	</c:if>

</c:forEach>
<c:if test="${isSearch}">
	<c:set scope="request" var="search_html">
		<div style="border:1px solid #cccccc;margin-bottom: 3px;">
		<table style=" padding : 5px;"><tr>
			<td width="*">${search_html }</td><td><div  class="button" icon_primary="ui-icon-search" text="${!isMobile }" onclick="search(this)">검색</div></td>
		</tr></table>
		</div>
	</c:set>
</c:if>
<c:set var="_$">${'$'}{'$'}</c:set>
<%//목록 생성%>
${'<' }c:forEach var="row" items="${'$' }{rows }" varStatus="status">
	<tr class="row_${'$' }{status.index + 1}">
		<c:forEach var="info" items="${infos}">
			<c:set var="key">${info.key}</c:set>
			<c:set var="type">${ui_field[sp:concat(key, '_type')]}</c:set>
			
			<c:if test="${type!='unuse' }">
				<c:set var="label">${ui_field[sp:concat(key, '_label')]}</c:set>
				<c:set var="link">${fn:replace(ui_field[sp:concat(key, '_link')], '$', _$)}</c:set>
				<c:set var="link_type">${ui_field[sp:concat(key, '_link_type')]}</c:set>
				<c:set var="valid">${ui_field[sp:concat(key, '_valid')]}</c:set>
				<c:set var="valid2">${ui_field[sp:concat(key, '_valid2')]}</c:set>
				<c:set var="keyValid">${ui_field[sp:concat(key, '_key_valid')]}</c:set>
				<c:set var="width">${ui_field[sp:concat(key, '_width')]}</c:set>
				<c:set var="hide">${ui_field[sp:concat(key, '_hide')]}</c:set>
				<c:set var="hide_web">${ui_field[sp:concat(key, '_hide_web')]}</c:set>
				<c:set var="maxlength">${ui_field[sp:concat(key, '_maxlength')]}</c:set>
	
				<c:choose>
					<c:when test="${type!='total_record'}">
					<%//캐쉬처리%>
						<td class="${hide=='hide' ? 'hide_mb ' : '' } ${hide_web=='hide_web' ? 'hide_web' : '' }" style="${type=='hidden' ? 'display: none;' : ''}" align="${align[type] }">
							${'<' }src:mk_field name="${key }" values="${'$' }{row }" isSearch="false" type="${type }" link="${link }"  link_type="${link_type }" valid="${valid }" valid2="${valid2 }"  keyValid="${keyValid }" maxlength="${maxlength }" label="${label }" /> 
						</td>
					</c:when>
				</c:choose>
			</c:if>
		</c:forEach>
	</tr>
${'<' }/c:forEach>
<c:set  scope="request" var="title"><tr>${title }</tr></c:set>
