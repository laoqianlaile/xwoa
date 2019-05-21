/**
 * @author Roger Wu 
 * @version 1.0
 */
(function($){
	$.fn.cssv = function(pre){
		var cssPre = $(this).css(pre);
		return cssPre.substring(0, cssPre.indexOf("px")) * 1;
	};
	$.fn.jBar = function(options){
		var op = $.extend({container:"#container", collapse:".collapse",collapseBut:".collapse div div", toggleBut:".toggleCollapse div", sideBar:"#sidebar", sideBar2:"#sidebar_s", splitBar:"#splitBar", splitBar2:"#splitBarProxy"}, options);
		return this.each(function(){
			var jbar = this;
			var sbar = $(op.sideBar2, jbar);
			var bar = $(op.sideBar, jbar);
			$(op.toggleBut, bar).hover(function(){$(this).addClass("hover");},function(){$(this).removeClass("hover")});
			$(op.toggleBut, bar).click(function(){
				CentitUI.ui.sbar = false;
				$(op.splitBar).hide();
				var sbarwidth = sbar.cssv("left") + sbar.outerWidth();
				var barleft = sbar.outerWidth() - bar.outerWidth();
				var cleft = $(op.container).cssv("left") - (bar.outerWidth() - sbar.outerWidth());
				var cwidth = bar.outerWidth() - sbar.outerWidth() + $(op.container).outerWidth();
				if(parseInt($(op.container).css("left"))==0){
					$(op.container).animate({width: cwidth},50,function(){
						bar.animate({left: Math.abs(barleft)}, 500, function(){
							//alert(barleft);
							bar.hide();
							sbar.show().css({"left":bar.width()+10}).animate({left: bar.width()-sbar.width()}, 200);
							$(window).trigger("resizeGrid");
						});
					});
				}else{
					$(op.container).animate({left: cleft,width: cwidth},50,function(){
						bar.animate({left: barleft}, 500, function(){
							bar.hide();
							sbar.show().css("left", -50).animate({left: 5}, 200);
							$(window).trigger("resizeGrid");
						});
					});
				}
				/*$(op.collapse,sbar).click(function(){
					var sbarwidth = sbar.cssv("left") + sbar.outerWidth();
					if(bar.is(":hidden")) {
						//$(op.toggleBut, bar).hide();
						//bar.show().animate({left: sbarwidth}, 500);
						//$(op.container).click(_hideBar);
					} else {
						//bar.animate({left: barleft}, 500, function(){
							//bar.hide();
						//});
					}
					function _hideBar() {
						$(op.container).unbind("click", _hideBar);
						if (!CentitUI.ui.sbar) {
							//bar.animate({left: barleft}, 500, function(){
								//bar.hide();
							//});
						}
					}
					return false;
				});*/
				return false;
			});
			$(op.collapseBut, sbar).hover(function(){$(this).addClass("hover");},function(){$(this).removeClass("hover")});
			$(op.collapse, sbar).click(function(){
				CentitUI.ui.sbar = true;
				if(parseInt($(op.container).css("left"))==0){
					var cl = bar.outerWidth()+sbar.outerWidth()+10;
					sbar.animate({"left":bar.width()+10}, 200, function(){				
						bar.show();
						sbar.hide();
					});
					
					bar.animate({"left":5 }, 800, function(){
						$(op.splitBar).show();
						$(op.toggleBut, bar).show();					
						var cright = bar.outerWidth() - sbar.outerWidth();
						var cwidth = $(op.container).outerWidth() - cright;
						$(op.container).css({left: 0,width: cwidth});
						//$(op.collapse, sbar).unbind('click');
						$(window).trigger("resizeGrid");
					});
				}else{
					sbar.animate({left: -25}, 200, function(){				
						bar.show();
						sbar.hide();
					});
				
					bar.animate({left: 5}, 800, function(){
						$(op.splitBar).show();
						$(op.toggleBut, bar).show();					
						var cleft = 5 + bar.outerWidth() + $(op.splitBar).outerWidth();
						var cwidth = $(op.container).outerWidth() - (cleft - $(op.container).cssv("left"));
						$(op.container).css({left: cleft,width: cwidth});
						//$(op.collapse, sbar).unbind('click');
						$(window).trigger("resizeGrid");
					});
					
				}
				return false;
			});
			$(op.splitBar).mousedown(function(event){
				$("#hiddenFrame").height($("#layout").height()).show();
				$(op.splitBar2).each(function(){
					var spbar2 = $(this);
					setTimeout(function(){spbar2.show();}, 100);
					spbar2.css({visibility: "visible",left: $(op.splitBar).css("left")});					
					spbar2.jDrag($.extend(options, {obj:$("#sidebar"), move:"horizontal", event:event,stop: function(){
						$(this).css("visibility", "hidden");
						if(parseInt($(op.container).css("left"))!=0){
							var move = $(this).cssv("left") - $(op.splitBar).cssv("left");
							//alert(move);
							var sbarwidth = bar.outerWidth() + move;
							var cleft = $(op.container).cssv("left") + move;
							var cwidth = $(op.container).outerWidth() - move;
							bar.css("width", sbarwidth);
							$(op.splitBar).css("left", $(this).css("left"));
							$(op.container).css({left: cleft,width: cwidth});
						}else{
							var move = $(this).cssv("left") - $(op.splitBar).cssv("left");
							//alert(move);
							var sbarwidth = bar.outerWidth() - move;
							var cwidth = $(op.container).outerWidth() + move;
							bar.css("width", sbarwidth);
							$(op.splitBar).css("left", $(this).css("left"));
							$(op.container).css({width: cwidth});
							$("#leftside").css({right: sbarwidth+10});
						}
						
						$("#hiddenFrame").hide();
					}}));
					return false;					
				});
			});
		});
	}
})(jQuery);
