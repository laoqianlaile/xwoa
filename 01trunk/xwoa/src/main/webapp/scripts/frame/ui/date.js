/**
 * 封装时间控件
 * 
 * @author:	zk
 * @version: 0.1
 * @create: 2014/4/15
 * @modify: 2014/4/15 by zk
 */

 define(['jquery', 'WdatePicker', 'Core'], function($, WdatePicker, Core) {
	 /**
	  * 定义一些常量
	  */
	 var Config = {
		// 日期控件需要读取的属性
		WDATE_PROPERTIES: ['dateFmt', 'minDate', 'maxDate', 'startDate', 'readOnly', 'specialDates', 'specialDays', 'disabledDays', 'disabledDates'],
		
		// 初始化选择器
		SELECTOR: 'input.Wdate'
	 };
	 
	 /**
	  * 日期控件处理事件
	  * 
	  * 参见：http://dp.my97.net/dp/demo/index.htm
	  */
	function dateHandle(event) {
		var $this = $(this), options = {readOnly: true};
		
		// 显示时间
		if ('time' === $this.data('type')) {
			options.dateFmt = Core.TIME_FORMAT;
		}
		
		$this.readDatas(Config.WDATE_PROPERTIES, options);
		WdatePicker(options);
		
		event.preventDefault();
	}
 
	 var D = {
		init: function(container) {
		 	var inputs = $(Config.SELECTOR, container);
		 	
		 	inputs.each(function() {
				var el = $(this);
				
				// 默认设置输入框只读
				if (el.data('readOnly') !== false) {
					el.prop('readonly', true);
				}
			}).click(dateHandle);
		 	
			return inputs
	 	}
	 };
	 
	 return D;
 });