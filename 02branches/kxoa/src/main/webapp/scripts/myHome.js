(function() {
	$.myHome = {
		_op:{width:350, height:305, minW:50, minH:40},
		_home:null,
		_columns:[],
		_dialogs:[],
		_column_count:0,
		_current:null,
		_marginLeft:0,
		
		_add_column:function() {
			var length = this._columns.length;
			this._home.append('<div id="column'+ length +'" class="column"></div>');
			
			this._columns.push($('#column'+length));
			this._column_count = this._columns.length;
			
			$('#column'+length).data("count", this._column_count);
			this._dialogs.push([]);
			
			
			return this._columns[this._column_count - 1];
		},
		
		_make_columns:function(count, option, home) {
			if (!this._home) this._init(home, true);
			
			option = option.columns;
			var column = null;
			var columns = [];
			
			for (var i=0; i<count; i++){
				column = this._add_column();
				
				if (option && i<option.length) {
					column.data('width', (option[i].width ? option[i].width : '32%'));
				}
				
				// 初始化列信息
				columns[i] = new Array();
				for (var j=0; j<3; j++) {
					if (parseInt(column.data('width')) * (j + 1) <= 100) {
						columns[i][j] = null;
					}else {
						columns[i][j] = '_blank';
					}
				}
			}
			
			return columns;
		},
		
		_add_dialog:function(column, title, options) {
			column.append(CentitUI.frag["myHomeDialog"]);
			var dialog = column.find(">.dialog:last-child");
			
			dialog.find(".dialogHeader").find("h1").html(title);
			
			var op = $.extend({}, this._op, options);
			var height = op.height>op.minH?op.height:op.minH;
			var width = op.width>op.minW?op.width:op.minW;
			
			if (column.data('width')) {
				width = column.width() * parseInt(column.data('width')) / 100;
			}
			
			if(isNaN(dialog.height()) || dialog.height() < height){
				$(dialog).height(height+"px");
				$(".dialogContent",dialog).height(height - $(".dialogHeader", dialog).outerHeight() - $(".dialogFooter", dialog).outerHeight() - 6);
			}
			if(isNaN(dialog.css("width")) || dialog.width() < width) {
				$(dialog).width(width+"px");
			}
			
			//隐藏最小化按钮
			dialog.find("a.minimize").hide();
			dialog.find("a.maximize").hide();
			dialog.find("a.close").hide();
			
			//动态控制两个dialog之间的距离
			var w = dialog.parent().parent().width();
			var margin = "";
			if (w > 1000) {
				margin = "15px 20px 5px 5px";
			}else {
				margin = "15px 10px 5px 5px";
			}
			dialog.css({position: "static",
				margin: margin});
			
			return dialog;
		},
		
		_load_background:function() {
			this._home.append(CentitUI.frag["myHome"]);
			this._home.find("#dialogProxy").hide();
		},
		
		_load_content:function(dialog, url) {
			var content = dialog.find(".dialogContent");
			content.width($(dialog).width());
			content.height($(dialog).height() - 5);
			
			var frame = dialog.find('iframe');
//			frame.width($(dialog).width() - 14);
//			frame.height($(dialog).height() - 5);
			frame.attr('src', url);
			
			content.css({overflow:'auto'});
		},
		
		_init:function(home, flag) {
			this._home = $(home);
			
			// 初始化阴影、改变大小边框等
			//this._load_background();
			
			// 添加一列
			if (!flag) {
				this._add_column();
			}
		},
		
		open:function(url, id, title, home, count, options){
			var dialog;
			if (!this._home) this._init(home);
			
			if (count < 0) {
				alert("所要插入的列数不正确");
				return false;
			}
			
			
			if (count - this._column_count > 0) {
				var tColumn = null;
				var length = count-this._column_count;
				for (var i=0; i<length; i++) {
					tColumn = this._add_column();
				}
				dialog = this._add_dialog(tColumn, title, options);
			}else {
				dialog = this._add_dialog(this._columns[count - 1], title, options);
			}
			
			dialog.data("url", url);
			dialog.data("id", id);
			dialog.data("title", title);
			dialog.attr("id", id);
			
			this._load_content(dialog, url);
			
			dialog.bind('mouseover', function() {
				$.myHome.attachShadow(this);
			});
			
			dialog.bind('mouseout', function() {
				$.myHome.disattachShadow(this);
			});
			
			dialog.find("a.close").bind('click', function() {
				dialog.unbind('mouseover').unbind('mouseout');
				$.myHome.disattachShadow(dialog);
				$.myHome.close(dialog);
			});
			
			dialog.find("a.maximize").click(function(e) {
				$.myHome.maxsize(dialog);
			});
			
			dialog.find("a.restore").click(function(e) {
				$.myHome.restore(dialog);
			});
		},
		
		init:function(data, home, option) {
			// 初始化3列
//			if (!this._home) this._init(home);
//			while (this._column_count < 3) {
//				this._add_column();
//			}
//			
//			var columns = new Array();
//			for (var i=0; i<3; i++) {
//				columns[i] = new Array();
//				for (var j=0; j<9; j++) {
//					columns[i][j] = null;
//				}
//			}
			
			var countColumns = option.columns.length;
			var columns = this._make_columns(countColumns, option, home);
			var unPosition = [];
			
			var c = $.cookie("myHome");
			var positionInfo = null;
			
			if (c) {
				//positionInfo = $.evalJSON(c);
//				alert(c);
			}
			
			for (var i=0; i<data.length; i++) {
				var id = data[i].id;
				
				// 如果有位置信息则添加
				if (positionInfo && positionInfo[id]) {
					var x = positionInfo[id].x;
					var y = positionInfo[id].y;
					
					columns[x][y] = data[i];
				}else {
					unPosition.push(data[i]);
				}
			}
			
			// 处理位置记录中没有的数据
			for (var i=0; i<unPosition.length; i++) {
				var op = this._findNewPosition(columns, data.length);
				if (!op) continue;
				columns[op.x][op.y] = unPosition[i];
			}
			
			for (var i=0; i<countColumns; i++) {
				for (var j=0; j<3; j++) {
					var d = columns[i][j];
					
					if (d && d != '_blank') {
						this.open(d.url, d.id, d.title, home, i+1, d.options);
					}
				}
			}
			
			// this._makeSortable();
		},
		
		_findNewPosition:function(columns, count) {
			for (var i=0; i<columns.length; i++) {
				for (var j=0; j<3; j++) {
					
					if (columns[i][j]) continue;
					
					return {x:i, y:j};
					
				}
			}
		},
		
		attachShadow:function(dialog) {
			var top = $(dialog).position().top;
			var left = $(dialog).position().left;
			
			var shadow =this._home.find("div.shadow");
			if(shadow.is(":hidden")) shadow.show();
				
//			alert(top + ", " + left);
			
			shadow.css({
				top: parseInt(top + 10),
				left: parseInt(left - 5),
				height: parseInt($(dialog).height()) - 5,
				width: parseInt($(dialog).width()) + 8,
				zIndex:parseInt($(dialog).css("zIndex")) - 100
			});
			$(".shadow_c", shadow).children().andSelf().each(function(){
				$(this).css("height", $(dialog).outerHeight() - 4);
			});
			
			shadow.hide(); //debug
		},
		
		disattachShadow:function(dialog) {
			var shadow =this._home.find("div.shadow");
			shadow.hide();
		},
		
		close:function(dialog) {
//			var column = $(dialog).parent("div.cloumn");
			
			alertMsg.confirm("是否要删除" + dialog.data("title") + "窗口？", {okName:"是", cancelName:"否", 
				okCall:function() {
					$(dialog).remove();
					$.myHome.saveDialogPosition();
				},
				cancelCall:function() {
					return false;
				}});
			
			
//			// 列中已经没有窗口，删除列
//			if (!column.find(">.dialog:last-child").html()) {
//				
//				
//				this._columns.slice(columen.data("count"), 1);
//				this._column_count = this._columns.length;
//				
//				alert(this._column_count);
//				
//				$(column).remove();
//			}
		},
		
		maxsize:function(dialog) {
			dialog.unbind('mouseover').unbind('mouseout');
			$.myHome.disattachShadow(dialog);
			
			dialog.find("a.maxisize").hide();
			dialog.find("a.restore").show();
			
			dialog.data("original",{
				width:$(dialog).width(),
				height:$(dialog).height()
			});
			
			var winH = $(this._home).height() ;
			var winW = $(this._home).width() ;
			
			$(dialog).css({
				position:'absolute',
				top:'0px',
				left:'0px'
			}).width(winW).height(winH);
			
			$( ".column" ).sortable('disable');
			this._resizeContent(dialog, winW, winH);
		},
		
		restore:function(dialog) {
			dialog.bind('mouseover', function() {
				$.myHome.attachShadow(this);
			});
			
			dialog.bind('mouseout', function() {
				$.myHome.disattachShadow(this);
			});
			
			var original = dialog.data("original");
			
			dialog.find("a.maxisize").show();
			dialog.find("a.restore").hide();
			
			$(dialog).css({
				position:'static',
				margin:'15px 5px 5px 5px'
			}).width(original.width).height(original.height);
			
			$( ".column" ).sortable('enable');
			this._resizeContent(dialog, original.width, original.height);
			
			this.saveDialogPosition();
		},
		
		_resizeDialog:function(dialog, width, lineW) {
			width = parseInt(lineW * parseInt(width) / 100);
			
			dialog.width(width);
		},
		
		saveDialogPosition:function(){
			var info = this._toArray();
			
//			alert($.toJSON(info));
			
			$.cookie('myHome', $.toJSON(info));
		},
		
		_makeSortable: function() {
			$( ".column" ).sortable({
				connectWith: ".column",
				forcePlaceholderSize: true,
//	            revert: 50,
	            delay: 10,
	            opacity: 0.8,
	            containment: '.columns',
	            handle:'.dialogHeader',
	            scroll:true,
	            stop:function(e, ui) {
	            	var el = ui.item;
	            	el.css("position", "static");
	            	$.myHome.attachShadow(el);
	            	$.myHome.saveDialogPosition();
	            	
	            	$.myHome._home.find('iframe').show();
	            },
	            start:function(e, ui){
	            	var dialog = ui.item;
	            	$(".ui-sortable-placeholder").each(function(){
	            		$(this).css("width", dialog.width());
	            		$(this).css("height", dialog.height());
	            	});
	            	$.myHome.disattachShadow(dialog);
	            	$.myHome._home.data('from', $(ui.item).parent('.column'));
	            },
	            sort:function(e, ui) {
	            	var el = ui.item;
	            	$.myHome.attachShadow(el);
	            	
	            	$.myHome._home.find('iframe').hide();
	            	
	            },
	            over :function(e, ui) {
	            	var el = ui.item;
	            	$.myHome.disattachShadow(el);
	            },
	            receive: function(e, ui){
	    			var el = $(ui.item);
	    			
	    			if (!el) return false;
	    			
	    			var to = el.parent('.column');
	    			var from = $.myHome._home.data('from');
	    			
	    			if(parseInt(to.data('width')) * to.find('.dialog').length > 100) {
	    				var elReplace = to.find('.dialog[id!="'+ el.attr('id') +'"]:first');
	    				elReplace.css({width:from.data('width')});
	    				elReplace.find('.dialogContent').css({width:'100%'});
	    				elReplace.find('iframe').css({width:'100%'});
	    			} 
	    			el.css({width:to.data('width')});
	    			el.find('.dialogContent').css({width:'100%'});
	    			el.find('iframe').css({width:'100%'});
//	    			this._resizeDialog(el, to.data('width'), to.width());
	    			
	    			
//	    			this._resizeContent(el, el.width(), original.height());
//	    			this._resizeContent(elReplace, elReplace.width(), elReplace.height());
	    			
	    			from.append(elReplace);
	    		}
			});
			
//			$(".portlet").resizable();
			
			$( ".column" ).disableSelection(); 
		},
		
		_toArray:function() {
			var info = {};
			
			for (var i=0; i<this._columns.length; i++) {
				var dialogs = $(this._columns[i]).find('.dialog');
				
				for (var j=0; j<dialogs.length; j++) {
					var el = $(dialogs[j]);
					
					info[el.data('id')] = {x:i, y:j};
				}
			}
			
			return info;
		},
		
		_resizeContent:function(dialog,width,height) {
			var content = $(".dialogContent", dialog);
			content.css({width:(width) + "px",height:height - $(".dialogHeader", dialog).outerHeight() - $(".dialogFooter", dialog).outerHeight() - 6});
			content.find("[layoutH]").layoutH(content);
			$(".pageContent", dialog).css("width", (width-14) + "px");
			dialog.find('.dialogContent').css({width:'100%',height:height - $(".dialogHeader", dialog).outerHeight() - $(".dialogFooter", dialog).outerHeight() - 6});
			dialog.find('iframe').css({width:'100%',height:'100%'});
			
//			$(window).trigger("resizeGrid");
		},
		
		_resizeIframe:function(dialog,width,height) {
			$('.dialog').each(function(i, el){
				el = $(el);
				
				$.myHome._resizeContent(el, el.width(), el.height()+7);
			}) ;
		}
	};
})(jQuery);