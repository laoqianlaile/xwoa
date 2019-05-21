<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head lang="cn">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>资源管理</title>
</head>
<body>
           <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">资产编码</td>
					<td align="left" >${info.equipmentCode}</td>
					<td class="addTd">资产名称</td>
					<td align="left">${info.equipmentName }</td>
				</tr>
				<tr>
					<td class="addTd">投入使用时间：</td>
					<td align="left">
					<fmt:formatDate value='${info.inuseTime}' pattern='yyyy-MM-dd ' />
					</td>
					<td class="addTd">资产负责人：</td>
					<td align="left">${cp:MAPVALUE('usercode',info.equipmentCharge)}</td>
				</tr>
				<tr>
					<td class="addTd">资产其他说明</td>
					<td align="left" colspan="3">${info.equipmentDesc }</td>
				</tr>
				</table>
	        <s:form id="equipmentInfoForm"  namespace="/oa"
	        action="equipmentInfo" style="margin-top:0;margin-bottom:5"
	        method="post">
			<input type="hidden" id="supEquipmentType" name="s_supEquipmentType"
				value="${s_supEquipmentType}" />
			<input type="hidden" id="equipmentType" name="s_equipmentType"
				value="${s_equipmentType}" />
					
			<input type="hidden" id="equipmentId" name="equipmentId"
				value="${info.equipmentId}" />
		<fieldset>

		<legend>办公资源查询</legend>		
			<table cellpadding="0" cellspacing="0" align="left" >
				<tr>
					<td class="addTd">申请人：</td>
					<td width="180">
					<input type="text" class="autocomplete"   data-token-limit='1' name="s_applicant"  data-pre-populate='${populate}' data-url="${pageContext.request.contextPath}/oa/equipmentInfo!selectUser.do" />
					</td>
					<td class="addTd">使用状态：</td>
					<td width="180">
					<select id="s_equipmentState" name="s_equipmentState" style="width: 100px;">
											<option value=""></option>
											<c:forEach var="dt" items="${cp:DICTIONARY('oaEquState')}">
												<option value="${dt.id.datacode}" <c:if test="${dt.id.datacode==s_equipmentState }"> selected="selected"</c:if>>${dt.datavalue }</option>
											</c:forEach>
					</select>
					</td>
					<td>
					<s:submit method="detailList" key="opt.btn.query" cssClass="btn" />
<!-- 					<input type="submit" class="btn"  data-form="#equipmentInfoForm" value="查询"/> -->
					<s:submit method="list" cssClass="btn" value="返回" />
<!-- 					<input type="button" class="btn"   -->
<!-- 					target="submit"  -->
<!-- 					data-form="#equipmentInfoForm"  -->
<%-- 					data-href="${pageContext.request.contextPath}/oa/equipmentInfo!list.do" value="返回"> --%>
					</td>
				</tr>
			</table>
		</fieldset>
		</s:form>
	<ec:table
		action="${pageContext.request.contextPath}/oa/equipmentInfo!detailList.do"
		items="equipmentUsings" var="fEquipmentUsing"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
	<%-- 	<ec:exportXls fileName="EquipmentInfo.xls"></ec:exportXls>
		<ec:exportPdf fileName="EquipmentInfo.pdf" headerColor="blue"
			headerBackgroundColor="white"></ec:exportPdf> --%>
		<ec:row>

			<ec:column property="useRequestId" title="申请号"
				style="text-align:center" />

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
			<ec:column property="opt" title="使用详情" sortable="false"
				style="text-align:center">
             <a href="${pageContext.request.contextPath}/oa/equipmentUsing!infoDetailview.do?useRequestId=${fEquipmentUsing.useRequestId}&equipmentState=4&s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}">
			       使用详情</a>
			</ec:column>
		</ec:row>
	</ec:table>
</body>
<%@ include file="/page/common/scripts.jsp"%> 

</html>
