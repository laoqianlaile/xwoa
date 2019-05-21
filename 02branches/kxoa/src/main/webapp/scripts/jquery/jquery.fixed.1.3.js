/* =================================================
//
// jQuery Fixed Plugins 1.3.1
// author : 
// Url: 
// Data : 2012-03-30
//
// 参数 : float --> 悬浮方向[left or right]
//		  minStatue --> 最小状态，只有show_btn
//		  skin      --> 皮肤控制
//		  durationTime --> 完成时间
//事例  :	
		  $("#scrollsidebar2").fix({
		  	float : 'right',	//default.left or right 
			minStatue : true,	//default.false or true 
			skin : 'green',		//default.gray or yellow 、blue 、green 、orange 、white 
		  	durationTime : 1000 //
		  });
//
// =================================================*/

;(function($){
    $.fn.fix = function(options){
        var defaults = {
            float : 'left',
			minStatue : false,
			skin : 'gray',
			durationTime : 1000	
        };
        var optionsT = $.extend(defaults, options);		
        this.each(function(){			
            //获取对象
			var thisBox = $(this),
				closeBtn = thisBox.find('.close_btn' ),
				show_btn = thisBox.find('.show_btn' ),
				sideContent = thisBox.find('.side_content'),
				sideList = thisBox.find('.side_list')
				;	
			var defaultTop = thisBox.offset().top;	//对象的默认top	
			thisBox.css(optionsT.float, 0);
			if(optionsT.minStatue){
				$(".show_btn").css("float", optionsT.float);
				sideContent.css('width', 0);
				show_btn.css('width', 25);
				
			}
			//皮肤控制
			if(optionsT.skin) thisBox.addClass('side_'+optionsT.skin);
			//核心scroll事件			
			$(window).bind("scroll",function(){
				var scotop ; 
				if(document.body.scrollTop){
				scotop = document.body.scrollTop;
				}else{
				scotop = document.documentElement.scrollTop
				}
				
				var offsetTop = defaultTop +scotop + "px";
				thisBox.animate({
	                top: offsetTop
	            },
	            {
	                duration: optionsT.durationTime,	
	                queue: false    //此动画将不进入动画队列
	            });
			});	
			//close事件
			closeBtn.bind("click",function(){
				sideContent.animate({width: '0px'},"fast");
            	show_btn.stop(true, true).delay(300).animate({ width: '25px'},"fast");
			});
			//show事件
			 show_btn.click(function() {
	            $(this).animate({width: '0px'},"fast");
	            sideContent.stop(true, true).delay(200).animate({ width: '154px'},"fast");
	        });
				
        });	//end this.each

    };
})(jQuery);