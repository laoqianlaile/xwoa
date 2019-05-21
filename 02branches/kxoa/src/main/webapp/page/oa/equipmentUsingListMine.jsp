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
<fieldset class="search">

		<legend>办公资源查询</legend>
	        <s:form id="equipmentUsingForm"  namespace="/oa"
	        action="equipmentUsing" style="margin-top:0;margin-bottom:5"
	        method="post">

			
			<input type="hidden" id=supEquipmentType name="s_supEquipmentType"
				value="${s_supEquipmentType}" />
			<input type="hidden" id=equipmentType name="s_equipmentType"
				value="${s_equipmentType}" />
			<table cellpadding="0" cellspacing="0" align="left">
				<tr>
<!-- 					<td class="addTd">申请人：</td> -->
<%-- 					<td width="180"><input type="text" class=" users" placeholder="输入用户名、拼音或登录名" autocomplete="off" usercodeRef="s_applicant" value="${cp:MAPVALUE('usercode',s_applicant)}" /></td> --%>
					
					<td class="addTd">资源选择：</td>
					<td width="180">
					<select id="s_equipmentId" name="s_equipmentId" >
											<option value=""></option>
											<c:forEach var="dt" items="${equList}">
											
												<option value="${dt.equipmentId}" <c:if test="${s_equipmentId eq dt.equipmentId }"> selected="selected"</c:if>>${dt.equipmentName }</option>
											</c:forEach>
					</select>
					</td>
					<td class="addTd">使用状态：</td>
					<td width="180">
					<select id="s_equipmentState" name="s_equipmentState" date-value="${s_equipmentState }">
											<option value=""></option>
											<c:forEach var="dt" items="${cp:DICTIONARY('oaEquState')}">
												<option value="${dt.id.datacode}" <c:if test="${dt.id.datacode==s_equipmentState }"> selected="selected"</c:if>>${dt.datavalue }</option>
											</c:forEach>
					</select>
					</td>
					</tr>
					<tr>
					<td class="addTd">开始时间：</td>
					<td width="180" >
					<input type="text" name="s_planBeginTime" id="s_planBeginTime"
						value="${s_planBeginTime}" class="Wdate" />
					</td>
					
					<td class="addTd">结束时间：</td>
					<td width="180" id="s_planEndTime">
					<input type="text" name="s_planEndTime" id="s_planEndTime"
						value="${s_planEndTime}" class="Wdate" />
					</td>
					
					
					<td>
					<s:submit method="listMine" key="opt.btn.query" cssClass="btn"  onclick="return checkFrom();"/>
<!-- 					<input type="submit" class="btn"  data-form="#equipmentUsingForm" value="查询"/> -->
					<input type="button" class="btn" 
					onclick="window.location.href='${pageContext.request.contextPath}/oa/equipmentInfo!applicantList.do?s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}'" value="返回">
					
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>
	<ec:table
		action="${pageContext.request.contextPath}/oa/equipmentUsing!listMine.do"
		items="objList" var="fEquipmentUsing"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		retrieveRowsCallback="limit">
		<%-- <ec:exportXls fileName="EquipmentInfo.xls"></ec:exportXls>
		<ec:exportPdf fileName="EquipmentInfo.pdf" headerColor="blue"
			headerBackgroundColor="white"></ec:exportPdf> --%>
		<ec:row>

			<ec:column property="useRequestId" title="申请号"
				style="text-align:center" />

			<ec:column property="equipmentInfo.equipmentName" title="资产名称" sortable="false"
				style="text-align:center">
				${fEquipmentUsing.equipmentInfo.equipmentName}
			</ec:column>

			<ec:column property="planBeginTime" title="预计开始时间"
				style="text-align:center">
				<fmt:formatDate value='${fEquipmentUsing.planBeginTime}' pattern='yyyy-MM-dd  HH:mm' />
             </ec:column>
			<ec:column property="planEndTime" title="预计结束时间"
				style="text-align:left；">
					<fmt:formatDate value='${fEquipmentUsing.planEndTime}' pattern='yyyy-MM-dd  HH:mm' />
				</ec:column>
			<ec:column property="purposeType" title="用途类别"
				style="text-align:center">
					<c:set var="dt" value="${empty fEquipmentUsing.purposeType ? 'NONE' : fEquipmentUsing.purposeType}" /> ${cp:MAPVALUE('oaPurType', dt) }
				</ec:column>
			<ec:column property="equipmentState" title="使用状态"
				style="text-align:center">
				<c:set var="dt" value="${empty fEquipmentUsing.equipmentState ? 'NONE' : fEquipmentUsing.equipmentState}" />
									${cp:MAPVALUE('oaEquState', dt) }
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
				
            <c:if test="${ fEquipmentUsing.equipmentState=='2' }">
				<a href="${pageContext.request.contextPath}/oa/equipmentUsing!edit.do?useRequestId=${fEquipmentUsing.useRequestId}&equipmentState=${fEquipmentUsing.equipmentState}&s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}">
					 反馈
				</a>
			</c:if>
             <a href="${pageContext.request.contextPath}/oa/equipmentUsing!infoDetailview.do?useRequestId=${fEquipmentUsing.useRequestId}&equipmentState=4&s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}">
			      使用详情 </a>
			</ec:column>
		</ec:row>
	</ec:table>

</body>
<%@ include file="/page/common/scripts.jsp"%> 

	<script type="text/javascript">
	function checkFrom(){
		var begD = $("#s_planBeginTime").val();
		var endD = $("#s_planEndTime").val();
		if(endD!=""&&begD>endD){
			alert("结束时间不能早于开始时间。");
// 			$("#s_endTime").focus();
			return false;
		}
		return true;
	}
	</script>
</html>
