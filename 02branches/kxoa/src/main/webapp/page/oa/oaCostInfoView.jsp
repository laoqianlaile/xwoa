<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<%-- <sj:head locale="zh_CN" /> --%>


</head>

<body class="sub-flow">


<%@ include file="/page/common/messages.jsp"%>

<a href='oa/oaCostInfo!list.do?s_djid=${djid}&djid=${djid}&show_type=${show_type}' >
	<s:text name="opt.btn.back" />
</a>
<p>	
 <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
  
				<tr>
					<td class="addTd">
						<s:text name="oaCostInfo.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
			
					<td class="addTd">
						<s:text name="oaCostInfo.djid" />
					</td>
					<td align="left">
						<s:property value="%{djid}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaCostInfo.costType" />
					</td>
					<td align="left">
						<s:property value="%{costType}" />
					</td>
			
					<td class="addTd">
						<s:text name="oaCostInfo.thecost" />
					</td>
					<td align="left">
						<s:property value="%{thecost}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaCostInfo.remark" />
					</td>
					<td align="left"colspan="3">
						<s:property value="%{remark}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaCostInfo.creater" />
					</td>
					<td align="left">
					 ${cp:MAPVALUE("usercode",creater)} 
					</td>
			
					<td class="addTd">
						<s:text name="oaCostInfo.creatertime" />
					</td>
					<td align="left">
					<s:date name="creatertime" format="yyyy-MM-dd" />
					</td>
				</tr>	

</table>



</body>
</html>
