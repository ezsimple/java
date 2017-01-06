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
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>전자정부 프레임워크 - 게시판 예제</title>

<!-- Core CSS - Include with every page -->
<link href="<c:url value='/resources/sb-admin-v2/css/bootstrap.min.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/sb-admin-v2/font-awesome/css/font-awesome.css'/>" rel="stylesheet">

<!-- Page-Level Plugin CSS - Tables -->
<link href="<c:url value='/resources/sb-admin-v2/css/plugins/dataTables/dataTables.bootstrap.css'/>" rel="stylesheet">

<!-- SB Admin CSS - Include with every page -->
<link href="<c:url value='/resources/sb-admin-v2/css/sb-admin.css'/>" rel="stylesheet">
<style type="text/css">
/* Reset the container */
.container {
	max-width: none !important;
	width: 970px;
}
</style>

</head>

<body>
	<div class="container">
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-heading">전자정부 프레임워크 (게시판)</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="table-responsive">
                            <form:form commandName="searchVO" name="listForm" method="post">
                              <input type="hidden" name="selectedId" />
                              <table class="table table-striped table-bordered table-hover" id="dataTables-example">
									  <colgroup>
									  	<col width="60" />
									  	<col width="120" />
									  	<col width="150" />
									  	<col width="" />
									  	<col width="100" />
									  	<col width="60" />
									  </colgroup>
                                  <thead>
                                      <tr>
                                          <th id="no">번호</th>
                                          <th>아이디</th>
                                          <th>제목</th>
                                          <th>내용</th>
                                          <th>등록자</th>
                                          <th>댓글</th>
                                      </tr>
                                  </thead>

                                  <tbody>
                                      <c:set var="total" value="${fn:length(resultList) }"/>
                                      <c:forEach var="result" items="${resultList}" varStatus="status">
                                      <tr onclick="javascript:fn_egov_select('<c:out value="${result.id}"/>')">
                                          <c:set var="replyCnt" value="0"/>
                                          <c:if test="${result.replyCnt>0}"><c:set var="replyCnt" value="${result.replyCnt }"/></c:if>
                                          <td><c:out value="${total - status.count + 1}"/></td>
                                          <td><c:out value="${result.id}"/></td>
                                          <td>
                                          ${fn:substring(result.name,0,15) }
                                          ${fn:length(result.name) > 15 ? "..." : "" }
                                          </td>
                                          <td>
                                          ${fn:substring(result.description,0,20) }
                                          ${fn:length(result.description) > 20 ? "..." : "" }
                                          </td>
                                          <td><c:out value="${result.regUser}"/></td>
                                          <td><c:out value="${replyCnt }"/></td>
                                      </tr>
                                      </c:forEach>

                                  </tbody>
                              </table>
                              <div class="text-center">
                              <button class="btn btn-success" type="button"
									onclick="javascript:fn_egov_addView();return false;">등록</button>
                              </div>
                            </form:form>
						</div>
						<!-- /.table-responsive -->
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->

	<!-- Core Scripts - Include with every page -->
    <script src="<c:url value='/resources/sb-admin-v2/js/jquery-1.10.2.js'/>"></script>
    <script src="<c:url value='/resources/sb-admin-v2/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/resources/sb-admin-v2/js/plugins/metisMenu/jquery.metisMenu.js'/>"></script>

	<!-- Page-Level Plugin Scripts - Tables -->
    <script src="<c:url value='/resources/sb-admin-v2/js/plugins/dataTables/jquery.dataTables.js'/>"></script>
    <script src="<c:url value='/resources/sb-admin-v2/js/plugins/dataTables/dataTables.bootstrap.js'/>"></script>

	<!-- SB Admin Scripts - Include with every page -->
    <script src="<c:url value='/resources/sb-admin-v2/js/sb-admin.js'/>"></script>

    <script type="text/javaScript" language="javascript" defer="defer">
    /* 글 수정 화면 function */
    function fn_egov_select(id) {
        document.listForm.selectedId.value = id;
        document.listForm.action = "<c:url value='/sample/egovSampleView.do'/>";
        document.listForm.submit();
    }
    /* 글 등록 화면 function */
    function fn_egov_addView() {
        document.listForm.action = "<c:url value='/sample/addSampleView.do'/>";
        document.listForm.submit();
    }

    /* 글 목록 화면 function */
    function fn_egov_selectList() {
        document.listForm.action = "<c:url value='/sample/egovSampleList.do'/>";
        document.listForm.submit();
    }

    <!--
    /* pagination 페이지 링크 function */
    function fn_egov_link_page(pageNo){
        document.listForm.pageIndex.value = pageNo;
        document.listForm.action = "<c:url value='/sample/egovSampleList.do'/>";
        document.listForm.submit();
    }
    //-->
    </script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable();
        $('#no').trigger('click');
     });
    </script>

</body>

</html>
