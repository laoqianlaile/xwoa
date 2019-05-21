<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaCarinfo.list.title" /></title>
</head>
<fieldset>
	<legend>选择资源</legend>
	
	<form action="#" id="testForm">
		<ec:table action="#" items="objList" var="fEquipmentInfo" showPagination="false"  sortable="false" tableId="weaponsSelect"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"  retrieveRowsCallback="limit">
			<ec:row styleClass="name">
			    <input type="hidden" id="equipmentId${fEquipmentInfo.equipmentId }" value="${fEquipmentInfo.equipmentId }"
								name="equipmentId${fEquipmentInfo.equipmentId }" />
				 <input type="hidden" id="equipmentName${fEquipmentInfo.equipmentId }" value="${fEquipmentInfo.equipmentName }"
								name="equipmentName${fEquipmentInfo.equipmentId }" />
			<ec:column property="opt" title="操作" sortable="false"
				style="text-align:center">
				<input type="radio" name="equipmentId"
					onclick="setParentVal('${fEquipmentInfo.equipmentId}')">
			</ec:column>
				<ec:column property="equipmentName" title="名称" styleClass="name">
				${fEquipmentInfo.equipmentName }
				</ec:column>
				<ec:column property="equipmentCharge" title="负责人" >
				${cp:MAPVALUE('usercode',fEquipmentInfo.equipmentCharge)}
				</ec:column>
				<ec:column property="equipmentType" title="类别" styleClass="类别">
				${cp:MAPVALUE('equipment', fEquipmentInfo.equipmentType) }
				</ec:column>
			</ec:row>
		</ec:table>
	</form>
</fieldset>	

<script type="text/javascript">

//获取父页面对象
var parentDocument = window.opener.document;
//设置返回值
function setParentVal(no) {

	if (window.confirm("确认选择吗")) {		
	
		parentDocument.getElementById('equipmentId').value = no;
	    parentDocument.getElementById('equipmentName').value = document.getElementById('equipmentName' + no).value;
		window.close();
	}
}



/* $('#weaponsSelect_table tr.name').click(function() {
	
	var tr = $(this), 
	id=$("td:eq(2)", tr),
	
	id=td.prev('td'),  dialog = tr.closest('div.dialog');
	
	dialog.data('result', {
		key: id.text().trim(),
		value: td.text().trim()
	}).trigger('close');
}); */
</script>