<%@ page import="java.util.ArrayList" %>
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
<c:set var="last" value="${UI_RESULT.last}"/>
<c:set var="danzi" value="${UI_RESULT.danzi}"/>
<c:set var="hosu" value="${UI_RESULT.hosu}"/>
<%// 동호별-일별통계 데이타 %>
<c:set var="daily" value="${UI_RESULT['daily']}"/>
<c:set var="dailyData" value="datetime,전력사용량\n"/>
<c:forEach var="row" items="${daily}" varStatus="status">
    <c:if test="${!empty row }">
        <c:set var="dailyData" value="${dailyData }${row.measure_time },${row.power_consumption}\n"/>
    </c:if>
</c:forEach>
<%-- <c:out value="${dailyData }"/> --%>

<%// 동호별-월별통계 데이타 %>
<c:set var="monthly" value="${UI_RESULT['monthly']}"/>
<c:set var="monthlyData" value=""/>
<c:forEach var="row" items="${monthly}" varStatus="status">
    <c:if test="${!empty row }">
        <c:set var="monthlyData" value="${monthlyData }${status.first?'':','}${row.power_consumption}" />
    </c:if>
</c:forEach>
<%--<c:out value="${monthlyData }" />--%>

<%// 검색일자,동,호가 없을시 최종 초기화 셋팅 %>
<c:set var="search_measure_date" value='<%= request.getParameter("search_measure_date")%>' />
<c:if test="${empty search_measure_date}">
    <c:set var="search_measure_date" value="${last[0].day }"/>
</c:if>
<c:set var="search_dong" value='<%= request.getParameter("search_dong")%>' />
<c:if test="${empty search_dong}">
    <c:set var="search_dong" value="${danzi[0].dong }"/>
</c:if>
<c:set var="search_ho" value='<%= request.getParameter("search_ho")%>' />
<c:if test="${empty search_ho}">
    <c:set var="search_ho" value="${hosu[0].ho }"/>
</c:if>

<!-- widget grid -->
<section id="widget-grid" class="">

	<!-- row -->
	<div class="row">

		<!-- NEW WIDGET START -->
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-colorbutton="false" data-widget-fullscreenbutton="false" data-widget-editbutton="false" data-widget-sortable="false">
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
                    <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                    <h2>
                        일별 전력 사용량 그래프
                        <fmt:parseDate value='${search_measure_date }' var='search_measure_date' pattern="yyyy-MM-dd" scope="page"/>
                        <fmt:formatDate value="${search_measure_date }" type="date" var="search_measure_date" pattern="yyyy-MM-dd"/>
                        (측정일 : ${search_measure_date } / 단위: kWh)
                    </h2>

                </header>

				<!-- widget div-->
				<div>

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->
						<input class="form-control" type="text">
					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
                    <div class="widget-body">
                        <div id="daily_graph" style="width:100%; height:300px;">일별전력사용량그래프</div>
                    </div>

                    <!-- end widget content -->

				</div>
				<!-- end widget div -->

			</div>
			<!-- end widget -->


            <!-- Widget ID (each widget will need unique ID)-->
            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-1" data-widget-colorbutton="false" data-widget-fullscreenbutton="false" data-widget-editbutton="false" data-widget-sortable="false">
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
                    <span class="widget-icon"> <i class="fa fa-bar-chart-o"></i> </span>
                    <h2>
                        월별 전력 사용량 그래프
                        <fmt:parseDate value='${search_measure_date }' var='search_measure_year' pattern="yyyy" scope="page"/>
                        <fmt:formatDate value="${search_measure_year }" type="date" var="search_measure_year" pattern="yyyy"/>
                        (${search_measure_year } 년 / 단위: kWh)
                    </h2>

                </header>

                <!-- widget div-->
                <div>

                    <!-- widget edit box -->
                    <div class="jarviswidget-editbox">
                        <!-- This area used as dropdown edit box -->
                        <input class="form-control" type="text">
                    </div>
                    <!-- end widget edit box -->

                    <!-- widget content -->
                    <div class="widget-body">
                        <canvas id="monthly_graph" height="100">월별전력사용량그래프</canvas>
                    </div>

                    <!-- end widget content -->

                </div>
                <!-- end widget div -->

            </div>
            <!-- end widget -->


			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-2" data-widget-editbutton="false">
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
					<h2>SmartMeter 검침정보 (최종검침일 : <src:mk_field name="day" values="${last[0] }" isSearch="false" type="date_view" />)</h2>

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
                        <input type="hidden" name="ui_id" value="search_power_list">
                        <input type="hidden" name="action_type" value="">
						<table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
							<thead>			                
								<tr>
									<th class="hasinput icon-addon">
										<fmt:parseDate value='${search_measure_date }' var='search_measure_date' pattern="yyyy-MM-dd" scope="page"/>
										<fmt:formatDate value="${search_measure_date }" type="date" var="search_measure_date" pattern="yyyy-MM-dd"/>
										<input id="dateselect_filter" name="search_measure_date" type="text" placeholder="검침일" class="form-control datepicker" data-dateformat="yy-mm-dd" value="${search_measure_date }">
										<label for="dateselect_filter" class="glyphicon glyphicon-calendar no-margin padding-top-15" rel="tooltip" title="" data-original-title="Filter Date"></label>
									</th>
									<th class="hasinput select">
                                        <select class="form-control select" name="search_dong" >
											<c:forEach var="row" items="${danzi }" varStatus="status">
												<option value="${row.dong}" ${row.dong==search_dong?'selected':''} >${row.dong } 동</option>
											</c:forEach>
										</select>
									</th>
									<th class="hasinput select">
                                        <select class="form-control select" name="search_ho" >
                                            <c:forEach var="row" items="${hosu }" varStatus="status">
                                                <option value="${row.ho}" ${row.ho==search_ho?'selected':''} >${row.ho } 호</option>
                                            </c:forEach>
                                        </select>
                                    </th>
									<th class="hasinput"></th>
									<th class="hasinput"></th>
									<th class="hasinput"></th>
									<th class="hasinput"></th>
									<th class="hasinput"></th>
								</tr>
								<tr>
									<th>검침일</th>
									<th>동</th>
									<th>호</th>
									<th>검침기No</th>
									<th>검침간격(분)</th>
									<th>전력사용량(kWh)</th>
									<th>연결기기</th>
									<th>참여여부</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="row" items="${rows }" varStatus="status">
								<tr>
									<td><src:mk_field name="measure_date" values="${row }" isSearch="false" type="datetime_view" link=""  link_type="linkLoad" valid="notempty" valid2=""  keyValid="" maxlength="19" label="검침일" /> </td>
									<td><src:mk_field name="dong" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="1" label="동" /> </td>
									<td><src:mk_field name="ho" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="1" label="호" /> </td>
									<td><src:mk_field name="device_id" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="30" label="검침기No" /> </td>
									<td><src:mk_field name="duration" values="${row }" isSearch="false" type="number_view" link=""  link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="14" label="검침간격(분)" /> </td>
									<td><src:mk_field name="power_consumption" values="${row }" isSearch="false" type="text_view" link=""  link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="20" label="전력사용량(kWh)" /> </td>
									<td><src:mk_field name="device_cd" values="${row }" isSearch="false" type="select_view" link=""  link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="10" label="연결기기" /> </td>
									<td><src:mk_field name="optin" values="${row }" isSearch="false" type="code_view" link=""  link_type="linkLoad" valid="" valid2=""  keyValid="" maxlength="1" label="이벤트참여" /> </td>
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
<%--<script src="../js/plugin/chartjs/chart.min.js"></script>--%>
<script type="text/javascript">
    $(document).ready(function() {

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
        // LINE CHART
        // ref: http://www.chartjs.org/docs/#line-chart-introduction
        var lineOptions = {
            ///Boolean - Whether grid lines are shown across the chart
            scaleShowGridLines : true,
            //String - Colour of the grid lines
            scaleGridLineColor : "rgba(0,0,0,.05)",
            //Number - Width of the grid lines
            scaleGridLineWidth : 1,
            //Boolean - Whether the line is curved between points
            bezierCurve : true,
            //Number - Tension of the bezier curve between points
            bezierCurveTension : 0.4,
            //Boolean - Whether to show a dot for each point
            pointDot : true,
            //Number - Radius of each point dot in pixels
            pointDotRadius : 4,
            //Number - Pixel width of point dot stroke
            pointDotStrokeWidth : 1,
            //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
            pointHitDetectionRadius : 20,
            //Boolean - Whether to show a stroke for datasets
            datasetStroke : true,
            //Number - Pixel width of dataset stroke
            datasetStrokeWidth : 2,
            //Boolean - Whether to fill the dataset with a colour
            datasetFill : true,
            //Boolean - Re-draw chart on page resize
            responsive: true,
            maintainAspectRatio: false, // !important on mobile
            animation: true
        };

        var monthlyData = '<c:out value="${monthlyData }"/>'.split(',');
        var lineData = {
            labels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
            datasets: [
                {
                    label: "월별 전력 사용량",
                    fillColor: "rgba(151,187,205,0.2)",
                    strokeColor: "rgba(151,187,205,1)",
                    pointColor: "rgba(151,187,205,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(151,187,205,1)",
                    data: monthlyData
                }
            ]
        };

        // because bootstrap prevent show bs tab drawings
        //$('a[href=#s2]').on('shown.bs.tab',function(){
//            var ctx = document.getElementById("monthly_graph").getContext("2d");
//            var chart = new Chart(ctx).Bar(lineData, lineOptions);
        //})
        // END LINE CHART

        var g1, g2;

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
            var dailyData = function() { return "${dailyData }"; }
            var options = {
                showRoller : true,
                customBars : false,
                //ylabel : '전력사용량 (Wh)',
                // ylabelWidth: 50,
                legend : 'always',
                labelsDivStyles : {
                    'textAlign' : 'right'
                },
                colors: ["#2ca02c"],
                fillGraph: true,
                labelsKMG2:true,
                // sigFigs:5,
                maxNumberWidth: 8,
                digitsAfterDecimal: 5, // 소숫점때문에 +1 추가
                showRangeSelector : true,
                rangeSelectorHeight : 30,
                rangeSelectorPlotStrokeColor : 'green',
                rangeSelectorPlotFillColor : 'lightgreen',
                axes: {
                }//end of axis
            }
            function draw_g_1() {
                g1 = new Dygraph(document.getElementById("daily_graph"), dailyData, options);
            }
            draw_g_1();
            // [].slice.call(document.querySelectorAll(".dygraph-axis-label-y")).forEach(function(e) {
            //	  e.parentElement.style.left="-5px";
            // });

            function draw_chart() {
                var ctx = document.getElementById("monthly_graph").getContext("2d");
                var chart = new Chart(ctx).Bar(lineData, lineOptions);
            }
            draw_chart();

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
                }).fnSort([[0, 'desc']]);

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

            function clear_ho_param() {
                var form = $('form');
                $('select[name=search_ho]', form).val('');
            }
            function form_submit(callback) {
                var form = $('form');
                var ui_id = $('input[name=ui_id]', form).val();
                console.log(callback)
                if (callback != undefined) callback.apply(this);
                var formData = form.serializeArray();
                var url = '../piece/-'+ui_id+'-bf.sh';
                $.post(url, formData, function(data, textStatus, xhr) {
                    switch(textStatus) {
                        case "success":
                            $('section[id=widget-grid]').children().remove();
                            $('section[id=widget-grid]').html(data);
                            break;
                        default:
                            alert("처리하는 중 오류가 발생하였습니다. \n문제가 지속되면 관리자에게 문의하세요");
                    }
                });
            }
            $('input[name=search_measure_date]').bind('change',function(){
                form_submit();
            })

            $('select[name=search_dong] ').bind('change',function(){
                form_submit(clear_ho_param);
            })

            $('select[name=search_ho] ').bind('change',function(){
                form_submit();
            })
            /* END TABLETOOLS */

        };
        // load related plugins
        loadScript("../js/plugin/chartjs/chart.js", function(){
            loadScript("../js/plugin/dygraphs/dygraph-combined-dev.js", function(){
                loadScript("../js/plugin/datatables/jquery.dataTables.min.js", function(){
                    loadScript("../js/plugin/datatables/dataTables.colVis.min.js", function(){
                        loadScript("../js/plugin/datatables/dataTables.tableTools.min.js", function(){
                            loadScript("../js/plugin/datatables/dataTables.bootstrap.min.js", function(){
                                loadScript("../js/plugin/datatable-responsive/datatables.responsive.min.js", pagefunction)
                            });
                        });
                    });
                });
            });
        });
    });
</script>
