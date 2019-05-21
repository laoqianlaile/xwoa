
 String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) { 
　 if (!RegExp.prototype.isPrototypeOf(reallyDo)) { 
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith); 
	} else { 
		return this.replace(reallyDo, replaceWith); 
	} 
}
;(function($){
    $.fn.VMiddleImg = function(options) {
        var defaults={
            "width":null,
            "height":null
        };
        var opts = $.extend({},defaults,options);
        return $(this).each(function() {
            var $this = $(this);
            var objHeight = $this.height(); //图片高度
            var objWidth = $this.width(); //图片宽度
            var parentHeight = opts.height||$this.parent().height(); //图片父容器高度
            var parentWidth = opts.width||$this.parent().width(); //图片父容器宽度
            var ratio = objHeight / objWidth;
            if (objHeight > parentHeight && objWidth > parentWidth) {
                if (objHeight > objWidth) { //赋值宽高
                    $this.width(parentWidth);
                    $this.height(parentWidth * ratio);
                } else {
                    $this.height(parentHeight);
                    $this.width(parentHeight / ratio);
                }
                objHeight = $this.height(); //重新获取宽高
                objWidth = $this.width();
                if (objHeight > objWidth) {
                    $this.css("top", (parentHeight - objHeight) / 2);
                    //定义top属性
                } else {
                    //定义left属性
                    $this.css("left", (parentWidth - objWidth) / 2);
                }
            }
            else {
                if (objWidth > parentWidth) {
                    $this.css("left", (parentWidth - objWidth) / 2);
                }
                $this.css("top", (parentHeight - objHeight) / 2);
            }
        });
    };
})(jQuery);
$(function(){
    /*css修复*/
	$('#wcb-nav li:eq(0)').css({'background':'none'});
	
	/*$('#newsTab li a:not(.current)').bind('mouseover',function(){
		$(this).css({'color':'#B00007'});			
	});*/
	
	$("#picScroll2 ul li img").VMiddleImg({"width":1080,"height":90});
	/*液态布局*/
	$('body').rePaint({
		minW:980,
		maxW:1170,
		setObj:[
	{obj:"#smooth-box",per:1,base:980},
	{obj:"#menu li",per:0.1,base:108},
	{obj:"#menuSub",per:1,base:980},
	{obj:"#hlgk",per:1,base:790},
	{obj:".IntroInfo-rg",per:1,base:550},
	{obj:"#zwgk",per:1,base:635},
	{obj:".ChangeList",per:1,base:380},
	{obj:".nav_div_bsRight",per:1,base:790},
	{obj:"#ggfw",per:1,base:635},
	{obj:".hnjj .FWDiv",per:1,base:615},
	{obj:"#zmhd",per:1,base:782},
	{obj:"#hdwh",per:1,base:775},
	{obj:"#hdwh .EduList",per:1,base:485},
	{obj:"#hnly",per:1,base:782},
	{obj:".SubMenuNox",per:1,base:980},
	{obj:".SubMenuContent",per:1,base:978},
	{obj:"#sitetopTips",per:1,base:980},
	{obj:"#header",per:1,base:980},
	{obj:"#header",per:1,base:980},
	{obj:"#site-menu",per:1,base:980},
	{obj:"#first",per:1,base:980},
	{obj:"#col-2",per:1,base:370},
	{obj:"#two",per:1,base:980},
	{obj:"#col-4-r",per:1,base:390},
	{obj:"#four",per:1,base:980},
	{obj:"#col-5",per:0,base:265},
	{obj:"#col-6",per:0.5,base:370},
	{obj:"#col-7",per:0.5,base:290},
	{obj:"#five",per:1,base:980},
	{obj:"#col-8",per:1,base:310},
	{obj:"#col-9",per:1,base:310},
	{obj:"#col-10",per:1,base:310},
	{obj:"#six",per:1,base:980},
	{obj:"#col-11",per:0.25,base:228},
	{obj:"#col-12",per:0.25,base:228},
	{obj:"#col-13",per:0.25,base:228},
	{obj:"#col-14",per:0.25,base:228},
	{obj:"#seven",per:1,base:980},
	{obj:"#picAd2",per:1,base:890},
	{obj:"#picScroll2",per:1,base:890},
	{obj:"#three",per:1,base:980},
	{obj:"#contentShow",per:1,base:980},
	{obj:"#contentShowLf",per:1,base:668},
	{obj:"#leaderList-rg",per:1,base:758},
	{obj:"#leader-active",per:1,base:450},
	{obj:"#publicService",per:1,base:456},
	{obj:"#publicService li",per:0.33,base:140},
	{obj:"#iframe_gzwd",per:0.5,base:355},
	{obj:"#zpCt",per:1,base:470},
	{obj:"#publicPosition",per:1,base:980},
	{obj:"#zwgkPage",per:1,base:980},
	{obj:"#seven",per:1,base:980}, 
	{obj:"#leader-say",per:1,base:450},
	{obj:"#hdwh .EduDesc",per:0.5,base:380},
	{obj:"#NavfoldBox .nav-flod-show",per:0.5,base:175},
	{obj:"#ggfw_MenuNav_fwxxcx",per:1,base:340},
	{obj:".msub-zmhd-lf",per:1,base:470},
	{obj:".ul_qybsList li",per:0.25,base:74},
	{obj:".ul_grbsList li",per:0.25,base:74}
		]
	});
	//要闻领导活动等tab切换
	$('.module').noteTabs({
	 	tabNav:'#newsTab-3 li',
		tabShow:'#newsShow-3 .infor-list',//news-list-box
		current:'current',
		methods:'tMore',
		moreBox:'#newsTab-3-box',
		more:'.more'
	 });
	//通知法规tab切换
	$('.module').noteTabs({
	 	tabNav:'#newsTab-1 li',
		tabShow:'#newsShow-1 .infor-list',
		current:'current',
		methods:'tMore',
		moreBox:'#newsTab-1-box',
		more:'.more'
	 });
	//人事考录tab切换
	 $('.module').noteTabs({
	 	tabNav:'#newsTab-2 li',
		tabShow:'#newsShow-2 .infor-list',
		current:'current',
		methods:'tMore',
		moreBox:'#newsTab-2-box',
		more:'.more'
	 });
	 //经济文化旅游tab切换
	 $('.mode').noteTabs({
	 	tabNav:'#colTab li',
		tabShow:'#colTabShow .colShow',
		current:'current',
		methods:'tMore',
		moreBox:'#colTab-box',
		more:'.txt-more'
	 });
	 //个人企业办事
	 $('.paddingRg').noteTabs({
	 	tabNav:'#workTitNav-0 li',
		tabShow:'#workTitShow-0 .wc-cont',
		current:'current',
		methods:'tLi'
	 });
	 //办事办件查询
  	$('.paddingRg').noteTabs({
	 	tabNav:'#workTitNav-1 li',
		tabShow:'#workTitShow-1 .wcb-box',
		current:'current',
		methods:'tLi'
	 });
	//文字滚动
	function textScroll(id){
		var $this = $(id);
		var scrollTimer;
		$this.hover(function(){
			clearInterval(scrollTimer);
		},function(){
			scrollTimer = setInterval(function(){
			scrollNews( $this );
		}, 3000 );
			}).trigger("mouseleave");
	}
	textScroll('#picScroll');
	//专题图片如果超过2张则自动滚动 by zzl 2013年8月8日12:14:39
	if($("#picScroll2 ul li img").size()>1){
		textScroll('#picScroll2');
	}
	//底部统计
	function toggleShow(nav,show){
		$(nav).toggle(
			function(){
				$(this).css({'background-image':'url(images/home_ctsubBottom_1.jpg)','color':'#fff'});
				$(show).slideDown();
			},function(){
				$(this).css({'background-image':'url(images/home_ctsubBottom_0.jpg)','color':'#B00007'});
				$(show).slideUp();
			}
		);
	};
	toggleShow('#srContNav a','#srContShow');
	
	//the bottom is added by zzl 2012年11月19日13:57:14
	$(".div_grbs a:nth-child(5n)").addClass("aNobg");//个人办事列表最右边的办事项去掉边框
	$(".div_qybs a:nth-child(5n)").addClass("aNobg");//企业办事列表最右边的办事项去掉边框
});
//文字滚动 核心
function scrollNews(obj){
	var $self = obj.find("ul:first");
	var lineHeight = $self.find("li:first").height(); //获取行高
	$self.animate({ "marginTop" : -lineHeight +"px" }, 800 , function(){
	$self.css({marginTop:0}).find("li:first").appendTo($self); //appendTo能直接移动元素
	})
};


$(function(){
	//下拉菜单加载后执行方法，处理旅游切换标签页
	foldSub("#NavfoldBox .nav-fold-list dd");
	$("#NavfoldBox .nav-fold-list dd").eq(0).trigger('mouseover');	
  //输入框获取焦点事件
	$('#txt_searchWord').focusin(function(){
		if($('#txt_searchWord').val()=="请输入关键词"){
			$('#txt_searchWord').val("");
			$('#txt_searchWord').css('color','#000');
		}
	});
	//输入框失去焦点事件
	$('#txt_searchWord').focusout(function(){
		if($('#txt_searchWord').val()==""){
			$('#txt_searchWord').val("请输入关键词");
			$('#txt_searchWord').css('color','gray');
		}
	});
	//检索表单提交监测
	$("#homeSearch").submit(function(){
	  var searchWord = document.getElementById("txt_searchWord").value;
	  if(searchWord=="请输入关键词"||searchWord==""){
		document.getElementById("txt_searchWord").value="请输入关键词";
	    document.getElementById("txt_searchWord").select();
		return false;
	  }
	  document.getElementById("txt_searchWordHide").value=searchWord.replaceAll(/\ /g,"*",false).replaceAll(/\u3000/g,"*");
	  return true;
    })
})