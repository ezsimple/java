<%@page import="java.util.Date"%>
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
<meta name="viewport" content="user-scalable=yes, width=device-width, initial-scale=1, maximum-scale=2">
<title>IoE Server</title>
<link href="../jquery/jquery-ui-1.11.2.custom/jquery-ui.css"  rel="stylesheet" type="text/css" media="screen" />
<link href="../jquery/jqGrid/css/ui.jqgrid.css"  rel="stylesheet" type="text/css" media="screen" />
<link href="../jquery/jqGrid/plugins/ui.multiselect.css" rel="stylesheet" type="text/css" media="screen" />
<link href="../css/contents.css" rel="stylesheet" type="text/css" />
<link href='../jquery/dynatree/skin/ui.dynatree.css' rel='stylesheet' type='text/css' >
<link href="../css/contents${isMobile ? '-mb' : '-web' }.css" rel="stylesheet" type="text/css" />

<script src="../jquery/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="../jquery/jquery-ui-1.11.2.custom/jquery-ui.min.js" type="text/javascript"></script>
<script src="../jquery/jqGrid/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="../jquery/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<script src="../js/commonUtil.js" type="text/javascript"></script>
<script src="../js/autoPageUtils.js" type="text/javascript"></script>
<script src='../jquery/dynatree/jquery.dynatree.js' type="text/javascript"></script>
<script src='../jquery/js/jquery.cookie.js' type="text/javascript"></script>
<script type="text/javascript">

function send(){
	$( ".log" ).text('');
	var data = $('#form').serializeArray()
	data1 = {
		device_id: $('#device_id').val(),
		interval: $('#interval').val(),
		measure_date: $('#measure_date').val(),
		power_consumption: $('#power_consumption').val(),
		ioe_m_ip: $('#ioe_m_ip').val(),
		device_cd: $('#device_cd').val(),
		opt_in: $('#opt_in').val()
	};
	
	$.ajax({
		url: "/dr/power",
		type: "POST",
		//MimeType: 'text/plain',
		//context: "sdsdsdsdsdfsddd",
		data: data
	}).done(function(data) {
		$( "#rest1" ).text(  getJSONString(data) );
	}).fail(function(a,b,c,d) {
		$( "#rest1" ).text('statusa : ' + a.status);
		$( ".log" ).text(a.responseText);
	});
}

function search(){
	$( ".log" ).text('');
	var data = {
		device_id: $('#device_id').val(),
		measure_date: $('#measure_date').val()
	};
	
	$.ajax({
		url: "/dr/power",
		type: "GET",
		data: data
	}).done(function(data) {
		var table = '<table class="lst" border="0"><tr>';
		var rows = data.list;
		
		if(rows.length<1){
			$( "#rest1" ).html('');
			return;
		}
		var dimensions = rows[0];
		var keys = $.map( dimensions, function( value, key ) {
			return key;
		});
		
		for(var j=0; j<keys.length; j++){
			var hide = (j>3 ? 'hide_mb' : '');
			table += '<th class="' + hide + '">' + keys[j].split('_').join(' ') + '</th>';
		}
		table += "</tr>";

		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			table += "<tr>";
			for(var j=0; j<keys.length; j++){
				var hide = (j>3 ? 'hide_mb' : '');
				table += '<td class="' + hide + '">' + row[keys[j]] + '</td>';
			}
			table += "</tr>";
		}
		table += "</table>";

		$( "#rest1" ).html(table);
	}).fail(function(a,b,c,d) {
		$( "#rest1" ).text('statusa : ' + a.status);
		$( ".log" ).text(a.responseText);
	});
	
}

function getJSONString(json)
{
    var ary = new Array();
    for(var key in json)
    {
        var val = json[key];
        if(typeof(val)=='object') val = getJSONString(val);
        else if(typeof(val)=='string') val = '"'+val+'"';
        ary.push(key+':'+val);
    }
    return '{'+ary.join()+'}';
}

function bspla(){
	

	$( ".log" ).text('');
	var data = {
			venid: '1223081447',
			meterid: '3048501223081447***',
			dataTypeype: 'ismart',
			dateStr: '20150115'
		};
	
	$.ajax({
		url: "http://125.140.110.206:8888/bspla/histories",
		type: "POST",
		//MimeType: 'text/plain',
		//context: "sdsdsdsdsdfsddd",
		data: data
	}).done(function(data) {
		$( "#rest1" ).text(  getJSONString(data) );
	}).fail(function(a,b,c,d) {
		$( "#rest1" ).text('status : ' + a.status);
		$( ".log" ).text(a.responseText);
	});
}

</script> 
</head>
<body >
	<header class="fix_height main_layout">
		<table><tr>
			<td>
				<img src="../images/log.png">
			</td>
			<td valign="bottom">
				<span style="font-size: 30px; ">&nbsp;IoE</span>
			</td>
			<td  valign="bottom"  style="padding-bottom: 3px;">
				<span style="font-size: 20px;">&nbsp;Server</span>
			</td>
		</tr></table>
		 
	</header>
	<div id="main_layout" class="main_layout" style="margin: 3px auto 3px; overflow:auto; ">
		<form id="form">
		<table class="vw" border="0">
			<tr><th width="200">device_id</th><td><input name="device_id" id="device_id" value="device_id"><br></td></tr>
			<tr><th>interval</th><td><input name="interval" id="interval" value="600"></td></tr>
			<tr><th>device_cd</th><td><input name="device_cd" id="device_cd" value="tv"></td></tr>
			<tr><th>ioe_m_ip</th><td><input name="ioe_m_ip" id="ioe_m_ip" value="192.168.0.1"></td></tr>
			<tr><th>optin</th><td><input name="optin" id="optin" value="Y"> Y:참여, N:미참여, 미설정시 IoE Server설정사용</td></tr>
		</table>
		현재로 부터 12시간 전 부터 현재 데이타까지 반복 전송
		<table class="vw" border="0">
			<tr>
				<th>measure_date</th>
				<th>power_consumption</th>
			</tr><tr>
				<td><input name="measure_date" id="measure_date" value="2015-03-11 11:05:00"></td>
				<td><input name="power_consumption" id="power_consumption" value="22.4"></td>
			</tr><tr>
				<td><input name="measure_date" id="measure_date" value="2015-03-11 11:10:00"></td>
				<td><input name="power_consumption" id="power_consumption" value="22.4"></td>
			</tr><tr>
				<td><input name="measure_date" id="measure_date" value="2015-03-11 11:15:00"></td>
				<td><input name="power_consumption" id="power_consumption" value="22.4"></td>
			</tr>
		</table>
		</form>
		<br>
		<button onclick="send();">전송</button>
		<button onclick="search();">조회</button>
		
		<br><br>
		<div id="rest1" ></div>
		log:
		<div class="log" ></div>
	</div>
	<footer class="fix_height main_layout" style="clear: both; text-align: center; color: #aaaaaa; ">
		<b>Copyright © I-ON Communications Corp.</b>
	</footer>
				
</body>
</htm>