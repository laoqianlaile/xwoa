	$.STAT = {
	        id : "#statTable",
	        toolBar : 'div.tool-bar>div',
	        searchBar : 'div.search-bar',
			renderTableCallbacks:[],
			paramMap : {},
			headerArray : {},
			callbacks: {}
	};
	
	// 添加渲染表格回调函数
	$.STAT.addRenderTableCallback = function(callback, table) {
		var mapName;
		
		if (!table) {
			mapName = $.STAT.id.replace('#', '');
		}
		else {
			mapName = table.attr('id');
		}
		
		this.callbacks[mapName] = {};
		this.callbacks[mapName].renderTableCallbacks = [];
		this.callbacks[mapName].renderTableCallbacks.push(callback);
	}
	
	/**
 * IFRAME自适应高度
 */
	$.STAT.autoHeight = function autoHeight(iframe){
    
    if (!iframe) {
    	return false;
    }
    
    // chrome处理iframe上的onmousescroll和ff ie不同，可以支持nicescroll
    if ($.browser.chrome) {
    	// iframe的高度
    	var ih = iframe.contentDocument.body.offsetHeight;
    	
    }
    // 其他浏览器只好固定iframe高度了
    else {
    	
    }
    
    if(iframe.Document){//ie自有属性
        iframe.height = iframe.Document.body.scrollHeight;
    }else if(iframe.contentDocument){//ie,firefox,chrome,opera,safari
        iframe.height = iframe.contentDocument.body.offsetHeight;
    }
}
	
	// 解析搜索栏参数
	$.STAT.parseSearchBar = function(searchBar) {
		var map = {};
		
		searchBar.find('input,select').each(function() {
			var el = $(this), name = el.attr('name'), value = el.val();
			
			if (name) {
				map[name] = value;
			}
		});
		$.STAT.paramMap = map;
	}
	
	
	// 解析表头
	$.STAT.parseTableHeader = function(table) {
		var header = table.find('thead'), trList = header.find('tr'), headerArray = [[],[]];
		
		// 单层表头
		if (trList.length == 1) {
			trList.find('th').each(function() {
				headerArray[0].push($(this));
			});
		}
		// 双层表头
		else if (trList.length == 2) {
			var tr1 = trList.eq(0), tr2 = trList.eq(1).find('th');
			
			// 第二层表头index
			var index = 0;
			
			tr1.find('th').each(function() {
				var th = $(this), rowspan = th.attr('rowspan'), colspan = th.attr('colspan');
				th.data('length', 1);
				
				// 假设rowspan和colspan不会同时大于1
				if (rowspan == 2) {
					headerArray[0].push(th);
				}
				// colspan没有或等于1
				else if (!colspan || 1 == colspan) {
					headerArray[0].push(th);
					headerArray[1].push(tr2[index++])
				}
				// colspan大于1
				else if (colspan > 1) {
					th.data('length', colspan);
					
					headerArray[0].push(th);
					for (var i=0; i<colspan; i++) {
						headerArray[1].push(tr2.eq(index++))
					}
				}
			});
		}
		
		$.STAT.headerArray = headerArray;
		
		return headerArray;
	}
	
	// 根据tbody内td找到与之对应的thead里的th
	// level = 1 第一层， level = 2为第二层
	$.STAT.findTHeadByTD = function(td, level) {
		level = level || 1;
		
		var headerArray = $.STAT.headerArray, index = 0;
		
		if (level > headerArray.length) {
			level = 1;
		}
		
		// 第二层，直接给出
		if (level == 2) {
			return headerArray[1][td.index()];
		}
		
		// 第一层根据length计算
		for (var i=0; i<headerArray[0].length; i++) {
			var th = headerArray[0][i];
			index += parseInt(th.data('length') || 1);

			if (index > td.index()) {
				return th;
			}
		}
	}
	
	$.STAT.parseSearchBar($($.STAT.searchBar));
	$.STAT.parseTableHeader($($.STAT.id));
	
	// 根据新要求渲染表格
	$.STAT.renderTable = function(common) {
		var table = $(this.id);
		// 如果没有命令，则执行渲染表格
		if (!common || $.type(common) != "string") {
			// 传的参数既是表格
			if (common && $.type(common) != "string") {
				table = common;
			}
		
			table.find('tbody tr').each(function() {
				var tr = $(this), tdArray = [];
				
				// 存储数据
				tr.find('td').each(function() {
					var td = $(this);
					
					tdArray.push(td);
				});
				
				var renderTableCallbacks = $.STAT.callbacks[table.attr('id')].renderTableCallbacks;

				for (var i=0; i<renderTableCallbacks.length; i++) {
					renderTableCallbacks[i].call(tr, tdArray, $.STAT.headerArray, $.STAT.paramMap);
				}
			});
		}
		// 否则执行命令
		else {
			var args = [];
			for (var i=1; i<arguments.length; i++) {
				args[i-1] = arguments[i];
			}
			eval('$.STAT.'+common).apply(table, args);
		}
		
	}
	
	// 自动隐藏列
	$.STAT.autoHideColumn = function(options) {
		// 只选择第一行表头
		var header = $.STAT.headerArray[0], table = this, info = {columns:[]};
		
		for(var i=0; i<header.length; i++) {
			var th = header[i], name = th.text(), index = th.index();
			
			if (th.is(':hidden')) {
				continue;
			} 

			if (options && options.exclude) {
				// 排除某些列
				if ($.inArray(name, options.exclude) > -1) {
					continue;
				}
			}
			
			info.columns.push({
				name: name,
				index: index
			});
		}
		
		$(Mustache.render(parent.FlatLab.Template['HIDE_COLUMN'], info)).insertBefore(table.closest('div.row'));
		
		var buttons =  $('div.table-headers');
		buttons.data('width', buttons.width());
		buttons.hide();
		
		// 弹出选择按钮组事件
		$('button.btn-headers').click(function() {
			var btn = $(this), value = btn.data('value'), icon = btn.find('span');
			
			icon.removeClass('glyphicon-chevron-right glyphicon-chevron-left');

			// 已经展开
			if (value) {
				icon.addClass('glyphicon-chevron-right');
				buttons.fadeOut();
				btn.data('value', 0);
			}
			else {
				icon.addClass('glyphicon-chevron-left');
				buttons.fadeIn();
				btn.data('value', 1);
			}
		});

		// 隐藏列按钮事件
		$('button', buttons).click(function() {
			var btn = $(this), index = btn.data('index')+1;
			
			btn.toggleClass('grey');
			$('th', table).eq(index-1).add('td:nth-child('+index+')', table).toggle();
		});
	}
	
	// 工具栏添加按钮
	$.STAT.addButtonOnToolBar = function(template) {
		var toolBar = $($.STAT.toolBar);
		
		toolBar.append(template);
	}
	
	// 工具栏删除按钮
	$.STAT.removeButtonOnToolBar = function(selector) {
		var toolBar = $($.STAT.toolBar);
		
		toolBar.find(selector).remove();
	}

// 树形表格
if ($.fn.treetable) {
	// 获取首行ID
	var id = $('#statTable tbody tr:first').data('ttId');	
	$('#statTable').treetable({
		expandable : true
	}).treetable('expandNode', id);
	$("span.indenter").bind('click',function(){
		var table = $('#statTable tbody');
		$('tr:visible:odd', table).css({
			background:'#ffffff'
		}).attr({"bg":'#ffffff'});
		$('tr:visible:even', table).css({
			background: '#dfe4e8'
		}).attr({"bg":'#dfe4e8'});
	});
	
	$('#statTable tbody td:first-child').css({
		'text-align': 'left'
	});
}	

// 通过IFRAME方式打开报表，只能通过下面的方式使用DWZ框架的一些方法
if (parent.$) {

	$(function() {
		var navTab = parent.navTab, alertMsg=parent.alertMsg, ajaxTodo = parent.ajaxTodo;
		
		// 打开新窗口
		$("a[target=navTab]").each(function(){
			$(this).click(function(event){
				var $this = $(this);
				
				// 根据TD找到对应的标题
				var title = $this.attr('title') || $.STAT.findTHeadByTD($this.closest('td')).text();
				
				var tabid = $this.attr("rel") || "_blank";
				var fresh = eval($this.attr("fresh") || "true");
				var external = eval($this.attr("external") || "false");
				var url = $this.attr("href");
			
				navTab.openTab(tabid, url,{title:title, fresh:fresh, external:external});
				event.preventDefault();
			});
			
			var href = $(this).attr('href');
			href = href.replace('合计', '');
			$(this).attr('href', href);
		});
		
		// AJAX
		$("a[target=ajaxTodo]").each(function() {
			$(this).click(function(event){
				var $this = $(this);
				var url = $this.attr("href");
				var title = $this.attr("title");
				if (title) {
					alertMsg.confirm(title, {
						okCall: function(){
							ajaxTodo(url, $this.attr("callback"));
						}
					});
				} else {
					ajaxTodo(url, $this.attr("callback"));
				}
				event.preventDefault();
			});
		});
		
		// 弹出窗口
	});
	
}

$(function(){
//	top.document.getElementById("progressBar").style.display = "none";
//	top.document.getElementById("background").style.display = "none";
	var table = $('#statTable tbody');
	$('tr:visible:odd', table).css({
		background:'#ffffff'
	}).attr({"bg":'#ffffff'});
	$('tr:visible:even', table).css({
		background: '#dfe4e8'
	}).attr({"bg":'#dfe4e8'});
	$('tr', table).hover(function(){
		$(this).css({
			background:'#fdecae'
		});
	},function(){
		if($(this).attr("bg")=="#ffffff"){
			$(this).css({
				background:'#ffffff'
			});
		}
		else{
			$(this).css({
				background:'#dfe4e8'
			});
		}
	});
	
	$('select.chosen').chosen();
});