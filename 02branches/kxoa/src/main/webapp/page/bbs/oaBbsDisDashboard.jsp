<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />

<link
	href="${pageContext.request.contextPath}/styles/default/css/oabbs/bbs.css"
	rel="stylesheet" type="text/css" />
<html>
<head>
<title><s:text name="oaBbsDashboard.list.title" /></title>
<style type="text/css">
table {
	margin-top: 15px;
}

table td {
	border: none;
	border-bottom: 1px solid #ececec;
	padding: 10px 0;
	width: 33%;
}

td img {
	float: left;
	vertical-align: middle;
	margin-right: 10px;
	margin-top: 5px;
	margin-left: 15px;
}

td span {
	color: #999;
	font-size: 12px;
}

.title {
	height: 30px;
	background: #f0f0f0;
	line-height: 30px;
	padding-left: 15px;
	margin-top: 25px;
}

a {
	text-decoration: none !important;
}

.top {
	margin-top: 10px;
	padding-left: 15px;
}

.top input {
	float: right
}
/*新增样式*/
/* 
#header-top{height:80px; background:url(${contextPath}/themes/blue/images/1.png) center left no-repeat}
#header-bottom{height:40px;background:url(${contextPath}/themes/blue/images/bannerbg.png)}
body{padding-left:0;margin:auto;width:100%}
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
	<%--  <div id="header">
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
                  <div style="padding:5px">id:123456789</div>
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

				<a
					href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!showBbsMainPage.do'>
					讨论版</a>>${object.layoutname}

			</div>
			<div class="pubpos-search-box">
				<span><input type="button" class="btn" id="addTheme"
					onclick="javascript:window.location.href='${pageContext.request.contextPath}/bbs/oaBbsTheme!edit.do?s_layoutcode=${object.layoutcode}';"
					value="我要发帖" style="float: left;" /></span> <input name="s_search"
					class="search-txt" value="${s_search}" id="txt_searchWord_nav"
					type="text" size="100" placeholder="请输入关键词"> <input
					name="submit" class="submit-botton" id="home_btnSearchSubmit"
					type="submit" value="搜 索">
			</div>
		</div>
		<div class="bar">
			<div class="title ">
				<c:if
					test="${null eq object.isShowTime or 'T' ne  object.isShowTime}">
					<i class="icon icon-blue icon-locked"
						title="${object.openTimeTip }"></i>
				</c:if>
				${object.layoutname}
			</div>
			<c:if test="${not empty object.oaBbsDiscussions}">
				<table border="0" cellpadding="0" cellspacing="0">
					<tbody align="left">
						<c:forEach items="${object.oaBbsDiscussions }" var="discussions"
							varStatus="ts">

							<%-- 					<c:if test="${8 >= ts.index }"> --%>

							<c:if test="${ts.count eq 1 || ts.index % 3 eq 0}">
								<tr>
							</c:if>
							<td class="showTimeTd"><c:if
									test="${null eq object.isShowTime or 'T' ne  object.isShowTime or null eq discussions.isShowTime or 'T' ne  discussions.isShowTime}">
									<i class="icon icon-blue icon-locked"></i>
								</c:if> <c:choose>
									<c:when test="${not empty discussions.showpicturename }">
										<img
											src='${contextPath }/bbs/oaBbsDiscussion!showImage.do?layoutno=${discussions.layoutno}'
											width="25px" height="25px" />
									</c:when>
									<c:otherwise>
										<img style="width: 25px; height: 25px"
											src='${ctx}/themes/blue/images/img.png' />
									</c:otherwise>
								</c:choose> <span> <a class="showTime"
									data-isshowtime="${discussions.isShowTime }"
									data-href="${pageContext.request.contextPath}/bbs/oaBbsTheme!showThemeMainPage.do?s_layoutno=${discussions.layoutno}">
										<font class="${discussions.colorOftitle}BBS">
											${discussions.sublayouttitle} </font><span
										style="color: #F60; font-weight: 400; font-style: normal;">(${discussions.todayThemenum
											})</span>
								</a> <c:if
										test="${null eq object.isShowTime or 'T' ne  object.isShowTime or null eq discussions.isShowTime or 'T' ne  discussions.isShowTime}">

										<div class="openTimeTipClass">
											该模块还未到开放时间！<br />${discussions.openTimeTip }<br />${
											discussions.openTimeTipEnd}
										</div>

									</c:if> <br> 主题：${empty discussions.subjectnum ? 0 :
									discussions.subjectnum}<span style="margin: 0px 5px 0px 5px">|</span>帖数：${empty
									discussions.postsnum ? 0 : discussions.postsnum}
							</span></td>
							<c:if test="${ts.count % 3 eq 0 || ts.count eq 3}">
								</tr>
							</c:if>
							<%-- 					</c:if> --%>
						</c:forEach>
					</tbody>
				</table>

			</c:if>
			<c:if test="${ empty object.oaBbsDiscussions}">
                暂无子模块，尽请期待。
      </c:if>

		</div>
	</form>
</body>
<script type="text/javascript">
	$(function() {
		var $search = $('#search');
		$search.bind('keypress', function(event) {
			if (event.keyCode == "13") {
				$('#searchForm').submit();
			}
		});

		$(".showTime").live('click', function() {

			var isShowTime = $(this).data("isshowtime");
			var href = $(this).data('href');
			if ("T" == isShowTime) {
				window.location.href = href;
			} else {

			}
		});

		$(".showTimeTd").hover(function() {
			$(this).find(".openTimeTipClass").fadeToggle('fast');
		});

	});
</script>
</html>
