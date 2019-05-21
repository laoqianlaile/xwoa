<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="optProcAttention.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="optProcAttention.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='powerruntime/optProcAttention!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="optProcAttention.djId" />
					</td>
					<td align="left">
						<s:property value="%{djId}" />
					</td>
				</tr>
  
				<tr>
					<td class="TDTITLE">
						<s:text name="optProcAttention.usercode" />
					</td>
					<td align="left">
						<s:property value="%{usercode}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="optProcAttention.attsettime" />
					</td>
					<td align="left">
						<s:property value="%{attsettime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optProcAttention.attsetuser" />
					</td>
					<td align="left">
						<s:property value="%{attsetuser}" />
					</td>
				</tr>	

</table>



</body>
</html>
