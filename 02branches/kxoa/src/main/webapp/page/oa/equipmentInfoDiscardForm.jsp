<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head lang="cn">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>固定资产管理</title>
</head>
<body>
<fieldset class="form">
		<legend>
		<p class="ctitle">
		固定资产注销
	    </p>
		</legend> 
	
		
		<%@ include file="/page/common/messages.jsp"%>
		<form method="post"
			action="${pageContext.request.contextPath}/oa/equipmentInfo!save.do"
			class="form-horizontal" id="equipmentInfoForm" validate="true">
			<input type="hidden" id=equipmentId name="equipmentId"
				value="${equipmentId}" />
			<input type="hidden" id=equipmentState name="equipmentState"
									value="F" />	
			<input type="hidden" id=s_supEquipmentType
				name="s_supEquipmentType" value="${s_supEquipmentType}" /> 
			<input type="hidden" id=s_equipmentType name="s_equipmentType"
				value="${s_equipmentType}" />
			
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">注销经办人：</td>
					<td align="left" >
					<input type="hidden" id=discardOperator name="discardOperator"
									value="${discardOperator}" />
					${cp:MAPVALUE('usercode',discardOperator)}				
					</td>
                     <td class="addTd">注销时间：</td>
					<td align="left">
					<input type="hidden" name="discardDate"
										value="<fmt:formatDate value='${discardDate}' pattern='yyyy-MM-dd  hh:mm:ss'/>"/>
						
                      <fmt:formatDate value='${discardDate}' pattern='yyyy-MM-dd '  />
										</td>
				</tr>

				<tr>
					
					<td class="addTd">注销类别：</td>
					<td align="left" colspan="3"><select id="discardType" name="discardType" >
										<c:forEach var="dt" items="${cp:DICTIONARY('oaDisType')}">
										<option value="${dt.id.datacode}">
										${dt.datavalue}</option>
									</c:forEach>
									</select></td>
				</tr>
				<tr>
					<td class="addTd">注销原因：</td>
					<td align="left" colspan="3" ><textarea id="discardReason" name="discardReason" 
										rows="5" cols="50">${discardReason }</textarea>
					</td>
				</tr>
			</table>
			<div class="formButton">
			<input type="button" name="back" Class="btn"
				onclick="history.back(-1);" value="返回" /> 
			<button type="submit" class="btn btn-primary">保存提交</button>	
			</div>
		</form>

	</fieldset>	
</body>
<%@ include file="/page/common/scripts.jsp"%> 
<script type="text/javascript">
	
	</script>
</html>
