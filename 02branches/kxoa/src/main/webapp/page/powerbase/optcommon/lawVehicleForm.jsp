<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>
		<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
<title>执法车辆管理</title>
</head>
<script type="text/javascript">
/**
 * common window dialogs
 */

</script>
<body>
<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding:10px;">

<legend style="padding:4px 8px 3px;"><b>车辆信息</b></legend>
<s:form action="lawVehicle" method="post" namespace="/powerbase" id="lawVehicleForm" enctype="multipart/form-data">
			<s:submit name="save" method="lawsave" cssClass="btn" key="opt.btn.save" />
			<input type="hidden" id="vehicleId" name="vehicleId" value="${vehicleId}"/>
 			<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" /> 
              <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
              <tr>
					<td class="addTd" width="130">
						<s:text name="lawVehicle.plateNumber" />
					</td>
					<td >
						<input type="text" id="plateNumber" name="plateNumber" value="${object.plateNumber}" style="width:300px;" >
					</td>
		
					<td class="addTd" width="130">
					<s:text name="lawVehicle.vehicleType" />
					</td>
					<td >
						<input type="text" id="vehicleType" name="vehicleType" value="${object.vehicleType}" style="width:300px;">
  						
					</td>
				</tr>

					<tr>
					<td class="addTd" width="130">
					<s:text name="lawVehicle.ownerUnit" />
					</td>
					<td ><select name="ownerUnit" style="width: 180px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${unitList }">
								<option value="${row.depno}"
									<c:if test="${object.ownerUnit eq row.depno}"> selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="addTd" width="130">
					<s:text name="lawVehicle.vehicleAdmin" />
					</td>
					<td >
						<input type="text" id="vehicleAdmin" name="vehicleAdmin" value="${object.vehicleAdmin}" style="width:300px;">
					</td>
				</tr>	
			
				<tr>
					<td class="addTd" width="130"><s:text name="lawVehicle.purchaseDate" />
					</td>
					<td >
						<sj:datepicker id="purchaseDate" name="purchaseDate"  style="width:140px"
			yearRange="2000:2020"  displayFormat="yy-mm-dd" changeYear="true"  timepicker="true"
			value="%{object.purchaseDate}"/>
			</td>
				<td class="addTd" width="130"><s:text name="lawVehicle.purchasePrice" />
					</td>
			        <td >
						<input type="text" id="purchasePrice" name="purchasePrice" value="${object.purchasePrice}" style="width:300px;">
  						
					</td>
				</tr>
				<tr>
			<td class="addTd" width="130"><s:text	name="lawVehicle.engineNo" />
					</td>
			        <td >
						<input type="text" id="engineNo" name="engineNo" value="${object.engineNo}" style="width:300px;">
  						
					</td>
					<td class="addTd" width="130"><s:text	name="lawVehicle.carframeNo" />
					</td>
			        <td >
						<input type="text" id="carframeNo" name="carframeNo" value="${object.carframeNo}" style="width:300px;">
  						
					</td>
				</tr>
						<tr>
					<td class="addTd" width="130"><s:text 	name="lawVehicle.recorder" /></td>
					<td align="left" ><input type="hidden" id="recorder" name="recorder"
								value="${object.recorder}" />
					<s:property value="%{object.recorder}" /></td>
					<td class="addTd" width="130"><s:text	name="lawVehicle.recordDate" /></td>
					<td align="left" ><s:date name="recordDate"
							format="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				
</table>
</s:form>
</fieldset>

</body>
</html>
