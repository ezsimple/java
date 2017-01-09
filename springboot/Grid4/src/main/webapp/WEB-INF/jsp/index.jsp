<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<tag:env/>
<title>WelCome to Grid4</title>
</head>
<body>
<div><c:out value="${currentUser }"/></div>
<div><a href="user/login.do">사용자 로그인</a></div>
<div><a href="user/logout.do">사용자 로그아웃</a></div>
<div><a href="user/register.do">사용자 등록</a></div>
<div><a href="user/list.do">사용자 목록</a></div>
<div><a href="admin/adminlte.do">어드민 템플릿</a></div>
</body>
</html>