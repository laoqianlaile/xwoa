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
 
 table{width:98%;border: 1px solid #ececec;margin-top:10px;}
table td {
	border: none;
 	border-bottom: 1px solid #ececec; 
	padding: 10px 0 10px 15px;
/* 	width: 33%; */
}

td span {
	color: #999;
	font-size: 12px;
}
/* #infoView fieldset { display:none; } */
</style>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>


	<form
		action="${pageContext.request.contextPath}/bbs/oaBbsTheme!ownerThemeList.do"
		id="form" method="post" style="margin-top: 0; margin-bottom: 5">
		<input type="hidden" name="s_usercode" value="${s_usercode}" />

		<form>

			<table cellpadding="0" cellspacing="0">
				<tr>
					<td >主题</td>
					<td>板块</td>
					<td>审核结果</td>
					<td>回复/查看</td>
					<!-- <td>最后更新</td> -->
					<!-- 			<td>操作</td> -->
				</tr>
				<c:forEach items="${objList }" var="theme">
					<tr>
						<td><c:choose>
								           
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
					</c:choose><c:choose>
								<c:when test="${'T' eq theme.state}">
									<a target="navTab" external="true" title="查看帖子"
									rel="external_XXYDTLB"
										href='${pageContext.request.contextPath}/bbs/oaBbsTheme!view.do?themeno=${theme.themeno}&s_layoutno=${s_layoutno}'
										style="text-decoration: none;">
								</c:when>
								<c:otherwise>
									<p class="through" title="已被删除">
								</c:otherwise>
							</c:choose> ${theme.sublayouttitle}
							
								<%-- <c:when test="${fn:length(theme.sublayouttitle) gt 30 }">${fn:substring(theme.sublayouttitle , 0, 30) }...</c:when>
								<c:otherwise>${theme.sublayouttitle} </c:otherwise> --%>
							<c:choose>
								<c:when test="${theme.state eq 'T' }">
									</a>
								</c:when>
								<c:otherwise>
									</p>
								</c:otherwise>
							</c:choose></td>


						<td><font class="getsubLayoutTitle" id="${theme.themeno }"
							data-themeno="${theme.themeno }"
							data-layoutno="${theme.layoutno }">${theme.layoutno }</font></td>

						<td><c:if test="${'T' eq theme.approvalresults }">通过</c:if> <c:if
								test="${'F' eq theme.approvalresults }">
								<span style="color: red;">不通过</span>
							</c:if></td>
						<td>${empty theme.postsnum ? 0:theme.postsnum}/${empty
							theme.postsviewnum ? 0:theme.postsviewnum}</td>
						<%-- <td><c:choose>
								<c:when test="${null ne theme.lastmodifytime }">
									<fmt:formatDate value="${theme.lastmodifytime }"
										pattern="yyyy-MM-dd hh:mm:ss" />
								</c:when>
								<c:otherwise>
									<fmt:formatDate value="${theme.createtime }"
										pattern="yyyy-MM-dd hh:mm:ss" />
								</c:otherwise>
							</c:choose></td> --%>
						<!-- 			<td> <i class="icon icon-color icon-trash" -->
						<!-- 							 title="删除帖子"></i></td>		 -->
					</tr>

				</c:forEach>
			</table>

			<c:set var="listURL" value="oaBbsTheme!ownerThemeList.do" />
			<c:set var="maxPageItems" value="10"></c:set>
			<c:if test="${fn:length(objList)>=1}">
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
										$("#" + themeno).html(json.status);
									},
									error : function() {
										alert("数据获取失败，刷新后重试！");
									}

								})
					});
</script>
</html>
