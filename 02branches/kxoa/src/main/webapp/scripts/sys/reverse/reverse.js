/**
 * 反转Ajax
 */
$(function() {
	dwr.engine.setNotifyServerOnPageUnload(true, true);
	dwr.engine.setActiveReverseAjax(true);
});

jQuery.receive = {
	receiveMessages : function(message) {
		alert(message);
	}
};

jQuery.CENTIT_OA_RECEIVE = {
	sysExpired : function(message) {
		alert(message);

		window.location = ('undefined' == typeof window.DWZ ? window.Config.contextPath : window.DWZ.contextPath);
	}
};