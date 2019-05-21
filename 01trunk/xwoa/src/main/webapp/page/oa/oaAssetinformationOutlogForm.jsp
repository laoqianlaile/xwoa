	<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaAssetinformationInlog.edit.title" /></title>
</head>

<body>
	<fieldset class="form">
	<legend>
			<p>出库信息</p>
		</legend>
		<div align="right">
		<input type="button" name="buttonForm" value="新增" class="btn" />
		<input type="button"
				name="back" Class="btn" onclick="history.back(-1);" value="返回" />
		</div>
		<iframe id="iframe1" name="info3"
			src="${pageContext.request.contextPath}/oa/oaAssetinformation!view.do?no=${no}&callback=T"
			width="100%" style="margin-bottom: 10px;" frameborder="no"
			scrolling="no" border="0" marginwidth="0"
			onload="this.height=window.frames['info3'].document.body.scrollHeight"></iframe>
		<s:form action="oaAssetinformationInlog" method="post" namespace="/oa"
			id="oaAssetinformationOutlogForm" style="display:none;width:100%;">
			<input type="hidden" name="s_supEquipmentType" value="resourse" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd" >资产名称</td>
					<td align="left" colspan="4">${cp:MAPVALUE('equipment',
						object.no) }</td>
					
				</tr>

				<tr>
				    <td class="addTd" >领用时间<span style="color:red;">*</span></td>
					<td align="left">
					<input type="text" class="Wdate" style="height:22px;width:70%;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${applydate}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="applydate" name="applydate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />				
					</td>
					<td class="addTd"><s:text
							name="oaAssetinformationOutlog.assetnum" /><span style="color:red;">*</span></td>
					<td align="left" colspan="4"><s:textfield name="assetnum"  id="assetnum"/></td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="oaAssetinformationOutlog.applyuser" /><span style="color:red;">*</span>
					</td>
					<td align="left">
					<input type="hidden" name="applyuser" id="applyuser" value=""/>
					<s:textfield name="applyusers" id="applyusers" style="width:60%;" readonly="true"/>
					<input type="button" class='btn' name="powerBtn"
								onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaAssetinformation!userSelectList.do?isDept=${isDept}','powerWindow',null);"
								value="选择人员">
					</td>
					<td class="addTd"><s:text
							name="oaAssetinformationOutlog.applyUnitcode" /></td>
					<td align="left">
					<input type="hidden" name="applyUnitcode" id="applyUnitcode" value="" />
					<s:textfield name="applyUnitcodes" id="applyUnitcodes" value="" readonly="true"/></td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="oaAssetinformationOutlog.createRemark" /><span style="color:red;">*</span></td>
					<td align="left" colspan="4"><s:textarea name="createRemark" id="createRemark"
							style="width:100%;" /></td>
				</tr>

			</table>
			<div class="formButton" id="formButton" style="display: none;">
			     <input type="button" class="btn" id="save" value="保存"
				         onclick="submitItemFrame('SAVE');" /> 
		    </div>

		</s:form>
		<iframe id="info2" name="info4"
			src="${pageContext.request.contextPath}/oa/oaAssetinformationOutlog!list.do?no=${no}&datacode=${object.no}&isDept=${isDept}"
			width="100%" style="margin-bottom: 10px;" frameborder="no"
			scrolling="no" border="0" marginwidth="0"
			onload="this.height=window.frames['info4'].document.body.scrollHeight"></iframe>

		<%@ include file="/page/common/messages.jsp"%>
		

	</fieldset>
	<script type="text/javascript">
		var srForm = document.getElementById("oaAssetinformationOutlogForm");
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
					srForm.action = 'oaAssetinformationOutlog!save.do?no=${no}&djid=${object.djid}';
				}
				srForm.submit();
			}
		}
		function checkForm(){
			if($("#applydate").val()==''){
				alert("领用时间不可为空！");
				$('#applydate').focus();
				return false;
			}
			if($("#assetnum").val()==''){
				alert("领用数量不可为空！");
				$('#assetnum').focus();
				return false;
			}
			if($("#assetnum").val()!=''){
				var isvalid= (new RegExp("^[1-9]\\d*$")).test($("#assetnum").val());
				if(!isvalid){
					alert("领用数量格式不正确！");
					$('#assetnum').focus();
					return false;
				}
			}
			if($("#applyusers").val()==''){
				alert("领用者不可为空！");
				$('#applyusers').focus();
				return false;
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
