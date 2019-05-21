define(['jquery'], function($) {
	// 编辑
    function enableEditTable(event) {
    	
    	var $this = $(this);
    	
    	if ($this.hasClass('editing')) return true;
    	
    	var editTD = $('td.editing');
    	
    	// 点击非正在编辑TD时，先取消所有编辑
    	if (editTD[0] && editTD[0] != $(event.target).parent('td')) {
    		$(document).trigger('click');
    	}
    	
    	
    	// 通过弹出窗口获取值
		var selectable = $this.find('input[target=dialog]');
		if (selectable.length) {
			$this.addClass('editing');
			selectable.trigger('click');
    		return;
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


	var E = {
		init : function(container, options) {
			// 编辑完后直接提交
			var tables = $('tbody td.editable').parents('table');
			tables.each(function() {
				var table = $(this).css({
					'table-layout': 'fixed'
				});
				
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
		
			
			$('td.editable', container).each(function() {
				var $this = $(this);
				var value = [];
				
				if ($this.data('doneFlag')) return true;
				
				// 隐藏编辑框
				$('input[type=text],select', $this).each(function() {
					var element = $(this);
					
					if (element.is('input')) {
						value.push(element.val());
					}
					else if (element.is('select')) {
						value.push($('option:selected', element).text());
					}
					
					element.hide().width($this.width()).css({
						padding:0, margin:'-5px'
					});
				});
				
				var text = $this.text();
				
				$this.click(enableEditTable).css({
					cursor:'pointer'
				}).removeClass('editing').append('<div>'+ value.join(' ') +'</div>');
				
				// 添加标记
				$this.data('doneFlag', true);
			});
			
			$(document).click(disableEditTable);
		}
	};
	
	return E;
});