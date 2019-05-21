<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaInfosummary.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaInfosummary.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/oaInfosummary!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.id" />
					</td>
					<td align="left">
						<s:property value="%{id}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.creater" />
					</td>
					<td align="left">
						<s:property value="%{creater}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.unitcode" />
					</td>
					<td align="left">
						<s:property value="%{unitcode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.telephone" />
					</td>
					<td align="left">
						<s:property value="%{telephone}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.sex" />
					</td>
					<td align="left">
						<s:property value="%{sex}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.remark" />
					</td>
					<td align="left">
						<s:property value="%{remark}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.creatertime" />
					</td>
					<td align="left">
						<s:property value="%{creatertime}" />
					</td>
				</tr>	

</table>



</body>
</html>
