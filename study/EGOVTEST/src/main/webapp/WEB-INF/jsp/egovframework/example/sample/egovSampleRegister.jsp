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

<c:set var="registerFlag" value="${(empty sampleVO.id)||(sampleVO.id==null)? '등록' : '수정'}"/>
<!--For Commons Validator Client Side-->
<script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-1.4.2.min.js' />" ></script>
<script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
<validator:javascript formName="sampleVO" staticJavascript="false" xhtml="true" cdata="false"/>

</head>

<body>
	<div class="container">
		<form:form role="form" commandName="sampleVO" name="detailForm" method="post">
			<input type="hidden" name="useYn" value="Y"/>
			<div class="panel panel-primary">
				<div class="panel-heading">
                  <b><c:out value="${registerFlag }"/> 페이지</b>
                  <c:if test="${registerFlag == '수정'}">
                  		(
                     <label for="id">ID : </label>
                     <span class="form-control-static"><c:out value="${sampleVO.id}"/></span>
                     <input type="hidden" name="id" cssClass="essentiality" maxlength="10" value='<c:out value="${sampleVO.id }"/>'/>
                        )
                  </c:if>
				</div>
				<!-- /#panel-heading -->
				<div class="panel-body">
                    <div class="form-group">
                      <label>제목</label>
                      <input class="form-control" name="name" placeholder="제목을 입력하세요" value='<c:out value="${sampleVO.name }"/>'>
				        <form:errors path="name" />
                    </div>
                    <div class="form-group">
                      <label>내용</label>
                      <textarea class="form-control" rows="3" name="description" placeholder="내용을 입력하세요"><c:out value="${sampleVO.description }"/></textarea>
				        <form:errors path="description" />
                    </div>
                    <div class="form-group">
                      <label>등록자</label>
                      <input class="form-control" name="regUser" placeholder="이름을 입력하세요" value='<c:out value="${sampleVO.regUser }"/>' />
				        <form:errors path="regUser" />
                    </div>
				</div>
				<div class="panel-footer">
				<!-- /.row -->
				<div class="row">
					<div class="panel-body">
						<div class="table-responsive">
							<div class="text-right">
                            <button type="button" title="목록" class="btn btn-primary btn-circle"
                            onclick="javascript:fn_egov_selectList();return false;"><i class="fa fa-list"></i></button>
                            <button type="button" title="저장" class="btn btn-success btn-circle" 
                            onclick="javascript:fn_egov_save();return false;"><i class="fa fa-save"></i></button>
                            <button type="button" title="새로고침" class="btn btn-warning btn-circle"
                            onclick="javascript:document.detailForm.reset();return false;"><i class="fa fa-refresh"></i></button>
							</div>
						</div>
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.row -->
                </div>
			</div>
            <!-- 검색조건 유지 -->
            <input type="hidden" name="selectedId" value="<c:out value='${sampleVO.id }'/>"/>
            <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
            <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
            <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
		</form:form>
		<!-- /#panel-primary -->

	</div>
	<!-- /.container -->

	<!-- Core Scripts - Include with every page -->
	<script
		src="<c:url value='/resources/sb-admin-v2/js/jquery-1.10.2.js'/>"></script>
	<script
		src="<c:url value='/resources/sb-admin-v2/js/bootstrap.min.js'/>"></script>
	<script
		src="<c:url value='/resources/sb-admin-v2/js/plugins/metisMenu/jquery.metisMenu.js'/>"></script>

	<!-- Page-Level Plugin Scripts - Tables -->
	<script
		src="<c:url value='/resources/sb-admin-v2/js/plugins/dataTables/jquery.dataTables.js'/>"></script>
	<script
		src="<c:url value='/resources/sb-admin-v2/js/plugins/dataTables/dataTables.bootstrap.js'/>"></script>

	<!-- SB Admin Scripts - Include with every page -->
	<script src="<c:url value='/resources/sb-admin-v2/js/sb-admin.js'/>"></script>

    <script type="text/javaScript" language="javascript" defer="defer">
    /* 글 목록 화면 function */
    function fn_egov_selectList() {
        document.detailForm.action = "<c:url value='/sample/egovSampleList.do'/>";
        document.detailForm.submit();
    }
    /* 글 등록 function */
    function fn_egov_save() {
        frm = document.detailForm;
        if(!validateSampleVO(frm)){
            return;
        }else{
           frm.action = "<c:url value="${registerFlag == '등록' ? '/sample/addSample.do' : '/sample/updateSample.do'}"/>";
           frm.submit();
         }
    }
    /* 댓글 function */
    function fn_egov_reply() {
        frm = document.detailForm;
        if(!validateSampleVO(frm)){
            return;
        }else{
            frm.action = "<c:url value="/sample/replySample.do"/>";
           frm.submit();
        }
    }
    /* 글 삭제 function */
    function fn_egov_delete() {
        document.detailForm.action = "<c:url value='/sample/deleteSample.do'/>";
        var id = $('input[name=id]').val();
        if(confirm("게시물(ID:"+id+")을 삭제하시겠습니까?"))
        document.detailForm.submit();
    }
    /* 댓글 삭제 function */
    function fn_egov_reply_delete(id) {
        document.detailForm.action = "<c:url value='/sample/deleteReplySample.do'/>";
        $('input[name=id]').val(id);
        if(confirm("댓글(ID:"+id+")을 삭제하시겠습니까?"))
          document.detailForm.submit();
    }
    </script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document).ready(function() {
			$(document).on('keypress','input[name=regUser]', function(e) {
			    if (e.which == 13) {/* 13 == enter key@ascii */
			       fn_egov_save();
			    }
			});
		});
	</script>

</body>

</html>
