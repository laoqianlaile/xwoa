<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			权力库变更申请
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
		<script src="${pageContext.request.contextPath}/scripts/suggest.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset style="padding:10px;">
			<legend>
				 查询条件
			</legend>
			
			<s:form action="supPower" namespace="/powerbase" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
					<tr >
						<td class="addTd">权力编号:</td>
						<td><s:textfield name="s_itemId" style="width:180px" value="%{#parameters['s_itemId']}"></s:textfield></td>
						<td class="addTd">权力名称:</td>
						<td><s:textfield name="s_itemName" style="width:180px" value="%{#parameters['s_itemName']}"/> </td>
					</tr>	
				<tr>
					<td class="addTd">行使部门:</td>
					<td>
					<select name="s_orgId">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitList }">
								<option value="${row.depno}"
									<c:if test="${parameters.s_orgId[0] eq row.depno}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select></td>
				
					<td class="addTd">权力类型:</td>
					<td>
							<select name="s_itemType" style="width: 180px"
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
					<tr>
						<td></td>
						<td>
							<s:submit method="listSupPowerSQ"  key="opt.btn.query" cssClass="btn"/>
							<s:submit method="SuppowerQlbgSqXz"  key="opt.btn.new" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>
		<ec:table action="powerbase/supPower!listSupPowerSQ.do" items="supPowerWithoutLobList" var="SupPowerWithoutLob" 
		          imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
			<c:set var="itemIdNum">权力编码</c:set>	
				<ec:column property="itemId" title="${itemIdNum}" style="text-align:center" >
				<a href="<%=request.getContextPath()%>/powerbase/suppowerchglog!listVersion.do?itemId=${SupPowerWithoutLob.itemId}&version=${SupPowerWithoutLob.version}">${SupPowerWithoutLob.itemId}</a>
				</ec:column>
				<ec:column property="itemName" title="权力事项名称" style="text-align:center">
				<c:choose>
					<c:when test="${fn:length(SupPowerWithoutLob.itemName) > 30}">
						<c:out value="${fn:substring(SupPowerWithoutLob.itemName, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${SupPowerWithoutLob.itemName}" />
					</c:otherwise>
				</c:choose>
				</ec:column>
				<ec:column property="orgId" title="主办部门" style="text-align:center" >
					${cp:MAPVALUE("depno",SupPowerWithoutLob.orgId)}
				</ec:column>
				<ec:column property="itemType" title="权力类型" style="text-align:center" >
					${cp:MAPVALUE("ITEM_TYPE",SupPowerWithoutLob.itemType)}
				</ec:column>
				<ec:column property="opt" title="变更状态" style="text-align:center" sortable="false">
					<c:if test="${SupPowerWithoutLob.chgType eq 'fz' or SupPowerWithoutLob.chgType eq 'xg' or SupPowerWithoutLob.chgType eq 'gq' or SupPowerWithoutLob.chgType eq 'qy'}">
					处理中
					</c:if>				
				</ec:column>
			    <ec:column property="opt" title="操作" sortable="false" style="text-align:center">
					<c:if test="${SupPowerWithoutLob.chgType eq '' or SupPowerWithoutLob.chgType eq null and SupPowerWithoutLob.qlState ne 'T' and SupPowerWithoutLob.qlState ne 'X' }">
						<a href='powerbase/supPower!SuppowerQlbgSqXg.do?itemId=${SupPowerWithoutLob.itemId}&version=${SupPowerWithoutLob.version}' >修改</a>
						<a href='supPower!updateQlbgState.do?itemId=${SupPowerWithoutLob.itemId}&qlState=T&version=${SupPowerWithoutLob.version}' onclick='return confirm("是否挂起该权力事项?");'>挂起</a>
						<a href='supPower!updateQlbgState.do?itemId=${SupPowerWithoutLob.itemId}&qlState=X&version=${SupPowerWithoutLob.version}' onclick='return confirm("是否废止该权力事项?");'>废止</a>
					
					</c:if>
					<c:if test="${(SupPowerWithoutLob.qlState eq 'T' or SupPowerWithoutLob.qlState eq 'X') and SupPowerWithoutLob.chgType ne 'qy'}">
						<a href='supPower!upDateQlbgQyState.do?itemId=${SupPowerWithoutLob.itemId}&version=${SupPowerWithoutLob.version}&qlState=A'onclick='return confirm("是否启用该权力事项?");' >启用</a>
					</c:if>
			    </ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
	/**
	 * common window dialogs这是弹出窗口的实现代码
	 */
	function openNewWindow(winUrl,winName,winProps){
		if(winProps =='' || winProps == null){
			winProps = 'height=400px,width=400px,directories=false,z-look=true,alwaysRaised=true,location=false,top=100,left=500,menubar=false,Resizable=no,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
	</script>
</html>
