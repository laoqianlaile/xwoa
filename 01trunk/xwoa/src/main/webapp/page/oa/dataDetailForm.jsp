<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>固定资产信息</title>
</head>

<body>
	<fieldset class="form">
		<legend class="ctitle" style="width: auto; margin-bottom: 10px;">
			<c:if test="${ empty datadictionary.id.datacode}">
		新增资源类别
	</c:if>
			<c:if test="${ not empty datadictionary.id.datacode}">
		编辑资源类别
	</c:if>
		</legend>

		<%@ include file="/page/common/messages.jsp"%>

		<form action="${pageContext.request.contextPath}/oa/equipmentInfo!dictionarySave.do" method="post" 
			id="equipmentInfoForm" data-validate="true">
			<input type="hidden" name="datadictionary.id.catalogcode" value="${datadictionary.id.catalogcode}" />
			<input type="hidden" id=s_supEquipmentType name="s_supEquipmentType" value="${s_supEquipmentType}" /> 
			<input type="hidden" id=s_equipmentType name="s_equipmentType" value="${s_equipmentType}" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">上级分类名称：</td>
					<td align="left" colspan="3">
					<c:if test="${ empty datadictionary.id.datacode}">
					<input type="hidden" name="datadictionary.extracode" value="${datadictionary.extracode}" />
                    ${cp:MAPVALUE('equipment',datadictionary.extracode)}
					</c:if>
					<c:if test="${ not empty datadictionary.id.datacode}">
					<select id="extracode" name="datadictionary.extracode">
						<c:forEach var="dt" items="${unitNames}">
							<option value="${dt.datacode}" <c:if test="${dt.datacode==datadictionary.extracode }"> selected="selected"</c:if>>${dt.datavalue }</option>
						</c:forEach>
                    </select>
					</c:if>
				</tr>

				<tr>
					<td class="addTd">类别名称：</td>
					<td align="left"><input type="text" name="datadictionary.datavalue" id="datavalue" class="focused required" value="${datadictionary.datavalue }" />
					<span style="color: red">*</span></td>
				</tr>
				<tr>
				<c:if test="${ empty datadictionary.id.datacode}">
					<td class="addTd">${fdesc[0]}：</td>
					<td align="left">
<%-- 					<input type="text" name="datacode" id="datacode" class="focused required" value="${datadictionary.id.datacode }"> --%>
					<input id="datacode" name="datacode" class="required" type="text"  value="${datadictionary.id.datacode }"
								data-rule-remote="${pageContext.request.contextPath}/oa/equipmentInfo!isDExist.do" />
								<span style="color: red">*</span>
					</td>
				</c:if>

							<c:if test="${ not empty datadictionary.id.datacode}">
								<input type="hidden" name="datadictionary.id.datacode" value="${datadictionary.id.datacode}" />
							</c:if>	
				
				</tr>
				<tr>
					<td class="addTd">备注：</td>
					<td align="left">
					<input type="text" name="datadictionary.datadesc" id="datadesc" class="focused " value="${datadictionary.datadesc }">
					</td>
				
				</tr>
				
			</table>
			<div  class="formButton">
			 <button type="submit" class="btn btn-primary">保存提交</button>
			 <input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
			</div>
		</form>
	</fieldset>
</body>
<%@ include file="/page/common/scripts.jsp"%> 

</html>
