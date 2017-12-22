var AJDTreeView = ACommon.extend({
	renderTo : '#listResult',
	enableBottom : true,
	initCollapse : true,
	addBtnClickFn : function(upperId, path) {},
	editBtnClickFn : function(id) {},
	delBtnClickFn : function(id, upperId) {},
	actionBtnClickFn : function(id, name, depth) {},
	constructor : function(config) {
		AListTable.superclass.prototype.constructor.apply(this,arguments);
		this.load();
	},
	loadBefore:function(dfd){
		dfd.resolve();
	},
	loadAfter:function(data){
	},
	load : function(params, _loadEndedFn){
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
					self.loadAfter(data)
					if(_loadEndedFn) {
						_loadEndedFn();
					}
			});
		});
		this.loadBefore(dfd);
	},
	reload : function(){
		this.load();
	},
	initItems :  function(data){
		var self = this;

		var rootItem = new AJDTreeItem({upperId:'',id:'_root',name:'root', depth:1,tree:self});
		var rows = data.rows || [];
		$.each(rows,function(i,row){
			rootItem.addItem(new AJDTreeItem({upperId:row.upperId, id:row.id, name:row.name, depth:row.depth, path:row.path, etc:row.etc, tree:self}))
		})
		return rootItem;
	},
	render : function(data){
		var self = this;

		var rootItem = this.initItems(data);
		var target = $(this.renderTo);
		target.empty();
		
		rootItem.getElement(0).appendTo(target);
	},
	expand : function(_id, _sub) {
		var _self = this;
		var obj = $('li[id="' + _id + '"]')  
		var children = obj.find(' > ul > li');
		var parentLi = obj.parent('ul').parent('li');
		if(parentLi.attr('id')) {
			//console.log(_id, parentLi.attr('id'));
			_self.expand(parentLi.attr('id'), false);
		}
		
		obj.find(' > ul > li').each(function( index ) {
			if(_sub) {
				_self.expand($( this ).attr('id'), true);
			}
			$( this ).show('fast');
		});
		
		if(obj.find(' > span > i').attr('class') != 'icon-leaf') {
			obj.find(' > span > i').addClass('fa-folder-open').removeClass('fa-folder');
		}
	},
	collapse : function(_id, _sub) {
		var _self = this;
		var obj = $('li[id="' + _id + '"]')  
		var children = obj.find(' > ul > li');
		obj.find(' > ul > li').each(function( index ) {
			if(_sub) {
				_self.collapse($( this ).attr('id'), true);
			} 
			$( this ).hide('fast');
		});
		
		if(obj.find(' > span > i').attr('class') != 'icon-leaf') {
			obj.find(' > span > i').addClass('fa-folder').removeClass('fa-folder-open');
		}
	},
	select : function(_id) {
		var obj = $('li[id="' + _id + '"]')  
		var spanEl = obj.find(' > span');
		spanEl.click();
	}

});

var AJDTreeItem = Class.extend({
	id : 'root',
	name : 'rootName',
	depth:1,
	upperId : '',
	path : '/',
	etc : '',
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
			
			var spanEl = $('<span  title="' + (this.etc ? this.etc : this.id) + '"><i class="fa fa-lg fa-folder-open"></i><strong>' + this.name + '</strong></span>');
			spanEl.appendTo(element);
			element.append('&nbsp;&nbsp;');
			
			if(this.tree.initCollapse) {
				//console.log(spanEl.parent('li'));
				if(this.depth > 1) {
					spanEl.find(' > i').addClass('fa-folder').removeClass('fa-folder-open');
				}
				if(this.depth > 2) {
					spanEl.parent('li').hide();
				}
			}

			spanEl.click(function(e){
				self.tree.actionBtnClickFn.apply(this,[self.id, self.name, self.depth]);
			});
			if(this.depth == 4) {
				spanEl.find(' > i').removeClass().addClass('icon-leaf');
				
			} else {
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
				
				if(self.tree.enableBottom) {
					var addBtnEl = $('<a href="javascript:void(0);"><i class="fa fa-lg fa-plus-square "></i></a> ');
					addBtnEl.click(function(e){
						self.tree.addBtnClickFn.apply(this,[self.id, self.path]);
					});
					addBtnEl.appendTo(element);
					element.append('&nbsp;');
				}
			}
			if(this.subItems.length == 0) {
				if(this.id != '000001' && this.id != 'root' && self.tree.enableBottom) {
					var delBtnEl = $('<a href="javascript:void(0);"><i class="fa fa-lg fa-minus-square "></i></a> ');
					delBtnEl.click(function(e){
						self.tree.delBtnClickFn.apply(this,[self.id, self.upperId]);
					});
					delBtnEl.appendTo(element);
					element.append('&nbsp;');
				}
			}
			
			if(self.tree.enableBottom) {
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
