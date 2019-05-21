define(['jquery', 'Core', 'TokenInput'], function($, Core) {
	var Config = {
	              SELECTOR: '.autocomplete',
	              DEFAULT_PROPERTIES: [
	                                   'url', 
	                                   'method', 
	                                   'theme', 
	                                   'noResultsText', 
	                                   'searchingText', 
	                                   'hintText',
	                                   'prePopulate',
	                                   'tokenLimit'
	                                   ],
	              DEFAULT_OPTIONS: {
						method: 'post',
						theme:	'facebook',
						noResultsText: '没有找到结果',
						searchingText: '正在查询',
						hintText: '请输入搜索关键字'
				  }
	};
	
	var A = {
	         init: function(container, options) {
        	    options = $.extend({}, Config.DEFAULT_OPTIONS, options);
	         
				$(Config.SELECTOR, container).each(function() {
					var settings = $.extend({}, options), input = $(this);
					
					input.readDatas(Config.DEFAULT_PROPERTIES, settings);
					
					input.tokenInput(settings.url, settings);
				}); 
			 }
	};
	
	return A;
});