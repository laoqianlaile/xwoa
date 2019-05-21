(function($) {
	function setSize(iframe) {
		var body = iframe.contents().find('body');
		iframe.width(body.width());
		iframe.height(body.height());
		
		console.log(body, body.width(), body.height());
		
		return body;
	}

	/**
	 * 自适应高度、宽度
	 */
	function autoHeight(iframe) {
//		iframe.off('load');
//		iframe.off('resize');
		var body = setSize(iframe);
		
		body.on('resize', function() {
			setSize(iframe);
		});
		
		// 继续深入内容中的iframe，给每一层iframe都加上自动改变高度、宽度事件
		body.find('iframe').on('load', function() {
			autoHeight($(this));
		});
	}
	
	function init(iframe) {
		iframe.on('load', function() {
			var contents = $(this).contents();
			//contents.find('html').niceScroll({cursorcolor:"#95B8E7"});
			console.log('loaded');

			contents.find('body iframe').each(function() {
				console.log('iframe', $(this));
				$(this).on('load', function() {
					console.log('sub iframe loaded');
					autoHeight($(this));
				});
			});
		})
	}

	$.fn.autoHeight = function() {
		return $(this).each(function() {
			var iframe = $(this);
			var state = iframe.data('autoHeight');
				
			if (!state) {
				init(iframe);
				iframe.data('autoHeight', true);
			}
		});
	}
})(jQuery);