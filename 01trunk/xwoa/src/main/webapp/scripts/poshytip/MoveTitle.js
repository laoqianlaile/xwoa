
/*(function($) {
	$.tooltips = function(opts) {

		var settings = $.extend({
			className : "jToolTips",
			tipbox : "jToolTipsBox"
		}, opts);

		var tipBox = $(' DIV id="' + settings['tipbox'] + '"></DIV> ').hide()
				.appendTo('body');

		$("." + settings['className']).each(function() {
			var _this = this;			
			$(this).bind("mouseover mousemove", function(event) {
				if (!_this.isActive) {
					$(_this).attr("title", "");
					_this.isActive = true;
					tipBox.html($("#" + $(_this).attr("rel")).html()).show();
				}
				tipBox.css({
					top : event.pageY + 12,
					left : event.pageX + 12
				});
			});
			$(this).mouseout(function() {
				_this.isActive = false;
				tipBox.hide();
			});
		});
		
		$("tbody td").each(function() {
			alert(1111);
			var _this = this;
			//是否显示tip开关，ectable暂不使用开关
			_this.isActive = true;
			$(this).bind("mouseover mousemove", function(event) {
				if (!_this.isActive) {
					$(_this).attr("title", "");
					_this.isActive = true;
					tipBox.html($("#" + $(_this).attr("rel")).html()).show();
				}
				tipBox.css({
					top : event.pageY + 12,
					left : event.pageX + 12
				});
			});
			$(this).mouseout(function() {
				_this.isActive = false;
				tipBox.hide();
			});
		});

	};
})(jQuery);*/

jQuery.noConflict(); 
// jQuery 未加载 

$(function() {
	//排除分页下的td
	$("tbody td:not(.compactToolbar td,.statusBar)").poshytip({
		content:function(updateCallback){
			var inControl = $(this).children("a");
			var ecinPut = $(this).children("input");
			var vhtml="";
			if (inControl.length == 1
					&& inControl.html().replace(/& nbsp;/g, "") != "") {
				//$(this).attr("title", inControl.html());
				//$(this).poshytip(inControl.html());
				if($(this).attr("class")!='noshow'){
					vhtml=inControl.html();
				}else{
					$(this).poshytip('hide');

				}				
			}if (ecinPut.length == 1) {
				//$(this).attr("title", inControl.html());
				//$(this).poshytip(inControl.html());
				if($(this).attr("class")!='noshow'){
					var vlen=ecinPut.val().trim().length;
					vhtml=ecinPut.val();
					if(vlen>30){
						  vhtml="<div style='width:400px;word-break:break-all;'>"+vhtml+"</div>";
					 }
				}else{
					$(this).poshytip('hide');

				}				
			} 
			else if ($(this).children().length == 0
					&& $(this).html().replace(/& nbsp;/g, "") != "") {
				//$(this).attr("title", $(this).html());
				if($(this).attr("class")!='noshow'){
				  var vlen=$(this).html().trim().length;
				  vhtml=$(this).html();
				  if(vlen>30){
					  vhtml="<div style='width:400px;'>"+$(this).html()+"</div>";
				  }
				}else{
					$(this).poshytip('hide');
				}
			}
			return vhtml ;
		}
	});

});