$(function() {
	if (window.navHead) {
		return;
	}
	
	NavHead = function(nav) {
		this.nav = $(nav);
		nav = this.nav;
		
		// 导航栏宽度
		this.navWidth = nav.width();
		
		// 记录更新提示的数组，保存li的index
		this.newFlags = [];
		
		// 显示菜单起始位置
		this.startPos = 0;
		
		// 显示菜单结束位置
		this.endPos = 0;
		
		// 上一个按钮
		this.prevLi = nav.find('li.prev');
		
		// 下一个按钮
		this.nextLi = nav.find('li.next');
		
		// 菜单
		this.menu = nav.find('li').not(function() {
			return $(this).hasClass('prev') || $(this).hasClass('next');
		});
		
		this.init();
		
		this.attachEvent();
	}
	
	/**
	 * 初始化
	 */
	NavHead.prototype.init = function() {
		var width = 0, li, overflowFlag = false, navObj = this, navWidth = this.navWidth;
		
		// 计算宽度
		this.menu.each(function(index) {
			li = $(this);
			width += li.outerWidth();
			
			if (width > navWidth) {
				overflowFlag = true;
				
				// 恢复宽度和位置至上一个正常值
				width -= li.outerWidth();
				navObj.endPos = index - 1;
				return false;
			}
		});
		
		// 超长
		if (overflowFlag) {
			this.prevLi.hide();
			
			// 加上控制按钮后宽度不够
			if (width > this.navWidth - this.nextLi.width() - this.prevLi.width()) {
				this.endPos -= 1;
			}
			
			// 隐藏超长的菜单
			for (var i=this.endPos+1; i<this.menu.length; i++) {
				this.menu.eq(i).hide();
			}
		}
		else {
			this.prevLi.hide();
			this.nextLi.hide()
		}
		
		var contentTemp = [];
		// 读取内容
		this.menu.each(function() {
			li = $(this);
			var temp = $('#' + li.attr('ref'));
			if (temp.length) {
				contentTemp.push(temp);
			}
		});
		
		this.contents = $(contentTemp);
		this.contents.each(function() {
			$(this).hide();
		});
		this.chose(1);
	}
	
	/**
	 * 附加事件
	 */
	NavHead.prototype.attachEvent = function() {
		var navObj = this, li, index;
		
		this.prevLi.click(function() {
			navObj.moveNav(-1);
			navObj.glinkFlag();
		});
		
		this.nextLi.click(function() {
			navObj.moveNav(1);
			navObj.glinkFlag();
		});
		
		this.menu.click(function() {
			li = $(this); 
			index = li.index();
			
			li.removeClass('new');
			for (var i=0; i<navObj.newFlags.length; i++) {
				if (navObj.newFlags[i] == index) {
					navObj.newFlags.splice(i, 1);
					break;
				}
			}
			navObj.glinkFlag();
			
			navObj.chose($(this));
		});
		
		$('div.news div.title a').live('click', function(event) {
			var $this = $(this);
			var title = $this.attr("relTitle") || $this.text();
			var tabid = $this.attr("rel") || "_blank";
			var fresh = eval($this.attr("fresh") || "true");
			var external = eval($this.attr("external") || "false");
			var url = $this.attr("href");
			
			parent.navTab.openTab(tabid, url, {
				title : title,
				fresh : fresh,
				external : external
			});
			
			// 去掉new样式
			$this.closest('div.news').removeClass('new');
			
			event.preventDefault();
		});
	}
	
	/**
	 * 移动菜单
	 * @param {Object} offset
	 */
	NavHead.prototype.moveNav = function(offset) {
		var length = this.menu.length;
		
		// 超出范围不予响应
		if (this.startPos + offset < 0 || this.endPos + offset > length) {
			return;
		}
		
		this.startPos += offset;
		this.endPos += offset;
		
		this.menu.hide();
		this.prevLi.show();
 		this.nextLi.show();
		
		// 显示新的可展示菜单
		for (var i=this.startPos; i<=this.endPos; i++) {
			this.menu.eq(i).show();
		}
		
		// 判断上一个和以下各按钮是否可以显示
		if (this.startPos == 0) {
			this.prevLi.hide();
		}
		
		if (this.endPos == length - 1) {
			this.nextLi.hide();
		}
	}
	
	/**
	 * 选择
	 * 
	 * @param {Object} param
	 */
	NavHead.prototype.chose = function(param) {
		var li, navObj = this;
		
		// 数字代表LI的位置
		if ($.isNumeric(param)) {
			param = parseInt(param);
			li = this.menu.eq(param-1);
		}
		// 字符串代表LI的REF引用
		else if (typeof param == "string"){
			li = this.nav.find('li[ref='+param+']');
		}
		else {
			li = $(param);
		}
		
		this.menu.removeClass('selected');
		li.addClass('selected');
		
		var content = $('#' + li.attr('ref'));
		// 没有找到对应的DIV
		if (!content.length) {
			return;
		}
		
		// 和当前显示的DIV相同
		if (this.currentContent && this.currentContent.attr('id') == content.attr('id')) {
			return;
		}
		
		this.currentIndex = li.index();
		
		if (this.currentContent) {
			this.currentContent.hide();
			content.show();
		}
		else {
			content.show();
		}
		
		this.currentContent = content;
	}
	
	/**
	 * 添加更新标记
	 * @param {Object} param
	 */
	NavHead.prototype.addNewFlag = function(param) {
		var li;
		
		// 数组 
		if ($.isArray(param)) {
			for (var i=0; i<param.length; i++) {
				this.addNewFlag(param[i]);
			}
			
			return;
		}
		
		// 数字代表LI的位置
		if ($.isNumeric(param)) {
			param = parseInt(param);
			li = this.menu.eq(param-1);
		}
		// 字符串代表LI的REF引用
		else if (typeof param == "string"){
			li = this.nav.find('li[ref='+param+']');
		}
		else {
			li = $(param);
		}
		
		// 添加更新标记
		li.addClass('new');
		if ($.inArray(param, this.newFlags) < 0) {
			this.newFlags.push(li.index());
		}
		
		this.glinkFlag();
	}
	
	NavHead.prototype.glinkFlag = function() {
		// 有更新标记的菜单集合
		var flags = this.newFlags, navObj = this;
		var prevFlag = false;
		
		// 没有更新提示，退出
		if (flags.length == 0) {
			clearInterval(this.prevGlinkFlag);
			clearInterval(this.nextGlinkFlag);
			this.prevGlinkFlag = null;
			this.nextGlinkFlag = null;
			this._clearGlinkFlag(this.prevLi);
			this._clearGlinkFlag(this.nextLi);
			
			return;
		}
		
		// 判断上一个按钮是否需要闪烁提示
		for (var i=0; i<flags.length; i++) {
			var pos = flags[i];
			
			if (pos < this.startPos + 1) {
				prevFlag = true;
				if (!this.prevGlinkFlag) {
					this.prevGlinkFlag = setInterval(function() {
						navObj._createGlinkFlag(navObj.prevLi);
					}, 500);
					break;
				} 
			}
		}
		
		// 清除
		if (!prevFlag) {
			clearInterval(this.prevGlinkFlag);
			this.prevGlinkFlag = null;
			this._clearGlinkFlag(this.prevLi);
		}
		
		// 判断下一个按钮是否需要闪烁提示
		for (var i=0; i<flags.length; i++) {
			var pos = flags[i];
			
			if (pos > this.endPos + 1) {
				if (!this.nextGlinkFlag) {
					this.nextGlinkFlag = setInterval(function() {
						navObj._createGlinkFlag(navObj.nextLi);
					}, 500);
					return;
				} 
			}
		}
		
		clearInterval(this.nextGlinkFlag);
		this.nextGlinkFlag = null;
		this._clearGlinkFlag(this.nextLi);
	}
	
	/**
	 * 取消闪烁
	 * @param {Object} obj
	 */
	NavHead.prototype._clearGlinkFlag = function(obj) {
		obj = $(obj);
		obj.removeClass('state1').removeClass('state2').removeClass('state3');
	}
	
	/**
	 * 开始闪烁
	 * @param {Object} obj
	 */
	NavHead.prototype._createGlinkFlag = function(obj) {
		obj = $(obj);
		
		var state = ["state1", "state2", "state3"];
		
		for (var i=0; i<state.length; i++) {
			var cls = state[i];
			
			// 转换到下一个class
			if (obj.hasClass(cls)) {
				obj.removeClass(cls);
				obj.addClass(state[(i+1) % 3]);
				return;
			}
		}
		
		obj.addClass(state[0]);
	}
	
	window.NavHead = NavHead;
}); 
































