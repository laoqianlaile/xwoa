
/**
 * 拖拽、排序
 */
$(function() {
	if (!Sortable) {
		return;
	}
	Sortable.prototype.widgets["WATER-FALL"] = {
		/**
		 * 元素交换到其他列
		 * @param {Object} source
		 * @param {Object} column
		 */
		replaceColumns : function(source, column) {
			var holder = source.data('placeHolder');
			var waterFall = holder.data('waterFall');
		
			waterFall.col = column.col;
			waterFall.line = column.line+1;
		},
		
		/**
		 * 获取元素与哪一个列重叠面积最大
		 * 
		 * @param {Object} element
		 * @param {Object} columns
		 */
		getMaxOverColumn : function(element, columns) {
			var size = 0, widget = null;
			
			for (var i=0; i<columns.length; i++) {
				var temp = this.getOverWidgetSize(element, columns[i], this.options.widgetRatio);
				if ( temp > size) {
					size = temp;
					widget = columns[i];
				}
			}
			
			return widget;
		},
		
		event : {
			/**
			 * 开始拖拽前保留信息
			 * 
			 * @param {Object} element
			 * @param {Object} remainElements
			 */
			beforeDrag : function (element, remainElements) {
				// 保存容器分割列的信息
				this.columns = this.water.getColumns();
			},
			
			/**
			 * 创建占位元素后保存信息
			 * 
			 * @param {Object} placeHolder
			 * @param {Object} element
			 */
			afterCreatePlaceHolder : function (placeHolder, element) {
				placeHolder.data('waterFall', element.data('waterFall'));
			},
			
			/**
			 * 移动拖拽主过程
			 * 
			 * @param {Object} element
			 * @param {Object} remainElements
			 */
			processMoving : function (element, remainElements) {
				var widget = this.getMaxOverWidget(element, remainElements), column;
				
				if (widget) {
					
					// 将要移动的元素和正在移动的元素是同一个，则不作处理
					if (this.movingWidget && this.movingWidget.attr('id') == widget.attr('id')) {
						return;
					}
				
					this.movingWidget = this.replaceWidgets(element, widget);
				}
				else {
					column = this.getMaxOverColumn(element, this.columns);
					
					// 将要移动到列和元素所在的列为同一列，则不作处理
					if (column && column.col != element.data('waterFall').col) {
						this.replaceColumns(element, column);
						
						if (this.event.afterReplace) {
							this.event.afterReplace.call(this, element, widget, remainElements);
						}
						
						return;
					}
					
					this.movingWidget = null;
					return;
				}
				
				if (this.event.afterReplace) {
					this.event.afterReplace.call(this, element, widget, remainElements);
				}
			},
			
			/**
			 * 交换完元素调用事件
			 * 
			 * @param {Object} element
			 * @param {Object} widget
			 * @param {Object} remainElements
			 */
			afterReplace : function(element, widget, remainElements) {
				var holder = element.data('placeHolder');
				
				if (widget) {
					// 交换waterFall信息
					var waterFall = widget.data('waterFall');
					
					widget.data('waterFall', holder.data('waterFall'));
					holder.data('waterFall', waterFall);
				}
				
				// 重新排列位置
				this.water.sort(remainElements.add(holder));
			},
			
			/**
			 * 释放鼠标后调用事件
			 * 
			 * @param {Object} element
			 * @param {Object} remainElements
			 */
			afterMouseUp : function(element, remainElements) {
				element.data('waterFall', element.data('placeHolder').data('waterFall'));
				
				// 位置顺序保存至cookie中
				if ($.cookie) {
					var info = {}, id, waterFall;
					
					remainElements.add(element).each(function() {
						id = $(this).attr('id');
						waterFall = $(this).data('waterFall');
						
						info[id] = waterFall;
					});
					
					$.cookie("widget-waterfall", $.toJSON(info));
				}
			}
		}
	} 
});

	
	
	