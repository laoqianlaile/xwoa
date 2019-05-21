
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>会议室查看</title>
</head>

<body>
<%@ include file="/page/common/messages.jsp"%>
  <s:hidden name="viewtype" value="%{viewtype}"></s:hidden>

		<p>	
		<fieldset >
		<legend>
		 基本信息
		</legend>
			
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">		
  
				<tr>
					<td class="addTd">
						流水号
					</td>
					<td align="left" colspan="3">
						<s:property value="%{djId}" />
					</td>
					</tr>
					<tr>
					<td class="addTd">
						申请单号
					</td>
					<td align="left">
						<s:property value="%{applyNo}" />
					</td>
			
					<td class="addTd">
						申请部门
					</td>
					<td align="left">
						 ${cp:MAPVALUE("unitcode",depno)}
					</td>
				</tr>	
				<tr>
					<td class="addTd">
						申请人
					</td>
					<td align="left">
							 ${cp:MAPVALUE("usercode",creater)}
					</td>

					<td class="addTd">
						申请日期
					</td>
					<td align="left">
						<s:date name="createtime" format="yyyy-MM-dd HH:mm" />
					
					</td>
				</tr>	
         	
				<tr>
						<td class="addTd">
						联系电话
					</td>
					<td align="left">
						<s:property value="%{telephone}" />
					</td>	
					<td class="addTd">
						会议室
					</td>
					<td align="left">
						${oaMeetApply.oaMeetinfo.meetingName} 
						
						
					</td>
				</tr>	
           
				<tr>
					<td class="addTd">
						预计开始时间
					</td>
					<td align="left">
						<s:date name="planBegTime" format="yyyy-MM-dd HH:mm" />
					
					</td>
					<td class="addTd">
						预计结束时间
					</td>
					<td align="left">
						<s:date name="planEndTime" format="yyyy-MM-dd HH:mm" />
					
					</td>
				</tr>
				
				
					<td class="addTd">
						需求描述
					</td>
					<td align="left" colspan="3">
					 <s:property value="%{reqRemark}" />
					</td>
					</tr>
					<tr>
	             	<td class="addTd">
					备注
					</td>
					<td align="left" colspan="3">
					<s:property value="%{remark}" />
					</td>
	
				</tr>	
					
		  <tr>
					<td class="addTd">
						业务状态
					</td>
					<td align="left">
					${cp:MAPVALUE("bizType",bizstate)}
					</td>
						<td class="addTd">
						申请状态
					</td>
					<td align="left">
					${cp:MAPVALUE("solvestatus",solvestatus)}
					</td>
				</tr>	
            <c:if test="${not empty solvetime  }">
				<tr>
					<td class="addTd">
						办结时间
					</td>
					<td align="left">
						<s:property  value="%{solvetime}" />
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
				
				<c:if test="${not empty doBegTime}">
			<fieldset class="_new">
					<legend>
					 安排信息
					</legend>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">		

					<tr>
					<td class="addTd">
						安排开始时间
					</td>
					<td align="left">
						<s:date name="doBegTime" format="yyyy-MM-dd HH:mm" />
					
					</td>
					<td class="addTd">
						安排结束时间
					</td>
					<td align="left">
						<s:date name="doEndTime" format="yyyy-MM-dd HH:mm" />
					
					</td>
				</tr>
				<tr>
					<td class="addTd" >
						安排人
					</td>
					<td align="left" >
						<s:property value="%{doCreater}" />
					</td>
						<td class="addTd" >
						安排部门
					</td>
					<td align="left" >
						<s:property value="%{doDepno}" />
					</td>
				</tr>
				<tr>
					<td class="addTd" >
						安排备注
					</td>
					<td align="left" colspan="3">
						<s:property value="%{doRemark}" />
					</td>
					
				</tr>
				</table>
				</fieldset>
				</c:if>
			

</body>
</html>


