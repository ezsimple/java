<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag"  tagdir="/WEB-INF/tags/tag" %> 
<%@ taglib prefix="src"  tagdir="/WEB-INF/tags/src" %>

<%String client_ip = request.getRemoteAddr();%>
<c:set var="client_ip" value="<%=client_ip%>"/>


<script src='../jquery/js/cookies.js' type="text/javascript"></script>
<script src="../js/valid.js" type="text/javascript"></script>
<script src="../js/commonUtil.js" type="text/javascript"></script>
<script src="../js/autoPageUtils.js" type="text/javascript"></script>
<script type="text/javascript">

function send(){
	$( ".log" ).text('');
	var data = $('#form_').serializeArray()
    var json_str = JSON.stringify(data);
	$.ajax({
		url: "/dr/power",
		type: "POST",
		//MimeType: 'text/plain',
		//context: "sdsdsdsdsdfsddd",
		data: data
	}).done(function(data) {
		$( "#rest1" ).text( json_str );
        $( '.log').text(getJSONString(data));
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
//	console.log(data.device_id);
//	console.log(data.measure_date);
	$.ajax({
		url: "/dr/powerConsume",
		type: "GET",
		data: data
	}).done(function(data) {
		var table = '<table class="table table-bordered"><thead><tr>';
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
			table += '<th>' + keys[j] + '</th>';
		}
		table += "</tr></thead><tbody>";

		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			table += "<tr>";
			for(var j=0; j<keys.length; j++){
				table += '<td>' + row[keys[j]] + '</td>';
			}
			table += "</tr>";
		}
		table += "</tbody></table>";
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

<!-- widget grid -->
<section id="widget-grid" class="">

	<!-- row -->
	<div class="row">

		<!-- NEW WIDGET START -->
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-9">

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false">
				<!-- widget options:
				usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

				data-widget-colorbutton="false"
				data-widget-editbutton="false"
				data-widget-togglebutton="false"
				data-widget-deletebutton="false"
				data-widget-fullscreenbutton="false"
				data-widget-custombutton="false"
				data-widget-collapsed="true"
				data-widget-sortable="false"

				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-bug"></i> </span>
					<h2>IoE.m 연동 테스트</h2>

				</header>

				<!-- widget div-->
				<div>

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="widget-body">

						<form class="form-horizontal smart-form" id="form_" >
							<fieldset>
								<legend>기본 전송 정보</legend>
								<section>
									<label class="label">검침기No (device_id)</label>
									<label class="input">
										<input style="border-color:#7DC27D !important;" type="text" name="device_id" id="device_id" value="device_id" />
									</label>
								</section>
								<section>
									<label class="label">전력사용시간 (duration)</label>
									<label class="input">
										<input type="text" name="interval" value="5" />
									</label>
								</section>
								<section>
									<label class="label">연결장치 코드 (device_cd)</label>
									<label class="input">
										<input type="text" name="device_cd" value="tv"/>
									</label>
								</section>
								<section>
									<label class="label">IoE.m IPv4 주소 (ioe_m_ip)</label>
									<label class="input">
										<input type="text" name="ioe_m_ip" value="${client_ip }"/>
									</label>
								</section>
								<section>
									<label class="label">이벤트 참여여부 (optin)</label>
									<label class="input">
										<input type="text" name="optin" value="Y"/>
									</label>
								</section>
								<section>
									<label class="label">동 (dong)</label>
									<label class="input">
										<input type="text" name="building_number" value="508"/>
									</label>
								</section>
								<section>
									<label class="label">호 (ho)</label>
									<label class="input">
										<input type="text" name="house_number" value="1001"/>
									</label>
								</section>
							</fieldset>

							<fieldset>
								<legend>측정일자 및 전력사용량</legend>
								<section>
									<label class="label">
										<p>
										(※) 현재로 부터 12시간 전 부터 현재 데이타까지 반복 전송<br>
										(※) GREEN INPUT FIELD는 조회시 필수 파라미터.</p>
									</label>
								</section>
								<div class="row">
									<section class="col col-6">
										<label class="label">검침일시 (measure_date)</label>
									</section>
									<section class="col col-6">
										<label class="label">전력사용량 (power_consumption)</label>
									</section>
								</div>
								<div class="row">
									<section class="col col-6">
										<label class="input">
											<input style="border-color:#7DC27D !important;" type="text" name="measure_date" id="measure_date" value="2015-03-11 11:05:00"/>
										</label>
									</section>
									<section class="col col-6">
										<label class="input">
											<input type="text" name="power_consumption" value="22.4"/>
										</label>
									</section>
								</div>
								<div class="row">
									<section class="col col-6">
										<label class="input">
											<input type="text" name="measure_date" value="2015-03-11 11:10:00"/>
										</label>
									</section>
									<section class="col col-6">
										<label class="input">
											<input type="text" name="power_consumption" value="23.4"/>
										</label>
									</section>
								</div>
								<div class="row">
									<section class="col col-6">
										<label class="input">
											<input type="text" name="measure_date" value="2015-03-11 11:15:00"/>
										</label>
									</section>
									<section class="col col-6">
										<label class="input">
											<input type="text" name="power_consumption" value="24.4"/>
										</label>
									</section>
								</div>
							</fieldset>

							<footer>
								<button class="btn btn-primary" onclick="send();return false;">
									<i class="fa fa-edit"></i>
									전송
								</button>
								<button type="button" class="btn btn-default" onclick="search();return false;">
									조회
								</button>
							</footer>
							<div class="row">
								<section>
									<div id="rest1" class="table-responsive padding-10" ></div>
								</section>
							</div>
							<div class="row">
								<section class="padding-10">
									처리결과:
									<div class="log" ></div>
								</section>
							</div>
						</form>

					</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->

			</div>
			<!-- end widget -->

		</article>
		<!-- WIDGET END -->

	</div>

	<!-- end row -->

</section>
<!-- end widget grid -->
