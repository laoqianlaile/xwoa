$(function() {
	if (!Highcharts) {
		alert('没有引用hightcharts库');
		return false;
	}
	
	$.myChart = {
		_renderID: 'chart',	
		
		_table: null,
		
		_data: null,
		
		_title: null,
		
		_table_rowCategories: null,
		
		_chartOptions: {
			chart: {
				width:1000,
				height:350
			}
		},
		
		_defaultType: 'column',
		
		_option: {
			auto:true, 
			autoRow:true, 
			autoColumn:false,
			debug:false,
			alwaysShow:false,
			button: {
				type: [{
					type:'column',
					text:'柱状图'
				},{
					type:'pie',
					text:'饼状图'
				}, {
					type:'line',
					text:'折线图'
				}]
			}	
		},
		
		_debug: false,
		
		_colors: Highcharts.getOptions().colors,
		
		// EC TREE 列首选择器 （第一行）
		_ECTREE_FIRSTROW: 'tbody>tr>td.tableHeader',
		
		// EC TREE 行首选择器（第一列）
		_ECTREE_FIRSTCOL: 'tbody>tr>td>table>tbody>tr>td[align="left"]',
		
		// TABLE 列首选择器 （第一行）
		_TABLE_FIRSTROW: 'thead>tr>td',
		
		// TABLE 行首选择器（第一列）
		_TABLE_FIRSTCOL: 'tbody>tr',
		
		
		/**
		 * 初始化：解析数据，给表格给单元格添加事件
		 * 
		 * @param tableId
		 * @param data
		 * @param op
		 * @param chartOp
		 */
		init: function(tableId, data, op, chartOp) {
			this._table = tableId ? $(tableId):null;
			
			this._title = data.formName;
			
			$.extend(this._option, op);
			$.extend(this._chartOptions, chartOp);
			
			this._debug = this._debug || this._option.debug;
			
			if (this._option.renderTo) {
				this._renderID = this._option.renderTo;
			}
			if (this._option.defaultType) {
				this._defaultType = this._option.defaultType;
				this._option.button = false;
			}
			
			// 解析统计数据
			var statDatas = this.parseData(data);
			
			// 可以不依赖于表格 
			if (this._table) {
				// 解析表格
				var cells = this.parseTable();
				
				if (this._option.rowCategories && 'table'==this._option.rowCategories) {
					statDatas.rowCategories = this._table_rowCategories;
				}
				
				// 为表格添加事件
				this.attachEvent(statDatas, cells, this._option);
			}
			
			// 绘制背景
			this.drawBackground();
			
			// 显示默认的图表
			var stat = this._parseStatOption(this._option);
			this.showDefaultChart(statDatas, stat.show);
			
			this._initSearchArea();
		},
		
		/**
		 * 展示默认图表
		 * @param datas
		 * @param stat
		 */
		showDefaultChart: function(datas, stat) {
			if (!stat) return;
			
			var type = stat.type;
			var text = stat.cell;
			var data = {};
			
			if ('row' == type) {
				data = {
					categories: datas.rowCategories,
					series:this._extraData(datas.rowData[text], datas.rowCategories),
					name:text
				};
			}else if ('column' == type) {
				data = {
					categories: datas.colCategories,
					series:this._extraData(datas.colData[text], datas.colCategories),
					name:text
				};
			}
			
			this._data = data;
			$.myChart._drawChart($.myChart._data, $.myChart._defaultType, $.myChart._chartOptions);
		},
		
		/**
		 * 给表格添加事件
		 * 
		 * @param datas
		 * @param cells
		 * @param op
		 */
		attachEvent: function(datas, cells, op) {
			var stat = this._parseStatOption(op);
			
			if (op.auto) {
				
				if (op.autoRow) {
					this._attachEvent2Cells(cells.firstColumn, datas.rowCategories, datas.rowData, false);
				}else if (stat.row && stat.row.length>0){
					this._attachEvent2CellsByStatOption(cells.firstColumnObj, datas.rowCategories, datas.rowData, stat.row, false);
				}
				
				if (op.autoColumn) {
					this._attachEvent2Cells(cells.firstRow, datas.colCategories, datas.colData, true);
				}else if (stat.column && stat.column.length>0) {
					this._attachEvent2CellsByStatOption(cells.firstRowObj, datas.colCategories, datas.colData, stat.column, true);
				}
				
			}else {
				if (stat.row && stat.row.length>0){
					this._attachEvent2CellsByStatOption(cells.firstColumnObj, datas.rowCategories, datas.rowData, stat.row, false);
				}
				
				if (stat.column && stat.column.length>0) {
					this._attachEvent2CellsByStatOption(cells.firstRowObj, datas.colCategories, datas.colData, stat.column, true);
				}
			}
			
			return stat;
		},
		
		drawBackground: function () {
			var div = document.getElementById(this._renderID);
			
			// 若没有图表容器，则自动生成图表容器
			if (!div) {
				if (this._table) {
					this._table.parent().before('<div id="'+this._renderID+'"></div>');
				}else {
					$('body').append('<div id="'+this._renderID+'"></div>');
				}
			}
			
			var div = $(document.getElementById(this._renderID));
			
			if (!this._option.alwaysShow) {
				div.wrap('<div id="chartParent" style="text-align:center; margin-left:auto; margin-right:auto;"></div>');
			}
			
			this._drawButton(div);
		},
		
		_drawButton: function (div) {
			var op = this._option.button;
			if (!op) return;
			
			var type = op.type || [{type:'column', text:'柱状图'}];
			
			// 不需要画按钮
			if (type.length <= 1) return;
			
			div.parent().append('<input type="button" class="btn" id="chartButton" style="display:none" />');
			var btn = $('#chartButton');
			
			// 添加样式
			if (op.css) {
				btn.css(op.css);
			}
			
			this._defaultType = type[0].type;
			btn.attr('value', '切换至'+type[1].text);
			
			// 给按钮添加事件
			btn.bind('click', function() {
				for (var i=0; i<type.length; i++) {
					if ($.myChart._defaultType == type[i].type) break;
				}
				
				$.myChart._defaultType = type[(i+1)%3].type;
				btn.attr('value', '切换至'+type[(i+2)%3].text);
				
				$.myChart._drawChart($.myChart._data, $.myChart._defaultType, $.myChart._chartOptions);
			});
		},
		
		/**
		 * 画图表
		 * 
		 * @param datas
		 * @param type
		 * @param option
		 */
		_drawChart: function(datas, type, option) {
			
			// 显示按钮
			$('#chartButton').show();
			
			var op = {
				 chart: {
	                    renderTo: this._renderID,
	                    defaultSeriesType: type
	                },
	                title: {
	                    text: this._title
	                },
	                xAxis: {
	                    categories: datas.categories
	                },
	                yAxis: {
	                    title: {
	                        text: datas.name
	                    }
	                },
	                series: [{name: datas.name, data:datas.series}]
			};
			if (option) {
				for (var name in option) {
					if (!op[name]) op[name] = {};
					$.extend(op[name], option[name]);
				}
			}
			
			if (this._debug) {
				alert('数据\n'+$.toJSON(datas));
				alert('数据2\n'+$.toJSON(op));
			}
			
			return new Highcharts.Chart(op);
		},
		
		/**
		 * 为表格每个单元格添加数据
		 * @param cells
		 * @param categories
		 * @param datas
		 * @param statOptions
		 * @param isColumn
		 */
		_attachEvent2CellsByStatOption: function(cells, categories, datas, statOptions, isColumn) {
			for (var i=0; i<statOptions.length; i++) {
				var op = statOptions[i];
				this._attachEvent2Cell(cells[op], categories, datas[op], isColumn);
			}
		},
		
		/**
		 * 为表格每个单元格添加数据
		 * @param cells
		 * @param categories
		 * @param data
		 * @param isColumn
		 */
		_attachEvent2Cells: function(cells, categories, datas, isColumn) {
			var columns = this._table.data('columns');
			var firstShowIndex = this._table.data('firstShowIndex');
			
			for (var i=0; i<cells.length; i++) {
				
				if (!isColumn) {
					this._attachEvent2Cell(cells[i], categories, datas[cells[i].text], isColumn);
				}else if (columns[i+firstShowIndex].isShow == 'T' && columns[i+firstShowIndex].drawChart == 'T') {
					this._attachEvent2Cell(cells[i], categories, datas[cells[i].text], isColumn);
				}
			}
		},
		
		_attachEvent2Cell: function (cell, categories, data, isColumn) {
			// TOFIX 行统计：合计、平均
			if (!data || !cell) return;
			
			var td = cell['cell'];
			
			var title = (isColumn===true) ? '统计“'+cell.text+'”列数据' : '统计“'+cell.text+'”行数据';
			
			// 保存数据
			td.data('chartDatas', {
				categories:categories,
				series:this._extraData(data, categories),
				name:cell.text
			});
			
			td.attr('title', title);
			td.css({
				cursor:'hand'
			});
			
			td.bind('click', function(event) {
				$.myChart._data = $(event.target).data('chartDatas');
				$.myChart._drawChart($.myChart._data, $.myChart._defaultType, $.myChart._chartOptions);
			});
		},
		
		/**
		 * 解析用户选项里stat属性
		 * @param op
		 */
		_parseStatOption: function(op) {
			var row = [];
			var column = [];
			var data = {};
			
			var stat = op.stat;
			if (!stat) return {row:row, column:column};
			
			for(var i=0; i<stat.length; i++) {
				var s = stat[i];
				
				if ('row' == s.type) {
					row.push(s.cell);
				}else if ('column' == s.type) {
					column.push(s.cell);
				}
				
				if (s.show || !data.show) data.show = s;
			}
			
			data.column = column;
			data.row = row; 
			
			if (this._debug) {
				alert('统计Option信息\n'+$.toJSON(data));
			}
			
			return data;
		},
		
		/**
		 * 扩展数据，添加颜色和名称供图表使用
		 * 
		 * @param series
		 * @param categories
		 */
		_extraData: function(series, categories) {
			var data = [];
			
			for (var i=0; i<series.length; i++) {
				data.push({
					color:this._colors[i%9],
					y:series[i],
					name:categories[i]
				});
			}
			
			return data;
		},
		
		/**
		 * 解析表格
		 */
		parseTable: function() {
			var isTree = this._table.find('table:first').length>0 ? true:false;
			
			var columns = this._table.data('columns');
			var firstShowIndex = this._table.data('firstShowIndex');
			
			var firstColumn = this._getFirstColumn(isTree);
			var firstRow = this._getFirstRow(isTree);
			var firstRowObj = {};
			var firstColumnObj = {};
			var rowCategories = [];
			
			// 第一行并不是所有都需要统计
			for(var i=0; i<firstRow.length; i++) {
				var row = firstRow[i];
				firstRowObj[row.text] = row;
				
				if (columns[i+firstShowIndex].isShow == 'T' && columns[i+firstShowIndex].drawChart == 'T'){
					rowCategories.push(row.text);
				}
			}
			this._table_rowCategories = rowCategories;
			
			for (var i=0; i<firstColumn.length; i++) {
				var column = firstColumn[i];
				firstColumnObj[column.text] = column;
			}
			
			if (this._debug) {
				alert('ECTREE '+ isTree);
				
				alert('行'+firstRow.length+'个元素');
				
				alert('行categories\n'+$.toJSON(rowCategories));
				this._debugCellArray(firstRow, '第一行数组');
				this._debugCellObj(firstRowObj, '第一行对象');
				
				alert('列'+firstColumn.length+'个元素');
				
				this._debugCellArray(firstColumn, '第一列数组');
				this._debugCellObj(firstColumnObj, '第一列对象');
				
			}
			
			// 需求：将表格上统计多少条的数据去掉
			this._table.find('td.statusBar').hide();
			var width = this._table.width();
			if (width<this._chartOptions.chart.width) 
				this._chartOptions.chart.width = width;
			
			return {firstColumn:firstColumn,
				firstRow:firstRow,
				firstRowObj:firstRowObj,
				firstColumnObj:firstColumnObj
			};
		},
		
		/**
		 * 获取表格首列
		 * @param isTree
		 */
		_getFirstColumn: function(isTree) {
			var cells = [];
			
			if (isTree) {
				this._table.find(this._ECTREE_FIRSTCOL).each(function(index, el) {
					el = $(el);
					cells.push({
						cell: el,
						text: $.trim(el.text())
					});
				});
			}else {
				this._table.find(this._TABLE_FIRSTCOL).each(function(index, el) {
					el = $(el).find('td:first');
					cells.push({
						cell: el,
						text: $.trim(el.text())
					});
				});
			}
			
			return cells;
		},
		
		/**
		 * 获取表格首行
		 * @param isTree
		 */
		_getFirstRow: function(isTree) {
			var cells = [];
			
			if (isTree) {
				this._table.find(this._ECTREE_FIRSTROW).each(function(index, el) {
					el = $(el);
					cells.push({
						cell: el,
						text: $.trim(el.text())
					});
				});
			}else {
				this._table.find(this._TABLE_FIRSTROW).each(function(index, el) {
					el = $(el);
					
					if (el.is(':hidden')) {
						return true;
					}
					
					cells.push({
						cell: el,
						text: $.trim(el.text())
					});
				});
			}
			
			return cells;
		},
		
		formatStr: function(str) {
			str = str.replaceAll('#', '');
			return $.trim(str);
		},
		
		/**
		 * 解析数据
		 * 
		 * @param data
		 */
		parseData: function(data) {
			// 数据列头信息
			var columns = data.columns;
			
			// 表格填充信息
			var formData = data.fromData;
			
			// 存储需要统计数据
			var firstShowIndex = -1;
			var colCategories = [];
			var rowCategories = [];
			var colData = {};
			var rowData = {};
			
			// 获取需要统计的表头信息
			for (var i=0; i<columns.length; i++) {
				var column = columns[i];
				
				if ('T' == column.isShow) {
					
					if ('T' == column.drawChart) {
						rowCategories.push(column.colName);
					}else if(firstShowIndex < 0) {// 第一个显示并且不统计的为行首
						firstShowIndex = i;
					};
				};
			}
			if (firstShowIndex<0) firstShowIndex=0;
			
			if (!formData) formData = [];
			// 获取表格内容信息 列数据
			for (var i=0; i<formData.length; i++) {
				var datas = [];
				for (var j=0; j<columns.length; j++) {
					var column = columns[j];
					if ('T' == column.isShow && 'T' == column.drawChart) {
						datas.push(parseFloat(formData[i][j]));
					};
				}
				
				colCategories.push(this.formatStr(formData[i][firstShowIndex]));
				rowData[this.formatStr(formData[i][firstShowIndex])] = datas;
			}
			
			var average = [];
			var sum = [];
			// 获取表格内容信息 行数据
			for (var i=0; i<columns.length; i++) {
				var datas = [];
				var column = columns[i];
				var flag = ('T' == column.isShow && 'T' == column.drawChart);
				
				// 合计和平均值只需要设置一次 
				if (flag) {
					average.push(parseFloat(column.averageValue));
					sum.push(parseFloat(column.sumValue));
				}
				
				for (var j=0; j<formData.length; j++) {
					
					if (flag) {
						datas.push(parseInt(formData[j][i]));
					} else {
						datas.push($.trim(formData[j][i]));
					};
				}
				
				colData[column.colName] = datas;
			}
			rowData['平均'] = average;
			rowData['合计'] = sum;
			
			if (this._table) {
				this._table.data('columns', columns);
				this._table.data('firstShowIndex', firstShowIndex);
				this._table.data('colCategories', colCategories);
				this._table.data('rowCategories', rowCategories);
				this._table.data('colData', colData);
				this._table.data('rowData', rowData);
			}
			
			if (this._debug) {
				alert('全部数据\n'+$.toJSON(data));
				alert('列\n'+$.toJSON(columns));
				alert('数据源\n'+$.toJSON(formData));
				alert('首列位置：'+firstShowIndex);
				alert('列首\n'+$.toJSON(colCategories));
				alert('列数据\n'+$.toJSON(colData));
				alert('行首\n'+$.toJSON(rowCategories));
				alert('行数据\n'+$.toJSON(rowData));
			}
			
			return {
				firstShowIndex:firstShowIndex,
				colCategories:colCategories,
				rowCategories:rowCategories,
				colData:colData,
				rowData:rowData
			};
		},
		
		_debugCellArray: function (a, text) {
			var cells = [];
			
			for (var i=0; i<a.length; i++) {
				cells.push(a[i].text);
			}
			
			alert(text+'\n'+cells.join(','));
		},
		
		_debugCellObj: function (o, text) {
			var cells = [];
			
			for (var name in o) {
				cells.push(name);
			}
			
			alert(text+'\n'+cells.join(','));
		},
		
		_initSearchArea:function() {
			var nowYear = new Date();
			nowYear = nowYear.getFullYear();
			
			$('select').css({
				width : '150px'
			});
			
			$('input[type="text"]').css({
				width : '150px'
			});
			
			$('input[type="checkbox"]').css({
				'background-color':'#caefff',
				padding:'0px',
				'line-height':'12px',
				border:'0px'
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

			var offset = parent.offset();
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

		choseUnit:function (tree, id) {
			var unit = $('#unitbox');
			var source = unit.data('source');
			source.val(tree.currentNode.text);
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