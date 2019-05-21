$(function() {
	if (!Highcharts) {
		alert('没有引用hightcharts库');
		return false;
	}
	
	$.myChart = {
		_renderID: 'chart',
		
		_colors: Highcharts.getOptions().colors,
		
		_charts: {},
		
		_table: null,
		
		_title: null,
		
		_td: null,
		
		_type: null,
		
		showColumnChart: function(td, type, width, height) {
			var option = this._getColumnStat(td);
			var chart = this._createColumnChart(this._title, option.yTitle, option.data, option.categories, type, width, height);
			
			this._td = td;
			this._type = 'column';
		},
		
		showLineChart: function(td, type, width, height) {
			var option = this._getLineStat(td);
			var chart = this._createColumnChart(this._title, option.yTitle, option.data, option.categories, type, width, height);
			
			this._td = td;
			this._type = 'line';
		},
		
		init: function(tab, title, op) {
			tab = $(tab);
			this._table = tab;
			this._title = title;
			
			this._drawHome(op);
			// 如果没有传入op默认添加自动事件
			if (!op || op.auto) {
				
				this._addEvent(tab);
			}
			
			// 指定对某行或某列统计
			if (op && op.stat) {
				$.extend(op.stat, {width:op.width, height:op.height});
				this._stat4SpecialColumn(tab, op.stat);
			}
			
//			this._initSearchArea();
		},
		
		// 单独为某行或某列添加事件
		_stat4SpecialColumn:function(tab, options) {
			for (var i=0; i<options.length; i++) {
				var op = options[i];
				
				var td = this._getTD(tab, op.td, op.type);
				if (!td) continue;
				
				td.css({cursor:'hand'});
				
				if (op.type == 'column') {
					td.click(function() {
						$.myChart.showColumnChart(this, op.chartType, options.width, options.height);
					});
					
					if (op.show) $.myChart.showColumnChart(td, op.chartType, options.width, options.height);
				}else if (op.type == 'line') {
					td.click(function() {
						$.myChart.showLineChart(this, op.chartType, options.width, options.height);
					});
					
					if (op.show) $.myChart.showLineChart(td, op.chartType, options.width, options.height);
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
				
				if (td.attr('stat') == 'T') {
					td.css({cursor:'hand'});
					
					td.click(function() {
						$.myChart.showColumnChart(this);
					});
				};
				
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
				this._table.parent().append('<input type="button" style="float:right; margin:2px;" class="btn" value="切换至饼状图" chart="column" id="chartType"/><br><div id="'+this._renderID+'"></div>');
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
		
		_createColumnChart: function(title, yTitle, data, categories, chartType, width, height){
			var type = chartType ? chartType : ($('#chartType').attr('chart') ? $('#chartType').attr('chart') : 'column');
			
			data = this._addExtraData(data, categories);
			
			if (!height) height=250;
			
			
			if (this._table && !height) {
				height = this._table.height();
			}
			console.dir(data);
			return new Highcharts.Chart({
				 chart: {
                     renderTo: this._renderID,
                     defaultSeriesType: type,
                     height:height,
                     width:width
                 },
                 title: {
                     text: title ? title : ''
                 },
                 xAxis: {
                     categories: categories ? categories : [],
                     labels: {
                	 	staggerLines: 2
                 	 }
                 },
                 yAxis: {
                     title: {
                         text: yTitle ? yTitle : ''
                     }
                 },
                 series: data ? [{name:yTitle, data:data}] : []
			});
		},
		
		_addExtraData:function(data, categories) {
			var op = [];
			
			for (var i=0; i<data.length; i++) {
				var obj = {};
				obj.color = this._colors[i%9];
				obj.y = data[i];
				obj.name = categories[i];
				
				op.push(obj);
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
			
			var trs = withHead ? tab.find('tr:visible') : tab.find('tbody tr:visible');
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
			return tab.find('tr').eq(count-1).find('td:visible');
		},
		
		_getColumnStat: function(td) {
			td = $(td);
			
			var index = td.index();
			var tab = td.closest('table');
			
			var option = {};
			var column = this._findColumn($(tab), index+1, false);
			var first = this._findFirstColumn($(tab), false);
			
			option.data = [];
			option.categories = [];
			
			$(column).each(function(i, td) {
				td = $(td);
				var text = td.text().trim();
				if (!text) {
					text = td.find('a').text().trim();
				}
				option.data.push(text ? parseInt(text) : 0);
			});
			
			
			$(first).each(function(i, td) {
				td = $(td);
				option.categories.push(td.text());
			});
			
			option.yTitle = td.text();
			
			
//			alert($.toJSON(option.data));
//			alert($.toJSON(option.categories));
			return option;
		},
		
		_getLineStat: function(td) {
			td = $(td);
			var tab = td.closest('table');
			
			var option = {};
			var line = td.parent('tr').find('td:visible');
			var first = this._findLine(tab, 1, false);
			
			option.data = [];
			option.categories = [];
			
			
			$(first).each(function(i, td) {
				td = $(td);
				
				if ('T' == td.attr('stat')) {
					option.categories.push(td.text().trim());
					var text = $(line[i]).text().trim();
					if (!text) {
						text = $(line[i]).find('a').text().trim();
					}
					option.data.push(text ? parseInt(text) : 0);
				}
			});
			
			option.yTitle = $(line[0]).text().trim();
			
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