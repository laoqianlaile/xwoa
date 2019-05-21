<!DOCTYPE html>
<%@page import="com.goldgrid.weboffice.TemplateService"%>
<%@ page contentType="text/html;charset=UTF-8" import="java.util.*"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>办理信息</title>
<%-- <sj:head locale="zh_CN" /> --%>
<!-- 新样式文件 -->
<link
	href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">

function checkForm(){
	var ideacode = $.trim($('input:radio[name="ideacode"]:checked').val()); //取radio
		
	if('${moduleParam.hasIdea}' == 'T' && ideacode != undefined && trim(ideacode).length==0){
		alert("请选择${moduleParam.ideaLabel}。");
// 		_get('ideacode').focus();
		return false;
	}
	
	if('${moduleParam.btIdea}' != 'F' &&trim(_get('transcontent').value).length==0){
		alert("${moduleParam.ideaContent}不能为空。");
		_get('transcontent').focus();
		return false;
	}
	
	if(_get('transcontent').value.length>500){
		alert("${moduleParam.ideaContent}超出最大长度");
		_get('transcontent').focus();
		return false;
	}
	
	//协办部门
	if (_get('xbArea') != undefined && '${moduleParam.hasOrgRole}' == 'T'
				&& $("#xbOrgNames").val() == '' && !$("#xbArea").is(":hidden")) {
			alert("${moduleParam.xbOrgRoleName}不能为空。");
			return false;
		}
/* 	alert(_get('zbengineRoleCodeTr'));
	alert('${moduleParam.hasOrgRole}');
	alert($("#engineUserNames").val());
	alert(!$("#zbengineRoleCodeTr").is(":hidden"));
	alert('${moduleParam.engineRoleName}'); */
		//权限引擎
		if (_get('zbengineRoleCodeTr') != undefined && '${moduleParam.assignEngineRole}' == 'T'
				&& $("#engineUserNames").val() == ''
				&& !$("#zbengineRoleCodeTr").is(":hidden")) {
			alert("${moduleParam.engineRoleName}不能为空。");
			return false;
		}

		//办件角色
		if (_get('zbOrgRoleCodeTr') != undefined
				&& '${moduleParam.assignTeamRole}' == 'T'
				&& $("#bjUserNames").val() == ''
				&& !$("#zbOrgRoleCodeTr").is(":hidden")) {
			if (trim(_get('bjUserNames').value).length == 0 || trim(_get('bjCodes').value).length == 0 ) {
				alert("${moduleParam.teamRoleName}不能为空。");
				return false;
			}
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
	
		
	if ($("#curTemplateId").attr("id") && '${moduleParam.hasDocument}' == 'T' ) {
		if (getIdeacodeStyle().toLowerCase() == 'hidden' ||  ideacode== 'T') {
			try {
				var result = $.ajax({
							url: "${contextPath}/dispatchdoc/ioDocTasksExcute!checkArchiveTypesByDjId.do?djId=${djId}&archiveType=" + $("#archiveType").val(),
							async: false
						}).responseText;
				if ("none" == result) {
					alert("${moduleParam.documentLabel}不能为空。");
					return false;
				}
			} catch (e) {
				alert("未获取到文书相关信息");
				return false;
			}
		}
	}
	
	if ('${templateFromNode}' == 'TRUE' && '${templateFromNode}' != 'T') {
		var showDocNum = 0;
		var docs = $("a[onclick^=openDocNodeEdit]");
		if (docs && docs.length) {
			for (var i=docs.length-1; i>=0; i--) {
				if (!$(docs[i]).parent().is(":hidden")) {
					var result = $.ajax({
						url: "${contextPath}/dispatchdoc/ioDocTasksExcute!checkArchiveTypesByDjId.do?djId=${djId}&archiveType=" + $(docs[i]).parent().attr("archiveType"),
						async: false
					}).responseText;
					if ("none" == result) {
						alert("请保存文书【" + $(docs[i]).parent().attr("fileName") + "】");
						return false;
					}
				}
			}
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
			
	}
	
// 	var ideacode = $("#ideacode").val();
	
// 	var curTemplateId = $("#curTemplateId").val();
	if (("sl" == ideacode || "nsl" == ideacode) && ($("div #sl")[0] && $("div #nsl")[0])) {
		var result = "";
		try {
			result = $.ajax({
						url: "${contextPath}/dispatchdoc/ioDocTasksExcute!checkArchiveTypesByDjId.do?djId=${djId}&archiveType=" + ideacode,
						async: false
					}).responseText;
		} catch (e) {
			alert("未取得当前文书信息，请联系管理员。");
		}
		if ("exist" != result) {
			alert("请保存办理文书。");
			return false;
		}
	}
	
	var flowPhase = $("#flowPhase", window.parent.document).val();
	
	var isEditForm = false;	
	
	if(window.parent.frames['editFrame'] && window.parent.frames['editFrame'].document.forms[0]){
		isEditForm = true;
		if ("nw" == flowPhase) {
			if (!window.parent.frames["editFrame"].window.doSubmit()) {
				return false;
			}
		} else {
			var frame = window.parent.frames['editFrame'];
			if (!frame.window.doCheck()) {
				return false;
			}
		}
	}
	
	
	if (isEditForm && window.parent.frames['editFrame'].document.forms[0].submit());	
// 	submitBizData();
	return true;
}

var getIdeacodeStyle = function(){
	var ev =$("#ideacode").attr("type");
	if(ev==null){
		return "";
	}else{
		return ev;
	}
};
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
function dochange(){
	var value =$.trim($('input:radio[name="ideacode"]:checked').val()) ;
	if(value=='bgs'){
		_get("qb_ngrcl").style.display = "table-row";

	}else{
		_get("qb_ngrcl").style.display = "none";

	}
	
}
</script>
</head>
<script type="text/javascript">
//var optBaseInfoJson = ${optBaseInfoJson};
function _SelectItemByValue(objSelect, objItemText) {
    //判断是否存在        
    //var isExit = false;        
    for (var i = 0; i < objSelect.options.length; i++) {
        if (objSelect.options[i].value == objItemText) {        
            objSelect.options[i].selected = true;
            //isExit = true;
            break;
        }        
    } 
}

function openTemplate(val,documentType){
	if(val  == '' || val == null){
		return;
	}
	var tempArr =val.split(',');
	var recordId=tempArr[0];
	var tempType=tempArr[1];
	
	openDocEdit(recordId,documentType);
}


//选择模版上传文档
function openDocEdit(val,archiveType){
	_get('archiveType').value = archiveType;
	var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
	var docAction = "ZW_EDIT";
	var curTemplateId = document.getElementById("curTemplateId").value;
	if (curTemplateId != null && curTemplateId != val) {
		if(window.confirm("重新选择模板会生成新的文件，已保存的内容将被覆盖，是否确定？")){
			docAction = "ZW_ADD";
		}	
	}
	docAction = getFlowStepByArchiveType(docAction, archiveType);
		
	var param = "flowStep=" +  docAction +"&RecordID=${djId}&Template=" + val +"&archiveType="+archiveType+ "&nodeCode=" + $("#nodeCode").val()
	 			+"&NeedBookMark=1";
	
	openNewWindow(uri + "?"+ param,'editForm','');
}

//修改文档
function updtDoc(archiveType){
	var nodeCode = $("#nodeCode").val();
	archiveType = archiveType ? archiveType : $("#archiveType").val();
	if(archiveType == null || archiveType == ''){
		archiveType = 'zw';
	}
	var curTemplateId = document.getElementById("curTemplateId").value;
	if (curTemplateId != "" && curTemplateId != null) {
		var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
		var flowStep = "ZW_EDIT";
		flowStep = getFlowStepByArchiveType(flowStep, archiveType);
		//清稿阶段--印刷
		if(nodeCode=='ys'){
			flowStep = "PRINT_EDIT";
		}
		var param = "flowStep=" + flowStep + "&RecordID=${djId}&EditType=2,1&ShowType=1&Template=" + curTemplateId+"&archiveType="+archiveType+"&fileStyle=2"+ "&nodeCode=" + $("#nodeCode").val();
		openNewWindow(uri + "?"+ param,'editForm','');
	} else {
		alert("请生成您需要的文书！！");
	}
}

function delDoc(){
	if(window.confirm("确认删除当前正文？")){
		var djId=$("#djId").val();
		var archiveType = $("#archiveType").val();
		var nodeInstId = $("#nodeInstId").val();
		$.ajax({
			type : "POST",
			url : "generalOperator!deleteStuffByArchiveType.do?djId="+djId +"&archiveType="+archiveType+"&nodeInstId="+nodeInstId,
			success : function(data) {
				alert("删除成功");
			},
			error : function() {
				alert("失败");
			}
		}); 
		$("#addDoc").show();
		$("#zwedit").hide();
		$("#recordId").attr("value","");
		//$("#archiveType").attr("value","");
		$("#curTemplateId").attr("value","");
	}
}

function updtDNDoc(archiveType){
	var dnTemplateId = document.getElementById("dnTemplateId").value;
	if (dnTemplateId != "" && dnTemplateId != null) {
		var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
		var param = "flowStep=SQ_EDIT&RecordID=${object.djId}&EditType=2,1&ShowType=1&Template=1386585483609&archiveType=" + archiveType;
		openNewWindow(uri + "?"+ param,'editForm','');
	} else {
		alert("请生成您需要的文书！！");
	}
}

//查看公文
function viewDoc(archiveType,templateId){
	var curTemplateId = document.getElementById("curTemplateId").value;
	var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
	var param = "flowStep=VIEW&RecordID=${djId}&EditType=4,0&ShowType=1&archiveType="+archiveType+"&Template=" + templateId+ "&nodeCode=" + $("#nodeCode").val();
	openNewWindow(uri + "?"+ param,'viewForm','');
}




//根据值设置select中的选项       
function _getSelectedItemLabel(objSelect) {
    //判断是否存在        
    //var isExit = false;
//   $("#transidea").val($.trim($('input:radio[name="ideacode"]:checked').attr("lable")));
   initCommon();
    var parentObj = window.parent.document.getElementById("transFrame");
    if(parentObj){
	    var h =  parseInt(document.getElementById("ioDocTasksForm").scrollHeight)+50;	    
	    parentObj.height = h+"px"; 
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

//保存业务数据，在提交办理的时候，同时提交
function submitBizData(){
	if(window.parent.frames['viewFrame'].document.forms[0] != undefined){
		window.parent.frames['viewFrame'].document.forms[0].submit();
	}
}


//从流程节点配置中加载文书模版 
function openDocNodeEdit(val,archiveType,codeCode){
	var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
	flowStep = getFlowStepByArchiveType(flowStep, archiveType);
	var param = "flowStep=" + flowStep + "&RecordID=${djId}&Template=" + val +"&archiveType="+archiveType + "&nodeCode=" + $("#nodeCode").val()
	 			+"&NeedBookMark=1&codeCode="+codeCode+"&primaryUnit=${session.SPRING_SECURITY_CONTEXT.authentication.principal.primaryUnit}";	
	openNewWindow(uri + "?"+ param,'editForm','');
}

function getFlowStepByArchiveType(flowStep, archiveType) {
	if ("sl" == archiveType) {
		flowStep = "SL_UP";
	} else if ("nsl" == archiveType) {
		flowStep = "NSL_UP";
	} else if ("bz" == archiveType) {
		flowStep = "BZ_UP";
	}
	return flowStep;
}

function rollBackOpt() {
	if ($.trim($("#transcontent").val())) {
		var subUrl = "${contextPath}/dispatchdoc/ioDocTasksExcute!rollBackOpt.do";
		document.forms[0].action = subUrl;
		document.forms[0].submit();
	} else {
			alert("${moduleParam.ideaContent}不能为空。");
			$("#transcontent").focus();
		return false;
	}
}


</script>

<body class="sub-flow">
	<div class="group-title">
		<div class="title-ico"></div>
		<div class="title-name">办理信息</div>
		<hr class="title-split-line"
			style="position: absolute; width: 95%; height: 1px; border: none; border-top: 1px solid #CCC; padding: 0 !important; top: 10px; z-index: 0" />
	</div>
	<s:form action="ioDocTasksExcute" method="post"
		namespace="/dispatchdoc" id="ioDocTasksForm" target="_parent"
		onsubmit="return checkForm();">
		<input type="hidden" id="djId" name="djId" value="${djId}" />
		<input type="hidden" id="wfcode" name="wfcode" value="${wfcode}" />
		<input type="hidden" id="flowInstId" name="flowInstId"
			value="${flowInstId}" />
		<input type="hidden" id="nodeInstId" name="nodeInstId"
			value="${nodeInstId}" />
		<input type="hidden" id="flowPhase" name="flowPhase"
			value="${flowPhase}" />
		<input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}" />
		<input type="hidden" id="nodeType" name="nodeType" value="${nodeType}" />
		<input type="hidden" id="isReady" name="isReady" />
		<input type="hidden" id="ishq" name="ishq" value="${object.ishq}" />

		<input type="hidden" id="moduleCode" name="moduleCode"
			value="${moduleCode}" />

		<!-- 新布局开始 -->
		<%-- <div class="div_wrapper">
				<div class="score"  id="bwd">
					<h5>
					<label class="line"></label>办理信息
					<!-- 通用运行模块维护入口 开始 -->
					<c:if
						test="${cp:CHECKUSEROPTPOWER('generalModuleParam', 'real-time-maintain') }">
						<img src='${contextPath }/styles/images/menu/page.gif'
							width="20px" title="维护通用运行模块" height="20px" id="edit-module" />
					</c:if>
					<!-- 通用运行模块维护入口 结束 -->
				</h5>
				</div>
		</div> --%>

		<%-- <fieldset>
					<legend>办理信息
					<!-- 通用运行模块维护入口 开始 -->
					<c:if
						test="${cp:CHECKUSEROPTPOWER('generalModuleParam', 'real-time-maintain') }">
						<img src='${contextPath }/styles/images/menu/page.gif'
							width="20px" title="维护通用运行模块" height="20px" id="edit-module" />
					</c:if>
					<!-- 通用运行模块维护入口 结束 -->
					</legend>
					
				</fieldset>	 --%>

		<table border="0" cellpadding="0" cellspacing="0" id="tb"
			class="viewTable" style="margin-top: 20px; width: 95%">
			<c:if test="${moduleParam.hasIdea eq 'T' }">
				<tr id="tr_ideacode">
					<td class="addTd" width="14%">${moduleParam.ideaLabel}<font
						color="red">*</font></td>
					<td align="left" width="86%"><input type="hidden"
						name="transidea" value="" id="transidea"> <!--签报流程拟稿人处理环节特殊处理  -->
						<c:if test="${ nodeCode eq 'qb_bgrcl'}">
							<c:forEach var="row"
								items="${cp:DICTIONARY(moduleParam.ideaCatalog)}"
								varStatus="status">
								<input type="radio" name="ideacode" method="dochange"
									value="${row.key}" lable="${row.value}"
									${(object.ideacode eq row.key or (empty object.ideacode and  status.index  eq '0')) ? 'checked = "checked"' : ''} />
								<c:out value="${row.value}" />
							</c:forEach>
						</c:if> <!--通用配置  --> <c:if test="${ nodeCode ne 'qb_bgrcl'}">
							<!-- 当结果标签是处长核稿时，根据拟文内容判断流程时核稿还是会签，下拉框显示重新登记和（会签、核稿中的一个） -->
							<c:if test="${moduleParam.ideaCatalog eq 'XMSB_ZBSHfenzhi'}">
								<c:forEach var="row"
									items="${cp:DICTIONARY(moduleParam.ideaCatalog)}"
									varStatus="status">
									<c:if
										test="${object.ideacode eq row.datacode or row.datacode eq 'B'}">
										<input type="radio" name="ideacode"
											method="_getSelectedItemLabel" value="${row.datacode}"
											${(object.ideacode eq row.key or (empty object.ideacode and row.extracode eq 'D')) ? 'checked = "checked"' : ''} />
										<c:out value="${row.datavalue}" />
									</c:if>

								</c:forEach>
							</c:if>

							<c:if test="${moduleParam.ideaCatalog ne 'XMSB_ZBSHfenzhi'}">
								<c:forEach var="row"
									items="${cp:DICTIONARY(moduleParam.ideaCatalog)}"
									varStatus="status">
									<input type="radio" name="ideacode"
										method="_getSelectedItemLabel" value="${row.key}"
										lable="${row.value}"
										${(object.ideacode eq row.key or (empty object.ideacode and  status.index  eq '0')) ? 'checked = "checked"' : ''} />
									<c:out value="${row.value}" />
								</c:forEach>
							</c:if>
						</c:if></td>
				</tr>
			</c:if>

			<c:if test="${moduleParam.hasIdea ne 'T'}">
				<input type="hidden" name="ideacode" value="${object.ideacode}"
					id="ideacode">
			</c:if>

			<!-- 办件角色 -->
			<c:if test="${moduleParam.assignTeamRole eq 'T' }">
				<tr id="zbOrgRoleCodeTr">
					<td class="addTd" width="150">${moduleParam.teamRoleName}<c:if
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

			<!-- 办件角色2   -->
			<c:if test="${moduleParam.assignTeamRole eq 'T' }">
				<!-- 				发文处室负责人选择会签人员办件角色 -->
				<c:if
					test="${nodeCode eq 'FW_CSSH' ||nodeCode eq 'FW_BGSMS'||nodeCode eq 'FW_BGSFS'}">
					<tr id="zbOrgRoleCodeTr2" style="display: none;">
						<td class="addTd" width="150">${moduleParam2.teamRoleName}<font
							color="red">*</font></td>
						<td align="left"><input type="text" id="bjUserNames2"
							name="bjUserNames2" style="width: 100%;" value="${bjUserNames2}"
							readonly="readonly" /> <input type="hidden" id="bjCodes2"
							name="teamUserCodes2" value="${teamUserCodes2}" /> <input
							type="hidden" id="roleCode2" name="teamRoleCode2"
							value="${moduleParam2.teamRoleCode}" /> <!-- <input type="hidden" id="attType" name="optProcAttention.attType" value="1" /> -->
						</td>
					</tr>
				</c:if>
				<!-- 					办公室秘书选择 办公室负责人审核 -->
				<c:if test="${nodeCode eq 'FW_CSSH' || nodeCode eq 'FW_BGSMS'}">
					<tr id="zbOrgRoleCodeTr3" style="display: none;">
						<td class="addTd" width="150">${moduleParam3.teamRoleName}<font
							color="red">*</font></td>
						<td align="left"><input type="text" id="bjUserNames3"
							name="bjUserNames3" style="width: 100%;" value="${bjUserNames3}"
							readonly="readonly" /> <input type="hidden" id="bjCodes3"
							name="teamUserCodes3" value="${teamUserCodes3}" /> <input
							type="hidden" id="roleCode3" name="teamRoleCode3"
							value="${moduleParam3.teamRoleCode}" /> <!-- <input type="hidden" id="attType" name="optProcAttention.attType" value="1" /> -->
						</td>
					</tr>
				</c:if>
			</c:if>


			<!-- 权限引擎角色 -->
			<c:if test="${moduleParam.assignEngineRole eq 'T' }">
				<tr id="zbengineRoleCodeTr">
					<td class="addTd" width="150">${moduleParam.engineRoleName}<font
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
			<c:if test="${ nodeCode ne 'qb_bgrcl' }">
				<c:if test="${moduleParam.hasOrgRole eq 'T' }">
					<c:if test="${not empty moduleParam.zbOrgRoleCode }">
						<tr>
							<td class="addTd" width="14%">主办处室</td>
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
					</c:if>
					<tr id="xbArea">
						<td class="addTd" width="14%">${moduleParam.xbOrgRoleName}<font color="#ff0000">*</font></td>
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
			
          
		     
			<c:if test="${not empty moduleParam.ideaContent}">
				<tr>
					<td class="addTd" width="14%">${moduleParam.ideaContent}
					<c:if
							test="${moduleParam.btIdea ne 'F'}">
					<font
						color="red">*</font></c:if></td>
					<td align="left" width="86%"><c:if
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
						<span id="transcontentMessage">&nbsp;</span>
				    </td>
				</tr>
			</c:if>
			
			  <!-- 是否发送短信	短信功能开发且配置为发送 start-->
			<c:if test="${'T' eq cp:MAPVALUE('sendMSg','isopen') and 'T' eq moduleParam.canSendMessage }">	
			<tr>
			   <td class="addTd" width="14%">短信提醒&nbsp;</td>
			   <td align="left" width="86%">
				
				<input id="isSendMsgView" class="checkboxClass" type="checkbox"
						name="isSendMsgView" <c:if test="${'T' eq object.isSendMessage }">checked="checked" </c:if> />是否短信提醒下一步操作人员
					<input type="hidden" id="isSendMessage" name="isSendMessage" value="${object.isSendMessage }" />
					
				</td>
			 </tr>
		    </c:if> 
		     <!-- 是否发送短信	短信功能开发且配置为发送 end-->
			
			<c:if test="${moduleParam.hasAttention eq 'T' }">
				<tr>
					<td class="addTd" width="150">${moduleParam.attentionLabel}</td>
					<td align="left"><input type="text" id="attUserNames"
						name="attUserNames" readonly="readonly" style="width: 100%;"
						value="${attUserNames}" /> <input type="hidden" id="attUserCodes"
						name="attUserCodes" value="${attUserCodes}" /> <input
						type="hidden" id="attType" name="optProcAttention.attType"
						value="1" /></td>
				</tr>
			</c:if>

			<c:if test="${moduleParam.hasZbUser eq 'T' }">
				<tr>
					<!-- 主办承办人权限变量设置 -->
					<td class="addTd" width="150">${moduleParam.zbUserLabel}<font
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
					<td class="addTd" width="150">${moduleParam.xbUserLabel}<font
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




			<input type="hidden" id="curTemplateId" name="curTemplateId"
				value="${object.recordId}" />

			<c:if test="${moduleParam.hasDocument eq 'T' }">
				<tr>
					<td class="addTd" width="150">${moduleParam.documentLabel}</td>
					<td align="left"><input type="hidden" id="archiveType"
						name="archiveType" value="${object.archiveType}" /> <span
						id="addDoc"> <select id="recordId" name="recordId"
							onchange="openTemplate(this.value,'${moduleParam.documentType}');">
								<option value="">---请选择---</option>
								<c:forEach var="temp" items="${templateList}">
									<option value="${temp.recordId},${temp.tempType}"
										<c:if test="${object.recordId==temp.recordId}">selected="selected"</c:if>>
										<c:out value="${temp.descript}" /></option>
								</c:forEach>
						</select>
					</span> <span id="zwedit" style="display: none;"> <span id="fwname"></span>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="editdc" onclick="updtDoc();"
							value="修改正文" class="btn processBtn" /> <!-- 
								<input type="button" id="deletedc" onclick="delDoc();" value="删除正文" class="btn flowBtn" />
							
								 -->
					</span> <script type="text/javascript">
								var curTemplateId = document.getElementById("curTemplateId").value;
								if(curTemplateId != null && curTemplateId != ''){
									document.getElementById("zwedit").style.display="";
									document.getElementById("addDoc").style.display="none";
									
									var textt = $.ajax({
												url: "${contextPath}/dispatchdoc/dispatchDoc!getDispatchDocTitleByDjId.do?djId=${djId}",
												async: false
											}).responseText;
									if ("" == textt) {
										var indexx=document.getElementById('recordId').selectedIndex ;
								        var textt=document.getElementById("recordId").options[indexx].text;
									}
									document.getElementById("fwname").innerHTML = textt + ".doc";
								}
								
							</script> <c:if test="${powerid eq 'SD370000FG-GW-0008' }">
							<span id="addDn"> <input type="hidden" id="dnTemplateId"
								name="dnTemplateId" value="${dnRecordId}" /> <input
								type="button" id="viewDn" onclick="editDN();"
								value="${'sq' eq dnArchiveType ? '送签' : '会签'}说明书"
								class="btn processBtn" />
							</span>
							<span id="dnedit" style="display: none;"> <span
								id="dnname"></span>&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
								id="viewdn" onclick="updtDNDoc('${dnArchiveType}');"
								value="修改${'sq' eq dnArchiveType ? '送签' : '会签'}说明书"
								class="btn processBtn" />
							</span>

							<script type="text/javascript">
							var curTemplateId = document.getElementById("dnTemplateId").value;
							if(curTemplateId != null && curTemplateId != ''){
								document.getElementById("dnedit").style.display="";
								document.getElementById("addDn").style.display="none";
								
								document.getElementById("dnname").innerHTML="${'sq' eq dnArchiveType ? '送签' : '会签'}说明书.doc";
							}
						</script>
						</c:if></td>
				</tr>
			</c:if>
			<c:if test="${moduleParam.docReadOnly eq 'T' }">
				<tr>
					<td class="addTd" width="150">文书查看</td>
					<td align="left"><c:if test="${templateFileList eq null}">
							&nbsp;&nbsp;未配置或者未上传文书
						</c:if> <c:if test="${templateFileList ne null}">
							<c:forEach var="temp" items="${templateFileList}">
								<div id="${temp.tempType}">
									&nbsp;&nbsp;<input type="button" id="viewdc"
										onclick="viewDoc('${temp.tempType}','${temp.recordId}');"
										value="${temp.descript}" class="btn processBtn" />
								</div>
							</c:forEach>
						</c:if></td>
				</tr>
				<script>
					/********多文书操作打印的情况，根据办理意见决定打印文书名称******/
						var ideacode = $("#ideacode").val();
						if(ideacode == 'sl'){
							$("#nsl").hide();
						}
						
						if(ideacode == 'nsl'){
							$("#sl").hide();
						}
						
						if(ideacode == 'zy'){
							$("#nzy").hide();
						}
						
						if(ideacode == 'nzy'){
							$("#zy").hide();
						}
					/********多文书操作打印的情况，根据办理意见决定打印文书名称******/
					</script>
			</c:if>


			<!-- 从流程节点配置中加载文书模版 -->
			<c:if
				test="${ templateFromNode eq 'TRUE' && moduleParam.docReadOnly ne 'T'}">
				<tr>
					<td class="addTd" width="150">办理文书</td>
					<td align="left"><c:forEach var="temp"
							items="${templateFileList}">
							<div id="${temp.tempType}" archiveType="${temp.tempType}"
								fileName="${temp.descript}">
								<a id="${temp.tempType}_add" href="javascript:void(0);"
									onclick="openDocNodeEdit('${temp.recordId}','${temp.tempType}','${temp.codeCode}');"
									class="btnA"> <span class="btn"> <c:choose>
											<c:when test="${fn:length(temp.descript) > 8}">
												<c:out value="${fn:substring(temp.descript, 0, 8)}..." />
											</c:when>
											<c:otherwise>
												<c:out value="${temp.descript}" />
											</c:otherwise>
										</c:choose>
								</span></a> <span id="templateEdit_${temp.tempType}" style="display: none;">
									<span id="templateName_${temp.tempType}">${fn:length(temp.descript) > 8 ? (fn:substring(temp.descript, 0, 8) + '...') : temp.descript}.doc</span>&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" id="viewTemplate_${temp.tempType}"
									onclick="updtDoc('${temp.tempType}');"
									value="修改${fn:length(temp.descript) > 8 ? (fn:substring(temp.descript, 0, 8) + '...') : temp.descript}"
									class="btn processBtn" />
								</span>

								<script type="text/javascript">
										var existDoc = $.ajax({
											url: "${contextPath}/powerruntime/generalOperator!existTemplate.do?djId=${djId}&archiveType=${temp.tempType}&nodeInstId=${nodeInstId}",
											async: false
										}).responseText;
										
										if ("" == existDoc) {
											alert("获取${temp.descript}失败！");
										} else if ("yes" == existDoc) {
											$("#${temp.tempType}_add").hide();
											$("#templateEdit_${temp.tempType}").show();
										}
									</script>
							</div>
						</c:forEach></td>
				</tr>
			</c:if>
			
			  <!-- 					温馨提示	 -->
			<c:if test="${not empty moduleParam.tips}">	
			<tr>
			    <td colspan="4" align="left">
				<img class="wxtx_img" src="${pageContext.request.contextPath}/newStatic/image/wxtx.png" /> <span class="wxtx_span" >温馨提示：</span><span class="wxtx_count"> ${moduleParam.tips}</span>
				</td>
			 </tr>
		    </c:if>



			<c:if test="${moduleParam.riskId !=null && moduleParam.riskId !=0}">
				<%@ include file="/page/powerruntime/optcommon/optRiskInfoForm.jsp"%>
			</c:if>

			<c:if test="${moduleParam.hasStuff eq 'T' }">
				<tr>
					<td colspan="2" style="padding-bottom: 8px;"><iframe
							id="basicsj" name="sjfj" src="" width="100%" height=""
							frameborder="no" scrolling="false" border="0" marginwidth="0"
							marginheight="0"></iframe></td>
				</tr>

			</c:if>
		</table>


		<center style="margin-top: 10px; display: none;">
			<s:submit id="submitBtn" name="submitBtn" method="submitOpt"
				cssClass="btn" value="发 送"
				onclick="setSubmitSrcElement(event,'submitBtn')" />
			<c:if test="${moduleParam.canDefer eq 'T' }">
				<s:submit id="suspendBtn" name="suspendBtn"
					method="suspendNodeInstance" cssClass="btn" value="暂 缓"
					onclick="setSubmitSrcElement(event,'suspendBtn')" />
			</c:if>
			<c:if test="${moduleParam.canRollback eq 'T' }">
				<input type="button" id="rollBackBtn" name="rollBackBtn" value="回 退"
					onclick="rollBackOpt();" />
			</c:if>
			<c:if test="${moduleParam.canClose eq 'T' }">
				<s:submit id="endFlowBtn" name="endFlowBtn" method="endFlow"
					cssClass="btn" value="办 结"
					onclick="setSubmitSrcElement(event,'endFlowBtn')" />
			</c:if>
			<s:submit id="saveBtn" name="saveBtn" method="saveOpt" cssClass="btn"
				value="保 存" onclick="setSubmitSrcElement(event,'saveBtn')" />
			<input id="backBtn" name="backBtn" type="button" value="返回"
				class="btn" onclick="window.history.go(-1);" />
		</center>
	</s:form>

</body>
<c:if test="${moduleParam.hasStuff eq 'T' }">
	<script type="text/javascript">
var url='<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${djId}&stuffInfo.nodeInstId=${nodeInstId}&stuffInfo.groupid=${moduleParam.stuffGroupId}';
var obj = document.getElementById("basicsj");
obj.src = url;
obj.onload = function(){
	obj.style.height = window.frames['sjfj'].document.body.scrollHeight+"px";
};
</script>
</c:if>

<script type="text/javascript">
var submitSrcElement = null;
function setSubmitSrcElement(e,srcElementId) {
	submitSrcElement = srcElementId;
	saveOptbtn(e);
}
function saveOptbtn(e){
    e=EventUtil.getEvent(e);
	var obj =parent.frames['view2Frame'];
	if(obj!=undefined){
		if(checkForm()){//先验证后提交
			obj.document.getElementById("dispatchDocForm").submit(); 
		}else{//跨浏览器事件对象阻止冒泡给onsubmit再次验证
			EventUtil.preventDefault(e);
		}
	}
}


var flowStep = "";
var oOrgUser = new Object();
//添加oOrgUser对象，用于在js函数Person时作为参数传递（作用主要用于页面在选择人员或机构以后拼装办理意见）
oOrgUser["zbOrgCode"] = $("#zbOrgCode");
oOrgUser["zbOrgRoleCode"] = $("#zbOrgRoleCode");
oOrgUser["xbOrgNames"] = $("#xbOrgNames");
oOrgUser["xbOrgRoleCode"] = $("#xbOrgRoleCode");
oOrgUser["bjUserNames"] = $("#bjUserNames");
oOrgUser["roleCode"] = $("#roleCode");
oOrgUser["nodeCode"] = $("#nodeCode");
oOrgUser["transcontent"] = $("#transcontent");
oOrgUser["engineRoleCode"] = $("#engineRoleCode");
oOrgUser["engineUserNames"] = $("#engineUserNames");


/*主办承办人*/
oOrgUser["zbUserRoleCode"] = $("#zbUserRoleCode");
oOrgUser["zbUserCodes"] = $("#zbUserCodes");
oOrgUser["zbUserNames"] = $("#zbUserNames");

/*审核人*/
oOrgUser["auditUserRoleCode"] = $("#auditUserRoleCode");
oOrgUser["auditUserCodes"] = $("#auditUserCodes");
oOrgUser["auditUserNames"] = $("#auditUserNames");


$(document).ready(function() {
	$("#isReady").val("ok");
	
	flowStep = $("#flowPhase").val();
	nodeCode = $("#nodeCode").val();
	
// 	var ideacode = $("#ideacode").val();
	var ideacode = $.trim($('input:radio[name="ideacode"]:checked').val()); //取radio
	
	//打印决定书节点dyjds
	if ("sl" == nodeCode || "slsh" == nodeCode || "dyjds" == nodeCode || nodeCode.indexOf("print") >= 0 || nodeCode.indexOf("dayin") >= 0) {
		hideAllAndShow(ideacode);
	}
	
	// 初始化已选择的人员或者机构
	if ("" == $.trim($("#transcontent").val())) {
		genHandleComments(oOrgUser);
	}
	
	//初始化办理页面
	initCommon();

		
		
	    	/* if(_get("transcontent")){_get('transcontent').value='';} */
});

function hideAllAndShow(ideacode) {
	$("#sl").hide();
	$("#bz").hide();
	$("#nsl").hide();
	if ("sl" == nodeCode && $("#viewdc").parent().parent().hide());
	$("#zy").hide();
	$("#nzy").hide();
	$("#" + ideacode).show();
}


// 受理，不受理，补正
function changeIdeacode() {
// 	var ideacode = $("#ideacode").val();
	var ideacode = $.trim($('input:radio[name="ideacode"]:checked').val()); //取radio
	hideAllAndShow(ideacode);
	var nodeCode = $("#nodeCode").val();
	if ("zbcsfb" == nodeCode) {
		if ("F" == ideacode) {
			$("#zbOrgRoleCodeTr").hide();
		} else {
			$("#zbOrgRoleCodeTr").show();
		}
	}
}


$("#attUserNames").click(function(){
	var s = '${attentionJson}';
	if(s.trim().length){
		
		window.parent.selectPopWin(jQuery.parseJSON(s),$("#attUserNames"),$("#attUserCodes"));
	}
});

$("#bjUserNames").click(function(){
	
	var d = '${userjson}';
	if(d.trim().length){
		// 人员选择树
		window.parent.selectPopWinTree(d,$("#bjUserNames"),$("#bjCodes"),oOrgUser);
	};
});

$("#bjUserNames2").click(function(){
	
	var d = '${userjson2}';
	if(d.trim().length){
// 		window.parent.selectPopWin(jQuery.parseJSON(d),$("#bjUserNames"),$("#bjCodes"), oOrgUser);
		// 人员选择树
		window.parent.selectPopWinTree(d,$("#bjUserNames2"),$("#bjCodes2"));
	};
});

$("#bjUserNames3").click(function(){
	
	var d = '${userjson3}';
	if(d.trim().length){
// 		window.parent.selectPopWin(jQuery.parseJSON(d),$("#bjUserNames"),$("#bjCodes"), oOrgUser);
		// 人员选择树
		window.parent.selectPopWinTree(d,$("#bjUserNames3"),$("#bjCodes3"));
	};
});


$("#zbOrgCode").change(function() {
	genHandleComments(oOrgUser);
});

$("#xbOrgNames").click(function(){
	var d = '${unitsJson}';
	if(d.trim().length){
		window.parent.selectPopWin(jQuery.parseJSON(d),$("#xbOrgNames"),$("#xbOrgCodes"), oOrgUser);
	};
});

$("#zbUserNames").click(function(){
	var d = '${zbuserjson}';
	if(d.trim().length){
		window.parent.selectPopWin(jQuery.parseJSON(d),$("#zbUserNames"),$("#zbUserCodes"), oOrgUser);
	};
});


$("#auditUserNames").click(function(){
	var d = '${audituserjson}';
	if(d.trim().length){
		window.parent.selectPopWin(jQuery.parseJSON(d),$("#auditUserNames"),$("#auditUserCodes"), oOrgUser);
	};
});



$("#engineUserNames").click(function(){
	var d = '${engineUserJson}';
	if(d.trim().length){
		window.parent.selectPopWinTree(d,$("#engineUserNames"),$("#engineCodes"), oOrgUser);
	};
});

//取消保存功能
//window.parent.hiddSaveBtn();

//受理步骤多文书操作
$('input:radio[name="ideacode"]').click(function(e){
	clickMethod=$.trim($('input:radio[name="ideacode"]:checked').attr("method"));
	
	if("dochange"==clickMethod){
		dochange();
	}else if("_getSelectedItemLabel"==clickMethod){
		_getSelectedItemLabel() ;
	}

	changeIdeacode();
	initCommon();
	var ideacode = $.trim($('input:radio[name="ideacode"]:checked').val()); //取radio
	if(ideacode == "FW"){
		if(window.confirm("此选项将进入拟发文业务。")){
			window.parent.location.href = "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!toDispatchDocFlow.do?ideacode=FW&djId=${djId}&flowCode=000392&flowInstId=${flowInstId}&nodeInstId=${nodeInstId}";
							}
						}
					});

	//页面公用加载
	function initCommon() {
	
		//个性化需求  -主席节点默认已阅----变通  通配配置非必填验证节点默认已阅  ---后面注意统一
		if('${moduleParam.btIdea}' == 'F'&&trim($('#transcontent').val()).length==0){
			$('#transcontent').val("已阅");
		}
		
		
		var str="${djId}";
		if(str.indexOf("FW")!=-1){
			initZGHFW();
		}
		if(!str.indexOf("SW")!=-1){
		initZGHSW();
		}
	}

	//市总工会发文
	function initZGHFW() {
		
		var nodeCode = $("#nodeCode").val();
		var ideacode = $.trim($('input:radio[name="ideacode"]:checked').val()); //取radio
		$("#transidea").val($.trim($('input:radio[name="ideacode"]:checked').attr("lable")));

		// 初始化 T B F T选人 选协办  B F隐藏
		if (ideacode == 'F' || ideacode == 'B') {
			if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
			}
			if (_get("xbArea")) {
				_get("xbArea").style.display = "none";
			}
		} else if (ideacode == 'T') {
			if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "table-row";
			}
			if (_get("xbArea")) {
				_get("xbArea").style.display = "table-row";
			}
		}
		//		发文开始
		// 1.FW_CSSH  部门审核
		if (nodeCode == 'FW_CSSH') {//     发文处室负责人审核 B 退回领导人  T  秘书  HQ 提交会签  HG提交核稿
			if (ideacode == 'B') {//拟稿人
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr2")) {
				_get("zbOrgRoleCodeTr2").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr3")) {
				_get("zbOrgRoleCodeTr3").style.display = "none";
				}
				if (_get("zbengineRoleCodeTr")) {
				_get("zbengineRoleCodeTr").style.display = "none";
				}
			} else if (ideacode == 'T') {//同意并提交办公室秘书审核
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "table-row";
				}
				if (_get("zbOrgRoleCodeTr2")) {
				_get("zbOrgRoleCodeTr2").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr3")) {
				_get("zbOrgRoleCodeTr3").style.display = "none";
				}
				if (_get("zbengineRoleCodeTr")) {
				_get("zbengineRoleCodeTr").style.display = "none";
				}
			} else if (ideacode == 'HQ') {//同意并提交处室会签
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr2")) {
				_get("zbOrgRoleCodeTr2").style.display = "table-row";
				}
				if (_get("zbOrgRoleCodeTr3")) {
				_get("zbOrgRoleCodeTr3").style.display = "none";
				}
				if (_get("zbengineRoleCodeTr")) {
				_get("zbengineRoleCodeTr").style.display = "none";
				}
			}
			else if (ideacode == 'HG') {//同意并提交核稿
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr2")) {
				_get("zbOrgRoleCodeTr2").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr3")) {
				_get("zbOrgRoleCodeTr3").style.display = "table-row";
				}
				if (_get("zbengineRoleCodeTr")) {
				_get("zbengineRoleCodeTr").style.display = "none";
				}
			}else if (ideacode == 'LDQF') {//同意并提交呈主席签发
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr2")) {
				_get("zbOrgRoleCodeTr2").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr3")) {
				_get("zbOrgRoleCodeTr3").style.display = "none";
				}
				if (_get("zbengineRoleCodeTr")) {
				_get("zbengineRoleCodeTr").style.display = "table-row";
				}
			}
		}

		// 2.FW_BGSMS  办公室秘书核稿
		//办公司秘书核稿  选择 是否会签   退回拟稿人  办公室负责人审核  领导审核
		if (nodeCode == 'FW_BGSMS') {//办公室秘书核稿
			if (ideacode == 'B' || ideacode == 'F') {//退回拟稿人  转部门核稿
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr2")) {
				_get("zbOrgRoleCodeTr2").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr3")) {
				_get("zbOrgRoleCodeTr3").style.display = "none";
				}

			} else if (ideacode == 'BGS') {//转办公室负责人
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr2")) {
				_get("zbOrgRoleCodeTr2").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr3")) {
				_get("zbOrgRoleCodeTr3").style.display = "table-row";
				}
			}

			else if (ideacode == 'HQ') {//补充会签部门
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr2")) {
				_get("zbOrgRoleCodeTr2").style.display = "table-row";
				}
				if (_get("zbOrgRoleCodeTr3")) {
				_get("zbOrgRoleCodeTr3").style.display = "none";
				}
			} else if (ideacode == 'LDQF') {//转领导签发
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "table-row";
				}
				if (_get("zbOrgRoleCodeTr2")) {
				_get("zbOrgRoleCodeTr2").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr3")) {
				_get("zbOrgRoleCodeTr3").style.display = "none";
				}
			} else {
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "table-row";
				}
				if (_get("zbOrgRoleCodeTr2")) {
				_get("zbOrgRoleCodeTr2").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr3")) {
				_get("zbOrgRoleCodeTr3").style.display = "none";
				}
			}
		}
		// 3.FW_BGSFS  办公室负责人复审
		if (nodeCode == 'FW_BGSFS') {//办公室负责人复审
			if (ideacode == 'T') {//转分管领导会签
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "table-row";
				}
				if (_get("zbOrgRoleCodeTr2")) {
				_get("zbOrgRoleCodeTr2").style.display = "none";
				}
			} else if (ideacode == 'F') {//转领导会签
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr2")) {
				_get("zbOrgRoleCodeTr2").style.display = "table-row";
				}
			} else if (ideacode == 'B') {
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
				}
				if (_get("zbOrgRoleCodeTr2")) {
				_get("zbOrgRoleCodeTr2").style.display = "none";
				}
			}
		}
		// 4.FW_FGLDHQ  分管领导会签
		if (nodeCode == 'FW_FGLDHQ') {//分管领导会签 
			if (ideacode == 'B' || ideacode == 'F' || ideacode == 'ZWH') {//退回秘书  退回办公室负责人 直接置文号
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
				}
				if (_get("zbengineRoleCodeTr")) {
				_get("zbengineRoleCodeTr").style.display = "none";
			    }
			} else if (ideacode == 'T') {//补充会签部门
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
				}
				if (_get("zbengineRoleCodeTr")) {
				_get("zbengineRoleCodeTr").style.display = "table-row";
			    }
			}

		}

		// 5.FW_LDQF  领导签发
		if (nodeCode == 'FW_LDQF') {//领导签发 、收文业务处室审核
			if (ideacode == 'B' || ideacode == 'T') {//退回秘书	
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
				}

			} else if (ideacode == 'F') {//阅办
				if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "table-row";
				}
			}
		}
		// 		发文结束
	}
	//市总工会收文
	function initZGHSW() {
		var nodeCode = $("#nodeCode").val();
		var ideacode = $.trim($('input:radio[name="ideacode"]:checked').val()); //取radio
		$("#transidea").val($.trim($('input:radio[name="ideacode"]:checked').attr("lable")));
		// 初始化 T B F T选人 选协办  B F隐藏
		if (ideacode == 'F' || ideacode == 'B') {
			if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "none";
			}
			if (_get("xbArea")) {
				_get("xbArea").style.display = "none";
			}
		} else if (ideacode == 'T') {
			if (_get("zbOrgRoleCodeTr")) {
				_get("zbOrgRoleCodeTr").style.display = "table-row";
			}
			if (_get("xbArea")) {
				_get("xbArea").style.display = "table-row";
			}
		}
		//		收文开始
// 		1.办公室主任批分 sw_py
	    if(nodeCode=='sw_py'){//厅办副主任批分
	        if(ideacode=='T'){//提交接收部门
	        	if (_get("xbArea")) {
	    		_get("xbArea").style.display = "table-row";
	        	}
	        	if (_get("zbOrgRoleCodeTr")) {
	    		_get("zbOrgRoleCodeTr").style.display = "none";
	        	}
	        	if (_get("zbengineRoleCodeTr")) {
	    		_get("zbengineRoleCodeTr").style.display = "none";
	        	}
	    		
	    	}else if(ideacode=='F'){//主要领导批阅
	    		if (_get("xbArea")) {
	    		_get("xbArea").style.display = "none";
	    		}
	    		if (_get("zbOrgRoleCodeTr")) {
	    		_get("zbOrgRoleCodeTr").style.display = "none";
	    		}
	    		if (_get("zbengineRoleCodeTr")) {
	    		_get("zbengineRoleCodeTr").style.display = "table-row";
	    		}
	    	}else if(ideacode=='FG'){//分管领导
	    		if (_get("xbArea")) {
	    		_get("xbArea").style.display = "none";
	    		}
	    		if (_get("zbOrgRoleCodeTr")) {
	    		_get("zbOrgRoleCodeTr").style.display = "table-row";
	    		}
	    		if (_get("zbengineRoleCodeTr")) {
	    		_get("zbengineRoleCodeTr").style.display = "none";
	    		}
	    	}
	    	else if(ideacode=='TFG'){//同时呈主席+部门
	    		if (_get("xbArea")) {
	    		_get("xbArea").style.display = "table-row";
	    		}
	    		if (_get("zbOrgRoleCodeTr")) {
	    		_get("zbOrgRoleCodeTr").style.display = "table-row";
	    		}
	    		if (_get("zbengineRoleCodeTr")) {
	    		_get("zbengineRoleCodeTr").style.display = "none";
	    		}
	    	}
	    	else{
	    		if (_get("xbArea")) {
	    		_get("xbArea").style.display = "none";
	    		}
	    		if (_get("zbOrgRoleCodeTr")) {
	    		_get("zbOrgRoleCodeTr").style.display = "none";
	    		}
	    		if (_get("zbengineRoleCodeTr")) {
	    		_get("zbengineRoleCodeTr").style.display = "none";
	    		}
	    		
	    	}
	        /* if(_get("xbOrgNames")){_get("xbOrgNames").value='';}
	    	if(_get("bjUserNames")){_get("bjUserNames").value='';}
	    	if(_get("transcontent")){_get('transcontent').value='';}
	    	if(_get("engineCodes")){_get('engineCodes').value='';} */
	        }
// 		2.市总领导批阅sw_tldpy
// 		3.分管领导批阅sw_fgldpy
		  
		  if(nodeCode=='sw_fgldpy' ){//收文_分管领导批阅节点
		    	if(ideacode=='LD' ){//提交主要领导审阅	
		    		if(_get("zbOrgRoleCodeTr")){_get("zbOrgRoleCodeTr").style.display = "none";}
		    		if(_get("xbArea")){_get("xbArea").style.display = "none";}
		    		if(_get("zbengineRoleCodeTr")){_get("zbengineRoleCodeTr").style.display = "table-row";}
					
				}else if(ideacode=='T'){//提交部门
					if(_get("zbOrgRoleCodeTr")){_get("zbOrgRoleCodeTr").style.display = "none";}
					if(_get("zbengineRoleCodeTr")){_get("zbengineRoleCodeTr").style.display = "none";}
					if(_get("xbArea")){_get("xbArea").style.display = "table-row";}
				}else if(ideacode=='LZ'){//提交分管领导以及部门收文
					if(_get("zbOrgRoleCodeTr")){_get("zbOrgRoleCodeTr").style.display = "table-row";}
					if(_get("zbengineRoleCodeTr")){_get("zbengineRoleCodeTr").style.display = "none";}
					if(_get("xbArea")){_get("xbArea").style.display = "table-row";}
				}else if(ideacode=='F'){//提交分管领导
					if(_get("zbOrgRoleCodeTr")){_get("zbOrgRoleCodeTr").style.display = "table-row";}
					if(_get("zbengineRoleCodeTr")){_get("zbengineRoleCodeTr").style.display = "none";}
					if(_get("xbArea")){_get("xbArea").style.display = "none";}
				}else{
					if(_get("zbOrgRoleCodeTr")){_get("zbOrgRoleCodeTr").style.display = "none";}
					if(_get("xbArea")){_get("xbArea").style.display = "none";}
					if(_get("zbengineRoleCodeTr")){_get("zbengineRoleCodeTr").style.display = "none";}
				}
		    
		    	/* if(_get("xbOrgNames")){_get("xbOrgNames").value='';}
		    	if(_get("bjUserNames")){_get("bjUserNames").value='';}
		    	if(_get("transcontent")){_get('transcontent').value='';}
		    	if(_get("engineCodes")){_get('engineCodes').value='';} */
		    }
// 		4.市总领导意见汇总SW_LDYJSY
		  if(nodeCode=='SW_LDYJSY' ){//收文——领导意见汇总节点
		    	if(ideacode=='F' ){//提交分管领导审阅	
		    		if(_get("zbOrgRoleCodeTr")){
					_get("zbOrgRoleCodeTr").style.display = "table-row";
		    		}
					if(_get("zbengineRoleCodeTr")){
						_get("zbengineRoleCodeTr").style.display = "none";
						}
					if(_get("xbArea")){
					_get("xbArea").style.display = "none";
					}
				}else if(ideacode=='T'){//提交部门
					 if (_get("zbOrgRoleCodeTr")) {
						 _get("zbOrgRoleCodeTr").style.display = "none";
					 }
					 if (_get("xbArea")) {
					_get("xbArea").style.display = "table-row";
					 }
					if(_get("zbengineRoleCodeTr")){_get("zbengineRoleCodeTr").style.display = "none";}
				}else if(ideacode=='LZ'){//提交分管领导以及部门收文
					 if (_get("zbOrgRoleCodeTr")) {
					_get("zbOrgRoleCodeTr").style.display = "table-row";
					 }
					 if (_get("xbArea")) {
					_get("xbArea").style.display = "table-row";
					 }
					if(_get("zbengineRoleCodeTr")){_get("zbengineRoleCodeTr").style.display = "none";}
				}else if(ideacode=='LD'){//提交主要领导
					
					if(_get("zbOrgRoleCodeTr")){_get("zbOrgRoleCodeTr").style.display = "none";}
		    		if(_get("xbArea")){_get("xbArea").style.display = "none";}
		    		if(_get("zbengineRoleCodeTr")){_get("zbengineRoleCodeTr").style.display = "table-row";}
		    		
				}else if(ideacode=='TF'){//提交领导以及部门收文
					 if (_get("zbOrgRoleCodeTr")) {
							_get("zbOrgRoleCodeTr").style.display = "table-row";
							 }
							 if (_get("xbArea")) {
							_get("xbArea").style.display = "table-row";
							 }
							if(_get("zbengineRoleCodeTr")){_get("zbengineRoleCodeTr").style.display = "none";}
						}else{
					if(_get("zbOrgRoleCodeTr")){
					_get("zbOrgRoleCodeTr").style.display = "none";}
					if(_get("xbArea")){
					_get("xbArea").style.display = "none";}
					if(_get("zbengineRoleCodeTr")){_get("zbengineRoleCodeTr").style.display = "none";}
				}	
		    	
		    	/* if(_get("xbOrgNames")){_get("xbOrgNames").value='';}
		    	if(_get("bjUserNames")){_get("bjUserNames").value='';}
		    	if(_get("transcontent")){_get('transcontent').value='';}
		    	if(_get("engineCodes")){_get('engineCodes').value='';} */
		    	
		    }
// 		5.部门收文sw_yd
		  if(nodeCode=='sw_yd' ){//发文处室负责人审核 F非本处室业务  T 提交部门
		    	if(ideacode=='F' ){//非本处室业务	
		    		if(_get("zbengineRoleCodeTr")){
					_get("zbengineRoleCodeTr").style.display = "none";
		    		}
				}else if(ideacode=='T'){//提交部门
					if(_get("zbengineRoleCodeTr")){
					_get("zbengineRoleCodeTr").style.display = "table-row";
					}
				}else{
					if(_get("zbengineRoleCodeTr")){
					_get("zbengineRoleCodeTr").style.display = "none";
					}
				}	
		    }
// 		6.文书分发sw_ff
		  if(nodeCode=='SW_ff'){//收文——收文分发节点
		    	if(ideacode=='F'|| ideacode=='B'){//提交领导审阅	
		    		if(_get("zbOrgRoleCodeTr")){
					_get("zbOrgRoleCodeTr").style.display = "table-row";
		    		}
		    		if(_get("xbArea")){
					_get("xbArea").style.display = "none";
		    		}
				}else if(ideacode=='T'){//提交部门
					if(_get("zbOrgRoleCodeTr")){
						_get("zbOrgRoleCodeTr").style.display = "none";	
					}
					if(_get("xbArea")){
					_get("xbArea").style.display = "table-row";
					}
				}else{
					if(_get("zbOrgRoleCodeTr")){
					_get("zbOrgRoleCodeTr").style.display = "none";
					}
					if(_get("xbArea")){
					_get("xbArea").style.display = "none";
					}
				}	
		    }
// 		7.部门审核sw_czsh
		  if(nodeCode=='sw_czsh'){//领导签发 、收文业务处室审核
		    	if(ideacode=='B' || ideacode=='T'){//退回秘书	
		    		if(_get("zbengineRoleCodeTr")){
					_get("zbengineRoleCodeTr").style.display = "none";
					}
					
				}else if(ideacode=='F'){//阅办
					if(_get("zbengineRoleCodeTr")){
					_get("zbengineRoleCodeTr").style.display = "table-row";
					}
				}	    
		    }
// 		8.收文办理sw_bl
		  if(nodeCode=='sw_bl'&&_get("zbengineRoleCodeTr")){//收文流程-收文办理环节： F非本处室业务  T 提交部门
		    	if(ideacode=='T'){//非本处室业务	
		    		if(_get("zbengineRoleCodeTr")){
					_get("zbengineRoleCodeTr").style.display = "none";
		    		}
				}else if(ideacode=='F'){//提交部门
					if(_get("zbengineRoleCodeTr")){
					_get("zbengineRoleCodeTr").style.display = "table-row";
					}
				}else{
					if(_get("zbengineRoleCodeTr")){
					_get("zbengineRoleCodeTr").style.display = "none";
					}
				}	
		    	/* if(_get("xbOrgNames")){_get("xbOrgNames").value='';}
		    	if(_get("bjUserNames")){_get("bjUserNames").value='';}
		    	if(_get("transcontent")){_get('transcontent').value='';}
		    	if(_get("engineCodes")){_get('engineCodes').value='';} */
		    }

		// 		收文结束
	}
	// 	<!-- 通用运行模块维护入口 开始 -->
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
// 	<!-- 通用运行模块维护入口 结束 -->
</script>


<script type="text/javascript">
	function getOptBaseInfoJson() {
		return getOptCommonBizJson();
	}

	function getOptCommonBizJson() {
		return $
		{
			optCommonBizJson
		}
		;
	}
	function getOptProcInfoJSON1() {
		return getOptProcInfoJSON();
	}

	function getOptProcInfoJSON() {
		return $
		{
			optProcInfoJSON
		}
		;

	}
	$('#isSendMsgView').click(function(){
		var $this = $(this);
		if($this.attr('checked') == 'checked'){
			$('#isSendMessage').val('T');
		}else{
			$('#isSendMessage').val('F');
		}
	});
	
	//文件摘要文本框输入字数的提示
	$("#transcontent").keyup(
			function() {
				CommonUtils.showTextAreaLimited("transcontent",
						"transcontentMessage", 150);
				return false;
			});
</script>
</html>