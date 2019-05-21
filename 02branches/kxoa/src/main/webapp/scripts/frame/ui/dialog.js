/**
 * 封装弹出窗口控件
 * 
 * @author:	zk
 * @version: 0.1
 * @create: 2014/4/15
 * @modify: 2014/4/15 by zk
 */

define(['jquery', 'pDialog', 'Core'], function($, pDialog, Core) {
	 /**
	  * 定义一些常量
	  */
	 var Config = {
		// 弹出窗口需要读取的属性
		DIALOG_PROPERTIES: ['href', 'rel', 'title', 'width', 'height', 'max', 'mask', 'maxable', 'minable', 'fresh', 'resizable', 'drawable', 'close', 'callback', 'params']
	 };
	 
	function closeHandle() {
		var dialog = this;
		var target = dialog.data('target'), result = dialog.data('result');
		
		if (!result) {
			// 取消表格的编辑状态
			$(document).trigger('click');
			return true;
		};

		return closeCallback(result, target);
	}
	
	function closeCallback(data, obj, tr, table) {
		var key = data.key, value = data.value;
		
		var refObject = obj.parent().find('input[name='+obj.data('ref')+']');
		
		// 第一种方式，直接获取值。直接把data的值赋给obj
		if (data && key === undefined && value === undefined) {
			obj.val(data);
		}
		
		// 第二种方式，显式value赋给对象，隐式key赋给另一个对象
		else if (key !== undefined && value !== undefined && refObject.length) {
			obj.val(value);
			refObject.val(key);
		}
		
		// 取消表格的编辑状态
		$(document).trigger('click');
		
		return true;
	}
	
	
	 
	var D = {
		init: function(obj, options) {
			// 默认配置
			var DEFAULT_OPTION = $.extend({
				rel: '_blank',					// 相当于ID
				fresh: true,
				max: false,						// 以最大化方式打开
				mask: true,
				maxable: false,
				minable: false,
				resizable: true,
				drawable: false
			}, options);
		
			obj.each(function() {
				var el = $(this), options = $.extend({title: el.text()}, DEFAULT_OPTION);
				
				// 附加事件
				el.readDatas(Config.DIALOG_PROPERTIES, options);
				
				// 关闭窗口回调函数
				if (options.close && options.close !== true) {
					options.close = eval(options.close);
				}
				
				el.click(function(event) {
					var target = $(this);
					
					var params = options.params;
					
					if (params) {
						params = Core.readTagParams(params);
						options.href = Core.addParams4URL(options.href, params);
					}
					
					// 通过弹出框选值，添加close事件，并保存触发事件的INPUT输入框
					if (target.hasClass('selectable')) {
						options.close = closeHandle;
						options.target = target;
					}
					
					pDialog.open(options.href, options.rel, options.title, options);
					
					event.preventDefault();
				});
			});
		},
		
		selectableCallback: function() {
	
		},

		open: function(id, url, title, options) {
			pDialog.open(id, url, title, options);
		}
	}
	
	return D;
});