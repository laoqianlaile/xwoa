<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="voaBizBindInfo.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="voaBizBindInfo.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/voaBizBindInfo!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.djId" />
					</td>
					<td align="left">
						<s:property value="%{djId}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.transaffairname" />
					</td>
					<td align="left">
						<s:property value="%{transaffairname}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.orgcode" />
					</td>
					<td align="left">
						<s:property value="%{orgcode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.powerid" />
					</td>
					<td align="left">
						<s:property value="%{powerid}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.powername" />
					</td>
					<td align="left">
						<s:property value="%{powername}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.bizstate" />
					</td>
					<td align="left">
						<s:property value="%{bizstate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.biztype" />
					</td>
					<td align="left">
						<s:property value="%{biztype}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.createdate" />
					</td>
					<td align="left">
						<s:property value="%{createdate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.itemtype" />
					</td>
					<td align="left">
						<s:property value="%{itemtype}" />
					</td>
				</tr>	

</table>



</body>
</html>
