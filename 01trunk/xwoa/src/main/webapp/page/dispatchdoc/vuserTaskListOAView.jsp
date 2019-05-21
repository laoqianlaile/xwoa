<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="vuserTaskListOA.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="vuserTaskListOA.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='dispatchdoc/vuserTaskListOA!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.taskId" />
					</td>
					<td align="left">
						<s:property value="%{taskId}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.nodeInstId" />
					</td>
					<td align="left">
						<s:property value="%{nodeInstId}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.unitCode" />
					</td>
					<td align="left">
						<s:property value="%{unitCode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.userCode" />
					</td>
					<td align="left">
						<s:property value="%{userCode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.roleType" />
					</td>
					<td align="left">
						<s:property value="%{roleType}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.roleCode" />
					</td>
					<td align="left">
						<s:property value="%{roleCode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optId" />
					</td>
					<td align="left">
						<s:property value="%{optId}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.flowInstId" />
					</td>
					<td align="left">
						<s:property value="%{flowInstId}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.flowOptName" />
					</td>
					<td align="left">
						<s:property value="%{flowOptName}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.flowOptTag" />
					</td>
					<td align="left">
						<s:property value="%{flowOptTag}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.authDesc" />
					</td>
					<td align="left">
						<s:property value="%{authDesc}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.nodeName" />
					</td>
					<td align="left">
						<s:property value="%{nodeName}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.nodeType" />
					</td>
					<td align="left">
						<s:property value="%{nodeType}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.nodeOptType" />
					</td>
					<td align="left">
						<s:property value="%{nodeOptType}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optName" />
					</td>
					<td align="left">
						<s:property value="%{optName}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.methodName" />
					</td>
					<td align="left">
						<s:property value="%{methodName}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optUrl" />
					</td>
					<td align="left">
						<s:property value="%{optUrl}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optMethod" />
					</td>
					<td align="left">
						<s:property value="%{optMethod}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optDesc" />
					</td>
					<td align="left">
						<s:property value="%{optDesc}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optCode" />
					</td>
					<td align="left">
						<s:property value="%{optCode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optParam" />
					</td>
					<td align="left">
						<s:property value="%{optParam}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.inststate" />
					</td>
					<td align="left">
						<s:property value="%{inststate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.nodeCreateTime" />
					</td>
					<td align="left">
						<s:property value="%{nodeCreateTime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.expireOptSign" />
					</td>
					<td align="left">
						<s:property value="%{expireOptSign}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.expireOpt" />
					</td>
					<td align="left">
						<s:property value="%{expireOpt}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.grantor" />
					</td>
					<td align="left">
						<s:property value="%{grantor}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.timeLimit" />
					</td>
					<td align="left">
						<s:property value="%{timeLimit}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.lastUpdateUser" />
					</td>
					<td align="left">
						<s:property value="%{lastUpdateUser}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.lastUpdateTime" />
					</td>
					<td align="left">
						<s:property value="%{lastUpdateTime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.flowPhase" />
					</td>
					<td align="left">
						<s:property value="%{flowPhase}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.nodeCode" />
					</td>
					<td align="left">
						<s:property value="%{nodeCode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.djId" />
					</td>
					<td align="left">
						<s:property value="%{djId}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.powerid" />
					</td>
					<td align="left">
						<s:property value="%{powerid}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.powername" />
					</td>
					<td align="left">
						<s:property value="%{powername}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.flowCode" />
					</td>
					<td align="left">
						<s:property value="%{flowCode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.createuser" />
					</td>
					<td align="left">
						<s:property value="%{createuser}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.createdate" />
					</td>
					<td align="left">
						<s:property value="%{createdate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.caseNo" />
					</td>
					<td align="left">
						<s:property value="%{caseNo}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.sendArchiveNo" />
					</td>
					<td align="left">
						<s:property value="%{sendArchiveNo}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.acceptArchiveNo" />
					</td>
					<td align="left">
						<s:property value="%{acceptArchiveNo}" />
					</td>
				</tr>	

</table>



</body>
</html>
