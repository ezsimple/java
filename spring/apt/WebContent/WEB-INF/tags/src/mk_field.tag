<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag"  tagdir="/WEB-INF/tags/tag" %> 

<%@ attribute name="type" type="java.lang.String" description="콘트롤 타입"%>
<%@ attribute name="name" type="java.lang.String" description="필드명"%>
<%@ attribute name="maxlength" type="java.lang.String" description="입력길이"%>
<%@ attribute name="link" type="java.lang.String" description="link파리메터"%>
<%@ attribute name="link_type" type="java.lang.String" description="link타입"%>
<%@ attribute name="valid" type="java.lang.String" description="필드 정합성 체크 정보"%>
<%@ attribute name="valid2" type="java.lang.String" description="필드 정합성 체크 정보"%>
<%@ attribute name="keyValid" type="java.lang.String" description="필드 정합성 체크 정보"%>
<%@ attribute name="label" type="java.lang.String" description="필드명"%>
<%@ attribute name="values" type="java.util.Map" description="필드값"%>
<%@ attribute name="isSearch" type="java.lang.Boolean" required="true" description="검색필드"%>

<c:set var="valid" >${valid}${empty(valid2) ? '' : ',' }${valid2}</c:set>
<c:set var="emptyText" >${label} 입력</c:set>
<c:set var="fieldName" >${isSearch ? 'search_' : '' }${name }</c:set>
<c:set var="ctl">
	<c:choose>
		<c:when test="${type=='text_view'}">
			${values[fieldName]}
		</c:when>
		<c:when test="${type=='select_view' || type=='radio_view'}">
			<tag:code name="${fieldName }" groupId="${fieldName }" value="${fn:toLowerCase(values[fieldName]) }" />
		</c:when>
        <c:when test="${type=='code_view'}">
            <tag:code name="${fieldName }" groupId="${fieldName }" value="${fn:toUpperCase(values[fieldName]) }" />
        </c:when>
		<c:when test="${type=='datetime_view'}">
			<c:set var="name_fmt">${fieldName }@yyyy-MM-dd HH:mm:ss</c:set>
			${values[name_fmt]}
		</c:when>
		<c:when test="${type=='date_view'}">
			<c:set var="name_fmt">${fieldName }@yyyy-MM-dd</c:set>
			${values[name_fmt]}
		</c:when>
		<c:when test="${type=='number_view'}">
			<c:set var="name_fmt">${name }@#,##0</c:set>
			${values[name_fmt]}
		</c:when>
		<c:when test="${type=='read'}">
			<input type="text" name="${fieldName }" readonly="readonly" value="${values[fieldName]}">
		</c:when>
		<c:when test="${type=='text'}">
			${values[fieldName]}
		</c:when>
		<c:when test="${type=='input'}">
			<input type="text" name="${fieldName }" value="${values[fieldName]}">
		</c:when>
		<c:when test="${type=='password'}">
			<c:set var="idEditable" value="${true}"/>
			<input class="control" type="password" name="${fieldName }" value="" placeholder="${emptyText}" style="width: ${isSearch || maxlength<30 ? '150px' : '99%' };" maxlength="${maxlength }" valid="${valid }" key_press="${keyValid }" >
		</c:when>
		<c:when test="${type=='number'}">
			<c:set var="idEditable" value="${true}"/>
			${values[fieldName]}
		</c:when>
		<c:when test="${type=='date'}">
			<c:set var="idEditable" value="${true}"/>
			<c:set var="name_fmt">${fieldName }@yyyy-MM-dd</c:set>
			<input class="control datepicker" type="text" name="${fieldName }" value="${values[isSearch ? fieldName : name_fmt]}" } />
		</c:when>
		<c:when test="${type=='textarea'}">
			<c:set var="idEditable" value="${true}"/>
			<textarea class="control" name="${fieldName }" style="width: 98%;height: 150px;" maxlength="${maxlength }" valid="${valid }" key_press="${keyValid }"} >${values[fieldName]}</textarea>
		</c:when>
		<c:when test="${type=='file' || type=='file_img'}">
			<c:set var="idEditable" value="${true}"/>
			<tag:file className="control" name="${fieldName }"  value="${values[fieldName]}" type="${type }" style="width: 99%;"/>
		</c:when>
		<c:when test="${type=='files' || type=='files_img'}">
			<c:set var="idEditable" value="${true}"/>
			<tag:files className="control" name="${fieldName }"  value="${values[fieldName]}" type="${type }" style="width: 99%;"/>
		</c:when>
		<c:when test="${type=='select'}">
			<c:set var="idEditable" value="${true}"/>
			<tag:select name="${fieldName }" groupId="${name }" emptyText="${label } 선택" selected="${values[fieldName]}" isSearch="${isSearch }" className="control" valid="${valid }" />
		</c:when>
		<c:when test="${type=='check'}">
			<c:set var="idEditable" value="${true}"/>
			&lt;tag:check name="${fieldName }" groupId="${name }" checked="${values[fieldName]}" valid="${valid }"/>
		</c:when>
		<c:when test="${type=='radio'}">
			<c:set var="idEditable" value="${true}"/>
			<tag:radio name="${fieldName }" groupId="${name }" checked="${values[fieldName]}" isSearch="${isSearch }" valid="${valid }"/>
		</c:when>
		<c:when test="${type=='button'}">
			<span class="button">${label }</span>
		</c:when>
		<c:when test="${type=='del'}">
			<div style="clear:both; margin: 2px; text-align: center; " class="${values[fieldName]}">
				<input type="hidden" name="del_${fieldName }" id="${values[fieldName]}" value="0">
				<div style="cursor: pointer;" title="삭제"  onclick="delField('${values[fieldName]}')">
					<img src="../images/icon/close.png">
				</div>
			</div>
		</c:when>
		<c:when test="${type=='user_group'}">
			<c:set var="user_group">${values[fieldName] }</c:set>
			<c:choose>
				<c:when test="${user_group == 'U'}">일반사용자</c:when>
				<c:when test="${user_group == 'A'}">관리자</c:when>
				<c:when test="${user_group == 'S'}">시스템관리자</c:when>
				<c:otherwise>누구냐? 넌</c:otherwise>
			</c:choose>	
		</c:when>
		<c:otherwise>
			<c:set var="idEditable" value="${true}"/>
			<input class="control" type="${type }" name="${fieldName }" value="${values[fieldName]}" maxlength="${maxlength }" key_press="${keyValid }"} >
		</c:otherwise>
	</c:choose>
</c:set>

<c:choose>
	<c:when test="${!empty(link)}">
		<c:set var="link_value">onclick="${link_type }(this, <tag:el source="${link }" param="${values }"/>)"</c:set>
		<c:set var="link_class" value="link"/>
		<a ${link_value} class="editable editable-click" style="cursor:pointer;">${ctl }</a>
	</c:when>
	<c:otherwise>
		${ctl }
	</c:otherwise>
</c:choose>
