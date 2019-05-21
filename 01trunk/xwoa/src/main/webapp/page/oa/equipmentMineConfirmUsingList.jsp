<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head lang="cn">
<style type="text/css">
 .autocomplete{width:200px;}
</style>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>资源管理</title>
</head>
<body>
	        <s:form id="equipmentInfoForm" namespace="/oa"
	        	action="equipmentUsing" style="margin-top:0;margin-bottom:5"
	          method="post">
			<input type="hidden" id=supEquipmentType name="s_supEquipmentType"
				value="${s_supEquipmentType}" />
			<input type="hidden" id=equipmentType name="s_equipmentType"
				value="${s_equipmentType}" />
			<input type="hidden" id=equipmentState name="s_equipmentState"
				value="${s_equipmentState}" />
				
     <fieldset>
		<legend>办公资源查询</legend>		
			<table cellpadding="0" cellspacing="0" align="left">
				<tr>
					<td class="addTd">申请人：</td>
					<td>
					<input type="text" class="autocomplete" data-token-limit='1' name="s_applicant"  data-pre-populate='${populate}' data-url="${pageContext.request.contextPath}/oa/equipmentInfo!selectUser.do" />
					</td>
					<td>
					<s:submit method="listMineConfirm" key="opt.btn.query" cssClass="btn" />
<!-- 				<input type="submit" class="btn"  data-form="#equipmentInfoForm" value="查询"/> -->
					<input type="button" class="btn" 
					onclick="window.location.href='${pageContext.request.contextPath}/oa/equipmentInfo!applicantList.do?s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}'" value="返回">
					</td>
				</tr>
			</table>
			</fieldset>
		</s:form>
	<ec:table
		action="${pageContext.request.contextPath}/oa/equipmentUsing!listMineConfirm.do"
		items="objList" var="fEquipmentUsing"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<%-- <ec:exportXls fileName="EquipmentInfo.xls"></ec:exportXls>
		<ec:exportPdf fileName="EquipmentInfo.pdf" headerColor="blue"
			headerBackgroundColor="white"></ec:exportPdf> --%>
		<ec:row>

			<ec:column property="useRequestId" title="申请号"
				style="text-align:center" />
            <ec:column property="equipmentName" title="资产名称"
				style="text-align:center">
				${fEquipmentUsing.equipmentInfo.equipmentName }
			</ec:column>
			<ec:column property="applicant" title="申请人"
				style="text-align:center">
				${cp:MAPVALUE('usercode',fEquipmentUsing.applicant)}
			</ec:column>

			<ec:column property="purposeType" title="用途类别"
				style="text-align:center">
				<c:set var="dt" value="${empty fEquipmentUsing.purposeType ? 'NONE' : fEquipmentUsing.purposeType}" /> ${cp:MAPVALUE('oaPurType', dt) }
             </ec:column>
			<ec:column property="equipmentState" title="使用状态"
				style="text-align:left；">
					<c:set var="dt" value="${empty fEquipmentUsing.equipmentState ? 'NONE' : fEquipmentUsing.equipmentState}" />
									${cp:MAPVALUE('oaEquState', dt) }
				</ec:column>
			<ec:column property="auditor" title="确认人"
				style="text-align:center">
					${cp:MAPVALUE('usercode',fEquipmentUsing.auditor)}
				</ec:column>
			<ec:column property="beAccepted" title="接受申请"
				style="text-align:center">
				${cp:MAPVALUE('YES_NO',fEquipmentUsing.beAccepted)}
             </ec:column>
			<ec:column property="beUsed" title="使用"
				style="text-align:left；">
					${cp:MAPVALUE('YES_NO',fEquipmentUsing.beUsed)}
				</ec:column>
			
				
			<c:set var="optlabel">
				<s:text name="opt.btn.collection" />
			</c:set>
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">
			<c:if test="${ fEquipmentUsing.equipmentState=='1' }">
             <a href="${pageContext.request.contextPath}/oa/equipmentUsing!edit.do?useRequestId=${fEquipmentUsing.useRequestId}&equipmentState=${fEquipmentUsing.equipmentState}&s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}">
			      确认</a>
		 </c:if>
			</ec:column>
		</ec:row>
	</ec:table>
	
</body>
<%@ include file="/page/common/scripts.jsp"%> 

</html>
