<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title><s:text name="suppower.list.title" /></title>
	</head>
	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset style="padding:10px;">
			<legend>
				查询条件
			</legend>
	
			<s:form action="applyUnitInfo" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
				<input type="hidden" name="frameId" value="${frameId}" />
				<input type="hidden" name="selectType" value="${selectType}" />
				<table cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td>
							单位名称:<s:textfield name="s_unitname" style="width:180px" value="%{#parameters['s_itemName']}" />
							&nbsp;&nbsp;&nbsp;&nbsp;<s:submit method="selectList" key="opt.btn.query" cssClass="btn" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="btnOk" name="btnOk" value="确定" class="btn" />
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>
	
		<ec:table action="applyUnitInfo!selectList.do" items="unitList" var="unit"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="opt" title="选择" sortable="false" style="text-align:center">
					<input type="${selectType}" name="unitcode" value="${unit.unitcode}_${unit.unitname}"/>
				</ec:column>
				<ec:column property="unitname" title="单位名称" style="text-align:center" sortable="false"/>
				<ec:column property="contact" title="联系人" style="text-align:center" sortable="false" />
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
		var frameId = "${frameId}";
		var selectType = "${selectType}";
		var JDialogLink = "${JDialogLink}";
		
		$(document).ready(function() {
			if ("yes" == JDialogLink) {
				if ("checkbox" == selectType) {
					var otherUnits = $("#otherUnitCodes", window.parent.frames[frameId].document).val().split(",");
					var otherUnitNames = $("#otherUnits", window.parent.frames[frameId].document).val().split(",");
					var append = "";
					for (var i=0; i<otherUnits.length; i++) {
						if ("" != otherUnits[i]) {
							append += "<input type=hidden name=unitcode value='" + otherUnits[i] + "_" + otherUnitNames[i] + "' /><br/>";
						}
					}
					$("#ec div:first").append(append);
				} else if ("radio" == selectType) {
					var mainNotifyUnit = $("#mainNotifyUnitCode", window.parent.frames[frameId].document).val();
					var mainNotifyUnitName = $("#mainNotifyUnit", window.parent.frames[frameId].document).val();
					if ("" != mainNotifyUnit) {
						append = "<input type=hidden name=unitcode value='" + mainNotifyUnit + "_" + mainNotifyUnitName + "' /><br/>";
					}
					$("#ec div:first").append(append);
				}
				$("[name=JDialogLink]").remove();
			}
			
			var allUnitHiddens = $("input[type='hidden'][name='unitcode']");
			
			var allUnitCheckBoxs = $("input[type='checkbox'][name='unitcode']");
			for (var i=allUnitHiddens.length-1; i>=0; i--) {
				var hiddenValue = $(allUnitHiddens[i]).val();
				for (var j=0; j<allUnitCheckBoxs.length; j++) {
					if (hiddenValue == $(allUnitCheckBoxs[j]).val()) {
						$(allUnitCheckBoxs[j]).attr("checked", true);
						$(allUnitHiddens[i]).remove();
						break;
					}
				}
			}
			
			var allUnitRadios = $("input[type='radio'][name='unitcode']");
			for (var i=allUnitHiddens.length-1; i>=0; i--) {
				var hiddenValue = $(allUnitHiddens[i]).val();
				for (var j=0; j<allUnitRadios.length; j++) {
					if (hiddenValue == $(allUnitRadios[j]).val()) {
						$(allUnitRadios[j]).attr("checked", true);
						$(allUnitHiddens[i]).remove();
						break;
					}
				}
			}
			
			
			$("input[type=radio]").click(function() {
				var value=$(this).val();
				var index = value.indexOf("_");
				if (confirm("确认要选择" + value.substring(index+1) + "吗？\n点击确定后，窗口将会关闭。")) {
					$("#mainNotifyCode", window.parent.frames[frameId].document).val(value.substring(0, index));
					$("#mainNotifyUnit", window.parent.frames[frameId].document).val(value.substring(index+1));
					window.parent.window.JDialog.close();
				}
				return false;
			});
			
			$("#btnOk").click(function() {
				var unitcodes = new Array();
				var unitnames = new Array();
				var unitHiddens = $("input[type='hidden'][name='unitcode']");
				for (var i=0; i<unitHiddens.length; i++) {
					var unitinfo = $(unitHiddens[i]).val();
					var index = unitinfo.indexOf("_");
					var unitcode = unitinfo.substring(0, index);
					for (var j=0; j<unitcodes.length; j++) {
						if (unitcode == unitcodes[j]) {
							continue;
						}
					}
					unitcodes.push(unitcode);
					unitnames.push(unitinfo.substring(index+1));
				}
				var unitCheckBoxs = $("input[type='checkbox'][name='unitcode']:checked");
				for (var i=0; i<unitCheckBoxs.length; i++) {
					var unitinfo = $(unitCheckBoxs[i]).val();
					var index = unitinfo.indexOf("_");
					var unitcode = unitinfo.substring(0, index);
					for (var j=0; j<unitcodes.length; j++) {
						if (unitcode == unitcodes[j]) {
							continue;
						}
					}
					unitcodes.push(unitcode);
					unitnames.push(unitinfo.substring(index+1));
				}
				$("#otherUnitCodes", window.parent.frames[frameId].document).val(unitcodes.join(","));
				$("#otherUnits", window.parent.frames[frameId].document).val(unitnames.join(","));
				window.parent.window.JDialog.close();
			});
		});
	</script>
</html>
