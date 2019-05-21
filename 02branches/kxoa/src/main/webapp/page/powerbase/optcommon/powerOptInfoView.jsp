<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="powerOptInfo.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="powerOptInfo.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='powerruntime/powerOptInfo!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="powerOptInfo.itemId" />
					</td>
					<td align="left">
						<s:property value="%{itemId}" />
					</td>
				</tr>
  
				<tr>
					<td class="TDTITLE">
						<s:text name="powerOptInfo.wfcode" />
					</td>
					<td align="left">
						<s:property value="%{wfcode}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="powerOptInfo.riskid" />
					</td>
					<td align="left">
						<s:property value="%{riskid}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="powerOptInfo.setoperator" />
					</td>
					<td align="left">
						<s:property value="%{setoperator}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="powerOptInfo.setime" />
					</td>
					<td align="left">
						<s:property value="%{setime}" />
					</td>
				</tr>	

</table>



</body>
</html>
