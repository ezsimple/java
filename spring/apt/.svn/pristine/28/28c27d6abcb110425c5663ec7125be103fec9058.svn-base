/**
 * Created by Administrator on 2015-10-14.
 */

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

$.fn.modal.Constructor.prototype.enforceFocus = function() {};
var AModal = ACommon.extend({
    template : '<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">'
    + '<div class="modal-dialog">'
    + '  <div class="modal-content">'
    + '    <div class="modal-header">'
    + '      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
    + '      <h4 class="modal-title">Modal title</h4>'
    + '    </div>'
    + '    <div class="modal-body" style="overflow-x: auto;"></div>'
    + '    <div class="modal-footer"></div>'
    + '  </div>'
    + '</div>',
    buttons : [],
    title : 'Modal',
    constructor : function(config) {
        AModal.superclass.prototype.constructor.apply(this,arguments);
        var self = this;

        this.element = $(this.template);
        $('.modal-title', this.element).text(this.title);
        //console.debug($(window).width());
        if(this.width ) {
            if($(window).width() > this.width) {
                $('.modal-dialog', this.element).width(this.width);
            }
        }
        this.element.appendTo($(document.body));

        this.modal = this.element.modal({
            backdrop: "static"
        });

        this.modal.on('hidden.bs.modal', function() {
            self.element.remove();
            self.closeClick.apply(this, arguments);
        });

        this.initButtons();
        this.initBody();

        var buttonEl = $('.modal-footer', this.element);

        $.each(this.buttons, function(j, button) {
            var btn = $('<a class="btn ' + (button.className ? button.className : 'btn-default') + ' btn-sm">' + button.name + '</a>');
            btn.click(function() {
                button.click.apply(this, arguments);
            }).appendTo(buttonEl)
        });
    },
    closeClick : function() {},
    initButtons : function(){
        var self = this;
        this.buttons = [];

        this.buttons.push({
            name : __m.get('button_cancel'),
            className : 'btn-danger',
            click : function() {
                //self.closeClick.apply(this, arguments);
                self.modal.modal('hide');
            }
        });
    },
    initBody : function(){
        $('.modal-body', self.element).text('');
    },
    show : function() {
        this.modal.show();
    },
    close : function() {
        //this.closeClick.apply(this, arguments);
        this.modal.modal('hide');
    }
});

var AConfirm = AModal.extend({
    constructor : function(config) {
        this.title = __m.get('object_confirm');
        AConfirm.superclass.prototype.constructor.apply(this,arguments);
    },
    okClick : function() {},
    initButtons : function(){
        var self = this;
        this.buttons = [];

        this.buttons.push({
            name : __m.get('button_ok'),
            className : 'btn-info',
            click : function() {
                self.okClick.apply(this, arguments);
                self.modal.modal('hide');
            }
        }, {
            name : __m.get('button_cancel'),
            className : 'btn-danger',
            click : function() {
                //self.closeClick.apply(this, arguments);
                self.modal.modal('hide');
            }
        });
    },
    initBody : function(){
        $('.modal-body', this.element).html(this.msg);
    }
});

var AFormModal = AModal.extend({
    constructor : function(config) {
        AFormModal.superclass.prototype.constructor.apply(this,arguments);
    },
    initButtons : function(){
        var self = this;
        //this.buttons = [];
        this.buttons.push({
            name : __m.get('button_close'),
            className : 'btn-danger',
            click : function(){
                //self.closeClick.apply(this, arguments);
                self.modal.modal('hide');
            }
        })
    },
    initBody : function(){
        var self = this;

        if(self.url) {
            $.ajax(self.url,{
            }).done(function(data){
                $('.modal-body',self.element).html(data);
            });
        }
    },
    reloadBody : function(reloadUrl){
        var self = this;
        if(reloadUrl) {
            $.ajax(reloadUrl,{
            }).done(function(data){
                $('.modal-body',self.element).html(data);
            });
        }
    },
});

var AViewModal = AModal.extend({
    constructor : function(config) {
        AFormModal.superclass.prototype.constructor.apply(this,arguments);
    },
    initButtons : function(){
        var self = this;
        this.buttons = [];
        this.buttons.push({
            name : __m.get('button_close'),
            className : 'btn-danger',
            click : function(){
                //self.closeClick.apply(this, arguments);
                self.modal.modal('hide');
            }
        })
    },
    initBody : function(){
        var self = this;

        if(self.url) {
            $.ajax(self.url,{
            }).done(function(data){
                $('.modal-body',self.element).html(data);
            });
        }
    },
    reloadBody : function(){
        var self = this;
        if(self.url) {
            $.ajax(self.url,{
            }).done(function(data){
                $('.modal-body',self.element).html(data);
            });
        }
    },
});

