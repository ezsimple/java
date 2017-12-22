<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ attribute name="name" type="java.lang.String" required="true"%>
<%@ attribute name="checked" type="java.lang.String" %>
<%@ attribute name="className" type="java.lang.String"%>
<%@ attribute name="valid" type="java.lang.String" %>
<%@ attribute name="codes" type="java.lang.String" required="true" description=" =선택, id1=val1 , id2=val2 ...(공백은 앞뒤 공백은 제거됨, 길이가 0인 값을 넣고 싶으면 공백을 넣으세요.)"%>
<%@ attribute name="secederRecord" type="java.lang.String" description="레코드 분리자 기본값 (,)"%>
<%@ attribute name="secederField" type="java.lang.String" description="필드 분리자 기본값 (=)"%>
<c:set var="secederR" value="${empty(secederRecord) ? ',' : secederRecord}" />
<c:set var="secederF" value="${empty(secederField) ? '=' : secederField}" />
<c:set var="codeList" value="${fn:split(codes, secederR)}" />
<div  title="${title }"  class="${className }">
<c:if test="${!empty(valid)}">
	<c:set var="valid">valid="${valid }"</c:set>
</c:if>
<c:forEach var="row" items="${codeList}"> 
	<c:set var="code" value="${fn:split(row, secederF)}" />
	<c:set var="val" value="${fn:trim(code[0])}" />
	<span style="white-space : nowrap; ">
		<input name="${name}" id="${name}_${val }"  type="radio" value="${val }" ${att } ${checked == val ? 'checked' : ''} valid="${valid }" style="display:inline;margin:0px; height: 20px; vertical-align: middle; "  ${attr } ><label style="height: 20px;display:inline;vertical-align: middle; "  for="${name}_${val }">${fn:trim(code[1])}&nbsp;&nbsp;</label>
	</span>
</c:forEach>
</div>