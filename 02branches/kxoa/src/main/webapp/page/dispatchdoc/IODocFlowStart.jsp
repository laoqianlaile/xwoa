<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>选择工作流</title>
</head>
<body>
<center>
	<fieldset style="padding:10px;display:block;margin-bottom:10px;width:70%;height:400px;">
		<legend style="padding:4px 8px 3px;"><b>发改委业务流程</b></legend><!--
		<c:if test="${not empty flowList}">
			<c:forEach var="flowDescribe" items="${flowList}">
				&nbsp;
				<c:if test="${fn:indexOf(flowDescribe.flowDesc,'收文') lt 0}">
					<a href="${pageContext.request.contextPath}/dispatchdoc/dispatchDoc!startDispatchDoc.do?flowCode=${flowDescribe.cid.flowCode}&object.optBaseInfo.optId=DISPATCH_DOC&version=${flowDescribe.cid.version}">
						<c:out value="${flowDescribe.flowName}"></c:out>
					</a>
				</c:if>
				<c:if test="${fn:indexOf(flowDescribe.flowDesc,'收文') gt -1}">
					<a href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!edit.do?wfcode=${flowDescribe.cid.flowCode}&object.optBaseInfo.optId=DISPATCH_DOC&version=${flowDescribe.cid.version}">
						<c:out value="${flowDescribe.flowName}"></c:out>
					</a>
				</c:if>
				<br><br>
			</c:forEach>
		</c:if>
		-->
<table>
	<tr>
		<td><a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!registerProjectEdit.do?optBaseInfo.flowCode=000490&optBaseInfo.optId=DISPATCH_DOC"><span
			class="btn">内外商投资项目</span></a></td>
		<td>
		<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?flowCode=000535&optId=IO_DOC&itemId=SD370000FG-GW-0001"><span
			class="btn">办（阅办）件</span></a>
		</td>
		<td>
		<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!registerPiece.do?flowCode=000533&optId=IO_DOC&itemId=SD370000FG-GW-0002"><span
			class="btn">阅件</span></a>
		</td>
		<td>
		<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?flowCode=000536&optId=IO_DOC&itemId=SD370000FG-GW-0003"><span
			class="btn">会签件</span></a>
		</td>
	</tr>
	
	<tr>
		<td><a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?flowCode=000711&optId=IO_DOC&itemId=SD370000FG-GW-0004"><span
			class="btn">督查件</span></a>
			</td>
		<td>
		<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!registerDoOrReadEdit.do?flowCode=000730&optId=IO_DOC&itemId=SD370000FG-GW-0005"><span
			class="btn">人大代表</span></a>
		</td>
		<td>
		<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!registerPiece.do?flowCode=000530&optId=IO_DOC&itemId=SD370000FG-GW-0006"><span
			class="btn">委内督查件</span></a>
		</td>
		<td>
		<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/dispatchDoc!registerEdit.do?flowCode=000534&optId=IO_DOC&itemId=SD370000FG-GW-0007"><span
			class="btn">主动拟发文</span></a>
		</td>
	</tr>
	<tr>
	<td>
<!-- 	<a class="btnA" -->
<%-- 			href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!edit.do?declare=yes&optBaseInfo.optId=IO_DOC"><span --%>
<!-- 			class="btn">公文登记</span></a>	 -->
<a class="btnA"
			href="${pageContext.request.contextPath}/powerbase/vsuppowerinusing!listDocPower.do?declare=yes&applyItemType=YBXM,YBQL,MHJG,SBMS&s_orgId=${session.SPRING_SECURITY_CONTEXT.authentication.principal.primaryUnit}">
		<span class="btn">公文登记</span></a>
	</td>
	<td>
		<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/dispatchDoc!registerEdit.do?flowCode=000692&optId=IO_DOC&itemId=SD370000FG-GW-0008"><span
			class="btn">代拟发文</span></a>
		</td>
	<td>
	<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!listSyncIncomeDoc.do"><span
			class="btn">待接收列表</span></a>	
	</td>
	<td>
	<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/ioDocArchiveNo!showZwhReserved.do"><span
			class="btn">文号预留列表</span></a>	
	</td>
	</tr>
	<tr>
	<td>
	<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/ioDocArchiveNo!viewZwhReserved.do"><span
			class="btn">文号预留查看</span></a>
	</td>
	
	<td>
	<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/VUserTransaffair!listFwh.do"><span
			class="btn">已用拟发文文号</span></a>
	</td>
	
	<td>
	<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/VProjectAuditInfo!listResult.do"><span
			class="btn">结果公示报表</span></a>
	</td>
	
	<td>
	<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/dispatchDoc!registerEdit.do?flowCode=000531&optId=IO_DOC&itemId=SD370000FG-GW-0009"><span
			class="btn">专报件</span></a>
	</td>
	</tr>
	<tr>
	<td>
	<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/ioDocArchiveNo!viewZwhReserved.do"><span
			class="btn">文号预留查看</span></a>
	</td>
	
	<td>
	<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/VUserTransaffair!listFwh.do"><span
			class="btn">已用拟发文文号</span></a>
	</td>
	
	<td>
	<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/VStuffTransaffair!list.do?s_subItemTypenogw=T"><span
			class="btn">行政审批附件管理</span></a>
	</td>
	
	<td>
	<a class="btnA"
			href="${pageContext.request.contextPath}/dispatchdoc/VStuffTransaffair!list.do?s_subItemTypenogw=F"><span
			class="btn">公文办理附件管理</span></a>
	</td>
	</tr>
</table>
</fieldset>		
	</center>
</body>
</html>