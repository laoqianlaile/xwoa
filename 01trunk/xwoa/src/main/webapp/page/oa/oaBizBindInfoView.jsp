<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaBizBindInfo.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaBizBindInfo.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/oaBizBindInfo!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.startDjid" />
					</td>
					<td align="left">
						<s:property value="%{startDjid}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.endDjid" />
					</td>
					<td align="left">
						<s:property value="%{endDjid}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.bizType" />
					</td>
					<td align="left">
						<s:property value="%{bizType}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.createtime" />
					</td>
					<td align="left">
						<s:property value="%{createtime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.createuser" />
					</td>
					<td align="left">
						<s:property value="%{createuser}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.nodeinstid" />
					</td>
					<td align="left">
						<s:property value="%{nodeinstid}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.nodename" />
					</td>
					<td align="left">
						<s:property value="%{nodename}" />
					</td>
				</tr>	

</table>



</body>
</html>
