<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag"  tagdir="/WEB-INF/tags/tag" %> 
<%@ taglib prefix="src"  tagdir="/WEB-INF/tags/src" %> 

<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta name="viewport" content="user-scalable=yes, width=device-width, initial-scale=1, maximum-scale=1">
<title>IoE Server</title>
<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon" />
<link href="../jquery/jquery-ui-1.11.2.custom/jquery-ui.css"  rel="stylesheet" type="text/css" media="screen" />
<link href="../jquery/jqGrid/css/ui.jqgrid.css"  rel="stylesheet" type="text/css" media="screen" />
<link href="../jquery/jqGrid/plugins/ui.multiselect.css" rel="stylesheet" type="text/css" media="screen" />
<link href='../jquery/dynatree/skin/ui.dynatree.css' rel='stylesheet' type='text/css' >
<link href="../css/contents.css" rel="stylesheet" type="text/css" />
<link href="../css/contents${isMobile ? '-mb' : '-web' }.css" rel="stylesheet" type="text/css" />

<script src="../jquery/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="../jquery/jquery-ui-1.11.2.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src='../jquery/js/cookies.js' type="text/javascript"></script>
<c:if test="${isMobile}">
<script src="../jquery/js/hammer.min.js" type="text/javascript"></script>
</c:if>
<!-- script src='../jquery/dynatree/jquery.dynatree.js' type="text/javascript"></script -->
<!--[if IE]>
    <script type="text/javascript" src="../jquery/flotr2/flotr2.ie.min.js"></script>
<![endif]-->
<script src="../jquery/flotr2/flotr2.min.js" type="text/javascript"></script>
<!-- 
<script src="../jquery/jqGrid/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="../jquery/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
 -->
<script src="../js/commonUtil.js" type="text/javascript"></script>
<script src="../js/valid.js" type="text/javascript"></script>
<script src="../js/autoPageUtils.js" type="text/javascript"></script>
<script src="../js/chart.js" type="text/javascript"></script>

<c:set var="context" value="${pageContext.request.contextPath}" />
<c:if test="${empty context}"><c:set var="context" value="/"/></c:if>
<script type="text/javascript">
var context = "${context }";
$(function() {
	$('#${uuid}').addClass('ui-widget-header').css({width:'100%'}).show();

	if(!isMobile){
		tooltip();
	}
	
	setCurrentMenu();
});
function setCurrentMenu(){
	var subMenus = $('.sub_menu');
	
	for(var i=0; i<subMenus.length; i++){
		if($(subMenus[i]).attr('onclick').indexOf('${menu_id}')>0){
			$(subMenus[i]).css({background:'#eeeeee'});
			break;
		}
	}
}

function tooltip(){
	$( document ).tooltip({
		items: "[title]",
		content: function() {
			var element = $( this );
			setTimeout(function () {
				var toolTip = $('.ui-tooltip');
				if(toolTip.length>1){
					toolTip.hide();
				}
				//alert(22);
			}, 2000);		
			
			if ( element.is( "[title]" ) ) {
				return element.attr( "title" );
			}
		}
	});
}
function load(url){
	var target = $('#main_layout');
	target.load('../' + url);
}
function openUrl(url){

	document.location.href = '..' + url;
}
function check(chartId){
	if(chartId.length>0 && chartId.get(0).clientHeight==0){
		return;
	}
}
function goLoginPage(){
	document.location.href='../-login-login_form/.sh';
}
function logout(){
	$.getJSON( "../logout.sh", function( data ) {
		document.location.href="${context }";

	});
}
function changeMy(){
	document.location.href='../-at-lo-pg/-userChange_form.sh';
}

</script> 
</head>
<body >
	<c:set var="menu_list">
		<div style="float:left; width: 170px; height:700px; margin:3px 6px 0 0;">
			<div class="menu_left menu_event" style=" width: 100%; height:100px; margin-bottom:3px; border:1px solid #222222;">
				<div class="home_tit" >이벤트 관리</div>
				<div class="sub_menu" style="cursor: pointer;padding:5px;" onclick="openUrl('/-at-lo-pg/-event_list.sh')">이벤트 목록</div>
				<div class="sub_menu" style="cursor: pointer;padding:5px;" onclick="openUrl('/-at-lo-pg/-eventCtl_list.sh')">이벤트별 제어정보</div>
			</div>
			<div class="menu_left menu_power" style=" width: 100%; height:100px; margin-bottom:3px; border:1px solid #222222;">
				<div class="home_tit" >전력사용량 관리</div>
				<div class="sub_menu" style="cursor: pointer;padding:5px;" onclick="openUrl('/-at-lo-pg/-search_powerCBL_list.sh')">CBL 정보</div>
				<div class="sub_menu" style="cursor: pointer;padding:5px;" onclick="openUrl('/-at-lo-pg/-search_powerKep_list.sh')">한전검침 정보</div>
				<div class="sub_menu" style="cursor: pointer;padding:5px;" onclick="openUrl('/-at-lo-pg/-search_power_list.sh')">SmartMeter검침 정보</div>
			</div>
			<c:if test="${session['access_cls']=='A' }">
				<div class="menu_left menu_cust" style=" width: 100%; height:100px; margin-bottom:3px; border:1px solid #222222;">
					<div class="home_tit" >전력 수용가 관리</div>
					<div class="sub_menu" style="cursor: pointer;padding:5px;" onclick="openUrl('/-at-lo-pg/-custView.sh')">수용가 정보</div>
					<div class="sub_menu" style="cursor: pointer;padding:5px;" onclick="openUrl('/-at-lo-pg/-custMeter_list.sh')">SmatrMeter정보</div>
				</div>
				<div class="menu_left menu_user" style=" width: 100%; height:100px; margin-bottom:3px; border:1px solid #222222;">
					<div class="home_tit" >사용자 관리</div>
					<div class="sub_menu" style="cursor: pointer;padding:5px;" onclick="openUrl('/-at-lo-pg/-userList.sh')">사용자 관리</div>
					<div class="sub_menu" style="cursor: pointer;padding:5px;" onclick="openUrl('/-at-lo-pg/-userChange_form.sh')">개인정보 관리</div>
				</div>
				<div class="menu_left menu_sys" style=" width: 100%; height:100px; margin-bottom:3px; border:1px solid #222222;">
					<div class="home_tit" >시스템 관리</div>
					<div class="sub_menu" style="cursor: pointer;padding:5px;" onclick="openUrl('/-at-lo-pg/-scheduleList.sh')">연동상태 관리</div>
					<div class="sub_menu" style="cursor: pointer;padding:5px;" onclick="openUrl('/-at-lo-pg/-searchLogFile_list.sh')">연동로그 관리</div>
					<div class="sub_menu" style="cursor: pointer;padding:5px;" onclick="openUrl('/-at-lo-pg/-devReqList.sh')">요구사항 리스트(임시)</div>
				</div>
			</c:if>
			<c:if test="${session['access_cls']!='A' }">
				<div class="menu_left menu_user" style=" width: 100%; height:100px; margin-bottom:3px; border:1px solid #222222;">
					<div class="home_tit" >개인정보 관리</div>
					<div class="sub_menu" style="cursor: pointer;padding:5px;" onclick="openUrl('/-at-lo-pg/-userChange_form.sh')">개인정보 관리</div>
				</div>
			</c:if>
		</div>
	</c:set>

	<header class="fix_height main_layout">
		<div  style="border:1px solid #222222;">
		<table style="width: 100%;"><tr>
			<td style="width: 60px; ">
				<a href="../-dr-home/.sh"><img src="../images/log.png" border="0"></a>
			</td>
			<td valign="bottom">
				<span style="font-size: 30px; ">&nbsp;<b>IoE</b></span>
				<span style="font-size: 20px;">&nbsp;<b>Server</b></span>
			</td>
			<td  valign="bottom"  style="padding-bottom: 3px; ">
				<c:if test="${!isLogin}">
					<div onclick="goLoginPage()" style="cursor:pointer;  float: right;border:1px solid #222222; margin:5px;">로그인</div>
				</c:if>
				<c:if test="${isLogin}">
					<div onclick="logout()" style="cursor:pointer; float: right;border:1px solid #222222; margin:5px;">로그아웃</div>
					<div onclick="changeMy()" style="cursor:pointer; float: right;border:1px solid #222222; margin:5px;">수정</div>
					<div style="  float: right;  margin:5px;">${session['user_name']}</div>
				</c:if>
			</td>
		</tr></table>
		</div>
	</header>
	<c:set var="isHome" value="${UI_TPL == 'dr/home.jsp'  }"/>
	<c:set var="isLogin" value="${UI_TPL == 'login/login_form.jsp'  }"/>
	<div class="main_layout hide_mb" style="margin-bottom:5px; background: #cccccc;">
		<div class="top_menu" onclick="openUrl('/-at-lo-pg/-event_list.sh')">이벤트 관리</div>
		<div class="top_menu" onclick="openUrl('/-at-lo-pg/-search_powerKep_list.sh')">전력 사용량</div>
		<c:if test="${session['access_cls']=='A' }">
			<div class="top_menu" onclick="openUrl('/-at-lo-pg/-custView.sh')">수용가 관리</div>
			<div class="top_menu" onclick="openUrl('/-at-lo-pg/-userChange_form.sh')">사용자 관리</div>
			<div class="top_menu" onclick="openUrl('/-at-lo-pg/-scheduleList.sh')">시스템 관리</div>
		</c:if>
	</div>
	<div class="main_layout" style="overflow-y:auto; overflow-x:hidden;">
		${!isHome && !isMobile && !isLogin ? menu_list : '' }
		<div id="main_layout" style=" ${isMobile ? '' : (isHome || isLogin ? 'width:1000px;' : 'float:left;width:824px;')}"> 
			<c:import url="${UI_TPL }"/>
		</div>
	</div>
	
	<footer class="fix_height main_layout" style="clear: both; text-align: center; color: #aaaaaa; ">
		<b>Copyright © I-ON Communications Corp.</b>
	</footer>
	
	<div ${isMobile || pageContext.request.serverName != 'www.ioe.or.kr' ? 'class="menu"' : ' style="display: none;'}>
		<c:if test="${!isMobile }">
			<ul>
				<li class="ui-widget-header">개발 메뉴</li>
				<li onclick="openPage('../admin-src/main.sh')"><img src="../images/icon/program-group-icon.png"> UI 생성</li>
				
				<li class="ui-widget-header">XML to DB 연동</li>
				<li onclick="openUrl('/-admin-mapper-main/createtbl.sh')">1. 테이블 생성</a></li>
				<li onclick="openUrl('/-admin-mapper-main/xml2db_mapping.sh')">2. XPath에 필드 매핑</a></li>
				<li onclick="openUrl('/-admin-mapper-main/trigger_mapping.sh')">3. XPath에 연동쿼리 매핑</a></li>
		
				<li class="ui-widget-header">페이지 로딩 예제</li>
				<li onclick="openUrl('/-at-portlet-ly/sample1.sh')">포틀릿 예제</li>
				<li onclick="load('/-at-portlet/sample1.sh')">포틀릿 예제(레이어)</li>
				<li onclick="openUrl('/-at-lo-h3/-code_group_list.sh')">코드 관리</li>
				<li onclick="load('/-at-lo-h3/-code_group_list.sh')">코드 관리(레이어)</li>
				<li class="ui-widget-header">기타</li>
				<li onclick="openUrl('/-admin-menu-manual/.sh')"><img src="../images/icon/help-file-icon.png"> 메뉴얼</li>
				<li onclick="openUrl('/-at-lo-pg/-devReqList.sh')"><img src="../images/icon/network-checklist-icon.png"> 요구사항 리스트</li>
			</ul>
		</c:if>
		${isMobile ? menu_list : '' }
	</div>
</body>
</htm>
