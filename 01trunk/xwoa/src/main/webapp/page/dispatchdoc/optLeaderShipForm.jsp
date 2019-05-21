<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>分配流程机构</title>
	<%-- <sj:head locale="zh_CN" /> --%>
<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding:10px;">
<legend>分配流程机构</legend>
<s:form id="organizeForm" name="organizeForm" method="post"
 action="incomeDoc" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
<input type="hidden" id="djId" name="ship.djId" value="${djId}">
<input type="hidden" id="no" name="ship.no" value="${ship.no}">
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
	<tr class="xbcdep">
		<td class="addTd" width="20%">批示领导：</td>
		<td align="left" width="80%">
			<input type="text" id="leaderName" name="ship.leaderName" maxlength="100" value="${ship.leaderName}" style="width:70%" />
		</td>
	</tr>
	<tr class="xbcdep">
		<td class="addTd"  width="20%">领导单位：</td>
		<td align="left"  width="80%">
		<input type="text" id="unitName" name="ship.unitName" maxlength="100" value="${ship.unitName}" style="width:70%" />
		</td>
	</tr>
	<tr class="xbcdep">
		<td class="addTd">批示时间：</td>
		<td align="left">
		<input type="text" class="Wdate"  id="shipDate" name="ship.shipDate" 
				style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
				value='<fmt:formatDate value="${ship.shipDate}" pattern="yyyy-MM-dd"/>'
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 		<sj:datepicker id="shipDate"  --%>
<%-- 		name="ship.shipDate" value="%{ship.shipDate}" style="width: 120px;" --%>
<%-- 		yearRange="2000:2020" displayFormat="yy-mm-dd"  --%>
<%-- 		changeYear="true" readonly="true" cssStyle="width: 100px;" /> --%>
		</td>
	</tr>
	<tr class="xbcdep">
		<td class="addTd">批示内容：</td>
		<td align="left">
		<input type="text" id="leaderNote" name="ship.leaderNote" maxlength="100" value="${ship.leaderNote}" style="width:100%" />
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" value="保存" Class="btn" onclick="checkOrganize()"  />
			<input type="button" value="返回" Class="btn" onclick="javscript:window.parent.window.JDialog.close();" />
		</td>
	</tr>
</table>
</s:form>
</fieldset>
</body>
<script type="text/javascript">
function checkOrganize(){
	if(!$.trim($("#leaderName").val())){
		alert('请输入批示领导');
		return false;
	}
	if(!$("#unitName").val()) {
		alert('请输入领导单位');
		return false;
	}
	if(!$("#shipDate").val()) {
		alert('请输入批示时间');
		return false;
	}
	
	////
	var data = $("#organizeForm").serializeArray(); 
	var no = $.ajax({
		url: "${contextPath}/dispatchdoc/incomeDoc!saveShip.do",
		type: "post",
		data: data,
		async: false
	}).responseText;
	
	if (no) {
		var djId = $("#djId").val();
		
		var buffer = new StringBuffer();
		buffer.append("<td>" + $("#leaderName").val() + "</td><td>" + $("#unitName").val() + "</td><td>" + $("#shipDate").val());
		buffer.append("</td><td>" + $("#leaderNote").val() + "</td><td>");
		buffer.append("<input type='button' class='btn' value='编辑' onclick='editShip(\"" + djId + "\",\"" + no + "\");' />&nbsp;&nbsp;");
		buffer.append("<input type='button' class='btn' value='删除' onclick='deleteShip(this,\"" + djId + "\",\"" + no + "\");' />&nbsp;</td>");
		
		if ($("#no").val()) { // edit
			$("#tr_" +  no, window.parent.document).html(buffer.toString());
		} else { // add
			$("#ship_list", window.parent.document).append("<tr id='tr_" + no + "'>" + buffer.toString() + "</tr>");
		}
		
		window.parent.window.JDialog.close();
	} else {
		alert("保存失败！");
	}
	
	return false;
}

function addOrganize(orgCode, orgName) {
	var unitCode = $("#unitCode").val();
	var unitCodeArray = $.trim(unitCode) ? unitCode.split(",") : new Array();
	
	if (unitCodeArray.contains(orgCode)) {
		return false;
	}
	
	unitCodeArray.push(orgCode);
	$("#unitCode").val(unitCodeArray.join(","));
	
	var orgNameBuffer = new StringBuffer();
	orgNameBuffer.append("<span id='orgCode_" + orgCode + "'>[" + orgName + "&nbsp;");
	orgNameBuffer.append("<a href='###' onclick='deleteOrganize(\"" + orgCode + "\");'>");
	orgNameBuffer.append("<img border='0' src='../images/close.png'></a>]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>");
	$("#orgNameArea").append(orgNameBuffer.toString());
	
	return false;
}

function deleteOrganize(orgCode) {
	var unitCode = $("#unitCode").val();
	var unitCodeArray = $.trim(unitCode) ? unitCode.split(",") : new Array();
	unitCodeArray.remove(orgCode);
	$("#unitCode").val(unitCodeArray.join(","));
	$("#orgCode_" + orgCode).remove();
	
	return false;
}

$(document).ready(function() {
	$.ajax({
		type : "POST",
		url : "${pageContext.request.contextPath}/sys/userDef!getUnits.do",
		success : function(data) {
			var menuList = jQuery.parseJSON(data);
			var shadow = "<div id='boxContent' style=''><div id='lists' class='getTree' style='width:580px;height:120px;margin-left:0px;border:1px solid #369;position:static;'>Loader</div></div>";
			if($("#boxContent").length > 0){
				$("#boxContent").show();
			}else{
				$("#organizeArea").parent().parent().append(shadow);
			 	setTimeout(function() {
					menuDisplay(menuList,"0");
				}, 0);
			};
			
			$("#lists span").live('click', function() {
				var $this = $(this);
				addOrganize($this.attr("rel"), $this.html());
// 				$("#lists span").die("click");
			});
		},
		error : function() {
			
		}
	});
});
</script>
</html>
	