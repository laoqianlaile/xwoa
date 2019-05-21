<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	 	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
		
<title>权力库管理</title>
<%-- <sj:head locale="zh_CN" /> --%>
</head>

<body>
	<fieldset class="search">
		<legend> 查询条件 </legend>

		<s:form action="supPower" namespace="/powerbase"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<input id="qlState" type="hidden" name="qlState"
					value="${object.qlState}" />
				<tr>
					<td class="addTd">权力编号:</td>
					<td><s:textfield name="s_item_id" style="width:180px"
							value="%{#parameters['s_item_id']}"></s:textfield></td>

					<td class="addTd">权力名称:</td>
					<td><s:textfield name="s_itemName" style="width:180px"
							value="%{#parameters['s_itemName']}" /></td>
				</tr>
				<tr>
					<td class="addTd">行使部门:</td>
					<td><select name="s_orgId"  style="width: 195px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitList }">
								<option value="${row.depno}"
									<c:if test="${parameters.s_orgId[0] eq row.depno}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select></td>

					<td class="addTd">权力类型:</td>
					<td><select name="s_itemType" style="width: 180px"
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
					<td colspan="4"><s:submit method="list" key="opt.btn.query" cssClass="btn" />
						<s:submit method="editInitial" key="opt.btn.new" cssClass="btn" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="powerbase/supPower!list.do" items="supPowerWithoutLobList" styleClass="ectableRegions tableRegion"
		var="suppower"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="itemId" title="权力编号" style="text-align:center" width="10%"/>

			<ec:column property="itemName" title="权力名称" style="text-align:center" width="40%">
			<input  type="hidden"  title="${suppower.itemName}" value="${suppower.itemName}" />
				<c:choose>
					<c:when test="${fn:length(suppower.itemName) > 30}">
						${fn:substring(suppower.itemName, 0, 30)}...
					</c:when>
					<c:otherwise>
						${suppower.itemName}
					</c:otherwise>
				</c:choose>
			</ec:column>

			<ec:column property="orgId" title="行使部门" style="text-align:center" width="12%">
					${cp:MAPVALUE("depno",suppower.orgId)}
				</ec:column>
			<ec:column property="itemType" title="权力类型" style="text-align:center" width="10%">
					${cp:MAPVALUE("ITEM_TYPE",suppower.itemType)}
			</ec:column>
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center" width="10%">
				<a
					href='powerbase/suppowerchglog!listVersion.do?itemId=${suppower.itemId}&version=${suppower.version}'><s:text
						name="opt.btn.view" /></a>
				<a
					href='supPower!edit.do?itemId=${suppower.itemId}&version=${suppower.version}'>编辑</a>
				<!-- <a
					href='powerruntime/powerOptInfo!edit.do?itemId=${suppower.itemId}&version=${suppower.version}'>编辑业务</a>
				<a
					href='powerruntime/powerOrgInfo!edit.do?itemId=${suppower.itemId}&version=${suppower.version}'>关联部门</a>
			 -->
				<%-- <c:if test="${suppower.qlState eq 'A'}">
					<a
						href='supPower!upDateQlState.do?itemId=${suppower.itemId}&version=${suppower.version}&qlState=T'
						onclick='return confirm("是否挂起该权力事项?");'>挂起</a>
					<a
						href='supPower!upDateQlState.do?itemId=${suppower.itemId}&version=${suppower.version}&qlState=X'
						onclick='return confirm("是否废止该权力事项?");'>废止</a>
				</c:if>
				<c:if test="${suppower.qlState eq 'T'}">
					<a
						href='supPower!upDateQlState.do?itemId=${suppower.itemId}&version=${suppower.version}&qlState=A'>启用</a>
				</c:if>
				<c:if test="${suppower.qlState eq 'X'}">
					<a
						href='supPower!upDateQlState.do?itemId=${suppower.itemId}&version=${suppower.version}&qlState=A'>启用</a>
				</c:if> --%>
				<c:if test="${suppower.qlState eq 'A'}">			
					<a
						href='supPower!upDateQlState.do?itemId=${suppower.itemId}&version=${suppower.version}&qlState=X'
						onclick='return confirm("是否废止该权力事项?");'>废止</a>
				</c:if>
				<c:if test="${suppower.qlState eq 'X'}">
					<a
						href='supPower!upDateQlState.do?itemId=${suppower.itemId}&version=${suppower.version}&qlState=A'>启用</a>
				</c:if>
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
