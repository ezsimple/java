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
<c:set var="def_rows" value="${UI_RESULT['list']}"/>
<c:set var="rows" value="${UI_RESULT.list}"/>

<!-- widget grid -->
<section id="widget-grid" class="">

	<!-- row -->
	<div class="row">

		<!-- NEW WIDGET START -->
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
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
					<span class="widget-icon"> <i class="fa fa-table"></i> </span>
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
					<div class="widget-body no-padding">
						<form id="form_" action="" method="post" enctype="multipart/form-data">
						<input type="hidden" name="ui_id" value="userList"> 
						<input type="hidden" name="action_type" value="">

						<table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
							<thead>			                
								<tr>
									<th>UUID</th>
									<th>이름</th>
									<th>사용자그룹</th>
									<th>핸드폰</th>
									<th>이메일</th>
                                    <th>사용자상태</th>
                                    <th></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="row" items="${rows }" varStatus="status">
								<tr>
									<td><src:mk_field name="user_uuid" values="${row }" isSearch="false" type="text_view" link="'userView',{user_uuid:'${'$'}{user_uuid}'}"  link_type="linkPopup" valid="" valid2=""  keyValid="" maxlength="40" label="유저UUID" /> </td>
									<td><src:mk_field name="user_name" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="50" label="이름" /> </td>
									<td><src:mk_field name="access_cls" values="${row }" isSearch="false" type="user_group" link=""  link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="50" label="사용자그룹" /> </td>
									<td><src:mk_field name="user_mb" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="20" label="핸드폰" /> </td>
									<td><src:mk_field name="user_email" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="50" label="이메일" /> </td>
                                    <td>
                                        <c:set var="user_status">
                                            <src:mk_field name="user_status" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="50" label="사용자상태" />
                                        </c:set>
                                        <c:choose>
                                            <c:when test="${user_status == 'Y'}">
                                                <span class="badge bg-color-blue">활동중</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-color-red">사용중지</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
									<td>
										<c:choose>
											<c:when test="${session.user_uuid == row.user_uuid }">
												<a class="btn btn-info disabled btn-xs" onclick="linkEditPopup(this,'userEdit',{user_uuid:'${row.user_uuid }'})">수정</a>
                                                <c:choose>
                                                    <c:when test="${row.user_status == 'Y'}">
                                                        <a class="btn btn-danger disabled btn-xs" onclick="linkStopPopup(this,'userEdit',{user_uuid:'${row.user_uuid }','access_cls':'${row.access_cls }'})">중지</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a class="btn btn-success disabled btn-xs" onclick="linkActPopup(this,'userEdit',{user_uuid:'${row.user_uuid }','access_cls':'${row.access_cls }'})">활성</a>
                                                    </c:otherwise>
                                                </c:choose>

											</c:when>
											<c:otherwise>
                                                <c:choose>
                                                    <c:when test="${session.access_cls == 'A' || session.access_cls == 'S'}">
                                                        <a class="btn btn-info btn-xs" onclick="linkEditPopup(this,'userEdit',{user_uuid:'${row.user_uuid }'})">수정</a>
                                                        <c:choose>
                                                            <c:when test="${row.user_status == 'Y'}">
                                                                <a class="btn btn-danger btn-xs" onclick="linkStopPopup(this,'userEdit',{user_uuid:'${row.user_uuid }','access_cls':'${row.access_cls }'})">중지</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a class="btn btn-success btn-xs" onclick="linkActPopup(this,'userEdit',{user_uuid:'${row.user_uuid }','access_cls':'${row.access_cls }'})">활성</a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a class="btn btn-info disabled btn-xs" onclick="linkEditPopup(this,'userEdit',{user_uuid:'${row.user_uuid }'})">수정</a>
                                                        <c:choose>
                                                            <c:when test="${row.user_status == 'Y'}">
                                                                <a class="btn btn-danger disabled btn-xs" onclick="linkStopPopup(this,'userEdit',{user_uuid:'${row.user_uuid }','access_cls':'${row.access_cls }'})">중지</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a class="btn btn-success disabled btn-xs" onclick="linkActPopup(this,'userEdit',{user_uuid:'${row.user_uuid }','access_cls':'${row.access_cls }'})">활성</a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:otherwise>
                                                </c:choose>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
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

	<!-- end row -->

</section>
<!-- end widget grid -->

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
	 */
	
	// PAGE RELATED SCRIPTS
	
	// pagefunction	
	var pagefunction = function() {
		//console.log("cleared");
		
		/* // DOM Position key index //
		
			l - Length changing (dropdown)
			f - Filtering input (search)
			t - The Table! (datatable)
			i - Information (records)
			p - Pagination (paging)
			r - pRocessing 
			< and > - div elements
			<"#id" and > - div with an id
			<"class" and > - div with a class
			<"#id.class" and > - div with an id and class
			
			Also see: http://legacy.datatables.net/usage/features
		*/	

		/* BASIC ;*/
			var responsiveHelper_dt_basic = undefined;
			var responsiveHelper_datatable_fixed_column = undefined;
			var responsiveHelper_datatable_col_reorder = undefined;
			var responsiveHelper_datatable_tabletools = undefined;
			
			var breakpointDefinition = {
				tablet : 1024,
				phone : 480
			};

			$('#dt_basic').dataTable({
				"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"+
					"t"+
					"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
				"autoWidth" : true,
				"preDrawCallback" : function() {
					// Initialize the responsive datatables helper once.
					if (!responsiveHelper_dt_basic) {
						responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
					}
				},
				"rowCallback" : function(nRow) {
					responsiveHelper_dt_basic.createExpandIcon(nRow);
				},
				"drawCallback" : function(oSettings) {
					responsiveHelper_dt_basic.respond();
				}
			});

		/* END BASIC */
		
		/* COLUMN FILTER  */
	    var otable = $('#datatable_fixed_column').DataTable({
	    	//"bFilter": false,
	    	//"bInfo": false,
	    	//"bLengthChange": false
	    	//"bAutoWidth": false,
	    	//"bPaginate": false,
	    	//"bStateSave": true // saves sort state using localStorage
			"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6 hidden-xs'f><'col-sm-6 col-xs-12 hidden-xs'<'toolbar'>>r>"+
					"t"+
					"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
			"autoWidth" : true,
			"preDrawCallback" : function() {
				// Initialize the responsive datatables helper once.
				if (!responsiveHelper_datatable_fixed_column) {
					responsiveHelper_datatable_fixed_column = new ResponsiveDatatablesHelper($('#datatable_fixed_column'), breakpointDefinition);
				}
			},
			"rowCallback" : function(nRow) {
				responsiveHelper_datatable_fixed_column.createExpandIcon(nRow);
			},
			"drawCallback" : function(oSettings) {
				responsiveHelper_datatable_fixed_column.respond();
			}		
		
	    });
	    
	    // custom toolbar
	    $("div.toolbar").html('<div class="text-right"><img src="img/logo.png" alt="SmartAdmin" style="width: 111px; margin-top: 3px; margin-right: 10px;"></div>');
	    	   
	    // Apply the filter
	    $("#datatable_fixed_column thead th input[type=text]").on( 'keyup change', function () {
	    	
	        otable
	            .column( $(this).parent().index()+':visible' )
	            .search( this.value )
	            .draw();
	            
	    } );
	    /* END COLUMN FILTER */   
    
		/* COLUMN SHOW - HIDE */
		$('#datatable_col_reorder').dataTable({
			"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"+
					"t"+
					"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
			"autoWidth" : true,
			"preDrawCallback" : function() {
				// Initialize the responsive datatables helper once.
				if (!responsiveHelper_datatable_col_reorder) {
					responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper($('#datatable_col_reorder'), breakpointDefinition);
				}
			},
			"rowCallback" : function(nRow) {
				responsiveHelper_datatable_col_reorder.createExpandIcon(nRow);
			},
			"drawCallback" : function(oSettings) {
				responsiveHelper_datatable_col_reorder.respond();
			}			
		});
		
		/* END COLUMN SHOW - HIDE */

		/* TABLETOOLS */
		$('#datatable_tabletools').dataTable({
			
			// Tabletools options: 
			//   https://datatables.net/extensions/tabletools/button_options
			"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"+
					"t"+
					"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
	        "oTableTools": {
	        	 "aButtons": [
	             "copy",
	             "csv",
	             "xls",
	                {
	                    "sExtends": "pdf",
	                    "sTitle": "SmartAdmin_PDF",
	                    "sPdfMessage": "SmartAdmin PDF Export",
	                    "sPdfSize": "letter"
	                },
	             	{
                    	"sExtends": "print",
                    	"sMessage": "Generated by SmartAdmin <i>(press Esc to close)</i>"
                	}
	             ],
	            "sSwfPath": "js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
	        },
			"autoWidth" : true,
			"preDrawCallback" : function() {
				// Initialize the responsive datatables helper once.
				if (!responsiveHelper_datatable_tabletools) {
					responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper($('#datatable_tabletools'), breakpointDefinition);
				}
			},
			"rowCallback" : function(nRow) {
				responsiveHelper_datatable_tabletools.createExpandIcon(nRow);
			},
			"drawCallback" : function(oSettings) {
				responsiveHelper_datatable_tabletools.respond();
			}
		});
		
		/* END TABLETOOLS */

	};

	// load related plugins
	
	loadScript("../js/plugin/datatables/jquery.dataTables.min.js", function(){
		loadScript("../js/plugin/datatables/dataTables.colVis.min.js", function(){
			loadScript("../js/plugin/datatables/dataTables.tableTools.min.js", function(){
				loadScript("../js/plugin/datatables/dataTables.bootstrap.min.js", function(){
					loadScript("../js/plugin/datatable-responsive/datatables.responsive.min.js", pagefunction)
				});
			});
		});
	});

	// Set Modal Title
	var _modal_title = '사용자 정보'

    // 중지용 Modal
    var linkStopPopup = function (ele, ui_id, data) {
        var confirmModal = new AConfirm({
            title: '확인요청',
            msg : '사용중지 하시겠습니까?',
            url : '../piece/-'+ui_id+'-bf.sh',
            okClick : function() {
                var modal = $('.modal-body',this.element);
                $form = $('<form method="post" enctype="multipart/form-data"></form>');
                $('<input>').attr({type:'hidden', name:'ui_id', value:ui_id}).appendTo($form);
                $('<input>').attr({type:'hidden', name:'action_type', value:'U'}).appendTo($form);
                $('<input>').attr({type:'hidden', name:'user_status', value:'N'}).appendTo($form);
                $.each(data, function(k,v) {
                    $('<input>').attr({type:'hidden', name:k, value:v}).appendTo($form);
                });
                $form.appendTo(modal);
                var url = '../action/-'+ui_id+'.sh';
                var formData = $form.serializeArray();
                $.post(url,formData,function(data,textStatus,xhr) {
                    (data.success)?location.reload():alert("처리하는 중 오류가 발생하였습니다. \n문제가 지속되면 관리자에게 문의하세요");
                })
            }
        });
        confirmModal.show()
        _modal = confirmModal;
    }
    // 활성용 Modal
    var linkActPopup = function (ele, ui_id, data) {
        var confirmModal = new AConfirm({
            title: '확인요청',
            msg : '활성화 하시겠습니까?',
            url : '../piece/-'+ui_id+'-bf.sh',
            okClick : function() {
                var modal = $('.modal-body',this.element);
                $form = $('<form method="post" enctype="multipart/form-data"></form>');
                $('<input>').attr({type:'hidden', name:'ui_id', value:ui_id}).appendTo($form);
                $('<input>').attr({type:'hidden', name:'action_type', value:'U'}).appendTo($form);
                $('<input>').attr({type:'hidden', name:'user_status', value:'Y'}).appendTo($form);
                $.each(data, function(k,v) {
                    $('<input>').attr({type:'hidden', name:k, value:v}).appendTo($form);
                });
                $form.appendTo(modal);
                var url = '../action/-'+ui_id+'.sh';
                var formData = $form.serializeArray();
                $.post(url,formData,function(data,textStatus,xhr) {
                    (data.success)?location.reload():alert("처리하는 중 오류가 발생하였습니다. \n문제가 지속되면 관리자에게 문의하세요");
                })
            }
        });
        confirmModal.show()
        _modal = confirmModal;
    }
</script>
