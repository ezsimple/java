<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag"  tagdir="/WEB-INF/tags/tag" %> 
<%@ taglib prefix="src"  tagdir="/WEB-INF/tags/src" %> 
<div monitor="1" class="monitor monitor1 auto_height" style="margin-top:5px;  ${isMobile ? 'width:99%' : 'width:1000px'};">
	<div style="float:left; width: ${isMobile ? '100%' : '230px'}; height:250px; margin:0 5px 5px 0; text-align: center; border:1px solid #222222;">
		<div class="home_tit">이벤트 정보</div>
		<div style="height: 100px;">
			<div id="has_event"  style="width: 100%;display: none;">
				<table style="width: 100%;">
					<tr>
						<td valign="middle" style="font-size: 13px;font-weight: bold; height:100px; line-height: 16px;">
							<img id="active_event" src="../images/apt/active-icon.png" ><img id="far_event" src="../images/apt/far-icon.png" >
						</td>
					</tr>
					<tr>
						<td style="font-size: 13px;font-weight: bold;line-height: 16px;">
							
							<div id="event_start_date" style="font-size: 13px;color: red; "></div><br>
							상태 : <span id="event_status"></span><br><br>
							감축량 : <span id="tot_reduction"></span>kW<br>
							지속시간 : <span id="event_duration"></span>
						</td>
					</tr>
				</table>
			</div>
			<div id="no_event" style=" padding-top:10px; display: none;">
				<img src="../images/apt/happy-icon.png" ><br>
				이벤트 없음<br><br>
				<img src="../images/apt/laptop-battery-icon.png" width="32">지난 이벤트
				<!-- load시 : /piece/-mainEvent-f.sh -->
				<div id="mainEvent"  style="padding: 3px;"></div>
			</div>
		</div>
	</div>
	
	<div class="chart" style="float:left; width: ${isMobile ? '100%' : '760px'};height:250px; margin:0 0 5px 0; border:1px solid #222222;">
		<div class="home_tit" >
			CBL 기준 전일 대비 전력 사용량 그래프
		</div>
		<br>
		<src:import uiId="powerCbl_sts" />
		<div style="display:${isMobile ? 'none' : ''}; position: relative; top:-208px; left: 725px;" class="button" icon_primary="ui-icon-document" text="false" onclick="showSheet(this,'powerCbl_sts')">데이타 보기</div>
	</div>


	<div style="float:left; width: ${isMobile ? '100%' : '230px'}; height:250px; margin:0 5px 5px 0; text-align: center; border:1px solid #222222;">
		<div class="home_tit" >최근 검침 상태</div>
		
		<div  style="padding: 10px;">
			<br><img src="../images/apt/smart meter.jpg" width="70">
			(IoE.M 최종 정보)<br><br>
			<table class="lst">
				<tr><th colspan="2"><img id="ioem_status" src="../images/icon/green-icon.png">&nbsp;최근 검침 시간</th></tr>
				<tr><td colspan="2"><span id="last_check_time"></span><br></td></tr>
				<tr><th>당일사용량</th><th>미터기</th></tr>
				<tr><td><span id="today_value"></span>kWh</td><td><span id="meter_cnt"></span>개</td></tr>
			</table>
			<br>
		</div> 			
	</div>
	<div class="chart" style="float:left; width: ${isMobile ? '100%' : '760px'};height:250px; margin:0 0px 5px 0; border:1px solid #222222;">
		<div class="home_tit" >
			스마트미터 전력 사용량 그래프
		</div>
		<br>
		<src:import uiId="powerApt_sts" />
		<div style="display:${isMobile ? 'none' : ''}; position: relative; top:-208px; left: 725px;" class="button" icon_primary="ui-icon-document" text="false" onclick="showSheet(this,'powerApt_sts')">데이타 보기</div>
	</div>


	<div style="float:left; width: ${isMobile ? '100%' : '230px'}; height:250px;  margin:0 5px 5px 0; text-align: center; border:1px solid #222222;">
		<div class="home_tit" >날씨정보</div>
		<br>
		<img id="w_icon" width="36" src="http://static.naver.net/weather/images/w_icon/w_l21.gif">
		<span id="wfkor" style="font-size: 14px;font-weight:bold;"></span>
		<span id="temp" style="font-size: 14px;font-weight:bold;"></span><span style="font-size: 14px;font-weight:bold;">℃</span>
		<br>(<span id="addr_dong" ></span>)
		<div id="weatherInfo">
			<src:import uiId="mainTempGraph" />
		</div>
	</div>
	<div style="float:left; width: ${isMobile ? '100%' : '230px'}; height:250px;  margin:0 5px 5px 0; text-align: center; border:1px solid #222222;">
		<div class="home_tit" >한전 예보 단계</div>
		<div id="tabs"  style="margin: 0;padding: 0;height: 220px;text-align: center;">
			<ul>
				<li><a href="#tabs-1"> 예보 </a></li>
				<li><a href="#tabs-2"> 피크 </a></li>
				<li><a href="#tabs-3"> 예비 </a></li>
			</ul>
			<div id="tabs-1" class="tab_kep" style=" margin: 0 auto 0 auto; padding:0;  width: ${isMobile ? '100%' : '190px'};">
			</div>
		 	<div id="tabs-2" class="tab_kep" style=" margin: 0 auto 0 auto; padding:0;  width: ${isMobile ? '100%' : '190px'}; line-height:7px;s">
			</div>
		 	<div id="tabs-3" class="tab_kep" style=" margin: 0 auto 0 auto; padding:0;  width: ${isMobile ? '100%' : '190px'};">
			</div>
		</div>
	</div>

	<div id="kep_chart" style="display:${isMobile ? 'none' : ''}; float:left;width: 523px;height:250px; margin:0 0 5px 0; text-align: center; border:1px solid #222222;">
		<div class="home_tit" >한전 실시간 전력수급 그래프</div>
		<img src="http://power.kpx.or.kr/ems_chart_compare.php" width="500" height="225">
	</div>
	
</div>
<div id="kpe_page" style="display: none;width: 2px;"></div>

<style>
.graph_left {
	margin-left:5px;
    float: left;
    width: 90px;
}
.graph_left_bar {
    background: none repeat scroll 0 0 #a8aebc;
    height: 73px;
    margin: auto;
    position: relative;
    width: 11px;
}
.graph_right {
    float: left;
    width: 90px;
}
.graph_right_bar {
    background: none repeat scroll 0 0 #a8aebc;
    height: 73px;
    margin: auto;
    position: relative;
    width: 11px;
}
.graph_left_bar img {
    bottom: 0;
    left: 0;
    position: absolute;
}
.graph_right_bar img {
    bottom: 0;
    left: 0;
    position: absolute;
}
</style>
<script type="text/javascript">
	$(function() {
		loadData();
		setInterval(loadData,20000);
		//지난이벤트 로딩
		$('#mainEvent').load('/piece/-mainEvent-f.sh');
		//한전예보정보 로딩
		$('#kpe_page').load('../proxy.sh?charset=euc-kr&url=http://www.kpx.or.kr/KOREAN/htdocs/forecast/forecast.htm', {}, function(a,b,c){
			var html = '<br>' + $('div[class=level]').html();
			$('#tabs-1').append(html);
			html = '<br>' + $('div[class=time]').html();
			$('#tabs-2').append(html);
			html = '<br><div style="width:200px;margin: 0 auto 0 auto;"><br>' + $('div[class=max]').html() + '</div>';
			$('#tabs-3').append(html);
			$('#kpe_page').html('');
		});
		$( "#tabs" ).tabs();
		//차트기본 제공되는 버튼 숨김
		$('.sheet_toggle_button').hide();
	});
	
	function loadData(){
		loadEvent();
		loadLoem();
		loadWeather();
		
	}
	//이벤트알람 아이콘 효과용
	var intervalID;
	//이벤트 로딩
	function loadEvent(){
		$.getJSON( "../ai/aptEvent.curentEvent", function( data ) {
			if(data.row.event_status){
				
				if(data.row.event_status=='active'){
					clearInterval(intervalID);

					intervalID = setInterval(function(){
						$('#active_event').fadeToggle( "slow", "linear" )
					},1000);
					
					$('#far_event').hide();
				}else{
					$('#active_event').hide();
					$('#far_event').show();
				}
				$('#event_status').text(data.row.event_status=='active' ? '진행중' : '이벤트 발령');
				$('#event_start_date').text(data.row.start_date);
				$('#tot_reduction').text(data.row.tot_reduction);
				$('#event_duration').text(data.row.tot_duration + ' 분');
				$('#no_event').hide();
				$('#has_event').show();
			}else{
				$('#has_event').hide();
				$('#no_event').show();
			}
		});
	}
	//검침정보 로딩
	function loadLoem(){
		$.getJSON( "../ai/aptPower.main", function( data ) {
			$('#last_check_time').text(data.row.measure_date);
			$('#today_value').text(data.row.today_value);
			$('#meter_cnt').text(data.row.meter_cnt);
			$('#ioem_status').attr('src', data.row.ioem_status=='OK' ? '../images/icon/green-icon.png' : '../images/icon/err-icon.png');
			
		});
	}
	//날씨정보 로딩
	function loadWeather(){
		$.getJSON( "../ai/weather.mainIcon", function( data ) {
			$('#w_icon').attr('src', '../images/weather/DB0' + data.row.w_icon + '_B.png');
			$('#temp').text(data.row.temp);
			$('#wfkor').text(data.row.wfkor);
			$('#addr_dong').text(data.row.addr_gu + ' ' + data.row.addr_dong);
		});
	}
	var _sheet = {};
	//그래프정보 표로 보여주기
	function showSheet(me, id){
		var dialog = _sheet[id];
		
		if(dialog){
			dialog.dialog('open');
			return;
		}
		
		var chart = $(me).closest('.chart');
		//var sheet = $('table', chart.find('.sheet'));
		var sheet = chart.find('.sheet');
		var title = chart.find('.home_tit').text();
		dialog = sheet.dialog({
			title: title,
			height:500,
			modal: true
		});
		
		_sheet[id] = dialog;
	}
</script> 

