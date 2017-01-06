<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
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
.error { color : #ff0000; }
</style>

<c:set var="registerFlag" value="${empty sampleVO.id ? '등록' : '수정'}"/>
<script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-1.4.2.min.js' />" ></script>
<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
<validator:javascript formName="replyVO" staticJavascript="false" xhtml="true" cdata="false"/>

</head>

<body>
	<div class="container">
		<form:form commandName="sampleVO" name="detailForm" method="post">
          <form:hidden path="id"/>
          <form:hidden path="name"/>
          <form:hidden path="useYn"/>
          <input type="hidden" name="selectedId" value="<c:out value='${sampleVO.id }'/>"/>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<b><c:out value="${sampleVO.name }" /></b>
                  	(
                     <label for="id">ID : </label>
                     <span class="form-control-static"><c:out value="${sampleVO.id}"/></span>
                    )
				</div>
				<!-- /#panel-heading -->
				<div class="panel-body">
				   <c:set var="newline" value="<%= \"\n\" %>" />
					${fn:replace(sampleVO.description, newline, "<br />")}
				</div>
				<div class="panel-footer text-right">
					등록자 :
					<c:out value="${sampleVO.regUser }" />
				</div>
				<!-- /#panel-footer -->
				<!-- /.row -->
				<div class="row">
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<colgroup>
									<col width="30" />
									<col width="120" />
									<col width="" />
									<col width="100" />
									<col width="5" />
								</colgroup>
								<tbody>
									<c:set var="total" value="${fn:length(replies) }" />
									<c:forEach var="result" items="${replies}" varStatus="status">
										<tr>
											<td><c:out value="${status.count}" /></td>
											<td><c:out value="${result.id}" /></td>
											<td>${fn:replace(result.description, newline, "<br />")}</td>
											<td><c:out value="${result.regUser}" /></td>
											<td>
											<button class="btn btn-outline btn-danger" type="button"
											onclick='javascript:fn_egov_reply_delete("${result.id }");return false;' title="삭제">
											<i class="fa fa-trash-o"></i>
											</button>
											</td>
										</tr>

									</c:forEach>
								</tbody>
							</table>
		</form:form>
							<div class="form-group">
								<table class="table table-striped">
								<colgroup>
									<col width="" />
									<col width="5" />
								</colgroup>
								<tr>
								<td>
		<form:form commandName="replyVO" name="replyForm" method="post">
		   <spring:bind path="id">
		   <input type="hidden" name="id" value="${sampleVO.id }"/>
		   </spring:bind>
		   <spring:bind path="name">
          <input type="hidden" name="name" value="${sampleVO.name } - 댓글"/>
		   </spring:bind>
		   <spring:bind path="useYn">
          <input type="hidden" name="useYn" value="${sampleVO.useYn }"/>
		   </spring:bind>
          <input type="hidden" name="selectedId" value="<c:out value='${sampleVO.id }'/>"/>
								<form:textarea class="form-control" rows="3" path="description" placeholder="댓글을 입력하세요"/>
								<form:errors path="description" cssClass="error"/>

                      		<form:input class="form-control pull-right" style="width:20%;" path="regUser" placeholder="이름을 입력하세요" />
				        		<form:errors path="regUser" cssClass="error"/>
        </form:form>

								</td>
								<td data-placement="bottom">
                                  <button class="btn btn-success" type="button" onclick="javascript:fn_egov_reply();return false;">댓글</button>
								</td>
								</tr>
								</table>
							</div>

							<div class="text-center">
								<button class="btn btn-primary" type="button" onclick="javascript:fn_egov_selectList();return false;">목록</button>
                            <button class="btn btn-warning" type="button" onclick="javascript:fn_egov_modify();return false;"><c:out value='${registerFlag}'/></button>
								<button class="btn btn-danger" type="button"  onclick="javascript:fn_egov_delete();return false;">삭제</button>
							</div>
						</div>
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.row -->
			</div>

        <!-- 검색조건 유지 -->
		<form:form commandName="sampleVO" name="searchForm" method="post">
        <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
        <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
        <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
		</form:form>
		<!-- /#panel-primary -->

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
    <!--
    /* 글 목록 화면 function */
    function fn_egov_selectList() {
        document.detailForm.action = "<c:url value='/sample/egovSampleList.do'/>";
        document.detailForm.submit();
    }
    /* 글 등록 function */
    function fn_egov_modify() {
        frm = document.detailForm;
        frm.action = '<c:url value="/sample/egovSampleModify.do"/>';
        var registerFlag = '<c:out value="${registerFlag}"/>';
        var id = $('input[name=id]').val();
        if(confirm("게시물(ID:"+id+")을 "+registerFlag+"하시겠습니까?"))
        frm.submit();
    }
    /* 댓글 function */
    function fn_egov_reply() {
        frm = document.replyForm;
        frm.action = "<c:url value="/sample/replySample.do"/>";
        if(!validateReplyVO(frm)){
        	return false;
         }
        if(confirm("댓글을 등록 하시겠습니까?"))
        frm.submit();
    }
    /* 댓글 삭제 function */
    function fn_egov_reply_delete(id) {
        document.detailForm.action = "<c:url value='/sample/deleteReplySample.do'/>";
        $('input[name=id]').val(id);
        if(confirm("댓글(ID:"+id+")을 삭제하시겠습니까?"))
          document.detailForm.submit();
    }
    /* 글 삭제 function */
    function fn_egov_delete() {
        document.detailForm.action = "<c:url value='/sample/deleteSample.do'/>";
        var id = $('input[name=id]').val();
        if(confirm("게시물(ID:"+id+")을 삭제하시겠습니까?"))
        document.detailForm.submit();
    }
    </script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document).ready(function() {
			$(document).on('keypress','input[name=regUser]', function(e) {
			    if (e.which == 13) {/* 13 == enter key@ascii */
			       fn_egov_reply();
			    }
			});
		});
	</script>

</body>

</html>
