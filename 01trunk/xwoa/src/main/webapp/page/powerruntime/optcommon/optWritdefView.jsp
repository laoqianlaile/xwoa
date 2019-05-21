<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="optWritdef.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="optWritdef.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='wwd/optWritdef!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="optWritdef.writid" />
					</td>
					<td align="left">
						<s:property value="%{writid}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="optWritdef.temptype" />
					</td>
					<td align="left">
						<s:property value="%{temptype}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optWritdef.recordid" />
					</td>
					<td align="left">
						<s:property value="%{recordid}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optWritdef.writcode" />
					</td>
					<td align="left">
						<s:property value="%{writcode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optWritdef.initvalue" />
					</td>
					<td align="left">
						<s:property value="%{initvalue}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optWritdef.isinuse" />
					</td>
					<td align="left">
						<s:property value="%{isinuse}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="optWritdef.remark" />
					</td>
					<td align="left">
						<s:property value="%{remark}" />
					</td>
				</tr>	

</table>



</body>
</html>
