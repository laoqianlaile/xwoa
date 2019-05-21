$(function() {
	if (!Highcharts) {
		alert('没有引用hightcharts库');
		return false;
	}
	
	$.myChart = {
		_renderID: 'chart',
		
		_colors: Highcharts.getOptions().colors,
		
		_colors2: ['#4572A7', '#AA4643', '#89A54E', '#80699B', '#3D96AE', 
		   '#DB843D', '#92A8CD', '#A47D7C', '#B5CA92'],
		
		_charts: {},
		
		_table: null,
		
		_title: null,
		
		_td: null,
		
		_type: null,
		
		_height: null,
		
		_width: null,
		
		highcharts_option: null,
		
		
		
		setThemes : function() {
			Highcharts.theme = {
				colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4']
			};
			
			// Apply the theme
			var highchartsOptions = Highcharts.setOptions(Highcharts.theme);
		},
		
		showColumnChart: function(td, type, width, height, head,color,num) {
			var option, temp, charts = this;
			td = $(td);
			td.each(function(index) {
				temp = charts._getColumnStat($(this), head,num);
				
				// 第一次全数据
				if (!option) {
					option = temp;
				}
				// 第二次将原data数据压缩成一个数组塞入
				else if (index == 1) {
					option = {
							categories: option.categories,
							datas: [
							{
								data: option.data,
								link: option.link,
								yTitle: option.yTitle
							}
							]
					};

					option.datas.push({
						data: temp.data,
						link: temp.link,
						yTitle: temp.yTitle
					});
				}
				// 以后只累加data和link数据
				else {
					option.datas.push({
						data: temp.data,
						link: temp.link,
						yTitle: temp.yTitle
					});
				}
			});			

			//console.log(option);
			var chart = this._createColumnChart(this._title, option, type,color, width, height);
			
			this._td = td;
			this._type = 'column';
		},
		
		showLineChart: function(td, type, width, height,color,num) {
			//console.log(td);
			td = $(td);
			var option, temp, charts = this;
			
			td.each(function(index) {
				temp = charts._getLineStat($(this),num);
				
				// 第一次全数据
				if (!option) {
					option = temp;
				}
				// 第二次将原data数据压缩成一个数组塞入
				else if (index == 1) {
					option = {
							categories: option.categories,
							datas: [
							{
								data: option.data,
								link: option.link,
								yTitle: option.yTitle
							}
							]
					};
				

					option.datas.push({
						data: temp.data,
						link: temp.link,
						yTitle: temp.yTitle
					});
				}
				// 以后只累加data和link数据
				else {
					option.datas.push({
						data: temp.data,
						link: temp.link,
						yTitle: temp.yTitle
					});
				}
			});

			//console.log(option);
			var chart = this._createColumnChart(this._title, option, type,color, width, height);
			
			this._td = td;
			this._type = 'line';
		},
		
		init: function(tab, title, op) {
			this.setThemes();
		
			tab = $(tab);
			this._table = tab;
			this._title = title;
			
			this._drawHome(op);
			
			// 没有数据
//			if (0 == tab.find('tbody tr').length) {
//				$('#'+ this._renderID).html('没有数据');
//				return false;
//			}
			
			// 如果没有传入op默认添加自动事件
			if (!op || op.auto) {
				this._addEvent(tab);
			}
			
			// 指定对某行或某列统计
			if (op && op.stat) {
				$.extend(op.stat, {width:op.width, height:op.height});
				this._width = op.width;
				this._height = op.height;
				
//				if (!this._width) {
//					this._width = $('#' + this._renderID).width();
//				}

				this.highcharts_option = op.highcharts;
				this._stat4SpecialColumn(tab, op.stat);
			}
			
//			this._initSearchArea();
		},
		
		// 单独为某行或某列添加事件
		_stat4SpecialColumn:function(tab, options) {
			// 数组时，显示单行或者单列数据
			if ($.isArray(options)) {
				for (var i=0; i<options.length; i++) {
					var op = options[i];
					
					var td = this._getTD(tab, op.td, op.type);
					if (!td) continue;
					
					td.css({cursor:'hand'});
					
					if (op.type == 'column') {
						td.click(function() {
							$.myChart.showColumnChart(this, op.chartType, options.width, options.height, op.head,op.bgColor,op.num);
						});
						
						if (op.show) $.myChart.showColumnChart(td, op.chartType, options.width, options.height, op.head,op.bgColor,op.num);
					}else if (op.type == 'line') {
						td.click(function() {
							$.myChart.showLineChart(this, op.chartType, options.width, options.height,op.bgColor,op.num);
						});
						
						if (op.show) $.myChart.showLineChart(td, op.chartType, options.width, options.height,op.bgColor,op.num);
					}
					
				}
			}
			// 对象时，显示多行或多列的集合数据
			else {
				if (options.type == 'column') {
					// 查询表头的集合，例如从第二列到第五列，options.td值为：th:gt(1):lt(5)
					$.myChart.showColumnChart(tab.find('thead').find(options.td), options.chartType, options.width, options.height,options.head,options.bgColor,options.num);
				}else if (options.type == 'line') {
					// 查询行头的集合，例如从第二行到第五行，options.td值为：tr:gt(1):lt(5) td:visible:nth-child(1)
					$.myChart.showLineChart(tab.find('tbody').find(options.td), options.chartType, options.width, options.height,options.bgColor,options.num);
				}
			}
		},
		
		// 根据内容和类别（行或列）来获取TD
		_getTD:function(tab, text, type) {
			var td = null;
			if (type == 'column') {
				tab.find('tr').eq(0).find('th').each(function(i, t){
					if (text == $.trim($(t).text())) {
						td = $(t);
						return false;
					}
				});
			} else if (type == 'line') {
				var tds = this._findFirstColumn(tab, false);
				for (var i=0; i<tds.length; i++) {
					if (text == $.trim($(tds[i]).text())) {
						td = tds[i];
						break;
					}
				}
			}
			
			return td;
		},
		
		_addEvent: function(tab) {
			// 给表头添加列事件
			tab.find('tr').eq(0).find('th:visible').each(function(i, td) {
				td = $(td);
				
				if (i==0) return true;

//				if (td.attr('stat') == 'T') {
					td.css({cursor:'hand'});
					
					td.click(function() {
						$.myChart.showColumnChart(this);
					});
//				};
				
			});
			
			// 给表头添加行事件
			var column = this._findFirstColumn(tab, false);
			for(var i=0; i<column.length; i++) {
				var td = column[i];
				td.css({cursor:'hand'});
				
				td.click(function() {
					$.myChart.showLineChart(this);
				});
			};
		},
		
		_drawHome: function (op) {
			if (op) {
				this._renderID = op.renderTo ? op.renderTo : this._renderID;
			}
			
			// 不用画按钮
			if (op && op.button === false) {
				
				if (!document.getElementById(this._renderID)) {
					this._table.parent().append('<input type="hidden" chart="column" id="chartType" /><div id="'+this._renderID+'"></div>');
				}
				
				return;
			}
			
			// 需要画按钮
			if (!document.getElementById(this._renderID)) {
				if (op.stat && op.stat[0] && op.stat[0].chartType === 'pie') {
					$('div.container').append('<div class="row tool-bar"><div class="col-xs-12"><input type="button" style="float:right; margin:2px;" class="btn btn-info btn-xs" value="切换至柱状图" chart="pie" id="chartType"/></div></div><div class="row" id="chart"></div>');
//					this._table.parent().append('<br><div id="'+this._renderID+'"></div>');
				}
				else {
					$('div.container').append('<div class="row tool-bar"><div class="col-xs-12"><input type="button" style="float:right; margin:2px;" class="btn btn-info btn-xs" value="切换至饼状图" chart="column" id="chartType"/></div></div><div class="row" id="chart"></div>');
//					this._table.parent().append('<input type="button" style="float:right; margin:2px;" class="btn btn-info btn-xs" value="" chart="column" id="chartType"/><br><div id="'+this._renderID+'"></div>');
				}
				
				// IE下统计图表宽度总是超长
//				if ($.browser.msie && $('#chart')[0]) {
//					$('#chart').width($('div.container').width() * 0.96);
//				}
			}
			
			$('#chartType').click(function() {
				var btn = $(this);
				
				if (btn.attr('chart') == 'column') {
					btn.attr('chart', 'pie');
					btn.attr('value', '切换至柱状图');
				}else {
					btn.attr('chart', 'column');
					btn.attr('value', '切换至饼状图');
				}
				
				if ($.myChart._td) {
					if ($.myChart._type=='column') {
						$.myChart.showColumnChart($.myChart._td);
					}else if ($.myChart._type == 'line') {
						$.myChart.showLineChart($.myChart._td);
					}
					
				}
			});
		},
		
		_createColumnChart: function(title, option, chartType,color){
			var type = chartType ? chartType : ($('#chartType').attr('chart') ? $('#chartType').attr('chart') : 'column');
			var bgColor = color? color: "white";
			var yTitle = option.yTitle, data, categories = option.categories;
			var width = this._width;
			var height = this._height;
			var showflag;
			// 单行数据
			if (!option.datas) {
				data = this._addExtraData(categories, option);
				showflag= this._showTag(option);
			}
			// 多行数据
			else {
				data = this._addExtraData(categories, option.datas);
				showflag=this._showTag(option.datas);
			}
			
			
			
			//console.log(data);
			
			if (!height) height=250;
			if (this._table && !height) {
				height = this._table.height();
			}
			
			var options;
			// 单行
			if (!option.datas) { 
				
				// 判断是否有数据
				var sum = 0;
				
				for (var i=0; i<data.length; i++) {
					sum += parseFloat(data[i].y);
				}
				
//				if ((type == 'pie' || type == 'column') && !sum) {
//					var div = $('<div></div>').text('没有数据').css({
//						'text-align': 'center',
//						'line-height': '25px'
//					});
//					
//					$('#'+this._renderID).append(div);
//
//					return false;
//				}
				options = {
					 chart: {
		                renderTo: this._renderID,
		                backgroundColor : bgColor,
		                defaultSeriesType: type
		            },
		            colors: ['#4572A7', '#AA4643', '#89A54E', '#80699B', '#3D96AE', 
		            '#DB843D', '#92A8CD', '#A47D7C', '#B5CA92'],
		            legend: {
		                enabled: false
		            },
		            title: {
		                text: title ? title : ''
		            },
		            xAxis: {
		                categories: categories ? categories : [],
		                labels: {
//		            		rotation : -45
		            	}
		            },
		            yAxis: {
		                title: {
		                    text: ''
		                },
		                floor: 0,
		                min:0
		            },
		            plotOptions: {
		            	line: {
		            		dataLabels: {
		            			enabled : true
		            		}
		            	},
		            	
		            	pie: {
		            		dataLabels: {
		            			enabled : true
		            		}
		            	}
		            },
		            credits : {
		            	enabled: false
		            },
		            series: data ? [{name:yTitle, data:data}] : []
				}
            }
			// 多行
			else {
				options = {
					 chart: {
		                renderTo: this._renderID,
		                defaultSeriesType: type
		            },
		            colors: ['#4572A7', '#AA4643', '#89A54E', '#80699B', '#3D96AE', 
		            '#DB843D', '#92A8CD', '#A47D7C', '#B5CA92'],
		            legend: {
		                enabled: true
		            },
		            title: {
		                text: title ? title : ''
		            },
		            xAxis: {
		                categories: categories ? categories : []
		            },
		            yAxis: {
		                title: {
		                    text: ''
		                },
		                floor: 0,
		                min: 0
		            },
		            series: data ? data : []
				}
			}	
			
			if (width) {
				options.chart.width = width;
			}
			if (height) {
				options.chart.height = height;
			}
			
			if (this.highcharts_option) {
				options = $.extend(true, options, this.highcharts_option);
			}
//			console.log(options);
			
//			if(showflag){
				return new Highcharts.Chart(options);	
//			}else{
//				return;
//			}
			
		},
		//值全部为空时不画图
		_showTag:function(datas){
			var data;
			var flage=false;
			
			// 单行数据
			if (!$.isArray(datas)) {
				data = datas.data;
				for (var i=0; i<data.length; i++) {
				
					if(data[i]!=0){
						flage=true;
						return flage; 
					}
					 
				}
			}
			// 多行数据
			else {
				for (var i=0; i<datas.length; i++) {
					data = datas[i];
					if(data.data!=0){
						flage=true;
						return flage; 
					}
					           
				}
			}
			
			window.SHOW_TALBE = !flage;
			return false;   
		},
		
		_addExtraData:function(categories, datas) {
			var op = [];
			var data, links, title;
			
			// 单行数据
			if (!$.isArray(datas)) {
				data = datas.data;
				links = datas.link;
				tilte = datas.yTitle;
			
				for (var i=0; i<data.length; i++) {
					var obj = {};
					obj.color = this._colors2[i%10];
					obj.y = data[i];
					obj.name = categories[i];
					obj.link = links[i];
					op.push(obj);
				}
			}
			// 多行数据
			else {
				for (var i=0; i<datas.length; i++) {
					var obj = {};
					data = datas[i];
					
					obj.name = data.yTitle;
					obj.data = data.data;
					obj.link = data.link;
					op.push(obj);                
				}
			}
			
			return op;
		},
		
		/**
		 * 获取表格第一列信息
		 * 
		 * @param tab 表格
		 * @param withHead 是否包含表头
		 * @returns
		 */
		_findFirstColumn: function(tab, withHead) {
			tab = $(tab);
			
			return this._findColumn(tab, 1, withHead);
		},
		
		/**
		 * 获取表格某列信息
		 * 
		 * @param tab 表格
		 * @param count 第几列
		 * @param withHead 是否包含表头
		 * @returns {Array}
		 */
		_findColumn: function(tab, count, withHead) {
			tab = $(tab);
			var trs;
			
			// 表格可见
			if (tab.is(':visible')) {
				trs = withHead ? tab.find('tr:visible') : tab.find('tbody tr:visible');
			}
			// 表格不可见
			else {
				trs = withHead ? tab.find('tr') : tab.find('tbody tr');
			}
			
			var column = [];
			
			trs.each(function(i, tr) {
				tr = $(tr);
				column.push(tr.find('td').eq(count-1));
			});
			
			return column;
		},
		
		/**
		 * 获取表格某行信息
		 * 
		 * @param tab 表格
		 * @param count 第几行
		 * @param withStat 是否只包含统计行
		 * @returns
		 */
		_findLine: function(tab, count, withStat) {
			tab = $(tab);
			if (count == 1) {
				return tab.find('tr').eq(count-1).find('th:visible');
			}
			
			// 表格可见
			if (tab.is(':visible')) {
				if (count == 1) {
					return tab.find('tr').eq(count-1).find('th');
				}
				else {
					return tab.find('tr').eq(count-1).find('td');
				}
			}
			// 表格不可见
			else {
				if (count == 1) {
					return tab.find('tr').eq(count-1).find('th:visible');
				}
				else {
					return tab.find('tr').eq(count-1).find('td:visible');
				}
			}
			
		},
		
		_getColumnStat: function(td, head,num) {
			td = $(td);
			
			var index = td.index();
			var tab = td.closest('table');
			
			var option = {};
			var column = this._findColumn($(tab), index+1, false);
			var first;
			
			if (head) {
				first = this._findColumn($(tab), head, false)
			}
			else {
				first = this._findFirstColumn($(tab), false); 
			}
			
			option.data = [];
			option.categories = [];
			option.link = [];
			
			$(first).each(function(i, td) {
				td = $(td);
				// 合计项不做统计
				if (!td.text() || td.text() === '合计') {
					return true;
				}
				if(num){
					if(i<num){//控制显示条数
						option.categories.push(td.text());				
					}					
				}
				else{
					if(i<15){//控制显示条数
						option.categories.push(td.text());
					}
				}
			});

			$(column).each(function(i, td) {
				td = $(td);
				var text = $.trim(td.text());
				if (!text) {
					text = $.trim(td.find('a').text());
				}
				
				if (td.find('a').length) {
					option.link.push(td.find('a').attr('href'));
				}
				else {
					option.link.push(null);
				}
				
				// 数据不能大于表头是数目
				if (i >= option.categories.length) {
					return false;
				}
				
				option.data.push(text ? parseInt(text) : 0);
			});
			
			
			
			
			option.yTitle = td.text();
			
			
//			alert($.toJSON(option.data));
//			alert($.toJSON(option.categories));
			return option;
		},
		
		_getLineStat: function(td,num) {
			td = $(td);
			var tab = td.closest('table');
			
			var option = {};
			var line = td.parent('tr').find('td:visible');
			var first = this._findLine(tab, 1, false);
			
			option.data = [];
			option.categories = [];
			option.link = [];
			
			$(first).each(function(i, td) {
				td = $(td);
				
				if (i == 0) return true;

//				if ('T' == td.attr('stat')) {
				if(num){
					if(i<num){//控制显示条数
						option.categories.push($.trim(td.text()));					
					}					
				}
				else{
					if(i<15){//控制显示条数
						option.categories.push($.trim(td.text()));					
					}
				}
					var text = $.trim($(line[i]).text());
					if (!text) {
						text = $.trim($(line[i]).find('a').text());
					}
					
					if ($(line[i]).find('a').length) {
						option.link.push($(line[i]).find('a').attr('href'));
					}
					else {
						option.link.push(null);
					}
					
					option.data.push(text ? parseInt(text) : 0);
//				}
			});
			
			option.yTitle = $.trim($(line[0]).text());
			
//			alert($.toJSON(option.data));
//			alert($.toJSON(option.categories));
			
			return option;
		},
		
		_initSearchArea:function() {
			var nowYear = new Date();
			nowYear = nowYear.getFullYear();
			
			$('select').css({
				width : '150px'
			});
			$('input[class!="btn"]').css({
				width : '150px'
			});
			
			
			CentitUI.init(
					"../page/frame/centitui.frag.xml",
					{
						callback : function() {
							if ($.fn.datepicker) {
								$('input.date').each(function() {
									var $this = $(this);
									var opts = {};
									if ($this.attr("format"))
										opts.pattern = $this.attr("format");
									if ($this.attr("yearstart"))
										opts.yearstart = $this.attr("yearstart");
									if ($this.attr("yearend"))
										opts.yearend = $this.attr("yearend");
									$this.datepicker(opts);
								});
							}
						}
					});
			
			
			$(document).click(function(event){
				event.stopPropagation();
				var el = $(event.target);
				
				if ('INPUT' == event.target.tagName || el.parents('div#unitbox')[0]) {
					return;
				}
				
				$('div#unitbox').hide();
			});
			
		},
		
		locateMe: function(parent, child) {
			parent = $(parent);
			child = $(child);

			var offset = parent.position();
			var iTop = offset.top + parent.outerHeight() + 2;

			child.css({
				top : iTop + 'px',
				left : offset.left + 'px',
				position : 'absolute'
			});
		},

		showUnitSearch:function(el, flag) {
			el = $(el);
			var unit = $('#unitbox');

			if (!flag) {
				unit.hide();
				return;
			}

			this.locateMe(el, unit);
			unit.data('source', el);
			unit.show();
		},

		choseUnit:function (name, id) {
			var unit = $('#unitbox');
			var source = unit.data('source');
			source.val(name);
			source.prevAll('input:first').val(id);
			unit.hide();
		},
		
		closeUnit:function(event) {
			event.stopPropagation();
			var unit = $('#unitbox');
			unit.hide();
		}
	};
});