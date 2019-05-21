﻿<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<%-- <%@ include file="/page/common/charisma-css.jsp"%> --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%-- <title><s:text name="incomeDocTask.list.title" /></title> --%>
</head>
<style>
td.tableHeader {
	word-break: keep-all;
	white-space: nowrap;
}
</style>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset   class="search">
	
		<legend>
				<c:choose>
				<c:when test="${s_supEquipmentType eq 'meeting' }">会议室使用</c:when>
				<c:when test="${s_supEquipmentType eq 'resourse'}">资源使用</c:when>
				<c:when test="${s_supEquipmentType eq 'car'}">车辆使用</c:when>
				</c:choose>
		
		</legend>

		<s:form id="equipmentInfoForm" namespace="/oa"
			action="equipmentInfo" style="margin-top:0;margin-bottom:5"
			method="post">
			<input type="hidden" id=supEquipmentType name="s_supEquipmentType" value="${s_supEquipmentType}" />
			<!-- 			<input type="hidden" id=equipmentType name="s_equipmentType" -->
			<%-- 				value="${s_equipmentType}" /> --%>
			<table cellpadding="0" cellspacing="0" align="left">
				<tr>
					<td class="addTd">资产编码:</td>
					<td ><input type="text" class="span2"
						name="s_equipmentCode" value="${s_equipmentCode }" /></td>
					<td class="addTd">资产名称:</td>
					<td ><input type="text" name="s_equipmentName"
						value="${s_equipmentName }" /></td>

				</tr>
				<tr>
					<td class="addTd">类别:</td>
					<td><select name="s_equipmentType" id="s_equipmentType" width="180">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${dictDetails }">
								<option value="${row.datacode}"
									<c:if test="${s_equipmentType eq row.datacode }">selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td>
					<td></td>
					<td >
					<s:submit method="applicantList" key="opt.btn.query" cssClass="btn" />
<!-- 						<input type="submit" class="btn" -->
<!-- 						data-form="#equipmentInfoForm" value="查询" />  -->
							<input type="button" name="back" Class="btn"
							 onclick="javascript:window.location.href='${pageContext.request.contextPath}/oa/equipmentUsing!edit.do?s_supEquipmentType=${s_supEquipmentType}';"
							 value="申请使用" />
<!-- 						<input type="button" -->
<!-- 						class="btn" target="submit" data-form="#equipmentInfoForm" -->
<%-- 						data-href="${pageContext.request.contextPath}/oa/equipmentUsing!edit.do" --%>
<!-- 						value="申请使用">  -->
						<input type="button" name="back" Class="btn"
							onclick="javascript:window.location.href='${pageContext.request.contextPath}/oa/equipmentUsing!listMine.do?s_supEquipmentType=${s_supEquipmentType}';"
							 value="我的申请" />
<!-- 						<input type="button" class="btn" -->
<!-- 						target="submit" data-form="#equipmentInfoForm" -->
<%-- 						data-href="${pageContext.request.contextPath}/oa/equipmentUsing!listMine.do" --%>
<!-- 						value="我的申请">  -->
						<input type="button" name="back" Class="btn"
							onclick="javascript:window.location.href='${pageContext.request.contextPath}/oa/equipmentUsing!listMineConfirm.do?s_equipmentState=1&s_supEquipmentType=${s_supEquipmentType}';"
							 value="申请审批"" />						
<!-- 						<input type="button" class="btn" -->
<!-- 						target="submit" data-form="#equipmentInfoForm" -->
<%-- 						data-href="${pageContext.request.contextPath}/oa/equipmentUsing!listMineConfirm.do?s_equipmentState=1" --%>
<!-- 						value="申请审批"> -->
						</td>

				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table
		action="${pageContext.request.contextPath}/oa/equipmentInfo!applicantList.do"
		items="objList" var="fEquipmentInfo"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<%-- <ec:exportXls fileName="EquipmentInfo.xls"></ec:exportXls>
		<ec:exportPdf fileName="EquipmentInfo.pdf" headerColor="blue"
			headerBackgroundColor="white"></ec:exportPdf> --%>
		<ec:row>

			<ec:column property="equipmentCode" title="资产编码"
				style="text-align:center" />

			<ec:column property="equipmentName" title="资产名称"
				style="text-align:center">
				<!-- 				<a -->
				<%-- 					href='${pageContext.request.contextPath}/oa/equipmentInfo!view.do?equipmentId=${fEquipmentInfo.equipmentId}&s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}'> --%>
				<a
					href="${pageContext.request.contextPath}/oa/equipmentInfo!applicantDetailList.do?equipmentId=${fEquipmentInfo.equipmentId}&s_supEquipmentType=${s_supEquipmentType}">
					${fEquipmentInfo.equipmentName}</a>
			</ec:column>

			<ec:column property="equipmentType" title="资产类别"
				style="text-align:center">
				${cp:MAPVALUE("equipment",fEquipmentInfo.equipmentType)}
             </ec:column>
			<ec:column property="equipmentCharge" title="资产负责人"
				style="text-align:left；">
					${cp:MAPVALUE("usercode",fEquipmentInfo.equipmentCharge)}
				</ec:column>
			<ec:column property="equipmentState" title="资产状态"
				style="text-align:center">
					${USE_STATE[fEquipmentInfo.equipmentState]} 
				</ec:column>

			<ec:column property="chartList" title="使用情况">
				<div class="fixed-assets">
					<c:forEach items="${fEquipmentInfo.chartList }" var="chartInfo"
						varStatus="s">
						<c:if test="${!s.last}">
						<div class="${chartInfo.color}" style="width:${chartInfo.value}%"
							title=" ${cp:MAPVALUE('usercode',chartInfo.user)}  ${chartInfo.using}  起始时间：<fmt:formatDate
										value='${chartInfo.beginTime}' pattern='MM-dd  HH:mm:ss' />/结束时间：<fmt:formatDate
										value='${chartInfo.endTime}' pattern='MM-dd  HH:mm:ss' />"></div>
						</c:if>
						<c:if test="${s.last}">
							<div class="line current"
								style="width:2px; left:${chartInfo.value}%;" title="当前时间"></div>
						</c:if>
					</c:forEach>
				</div>
			</ec:column>
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">

				<a 
				href="${pageContext.request.contextPath}/oa/equipmentUsing!edit.do?equipmentId=${fEquipmentInfo.equipmentId}&s_supEquipmentType=${s_supEquipmentType}">
					申请</a>

				<a 
				href="${pageContext.request.contextPath}/oa/equipmentUsing!listMine.do?s_equipmentId=${fEquipmentInfo.equipmentId}&s_supEquipmentType=${s_supEquipmentType}">
					我的申请 </a>



				<%-- 			<a href="${pageContext.request.contextPath}/oa/equipmentInfo!applicantDetailList.do?equipmentId=${fEquipmentInfo.equipmentId}&s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}"> --%>
				<!-- 			使用明细</a> -->

			</ec:column>
		</ec:row>
	</ec:table>
</body>
<%-- <%@ include file="/page/common/scripts.jsp"%> --%>

</html>