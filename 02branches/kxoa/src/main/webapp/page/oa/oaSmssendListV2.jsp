<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
		<title>
			<s:text name="oaSmssend.list.title" />
		</title>
		<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	<style type="text/css">
	font{font-size:14px}
	.tableSpan{font-size:14px; color:green}
	</style>
	
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
			<legend class="headTitle">
				 短信平台管理
			</legend>
			<div class="searchDiv">
			<s:form  method="post" action="oaSmssend" namespace="/oa" style="margin-top:0;margin-bottom:5" id="oaSmssendForm">
				<div class="searchArea">
				<table style="width: auto;">
					<tr >
					<td class="searchBtnArea">
					<s:submit method="editSend" cssClass="whiteBtnWide" value="发送短信" />
					</td>
					
					<td class="searchTitleArea" >
					<label class="searchCondTitle" style="width: 95px;"><s:text name="oaSmssend.acceptnum" />:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_acceptnum" value="${s_acceptnum}" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea" >
					<label class="searchCondTitle" style="width: 95px;"><s:text name="oaSmssend.acceptpeo" />:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_acceptpeo" value="${s_acceptpeo}" />&nbsp;&nbsp;	
						<input id="gaoji" type="button" value="高级" class="grayBtnWide" onclick="showgaoji()">
						<input id="shouqi" type="button" value="收起" class="grayBtnWide" style="display: none;" onclick="toshouqi()">
					</td>
					<td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>

					</tr>
					
					<tr id="gaoji_more" style="display: none;">
					<td></td>
					<td class="searchTitleArea" >
					<label class="searchCondTitle" style="width: 95px;"><s:text name="oaSmssend.sendpeo" />:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_sendpeo" value="${s_sendpeo}" />
					</td>
					<td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
					<td class="searchTitleArea" >
					<label class="searchCondTitle" style="width: 95px;"><s:text name="oaSmssend.content" />:</label>
					</td>
					<td class="searchCountArea">
					<input class="searchCondContent" style="height: auto;" type="text" name="s_content" value="${s_content}" />
					</tr>
				</table>
				</div>
			</s:form>
			</div>
		</fieldset>

		<ec:table action="oa/oaSmssend!list.do" items="objList" var="oaSmssend"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"  retrieveRowsCallback="limit">
			<ec:row>

				<c:set var="tacceptnum"><s:text name='oaSmssend.acceptnum' /></c:set>	
				<ec:column property="acceptnum" title="${tacceptnum}" style="text-align:center" />

				<c:set var="tacceptpeo"><s:text name='oaSmssend.acceptpeo' /></c:set>	
				<ec:column property="acceptpeo" title="${tacceptpeo}" style="text-align:center" />

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
				<c:choose>
					<c:when test="${'-1' eq oaSmssend.state }">待发送</c:when>
					<c:otherwise>${cp:MAPVALUE('sendMsgState',oaSmssend.state)}</c:otherwise>
				</c:choose>
				<%-- <c:choose>
				
					<c:when test="${'-1' eq oaSmssend.state}">待发送</c:when>
					<c:when test="${'0' ne oaSmssend.state && null eq oaSmssend.restoremessage}">
					<font color="red">${cp:MAPVALUE('sendMsgState',oaSmssend.state)}</font></c:when>
					<c:when test="${'0' ne oaSmssend.restoremessage}"><font color="red">${cp:MAPVALUE('sendMsgResState',oaSmssend.restoremessage)}</font></c:when>
					<c:when test="${'0' eq oaSmssend.restoremessage}"><span class="tableSpan">${cp:MAPVALUE('sendMsgResState','0')}</span></c:when>
					<c:otherwise>${cp:MAPVALUE('sendMsgResState',oaSmssend.restoremessage)}</c:otherwise>
				</c:choose> --%>
			</ec:column>
			
		    <ec:column property="datasource" title="来源" style="text-align:center">${cp:MAPVALUE('smsSource',oaSmssend.datasource) }</ec:column>
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<a class="check_email" href='oa/oaSmssend!view.do?smsid=${oaSmssend.smsid}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
					<c:if test="${'0' ne oaSmssend.state }">
						<a class="forward_email" href='oa/oaSmssend!edit.do?smsid=${oaSmssend.smsid}&edit=T'>重新发送</a>
					</c:if>
			</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
	function sub(){
		$("#oaSmssendForm").attr("action","oaSmssend!list.do");
		$("#oaSmssendForm").submit();
	}
	function showgaoji(){
		$("#shouqi").show();
		$("#gaoji_more").show();
		$("#gaoji").hide();
	}
	function toshouqi(){
		$("#shouqi").hide();
		$("#gaoji_more").hide();
		$("#gaoji").show();
	}
	</script>
</html>
