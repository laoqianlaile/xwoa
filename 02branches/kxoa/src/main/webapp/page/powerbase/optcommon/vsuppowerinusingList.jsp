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
	<fieldset  class="search">
		<legend> 查询条件 </legend>

		<s:form action="vsuppowerinusing" namespace="/powerbase"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center">
				<input id="qlState" type="hidden" name="qlState"
					value="${object.qlState}" />
				<tr>
					<td class="addTd">权力编号:</td>
					<td><s:textfield name="s_itemId" style="width:180px;height:25px;"
							value="%{#parameters['s_itemId']}"></s:textfield></td>

					<td class="addTd">权力名称:</td>
					<td><s:textfield name="s_itemName" style="width:180px;height:25px;"
							value="%{#parameters['s_itemName']}" /></td>
				</tr>
				<tr>
					<td class="addTd">行使部门:</td>
					<td><select name="s_orgId"  style="width: 180px;height:25px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:LVB('depno')}">
								<option value="${row.value}"
									<c:if test="${parameters.s_orgId[0] eq row.value}">selected="selected"</c:if>>
									<c:out value="${row.label}" />
								</option>
							</c:forEach>
					</select></td>

					<td class="addTd">权力类型:</td>
					<td><select name="s_itemType" style="width: 180px;height:25px;">
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
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="powerbase/vsuppowerinusing!list.do" items="objList"
		var="suppower"
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

			<ec:column property="orgId" title="行使部门" style="text-align:center">
					${cp:MAPVALUE("depno",suppower.orgId)}
				</ec:column>
				<ec:column property="itemType" title="权力类型" style="text-align:center">
					${cp:MAPVALUE("ITEM_TYPE",suppower.itemType)}
			</ec:column>
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">							
				 <a href='powerbase/powerOptInfo!edit.do?itemId=${suppower.itemId}&wfcode=${suppower.optFlowCode}'>编辑业务</a>
				 <c:if test="${suppower.itemType eq 'GW' or suppower.itemType eq 'QB'}">
				 <a href='powerbase/powerOrgInfo!edit.do?itemId=${suppower.itemId}'>关联部门</a>	
				 </c:if>								
				<c:if test="${suppower.itemType eq 'SQ' }">
				<a href='powerbase/powerUserInfo!userList.do?itemId=${suppower.itemId}&version=${suppower.version}'>关联人员</a>
				</c:if>	
				<%-- <c:if test="${suppower.itemType  ne 'SQ' }">
				
				</c:if>	 --%>
					</ec:column>
		</ec:row>
	</ec:table>
</body>
<script type="text/javascript">

</script>
</html>
