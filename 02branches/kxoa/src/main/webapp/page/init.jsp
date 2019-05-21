<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript">

function forwordLogin(){
	window.location.href="${pageContext.request.contextPath}/page/frame/login.jsp";
}
</script>
</head>
<body onLoad="forwordLogin();"></body>
</html>