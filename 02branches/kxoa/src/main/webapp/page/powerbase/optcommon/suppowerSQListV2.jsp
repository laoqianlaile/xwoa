<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
<title>内部事权</title>
<%-- <sj:head locale="zh_CN" /> --%>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="search">
		<legend class="headTitle">
		<c:choose>
		<c:when test="${s_itemType eq 'QB' }">签报登记</c:when>
		<c:when test="${s_itemType eq 'SQ' }">事项登记</c:when>
		</c:choose>
		</legend>
		<div class="searchDiv">
		<s:form action="supPower" namespace="/powerbase" id="supPowerform" method="post"
			style="margin-top:0;margin-bottom:5">
			<div class="searchArea">
				<input id="qlState" type="hidden" name="qlState"
					value="${object.qlState}" />
					<input type="hidden" name="s_itemType"	value="${s_itemType}"/>
					<input type="hidden" name="s_isNetwork"	value="${s_isNetwork}"/>
			<table style="width: auto;">
				<tr>
					<td class="searchTitleArea"><label class="searchCondTitle">行使部门:</label></td>
					<td class="searchCountArea"><select name="s_orgId" class="searchCondContent" style="width: 120px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitList }">
								<option value="${row.depno}"
									<c:if test="${parameters.s_orgId[0] eq row.depno}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="searchTitleArea"><label class="searchCondTitle">事权编号:</label></td>
					<td class="searchCountArea"><input type="text" name="s_cid.itemId" class="searchCondContent"
							value="${s_cid.itemId}" /></td>

					<td class="searchTitleArea"><label class="searchCondTitle"><c:if test="${s_itemType eq 'SQ' }">
						事项标题:
						</c:if>
						<c:if test="${s_itemType eq 'QB' }">
						签报标题:
						</c:if></label></td>
					<td class="searchCountArea"><input type="text" name="s_itemName" class="searchCondContent"
							value="${s_itemName}" /></td>
					<td class="searchCondArea" onclick="sub();"><div class="clickDiv" ></div></td>
				</tr>
				
			</table>
			</div>
		</s:form>
		</div>
	</fieldset>
<table>
<tr align="left">
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>温馨提示</font>：点击事项编号，可以查看事项和流程的详细信息以及相关登记注意事项；
</td>
</tr>

</table>
	<ec:table action="powerbase/supPower!sqList.do" items="userPowerList"
		var="suppower"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<ec:row>
			<ec:column property="itemId" title="事权编号" style="text-align:center" >
			<a
					href='<%=request.getContextPath()%>/powerbase/powerbase/suppowerchglog!listVersion.do?itemId=${suppower.itemId}&version=${suppower.version}' title="查看事项详细信息">${suppower.itemId}</a>
			
			</ec:column>
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
				${cp:MAPVALUE("depno",suppower.orgId)}
				</ec:column>
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">
				<c:if test="${not empty suppower.wfcode}">
				<a
					href='<%=request.getContextPath()%>/powerruntime/optApply!editDoOrReadSQ.do?optId=${suppower.optid }&itemId=${suppower.itemId}&isapplyuser=${suppower.isapplyuser }&flag=${suppower.itemType }&s_itemtype=${suppower.itemType }&show_type=F&orgcode=${unitcode}&applyItemType=${suppower.applyItemType}&oaModuleType=${suppower.oaModuleType}' title="登记事项">登记</a>
				</c:if>
			<c:if test="${empty suppower.wfcode}">
			<a href="javascript:showError();" title="事项配置异常，不可登记，请联系管理员！"><font color="red">登记</font></a>
			</c:if>
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
	
	function showError(){
		alert("事项配置异常，不可登记，请联系管理员！");		
	}
	 function sub(){
			$("#supPowerform").attr("action","supPower!sqList.do");
			$("#supPowerform").submit();
		} 
</script>
</html>
