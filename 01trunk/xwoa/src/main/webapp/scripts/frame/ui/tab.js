/**
 * 封装菜单标签栏控件
 * 
 * @author:	zk
 * @version: 0.1
 * @create: 2014/4/15
 * @modify: 2014/4/15 by zk
 */

define('Tab', ['jquery', 'pTab', 'Core'], function($, pTab, Core) {
	 /**
	  * 定义一些常量
	  */
	 var Config = {
		// 菜单标签栏需要读取的属性
		TAB_PROPERTIES: ['href', 'rel', 'fresh', 'external', 'warn', 'title']
	 };

	var T = {
		init: function(obj, options) {
			// 默认配置
			var DEFAULT_OPTION = $.extend({
				rel: '_blank',					// 相当于ID
				fresh: true,
				external: false					// 是否以IFRAME打开
			}, options);
		
			obj.each(function() {
				var el = $(this), options = $.extend({title: el.text()}, DEFAULT_OPTION);
				
				// 附加事件
				el.readDatas(Config.TAB_PROPERTIES, options);
				el.click(function(event) {
					pTab.openTab(options.rel, options.href, options);
					event.preventDefault();
				});
			});
		},
		
		open: function(id, url, title, options) {
			pTab.openTab(id, url, title, options);
		}
	}
	
	return T;
});