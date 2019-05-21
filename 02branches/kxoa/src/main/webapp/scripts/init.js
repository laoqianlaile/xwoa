define(function(require) {

//    var $ = require('jquery');
    require('./colorbox/jquery.colorbox.js')($);
    var addressBook = require('./addressBook.js');
    var selectUser = require('./selectUser.js');
      var notice = require('./notice.js');
    function toNotice(ls) {
        notice.show(4000);
    }

    return {
        start:function() {

        }  ,
        showBox:function() {
            $(".example7").colorbox({width:"80%", height:"80%", open:true, iframe:true});
        }     ,
        notify:function(f) {
            toNotice(f);
        }   ,
        showAddressBook:function(id, options) {
            addressBook.showAddressBookDetail(id, options);
        }  ,
        registerNotOpen:function() {
            $('#register').click(function(e) {
                alert('注册功能尚未开放！');
                e.preventDefault();
            });
        },
        initializeAjax:function() {

        }   ,
        autoSelectUser:function(options) {
            selectUser.autoSelectUser(options);
        }     ,
        initializeUserName:function(options) {

            selectUser.initializeUserName(options);
        } ,
        showResultTip:function(){
            $.colorbox({html:'<p style="width: 200px;font-size: 14px;text-align: center;">操作成功！</p>',opacity:0.4});
        }
    }


});


seajs.use('init', function(init) {
    //重要！！
    //初始化的方法在此执行

    init.registerNotOpen();//注册尚未开放
    init.initializeAjax();//初始化ajax全局配置

    //下面是页面需要调用的方法，可以直接在标签上写onclick="showAddressBook(id)";
    //根据Id来查看通讯录
    window.showAddressBook = function(addressBookId, options) {
        init.showAddressBook(addressBookId, options);
    }
    //自动提示autoComplete选择用户模块
    window.autoSelectUser = function(obj, options) {
        if (obj) {
            options.object = $(obj);
        } else {
            options.object = $('#userCode');
        }


        init.autoSelectUser(options);
    }
    //在使用选择用户模块时，可能需要初始化userName
    window.initializeUserName = function(options) {
        init.initializeUserName(options);
    }
    window.notify = function(){
        init.notify();
    }
    window.showResultTip = function(){
        init.showResultTip();
    }
});