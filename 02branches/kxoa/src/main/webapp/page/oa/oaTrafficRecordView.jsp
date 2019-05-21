<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaTrafficRecord.view.title" />
</title>
</head>

<body>

	<%@ include file="/page/common/messages.jsp"%>

<a href='oa/oaTrafficRecord!list.do?s_djid=${djid}&djid=${djid}&show_type=${show_type}'>
	<s:text name="opt.btn.back" />
</a>
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
		<tr>
			<td class="addTd"><s:text name="oaTrafficRecord.no" /></td>
			<td align="left"><s:property value="%{no}" /></td>
	
			<td class="addTd"><s:text name="oaTrafficRecord.djid" /></td>
			<td align="left"><s:property value="%{djid}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaTrafficRecord.carno" /></td>
			<td align="left"><s:property value="%{carno}" /></td>
		
			<td class="addTd"><s:text name="oaTrafficRecord.carType" /></td>
			<td align="left"><s:property value="%{carType}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaTrafficRecord.brand" /></td>
			<td align="left"><s:property value="%{brand}" /></td>
	
			<td class="addTd"><s:text name="oaTrafficRecord.modelType" />
			</td>
			<td align="left"><s:property value="%{modelType}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaTrafficRecord.depno" /></td>
			<td align="left"><s:property value="%{depno}" /></td>
	
			<td class="addTd"><s:text name="oaTrafficRecord.douser" /></td>
			<td align="left"><s:property value="%{douser}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaTrafficRecord.dotime" /></td>
			<td align="left"><s:date name="%{dotime}" format="yyyy-MM-dd HH:mm"/></td>
	
			<td class="addTd"><s:text name="oaTrafficRecord.doaddress" />
			</td>
			<td align="left"><s:property value="%{doaddress}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaTrafficRecord.penalty" /></td>
			<td align="left"><s:property value="%{penalty}" /></td>
		
			<td class="addTd"><s:text name="oaTrafficRecord.actualDamages" />
			</td>
			<td align="left"><s:property value="%{actualDamages}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaTrafficRecord.remark" /></td>
			<td align="left" colspan="3"><s:property value="%{remark}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaTrafficRecord.creater" /></td>
			<td align="left">
			${cp:MAPVALUE("usercode",creater)}
			</td>
	
			<td class="addTd">提交时间
			</td>
			<td align="left"><s:date name="%{creatertime}"  format="yyyy-MM-dd HH:mm"/></td>
		</tr>

	</table>



</body>
</html>
