/**
 * 解决桌面框架下，打开窗口URL前进、后退问题
 * 
 * 在使用window.history进行back和forward时，主页面和iframe共享同一个history，这就造成了如果只想后退iframe中
 * 的链接，结果却造成主页面的返回。
 * 
 * 为了解决这个问题，下面的方法会单独保存每一个iframe加载时的href。（在iframe中onload时调用），实现iframe前进、后退功能
 */
$(function() {
	if ($.history) {
		return;
	}
	
	function _getIframe(iframe) {
		if (typeof iframe == "string") {
			iframe = document.getElementById(iframe);
		}
	
		iframe = $(iframe);
		
		// 非IFRAME即为IFRAME里一个元素
		if (!iframe.is('iframe')) {
			iframe = iframe.closest('iframe');
			
			// 不是IFRAME或者不是IFRAME里的一个元素，退出
			if (!iframe.length) return;
		}
		
		return iframe;
	}
	
	function _getIframeInfo(iframe) {
		iframe = _getIframe(iframe);
		
		if (!iframe) return;
		
		var id = iframe.attr('id');

		var info = $.history._IFRAME_MAP[id];
		
		if (!info) {
			info = {
				hrefList: [], 
				ignoreThisTime: false,
				iframe: iframe[0]
			};
			
			iframe[0].contentWindow._iframe = iframe;

			$.history._IFRAME_MAP[id] = info;
		}
		
		return info;
	}
	
	/**
	 * 根据是否能后退，改变窗口上后退按钮样式
	 */
	function _changeButton(iframe) {
		var flag = $.history.canBack(iframe);
		
		iframe = $(iframe);
		
		var backBtn = iframe.data('backBtn');
		if (!backBtn) {
			backBtn = iframe.closest('div.window-container').find('div.control-handle a.goback');
			iframe.data('backBtn', backBtn);
		}
		
		if (flag) {
			backBtn.removeClass('disabled');
		}
		else {
			backBtn.addClass('disabled');
		}
	}

	$.history = {
			_IFRAME_MAP : {},
			
			/**
			 * 保存URL
			 */
			save: function(iframe) {
				// 从MAP中获取IFRAME信息，如果没有则自动创建
				var iframeInfo = _getIframeInfo(iframe);
				
				if (!iframeInfo) return;
				
				// 后退、前进本身所触发的IFRAME重新加载，不会被记录
				if (iframeInfo.ignoreThisTime) {
					iframeInfo.ignoreThisTime = false;
					
					// 改变按钮样式是否可以点击后退
					_changeButton(iframeInfo.iframe);
					return;
				}
				
				var href = iframeInfo.iframe.contentWindow.location.href;
				var list = iframeInfo.hrefList;
				
				if (0 == list.length) {
					list.push(href);
				}
				else {
					// 如果新加载的href与最后一个href相同，则不记录
					if (href != list[list.length - 1]) {
						list.push(href);
					}
				}
				
				_changeButton(iframeInfo.iframe);
			},
			
			/**
			 * 后退
			 */
			back: function(iframe) {
			
				// 从MAP中获取IFRAME信息，如果没有则自动创建
				var iframeInfo = _getIframeInfo(iframe);
				
				if (!iframeInfo) return;
				
				// 小于2个location时禁止返回
				var hrefs = iframeInfo.hrefList;
				
				if (hrefs.length < 2) return;
				
				hrefs.pop();
				var href = hrefs[hrefs.length - 1];
				
				iframeInfo.ignoreThisTime = true;
				iframeInfo.iframe.contentWindow.location.replace(href);
				
			},
			
			clear: function(iframe) {
				// 从MAP中获取IFRAME信息，如果没有则自动创建
				var iframeInfo = _getIframeInfo(iframe);
				
				if (!iframeInfo) return;
				
				// 清空
				iframeInfo.hrefList.length = 0;
				
			},
			
			/**
			 * 能否后退
			 */
			canBack: function(iframe) {
				// 从MAP中获取IFRAME信息，如果没有则自动创建
				var iframeInfo = _getIframeInfo(iframe);
				
				if (!iframeInfo) return false;
				
				return iframeInfo.hrefList.length > 1;
			}
	};
	
});

