<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>正在为您转向，请稍候......</title>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>

<script type="text/javascript">
  $(function(){
	  var msg = "${param.msg}";
	  if(msg=="openTab"){
		  transitMainTip();
	  }else{
		  invalidSessionTip();
	  }
  });
  function showbox(id){
	  $("#"+id).siblings("div").hide();
	  $("#"+id).show();
  }
  /*跳转到主页提示*/
  function transitMainTip(){
	  showbox("transitMainTip");
	  setTimeout(function() {
		  goMain();
		}, 500);
	  
  }
  function goMain()
  {  
		window.location.href="${pageContext.request.contextPath}/sys/mainFrame!loginSuccess.do";
		return;
  }
  /*session失效提示*/
  function invalidSessionTip(){
	  showbox("invalidSessionTip");
	  var h = $(window).height();
	  var top = (h-$("div.box").height())/2-100;
	  $("div.box").css("margin-top",top);
	  
	  setTimeout(function() {
			calctime(10);
		}, 1000);
  }
  function calctime(time) {
		time = time - 1;
		if(time >= 1){
			var positionX=$(".timer").css("background-position-x").replace("px","");
			positionX = parseInt(positionX);
			$(".timer").css("background-position-x",(positionX + 108)+"px");
			
			setTimeout(function() {
				calctime(time);
			}, 1000);
		}else{
			top.window.location.href="${pageContext.request.contextPath}/page/frame/login.jsp";
		}
	}
	
</script>
<style type="text/css">
*{margin:0;padding:0}
 .box{width:475px;height:224px;margin:0 auto;padding:12px 10px;
      background:url('${pageContext.request.contextPath}/images/msgboxbg.png') no-repeat;display:none}
 .box .title{width:100%;height:160px;background:url(${pageContext.request.contextPath}/images/warn.png) 40px 35px no-repeat}
 .box .title span{display:block;}
 .box .title span.timer{background:url(${pageContext.request.contextPath}/images/process.png) -972px center no-repeat;
                width:108px;margin-left:180px;height:108px;}
 .box .title span.msg{font-size:20px;font-weight: bolder;color:#CAAFAE;padding-top:60px;
       line-height: 30px;height:30px;margin-left:130px;}
  .box .toolbar{width:100%;height:64px;}
  .box a{display:inline-block;width:30%;margin-left:10%;height:100%;line-height:64px;padding-left:40px;cursor: pointer;color:#888A8C;text-decoration: none;}
  .box a#toLogin{background:url(${pageContext.request.contextPath}/images/refresh.png) 0px 18px no-repeat}
  .box a#toClose{background:url(${pageContext.request.contextPath}/images/back.png) 0px 16px no-repeat}
</style>
</head>
<body>
   <div id="invalidSessionTip" class="box">
     <div class="title">
       <span class="msg">身份认证已失效，即将去登录页</span>
       <span class="timer"></span>
     </div>
     <div class="toolbar">
        <a id="toLogin" href="${pageContext.request.contextPath}/page/frame/login.jsp">立即登录系统</a>
        <a id="toClose" href="javascript:window.close()">关闭当前窗口</a>
     </div>
   </div>
   <div id="transitMainTip">
      <div style="cursor: progress; position: fixed; top: 45%; left: 45%; width: 200%; height: 200%; overflow: hidden;">
      <img alt="正在为您转向，请稍候......" src="${pageContext.request.contextPath}/images/page-loader.gif">
      </div>
   </div>
</body>
</html>