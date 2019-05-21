
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>签报查看</title>
</head>

<body>
<%@ include file="/page/common/messages.jsp"%>
  <s:hidden name="viewtype" value="%{viewtype}"></s:hidden>
  <c:if test="${ 'T' eq viewtype }"> 
<input type="button" onclick="window.history.back();" value="返回" class="btn"> 
</c:if>
<p>	
<fieldset class="_new">
	<legend style="padding:4px 8px 3px;">
	<b>签报拟稿信息</b>
		</legend>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">		
  
				<tr>
					<td class="addTd">
						流水号
					</td>
					<td align="left">
						<s:property value="%{djId}" />
					</td>
					<td class="addTd">
						文件标题
					</td>
					<td align="left">
						<s:property value="%{signedReportTitle}" />
					</td>
				</tr>	

			

				<tr>
					<td class="addTd">
						密级
					</td>
					<td align="left">
						 ${cp:MAPVALUE("FW_SECRETS_LEVEL",secretsDegree)}
					</td>
				
					<td class="addTd">
						紧急程度
					</td>
					<td align="left">
							 ${cp:MAPVALUE("critical_level",criticalLeveal)}
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						签报号
					</td>
					<td align="left">
						<s:property value="%{signedReportNo}" />
					</td>
				
					<td class="addTd">
						签报日期
					</td>
					<td align="left">
						<s:date name="signedDate" format="yyyy-MM-dd" />
					
					</td>
				</tr>	
         	
				<tr>
					<td class="addTd">
							签报人
					</td>
					<td align="left">
						<s:property value="%{signedPersion}" />
					</td>
				
					<td class="addTd">
						会签部门
					</td>
					<td align="left">
						<s:property value="%{otherDep}" />
					</td>
				</tr>	
           
				<tr>
					<td class="addTd">
						主办部门
					</td>
					<td align="left" colspan="3">
						<s:property value="%{mainSignedDep}" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						拟稿人
					</td>
					<td align="left">
						<s:property value="%{draftUserName}" />
					</td>
				
						<td class="addTd">
							拟稿人电话
					</td>
					<td align="left">
						<s:property value="%{draftTelephone}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						保管期限
					</td>
					<td align="left" colspan="3">
					 ${cp:MAPVALUE("RETENTION_PERIOD",retentionPeriod)}
					</td>
	
				</tr>	
		  <tr>
					<td class="addTd">
						业务状态
					</td>
					<td align="left"colspan="3">
					${cp:MAPVALUE("bizType",bizstate)}
					</td>
				</tr>	
            <c:if test="${not empty solvetime  }">
				<tr>
					<td class="addTd">
						办结时间
					</td>
					<td align="left">
			<s:date name="solvetime" format="yyyy-MM-dd" />
						
					</td>
				</tr>
			</c:if>
			 <c:if test="${ not empty solvenote  }">	
				<tr>
					<td class="addTd" >
						办结意见
					</td>
					<td align="left" colspan="3">
						<s:property value="%{solvenote}" />
					</td>
				</tr>
			</c:if>	

</table>

</fieldset>

</body>
</html>


