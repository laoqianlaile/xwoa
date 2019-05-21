<%@page import="com.goldgrid.weboffice.TemplateService"%>
<%@ page contentType="text/html;charset=UTF-8"  import="java.util.*" %>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>办理信息</title>
<%-- <sj:head locale="zh_CN" /> --%>
<script type="text/javascript">
function checkForm(){
	var laber='${moduleParam.hasIdea}';
	
	/* if(laber!=''){
	if(_get('ideacode') != undefined && trim(_get('ideacode').value).length==0){
		alert("请选择${moduleParam.ideaLabel}。");
		_get('ideacode').focus();
		return false;
	}
	} */
	
	if(trim(_get('transcontent').value).length==0){
		alert("${moduleParam.ideaContent}不能为空。");
		_get('transcontent').focus();
		return false;
	}
	
	/* if(_get('bjCodes') != undefined && '${moduleParam.assignTeamRole}' == 'T'){
		if(trim(_get('bjCodes').value).length==0){
			alert("${moduleParam.teamRoleName}不能为空。");			
			return false;
		}
		
	} */
	
	if(_get('curTemplateId') != undefined && '${moduleParam.hasDocument}' == 'T' ){
		if($("#ideacode").val()== 'T' || $("#ideacode")==undefined){
			if(trim(_get('curTemplateId').value).length==0){
				alert("${moduleParam.documentLabel}不能为空。");
				return false;
			}
		}
	}
	
// 	submitBizData();
	return true;
}

var _get = function (id) {
	return document.getElementById(id);
};

//字符空格处理
var trim = function (str) {
	return str.replace(/^\s+|\s+$/g, "");
};
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
	
	openDocEdit(recordId,tempType);
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
		
	var param = "flowStep=" +  docAction +"&RecordID=${djId}&Template=" + val +"&archiveType="+archiveType
	 			+"&NeedBookMark=1";
	
	openNewWindow(uri + "?"+ param,'editForm','');
}

//修改文档
function updtDoc(){
	var archiveType = _get('archiveType').value;
	var curTemplateId = document.getElementById("curTemplateId").value;
	if (curTemplateId != "" && curTemplateId != null) {
		var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
		var param = "flowStep=ZW_EDIT&RecordID=${djId}&EditType=2,1&ShowType=1&Template=" + curTemplateId+"&archiveType="+archiveType;
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
		$("#archiveType").attr("value","");
		$("#curTemplateId").attr("value","");
	}
}

//查看公文
function viewDoc(archiveType,templateId){
	var curTemplateId = document.getElementById("curTemplateId").value;
	var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
	var param = "flowStep=VIEW&RecordID=${djId}&EditType=4,0&ShowType=1&archiveType="+archiveType+"&Template=" + templateId;
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
	var param = "flowStep=ZW_EDIT&RecordID=${djId}&Template=" + val +"&archiveType="+archiveType
	 			+"&NeedBookMark=1&codeCode="+codeCode+"&primaryUnit=${session.SPRING_SECURITY_CONTEXT.authentication.principal.primaryUnit}";	
	openNewWindow(uri + "?"+ param,'editForm','');
}
</script>

<body>
<s:form action="%{actionName}" method="post" namespace="/dispatchdoc" id="ioDocTasksForm" target="_parent" onsubmit="return checkForm();">
<input type="hidden" id="djId" name="djId" value="${djId}" />
<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />
<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">
<input type="hidden" id="flowPhase" name="flowPhase" value="${flowPhase}" />
<input type="hidden" id="nodeCode" name="nodeCode" value="${ nodeCode}" />
<input type="hidden" id="isReady" name="isReady" />

<fieldset style=" display: block; padding: 10px;">
			<legend>
				<b>办理信息<c:if test="${not empty nodeName}">-${nodename}</c:if></b>
			</legend>
			<table border="0" cellpadding="0" cellspacing="0" id="tb" class="viewTable" style="margin-top: 20px;">
			<c:if test="${moduleParam.hasIdea eq 'T' }">
				<tr>
					<td class="addTd" width="13%">${moduleParam.ideaLabel}<font color="red">*</font></td>
					<td align="left" width="87%">
						<input type="hidden" name="transidea" value="" id="transidea">
						<select id="ideacode"  name="ideacode" onchange="_getSelectedItemLabel(this)">
						<option VALUE="" >---请选择---</option>
							<c:forEach var="row" items="${cp:LVB(moduleParam.ideaCatalog)}">
								<option value="${row.value}" label="${row.label}" <c:if test="${ideacode==row.value}">selected="selected"</c:if>>
									<c:out value="${row.label}" />
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
			<tr>
					<td class="addTd" width="13%">是否会办<font color="red">*</font></td>
					<td align="left" width="87%">
						<input type="hidden" name="transidea" value="" id="transidea">						
						&nbsp;&nbsp;
							<span id="ishqArea"><input type="checkbox" id="ishq" name="ishq" value="T" ${"T" eq optProcInfo.ishq ? "checked" : ""}/>是否会办</span>
					</td>
				</tr>
				<input type="hidden" name="ideacode" value="sl" id="ideacode">
			
			<c:if test="${not empty moduleParam.ideaContent}">	
				<tr>
					<td class="addTd" width="13%">${moduleParam.ideaContent}<font color="red">*</font></td>
					<td align="left" width="87%">
						<s:textarea id="transcontent"
								name="transcontent" style="width:100%; height:40px;" value=""/>
					</td>
				</tr>
			</c:if>			
		        <!-- 办件角色 -->
				<c:if test="${moduleParam.assignTeamRole eq 'T' }">
					<tr>
					<td class="addTd" width="140">${moduleParam.teamRoleName}</td>
					<td align="left">
							<input type="text" id="bjUserNames" name="bjUserNames" style="width:100%;" value="${bjUserNames}"  readonly="readonly" />
							<input type="hidden" id="bjCodes" name="teamUserCodes" value="${teamUserCodes}" />
							<input type="hidden" id="roleCode" name="teamRoleCode" value="${moduleParam.teamRoleCode}" />
							<!-- <input type="hidden" id="attType" name="optProcAttention.attType" value="1" /> -->
					</td>
					</tr>
				</c:if>
				
				<input type="hidden" id="curTemplateId" name="curTemplateId"  value="" />
				
				
				
				
				<!-- 从流程节点配置中加载文书模版 -->
				<c:if test="${ templateFromNode eq 'TRUE' && moduleParam.docReadOnly ne 'T'}">
					<tr>
						<td class="addTd" width="140">办理文书</td>
						<td align="left">			
							<c:forEach var="temp" items="${templateFileList}">
							<div id="${temp.tempType}">
							<a href="javascript:void(0);" onclick="openDocNodeEdit('${temp.recordId}','${temp.tempType}','${temp.codeCode}');" class="btnA">
								<span id="${temp.tempType}" class="btn">
								<c:choose>
									<c:when test="${fn:length(temp.descript) > 8}">
										<c:out value="${fn:substring(temp.descript, 0, 8)}..." />
									</c:when>
									<c:otherwise>
										<c:out value="${temp.descript}" />
									</c:otherwise>
								</c:choose>
								</span></a>
								</div>
							</c:forEach>
					   </td>
					</tr>
				</c:if>				

				
			<c:if test="${moduleParam.hasStuff eq 'T' }">
					<tr>
					<td colspan="2" style="padding-bottom:8px;">
						<iframe id="basicsj"  name="sjfj"
								src="" width="100%" height=""
							frameborder="no" scrolling="false" border="0" marginwidth="0"
							marginheight="0"></iframe>
					</td></tr>
				
			</c:if>
			</table>
		</fieldset>		
		<center style="margin-top: 10px;display:none;" >
			<s:submit id="submitBtn" name="submitBtn" method="%{submitOptURI}" cssClass="btn" value="发 送" />
			<c:if test="${moduleParam.canDefer eq 'T' }">
				<s:submit id="suspendBtn" name="suspendBtn" method="suspendNodeInstance" cssClass="btn" value="暂 缓" />
			</c:if>
			<c:if test="${moduleParam.canRollback eq 'T' }">
				<s:submit id="rollBackBtn" name="rollBackBtn" method="rollBackOpt" cssClass="btn" value="回 退" />
			</c:if>
			<c:if test="${moduleParam.canClose eq 'T' }">
				<s:submit id="endFlowBtn" name="endFlowBtn" method="endFlow" cssClass="btn" value="办 结" />
			</c:if>
			<s:submit id="saveBtn" name="saveBtn" method="%{saveOptURI}" cssClass="btn" value="保 存" />
			<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"  onclick="window.history.go(-1);"/>	
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
var flowStep = "";
$(document).ready(function() {
	$("#isReady").val("ok");	
	flowStep = $("#flowPhase").val();	
// 	$("#xbArea", window.parent.document.pfFrame.window.document).hide();
});

$("#ishq").click(function() {
	if ($(this).attr("checked")) {
		$("#xbArea", window.parent.document.pfFrame.window.document).show();
	} else {
		$("#xbArea", window.parent.document.pfFrame.window.document).hide();
	}
});

// 受理，不受理，补正
function changeIdeacode() {
	var ideacode = $("#ideacode").val();
	if ("sl" == ideacode) {
		$("#sl").show();
		$("#ishqArea").show();
		$("#nsl").hide();
		if ("sl" == flowStep && $("#bz").parent().parent().hide());
		$("#pfFrame", window.parent.document).show();
	} else if ("nsl" == ideacode) {
		$("#sl").hide();
		$("#ishqArea").hide();
		$("#nsl").show();
		if ("sl" == flowStep && $("#bz").parent().parent().hide());
		$("#pfFrame", window.parent.document).hide();
	} else if ("bz" == ideacode) {
		$("#sl").hide();
		$("#ishqArea").hide();
		$("#nsl").hide();
		if ("sl" == flowStep && $("#bz").parent().parent().show());
		$("#pfFrame", window.parent.document).hide();
	} else if ("zy" == ideacode) {
		$("#zy").show();
		$("#nzy").hide();
	} else if ("nzy" == ideacode) {
		$("#nzy").show();
		$("#zy").hide();
	} else {
		$("#sl").hide();
		$("#ishqArea").hide();
		$("#nsl").hide();
		if ("sl" == flowStep && $("#bz").parent().parent().hide());
		$("#pfFrame", window.parent.document).hide();
		$("#zy").hide();
		$("#nzy").hide();
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
		window.parent.selectPopWin(jQuery.parseJSON(d),$("#bjUserNames"),$("#bjCodes"));
	};
});

//取消保存功能
//window.parent.hiddSaveBtn();

//受理步骤多文书操作
$("#ideacode").change(function(e){
	changeIdeacode();
	
	if($("#ideacode").val() == "FW"){
		if(window.confirm("此选项将进入发文业务。")){
			window.parent.location.href = "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!toDispatchDocFlow.do?ideacode=FW&djId=${djId}&flowCode=000392&flowInstId=${flowInstId}&nodeInstId=${nodeInstId}";
		}
	}
});
</script>

<script type="text/javascript">
function getOptBaseInfoJson(){	
	return getOptCommonBizJson();
}

function getOptCommonBizJson(){
	return ${optCommonBizJson};
}
</script>
</html>