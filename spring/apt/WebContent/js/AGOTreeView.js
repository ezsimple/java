var AGOTreeView = ACommon.extend({
	renderTo : '#listResult',
	lastParams : {}, 
	enableRootEditing : true,
	addBtnClickFn : function(upperId, path) {},
	editBtnClickFn : function(id) {},
	delBtnClickFn : function(id) {}, 
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

		var rootItem = new AGOTreeItem({upperId:'',id:'_root', name:'root', depth:0, tree:self, fixed:true});
		var rows = data.rows || [];
		$.each(rows,function(i,row){
			rootItem.addItem(new AGOTreeItem({upperId:row.upperId, id:row.id, name:row.name, depth:row.depth, path:row.path, fixed:row.fixed, depth:row.depth, tree:self}))
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

var AGOTreeItem = Class.extend({
	id : 'root',
	name : 'rootName',
	depth:1,
	upperId : '',
	path : '/',
	depth:1,
	fixed : false,
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
			
			var spanEl = $('<span  title="' + this.id + '" ' + (this.fixed == 'true' ? 'style="background-color:#EEE;"' : '') + '><i class="fa fa-lg fa-folder-open"></i> <strong>' + this.name + ' (' + this.id + ')'+ '</strong>' + ' </span>');
			spanEl.appendTo(element);
			element.append('&nbsp;&nbsp;');
			
			if(this.depth < 4 ) {
				var addBtnEl = $('<a href="javascript:void(0);"><i class="fa fa-lg fa-plus-square "></i></a> ');
				addBtnEl.click(function(e){
					self.tree.addBtnClickFn.apply(this,[self.id, self.path]);
				});
				addBtnEl.appendTo(element);
				element.append('&nbsp;');
			} else {
				spanEl.find(' > i').removeClass().addClass('icon-leaf');
			}
			
			if(this.subItems.length == 0 && this.fixed != 'true') {
				if(this.upperId != '_root') {
					var delBtnEl = $('<a href="javascript:void(0);"><i class="fa fa-lg fa-minus-square "></i></a> ');
					delBtnEl.click(function(e){
						self.tree.delBtnClickFn.apply(this,[self.id]);
					});
					delBtnEl.appendTo(element);
					element.append('&nbsp;');
				}
			} else {
				if(this.depth < 4 ) {
					spanEl.click(function(e){
						var children = $(this).parent('li.parent_li').find(' > ul > li');
						if (children.is(':visible')) {
							children.hide('fast');
							$(this).find(' > i').removeClass().addClass('fa fa-lg fa-folder');
						} else {
							children.show('fast');
							$(this).find(' > i').removeClass().addClass('fa fa-lg fa-folder-open');
						}
						e.stopPropagation();
					});
				}
			}
			
			if(this.upperId != '_root') {
				var editBtnEl = $('<a href="javascript:void(0);"><i class="fa fa-lg fa-edit"></i></a> ');
				editBtnEl.click(function(e){
					self.tree.editBtnClickFn.apply(this,[self.id]);
				});
				editBtnEl.appendTo(element);
			}
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
