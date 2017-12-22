
	//tags/tag/check_array.tag 에서 호출
	function changeCheck(ctls, name){
		var vals = "";
		for(var i=0; i<ctls.length; i++){
			var val = $(ctls[i]).val();
			vals += "," + val;
		}

		vals = vals.substring(1);

		$('.check_hidden[name='+name+']').val(vals);
	}

	function checkValidOnChange(){
		$(document).on('change', '[valid]', function(e){
			validItem(e.target);;
		});
	}
	
	//영문
	function alpa(event){
		if(event.charCode == 0 || (event.charCode >= 65 && event.charCode <= 90) || (event.charCode >= 97 && event.charCode <= 122)) {
			return true;
		}
		return false;	
	}

	//숫자
	function numeric(event){
		if(event.charCode == 0 ||  event.charCode >= 48 && event.charCode <= 57){ 
			return true;
		}
		return false;	
	}
	//영숫자
	function alpa_numeric(event){
		if(event.charCode == 0 ||  (event.charCode >= 48 && event.charCode <= 57) || (event.charCode >= 65 && event.charCode <= 90) || (event.charCode >= 97 && event.charCode <= 122)){ 
			return true;
		}
		return false;	
	}
	function getFieldName(ctl){
		var label = ctl.closest("[label]");;
		
		if(label.length>0){
			return label.attr('label');
		}
		return $('[label='+ctl.attr('name')+']',ctl.closest('form')).text();
	}
	function getControlValue(obj){
		var $obj = $(obj);
		if($obj.length<1){
			return '';
		}
		var nodeName = $obj.get(0).nodeName;
		
		if(nodeName =='SELECT'){
			return $('option:selected',$obj).val();
		}else if(nodeName =='INPUT'){
			if($obj.attr('type').toLowerCase()=='radio'){
				var ctl = $obj.closest('.editable');
				var sel =  $('input:checked',ctl);
				return sel.length>0 ? sel.val() : '';
			}
		}
		
		return $obj.val().trim();
	}
	
	function valid(formId){
		var ctls = $('[valid]',$(formId));
		
		for(var i=0; i<ctls.length ; i++){
			
			if(!validItem(ctls[i])){
				return false;
			};
		}
		//if($('#subject').val().indexOf('<')>-1){
		//	alert("'<' 문자는 사용할수 없습니다.");
		//	$('#subject').focus();
		//	return false;
		//}
		return true;
	}	
	function validItem(obj){
		var ctl =$(obj);
		var valids = ctl.attr('valid').split(',');
		
		for(var n=0; n<valids.length; n++){
			var opt = valids[n].split(':');
			var fnc = opt[0].trim();
			
			if(fnc==''){
				continue;
			}
			if($.valid_fnc[fnc]){
				var isValid = $.valid_fnc[fnc](ctl, getControlValue(ctl), opt);
				if(!isValid){
					return false;
				}
			}else{
				alert(fnc + '함수를 구현하세요.\n$.valid_fnc["'+fnc+'"]=function(ctl,val, opt){...};');
				return false;
			}
		}
		return true;
	}
	
	
	//정합성 체크함수 구현
	$.valid_fnc = {
		notempty : function(ctl, val){
			if(val=='' || val=='<br>'){
				alert(getFieldName(ctl) + '에 값이 없습니다.');
				ctl.focus();
				return false;
			}
			return true;
		},
		date : function(ctl, val){
			if(val==''){
				return true;
			}

			try{
				$.datepicker.parseDate( option.dateFormat, val);
			}catch(e){
				alert(getFieldName(ctl) + '의 값이 올바른 날짜의 값이 아닙니다. 날짜를 입력하세요.');
				ctl.focus();
				return false;
			}

			return true;
		},
		rangedate : function(ctl, val, opt){
			if($('#'+opt[1]).val() > $('#'+opt[2]).val()){
				alert(getFieldName(ctl) + "의 시작일이 종료일보다 클 수 없습니다.");
				ctl.focus();
				return false;
			}
			return true;
		},
		ext : function(ctl, val, opt){
			val = val.toLowerCase();
			if(val=='') {
				return true;
			}
			for(var i=1; i<opt.length; i++){
				if(val.endsWith('.'+opt[i])){
					return true;
				}
			}
			
			alert(getFieldName(ctl) + "에 첨부한 문서 종류는 등록 할 수 없습니다.");
			ctl.focus();
			return false;
			
		},
		equals: function(ctl, val, opt){//['equals:other name']
			var equalField = $('input[name="' + opt[1] + '"]');
			if(val!=getControlValue(equalField)){
				alert(getFieldName(ctl) + '의 값이 ' + getFieldName(equalField) + '의 값과 일치하지 않습니다.');
				return false;
			}

			return true;
		},
		mail: function(ctl, val, opt){
			if(val==''){
				return true;
			}
			var reg = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;
			if( reg.test(ctl.val())){
				return true;
			}
			alert(getFieldName(ctl) + "에 메일 주소를 입력하세요.");
		}
				
	};

	$.valid_fnc['password'] = function(ctl, val, opt){

		var pwd = val;
		// 비밀번호 - 자리수 체크 (6~16)
		if (pwd.length < 6 || pwd.length > 16) {
			alert('비밀번호는 6~16자리로 영문소문자와 숫자를 반드시 포함해 조합해야 합니다.'); // 비밀번호는 6~16자리의 영문소문자와 숫자만 입력 가능합니다.
			return false;
		}
		var alpaSmall = 'abcdefghijklmnopqrstuvwxyz';
		var num = '1234567890';
		var numcheck = false;
		var alphacheck = false;
		// 비밀번호 - 소문자 또는 숫자
		for (var i=0; i < pwd.length; i++ ) {
			// 숫자가 한글자라도 쓰였는지 확인.
			if( num.indexOf(pwd.substring(i,i+1))>= 0){
				numcheck = true;
			}
			// 영문 소문자가 한글자라도 쓰였는지 확인.
			if( alpaSmall.indexOf(pwd.substring(i,i+1))>= 0){
				alphacheck = true;
			}
		}
		// 비밀번호 - 영문소문자와 숫자가 혼합하여 쓰였는지 확인.
		if(numcheck == false || alphacheck == false){
			alert('비밀번호는 6~16자리로 영문소문자와 숫자를 반드시 포함해 조합해야 합니다.'); // 비밀번호는 6~16자리의 영문소문자와 숫자만 입력 가능합니다.
			return false;
		}

		return true;
	};