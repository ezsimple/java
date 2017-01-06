<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
A:link {text-decoration: none; color:black;}
A:visited {text-decoration: none; color:black;}
A:active {text-decoration: none; color:black;}
A:hover {text-decoration: none; color: blue;}
body{padding: 20px;background-color: #FFF; text-align: left;
    font: 76% Verdana,Arial,sans-serif}
div#title{width:200px;font-size:110%;font-weight:bold;margin:0 auto;text-align:center;background:#C3D9FF;color:#FFF}
div#container{width:550px;margin: 0 auto;text-align:center}
div#header{width:100%; margin:0 auto; background:#F0F0E7;align:center}
div#inheader{width:90%; margin:0 auto; background:#F0F0E7;text-align:left;word-wrap:break-word}
div#box{width:100%;margin:0 auto;background:#E4E7F2;text-align:center}
div#inbox{width:95%;margin:0 auto;background:#E4E7F2;text-align:center;word-wrap:break-word}
div#box p{margin:0;padding: 5px}
div#help{color:#BABABA;font-family:굴림;font-size:11px}
div#footer{color:#DBDBDB;font-family:굴림;font-size:11px}
p{margin:0;padding-bottom:0.7em}
input {clear:both;font-family: Helvetica, Verdana;border-radius:6px; border-style:solid;box-shadow:0px 0px 0px #fff;padding-left:5px;padding-right:5px;border-color:#BABABA;border-width:1px;vertical-align:top}
input[type="submit"]{background:#C3D9FF;border-color:#C3D9FF;font-weight:bold;color:#fff;text-align:center}
</style>

<title>Oracle Package Source Generator</title>
</head>
<body>
<form method="post" action="createpkg.do"> 
	Table Name <input type="text" name="table_name" value=""/></br>
	PK Column <input type="text" name="table_pk" value=""/>(콤마(,)로 구분하여 여러개 입력 가능)</br>
	Package Name <input type="text" name="pkg_nm" value=""/></br>
	<input type="submit" value="Generate" />
</form>
<pre>${pkg_header}</pre>
<pre>${pkg_body}</pre>

<div id="container">
<div style="height:5px;weight:100%;"></div>
<div id="header"></div>
<div style="height:5px;weight:100%;"></div>
<div id="box"></div>
</div> 
</body>
</html>
