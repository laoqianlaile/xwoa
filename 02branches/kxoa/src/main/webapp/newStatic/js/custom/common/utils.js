/**
 *  系统常用工具
 *  自己自行设定命名空间，然后用jquery或者window对象发布出去
 *  注意：不要将除jquery外的第三方插件的公用方法放在这里
 */

;(function($,w){
	/********************抽取常用函数方法************************/
	var func = {
		/**
		 * 通用ajax方法，为了方便在回调函数里统一拦截
		 */
		ajax:function(opt){
			var defaultSetting = {
				type:'post',
				dataType:'json'
			};
			var setting = $.extend({},defaultSetting,opt);
			
			$.ajax({
				url:setting.url,
				type:setting.type,
				data:setting.data,
				dataType:setting.dataType,
				success:function(res){
					//可添加方法
					setting.success(res);
				}
			});
		}
		
	};
	
	/******************************规则校验**********************/
	var rule = {
			/**
			 * 是否为空
			 */
			isEmpty:function(str){
				if(str == null || str == undefined || str == '' || str == 'null' || str == 'undefined')
					return true;
				return false;
			},
			/**
			 * 是否是数字
			 */
			isDigit:function(str){
				if(/^\d*$/.test(str)){
					return true;
				}
				return false;
			}
	};
	
	//扩展jquery类库，发布自定义方法
	$.extend({
		Func:func,
		Rule:rule
	})
})(jQuery,window);