<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />

<link
	href="${pageContext.request.contextPath}/styles/default/css/oabbs/bbs.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
.bar table {
	margin-top: 15px;
}

.bar table td {
	border: none;
	border-bottom: 1px solid #ececec;
	padding: 10px 0;
	/* 	width: 33%; */
}

td img {
	float: left;
	vertical-align: middle;
	margin-right: 10px;
	margin-top: 5px;
	margin-left: 15px;
	width: 82px;
	height: 57px;
}

td span {
	font: 12px/1.5 '微软雅黑';
}

a {
	text-decoration: none !important;
}

.title {
	height: 35px;
	background: #f0f0f0;
	line-height: 30px;
	/* 	padding-left: 15px; */
	margin-top: 30px;
}

.title_backcolor {
	background: url(${ctx}/themes/blue/images/comiis_bm_h.gif) no-repeat;
	height: 34px;
	overflow: hidden;
	padding-left: 15px;
	float: left;
}

.title_backcolor a {
	float: left;
	background: url(${ctx}/themes/blue/images/comiis_bm_h.gif) no-repeat
		100% 100%;
	color: #fff;
	height: 34px;
	padding-right: 35px;
}

a {
	text-decoration: none;
}

.top {
	margin-top: 10px;
	padding-left: 15px;
}

.top input {
	float: right
}

.red {
	color: red;
	/* cursor: pointer; */
}

.childBlockTile_right {
	float: right;
	margin-top: 10px;
	vertical-align: middle;
	padding-right: 15px;
}

a {
	cursor: pointer;
}
/*新增样式*/
/* #header-top{height:80px; background:url(${contextPath}/themes/blue/images/1.png) center left no-repeat}
#header-bottom{height:40px;background:url(${contextPath}/themes/blue/images/bannerbg.png)}
body{padding-left:0;margin:auto}
.header-top-left{float:left;margin-left:200px;padding-top:30px;}
.header-top-left li{float:left;list-style:none;width:83px;height:24px;margin-right:10px;background:url(${contextPath}/themes/blue/images/2.png)}
.header-top-left .select{background:url(${contextPath}/themes/blue/images/3.png)}
.header-top-left li a{width:100%;text-align:center;line-height:24px;float:left;color:white;text-decoration: none}
.header-top-right{float:right;margin-right:20px;width:280px;padding-top:20px;color:#8c8c8c}
.header-top-right img{float:left;margin-right:10px}
.header-top-right li{float:left;list-style:none;padding:0px 10px 0px 0px;}
.header-top-right li a{color:#8c8c8c;text-decoration: none}
.title a{float:left;line-height:30px}
#header-bottom ul li{float:left;height:40px;line-height:40px;margin-right:20px;margin-left:5px;list-style:none;color:white}
#header-bottom li a{color:white;text-decoration: none} 
 */
</style>
</head>


<body>
	<%-- <div id="header">
       <div id="header-top">
          <ul class="header-top-left">
          <li><a>首页</a></li>
          <li class="select"><a>讨论区</a></li>
          <li><a>视频节目</a></li>
          <li><a>新闻动态</a></li>
          </ul>
          <div class="header-top-right">
             <img alt="" src="${contextPath}/themes/blue/images/14.png">
             <div>
                  <div>id:123456789</div>
                  <ul>
		          <li><a>设置</a></li>
		          <li>|</li>
		          <li><a>消息</a></li>
		          <li>|</li>
		          <li><a>提醒</a></li>
		          <li>|</li>
		          <li><a>退出</a></li>
		          </ul>
             </div>
          </div>
       </div>
       <div id="header-bottom">
             <ul>
		          <li><a>办公信息库</a></li>
		          <li><a>办公管理.软件系统</a></li>
		          <li><a>办公管理.硬件设备</a></li>
		          <li><a>综合管理中心</a></li>
		     </ul>
       </div>
   </div> --%>

	<form id="searchForm"
		action="${pageContext.request.contextPath}/bbs/oaBbsTheme!showThemeMainPage.do"
		method="post">

		<div class="public-position" id="publicPosition">
			<div class="pubpos-nav">
				今日帖子数：<span style="color: red">${oaBbsDashboard.todayThemeNum }</span><span
					style="margin: 0px 5px 0px 5px;">|</span>昨日帖子数：<span style="color: red">${oaBbsDashboard.preThemeNum
					}</span><span style="margin: 0px 5px 0px 5px">|</span>总帖子数：<span
					style="color: red">${oaBbsDashboard.themeNum }</span> <span
					style="margin: 0px 5px 0px 5px">|</span><a
					href="${pageContext.request.contextPath}/bbs/oaBbsTheme!showThemeMainPage.do?s_creater=${oaUserinfo.usercode}">我的帖子</a>
				<span style="margin: 0px 5px 0px 5px">|</span><a
					href="${pageContext.request.contextPath}/bbs/oaBbsTheme!showThemeMainPage.do?s_timeType=1">今日新帖</a>
			</div>
			<div class="pubpos-search-box">
				<span><input type="button" class="btn" id="addTheme"
					value="我要发帖" style="float: left;" /></span> <input name="s_search"
					class="search-txt" value="${s_search}" id="txt_searchWord_nav"
					type="text" size="100" placeholder="请输入关键词"> <input
					name="submit" class="submit-botton" id="home_btnSearchSubmit"
					type="submit" value="搜 索">
			</div>
		</div>
	</form>
	<c:forEach var="dashboard" items="${objList }" varStatus="s">
		<div class="bar">
			<div class="title   showTimeTd" >
				<c:if
					test="${null eq dashboard.isShowTime or 'T' ne  dashboard.isShowTime}">
					<i class="icon icon-blue icon-locked" style="float: left;"></i>

				</c:if>
				<h2 class="title_backcolor">
					<a class="showTime " data-isshowtime="${dashboard.isShowTime }"
						data-href="${pageContext.request.contextPath}/bbs/oaBbsDashboard!showBbsDisMainPage.do?layoutcode=${dashboard.layoutcode}">
						${ dashboard.layoutname}</a>
					<c:if
						test="${null eq dashboard.isShowTime or 'T' ne  dashboard.isShowTime}">
						<div class="openTimeTipClass">该模块还未到开放时间！<br/>${dashboard.openTimeTip }<br/>${ dashboard.openTimeTipEnd}</div>
					</c:if>
				</h2>
				<img class="has_children childBlockTile_right"
					style="width: 17px; height: 17px; vertical-align: middle; margin-left: 5px;"
					src='${ctx}/themes/blue/images/collapsed_no.gif' />

			</div>
			<c:if test="${not empty dashboard.oaBbsDiscussions }">

				<table border="0" cellpadding="0" cellspacing="0">
					<tbody align="left">
						<c:forEach items="${dashboard.oaBbsDiscussions}" var="discussions"
							varStatus="ts">

							<c:if test="${8 >= ts.index }">

								<c:if test="${ts.count eq 1 || ts.index % 3 eq 0}">
									<tr>
								</c:if>
<%-- 								title="该模块还未到开放时间！${discussions.openTimeTip }${ discussions.openTimeTipEnd}" --%>
								<td class="showTimeTd"><c:if
										test="${null eq dashboard.isShowTime or 'T' ne  dashboard.isShowTime or null eq discussions.isShowTime or 'T' ne  discussions.isShowTime}">
										<i class="icon icon-blue icon-locked"></i>

									</c:if> 
									
									<c:choose>

										<c:when test="${not empty discussions.showpicturename }">


											<img
												src='${contextPath }/bbs/oaBbsDiscussion!showImage.do?layoutno=${discussions.layoutno}' />
										</c:when>
										<c:otherwise>
											<img src='${ctx}/themes/blue/images/img.png' />
										</c:otherwise>
									</c:choose> <span> <a class="showTime"
										data-isshowtime="${discussions.isShowTime }"
										data-href="${pageContext.request.contextPath}/bbs/oaBbsTheme!showThemeMainPage.do?s_layoutno=${discussions.layoutno}">

											<font class="${discussions.colorOftitle}BBS"
											style="font-weight: 700;">${discussions.sublayouttitle}</font><span
											style="color: #F60; font-weight: 400; font-style: normal;">(${ empty discussions.todayThemenum ? 0 : discussions.todayThemenum
												})</span>

									</a> <c:if
											test="${null eq dashboard.isShowTime or 'T' ne  dashboard.isShowTime or null eq discussions.isShowTime or 'T' ne  discussions.isShowTime}">
											<c:choose>

												<c:when test="${null eq dashboard.isShowTime or 'T' ne  dashboard.isShowTime }">

													<div class="openTimeTipClass">该模块还未到开放时间！<br/>${dashboard.openTimeTip }<br/>${ dashboard.openTimeTipEnd}</div>
												</c:when>
												<c:otherwise>
													<div class="openTimeTipClass">该模块还未到开放时间！<br/>${discussions.openTimeTip }<br/>${ discussions.openTimeTipEnd}</div>
												</c:otherwise>
											</c:choose>
										</c:if> <br> 主题：${empty discussions.subjectnum ? 0 :
										discussions.subjectnum}<span style="margin: 0px 5px 0px 5px">|</span>帖数：${empty
										discussions.postsnum ? 0 : discussions.postsnum}
								</span></td>
								<c:if test="${ts.count % 3 eq 0 || ts.count eq 3}">

									</tr>
								</c:if>
							</c:if>
							<c:if test="${8 < ts.index }">
								<a
									href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!showBbsDisMainPage.do?layoutcode=${dashboard.layoutcode}'>
									更多模块 </a>

							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<c:if test="${ empty dashboard.oaBbsDiscussions}">
                暂无子模块，尽请期待。
      </c:if>
		</div>
	</c:forEach>

	<input type="hidden" value="" name="s_layoutcode" id="s_layoutcode" />
	<input type="hidden" value="" name="s_layoutno" id="s_layoutno" />

	<!-- 选择人员的模块 -->
	<div id="attAlert"
		style="width: 400px; height: 400px; display: none; z-index: 1; position: absolute; top: 60px; left: 20%;">

		<table cellpadding="0" cellspacing="0" class="fwin">
			<tbody>
				<tr>
					<td class="t_l"></td>
					<td class="t_c" style="cursor: move"></td>
					<td class="t_r"></td>
				</tr>
				<tr>
					<td class="m_l">&nbsp;&nbsp;</td>
					<td class="m_c" id="fwin_content_nav" fwin="nav" style="">


						<h4 class="flb" style="cursor: move">
							<em>讨论版导航</em><span><input type="button" id="editTheme"
								class="editThemeDisable"
								onclick="javascript:window.location.href='${pageContext.request.contextPath}/bbs/oaBbsTheme!edit.do';"
								value="我要发帖" /></span> <span id="close2" class="flbc"></span>

						</h4>

						<div class="fix">
							<div id="leftSide">
								<ul class="level1" style="display: block !important">
									<c:forEach var="row" items="${objList}">
										<c:if test="${'T' eq row.isShowTime }">
											<li nodeID="${row.layoutcode}">${row.layoutname}

												<ul class="level2">
													<c:forEach items="${row.oaBbsDiscussions}"
														var="discussions" varStatus="ts">
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

					</td>
					<td class="m_r"></td>
				</tr>
				<tr>
					<td class="b_l"></td>
					<td class="b_c"></td>
					<td class="b_r"></td>
				</tr>
			</tbody>
		</table>
	</div>



</body>

<script type="text/javascript">
	$(function() {

		var $search = $('#search');
		$search.bind('keypress', function(event) {
			if (event.keyCode == "13") {
				$('#searchForm').submit();
			}
		});

		$("#editTheme").attr("disabled", "disabled");

		$('.hidden_childen').live(
				'click',
				function() {
					$(this).addClass("has_children").removeClass(
							"hidden_childen").parent().next("table").show()
							.end();
					$(this).attr("src",
							"${ctx}/themes/blue/images/collapsed_no.gif");
				});
		$(".has_children")
				.live(
						'click',
						function() {
							$(this).addClass("hidden_childen").removeClass(
									"has_children").parent().next("table")
									.hide().end();
							$(this)
									.attr("src",
											"${ctx}/themes/blue/images/collapsed_yes.gif");
						});

		$('#addTheme').live('click', function() {
			var myAlert = document.getElementById("attAlert");
			myAlert.style.display = "block";
			// 			setAlertStyle("attAlert");
		});

		$('.flbc').live('click', function() {
			var myAlert = document.getElementById("attAlert");
			myAlert.style.display = "none";
			document.body.style.overflow = "auto";
		});
		//控制二级菜单显示
		$("#leftSide>ul>li").live('click', function() {
			/*添加選中效果*/
			$("#leftSide>ul>li").removeClass("switch-hover");
			$(this).addClass("switch-hover");
			$('#leftSide ul.level2').hide();

			/*發帖按鈕樣式*/
			// 			$("#editTheme").attr("disabled", "disabled");
			// 			$("#editTheme").attr("class","editThemeDisable");
			/*二級菜單顯示*/
			$(this).find('ul').show();
		});

		$(".level2>li").live('click', function() {
			/*發帖按鈕樣式*/

			$("#editTheme").attr("class", "editTheme");
			$("#editTheme").removeAttr("disabled");

			url = "${pageContext.request.contextPath}/bbs/oaBbsTheme!edit.do";
			url = url + "?s_layoutno=" + $(this).attr("nodeID");
			href = "javascript:window.location.href='" + url + "';";
			$("#editTheme").attr("onclick", href);

			/*添加選中效果*/
			$(".level2>li").removeClass("switch-hover");
			$(this).addClass("switch-hover");

		});

		$(".showTime").live('click', function() {

			var isShowTime = $(this).data("isshowtime");
			var href = $(this).data('href');
			if ("T" == isShowTime) {
				window.location.href = href;
			} else {
				
// 				$(this).next(".openTimeTipClass").fadeToggle('fast');
				
// 				$(this).next(".openTimeTipClass").delay(5000).fadeToggle('fast');

// 				var t = setTimeout($(this).next(".openTimeTipClass").fadeToggle(),50000);
// 				alert("t");
			}
		});
		$(".showTimeTd").hover( function() {
				$(this).find(".openTimeTipClass").fadeToggle('fast');
		});
		
		

		var clicked = "Nope.";
		var mausx = "0";
		var mausy = "0";
		var winx = "0";
		var winy = "0";
		var difx = mausx - winx;
		var dify = mausy - winy;

		$("html")
				.mousemove(
						function(event) {
							mausx = event.pageX;
							mausy = event.pageY;
							winx = $("#attAlert").offset().left;
							winy = $("#attAlert").offset().top;
							if (clicked == "Nope.") {
								difx = mausx - winx;
								dify = mausy - winy;
							}

							var newx = event.pageX
									- difx
									- $("#attAlert").css("marginLeft").replace(
											'px', '');
							var newy = event.pageY
									- dify
									- $("#attAlert").css("marginTop").replace(
											'px', '');

							if (clicked == "Yeah.") {

								$("#attAlert").css({
									top : newy,
									left : newx
								});
							}

						});

		$(".flb").mousedown(function(event) {
			clicked = "Yeah.";
		});

		$("html").mouseup(function(event) {

			clicked = "Nope.";
		});

	});
</script>

</html>
