<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/tag"%>
<%@ taglib prefix="src" tagdir="/WEB-INF/tags/src"%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<c:if test="${empty context}"><c:set var="context" value="/"/></c:if>
<c:set var="uuid" value="${sp:uuid() }"/>
<c:set var="def_rows" value="${UI_RESULT['row'] }"/>
<tag:el source="" param="${def_rows }"/>
<c:set var="row" value="${UI_RESULT.row }"/>
<c:set var="rows" value="${row }"/>

<c:set var="cu_id" value="${row.cu_id }" />
<c:set var="kep_cu_id" value="${row.kep_cu_id }" />
<c:set var="contract_type" value="${row.contract_type }" />
<c:set var="cu_name" value="${row.cu_name }" />
<c:set var="kma_zone" value="${row.kma_zone }" />
<c:set var="gridx" value="${row.gridx }" />
<c:set var="gridy" value="${row.gridy }" />
<c:set var="addr_sido" value="${row.addr_sido }" />
<c:set var="addr_gu" value="${row.addr_gu }" />
<c:set var="addr_dong" value="${row.addr_dong }" />
<c:set var="b_name" value="${row.b_name }" />
<c:set var="staff_info" value="${row.staff_info }" />
<c:set var="staff_name" value="${row.staff_name }" />
<c:set var="staff_tel" value="${row.staff_tel }" />
<c:set var="staff_mb" value="${row.staff_mb }" />
<c:set var="staff_email" value="${row.staff_email }" />
<c:set var="device_info" value="${row.device_info }" />
<c:set var="contract_power" value="${row.contract_power }" />
<c:set var="supply_type" value="${row.supply_type }" />
<c:set var="tran_dt" value="${row.tran_dt }" />
<c:set var="amr_dt" value="${row.amr_dt }" />
<c:set var="amr_no" value="${row.amr_no }" />
<c:set var="effective_ym" value="${row.effective_ym }" />
<c:set var="laying_dt" value="${row.laying_dt }" />
<c:set var="guidelines_digits" value="${row.guidelines_digits }" />
<c:set var="factory" value="${row.factory }" />
<c:set var="amr_owned" value="${row.amr_owned }" />

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
					<span class="widget-icon"> <i class="fa fa-user"></i> </span>
					<h2>수용가 정보</h2>

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

						<form class="form-horizontal smart-form" id="form_" action="" method="post" enctype="multipart/form-data" >
							<input type="hidden" name="ui_id" value="custView">
							<input type="hidden" name="action_type" value="">
							<fieldset>
								<legend>수용가 정보</legend>
								<div class="row">
									<section class="col col-6">
										<label class="label">고객번호</label>
										<label class="input">
											<input type="text" name="cu_id" value="${cu_id }"/>
										</label>
									</section>
									<section class="col col-6">
										<label class="label">한전고객번호</label>
										<label class="input">
											<input type="text" name="kep_cu_id" value="${kep_cu_id }"/>
										</label>
									</section>
								</div>

								<div class="row">
									<section class="col col-6">
										<label class="label">고객명</label>
										<label class="input">
											<input type="text" name="cu_name" value="${cu_name }"/>
										</label>
									</section>
									<section class="col col-6">
										<label class="label">계약종류</label>
										<label class="input">
											<input type="text" name="contract_type" value="${contract_type }"/>
										</label>
									</section>
								</div>

								<div class="row">
									<section class="col col-10">
										<label class="label">주소</label>
										<label class="input">
											<input type="text" name="address" value="${addr_sido } ${addr_gu } ${addr_dong }"/>
											<input type="hidden" name="addr_sido" value="${addr_sido }"/>
											<input type="hidden" name="addr_gu" value="${addr_gu }"/>
											<input type="hidden" name="addr_dong" value="${addr_dong }"/>
										</label>
									</section>
									<section class="col col-1">
										<label class="label">　</label>
										<div id="addr_search" class="btn btn-sm btn-primary" >
										<span class="fa fa-search"> 찾기</span>
										</div>

									</section>
								</div>

								<div class="row">
									<section class="col col-6">
										<label class="label">기상청 법정동 코드</label>
										<label class="input">
											<input type="text" name="kma_zone" value="${kma_zone }"/>
										</label>
									</section>
									<section class="col col-6">
										<label class="label">행정구역 좌표</label>
										<label class="input">
											<input type="text" name="gridxy" value="${gridx },${gridy }" />
											<input type="hidden" name="gridx" value="${gridx }" />
											<input type="hidden" name="gridy" value="${gridy }" />
										</label>
									</section>
								</div>

								<section>
									<label class="label">건물명 / 아파트명</label>
									<label class="input">
										<input type="text" name="b_name" value="${b_name }"/>
									</label>
								</section>

							</fieldset>
							
							<fieldset>
								<legend>담당자 정보</legend>
								<div class="row">
									<section class="col col-6">
										<label class="label">담당자 성명</label>
										<label class="input">
											<input type="text" name="staff_name" value="${staff_name }"/>
										</label>
									</section>
									<section class="col col-6">
										<label class="label">이메일</label>
										<label class="input">
											<input type="text" name="staff_email" value="${staff_email }"/>
										</label>
									</section>
								</div>

								<div class="row">
									<section class="col col-6">
										<label class="label">연락처(사무실)</label>
										<label class="input">
											<input type="text" name="staff_tel" value="${staff_tel }"/>
										</label>
									</section>
									<section class="col col-6">
										<label class="label">핸드폰</label>
										<label class="input">
											<input type="text" name="staff_mb" value="${staff_mb }"/>
										</label>
									</section>
								</div>
							</fieldset>

<!-- 							<fieldset> -->
<!-- 								<legend>기기 정보</legend> -->
<!-- 								<div class="row"> -->
<!-- 									<section class="col col-6"> -->
<!-- 										<label class="label">계약전력</label> -->
<!-- 										<label class="input"> -->
<%-- 											<input type="text" name="contract_power" value="${contract_power }"/> --%>
<!-- 										</label> -->
<!-- 									</section> -->
<!-- 									<section class="col col-6"> -->
<!-- 										<label class="label">공급방식</label> -->
<!-- 										<label class="input"> -->
<%-- 											<input type="text" name="supply_type" value="${supply_type }"/> --%>
<!-- 										</label> -->
<!-- 									</section> -->
<!-- 								</div> -->
<!-- 								<div class="row"> -->
<!-- 									<section class="col col-6"> -->
<!-- 										<label class="label">송전일자</label> -->
<!-- 										<label class="input"> -->
<%-- 											<src:mk_field name="tran_dt" isSearch="false" values="${row}" type="date"  valid="notempty,date" /> --%>
<!-- 										</label> -->
<!-- 									</section> -->
<!-- 									<section class="col col-6"> -->
<!-- 										<label class="label">원격 시작일</label> -->
<!-- 										<label class="input"> -->
<%-- 											<src:mk_field name="amr_dt" isSearch="false" values="${row}" type="date"  valid="notempty,date" /> --%>
<!-- 										</label> -->
<!-- 									</section> -->
<!-- 								</div> -->
<!-- 								<div class="row"> -->
<!-- 									<section class="col col-6"> -->
<!-- 										<label class="label">계기번호</label> -->
<!-- 										<label class="input"> -->
<%-- 											<input type="text" name="amr_no" value="${amr_no }"/> --%>
<!-- 										</label> -->
<!-- 									</section> -->
<!-- 									<section class="col col-6"> -->
<!-- 										<label class="label">실효년월</label> -->
<!-- 										<label class="input"> -->
<%-- 											<input type="text" name="effective_ym" value="${effective_ym }" maxlength="6"/> --%>
<!-- 										</label> -->
<!-- 									</section> -->
<!-- 								</div> -->
<!-- 								<div class="row"> -->
<!-- 									<section class="col col-6"> -->
<!-- 										<label class="label">부설일자</label> -->
<!-- 										<label class="input"> -->
<%-- 											<src:mk_field name="laying_dt" isSearch="false" values="${row}" type="date"  valid="notempty,date" /> --%>
<!-- 										</label> -->
<!-- 									</section> -->
<!-- 									<section class="col col-6"> -->
<!-- 										<label class="label">지침자리수</label> -->
<!-- 										<label class="input"> -->
<%-- 											<input type="text" name="guidelines_digits" value="${guidelines_digits }"/> --%>
<!-- 										</label> -->
<!-- 									</section> -->
<!-- 								</div> -->
<!-- 								<div class="row"> -->
<!-- 									<section class="col col-6"> -->
<!-- 										<label class="label">제작소</label> -->
<!-- 										<label class="input"> -->
<%-- 											<input type="text" name="factory" value="${factory }"/> --%>
<!-- 										</label> -->
<!-- 									</section> -->
<!-- 									<section class="col col-6"> -->
<!-- 										<label class="label">계기소유</label> -->
<!-- 										<label class="input"> -->
<%-- 											<input type="text" name="amr_owned" value="${amr_owned }"/> --%>
<!-- 										</label> -->
<!-- 									</section> -->
<!-- 								</div> -->
<!-- 							</fieldset> -->

							<footer>
								<button id="changeit" type="submit" class="btn btn-primary" >
								<i class="fa fa-edit"></i>
								수정
								</button>
								<button id="btn-cancel" type="button" class="btn btn-default" >
								취소
								</button>
							</footer>
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

<!-- Modal -->
<div id="popup_addr"></div>
<div class="modal fade" id="addrModal" tabindex="-1" role="dialog" aria-labelledby="addrModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="addrModalLabel">지역선택2</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Title" required />
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-primary">확인</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript">
	var months = [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월',
			'11월', '12월' ];
	var days = [ "일", "월", "화", "수", "목", "금", "토" ];
	var option = {
		monthNames : months,
		dayNamesMin : days,
		prevText : '<i class="fa fa-chevron-left"></i>',
		nextText : '<i class="fa fa-chevron-right"></i>',
		yearSuffix : '년',
		dateFormat : 'yy-mm-dd'
	};
	$('.datepicker').datepicker(option);
</script>

<!-- 주소찾기 -->
<script type="text/javascript">
	var isModify = false;
	var colors = ["success","warning","info","danger"];
	$(function() {
		//법정동코드로 날씨정보를 수집하기 위해 주소를 입력 처리를 한다.
		var popup_addr = $('#popup_addr');
		popup_addr.dialog({
			title : '지역선택',
			width : 150,
			autoOpen : false,
			modal : true
		});
		//시도
		$('#addr_search').click(function() {
			if(!isModify) return;
			$.getJSON('../proxy.sh?charset=utf-8&url=http://www.kma.go.kr/DFSROOT/POINT/DATA/top.json.txt', function( data ) {
				// mask_off();
				var html = '<table class="table"><tr><th>시/도</th></tr>';
				for(var i=0; i<data.length; i++){
					html += '<tr class='+colors[i%colors.length]+'><td onclick="setAddr1(\'' + data[i].code + '\',\'' + data[i].value + '\')">' + data[i].value + '</td></tr>';
				}
				popup_addr.html(html);
				popup_addr.dialog('open');
			});
		});
	});
	//구군
	var setAddr1 = function(code, val){
		$('input[name=address]').val('');
		$('input[name=addr_sido]').val(val);
		$('input[name=addr_gu]').val('');
		$('input[name=addr_dong]').val('');
		$('input[name=kma_zone]').val('');
		$('input[name=gridx]').val('');
		$('input[name=gridy]').val('');
		
		$.getJSON('../proxy.sh?charset=utf-8&url=http://www.kma.go.kr/DFSROOT/POINT/DATA/mdl.' + code + '.json.txt', function( data ) {
			var html = '<table class="table"><tr><th>구/군</th></tr>';
			for(var i=0; i<data.length; i++){
				html += '<tr class='+colors[i%colors.length]+'><td onclick="setAddr2(\'' + data[i].code + '\',\'' + data[i].value + '\')">' + data[i].value + '</td></tr>';
			}
			var popup_addr = $('#popup_addr');
			popup_addr.html(html);
			popup_addr.dialog('open');
		});
	}
	//동
	var setAddr2 = function(code, val){
		$('input[name=addr_gu]').val(val);
		$.getJSON('../proxy.sh?charset=utf-8&url=http://www.kma.go.kr/DFSROOT/POINT/DATA/leaf.' + code + '.json.txt', function( data ) {
			var html = '<table class="table"><tr><th>읍/면/동</th></tr>';
			for(var i=0; i<data.length; i++){
				html += '<tr class='+colors[i%colors.length]+'><td onclick="setAddr3(\'' + data[i].code + '\',\'' + data[i].value + '\',\'' + data[i].x + '\',\'' + data[i].y + '\')">' + data[i].value + '</td></tr>';
			}
			var popup_addr = $('#popup_addr');
			popup_addr.html(html);
			popup_addr.dialog('open');
		});
	}
	//법정동코드를 설정한다.
	var setAddr3 = function(code, val, x, y){
		$('input[name=addr_dong]').val(val);

		var sido = $('input[name=addr_sido]').val();
		var gu = $('input[name=addr_gu]').val();
		var dong = val;
		$('input[name=address]').val(sido+" "+gu+" "+dong);
		
		$('input[name=kma_zone]').val(code);
		$('input[name=gridx]').val(x);
		$('input[name=gridy]').val(y);
		$('input[name=gridxy]').val(x+","+y);
		$('#popup_addr').dialog('close');
	}
</script>

<script type="text/javascript">

	/* DO NOT REMOVE : GLOBAL FUNCTIONS!
	 *
	 * pageSetUp(); WILL CALL THE FOLLOWING FUNCTIONS
	 *
	 * // activate tooltips
	 * $("[rel=tooltip]").tooltip();
	 *
	 * // activate popovers
	 * $("[rel=popover]").popover();
	 *
	 * // activate popovers with hover states
	 * $("[rel=popover-hover]").popover({ trigger: "hover" });
	 *
	 * // activate inline charts
	 * runAllCharts();
	 *
	 * // setup widgets
	 * setup_widgets_desktop();
	 *
	 * // run form elements
	 * runAllForms();
	 *
	 ********************************
	 *
	 * pageSetUp() is needed whenever you load a page.
	 * It initializes and checks for all basic elements of the page
	 * and makes rendering easier.
	 *
	 */
	 
	 var disableAll = function() {
		var o = $('input[type=text]');
		o.parent('.input').addClass('state-disabled');
		o.prop('disabled',true);
		$('#addr_search').hide();
		$('#btn-cancel').hide();
		$('#btn-cancel').unbind('click');
		$('#btn-cancel').bind('click',function(){
			history.back();
		});
	 };

	 var enableAll = function() {
		var o = $('input[type=text]');
		o.parent('.state-disabled').removeClass('state-disabled');
		o.prop('disabled',false);
		$('#addr_search').show();		
		$('#address').prop('readonly',true);
		$('input[name=address]').prop('readonly',true);
		$('input[name=kma_zone]').prop('readonly',true);
		$('input[name=gridxy]').prop('readonly',true);
		$('input[name=action_type]').val('U');
		$('#btn-cancel').show();
		$('#btn-cancel').unbind('click');
		$('#btn-cancel').bind('click',function(){
			location.reload(); 
		});
		isModify = true;
	 };
	 
	 var changeBtn = function() {
		 var o = $('#changeit'); // only submit button
		 o.html('<i class="fa fa-save"></i> 저장');
		 enableAll();
	 }
	 
	 function form_submit() {
		var form = $('form');
		var url = '../action/-' + $('[name=ui_id]', form).val() + '.sh';
		var formData = form.serializeArray();
		$.post(url, formData, function(data, textStatus, xhr) {
			if (data.success) {
				location.reload();
			} else {
				alert("처리하는 중 오류가 발생하였습니다. \n문제가 지속되면 관리자에게 문의하세요");
			}
		});
	}

	// 수정/저장 버튼 클릭
	$('#changeit').click(function(e) {
		e.preventDefault();
		if (!isModify) {
			return changeBtn();
		}
		form_submit();
	})

	pageSetUp();

	/*
	 * ALL PAGE RELATED SCRIPTS CAN GO BELOW HERE
	 * eg alert("my home function");
	 * 
	 * var pagefunction = function() {
	 *   ...
	 * }
	 * loadScript("js/plugin/_PLUGIN_NAME_.js", pagefunction);
	 * 
	 * TO LOAD A SCRIPT:
	 * var pagefunction = function (){ 
	 *  loadScript(".../plugin.js", run_after_loaded);	
	 * }
	 * 
	 * OR
	 * 
	 * loadScript(".../plugin.js", run_after_loaded);
	 */

	// PAGE RELATED SCRIPTS
	// pagefunction
	var pagefunction = function() {

		// class switcher for radio and checkbox
		$('input[name="demo-switcher-1"]').change(
				function() {
					//alert($(this).val())
					$this = $(this);

					myNewClass = $this.attr('id');

					$('.demo-switcher-1 input[type="checkbox"]').removeClass();
					$('.demo-switcher-1 input[type="checkbox"]').addClass(
							"checkbox " + myNewClass);

					$('.demo-switcher-1 input[type="radio"]').removeClass();
					$('.demo-switcher-1 input[type="radio"]').addClass(
							"radiobox " + myNewClass);

				});

	};

	// end pagefunction

	// run pagefunction on load
	pagefunction();
	disableAll();

</script>
