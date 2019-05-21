<%@page import="com.goldgrid.weboffice.TemplateService"%>
<%@ page contentType="text/html;charset=UTF-8" import="java.util.*"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>办理信息</title>
<%-- <sj:head locale="zh_CN" /> --%>
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

function checkForm(){
	
	if(trim(_get('alltranscontent').value).length==0){
		alert("批办意见不能为空。");
		_get('alltranscontent').focus();
		return false;
	}
	
	if(_get('alltranscontent').value.length>500){
		alert("批办意见超出最大长度");
		_get('alltranscontent').focus();
		return false;
	}	
	//最终整理成最后结果信息：领导批办意见，清理已经存的办件code，部门code等
	if(_get('alltranscontent').value.length>0){
		_get('transcontent').value=_get('alltranscontent').value;
		_get('zbOrgRoleCode').value='';
		_get('xbOrgCodes').value='';
		_get('yjCodes').value='';
		_get('yjCode').value='';		
		return true;
	}
	
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
    for (var i = 0; i < objSelect.options.length; i++) {
        if ( objSelect.options[i].selected) {
        	document.getElementById("transidea").value=objSelect.options[i].label;
            break;
        }
    }
   var nodeCode = $("#nodeCode").val();
   var ideacode = $("#ideacode").val();
 
    //办公司秘书核稿  选择 是否会签   退回拟稿人  办公室负责人审核  领导审核
	  if(nodeCode=='zbcsfzrsh'){//办公室秘书核稿
		    if(ideacode=='B' ){//退回拟稿人、转办公室负责人
				
				_get("zbOrgRoleCodeTr").style.display = "none";
				_get("zbOrgRoleCodeTr2").style.display = "none";
				_get("zbOrgRoleCodeTr3").style.display = "none";
				
			}else if( ideacode=='BGS'){
				_get("zbOrgRoleCodeTr").style.display = "none";
				_get("zbOrgRoleCodeTr2").style.display = "none";
				_get("zbOrgRoleCodeTr3").style.display = "block";
			}
		    
		    else if(ideacode=='HQ'){//补充会签部门
				
		    	_get("zbOrgRoleCodeTr").style.display = "none";
				_get("zbOrgRoleCodeTr2").style.display = "block";
				_get("zbOrgRoleCodeTr3").style.display = "none";
			}else if(ideacode=='LDQF'){//转领导签发
				
				_get("zbOrgRoleCodeTr").style.display = "block";
				_get("zbOrgRoleCodeTr2").style.display = "none";
				_get("zbOrgRoleCodeTr3").style.display = "none";
			}else {
				
				_get("zbOrgRoleCodeTr").style.display = "block";
				_get("zbOrgRoleCodeTr2").style.display = "none";
				_get("zbOrgRoleCodeTr3").style.display = "none";
			}	  
		    }
    if(nodeCode=='FW_BGSFZRFS'){//办公室负责人复审
	    if(ideacode=='T'){//转分管领导会签
	    document.getElementById("roleCode").value='fgwldqf';	
	    _get("zbOrgRoleCodeTr").style.display = "block";
		}else  if(ideacode=='B'){
			_get("zbOrgRoleCodeTr").style.display = "none";
		}
	    }
    
    if(nodeCode=='FW_LDHQ'){//分管领导会签 
    	if(ideacode=='B'){//退回秘书		
			_get("zbOrgRoleCodeTr").style.display = "none";
			
		}else if(ideacode=='T'){//补充会签部门
			_get("zbOrgRoleCodeTr").style.display = "block";
		}    
    }
    
    if(nodeCode=='fgwldqf' || nodeCode=='sw_czsh'){//领导签发 、收文业务处室审核
    	if(ideacode=='B' || ideacode=='T'){//退回秘书		
			_get("zbOrgRoleCodeTr").style.display = "none";
			
		}else if(ideacode=='F'){//阅办
			_get("zbOrgRoleCodeTr").style.display = "block";
		}	    
    }
    
     
    if(nodeCode=='FW_CSSH' ){//     发文处室负责人审核 B 退回领导人  T  ishq 会签 ---秘书   T  ishq！=T  秘书
    	if(ideacode=='B' ){//拟稿人		
			_get("zbOrgRoleCodeTr").style.display = "none";
			_get("zbOrgRoleCodeTr2").style.display = "none";
		}else if(ideacode=='T'){//同意并提交
			_get("zbOrgRoleCodeTr").style.display = "block";
		
		if($("#ishq").val()=='T'){//是否会签
			_get("zbOrgRoleCodeTr2").style.display = "block";
		}else{
			_get("zbOrgRoleCodeTr2").style.display = "none";
		}
		}    
    }
    if(nodeCode=='sw_yd' ){//发文处室负责人审核 F非本处室业务  T 提交部门
    	if(ideacode=='F' ){//非本处室业务	
			_get("zbOrgRoleCodeTr").style.display = "none";
		}else if(ideacode=='T'){//提交部门
			_get("zbOrgRoleCodeTr").style.display = "block";
		}else{
			_get("zbOrgRoleCodeTr").style.display = "none";
		}	
    }
    
    if(nodeCode=='SW_LDYJSY' ){//收文——领导意见汇总节点
    	if(ideacode=='F' ){//提交领导审阅	
			_get("zbOrgRoleCodeTr").style.display = "block";
			_get("xbArea").style.display = "none";
		}else if(ideacode=='T'){//提交部门
			_get("zbOrgRoleCodeTr").style.display = "none";
			_get("xbArea").style.display = "block";
		}else{
			_get("zbOrgRoleCodeTr").style.display = "none";
			_get("xbArea").style.display = "none";
		}	
    }
    
    if(nodeCode=='SW_ff'){//收文——收文分发节点
    	if(ideacode=='F'|| ideacode=='B'){//提交领导审阅	
    		if(_get("zbOrgRoleCodeTr")){
			_get("zbOrgRoleCodeTr").style.display = "block";
    		}
    		if(_get("xbArea")){
			_get("xbArea").style.display = "none";
    		}
		}else if(ideacode=='T'){//提交部门
			if(_get("zbOrgRoleCodeTr")){
				_get("zbOrgRoleCodeTr").style.display = "none";	
			}
			if(_get("xbArea")){
			_get("xbArea").style.display = "block";
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
    
    var parentObj = window.parent.document.getElementById("transFrame");
    if(parentObj){
	    var h =  parseInt(document.getElementById("ioDocTasksForm").scrollHeight)+10;
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
		<%-- <fieldset class="_new">
			<legend>
				<b>办理信息<c:if test="${not empty nodeName}">-${nodeName}</c:if></b>
				<!-- 通用运行模块维护入口 开始 -->
				<c:if test="${cp:CHECKUSEROPTPOWER('generalModuleParam', 'real-time-maintain') }"> 
				<img src='${contextPath }/styles/images/menu/page.gif' width="20px" title="维护通用运行模块"
					height="20px" id="edit-module" />
				</c:if>
				<!-- 通用运行模块维护入口 结束 -->
			</legend> --%>
			
		<div class="group-title">
			<div class="title-ico"></div>
			<div class="title-name">办理信息</div>
			<hr class="title-split-line"
				style="position: absolute; width: 100%; height: 1px; border: none; border-top: 1px solid #CCC; padding: 0 !important; top: 10px; z-index: 0" />
		</div>
		
		<table border="0" cellpadding="0" cellspacing="0" id="tb"
				class="viewTable" style="margin-top: 20px;">
				<c:if test="${moduleParam.hasIdea eq 'T' }">
					<tr id="tr_ideacode">
						<td class="addTd" width="13%">${moduleParam.ideaLabel}<font
							color="red">*</font></td>
						<td align="left" width="87%"><input type="hidden"
							name="transidea" value="" id="transidea"> 
							 <!--通用配置  --> 
							<option VALUE="">---请选择---</option> <c:forEach var="row"
								items="${cp:DICTIONARY(moduleParam.ideaCatalog)}">							
									<option value="${row.key}" label="${row.value}"
										<c:if test="${object.ideacode==row.key}">selected="selected"</c:if>>
										<c:out value="${row.value}" />
									</option>
							</c:forEach> </select> &nbsp;&nbsp;</td>
					</tr>
				</c:if>
<tr>
<td class="addTd" style="width: 150px;">公文处理方式</td>
<td class="addTd">批办意见</td>
<td class="addTd" style="width: 150px;">确定操作</td>
</tr>

<%-- <c:if test="${moduleParam.moduleCode eq 'sw_fgldpy' }">
				<!-- 办件角色  阅示-->
					<tr id="zbOrgRoleCodeTr">
						<td class="addTd" width="10%">${moduleParam.teamRoleName}<c:if
								test="${moduleParam.isTeamRoleCheck ne 'F' }">
							</c:if></td>
						<td align="left">请<input type="text" id="yjUserNames"
							name="yjUserNames" value=""
							readonly="readonly" style="width: 30%"/> <input type="hidden" id="yjCodes"
							name="teamUserCodes1" value="${teamUserCodes}" /> <input
							type="hidden" id="yjCode" name="teamRoleCode1"
							value="${moduleParam.teamRoleCode}"/>（领导）及
						<input type="text" id="ybOrgNames"
								name="ybOrgNames" value="${ybOrgNames}"
								readonly="readonly" style="width: 30%"/> <input type="hidden" id="ybOrgCodes"
								name="ybOrgCodes" value="${ybOrgCodes}" />（部门）
						<select id="ldzdx" style="width:8%;height:25px;" name="ldzdx">
							<c:forEach var="row" items="${cp:DICTIONARY('LDZDX')}">
								<option value="${row.datacode}">
									<c:out value="${row.datavalue}"/>
								</option>
							</c:forEach>
					</select>。<br>
								<s:textarea id="ybtranscontent" name="ybtranscontent"
								style="width:100%; height:37px;"/></td>
								<td align="center"><input type="button" name="procollys" id="procollys"  class="btn" onclick="procoll('procollys');" value="确定"/></td>
					</tr>
</c:if> --%>
	<!-- 办件角色  阅示-->
				<c:if test="${moduleParam.assignTeamRole eq 'T' }">
					<tr id="zbOrgRoleCodeTr">
						<td class="addTd" width="10%">${moduleParam.teamRoleName}<c:if
								test="${moduleParam.isTeamRoleCheck ne 'F' }">
							</c:if></td>
						<td align="left">请<input type="text" id="yjUserNames"
							name="yjUserNames" value=""
							readonly="readonly" style="width: 30%"/> <input type="hidden" id="yjCodes"
							name="teamUserCodes1" value="${teamUserCodes}" /> <input
							type="hidden" id="yjCode" name="teamRoleCode1"
							value="${moduleParam.teamRoleCode}"/>（领导）及
						<input type="text" id="ybOrgNames"
								name="ybOrgNames" value="${ybOrgNames}"
								readonly="readonly" style="width: 30%"/> <input type="hidden" id="ybOrgCodes"
								name="ybOrgCodes" value="${ybOrgCodes}" />（部门）
						<select id="ldzdx" style="height:25px;" name="ldzdx">
							<c:forEach var="row" items="${cp:DICTIONARY('LDZDX')}">
								<option value="${row.datacode}">
									<c:out value="${row.datavalue}"/>
								</option>
							</c:forEach>
					</select>。<br>
								<s:textarea id="ybtranscontent" name="ybtranscontent"
								style="width:100%; height:37px;"/></td>
								<td align="center"><input type="button" name="procollys" id="procollys"  class="btn" onclick="procoll('procollys');" value="确定"/></td>
					</tr>
				</c:if>
				<!--通用配置批分协办  -->
						<c:if test="${not empty moduleParam.zbOrgRoleCode }">
							<tr>
								<td class="addTd" width="13%">办理</td>
								<td align="left">请<input type="hidden" id="zbOrgRoleCode_"
									name="zbOrgRoleCode_" value="">
									<select id="zbOrgCode_" name="zbOrgCode_">
										<option value="">---请选择---</option>
										<c:forEach items="${unitList}" var="unit">
											<option value="${unit.unitcode}"
												<c:if test="${zbOrgCode == unit.unitcode}" >selected = "selected"</c:if>>
												<c:out value="${unit.unitname}" />
											</option>
										</c:forEach>
								</select>（主办部门）会同<input type="hidden" id="unitsJson" name="unitsJson"
								value='${unitsJson}' /> <input type="text" id="xbOrgNames_"
								name="xbOrgNames_"  value=""
								readonly="readonly" style="width: 30%"/> <input type="hidden" id="xbOrgCodes_"
								name="xbOrgCodes_" value="" /> <input type="hidden"
								id="xbOrgRoleCode_" name="xbOrgRoleCode_"
								value="" />（协办部门）<select id="ldzdx_" style="height:25px;" name="ldzdx_">
							<c:forEach var="row" items="${cp:DICTIONARY('BMBL')}">
								<option value="${row.datacode}">
									<c:out value="${row.datavalue}"/>
								</option>
							</c:forEach>
					</select>。<br>
								<s:textarea id="transcontent" name="transcontent"
								style="width:100%; height:37px;" value="" /></td>
								<td align="center"><input type="button" name="procoll_org" id="procoll_org"  class="btn" onclick="procoll('procoll_org');" value="确定"/></td>
						</tr>
				</c:if>

				<c:if test="${not empty moduleParam.ideaContent}">
					<tr>
						<td class="addTd" width="13%">${moduleParam.ideaContent}</td>
						<td align="left" width="87%"><c:if
								test="${moduleParam.isQuickContent eq 'T'}">
								<div>
									<c:forEach var="row"
										items="${cp:LVB(moduleParam.quickContentResult)}">
										<input type="radio" id="quickContent" name="quickContent"
											value="${row.label}" onclick="_getQuickContentLabel(this);" />${row.label}&nbsp;
					</c:forEach>
								</div>
							</c:if> <!-- 意见栏原始 --><s:textarea id="pstranscontent" name="pstranscontent"
								style="width:100%; height:27px;" />
							<span id="pstranscontentMessage"></span></td>
							<td align="center"><input type="button" name="procoll_ps" id="procoll_ps"  class="btn" onclick="procoll('procoll_ps');" value="确定"/></td>
					</tr>
				</c:if>
				<tr>
				<td class="addTd" width="13%">最终意见汇总<font color="red">*</font>
				</td>
				<td colspan="3">
				<s:textarea id="alltranscontent" name="alltranscontent"
								style="width:100%; height:49px;" />
				</span>
				</td>
				
				</tr>
				
				
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
							name="zbUserCodes" value="${zbUserCodes}" /> <input
							type="hidden" id="zbUserRoleCode" name="zbUserRoleCode"
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




				<input type="hidden" id="curTemplateId" name="curTemplateId"
					value="${object.recordId}" />

				<c:if test="${moduleParam.hasDocument eq 'T' }">
					<tr>
						<td class="addTd" width="140">${moduleParam.documentLabel}</td>
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
						</span> <span id="zwedit" style="display: none;"> <span
								id="fwname"></span>&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
								id="editdc" onclick="updtDoc();" value="修改正文"
								class="btn processBtn" /> <!-- 
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
								
							</script> 
							</td>
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
						<td class="addTd" width="140">办理文书</td>
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
									</span></a> <span id="templateEdit_${temp.tempType}"
										style="display: none;"> <span
										id="templateName_${temp.tempType}">${fn:length(temp.descript) > 8 ? (fn:substring(temp.descript, 0, 8) + '...') : temp.descript}.doc</span>&nbsp;&nbsp;&nbsp;&nbsp;
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

				<c:if test="${moduleParam.hasStuff eq 'T' }">
					<tr>
						<td colspan="2" style="padding-bottom: 8px;"><iframe
								id="basicsj" name="sjfj" src="" width="100%" height=""
								frameborder="no" scrolling="false" border="0" marginwidth="0"
								marginheight="0"></iframe></td>
					</tr>

				</c:if>
				
				 <!-- 是否发送短信	短信功能开发且配置为发送 -->
			<c:if test="${'T' eq cp:MAPVALUE('sendMSg','isopen') and 'T' eq moduleParam.canSendMessage }">	
			<tr>
				<td class="addTd" width="140">短信提醒</td>
			   <td colspan="2" align="left" style="height:30px;">
				
				<input id="isSendMsgView" class="checkboxClass" type="checkbox"
						name="isSendMsgView" <c:if test="${'T' eq object.isSendMessage }">checked="checked" </c:if> />短信提醒下一步操作人员
					<input type="hidden" id="isSendMessage" name="isSendMessage" value="${object.isSendMessage }" />
					
				</td>
			 </tr>
		    </c:if> 
		    
		    
				
				 <!-- 					温馨提示	 -->
			<c:if test="${not empty moduleParam.tips}">	
			<tr>
			    <td colspan="3" align="left">
				<img class="wxtx_img" src="${pageContext.request.contextPath}/newStatic/image/wxtx.png" /> <span class="wxtx_span" >温馨提示：</span><span class="wxtx_count"> ${moduleParam.tips}</span>
				</td>
			 </tr>
		    </c:if>
		    
		   
			</table>
<!-- 		</fieldset> -->

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
oOrgUser["ybOrgNames"] = $("#ybOrgNames");
oOrgUser["ybOrgRoleCode"] = $("#ybOrgRoleCode");
oOrgUser["xbOrgNames"] = $("#xbOrgNames");
oOrgUser["xbOrgRoleCode"] = $("#xbOrgRoleCode");
oOrgUser["yjUserNames"] = $("#yjUserNames");
oOrgUser["yjCode"] = $("#yjCode");
oOrgUser["bjUserNames"] = $("#bjUserNames");
oOrgUser["roleCode"] = $("#roleCode");
oOrgUser["nodeCode"] = $("#nodeCode");
oOrgUser["transcontent"] = $("#transcontent");
oOrgUser["ybtranscontent"] = $("#ybtranscontent");
oOrgUser["engineRoleCode"] = $("#engineRoleCode");
oOrgUser["engineUserNames"] = $("#engineUserNames");

oOrgUser["zbOrgCode_"] = $("#zbOrgCode_");
oOrgUser["zbOrgRoleCode_"] = $("#zbOrgRoleCode_");
oOrgUser["xbOrgNames_"] = $("#xbOrgNames_");
oOrgUser["xbOrgRoleCode_"] = $("#xbOrgRoleCode_");
oOrgUser["ldzdx"] = $("#ldzdx");
oOrgUser["ldzdx_"] = $("#ldzdx_");
/*主办承办人*/
oOrgUser["zbUserRoleCode"] = $("#zbUserRoleCode");
oOrgUser["zbUserCodes"] = $("#zbUserCodes");
oOrgUser["zbUserNames"] = $("#zbUserNames");

/*审核人*/
oOrgUser["auditUserRoleCode"] = $("#auditUserRoleCode");
oOrgUser["auditUserCodes"] = $("#auditUserCodes");
oOrgUser["auditUserNames"] = $("#auditUserNames");


$(document).ready(function() {
	// 初始化已选择的人员或者机构
	if ("" == $.trim($("#transcontent").val())) {
		genHandleComments(oOrgUser);
	}	  
	
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
	var ideacode = $("#ideacode").val();
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

$("#yjUserNames").click(function(){	
	var d = '${userjson}';
	if(d.trim().length){
		// 人员选择树
		//top.popUserTreeWin(d,$("#bjUserNames"),$("#bjCodes"),oOrgUser);		
		window.parent.selectPopWinTree2(d,$("#yjUserNames"),$("#yjCodes"),oOrgUser);
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


$("#zbOrgCode_").change(function() {
	genHandleComments(oOrgUser);
});
$("#ldzdx").change(function() {
	genHandleComments(oOrgUser);
});
$("#ldzdx_").change(function() {
	genHandleComments(oOrgUser);
});
$("#ybOrgNames").click(function(){
	var d = '${unitsJson}';
	if(d.trim().length){
		window.parent.selectPopWin(jQuery.parseJSON(d),$("#ybOrgNames"),$("#ybOrgCodes"), oOrgUser);
	};
});
$("#xbOrgNames_").click(function(){
	var d = '${unitsJson}';
	if(d.trim().length){
		window.parent.selectPopWin(jQuery.parseJSON(d),$("#xbOrgNames_"),$("#xbOrgCodes_"), oOrgUser);
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
		window.parent.selectPopWin(jQuery.parseJSON(d),$("#engineUserNames"),$("#engineCodes"), oOrgUser);
	};
});

//取消保存功能
//window.parent.hiddSaveBtn();

//受理步骤多文书操作
$("#ideacode").change(function(e){
	changeIdeacode();
	
	if($("#ideacode").val() == "FW"){
		if(window.confirm("此选项将进入拟发文业务。")){
			window.parent.location.href = "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!toDispatchDocFlow.do?ideacode=FW&djId=${djId}&flowCode=000392&flowInstId=${flowInstId}&nodeInstId=${nodeInstId}";
							}
						}
					});

	//页面公用加载
	function initCommon() {
		var nodeCode = $("#nodeCode").val();
		var ideacode = $("#ideacode").val();
		if (nodeCode == 'zbcsfzrsh' && "T" == '${object.iswbhq}') {
			$("#tr_ideacode").hide();
		}

	}

	initCommon();

// 	<!-- 通用运行模块维护入口 开始 -->
	$("#edit-module")
			.click(
					function() {
						url = "${contextPath}/powerruntime/powerruntime/generalModuleParam!edit.do?moduleCode="
								+ $("#moduleCode").val()+"&isAutoClose=T&r=" + Math.random(); ;
								
						var returnValue=window.showModalDialog(url,window,"dialogWidth=800px;dialogHeight=800px;scroll:yes"); 
								if(returnValue == "T"){
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
		return ${optCommonBizJson};
	}
	function getOptProcInfoJSON1() {
		return getOptProcInfoJSON();
	}

	function getOptProcInfoJSON() {
		return ${optProcInfoJSON};

	}
	//文件摘要文本框输入字数的提示
/* 	$("#transcontent").keyup(
			function() {
				CommonUtils.showTextAreaLimited("transcontent",
						"transcontentMessage", 150);
				return false;
			}); */

	
	function procoll(t){
		var procoll = document.getElementById(t);
		var alertvalue=procoll.value;
		
		var bytranscontent=document.getElementById("ybtranscontent").value;//阅示
		var transcontent=document.getElementById("transcontent").value;//主办分办
		var pstranscontent=document.getElementById("pstranscontent").value;//批示
		var alltranscontent='';
		
		var procollalert=document.getElementById("procollys").value;//阅示
		var procoll_bylert=document.getElementById("procoll_org").value;//主办分办
		var procoll_psalert=document.getElementById("procoll_ps").value;//批示
		//获取状态为以确定的
		//alert(procoll_psalert);
			if('procoll_ps'==t){//1批示
				if('确定'==procoll_psalert){
				alltranscontent=pstranscontent;
				}
				if('取消'==procollalert){
					alltranscontent=alltranscontent+bytranscontent;
					}
				if('取消'==procoll_bylert){
					alltranscontent=alltranscontent+transcontent;
					}
			} 	
			if('procollys'==t){//2阅示
				if('取消'==procoll_psalert){
					alltranscontent=pstranscontent;
					}
					if('确定'==procollalert){
						alltranscontent=alltranscontent+bytranscontent;
						}
					if('取消'==procoll_bylert){
						alltranscontent=alltranscontent+transcontent;
						}
			} 
			if('procoll_org'==t){//3主办分办
				if('取消'==procoll_psalert){
					alltranscontent=pstranscontent;
					}
					if('取消'==procollalert){
						alltranscontent=alltranscontent+bytranscontent;
						}
					if('确定'==procoll_bylert){
						alltranscontent=alltranscontent+transcontent;
						}
			} 
			
			//alert(alltranscontent);
		if(window.confirm("您["+alertvalue+"]操作吗？")){	
				
				if(alertvalue=="确定"){
					$(procoll).val("取消");
				}else{
					$(procoll).val("确定");
				}
				document.getElementById("alltranscontent").value=alltranscontent;//汇总
		}		
	}
			
			$('#isSendMsgView').click(function(){
				var $this = $(this);
				if($this.attr('checked') == 'checked'){
					$('#isSendMessage').val('T');
				}else{
					$('#isSendMessage').val('F');
				}
			});
</script>
</html>