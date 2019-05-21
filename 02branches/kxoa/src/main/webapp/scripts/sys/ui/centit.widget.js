/**
 * 桌面悬挂插件
 */
$(function() {
	if (!DWZ.Widget) {
		
		function Widget(id, title, url, options) {
			this.id = id;
			this.title = title;
			this.url = url;
			
			var op = $.extend(true, {}, this.DEFAULT_OPTIONS, options);
			var widget = $(DWZ.frag["WIDGET"]).attr('id', id);
			
			// 标题
			if (title) {
				widget.find('div.move span').html(title);
			}
			
			// 链接地址
			widget.find('iframe').attr('src', url);
			
			// 是否有边框
			if (!op.border) {
				widget.addClass('noborder');
			}
			
			if (op.css) {
				widget.css(op.css);
			}
			
			if (op.waterFall) {
				widget.data('waterFall', op.waterFall);
			}
			
			this.widget = widget;
		}
		
		Widget.prototype.create = function(id, title, url, options) {
			
			return this.widget;
		};
		
		Widget.prototype.DEFAULT_OPTIONS = {
				border: true
		};
		
		Widget.prototype.event = {
				afterClose: function() {}
		};
		
		// 关闭插件
		Widget.prototype.close = function() {};
		
		DWZ.Widget = Widget;
	}
	
	$.fn.extend({
		shock: function(time) {
			var $this = $(this);
			
			function state1(){
				 $this.removeClass("r2"); 
				 $this.addClass("r1");
				 $this.data("time1", setTimeout(state2, 50));
			}
			
			function state2(){	   
			    $this.removeClass("r1");
			    $this.addClass("r2"); 
				$this.data("time2", setTimeout(state1, 50));
			}
			
			function begin() {
				state1();
				
				if (time) {
					setTimeout(function() {
						$this.removeShock();
					}, time);
				}
			}
			
			begin();
			
			return $this;
		},
		
		removeShock: function() {
			var $this = $(this);
			
			clearTimeout($this.data('time1'));
			clearTimeout($this.data('time2'));
			$this.removeClass("r2"); 
			$this.removeClass("r1");
			
			return $this;
		},
		
		mask: function() {
			var $this = $(this);
			
			$this.find('div.background').show();
			
			return $this;
		},
		
		removeMask: function() {
			var $this = $(this);
			
			$this.find('div.background').hide();
			
			return $this;
		}
	});
});

