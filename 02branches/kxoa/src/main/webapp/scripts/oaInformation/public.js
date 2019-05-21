/*  
*@Description: 全站通用js
*@Author: 978873550@qq.com
*@Update: 978873550@qq.com (2012-09-23)
 */
 /**tabs选择类
* tabNav：鼠标滑过对象
* tabShow：要进行切换显隐的对象
* current：tabNav选中效果current可自由命名
* methods: 方法进行自由选择 tLi tA tMore
* moreBox: 更多对应的选项进行切换的盒子id
* more:更多显隐的对象
* event:事件支持mouseover click
* 978873550@qq.com(2012-09-25)
* **/
(function($){
	//插件主体
	$.fn.noteTabs=function(options){
		var opts=jQuery.extend({},jQuery.fn.noteTabs.defaults,options);
		
		var tabNavObj=$(opts.tabNav),//鼠标滑过对象
			tabShowObj=$(opts.tabShow,$(this)),//要进行切换显隐的对象
			moreBoxObj=$(opts.moreBox),//more链接切换对应的盒子
			moreObj=opts.more,//more链接对象
			currentStr=opts.current,//tabNav选中效果
			methodsStr=opts.methods,//方法的选择
			eventStr=opts.event;//事件选择
			
		return this.each(function(){
			$.fn.noteTabs.events['evt'](tabNavObj,tabShowObj,currentStr,eventStr,methodsStr,moreBoxObj,moreObj);
		});
		
	};
	//默认参数
	$.fn.noteTabs.defaults={
		tabNav:'#tabNav li',
		tabShow:'#tabShow .hidden',
		current:'current',
		event:'mouseover',
		methods:'tA',
		moreBox:'#moreBox',
		more:'.more'
	};
	//方法
	$.tabsHandle=$.fn.noteTabs.tabsHandle={
		//current在Li上
		tLi:function(elem,tabShowObj,currentStr){
			var num=elem.index();
			elem.addClass(currentStr).siblings().removeClass(currentStr);
			tabShowObj.eq(num).show().siblings().hide();
		},
		//current在A上
		tA:function(elem,tabShowObj,currentStr){
			var num=elem.index();
			elem.find('a').addClass(currentStr).parent('li').siblings().find('a').removeClass(currentStr);
			tabShowObj.eq(num).show().siblings().hide();
		},
		//current在Li上且添加more切换
		tMore:function(elem,tabShowObj,currentStr,moreBoxObj,moreObj){
			var num=elem.index();
			elem.find('a').addClass(currentStr).parent('li').siblings().find('a').removeClass(currentStr);
			tabShowObj.eq(num).show().siblings().hide();
			moreBoxObj.find(moreObj).eq(num).show().siblings(moreObj).hide()
		},
		//current在li上且添加more切换
		tLiMore:function(elem,tabShowObj,currentStr,moreBoxObj,moreObj){
			var num=elem.index();
			elem.addClass(currentStr).siblings().removeClass(currentStr);
			tabShowObj.eq(num).show().siblings().hide();
			
			moreBoxObj.find(moreObj).eq(num).show().siblings(moreObj).hide()
		}
	};
	//事件
	$.fn.noteTabs.events={
		
		evt:function(tabNavObj,tabShowObj,currentStr,eventStr,methodsStr,moreBoxObj,moreObj){
			switch(methodsStr)
			{
				case 'tLi':
					tabNavObj.bind(eventStr,function(){
						$.tabsHandle.tLi($(this),tabShowObj,currentStr);
					});
				break;
				case 'tA':
					tabNavObj.bind(eventStr,function(){
						$.tabsHandle.tA($(this),tabShowObj,currentStr);
					});
				break;
				case 'tMore':
					tabNavObj.bind(eventStr,function(){
						$.tabsHandle.tMore($(this),tabShowObj,currentStr,moreBoxObj,moreObj);
					});
				break;
				case 'tLiMore':
					tabNavObj.bind(eventStr,function(){
						$.tabsHandle.tLiMore($(this),tabShowObj,currentStr,moreBoxObj,moreObj);
					});
				break;
			};
	
			return this;
		}
	};
})(jQuery);
/*液态布局
* body设置rePaint属性且为true的时候开启，否则不调用液态布局
*/
(function($){
	$.fn.rePaint=function(options){
		var opts=jQuery.extend({},jQuery.fn.rePaint.config,options);
		var setObj=opts.setObj,//手动设置id对象集合
		 space=0,
		 Base=opts.minW+space,
		 MaxWidth=opts.maxW;
		 var debug=$('body').attr("rePaint");
		 
		function reSizeBox(){
			var seeW=$(window).width();
			var dat=seeW-Base;
			var length=setObj.length;
			
			if(seeW>Base&&seeW<MaxWidth){
				for(var i=0;i<length;i++){
					$(setObj[i].obj).width(dat*setObj[i].per+setObj[i].base);
					//$(setObj[i].obj).animate({width:dat*setObj[i].per+setObj[i].base},400);
				}
			}else if(seeW>MaxWidth){
				var dat=MaxWidth-Base;
				for(var i=0;i<length;i++){
					$(setObj[i].obj).width(dat*setObj[i].per+setObj[i].base);
				}
			}else{
				for(var i=0;i<length;i++){
					$(setObj[i].obj).width(setObj[i].base);
				}
			};
			
		};
		//载入初始化
		if(debug=="true"){
			reSizeBox();
		};
		
		return this.each(function(){
			
			if(debug=="true"){
				$(window).resize(function() {
					reSizeBox();
				});
			};
			
		});
	};
	//参数配置
	$.fn.rePaint.config={
		minW:980,
		maxW:1400,
		setObj:[
		{obj:"#pageBox",per:1,base:980},
		{obj:"#footer",per:1,base:980}
		]
	};
})(jQuery);

(function($){
	$.fn.textSlider = function(settings){    
            settings = jQuery.extend({
                speed : "normal",
                line : 2,
                timer : 3000
            }, settings);
            return this.each(function() {
                $.fn.textSlider.scllor( $( this ), settings );
            });
        };
	$.fn.textSlider.scllor = function($this, settings){
            var ul = $("ul:eq(0)",$this );
            var timerID;
            var li = ul.children();
            var liHight=$(li[0]).height();
            var upHeight=0-settings.line*liHight;//滚动的高度；
            var scrollUp=function(){
                ul.animate({marginTop:upHeight},settings.speed,function(){
                    for(i=0;i<settings.line;i++){
                        ul.find("li:first",$this).appendTo(ul);
                    }
                    ul.css({marginTop:0});
                });
            };
            var autoPlay=function(){
                timerID = window.setInterval(scrollUp,settings.timer);
            };
            var autoStop = function(){
                window.clearInterval(timerID);
            };
            //事件绑定
            ul.hover(autoStop,autoPlay).mouseout();
	};
})(jQuery);
(function($){
	$.fn.slide=function(options){
		$.fn.slide.deflunt={
		effect : "fade", //效果 || fade：渐显； || top：上滚动；|| left：左滚动；|| topLoop：上循环滚动；|| leftLoop：左循环滚动；|| topMarquee：上无缝循环滚动；|| leftMarquee：左无缝循环滚动；
		autoPlay:false, //自动运行
		delayTime : 500, //效果持续时间
		interTime : 2500,//自动运行间隔。当effect为无缝滚动的时候，相当于运行速度。
		defaultIndex : 0,//默认的当前位置索引。0是第一个
		titCell:".hd li",//导航元素
		mainCell:".bd",//内容元素的父层对象
		trigger: "mouseover",//触发方式 || mouseover：鼠标移过触发；|| click：鼠标点击触发；
		scroll:1,//每次滚动个数。
		vis:1,//visible，可视范围个数，当内容个数少于可视个数的时候，不执行效果。
		titOnClassName:"on",//当前位置自动增加的class名称
		autoPage:false,//系统自动分页，当为true时，titCell则为导航元素父层对象，同时系统会在titCell里面自动插入分页li元素(1.2版本新增)
		prevCell:".prev",//前一个按钮元素。
		nextCell:".next"//后一个按钮元素。
		};

		return this.each(function() {
			var opts = $.extend({},$.fn.slide.deflunt,options);
			var index=opts.defaultIndex;
			var prevBtn = $(opts.prevCell, $(this));
			var nextBtn = $(opts.nextCell, $(this));
			var navObj = $(opts.titCell, $(this));//导航子元素结合
			var navObjSize = navObj.size();
			var conBox = $(opts.mainCell , $(this));//内容元素父层对象
			var conBoxSize=conBox.children().size();
			var slideH=0;
			var slideW=0;
			var selfW=0;
			var selfH=0;
			var autoPlay = opts.autoPlay;
			var inter=null;//setInterval名称 
			var oldIndex = index;

			if(conBoxSize<opts.vis) return; //当内容个数少于可视个数，不执行效果。

			//处理分页
			if( navObjSize==0 )navObjSize=conBoxSize;
			if( opts.autoPage ){
				var tempS = conBoxSize-opts.vis;
				navObjSize=1+parseInt(tempS%opts.scroll!=0?(tempS/opts.scroll+1):(tempS/opts.scroll)); 
				navObj.html(""); 
				for( var i=0; i<navObjSize; i++ ){ navObj.append("<li>"+(i+1)+"</li>") }
				var navObj = $("li", navObj);//重置导航子元素对象
			}

			conBox.children().each(function(){ //取最大值
				if( $(this).width()>selfW ){ selfW=$(this).width(); slideW=$(this).outerWidth(true);  }
				if( $(this).height()>selfH ){ selfH=$(this).height(); slideH=$(this).outerHeight(true);  }
			});

			switch(opts.effect)
			{
				case "top": conBox.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; height:'+opts.vis*slideH+'px"></div>').css( { "position":"relative","padding":"0","margin":"0"}).children().css( {"height":selfH} ); break;
				case "left": conBox.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; width:'+opts.vis*slideW+'px"></div>').css( { "width":conBoxSize*slideW,"position":"relative","overflow":"hidden","padding":"0","margin":"0"}).children().css( {"float":"left","width":selfW} ); break;
				case "leftLoop":
				case "leftMarquee":
					conBox.children().clone().appendTo(conBox).clone().prependTo(conBox); 
					conBox.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; width:'+opts.vis*slideW+'px"></div>').css( { "width":conBoxSize*slideW*3,"position":"relative","overflow":"hidden","padding":"0","margin":"0","left":-conBoxSize*slideW}).children().css( {"float":"left","width":selfW}  ); break;
				case "topLoop":
				case "topMarquee":
					conBox.children().clone().appendTo(conBox).clone().prependTo(conBox); 
					conBox.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; height:'+opts.vis*slideH+'px"></div>').css( { "height":conBoxSize*slideH*3,"position":"relative","padding":"0","margin":"0","top":-conBoxSize*slideH}).children().css( {"height":selfH} ); break;
			}

			//效果函数
			var doPlay=function(){
				switch(opts.effect)
				{
					case "fade": case "top": case "left": if ( index >= navObjSize) { index = 0; } else if( index < 0) { index = navObjSize-1; } break;
					case "leftMarquee":case "topMarquee": if ( index>= 2) { index=1; } else if( index<0) { index = 0; } break;
					case "leftLoop": case "topLoop":
						var tempNum = index - oldIndex; 
						if( navObjSize>2 && tempNum==-(navObjSize-1) ) tempNum=1;
						if( navObjSize>2 && tempNum==(navObjSize-1) ) tempNum=-1;
						var scrollNum = Math.abs( tempNum*opts.scroll );
						if ( index >= navObjSize) { index = 0; } else if( index < 0) { index = navObjSize-1; }
					break;
				}
				switch (opts.effect)
				{
					case "fade":conBox.children().stop(true,true).eq(index).fadeIn(opts.delayTime).siblings().hide();break;
					case "top":conBox.stop(true,true).animate({"top":-index*opts.scroll*slideH},opts.delayTime);break;
					case "left":conBox.stop(true,true).animate({"left":-index*opts.scroll*slideW},opts.delayTime);break;
					case "leftLoop":
						if(tempNum<0 ){
								conBox.stop(true,true).animate({"left":-(conBoxSize-scrollNum )*slideW},opts.delayTime,function(){
								for(var i=0;i<scrollNum;i++){ conBox.children().last().prependTo(conBox); }
								conBox.css("left",-conBoxSize*slideW);
							});
						}
						else{
							conBox.stop(true,true).animate({"left":-( conBoxSize + scrollNum)*slideW},opts.delayTime,function(){
								for(var i=0;i<scrollNum;i++){ conBox.children().first().appendTo(conBox); }
								conBox.css("left",-conBoxSize*slideW);
							});
						}break;// leftLoop end

					case "topLoop":
						if(tempNum<0 ){
								conBox.stop(true,true).animate({"top":-(conBoxSize-scrollNum )*slideH},opts.delayTime,function(){
								for(var i=0;i<scrollNum;i++){ conBox.children().last().prependTo(conBox); }
								conBox.css("top",-conBoxSize*slideH);
							});
						}
						else{
							conBox.stop(true,true).animate({"top":-( conBoxSize + scrollNum)*slideH},opts.delayTime,function(){
								for(var i=0;i<scrollNum;i++){ conBox.children().first().appendTo(conBox); }
								conBox.css("top",-conBoxSize*slideH);
							});
						}break;//topLoop end

					case "leftMarquee":
						var tempLeft = conBox.css("left").replace("px",""); 

						if(index==0 ){
								conBox.animate({"left":++tempLeft},0,function(){
									if( conBox.css("left").replace("px","")>= 0){ for(var i=0;i<conBoxSize;i++){ conBox.children().last().prependTo(conBox); }conBox.css("left",-conBoxSize*slideW);}
								});
						}
						else{
								conBox.animate({"left":--tempLeft},0,function(){
									if(  conBox.css("left").replace("px","")<= -conBoxSize*slideW*2){ for(var i=0;i<conBoxSize;i++){ conBox.children().first().appendTo(conBox); }conBox.css("left",-conBoxSize*slideW);}
								});
						}break;// leftMarquee end

						case "topMarquee":
						var tempTop = conBox.css("top").replace("px",""); 
							if(index==0 ){
									conBox.animate({"top":++tempTop},0,function(){
										if( conBox.css("top").replace("px","") >= 0){ for(var i=0;i<conBoxSize;i++){ conBox.children().last().prependTo(conBox); }conBox.css("top",-conBoxSize*slideH);}
									});
							}
							else{
									conBox.animate({"top":--tempTop},0,function(){
										if( conBox.css("top").replace("px","")<= -conBoxSize*slideH*2){ for(var i=0;i<conBoxSize;i++){ conBox.children().first().appendTo(conBox); }conBox.css("top",-conBoxSize*slideH);}
									});
							}break;// topMarquee end


				}//switch end
					navObj.removeClass(opts.titOnClassName).eq(index).addClass(opts.titOnClassName);
					oldIndex=index;
			};
			//初始化执行
			doPlay();

			//自动播放
			if (autoPlay) {
					if( opts.effect=="leftMarquee" || opts.effect=="topMarquee"  ){
						index++; inter = setInterval(doPlay, opts.interTime);
						conBox.hover(function(){if(autoPlay){clearInterval(inter); }},function(){if(autoPlay){clearInterval(inter);inter = setInterval(doPlay, opts.interTime);}});
					}else{
						 inter=setInterval(function(){index++; doPlay() }, opts.interTime); 
						$(this).hover(function(){if(autoPlay){clearInterval(inter); }},function(){if(autoPlay){clearInterval(inter); inter=setInterval(function(){index++; doPlay() }, opts.interTime); }});
					}
			}

			//鼠标事件
			var mst;
			if(opts.trigger=="mouseover"){
				navObj.hover(function(){ clearTimeout(mst); index=navObj.index(this); mst = window.setTimeout(doPlay,200); }, function(){ if(!mst)clearTimeout(mst); });
			}else{ navObj.click(function(){index=navObj.index(this);  doPlay(); })  }
			nextBtn.click(function(){ index++; doPlay(); });
			prevBtn.click(function(){  index--; doPlay(); });

    	});//each End

	};//slide End

})(jQuery);
$(function(){
	/*siteTopTips*/
	$('#smoothBegin').toggle(
		function(){
			$('#smoothTips').slideDown();
		},
		function(){
			$('#smoothTips').slideUp();
		}
	);
	
	/*menuSub
	 *menuBox:整个导航的大盒子
	 *navLi:主导航对象
	 *subBox:二级导航对象
	 * 978873550@qq.com
	 * */
	 var menuStay;
	function menuSub(menuBox,navLi,subBox){
		var num;
		 	 if (!$(menuBox).is(":animated")) {
		 	//首先判断是否处于动画状态
	        $(navLi).live('mouseover',function(){
				var $this = $(this);
				//menuStay = window.setTimeout(function(){
	        	num=$this.index();
	        	$this.addClass('current').siblings().removeClass('current');
	        	$(subBox).show();
	        	$(subBox).find('ul:eq(0)').children('li').eq(num).delay(300)
	        	.animate({ opacity:'show', height:'show' },400)
	        	.siblings().stop(true,true).hide();
	        });
	     	 //控制鼠标移除事件
	        $(menuBox).live('mouseleave',function(){
				$(subBox).find('ul:eq(0)').children('li').eq(num).stop()
				//window.clearTimeout(menuStay)
	            $(subBox).hide();
	            $(navLi).removeClass('current');
	        });
	        
	     };
	       
     };
     menuSub('#site-menu','#menu li','#menuSub');
     //主导航二级
	 $('#zwgk').noteTabs({
	 	tabNav:'#subNav-workTab li',
		tabShow:'#subNav-workShow .NormalNewsList',
		current:'hover',
		methods:'tLi'
	 });
	/*返回顶部*/
	function returnTop(){
	 var $backToTopTxt = "返回顶部", $backToTopEle = $('<div class="backToTop" id="backToTop"></div>').appendTo($("body"))
        .text($backToTopTxt).attr("title", $backToTopTxt).click(function() {
            $("html, body").animate({ scrollTop: 0 }, 120);
    }), $backToTopFun = function() {
        var st = $(document).scrollTop(), winh = $(window).height();
        (st > 0)? $backToTopEle.show(): $backToTopEle.hide();    
        //IE6下的定位
        if (!window.XMLHttpRequest) {
            $backToTopEle.css("top", st + winh - 166);    
        }
    };	
     $(window).bind("scroll", $backToTopFun);
    	$(function() { $backToTopFun(); });
    };
    returnTop();
	
function AddFavorite(sURL, sTitle)
{
    try
    {
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e)
    {
        try
        {
            window.sidebar.addPanel(sTitle, sURL, "");
        }
        catch (e)
        {
            alert("加入收藏失败，请使用Ctrl+D进行添加");
        }
    }
};
function SetHome(obj,vrl){
        try{
                obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
        }
        catch(e){
                if(window.netscape) {
                        try {
                                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                        }
                        catch (e) {
                                alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
                        }
                        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                        prefs.setCharPref('browser.startup.homepage',vrl);
                 }
        }
};
	//加入收藏设为主页
	(function(){
		var getEobj=document.getElementById('addFavorite');
		if(getEobj){
			getEobj.onclick=function(){
				AddFavorite(window.location,document.title)
			};
		}
		var getEset=document.getElementById('setHome');
		if(getEset){
			getEset.onclick=function(){
				SetHome(this,window.location);
			};
		}
	})();
})

//下拉菜单加载后执行方法，处理旅游切换标签页
var commonSubMenuLoaded = function(){
	//iframe加载后执行该方法，由于是在iframe中渲染DOM元素到主页面中，所以设置在加载后1秒钟后再执行元素的动作绑定
	window.setTimeout(function(){
	  foldSub("#NavfoldBox .nav-fold-list dd");
	$("#NavfoldBox .nav-fold-list dd").eq(0).trigger('mouseover');						   
	},1000);
    
}
	//折叠
	function foldSub(targetObj){
		var targetObj= $(targetObj);
		var getW=$(targetObj).find(".nav-brief-box").width();
		//$(targetObj).find(".nav-brief-box").width(0);
		targetObj.hover(function(){
			$(this).addClass("current").siblings().removeClass("current");
			$(this).find(".nav-flod-show").show();
			//$(this).find(".nav-brief-box").stop(true,true).delay(100).animate({'width':getW},600);
			$(this).find(".nav-brief-box").show();
		},
		function(){
			$(this).addClass("current").siblings().removeClass("current");
			//$(targetObj).not($(this)).find(".nav-brief-box").width(0);
		});
	}; 

	// 网页简繁体转换
// 本js用于客户在网站页面选择繁体中文或简体中文显示，默认是正常显示，即简繁体同时显示
// 在用户第一次访问网页时,会自动检测客户端语言进行操作并提示.此功能可关闭
// 本程序只在UTF8编码下测试过，不保证其他编码有效

// -------------- 以下参数大部分可以更改 --------------------
//s = simplified 简体中文 t = traditional 繁体中文 n = normal 正常显示
var zh_default = 'n'; //默认语言，请不要改变
var zh_choose = 'n'; //当前选择
var zh_expires = 7; //cookie过期天数
var zh_class = 'link_language'; //链接的class名，id为class + s/t/n 之一
var zh_style_active = 'font-weight:bold; color:green;'; //当前选择的链接式样
var zh_style_inactive = 'color:blue;'; //非当前选择的链接式样
var zh_browserLang = ''; //浏览器语言
var zh_autoLang_t = true; //浏览器语言为繁体时自动进行操作
var zh_autoLang_s = false; //浏览器语言为简体时自动进行操作
var zh_autoLang_alert = false; //自动操作后是否显示提示消息
//自动操作后的提示消息
var zh_autoLang_msg = '歡迎來到本站,本站爲方便台灣香港的用戶\n1.采用UTF-8國際編碼,用任何語言發帖都不用轉碼.\n2.自動判斷繁體用戶,顯示繁體網頁\n3.在網頁最上方有語言選擇,如果浏覽有問題時可以切換\n4.本消息在cookie有效期內只顯示一次';
var zh_autoLang_checked = 0; //次检测浏览器次数,第一次写cookie为1,提示后为2,今后将不再提示


//判断浏览器语言的正则,ie为小写,ff为大写
var zh_langReg_t = /^zh-tw|zh-hk$/i;
var zh_langReg_s = /^zh-cn$/i;

//简体繁体对照字表,可以自行替换
var zh_s = '皑蔼碍爱翱袄奥坝罢摆败颁办绊帮绑镑谤剥饱宝报鲍辈贝钡狈备惫绷笔毕毙闭边编贬变辩辫鳖瘪濒滨宾摈饼拨钵铂驳卜补参蚕残惭惨灿苍舱仓沧厕侧册测层诧搀掺蝉馋谗缠铲产阐颤场尝长偿肠厂畅钞车彻尘陈衬撑称惩诚骋痴迟驰耻齿炽冲虫宠畴踌筹绸丑橱厨锄雏础储触处传疮闯创锤纯绰辞词赐聪葱囱从丛凑窜错达带贷担单郸掸胆惮诞弹当挡党荡档捣岛祷导盗灯邓敌涤递缔点垫电淀钓调迭谍叠钉顶锭订东动栋冻斗犊独读赌镀锻断缎兑队对吨顿钝夺鹅额讹恶饿儿尔饵贰发罚阀珐矾钒烦范贩饭访纺飞废费纷坟奋愤粪丰枫锋风疯冯缝讽凤肤辐抚辅赋复负讣妇缚该钙盖干赶秆赣冈刚钢纲岗皋镐搁鸽阁铬个给龚宫巩贡钩沟构购够蛊顾剐关观馆惯贯广规硅归龟闺轨诡柜贵刽辊滚锅国过骇韩汉阂鹤贺横轰鸿红后壶护沪户哗华画划话怀坏欢环还缓换唤痪焕涣黄谎挥辉毁贿秽会烩汇讳诲绘荤浑伙获货祸击机积饥讥鸡绩缉极辑级挤几蓟剂济计记际继纪夹荚颊贾钾价驾歼监坚笺间艰缄茧检碱硷拣捡简俭减荐槛鉴践贱见键舰剑饯渐溅涧浆蒋桨奖讲酱胶浇骄娇搅铰矫侥脚饺缴绞轿较秸阶节茎惊经颈静镜径痉竞净纠厩旧驹举据锯惧剧鹃绢杰洁结诫届紧锦仅谨进晋烬尽劲荆觉决诀绝钧军骏开凯颗壳课垦恳抠库裤夸块侩宽矿旷况亏岿窥馈溃扩阔蜡腊莱来赖蓝栏拦篮阑兰澜谰揽览懒缆烂滥捞劳涝乐镭垒类泪篱离里鲤礼丽厉励砾历沥隶俩联莲连镰怜涟帘敛脸链恋炼练粮凉两辆谅疗辽镣猎临邻鳞凛赁龄铃凌灵岭领馏刘龙聋咙笼垄拢陇楼娄搂篓芦卢颅庐炉掳卤虏鲁赂禄录陆驴吕铝侣屡缕虑滤绿峦挛孪滦乱抡轮伦仑沦纶论萝罗逻锣箩骡骆络妈玛码蚂马骂吗买麦卖迈脉瞒馒蛮满谩猫锚铆贸么霉没镁门闷们锰梦谜弥觅绵缅庙灭悯闽鸣铭谬谋亩钠纳难挠脑恼闹馁腻撵捻酿鸟聂啮镊镍柠狞宁拧泞钮纽脓浓农疟诺欧鸥殴呕沤盘庞国爱赔喷鹏骗飘频贫苹凭评泼颇扑铺朴谱脐齐骑岂启气弃讫牵扦钎铅迁签谦钱钳潜浅谴堑枪呛墙蔷强抢锹桥乔侨翘窍窃钦亲轻氢倾顷请庆琼穷趋区躯驱龋颧权劝却鹊让饶扰绕热韧认纫荣绒软锐闰润洒萨鳃赛伞丧骚扫涩杀纱筛晒闪陕赡缮伤赏烧绍赊摄慑设绅审婶肾渗声绳胜圣师狮湿诗尸时蚀实识驶势释饰视试寿兽枢输书赎属术树竖数帅双谁税顺说硕烁丝饲耸怂颂讼诵擞苏诉肃虽绥岁孙损笋缩琐锁獭挞抬摊贪瘫滩坛谭谈叹汤烫涛绦腾誊锑题体屉条贴铁厅听烃铜统头图涂团颓蜕脱鸵驮驼椭洼袜弯湾顽万网韦违围为潍维苇伟伪纬谓卫温闻纹稳问瓮挝蜗涡窝呜钨乌诬无芜吴坞雾务误锡牺袭习铣戏细虾辖峡侠狭厦锨鲜纤咸贤衔闲显险现献县馅羡宪线厢镶乡详响项萧销晓啸蝎协挟携胁谐写泻谢锌衅兴汹锈绣虚嘘须许绪续轩悬选癣绚学勋询寻驯训讯逊压鸦鸭哑亚讶阉烟盐严颜阎艳厌砚彦谚验鸯杨扬疡阳痒养样瑶摇尧遥窑谣药爷页业叶医铱颐遗仪彝蚁艺亿忆义诣议谊译异绎荫阴银饮樱婴鹰应缨莹萤营荧蝇颖哟拥佣痈踊咏涌优忧邮铀犹游诱舆鱼渔娱与屿语吁御狱誉预驭鸳渊辕园员圆缘远愿约跃钥岳粤悦阅云郧匀陨运蕴酝晕韵杂灾载攒暂赞赃脏凿枣灶责择则泽贼赠扎札轧铡闸诈斋债毡盏斩辗崭栈战绽张涨帐账胀赵蛰辙锗这贞针侦诊镇阵挣睁狰帧郑证织职执纸挚掷帜质钟终种肿众诌轴皱昼骤猪诸诛烛瞩嘱贮铸筑驻专砖转赚桩庄装妆壮状锥赘坠缀谆浊兹资渍踪综总纵邹诅组钻致钟么为只凶准启板里雳余链泄';
var zh_t = '皚藹礙愛翺襖奧壩罷擺敗頒辦絆幫綁鎊謗剝飽寶報鮑輩貝鋇狽備憊繃筆畢斃閉邊編貶變辯辮鼈癟瀕濱賓擯餅撥缽鉑駁蔔補參蠶殘慚慘燦蒼艙倉滄廁側冊測層詫攙摻蟬饞讒纏鏟産闡顫場嘗長償腸廠暢鈔車徹塵陳襯撐稱懲誠騁癡遲馳恥齒熾沖蟲寵疇躊籌綢醜櫥廚鋤雛礎儲觸處傳瘡闖創錘純綽辭詞賜聰蔥囪從叢湊竄錯達帶貸擔單鄲撣膽憚誕彈當擋黨蕩檔搗島禱導盜燈鄧敵滌遞締點墊電澱釣調叠諜疊釘頂錠訂東動棟凍鬥犢獨讀賭鍍鍛斷緞兌隊對噸頓鈍奪鵝額訛惡餓兒爾餌貳發罰閥琺礬釩煩範販飯訪紡飛廢費紛墳奮憤糞豐楓鋒風瘋馮縫諷鳳膚輻撫輔賦複負訃婦縛該鈣蓋幹趕稈贛岡剛鋼綱崗臯鎬擱鴿閣鉻個給龔宮鞏貢鈎溝構購夠蠱顧剮關觀館慣貫廣規矽歸龜閨軌詭櫃貴劊輥滾鍋國過駭韓漢閡鶴賀橫轟鴻紅後壺護滬戶嘩華畫劃話懷壞歡環還緩換喚瘓煥渙黃謊揮輝毀賄穢會燴彙諱誨繪葷渾夥獲貨禍擊機積饑譏雞績緝極輯級擠幾薊劑濟計記際繼紀夾莢頰賈鉀價駕殲監堅箋間艱緘繭檢堿鹼揀撿簡儉減薦檻鑒踐賤見鍵艦劍餞漸濺澗漿蔣槳獎講醬膠澆驕嬌攪鉸矯僥腳餃繳絞轎較稭階節莖驚經頸靜鏡徑痙競淨糾廄舊駒舉據鋸懼劇鵑絹傑潔結誡屆緊錦僅謹進晉燼盡勁荊覺決訣絕鈞軍駿開凱顆殼課墾懇摳庫褲誇塊儈寬礦曠況虧巋窺饋潰擴闊蠟臘萊來賴藍欄攔籃闌蘭瀾讕攬覽懶纜爛濫撈勞澇樂鐳壘類淚籬離裏鯉禮麗厲勵礫曆瀝隸倆聯蓮連鐮憐漣簾斂臉鏈戀煉練糧涼兩輛諒療遼鐐獵臨鄰鱗凜賃齡鈴淩靈嶺領餾劉龍聾嚨籠壟攏隴樓婁摟簍蘆盧顱廬爐擄鹵虜魯賂祿錄陸驢呂鋁侶屢縷慮濾綠巒攣孿灤亂掄輪倫侖淪綸論蘿羅邏鑼籮騾駱絡媽瑪碼螞馬罵嗎買麥賣邁脈瞞饅蠻滿謾貓錨鉚貿麽黴沒鎂門悶們錳夢謎彌覓綿緬廟滅憫閩鳴銘謬謀畝鈉納難撓腦惱鬧餒膩攆撚釀鳥聶齧鑷鎳檸獰甯擰濘鈕紐膿濃農瘧諾歐鷗毆嘔漚盤龐國愛賠噴鵬騙飄頻貧蘋憑評潑頗撲鋪樸譜臍齊騎豈啓氣棄訖牽扡釺鉛遷簽謙錢鉗潛淺譴塹槍嗆牆薔強搶鍬橋喬僑翹竅竊欽親輕氫傾頃請慶瓊窮趨區軀驅齲顴權勸卻鵲讓饒擾繞熱韌認紉榮絨軟銳閏潤灑薩鰓賽傘喪騷掃澀殺紗篩曬閃陝贍繕傷賞燒紹賒攝懾設紳審嬸腎滲聲繩勝聖師獅濕詩屍時蝕實識駛勢釋飾視試壽獸樞輸書贖屬術樹豎數帥雙誰稅順說碩爍絲飼聳慫頌訟誦擻蘇訴肅雖綏歲孫損筍縮瑣鎖獺撻擡攤貪癱灘壇譚談歎湯燙濤縧騰謄銻題體屜條貼鐵廳聽烴銅統頭圖塗團頹蛻脫鴕馱駝橢窪襪彎灣頑萬網韋違圍爲濰維葦偉僞緯謂衛溫聞紋穩問甕撾蝸渦窩嗚鎢烏誣無蕪吳塢霧務誤錫犧襲習銑戲細蝦轄峽俠狹廈鍁鮮纖鹹賢銜閑顯險現獻縣餡羨憲線廂鑲鄉詳響項蕭銷曉嘯蠍協挾攜脅諧寫瀉謝鋅釁興洶鏽繡虛噓須許緒續軒懸選癬絢學勳詢尋馴訓訊遜壓鴉鴨啞亞訝閹煙鹽嚴顔閻豔厭硯彥諺驗鴦楊揚瘍陽癢養樣瑤搖堯遙窯謠藥爺頁業葉醫銥頤遺儀彜蟻藝億憶義詣議誼譯異繹蔭陰銀飲櫻嬰鷹應纓瑩螢營熒蠅穎喲擁傭癰踴詠湧優憂郵鈾猶遊誘輿魚漁娛與嶼語籲禦獄譽預馭鴛淵轅園員圓緣遠願約躍鑰嶽粵悅閱雲鄖勻隕運蘊醞暈韻雜災載攢暫贊贓髒鑿棗竈責擇則澤賊贈紮劄軋鍘閘詐齋債氈盞斬輾嶄棧戰綻張漲帳賬脹趙蟄轍鍺這貞針偵診鎮陣掙睜猙幀鄭證織職執紙摯擲幟質鍾終種腫衆謅軸皺晝驟豬諸誅燭矚囑貯鑄築駐專磚轉賺樁莊裝妝壯狀錐贅墜綴諄濁茲資漬蹤綜總縱鄒詛組鑽緻鐘麼為隻兇準啟闆裡靂餘鍊洩';
String.prototype.tran = function() {
var s1,s2;
if (zh_choose == 't') {
   s1 = zh_s;
   s2 = zh_t;
}else if(zh_choose == 's') {
   s1 = zh_t;
   s2 = zh_s;
}else {
   return this;
}
var a = '';
var l = this.length;
for(var i=0;i<this.length;i++){
        var c = this.charAt(i);
        var p = s1.indexOf(c)
        a += p < 0 ? c : s2.charAt(p);
    }
return a;
}
function setCookie(name, value) {
var argv = setCookie.arguments;
var argc = setCookie.arguments.length;
var expires = (argc > 2) ? argv[2] : null;
if (expires != null) {
   var LargeExpDate = new Date ();
   LargeExpDate.setTime(LargeExpDate.getTime() + (expires*1000*3600*24));
}
document.cookie = name + "=" + escape (value)+((expires == null) ? "" : ("; expires=" +LargeExpDate.toGMTString()));
}
function getCookie(Name) {
var search = Name + "="
if (document.cookie.length > 0) {
   offset = document.cookie.indexOf(search);
   if(offset != -1) {
    offset += search.length;
    end = document.cookie.indexOf(";", offset);
    if(end == -1) end = document.cookie.length;
    return unescape(document.cookie.substring(offset, end));
   }else {
    return '';
   }
}
}


function zh_tranBody(obj) { 
var o = (typeof(obj) == "object") ? obj.childNodes : document.body.childNodes;
for (var i = 0; i < o.length; i++) {
   var c = o.item(i);
   if ('||BR|HR|TEXTAREA|SCRIPT|'.indexOf("|"+c.tagName+"|") > 0) continue;
   if (c.className == zh_class) {
    if (c.id == zh_class + '_' + zh_choose) {
     c.setAttribute('style', zh_style_active);
     c.style.cssText = zh_style_active;
    }else {
     c.setAttribute('style', zh_style_inactive);
     c.style.cssText = zh_style_inactive;
    }
    continue;   
   }
   if (c.title != '' && c.title != null) c.title = c.title.tran();
   if (c.alt != '' && c.alt != null) c.alt = c.alt.tran();
   if (c.tagName == "INPUT" && c.value != '' && c.type != 'text' && c.type != 'hidden' && c.type != 'password') c.value = c.value.tran();
   if (c.nodeType == 3) {
    c.data = c.data.tran();  
   }else{
    zh_tranBody(c);
   }
}
}

function zh_tran(go) {
if (go) zh_choose = go;
setCookie('zh_choose', zh_choose, zh_expires);
if (go == 'n') {
   window.location.reload();
}else {
	if(go=="s"){
		  $("#link_language_s").hide();
		  $("#link_language_t").show();
		}else{
			$("#link_language_t").hide();
			$("#link_language_s").show();
		  
		}
   zh_tranBody();
}
}

function zh_getLang() {
if (getCookie('zh_choose')) {
   zh_choose = getCookie('zh_choose');
   return true;
} 
if (!zh_autoLang_t && !zh_autoLang_s) return false;
if (getCookie('zh_autoLang_checked')) return false;
if (navigator.language) {
   zh_browserLang = navigator.language;
}else if (navigator.browserLanguage) {
   zh_browserLang = navigator.browserLanguage;
}
if (zh_autoLang_t && zh_langReg_t.test(zh_browserLang)) {
   zh_choose = 't';
}else if (zh_autoLang_s && zh_langReg_s.test(zh_browserLang)) {
   zh_choose = 's';
}
zh_autoLang_checked = 1;
setCookie('zh_choose', zh_choose, zh_expires);
if (zh_choose == zh_default) return false;
return true;
}


function zh_init() {
zh_getLang(); 
c = document.getElementById(zh_class + '_' + zh_choose);
if (zh_choose != zh_default) {
   if (window.onload) {
    window.onload_before_zh_init = window.onload;
    window.onload = function() {
     zh_tran(zh_choose);
     if (getCookie('zh_autoLang_check')) {alert(zh_autoLang_msg);};
     window.onload_before_zh_init();
     };
   }else {
    window.onload = function() {
     zh_tran(zh_choose);
     if (getCookie('zh_autoLang_check')) {alert(zh_autoLang_msg);};
     };
   }
}

}
zh_init();
	
$(function(){
	//设置站内所有细缆及外部链接均在新窗口中打开（分页相关的链接在本页打开） add by zzl 2012年12月26日 15:52:17
    /*$("a[href$='.html'],a[href^='http://']").attr("target","_blank");		   
	$("#div_page a").attr("target","_self");*/
	//设置简繁体
	if (getCookie('zh_choose')) {
        var zh_choose = getCookie('zh_choose');	   
	    if(zh_choose!=null&&zh_choose=="s"){
		  $("#link_language_s").hide();
		  $("#link_language_t").show();
		}else{
			$("#link_language_s").hide();
			$("#link_language_t").show();
		  
		}
	}	   
		   
		   
  //设置细缆字号大小单击事件 by zzl 2012年12月23日 21:35:01
  $("#fontBig").click(function(){
    $("#div_content,#div_content .TRS_Editor *").css("font-size","24px").css("line-height","165%");
  })
  $("#fontNormal").click(function(){
    $("#div_content,#div_content .TRS_Editor *").css("font-size","18px").css("line-height","165%");
  })
  $("#fontSmall").click(function(){
    $("#div_content,#div_content .TRS_Editor *").css("font-size","12px").css("line-height","165%");
  }) 
  
})

