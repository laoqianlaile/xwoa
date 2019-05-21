<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaAccidentRecord.view.title" />
</title>
</head>

<body>

	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<a href='oa/oaAccidentRecord!list.do?s_djid=${djid}&djid=${djid}&show_type=${show_type}'>
	<s:text name="opt.btn.back" />
</a>
	<p>
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
		<tr>
			<td class="addTd"><s:text name="oaAccidentRecord.no" /></td>
			<td align="left"><s:property value="%{no}" /></td>
	
			<td class="addTd"><s:text name="oaAccidentRecord.djid" /></td>
			<td align="left"><s:property value="%{djid}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaAccidentRecord.carno" /></td>
			<td align="left"><s:property value="%{carno}" /></td>
	
			<td class="addTd"><s:text name="oaAccidentRecord.carType" />
			</td>
			<td align="left"><s:property value="%{carType}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaAccidentRecord.brand" /></td>
			<td align="left"><s:property value="%{brand}" /></td>
		
			<td class="addTd"><s:text name="oaAccidentRecord.modelType" />
			</td>
			<td align="left"><s:property value="%{modelType}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaAccidentRecord.depno" /></td>
			<td align="left"><s:property value="%{depno}" /></td>
	
			<td class="addTd"><s:text name="oaAccidentRecord.douser" /></td>
			<td align="left"><s:property value="%{douser}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaAccidentRecord.creater" />
			</td>
			<td align="left"> ${cp:MAPVALUE("usercode",creater)} </td>
	
			<td class="addTd"><s:text name="oaAccidentRecord.creatertime" />
			</td>
			<td align="left"><s:date name="%{creatertime}" format="yyyy-MM-dd HH:mm" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaAccidentRecord.dotime" /></td>
			<td align="left"><s:date name="%{dotime}" format="yyyy-MM-dd HH:mm" /></td>
		
			<td class="addTd"><s:text name="oaAccidentRecord.doaddress" />
			</td>
			<td align="left" colspan="3"><s:property value="%{doaddress}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text
					name="oaAccidentRecord.responsibility" /></td>
			<td align="left"><s:property value="%{responsibility}" /></td>
		
			<td class="addTd"><s:text name="oaAccidentRecord.casualties" />
			</td>
			<td align="left"><s:property value="%{casualties}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaAccidentRecord.losses" /></td>
			<td align="left"><s:property value="%{losses}" /></td>
	
			<td class="addTd"><s:text name="oaAccidentRecord.penalty" />
			</td>
			<td align="left"><s:property value="%{penalty}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text
					name="oaAccidentRecord.actualDamages" /></td>
			<td align="left"><s:property value="%{actualDamages}" /></td>
		
			<td class="addTd"><s:text
					name="oaAccidentRecord.claimDifference" /></td>
			<td align="left"><s:property value="%{claimDifference}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text
					name="oaAccidentRecord.accidentAfter" /></td>
			<td align="left" colspan="3"><s:property value="%{accidentAfter}" /></td>
		</tr>

	
		<tr>
			<td class="addTd"><s:text name="oaAccidentRecord.photoName" />
			</td>
			<td align="left" colspan="3"><s:property value="%{photoName}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaAccidentRecord.remark" /></td>
			<td align="left" colspan="3"><s:property value="%{remark}" /></td>
		</tr>

	</table>



</body>
</html>
