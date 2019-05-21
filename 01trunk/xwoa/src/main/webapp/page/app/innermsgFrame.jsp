<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人消息</title>
</head>
<frameset rows="*" cols="50%,*" frameborder="yes" border="1" framespacing="0" id="msgFrameSet" name="msgFrameSet"> 
<frame src="<%=request.getContextPath() %>/app/innermsg!list.do" name="msgList" noresize="noresize" scrolling="auto" id="msgList" /> 
 <frame src="" name="msg_view" scrolling="auto" id="msgView"/>
</frameset>
<noframes><body>
</body></noframes>
</html>
