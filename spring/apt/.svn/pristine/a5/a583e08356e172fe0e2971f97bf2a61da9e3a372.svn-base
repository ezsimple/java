function xJSMessage(){
	this.messages = new Array();
	this.init = function(){
		this.add('button_add', '추가');
		this.add('button_save', '저장');
		this.add('button_del', '삭제');
		this.add('button_close', '닫기');
		this.add('button_update', '수정');
		this.add('object_confirm', '확인요청');
		this.add('button_ok', '확인');
		this.add('button_cancel', '취소');
	}
	this.get = function(key, r1, r2, r3){
		var result = '';
		var entry = this.findEntry(key);
		if (entry){
			result = entry.value;
		} else {
		return '[[--' + key + '--]]';
		}
		if(r1){
			result = this.replace(result, '{0}', r1);
		}
		if(r2){
			result = this.replace(result, '{1}', r2);
		}
		if(r3){
			result = this.replace(result, '{2}', r3);
		}
		return result;
	}
	this.add = function(key, value){
		if(key){
			var entry = this.findEntry(key);
			if (entry){
				entry.value = value;
			} else {
				entry = new Object();
				entry.key = key;
				entry.value = value;
				this.messages[this.messages.length] = entry;
			}
		}
	};
	this.findEntry = function (key){
		for (var i=0;i<this.messages.length;i++){
			var entry = this.messages[i];
			if (entry.key == key){
				return entry;
			}
		}
		return null;
	};
	this.replace = function(_str, _this, _that) { 
		return _str.split(_this).join(_that); 
	} ;
}
var __m = new xJSMessage();
__m.init();
