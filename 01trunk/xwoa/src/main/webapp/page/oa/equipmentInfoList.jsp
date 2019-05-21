<!DOCTYPE html>
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
	<fieldset    class="search">

		<legend>
		<c:choose>
		<c:when test="${s_supEquipmentType eq 'equipment' }">办公资源Test</c:when>
		<c:when test="${s_supEquipmentType eq 'resourse' }">资源管理</c:when> 
		<c:when test="${s_supEquipmentType eq 'car' }">车辆管理</c:when> 
		<c:when test="${s_supEquipmentType eq 'meeting' }">会议室管理</c:when> 
		</c:choose>
		
		</legend>

		<s:form id="equipmentInfoForm" namespace="/oa"
			action="equipmentInfo" style="margin-top:0;margin-bottom:5"
		 	method="post">
			<input type="hidden" id=supEquipmentType name="s_supEquipmentType"
				value="${s_supEquipmentType}" />
			<table cellpadding="0" cellspacing="0" align="left">
				<tr>
					<td class="addTd">资产编码:</td>
					<td ><input type="text" class="span2"
						name="s_equipmentCode" value="${s_equipmentCode }" /></td>
					<td class="addTd">资产名称:</td>
					<td ><input type="text" name="s_equipmentName"
						value="${s_equipmentName }" /></td>
				    	<td></td>
					<td width="180"></td>
				</tr>
				<tr>
				 <td class="addTd">资产类别:</td>
						<td ><select name="s_equipmentType" id="s_equipmentType" >
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${dictDetails }">
								<option value="${row.datacode}"
									<c:if test="${s_equipmentType eq row.datacode }">selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
					</td>
					<td class="addTd"><input type="checkbox" id="s_isBoth"
						name="s_isBoth" value="true"
						<c:if test="${s_isBoth=='true' }"> checked="checked" </c:if>>
						包含禁用</td>
				</tr>
				
				<tr class="searchButton">
					<td colspan="4">
					<s:submit method="list" key="opt.btn.query" cssClass="btn" />
<!-- 					<input type="submit" class="btn"  data-form="#equipmentInfoForm" value="查询"/> -->
					<s:submit method="edit" cssClass="btn" value="新增资源 " />
<!-- 					<input type="button" class="btn"  -->
<!-- 					target="submit" data-form="#equipmentInfoForm"  -->
<%-- 					data-href="${pageContext.request.contextPath}/oa/equipmentInfo!edit.do" value="新增资源 "> --%>
				    <s:submit method="equipmentInfoType" cssClass="btn" value="维护类别" />
<!-- 				    <input type="button" class="btn" -->
<!-- 				     target="submit" data-form="#equipmentInfoForm"  -->
<%-- 				     data-href="${pageContext.request.contextPath}/oa/equipmentInfo!equipmentInfoType.do" value="维护类别"> --%>
					</td>
						
				</tr>
			</table>
		</s:form>
	</fieldset>

	<ec:table
		action="${pageContext.request.contextPath}/oa/equipmentInfo!list.do"
		items="objList" var="fEquipmentInfo"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
<%-- 		<ec:exportXls fileName="EquipmentInfo.xls"></ec:exportXls> --%>
<%-- 		<ec:exportPdf fileName="EquipmentInfo.pdf" headerColor="blue" --%>
<%-- 			headerBackgroundColor="white"></ec:exportPdf> --%>
		<ec:row>

			<ec:column property="equipmentCode" title="资产编码"
				style="text-align:center" />

			<ec:column property="equipmentName" title="资产名称"
				style="text-align:center">
<%-- 				<a href='${pageContext.request.contextPath}/oa/equipmentInfo!view.do?equipmentId=${fEquipmentInfo.equipmentId}&s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}'> --%>
				<%-- <a 
				href="${pageContext.request.contextPath}/oa/equipmentInfo!detailList.do?equipmentId=${fEquipmentInfo.equipmentId}&s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}"> --%>
					${fEquipmentInfo.equipmentName}
				<!-- </a> -->
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
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="${optlabel}" sortable="false"
				style="text-align:center">

				<%-- 				<c:if test="${cp:CHECKUSEROPTPOWER('resourse', 'edit') }"> --%>
				<a 
				href="${pageContext.request.contextPath}/oa/equipmentInfo!edit.do?equipmentId=${fEquipmentInfo.equipmentId}&s_supEquipmentType=${s_supEquipmentType}">
					编辑</a>
				<%-- 				</c:if> --%>
				<%-- 				<c:if test="${cp:CHECKUSEROPTPOWER('equInfo', 'detailList') }"> --%>
<!-- 				<a -->
<%-- 					href="${pageContext.request.contextPath}/oa/equipmentInfo!detailList.do?equipmentId=${fEquipmentInfo.equipmentId}&s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}"> --%>
<!-- 					使用明细</a> -->
				<%-- 				</c:if> --%>
				<%-- 				<c:if test="${cp:CHECKUSEROPTPOWER('equInfo', 'discard') }"> --%>
				<c:if test="${fEquipmentInfo.equipmentState!='F' }">
					<a 
					href="${pageContext.request.contextPath}/oa/equipmentInfo!discardEdit.do?equipmentId=${fEquipmentInfo.equipmentId}&s_supEquipmentType=${s_supEquipmentType}">
						禁用</a>
						
						
				</c:if>
				<%-- 				</c:if> --%>
				<%-- 				<c:if test="${cp:CHECKUSEROPTPOWER('equInfo', 'delete') }"> --%>
				<%-- <a href="${pageContext.request.contextPath}/oa/equipmentInfo!delete.do?equipmentId=${fEquipmentInfo.equipmentId}&s_supEquipmentType=${s_supEquipmentType}" onclick='return confirm("确认删除该记录?");'>
				删除</a> --%>
				<%-- 				</c:if> --%>
			</ec:column>
		</ec:row>
	</ec:table>
</body>
<%@ include file="/page/common/scripts.jsp"%> 

</html>