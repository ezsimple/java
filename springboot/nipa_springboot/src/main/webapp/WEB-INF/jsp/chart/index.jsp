<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<c:set var="items" value="${test.list }"/>
<c:out value="${items }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시각화</title>
</head>
<body>

<table border="1">
<tr><th>UID</th><th>MSG</th></tr>
<c:forEach var="o" items="${items }" varStatus="sts">
	<tr><td>${o.uid }</td><td>${o.msg }</td></tr>
</c:forEach>
</table>

</body>
</html>