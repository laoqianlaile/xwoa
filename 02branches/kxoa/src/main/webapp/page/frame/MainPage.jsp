<!DOCTYPE html>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	response.addHeader("P3P", "CP=CAO PSA OUR");
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<c:out value="${cp:MAPVALUE('SYSPARAM','SysName')}" />
</title>
<link href="${pageContext.request.contextPath}/themes/css/am.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/blue/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/scripts/inputZtree/zTreeStyle.css" type="text/css">
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<%-- <script	src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js" type="text/javascript"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/inputZtree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/inputZtree/inputZtree.js"></script>
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/css/core.css"	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/css/core-custom.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/uploadify/css/uploadify.css"	rel="stylesheet" type="text/css" />



<!--若要使用原来的dashboard.jsp，则将引入的css文件改成dashboard.css  -->
<link href="${pageContext.request.contextPath}/page/app/dashboardV2.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/css/smart_wizard/smart_wizard.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/scripts/artDialog4.1.7/skins/default.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js" type="text/javascript"></script>
<!--[if IE]> <link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" /> <![endif]-->

<script	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/jquery.nicescroll.js"	type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/scripts/speedup.js"	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/jquery.validate.min.js"	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/jquery.cookie.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/jquery.bgiframe.js"	type="text/javascript"></script>
<!-- <script src="${pageContext.request.contextPath}/xheditor/xheditor-1.1.9-zh-cn.min.js" type="text/javascript"></script> -->
<script	src="${pageContext.request.contextPath}/uploadify/scripts/swfobject.js"	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/coolMenu.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/core.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/util.date.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/validate.method.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/regional.zh.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/barDrag.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/drag.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/tree.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/centitui/ui.js"	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/theme.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/switchEnv.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/alertMsg.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/contextmenu.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/navTab.js"	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/centitui/tab.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/resize.js"	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/dialog.js"	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/dialogDrag.js"	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/cssTable.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/stable.js"	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/taskBar.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/ajax.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/pagination.js"	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/database.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/effects.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/panel.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/checkbox.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/history.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/combox.js"	type="text/javascript"></script>
<!-- 上传文件的js -->
<script	src="${pageContext.request.contextPath}/scripts/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/centitUntil.js"	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/highcharts.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centit.charts.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/smart_wizard/jquery.smartWizard-2.0.js"	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/jquery.json-2.3.min.js"	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/jquery-ui/jquery-ui-1.8.19.custom.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/datepicker.js"	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centitui/accordion.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/centit.history.js" type="text/javascript"></script>
<script src="${contextPath }/scripts/Mztreeview1.0/MzTreeView10.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/scripts/jquery/jquery.treetable/jquery.treetable.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/moveTip.js"	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/common.js" type="text/javascript"></script>
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.show{display:block!important}
</style>
<script type="text/javascript">



function openNewWindow(winUrl,winName,winProps){
	if(winProps =='' || winProps == null){
		winProps = 'height='+(window.screen.availHeight-50) +',width='+window.screen.availWidth
		+',directories=false,location=false,top=0,left=0,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
	}
	window.open(winUrl, winName, winProps);
}
$(function(){
	if(top.document!=document&&top.document.getElementById("themeList")){
	   $(top.document.getElementById("themeList")).theme({themeBase:"${pageContext.request.contextPath}/themes"});			
	}
});
	$(function() {
		CentitUI.init("${pageContext.request.contextPath}/page/frame/centitui.frag.xml", {
			loginUrl : "${pageContext.request.contextPath}/logindialog.jsp",
			loginTitle : "登录", // 弹出登录对话框
// 			loginUrl:"login.html",	// 跳到登录页面
			statusCode : {
				ok : 200,
				error : 300,
				timeout : 301
			}, //【可选】
			pageInfo : {
				pageNum : "pageNum",
				numPerPage : "numPerPage",
				orderField : "orderField",
				orderDirection : "orderDirection"
			}, //【可选】
			debug : false, // 调试模式 【true|false】
			callback : function() {
				initEnv();
				menuDisplay(jQuery.parseJSON('${OA_MENUS}'), '${pageContext.request.contextPath}', '${superFunctionId}');
				/* $(".menuHead").click(function(){
					var nodeId = $(this).attr("setId");
					menuDisplay(jQuery.parseJSON('${OA_MENUS}'),'${pageContext.request.contextPath}',nodeId);
					initUI();
				}); */
				setTimeout(function() {
					// $("#firstPage1").click();
					var url = $("#firstPage1").attr('href');
					navTab.openTab("main", url, {
						title : "我的首页",
						fresh : false,
						external : false
					});
					$("#sidebar").hide();
					/*
					$("#container").css({
						left : "5px"
					});
					*/
					$("#container").removeAttr("style");
				}, 200);
				CentitUI.ui.sbar = false;
				/*
				$("#themeList").theme({
					themeBase : "${pageContext.request.contextPath}/themes"
				}); // themeBase 相对于dashboard页面的主题base路径
				*/
			}
		});
		var date = new Date();
		var str = "今天是：" + date.getFullYear() + "." + (parseInt(date.getMonth()) + 1) + "." + date.getDate() + "&nbsp;&nbsp;&nbsp;星期" + '日一二三四五六'.charAt(new Date().getDay());
// 		document.getElementById("timeBar").innerHTML = str;
	});
	var menuFlag = {
		geren : 0,
		nbshiquan : 0,
		gongwen : 0,
		bangong : 0,
		richang : 0,
		guanli : 0
	};
	function changeClass(obj) {
		$("#layout").removeClass("improveStyle");
		var _this = $(obj);
		if (_this.hasClass('select') && obj.title != "首页")
			return false;
		else {
			$(".tabBar li .select").removeClass('select');
			$(".tabBar li").removeClass('select');
			_this.addClass('select');
			_this.parent('li').addClass('select');
			
			navTab.closeAllTab();
			if (obj.title == "首页") {
				$("#layout").addClass("improveStyle");
// 					"background-image" : "url('../themes/default/improve/bigNav-1.png')"
				CentitUI.ui.sbar = false;
				navTab._switchTab(0);
				$("#firstPage1").click();
				$("#sidebar:visible,#sidebar_s:visible").hide();
				$("#container").removeAttr("style");
				var iContentH = $(window).height() - $("#header").height() - 34;
				$("#container .tabsPageContent").height(iContentH + 34);
			}else if (obj.title == "个人待办") { // 个人办公首页
				showSelectedMenu('YGJGGRBG','GRBGGRDB','GRBGDBSX','个人办公');
			}else if (obj.title == "签报办理") { // 签报办理
				showSelectedMenu('YGJGNBSX','RCBGQBGL','QBDB','事项办理');
			}else if (obj.title == "阅办文待办") { // 公文流转首页
				showSelectedMenu('YGJGGWLZ','GWLZSWGL','SWGLSWDB','公文流转');
			}else if (obj.title == "日常办公首页") {
				showSelectedMenu('YGJGRCBG','GGZY','NEWS_GGZY','日常办公');
			}else if (obj.title == "后台管理") {
				showSelectedMenu('Zhgl','ORGMAG','USERMAG','后台管理');
			} else if (obj.title == "文件柜") {
				showSelectedMenu('ZJC','YGJGWJG','WJGWJSJX','文件柜');
			} 
		}
	};
	function addNavOn(self) {
		$(self).addClass("on");
	}
	function removeNavOn(self) {
		$(self).removeClass("on");
	}
	function hideMenu() {
		$("#ul_YGJG>li").each(function() {
			$(this).hide();
			$(this).find('ul>li>ul').hide();
		});
	}
	function menuShow() {
		CentitUI.ui.sbar = true;
		if (!$("#sidebar").is(":visible")) {
			$("#sidebar").show();
			$("#sidebar").css({
				left : "11px"
			});
			if ($("#sidebar_s").is(":visible"))
				$("div.toggleCollapse").click();
			else {
				$("#container").css({
					left : "193px",
					width : $("#container").width() - 198
				});
			}
		}
		var iContentH = $(window).height() - $("#header").height() - 34;
		$("#container .tabsPageContent").height(iContentH + 8);
	}
	
	
   // rtx 登录自动加载链接
	$(function() {
		<c:if test="${not empty url }">
		 setTimeout(function(){
			$("#external_GRBGGRRCAP").click();
			navTab.openTab('test', '${pageContext.request.contextPath}/${url}', {external: true, title:"${title}"});
		},1000);
		</c:if>
	    
		getUserCountOnline();
		getUserCountOnline1();
		setInterval("getUserCountOnline()",60000);
		var col1=setInterval(getUserCountOnline1,60000);
		$("#message").mouseover(function(){//停止定时器实现暂停的效果
			clearInterval(col1);
		});
		$("#message").mouseout(function(){      //开启定时器，实现重启的效果
			col1=setInterval(getUserCountOnline1,60000);    
 		});
	  });
   
   function getUserCountOnline(){
	   $.ajax({ //页面setTimeout或者setinterval向后台发送请求，请务必将参数加上去：acType='auto'
		   url:"${pageContext.request.contextPath}/sys/accessLog!getUserCountOnline.do?acType=auto",
		   type:"post",
		   dataType:"json",
		   success:function(data){
			   $("#userCountOnline").html(data.count); 
			   $("#userCountOnlineDash").html(data.count);
			   $("#userCountOnlineMainPage").html(data.count); 
		   }
	   });
   }
   function getUserCountOnline1(){
	   messagenum();
	   tanchu();
   }
   
   
   /**********************************************关于屏保****************************************************************/
   /**
   *锁屏
   */
   function lockScreen(auto){
	   var requireUrl = "";
	   if(auto){
		   requireUrl = "${pageContext.request.contextPath}/sys/mainFrame!lockScreen.do?acType='auto'";
	   }else{
		   requireUrl = "${pageContext.request.contextPath}/sys/mainFrame!lockScreen.do";
	   }
	   $.ajax({ 
		   url:requireUrl,
		   type:"post",
		   dataType:"json",
		   success:function(data){
			   if(data){
				   setupScreensaver();   
			   }else{
				   alert("锁屏失败");
			   }
			   
		   }
	   });
   }
   
   /**
   * 开启屏保
   */
   function setupScreensaver(){
	   clearInterval(intervalProcessAutoLockScreen);
	   var screensaver = $("#screensaver");
	   screensaver.find("#userPassword").val("");
	   screensaver.height($(window).height());
	   screensaver.show();
   }
   
   /**
   *关闭屏保
   */
   function closeScreensaver(){
	   $("#screensaver").hide();
	   startLockSceenTimer();
   }
   
   /**
   *锁屏定时器句柄
   */
   var intervalProcessAutoLockScreen;
   
   /**
   * 定时查看是否需要开启屏保,每分钟执行一次
   */
   function startLockSceenTimer(){
	   intervalProcessAutoLockScreen = setInterval(function(){
		   try{
			   var lastAccessTime = getCookie("__lastAccessTime");
			   if(lastAccessTime){
				   lastAccessTime = parseInt(lastAccessTime);
				   var now = new Date().getTime();
				   //限制最大空闲时间为30分钟
				   var maxIdle = 30*60*1000;
				   if(now-lastAccessTime>maxIdle){
					   lockScreen(true);
				   }
			   }
		   }catch(e){
		   }
	   },60000);
   }
   function getCookie(name){  
	    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));  
	    if(arr != null){  
	     return unescape(arr[2]);   
	    }else{  
	     return null;  
	    }  
	}   
   /**
   * 屏保页登录
   */
   function loginInScreensaver(){
	   var pwd = $("#userPassword").val();
	   if(pwd==''){
		   alert("请输入密码");
		   return;
	   }
	   $.ajax({
		   type:"post",
		   url:"${ctx}/j_spring_security_check",
		   dataType:"json",
		   data:{
			   "j_username":$("#userName").val(),
			   "j_password":$("#userPassword").val(),
			   "j_checkcode":"nocheckcode"
		   },
		   success:function(data){
			   if(!data){
				   alert("密码错误");
			   }else{
				   closeScreensaver();
			   }
		   }
	   });
   }
$(function(){
	 //开启屏保计时器
	   startLockSceenTimer();
	   //页面加载时根据用户状态加载屏保装置，防止用户刷新
	   var currUserState = "${session.SPRING_SECURITY_CONTEXT.authentication.principal.userState}";
	   if(currUserState=="2"){
		   setupScreensaver();
	   }
	   
	   //整个dom禁用F5和右键，主要是为了避免刷新可能触发浏览器关闭
	  $(document).keydown(function(e){
		  e = window.event || e;
		  var keycode = e.keyCode || e.which;
		  var obj = e.target || e.srcElement;//获取事件源 
		  var t = obj.type || obj.getAttribute('type');//获取事件源类型 
		  
		  if((keycode == 116)
				  || (e.ctrlKey && keycode==82)//ctrl+R也是刷新
				  ){
			  if(window.event){// ie
				  try{e.keyCode = 0;}catch(e){}
				  e.returnValue = false;
			  }else{// ff
				  e.preventDefault();
			  }
		  }
		  if((e.altKey && (keycode == 37 || keycode == 39))//alt+方向键,
				  || (e.ctrlKey && (keycode==78 || keycode==87))//ctrl+n新建窗口,ctrl+w关闭选项卡
			      || (e.shiftKey && keycode==121)// shift+F10 弹出右键菜单	 
		  ){ 
			  if(window.event){// ie
				  e.returnValue = false;
			  }else{
				  e.preventDefault();
			  }
		  }
	  }).bind("contextmenu",function(e){//禁止右键
		   return false;
	   });
	   
	   //屏保禁用按键，以及右键
     $("#screensaver").keydown(function(e){
		  e = window.event || e;
		  var keycode = e.keyCode || e.which;
		  var obj = e.target || e.srcElement;//获取事件源 
		  var t = obj.type || obj.getAttribute('type');//获取事件源类型 
		  
		 
		  if((keycode == 8 && t!="password" && t!="text") //回退
				  || (keycode >= 112 && keycode <= 123) //F1-F12
				  || (e.ctrlKey && keycode==82)//ctrl+R也是刷新
				  ){
			  if(window.event){// ie
				  try{e.keyCode = 0;}catch(e){}
				  e.returnValue = false;
			  }else{// ff
				  e.preventDefault();
			  }
		  }
	   }); 
	   
	   //登录操作
	   $("#userPassword").keydown(function(e){
		   e = window.event || e;
			  var keycode = e.keyCode || e.which;
		   if(keycode==13){
			   loginInScreensaver();
		   }
	   });
	   
});
//关于关闭浏览器处理，注意刷新页面有可能触发到浏览器关闭事件，这里没有很好的避免
var canExist = false;
 window.onbeforeunload = function() {
		var n = window.event.screenX - window.screenLeft;   
	    var b = n > document.documentElement.scrollWidth-20;   
	    if(b && window.event.clientY < 0 || window.event.altKey)   
	    {   
	    	 canExist = true;
	    	 return "警告：关闭该窗口将会退出本系统！";
	    }
	    return "";
}
 
window.onunload = function(){
	if(canExist){
		//同步ajax退出，避免关闭浏览器后又弹出刷新的提示
		$.ajax({type:"get",url:"${ctx}/j_spring_security_logout",async:false,dataType:"html"});
	}
}
$(function(){
	$(".zd").click(function(){
		$(this).siblings("a").toggleClass("hide");
		$(this).toggleClass("zdarrow");
		messagenum();
		 $("#message_div").hide(); 
	})
});
 function tab(th,obj){
	 $(th).siblings('a').removeClass('select');
	 $(th).addClass('select');
	$("#message_div").css("display","block");
	if(obj=="tx"){
		$("#message_title").html("未读提醒");
		$("#messagediv_yj").hide();
		$("#messagediv_tz").hide();
		$("#messagediv_db").hide();
		$("#messagediv_tx").show();
		tx_json();
	}else if(obj=="yj"){
		$("#message_title").html("未读邮件");
		$("#messagediv_tz").hide();
		$("#messagediv_db").hide();
		$("#messagediv_tx").hide();
		$("#messagediv_yj").show();
		yj_json();
	}else if(obj=="tz"){
		$("#message_title").html("未读通知");
		$("#messagediv_db").hide();
		$("#messagediv_tx").hide();
		$("#messagediv_yj").hide();
		$("#messagediv_tz").show();
		tz_json();
	}else if(obj=="db"){
		$("#message_title").html("待我办理");
		$("#messagediv_tx").hide();
		$("#messagediv_yj").hide();
		$("#messagediv_tz").hide();
		$("#messagediv_db").show();
		db_json();
	}else if(obj=="tsw"){
		$("#message_title").html("未读厅阅办文");
		sw_json("");
	}else if(obj=="cssw"){
		$("#message_title").html("未读处室阅办文");
		sw_json("bmsw");
	}
}
 function shouq(obj){
	 if(obj=="tx"){
		 $("#message_div").css("display","none");
	 }
 }
function tanchu(){
	$("#zd_a").siblings("a").toggleClass("hide");
	$("#zd_a").toggleClass("zdarrow");
	$("#message_div").hide();
}
function messagenum(){
	var times=timestamp();
	$.ajax({
		url:"<%=request.getContextPath()%>/app/dashboard!getMessageNum.do",
		type:"POST",
		data:{"timestamp":times},
		dataType:"json",
		success:function(date){
			if(date!=null){
				 $.each(date,function(key,value){
					 if(key==1){
						 if(value!=0){
						 	$("#yj_a").html("未读邮件<span>"+value+"</span>");
						 }else{
							 $("#yj_a").html("未读邮件");
						 }
					 }else if(key==5){
						 if(value!=0){
						 	$("#tx_a").html("提醒<span>"+value+"</span>");
						 }else{
							 $("#tx_a").html("提醒");
						 }
					 }else if(key==4){
						 if(value!=0){
						 	$("#db_a").html("待办<span>"+value+"</span>");
						 }else{
							 $("#db_a").html("待办");
						 }
					 }else if(key==6){
						 if(value!=0){
						 	$("#tz_a").html("通知<span>"+value+"</span>");
						 }else{
							 $("#tz_a").html("通知"); 
						 }
					 }/* else if(key==7&&value!=0){
						 $("#tsw_a").html("厅收文<span>"+value+"</span>");
					 }else if(key==8&&value!=0){
						 $("#cssw_a").html("处室收文<span>"+value+"</span>");
					 } */
				 });
			}
		}
	});
}
//提醒
function tx_json(){
	$("#messagediv_tx").html("");
	$.ajax({
		url:"<%=request.getContextPath()%>/app/dashboard!oaRemindInformation.do",
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date!=null){
			 $.each(date,function(i,o){
				var item;
				item="<tr><td class='itemTitle'><a id='tx_m"+i+"' title='"+o.title+"' href='javaScript:void(0);'>【"+o.reType+"】"+o.title+"</a></td><td style='text-align:right;width:100px;padding-right:15px'> "+omitTime(o.begtime)+"</td></tr>";
				if(i<5){
				$("#messagediv_tx").append(item);
				var id="tx_m"+i;
				$('#' + id).click(function(){
					tanchu();
					openMenu(this,'fqtx1',"<%=request.getContextPath()%>/oa/oaRemindInformation!view.do?no="+o.no+"&dashboard=TX");
					return false;
				});
				}
			 });
		}}
	});
}
//未读邮件
function yj_json(){
	$("#messagediv_yj").html("");
	$.ajax({
		url:"<%=request.getContextPath()%>/app/dashboard!syyj_new.do",
		type:"post",
		data:{"mailtype":"I"},
		dataType:"json",
		success:function(date){
			var list=date;
			 $.each(list,function(i,o){
				 if(o.msgstate=="U"){
				 var item;
					item="<tr>";
					item+="<td class='itemTitle'  >"+
					"<a ";
					item+=" id='sjx_m"+i+"' ";
					item+="target='navTab' external='true'"+
					"title='收件箱' >" + 
							"<span title='"+o.sender+"'>";
					item+="["+omitText2(o.sender)+"]</span><span title='"+o.msgtitle+"'>";
					item+=o.msgtitle+"</span>"+
					"</a>"+"</td>";
					item+="<td  style='text-align:right;width:50px;padding-right:15px'>";
					item+=changeTime(o.senddate)+
					"</td>"+
				"</tr>";
				if(i<5){
					$("#messagediv_yj").append(item);
					var id="sjx_m"+i;
    				$('#' + id).click(function(){
    					tanchu();
    					openMenu(this, 'sjx', "<%=request.getContextPath()%>/oa/innermsg!view.do?msgcode=" +
    							o.msgcode+"&s_msgtype=P&s_mailtype=I&isShow=isShow");
    					return false;
    				});
				}
				 }
			 });
		}
	});
}
//通知
function tz_json(){
	$("#messagediv_tz").html("");
	$.ajax({
		url:"<%=request.getContextPath()%>/app/dashboard!toDayOaInformation.do",
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date!=null){
			 $.each(date,function(i,o){
				var item;
				item="<tr><td class='itemTitle'><a title='"+o.title+"' id='tz_m"+i+"' href='javaScript:void(0);' > " +
				o.title+"</a></td><td  style='text-align:right;width:50px;padding-right:15px'>"+
				changeTime(o.creatertime)+
				"</td></tr>";
				if(i<5){
				$("#messagediv_tz").append(item);
				var id="tz_m"+i;						
				$('#' + id).click(function(){
					tanchu();
					openMenu(this, 'tzgg', "<%=request.getContextPath()%>/oa/oaInformation!view.do?no="+o.no+"&flag=GGZY&show_type=F");							
					return false;
				});
				}
			 });
		}}
	});
}
//待办
function db_json(){
	$("#messagediv_db").html("");
	$.ajax({
		url:"<%=request.getContextPath()%>/app/dashboard!sydb_new.do",
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date!=null){
			 $.each(date,function(i,o){
				var item;
				item="<tr><td class='itemTitle'><a id='db_m"+i+"' title='"+o.transaffairname+"'  >" +
				
				"【"+o.itemtype+"】"+o.transaffairname+"</a></td></tr>";
				if(i<5){
				$("#messagediv_db").append(item);
				var id="db_m"+i;
				$('#' + id).click(function(){
					tanchu();
					openMenu(this, 'dwbl', '<%=request.getContextPath()%>' + o.nodeOptUrl );
					return false;
				});	
				}
			 });
		}}
	});
} 
//收文
function sw_json(bmsw){
	$("#messagediv").html("");
	$.ajax({
		url:"<%=request.getContextPath()%>/app/dashboard!wdsw.do",
		type:"POST",
		data:{"bmsw":bmsw},
		dataType:"json",
		success:function(date){
			
			 $.each(date,function(i,o){
				var item;
				item="<tr><td class='itemTitle'><a";
				if(bmsw="bmsw"){
				item+=" id='bmsw_m"+i;
				}else{
					item+=" id='dwsw_m"+i;
				}
				item+="'  href='javaScript:void(0);' >"+o.incomeDocTitle+"</a></td></tr>";
				if(i<5){
				$("#messagediv").append(item);
				if(bmsw=="bmsw"){
    				$("#bmsw").append(item);
    				var id="bmsw_m"+i;
    				$('#' + id).click(function(){
    					tanchu();
    					openMenu(this, 'bmsw',"<%=request.getContextPath()%>/dispatchdoc/incomeDoc!generalOptView.do?djId="+o.djId+"&nodeInstId=0&dashboard=BMSW");
    					return false;
    				});
    			}else{
    				$("#dwsw").append(item);
    				var id="dwsw_m"+i;
    				
    				$('#' + id).click(function(){
    					tanchu();
    					openMenu(this, 'dwsw',"<%=request.getContextPath()%>/dispatchdoc/incomeDoc!generalOptView.do?djId="+o.djId+"&nodeInstId=0&dashboard=DWSW");
    					return false;
    				});
    			}
				
				
				}
			 });
		}
	});
}
function omitText3(src){
	if(src.length>14){
		src=src.substring(0,14);
		src+="...";
	}
	return src; 
}

function omitTime(time){
	var s=time.substr(time.length-14,5);
	s+=" ";
	s+=time.substr(time.length-8,5);
	return s;
}


</script>
<style>
.hide{display: none!important}
.icon {
	display: inline-block !important;
}
.select{color: darkmagenta;}
#screensaver{z-index:99999;position: absolute;top:0;left:0;width:100%;display:none; background:url(${ctx}/images/saver_bg.jpg) center center no-repeat}
#screensaver .screensaver-form{width:203px;height:200px; text-align:center; position:absolute;left:50%;top:50%;margin-left:-100px;margin-top:-100px}
#screensaver .lock_btn{height: 30px; width: 30px;background: url(${ctx}/images/saver_form_btn.png);float: right;border:none !important;cursor: pointer;}
#screensaver .lock_password{height: 20px;line-height: 20px;float: left; margin-top: 3px;width: 165px;border: 1px solid #D2D2D2;}
.me table{table-layout: fixed}
.itemTitle a{
    display: block;
    width: 95%;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

</style>
</head>

<body scroll="no" style="overflow: auto; min-width: 1024px;"> 
	 <%-- <div id="message" class="me" >
	 	<div  class="message_div" id="message_div" style="display: none;width: 335px;border: solid 2px #eeeeee;margin-bottom: 5px">
	 	
	 		<div style="height: 30px; background-color: #EEEEEE;">
	 			<img  style="float:left;margin-top: 8px;margin-left: 10px;margin-right: 8px;" src="<%=request.getContextPath()%>/themes/default/improve/xxtx.png" />
	 			<span style="float:left;line-height:210%; text-align: left;" id="message_title">动态写入标题</span >
	 			<span class="div_zd" ><a  href="javaScript:void(0);" onclick="shouq('tx');$('.message>a').removeClass('select');return false;"><img src="<%=request.getContextPath()%>/themes/default/improve/zdarrow1.png" /></a></span>
	 		</div>
	 		<div  style="height: 160px">
	 		<table style="width: 100%;display: none;" id="messagediv_tx" >
	 		</table>
	 		<table style="width: 100%;display: none;" id="messagediv_db" >
	 		</table>
	 		<table style="width: 100%;display: none;" id="messagediv_tz" >
	 		</table>
	 		<table style="width: 100%;display: none;" id="messagediv_yj" >
	 		</table>
	 		</div>
	 	
	 	</div>
	 	<div class="message" id="message_a">
			<a id="tx_a" class="tx" onclick="tab(this,'tx')">提醒</a>
			<a id="db_a" class="db" onclick="tab(this,'db')" >待办</a>
			<a id="tz_a" class="tz" onclick="tab(this,'tz')" >通知</a>
<!-- 			<a id="tsw_a" class="sw" onclick="tab(this,'tsw')" >厅收文</a> -->
			<!-- <a id="cssw_a" class="sw" onclick="tab(this,'cssw')" >处室收文</a> -->
<!-- 			<a id="yj_a" class="yj" onclick="tab(this,'yj')" >未读邮件</a> -->
			 <a id="zd_a" class="zd" onclick="tab(this,'zd')" ></a> 
		</div>
		
	 </div>  --%>
	<div id="layout" class="improveStyle">
		<div id="header">
			<div class="headerNav">
				<ul class="tabBar">
					<li class="home select">	
						<a class="select" onmousedown="addNavOn(this);" onmouseup="removeNavOn(this);" onclick="changeClass(this)" title="首页" href="#">我的首页</a>
					</li>
					<c:if test="${ cp:HASOPTPOWER('YGJGGRBG') }">
						<li class="to">
							<%--                      <a onmousedown="addNavOn(this);" onmouseup="removeNavOn(this);" onclick="changeClass(this)" href="${pageContext.request.contextPath}/dispatchdoc/vuserTaskListOA!list.do?" title="个人办公首页" target="navTab" rel="external_GRBGDBSX" external="true"></a> --%>
							<a onmousedown="addNavOn(this);" onmouseup="removeNavOn(this);" onclick="changeClass(this)" href="${pageContext.request.contextPath}/dispatchdoc/vuserTaskListOA!list.do" title="个人待办" target="navTab" rel="external_GRBGDBSX" external="true" >个人办公</a>
						</li>
					</c:if>
					<c:if test="${cp:HASOPTPOWER('YGJGNBSX') }">
						<li class="three">
							<%--                      <a onmousedown="addNavOn(this);" onmouseup="removeNavOn(this);" onclick="changeClass(this)" href="${pageContext.request.contextPath}/powerbase/supPower!nbsqList.do?s_itemType=SQ" title="内部事权首页" target="navTab" rel="external_NBSXSXDJ" external="true"></a> --%>
							<a onmousedown="addNavOn(this);" onmouseup="removeNavOn(this);" onclick="changeClass(this)" href="${pageContext.request.contextPath}/dispatchdoc/vuserTaskListOA!list.do?s_itemtype=QB" title="签报办理" target="navTab" rel="external_RCBGQBGL" external="true">签报管理</a>
						</li>
					</c:if>
					<c:if test="${cp:HASOPTPOWER('YGJGGWLZ') }">
						<li class="four">
							<%--                      <a onmousedown="addNavOn(this);" onmouseup="removeNavOn(this);" onclick="changeClass(this)" href="${pageContext.request.contextPath}/dispatchdoc/dispatchDoc!list.do?show_type=T" title="公文流转首页" target="navTab" rel="external_FWGLFWDJ" external="true"></a> --%>
							<a onmousedown="addNavOn(this);" onmouseup="removeNavOn(this);" onclick="changeClass(this)" href="${pageContext.request.contextPath}/dispatchdoc/vuserTaskListOA!list.do?s_flowCode=000394&s_itemtype=SW" title="阅办文待办" target="navTab" rel="external_SWGLSWDB" external="true">公文流转</a>
						</li>
					</c:if>
					<c:if test="${cp:HASOPTPOWER('YGJGRCBG') }">
						<li class="five">
							<a onmousedown="addNavOn(this);" onmouseup="removeNavOn(this);" onclick="changeClass(this)" href="${pageContext.request.contextPath}/oa/oaInformation!mainlist.do?infoType=info&flag=GGZY" title="日常办公首页" target="navTab" rel="external_RCBGGGTZ" external="true">日常办公</a>
						</li>
					</c:if>
					<c:if test="${cp:HASOPTPOWER('Zhgl') }">
						<li class="six">
							<a onmousedown="addNavOn(this);" onmouseup="removeNavOn(this);" onclick="changeClass(this)" href="${pageContext.request.contextPath}/sys/userDef!list.do" title="后台管理" target="navTab" rel="external_USERMAG" external="true">后台管理</a>
						</li>
					</c:if>		
					<c:if test="${cp:CHECKUSEROPTPOWER('WDSYPZ', 'showcode')}">
					<li class="seven2">
						<a onmousedown="addNavOn(this);" onmouseup="removeNavOn(this);" onclick="openQRcode();return false;" href="javascript:void(0);" title="二维 码扫描" >二维码</a>
					</li>
					</c:if>
					<c:if test="${cp:HASOPTPOWER('YGJGWJG') }">
						<li class="to">
							<a onmousedown="addNavOn(this);" onmouseup="removeNavOn(this);" onclick="changeClass(this)" href="${pageContext.request.contextPath}/oa/optFileTransferReceive!list.do" title="文件柜" target="navTab" rel="external_WJGWJSJX" external="true">文件柜</a>
						</li>
					</c:if>
				</ul>
				<!-- 
				<div class="rightHeader">
					<div class="extendHeader"></div>
				</div>
				 -->
			</div>
			
			
			<!-- navMenu -->
         <!-- 			个人设置  开始-->
		 <div class="bdpfmenu" style="right: 425px; top: 110px;display:none">
			 <div class="bdnuarrow bdbriarrow"></div>
				<div class="bdnuarrow">
					<em></em><i></i>
				</div>
				<a  onclick="doDialog()" style="background: #F7F7F7;"><span class="icon icon-color icon-user" ></span>基本信息</a>
				<a  href="<s:url value='/sys/userDef!modifyPwdPage.do' />" target="dialog" drawable="false" width="550" height="350"> <span class="icon icon-color icon-wrench" ></span>修改密码</a>
				<a href="javascript:;" style="background: #F7F7F7;"><span class="icon icon-color icon-clipboard" ></span>我的便签</a>
				<a href="javascript:;" ><span class="icon icon-color icon-comment-text" ></span>快捷意见</a>
			</div>
		</div>
		<!-- 			个人设置  结束-->
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<c:if test="${username=='noname'}">
				<c:set var="funcs" value="${session.USERDETAIL.userFuncs}" />
			</c:if>
			<c:if test="${not(username=='noname')}">
				<c:set var="funcs" value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.userFuncs}" />
			</c:if>
			<div class="accordionContent" style="display: none">
				<ul class="tree treeFolder">
					<c:if test="${not(username=='noname')}">
						<li>
							<a id='firstPage1' href='<c:url value='${firstPage}' />' target="navTab" rel='main'>我的首页</a>
						</li>
					</c:if>
				</ul>
			</div>
			<c:set var="topdepno">${cp:MAPVALUE('stat_info','topdepno')}</c:set>
			<div class="left_custom" style="height:148px;left:12px;top:30px;z-index:100;width:168px">
			<c:if test="${not(username=='noname')}">
				<authz:authentication var="username" property="name" />
				<div><c:if test="${username!='noname'}">
										您好，<%-- ${cp:MAPVALUE("unitcode",unitcode) } --%> ${username}
										</c:if>
										<span class="icon  icon-carat-1-s">	</span></div>
				
				<%-- <c:if test="${cp:CHECKUSEROPTPOWER('WDSYPZ', 'showOnline')}">	 --%>					
				<div><a style="color:white; font-size: 12px;" onmouseover="this.style.cursor='hand'" onclick="doShow();" >当前在线<span id="userCountOnlineMainPage" style="cursor:pointer;"></span>人</a></div>
				<%-- </c:if> --%>
				
				<!-- <div>上次登录2016-04-27&nbsp;12:00</div> -->
				<div>
				   <a onclick="lockScreen(false);" href="###" title="锁屏"><img alt='锁屏' src='../newStatic/image/left_custom1.png'/></a>
				   <c:if test="${cp:MAPSTATE('SYSPARAM','CAS') eq 'T'}">
						<a title="注销" style="text-decoration: none" href="<s:url value='/j_spring_security_logout'/>"><img alt='关闭' src='../newStatic/image/left_custom3.png'/></a>
					</c:if>
					<c:if test="${not (cp:MAPSTATE('SYSPARAM','CAS') eq 'T')}">
						<a title="注销" style="text-decoration: none" href="<s:url value='/j_spring_security_logout'/>"><img alt='关闭' src='../newStatic/image/left_custom3.png'/></a>
					</c:if>
				   <a  title="修改密码" href="<s:url value='/sys/userDef!modifyPwdPage.do' />" target="dialog" drawable="false" width="550" height="350"><img src='../newStatic/image/white_key.png'/></a>
				   
				   
				   <c:if test="${cp:CHECKUSEROPTPOWER('NBTXL', 'list')}">
				   <a href="javascript:void(0);" onclick="openMenu(this,'txl','<%=request.getContextPath()%>/oa/addressbooks!list_new.do');return false;" rel="GRBGGRTXL" title="通讯录" ><img alt='电话簿' src='../newStatic/image/left_custom2.png'/></a>
				   </c:if>
					<%-- <a title="资料库" href="javascript:void(0);" onclick="openMenu(this,'zlk','<%=request.getContextPath()%>/app/publicinfo!listFile.do?mode=PUBLICFILE');return false;"><img src='../newStatic/image/left_custom4.png'/></a> --%>
				
				</div>
				<div class="seg_line"></div>
			</c:if>
			</div>
			
			<div id="sidebar" style="width:180px">
				<!-- 左边菜单 -->
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<ul class="navTab-tab" style="top: -2px;">
							<li tabid="main" class="main">
								<a href="javascript:;">
									<span>
										<span class="home_icon">我的首页</span>
									</span>
								</a>
							</li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<div class="tabsMoreList" style="display: none;">
					<ul>
						<li class="selected">
							<a href="javascript:;">我的主页</a>
						</li>
					</ul>
				</div>
				<div id="firstPage" class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox" style="background:#f3f3f3;height:100%;">
						<div class="pageFormContent" layoutH="10"></div>
					</div>
				</div>
			</div>
		</div>
		<!-- 拖动条 -->
		<div id="hiddenFrame"></div>
		<div id="splitBar"></div>
		<div id="splitBarProxy"></div>
		<div id="footer">
			<div style="float: left; padding: 2 5px;">
				<strong></strong>
			</div>
			<div>
				<strong style="line-height: 24px; display: block; font-size: 12px;">技术支持电话：${cp:MAPVALUE("SYSPARAM","adminPhone")}</strong>
			</div>
		</div>
	</div>
	<!-- 选择人员的模块 -->
	<div id="attAlert" class="alert"
		style="width: 600px; height: 330px; position:absolute;top:20px;left:20%;overflow: hidden;display:none">
		<h4>
			<span id="selectTT">选择</span><span id="close2"
				style="float: right; margin-right: 8px;" class="close"
				onclick="closeAlert('attAlert');">关闭</span>
		</h4>
		<div class="fix">
			<div id="leftSide"></div>
			<div id="l-r-arrow">
				<div class="lb"></div>
				<div class="rb"></div>
			</div>
			<div id="rightSide">
				<ul></ul>
			</div>
			<div id="t-b-arrow">
				<!-- <div class="tb"></div>
	            <div class="bb"></div> -->
				<b class="btns"><input id="save" class="btn" type="button"
					value="保     存" /><input id="clear" class="btn" type="button"
					value="取     消" style="margin-top: 5px;" /></b>
			</div>
		</div>
	</div>
	<!-- 锁屏界面 -->
	<div id="screensaver">
	    <div class="screensaver-form">
            <img alt="头像" src="${ctx}/images/saver_facer.png"/>
            <p style="padding: 10px 0;">${username}</p>
            <input id="userPassword" class="lock_password" type="password" /><input class="lock_btn" type="button" onclick="loginInScreensaver()"/>
            <input type="hidden" id="userName" value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.loginname}"/>
        </div>
	</div>

	
	
	<%-- <input type="hidden" id="gwDbnumber" name="gwDbnumber" value="${gwDbnumber}" />
	 <div id="dbShow" class="alert"
		style="width: 550px; height: 100px; overflow: hidden;">
		<h4>
				<span>公文待办提醒</span><span id="close"
					style="float: right; margin-right: 8px;" class="close"
					onclick="closeAlert('dbShow');">关闭</span>
			</h4>
		<p style="text-align: center;vertical-align: middle;">您有${gwDbnumber}公文需要办理，
		<c:if test="${gwOutWayNum ne 0 }">
			其中${gwOutWayNum}件即将超期，请及时办理，
		</c:if>
		请点击
		<a href="<c:url value='/dispatchdoc/ioDocTasks!listIncomeDocTasks_Common.do?'/>" target="_blank" onclick="closeAlert('dbShow');">办理</a>!</p>
		<p style="text-align: center;vertical-align: middle;">您有${bjDbnumber}办件需要办理，
		<c:if test="${bjOutWayNum ne 0 }">
			其中${bjOutWayNum}件即将超期，请及时办理，
		</c:if>
		请点击
		<a href="<c:url value='/dispatchdoc/ioDocTasks!listIncomeDocTasks.do?'/>" target="_blank" onclick="closeAlert('dbShow');">办理</a>!</p>
		</div>  --%>
		
	<script src="${pageContext.request.contextPath}/newStatic/js/custom/common/dashboardV3.js" type="text/javascript"></script>	
	<script src="${pageContext.request.contextPath}/newStatic/js/scroll/scroll.js" type="text/javascript"></script>
	<script language="JavaScript">


	
	/////////////////////////
		function doBackHome() {
			window.location.href = "${casHome }";
		}
		function parentAClick(id, closeFlag) {
			if (closeFlag)
				navTab.closeCurrentTab();
			$("#" + id + ">div>a").click();
		}
		function popUserTreeWin(jaStr,o1,o2){
			new treePerson($.parseJSON(jaStr), o1, o2).init();
			setAlertStyle("attAlert",$(window).height()/2-150);
		}
		function popUserTreeWin(jaStr,o1,o2,oShow){
			new treePerson($.parseJSON(jaStr), o1, o2,oShow,30).init();
			setAlertStyle("attAlert",($(window).height()-136)/2+($(document).scrollTop()==0?-200:$(document).scrollTop()));
		}

		function doShow() {

			art.dialog
					.open('${pageContext.request.contextPath}/sys/vUserOnline!list.do',
							 {title: '在线人员列表', width: 1050, height: 640});

		}
		function doDialog() {

			art.dialog
					.open("<%=request.getContextPath()%>/oa/oaUserinfo!userinfoView.do?s_usercode=${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode}&flag=table0",
							 {title: '基本信息', width: 1050, height: 850});

		}
	
    // 	个人设置
	$(".persioninfo").click(function(){
		alert(hello)
	});
	
	
	function div_show(){
		if($(".bdpfmenu").hasClass('show')){
			$(".bdpfmenu").removeClass('show');
		}
		else{
			$(".bdpfmenu").addClass('show');
			$(".bdpfmenu a").click(function(){
				$(".bdpfmenu").removeClass('show');
			});
		}
	}

		//时间戳
		function timestamp(){
			var timestamp = parseInt(new Date().getTime()/1000);
			return timestamp;
		}

		$(function(){

			$('#closeQRcode').click(function(){
				$('#shadow_QRcode').hide();
			});
			// 显示二维码
		});
		function openQRcode(){
			$('#shadow_QRcode').show();
		}
		

	</script>
	<div class="shadow_QRcode" id="shadow_QRcode">
		<div id="QRshadow"></div>
		<img alt="" src="../newStatic/image/njzgh_prod.png"/>
		<div id="closeQRcode"></div>
	</div>	
</body>

</html>
