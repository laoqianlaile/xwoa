<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="suppower.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding:10px;">
		<legend>
			查询条件
		</legend>

		<s:form action="supPower" namespace="/powerbase"	style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
			<input type="hidden" name="s_orgId" value="${session.SPRING_SECURITY_CONTEXT.authentication.principal.primaryUnit}">
			<input type="hidden" name="s_itemType" value="${itemType}">	
			<input type="hidden" name="itemType" value="${itemType}">	
				<tr>
					<td class="addTd">权力编号:</td>
					<td>
					<s:textfield name="s_itemId" style="width:180px" value="%{#parameters['s_itemId']}" /></td>
				</tr>		
				<tr>
					<td class="addTd">权力名称:</td>
					<td>
					<s:textfield name="s_itemName" style="width:180px" value="%{#parameters['s_itemName']}" /></td>
				</tr>

				<tr>
					<td align="center" colspan="2">
					<s:submit method="listSupPower" key="opt.btn.query" cssClass="btn" /> 
					<input type="button" class="btn" value="关闭" onclick="window.close();">
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="powerbase/suppower!listSupPower_Sd.do" items="selectPowerList" var="suppower"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		
		<input type="hidden" id="itemId${suppower.itemId}" name="itemId${suppower.itemId}" value="${suppower.itemId}">
		<input type="hidden" id="itemName${suppower.itemId}" name="itemName${suppower.itemId}" value="${suppower.itemName}">
		<input type="hidden" id="orgId${suppower.itemId}" name="orgId${suppower.itemId}" value="${suppower.orgId}">
		<input type="hidden" id="orgName${suppower.itemId}" name="orgName${suppower.itemId}" value='${cp:MAPVALUE("unitcode",suppower.orgId)}'>
		<input type="hidden" id="applyItemType${suppower.itemId}" name="applyItemType${suppower.itemId}" value="${suppower.applyItemType}">
		<ec:row>
			<ec:column property="itemId" title="权力编号"	style="text-align:center" sortable="false"/>

			<ec:column property="itemName" title="权力名称"	style="text-align:center" sortable="false">	
				<c:choose>
					<c:when test="${fn:length(suppower.itemName) > 30}">
						<c:out value="${fn:substring(suppower.itemName, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${suppower.itemName}" />
					</c:otherwise>
				</c:choose>
			</ec:column>
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">
				<input type="radio" name="itemId"
					onclick="setParentVal('${suppower.itemId}')">
			</ec:column>
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">	
	var parentDocument = window.opener.document;//获取父页面对象

	//设置返回值
	function setParentVal(itemId) {
		if (window.confirm("确认选择此项权力吗？选择后窗口将自动关闭。")) {
				parentDocument.getElementById('powername').value = document.getElementById('itemName' + itemId).value;
				parentDocument.getElementById('itemId').value = document.getElementById('itemId' + itemId).value;
				window.close();
		}
		/*****************业务数据页面跳转end*********/
	}
</script>
</html>
