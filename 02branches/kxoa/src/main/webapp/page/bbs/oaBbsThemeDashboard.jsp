<!-- <!DOCTYPE html> -->
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
<html>
<head>

<link
	href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/charisma-app.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/bootstrap-classic.css"
	rel="stylesheet" />


<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />

<title><s:text name="oaBbsDashboard.list.title" /></title>
<style type="text/css">
.bbsThemeTable {
	width: 98%;
	border: 1px solid #ececec;
	margin: auto
}

.bbsThemeTable  td {
	border: none;
	border-bottom: 1px solid #ececec;
	padding: 10px 0 10px 15px;
	/* 	width: 33%; */
}

.bbsTheme td span {
	color: #999;
	font-size: 12px;
}

a {
	text-decoration: none !important;
}

.top-title {
	height: 30px;
	background: #f0f0f0;
	line-height: 30px;
	padding-left: 15px;
	margin-top: 25px;
}

.top {
	height: 30px;
	line-height: 30px;
	padding-left: 15px;
	margin-top: 10px;
}

.top input {
	float: right;
}

.btn {
	color: white
}

/* #form1 { */
/* 	padding-left: 15px; */
/* 	background: #F0F0F0; */
/*     border-bottom: 1px solid rgb(220, 208, 208); */
/* } */
#allTheme {
	margin-left: 10px
}

select {
	width: 90px;
	border: 0px solid #bbb;
	background: #F0F0F0;
	margin-bottom: 0px;
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
.btn {
	background: url(${contextPath}/themes/blue/images/btn3.png) left 2px
		no-repeat;
}




#editTheme {
	margin-right: 10px;
	/*  	margin-top: -20px;  */
	/* 	vertical-align: middle; */
}

.alert h4 {
	text-align: right;
	padding: 5px 0 6px 5px;
}

.red {
	color: red;
}
.public-position {
	background: rgb(238, 238, 238);
	margin: 10px auto;
	border: 1px solid rgb(204, 204, 204);
	border-image: none;
	/* width: 99% !important;  */
	height: 40px;
	overflow: hidden;
}

.pubpos-search-box {
	overflow: hidden;
	float: right;
	display: inline;
}

.pubpos-nav {
	height: 30px;
	line-height: 40px;
	overflow: hidden;
	padding-left: 10px;
	float: left;
	display: inline;
}

.pubpos-search-box {
	padding: 8px 0px 0px 0px;
	width: 300px;
	height: 30px;
}

.search-box {
	padding: 10px;
	height: 47px;
	overflow: hidden;
	margin-bottom: 10px;
}

.search-txt {
	padding: 2px 0px 2px 2px;
	border: 1px solid rgb(204, 204, 204);
	border-image: none;
	width: 150px;
	height: 27px !important;
	color: rgb(67, 67, 67);
	/*  float: left;  */
	display: inline;
	padding: 0px !important;
}

.submit-botton {
	background: rgb(176, 0, 7);
	border: currentColor;
	border-image: none;
	width: 40px !important;
	height: 27px !important;
	color: rgb(255, 255, 255); /* float: left */;
	display: inline;
	cursor: pointer;
}
</style>
</head>


<body>

	<form id="form1"
		action="${pageContext.request.contextPath}/bbs/oaBbsTheme!showThemeMainPage.do"
		method="post">

		<div class="public-position" id="publicPosition">
			<div class="pubpos-nav">
				<a
					href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!showBbsMainPage.do'>
					您当前的位置：讨论版</a>
				<c:if test="${not empty bbsDiscussion.oaBbsDashboard}">
		        > <a
						href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!showBbsDisMainPage.do?layoutcode=${bbsDiscussion.oaBbsDashboard.layoutcode}'>
						${bbsDiscussion.oaBbsDashboard.layoutname} </a>>	${bbsDiscussion.sublayouttitle}
		       </c:if>

			</div>
			<div class="pubpos-search-box">
				<span> <c:if test="${not empty s_layoutno}">
						<input type="button" class="btn" style="float: left;"
							onclick="javascript:window.location.href='${pageContext.request.contextPath}/bbs/oaBbsTheme!edit.do?s_layoutno=${bbsDiscussion.layoutno}';"
							value="我要发帖" />
					</c:if> <c:if test="${empty s_layoutno}">
						<input type="button" class="btn" id="addTheme"
							style="float: left;" value="我要发帖" />
					</c:if>
				</span> <input name="s_search" class="search-txt" value="${s_search}"
					id="txt_searchWord_nav" type="text" size="100" placeholder="请输入关键词">

				<input name="submit" class="submit-botton" id="home_btnSearchSubmit"
					type="submit" value="搜 索">
			</div>
		</div>

		<div class="top">
			今日主题数：<span class="red">${todayThemeNum}</span><span
				style="margin: 0px 5px 0px 5px">|</span>板块主题数：<span class="red">${empty
				bbsDiscussion. subjectnum?0:bbsDiscussion. subjectnum}</span> 
		</div>
		<table border="0" cellpadding="0" cellspacing="0"
			class="bbsThemeTable">
			<!-- 	 <form id="form1" -->
			<%-- 		action="${pageContext.request.contextPath}/bbs/oaBbsTheme!showThemeMainPage.do" --%>
			<!-- 		method="post"> -->
			<tr class="top-title">
				<td><input type="hidden" name="s_layoutno"
					value="${s_layoutno }"> <!-- 		我的帖子 --> <input
					type="hidden" name="s_creater" value="${s_creater }"> 筛选：<select
					name="s_timeType" id="s_timeType"
					onChange="document.all.form1.submit();">
						<option value="">全部时间</option>
						<c:forEach var="row" items="${cp:DICTIONARY('timeType')}">
							<option value="${row.datacode}"
								<c:if test="${s_timeType eq row.datacode }">selected="selected"</c:if>>
								<c:out value="${row.datavalue}" />
							</option>
						</c:forEach>
				</select> 排序：<select name="s_orderType" id="s_orderType"
					onChange="document.all.form1.submit();">
						<c:forEach var="row" items="${cp:DICTIONARY('orderType')}">
							<option value="${row.datacode}"
								<c:if test="${s_orderType eq row.datacode }">selected="selected"</c:if>>
								<c:out value="${row.datavalue}" />
							</option>
						</c:forEach>
				</select> <a href="#" id="allTheme">全部</a><span
					style="margin: 0px 5px 0px 5px">|</span><a href="#"
					id="jinghuaTheme"> 精华 <input type="hidden" name="s_themeset"
						id="s_themeset" value="${s_themeset }">
				</a></td>
				<td>板块</td>
				<td>作者</td>
				<td>查看/回复</td>
				<td>最后更新时间</td>
			</tr>
			<!-- 	</form> -->
			<c:forEach items="${objList }" var="theme">
				<tr>
					<td class="td_search"><c:choose>

							<c:when test="${theme.themeset eq 'G' }">
	  			[${cp:MAPVALUE('themeset',theme.themeset) }]&nbsp;&nbsp;
	  			 <i class="bbsgg" title="公告"></i>
							</c:when>
							<c:when test="${theme.themeset eq 'T' }">
					
	  			[${cp:MAPVALUE('themeset',theme.themeset) }]&nbsp;&nbsp;
	  			 <i class="bbszd" title="置顶"></i>
							</c:when>
							<c:when test="${theme.themeset eq 'J' }">
					
	  			[${cp:MAPVALUE('themeset',theme.themeset) }]&nbsp;&nbsp;
	  			 <i class="bbsjh" title="精华"></i>
							</c:when>
							<c:otherwise>
								<i class="icon icon-color icon-star-off" title="普通帖"></i>
							</c:otherwise>
						</c:choose> <a
						href='${pageContext.request.contextPath}/bbs/oaBbsTheme!view.do?themeno=${theme.themeno}&s_layoutno=${s_layoutno}'
						style="text-decoration: none;"> <c:choose>
								<c:when test="${fn:length(theme.sublayouttitle) gt 50 }">${fn:substring(theme.sublayouttitle , 0, 50) }...</c:when>
								<c:otherwise>${theme.sublayouttitle} </c:otherwise>
							</c:choose> <%-- 	  			<c:if test="${not empty theme.fileDocname }"> --%> <!-- 						        <i class="icon icon-color icon-page" style="margin-left:15px;" title="附件"></i> -->
							<%-- 							</c:if> --%> <%-- <c:if test="${ loginer eq   OaHelpinfo.creater }">
									<a href="oaHelpinfo!edit.do?djid=${OaHelpinfo.djid}">
									<i class="icon icon-color icon-edit" style="margin-left:15px;" title="修改"></i>
									</a>
							</c:if> --%> <br></td>


					<td><font class="getsubLayoutTitle" id="${theme.themeno }"
						data-themeno="${theme.themeno }"
						data-layoutno="${theme.layoutno }">${theme.layoutno }</font></td>
					<td>${cp:MAPVALUE("usercode",theme.creater)}</td>
					<td><span
						title="查看数：${empty theme.postsviewnum ? 0:theme.postsviewnum}/回复数：${empty theme.postsnum ? 0:theme.postsnum}">${empty
							theme.postsviewnum ? 0:theme.postsviewnum}/${empty theme.postsnum
							? 0:theme.postsnum}</span></td>
					<td><fmt:formatDate value="${theme.createtime}"
							pattern="yyyy-MM-dd HH:mm" /></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<c:set var="listURL" value="oaBbsTheme!showThemeMainPage.do" />
	<c:set var="maxPageItems" value="10"></c:set>
	<c:if test="${fn:length(objList)>=1}">
		<%@ include file="/page/common/pagingBar.jsp"%>
	</c:if>



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
									<c:forEach var="row" items="${oaBbsDashboards}">
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
<div class="background" id="background" style="display: none;"></div>
<div class="progressBar" id="progressBar" style="display: none;"></div>
<%@ include file="/page/common/scripts.jsp"%>
<script type="text/javascript">
	//初始化对应子模块版块名称
	$
			.each(
					$('.getsubLayoutTitle'),
					function() {

						var layoutno = $(this).data("layoutno");
						var themeno = $(this).data("themeno");
						// 		a=$(this)
						//子模块版块名称
						$
								.ajax({
									type : "POST",
									url : "${contextPath}/bbs/oaBbsDiscussion!getsubLayoutTitle.do?layoutno="
											+ layoutno,
									dataType : "json",
									success : function(json) {
										$("#" + themeno).html(json.status);
									},
									error : function() {
										alert("数据获取失败，刷新后重试！");
									}

								})
					});

	$(function() {
		//搜索
		var $search = $('#search');
		$search.bind('keypress', function(event) {
			if (event.keyCode == "13") {
				$('#form1').submit();
			}
		});

		//查询高亮显示 ----待完善
		var search = $('#search').val();
		$('.td_search').each(

				function(index, element) {
					$(element).text().replace(search,
							"<font color='red'>" + search + "</font>");

				}

		);
		//精华帖
		$("#jinghuaTheme").click(function() {
			$("#s_themeset").val("J");
			$("#form1").submit();

		});
		//全部帖
		$("#allTheme").click(function() {
			$("#s_timeType").val("");
			$("#s_orderType").val("");
			$("#s_themeset").val("");
			$("#form1").submit();

		});

		$("#editTheme").attr("disabled", "disabled");
		
		$('#addTheme').live('click', function() {
			var myAlert = document.getElementById("attAlert");
			myAlert.style.display = "block"; 
// 			setAlertStyle("attAlert");
		});
	
		$('#close2').live('click', function() {
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
		
			$("#editTheme").attr("class","editTheme");
 		    $("#editTheme").removeAttr("disabled");
			
			url = "${pageContext.request.contextPath}/bbs/oaBbsTheme!edit.do";
			url = url + "?s_layoutno=" + $(this).attr("nodeID");
			href = "javascript:window.location.href='" + url + "';";
			$("#editTheme").attr("onclick", href);
			

			/*添加選中效果*/
			$(".level2>li").removeClass("switch-hover");
			$(this).addClass("switch-hover");
		
		});

	});
</script>

</html>
