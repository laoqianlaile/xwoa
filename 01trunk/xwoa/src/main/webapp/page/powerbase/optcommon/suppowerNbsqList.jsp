<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>内部事权</title>
<%-- <sj:head locale="zh_CN" /> --%>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend>
		<c:choose>
		<c:when test="${s_itemType eq 'QB' }">签报登记</c:when>
		<c:when test="${s_itemType eq 'SQ' }">事项登记</c:when>
		</c:choose>
		</legend>

		<s:form action="supPower" namespace="/powerbase"
			style="margin-top:0;margin-bottom:5">
			<table cellpadding="0" cellspacing="0" align="center" class="searchTable">
				<input id="qlState" type="hidden" name="qlState"
					value="${object.qlState}" />
					<input type="hidden" name="s_itemType"	value="${s_itemType }"/>
				<tr>
					<td >权力编号:</td>
					<td><s:textfield name="s_cid.itemId" style="width:180px"
							value="%{#parameters['s_cid.itemId']}"></s:textfield></td>

					<td ><c:if test="${s_itemType eq 'SQ' }">
						事项标题:
						</c:if>
						<c:if test="${s_itemType eq 'QB' }">
						签报标题:
						</c:if></td>
					<td><s:textfield name="s_itemName" style="width:180px"
							value="%{#parameters['s_itemName']}" /></td>
				</tr>
				<tr>
					<td >行使部门:</td>
					<td><select name="s_orgId"style="width:180px;height:25px;" >
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitList }">
								<option value="${row.depno}"
									<c:if test="${parameters.s_orgId[0] eq row.depno}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				
				<tr class="searchButton">				
					<td colspan="5"><s:submit method="nbsqList" key="opt.btn.query" cssClass="btn" />
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table action="powerbase/supPower!nbsqList.do" items="nowList"
		var="suppower"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="itemId" title="权力编号" style="text-align:center" />
            <c:if test="${s_itemType eq 'SQ' }"> 
			<ec:column property="itemName" title="事项标题" style="text-align:center">
				<c:choose>
					<c:when test="${fn:length(suppower.itemName) > 30}">
						<c:out value="${fn:substring(suppower.itemName, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${suppower.itemName}" />
					</c:otherwise>
				</c:choose>
			</ec:column>
            </c:if>
             <c:if test="${s_itemType eq 'QB' }"> 
			<ec:column property="itemName" title="签报标题" style="text-align:center">
				<c:choose>
					<c:when test="${fn:length(suppower.itemName) > 30}">
						<c:out value="${fn:substring(suppower.itemName, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${suppower.itemName}" />
					</c:otherwise>
				</c:choose>
			</ec:column>
            </c:if>
			<ec:column property="orgId" title="行使部门" style="text-align:center">
					${cp:MAPVALUE("unitcode",suppower.unitcode)}
				</ec:column>
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">
				
				<a
					href='<%=request.getContextPath()%>/powerruntime/optApply!editDoOrRead.do?optId=${suppower.optid }&itemId=${suppower.itemId}&isapplyuser=${suppower.isapplyuser }&flag=${suppower.itemType }&s_itemtype=${suppower.itemType }&show_type=F&orgcode=${suppower.unitcode}'>登记</a>
			</ec:column>

		</ec:row>
	</ec:table>
</body>
<script type="text/javascript">

	function openNewWindow(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'height=500px,width=790px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
</script>
</html>
