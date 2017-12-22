function drawChart(type, chart_id, chartData, ui_id) {
	try {
		_drawChart(type, chart_id, chartData, ui_id);
		$.chart_data.push({
			type : type,
			chart_id : chart_id,
			chartData : chartData,
			ui_id : ui_id
		});
	} catch (e) {
		// TODO: handle exception
	}
}
function _drawChart(type, chart_id, chartData, ui_id) {
	var chartId = $(chart_id);
	var isEnable = chartId.length > 0;
	try {
		if (chartId.length < 1 || chartId.get(0).clientHeight == 0) {
			return isEnable;
		}
		var option = $.chart_option[type](chartData);
		if(ui_id){
			var uiOption = $.chart_option[ui_id]
			if(uiOption){
				var newOption = {};
				addObject(newOption, option);
				addObject(newOption, uiOption);
				option = newOption;
			}
		}
		Flotr.draw($(chart_id).get(0), chartData.data, option);

	} catch (e) {
		return isEnable;
	}

	return isEnable;
}

function addObject(target, source){

	if(source) {
		$.each(source, function(key, value){
			target[key] = value;
		});
	}

	return target;
}

// 차트 다시 그리기
$['reDrawChartByReSize'] = function() {
	var chart_data = [];

	for (var i = 0; i < $.chart_data.length; i++) {
		var data = $.chart_data[i];

		if (_drawChart(data.type, data.chart_id, data.chartData, data.ui_id)) {
			chart_data.push(data);
		}
	}

	$.chart_data = chart_data;
};



$.chart_data = [];
$.chart_option = {
	chart_bar_iy : function (data){ return optionBarIY(data); },
	chart_bar_ixy : function (data){ return optionBarIXY(data); },
	chart_bar_xy : function (data){ return optionBarXY(data); },
	chart_line_xy : function (data){ return optionLineXY(data); },
	chart_line_ixy : function (data){ return optionLineIXY(data); },
	chart_pie_iy : function (data){ return optionPie(data); },
	chart_bubble : function (data){ return optionBubble(data); }
};

var optionBarIY = function(chartData){
	var option  = {
		bars : {
			show : true,
			horizontal : false,
			// shadowSize : 0,
			barWidth : 1
		},
		legend : {
			position : 'ne',
			// Position the legend 'south-east'.
			// labelFormatter: labelFn,
			// Format the labels.
			backgroundColor : '#D2E8FF' // A light blue background color.
		},
		xaxis : {
			noTicks : chartData.data.length + 2, // Display n ticks.
			tickFormatter : function(n) {
				return '&nbsp;'; // ''&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; '
									// + Math.ceil(n) + '월';
			},
			showLabels : true
		},
		yaxis : {
			min : 0,
			autoscaleMargin : 1,
			tickFormatter : function(n) {
				return Math.ceil(n);
			}
		},
		mouse : {
			track : true,
			relative : true,
			position : 'ns',
			trackFormatter : function(data) {
				return data.series.label + ' : ' + Math.ceil(data.y)
			}
		},
		grid : {
			horizontalLines : true,
			verticalLines : false
		}
	};
	
	return option;
};

var optionBarIXY = function(chartData){
	var option  = {
		bars : {
			show : true,
			horizontal : horizontal,
			// shadowSize : 0,
			barWidth : 3600000 * 20 / chartData.itemCount
		},
		xaxis : {
			mode : chartData.type,
			labelsAngle : 45,
			noTicks : 5,
			timeUnit : 'hour',
			tickFormatter : function(n) {
				var date = new Date();
				date.setTime(n);
				return '&nbsp;&nbsp;' + $.datepicker.formatDate(('mm-dd '), date);
			}
		},
		yaxis : {
			min : 0,
			autoscaleMargin : 1,
			tickFormatter : function(n) {
				return Math.ceil(n);
			}
		},
		mouse : {
			track : true,
			relative : true,
			position : 'ns',
			trackFormatter : function(data) {
				var date = new Date();
				date.setTime(data.x);
				return data.series.label + ' : (' + $.datepicker.formatDate(('yy-mm-dd '), date) + ', ' + Math.ceil(data.y) + ')'
			}
		},
		grid : {
			horizontalLines : true,
			verticalLines : true
		}
	};
	
	return option;
};

var optionBarXY = function(chartData){
	var option  = {
		bars : {
			show : true,
			horizontal : false,
			// shadowSize : 0,
			barWidth : 3600000 * 21
		},
		xaxis : {
			mode : chartData.ype,
			labelsAngle : 45,
			noTicks : chartData.data.length + 5,
			tickFormatter : function(n) {
				// var date = new Date();
				// date.setTime(n);
				// return $.datepicker.formatDate(('yy-mm-dd '), date);
				return n;
			}
		},
		yaxis : {
			autoscaleMargin : 1,
			tickFormatter : function(n) {
				return Math.ceil(n);
			}
		},
		mouse : {
			track : true,
			relative : true,
			position : 'ns',
			trackFormatter : function(data) {
				var val = ' : (' + data.x + ', ' + Math.ceil(data.y) + ')';
				$('.chart_value').html(val);
				return val;
			}
		},
		grid : {
			horizontalLines : true,
			verticalLines : true
		}
	};
	
	return option;
};

var optionLineXY = function(chartData){
	var option  = {
		lines : {
			fill : false
		},
		legend : {
			position : 'ee',
			backgroundColor : '#D2E8FF' // A light blue background color.
		},
		xaxis : {
			mode : chartData.type,
			labelsAngle : 45,
			noTicks : 16,
			tickFormatter : function(n) {
				// var date = new Date();
				// date.setTime(n);
				// return '&nbsp;&nbsp;' + $.datepicker.formatDate(('hh'),
				// date);
				try {
					//alert(n)
					if(n > 1000000)
					return xaxis_fnc(n);
				} catch (e) {
					// TODO: handle exception
				}
				return Math.ceil(n);
			}
		},
		yaxis : {
			min : 0,
			autoscaleMargin : 1,
			tickFormatter : function(n) {
				return Math.ceil(n);
			}
		},
	/*		mouse : {
				track : true,
				relative : false,
				position : 'ns',
				trackFormatter : function(data) {
					// var date = new Date();
					// date.setTime(data.x);
					// return data.series.label + ' : (' +
					// $.datepicker.formatDate(('yy-mm-dd '), date) + ', ' +
					// Math.ceil(data.y) + ')'
					var val = '(' + data.x + ', ' + Math.ceil(data.y) + ')';
					$('.chart_value').html(val);
					return val;
				}
			},*/
		grid : {
			horizontalLines : true,
			verticalLines : true
		}
	};
	
	return option;
};

var optionLineIXY = function(chartData){
	var option  = {
		lines : {
			fill : false
		},
		legend : {
			position : 'ee',
			backgroundColor : '#D2E8FF' // A light blue background color.
		},
		xaxis : {
			mode : chartData.type,
			labelsAngle : 45,
			noTicks : 12,
			tickFormatter : function(n) {
				// var date = new Date();
				// date.setTime(n);
				// return '&nbsp;&nbsp;' + $.datepicker.formatDate(('mm-dd '),
				// date);
				return Math.ceil(n);
			}
		},
		yaxis : {
			min : 0,
			autoscaleMargin : 1,
			tickFormatter : function(n) {
				return Math.ceil(n);
			}
		},
		/*
		 * mouse : { track : true, relative : false, position: 'ns',
		 * trackFormatter: function(data) { //var date = new Date();
		 * //date.setTime(data.x); //return data.series.label + ' : (' +
		 * $.datepicker.formatDate(('yy-mm-dd '), date) + ', ' +
		 * Math.ceil(data.y) + ')' var x1 = Math.floor(data.x); var x2 =
		 * data.x-x1; x2 = x2*60/100; var x = x1 + x2; var val =
		 * data.series.label + ' : (' + x + ', ' + Math.ceil(data.y) + ')';
		 * $('.chart_value').html(val); return val; } },
		 */grid : {
			horizontalLines : true,
			verticalLines : true
		}
	};
	
	return option;
};

var optionPie = function(datachartData){
	var option  = {
		HtmlText : false,
		grid : {
			verticalLines : false,
			horizontalLines : false
		},
		xaxis : {
			showLabels : false
		},
		yaxis : {
			showLabels : false
		},
		pie : {
			show : true,
			explode : 6
		},
		mouse : {
			track : true,
			trackFormatter : function(data) {
				return data.series.label + ' : ' + Math.ceil(data.y)
			}
		},
		legend : {
			position : 'ne',
			backgroundColor : '#D2E8FF'
		}
	};
	
	return option;
};

var optionBubble = function(chartData){
	var option  = {
		bubbles : {
			show : true,
			baseRadius : 5
		},
		xaxis : {
			min : chartData.minX - chartData.maxZ,
			max : chartData.maxX + chartData.maxZ,
			tickFormatter : function(n) {
				return Math.ceil(n);
			}
		},
		yaxis : {
			min : chartData.minY - chartData.maxZ,
			max : chartData.maxY + chartData.maxZ,
			autoscaleMargin : 1,
			tickFormatter : function(n) {
				return Math.ceil(n);
			}
		},
		mouse : {
			track : true,
			relative : true,
			position : 'nn',
			trackFormatter : function(data) {
				return data.series.label + ' : (' + Math.ceil(data.x) + ', ' + Math.ceil(data.y) + ', ' + Math.ceil(data.series.data[0][2]) + ')';
			}
		},
		grid : {
			horizontalLines : true,
			verticalLines : true
		}
	};
	
	return option;
};


