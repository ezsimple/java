<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>목록보기</title>
    <link href="<c:url value='/resources/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/resources/bootstrap/css/bootstrap-theme.min.css'/>" rel="stylesheet">
<style type="text/css">
/* Reset the container */
.container {
	max-width: none !important;
	width: 970px;
}
</style>
	<script src="<c:url value='/resources/js/jquery/jquery-1.4.2.min.js'/>"></script>
	<script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js'/>"></script>
  </head>
  <body>
    <div class="container">
		<table class="table table-bordered table-striped table-hover">
			<colgroup>
				<col width="40"/>
				<col width="100"/>
				<col width="150"/>
				<col width=""/>
				<col width="60"/>
			</colgroup>
			<thead>
				<tr>
					<th width="30">번호</th>
					<th>ID</th>
					<th>제목</th>
					<th>내용</th>
					<th>등록자</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>SAMPLE-00001</td>
					<td>제목</td>
					<td>내용</td>
					<td>등록자</td>
				</tr>
				<tr>
					<td>2</td>
					<td>SAMPLE-00002</td>
					<td>제목</td>
					<td>내용</td>
					<td>등록자</td>
				</tr>
			</tbody>
		</table>
		<a class="btn btn-lg btn-success" href="#">등록</a>
    </div><!-- /#container -->
  </body>
</html>
