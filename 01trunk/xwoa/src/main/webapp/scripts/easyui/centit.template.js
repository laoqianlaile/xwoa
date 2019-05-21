(function($) {
	var template = window.Template = {};
	
	$('script[type$=template]').each(function() {
		var html = this.innerHTML;
		
		if (window.Mustache) {
			Mustache.parse(html);
		}
		template[$(this).attr('id')] = html;
	});
})(jQuery);