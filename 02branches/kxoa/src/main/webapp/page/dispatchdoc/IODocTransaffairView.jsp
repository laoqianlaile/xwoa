<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
	<head>
		<title>
			办件信息查看
		</title>
		<SCRIPT type="text/javascript" src="${pageContext.request.contextPath}/scripts/scrolltop.js"></SCRIPT>
		<LINK rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/default/css/lrtk.css">
		<script src="${pageContext.request.contextPath}/scripts/arrow.js"
			type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/kjyj.js"
			type="text/javascript"></script>
		<script type="text/javascript" src="jquery-1.6.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dtree.js"></script>
		<link href="${pageContext.request.contextPath}/workflow/css/dtree.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/themes/css/arrow.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<s:url value="/scripts/colorbox/jquery.colorbox.js"/>" charset="utf-8"></script>
		<link href="${pageContext.request.contextPath}/scripts/colorbox/colorbox.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/scripts/jquery-ui/jquery-ui-1.9.2.custom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<s:url value="/scripts/addressBook.js"/>" charset="utf-8"></script>
		<script type="text/javascript" src="<s:url value="/scripts/centit.js"/>" charset="utf-8"></script>
		<script type="text/javascript" src="<s:url value="/scripts/jquery-ui/jquery-ui-1.9.2.custom.js"/>" charset="utf-8"></script>
		<script type="text/javascript" src="<s:url value="/scripts/opendiv_Win.js"/>" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/themes/css/arrow.css" rel="stylesheet" type="text/css" />		
	</head>
<body>
	<!-- 返回顶部浮层 -->
	<div id="myDiv" class="tt" style="display: none">
		<A HREF="javascript: window.scrollTo(0, 0); void 0"
			ONMOUSEOVER="window.status = 'top'; return true;"
			ONMOUSEOUT="window.status = ''; return true;"> <img
			align="middle" alt="返回顶部"
			src="${pageContext.request.contextPath}/styles/default/images/lanren_top.jpg"
			border="0" />
		</A>
	</div>
	<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"  onclick="window.history.go(-1);"/>
	<!-- 办件名称、办件标题显示 -->
	<div class="flowTitle" align="right">
		<%-- <span style="padding-right:20px;">本环节已用时间:${worktime}</span> --%>
		<span style="font-size:18pt;color:red;float:center;padding-right:460px;" >${affairTitle}&nbsp;&nbsp;</span><br>
		<c:if test="${not empty nodeInstId}">
			<span>${nodeName}&nbsp;&nbsp;</span>
			<c:if test="${not empty nodePromiseTime}">
				<span>环节总时限：${nodePromiseTime}天 &nbsp;&nbsp;</span>
			</c:if>
			<c:if test="${not empty nodeTime}">
				<span style="color: red;">剩余期限：${nodeTime}&nbsp;&nbsp;</span>
			</c:if>
			<c:if test="${not empty flowDefTime}">
				<span>办件总时限：${flowDefTime}天&nbsp;&nbsp;</span>
			</c:if>
			<c:if test="${not empty flowTime}">
				<span>办件整体剩余时限：${flowTime}</span>
			</c:if>
			&nbsp;&nbsp;
		</c:if>
	</div>
	
	<!-- 动态框架整合区 -->
	<c:forEach items="${jspInfo.frameList}" var="fInfo">
		<iframe id="${fInfo.frameId}" name="${fInfo.frameId}" src="<c:url value='${fInfo.frameSrc}'/>" width="100%" style="margin-bottom:10px;"
			frameborder="no" scrolling="false" border="0" marginwidth="0" onload="this.height=window.frames['${fInfo.frameId}'].document.body.scrollHeight"></iframe>
	</c:forEach>
	
	<!-- 引用tab页 -->
	<c:if test="${not empty tabFrameList}">
		<%@ include file="/page/dispatchdoc/common/ioDocView.jsp"%>
	</c:if>
</body>
</html>