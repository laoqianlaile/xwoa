/**
 * Theme Plugins
 * @author ZhangHuihua@msn.com
 */


(function($){
	$.fn.extend({
		theme: function(options){
			var op = $.extend({themeBase:"themes"}, options);
			var _themeHref = op.themeBase + "/#theme#/style.css";
			return this.each(function(){
				var jThemeLi = $(this).find(">li[theme]");
				var setTheme = function(themeName){
					$("head").find("link[href$='style.css']").attr("href", _themeHref.replace("#theme#", themeName));
					jThemeLi.find(">div").removeClass("selected");
					jThemeLi.filter("[theme="+themeName+"]").find(">div").addClass("selected");
					if ($.isFunction($.cookie)) $.cookie("CentitUI_theme", themeName,{ expires: 365, path: '/' });
				}
				
				jThemeLi.each(function(index){
					var $this = $(this);
					var themeName = $this.attr("theme");
					$this.addClass(themeName).click(function(){
						setTheme(themeName);
						/*$("iframe").each(function(i){
							document.frames[i].location.reload(true);
						});*/
					});
				});
					
				if ($.isFunction($.cookie)){
					var themeName = $.cookie("CentitUI_theme");
					if (themeName) {
						setTheme(themeName);
					}
				}
				
			});
		}
	});
})(jQuery);
