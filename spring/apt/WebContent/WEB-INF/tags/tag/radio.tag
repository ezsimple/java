<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag"  tagdir="/WEB-INF/tags/tag" %> 
<%@ taglib prefix="src"  tagdir="/WEB-INF/tags/src" %> 
<%@ attribute name="name" type="java.lang.String" required="true"%>
<%@ attribute name="groupId" type="java.lang.String" required="true"%>
<%@ attribute name="checked" type="java.lang.String" %>
<%@ attribute name="className" type="java.lang.String"%>
<%@ attribute name="style" type="java.lang.String" %>
<%@ attribute name="valid" type="java.lang.String" %>
<%@ attribute name="attr" type="java.lang.String" %>
<%@ attribute name="isSearch" type="java.lang.Boolean" required="true" description="검색필드"%>
<c:if test="${empty(_code) }">
	<sp:sp var="CODE_RESULT" queryPath="ui" action="codeList" processorList="mybatis" exception="false"/>
	<tag:list2group var="_code" group_field="group_id" list="${rows }"/>
</c:if>
<c:set var="id" value="${sp:uuid() }"/>
<radio  class="${className }" >
	<c:forEach var="row" items="${_code[groupId]}">
		<c:if test="${isSearch || row.use_cd!='S' }">
			<c:set var="style">style="height: 20px;display:inline;vertical-align: middle;</c:set>
			<c:set var="displyInfo">${fn:contains(row.display_info,'style=') ? fn:replace(fn:replace(row.display_info,'&#034;','"'),'style="', style) : sp:concat(style, '"')}</c:set>
			<input name="${name}" id="${id}_${row.code_value }"  type="radio" value="${row.code_value }" ${checked == row.code_value ? ' checked ' : ''}  valid="${valid }" ${att } style="display:inline;margin:0px; height: 20px; vertical-align: middle;"  reference_value="${row.reference_value}" ><label ${displyInfo } for="${id}_${row.code_value }">${row.code_name}&nbsp;</label>
		</c:if>
	</c:forEach>
</radio>