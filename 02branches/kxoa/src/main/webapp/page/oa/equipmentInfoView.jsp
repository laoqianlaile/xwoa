<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><s:text name="dataindividual.edit.title" /></title>


<script
	src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js"
	type="text/javascript"></script>
<link
	href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css"
	rel="stylesheet" type="text/css" />
<script src="<s:url value='/scripts/centit_validator.js'/>"
	type="text/javascript"></script>
</head>

<body>
	<fieldset>
		<legend class="ctitle" style="width: auto; margin-bottom: 10px;">
		查看资源信息
		</legend>

		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="equipmentInfo" method="post" namespace="/oa"
			id="equipmentInfoForm" validator="true">
			<input type="button" name="back" Class="btn"
				onclick="history.back(-1);" value="返回" />
			<input type="hidden" id="equipmentId" name="equipmentId"
				value="${equipmentId }" />
			<input type="hidden" id=s_supEquipmentType name="s_supEquipmentType"
				value="${s_supEquipmentType }" />
			<input type="hidden" id="s_equipmentType" name="s_equipmentType"
				value="${s_equipmentType }" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">资产编码</td>
					<td align="left" >${equipmentCode }</td>
					<td class="addTd">资产状态</td>
					<td align="left">${USE_STATE[equipmentState]} </td>
				</tr>

				<tr>
					<td class="addTd">资产名称</td>
					<td align="left">${equipmentName }</td>
					<td class="addTd">资产类别</td>
					<td align="left">${cp:MAPVALUE("equipment",equipmentType)}</td>
				</tr>
				<tr>
					<td class="addTd">投入使用时间：</td>
					<td align="left">
					<fmt:formatDate value='${inuseTime}' pattern='yyyy-MM-dd ' />
					</td>
					<td class="addTd">资产负责人：</td>
					<td align="left">${cp:MAPVALUE('usercode',equipmentCharge)}</td>
				</tr>
				<tr>
					<td class="addTd">资产初始价值：</td>
					<td align="left">
					<fmt:formatNumber value="${equipmentPrice }" type="number" pattern="#" /> 元
					</td>
					<td class="addTd">年折旧比率：</td>
					<td align="left">${yearlyDepreciation }
					</td>
				</tr>
				<c:if test="${equipmentState=='F' }">
				
				<tr>
				    <td class="addTd">注销经办人：</td>
					<td align="left">
					${cp:MAPVALUE('usercode',discardOperator)}
					</td>
					<td class="addTd">注销时间：</td>
					<td align="left"><fmt:formatDate 	value='${discardDate}' pattern='yyyy-MM-dd  HH:mm:ss' />
					</td>
				</tr>
				<tr>
				    <td class="addTd">注销类别：</td>
					<td align="left" colspan="3">
					${cp:MAPVALUE('oaDisType',discardType)}
					</td>
					</tr>
					<tr>
					<td class="addTd" >注销原因：</td>
					<td align="left" colspan="3">${discardReason }
					</td>
				</tr>	
				</c:if>
				
				<tr>
					<td class="addTd">资产其他说明</td>
					<td align="left" colspan="3">${equipmentDesc }</td>
				</tr>


			</table>
		</s:form>
	</fieldset>
</body>
<%@ include file="/page/common/scripts.jsp"%> 

</html>