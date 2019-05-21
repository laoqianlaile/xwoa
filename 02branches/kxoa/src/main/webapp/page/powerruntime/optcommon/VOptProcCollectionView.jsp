<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="VOptProcCollection.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="VOptProcCollection.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='powerruntime/VOptProcCollection!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.djId" />
					</td>
					<td align="left">
						<s:property value="%{djId}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.nodeInstId" />
					</td>
					<td align="left">
						<s:property value="%{nodeInstId}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.unitCode" />
					</td>
					<td align="left">
						<s:property value="%{unitCode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.userCode" />
					</td>
					<td align="left">
						<s:property value="%{userCode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.roleType" />
					</td>
					<td align="left">
						<s:property value="%{roleType}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.roleCode" />
					</td>
					<td align="left">
						<s:property value="%{roleCode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.optId" />
					</td>
					<td align="left">
						<s:property value="%{optId}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.flowInstId" />
					</td>
					<td align="left">
						<s:property value="%{flowInstId}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.flowOptTag" />
					</td>
					<td align="left">
						<s:property value="%{flowOptTag}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.nodeName" />
					</td>
					<td align="left">
						<s:property value="%{nodeName}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.methodName" />
					</td>
					<td align="left">
						<s:property value="%{methodName}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.optUrl" />
					</td>
					<td align="left">
						<s:property value="%{optUrl}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.optMethod" />
					</td>
					<td align="left">
						<s:property value="%{optMethod}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.optCode" />
					</td>
					<td align="left">
						<s:property value="%{optCode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.optParam" />
					</td>
					<td align="left">
						<s:property value="%{optParam}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.inststate" />
					</td>
					<td align="left">
						<s:property value="%{inststate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.grantor" />
					</td>
					<td align="left">
						<s:property value="%{grantor}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.timeLimit" />
					</td>
					<td align="left">
						<s:property value="%{timeLimit}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.lastUpdateTime" />
					</td>
					<td align="left">
						<s:property value="%{lastUpdateTime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.flowPhase" />
					</td>
					<td align="left">
						<s:property value="%{flowPhase}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.transaffairname" />
					</td>
					<td align="left">
						<s:property value="%{transaffairname}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.bizstate" />
					</td>
					<td align="left">
						<s:property value="%{bizstate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.createdate" />
					</td>
					<td align="left">
						<s:property value="%{createdate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.transAffairNo" />
					</td>
					<td align="left">
						<s:property value="%{transAffairNo}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.atttype" />
					</td>
					<td align="left">
						<s:property value="%{atttype}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.attsettime" />
					</td>
					<td align="left">
						<s:property value="%{attsettime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.isatt" />
					</td>
					<td align="left">
						<s:property value="%{isatt}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="VOptProcCollection.removesettime" />
					</td>
					<td align="left">
						<s:property value="%{removesettime}" />
					</td>
				</tr>	

</table>



</body>
</html>
