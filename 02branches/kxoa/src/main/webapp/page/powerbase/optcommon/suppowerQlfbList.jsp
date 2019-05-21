<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>权力事项发布</title>
<%-- <sj:head locale="zh_CN" /> --%>
</head>

<body>
	<fieldset   class="search">
		<legend> 查询条件 </legend>

		<s:form action="supPower" namespace="/powerbase" style="margin-top:0;margin-bottom:5">
			<input id="qlState" type="hidden" name="qlState"value="${object.qlState}" />
			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td class="addTd">权力编号:</td>
					<td><s:textfield name="s_item_id"
							value="%{#parameters['s_item_id']}"></s:textfield></td>

					<td class="addTd">权力名称:</td>
					<td><s:textfield name="s_itemName"
							value="%{#parameters['s_itemName']}" /></td>
				</tr>
				<tr>
					<td class="addTd">行使部门:</td>
					<td><select name="s_orgId">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitList }">
								<option value="${row.depno}"
									<c:if test="${parameters.s_orgId[0] eq row.depno}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select></td>

					<td class="addTd">权力类型:</td>
					<td><select name="s_itemType"
						onchange="checkItemType();">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('ITEM_TYPE')}">
								<option value="${row.key}"
									<c:if test="${parameters.s_itemType[0] eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>


				<tr  class="searchButton">
					<td colspan="4"><s:submit method="Qlfblist" key="opt.btn.query" cssClass="btn" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="powerbase/supPower!Qlfblist.do" items="subPowerList" var="suppower"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="itemId" title="权力编号" style="text-align:center" />

			<ec:column property="itemName" title="权力名称" style="text-align:center">
				<c:choose>
					<c:when test="${fn:length(suppower.itemName) > 30}">
						<c:out value="${fn:substring(suppower.itemName, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${suppower.itemName}" />
					</c:otherwise>
				</c:choose>
			</ec:column>

			<ec:column property="orgId" title="所属部门" style="text-align:center">
					${cp:MAPVALUE("depno",suppower.orgId)}
				</ec:column>
			<ec:column property="opt" title="操作" sortable="false" style="text-align:center">
				<a href='supPower!SuppowerQlfbView.do?itemId=${suppower.itemId}&version=${suppower.version}'>发布</a>
			</ec:column>

		</ec:row>
	</ec:table>
</body>
<script type="text/javascript">
	//function doEditOpt(){
	//$("showMessage").show();	
	//}
	function openNewWindow(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'height=500px,width=790px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
</script>
</html>
