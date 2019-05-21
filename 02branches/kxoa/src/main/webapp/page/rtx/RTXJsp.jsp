<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ page import="rtx.Signauth"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String strUser = request.getParameter("user");
String strSign = request.getParameter("sign");

Signauth SignObj = new Signauth();
boolean bRet = SignObj.auth(strUser, strSign);	

/*  if(bRet)
{
	response.sendRedirect("index.jsp");
}
else
{
	response.sendRedirect("frame/login.jsp");
}  */
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
  
  <body>
  </body>
</html>
