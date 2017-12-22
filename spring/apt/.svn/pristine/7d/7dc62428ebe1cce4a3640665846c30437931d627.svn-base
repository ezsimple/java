<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:if test="${empty context}"><c:set var="context" value="/"/></c:if>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width">
	
	<meta name="title" content="IoE Server">
	<meta name="author" content="I-ON Communications">
	<meta name="description" content="IoE Server is optimized for Apartment Dement-Response and is suitable for energy management service.">
	<meta name="copyright" content="2015 I-ON Communications Incorporated. All rights reserved.">
	
	<title>IoE Server</title>
	<link type="image/x-icons" rel="shortcut icon" href="../img/favicon/favicon.ico">
	
	<link type="text/css" rel="stylesheet" href="../css/base.css">
	<link type="text/css" rel="stylesheet" href="../css/login.css">
	<script type="text/javascript" src="../js/libs/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="../js/libs/jquery-ui-1.10.3.min.js"></script>
</head>

<body class="ko">
	<div id="wrap" class="page_wrap">
		<!-- 헤더영역 -->
		<header>
	    	<div class="header_inner">
	            <a href="/index.do"><h1 class="top_logo"><span>IoE Server</span></h1></a>
	        </div>             
	    </header>    
	    <!-- 본문 -->
	    <section id="container" class="container"> 
	    	<div class="content_area">            
	            <div class="login_area">
	            	<!-- 로그인 -->
	                <div class="box_base box_login">
	                	<textarea id="exception" style="display:none">UserSecurityException</textarea>
						<textarea id="msg" style="display:none"></textarea>
	                    <form action="at.sh" method="post" name="login_form" onkeydown="keydown(event)" onsubmit="return login();">
	                    	<input type="hidden" name="redirect" class="redirect" value="">
	                        <fieldset>
	                            <legend>IoE 로그인</legend>
	                            <label for="userId"></label>
	                            <input type="text" id="uid" name="uid" placeholder="admin" autocapitalize="off" autocorrect="off" autocomplete="off" value="admin" >
	                            <script type="text/javascript" language="javascript">
									document.getElementById("uid").focus();
								</script>
	                            <label for="passWord"></label>
	                            <input type="password" id="pwd" name="pwd" value="1111" placeholder="Password" class="password" onkeydown="if(event.keyCode==13){doSubmit();}" autocomplete="off" > 
	                            <div class="login_option">
	                            <input type="checkbox" name="svuid" id="svuid">
	                            <label for="svuid">아이디 저장</label>
	                            </div>
	                            <button type="submit" class="btn_login" onkeydown="if(event.keyCode==13){doSubmit();}">IoE 로그인</button>
	                        </fieldset>
	                    </form>
	                </div><!-- //box_base -->
	                <!-- 아이디or비밀번호 잘못 입력했을 경우 -->
	                <div class="message">
	                    <p id="info_message"></p>
	                </div>
	            </div><!-- //login_area -->   
	        </div><!-- //content_area -->   
	        <!-- 푸터영역 -->
	        <footer>
	            <p class="copyright">Copyright © I-ON Communications Corp.</p>
	        </footer>
	    </section><!-- //container -->   
	</div><!-- //page_wrap -->
</body>
</html>

<script type="text/javascript">
var context = "${context }";
$(function() {
	$('#menu-btn').remove();
	$('#main_layout').parent().css({border: '1px solid #222222'})
	$('#login').click(function(){
		login();
	});
});

function login(){
	//if(!$.formValed('loginMainForm')) return false;

	var data ={
			uid : $('#uid').val().trim(),
			pwd : $('#pwd').val(),
			svuid : $('#svuid').val()
	};
	
	$.getJSON('../login.sh', data, function(data){
		if(data.success){
			document.location.href=context;
		}else if(data.isMessage){
			alert(data.message);
		}else{
			alert( '아이디 또는 패스워드가 잘못되었거나 회원승인상태가 아닙니다. \n확인 후 다시 로그인 하세요.' );
		}
	});
	
	return false;
}

function keydown(event){
	if(event.keyCode==13){
		login();
	}
}
</script>

