/**
 * 封装老控件的消息提示框 和 一个新增的右下角消息提醒
 * 
 * @author:	zk
 * @version: 0.1
 * @create: 2014/4/17
 * @modify: 2014/4/17 by zk
 */

define(['jquery', 'pMsg', 'Core'], function($, pMsg, Core) {
	var M = {
		info: function(msg, options) {
			pMsg.info(msg, options);
		},
		
		correct: function(msg, options) {
			pMsg.correct(msg, options);
		},
		
		warn: function(msg, options) {
			pMsg.warn(msg, options);
		},
		
		error: function(msg, options) {
			pMsg.error(msg, options);
		},
		
		confirm: function(msg, options) {
			pMsg.confirm(msg, options);
		},
		
		notify: function(title, msg, time, options) {
	
		}
	};
	
	return M;
});