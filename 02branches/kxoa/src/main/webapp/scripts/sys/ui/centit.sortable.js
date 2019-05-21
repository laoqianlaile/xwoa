
/**
 * 拖拽、排序
 */
$(function() {
	Sortable = function (element, selector, options, widget) {
		
		this.element = element;
		
		this.notHasSelectorFlag = false;
		if (!selector) {
			selector = element;
			this.notHasSelectorFlag = true;
		}
		this.selector = selector;
		
		var op = $.extend({}, this.DEFAULT_OPTION, options);
		this.options = op;
		
		// 插件，用作不同的排序方式
		if (widget && widget.name && this.widgets[widget.name]) {
			$.extend(true, this, this.widgets[widget.name]);
			
			if (widget.params) {
				$.extend(this, widget.params);
			}
		}
		
		this.init(op);
	}
	
	Sortable.prototype.widgets = {};
	
	Sortable.prototype.DEFAULT_OPTION = {
			mask: true,			// 拖拽时打开遮盖层
			zIndex: 1000,		// 拖拽时的Z-INDEX
			timeout: 20,		// 拖拽检测间隔时间(ms)
			placeHolderClass: 'widget-holder',	// 占位元素class
			widgetRatio: 0.51	// 矩形面积重合比率临界值
	}
	
	/**
	 * 初始化，添加事件
	 */
	Sortable.prototype.init = function(op) {
		var sortable = this;
		$(document).on('mousedown', sortable.selector, function(event) {
			var element, 
				startX = event.screenX, 
				startY = event.screenY;
			
			// 获取移动元素
			if (sortable.notHasSelectorFlag) {
				element = $(this);
			}
			else {
				element = $(this).parents(sortable.element);
			}
			
			// 保存元素拖拽前基础信息
			var left, top, zIndex;
			
			left = element.position().left;
			top = element.position().top;
			zIndex = element.css('z-index');
			
			element.data('left', left)
				.data('top', top)
				.data('zIndex', zIndex);
			
			// 所有可移动的元素
			var allElements = $(sortable.element);
			
			// 待交换的元素
			var remainElements = allElements.not(element);
				
			// 绘制widget上遮盖层
			if (op.mask && element.mask) {
				allElements.mask();
			}
			
			// 绘制占位元素
			placeHolder = sortable.createPlaceHolder(element);
			
			// 自定义事件
			if (sortable.event.beforeDrag) {
				sortable.event.beforeDrag.call(sortable, element, remainElements);
			}

			// 开始拖拽
			element.css('z-index', sortable.options.zIndex);
			
			// 记录正在移动的widget和移动事件句柄
			sortable.movingWidget = null;
			var moveHandler;
			
			$(document).on('mousemove', function(event) {
				var x = event.screenX, y = event.screenY;
				
				// 移动元素
				element.css({
					left: left + x - startX < 0 ? 0 : left + x - startX,
					top: top + y - startY < 0 ? 0 : top + y - startY
				});
				
				// 处理碰撞、交换位置等计算。在移动过程中不予响应，当鼠标停止一段时间后开始计算，节省资源
				clearTimeout(moveHandler);
				moveHandler = setTimeout(function() {
					sortable.event.processMoving.call(sortable, element, remainElements);
				}, op.timeout);
				
			}).on('mouseup', function(event) {
				// 停止事件
				$(this).off('mousemove').off('mouseup');
				clearTimeout(moveHandler);
				
				// 拖拽完毕后自定义事件
				if (sortable.event.afterMouseUp) {
					sortable.event.afterMouseUp.call(sortable, element, remainElements);
				}
				
				// 恢复元素
				element.animate({
					top: placeHolder.position().top,
					left: placeHolder.position().left
				}, function() {
					element.css('z-index', zIndex);
					// 清除widget上遮盖层
					if (op.mask && element.removeMask) {
						allElements.removeMask();
					}
					
					placeHolder.remove();
					placeHolder = null;
				});
			
			});
		})
	}
	
	/**
	 * 绘制占位元素
	 */
	Sortable.prototype.createPlaceHolder = function(widget) {
		var left = widget.position().left, top = widget.position().top;
		var width = widget.width(), height = widget.height();
		
		var placeHolder = $('<div></div>').insertBefore(widget).addClass(this.options.placeHolderClass).css({
			width: width,
			height: height,
			top: top,
			left: left
		});
		
		placeHolder.data('startX', left).data('startY', top);
		widget.data('placeHolder', placeHolder);
		
		if (this.event.afterCreatePlaceHolder) {
			this.event.afterCreatePlaceHolder.call(this, placeHolder, widget);
		}
		
		return placeHolder;
	}
	
	/**
	 * 获取两个矩形元素重合的面积
	 * 
	 * widget1 widget2 元素1 元素2
	 * ratio 比率 提供一个临界值，当面积比小于该值时返回0 大于该值时返回重合的面积
	 */
	Sortable.prototype.getOverWidgetSize = function(widget1, widget2, ratio) {
		var position1 = widget1.position(), position2 = widget2.position();
		var x1 = position1.left, x2 = position2.left;
		var y1 = position1.top, y2 = position2.top;
		var width1 = widget1.width(), width2 = widget2.width();
		var height1 = widget1.height(), height2 = widget2.height();
		
		var endx = Math.max(x1+width1,x2+width2);  
		var startx = Math.min(x1,x2);  
		var width = width1+width2-(endx-startx);  
		  
		var endy = Math.max(y1+height1,y2+height2);  
		var starty = Math.min(y1,y2);  
		var height = height1+height2-(endy-starty);
		
		
		if (width<=0||height<=0) {
	    	return 0;
		} 
		else {
			var size = width * height;
			
			if (size > (height1 * width1) * ratio || size > (height2 * width2) * ratio) return size;
			return 0;
		}  
			
	};
	
	/**
	 * 获取重合面积最大的元素
	 * 
	 * source 原元素
	 * widgets 带比较的元素集合
	 */
	Sortable.prototype.getMaxOverWidget = function (source, widgets) {
		var size = 0, widget = null;
		var sortable = this;
		
		widgets.each(function() {
			var temp = sortable.getOverWidgetSize(source, $(this), sortable.options.widgetRatio);
			
			if ( temp > size) {
				size = temp;
				widget = $(this);
			}
		});
		
		return widget;
	}
	
	/**
	 * 交换元素
	 * 
	 * source 元素1
	 * widget 元素2
	 * 
	 */
	Sortable.prototype.replaceWidgets = function (source, widget) {
		
		var top = widget.position().top, left = widget.position().left;
		var holder = source.data('placeHolder');
		
		widget.css({
			top: holder.position().top,
			left: holder.position().left
		});
		holder.css({
			top: top,
			left: left
		});
		
		return widget;
	}
	
	
	Sortable.prototype.event = {
		/**
		 * 处理移动中的元素，检查和其他元素的重合、交换
		 * 
		 * @param {Object} element 
		 * @param {Object} remainElements
		 */
		processMoving : function(element, remainElements) {
			var widget = this.getMaxOverWidget(element, remainElements);
				
			if (widget) {
				
				// 将要移动的元素和正在移动的元素是同一个，则不作处理
				if (this.movingWidget && this.movingWidget.attr('id') == widget.attr('id')) {
					return;
				}
			
				this.movingWidget = this.replaceWidgets(element, widget);
				
				if (this.event.afterReplace) {
					this.event.afterReplace.call(this, element, widget, remainElements);
				}
			}
			else {
				this.movingWidget = null;
			}
		
		}
		
		
	};
	
	window.Sortable = Sortable;
});

	
	
	