<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaAssetinformationInlog.edit.title" /></title>
</head>

<body>
	<fieldset>
		<legend>
			<p>进库信息</p>
		</legend>
			<div align="right">
		<input type="button" name="buttonForm" value="新增" class="btn" />
		<input type="button"
				name="back" Class="btn" onclick="history.back(-1);" value="返回" //若为厅机关则查询出所有厅机关的资产信息 />
			</div>
		<iframe id="iframe1" name="info1"
			src="${pageContext.request.contextPath}/oa/oaAssetinformation!view.do?no=${no}&callback=T"
			width="100%" style="margin-bottom: 10px;" frameborder="no"
			scrolling="no" border="0" marginwidth="0"
			onload="this.height=window.frames['info1'].document.body.scrollHeight"></iframe>
		<s:form action="oaAssetinformationInlog" method="post" namespace="/oa"
			id="oaAssetinformationInlogForm" style="display:none;width:100%;">
			<%-- <input type="hidden" name="no" value="${no }" />
			<input type="hidden" name="djid" value="${object.djid }" /> --%>
			<input type="hidden" name="s_supEquipmentType" value="resourse" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">资产名称</td>
					<td align="left" colspan="4">${cp:MAPVALUE('equipment',
						object.no) }</td>
				</tr>

				<tr>
					<td class="addTd"><s:text
							name="oaAssetinformationInlog.assetnum" /><span style="color:red;">*</span></td>
					<td align="left" colspan="4"><s:textfield name="assetnum" id="assetnum" size="40" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="oaAssetinformationInlog.createRemark" /><span style="color:red;">*</span></td>
					<td align="left" colspan="4"><s:textarea name="createRemark" id="createRemark"
							style="width:100%;" /></td>
				</tr>

			</table>
			<div class="formButton" id="formButton" style="display: none;">
			    <input type="button" class="btn" id="save" value="保存"
			    	onclick="submitItemFrame('SAVE');" /> 
		    </div>

		</s:form>
		<iframe id="info2" name="info2"
			src="${pageContext.request.contextPath}/oa/oaAssetinformationInlog!list.do?no=${no}"
			width="100%" style="margin-bottom: 10px;" frameborder="no"
			scrolling="no" border="0" marginwidth="0"
			onload="this.height=window.frames['info2'].document.body.scrollHeight"></iframe>

		<%@ include file="/page/common/messages.jsp"%>

	</fieldset>
	<script type="text/javascript">
		var srForm = document.getElementById("oaAssetinformationInlogForm");
		var buttonValue = document.getElementById("formButton");
		$("[name='buttonForm']").bind('click', function() {
			if ($("[name='buttonForm']").attr("value") == "新增") {
				$("[name='buttonForm']").attr("value", "取消");
				srForm.style.display = "block";
				buttonValue.style.display = "block";
			    parent.document.getElementById("info").height = document.body.scrollHeight;
			} else {
				$("[name='buttonForm']").attr("value", "新增");
				srForm.style.display = "none";
				buttonValue.style.display = "none";
			    parent.document.getElementById("info").height = document.body.scrollHeight;
			}
		});
		function submitItemFrame(subType) {
			if(checkForm()){
				if (subType == 'SAVE') {
					srForm.action = 'oaAssetinformationInlog!save.do?no=${no}&djid=${object.djid}';
				}
				srForm.submit();
			}
		}
		function checkForm(){
			if($("#assetnum").val()==''){
				alert("变更数量不可为空！");
				$('#assetnum').focus();
				return false;
			}
			if($("#assetnum").val()!=''){
				var isvalid= (new RegExp("^[1-9]\\d*$")).test($("#assetnum").val());
				if(!isvalid){
					alert("变更数量格式不正确！");
					$('#assetnum').focus();
					return false;
				}
			}
			if($("#createRemark").val()==''){
				alert("记录备注不可为空！");
				$('#createRemark').focus();
				return false;
			}
			if($("#createRemark").length>1000){
				alert("记录备注输入字符过长！");
				$('#createRemark').focus();
				return false;
			}
			return true;
		}
	</script>
</body>
</html>
