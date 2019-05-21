<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title><s:text name="innermsg.view.title" /></title>
</head>

<body>
<center>
<br><br><br><br>
<p class="ctitle"><b><s:property value="%{msgtitle}" /></b></p>
<%@ include file="/page/common/messages.jsp"%><s:form action="innermsg"  method="post" namespace="/app" id="innermsgForm" >
<input type="hidden" name="msgcode" value="${msgcode }">
<table style="width:80%" border="0" cellpadding="1" cellspacing="1">		
		<tr>
					<td width="10%">
					<fieldset style="border: hidden 1px #000000; ">
						&emsp;<s:text name="innermsg.sender" />:&emsp;
						${cp:MAPVALUE("usercode",object.sender)}<br>
						&emsp;<s:text name="innermsg.senddate" />:&emsp;
						<s:property value="%{senddate}" /><br>
						&emsp;<s:text name="innermsg.receive" />:&emsp;
						<c:if test="${object.receivetype eq 'O' }">
							${cp:MAPVALUE("unitcode",object.receive)}
						</c:if>
						<c:if test="${object.receivetype eq 'P' }">
							${cp:MAPVALUE("usercode",object.receive)}
						</c:if>
						<br>
						&emsp;<s:text name="innermsg.msgtitle" />:&emsp;
						<s:property value="%{msgtitle}" />
						<br>
						&emsp;附件:&emsp;<br>
						<c:forEach var="file" items="${fileList}" varStatus="status">
							&emsp;&emsp;<a href="../app/fileinfo!download.do?object.fileCode=${file.fileCode}">${file.fileName}.${file.fileExtName}</a>
							<c:if test="${(status.index + 1) % 3 == 0}">
								<br>
							</c:if>
						</c:forEach>
					</fieldset>
				<br>
					<fieldset style="border: hidden 1px #000000;">
						<legend>消息内容	</legend>
						<div style="width:100%; height:200px;OVERFLOW-y:auto;">
						${msgcontent}
						</div>
					</fieldset>
					
					<c:forEach var="reply" items="${replyList}" varStatus="status">
					<fieldset style="border: hidden 1px #000000;">
					<legend>回复人${status.index + 1}:${cp:MAPVALUE("usercode",reply.sender)},回复时间${reply. senddate}</legend>
					<br>
					<br>
					${reply. msgcontent}
					</fieldset>
					</c:forEach>
				</td>
		</tr>
</table>
<br>
<s:submit name="save"  method="reply" cssClass="btn"  value="回复" />&emsp;
<input type="button"  value="返回" class="btn"  onclick="window.history.back()"/>&emsp;
</s:form>
</center>
</body>
</html>
