<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaLeaderunits.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaLeaderunits.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='powerruntime/oaLeaderunits!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaderunits.leadercode" />
					</td>
					<td align="left">
						<s:property value="%{leadercode}" />
					</td>
				</tr>
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaderunits.unitcode" />
					</td>
					<td align="left">
						<s:property value="%{unitcode}" />
					</td>
				</tr>


</table>



</body>
</html>
