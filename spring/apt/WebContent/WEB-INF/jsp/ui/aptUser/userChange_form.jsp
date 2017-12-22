<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/sp.tld"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/tag"%>
<%@ taglib prefix="src" tagdir="/WEB-INF/tags/src"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:if test="${empty context}"><c:set var="context" value="/"/></c:if>
<c:set var="uuid" value="${sp:uuid()}"/>
<c:set var="def_rows" value="${UI_RESULT['row']}"/>
<c:set var="rows" value="${UI_RESULT.row}"/>
<c:set var="row" value="${rows }"/>

<c:set var="user_uuid" value="${row.user_uuid }" />
<c:set var="user_id" value="${row.user_id }" />
<c:set var="user_name" value="${row.user_name }" />
<c:set var="user_pw" value="${row.user_pw }" />
<c:set var="new_pwd" value="${row.new_pwd }" />
<c:set var="confirm_pwd" value="${row.confirm_pwd }" />
<c:set var="access_cls" value="${row.access_cls }" />
<c:set var="user_mb" value="${row.user_mb }" />
<c:set var="user_email" value="${row.user_email }" />

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
					<h2>개인정보 관리</h2>

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
							<input type="hidden" name="ui_id" value="userChange_form">
							<input type="hidden" name="action_type" value="">
							<fieldset>
								<legend>기본 정보</legend>
								<section>
									<label class="label">UUID</label>
									<label class="input">
										<input type="text" name="user_uuid" value="${user_uuid }" />
									</label>
								</section>
								<section>
									<label class="label">사용자ID</label>
									<label class="input">
										<input type="text" name="user_id" value="${user_id }" />
									</label>
								</section>
								<section>
									<label class="label">이름</label>
									<label class="input">
										<input type="text" name="user_name" value="${user_name }"/>
									</label>
								</section>

								<div class="passwd">
									<section>
										<label class="label">현재 패스워드</label>
										<label class="input">
										<src:mk_field name="user_pw" isSearch="false" values="${row }" type="password"  link="" link_type="linkLoad" valid="notempty" valid2=""  label="현재 패스워드"/>
										</label>
									</section>
									<section>
										<label class="label">신규 패스워드</label>
										<label class="input">
										<src:mk_field name="new_pwd" isSearch="false" values="${row }" type="password"  link="" link_type="linkLoad" valid="" valid2="password"  label="신규 패스워드"/>
										</label>
									</section>
									<section>
										<label class="label">패스워드 확인</label>
										<label class="input">
										<src:mk_field name="confirm_pwd" isSearch="false" values="${row }" type="password"  link="" link_type="linkLoad" valid="" valid2="equals:new_pwd"  label="패스워드 확인"/>
										</label>
									</section>
								</div>
							</fieldset>
							
							<fieldset>
								<legend>부가 정보</legend>
								<section>
									<label class="label">사용자 그룹</label>
									<div class="inline-group">
										<c:set var="my_group">${session.access_cls }</c:set>
										<label class="radio state-disabled">
											<input type="radio" name="access_cls" <c:if test="${my_group=='A'}">checked</c:if> disabled />
											<i></i>관리자
										</label>
										<label class="radio state-disabled">
											<input type="radio" name="access_cls" <c:if test="${my_group=='U'}">checked</c:if> disabled />
											<i></i>일반사용자
										</label>
										<label class="radio state-disabled">
											<input type="radio" name="access_cls" <c:if test="${my_group=='S'}">checked</c:if> disabled />
											<i></i>시스템관리자
										</label>
									</div>
								</section>
								<section>
									<label class="label">핸드폰</label>
									<label class="input">
										<input type="text" name="user_mb" value="${user_mb }"/>
									</label>
								</section>
								<section>
									<label class="label">이메일</label>
									<label class="input">
										<input type="text" name="user_email" value="${user_email }"/>
									</label>
								</section>
							</fieldset>

							<footer>
								<button id="changeit" type="submit" class="btn btn-primary" >
								<i class="fa fa-edit"></i>
								수정
								</button>
								<button type="button" class="btn btn-default" onclick="javascript:form_cancel();">
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

<script src="../js/valid.js" type="text/javascript"></script>
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
		
		o = $('.passwd');
		o.hide();
	
	 };
	 
	 var isModify = false;

	 var enableAll = function() {
		var o = $('input[type=text]');
		o.parent('.state-disabled').removeClass('state-disabled');
		o.prop('disabled',false);
		
		o = $('input[name=user_uuid]');
		o.parent('label').addClass('state-disabled');
		o.prop('disabled',true);

		o = $('input[name=user_id]');
		o.parent('label').addClass('state-disabled');
		o.prop('disabled',true);

		o = $('.passwd');
		o.show();

		$('input[name=action_type]').val('U');
		isModify = true;
	 };
	 
	 var changeBtn = function() {
		 var o = $('#changeit'); // only submit button
		 o.html('<i class="fa fa-save"></i> 저장');
		 enableAll();
	 }
	 

	// js/valid.js override 
	var getFieldName = function(ctl) {
		var label = ctl.attr('placeholder');
		if (label.length > 0) {
			return label;
		}
		return (ctl.closest('section').closest('label')).text();
	}

	// is same redirect operation
	function form_cancel() {
		location.reload();
	}

	function form_submit() {
		var form = $('form');
		//폼 정합성 체크
		if (!valid(form)) {
			return;
		}
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
		disableAll();
	};

	// end pagefunction

	// run pagefunction on load
	pagefunction();
</script>