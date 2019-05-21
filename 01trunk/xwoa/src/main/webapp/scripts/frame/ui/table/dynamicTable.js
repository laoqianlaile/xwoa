define(['jquery', 'UI', 'Msg', 'Form'], function($, UI, Msg, Form) {
	var Config = {
		ADD_TEMPLATE : '<a class="btn btn-success addBtn" title="添加数据">添加数据</a>',
		REMOVE_TEMPLATE : '<a class="btn btn-danger deleteBtn" title="删除数据">删除数据</a>',
		
		DEFAULT_PROPERTIES : ['ref', 'addButton', 'submitButton', 'property', 'name', 'beforeAdd']
	};


	function replaceNames(tr, names) {
		tr = tr.clone();
		var elements = $('input, select', tr);
		
		elements.each(function(index, ele) {
			$(ele).attr('name', names[index]);
		});
		
		return tr;
	}
	
	function renderTR(table) {
		var trs = table.find('tbody tr').removeClass('even').removeClass('odd');
		
		trs.filter(':odd').addClass('even');
		trs.filter(':even').addClass('odd');
		
		// 全是ECtable惹的祸
		trs.unbind('hover').unbind('onmouseover').unbind('onmouseout');
		trs.removeAttr('onmouseover').removeAttr('onmouseout');
		trs.hover(function() {
			$(this).addClass('highlight');
		}, function() {
			$(this).removeClass('highlight');
		})
	}
	
	var D = {
		init: function(container, options) {
		
			$('template', container).each(function() {
				var op = {}, template = $(this).hide().readDatas(Config.DEFAULT_PROPERTIES, op);
				
				var table = $(op['ref'], container);
				
				var addBtn = $(op['addButton'], container);
				var searchBtn = $(op['submitButton'], container);
				var property = op['property'] ? op['property'] : [];
				var baseName = op['name'];
				
				var beforeAdd = op['beforeAdd'];
				if (beforeAdd && !$.isFunction(beforeAdd)) beforeAdd = eval('(' + beforeAdd + ')');
				
				// 替换按钮
				template.html(template[0].innerHTML
						.replace('{addBtn}', Config.ADD_TEMPLATE)
						.replace('{deleteBtn}', Config.REMOVE_TEMPLATE));
				template = template.find('tr');
				
				// 模板内含有可编辑td，替换其class
				template.find('td').each(function() {
					var td = $(this);
					if (td.hasClass('E')) {
						td.removeClass('E');
						td.addClass('editable');
					}
				});
				
				addBtn.click(function() {
					//TODO 添加initUI
					if (beforeAdd) {
						require("UI").init( beforeAdd($(replaceNames(template, property))).appendTo(table.find('tbody')).show() );
					}
					else {
						require("UI").init( $(replaceNames(template, property)).appendTo(table.find('tbody')).show() );
					}
					
					renderTR(table);
				});
				
				// 提交时替换NAME
				searchBtn.click(function(event) {
					var trs = $('tbody>tr', table);
					if (table.hasClass('requiredData') && 0 == trs.length) {
						Msg.confirm('表格数据不能为空，请添加数据。', {
							retryLabel: '添加数据',
							retryAction: function() {
								addBtn.trigger('click');
							}
						});
						return false;
					}
					
					form = Form.getForm($(this), document);
			
					if (!form.valid || form.valid()) {
						trs.each(function(index) {
							var tr = $(this);
							
							$('input, select, textarea', tr).each(function() {
								var ele = $(this);
								var name = ele.attr('name');
								name = name.substring(name.indexOf('.') + 1);
								
								ele.attr('name', baseName + "[" + index + "]." + name);
							});
						});
					
						form.submit();
					}
					
					event.preventDefault();
				});
				
				$('a.addBtn', table).live('click', function() {
					//TODO 添加initUI 
					var tr = $(this).closest('tr');
					
					if (beforeAdd) {
						beforeAdd($(replaceNames(template, property))).insertAfter(tr).show();;
					}
					else {
						$(replaceNames(template, property)).insertAfter(tr).show();;
					}
					
					renderTR(table);
				});
				
				table.find('a.deleteBtn').live('click', function() {
					var tr = $(this).closest('tr');
					tr.remove();
				});
				
			});
		}	         
	};
	
	return D;
});