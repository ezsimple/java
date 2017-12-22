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
<c:set var="infos" value="${sp:sortField(__META__UI_RESULT[rcd_key], ui_field['field_names_all']) }"/>
<c:set var="_$">${'$'}{'$'}</c:set>

<c:forEach var="info" items="${infos}">
	<c:set var="type">${info.key}_type</c:set>
	<c:if test="${ui_field[type]=='total_record'}">
		<c:if test="${empty(paging) }">
			<c:set scope="request" var="paging">
				${'<' }src:paging totCount="${'$'}{rows['${info.key}']}" _start="${'$'}{rows['_start'] }" rows="${'$'}{rows['_rows']}"/>
			</c:set>
		</c:if>
	</c:if>
</c:forEach>
