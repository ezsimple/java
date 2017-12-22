var _modal;
var _aTable;
var linkPopup = function (ele, ui_id, data) {
	if (typeof _modal_title == 'undefined')
		var _modal_title = "　";
    var viewModal = new AViewModal({
//			width: 1000,
        url: '../piece/-' + ui_id + '-bf.sh',
        data: data,
        title : _modal_title,
        closeClick: function () {
            if (_aTable) {
                _aTable.reload();
            }
        },
    });
    viewModal.show();
    _modal = viewModal;
}

// 수정용 Modal
var linkEditPopup = function (ele, ui_id, data) {
    var updateModal = new AUpdateModal({
//			width: 1000,
        url: '../piece/-' + ui_id + '-bf.sh',
        data: data,
        title : _modal_title,
        okClick : function() {
            var form = $('.modal-body > form',this.element);
            $(form).each(function() {
                $(this).find('[name=action_type]').val('U');
            })
            var url = '../action/-'+ui_id+'.sh';
            var formData = form.serializeArray();
            // root.console.log(formData);
            $.post(url,formData,function(data,textStatus,xhr){
                (data.success)?location.reload():alert("처리하는 중 오류가 발생하였습니다. \n문제가 지속되면 관리자에게 문의하세요");
            })
        },
        closeClick: function () {
            if (_aTable) {
                _aTable.reload();
            }
        },
    });
    updateModal.show();
    _modal = updateModal;
}

// 삭제용 Modal
var linkDelPopup = function (ele, ui_id, data) {
    var confirmModal = new AConfirm({
        title: '확인요청',
        msg : '삭제 하시겠습니까?',
        url : '../piece/-'+ui_id+'-bf.sh',
        okClick : function() {
            var modal = $('.modal-body',this.element);
            $form = $('<form method="post" enctype="multipart/form-data"></form>');
            $('<input>').attr({type:'hidden', name:'ui_id', value:ui_id}).appendTo($form);
            $('<input>').attr({type:'hidden', name:'action_type', value:'D'}).appendTo($form);
            $.each(data, function(k,v) {
                $('<input>').attr({type:'hidden', name:k, value:v}).appendTo($form);
            });
            $form.appendTo(modal);
            var url = '../action/-'+ui_id+'.sh';
            var formData = $form.serializeArray();
            $.post(url,formData,function(data,textStatus,xhr) {
                (data.success)?location.reload():alert("처리하는 중 오류가 발생하였습니다. \n문제가 지속되면 관리자에게 문의하세요");
            })
        }
    });
    confirmModal.show()
    _modal = confirmModal;
}
var linkFnc = function(obj){}