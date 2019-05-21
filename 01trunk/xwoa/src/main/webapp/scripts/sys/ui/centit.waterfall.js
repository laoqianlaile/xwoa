/**
 * 瀑布流插件
 */
$(function() {
	/**
	 * container: 容器
	 * blocks: 待排序的模块
	 * splitNum: 把容器分割成多少列
	 * margin: 模块间的间隔（单位：长度）
	 */
	function WaterFall(container, blocks, splitNum, margin) {
		this.container = $(container);
		
		// 每一列的宽度根据公式： 宽度 = （容器的宽度  - （列数 + 1） * 间隔）/ 列数
		this.width = (this.container.width() - (splitNum + 1) * margin) / splitNum;
		
		this.splitNum = splitNum;
		
		this.margin = margin;
		
		// 现在只支持宽度一样的模块
		blocks.width(this.width);
		
		// 从cookie中读取位置信息
		if ($.cookie) {
			var info = $.cookie("widget-waterfall");
			
			if (info) {
				try {
					info = $.parseJSON(info);
				}
				catch(msg) {
					$.cookie("widget-waterfall", null);
				}
				
				for (var id in info) {
					var waterFall = info[id];
					
					$('#'+id).data('waterFall', waterFall);
				}
			}
		}		

		this.sort(blocks);
	}
	
	/**
	 * 获取每一列的信息（仅供页面拖拽使用）
	 * 
	 * 判断被拖拽的模块是否已进入新列的范围
	 */
	WaterFall.prototype.getColumns = function() {
		// 这里的height并不是真实的高度，实际上在判断模块是否已经被拖拽至新列时，这个高度应该假设成无限大，现在暂时用一个较大值代替
		var height = 2048, width = this.width;
		
		var columns = [], top=0, left = 0;
		
		for (var i=0; i<this.splitNum; i++) {
			// 每一列的起点坐标（left, 0）
			left = i*width + (i+1)*this.margin; 
			
			columns.push({
				position: (function() {
					return {
						left:this.left, 
						top:top
					};
				}),
				width: function() {
					return width;
				},
				height: function() {
					return height;
				},
				col: i+1,
				line: this.blockArray[i].length,	// 每一列包含的模块数
				left: left
			});
		}
		
		return columns;
	}
	
	/**
	 * 创建数组便于排序
	 */
	WaterFall.prototype.makeBlockArray = function(blocks) {
		var array = [], block, data;
		
		// 根据分割的列数创建数组
		for (var i=0; i<this.splitNum; i++) {
			array.push([]);
		}
		
		// 根据模块原有的 waterFall属性（包含模块所在行、列的信息），将模块放入数组
		for (var i=0; i<blocks.length; i++) {
			block = blocks.eq(i);
			data = block.data('waterFall');
			
			if (data) {
				array[data.col-1][data.line-1] = block;
			}
			
		}
		
		// 清理数组，因为原有waterFall属性可能会有脏数据，造成生成数组某一列可能会出现这种情况
		// [block1, null, block2]
		// 清理后的数组将会把空模块去除，变成新的数组：[block1, block2]
		for (var i=0; i<this.splitNum; i++) {
			this.clearArray(array[i]);
		}
		
		// 根据新清理数组，重新设置模块waterFall属性
		for (var i=0; i<this.splitNum; i++) {
			for (var j=0; j<array[i].length; j++) {
				block = array[i][j];
				data = block.data('waterFall');
				data.line = j+1;
				data.col = i+1;
			}
		}
		
		// 保存
		this.blockArray = array;
		
		return array;
	}
	
	/**
	 * 去除数组中空元素
	 */
	WaterFall.prototype.clearArray = function(arr) {
		for(var i=0; i<arr.length; i++){
			if(!arr[i]) {
				arr.splice(i,1); 
				i--;
			}
		}
	}
	
	/**
	 * 排序
	 * 
	 * 其实不是排序，顺序已经定义好了，只是按照顺序移动位置
	 */
	WaterFall.prototype.sort = function (blocks) {
		var array = this.makeBlockArray(blocks), column, block, left, top;
		
		for (var i=0; i<this.splitNum; i++) {
			column = array[i];
			
			left = i * this.width + (i + 1) * this.margin;
			top = this.margin;
			
			for (var j=0; j<column.length; j++)	{
				block = column[j];
				
				block.css({
					top: top,
					left: left
				}); 
				
				// 累加高度
				top += block.height() + this.margin;
			}	

		}
	}
	
	/**
	 * 刷新
	 */
	WaterFall.prototype.refresh = function (blocks) {
	
		this.width = (this.container.width() - (splitNum + 1) * margin) / splitNum;
		
		blocks.width(width);
		
		this.sort(blocks);
	}
	
	window.WaterFall = WaterFall;
});

