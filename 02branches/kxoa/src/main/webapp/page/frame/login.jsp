<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%@page import="com.centit.sys.util.RSAUtil"%>
<%@page import="java.security.interfaces.RSAPublicKey"%>
<%@page import="java.security.KeyPair"%>

<%@ include file="/page/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${cp:MAPVALUE('SYSPARAM','SysName')}" /></title>
<c:if test="${empty STYLE_PATH}">
	<c:set var="ctx" value="${pageContext.request.contextPath}"
		scope="request" />
	<c:set var="STYLE_PATH" value="${ctx}/styles/default" scope="session" />
</c:if>
<%
 RSAPublicKey rsap = (RSAPublicKey)RSAUtil.getKeyPair().getPublic();
 String module = rsap.getModulus().toString(16);  
 String empoent = rsap.getPublicExponent().toString(16);  
 request.setAttribute("module", module);  
 request.setAttribute("empoent", empoent);  
%>
<style type="text/css">
*{font-family: Simsun !important;}
h3 span{font-size: 1.17em}
</style>
<link href="${pageContext.request.contextPath}/themes/css/login.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/security/RSA.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/security/BigInt.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/security/Barrett.js" type="text/javascript"></script>
<script src="${ctxStatic}/js/security/base64.js" type="text/javascript"></script>
	<script type="text/javascript">
	//强制顶部打开登录页面
	  if(window!=top){
		  top.window.location.href="${ctx}/page/frame/login.jsp";
	  }
	</script>
</head>
<body>

<!-- 获取RTX控件的合法性认证 -->
<!--  <OBJECT id=RTXAX
          data=data:application/x-oleobject;base64,fajuXg4WLUqEJ7bDM/7aTQADAAAaAAAAGgAAAA== 
              classid=clsid:5EEEA87D-160E-4A2D-8427-B6C333FEDA4D VIEWASTEXT>
   </OBJECT>
 -->
 	

	<div class="login_wrapper">
    	<div>
        	<div class="login_top">
        		<div class='logo_img'></div>
            	<div style="float:right;margin-right:20px;position:relative;z-index:15">
					<div style="color: #357ca5; line-height: 40px; text-align: right; font-size: 16px;margin-top:10px; margin-bottom:-5px;">
						<!-- <iframe style="padding: 0px;margin: 0px;" width="300" scrolling="no" height="18" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=1&color=%23357ca5&icon=1&wind=1&num=1"></iframe> -->
						 <!-- <iframe  width="280" scrolling="no" height="25" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=34&color=%23357ca5&icon=1&num=3"></iframe>  -->
						<!-- <iframe width="320" scrolling="no" height="25" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=40&icon=1&num=3"></iframe> -->
						<!-- <iframe src="http://www.thinkpage.cn/weather/weather.aspx?uid=UCBBF88233&cid=CHJS000000&l=zh-CHS&p=SMART&a=1&u=C&s=4&m=2&x=0&d=1&fc=357ca5&bgc=&bc=&ti=0&in=0&li=" frameborder="0" scrolling="no" width="240" height="27" allowTransparency="true" style="font-family: Microsoft YaHei !important;"></iframe> -->
					<!-- <iframe name="weather_inc"
				src="http://i.tianqi.com/index.php?c=code&id=2&num=3" width="450"
				height="70" frameborder="0" marginwidth="0" marginheight="0"
				scrolling="no"></iframe> -->
				<iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=9" width="500" height="70" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" allowTransparency="true" style="font-family: Microsoft YaHei !important;"></iframe>
					</div>
				<!-- <h3 style="color: #357ca5; line-height: 40px;padding-top:5px;font-family: 宋体 !important; ">
						<span id="currentDay" ></span>&nbsp;
						<span id="hourAndMinu" ></span><span id="seconds" ></span>
						<span id="weekDay" ></span>
					</h3> -->
				</div>
            </div>
            
            <div>
            	<img alt="云" src="${contextPath}/themes/css/images/login/left.png" style="position:absolute; left:0;top:0;z-index:2" />
                <img alt="云" src="${contextPath}/themes/css/images/login/right.png" style="position:absolute; right:0;top:0;z-index:2"/>
            </div>
            <div class="font">
            	<h3>深入学习实践科学发展观</h3>
                <h3 style="margin-top:50px; text-indent:2em">以科学发展观统领工会工作全局</h3>
            </div>
            
          <s:form  method="post" name="loginForm" id="loginForm" action="/j_spring_security_check"  namespace="/" focus="loginName" onsubmit="return beforeSubmit()">
			<html:hidden property="action" />
			<!-- <input  type="hidden" name="j_checkcode"  value="nocheckcode"/> -->
			<div style="position: absolute;width: 200px;top: 60px; left:10px">
				<div id="div_login_alert" class="alert alert-danger alert-dismissable login-msg">
					<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
					<c:choose>
						<c:when
							test="${SPRING_SECURITY_LAST_EXCEPTION.message eq 'Bad credentials'}">
							<span
								style="color: red; font-family: Microsoft YaHei !important; font-size: 14px;">用户名或者密码错误</span>
						</c:when>
				 		<c:when test="${SPRING_SECURITY_LAST_EXCEPTION.message eq 'bad checkcode'}">
	                  		 <span
								style="color: red; font-family: Microsoft YaHei !important; font-size: 14px;">用户或验证码错误</span>
	                    </c:when>
	             
						<c:otherwise> 
						<span></span>
					</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${empty SPRING_SECURITY_LAST_EXCEPTION.message}">
					<span></span>
				</c:if>
			</div> 
			</div>
				<div class="username" id="username_div">
					<input type="text" id="username" name="j_username"  style="background: url(${contextPath}/themes/css/images/username.png) left center no-repeat white;"
						value="${SPRING_SECURITY_LAST_USERNAME}"
						onblur="if(this.value==''){this.style.background='url(${contextPath}/themes/css/images/username.png) left center no-repeat white'}if(this.value!=''){this.style.background='white'}"/>
				</div>
				<div class="password">
					<input type="password" name="j_password" id="password"  style="background: url(${contextPath}/themes/css/images/password.png) left center no-repeat white"
					onblur="if(this.value==''){this.style.background='url(${contextPath}/themes/css/images/password.png) left center no-repeat white'}if(this.value!=''){this.style.background='white'}" />
				</div>
			<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
			<div class="yanzhengma" >
			    <input type="text" id="check_num" style="background: url(${contextPath}/themes/css/images/yzm.png) left center no-repeat white"
			    onblur="if(this.value==''){this.style.background='url(${contextPath}/themes/css/images/yzm.png) left center no-repeat white'}if(this.value!=''){this.style.background='white'}"
					name="j_checkcode" />
				
				<!-- <input type="checkbox" />
				十天内免登陆&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>忘记密码？</a> -->
			
			<img alt="看不清，点击换一张"
					style="cursor: hand;height: 30px;margin-bottom: -8px;"
					src="<c:url value='/sys/userDef!captchaimage.do'/>"
					 id="safecode"
					onclick="this.src='<%=request.getContextPath()%>/sys/userDef!captchaimage.do?key='+Math.random();" />
			</div>
			</c:if>
<!-- 			<div> -->
<!--             <p class="login-help"><a href="./forgetPassword/forgetPassword.jsp">忘记密码?</a></p> -->
<!--             </div> -->
			<div >
			<c:if test="${empty SPRING_SECURITY_LAST_EXCEPTION.message}">
			<input type="hidden" id="hascheckcode" name="hascheckcode" value="have" />
			</c:if>
			<input type="hidden"  name="pwdTransferType" value="RSAciphertext"/>
			<input type="hidden" name="RSAEncrypted" id="RSAEncrypted">
			<input type="submit" class="btn btn_login" name="login" value="登&nbsp;&nbsp;录" />
			 <!-- <span class="login-help"><a href="./forgetPassword/forgetPassword.jsp">忘记密码?</a></span>  -->
			</div>
			 <div>
			 <span class="login-left"><a href="http://www.njkjgzz.org.cn/portal/njkx/index.action" title="科技工作者之家网">
			 <img alt="科技工作者之家网" src="${contextPath}/themes/css/images/login/logo-1.png"  />
			 </a></span> 
			 </div>
		</s:form>
            
        </div>
    </div>
     <div class="footer">版权所有：南京市科学技术协会 &nbsp;&nbsp;&nbsp;技术支持：江苏南大先腾信息产业股份有限公司 </div>
	
	
	
</body>

<script language="JavaScript">


$(function() {
	
	
	
	<c:if test="${empty SPRING_SECURITY_LAST_EXCEPTION.message}">
	$('#div_login_alert').hide();
	</c:if>
	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
	$('#div_login_alert').show();
	
	$('#loginForm').attr('style','background: url(${contextPath}/themes/css/images/login/login.png) center center;height:420px;');
//	$('div.username').attr('style','margin-top: 60px;');
	$('div.password').attr('style','margin-top: 40px;');
	$('input.btn').attr('style','margin-top: 40px;float: left; margin-left: 35px;');
	</c:if>

	 var hide = function() {
		setTimeout(function() {
			$('div.login-msg').fadeOut();
		}, 2000);
	};

	hide(); 
	var show = function(text) {
		$('#div_login_alert>span').text(text);
		$('#div_login_alert').show();

		hide();
	};
	/* zytz(); */
	setCurrentTime();
	setInterval(function(){
		setCurrentTime();
	},1000);
	
	initInput();
	
	
	// 获取天气
	$.getJSON("http://wthrcdn.etouch.cn/weather_mini?city=南京",function(result){
		var v = result.data.forecast[0];
		var high = v.high.substr(3,v.high.length);
		var low = v.low.substr(3,v.low.length);
		var type = v.type;
		$('#highTemperature').html(high);
		$('#lowTemperature').html(low);
		$('#weather').html(type);
	});
	
	if($("#check_num").val()==''){
		$("#check_num").attr('style','background:url(${contextPath}/themes/css/images/yzm.png) left center no-repeat white');
	}
	
});

// 	window.onload = init;
	var element = document.getElementsByTagName("li");
	function init() //初始化背景色 
	{
		var element = document.getElementsByTagName("li");
		for ( var i = 0; i < 4; i++) {
			var k = i + 1;
			element[i].style.background = "url(${contextPath}/themes/css/images/"
					+ k.toString() + ".jpg)";
		}
	}
		function initRTX(){
			try{
				//debugger;
 				 var usercodeRTX ='${session.SPRING_SECURITY_CONTEXT.authentication.principal.loginname}';
		           var objKernalRoot = RTXAX.GetObject("KernalRoot");
		           var objRtcData = objKernalRoot.Sign;
		           
		           
		           var strAccount = objKernalRoot.Account;
		           var strSgin = objRtcData.GetString("Sign");
		           
		           if(objRtcData!=null&&strSgin!=null){
		        	   window.location.href ="${contextPath}/page/RTXJsp.jsp?user="+strAccount+"&sign="+strSgin;
		           }
			   }catch(e){
				   console.log(e);
			} 

		}
		
	
	function addclick() {
		for ( var i = 0; i < 4; i++) {
			var k = i + 1;
			if (window.attachEvent)
				element[i].attachEvent("onclick", new Function("bgchage(" + k
						+ ");")); //创建事件不能直接.onclick而需要使用.attachEvent("事件",new Function("被调函数("+参数+");"));IE6通过 
			else
				element[i].addEventListener("click", new Function("bgchage("
						+ k + ");"), false);
		}
	}
	function bgchage(t) //根据色块改变背景色 
	{
		document.getElementsByTagName("body")[0].style.background = "url(${contextPath}/themes/css/images/"
				+ t + ".jpg) center bottom";
	}
// 	addclick();
	var p = $('#pic_player');
	var pics1 = [ {
		url : '${contextPath}/themes/css/images/1_06.png',
		time : 6000
	}, {
		url : '${contextPath}/themes/css/images/2_06.png',
		time : 6000
	}, {
		url : '${contextPath}/themes/css/images/1_06.png',
		time : 6000
	}, {
		url : '${contextPath}/themes/css/images/2_06.png',
		time : 6000
	} ];
	/* initPicPlayer(pics1, p.css('width').split('px')[0], p.css('height').split(
			'px')[0]); */
	// 
	// 
	function initPicPlayer(pics, w, h) {
		//选中的图片 
		var selectedItem;
		//选中的按钮 
		var selectedBtn;
		//自动播放的id 
		var playID;
		//选中图片的索引 
		var selectedIndex;
		//容器 
		var p = $('#pic_player');
		p.text('');
		p.append('<div id="piccontent"></div>');
		var c = $('#piccontent');
		for ( var i = 0; i < pics.length; i++) {
			//添加图片到容器中 
			c
					.append('<a href="#" onclick="javascript:return false;" target="_blank"><img id="picitem'+i+'" style="display: none;z-index:'+i+'" src="'+pics[i].url+'" /></a>');
		}
		//按钮容器，绝对定位在右下角 
		p.append('<div id="picbtnHolder1" style="position:absolute;top:'
				+ (h - 25) + 'px;width:' + w
				+ 'px;height:20px;z-index:10000;"></div>');
		// 
		var btnHolder = $('#picbtnHolder2');
		btnHolder
				.append('<div id="picbtns" style="float:right; padding-right:1px;"></div>');
		var btns = $('#picbtns');
		// 
		for ( var i = 0; i < pics.length; i++) {
			//增加图片对应的按钮 
			btns
					.append('<span id="picbtn'+i+'" style="cursor:pointer; border:solid 1px #ccc;background-color:#eee; display:inline-block;"> '
							+ (i + 1) + ' </span> ');
			$('#picbtn' + i).data('index', i);
			$('#picbtn' + i)
					.click(
							function(event) {
								if (selectedItem.attr('src') == $(
										'#picitem' + $(this).data('index'))
										.attr('src')) {
									return;
								}
								setSelectedItem($(this).data('index'));
							});
		}
		btns.append(' ');
		/// 
// 		setSelectedItem(0);
		//显示指定的图片index 
		function setSelectedItem(index) {
			selectedIndex = index;
			clearInterval(playID);
			//alert(index); 
			if (selectedItem)
				selectedItem.fadeOut('fast');
			selectedItem = $('#picitem' + index);
			selectedItem.fadeIn('slow');
			// 
			if (selectedBtn) {
				selectedBtn.css('backgroundColor', '#eee');
				selectedBtn.css('color', '#000');
			}
			selectedBtn = $('#picbtn' + index);
			selectedBtn.css('backgroundColor', '#000');
			selectedBtn.css('color', '#fff');
			//自动播放 
			playID = setInterval(function() {
				var index = selectedIndex + 1;
				if (index > pics.length - 1)
					index = 0;
				setSelectedItem(index);
			}, pics[index].time);
		}
	}

	$.tween = function(startProps, endProps, timeSeconds, animType, delay) {
		var tw = new Tween();
		tw.start(startProps, endProps, timeSeconds, animType, delay);
		//
		//alert(tw)
		//
		return tw;
	}

	function Tween() {
		this._frame = 20;
		//
		this._startProps = [];
		this._currentProps = [];
		this._endProps = [];
		//
		this._startTimer = 0;
		this._timeSeconds = 0;
		this._animType = "linear";
		this._delay = 0;
		//
		this._runID = -1;
		//
		this.run = function() {
		}
		this.complete = function() {
		}
	}
	//
	Tween.prototype.start = function(startProps, endProps, timeSeconds,
			animType, delay) {
		if (animType != undefined)
			this._animType = animType;
		if (delay != undefined)
			this._delay = delay;
		//
		this._timeSeconds = timeSeconds;
		this._startTimer = new Date().getTime();
		this._startProps = startProps;
		this._endProps = endProps;
		this._currentProps = [];
		//
		var $this = this;
		//
		this._runID = setInterval(function() {
			$this._run();
		}, this._frame);
		//
	}

	Tween.prototype.stop = function() {
		clearInterval(this._runID);
	}

	Tween.prototype._run = function() {
		if (new Date().getTime() - this._startTimer - (this._delay * 3000) < 0)
			return;
		var isEnd = false;
		for ( var i in this._startProps) {
			this._currentProps[i] = this.getValue(this._startProps[i],
					this._endProps[i], this._startTimer, new Date().getTime()
							- (this._delay * 1000), this._startTimer
							+ (this._timeSeconds * 1000), this._animType);
			if (this._startTimer + (this._timeSeconds * 1000)
					+ (this._delay * 1000) <= new Date().getTime()) {
				this._currentProps[i] = this._endProps[i];
				isEnd = true;
			}
		}
		this.run(this._currentProps);
		if (isEnd) {
			this.stop();
			this.complete(this._currentProps);
		}
	}

	Tween.prototype.getValue = function(_propStart, _propDest, _timeStart,
			_timeNow, _timeDest, _animType, _extra) {
		var t = _timeNow - _timeStart; // current time (frames, seconds)
		var b = _propStart; // beginning value
		var c = _propDest - _propStart; // change in value
		var d = _timeDest - _timeStart; // duration (frames, seconds)
		var s, a, p;

		if (_extra == undefined) {
			_extra = new Object();
			_extra["e1"] = 1;
			_extra["e2"] = 0;
			a = _extra["e1"]; // amplitude (optional - used only on *elastic easing)
			p = _extra["e2"]; // period (optional - used only on *elastic easing)
			s = _extra["e1"]; // overshoot ammount (optional - used only on *back easing)
		}
		switch (_animType.toLowerCase()) {
		case Tween.linear:
			// simple linear tweening - no easing
			return c * t / d + b;

		case Tween.easeinquad:
			// quadratic (t^2) easing in - accelerating from zero velocity
			return c * (t /= d) * t + b;
		case Tween.easeoutquad:
			// quadratic (t^2) easing out - decelerating to zero velocity
			return -c * (t /= d) * (t - 2) + b;
		case Tween.easeinoutquad:
			// quadratic (t^2) easing in/out - acceleration until halfway, then deceleration
			if ((t /= d / 2) < 1)
				return c / 2 * t * t + b;
			return -c / 2 * ((--t) * (t - 2) - 1) + b;

		case Tween.easeincubic:
			// cubic (t^3) easing in - accelerating from zero velocity
			return c * (t /= d) * t * t + b;
		case Tween.easeoutcubic:
			// cubic (t^3) easing out - decelerating to zero velocity
			return c * ((t = t / d - 1) * t * t + 1) + b;
		case Tween.easeinoutcubic:
			// cubic (t^3) easing in/out - acceleration until halfway, then deceleration
			if ((t /= d / 2) < 1)
				return c / 2 * t * t * t + b;
			return c / 2 * ((t -= 2) * t * t + 2) + b;

		case Tween.easeinquart:
			// quartic (t^4) easing in - accelerating from zero velocity
			return c * (t /= d) * t * t * t + b;
		case Tween.easeoutquart:
			// quartic (t^4) easing out - decelerating to zero velocity
			return -c * ((t = t / d - 1) * t * t * t - 1) + b;
		case Tween.easeinoutquart:
			// quartic (t^4) easing in/out - acceleration until halfway, then deceleration
			if ((t /= d / 2) < 1)
				return c / 2 * t * t * t * t + b;
			return -c / 2 * ((t -= 2) * t * t * t - 2) + b;

		case Tween.easeinquint:
			// quintic (t^5) easing in - accelerating from zero velocity
			return c * (t /= d) * t * t * t * t + b;
		case Tween.easeoutquint:
			// quintic (t^5) easing out - decelerating to zero velocity
			return c * ((t = t / d - 1) * t * t * t * t + 1) + b;
		case Tween.easeinoutquint:
			// quintic (t^5) easing in/out - acceleration until halfway, then deceleration
			if ((t /= d / 2) < 1)
				return c / 2 * t * t * t * t * t + b;
			return c / 2 * ((t -= 2) * t * t * t * t + 2) + b;

		case Tween.easeinsine:
			// sinusoidal (sin(t)) easing in - accelerating from zero velocity
			return -c * Math.cos(t / d * (Math.PI / 2)) + c + b;
		case Tween.easeoutsine:
			// sinusoidal (sin(t)) easing out - decelerating to zero velocity
			return c * Math.sin(t / d * (Math.PI / 2)) + b;
		case Tween.easeinoutsine:
			// sinusoidal (sin(t)) easing in/out - acceleration until halfway, then deceleration
			return -c / 2 * (Math.cos(Math.PI * t / d) - 1) + b;

		case Tween.easeinexpo:
			// exponential (2^t) easing in - accelerating from zero velocity
			return (t == 0) ? b : c * Math.pow(2, 10 * (t / d - 1)) + b;
		case Tween.easeoutexpo:
			// exponential (2^t) easing out - decelerating to zero velocity
			return (t == d) ? b + c : c * (-Math.pow(2, -10 * t / d) + 1) + b;
		case Tween.easeinoutexpo:
			// exponential (2^t) easing in/out - acceleration until halfway, then deceleration
			if (t == 0)
				return b;
			if (t == d)
				return b + c;
			if ((t /= d / 2) < 1)
				return c / 2 * Math.pow(2, 10 * (t - 1)) + b;
			return c / 2 * (-Math.pow(2, -10 * --t) + 2) + b;

		case Tween.easeincirc:
			// circular (sqrt(1-t^2)) easing in - accelerating from zero velocity
			return -c * (Math.sqrt(1 - (t /= d) * t) - 1) + b;
		case Tween.easeoutcirc:
			// circular (sqrt(1-t^2)) easing out - decelerating to zero velocity
			return c * Math.sqrt(1 - (t = t / d - 1) * t) + b;
		case Tween.easeinoutcirc:
			// circular (sqrt(1-t^2)) easing in/out - acceleration until halfway, then deceleration
			if ((t /= d / 2) < 1)
				return -c / 2 * (Math.sqrt(1 - t * t) - 1) + b;
			return c / 2 * (Math.sqrt(1 - (t -= 2) * t) + 1) + b;

		case Tween.easeinelastic:
			// elastic (exponentially decaying sine wave)
			if (t == 0)
				return b;
			if ((t /= d) == 1)
				return b + c;
			if (!p)
				p = d * .3;
			if (a < Math.abs(c)) {
				a = c;
				s = p / 4;
			} else
				s = p / (2 * Math.PI) * Math.asin(c / a);
			return -(a * Math.pow(2, 10 * (t -= 1)) * Math.sin((t * d - s)
					* (2 * Math.PI) / p))
					+ b;
		case Tween.easeoutelastic:
			// elastic (exponentially decaying sine wave)
			if (t == 0)
				return b;
			if ((t /= d) == 1)
				return b + c;
			if (!p)
				p = d * .3;
			if (a < Math.abs(c)) {
				a = c;
				s = p / 4;
			} else
				s = p / (2 * Math.PI) * Math.asin(c / a);
			return a * Math.pow(2, -10 * t)
					* Math.sin((t * d - s) * (2 * Math.PI) / p) + c + b;
		case Tween.easeinoutelastic:
			// elastic (exponentially decaying sine wave)
			if (t == 0)
				return b;
			if ((t /= d / 2) == 2)
				return b + c;
			if (!p)
				p = d * (.3 * 1.5);
			if (a < Math.abs(c)) {
				a = c;
				s = p / 4;
			} else
				s = p / (2 * Math.PI) * Math.asin(c / a);
			if (t < 1)
				return -.5
						* (a * Math.pow(2, 10 * (t -= 1)) * Math
								.sin((t * d - s) * (2 * Math.PI) / p)) + b;
			return a * Math.pow(2, -10 * (t -= 1))
					* Math.sin((t * d - s) * (2 * Math.PI) / p) * .5 + c + b;

			// Robert Penner's explanation for the s parameter (overshoot ammount):
			//  s controls the amount of overshoot: higher s means greater overshoot
			//  s has a default value of 1.70158, which produces an overshoot of 10 percent
			//  s==0 produces cubic easing with no overshoot
		case Tween.easeinback:
			// back (overshooting cubic easing: (s+1)*t^3 - s*t^2) easing in - backtracking slightly, then reversing direction and moving to target
			if (s == undefined)
				s = 1.70158;
			return c * (t /= d) * t * ((s + 1) * t - s) + b;
		case Tween.easeoutback:
			// back (overshooting cubic easing: (s+1)*t^3 - s*t^2) easing out - moving towards target, overshooting it slightly, then reversing and coming back to target
			if (s == undefined)
				s = 1.70158;
			return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
		case Tween.easeinoutback:
			// back (overshooting cubic easing: (s+1)*t^3 - s*t^2) easing in/out - backtracking slightly, then reversing direction and moving to target, then overshooting target, reversing, and finally coming back to target
			if (s == undefined)
				s = 1.70158;
			if ((t /= d / 2) < 1)
				return c / 2 * (t * t * (((s *= (1.525)) + 1) * t - s)) + b;
			return c / 2 * ((t -= 2) * t * (((s *= (1.525)) + 1) * t + s) + 2)
					+ b;

			// This were changed a bit by me (since I'm not using Penner's own Math.* functions)
			// So I changed it to call getValue() instead (with some different arguments)
		case Tween.easeinbounce:
			// bounce (exponentially decaying parabolic bounce) easing in
			return c - getValue(0, c, 0, d - t, d, Tween.easeoutbounce) + b;
		case Tween.easeoutbounce:
			// bounce (exponentially decaying parabolic bounce) easing out
			if ((t /= d) < (1 / 2.75)) {
				return c * (7.5625 * t * t) + b;
			} else if (t < (2 / 2.75)) {
				return c * (7.5625 * (t -= (1.5 / 2.75)) * t + .75) + b;
			} else if (t < (2.5 / 2.75)) {
				return c * (7.5625 * (t -= (2.25 / 2.75)) * t + .9375) + b;
			} else {
				return c * (7.5625 * (t -= (2.625 / 2.75)) * t + .984375) + b;
			}
		case Tween.easeinoutbounce:
			// bounce (exponentially decaying parabolic bounce) easing in/out
			if (t < d / 2)
				return getValue(0, c, 0, t * 2, d, Tween.easeinbounce) * .5 + b;
			return getValue(0, c, 0, t * 2 - d, d, Tween.easeoutbounce) * .5
					+ c * .5 + b;
		}
		return c * t / d + b;
	}
	//{animiType
	Tween.linear = "linear";
	Tween.easeinquad = "easeinquad";
	Tween.easeoutquad = "easeoutquad";
	Tween.easeinoutquad = "easeinoutquad";
	Tween.easeincubic = "easeincubic";
	Tween.easeoutcubic = "easeoutcubic";
	Tween.easeinoutcubic = "easeinoutcubic";
	Tween.easeinquart = "easeinquart";
	Tween.easeoutquart = "easeoutquart";
	Tween.easeinoutquart = "easeinoutquart";
	Tween.easeinquint = "easeinquint";
	Tween.easeoutquint = "easeoutquint";
	Tween.easeinoutquint = "easeinoutquint";
	Tween.easeinsine = "easeinsine";
	Tween.easeoutsine = "easeoutsine";
	Tween.easeinoutsine = "easeinoutsine";
	Tween.easeinexpo = "easeinexpo";
	Tween.easeoutexpo = "easeoutexpo";
	Tween.easeinoutexpo = "easeinoutexpo";
	Tween.easeincirc = "easeincirc";
	Tween.easeoutcirc = "easeoutcirc";
	Tween.easeinoutcirc = "easeinoutcirc";
	Tween.easeinelastic = "easeinelastic";
	Tween.easeoutelastic = "easeoutelastic";
	Tween.easeinoutelastic = "easeinoutelastic";
	Tween.easeinback = "easeinback";
	Tween.easeoutback = "easeoutback";
	Tween.easeinoutback = "easeinoutback";
	Tween.easeinbounce = "easeinbounce";
	Tween.easeoutbounce = "easeoutbounce";
	Tween.easeinoutbounce = "easeinoutbounce";

	

	// 设置当前时间
	function setCurrentTime(){
		var date = new Date();
		$('#hourAndMinu').html(date.getHours() + ":" + convert(date.getMinutes())+":");
		$('#seconds').html(convert(date.getSeconds()));
		$('#weekDay').html(convertToWeekDay(date.getDay()));
		$('#currentDay').html(date.getFullYear()+"年"+ (convert(date.getMonth()+1)) +"月"+ convert(date.getDate())+"日");
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
	
	$("#cancel").click(function(){
		$("#username").val("");
		$("#password").val("");
	});
	
	$("#check_num").click(function(){
		$("#check_num").attr('style','background:white');
	});
	
	$("#username").click(function(){
		$("#username").attr('style','background:white');
	});
	
	$("#password").click(function(){
		$("#password").attr('style','background:white');
		$("#password").trigger("select");
	});
	
	function beforeSubmit(){
		if($("#username").val()=='' || $("#password").val()==''){
		   return false;
		}
		if($("#password").val().substr(0,RSAEncrypted.length)!=RSAEncrypted){
			setMaxDigits(130);
			var key = new RSAKeyPair("${empoent}","","${module}");   
			var result = encryptedString(key,$("#password").val());
			$("#password").val(result);
		}
		$("#RSAEncrypted").val(RSAEncrypted);
		return true;
	}
	function getCookie(name){  
	    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));  
	    if(arr != null){  
	     return unescape(arr[2]);   
	    }else{  
	     return null;  
	    }  
	} 
	
	//获取链接参数 
	(function ($) {
         $.getUrlParam = function (name) {
             var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
             var r = window.location.search.substr(1).match(reg);
             if (r != null) return unescape(r[2]); return null;
         }
     })(jQuery);
	
	function initInput(){
		var clearType=$.getUrlParam('clearType');
		var un = getCookie("loginName");
		var pw = getCookie("userPwd");
		if(un&&pw){
			$("#username").val((new Base64()).decode(un));
			$("#password").val(pw);
			if(clearType=='clearPassword'){//重置密码时页面密码取消自动填充
				$("#password").val("");
			}
			
		}
		
	}
</script>
</html>
