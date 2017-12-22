var AOrganizationChartTreeView = ACommon.extend({
	renderTo : '#listResult',
	lastParams : {}, 
//	enableRootEditing : true,
//	addBtnClickFn : function(upperId, path) {},
//	editBtnClickFn : function(id) {},
//	delBtnClickFn : function(id) {}, 
	constructor : function(config) {
		AListTable.superclass.prototype.constructor.apply(this,arguments);
		this.load();
	},
	loadBefore:function(dfd){
		dfd.resolve();
	},
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
					self.render(data);
			});
		});
		this.loadBefore(dfd);
	},
	reload : function(){
		this.load();
	},
	initItems :  function(data){
		var self = this;

		var rootItem = new AOrganizationChartTreeItem({upperId:'',id:'_root', name:'root', depth:1, tree:self});
		var rows = data.rows || [];
		$.each(rows,function(i,row){
			rootItem.addItem(new AOrganizationChartTreeItem({upperId:row.upperId, id:row.id, name:row.name, depth:row.depth, path:row.path, owner:row.ownerName, headCnt:row.headCnt, members:row.members, tree:self}))
		})
		return rootItem;
	},
	render : function(data){
		var self = this;

		var rootItem = this.initItems(data);
		var target = $(this.renderTo);
		target.empty();
		
		rootItem.getElement(0).appendTo(target);
	}
});

var AOrganizationChartTreeItem = Class.extend({
	id : 'root',
	name : 'rootName',
	headCnt : 0,
	members : '',
	depth:1,
	upperId : '',
	path : '/',
	owner : '',
	subItems : [],
	constructor : function(config) {
		this.subItems = [];
		for ( var o in config) {
			this[o] = config[o];
		}
	},
	addItem : function(item){
		if(this.id == item.upperId){
			this.subItems[this.subItems.length] = item;
			return true;
		} else {
			$.each(this.subItems,function(s,subItem){
				if(subItem.addItem(item)) {
					return true;
				}
			});
			return false;
		}
	},
	getElement : function(){
		var self = this;
	
		var element = $('<div class="tree smart-form"></div>');
		if(this.id != '_root') {
			var element = $('<li style="white-space: nowrap;" class="parent_li" role="treeitem" id="' + this.id + '"></il> ');
			
			var spanEl = $('<span  title="' + this.id + '" class="text-center" style="padding: 3px;"></span>');
			spanEl.appendTo(element);
			
			var spanHeadEl = $('<div style="padding: 5px 20px; background-color: rgb(225, 243, 225);"><strong>' + this.name + ' (' + this.headCnt + ')' + (this.owner != '' && this.owner ? '<br><div class="txt-color-blue" style="font-size: 11px;">[' + this.owner + ']</div>'  : '') + '</strong></div>');
			if(this.subItems.length > 0) {
				spanHeadEl.click(function(e){
					//console.debug($(this).parent('span').parent('li.parent_li'));
					var children = $(this).parent('span').parent('li.parent_li').find(' > ul > li');
					if (children.is(':visible')) {
						children.hide('fast');
					} else {
						children.show('fast');
					}
					//e.stopPropagation();
				});
			}			
			spanHeadEl.appendTo(spanEl);
			var spanBodyEl = $('<div class="text-right txt-color-teal" style="padding: 3px 3px 3px 50px; font-size: 11px;">' + this.members + '</div>');
			spanBodyEl.appendTo(spanEl);
		} 
		
		//console.debug(this.subItems);
		if(this.subItems.length > 0) {
			var ulEl = $('<ul role="tree"></ul>')
			if(this.id != '_root') {
				ulEl = $('<ul role="group"></ul>')
			}
			for(var i=0; i < this.subItems.length; i++) {
				//console.debug(this.subItems[i]);
				this.subItems[i].getElement().appendTo(ulEl);
			}
			ulEl.appendTo(element);
		}
		return element;
	},
});
