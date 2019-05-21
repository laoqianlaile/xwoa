/**
 * 表单增强
 * 
 * @author:	zk
 * @version: 0.1
 * @create: 2014/4/15
 * @modify: 2014/4/15 by zk
 */

define(['jquery', 'Core', 'Validate', 'Cleditor'], function($, Core, Validate) {
	
	/**
	 * 定义一些常量
	 */
	var Config = {
		// 表单增强需要读取的属性
		FORMPLUS_PORPERTIES: ['isPlus','validate', 'enterSubmit', 'changeSubmit', 'autoFocus', 'upPrev', 'downNext', 'enterNext'],
		
		// 初始化选择器
		SELECTOR: 'form'
	};
	
	var F = {
		/**
		 * 表单功能增强
		 * 
		 * 回车提交表单（搜索页面表单）、下拉框选中后提交表单
		 * 表单校验
		 * 页面加载后聚焦首个可见表单元素
		 * 按键 ↑ 跳转聚焦到上一个可见表单元素
		 * 按键 ↓ 跳转聚焦到下一个可见表单元素
		 * 回车后跳转聚焦到下一个可见表单元素（编辑页面表单）
		 */
		init: function(container, op) {
			var forms = $(Config.SELECTOR, container);
			
			forms.each(function() {
				var form = $(this);
				
				// 默认配置
				var options = $.extend({
					isPlus: true,
					validate: false,					// 验证
					enterSubmit: false,					// 回车提交表单
					changeSubmit: false,				// 下拉框改变值提交表单
					autoFocus: true,					// 聚焦首个表单元素
					upPrev: true,						// 按键 ↑ 跳转聚焦到上一个可见表单元素
					downNext: true,						// 按键 ↓ 跳转聚焦到下一个可见表单元素
					enterNext: false					// 回车后跳转聚焦到下一个可见表单元素
				}, op);
				
				form.readDatas(Config.FORMPLUS_PORPERTIES, options);
				
				// 拒绝增强
				if (!options.isPlus) {
					return form;
				}
	
				var inputs = $(':input', form).data('form', form);
				
				// 验证
				if (options.validate) {
					var validator = form.validate();
					form.data('validator', validator);
				}
	
				// 回车提交表单
				if (options.enterSubmit) {
					inputs.filter(':text, textarea').bind('keyup', function(event) {
						if (event.keyCode == Core.KEYCODS.ENTER) {
							form.submit();
						}
					})
				}
				
				// 下拉框改变值提交表单
				if (options.changeSubmit) {
					inputs.filter('select, :radio').bind('change', function(event) {
						form.submit();
					})
				}
				
				// 页面加载后聚焦首个可见表单元素
				inputs.filter(function() {
					return !$(this).prop('readonly')
				}).filter(':visible:first').focus();
				
				// TODO 按键 ↑ 跳转聚焦到上一个可见表单元素
				// TODO 按键 ↓ 跳转聚焦到下一个可见表单元素
				// TODO 回车后跳转聚焦到下一个可见表单元素（编辑页面表单）
				
				
				$('select[refUrl]').interactSelect();
				
				//rich text editor
				$('.cleditor').cleditor();
			});
		
			return forms;
		},
		
		/**
		 * 获取元素对应的FORM
		 */
		getForm: function(el, container) {
			// 自身存储的form 
			var form = el.data('form') || el.attr('form');
			form = $(form, container);
			
			if (form.length) {
				return form;
			}
		
			// 默认使用父节点FORM
			form = el.closest('form');
			
			// 没有找到父节点FORM
			if (!form.length) {
				var date = new Date();
				form = $('<form></form>').attr('id', 'form'+date.getTime())
						.attr('method', 'post')
						.appendTo(container);
			}
			
			el.data('form', form);

			return form;
		}
	};
	
	return F;
});