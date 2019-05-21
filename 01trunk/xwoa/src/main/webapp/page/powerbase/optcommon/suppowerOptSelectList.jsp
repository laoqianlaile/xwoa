<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="suppower.list.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset>
		<legend>
			<s:text name="label.list.filter" />
		</legend>

		<s:form action="supPower" namespace="/powerruntime"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">

				<tr>
					<td class="addTd"><s:text name="suppower.itemId" />:</td>
					<td><s:textfield name="s_itemId" style="width:180px"
							value="%{#parameters['s_itemId']}" /></td>
				</tr>
	
				<tr>
					<td class="addTd"><s:text name="suppower.itemName" />:</td>
					<td><s:textfield name="s_itemName" style="width:180px"
							value="%{#parameters['s_itemName']}" /></td>
				</tr>

				<tr>
					<td align="center" colspan="2"><s:submit method="listSupPowerWithOutOpt"
							key="opt.btn.query" cssClass="btn" /> <input type="button"
						class="btn" value="关闭" onclick="window.close();"></td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="powerruntime/suppower!listSupPowerWithOutOpt.do" items="subPowerList"
		var="suppower"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<input type="hidden" id="itemId${suppower.itemId}"
			name="itemId${suppower.itemId}" value="${suppower.itemId}">
		<input type="hidden" id="itemName${suppower.itemId}"
			name="itemName${suppower.itemId}" value="${suppower.itemName}">	

		<ec:row>

			<c:set var="titemId">
				<s:text name='suppower.itemId' />
			</c:set>
			<ec:column property="itemId" title="${titemId}"
				style="text-align:center" sortable="false"/>

			<c:set var="titemName">
				<s:text name='suppower.itemName' /></c:set>
			<ec:column property="itemName" title="${titemName}"
				style="text-align:center" sortable="false">	
				<c:choose>
					<c:when test="${fn:length(suppower.itemName) > 30}">
						<c:out value="${fn:substring(suppower.itemName, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${suppower.itemName}" />
					</c:otherwise>
				</c:choose>
</ec:column>
			
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">
				<input type="radio" name="itemId"
					onclick="setParentVal('${suppower.itemId}')">
			</ec:column>
		</ec:row>
	</ec:table>

</body>
<script type="text/javascript">
	//获取父页面对象
	var parentDocument = window.opener.document;
	//设置返回值
	function setParentVal(itemId) {
		if (window.confirm("确认选择此项权力吗？选择后窗口将自动关闭。")) {		
				parentDocument.getElementById('itemId').value = document
						.getElementById('itemId' + itemId).value;			
			window.close();
		}
	}
</script>
</html>
