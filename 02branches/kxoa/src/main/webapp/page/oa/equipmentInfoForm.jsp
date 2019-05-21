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
		<legend>
		<p class="ctitle">
			<c:if test="${ empty equipmentId}">
		新增资源信息
	        </c:if>
			<c:if test="${ not empty equipmentId}">
		编辑资源信息
	        </c:if>
	    </p>
		</legend> 
		<%@ include file="/page/common/messages.jsp"%>

		<form action="${pageContext.request.contextPath}/oa/equipmentInfo!save.do" method="post" 
			id="equipmentInfoForm" data-validate="true">
			
			<input type="hidden" id="equipmentId" name="equipmentId"
				value="${equipmentId }" />
			<input type="hidden" id=s_supEquipmentType name="s_supEquipmentType"
				value="${s_supEquipmentType }" />
			<input type="hidden" id="s_equipmentType" name="s_equipmentType"
				value="${s_equipmentType }" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">资产编码<font color='red'>*</font></td>
					<td align="left" ><input type="text" name="equipmentCode" style="width:200px;height:25px"
						id="equipmentCode" class="focused required" data-rule-maxLength="60"
						value="${equipmentCode }" /></td>
                   <td class="addTd">资产类别</td>
					<td align="left">
					<select id="equipmentType" style="width:200px;height:25px"
						name="equipmentType" >
							<c:forEach var="row" items="${dictDetails }">
								<option value="${row.datacode}"
							    	<c:if test="${row.datacode==s_supEquipmentType}"> selected="selected"</c:if>
								    <c:if test="${row.datacode==s_equipmentType}"> selected="selected"</c:if>
									<c:if test="${row.datacode==equipmentType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
					</td>
				</tr>

				<tr>
					<td class="addTd">资产名称<font color='red'>*</font></td>
					<td align="left"><input type="text" name="equipmentName" style="width:200px;height:25px"data-rule-maxLength="200"
						id="equipmentName" class="focuse required"
						value="${equipmentName }" /></td>
					<td class="addTd">投入使用时间：</td>
					<td align="left"><input type="text" name="inuseTime" style="width:190px;height:25px"
						value="<fmt:formatDate value='${inuseTime}' pattern='yyyy-MM-dd '/>"
						data-option='{"dateFmt":"yyyy-MM-dd "}' class="Wdate focused{required:true}" />
					</td>
							
					
				</tr>
				<tr>
				<td class="addTd">资产负责人<font color='red'>*</font></td>
					<td align="left" colspan="3">
<!-- 					<input type="text" class=" users required" -->
<!-- 						placeholder="输入用户名、拼音或登录名" autocomplete="off" -->
<!-- 						usercodeRef="equipmentCharge" -->
<%-- 						value="${cp:MAPVALUE('usercode',equipmentCharge)}" /> <span --%>
<!-- 						style="color: red">*</span> -->
						
						<input type="text" class="required autocomplete"   name="equipmentCharge"  data-token-limit='1' data-pre-populate='${populate}' data-url="${pageContext.request.contextPath}/oa/equipmentInfo!selectUser.do" />
						</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td class="addTd">资产初始价值：</td> -->
<!-- 					<td align="left"><input type="text" name="equipmentPrice" -->
<!-- 						id="equipmentPrice" -->
<!-- 						class="focused required number" data-rule-range="[0,1000000000]" -->
<%-- 						value='<fmt:formatNumber value="${equipmentPrice }" type="number" pattern="#" />' />元<span --%>
<!-- 						style="color: red">*</span> -->
<!-- 					</td> -->
<!-- 					<td class="addTd">年折旧比率：</td> -->
<!-- 					<td align="left"><input type="text" name="yearlyDepreciation" -->
<!-- 						id="yearlyDepreciation" -->
<!-- 						class="focused required number}"  data-rule-range="[0,1]" -->
<%-- 						value='<fmt:formatNumber value="${yearlyDepreciation }" type="number" />' /> --%>
<!-- 					<span style="color: red">*</span> -->
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<td class="addTd">资产其他说明</td>
					<td align="left" colspan="3"><textarea id="equipmentDesc" style="width: 85%" data-rule-maxLength="500"
							name="equipmentDesc" rows="5" cols="50">${equipmentDesc }</textarea></td>
				</tr>


			</table>
			<div class="formButton">
			   <button type="submit" class="btn btn-primary">保存提交</button>
			   <input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
			</div>
		</form>
	</fieldset>
</body>
<%@ include file="/page/common/scripts.jsp"%> 

</html>