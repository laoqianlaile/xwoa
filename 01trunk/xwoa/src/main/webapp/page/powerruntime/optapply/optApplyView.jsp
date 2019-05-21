<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="srPermitApply.view.title" /></title>
</head>

<body class="sub-flow">
	<c:set var="notitle" value="${not empty param['notitle'] ? 'notitle' : ''}"/>
	    <h3 class="sub-flow-title ${notitle }">事项信息</h3>
<!-- <input type="button" value="返回" Class="btn" onclick="window.history.back()" /> -->

	
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
  					

				<tr>
					<td class="addTd">
						事项名称
					</td>
					<td align="left" colspan="3">
						${object.optBaseInfo.powername}
					</td>
				</tr>	
				
				<tr>
					<td class="addTd">
						事项标题
					</td>
					<td align="left" colspan="3">
						${object.optBaseInfo.transaffairname}
					</td>
				</tr>
				<tr>
					<td class="addTd">
						<c:if test="${object.optBaseInfo.itemtype eq'SQ'}">接收部门</c:if>
						<c:if test="${object.optBaseInfo.itemtype eq'QB'}">接收人员</c:if>
					</td>
					<td align="left" colspan="3">
						${unitValue}
					</td>
				</tr>
				<tr id="displayApply">
					<td class="addTd">
						事项内容
					</td>
					<td align="left" colspan="3">
						${object.optBaseInfo.content}
					</td>
				</tr>
				<c:if test="${not empty object.proposerName}">
				<tr>
					<td class="addTd">
						申请理由
					</td>
					<td align="left" colspan="3">
						<s:property value="%{applyReason}" />
					</td>
				</tr>	
			<tr>
				<td class="addTd">
						申请者
					</td>
					<td align="left" width="42%">
						<s:property value="%{proposerName}" />
					</td>
					<td class="addTd">
						申请日期
					</td>
					<td align="left" align="left" width="33%">
						<fmt:formatDate value="${object.applyDate }" pattern="yyyy-MM-dd" />
					</td>					
				</tr>
				<tr>
					<td class="addTd">
						申请方式
					</td>
					<td align="left">
					${cp:MAPVALUE('bjsqfs',object.applyWay) }
					</td>
				
					<td class="addTd">
						申请者类别 
					</td>
					<td align="left">
					${cp:MAPVALUE('PROPOSER_TYPE',object.proposerType) }
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						申请者证件类型
					</td>
					<td align="left">
					${cp:MAPVALUE('PaperType',object.proposerPaperType) }
					</td>
					<td class="addTd">
						申请者证件号码
					</td>
					<td align="left">
						<s:property value="%{proposerPaperCode}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						申请者电话
					</td>
					<td align="left">
						<s:property value="%{proposerPhone}" />
					</td>
					<td class="addTd">
						申请者手机
					</td>
					<td align="left">
						<s:property value="%{proposerMobile}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						申请者地址
					</td>
					<td align="left" colspan="3">
						<s:property value="%{proposerAddr}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						申请者邮编
					</td>
					<td align="left">
						<s:property value="%{proposerZipcode}" />
					</td>
					<td class="addTd">
						申请者电子邮件
					</td>
					<td align="left">
						<s:property value="%{proposerEmail}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						申请者机构代码
					</td>
					<td align="left">
						<s:property value="%{proposerUnitcode}" />
					</td>
					<td class="addTd">
						代理人姓名
					</td>
					<td align="left">
						<s:property value="%{agentName}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						代理人证件类型
					</td>
					<td align="left">
<%-- 						<s:property value="%{agentPaperType}" /> --%>
						${cp:MAPVALUE('PaperType',object.agentPaperType) }
					</td>
					<td class="addTd">
						代理人证件号码
					</td>
					<td align="left">
						<s:property value="%{agentPaperCode}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						代理人电话
					</td>
					<td align="left">
						<s:property value="%{agentPhone}" />
					</td>
					<td class="addTd">
						代理人手机
					</td>
					<td align="left">
						<s:property value="%{agentMobile}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						代理人地址 
					</td>
					<td align="left" colspan="3">
						<s:property value="%{agentAddr}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						代理人邮编
					</td>
					<td align="left">
						<s:property value="%{agentZipcode}" />
					</td>
					<td class="addTd">
						代理人电子邮件
					</td>
					<td align="left">
						<s:property value="%{agentEmail}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						代理人机构代码
					</td>
					<td align="left" colspan="3">
						<s:property value="%{agentUnitcode}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						申请备注 
					</td>
					<td align="left" colspan="3">
						<s:property value="%{applyMemo}" />
					</td>
				</tr>	
				</c:if>
</table>
</body>
</html>
