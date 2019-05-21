(function($){
	// 编辑
    function enableEditTable(event) {
    	
    	var $this = $(this);
    	
    	if ($this.hasClass('editing')) return true;
    	
    	var editTD = $('td.editing');
    	
    	// 点击非正在编辑TD时，先取消所有编辑
    	if (editTD[0] && editTD[0] != $(event.target).parent('td')) {
    		$(document).trigger('click');
    	}
    	
    	$this.addClass('editing').css({
    		cursor:'text'
    	}).find('div').fadeOut("fast", function() {
    		$this.find('input[type=text],select').fadeIn("fast", function() {
    			this.focus();
    		});
    	});
    }
    
    // 取消编辑
    function disableEditTable(event) {
    	
    	var target = $(event.target);
    	
    	// 点击正在编辑TD时无效
    	if (target.hasClass('editing') || target.parent('td').hasClass('editing')) return true;
    	
    	var value = [];
    	$('td.editing').removeClass('editing').css({cursor:'pointer'}).each(function() {
    		var $this = $(this);
    		var value = [];
    		var validFlag = 0;
    		var table = $this.parents('table');
    		var columns = table.find('thead tr:first th');
    		var line = $this.closest('tr');
    		
    		$('input[type=text],select', $this).each(function() {
    			var element = $(this);
    			
    			validFlag = element.valid();
    			if (!validFlag) {
    				return false;
    			}

    			if (element.is('input')) {
    				value.push(element.val());
    			}
    			else if (element.is('select')) {
    				value.push($('option:selected', element).text());
    			}
    			
    		})
    		
    		if (validFlag) {
    			// 单独提交每个单元格
    			if (table.data('action')) {
    				
    				$.post(table.data('action'), table.data('dataFn').call(table, value, $this, columns, line), function(data) {
    					$('input[type=text],select', $this).prop('readonly', true);
    					
    					if (table.data('callback').call(table, data) === true) {
	    					// 恢复单元格显示
	    					$('input[type=text],select', $this).prop('readonly', false).fadeOut("fast", function() {
		        				$this.find('div:last').html(value.join(' ')).fadeIn("fast");
		        			});
    					}
    					
    				}, 'json');

    			}
    			else {
    				$('input[type=text],select', $this).fadeOut("fast", function() {
        				$this.find('div:last').html(value.join(' ')).fadeIn("fast");
        			});
    			}
    		}
    		
    	});
    }
    
    // Tab键切换
    function switchTab(event) {
    	var td = $('td.editing');
    	
    	if (event.keyCode == Centit.keyCode.ENTER || event.keyCode == Centit.keyCode.TAB ) {
    		var next = td.next('td.editable');
        	console.log(next);
    	}
    	
    }
	
	$.editTable = {
		editTable : function() {
	    	$('tbody td.editable').each(function() {
	    		
	    		var $this = $(this);
	    		var value = [];
	    		
	    		if ($this.data('doneFlag')) return true;
	    		
	    		// 隐藏编辑框
	    		$('input[type=text],select', $this).width($this.width()).height(24).css({
	    			padding:0, margin:'-5px'
	    		}).each(function() {
	    			var element = $(this);
	    			
	    			if (element.is('input')) {
	    				value.push(element.val());
	    			}
	    			else if (element.is('select')) {
	    				value.push($('option:selected', element).text());
	    			}
	    			
	    			element.hide();
	    		});
	    		
	    		var text = $this.text();
	    		
	    		$this.click(enableEditTable).css({
	    			cursor:'pointer'
	    		}).removeClass('editing').append('<div>'+ value.join(' ') +'</div>');
	    		
	    		// 添加标记
	    		$this.data('doneFlag', true);
	    	});
	    	
	    	var tables = $('tbody td.editable').parents('table');
	    	tables.each(function() {
	    		var table = $(this);
	    		
	    		// 单独提交的action
	    		if (table.attr('action')) {
	    			table.data('action', table.attr('action'));
	    			
	    			var dataFn = table.attr('dataFn') || function() {};
	    			if (! $.isFunction(dataFn)) dataFn = eval('(' + dataFn + ')');
	    			table.data('dataFn', dataFn);
		    		
	    			var callback = table.attr('callback') || function() {return true;};
	    			if (! $.isFunction(callback)) callback = eval('(' + callback + ')');
	    			table.data('callback', callback);
	    		}
	    		
	    		
	    	});
	    	
	    	$(document).click(disableEditTable);
	    }
	    
	};
	
	function replaceNames(tr, names) {
		tr = tr.clone();
		var elements = $('input, select', tr);
		
		elements.each(function(index, ele) {
			$(ele).attr('name', names[index]);
		});
		
		return tr;
	}
	
	$.dynamicTable = {
		ADD_TEMPLATE : '<a class="btn btn-success addBtn" title="添加数据"><i class="icon-plus icon-white"></i></a>',
		REMOVE_TEMPLATE : '<a class="btn btn-danger deleteBtn" title="删除数据"><i class="icon-remove icon-white"></i></a>',
		
		dynamicTable : function() {
			$('table.dynamic').each(function() {
				var table = $(this);
				var addBtn = $(table.attr('addButton'));
				var searchBtn = $(table.attr('submitButton'));
				var property = table.attr('property') ? table.attr('property').split(',') : [];
				var baseName = table.attr('name');
				
				var beforeAdd = table.attr('beforeAdd');
				if (beforeAdd && !$.isFunction(beforeAdd)) beforeAdd = eval('(' + beforeAdd + ')');
				
				// 增加的TR模板
				var template = $('thead>tr.template', table).hide();
				
				// 替换按钮
				template.html(template[0].innerHTML
						.replace('{addBtn}', $.dynamicTable.ADD_TEMPLATE)
						.replace('{deleteBtn}', $.dynamicTable.REMOVE_TEMPLATE));
				
				addBtn.click(function() {
					if (beforeAdd) {
						beforeAdd($(replaceNames(template, property))).appendTo(table.find('tbody')).initUI().show();
					}
					else {
						$(replaceNames(template, property)).appendTo(table.find('tbody')).initUI().show();
					}
					
					$.editTable.editTable();
				});
				
				// 提交时替换NAME
				searchBtn.click(function() {
					var trs = $('tbody>tr', table);
					if (table.hasClass('requiredData') && 0 == trs.length) {
						Centit.msgConfirm('表格数据不能为空，请添加数据。', {
							retryLabel: '添加数据',
							retryAction: function() {
								addBtn.trigger('click');
							}
						});
						return false;
					}
					trs.each(function(index) {
						var tr = $(this);
						
						$('input, select, textarea', tr).each(function() {
							var ele = $(this);
							var name = ele.attr('name');
							name = name.substring(name.indexOf('.') + 1);
							
							ele.attr('name', baseName + "[" + index + "]." + name);
						});
					});
				});
				
				$('a.addBtn', table).live('click', function() {
					var tr = $(this).closest('tr');
					$(replaceNames(template, property)).insertAfter(tr).initUI().show();
					$.editTable.editTable();
				});
				
				table.find('a.deleteBtn').live('click', function() {
					var tr = $(this).closest('tr');
					tr.remove();
				});
				
			});
		}
	};
	
	function cloneTemplate(template, name) {
		var temp = template.clone();
		temp.data('template-name', name)
			.addClass('template-child')
			.show()
			.find('input,select,textarea').prop('disabled', false);
		
		return temp;
	}
	
	$.copyable = {
		REMOVE_TEMPLATE : '<a class="btn btn-danger deleteBtn" title="删除数据"><i class="icon-remove icon-white"></i></a>',
		
		copyall : function () {
			$('.copyable').each(function() {
				var template = $(this);
				template.wrap('<div></div>');
				template.html(template[0].innerHTML
						.replace('{deleteBtn}', $.dynamicTable.REMOVE_TEMPLATE));
				
				var isHidden = template.attr('hide') == 'true' ? true : false;
				var addBtn = $(template.attr('addButton'));
				var searchBtn = $(template.attr('submitButton'));
				var container = template.attr('container') ? $(template.attr('container')) : template.parent();
				var baseName = template.attr('name');
				var beforeAdd = template.attr('beforeAdd');
				if (beforeAdd && !$.isFunction(beforeAdd)) beforeAdd = eval('(' + beforeAdd + ')');
				
				// 模板隐藏、disable所有表单元素
				template.hide().find('input,select,textarea').prop('disabled', true);
				
				if (!isHidden) {
					if (beforeAdd) {
						before(cloneTemplate(template, baseName)).appendTo(container).initUI();
					}
					else {
						cloneTemplate(template, baseName).appendTo(container).initUI();
					}
					$.editTable.editTable();
				}
				
				addBtn.click(function() {
					cloneTemplate(template, baseName).appendTo(container).initUI();
					$.editTable.editTable();
				});
				
				$('.template-child a.deleteBtn').live('click', function() {
					var tr = $(this).closest('.template-child');
					tr.remove();
				});
				
				searchBtn.click(function() {
					var count = {};
					
					$('.template-child').each(function() {
						var child = $(this);
						var name = child.data('template-name');
						
						if (count[name] == undefined) {
							count[name] = 0;
						}
						else {
							count[name] = count[name] + 1;
						}
						var index = count[name];
						
						child.find('input[type=hidden],input[type=text],select,textarea').each(function() {
							var ele = $(this);
							var temp = ele.attr('name');
							temp = temp.substring(temp.indexOf('.') + 1);
							
							ele.attr('name', name + "[" + index + "]." + temp);
						});
					});
				});
			});
		}
	};
})(jQuery);