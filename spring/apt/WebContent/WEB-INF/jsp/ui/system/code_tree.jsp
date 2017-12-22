<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/tag"%>
<%@ taglib prefix="src" tagdir="/WEB-INF/tags/src"%>
<script type="text/javascript">

</script>
<%//이곳에 위로 개발자 정의 소스를 추가하세요%>
<%//[[START_AUTO_GENERATED_SRC]]%>
<c:set var="uuid" value="${sp:uuid()}"/>
<%//------------------------------------------------------------------------
//                     기본 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="def_rows" value="${UI_RESULT['rows']}"/>

<%//------------------------------------------------------------------------%>
	<span id="${uuid}" class="title" style="display:none;"><tag:el source="코드관리" param="${def_rows[0]}"/></span>
	<div class="unit_page" type="page" >
				<form id="form_" action="" method="post" enctype="multipart/form-data">
				
					<input type="hidden" name="ui_id" value="code_tree">
					<input type="hidden" name="action_type" value="">
					<!-- // -->
<%//------------------------------------------------------------------------
//                     현재 레코드 설정                                     
//------------------------------------------------------------------------%>

<c:set var="rows" value="${UI_RESULT.rows}"/>

<%//------------------------------------------------------------------------%>

			<%%>
			<c:set var="tree_id" value="${sp:uuid()}"/>

<script type="text/javascript">
$(function(){

	var treeData = ${sp:list2tree(rows, 'group_id', 'code_value', 'code_name', 'rid', 'root') };
	
    $("#tree_${tree_id }").dynatree({
        onActivate: function(node) {
            linkLoad('#tree_${tree_id }', <tag:el source="'code_view',{group_id:node.data.group_id, code_value: node.data.code_value}, 1" param="${rows[0] }"/>);
        },
        persist: true,
        children: treeData
    });
    
    $('.dynatree-container').css('overflow', 'visible');
});
	
</script> 
<div id="tree_${tree_id}"></div></form>
				<div style="clear: both; height: 25px; margin-top: 10px;padding:3px; ">
		</div><iframe name="submit_frame" style="width: 0px; height: 0px; display: none;"></iframe>
				
			</div>
			<script type="text/javascript">
		$(function() {	
			setTitle('#${uuid}');
		});
	</script>
<%//[[END_AUTO_GENERATED_SRC]]2107253209[[HASHCODE]]%>
<%//이곳에 아래로 개발자 정의 소스를 추가하세요%>
