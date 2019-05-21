<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title></title>
<style type="text/css">
#infoTab_BBS {
	position: relative;
	line-height: 43px;
	border-bottom: 1px solid #bbb;
	float: left;
	overflow: auto;
	padding-top: 1px;
	width: 12%;
	top: 20px;
}

#infoTab_BBS li {
	cursor: pointer;
	padding: 0 15px;
	border: 1px solid white;
	border-bottom: none;
	list-style: none;
	text-align: center;
	background-color: #59B5E8;
	color: white;
}

#infoTab_BBS li:hover {
	background-color: #0081CD;
}

.panel_left {
	display: none;
	position: absolute;
	left: 12.55%;
	top: 18%;
	border: 1px solid red;
	background: #fff;
	border: 1px solid #CECECE
}

.panel_left .panel_p {
	position: absolute;
	top: 20px;
	width: 7px;
	height: 15px;
	left: -7%;
	background:
		url(${pageContext.request.contextPath}/styles/default/css/tvimage/panel_left.png)
		no-repeat scroll;
}

.panel_left ul li {
	padding-bottom: 10px;
	width: 100px;
	line-height: 28px;
	text-align: center;
	cursor: pointer;
}

.panel_left ul li {
	color: #007E8C;
	font-weight:700;
	font-size:12px;
	font-family:"Arial,宋体"
	letter-spacing: 2px;
}

#infoView {
	border-top: none;
	padding: 0px 10px 10px 0px;
	float: right;
	width: 86%;
}

#infoTab_BBS .select {
	top: 1px;
	font-weight: bold;
	cursor: pointer;
	background-color: #0081CD;
}

#infoTab_BBS .current {
	border: 1px solid #ff0000;
	border-bottom: none;
	color: #ff0000;
}

#infoTab_BBS .disable {
	cursor: default;
	border: 1px solid #ddd;
	border-bottom: none;
	color: #ddd;
	font-weight: bold;
}

#infoView fieldset {
	display: none;
}
/* /*新增样式*/
#header-top {
	height: 80px;
	background: url(${contextPath}/themes/blue/images/1.png) center left
		no-repeat
}

#header-bottom {
	height: 40px;
	background: url(${contextPath}/themes/blue/images/bannerbg.png)
}

body {
	padding-left: 0;
	margin: auto;
	width: 100%
}

.header-top-left {
	float: left;
	margin-left: 200px;
	padding-top: 30px;
}

.header-top-left li {
	float: left;
	list-style: none;
	width: 83px;
	height: 24px;
	margin-right: 10px;
	background: url(${contextPath}/themes/blue/images/2.png)
}

.header-top-left .select {
	background: url(${contextPath}/themes/blue/images/3.png)
}

.header-top-left li a {
	width: 100%;
	text-align: center;
	line-height: 24px;
	float: left;
	color: white;
	text-decoration: none
}

.header-top-right {
	float: right;
	margin-right: 20px;
	width: 280px;
	padding-top: 20px;
	color: #8c8c8c
}

.header-top-right img {
	float: left;
	margin-right: 10px
}

.header-top-right li {
	float: left;
	list-style: none;
	padding: 0px 10px 0px 0px;
}

.header-top-right li a {
	color: #8c8c8c;
	text-decoration: none
}

.title a {
	float: left;
	line-height: 30px
}

#header-bottom ul li {
	float: left;
	height: 40px;
	line-height: 40px;
	margin-right: 20px;
	margin-left: 5px;
	list-style: none;
	color: white
}

#header-bottom li a {
	color: white;
	text-decoration: none
}
*
/
</style>
</head>

<body>
	<ul id="infoTab_BBS">
		<li
			url="<%=request.getContextPath()%>/bbs/oaBbsTheme!generalOptView.do?s_usercode=${s_usercode}"
			${empty curUrl ? "" : "class='select'"}>帖子</li>


		<li
			url="<%=request.getContextPath()%>/bbs/oaBbsTheme!attentionThemeList.do?s_usercode=${s_usercode}&s_laytype=S">

			<c:if
				test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode==s_usercode }">
				我的</c:if>
			<c:if
				test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode!=s_usercode }">
				他的</c:if>收藏
		</li>


		<li
			url="<%=request.getContextPath()%>/bbs/oaBbsTheme!attentionThemeList.do?s_usercode=${s_usercode}&s_laytype=G">
			<c:if
				test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode==s_usercode }">
				我的</c:if>
			<c:if
				test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode!=s_usercode }">
				他的</c:if>关注
		</li>

		<li id="last_li"
			url="<%=request.getContextPath()%>/oa/oaUserinfo!userinfoView.do?s_usercode=${s_usercode}&flag=table0">个人资料</li>

	</ul>
	<%-- <div class="panel_left">
		<p class="panel_p"></p>
		<ul id="panel_left_ul">
			<li
				url="<%=request.getContextPath()%>/oa/oaUserinfo!userinfoView.do?s_usercode=${s_usercode}&flag=table0">基本资料</li>
			<li
				url="<%=request.getContextPath()%>/oa/oaUserinfo!userinfoView.do?s_usercode=${s_usercode}&flag=table1">联系方式</li>
			<li
				url="<%=request.getContextPath()%>/oa/oaUserinfo!userinfoView.do?s_usercode=${s_usercode}&flag=table2">教育情况</li>
				
			<li
				url="<%=request.getContextPath()%>/oa/oaUserinfo!userinfoView.do?s_usercode=${s_usercode}&flag=table3">个人信息</li>
		</ul>
	</div> --%>
	<s:form action="oaUserinfo" method="post" namespace="/bbs"
		styleId="oaUserinfo">
		<input type="hidden" name="s_usercode" value="${s_usercode }" />
		</br>


		<div id="infoView">
			<!-- 修改id，不进入系统的comm*.js,没有iframe style -->
			<iframe id="tabFrames1" name="tabFrames1"
				src="<%=request.getContextPath()%>${curUrl}"
				onload="onLoadHeight(this);" width="100%" frameborder="0"
				scrolling="no" border="0" marginwidth="0"></iframe>
		</div>

	</s:form>
</body>

<script type="text/javascript">
	//右边隐藏效果
	$('#last_li').hover(function() {
		$('.panel_left').show();
	}, function() {
		$('.panel_left').hide();
	});
	$(".panel_left").hover(function() {
		$(this).show();
	}, function() {
		$(this).hide();
	});
	function onLoadHeight(t) {
		var height = window.frames['tabFrames1'].document.body.scrollHeight + 100;
		if (height > 600) {
			t.height = height;
		} else {
			t.height = 600;
		}

	}
	function sNav() {
		$("#infoTab_BBS li.current").addClass("select");
		$("#infoTab_BBS").click(
						function(e) {
					
							var e = e || window.event;
							var target = e.srcElement || e.target;
							if (target.tagName.toLowerCase() == "li"&& $(target).attr("class") != "disable") {
									$("#infoTab_BBS li").removeClass("select");
									$(target).addClass("select");
									$("#tabFrames1").attr("src",$(target).attr("url"));
							}
		});
		/* $("#panel_left_ul li").click(function(e){
			var e = e || window.event;
			var target = e.srcElement || e.target;
			if (target.tagName.toLowerCase() == "li"&& $(target).attr("class") != "disable") {
				$("#infoTab_BBS li.select").removeClass("select");
				$("#infoTab_BBS li").eq($(this).index('.panel_left')).addClass("select");
				$("#tabFrames1").attr("src",$(target).attr("url"));
				$('.panel_left').hide(200);
			}
		}); */
	}
	sNav();
</script>
</html>