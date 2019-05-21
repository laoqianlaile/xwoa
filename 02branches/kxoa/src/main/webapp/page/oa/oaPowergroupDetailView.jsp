<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaPowergroupDetail.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaPowergroupDetail.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/oaPowergroupDetail!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaPowergroupDetail.id" />
					</td>
					<td align="left">
						<s:property value="%{id}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaPowergroupDetail.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaPowergroupDetail.usercode" />
					</td>
					<td align="left">
						<s:property value="%{usercode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaPowergroupDetail.creatertime" />
					</td>
					<td align="left">
						<s:property value="%{creatertime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaPowergroupDetail.depid" />
					</td>
					<td align="left">
						<s:property value="%{depid}" />
					</td>
				</tr>	

</table>



</body>
</html>
