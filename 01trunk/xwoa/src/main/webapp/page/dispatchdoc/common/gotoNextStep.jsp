<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
	<head>
		<c:if test="${nextOptUrl ne null and nextOptUrl ne ' '}">
			<meta http-equiv="refresh" content="2; url='<%=request.getContextPath()%>${nextOptUrl}'">
		</c:if>
		
	</head>
	<body>
		<center>
				<br>
				<br>
				<br>
				<br>
				<c:if test="${nextOptUrl ne null and nextOptUrl ne ' '}">
				&nbsp;&nbsp;<font size="3" color="red">系统将自动进入下一操作步骤...</font>&nbsp;&nbsp;
				<br>
				<br>
				<a href="../dispatchdoc/ioDocTasks!listIncomeDocTasks.do?s_flowCode=${flowCode}&flowCode=${flowCode}"><font size="2">进入待办列表</font></a>
			</c:if>
		</center>
	</body>
</html>
