<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaSmssend.list.title" />
		</title>
		<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
			<legend>
				 短信平台管理
			</legend>
			
			<s:form action="oaSmssend" namespace="/oa" style="margin-top:0;margin-bottom:5" id="oaSmssendForm">
				<table cellpadding="0" cellspacing="0" align="center">
					<tr >
						<td><s:text name="oaSmssend.sendpeo" />:</td>
						<td><s:textfield name="s_sendpeo" /> </td>
				
						<td><s:text name="oaSmssend.acceptnum" />:</td>
						<td><s:textfield name="s_acceptnum" /> </td>
					</tr>	

					<tr >
						<td><s:text name="oaSmssend.acceptpeo" />:</td>
						<td><s:textfield name="s_acceptpeo" /> </td>
					
						<td><s:text name="oaSmssend.content" />:</td>
						<td><s:textfield name="s_content" /> </td>
					</tr>	

					<tr >
					
						<td align="right" colspan="4">
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
							<s:submit method="editSend" cssClass="btn" value="发送短信" />
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="oa/oaSmssend!list.do" items="objList" var="oaSmssend"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

				<c:set var="tsmsid"><s:text name='oaSmssend.smsid' /></c:set>	
				<ec:column property="smsid" title="${tsmsid}" style="text-align:center" />

				
				<c:set var="tacceptnum"><s:text name='oaSmssend.acceptnum' /></c:set>	
				<ec:column property="acceptnum" title="${tacceptnum}" style="text-align:center" />

				<c:set var="tacceptpeo"><s:text name='oaSmssend.acceptpeo' /></c:set>	
				<ec:column property="acceptpeo" title="${tacceptpeo}" style="text-align:center" />
				<c:set var="tsendpeo"><s:text name='oaSmssend.sendpeo' /></c:set>	
				<ec:column property="sendpeo" title="${tsendpeo}" style="text-align:center" />

				<c:set var="tsendtime"><s:text name='oaSmssend.sendtime' /></c:set>	
				<ec:column property="sendtime" title="${tsendtime}" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date"/>
				
				<c:set var="tcontent"><s:text name='oaSmssend.content' /></c:set>	
				<ec:column property="content" title="${tcontent}" style="text-align:center">
				 <input type="hidden" value="${oaSmssend.content}"/>      
			          <c:choose>
						<c:when test="${fn:length(oaSmssend.content) > 18}">
							<c:out
								value="${fn:substring(oaSmssend.content, 0, 18)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${oaSmssend.content}" />
						</c:otherwise>
					</c:choose>
				</ec:column>

				<c:set var="tstate"><s:text name='oaSmssend.state' /></c:set>	
				<ec:column property="state" title="${tstate}" style="text-align:center" >
					${cp:MAPVALUE('SMS_SEND_STATE',oaSmssend.state)}
				</ec:column>
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<a href='oa/oaSmssend!view.do?smsid=${oaSmssend.smsid}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
			</ec:column>
			</ec:row>
		</ec:table>
	</body>
</html>
