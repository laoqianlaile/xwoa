<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<title>学习园地</title>
<%-- <link href="${pageContext.request.contextPath}/themes/oaBbs/style.css" rel="stylesheet" type="text/css" /> --%>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/styles/default/css/oabbs/bbs.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
a {
	text-decoration: none !important;
	cursor: pointer;
}
/*新增样式*/
.page {
	background: #FBFBFB
}

#header-top {
	height: 80px;
	background: url(${contextPath}/themes/blue/images/1.png) center left
		no-repeat;
	background-color: #F0F0F0;
}

#header-bottom {
	height: 40px;
	background: url(${contextPath}/themes/blue/images/bannerbg.png)
}

body {
	padding-left: 0;
	margin: auto;
	background: #e7e7e7;
}

.header-top-left {
	float: left;
}

.header-top-left li {
	float: left;
	list-style: none;
	width: 83px;
	height: 24px;
	margin-right: 10px;
	background: url(${contextPath}/themes/blue/images/2.png);
	text-align: center;
}

.header-top-left .select {
	background: url(${contextPath}/themes/blue/images/3.png)
}
.header-top-left li a{
     color: white;
     line-height: 44px;
     font-weight: 400;
     font-size: 16px;
     font-family: "Microsoft YaHei";
     text-decoration: none
	 margin-left: 1px;
}
.header-top-left li a.current {
    width: 100%;
    text-align: center;
    float: left;
    color: white;
    text-decoration: none margin-left: 1px;
    background: #B10800;
    line-height: 34px;
    font-weight: 400;
    font-size: 16px;
    font-family: "Microsoft YaHei";
    position: relative;
    top: 12px;
}
.header-top-left li a:hover{
width: 100%;
	text-align: center;
	float: left;
	color: white;
     background: #B10800 url("${contextPath}/themes/blue/images/nv_a.png") no-repeat 50% -44px;
}
.header-top-right {
	float: right;
	margin-right: 40px;
	width: 280px; 
/* 	padding-top: 10px; */
	color: #8c8c8c;
	line-height: 16px;
	
}

.header-top-right img { /* 	float: left; */
	margin-right: 10px
}
.header-top-right img:hover { /* 	float: left; */
    cursor: pointer;;
	margin-right: 10px;

	border-radius:33px;
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

#infoView {
	width: 80%;
	margin:0 auto;
/* 	background-color:white; */
}

#header {

	height: 47px;
	margin: auto
}

#infoTab .select {
	top: 1px;
	font-weight: bold;
	cursor: default;
}

#infoTab .current {
	border: 1px solid #ff0000;
	border-bottom: none;
	color: #ff0000;
}

#infoTab .disable {
	cursor: default;
	border: 1px solid #ddd;
	border-bottom: none;
	color: #ddd;
	font-weight: bold;
}

#editTheme {
	margin-right: 60px;
	margin-bottom: 5px
}

#leftSide ul.level2 {
	position: absolute;
	left: 220px;
	top: 70px;
	display: none;
}

.tool {
	width: 70px;
	margin: auto
}

.tool .jf {
	float: left;
	padding: 2px;
}

.tool .dj {
	float: left;
	padding: 2px;
	border-top: 1px solid rgb(209, 209, 209)
}

li {
	cursor: default;
}
#nv{  background: #2F2F2F url("${contextPath}/themes/blue/images/nv.png") repeat-x 0 0;}
#nv.comiis_nv li{
      float: left;
	  padding-right: 2px;
	  height: 47px;
	  line-height: 44px;
	  background: url(${contextPath}/themes/blue/images/nv_a.png) no-repeat 100% 0;
	  font-weight: 400;
	  font-size: 16px;
	  font-family: "Microsoft YaHei";
}
#nv li,#top-userinfo {
       color:#fff;
}
</style>
</head>


<body>
	<form action="" id="oaHelpinfoForm" method="post" style="margin: 0;">
		<input type="hidden" id="s_layoutcode" name="s_layoutcode"
			value="${s_layoutcode}">
	</form>
 
	<%-- 此项目屏蔽头部的效果 <div id="header">
		<div id="nv" class="comiis_nv" style="top: 0px; left: 10%; z-index: 199; border-left-width: 0px; border-right-width: 0px; height: 47px; width: 80%; position: fixed; opacity: 0.85;">
			<ul class="header-top-left" id="top-infoTab">
				<li url="${pageContext.request.contextPath}/bbs/oaBbsDashboard!showBbsMainPage.do" target="_blank" rel="external_OAHELPINFO" external="true"><a class="current" >讨论区</a></li>
                <!-- <li url="${pageContext.request.contextPath}/oa/oaSubvideoInformation!oasubvideoMainpage.do" target="_blank" rel="external_OAHELPINFO" external="true"><a >视频节目</a></li> -->				
			</ul>
			<div class="header-top-right">
				<a id="top-userinfo"
					url="${contextPath }/oa/oaUserinfo!generalOptView.do?s_usercode=${oaUserinfo.usercode}">
								src='${contextPath }/oa/oaUserinfo!showImage.do?usercode=${oaUserinfo.usercode}'
								width="45px" height="45px" />
					<span><br> ${cp:MAPVALUE('usercode',oaUserinfo.usercode) }</span>

				</a>
			</div>
		</div>
	</div>  --%>
	
	
	<div id="infoView">
		<!-- 修改id，不进入系统的comm*.js,没有iframe style -->
		<iframe id="tabFrames1" name="tabFrames1"
			src="<%=request.getContextPath()%>/bbs/oaBbsDashboard!showBbsMainPage.do"
			onload="onLoadHeight(this);" width="100%" frameborder="0"
			scrolling="no" border="0" marginwidth="0"></iframe>
	</div>

	<!-- 选择人员的模块 -->
	<div id="attAlert" class="alert"
		style="width: 400px; height: 330px; position: absolute; top: 120px; left: 20%; overflow: hidden;">
		<h4>
			<span id="selectTT">讨论版导航</span><span id="close2"
				style="float: right; margin-right: 8px;" class="close"
				onclick="closeAlert('attAlert');">关闭</span>

		</h4>
		<h4>
			<!-- 			<a id="editTheme"  url="">我要发帖</a>  -->
			<input type="button" class="btn" id="editTheme" url=""
				<%-- 				onclick="javascript:window.location.href='${pageContext.request.contextPath}/bbs/oaBbsTheme!edit.do';" --%>
				value="我要发帖" />
		</h4>
		<div class="fix">
			<div id="leftSide">
				<ul class="level1" style="display: block !important">
					<c:forEach var="row" items="${objList}">
						<c:if test="${'T' eq row.isShowTime }">
							<li nodeID="${row.layoutcode}">${row.layoutname}

								<ul class="level2">
									<c:forEach items="${row.oaBbsDiscussions}" var="discussions"
										varStatus="ts">
										<c:if test="${'T' eq discussions.isShowTime }">
											<li pnodeId="${dashboard.layoutcode}"
												nodeID="${discussions.layoutno}">${discussions.sublayouttitle}</li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
						</c:if>
					</c:forEach>
				</ul>

			</div>



		</div>
	</div>
</body>
<script type="text/javascript">
	function onLoadHeight(t) {
		var height = window.frames['tabFrames1'].document.body.scrollHeight + 16;
		if (height > 500) {
			t.height = height;
		} else {
			t.height = 500;
		}
	}

	$(function() {
		var layoutcode = $("#s_layoutcode").val();
		if (layoutcode != '') {
			$('#infoTab li').each(function() {
				alert(layoutcode);
				var $this = $(this);
				var url = $this.attr('url');
				if (url) {
					if (url.indexOf(layoutcode) > -1) {
						$("#infoTab li a").removeClass("current");
						$this.addClass("current");
						$("#tabFrames1").attr("src", $this.attr("url"));
					}
				}
			});
		}
	});

	$(function() {
		$("#infoTab").click(
				function(e) {

					var e = e || window.event;
					var target = e.srcElement || e.target;
					var isShowTime = $(target).data("isshowtime");
					var s_layoutcode = $(target).data("layoutcode");

					if (target.tagName.toLowerCase() == "li"
							&& $(target).attr("class") != "disable") {

						// 						if ("T" == isShowTime) {//版面是否开放
						$("#s_layoutcode").attr("value", s_layoutcode);//版面代码

						// 							if (!$(target).hasClass("select")) {
						$("#infoTab li a").removeClass("current");
						$(target).addClass("current");
						$("#tabFrames1").attr("src", $(target).attr("url"));
						// 							}
						// 						} else {
						// 							alert("未到模块开放时间，敬请期待！");
						// 						}
					}

				});
	});

	$(function() {
		$("#top-infoTab").click(function(e) {
			var e = e || window.event;
			var target = e.srcElement || e.target;

			$("#top-infoTab li a.current").removeClass("current");
			$(target).addClass("current");
			$("#tabFrames1").attr("src", $(target).parent().attr("url"));
		});

		$("#top-userinfo").click(function() {
			$("#tabFrames1").attr("src", $(this).attr("url"));
		});

	});

	$('#addTheme').live('click', function() {
		setAlertStyle("attAlert");

	});
	$(document).ready(

	function() {
		$("#editTheme").attr("disabled", "disabled");

		$("#editTheme").click(function() {
			$("#tabFrames1").attr("src", $(this).attr("url"));

		});

		$('#addTheme').live('click', function() {
			setAlertStyle("attAlert");

		});

		//控制二级菜单显示
		$("#leftSide>ul>li").live('click', function() {
			$('#leftSide ul.level2').hide();
			$(this).find('ul').show();
			// 					$("#editTheme").attr("disabled","disabled");
		});

		$(".level2>li").live('click', function() {
			url = "${pageContext.request.contextPath}/bbs/oaBbsTheme!edit.do";
			url = url + "?s_layoutno=" + $(this).attr("nodeID");
			href = "javascript:window.location.href='" + url + "';";

			// 											$("#editTheme").attr("onclick",
			// 													href);
			$("#editTheme").attr("url", url);
			$("#editTheme").removeAttr("disabled");

		});

		$(".showTime").live('click', function() {

			var isShowTime = $(this).data("isshowtime");
			var href = $(this).data('href');
			if ("T" == isShowTime) {
				window.location.href = href;
			} else {

			}
		});
	});
	//消息框
	function alert(str, caption) {
		this.disappear = function() {
			$("#bgdiv").remove();
			$("#msgdiv").remove();
			$("#msgtitle").remove();
			msgobj = null;
			msgdiv = null;
		};
		disappear();
		this.m_text = str;
		this.m_caption = caption;
		this.m_width = 200;
		this.m_height = 100;
		this.m_wait = 1000;
		this.m_fade = 500;
		this.m_bordercolor = "#336699";
		this.m_titlecolor = "#99ccff";
		var msgw, msgh, bordercolor;
		msgw = m_width;//提示窗口的宽度
		msgh = m_height;//提示窗口的高度
		titleheight = 25 //提示窗口标题高度
		bordercolor = m_bordercolor;//提示窗口的边框颜色
		titlecolor = m_titlecolor;//提示窗口的标题颜色

		var swidth, sheight;
		swidth = document.body.offsetWidth;
		sheight = document.body.offsetHeight;
		if (sheight < screen.height) {
			sheight = screen.height;
		}
		//此处可以添加一个背景，防止多次点击保存按钮
		if (bgobj == undefined) {
			var bgobj = document.createElement("div");
		}
		bgobj.setAttribute('id', 'bgdiv');
		bgobj.style.position = "absolute";
		bgobj.style.top = "0";
		bgobj.style.background = "#777";
		bgobj.style.filter = "progid:dximagetransform.microsoft.alpha(style=3,opacity=25,finishopacity=75";
		bgobj.style.opacity = "0.6";
		bgobj.style.left = "0";
		bgobj.style.width = swidth + "px";
		bgobj.style.height = sheight + "px";
		bgobj.style.zindex = "10000";
		document.body.appendChild(bgobj);
		if (msgobj == null) {
			var msgobj = document.createElement("div");
		}
		msgobj.setAttribute("id", "msgdiv");
		msgobj.setAttribute("align", "center");
		msgobj.style.background = "white";
		msgobj.style.border = "1px solid " + bordercolor;
		msgobj.style.position = "absolute";
		msgobj.style.left = "50%";
		msgobj.style.top = "50%";
		msgobj.style.font = "12px/1.6em verdana, geneva, arial, helvetica, sans-serif";
		msgobj.style.marginLeft = "-115px";
		msgobj.style.marginTop = -115 + document.documentElement.scrollTop
				+ "px";
		msgobj.style.width = msgw + "px";
		msgobj.style.height = msgh + "px";
		msgobj.style.textAlign = "center";
		msgobj.style.lineHeight = (msgh - titleheight) + "px";
		msgobj.style.zIndex = "10001";
		if (title == null) {
			var title = document.createElement("h4");
		}
		title.setAttribute("id", "msgtitle");
		title.setAttribute("align", "left");
		title.style.margin = "0";
		title.style.padding = "3px";
		title.style.background = bordercolor;
		title.style.filter = "progid:dximagetransform.microsoft.alpha(startx=20, starty=20, finishx=100, finishy=100,style=1,opacity=75,finishopacity=100);";
		title.style.opacity = "0.75";
		title.style.border = "1px solid " + bordercolor;
		title.style.height = "18px";
		title.style.font = "12px verdana, geneva, arial, helvetica, sans-serif";
		title.style.color = "white";
		title.style.cursor = "pointer";
		title.innerHTML = "消息提示";
		title.onclick = function() {
			disappear();
		}
		document.body.appendChild(msgobj);
		document.getElementById("msgdiv").appendChild(title);
		var txt = document.createElement("p");
		txt.style.margin = "1em 0"
		txt.setAttribute("id", "msgtxt");
		txt.innerHTML = str;
		document.getElementById("msgdiv").appendChild(txt);

		this.fadeout = function() {
			$("#bgdiv").fadeOut(800);
			$("#msgdiv").fadeOut(800);
			$("#msgtitle").fadeOut(800, function() {
				disappear()
			});
		}
		setTimeout("fadeout()", 500);
	};
</script>
</html>
