<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<title>执法车辆查看</title>
	<script language="JavaScript" src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgcore.min.js" type="text/JavaScript"></script>
    <script language="JavaScript" src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgdialog.js" type="text/JavaScript"></script>
</head>

<body >

	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding: 10px;">
		<legend class="ctitle" style="width: auto; margin-bottom: 5px;">
			车辆信息查看
		</legend>
		<s:form action="lawVehicle" method="post" namespace="/powerbase"  enctype="multipart/form-data">
			
			<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" />
				<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
	
			<tr>
				<td class="addTd"><s:text name="lawVehicle.plateNumber" /></td>
				<td ><s:property value="%{plateNumber}" /></td>
		
				<td class="addTd"><s:text name="lawVehicle.vehicleType" /></td>
				<td ><s:property value="%{vehicleType}" /></td>
			
			</tr>
			<tr>
				<td class="addTd"><s:text name="lawVehicle.ownerUnit" /></td>
				<td align="left">${cp:MAPEXPRESSION
			        ("depno",phobject.ownerUnit)}</td>
	              <td class="addTd" width="130">
					<s:text name="lawVehicle.vehicleAdmin" />
					</td>
					<td ><s:property value="%{vehicleType}" /></td>
			</tr>
			<tr>
			<td class="addTd" width="130"><s:text name="lawVehicle.purchaseDate" />
					</td>
					<td >
			<fmt:formatDate value='${purchaseDate}' pattern='yyyy-MM-dd hh:mm:ss' />
			</td>
				<td class="addTd" width="130"><s:text name="lawVehicle.purchasePrice" />
					</td>
			        <td >
					<s:property value="%{purchasePrice}" />
  						
					</td>
			</tr>
			<tr>
				<td class="addTd" width="130"><s:text	name="lawVehicle.engineNo" />
					</td>
			        <td >
					<s:property value="%{engineNo}" />
  						
					</td>
					<td class="addTd" width="130"><s:text	name="lawVehicle.carframeNo" />
					</td>
			        <td >
						<s:property value="%{carframeNo}" />
					</td>
			<tr>
				<td class="addTd"><s:text name="lawVehicle.recorder" /></td>
				<td ><s:property value="%{recorder}" /></td>
		
				<td class="addTd"><s:text name="lawVehicle.recordDate" /></td>
				<td ><fmt:formatDate value='${phobject.recordDate}' pattern='yyyy-MM-dd hh:mm:ss' /></td>
			</tr>
			</table>
	
		</s:form>
	</fieldset>
	<script type="text/javascript">
	function downFile(caseno, fileType) {
		var url = "lawenforecase!downloadStuff.do?caseno=" + caseno + "&fileType="
				+ fileType;
		document.location.href = url;
	}

	</script>
</body>
</html>
