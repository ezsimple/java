var AListTable = ACommon.extend({
	template : 
		'<div class="dataTables_wrapper form-inline no-footer">' +
		'	<div class="dt-toolbar">' +
		'		<div class="col-xs-12 col-sm-11">' +
		'			<div id="filterSet"></div>' +
		'		</div>' +
		'		<div class="col-sm-1 col-xs-12 hidden-xs"><div id="listNumLabel" class="dataTables_length"></div></div>' +
		'	</div>' +
		'	<div class="table-responsive" >' +
		'		<table id="mainTable" class="table table-striped table-bordered table-hover dataTable ">' +
		'			<thead class="table-head"></thead>' +
		' 			<tbody class="table-tbody"></tbody>' +
		'		</table>' +
		'	</div>' +
		'</div>',
	bottomTemplate : 
		'	<div class="dt-toolbar-footer">' +
		'			<div class="col-sm-4">' +
		'				<div id="dt_basic_info" class="dataTables_info">Total : </div>' + 
		'			</div>' +
		'			<div class="col-sm-4 text-center">' +
		'				<div class="dataTables_paginate paging_bootstrap_full"><ul class="pagination pagination-sm"></ul></div>' +
		'			</div>' +
		'			<div id="buttonSet" class="col-sm-4 text-right"></div>' +
		'	</div>',
	renderTo : '#listResult',
	buttons : [],
	filters : [],
	lastParams : {pageNum:1, listNum : 20}, 
	enableListNum : true, 
	enablePageNavi : true,
	lastRowFooter : false,
	hasTickbox : false,
	enableBottom : true,
	constructor : function(config) {
		this.buttons = [];
		this.filters = [];
		AListTable.superclass.prototype.constructor.apply(this,arguments);
		this.render();
		this.initFilters();
		this.initListNum();
		this.initButtons();
	},
	codeSet : {},
	loadBefore:function(dfd){
		dfd.resolve();
	},
	loadEnded : function(data){},
	load : function(params){
		var self = this;
		
		for ( var o in params) {
			this.lastParams[o] = params[o];
		}
		var dfd = $.Deferred();
		dfd.done(function(){
			$.ajax(self.url,{
				data : self.lastParams,
				dataType:'json'
				}).done(function(data){
					self.renderBody(data);
					self.loadEnded(data);
			});
		});
		this.loadBefore(dfd);
	},
	reload : function(params) {
		this.lastParams.pageNum = 1;
		this.load(params);
	},
	render : function(){
		var target = $(this.renderTo);
		this.element = $(this.template);
		this.element.appendTo(target);
		
		if(this.enableBottom) {
			var bottomElement = $(this.bottomTemplate);
			bottomElement.appendTo(this.element);
		}

		if(this.hasTickbox) {
			var mainTableEl = $('#mainTable',this.element);
			mainTableEl.attr('class','table table-bordered table-striped table-hover dataTable smart-form has-tickbox');
		}
	
		this.load();
	},
	renderHead : function(data){
		var self = this;
		var headEl = $('.table-head',self.element);
		headEl.empty();
		
		var hrEl = $('<tr>');
		
		// set columns
		var sorts = data.sort.split(",");
		$.each(self.columns,function(i,column){
			var thEl = $('<th>').html(column.name).appendTo(hrEl);
			if(column.sorting) {
				thEl.attr('class', 'sorting');
				var sortField = column.sorting; 
				var sortValue = 'asc';
				$.each(sorts,function(s,sort){
					if(column.sorting == sort.split(":")[0]) {
						thEl.attr('class', 'sorting_' + sort.split(":")[1]);
						sortValue = sort.split(":")[1] == 'desc' ? 'asc' : 'desc';
					}
				});
				thEl.click(function(e){
					var sortParam = sortField + ':' + sortValue;
					//console.debug(sorts);
					if(self.getPressKeyCode(e) == 4 && sorts[0] != '' && sorts.length > 0 ) {
						var shiftSort = '';
						$.each(sorts,function(s,sort){
							if(sortField != sort.split(":")[0]) {
								shiftSort += sort + ',' ;
							}
						});
						sortParam = shiftSort + sortParam;
					}
					self.load({sortKey : sortParam})
				});
			}
		});
		
		hrEl.appendTo(headEl);
		
	},
	renderBody : function(data){
		var self = this;
		
		this.renderHead(data);
		
		// body render
		var bodyEl = $('.table-tbody',self.element);
		bodyEl.empty();
		
		var rows = data.rows || [];
		$.each(rows,function(i,row){
			var trEl = $('<tr>');
			if(self.trBgColor){
				var bgColor = self.trBgColor.apply(this,[row,i]);
				if(bgColor != '') {
					trEl.attr("style", "background-color:" + bgColor);
				}
			}
			trEl.appendTo(bodyEl);
			
			$.each(self.columns,function(j,column){
				if(data.rows.length == i+1 && self.lastRowFooter) {
					if(column.footerRender) {
						var footerTd = column.footerRender.apply(self,[row,i]); // {colspan: 2 , value : 'value'}
						var tdEl = $('<td class="sorting">').appendTo(trEl);
						if(footerTd.style) {
							tdEl.attr("style", footerTd.style);
						}
						if(footerTd.className) {
							tdEl.attr("class", footerTd.className);
						}
						if(footerTd.colspan) { 
							tdEl.attr('colspan', footerTd.colspan);
						}
						tdEl.html(footerTd.value);
					}
				} else {
					var tdEl = $('<td>').appendTo(trEl);
					var styleValue = '';
					
					if(column.style){
						styleValue += column.style + ';';
					}
					if(self.trBgColor){
						var bgColor = self.trBgColor.apply(this,[row,i]);
						if(bgColor != '') {
							styleValue += 'background-color:' + bgColor + ';';
						}
					}
					if(styleValue != '') {
						tdEl.attr("style", styleValue);
					}
					
					if(column.className) {
						tdEl.attr("class", column.className);
					}					
					if(column.buttons){
						$.each(column.buttons,function(k,button){
							var _show = true;
							if(button.show) {
								_show = button.show.apply(this,[row,i]);
							}
							
							var _auth = true;
							if(button.auth) {
								_auth = button.auth.apply(this,[row,i]);
							}
							
							if(_show) {
								if(button.br) {
									tdEl.append('<div style="padding-top: 3px;"/>');
								}
								var _name = typeof(button.name) == 'function' ? button.name.apply(this,[row,i]) : button.name;
								var btn = $('<a class="btn ' + ( button.className ? button.className : 'btn-default' ) + ' ' + ( _auth ? '' : 'disabled' )+ ' btn-xs">' + _name + '</a>');
								
								btn.click( function(){
									button.click.apply(this,[row,i]);
								});
								
								btn.appendTo(tdEl);
								tdEl.append(' ');
								
							}
						})
					} else if(column.render){
						var _num = 0;
						if(data.totalCount && data.limit) {
							_num = (data.totalCount - data.limit.offset - i);
						}
						tdEl.html(column.render.apply(self,[row,i,_num]));
					} else {
						if(column.id.indexOf('.') == -1){
							tdEl.html(row[column.id]);
						}else{
							var o = row;
							var keys = column.id.split('.');
							for(var z=0 ; z < keys.length; z++){
								var key = keys[z];
								//console.debug(key, o);
								if(o) {
									o = o[key];
								}
							}
							tdEl.html(o);
						}
					}
				}
			});
		});
		this.initPageNav(data);
	},
	search : function(){
		var filterSetEl = $('#filterSet',this.element);
		
		var nodes = $('input,select',filterSetEl);
		var params = {};
		
		nodes.each(function(i,el){
			var el = $(el);
			params[el.attr('name')] = el.val();
		});
		this.load(params);
		//console.debug('search',params);
	},
	initFilters : function(){
		var self = this;
		if(this.filters) {
			var filterSetEl = $('#filterSet',self.element);
			//filterSetEl.empty();
			$.each(this.filters,function(idx,filter){
				if(typeof filter == 'string'){
					//console.debug(1);
					var el = $('<div class="dt_filter"><div class="input-group">' + filter + '</div></div>');
					el.appendTo(filterSetEl);
				}else{
					filter.getElement().appendTo(filterSetEl);
					filter.leftPosition();
				}
			});
			
			$('.search', filterSetEl).each(function(i,el){
				if(el.nodeName=='SELECT'){
					$(el).change(function(){
						self.search();
					});
				}else{
					$(el).click(function(){
						self.search();
					});
				}
			})
		}
	},
	resetFilters : function(){
		$.each(this.filters,function(idx,filter){
			if(filter.reset){
				filter.reset();
			}
		});
	},
	initButtons : function(){
		// buttonSet
		var self = this;
		if(this.buttons) {
			var buttonSetEl = $('#buttonSet',self.element);
			buttonSetEl.empty();
			$.each(this.buttons,function(j,button){
				var _show = true;
				if(button.show) {
					_show = button.show.apply(this,[self, j]);
				}
				
				if(_show) {
					var btn = $('<a class="btn ' + ( button.className ? button.className : 'btn-default' )+ ' btn-sm">' + button.name + '</a>');
					btn.click( function(){
						button.click.apply(this,arguments);
					});
					btn.appendTo(buttonSetEl);
					buttonSetEl.append(' ');
				}
			});
		}
		
	},
	initListNum : function(){
		var self = this;
		
		if(this.enableListNum) {
			var listNumLabelEl = $('#listNumLabel',self.element);
			listNumLabelEl.empty();
			var listNumEl = $('<select class="form-control" size="1" name="listNum" id="listNum"></select>');
			listNumEl.appendTo(listNumLabelEl);
			$('<i></i>').appendTo(listNumLabelEl);
			$.each([10,20,30,50,100,200,500],function(idx,i){
				var span = $('<option>' + i + '</option>');
				span.appendTo(listNumEl)
				.attr('value',i);
				//.click(function(){
				//	var listNum = $(this).attr('value');
				//	self.load({pageNum:1, listNum :listNum});
				//});
				if(i == (self.lastParams.listNum ? self.lastParams.listNum : 20)) {
					span.attr('selected','selected')
				}
			});
			listNumEl.change(function(evt){
				self.load({pageNum:1, listNum :listNumEl.val()});
			});
		} else {
			if(this.filters.length  == 0 ) {
				var toolbarEl = $('.dt-toolbar',self.element);
				toolbarEl.remove();
			}
		}
	},
	initPageNav : function(data){
		var self = this;
		
		// totalCount
		var totalEl = $('#dt_basic_info',self.element);
		totalEl.text('Total : ' + data.totalCount);
		
		// pageNavi render
		var naviEl = $('.pagination',self.element);
		naviEl.empty();
		
		if(this.enablePageNavi) {
			var totalCount = data.limit.totalCount;
			var listNum = data.limit.limit; 
			var pageNum = parseInt(data.limit.offset / data.limit.limit) + 1;
	
			var totalNum = parseInt(totalCount / listNum) + (totalCount % listNum > 0 ? 1 : 0);
			var startNum = 1;
			if (pageNum % 10 == 0) {
				startNum = pageNum == 10 ? 1 : (parseInt(pageNum / 10) - 1) * 10 + 1;
			} else {
				startNum = (parseInt(pageNum / 10) == 0 ? 1 : parseInt(pageNum / 10) * 10 + 1);
			}
			var endNum = startNum == 1 ? 10 : startNum + 10;
			if (totalNum < endNum) {
				endNum = totalNum;
			} else {
				if (endNum > 10) {
					endNum--;
				}
			}
			
			var prevEl = $('<li class="prev ' + ( startNum - 1 == 0 ? 'disabled' : '') + '" data="' + (startNum - 1) + '"><a href="javascript:void(0);"><i class="fa fa-angle-left"></i></a></li>').appendTo(naviEl);
			if(startNum - 1 > 0 ){
				prevEl.click(function(){
					self.load({pageNum : $(this).attr('data')});
				});
			}
			
			for (var i = startNum; i <= endNum; i++) {
				var span = $('<li>').html('<a href="javascript:void(0);">' + i + '</a>').appendTo(naviEl);
				if(pageNum == i){
					span.attr('class','active');
				} else {
					span.attr('data',i).click(function(){
						self.load({pageNum : $(this).attr('data')});
					});
				}
			}
			
			var nextEl = $('<li class="next ' + ( endNum + 1 > totalNum ? 'disabled' : '') + '" data="' + (endNum + 1) + '"><a href="javascript:void(0);"><i class="fa fa-angle-right"></i></a></li>').appendTo(naviEl);
			if(endNum + 1 <= totalNum ){
				nextEl.click(function(){
					self.load({pageNum : $(this).attr('data')});
				});
			}		
		}
	}
});

var AHeadListTable = AListTable.extend({
	constructor : function(config) {
		AHeadListTable.superclass.prototype.constructor.apply(this,arguments);
	},
	renderHead : function(data){
		var self = this;
		var headEl = $('.table-head',self.element);
		headEl.empty();
		
		// set columns
		var sorts = data.sort.split(",");
		$.each(self.heads,function(a,head){
			var trEl = $('<tr>');
		
			$.each(head.columns,function(i,column){
				var thEl = $('<th>').text(column.name).appendTo(trEl);
				if(column.rowspan) {
					thEl.attr('rowspan', column.rowspan);
				}
				if(column.colspan) {
					thEl.attr('colspan', column.colspan);
				}
				if(column.sorting) {
					thEl.attr('class', 'sorting');
					var sortField = column.sorting; 
					var sortValue = 'asc';
					$.each(sorts,function(s,sort){
						if(column.sorting == sort.split(":")[0]) {
							thEl.attr('class', 'sorting_' + sort.split(":")[1]);
							sortValue = sort.split(":")[1] == 'desc' ? 'asc' : 'desc';
						}
					});
					thEl.click(function(e){
						var sortParam = sortField + ':' + sortValue;
						//console.debug(sorts);
						if(self.getPressKeyCode(e) == 4 && sorts[0] != '' && sorts.length > 0 ) {
							var shiftSort = '';
							$.each(sorts,function(s,sort){
								if(sortField != sort.split(":")[0]) {
									shiftSort += sort + ',' ;
								}
							});
							sortParam = shiftSort + sortParam;
						}
						self.load({sortKey : sortParam})
					});
				}
			});
			
			trEl.appendTo(headEl);
			
		});
		
	}
});

var ASearchElement = ACommon.extend({
	element : null,
	width : 200,
	inputId : 'searchKey',
	search : function(value) {},
	constructor : function(config) {
		ASearchElement.superclass.prototype.constructor.apply(this,arguments);
	},
	getElement : function(){
		return this.element;
	},
	leftPosition : function() {
		this.element.attr('style', 'width:' + this.width + 'px;' )
	},
	reset : function(){},
	defaultValue : ''
});


var AInputSearchElement = ASearchElement.extend({
	constructor : function(config) {
		this.placeholder = 'Filter';
		AInputSearchElement.superclass.prototype.constructor.apply(this,arguments);
		var self = this;
		
		this.element = $('<div class="dt_filter"><div class="input-group"><input class="form-control" id="' + this.inputId + '" name="' + this.inputId + '" placeholder="' + this.placeholder + '" type="text"><span class="input-group-addon findBtn" style="cursor:pointer;"><i class="fa fa-search"></i></span></div></div>');
		
		var inputEl = $('#' + this.inputId, this.element);
		if(self.defaultValue != '') {
			//console.debug(self.defaultValue);
			inputEl.val(self.defaultValue);
		}
		inputEl.keydown(function(evt){
			if(evt.keyCode == 13) {
				self.search.apply(this, [inputEl.val()]);
			}
			//console.debug(evt.keyCode);
		});
		$('.findBtn',this.element).click(function(){
			//console.debug(input.val());
			self.search.apply(this, [inputEl.val()]);
		});
	}
});

var ASelectSearchElement = ASearchElement.extend({
	constructor : function(config) {
		this.reload = false;
		AInputSearchElement.superclass.prototype.constructor.apply(this,arguments);
		var self = this;
		this.element = $('<div class="dt_filter"><div class="smart-form"><label class="select"><select id="' + this.inputId + '" name="' + this.inputId + '" class="form-control "></select><i></i></label></div></div>');
		
		var inputEl = $('#' + this.inputId, this.element);
		
		if(this.placeholder){
			inputEl.append($('<option value="" selected="">' + this.placeholder + '</option>'));
		}
		
		if(self.options) {
			for ( var idx in self.options) {
				var option = self.options[idx];
				inputEl.append($('<option value="' + option.value + '">' + option.name + '</option>'));	
				if(self.defaultValue != '') {
					inputEl.val(self.defaultValue);
				}
			}
		}
		
		if(self.url) {
			//console.debug(self.url);
			$.ajax(self.url,{
					dataType:'html'
				}).done(function(data){
					//console.debug(data);
					inputEl.append(data)
					if(self.defaultValue != '') {
						//console.debug(self.defaultValue);
						inputEl.val(self.defaultValue);
					}
			});
		} 
		
		inputEl.change(function(evt){
			self.search.apply(this, [inputEl.val()]);
			if(self.reload) {
				$.ajax(self.url,{
					dataType:'html',
					data : self.inputId + '=' + inputEl.val()	
				}).done(function(data){
					inputEl.empty();
					inputEl.append(data)
				});
				
			}
		});
	},
	reset : function(){
		var self = this;
		
		var inputEl = $('#' + this.inputId, this.element);
		var oldValue = inputEl.val();
		inputEl.empty();
		
		$.ajax(self.url,{
			dataType:'html'
		}).done(function(data){
			if(self.placeholder){
				inputEl.append($('<option value="" selected="">' + self.placeholder + '</option>'));
			}			
			inputEl.append(data);
			inputEl.val(oldValue);
		});
	}
});

var ASelectDaySearchElement = ASearchElement.extend({
	constructor : function(config) {
		this.reload = false;
		AInputSearchElement.superclass.prototype.constructor.apply(this,arguments);
		var self = this;
		this.element = $('<div class="dt_filter"><div class="smart-form"><label class="select"><select id="' + this.inputId + '" name="' + this.inputId + '" class="form-control "></select><i></i></label></div></div>');
		
		var inputEl = $('#' + this.inputId, this.element);
		
		if(this.placeholder){
			inputEl.append($('<option value="" selected="">' + this.placeholder + '</option>'));
		}
		
		for(var i = 1; i <= 31; i++) {
			inputEl.append($('<option value="' + i + '" ' + (this.defaultValue == i ? 'selected="selected"' : '') + '>' + __m.get("page_day", i) + '</option>'));
		}

		inputEl.change(function(evt){
			self.search.apply(this, [inputEl.val()]);
		});
	}
});

var ADateSearchElement = ASearchElement.extend({
	constructor : function(config) {
		this.placeholder = '';
		AInputSearchElement.superclass.prototype.constructor.apply(this,arguments);
		var self = this;
		
		this.element = $('<div class="dt_filter"><div class="smart-form"><label class="input"><input id="' + this.inputId + '" name="' + this.inputId + '" placeholder="' + this.placeholder + '" type="text"><i class="icon-append fa fa-calendar"></i></div></div>');
		
		var inputEl = $('#' + this.inputId, this.element);
		if(self.defaultValue != '') {
			//console.debug(self.defaultValue);
			inputEl.val(self.defaultValue);
			link.toDateFormat(inputEl);
		}
		inputEl.datepicker();
		
		inputEl.change(function(){
			//console.debug(inputEl.val());
			self.search.apply(this, [link.numberFormat(inputEl.val())]);
		});
	}
});

var ANotSearchElement = ASearchElement.extend({
	constructor : function(config) {
		this.reload = false;
		AInputSearchElement.superclass.prototype.constructor.apply(this,arguments);
		this.element = $('<div class="dt_filter">' + this.text + '</div>');
	}
});
