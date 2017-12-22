var ACommon = Class.extend({
	constructor : function(config) {
		for ( var o in config) {
			this[o] = config[o];
		}
	},
	getPressKeyCode : function(e) {
		var ctrlPressed = 0;
		var altPressed = 0;
		var shiftPressed = 0;
	
	 	if (parseInt(navigator.appVersion) > 3) {
		  	var evt = e ? e:window.event;
	
		  	if (document.layers && navigator.appName=="Netscape" && parseInt(navigator.appVersion)==4) {
		   		// NETSCAPE 4 CODE
				var mString =(e.modifiers+32).toString(2).substring(3,6);
			  	shiftPressed=(mString.charAt(0)=="1");
			   	ctrlPressed =(mString.charAt(1)=="1");
			   	altPressed  =(mString.charAt(2)=="1");
			} else {
				// NEWER BROWSERS [CROSS-PLATFORM]
			   shiftPressed = evt.shiftKey;
			   altPressed = evt.altKey;
			   ctrlPressed = evt.ctrlKey;
			}
	 	}
	 	
	 	if(ctrlPressed == 0 && altPressed == 1 && shiftPressed == 0) return 1;
	 	else if(ctrlPressed == 1 && altPressed == 0 && shiftPressed == 0) return 2;
	 	else if(ctrlPressed == 1 && altPressed == 1 && shiftPressed == 0) return 3;
	 	else if(ctrlPressed == 0 && altPressed == 0 && shiftPressed == 1) return 4;
	 	else if(ctrlPressed == 0 && altPressed == 1 && shiftPressed == 1) return 5;
	 	else if(ctrlPressed == 1 && altPressed == 0 && shiftPressed == 1) return 6;
	 	else if(ctrlPressed == 1 && altPressed == 1 && shiftPressed == 1) return 7;
	 	else return 0;
	}
});

function __getMonths(suffix) {
	var arr = new Array();
	for(var i = 0; i < 12; i++) {
		arr[i] = (i + 1) + suffix;
	}
	return arr;
};  

$.validator.addMethod("passwordCheck", function(value, element) {
	if($('#password').val() == value) {
		return true;
	}
	return false;
});

$.validator.addMethod("emailCheck", function(value, element) {
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;  
	if (!/Invalid|NaN/.test(value) && emailReg.test(value)) {
		return true;
	}else{
		return false;
	}	
});

$.validator.addMethod("requiredNumber",  function(value, element, params) {
    if (value != '' && link.numberFormat(value) > 0 ) {
        return true;
    }
    return false; 
});

$.validator.addMethod("percent100",  function(value, element, params) {
    if (link.numberFormat(value) == 100 ) {
        return true;
    }
    return false; 
});

$.validator.addMethod("expression",  function(value, element, options) {
    return options.fn ? options.fn() : false; 
});

function IDGenerator() {
	 this.length = 8;
	 this.timestamp = +new Date();
	 
	 var _getRandomInt = function( min, max ) {
		return Math.floor( Math.random() * ( max - min + 1 ) ) + min;
	 }
	 
	 this.generate = function() {
		 var ts = this.timestamp.toString();
		 var parts = ts.split("").reverse();
		 var id = "";
		 
		 for( var i = 0; i < this.length; ++i ) {
			var index = _getRandomInt( 0, parts.length - 1 );
			id += parts[index];	 
		 }
		 return id;
	 }
}