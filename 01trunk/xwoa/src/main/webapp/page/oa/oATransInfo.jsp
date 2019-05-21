
<%@page import="com.goldgrid.weboffice.TemplateService"%>
<%@ page contentType="text/html;charset=UTF-8" import="java.util.*"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
	
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>办理信息</title>
<%-- <sj:head locale="zh_CN" /> --%>
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/rtx/js/browinfo.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/rtx/js/rtxint.js"></script>

<script src="${pageContext.request.contextPath}/scripts/centitFlow/transinfo_idea.js"></script>

<script type="text/javascript">

function checkForm2(){
	if(checkForm()){
		return true;
	}else{
		openLoadIng(false);
		return false;
	}
}


function checkForm(){
	var ideacode = $.trim($('input:radio[name="ideacode"]:checked').val()); //取radio
	
	if('${moduleParam.hasIdea}' == 'T' && ideacode != undefined && trim(ideacode).length==0){
		alert("请选择${moduleParam.ideaLabel}。");
		_get('ideacode').focus();
		return false;
	}
	if('${moduleParam.btIdea}' != 'F' &&trim(_get('transcontent').value).length==0){
		alert("${moduleParam.ideaContent}不能为空。");
		_get('transcontent').focus();
		return false;
	}
	
	
	if(_get('transcontent').value.length>500){
		alert("${moduleParam.ideaContent}超出最大长度。");
		_get('transcontent').focus();
		return false;
	}
	
	if('${moduleParam.hasZbUser}' == 'T'){
		if(_get('zbUserCodes') != undefined && '${moduleParam.hasZbUser}' == 'T'
			&& !$("#tr_haszbuser").css("display")=="none"){
		if(trim(_get('zbUserCodes').value).length == 0){
			alert("${moduleParam.zbUserLabel}不能为空。");			
			return false;
		 }
		}
	
	}
	if('${moduleParam.hasXbUser}' == 'T'){
		if(_get('xbUserCodes') != undefined && '${moduleParam.hasXbUser}' == 'T'
			&& !$("#tr_hasxbuser").css("display")=="none"){
		if(trim(_get('xbUserCodes').value).length == 0){
			alert("${moduleParam.xbUserLabel}不能为空。");			
			return false;
		 }
		}
	
	}
	if('${moduleParam.isTeamRoleCheck}' != 'F' ){
		if(_get('bjCodes') != undefined && '${moduleParam.assignTeamRole}' == 'T'
				&& !$("#tr_assignTeamRole").css("display")=="none"){
			if(trim(_get('bjCodes').value).length==0){
				alert("${moduleParam.teamRoleName}不能为空。");			
				return false;
			}
		}
	}
	
	if('${moduleParam.isTeamRoleCheck}' == 'F'){
		if($("#ideacode").val() == 'T'){
			if(_get('bjCodes') != undefined && '${moduleParam.assignTeamRole}' == 'T'
					&& !$("#tr_assignTeamRole").css("display")=="none"){
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
	
	
	if (_get('curTemplateId') != undefined && $("#curTemplateId").attr("id") && '${moduleParam.hasDocument}' == 'T' ) {
		/* if ($("#ideacode").attr("type").toLowerCase() == 'hidden' || $("#ideacode").val() == 'T') {
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
		} */
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
		var nodeCode = $("#nodeCode").val();
		var variableName = "ishq";
		if ("xtcfb" == nodeCode) {
			variableName = "iswbhq";
		} else if ("wbhb" == nodeCode) {
			variableName = "ishbhq";
		}
		if ($("#" + variableName).attr("name")) {
			if ("" != $.trim($("#xbOrgCodes").val())) {
				$("#" + variableName).val("T");
			} else {
				$("#" + variableName).val("F");
			}
		} else {
			if ("" != $.trim($("#xbOrgCodes").val())) {
				$("<input type='hidden' id='" + variableName + "' name='" + variableName + "' value='T' />").insertAfter("#ideacode");
			} else {
				$("<input type='hidden' id='" + variableName + "' name='" + variableName + "' value='F' />").insertAfter("#ideacode");
			}
		}	
	}
	
	var ideacode = $("#ideacode").val();
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
	var isApprovalForm = false;
	
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
function dochange(){
	var value = $("#ideacode").val();
	if(value=='bgs'){
		_get("qb_ngrcl").style.display = "block";

	}else{
		_get("qb_ngrcl").style.display = "none";

	}
	
}
</script>
</head>
<script type="text/javascript">
//var optBaseInfoJson = ${optBaseInfoJson};


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
	//alert(curTemplateId);
	if (curTemplateId != null && curTemplateId != '') {
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
	archiveType = archiveType ? archiveType : $("#archiveType").val();
	if(archiveType == null || archiveType == ''){
		archiveType = 'zw';
	}
	var curTemplateId = document.getElementById("curTemplateId").value;
	if (curTemplateId != "" && curTemplateId != null) {
		var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
		var flowStep = "ZW_EDIT";
		flowStep = getFlowStepByArchiveType(flowStep, archiveType);
		var param = "flowStep=" + flowStep + "&RecordID=${djId}&EditType=2,1&ShowType=1&Template=" + curTemplateId+"&archiveType="+archiveType+ "&nodeCode=" + $("#nodeCode").val();
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
/* //受理步骤多文书操作
$('input:radio[name="ideacode"]').click(function(e){
	alert(1);
		
					}); */
</script>

<body class="sub-flow">

 <div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">办理信息</div>
	     <hr class="title-split-line" style="position: absolute;width: 100%;height: 1px;border:none;border-top:1px solid #CCC;padding: 0!important;top:10px;z-index:0"/>
</div>

	<s:form action="oATasksExcute" method="post" namespace="/oa"
		id="ioDocTasksForm" target="_parent" onsubmit="return checkForm2();">
		<input type="hidden" id="djId" name="djId" value="${djId}" />
		<%-- <input type="hidden" id="wfcode" name="wfcode" value="${wfcode}" /> --%>
		<input type="hidden" id="flowInstId" name="flowInstId"
			value="${flowInstId}" />
		<input type="hidden" id="nodeInstId" name="nodeInstId"
			value="${nodeInstId}" />
		<input type="hidden" id="flowPhase" name="flowPhase"
			value="${flowPhase}" />
		<input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}" />
		<input type="hidden" id="nodeType" name="nodeType" value="${nodeType}" />
		<input type="hidden" id="isReady" name="isReady" />

		<input type="hidden" id="moduleCode" name="moduleCode"
			value="${moduleCode}" />
		<input type="hidden" id="isDelete" name="isDelete"
			value="${isDelete }" />

		<%-- <h3 class="sub-flow-title">
			办理信息
			<c:if test="${not empty nodename}">-${nodename}</c:if>

			<!-- 通用运行模块维护入口 开始 -->
			<c:if
				test="${cp:CHECKUSEROPTPOWER('generalModuleParam', 'real-time-maintain') }">
				<img src='${contextPath }/styles/images/menu/page.gif' width="20px"
					title="维护通用运行模块" height="20px" id="edit-module" />
			</c:if>
			<!-- 通用运行模块维护入口 结束 -->

		</h3> --%>
		<table border="0" cellpadding="0" cellspacing="0" id="tb"
			class="viewTable" style="margin-top: 20px;">

			<c:if test="${moduleParam.hasIdea eq 'T' }">
				<tr id="tr_ideacode">
					<td class="addTd" width="13%">${moduleParam.ideaLabel}<font
						color="red">*</font></td>
					<td align="left" width="87%"><input type="hidden"
						name="transidea" value="" id="transidea">
						
					<!--通用配置  -->
					<c:forEach var="row" items="${cp:DICTIONARY(moduleParam.ideaCatalog)}" varStatus="status">
                                        <input type="radio"  name="ideacode"  value="${row.key}"  data-rule=${row.datadesc} lable="${row.value}"  ${(object.ideacode eq row.key or (empty object.ideacode and  status.index  eq '0')) ? 'checked = "checked"' : ''}/><c:out value="${row.value}" />
					</c:forEach>           
					
					 &nbsp;&nbsp;</td>
				</tr>
			</c:if>

			<c:if test="${moduleParam.hasIdea ne 'T'}">
				<input type="hidden" name="ideacode" value="${object.ideacode}"
					id="ideacode">
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
				<tr id="tr_haszbuser">
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
				<tr id="tr_hasxbuser">
					<!-- 审核人权限变量设置 -->
					<td class="addTd" width="140">${moduleParam.xbUserLabel}<font
						color="red">*</font></td>
					<td align="left"><input type="text" id="xbUserNames"
						name="xbUserNames" style="width: 100%;"
						value="${xbUserNames}" readonly="readonly" /> <input
						type="hidden" id="xbUserCodes" name="xbUserCodes"
						value="${xbUserCodes}" /> <input type="hidden"
						id="xbUserRoleCode" name="xbUserRoleCode"
						value="${moduleParam.xbUserRoleCode}" /></td>
				</tr>

			</c:if>

			<!-- 办件角色 -->
			<c:if test="${moduleParam.assignTeamRole eq 'T' }">
				<tr id="tr_assignTeamRole">
					<td class="addTd" width="140" id="zbTeamLabel">${moduleParam.teamRoleName}<c:if
							test="${moduleParam.isTeamRoleCheck ne 'F' }">
							<font color="red">*</font>
						</c:if></td>
					<td align="left"><input type="text" id="bjUserNames"
						name="bjUserNames" style="width: 100%;" value="${bjUserNames}"
						readonly="readonly" /> <input type="hidden" id="bjCodes"
						name="teamUserCodes" value="${teamUserCodes}" /> <input
						type="hidden" id="roleCode" name="teamRoleCode"
						value="${moduleParam.teamRoleCode}" />
					</td>
				</tr>
			</c:if>

			<!-- 权限引擎角色 -->
			<c:if test="${moduleParam.assignEngineRole eq 'T' }">
				<tr id="tr_assignEngineRole">
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
						<tr class="tr_hasorgrole">
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
					</c:if>
					<tr class="tr_hasorgrole">
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
		     
		     
			<input type="hidden" id="curTemplateId" name="curTemplateId"
				value="${object.recordId}" />

			<c:if test="${moduleParam.hasDocument eq 'T' }">
				<tr>
					<td class="addTd" width="140">${moduleParam.documentLabel}<span
						style="color: red;">*</span></td>
					<td align="left"><input type="hidden" id="archiveType"
						name="archiveType" value="${object.archiveType}" /> <span
						id="addDoc"> <select id="recordId" name="recordId"
							onchange="openTemplate(this.value,'${moduleParam.documentType}');">
								<option value="">---请选择---</option>
								<c:forEach var="temp" items="${templateList}">
									<option value="${temp.recordId},${temp.tempType}"
										<c:if test="${object.recordId==temp.recordId}">selected="selected"</c:if>>
										<c:out value="${temp.descript}" />
									</option>
								</c:forEach>
						</select>
					</span> <span id="zwedit" style="display: none;"> <span id="fwname"></span>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="editdc" onclick="updtDoc();"
							value="修改正文" class="btn processBtn" /> <!-- 
								<input type="button" id="deletedc" onclick="delDoc();" value="删除正文" class="btn flowBtn" />
							
								 -->
					</span> <script type="text/javascript">
								var curTemplateId = document.getElementById("curTemplateId").value;
								//alert(curTemplateId);
								if(curTemplateId != null && curTemplateId != ''){
									document.getElementById("zwedit").style.display="";
									document.getElementById("addDoc").style.display="none";
									
									var textt = $.ajax({
												url: "${contextPath}/powerruntime/optApply!getTransaffairnameByDjId.do?djId=${djId}",
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
					<td class="addTd" width="140">文书查看</td>
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
// 						var ideacode = $("#ideacode").val();
						var ideacode = $.trim($('input:radio[name="ideacode"]:checked').val()); //取radio
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
					<td class="addTd" width="140">办理文书</td>
					<td align="left"><c:forEach var="temp"
							items="${templateFileList}">
							<div id="${temp.tempType}" archiveType="${temp.tempType}"
								fileName="${temp.descript}33">
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
									<span id="templateName_${temp.tempType}">${fn:length(temp.descript)
										> 8 ? (fn:substring(temp.descript, 0, 8) + '...') :
										temp.descript}.doc</span>&nbsp;&nbsp;&nbsp;&nbsp; <input
									type="button" id="viewTemplate_${temp.tempType}"
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
				<img class="wxtx_img" src="${pageContext.request.contextPath}/newStatic/image/wxtx.png" /> <span class="wxtx_span" >温馨提醒：</span><span class="wxtx_count"> ${moduleParam.tips}</span>
				</td>
			 </tr>
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
				onclick="setSubmitSrcElement('submitBtn')" />
			<c:if test="${moduleParam.canDefer eq 'T' }">
				<s:submit id="suspendBtn" name="suspendBtn"
					method="suspendNodeInstance" cssClass="btn" value="暂 缓"
					onclick="setSubmitSrcElement('suspendBtn')" />
			</c:if>
			<c:if test="${moduleParam.canRollback eq 'T' }">
				<input type="button" id="rollBackBtn" name="rollBackBtn" value="回 退"
					onclick="rollBackOpt();" />
			</c:if>
			<c:if test="${moduleParam.canClose eq 'T' }">
				<s:submit id="endFlowBtn" name="endFlowBtn" method="endFlow"
					cssClass="btn" value="办 结"
					onclick="setSubmitSrcElement('endFlowBtn')" />
			</c:if>
			<s:submit id="saveBtn" name="saveBtn" method="saveOpt" cssClass="btn"
				value="保 存" onclick="setSubmitSrcElement('saveBtn')" />
			<input id="backBtn" name="backBtn" type="button" value="返回"
				class="btn" onclick="window.history.go(-1);" />
		</center>
	</s:form>
</body>
<c:if test="${moduleParam.hasStuff eq 'T' }">
	<script type="text/javascript">
var url='<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${djId}&stuffInfo.nodeInstId=${nodeInstId}&stuffInfo.groupid=${moduleParam.stuffGroupId}&isDelete=${isDelete}';
var obj = document.getElementById("basicsj");
obj.src = url;
obj.onload = function(){
	obj.style.height = window.frames['sjfj'].document.body.scrollHeight+"px";
};
</script>
</c:if>

<script type="text/javascript">
var submitSrcElement = null;
function setSubmitSrcElement(srcElementId) {
	submitSrcElement = srcElementId;
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
oOrgUser["transcontent"] = $("#transcontent");
oOrgUser["engineRoleCode"] = $("#engineRoleCode");
oOrgUser["engineUserNames"] = $("#engineUserNames");
oOrgUser["nodeCode"] = $("#nodeCode");

/*主办承办人*/
oOrgUser["zbUserRoleCode"] = $("#zbUserRoleCode");
oOrgUser["zbUserCodes"] = $("#zbUserCodes");
oOrgUser["zbUserNames"] = $("#zbUserNames");

/*审核人*/
oOrgUser["xbUserRoleCode"] = $("#xbUserRoleCode");
oOrgUser["xbUserCodes"] = $("#xbUserCodes");
oOrgUser["xbUserNames"] = $("#xbUserNames");


$(document).ready(function() {
	$("#isReady").val("ok");

	 adjustHeight();
});






$("#attUserNames").click(function(){
	var s = '${attentionJson}';
	if(s.trim().length){
		window.parent.selectPopWin(jQuery.parseJSON(s),$("#attUserNames"),$("#attUserCodes"));
	}
	
	
});

$("#bjUserNames").click(function(){
	 var d = '${userjson}';
     if (d.trim().length) {
    	 //不要在这进行字符串转json对象
		window.parent.selectPopWinTree(d,$("#bjUserNames"),$("#bjCodes"), oOrgUser);
	} 
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
		window.parent.selectPopWinTree(d,$("#zbUserNames"),$("#zbUserCodes"), oOrgUser);
	};
});


$("#xbUserNames").click(function(){
	var d = '${xbuserjson}';
	if(d.trim().length){
		window.parent.selectPopWinTree(d,$("#xbUserNames"),$("#xbUserCodes"), oOrgUser);
	};
});



$("#engineUserNames").click(function(){
	var d = '${engineUserJson}';
	if(d.trim().length){
		window.parent.selectPopWin(d,$("#engineUserNames"),$("#engineCodes"), oOrgUser);
	};
});

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
	function getOptBaseInfoJson() {
		return getOptCommonBizJson();
	}

	function getOptCommonBizJson() {
		return ${optCommonBizJson};
	}
	function getOptProcInfoJSON1() {
		return getOptProcInfoJSON();
	}

	function getOptProcInfoJSON() {
		return ${optProcInfoJSON};

	}
	function reinitIframe(iframe) {
		try {

			var bHeight = iframe.contentWindow.document.body.scrollHeight;

			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;

			var height = Math.max(bHeight, dHeight);

			iframe.height = height;
			//$(parent.document.getElementById("foot")).css({"bottom":"0px"});
		} catch (ex) {
		}

	}

	function adjustHeight() {//自动调整页面高度
		//alert(window.parent.document.getElementById("transFrame").style.height);
		if (window.parent.document.getElementById("transFrame")) {
			window.parent.document.getElementById("transFrame").style.height = document.body.scrollHeight
					+ "px";
		}
		//alert(window.parent.document.getElementById("transFrame").style.height);	
	}
</script>

    <script type="text/javascript"> 
        //声明一个对象的引用变量可能子页面调用
        var module;
       $(function(){
            module = $.require("transinfoIdea");  
            module.init();
       });
    </script>
</html>