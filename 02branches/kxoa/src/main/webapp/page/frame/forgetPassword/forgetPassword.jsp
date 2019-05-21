<!DOCTYPE html>
<%@page language="java" contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link href="${pageContext.request.contextPath}/themes/css/login.css"
	rel="stylesheet" />
<link rel="stylesheet" href="css/style.css" />
<%@ include file="/page/common/taglibs.jsp"%>
<script
	src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/scripts/frame/components/jquery/jquery.validation/jquery-validation-1.12.0/jquery.validate.js"
	type="text/javascript"></script>
<link type="text/css" rel="stylesheet"
	href="<s:url value='/scripts/form_validator/style/validator.css'/>"></link>
<s:include value="/page/common/formValidator.jsp"></s:include>
<%@ include file="/page/common/taglibs.jsp"%>
<title>忘记密码</title>
</head>
<body>
	<div class="login_wrapper">
		<div class="login_wrapper_div">
			<div class="login_top">
				<div class='logo_img'></div>
				<div
					style="float: right; margin-right: 20px; position: relative; z-index: 15">
					<!-- 					<div style="color: #357ca5; line-height: 40px; text-align: right; font-size: 16px;margin-top:10px; margin-bottom:-5px;"> -->
					<!-- 						<iframe style="padding: 0px;margin: 0px;" width="300" scrolling="no" height="18" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=1&color=%23357ca5&icon=1&wind=1&num=1"></iframe> -->
					<!-- 						 <iframe  width="280" scrolling="no" height="25" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=34&color=%23357ca5&icon=1&num=3"></iframe>  -->
					<!-- 						<iframe width="320" scrolling="no" height="25" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=40&icon=1&num=3"></iframe> -->
					<!-- 						<iframe src="http://www.thinkpage.cn/weather/weather.aspx?uid=UCBBF88233&cid=CHJS000000&l=zh-CHS&p=SMART&a=1&u=C&s=4&m=2&x=0&d=1&fc=357ca5&bgc=&bc=&ti=0&in=0&li=" frameborder="0" scrolling="no" width="240" height="27" allowTransparency="true" style="font-family: Microsoft YaHei !important;"></iframe> -->
					<!-- 					</div> -->
					<!-- 					<h3 style="color: #357ca5; line-height: 40px; "> -->
					<!-- 						<span id="currentDay" style="font-family: Microsoft YaHei !important;"></span>&nbsp; -->
					<!-- 						<span id="hourAndMinu" style="font-family: Microsoft YaHei !important;"></span><span style="font-family: Microsoft YaHei !important;">:</span><span id="seconds" style="font-family: Microsoft YaHei !important;"></span> -->
					<!-- 						<span id="weekDay" style="font-family: Microsoft YaHei !important;"></span> -->
					<!-- 					</h3> -->
				</div>
			</div>
			<div>
				<img src="${contextPath}/themes/css/images/login/left.png"
					style="position: absolute; left: 0; top: 0; z-index: 2" /> <img
					src="${contextPath}/themes/css/images/login/right.png"
					style="position: absolute; right: 0; top: 0; z-index: 2" />
			</div>
			<div class="font">
				<h3>深入学习实践科学发展观</h3>
				<h3 style="margin-top: 50px; text-indent: 2em">以科学发展观统领工会工作全局</h3>
			</div>
		</div>
	</div>
	<div class="footer"
		style="filter: progid:DXImageTransform.Microsoft.Alpha(opacity=60); background: #F3EEEE; opacity: 0.6;">
<!-- 		版权所有：南京市总工会 -->
<!-- 		&nbsp;&nbsp;&nbsp;技术支持：江苏南大先腾信息产业股份有限公司 -->
		</div>






	<div class="shadow_QRcode" id="shadow_QRcode">
		<div id="QRshadow"></div>

		<div class="message_sure">
			<div class="message_surepg">
				<h3>重置密码</h3>
				<ul>
					<li class="sfqr_li sfqr_select"></li>
					<li class="dxqr_li"></li>
					<li class="xmmsz_li"></li>
					<li class="wccz_li"></li>
				</ul>
				<div class="sfqr_div  step_div">
					<s:form id="sfqr_formcheck" action="" namespace="/sys"
						cssClass="pageForm required-validate" style=" display: none;">
						<input type="text" id="telephoneCheck" name="telephoneCheck"
							value="" />
						<input type="text" id="username" name="username"
							value="" />
						<input type="text" id="identityid" name="identityid" value="" />
					</s:form>
					<s:form id="sfqr_form" action="" namespace="/sys"
						cssClass="pageForm required-validate">
						<table>
							<tr>
								<td align="right" style="width: 190px">*请输入手机号码</td>
								<td align="left"><input type="text" id="telephone"
									name="j_telephone" value="" /><span id="telephoneTip"></span><span
									id="telephoneCheckTip"></span></td>
							</tr>
							<tr>
								<td align="right">*请再次输入手机号码</td>
								<td align="left"><input type="text" id="telephone_confirm"
									name="j_telephone_confirm" /><span id="telephone_confirmTip"></span>
								</td>
							</tr>
							<tr>
								<td align="right">*请输入验证码</td>
								<td align="left"><input type="text" id="j_checkcode"
									name="j_checkcode" value="" style="width: 156px;" /> <label
									class="login-help-lable"><img alt="看不清，点击换一张"
										style="cursor: hand; height: 30px; margin-bottom: -8px;"
										src="<c:url value='/sys/userDef!captchaimage.do'/>"
										id="safecode"
										onclick="this.src='<%=request.getContextPath()%>/sys/userDef!captchaimage.do?key='+Math.random();" /></label><span
									id="j_checkcodeTip"></span></td>
							</tr>
							<tr>
								<td align="right"></td>
								<td align="left"><input type="button" id="sfqr_nextstep"
									value="下一步" /></td>
							</tr>
						</table>
					</s:form>
					<div>*温馨提示:手机号必须是本人登陆账号和平台绑定的手机号码，否则您将无法重置密码</div>
				</div>
			

			<!--短信确认部分开始-->
			<div class="dxqr_div step_div" style="display: none">
				<s:form id="dxqr_form" action="" namespace="/sys"
					cssClass="pageForm required-validate">
					<table>
						<tr>
							<td align="right" style="width: 190px">*手机号码</td>
							<td align="left"><input readonly="readonly"
								id="telephone_readonly" value="您的联系方式" /></td>
						</tr>
						<tr>
							<td align="right">*短信验证码</td>
							<td align="left"><input type="text" id="messagecode" style="width: 156px;"
								name="messagecode" value="" /><span></span>
<!-- 								<lable class="pass-button-timer" id="pass-button-new1">发送验证码</lable>  -->
								<label class="pass-button-timer getCod"  id="J_getCode">获取验证码</label>
								<label class="pass-button-timer getCod"  id="J_getreCode" style="display:none;"> 重新发送</label>
                                <label class="pass-button-timer " id="J_resetCode" style="display:none;">
                                                                                                                重新发送（<span id="J_second">60</span>）</label>
								<span id="messagecodeTip"></span></td>
						</tr>
						<tr>
							<td align="right"></td>
							<td align="left"><input type="button" id="dxqr_nextstep"
								value="下一步" /></td>
						</tr>
					</table>
				</s:form>
				<div>*温馨提示:为了您的帐号安全，请完成身份验证。定期修改密码，有助于提高安全性；</div>
			</div>
			<!--短信确认部分结束-->

			<!--新密码设置部分开始-->
			<div class="xmmsz_div step_div" style="display: none">
				<s:form id="xmmsz_form" action="" namespace="/sys"
					cssClass="pageForm required-validate">
					<table>
						<tr>
							<td align="right" style="width: 190px">*新密码</td>
							<td align="left"><input type="password" name="password"
								id="password" /><span id="passwordTip"></span></td>
						</tr>
						<tr>
							<td align="right">*再次输入密码</td>
							<td align="left"><input type="password"
								name="password_confirm" id="password_confirm" /><span
								id="password_confirmTip"></span></td>
						</tr>
						<tr>
							<td align="right"></td>
							<td align="left"><input type="button" id="xmmsz_nextstep"
								value="下一步" /></td>
						</tr>
					</table>
				</s:form>
				<div>*温馨提示:为了保证您的信息安全，请不要重复设置的如（111111,666666等）；连续的（123456,234567等）；带有手机号码的，与个人资料（如身份证，生日等）相关的等过于简单的密码。</div>
			</div>
			<!--新密码设置部分结束-->

			<!--完成设置部分开始-->
			<div class="wccz_div step_div"
				style="color: #666666; font-size: 14px; display: none">
				<div class="wccz_success_div" style="display: none">
					<span class="onCorrect"  style="font-size: 14px; "></span>尊敬的用户，您已成功重置密码，新密码立即生效！
					</br>您可以点击<a onclick="forwordLogin()" style="font-size: 18px; color: #3399CC;">【登录系统】</a>进入办公平台。</span>
				</div>
				<div class="wccz_error_div"style=" display: none">
					<span  class="onShow"  style="font-size: 14px; ">尊敬的用户，由于某些未知错误，您的密码重置失败。
					</br>您可以点击<a onclick="forgetPassword()" style="font-size: 18px; color: #3399CC;">【重置密码】</a>再次重置密码。</span>
					</br>若多次尝试依旧不成功，您可以联系管理员协助解决。
				</div>
			</div>
			<!--完成设置部分结束-->
		</div>
		</div>
		<div id="closeQRcode" onclick="rebackLogin()"></div>
	</div>




</body>
<script type="text/javascript">

    window.document.execCommand('RespectVisibilityInDesign',false,true); 
    /*
            直接返回首页
    */
    function rebackLogin() {
		window.location.href = "${pageContext.request.contextPath}/page/frame/login.jsp";
	}
    /*
             修改完成后跳转至首页-情空密码区域
    */
	function forwordLogin() {
		window.location.href = "${pageContext.request.contextPath}/page/frame/login.jsp?clearType=clearPassword";
	}
     /*
             重新进入设置密码
     */
	function forgetPassword() {
		window.location.href = "${pageContext.request.contextPath}/page/frame/forgetPassword/forgetPassword.jsp";
	}
	//倒计时
	function resetCode(){
		$('#J_getCode').hide();
		$('#J_getreCode').hide();
		$('#J_second').html('99');
		$('#J_resetCode').show();
		var second = 99;
		var timer = null;
		timer = setInterval(function(){
			second -= 1;
			if(second >0 ){
				$('#J_second').html(second);
			}else{
				clearInterval(timer);
				$('#J_getreCode').show();
				$('#J_resetCode').hide();
			}
		},1000);
	}
	$(document)
			.ready(
					function() {

						$("#telephone").focus(function() {
							$("#telephoneCheckTip").hide();
						});
						validateStepsfqr();

						$("#sfqr_nextstep").click(function() {
							$.formValidator.pageIsValid("sfqr");
// 							telephoneCheckSuccess();
						});
						$("#dxqr_nextstep").click(function() {
							$.formValidator.pageIsValid("dxqr");
// 							messageCheckSuccess();
						});
						$("#xmmsz_nextstep").click(function() {
							$.formValidator.pageIsValid("xmmsz");
// 							PassswordSuccess(true);
						});
						
						$(".getCod").click(function(){
							//发送验证码发送ajax请求
						     	$.ajax({
								     url : "${pageContext.request.contextPath}/sys/anonymous/userForgetPassword!messagecodeCreate.do", 
								     data : {"identityid":$("#identityid").val(),"telephoneCheck":$("#telephoneCheck").val()},
								     dataType : "json", 
								     success : function(data){
								    	 if (data) {
									        resetCode();
								    	 }
								    }
							      });
						
						});
						/**
						  第一步用户联系方式确认
						 */

						function validateStepsfqr() {
							$.formValidator.initConfig({
								validatorgroup : "sfqr",
								formid : "sfqr_form",
								errorfocus : false,
								onsuccess : function() {
									var isSubmit = telephoneCheck();
									return isSubmit;
								},
								onerror : function(msg, obj, errorlist) {
								},
							});
							$.formValidator.initConfig({
								validatorgroup : "telephoneCheck",
								formid : "sfqr_formcheck",
								errorfocus : false,
								onsuccess : function() {
									telephoneCheckSuccess();
								},
								onerror : function(msg, obj, errorlist) {
								},
							});

							$("#telephoneCheck")
									.formValidator({
										validatorgroup : 'telephoneCheck'
									})
									.ajaxValidator(
											{
												url : "${pageContext.request.contextPath}/sys/anonymous/userForgetPassword!telephoneCheck.do",
												success : function(data,
														textStatus,
														XMLHttpRequest) {
													var json = eval("(" + data
															+ ")");
													if ("true" == json.validat) {
														$("#identityid").val(
																json.uuid);
														$("#username").val(
																json.username);
														telephoneCheckSuccess();
														return true;

													} else {
														$("#telephoneCheckTip")
																.show();
														$("#telephoneTip")
																.hide();
														return false;
													}
												},
												onerror : "必须是您登陆账号和平台绑定的手机号码"
											});
							$("#telephone").formValidator({
								onshow : "请输入与平台绑定的手机号码",
								onfocus : "必须是您登陆账号和平台绑定的手机号码",
								// 			oncorrect:"恭喜你,你输对了",
								validatorgroup : 'sfqr'
							}).inputValidator({
								min : 1,
								onerror : "手机号码不能为空"
							});
							$("#telephone_confirm").formValidator({
								// 			oncorrect:"恭喜你,你输对了",
								validatorgroup : 'sfqr'
							}).inputValidator({
								min : 1,
								onerror : "手机号码不能为空"
							}).compareValidator({
								desid : "telephone",
								operateor : "=",
								onerror : "2次输入的手机号不一致,请确认"
							});
							//2.验证号码
							$("#j_checkcode")
									.formValidator({
// 										onshow : "点击左侧切换图片",
										onfocus : "请输入左侧图片内容",
										validatorgroup : 'sfqr'
									})
									.ajaxValidator(
											{
												url : "${pageContext.request.contextPath}/sys/anonymous/userForgetPassword!forgetPasswordCheckcode.do",
												success : function(data,
														textStatus,
														XMLHttpRequest) {
													if ("true" == data) {
														return true;
													} else {
														return false;
													}
												},
												onerror : "验证码不正确"
											});

						}

						/**
						   短信确认
						 */
						function validateStepdxqr() {

							//验证信息
							$.formValidator.initConfig({
								validatorgroup : "dxqr",
								formid : "dxqr_form",
								onsuccess : function() {messageCheckSuccess();
								},
								onerror : function(msg, obj, errorlist) {
								},
							});

							$("#messagecode")
									.formValidator({
										onshow : "请输入您收到的最新平台短信验证码",
										validatorgroup : 'dxqr'
									})
									.ajaxValidator(
											{
												url : "${pageContext.request.contextPath}/sys/anonymous/userForgetPassword!messagecodeCheck.do?identityid="
														+ $("#identityid")
																.val()
														+ "&telephoneCheck="
														+ $("#telephoneCheck")
																.val(),
												success : function(data,
														textStatus,
														XMLHttpRequest) {
													if ("true" == data) {
														return true;
													} else {
														return false;
													}
												},
												onerror : "请输入您收到的最新平台短信验证码"
											});
						}

						/**
						   密码确认
						 */
						function validateStepxmmsz() {

							//验证信息
							$.formValidator.initConfig({
								validatorgroup : "xmmsz",
								formid : "xmmsz_form",
								onsuccess : function() {
									var isSubmit = updatPassword();
									return isSubmit;
								},
								onerror : function(msg, obj, errorlist) {

								},
							});

							$("#password").formValidator({
								onshow : "请设置您的新密码",
								onfocus : "请牢记您设置的密码",
								validatorgroup : 'xmmsz'
							}).inputValidator({
								min : 1,
								onerror : "请设置您的新密码"
							});

							$("#password_confirm")
									.formValidator({
										validatorgroup : 'xmmsz'
									})
									.inputValidator({
										min : 1,
										onerror : "请再次输入密码"
									})
									.compareValidator({
										desid : "password",
										operateor : "=",
										onerror : "2次输入的密码不一致,请确认"
									})
									.ajaxValidator(
											{
												url : "${pageContext.request.contextPath}/sys/anonymous/userForgetPassword!updatePassword.do?identityid="
														+ $("#identityid")
																.val(),
												success : function(data,
														textStatus,
														XMLHttpRequest) {
													if ("true" == data) {
														PassswordSuccess(true);
														return true;
													} else {
														// 					密码设置失败内容
														PassswordSuccess(false);
														return false;
													}
												},
												onerror : "密码设置失败"
											});
							;
						}

						/*yanzhengma*/
						function shortMessagraxc() {
							$('#btn_yzm')
									.click(
											function() {
												var count = 60;
												var countdown = setInterval(
														CountDown, 1000);

												function CountDown() {
													$("#btn_yzm").attr(
															"disabled", true);
													$("#btn_yzm").val(
															"" + count + "s");
													if (count == 0) {
														$("#btn_yzm")
																.val("重新获取")
																.removeAttr(
																		"disabled");
														clearInterval(countdown);
													}
													count--;
												}
											});
						}
						;

						function telephoneCheck() {
							$("#telephoneCheck").val($("#telephone").val());
							$("#telephoneCheck").blur();

						}
						/**
						        手机号码验证通过
						       页面切换
						      进入短信验证环节
						 */
						function telephoneCheckSuccess() {
							if($("#username").val()){
								$("#telephone_readonly").val($("#telephone").val()+"("+$("#username").val()+")");
							}
							$("#telephoneCheckTip").hide();
							$("#telephoneTip").show();
							$(".step_div").hide();
							$(".dxqr_div").show();
							$(".dxqr_li").addClass("dxqr_select");
							validateStepdxqr();
						}
						;

						/*
						    短信验证成功
						    页面切换
						    进入新密码设置环节
						 */
						function messageCheckSuccess() {
							$(".step_div").hide();
							$(".xmmsz_div").show();
							$(".xmmsz_li").addClass("xmmsz_select");
							validateStepxmmsz();
						}

						/*
						              新密码设置成功
						    页面切换
						      进入完成设置提示界面
						 */
						function PassswordSuccess(target) {
							$(".step_div").hide();
							$(".wccz_div").show();
							$(".wccz_li").addClass("wccz_select");
							//控制提示内容
							if (target) {
								$(".wccz_success_div").show();
								$(".wccz_error_div").hide();
							} else {
								$(".wccz_error_div").show();
								$(".wccz_success_div").hide();
							}
						}
					});
</script>
</html>
