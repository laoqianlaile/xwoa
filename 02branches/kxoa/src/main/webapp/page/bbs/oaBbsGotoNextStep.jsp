<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		
			<meta http-equiv="refresh" content="2; url='<%=request.getContextPath()%>/bbs/oaBbsTheme!showThemeMainPage.do?s_layoutno=${s_layoutno}'">
		
		
	</head>
	<body>
		<center>
				<br>
				<br>
				<br>
				<br>
				
				&nbsp;&nbsp;<font size="3" color="red">
				   <c:if test="${bbsDiscussion.isneed=='T'}">发帖成功，等待审核...</c:if>
				   <c:if test="${bbsDiscussion.isneed=='F'}">恭喜，发帖成功了</c:if>
				   </font>&nbsp;&nbsp;
				<br>
				<br>
				<a href="${pageContext.request.contextPath}/bbs/oaBbsTheme!showThemeMainPage.do?s_layoutno=${s_layoutno}"><font size="2">继续查看帖子</font></a>
			
		</center>
	</body>
</html>
