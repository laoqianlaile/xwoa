<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>编辑权力流程、风险</title>
</head>
<script type="text/javascript">
	/**
	 * common window dialogs
	 */
	function openNewWindow(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'height=600px,width=700px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
	var _get = function(id) {
		return document.getElementById(id);
	};
	//置空风险点信息
	function doclear() {
		_get('riskId').value = 0;
		_get('riskdes').value = '';
	}
	function doBackPower(url){
		document.location.href = url;
		
	}
</script>
<body class="sub-flow">
	<%@ include file="/page/common/messages.jsp"%>
	<s:form action="powerOptInfo" method="post" namespace="/powerbase"
		id="powerOptInfoForm">
<%-- 		<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" /> --%>
<!-- 	<input type="button"  value="返回" Class="btn" onclick="window.history.back()" /> -->
		<input type="hidden" id="itemId" name="itemId" value="${object.itemId}" />
	<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
	<input type="button"  value="返回" Class="btn" onclick="window.history.back()" />
			
<fieldset style="padding:10px;display:block;margin-bottom:10px;" class="form">
	<legend><b>事务流程配置</b></legend>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="viewTable">
	<tr>
				<td class="addTd" width="15%">流程名称<span style="color: red">*</span></td>
				<td align="left" width="82%">
				<input type="hidden" name="wfcode" id="wfcode" value="${object.wfcode }"/>
				<s:textarea name="wflabel" id="wflabel" value="%{wflabel}" style="width: 70%;height:30px;"/>
				<input type="button" class="btn" value="选择流程" onClick="openNewWindow('<%=request.getContextPath()%>/powerbase/powerOptInfo!listSelectFlowDescribe.do',null,null);"> 
			</td>
			</tr>
			<tr>
					<td class="addTd">选择显示模块<span style="color: red">*</span></td>
					<td align="left"><s:radio name="oaModuleType"
							id="oaModuleType" value="%{object.oaModuleType }"
							onclick="checkReceive(this.value);"
							list="#{'C':'基础模块','B':'业务模块' }" listKey="key" listValue="value"></s:radio></td>
				</tr>

				<tr id="moduleItem" style="display: none">
					<td class="addTd">业务模块</td>
					<td align="left"><select name="applyItemType"
						id="applyItemType" style="width: 20%;height:30px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('APPLYITEM')}">
								<option value="${row.key}"
									<c:if test="${object.applyItemType eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select>&nbsp;&nbsp;</td>
				</tr>
			<tr>
				<td class="addTd" >材料分组</td>
				<td align="left">
				<input type="hidden" name="group_id" id="group_id" value="${object.group_id }"/>
				<s:textarea name="grouplabel" id="grouplabel" value="%{grouplabel }" style="width: 70%;height:30px;"/>
				<input type="button" class="btn" value="选择材料" onClick="openNewWindow('<%=request.getContextPath()%>/powerbase/powerOptInfo!listSelectSuppowerstuffgroup.do',null,null);"> 
	</td>
			</tr>
		<%-- 	<tr>
				<td class="addTd" >申请事项类别</td>
				<td align="left"><select name="applyItemType"
					style="width: 200px">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('APPLYITEM')}">
							<option value="${row.key}"
								<c:if test="${object.applyItemType eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach>
				</select></td>
			</tr> --%>
	<tr id="moduleDoc">
				<td class="addTd" >模版名称</td>
				<td align="left">
				<input type="hidden" name="recordid" id="recordid" value="${object.recordid }"/>
				<s:textarea name="recordidlabel" id="recordidlabel" value="%{recordidlabel }" style="width: 70%;height:30px;"/>
				<input type="button" class="btn" value="选择模板" onClick="openNewWindow('<%=request.getContextPath()%>/powerbase/powerOptInfo!listSelectTempfile.do',null,null);"> 
				<%-- <select name="recordid"
					style="width: 200px">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${templatelist}">
							<option value="${row.value}"
								<c:if test="${object.recordid eq row.value}"> selected = "selected" </c:if>>
								<c:out value="${row.label}" />
							</option>
						</c:forEach>
				</select> --%></td>
						</tr>
	<%-- 	<tr id="isApply">
				<td class="addTd" >是否显示申请者</td>
				<td align="left"><select name="isApplyUser"
					style="width: 200px">
					<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('TrueOrFalse')}">
									<option value="${row.key}" 
									<c:if test="${object.isApplyUser eq row.key}"> selected = "selected" </c:if>
									<c:if test="${empty object.isApplyUser and row.key eq '01'}"> selected = "selected" </c:if>
									>
									<c:out value="${row.value}" /></option>
								</c:forEach>
				</select></td>
			</tr> --%>
			
			<tr id="generalModuleCode">
				<td class="addTd" >模块代码</td>
				<td align="left"><input type="text" name="generalModuleCode" value="${object.generalModuleCode }" style="height:30px;"/>
			        <input type="checkbox"  name="isGeneralModule" value="T"
					<c:if test="${object.isGeneralModule eq 'T' }">checked="checked"</c:if>/>启用</td>
			</tr>
			
			<c:if test="${'FW' eq power.itemType or  'SW' eq power.itemType }">
		    <tr id="tr_agencyName" >
					<td class="addTd">机关名义</td>
					<td align="left"><select name="agencyName"
						id="agencyName" style="width: 20%;height:30px;">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('AGENCYNAME')}">
								<option value="${row.key}"
									<c:if test="${object.agencyName eq row.key}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select>&nbsp;&nbsp;</td>
			</tr>
			</c:if>
				<tr>
					<td  colspan="2"><span style="color: red">温馨提示</span>：<br><span
						style="color: #0c6da0;font-family: Microsoft YaHei !important;font-weight: normal">
						1、业务须和流程关联，才可以使用。<br>
						2、基础模块为通用固化模块，业务模块为个性化业务；<br>
						3、业务模块配置对应字典项名称为 APPLYITEM ,业务流水号规则参照字典项BIZTYPE;<br>
						4、模板名称配置对应模板分类为事权文书(SQWS)<br>
						5、模块代码配置通用运行模块，可实现登记页面指定流程办理人员与机构<br>
						<c:if test="${'FW' eq power.itemType}">
						6、机关名义：主要用于公文分级管理确定文书模板及文号数据
						</c:if>
						</span>
						</td>
				</tr>
		</table>
</fieldset>

	</s:form>
	</body>
	<script type="text/javascript">
		checkReceive('${object.oaModuleType}');
		function checkReceive(t) {
			if (t == "B") {
				$("#moduleItem").css("display", "table-row");
				$("#isApply").css("display", "none");
				$("#moduleDoc").css("display", "none");
			} else {
				$("#moduleItem").css("display", "none");
				$("#isApply").css("display", "table-row");
				$("#moduleDoc").css("display", "table-row");
				document.getElementsByName('oaModuleType')[0].checked = true;
			}
		}
		
		function doCheck(){
			if($("#wfcode").val()==''){
				alert("申请日期不能为空");
				$("#wfcode").focus();
				return false;				
			}
			
		}
	</script>
	</html>