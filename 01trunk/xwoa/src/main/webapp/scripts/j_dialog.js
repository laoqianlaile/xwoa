//var JDialogInterval = null;
var JDialog = {

	// 配置项
	// 模态窗口背景色
	"cBackgroundColor" : "#ffffff",

	// 阴影距离(右)
	"cShadowRight" : 5,
	// 阴影距离(下)
	"cShadowDown" : 5,

	// 边框尺寸(像素)
	"cBorderSize" : 2,
	// 边框颜色
	"cBorderColor" : "#999999",

	// Header背景色
	"cHeaderBackgroundColor" : "#20376C",
	// 右上角关闭显示文本
	"cCloseText" : "关闭",
	// 鼠标移上去时的提示文字
	"cCloseTitle" : "关闭",
	
	"oDocument" : null,
	
	"scrolling": null,
	
	open : function(object) {
		if (!object) {
			alert("Dialog error! No params!");
			return false;
		}
		if (!object.src) {
			alert("Dialog error! No iframe src!");
			return false;
		}
		var dialogTitle = object.title ? object.title : "操作";
		var iframeWidth = object.width ? object.width : 400;
		var iframeHeight = object.height ? object.height : 300;
		var oDocument = object.oDocument ? object.oDocument : document;
		var scrolling = object.scrolling ? object.scrolling : "auto";
		
		JDialog.oDocument = oDocument;
		JDialog.scrolling = scrolling;
		
		JDialog.init(oDocument, dialogTitle, object.src, iframeWidth, iframeHeight);
	},
	
	init : function(oDocument, dialogTitle, iframeSrc, iframeWidth, iframeHeight) {
		var _px_top = 30; // title
		
		if (typeof ($("#d_000_shadow", oDocument)[0]) == "undefined") { // 如果不存在阴影层
			$("body", oDocument).prepend("<div id='d_000_shadow'>&nbsp;</div>"); // 添加body页面的阴影层
			var _d_shadow = $("#d_000_shadow", oDocument);
			var _html_width = oDocument.body.scrollWidth; // 获取页面宽度
			if (_html_width < iframeWidth + JDialog.cShadowRight + 2 * JDialog.cBorderSize) { // 计算页面宽度与iframe宽度，阴影层取其大
				_html_width = iframeWidth + JDialog.cShadowRight + 2 * JDialog.cBorderSize;
			}
			_d_shadow.css("width", _html_width + "px"); // 设置阴影层宽度为整个网页可见区域的宽度
			var _html_height = oDocument.body.scrollHeight; // 获取页面高度
			if (_html_height < iframeHeight + JDialog.cShadowDown + 2 * JDialog.cBorderSize) { // 计算页面高度与iframe高度，阴影层取其大
				_html_height = iframeHeight + JDialog.cShadowDown + 2 * JDialog.cBorderSize;
			}
			_d_shadow.css("height", _html_height + "px"); // 设置阴影层高度为整个body正文的高度
		}
		if (typeof ($("#d_000_dialog")[0], oDocument) != "undefined") { // 如果存在对话框，则移除对话框
			$("#d_000_dialog", oDocument).remove();
		}
		$("body", oDocument).prepend("<div id='d_000_dialog'></div>"); // （重新）添加对话框
		var _d_dialog = $("#d_000_dialog", oDocument); // 获取对话框dialog对象
		var _client_width = oDocument.body.clientWidth;
		var _scroll_left = oDocument.body.scrollLeft;
		var _left = (_client_width - (iframeWidth + JDialog.cBorderSize * 2 + JDialog.cShadowRight)) / 2;
		_d_dialog.css("left", (_left < 0 ? 0 : _left) + _scroll_left + "px"); // 设置弹出层到左边的距离
		var _client_height = oDocument.body.clientHeight;
		var _scroll_top = oDocument.body.scrollTop;
		var _top = (_client_height - (iframeHeight + JDialog.cBorderSize * 2 + _px_top + JDialog.cShadowDown)) / 2;
		_d_dialog.css("top", (_top < 0 ? 0 : _top) + _scroll_top + "px");
		_d_dialog.append("<div id='d_000_dialog_window'>&nbsp;</div>");
		var _d_dialog_window = $("#d_000_dialog_window", oDocument); // 弹出窗口
		_d_dialog_window.css("width", iframeWidth + JDialog.cBorderSize * 2 + "px");
		_d_dialog_window.css("height", iframeHeight + JDialog.cBorderSize * 2 + _px_top + "px");
		_d_dialog_window.css("left", JDialog.cShadowRight + "px");
		_d_dialog_window.css("top", JDialog.cShadowDown + "px");
		_d_dialog.append("<div id='d_000_dialog_modal'></div>");
		var _d_dialog_modal = $("#d_000_dialog_modal", oDocument); // 模态框
		_d_dialog_modal.css("border", JDialog.cBorderColor + " " + JDialog.cBorderSize + "px solid");
		_d_dialog_modal.css("width", iframeWidth + "px");
		_d_dialog_modal.css("background-color", JDialog.cBackgroundColor);
		_d_dialog_modal.append("<div id='d_000_dialog_modal_head'></div>");
		var _d_dialog_modal_head = $("#d_000_dialog_modal_head", oDocument);
		_d_dialog_modal_head.css("background-color", JDialog.cHeaderBackgroundColor);
		_d_dialog_modal_head.append("<span id='d_000_dialog_modal_head_left'><font color='#ffffff'><b>" + dialogTitle + "</b></font></span>");
		_d_dialog_modal_head.append("<span id='d_000_dialog_modal_head_right' title='"
				+ JDialog.cCloseTitle + "' onclick='JDialog.close();'><font color='#ffffff'><b>" + JDialog.cCloseText + "</b></font></span>");
		_d_dialog_modal.append("<div id='d_000_dialog_modal_body'></div>");
		var _d_dialog_modal_body = $("#d_000_dialog_modal_body", oDocument);
		_d_dialog_modal_body.css("width", iframeWidth + "px");
		_d_dialog_modal_body.css("height", iframeHeight + "px");
		_d_dialog_modal_body.append("<div id='d_000_dialog_modal_body_hide'>&nbsp;</div>");
		var _d_dialog_modal_body_hide = $("#d_000_dialog_modal_body_hide", oDocument);
		_d_dialog_modal_body_hide.css("top", "30px");
		_d_dialog_modal_body_hide.css("width", iframeWidth + "px");
		_d_dialog_modal_body_hide.css("height", iframeHeight + "px");
		_d_dialog_modal_body_hide.css("display", "none");
		_d_dialog_modal_body.append("<div id='d_000_dialog_modal_body_iframe'></div>");
		$("#d_000_dialog_modal_body_iframe", oDocument).append("<iframe id='d_000_iframe' src='" + iframeSrc + "' scrolling='"
						+ JDialog.scrolling + "' frameborder='0' width='" + iframeWidth + "' height='" + iframeHeight + "' />");
		$("#d_000_iframe", oDocument)[0].focus();
		$("body",JDialog.oDocument).css("overflow", "hidden");
	},
	close : function() {
		if (typeof ($("#d_000_shadow", JDialog.oDocument)[0]) != "undefined") {
			$("#d_000_shadow", JDialog.oDocument).remove();
		}
		if (typeof ($("#d_000_dialog", JDialog.oDocument)[0]) != "undefined") {
			$("#d_000_iframe", JDialog.oDocument)[0].src = "";
			$("#d_000_dialog",JDialog.oDocument).remove();
		}
		$("body",JDialog.oDocument).css("overflow", "auto");
	}
};
//function jd_stopBubble() {
//	if ($("#d_000_iframe", JDialog.oDocument).contents().find("body")[0]) {
//		$("#d_000_iframe", JDialog.oDocument)[0].contentWindow.onscroll = function() {
//			var scrollTop = $("#d_000_iframe", JDialog.oDocument).contents().find("body")[0].scrollTop;
//			var scrollHeight = $("#d_000_iframe", JDialog.oDocument).contents().find("body")[0].scrollHeight;
//			var windowHeight = $("#d_000_iframe", JDialog.oDocument).contents().find("body").height();
//			alert((scrollTop + " : " + windowHeight) + " : " + scrollHeight);
//		}
//		$("#d_000_iframe", JDialog.oDocument).contents().find("body")[0].onmousewheel = function(e) {
//			e = e || $("#d_000_iframe", JDialog.oDocument)[0].contentWindow.event;
//			var scrollTop = $("#d_000_iframe", JDialog.oDocument).contents().find("body")[0].scrollTop;
//			var scrollHeight = $("#d_000_iframe", JDialog.oDocument).contents().find("body")[0].scrollHeight;
//			var windowHeight = $("#d_000_iframe", JDialog.oDocument).contents().find("body").height();
//			if ((scrollTop + windowHeight >= scrollHeight && e.wheelDelta < 0) || (scrollTop <=0 && e.wheelDelta > 0)) {
//				return false;
//			}
//	  	};
//		clearInterval(JDialogInterval);
//	}
//}
//function jd_onmousewheelDown(oDocument) {
//	if(oDocument.addEventListener) {
//		oDocument.addEventListener('DOMMouseScroll', stopWheel, false);
//	} else {
//		oDocument.body.onmousewheel = function() {
//	  		return false;
//	  	};
//	}
//}
//function jd_onmousewheelOpen(oDocument) {
//	if(oDocument.addEventListener) {
//		oDocument.removeEventListener('DOMMouseScroll', stopWheel, false);
//	} else {
//		oDocument.body.onmousewheel=function(){
//			return;
//		};
//	}
//}