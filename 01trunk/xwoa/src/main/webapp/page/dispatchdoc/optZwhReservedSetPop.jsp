<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title><s:text name="预留文号" /></title>
</head>
<body>
	<s:form action="ioDocArchiveNo!saveZwhReserved.do" method="post" namespace="/dispatchdoc" id="ioDocArchiveNoForm">
		<input type="hidden" id="wjlx" name="wjlx" value="${object.wjlx}" />
		<input type="hidden" id="lsh" name="lsh" value="${object.lsh}" />
		<input type="hidden" id="lshYear" name="lshYear" value="${object.lshYear}" />
		<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
			<legend style="padding:4px 8px 3px;">
				<b>设置预留文号</b>
			</legend>
			<table id="editTable" width="200" border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">
						文件类型
					</td>
					<td>
						<c:out value="${cp:MAPVALUE('WJLX',object.wjlx)}" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						预留文号<font color="#ff0000">*</font>
					</td>
					<td align="left">
						<c:out value="${object.lsh}" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						预留原因<font color="#ff0000">*</font>
					</td>
					<td align="left">
						<textarea id="reservedReason" name="reservedReason" style="width: 100%; height: 80px;"></textarea>
						<span id="reservedReasonMessage"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" id="btnSave" name="btnSave" value="保存" class="btn" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" id="btnClose" name="btnClose" value="关闭" class="btn" />
					</td>
				</tr>
			</table>
		</fieldset>
	</s:form>
</body>
<script type="text/javascript">
	var reservedLshArray = null;
	var wjlx = $("#wjlx").val();
	var $opt = $("input[name=wjlxDataCode][value=" + wjlx + "]", window.parent.document);

	$(document).ready(function() {
		$opt.parent().prev().html($("#lsh").val());
	});
	
	$("#btnSave").click(function() {
		var validation = validateEmpty("reservedReason", "请输入预留原因")
							&& validateLength("reservedReason", 500, "预留原因超出最大长度");
		
		if (!validation) {
			return false;
		}
		
		var reservedNo = parseInt($("#lsh").val());
		
		$.ajax({
			type : "POST",
			url : "${contextPath}/dispatchdoc/ioDocArchiveNo!saveZwhReserved.do?wjlx=" + $("#wjlx").val() 
					+ "&lshYear=" + $("#lshYear").val() + "&reservedNo=" + reservedNo + "&reservedReason=" + $("#reservedReason").val(),
			dataType : "json",
			success : function(data) {
				if ("success" == data.status) {
					$opt.parent().prev().html(reservedNo+1);
					window.parent.window.JDialog.close();
				} else if ("unavailable" == data.status) {
					alert("文号 " + reservedNo + " 已经被使用，请刷新页面");
					window.parent.window.JDialog.close();
				} else {
					alert("预留文号 " + reservedNo + " 失败");
				}
			},
			error : function() {
				alert("预留文号 " + reservedNo + " 失败");
			}
		});
	});
	
	$("#btnClose").click(function() {
		window.parent.window.JDialog.close();
	});
	
	$("#reservedReason").keyup(function() {
		CommonUtils.showTextAreaLimited("reservedReason", "reservedReasonMessage", 500);
	});
</script>
</html>

