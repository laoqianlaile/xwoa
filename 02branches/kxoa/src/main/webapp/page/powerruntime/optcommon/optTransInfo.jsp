<%@ page contentType="text/html;charset=UTF-8" import="java.util.*"%>
<%@ include file="/page/common/taglibs.jsp"%>

<html>
<head>

<title>办理信息</title>
<script type="text/javascript">

function checkForm(){
	if('${moduleParam.hasIdea}' == 'T' && _get('ideacode') != undefined && trim(_get('ideacode').value).length==0){
		alert("请选择${moduleParam.ideaLabel}。");
		_get('ideacode').focus();
		return false;
	}
	
	if(trim(_get('transcontent').value).length==0){
		alert("${moduleParam.ideaContent}不能为空。");
		_get('transcontent').focus();
		return false;
	}
	
	if(_get('transcontent').value.length>500){
		alert("${moduleParam.ideaContent}长度不等超多500。");
		_get('transcontent').focus();
		return false;
	}
	
	if('${moduleParam.hasZbUser}' == 'T'){
		if(trim(_get('zbUserCodes').value).length == 0){
			alert("${moduleParam.zbUserLabel}不能为空。");			
			return false;
		}
		
		if(_get('zbUserCodes').value.indexOf(',') > 0){
			alert("${moduleParam.zbUserLabel}只能选择一位。");			
			return false;
		}
	}
	
	if('${moduleParam.isTeamRoleCheck}' != 'F' ){
		if(_get('bjCodes') != undefined && '${moduleParam.assignTeamRole}' == 'T'
				&& !$("#zbOrgRoleCodeTr").is(":hidden")){
			if(trim(_get('bjCodes').value).length==0){
				alert("${moduleParam.teamRoleName}不能为空。");			
				return false;
			}
		}
	}
	
	if('${moduleParam.isTeamRoleCheck}' == 'F'){
		if($("#ideacode").val() == 'T'){
			if(_get('bjCodes') != undefined && '${moduleParam.assignTeamRole}' == 'T'
					&& !$("#zbOrgRoleCodeTr").is(":hidden")){
				if(trim(_get('bjCodes').value).length==0){
					alert("${moduleParam.teamRoleName}不能为空。");			
					return false;
				}
			}
		}
	}
	
	if('${moduleParam.assignEngineRole}' == 'T'){
		if(!$.trim($("#engineCodes").val())) {
			alert("${moduleParam.engineRoleName}不能为空。");			
			return false;
		}
	}
	if('${moduleParam.hasOrgRole}' == 'T'){
		if(_get('zbOrgRoleCode') != undefined && trim(_get('zbOrgRoleCode').value).length==0 && trim('${moduleParam.zbOrgRoleCode }') > 0){
			alert("主办处室配置错误，请联系管理员。");
			return false;
		}
		if(_get('zbOrgCode') != undefined && trim(_get('zbOrgCode').value).length==0){
			alert("请选择主办处室。");
			return false;
		}
	}
	
	if ($("#xbOrgCodes").attr("id")) {
		var nodeCode = $("#nodeCode").val();
		var variableName = "ishq";
			if ("" != $.trim($("#xbOrgCodes").val())) {
				$("<input type='hidden' id='" + variableName + "' name='" + variableName + "' value='T' />").insertAfter("#ideacode");
			} else {
				$("<input type='hidden' id='" + variableName + "' name='" + variableName + "' value='F' />").insertAfter("#ideacode");
			}
	}
	

	return true;
}

var _get = function (id) {
	return document.getElementById(id);
};

//字符空格处理
var trim = function (str) {
	return str.replace(/^\s+|\s+$/g, "");
};

// 签报流程拟稿人处理下拉选择时展示js
var _get = function (id) {
	return document.getElementById(id);
};

</script>
</head>
<script type="text/javascript">
function _SelectItemByValue(objSelect, objItemText) {
    //判断是否存在        
    //var isExit = false;        
    for (var i = 0; i < objSelect.options.length; i++) {
        if (objSelect.options[i].value == objItemText) {        
            objSelect.options[i].selected = true;
            break;
        }        
    } 
}

//根据值设置select中的选项       
function _getSelectedItemLabel(objSelect) {
    //判断是否存在        
    for (var i = 0; i < objSelect.options.length; i++) {
        if ( objSelect.options[i].selected) {
        	document.getElementById("transidea").value=objSelect.options[i].label;
            break;
        }
    }
    
    var ideacode = $("#ideacode").val();
	    if(ideacode=='B' || ideacode=='F'){//
	    	if(_get("xbArea")){
	    		_get("xbArea").style.display = "none";
	    		
	    	}
	    	if(_get("zbOrgRoleCodeTr")){
	    		_get("zbOrgRoleCodeTr").style.display = "none";
	    		
	    	}
	    	adjustHeight();
		}else{
			if(_get("xbArea")){
	    		_get("xbArea").style.display = "block";
	    		
	    	}
	    	if(_get("zbOrgRoleCodeTr")){
	    		_get("zbOrgRoleCodeTr").style.display = "block";
	    		
	    	}	    	
	    	adjustHeight();
		}
}

function _getQuickContentLabel(objSelect) {
    //判断是否存在        
    //var isExit = false;
    var radios = document.getElementsByName("quickContent");
    for (var i = 0; i < radios.length; i++) {
        if ( radios[i].checked) {
        	document.getElementById("transcontent").value= radios[i].value;
            break;
        }
    }
}

</script>

<body class="sub-flow">
	
		<input type="hidden" id="isReady" name="isReady" />

		<input type="hidden" id="moduleCode" name="moduleCode"
			value="${moduleCode}" />
		<input type="hidden" id="isDelete" name="isDelete"
			value="${isDelete }" />

		<h3 class="sub-flow-title">
			办理信息
			<c:if test="${not empty nodename}">-${nodename}</c:if>

			<!-- 通用运行模块维护入口 开始 -->
			<c:if
				test="${cp:CHECKUSEROPTPOWER('generalModuleParam', 'real-time-maintain') }">
				<img src='${contextPath }/styles/images/menu/page.gif' width="20px"
					title="维护通用运行模块" height="20px" id="edit-module" />
			</c:if>
			<!-- 通用运行模块维护入口 结束 -->

		</h3>
		<table border="0" cellpadding="0" cellspacing="0" id="tb"
			class="viewTable" style="margin-top: 20px;">

			<c:if test="${moduleParam.hasIdea eq 'T' }">
				<tr id="tr_ideacode">
					<td class="addTd" width="13%">${moduleParam.ideaLabel}<font
						color="red">*</font></td>
					<td align="left" width="87%"><input type="hidden"
						name="transidea" value="" id="transidea"> <!--通用配置  --> <select
						id="ideacode" name="ideacode"
						onchange="_getSelectedItemLabel(this)"
						style="width: 200px; height: 25px;">
							<option VALUE="">---请选择---</option>
							<c:forEach var="row"
								items="${cp:DICTIONARY(moduleParam.ideaCatalog)}">
									<option value="${row.key}" label="${row.value}">
										<c:out value="${row.value}" />
									</option>
							</c:forEach>
					</select> &nbsp;&nbsp;</td>
				</tr>
			</c:if>

			<c:if test="${moduleParam.hasIdea ne 'T'}">
				<input type="hidden" name="ideacode" value=""
					id="ideacode">
			</c:if>

			<c:if test="${not empty moduleParam.ideaContent}">
				<tr>
					<td class="addTd" width="13%">${moduleParam.ideaContent}<font
						color="red">*</font></td>
					<td align="left" width="87%"><c:if
							test="${moduleParam.isQuickContent eq 'T'}">
							<div>
								<c:forEach var="row"
									items="${cp:LVB(moduleParam.quickContentResult)}">
									<input type="radio" id="quickContent" name="quickContent"
										value="${row.label}" onclick="_getQuickContentLabel(this);" />${row.label}&nbsp;
					</c:forEach>
							</div>
						</c:if> <s:textarea id="transcontent" name="transcontent"
							style="width:100%; height:40px;" value="%{object.transcontent}" />
					</td>
				</tr>
			</c:if>
			<c:if test="${moduleParam.hasAttention eq 'T' }">
				<tr>
					<td class="addTd" width="140">${moduleParam.attentionLabel}</td>
					<td align="left"><input type="text" id="attUserNames"
						name="attUserNames" readonly="readonly" style="width: 100%;"
						value="${attUserNames}" /> <input type="hidden"
						id="attUserCodes" name="attUserCodes" value="${attUserCodes}" />
						<input type="hidden" id="attType" name="optProcAttention.attType"
						value="1" /></td>
				</tr>
			</c:if>

			<c:if test="${moduleParam.hasZbUser eq 'T' }">
				<tr>
					<!-- 主办承办人权限变量设置 -->
					<td class="addTd" width="140">${moduleParam.zbUserLabel}<font
						color="red">*</font></td>
					<td align="left"><input type="text" id="zbUserNames"
						name="zbUserNames" style="width: 100%;" value="${zbUserNames}"
						readonly="readonly" /> <input type="hidden" id="zbUserCodes"
						name="zbUserCodes" value="${zbUserCodes}" /> <input type="hidden"
						id="zbUserRoleCode" name="zbUserRoleCode"
						value="${moduleParam.zbUserRoleCode}" /></td>
				</tr>
			</c:if>

			<c:if test="${moduleParam.hasXbUser eq 'T' }">
				<tr>
					<!-- 审核人权限变量设置 -->
					<td class="addTd" width="140">${moduleParam.xbUserLabel}<font
						color="red">*</font></td>
					<td align="left"><input type="text" id="auditUserNames"
						name="auditUserNames" style="width: 100%;"
						value="${auditUserNames}" readonly="readonly" /> <input
						type="hidden" id="auditUserCodes" name="auditUserCodes"
						value="${auditUserCodes}" /> <input type="hidden"
						id="auditUserRoleCode" name="auditUserRoleCode"
						value="${moduleParam.xbUserRoleCode}" /></td>
				</tr>

			</c:if>

			<!-- 办件角色 -->
			<c:if test="${moduleParam.assignTeamRole eq 'T' }">
				<tr id="zbOrgRoleCodeTr">
					<td class="addTd" width="140">${moduleParam.teamRoleName}<c:if
							test="${moduleParam.isTeamRoleCheck ne 'F' }">
							<font color="red">*</font>
						</c:if></td>
					<td align="left"><input type="text" id="bjUserNames"
						name="bjUserNames" style="width: 100%;" value="${bjUserNames}"
						readonly="readonly" /> <input type="hidden" id="bjCodes"
						name="teamUserCodes" value="${teamUserCodes}" /> <input
						type="hidden" id="roleCode" name="teamRoleCode"
						value="${moduleParam.teamRoleCode}" /> <!-- <input type="hidden" id="attType" name="optProcAttention.attType" value="1" /> -->
					</td>
				</tr>
			</c:if>

			<!-- 权限引擎角色 -->
			<c:if test="${moduleParam.assignEngineRole eq 'T' }">
				<tr id="zbOrgRoleCodeTr">
					<td class="addTd" width="140">${moduleParam.engineRoleName}<font
						color="red">*</font></td>
					<td align="left"><input type="text" id="engineUserNames"
						name="engineUserNames" style="width: 100%;"
						value="${engineUserNames}" readonly="readonly" /> <input
						type="hidden" id="engineCodes" name="engineUserCodes"
						value="${engineUserCodes}" /> <input type="hidden"
						id="engineRoleCode" name="engineRoleCode"
						value="${moduleParam.engineRoleCode}" /></td>
				</tr>
			</c:if>
			

			<!--通用配置批分协办  -->
				<c:if test="${moduleParam.hasOrgRole eq 'T' }">
					<c:if test="${not empty moduleParam.zbOrgRoleCode }">
						<tr>
							<td class="addTd" width="13%">主办处室</td>
							<td align="left"><input type="hidden" id="zbOrgRoleCode"
								name="zbOrgRoleCode" value="${moduleParam.zbOrgRoleCode}">
								<select id="zbOrgCode" name="zbOrgCode">
									<option value="">---请选择---</option>
									<c:forEach items="${unitList}" var="unit">
										<option value="${unit.unitcode}"
											<c:if test="${zbOrgCode == unit.unitcode}" >selected = "selected"</c:if>>
											<c:out value="${unit.unitname}" />
										</option>
									</c:forEach>
							</select></td>
						</tr>
					<tr id="xbArea">
						<td class="addTd" width="13%">${moduleParam.xbOrgRoleName}</td>
						<td><input type="hidden" id="unitsJson" name="unitsJson"
							value='${unitsJson}' /> <input type="text" id="xbOrgNames"
							name="xbOrgNames" style="width: 100%;" value="${xbOrgNames}"
							readonly="readonly" /> <input type="hidden" id="xbOrgCodes"
							name="xbOrgCodes" value="${xbOrgCodes}" /> <input type="hidden"
							id="xbOrgRoleCode" name="xbOrgRoleCode"
							value="${moduleParam.xbOrgRoleCode}" /></td>
					</tr>
				</c:if>
			</c:if>
		</table>
		
	
</body>


<script type="text/javascript">

$(document).ready(function() {
	$("#isReady").val("ok");
	
	flowStep = $("#flowPhase").val();
	nodeCode = $("#nodeCode").val();
	
	var ideacode = $("#ideacode").val();
	
	 var ideacode = $("#ideacode").val();
	    if(ideacode=='B' || ideacode=='F'){//
	    	if(_get("xbArea")){
	    		_get("xbArea").style.display = "none";
	    	}
	    	if(_get("zbOrgRoleCodeTr")){
	    		_get("zbOrgRoleCodeTr").style.display = "none";
	    	}
		}else{
			if(_get("xbArea")){
	    		_get("xbArea").style.display = "block";
	    	}
	    	if(_get("zbOrgRoleCodeTr")){
	    		_get("zbOrgRoleCodeTr").style.display = "block";
	    	}
		}
});

$("#attUserNames").click(function(){
	var s = '${attentionJson}';
	if(s.trim().length){
		window.parent.selectPopWin(jQuery.parseJSON(s),$("#attUserNames"),$("#attUserCodes"));
	}
	
	
});

$("#bjUserNames").click(function(){
	
	 var d = '${userjson}';
	 if (!d.trim().length){
		 alert("没有符合条件的人员！");
	 }
     if (d.trim().length) {
    	 //不要在这进行字符串转json对象
		selectPopWinTree(d,$("#bjUserNames"),$("#bjCodes"));
	} 
});



$("#xbOrgNames").click(function(){
	var d = '${unitsJson}';
	if(d.trim().length){
		selectPopWin(jQuery.parseJSON(d),$("#xbOrgNames"),$("#xbOrgCodes"));
	};
});

$("#zbUserNames").click(function(){
	var d = '${zbuserjson}';
	if(d.trim().length){
		selectPopWin(jQuery.parseJSON(d),$("#zbUserNames"),$("#zbUserCodes"));
	};
});


$("#auditUserNames").click(function(){
	var d = '${audituserjson}';
	if(d.trim().length){
		selectPopWin(jQuery.parseJSON(d),$("#auditUserNames"),$("#auditUserCodes"));
	};
});



$("#engineUserNames").click(function(){
	var d = '${engineUserJson}';
	if(d.trim().length){
		selectPopWin(jQuery.parseJSON(d),$("#engineUserNames"),$("#engineCodes"));
	};
});

//取消保存功能
//window.parent.hiddSaveBtn();

//受理步骤多文书操作
$("#ideacode").change(function(e){
	changeIdeacode();
					});

/**
 * 
 */
function selectPopWinTree(ja,o1,o2){
	new treePerson($.parseJSON(ja), o1, o2).init();/* 此处人员限制为一人 */
	setAlertStyle("attAlert");
}

function selectPopWin(json,o1,o2,oShow){
	new person(json,o1,o2,oShow).init();
	setAlertStyle("attAlert");
}

	//	<!-- 通用运行模块维护入口 开始 -->
	$("#edit-module")
			.click(
					function() {
						url = "${contextPath}/powerruntime/powerruntime/generalModuleParam!edit.do?moduleCode="
								+ $("#moduleCode").val()
								+ "&isAutoClose=T&r="
								+ Math.random();
						;

						var returnValue = window
								.showModalDialog(url, window,
										"dialogWidth=800px;dialogHeight=800px;scroll:yes");
						if (returnValue == "T") {
							window.location.reload();
						}
					});
//	<!-- 通用运行模块维护入口 结束 -->
</script>


<script type="text/javascript">
	

	function adjustHeight() {//自动调整页面高度
		//alert(window.parent.document.getElementById("transFrame").style.height);
		if (window.parent.document.getElementById("transFrame")) {
			window.parent.document.getElementById("transFrame").style.height = document.body.scrollHeight
					+ "px";
		}
		//alert(window.parent.document.getElementById("transFrame").style.height);	
	}
</script>


</html>