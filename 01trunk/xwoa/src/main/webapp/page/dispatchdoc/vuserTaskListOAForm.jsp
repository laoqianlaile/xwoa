<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="vuserTaskListOA.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="vuserTaskListOA.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="vuserTaskListOA"  method="post" namespace="/dispatchdoc" id="vuserTaskListOAForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.taskId" />
					</td>
					<td align="left">
	
  
							<s:textfield name="taskId" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.nodeInstId" />
					</td>
					<td align="left">
	
  
						<s:textfield name="nodeInstId"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.unitCode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="unitCode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.userCode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="userCode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.roleType" />
					</td>
					<td align="left">
	
  
						<s:textfield name="roleType"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.roleCode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="roleCode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optId" />
					</td>
					<td align="left">
	
  
						<s:textfield name="optId"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.flowInstId" />
					</td>
					<td align="left">
	
  
						<s:textfield name="flowInstId"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.flowOptName" />
					</td>
					<td align="left">
  
						<s:textarea name="flowOptName" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.flowOptTag" />
					</td>
					<td align="left">
  
						<s:textarea name="flowOptTag" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.authDesc" />
					</td>
					<td align="left">
  
						<s:textarea name="authDesc" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.nodeName" />
					</td>
					<td align="left">
  
						<s:textarea name="nodeName" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.nodeType" />
					</td>
					<td align="left">
	
  
						<s:textfield name="nodeType"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.nodeOptType" />
					</td>
					<td align="left">
	
  
						<s:textfield name="nodeOptType"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optName" />
					</td>
					<td align="left">
	
  
						<s:textfield name="optName"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.methodName" />
					</td>
					<td align="left">
	
  
						<s:textfield name="methodName"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optUrl" />
					</td>
					<td align="left">
  
						<s:textarea name="optUrl" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optMethod" />
					</td>
					<td align="left">
	
  
						<s:textfield name="optMethod"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optDesc" />
					</td>
					<td align="left">
  
						<s:textarea name="optDesc" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optCode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="optCode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.optParam" />
					</td>
					<td align="left">
  
						<s:textarea name="optParam" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.inststate" />
					</td>
					<td align="left">
	
  
						<s:textfield name="inststate"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.nodeCreateTime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="nodeCreateTime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.expireOptSign" />
					</td>
					<td align="left">
	
  
						<s:textfield name="expireOptSign"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.expireOpt" />
					</td>
					<td align="left">
	
  
						<s:textfield name="expireOpt"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.grantor" />
					</td>
					<td align="left">
	
  
						<s:textfield name="grantor"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.timeLimit" />
					</td>
					<td align="left">
	
  
						<s:textfield name="timeLimit"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.lastUpdateUser" />
					</td>
					<td align="left">
	
  
						<s:textfield name="lastUpdateUser"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.lastUpdateTime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="lastUpdateTime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.flowPhase" />
					</td>
					<td align="left">
	
  
						<s:textfield name="flowPhase"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.nodeCode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="nodeCode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.djId" />
					</td>
					<td align="left">
	
  
						<s:textfield name="djId"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.powerid" />
					</td>
					<td align="left">
  
						<s:textarea name="powerid" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.powername" />
					</td>
					<td align="left">
  
						<s:textarea name="powername" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.flowCode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="flowCode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.createuser" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createuser"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.createdate" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createdate"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.caseNo" />
					</td>
					<td align="left">
  
						<s:textarea name="caseNo" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.sendArchiveNo" />
					</td>
					<td align="left">
	
  
						<s:textfield name="sendArchiveNo"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="vuserTaskListOA.acceptArchiveNo" />
					</td>
					<td align="left">
	
  
						<s:textfield name="acceptArchiveNo"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
