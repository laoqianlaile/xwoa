/**
 * 选择框控件
 * 
 * @author:	zk
 * @version: 0.1
 * @create: 2014/4/15
 * 
 * @modify: 2014/4/15 by zk
 */

 define(['jquery', 'Core', 'AutoComplete'], function($, Core, AutoComplete) {
	 var Config = {
           SELECTOR: 'select,:checkbox,:radio',
           RADIO_PARENT_SELECTOR: '.radio-group',
           RADIO_PARENT_CLASS:	'radio-group',
           CHECKBOX_PARENT_SELECTOR: '.checkbox-group',
           CHECKBOX_PARENT_CLASS:	'checkbox-group'
	 };
	 
	 /**
	  * 获取radio或者checkbox存储的data-value值
	  * 
	  * 按照本身值 -> 父节点值 -> 兄弟节点值 的优先级顺序获取
	  */
     function _getDataValue(element) {
		 // 本身值  
		 var value = element.data('value');
		 if (value != undefined) {
			 return value;
		 }
		 
		 var selector, tag;
		 if (element.is(':checkbox')) {
			 selector = Config.CHECKBOX_PARENT_SELECTOR;
			 tag = ':checkbox';
		 }
		 else if (element.is(':radio')) {
			 selector = Config.RADIO_PARENT_SELECTOR;
			 tag = ':radio';
		 }
		 
		 // 父节点值
		 var parent = element.parent();
		 if (parent.is(selector)) {
			 value = parent.data('value');
		 }
		 if (value != undefined) {
			 return value;
		 }
		 
		 // 兄弟节点值
		 var name = element.attr('name');
		 element.closest('form').find(tag+'[name='+name+']').each(function() {
			 var el = $(this);
			 
			 if (el.data('value') != undefined) {
				 value = el.data('value');
				 return false;
			 }
		 });
		 
		 return value;
	 }
	  
	 var S = {
	    init: function(container) {
		 	var inputs = $(Config.SELECTOR, container);
		 	
		 	// 自动填充值
		 	this.auto(inputs);
		 	
		 	// 自动补全
			AutoComplete.init(container);

//            this.needRequired($(':text', container));
	 	},

         /**
          * 为必填项前加上 *
           * @param inputs
          */
//        needRequired: function(inputs) {
//            inputs.each(function() {
//                var el = $(this);
//                if (el.hasClass('required') || el.data('required') || el.is('[required]')) {
//                	if (el.is(':checkbox') || el.is(':radio')) {
//                		
//                	}
//                
//                    $('<span>*</span>').addClass('required-label').insertAfter(el);
//                }
//            });
//        },
	 	
		/**
		 * 下拉框（select）、单选框（radio）、多选框（checkbox）自动选中
		 * 
		 * 元素中添加 data-value 属性(相同name中只要有一个有值)，值为需要选中的值
		 * 单选框（radio）可以在外层元素添加 radio-group CLASS 并添加 data-value 属性
		 * 多选框（checkbox）可以在外层元素添加 checkbox-group CLASS 并添加 data-value 属性
		 */
	    auto: function(inputs) {
	    
			// 下拉框
			inputs.filter('select').each(function() {
				var el = $(this), value = el.data('value');
				
				el.find('option').filter(function() {
                    return $(this).val() == value;
				}).prop('selected', true);
			});
			
			// 多选框
			inputs.filter(':checkbox').each(function() {
				var el = $(this), value = _getDataValue(el);
				
				// 转换成数组
				if (!$.isArray(value)) {
					value = [value];
				}
				
				if ($.inArray(el.val(), value) > -1) {
					el.prop('checked', true);
				}
			});
			
			// 单选框
			inputs.filter(':radio').each(function() {
				var el = $(this), value = _getDataValue(el);
				
				if (el.val() == value) {
					el.prop('checked', true);
				}
				else {
					// 后台会把1转换成true, 0转换成false
					if ((el.val() === '1' && value === true) || (el.val() === '0' && value === false)) {
						el.prop('checked', true);
					}
				}
			});
			
			return inputs;
	 	}
	 };
	 
	 return S;
 });