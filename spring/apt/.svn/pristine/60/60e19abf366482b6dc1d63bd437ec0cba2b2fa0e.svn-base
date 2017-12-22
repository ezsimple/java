<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag"  tagdir="/WEB-INF/tags/tag" %> 
<%@ taglib prefix="src"  tagdir="/WEB-INF/tags/src" %> 
<%@ attribute name="rcd_key" required="true" type="java.lang.String" description="리스트 레코드(key value set))"%>
<%@ attribute name="rows" required="true" type="java.util.Map" description="리스트 레코드(key value set))"%>

<colgroup>
	<col width="${'$'}{isMobile ? '100' : '150' }">
	<col width="*">
</colgroup>
<%//데이타가 없는 경우 등록 화면으로 전환%>
<c:set var="isEdit" value="${fn:length(rows) > 0 ? '' : 'edit()'}"/>
<c:set var="infos" value="${sp:sortField(__META__UI_RESULT[rcd_key], ui_field['field_names_all']) }"/>
<c:set var="_$">${'$'}{'$'}</c:set>

<c:forEach var="info" items="${infos}" varStatus="status">
	<c:set var="label">${info.key}_label</c:set>
	<c:set var="type">${info.key}_type</c:set>
	<c:set var="link">${fn:replace(ui_field[sp:concat(info.key, '_link')], '$', _$)}</c:set>
	<c:set var="link_type">${info.key}_link_type</c:set>
	<c:set var="valid">${info.key}_valid[]</c:set>
	<c:set var="valid2">${info.key}_valid2[]</c:set>
	<c:set var="keyValid">${info.key}_key_valid</c:set>
	<c:set var="maxlength">${info.key}_maxlength</c:set>

	<c:choose>
		<c:when test="${ui_field[type]=='unuse'}">
		</c:when>
		<c:when test="${ui_field[type]=='group'}">
			</table>
			
			<h3 class="ui-state-default ui-sortable-handle" style=" margin-top: 0; margin-bottom: 0;padding: 3px;"><b>${rows[info.key] }</b></h3>
			
			<table class="vw" border="0" cellspacing="0" cellpadding="0"  style="margin-bottom: 10px;">
				<colgroup>
					<col width="${'$'}{isMobile ? '100' : '150' }">
					<col width="*">
				</colgroup>
		</c:when>
		<c:otherwise>
			<tr style="${ui_field[type]=='hidden' ? 'display: none;' : ''}">
				<th label="${info.key}"><span class="field_names" name="${info.key}">${ui_field[label] }</span></th>
				<td><span class="fields" name="${info.key}">${'<' }src:mk_field name="${info.key }" isSearch="false" values="${'$' }{rows}" type="${ui_field[type] }"  link="${link}" link_type="${ui_field[link_type] }" valid="${ui_field[valid] }" valid2="${ui_field[valid2] }"  keyValid="${ui_field[keyValid] }" maxlength="${ui_field[maxlength] }" label="${ui_field[label] }"/></span></td>
			</tr>
		</c:otherwise>
	</c:choose>
</c:forEach>
