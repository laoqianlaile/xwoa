<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录面板</title>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script type="text/javascript">
  $(function(){
	  onload();
	  $("input[type='text'],input[type='password']").blur(function(){
		 if($.trim($(this).val())==""){
			 $(this).css("background-image",$(this).data("bgimage"));
		 }
	  }).focus(function(){
		  var bgimage = $(this).css("background-image");
		  if(bgimage!="none"){
			  $(this).data("bgimage",bgimage);  
		  }
		  $(this).css("background-image","none");
	  }).keydown(function(e){
		     // 兼容FF和IE和Opera    
  	    var theEvent = e || window.event;    
  	    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
  	    if (code == 13) {    
  	        //回车执行查询
  	    	 $("#btnLobin").click();
  	    }    
	  });
	  $("#btnReset").click(function(){
		  $("input[type='text'],input[type='password']").each(function(){
			 $(this).val("");
			 if($(this).data("bgimage")){
				 $(this).css("background-image",$(this).data("bgimage"));
			 }
		  });
	  });
	  $("#btnLobin").click(function(){
		  $("#loginForm").submit();
	  });
  }); 
  function onload(){
	  //门户位置登录成功后返回的指令参数
	  var msg = "${param.msg}";
	 
	  if(msg=="openTab"){
		window.open ("${contextPath}/sys/mainFrame!loginSuccess.do" , "XJOASYS" ,
				  "top=0,left=0,resizable=yes,location=yes,width=2000,height=900" ) ;
	  }
	  if(msg=="loginFail"){
		  var exMsg = "${SPRING_SECURITY_LAST_EXCEPTION.message}";
		  if(exMsg=="Bad credentials"){
			  alert("登录失败，用户名或密码错误");
		  }
		  if(exMsg=="bad checkcode"){
			  alert("登录失败，验证错误");
		  }
	  }
  }
</script>
<style type="text/css">
  *{margin:0;padding:0;}
 .login-box{width:309px;height:169px;margin:0 auto;padding:10px 10px;border: 1px #ccc solid;
       background: url('${contextPath}/themes/css/images/bl_bg.png') repeat;}
 .login-box > div{margin-left: 55px;margin-top:10px;}
 .login-box > div input{border: 1px solid #ccc;vertical-align:middle;width:200px;height:25px;font-size: 17px}
 .login-title{margin-top:0 !important;color:#1F5F99;font-weight:bolder}
 .login-toolbar{text-align: center;padding-top: 10px;margin-left: 0px !important;}
 #username{background: #fff url(${contextPath}/themes/css/images/username.png) left center no-repeat}
 #password{background: #fff url(${contextPath}/themes/css/images/password.png) left center no-repeat}
 #check_num{background: #fff url(${contextPath}/themes/css/images/yzm.png) left center no-repeat;width:120px}
 #safecode{width:75px;height:25px;vertical-align:middle;}
</style>
</head>
<body>
   <form method="post" name="loginForm" id="loginForm" action="${contextPath}/j_spring_security_check" >
   <input type="hidden" name="__loginPosition" value="portal">
   <div class="login-box">
      <div class="login-title">用户登录</div>
      <div ><input id="username" name="j_username"  type="text" /></div>
      <div><input name="j_password" id="password" type="password" /></div>
      <div><input id="check_num" name="j_checkcode" type="text" maxlength="6"/>
      <img alt="看不清，点击换一张" style="cursor: hand;" id="safecode"
					src="${contextPath}/sys/userDef!captchaimage.do"
					onclick="this.src='${contextPath}/sys/userDef!captchaimage.do?key='+Math.random();" />
      </div>
      <div class="login-toolbar"><a href="javascript:void(0);" id="btnLobin">登录</a>
      <a  href="javascript:void(0);" id="btnReset" style="margin-left:20px">重置</a></div>
   </div>
   </form>
</body>
</html>