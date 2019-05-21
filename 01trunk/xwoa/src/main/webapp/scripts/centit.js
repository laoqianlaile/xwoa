/**
 * 全局js文件，包含通用组件和通用方法
 *
 */

//声明命名空间
var centit = centit || {};

/**
 *
 * 在jQuery基础上对ajax请求封装，封装后统一了常用的初始化参数，如加载loading,请求异常等，也可以根据需要更改。
 */

centit.ajax = (function() {
    var URL_PREFIX = '';
    var loaderId = 'loading-icon';
    var appContext = '';

    function ajaxSettings(options) {

        var settings = {loadParameter:{},urlPrefix:'/demo',global:true,success:function(XMLHttpRequest, textStatus, errorThrown) {
            alert("返回：" + XMLHttpRequest.responseText);
        },beforeSend:function(XMLHttpRequest, textStatus, errorThrown) {
            showLoading();
        },complete:function(XMLHttpRequest, textStatus, errorThrown) {
            hideLoading();

        },error:function(XMLHttpRequest, textStatus, errorThrown) {
            //alert("请求出错：" + data.responseText);
            centit.utils.showMessage("请求出错:状态" + XMLHttpRequest.readyState + "," + statusParser(XMLHttpRequest.status));
            return false;
        }};
        options = $.extend(settings, options); //  如果options里面有定义的key，就使用options里的，否则使用默认值
        URL_PREFIX = options.urlPrefix;
        initLoading(options.loadParameter);
        $.ajaxSetup(options);//全局ajax设置 参见jQuery API
    }

    function showLoading() {
        $('#' + loaderId).fadeIn();
    }

    function hideLoading() {
        $('#' + loaderId).delay(500).fadeOut(400);
    }

    function statusParser(statusCode) {
        var resultMap = {'200':'响应正常','404':'请求的URL不存在','12029':'与服务器中断连接'};
        return resultMap[statusCode] ? resultMap[statusCode] : '未知结果状态' + statusCode;
    }

    function initLoading(options) {
        var settings = $.extend({iconURL:URL_PREFIX + '/images/ajax-loader.gif',to:$('#MainContent')}, options);
        $('<img/>', {
            'id':loaderId,
            'src':settings.iconURL,
            'style':'display:none'
        }).appendTo(settings.to);
    }

    return {
        initAjax:function(option) {
            ajaxSettings(option);
        },
        ver:1.0,
        getAppPath:function() {
            if (URL_PREFIX == '') {
                alert('请先初始化ajax全局设置');
                return false;
            } else {
                return URL_PREFIX.split("/")[1];
            }
        }   ,
        setPrefix:function(url) {
            URL_PREFIX = url;
        },
        initLoader:function(options) {
            var settings = $.extend({iconURL:URL_PREFIX + '/styles/default/images/ajax-loader.gif',to:$('#MainContent')}, options);
            $('<img/>', {
                'id':loaderId,
                'src':settings.iconURL,
                'style':'display:display'
            }).appendTo(settings.to);

        }
    }
})();

centit.utils = (function() {
    function _showMessage(options) {
        var content;
        if ($.isPlainObject(options)) {
            options = $.extend({html:'弹出信息'}, options);
            content = options.html;
        } else {
            content = options;
        }
        $.colorbox({opacity:0.4,html:content,open:true});
    }

    return {
        //替代alert()方法，可以直接把string作为参数传入
        showMessage:function(options) {
            _showMessage(options);
        } ,
        //禁止回车键提交
        preventAccessKey:function() {
            //IE下正常
              $('body').keypress(function(){
                  if(event.keyCode==13||event.which==13){event.keyCode=0;}
              });
        }
    }
})();


