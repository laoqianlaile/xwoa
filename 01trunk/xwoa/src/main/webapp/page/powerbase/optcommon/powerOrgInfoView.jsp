<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="powerOrgInfo.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="powerOrgInfo.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='powerruntime/powerOrgInfo!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="powerOrgInfo.itemId" />
					</td>
					<td align="left">
						<s:property value="%{itemId}" />
					</td>
				</tr>
  
				<tr>
					<td class="TDTITLE">
						<s:text name="powerOrgInfo.unitcode" />
					</td>
					<td align="left">
						<s:property value="%{unitcode}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="powerOrgInfo.wfcode" />
					</td>
					<td align="left">
						<s:property value="%{wfcode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="powerOrgInfo.setoperator" />
					</td>
					<td align="left">
						<s:property value="%{setoperator}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="powerOrgInfo.setime" />
					</td>
					<td align="left">
						<s:property value="%{setime}" />
					</td>
				</tr>	

</table>



</body>
</html>
