<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<html>
  <head>
  <style type="text/css">
.login_wrapper {font-family: Microsoft YaHei !important; }
input{background: url(${contextPath}/themes/css/images/wbk.png) no-repeat;height:42px;width:250px;line-height:42px;padding-left: 10px;}
</style>
  <link href="${pageContext.request.contextPath}/themes/css/login.css" rel="stylesheet"/>
<script src="${ctxStatic}/js/security/RSA.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/security/BigInt.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/security/Barrett.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
  </head>
  <body >
  <div class="login_wrapper" >
    	<div class="login_wrapper_div">
        	<div class="login_top" >
        		<div class='logo_img'></div>
            	<div style="float:right;margin-right:20px;position:relative;z-index:15">
					<div style="color: #357ca5; line-height: 40px; text-align: right; font-size: 16px;margin-top:10px; margin-bottom:-5px;">
						<!-- <iframe style="padding: 0px;margin: 0px;" width="300" scrolling="no" height="18" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=1&color=%23357ca5&icon=1&wind=1&num=1"></iframe> -->
						 <!-- <iframe  width="280" scrolling="no" height="25" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=34&color=%23357ca5&icon=1&num=3"></iframe>  -->
						<!-- <iframe width="320" scrolling="no" height="25" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=40&icon=1&num=3"></iframe> -->
						<iframe src="http://www.thinkpage.cn/weather/weather.aspx?uid=UCBBF88233&cid=CHJS000000&l=zh-CHS&p=SMART&a=1&u=C&s=4&m=2&x=0&d=1&fc=357ca5&bgc=&bc=&ti=0&in=0&li=" frameborder="0" scrolling="no" width="240" height="27" allowTransparency="true" style="font-family: Microsoft YaHei !important;"></iframe>
					</div>
					<h3 style="color: #357ca5; line-height: 40px; ">
						<span id="currentDay" style="font-family: Microsoft YaHei !important;"></span>&nbsp;
						<span id="hourAndMinu" style="font-family: Microsoft YaHei !important;"></span><span style="font-family: Microsoft YaHei !important;">:</span><span id="seconds" style="font-family: Microsoft YaHei !important;"></span>
						<span id="weekDay" style="font-family: Microsoft YaHei !important;"></span>
					</h3>
				</div>
            </div>
            <div>
            	<img  src="${contextPath}/themes/css/images/login/left.png" style="position:absolute; left:0;top:0;z-index:2" />
                <img  src="${contextPath}/themes/css/images/login/right.png" style="position:absolute; right:0;top:0;z-index:2"/>
            </div>
            <div class="font">
            	<h3>深入学习实践科学发展观</h3>
                <h3 style="margin-top:50px; text-indent:2em">以科学发展观统领工会工作全局</h3>
            </div>
        </div>
  </div>   
  <div class="footer" style="filter: progid:DXImageTransform.Microsoft.Alpha(opacity=60);background:#F3EEEE;opacity: 0.6;">版权所有：连云港市徐圩 &nbsp;&nbsp;&nbsp;技术支持：江苏南大先腾信息产业有限公司 </div>
  
  <div class="shadow_QRcode" id="shadow_QRcode">
  	<div id="QRshadow"></div>
  	<div class="modifypasswordpg">
      			<div style="position:relative; top:17px;margin-left: 25px;margin-right: 25px;">
      				<img style="float: left;position:relative;" src="${contextPath}/themes/css/images/ftx.png"/>
        			 <h4 style="float:left;position:relative;text-align:left;color:red;font-family:Microsoft YaHei !important;font-size: 17px;margin-left: 10px;">首次登陆修改密码</h4>
        			<%--  <a style="float:right;position:relative;" id="sub" href="javaScript:void(0);" ><img id="sub_img" src="${contextPath}/themes/css/images/queren.png" /></a> --%>
     			</div>
  				<div style="position:relative; top:45px;text-align: center;">
      				<table style="margin: auto;height:160px;">
      					<tr>
      						<td style="text-align: right;"><label style="width:110px;color: #636768">*初始密码：</label></td>
      						<td colspan="2"><input id="oldPwd" type="password"   name="userPwd.oldPassword" minlength="6" maxlength="20"/></td>
      					</tr>
				      	<tr>
				      		<td style="text-align: right;"><label style="width:110px;color:#636768">*新密码：</label></td>
				      		<td colspan="2"><input id="newPwd" type="password"  
									name="userPwd.newPassword"/></td>
				      	</tr>
				      	<tr>
				      		<td style="text-align: right;"><label style="width:110px;color:#636768">*确认新密码：</label></td>
				      		<td><input id="confirmNewPwd" type="password"  
									 name="userPwd.confirmPassword"/></td>
							<td style="padding-left: 15px;padding-bottom: -5px;">
							<a id="sub" href="javaScript:void(0);" ><img id="sub_img" src="${contextPath}/themes/css/images/queren.png" /></a>
							</td> 
				      	 </tr>
      				</table>
  			</div>
  			<div style="position:relative; top:55px;text-align: center;color: #0B70C2;width: 442px;margin: 0 auto;">
  				<table style="color:#0B70C2;font-size: 11px;margin: auto;width: 100%; ">
  					<tr>
  					<td style="width: 111px;text-align: right;">温馨提示：&nbsp;&nbsp;</td>
  					<td>为了您的帐户安全，首次登录请先修改密码;</td>
  					</tr>
  					<tr><td></td><td>▪&nbsp;新密码不可与原始密码相同;</td></tr>
  					<tr><td></td><td>▪&nbsp;请选择6-20个字符，可以是英文字母（请注</td></tr>
  					<tr><td></td><td>&nbsp;&nbsp;意区分大小写）、或者数字，也可以英文与</td></tr>
  					<tr><td></td><td>&nbsp;&nbsp;数字结合;</td></tr>
  					<!-- <tr><td></td><td>▪&nbsp;定期修改密码，有助于提高安全性;</td></tr> -->
  					<tr><td></td><td>▪&nbsp;成功修改密码后，自动进入办公系统。</td></tr>
  				</table>
  				<!-- <p> 温馨提示：为了您的帐户安全，首次登录请先修改密码;</p>
  				<p> 新密码不可与原始密码相同;</p>
  				<p> 定期修改密码，有助于提高安全性;</p>
  				<p> 成功修改密码后，自动进入办公系统;</p> -->
  			</div>
  		</div>
  		<div id="closeQRcode" onclick="forwordLogin()"></div>
  
  </div>
  
  
  
  
  </body>
  <script>
  function forwordLogin(){
		window.location.href="${pageContext.request.contextPath}/page/frame/login.jsp";
	}
	$(function() {
		$('#sub').click(
				function() {
				   if($("#oldPwd").val()==''){
					   alert("旧密码不能为空");
					   return;
				   }
				   if($("#newPwd").val()==''){
					   alert("新密码不能为空");
					   return;
				   }
				   if($("#oldPwd").val()==$("#newPwd").val()){
					   alert("新密码与旧密码不能相同");
					   return;
				   }
				   if($("#confirmNewPwd").val()==''){
					   alert("确认新密码不能为空");
					   return;
				   }
				   if($("#newPwd").val()!=$("#confirmNewPwd").val()){
					   $("#confirmNewPwd").val("");
					   alert("两次输入的密码不一致，请重新输入");
					   return;
				   }
					$.ajax({
						type : "POST",
						async : false,
						dataType : "json",
						url : "${ctx}/sys/userDef!modifypwd.do?userPwd.oldPassword="
								+ $("#oldPwd").val()
								+ "&userPwd.newPassword="
								+ $("#newPwd").val()
								+ "&userPwd.confirmPassword="
								+ $("#confirmNewPwd").val(),
						success : function(json) {
							if (json.status == '密码修改成功！') {
								alert("密码更新成功");
								window.location.href="${ctx}/page/frame/loginTransition.jsp?msg=openTab";
							} else {
								alert(json.status);
							}
						},
						error : function() {
							alert(json.status);
						}
					});
			
				});
		/* $('#sub_img').mouseover(function(){
			$('#sub_img').attr("src","${contextPath}/themes/css/images/jr.png");
		});
		$('#sub_img').mouseout (function(){
			$('#sub_img').attr("src","${contextPath}/themes/css/images/wjr.png");
		}); */
		$('input').focus(function(){
			 $(this).css("background","url(${contextPath}/themes/css/images/wbkfs.png) no-repeat"); 
		});
		$('input').blur(function(){
			$(this).css("background","url(${contextPath}/themes/css/images/wbk.png) no-repeat"); 
		});
		
		setCurrentTime();
		setInterval(function(){
			setCurrentTime();
		},1000);
				
	});
	

		// 设置当前时间
		function setCurrentTime(){
			var date = new Date();
			$('#hourAndMinu').html(date.getHours() + ":" + convert(date.getMinutes()));
			$('#seconds').html(convert(date.getSeconds()));
			$('#weekDay').html(convertToWeekDay(date.getDay()));
			$('#currentDay').html(date.getFullYear()+"-"+ (convert(date.getMonth()+1)) +"-"+ convert(date.getDate()));
		}
		
		// 将0-9数字前加上“0”
		function convert(data){
			if(null != data && String(data).length == 1){
				return "0" + data ;
			}else
				return data;
		}
		
		// 获取当前星期几
		function convertToWeekDay(day){
			
			var weekDay = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
			if(null != day){
				return weekDay[day];
			}
		}
		function submit(event) {

		};
		
		
		
		
</script>
</html>