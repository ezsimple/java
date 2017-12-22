var AComment = ACommon.extend({
	template : 
		'<div  class="chat-body custom-scroll" style="height:150px">' +
		'	<ul id="commont-list-ul">' +
		'	</ul>' +
		'</div>' +
		'<div class="chat-footer" style="padding:5px 80px 5px 5px;">' +
		'	<label class="textarea"><textarea id="commentContent" style="height:60px;"></textarea></label>' +
		'	<span style="position:absolute;top:5px;right:5px;width:70px;">' +
		'	<a style="height:60px;width:75px;padding-top:20px;" id="addCommentButton" class="btn btn-sm btn-primary">' + __m.get('button_add') + '</a>' +
		'	</span>' +
		'</div>',
	renderTo : '#commentResult',
	lastParams : {pageNum:1, listNum : 20}, 
	upperId : 'none',
	writerId : '',
	constructor : function(config) {
		AListTable.superclass.prototype.constructor.apply(this,arguments);
		this.render();
	},
	render : function(){
		var self = this;

		var target = $(this.renderTo);
		//console.debug(this.renderTo);
		//console.debug(target);
		this.element = $(this.template);
		this.element.appendTo(target);
		
		var addCommentButtonEl = $('#addCommentButton',this.element);
		addCommentButtonEl.click(function(e){
			self.addComment();
		});
		this.load();
	},
	addComment : function(){
		var self = this;		
		var _commentContent = $('#commentContent',this.element).val();
		if(_commentContent) {
			$.ajax({
				url : self.url + '/create?upperId=' + self.upperId,
				type : 'post',
				dataType : 'json',
				data : {comment : _commentContent}, 
				success : function(obj) {
					if (obj.success) {
						self.load();
						$('#commentContent',this.element).val('');
					} else {
						alert(obj.message);
					}
				},
				error: function(e) {
					alert(e);
				}
			});
		}
	},
	delComment : function(_id, _employeeId){
		var self = this;
		var confirm = new AConfirm({
			msg : __m.get('msg_confirm_delete'),
			okClick : function(){
				$.ajax({
					url : self.url + '/remove?upperId=' + self.upperId,
					type : 'post',
					dataType : 'json',
					data : {id : _id, employeeId : _employeeId}, 
					success : function(obj) {
						if (obj.success) {
							self.load();
						} else {
							alert(obj.message);
						}
					},
					error: function(e) {
						alert(e);
					}
				});
			}
		});
		confirm.show();
	},
	load : function(params){
		var self = this;
		
		for ( var o in params) {
			this.lastParams[o] = params[o];
		}
		$.ajax(self.url + '/search?upperId=' + self.upperId, {
			data : self.lastParams,
			dataType:'json'
			}).done(function(data){
				self.renderBody(data);
		});
	},
	renderBody : function(data){
		var self = this;
		
		var bodyEl = $('#commont-list-ul',self.element);
		bodyEl.empty();
		
		var rows = data.rows || [];
		$.each(rows,function(i,row){
			var comment = '<li class="message">';
			comment += '<img src="' + link.getContextPath() + 'hr/employee/photo?id=' + row.employeeId + '&size=50&date=' + new Date() + '" class="online" width="40">'; //&size=100
			comment += '<div class="message-text">';
			comment += '<time>' + link.dateFormat(row.regDate) + '</time>';
			if(self.writerId == row.employeeId) {
				comment += '<span id="delBtn' + row.id + '" class="fa fa-times"></span>';
			}
			comment += '<a class="username">' + row.employeeName + '</a>'; 
			comment += link.replaceNewLine(row.comment) ; 
			comment += '</div>';
			comment += '</il>';
			var ilEl = $(comment);
			
			var delBtnEl = $('#delBtn'+ row.id, ilEl);
			if(delBtnEl) {
				delBtnEl.click(function(e){
					self.delComment(row.id, row.employeeId);
				});
			}
			
			
			ilEl.appendTo(bodyEl);
		});
	}
});