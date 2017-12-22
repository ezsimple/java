var isMobile = false;

$(function() {
	isMobile = $.cookie('isMobile') == 'Y';
	//수정모드에서 필드클릭시 에디트모드로 전환
	$(document).on('click', '.view_control', function(e){
		var trg = $(e.currentTarget);

		if(trg.attr('isEditMode')=='true'){
			trg.hide();
			$('.editable',trg.parent()).show().focus();
		}	
	});
	//수정모드에서 콘트롤 값 변경시 에디트모드->조회모드로 전환
	$(document).on('change', '.editable', function(e){
		var trg = $(e.currentTarget);
		var type = trg.attr('type');
		if(type=='file' || type=='files'){
			return;
		}
		
		var view = $('.view_control',trg.parent());
		var val = getText($('.control',trg));
		if(val==''){
			view.html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
		}else{
			view.text(val);
		}
		
		trg.hide();
		view.show();
	
	});
	
});

function getText(obj){
	var text = '';
	if(obj.length<1){
		return null;
	}
	var nodeName = obj.get(0).nodeName;
	
	if(nodeName =='SELECT'){
		var val = $('option:selected',obj).val();
		text = $('option:selected',obj).text();
		text = val=='' ? '' : text;
	}else if(nodeName =='RADIO'){
		var ctl = obj.closest('.editable');
		text = $('label[for='+$('input:checked',ctl).attr('id')+']').text();
	}else if(nodeName =='DIV' || nodeName =='SPAN'){
		return null;
	}else{
		text = obj.val();
	}
	return text;
}

//콘트롤을 초기화 한다.
function initLoadingControl(){	
	var fields = $('.field');
	
	for(var i=0; i< fields.length; i++){
		var fld = $(fields[i]);
		fld.removeClass('field');//이미 초기화 된 경우 재 초기화 되지 않도록
		
		var ctl = $('.editable', fld);
		
		if(ctl.length<1){
			continue;
		}
		
		var val;
		var width ='95%';
		var view = $('<span class="view_control">&nbsp;</span>');
		var obj = $('.control', fld);

		val = getText(obj);

		if(val!=null){
			view.html(val);	
		}else{
			view.html($('.control', fld).html());
		}
		view.css({width: width, disply:'inline', overflow:'hidden', 'margin-right':'8px'});
		ctl.hide();
		fld.append(view);
	}
	

	//아이콘처리
	var icons = $('.icon_');
	for(var i=0;i<icons.length;i++){
		var icon = $(icons[i]);
		var src = icon.attr('src');
		var title =icon.text();

		icon.html('<img src="' + src + '" title="' + title + '"/>');
	}
	
	//검색UI 설정
	var searchLabels = $('.search_label');
	var mwidth = 0;
	for(var i=0;i<searchLabels.length;i++){
		var width = $(searchLabels[i]).width();
		
		if(width>mwidth){
			mwidth = width;
		}
	}
	searchLabels.css({width: mwidth});
}

function addAttach(id){
	$('#attachs_'+id).append($('.attachTpl_'+id).clone().removeClass('attachTpl_'+id).show());
}

function delFile(file_id){
	$('#'+file_id).val(file_id);
	$('.'+file_id).hide();
}
function delField(id_field){
	$('#'+id_field).val(id_field);
	$('.'+id_field).parentsUntil('tr').parent().hide();

}

function cancel(ele){
	var page = $(ele).closest('.unit_page');;
	var view_controls = $('.view_control', page);
	view_controls.show();
	view_controls.attr('isEditMode','false');

	$('.editable', page).hide();
	//버튼제어
	$('.save_btn', page).hide();
	$('.del_btn', page).hide();
	$('.edit_btn', page).show();
	$('.cancel_btn', page).hide();
	try {
		cancelCallback(ele);
	} catch (e) {
		;
	}
}
function closePop(ele){
	$( "#dialog" ).dialog('close');	
}

function edit(target, action_type){
	var trg = $(target);
	var page = trg.attr('type')=='page' ? trg : trg.closest('.unit_page');

	$('[name=action_type]', page).val(action_type ? action_type : 'U');
	
	var view_controls = $('.view_control', page);
	view_controls.hide();
	view_controls.attr('isEditMode','true');
	
	$('.editable', page).show();
	//버튼제어
	$('.del_btn', page).show();
	$('.save_btn', page).show();
	$('.edit_btn', page).hide();
	$('.cancel_btn', page).show();
	try {
		editCallback(target);
	} catch (e) {
		;
	}
}

function del(ele){	
	if(!confirm('데이타를 삭제하시겠습니까?')){
		return;
	}
	var page = $(ele).closest('.unit_page');
	var form = $('form', page);
	var ui_id = $('[name=ui_id]',form).val()
	var url = '../action/-'+ui_id+'.sh';

	$('[name=action_type]', page).val('D');
	
	var formData = form.serializeArray();		

	$.post(url, formData, function(data, textStatus, xhr){

		//var data = $.parseJSON(response);
		
		if(data.success){
			try {
				submitCallback(form, data);
			} catch (e) {
				alert("submitCallback() 함수를 구현하세요.");
			}
		}else{
			alert("처리하는 중 오류가 발생하였습니다. \n문제가 지속되면 관리자에게 문의 하세요.\n" + data.message);					
		}
		
	});
}


function search(){
	//폼 정합성 체크
	
	goPage(0);
	
	return false;
}

function goPage(pageNo){
	
	var form = $('form');
	var target_layer = $('#main_layout').find('.monitor');
	
	var data = {
		_start: (pageNo-1)*20
	};
	
	var fields = form.serializeArray();
	$.each(fields, function(i, field){
		data[field.name] =field.value;
	});
	
	if(pageNo!=0){
		data['pageNo'] = pageNo;
	}
	var url = document.location.href;
	if(url.indexOf('/piece/')<0){
		var urls = url.split('/');
		url = '../piece/' + urls[urls.length-1].replace('.sh','-f.sh');
	}
	target_layer.load(url, data);
}


function form_submit(ele){	
	var page = $(ele).closest('.unit_page');
	var form = $('form', page);
	//폼 정합성 체크
	if(!valid(form)){
		return;
	}
	var url = '../action/-'+$('[name=ui_id]',form).val()+'.sh';
	//첨부파일이 있는 폼 처리
	if($('input[type=file]',form).length>0){
		attach_form_submit(url, form);
		return;
	}

	var formData = form.serializeArray();		

	$.post(url, formData, function(data, textStatus, xhr){

		//var data = $.parseJSON(response);
		
		if(data.success){
			try {
				submitCallback(form, data);
			} catch (e) {
				alert("submitCallback() 함수를 구현하세요.");
			}
		}else{
			alert("처리하는 중 오류가 발생하였습니다. \n문제가 지속되면 관리자에게 문의 하세요.\n" + data.message);					
		}
		
	});
}
function attach_form_submit(url, form){
	var fd = new FormData(form[0]);
	//FormData를 지원하지 않는 경우 처리
	if(fd==null){
	    $('#msg').text('업로드 정보 없습');
	    form.attr('target','submit_frame');
	    form.submit();
	    return;
	}
	//FormData를 지원하는 경우 처리
	var xhr = new XMLHttpRequest();
	 
	xhr.upload.addEventListener("progress", function(e, a1, a2) {
	       if (e.lengthComputable) {
	            var percentage = Math.round((e.loaded * 100) / e.total);
	           // $("#msg").text( ' - ' + percentage + '%');
	        	$('#msg').animate({width: percentage + '%'},70);	        }
	    }, false
	);
	
	xhr.onreadystatechange = function() { 
	    if (xhr.readyState == 4 && xhr.status == 200) {
			try {
				submitCallback(form, data);
			} catch (e) {
				alert("submitCallback() 함수를 구현하세요.");
			}
	    }
	};
	
	xhr.upload.addEventListener("load", function(e){
	       $('#msg').text('전송완료');
	    }, false
	);
	     
	xhr.open("POST", url);
	      
	xhr.send(fd);
	
}
//타이들을 설정한다.
function setTitle(ele){
	ele = $(ele);
	var obj = ele.closest('.monitor');
	$('#'+obj.attr('id')+'_title').text(ele.text());

}
function search(ele){
	var target = $(ele).closest(".monitor");
	var form = $(ele).closest("form");
	
	if(!valid(form)){
		return;
	}
	
	ui_id = $('[name=ui_id]', form).val();
	
	target.load('../piece/-'+ui_id+'-bf.sh',$(form).serializeArray());
}
function linkLoad(ele, ui_id, data, selector){
	var target;
	
	if($.isNumeric(selector)){
		target = $(ele).closest(".monitor");
		var idx = parseInt(target.attr('monitor')) + parseInt(selector);
		target = $('.monitor' + idx);
	}else{
		target = $(ele).closest(".monitor");
	}
	if(target.length<1){//로딩될 타켓이 없는 경우
		linkPopup(ele, ui_id, data);
		//alert('페이지를 불러올 selector : ' + selector + ' 를 찾을수 없습니다.');
		return;
	}
	if(target.attr('monitor')){
		target.closest('.split_tab').tabs( "option", "active", parseInt(target.attr('monitor'))-1 );
	}
	target.load('../piece/-'+ui_id+'-bf.sh',data, function(){
		
		var type = $(ele).attr('type');
		var isEdit = type=='add' || type=='edit';
		if(isEdit){//수정모드로 전환
			setTimeout(function(){
				edit($('.unit_page', target), (type=='add' ? 'I' : 'U'));
			},1000);
		}
	});
}
function linkPopup(ele, ui_id, data){
	data['ui_id'] = ui_id;
	var dialog = $( "#dialog" );
	
	if(dialog.length==0){

		dialog = $('<div id="dialog"></div>');
		$('body').append(dialog);
		dialog.dialog({
			autoOpen: false,
			modal: true,
			position: isMobile ? {} : { my: "center top", at: "center top", of: 'body' },
			minWidth: isMobile ? 300 : 1000,
			width: isMobile ? '100%' : 1000,
			show: {
				 effect: "blind",
				 duration: 1000
			},
			hide: {
				effect: "blind",
				duration: 1000
			}
		});
	}
	dialog.load('../piece/-'+ui_id+'-bf.sh',data, function(){
		var type = $(ele).attr('type');
		var isEdit = type=='add' || type=='edit';
		if(isEdit){//수정모드로 전환
			setTimeout(function(){
				edit($('.unit_page', dialog), (type=='add' ? 'I' : 'U'));
			},1000);
		}
	});
	dialog.dialog('open');
}
function linkPage(ele, ui_id, data, path){
	
	if(ui_id==''){
		document.location.href = path + '?' + $.param(data);
	}else{
		document.location.href = (path ? path + '/' : '') + '-'+ui_id+'.sh?' + $.param(data);
	}
}
function linkFnc(obj){
	;
}

function openPage(ui_id, tpl_path){
	var url = tpl_path ? '../' + tpl_path + '/-' + ui_id + '.sh' : ui_id;
	var form = $('#new_form');
	
	if(form.length<1){
		form = $('<form id="new_form" method="post" style="disply:none;" target="_blank"></form>');
		$('body').append(form);
	}
	

	form.attr('action', url);
	form.submit();
}

/*UI재구성
 * 예)
	var rebuildUiData = [
		[0,1], 
		[3,2], 
		[{title:'주소',index:[4,5,6],colspan:3}],
		[7,{title:'행정구역좌표',index:['(',8,',',9,')']}],
		[{title:'도로명주소',index:[10],colspan:3}],
		[{title:'건물명/아파트명',index:[11],colspan:3, style:{}}]
	];
	var selector = $('input[name=cu_id]').closest('table');
	rebuildUi(selector, rebuildUiData);
	//td 크기조절
	selector.find('td').css('width', (selector.width()-300)/2 + 'px');
*/

function rebuildUi(selector, rebuildUiData){
	if(isMobile){
		return;
	}
	
	try {
		var trs = $('tr', selector);
		var table = $('<table></table>');
		for(var i=0; i<rebuildUiData.length; i++){
			var tr = rebuildUiData[i];
			var nTr = $('<tr></tr>');
			for(var j=0; j<tr.length; j++){
				var td = tr[j];
				
				if($.type(td)==='number'){
					nTr.append($(trs[td]).children());
				}else{
					nTr.append($('<th>'+td['title']+'</th>'));
					var nTd =$('<td colspan="'+td['colspan']+ '"></td>');
					if(td['style']!=null) {
						nTd.css(td['style']);
					}
					var sTr = td['index'];
					for(var k=0;k<sTr.length; k++){
						if($.type(sTr[k])==='number'){
							nTd.append($(trs[sTr[k]]).find('td').children());
						}else{
							nTd.append(sTr[k]);
						}
					}
					nTr.append(nTd);
				}
			}
			table.append(nTr);
		}
		
		selector.children().remove();
		selector.append(table.children());
		selector.find('th').css('width','150px');
		
	} catch (e) {
		alert('UI재구성 중 오류 발생.\n 데이타를 확인하세요.\n' + e.toString())
	}
	
}