<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaSmssend.view.title" /></title>
<style type="text/css">
	font{font-size:14px}
	.tableSpan{font-size:14px; color:green}
	.pSpan{margin: 10px 0 0 5px;display: inline-block;}
</style>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
<style type="text/css">
	td{height: 30px}
</style>
</head>

<body class="sub-flow">
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="form">
		<legend>
				查看消息详情
		</legend>
	</fieldset>
	<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"
		onclick="window.history.go(-1);" />
	<p>
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">



		<tr>
			<td class="addTd"><s:text name="oaSmssend.acceptnum" /></td>
			<td align="left"><s:property value="%{acceptnum}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaSmssend.acceptpeo" /></td>
			<td align="left"><s:property value="%{acceptpeo}" /></td>
		</tr>
		<tr>
			<td class="addTd"><s:text name="oaSmssend.content" /></td>
			<td align="left"><s:property value="%{content}" /></td>
		</tr>
		
		<tr>
			<td class="addTd"><s:text name="oaSmssend.sendtime" /></td>
			<td align="left"><s:date name="%{sendtime}"
					format="yyyy-MM-dd HH:mm" /></td>
		</tr>
		<tr>
			<td class="addTd"><s:text name="oaSmssend.state" /></td>
			<td align="left"><c:choose>
					<c:when test="${'-1' eq object.state }">待发送</c:when>
					<c:otherwise>${cp:MAPVALUE('sendMsgState',state)}</c:otherwise>
				</c:choose></td>
		</tr>
		
		<tr>
			<td class="addTd">短信来源</td>
			<td align="left">${cp:MAPVALUE('smsSource',object.datasource) }</td>
		</tr>


	</table>
	<%-- <c:if test="${isShowLogs }"> --%>
	<p><span class="pSpan">发送记录</span></p>

	<ec:table items="smsSendLogList" var="log" action="oa/oaSmssendLog!list.do?smsid=${object.smsid}"
		sortable="false" showPagination="false" imagePath="${STYLE_PATH}/images/table/*.gif">
		<ec:row>
			<ec:column property="datasource" title="短信来源"
				style="text-align:center">${cp:MAPVALUE('smsSource',log.datasource) }</ec:column>
			<ec:column property="sendtime" title="发送时间" style="text-align:center">
				<fmt:formatDate value="${log.sendtime}"
					pattern="yyyy-MM-dd HH:mm:ss" />
			</ec:column>

			<ec:column property="content" title="发送内容" style="text-align:center">
				<input type="hidden" value="${log.content}" />
				<c:choose>
					<c:when test="${fn:length(log.content) > 18}">
						<c:out value="${fn:substring(log.content, 0, 18)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${log.content}" />
					</c:otherwise>
				</c:choose>
			</ec:column>

			<ec:column property="state" title="发送状态" style="text-align:center">
				<c:choose>
					<c:when test="${'-1' eq log.state} ">
						<span class="tableSpan">${cp:MAPVALUE('sendMsgState','E')}</font>
					</c:when>
					<c:when test="${'0' ne log.state}">
						<font color="red">${cp:MAPVALUE('sendMsgResState',log.state)}</font>
					</c:when>
					<c:when test="${'0' eq log.state}">
						<span class="tableSpan">${cp:MAPVALUE('sendMsgResState',log.state)}</span>
					</c:when>
					<c:otherwise>${cp:MAPVALUE('sendMsgResState',log.state)}</c:otherwise>
				</c:choose>
			</ec:column>

		</ec:row>
	</ec:table>
	<%-- </c:if> --%>

</body>
</html>
