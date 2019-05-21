<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<link
	href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/charisma-app.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/bootstrap-classic.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
<title><s:text name="oaBbsDiscussion.list.title" /></title>
<style type="text/css">
table{width:98%;border: 1px solid #ececec;margin:auto}
table td {
    word-break:break-all;
	border: none;
	border-bottom: 1px solid #ececec;
	padding: 10px 0 10px 15px;
	width: 33%;
}

td span {
	color: #999;
	font-size: 12px;
}

#infoTab {
	position: relative;
	height: 26px;
	line-height: 23px;
	overflow: auto;
	padding-top: 1px;
}

#infoTab li {
	cursor: pointer;
	position: relative;
	float: left;
	padding: 0 15px;
	margin-right: 6px;
	border-bottom: none;
}
/* #infoView { border:1px solid #bbb; border-top:none; padding:0px 10px 10px; } */
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

p.through{
 text-decoration:line-through;}
/* #infoView fieldset { display:none; } */
</style>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>


	<form
		action="${pageContext.request.contextPath}/bbs/oaBbsTheme!ownerReplayList.do"
		id="form" method="post" >
		<input type="hidden" name="s_usercode" value="${s_usercode}" />

		<form>

			<table border="0" cellpadding="0" cellspacing="0">
				<!-- 		<tr> -->
				<!-- 			<td>主题</td> -->
				<!-- 			<td>板块</td> -->
				<!-- 			<td>回复/查看</td> -->
				<!-- 			<td>最后更新</td> -->
				<!-- 		</tr> -->
				<c:forEach items="${themeReplayList }" var="replay"
					varStatus="status">
					<tr>
						<td>
<!-- 						<i class="icon icon-color icon-trash" -->
<!-- 							 title="删除回复"></i> -->
							 
							 <fmt:formatDate value="${replay.creatertime }"
								pattern="yyyy-MM-dd hh:mm:ss" /> 回复： <c:if
								test="${not empty objList[status.count].themeno }">
                               [<font class="getsubLayoutTitle" id="${replay.no }"
									data-themeno="${replay.no }"
									data-layoutno="${objList[status.count].layoutno }">${objList[status.count].layoutno}</font>]					 
									<c:choose>
								           
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
					</c:choose>
								
								<c:if test="${'D' eq objList[status.count].state}">
								<p class="through">
								<c:choose>
								<c:when
											test="${fn:length(objList[status.count].sublayouttitle) gt 50 }">${fn:substring(objList[status.count].sublayouttitle , 0, 50) }...</c:when>
										<c:otherwise>${objList[status.count].sublayouttitle} </c:otherwise>
									</c:choose>
								</p>
								</c:if>
								<c:if test="${'D' ne objList[status.count].state}">
								<a target="navTab" external="true" title="查看帖子"
									rel="external_XXYDTLB"
									href='${pageContext.request.contextPath}/bbs/oaBbsTheme!view.do?themeno=${objList[status.count].themeno}&s_layoutno=${s_layoutno}'
									style="text-decoration: none;"> <c:choose>
										<c:when
											test="${fn:length(objList[status.count].sublayouttitle) gt 50 }">${fn:substring(objList[status.count].sublayouttitle , 0, 50) }...</c:when>
										<c:otherwise>${objList[status.count].sublayouttitle} </c:otherwise>
									</c:choose></a>
								</c:if>
							</c:if>
							<c:if
								test="${empty objList[status.count].themeno or 'D' eq objList[status.count].state}">
								<p class="through" title="帖子已被删除">帖子已不存在</p>
							</c:if>
							 </br> 
							  <c:if
								test="${'D' ne replay.state}">
								<c:choose>
										<c:when
											test="${fn:length(replay.messageComment) gt 500 }">"${fn:substring(replay.messageComment , 0, 500) }"...</c:when>
										<c:otherwise>"${replay.messageComment}" </c:otherwise>
									</c:choose>
							
								</c:if>
							 <c:if
								test="${'D' eq replay.state}">
								<span  title="回复已被删除"> <c:choose>
										<c:when
											test="${fn:length(replay.messageComment) gt 500 }">"${fn:substring(replay.messageComment , 0, 50) }"...</c:when>
										<c:otherwise>"${replay.messageComment}" </c:otherwise>
									</c:choose></span>
								</c:if>
							
					         </td>
					</tr>

				</c:forEach>
			</table>

			<c:set var="listURL" value="oaBbsTheme!ownerReplayList.do" />
			<c:set var="maxPageItems" value="10"></c:set>
			<c:if test="${fn:length(themeReplayList)>=1}">
			<%@ include file="/page/common/pagingBar.jsp"%>
			</c:if>
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
						//子模块版块名称
						$
								.ajax({
									type : "POST",
									url : "${contextPath}/bbs/oaBbsDiscussion!getsubLayoutTitle.do?layoutno="
											+ layoutno,
									dataType : "json",
									success : function(json) {
										$("#" + themeno ).html(json.status);
									},
									error : function() {
										alert("数据获取失败，刷新后重试！");
									}

								})
					});
	
</script>
</html>
