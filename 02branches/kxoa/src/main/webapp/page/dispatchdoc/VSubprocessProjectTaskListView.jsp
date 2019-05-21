<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="" method="post" id="pagerForm">
		<div class="searchBar">
			<ul class="searchContent">
				
				<li><label><c:out value="VSubprocessProjectTaskList.taskId" />:</label> <c:out value="${VSubprocessProjectTaskList.taskId}" /></li>  
				<li><label><c:out value="VSubprocessProjectTaskList.nodeInstId" />:</label> <c:out value="${VSubprocessProjectTaskList.nodeInstId}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.unitCode" />:</label> <c:out value="${VSubprocessProjectTaskList.unitCode}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.userCode" />:</label> <c:out value="${VSubprocessProjectTaskList.userCode}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.roleType" />:</label> <c:out value="${VSubprocessProjectTaskList.roleType}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.roleCode" />:</label> <c:out value="${VSubprocessProjectTaskList.roleCode}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.optId" />:</label> <c:out value="${VSubprocessProjectTaskList.optId}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.flowInstId" />:</label> <c:out value="${VSubprocessProjectTaskList.flowInstId}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.flowOptName" />:</label> <c:out value="${VSubprocessProjectTaskList.flowOptName}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.flowOptTag" />:</label> <c:out value="${VSubprocessProjectTaskList.flowOptTag}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.authDesc" />:</label> <c:out value="${VSubprocessProjectTaskList.authDesc}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.nodeName" />:</label> <c:out value="${VSubprocessProjectTaskList.nodeName}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.nodeType" />:</label> <c:out value="${VSubprocessProjectTaskList.nodeType}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.nodeOptType" />:</label> <c:out value="${VSubprocessProjectTaskList.nodeOptType}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.optName" />:</label> <c:out value="${VSubprocessProjectTaskList.optName}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.methodName" />:</label> <c:out value="${VSubprocessProjectTaskList.methodName}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.optUrl" />:</label> <c:out value="${VSubprocessProjectTaskList.optUrl}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.optMethod" />:</label> <c:out value="${VSubprocessProjectTaskList.optMethod}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.optDesc" />:</label> <c:out value="${VSubprocessProjectTaskList.optDesc}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.optCode" />:</label> <c:out value="${VSubprocessProjectTaskList.optCode}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.optParam" />:</label> <c:out value="${VSubprocessProjectTaskList.optParam}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.inststate" />:</label> <c:out value="${VSubprocessProjectTaskList.inststate}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.nodeCreateTime" />:</label> <c:out value="${VSubprocessProjectTaskList.nodeCreateTime}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.expireOptSign" />:</label> <c:out value="${VSubprocessProjectTaskList.expireOptSign}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.expireOpt" />:</label> <c:out value="${VSubprocessProjectTaskList.expireOpt}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.grantor" />:</label> <c:out value="${VSubprocessProjectTaskList.grantor}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.timeLimit" />:</label> <c:out value="${VSubprocessProjectTaskList.timeLimit}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.lastUpdateUser" />:</label> <c:out value="${VSubprocessProjectTaskList.lastUpdateUser}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.lastUpdateTime" />:</label> <c:out value="${VSubprocessProjectTaskList.lastUpdateTime}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.flowPhase" />:</label> <c:out value="${VSubprocessProjectTaskList.flowPhase}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.djId" />:</label> <c:out value="${VSubprocessProjectTaskList.djId}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.transaffairname" />:</label> <c:out value="${VSubprocessProjectTaskList.transaffairname}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.bizstate" />:</label> <c:out value="${VSubprocessProjectTaskList.bizstate}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.bizstate" />:</label> <c:out value="${VSubprocessProjectTaskList.biztype}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.orgcode" />:</label> <c:out value="${VSubprocessProjectTaskList.orgcode}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.orgname" />:</label> <c:out value="${VSubprocessProjectTaskList.orgname}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.headunitcode" />:</label> <c:out value="${VSubprocessProjectTaskList.headunitcode}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.headusercode" />:</label> <c:out value="${VSubprocessProjectTaskList.headusercode}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.content" />:</label> <c:out value="${VSubprocessProjectTaskList.content}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.powerid" />:</label> <c:out value="${VSubprocessProjectTaskList.powerid}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.powername" />:</label> <c:out value="${VSubprocessProjectTaskList.powername}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.solvestatus" />:</label> <c:out value="${VSubprocessProjectTaskList.solvestatus}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.solvetime" />:</label> <c:out value="${VSubprocessProjectTaskList.solvetime}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.solvenote" />:</label> <c:out value="${VSubprocessProjectTaskList.solvenote}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.createuser" />:</label> <c:out value="${VSubprocessProjectTaskList.createuser}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.createdate" />:</label> <c:out value="${VSubprocessProjectTaskList.createdate}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.riskType" />:</label> <c:out value="${VSubprocessProjectTaskList.riskType}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.sendArchiveNo" />:</label> <c:out value="${VSubprocessProjectTaskList.sendArchiveNo}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.acceptArchiveNo" />:</label> <c:out value="${VSubprocessProjectTaskList.acceptArchiveNo}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.riskDesc" />:</label> <c:out value="${VSubprocessProjectTaskList.riskDesc}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.riskResult" />:</label> <c:out value="${VSubprocessProjectTaskList.riskResult}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.transAffairNo" />:</label> <c:out value="${VSubprocessProjectTaskList.transAffairNo}" /></li> 
				<li><label><c:out value="VSubprocessProjectTaskList.transAffairQueryKey" />:</label> <c:out value="${VSubprocessProjectTaskList.transAffairQueryKey}" /></li> 
			</ul>

			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<!-- 参数 navTabId 根据实际情况填写 -->
								<button type="button" onclick="javascript:navTabAjaxDone({'statusCode' : 200, 'callbackType' : 'closeCurrent', 'navTabId' : ''});">返回</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
	</div>

	
</div>



<%-- 
<html>
<head>
<title><c:out value="VSubprocessProjectTaskList.view.title" /></title>
<link href="<c:out value='${STYLE_PATH}'/>/css/am.css" type="text/css" rel="stylesheet">

<link href="<c:out value='${STYLE_PATH}'/>/css/messages.css" type="text/css" rel="stylesheet">

</head>

<body>
	<p class="ctitle">
		<c:out value="VSubprocessProjectTaskList.view.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<html:button styleClass="btn" onclick="window.history.back()" property="none">
		<bean:message key="opt.btn.back" />
	</html:button>
	<p>
	<table width="200" border="0" cellpadding="1" cellspacing="1">
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.taskId" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.taskId}" /></td>
		</tr>
		 
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.nodeInstId" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.nodeInstId}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.unitCode" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.unitCode}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.userCode" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.userCode}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.roleType" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.roleType}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.roleCode" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.roleCode}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.optId" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.optId}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.flowInstId" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.flowInstId}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.flowOptName" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.flowOptName}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.flowOptTag" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.flowOptTag}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.authDesc" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.authDesc}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.nodeName" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.nodeName}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.nodeType" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.nodeType}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.nodeOptType" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.nodeOptType}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.optName" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.optName}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.methodName" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.methodName}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.optUrl" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.optUrl}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.optMethod" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.optMethod}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.optDesc" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.optDesc}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.optCode" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.optCode}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.optParam" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.optParam}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.inststate" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.inststate}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.nodeCreateTime" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.nodeCreateTime}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.expireOptSign" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.expireOptSign}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.expireOpt" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.expireOpt}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.grantor" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.grantor}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.timeLimit" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.timeLimit}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.lastUpdateUser" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.lastUpdateUser}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.lastUpdateTime" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.lastUpdateTime}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.flowPhase" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.flowPhase}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.djId" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.djId}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.transaffairname" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.transaffairname}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.bizstate" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.bizstate}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.bizstate" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.bizstate}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.orgcode" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.orgcode}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.orgname" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.orgname}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.headunitcode" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.headunitcode}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.headusercode" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.headusercode}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.content" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.content}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.powerid" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.powerid}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.powername" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.powername}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.solvestatus" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.solvestatus}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.solvetime" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.solvetime}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.solvenote" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.solvenote}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.createuser" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.createuser}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.createdate" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.createdate}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.riskType" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.riskType}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.sendArchiveNo" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.sendArchiveNo}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.acceptArchiveNo" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.acceptArchiveNo}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.riskDesc" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.riskDesc}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.riskResult" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.riskResult}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.transAffairNo" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.transAffairNo}" /></td>
		</tr>
		
		<tr>
			<td class="TDTITLE"><c:out value="VSubprocessProjectTaskList.transAffairQueryKey" /></td>
			<td align="left"><c:out value="${VSubprocessProjectTaskList.transAffairQueryKey}" /></td>
		</tr>
		
	</table>

	

</body>
</html> --%>
