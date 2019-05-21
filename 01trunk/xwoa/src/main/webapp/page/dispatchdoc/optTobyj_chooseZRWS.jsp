<%@page import="com.goldgrid.weboffice.TemplateService"%>
<%@ page contentType="text/html;charset=UTF-8"  import="java.util.*" %>
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
	var ideacode = $.trim($('input:radio[name="ideacode"]:checked').val()); //取radio
	
	if(window.parent.frames['editFrame'] && window.parent.frames['editFrame'].document.forms[0]){
		window.parent.frames['editFrame'].document.forms[0].submit();
	}
	
	// 当选择为重新登记时，只检查是否输入了办理意见
	if("suspendBtn" == submitSrcElement || "endFlowBtn" == submitSrcElement
			|| ("B" == $("#nodeType").val() && "B" == $("#ideacode").val())
			|| ("bz" == $("#ideacode").val())
			|| ("hbcsfb" == $("#nodeCode").val() && "F" == $("#ideacode").val())
			|| ("wbhbcsfb2" == $("#nodeCode").val() && "F" == $("#ideacode").val())
			|| ("wbhb" == $("#nodeCode").val() && "F" == $("#ideacode").val())) {
		if (!$.trim($("#transcontent").val())) {
			alert("${moduleParam.ideaContent}不能为空。");
			$("#transcontent").focus();
			return false;
		}
		return true;
	}
	
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
		alert("${moduleParam.ideaContent}超出最大长度");
		_get('transcontent').focus();
		return false;
	}
	//批分-协办部门
	//alert($("#xbOrgNames").val());
	
	if(_get('xbArea') != undefined && '${moduleParam.hasOrgRole}' == 'T' && $("#xbOrgNames").val()==''
			&&!$("#xbArea").is(":hidden")){
		alert("${moduleParam.xbOrgRoleName}不能为空。");			
		return false;		
	}
	
	if(_get('zbengineRoleCodeTr') != undefined && '${moduleParam.hasOrgRole}' == 'T' && $("#engineUserNames").val()==''
		&&!$("#zbengineRoleCodeTr").is(":hidden")){
	alert("${moduleParam.engineRoleName}不能为空。");			
	return false;		
	}
	
	if(_get('bjCodes') != undefined && '${moduleParam.assignTeamRole}' == 'T'
			&& !$("#zbOrgRoleCodeTr").is(":hidden")){
		if(trim(_get('bjCodes').value).length==0){
			alert("${moduleParam.teamRoleName}不能为空。");			
			return false;
		}
	}
	
	if(_get('curTemplateId') != undefined && '${moduleParam.hasDocument}' == 'T' ){
		if($("#ideacode").attr("type").toLowerCase() == 'hidden' || $("#ideacode").val() == 'T'){
			if(trim(_get('curTemplateId').value).length==0){
				alert("${moduleParam.documentLabel}不能为空。");
				return false;
			}
		}
	}
	
	if ('${templateFromNode}' == 'TRUE' && '${templateFromNode}' != 'T') {
		var showDocNum = 0;
		var docs = $("a[onclick^=openDocNodeEdit]");
		if (docs.length > 0) {
			for (var i=0; i<docs.length; i++) {
				if (!$(docs[i]).parent().is(":hidden")) {
					showDocNum++;
				}
			}
		}
		
		if (showDocNum > 0) {
			if(trim(_get('curTemplateId').value).length==0){
				alert("请保存办理文书。");
				return false;
			}
		}
	}
	
	//var ideacode = $("#ideacode").val();
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
		
	var param = "flowStep=" +  docAction +"&RecordID=${djId}&Template=" + val +"&archiveType="+archiveType+ "&nodeCode=" + $("#nodeCode").val()
	 			+"&NeedBookMark=1";
	
	openNewWindow(uri + "?"+ param,'editForm','');
}

//修改文档
function updtDoc(){
	var archiveType = _get('archiveType').value;
	var curTemplateId = document.getElementById("curTemplateId").value;
	if (curTemplateId != "" && curTemplateId != null) {
		var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
		var param = "flowStep=ZW_EDIT&RecordID=${djId}&EditType=2,1&ShowType=1&Template=" + curTemplateId+ "&nodeCode=" + $("#nodeCode").val()+"&archiveType="+archiveType;
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
     $("#transidea").val($.trim($('input:radio[name="ideacode"]:checked').attr("lable")));
   var nodeCode = $("#nodeCode").val();
   var ideacode = $.trim($('input:radio[name="ideacode"]:checked').val()); 

    if(nodeCode=='sw_py'){//厅办副主任批分
    if(ideacode=='T'){//提交接收部门
		_get("xbArea").style.display = "block";
		_get("zbOrgRoleCodeTr").style.display = "none";
		_get("zbengineRoleCodeTr").style.display = "none";
		
	}else if(ideacode=='F'){//主要领导批阅
		_get("xbArea").style.display = "none";
		_get("zbOrgRoleCodeTr").style.display = "none";
		_get("zbengineRoleCodeTr").style.display = "block";
		//document.getElementById("roleCode").value="zyldSP";
		
		//alert(document.getElementById("roleCode").value);
	}else if(ideacode=='FG'){//分管领导
		_get("xbArea").style.display = "none";
		_get("zbOrgRoleCodeTr").style.display = "block";
		_get("zbengineRoleCodeTr").style.display = "none";
		//document.getElementById("roleCode").value='${moduleParam.teamRoleCode}';
		//alert(document.getElementById("roleCode").value);
	}else{
		_get("xbArea").style.display = "none";
		_get("zbOrgRoleCodeTr").style.display = "none";
		_get("zbengineRoleCodeTr").style.display = "none";
		
	}
    _get("xbOrgNames").value='';
	_get("bjUserNames").value='';
	_get('transcontent').value='';
	_get('engineCodes').value='';
    }else   if(nodeCode=='sw_fgldpy' ){//收文_分管领导批阅节点
    	if(ideacode=='LD' ){//提交主要领导审阅	
			_get("xbArea").style.display = "none";
			_get("zbengineRoleCodeTr").style.display = "block";
			_get("zbOrgRoleCodeTr").style.display = "none";
		}else if(ideacode=='T'){//提交部门
			_get("zbengineRoleCodeTr").style.display = "none";
			_get("zbOrgRoleCodeTr").style.display = "none";
			_get("xbArea").style.display = "block";
		}else{
			_get("xbArea").style.display = "none";
			_get("zbengineRoleCodeTr").style.display = "none";
			_get("zbOrgRoleCodeTr").style.display = "none";
		}
    
    	_get("xbOrgNames").value='';
    	_get('transcontent').value='';
    	_get('engineCodes').value='';
    	_get("bjUserNames").value='';
    }
    
    var parentObj = window.parent.document.getElementById("transFrame");
    if(parentObj){
	    var h =  parseInt(document.getElementById("ioDocTasksForm").scrollHeight)+10;
	    parentObj.height = h+"px"; 
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
	var param = "flowStep=ZW_EDIT&RecordID=${djId}&Template=" + val +"&archiveType="+archiveType + "&nodeCode=" + $("#nodeCode").val()
	 			+"&NeedBookMark=1&codeCode="+codeCode+"&primaryUnit=${session.SPRING_SECURITY_CONTEXT.authentication.principal.primaryUnit}";	
	openNewWindow(uri + "?"+ param,'editForm','');
}

</script>

<body class="sub-flow">
<s:form action="ioDocTasksExcute" method="post" namespace="/dispatchdoc" id="ioDocTasksForm" target="_parent" onsubmit="return checkForm();">
<input type="hidden" id="djId" name="djId" value="${djId}" />
<input type="hidden" id="wfcode" name="wfcode" value="${wfcode}" />
<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />
<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">
<input type="hidden" id="flowPhase" name="flowPhase" value="${flowPhase}" />
<input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}" />
<input type="hidden" id="nodeType" name="nodeType" value="${nodeType}" />
<input type="hidden" id="isReady" name="isReady" />
<input type="hidden" id="moduleCode" name="moduleCode" value="${moduleCode}" />

<div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">办理信息</div>
	     <hr class="title-split-line" style="position: absolute;width: 100%;height: 1px;border:none;border-top:1px solid #CCC;padding: 0!important;top:10px;z-index:0"/>
</div>

<%-- <fieldset style=" display: block; padding: 10px;">
			<legend>
				<b>办理信息
				<c:if test="${not empty nodeName}">-${nodename}</c:if>
				</b>
				<!-- 通用运行模块维护入口 开始 -->
				<c:if test="${cp:CHECKUSEROPTPOWER('generalModuleParam', 'real-time-maintain') }"> 
				<img src='${contextPath }/styles/images/menu/page.gif' width="20px" title="维护通用运行模块"
					height="20px" id="edit-module" />
				</c:if>
				<!-- 通用运行模块维护入口 结束 -->
			</legend> --%>
			<table border="0" cellpadding="0" cellspacing="0" id="tb" class="viewTable" style="margin-top: 20px;">
			<c:if test="${moduleParam.hasIdea eq 'T' }">
				<tr>
					<td class="addTd" width="13%">${moduleParam.ideaLabel}<font color="red">*</font></td>
					<td align="left" width="87%">
						<input type="hidden" name="transidea" value="" id="transidea">
				<c:if test="${moduleCode ne 'sw_fgldpy' }">		
					<c:forEach var="row" items="${cp:DICTIONARY(moduleParam.ideaCatalog)}" varStatus="status">
                   <input type="radio"  name="ideacode"  method="_getSelectedItemLabel" value="${row.key}" lable="${row.value}"  ${(object.ideacode eq row.key or (empty object.ideacode and  status.index  eq '0')) ? 'checked = "checked"' : ''}/><c:out value="${row.value}" />
					</c:forEach>	
				</c:if>	
				<c:if test="${moduleCode eq 'sw_fgldpy' }">		
					<c:forEach var="row" items="${cp:DICTIONARY('sw_fgldpyOLD')}" varStatus="status">
                   <input type="radio"  name="ideacode"  method="_getSelectedItemLabel" value="${row.key}" lable="${row.value}"  ${(object.ideacode eq row.key or (empty object.ideacode and  status.index  eq '0')) ? 'checked = "checked"' : ''}/><c:out value="${row.value}" />
					</c:forEach>	
				</c:if>						
					</td>
				</tr>
			</c:if>
			
			<c:if test="${moduleParam.hasIdea ne 'T'}">
				<input type="hidden" name="ideacode" value="${object.ideacode}" id="ideacode">
			</c:if>
			
			<!--具有权限引擎权限——收文批分  -->
			<c:if test="${moduleParam.hasOrgRole eq 'T' }">
					<c:if test="${not empty moduleParam.zbOrgRoleCode }">
						<tr>
							<td class="addTd" width="13%">主办处室</td>
							<td align="left">
								<input type="hidden" id="zbOrgRoleCode" name="zbOrgRoleCode" value="${moduleParam.zbOrgRoleCode}">
								<select id="zbOrgCode" name="zbOrgCode">
										<option value="">---请选择---<--</option>
										<c:forEach items="${unitList}" var="unit">
											<option value="${unit.unitcode}" <c:if test="${zbOrgCode == unit.unitcode}" >selected = "selected"</c:if>>
												<c:out value="${unit.unitname}" />
											</option>
										</c:forEach>
								</select>
							</td>
						</tr>
					</c:if>
					<tr id="xbArea">
						<td class="addTd" width="13%">${moduleParam.xbOrgRoleName}<font color="red">*</font></td>
						<td>
							<input type="hidden" id="unitsJson" name="unitsJson" value='${unitsJson}' />
							<input type="text" id="xbOrgNames" name="xbOrgNames" style="width:100%;" value="${xbOrgNames}"  readonly="readonly" />
							<input type="hidden" id="xbOrgCodes" name="xbOrgCodes" value="${xbOrgCodes}" />
							<input type="hidden" id="xbOrgRoleCode" name="xbOrgRoleCode" value="${moduleParam.xbOrgRoleCode}" />
						</td>
					</tr>
				</c:if>
				  <!-- 办件角色 -->
				<c:if test="${moduleParam.assignTeamRole eq 'T' }">
					<tr id="zbOrgRoleCodeTr">
					<td class="addTd" width="140">${moduleParam.teamRoleName}<font color="red">*</font></td>
					<td align="left">
							<input type="text" id="bjUserNames" name="bjUserNames" style="width:100%;" value="${bjUserNames}"  readonly="readonly" />
							<input type="hidden" id="bjCodes" name="teamUserCodes" value="${teamUserCodes}" />
							<input type="hidden" id="roleCode" name="teamRoleCode" value="${moduleParam.teamRoleCode}" />
							<!-- <input type="hidden" id="attType" name="optProcAttention.attType" value="1" /> -->
					</td>
					</tr>
				</c:if>
				
				<!-- 权限引擎角色 -->
				<c:if test="${moduleParam.assignEngineRole eq 'T' }">
					<tr id="zbengineRoleCodeTr">
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
				
			<c:if test="${not empty moduleParam.ideaContent}">	
				<tr>
					<td class="addTd" width="13%">${moduleParam.ideaContent}<font color="red">*</font></td>
					<td align="left" width="87%">
						<s:textarea id="transcontent"
								name="transcontent" style="width:100%; height:40px;" value="%{object.transcontent}"/>
					<span id="transcontentMessage">&nbsp;</span></td>
				</tr>
			</c:if>
				<c:if test="${moduleParam.hasAttention eq 'T' }">
					<tr>
					<td class="addTd" width="140">${moduleParam.attentionLabel}</td>
					<td align="left">
							<input type="text" id="attUserNames" name="attUserNames" readonly="readonly" style="width:100%;" value="${attUserNames}"  />
							<input type="hidden" id="attUserCodes" name="attUserCodes" value="${attUserCodes}" />
							<input type="hidden" id="attType" name="optProcAttention.attType" value="1" />
					</td>
					</tr>
				</c:if>
		      
				
				<input type="hidden" id="curTemplateId" name="curTemplateId" value="${object.recordId}" />
				
				<c:if test="${moduleParam.hasDocument eq 'T' }">
					<tr>
						<td class="addTd" width="140">${moduleParam.documentLabel}</td>
						<td align="left">
						<input type="hidden" id="archiveType" name="archiveType"  value="${object.archiveType}" />	
							<div id="addDoc">
								<select id="recordId" name="recordId" onchange="openTemplate(this.value,'${moduleParam.documentType}');">
								 	<option value="">---请选择---</option>
									<c:forEach var="temp" items="${templateList}">
										<option value="${temp.recordId},${temp.tempType}" <c:if test="${object.recordId==temp.recordId}">selected="selected"</c:if>>
										<c:out value="${temp.descript}" /></option>
									</c:forEach>
								</select>
							</div>
							
							<div id="zwedit"  style="display:none;">
								<span id="fwname"></span>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" id="editdc" onclick="updtDoc();" value="修改正文" class="btn processBtn" />
								<!-- 
								<input type="button" id="deletedc" onclick="delDoc();" value="删除正文" class="btn flowBtn" />
								 -->
							</div>
							
							<script type="text/javascript">
								var curTemplateId = document.getElementById("curTemplateId").value;
								if(curTemplateId != null && curTemplateId != ''){
									document.getElementById("zwedit").style.display="";
									document.getElementById("addDoc").style.display="none";
									
							        var indexx=document.getElementById('recordId').selectedIndex ;
							        var textt=document.getElementById("recordId").options[indexx].text;
									document.getElementById("fwname").innerHTML="发文拟稿正文(" + textt + ").doc";
								}
								
							</script>
					   </td>
					</tr>
				</c:if>
				<c:if test="${moduleParam.docReadOnly eq 'T' }">
					<tr>
						<td class="addTd" width="140">文书查看</td>
						<td align="left">	
						<c:if test="${templateFileList eq null}">
							&nbsp;&nbsp;未配置或者未上传文书
						</c:if>
						<c:if test="${templateFileList ne null}">
							<c:forEach var="temp" items="${templateFileList}">
								<div id="${temp.tempType}">
								&nbsp;&nbsp;<input type="button" id="viewdc" onclick="viewDoc('${temp.tempType}','${temp.recordId}');" value="${temp.descript}" class="btn processBtn" />
								</div>
							</c:forEach>
						</c:if>
						</td>
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
				
				
				
				<c:if test="${moduleParam.riskId !=null && moduleParam.riskId !=0}">
				<%@ include file="/page/powerruntime/optcommon/optRiskInfoForm.jsp"%>				
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
			
			<!-- 是否发送短信	短信功能开发且配置为发送 -->
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
		    
			</table>
<!-- 		</fieldset> -->
		
		<center style="margin-top: 10px;display:none;" >
			<s:submit id="submitBtn" name="submitBtn" method="submitOpt" cssClass="btn" value="发 送" onclick="setSubmitSrcElement('submitBtn')" />
			<c:if test="${moduleParam.canDefer eq 'T' }">
				<s:submit id="suspendBtn" name="suspendBtn" method="suspendNodeInstance" cssClass="btn" value="暂 缓" onclick="setSubmitSrcElement('suspendBtn')" />
			</c:if>
			<c:if test="${moduleParam.canRollback eq 'T' }">
				<s:submit id="rollBackBtn" name="rollBackBtn" method="rollBackOpt" cssClass="btn" value="回 退" />
			</c:if>
			<c:if test="${moduleParam.canClose eq 'T' }">
				<s:submit id="endFlowBtn" name="endFlowBtn" method="endFlow" cssClass="btn" value="办 结" onclick="setSubmitSrcElement('endFlowBtn')" />
			</c:if>
			<s:submit id="saveBtn" name="saveBtn" method="saveOpt" cssClass="btn" value="保 存" onclick="setSubmitSrcElement('saveBtn')" />
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
oOrgUser["nodeCode"] = $("#nodeCode");
oOrgUser["engineRoleCode"] = $("#engineRoleCode");
oOrgUser["engineUserNames"] = $("#engineUserNames");

$(document).ready(function() {
	$("#isReady").val("ok");
	
	flowStep = $("#flowPhase").val();
	nodeCode = $("#nodeCode").val();
	
// 	var ideacode = $("#ideacode").val();
	var ideacode = $.trim($('input:radio[name="ideacode"]:checked').val()); //取radio
	
	    if(nodeCode=='sw_py'){//厅办副主任批分
	        if(ideacode=='T'){//提交接收部门
	    		_get("xbArea").style.display = "block";
	    		_get("zbOrgRoleCodeTr").style.display = "none";
	    		_get("zbengineRoleCodeTr").style.display = "none";
	    		
	    	}else if(ideacode=='F'){//主要领导批阅
	    		_get("xbArea").style.display = "none";
	    		_get("zbOrgRoleCodeTr").style.display = "none";
	    		_get("zbengineRoleCodeTr").style.display = "block";
	    		//document.getElementById("roleCode").value="zyldSP";
	    		
	    		//alert(document.getElementById("roleCode").value);
	    	}else if(ideacode=='FG'){//分管领导
	    		_get("xbArea").style.display = "none";
	    		_get("zbOrgRoleCodeTr").style.display = "block";
	    		_get("zbengineRoleCodeTr").style.display = "none";
	    		//document.getElementById("roleCode").value='${moduleParam.teamRoleCode}';
	    		//alert(document.getElementById("roleCode").value);
	    	}else{
	    		_get("xbArea").style.display = "none";
	    		_get("zbOrgRoleCodeTr").style.display = "none";
	    		_get("zbengineRoleCodeTr").style.display = "none";
	    		
	    	}
	        _get("xbOrgNames").value='';
	    	_get("bjUserNames").value='';
	    	_get('transcontent').value='';
	    	_get('engineCodes').value='';
	    	_get('engineUserNames').value='';
	        }else   if(nodeCode=='sw_fgldpy' ){//收文_分管领导批阅节点
	        	if(ideacode=='LD' ){//提交主要领导审阅	
	    			_get("xbArea").style.display = "none";
	    			_get("zbengineRoleCodeTr").style.display = "block";
	    			_get("zbOrgRoleCodeTr").style.display = "none";
	    		}else if(ideacode=='T'){//提交部门
	    			_get("zbengineRoleCodeTr").style.display = "none";
	    			_get("zbOrgRoleCodeTr").style.display = "none";
	    			_get("xbArea").style.display = "block";
	    		}else{
	    			_get("xbArea").style.display = "none";
	    			_get("zbengineRoleCodeTr").style.display = "none";
	    			_get("zbOrgRoleCodeTr").style.display = "none";
	    		}
	        
	        	_get("xbOrgNames").value='';
	        	_get('transcontent').value='';
	        	_get('engineCodes').value='';
	        	_get("bjUserNames").value='';
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


//受理，不受理，补正
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

// 拼接办理意见
function setTranscontent() {
	var bjUserSelected = $("#bjUserSelected")[0] ? $("#bjUserSelected").val() : "";
	var zbOrgSelected = $("#zbOrgSelected")[0] ? $("#zbOrgSelected").val() : "";
	var xbOrgSelected = $("#xbOrgSelected")[0] ? $("#xbOrgSelected").val() : "";
	if (bjUserSelected || zbOrgSelected || xbOrgSelected) {
		var opinion = "请" + (bjUserSelected ? (bjUserSelected + "同志阅") : "") 
						+ ((bjUserSelected && (zbOrgSelected || xbOrgSelected)) ? "，" : "")
						+ zbOrgSelected + ((zbOrgSelected && xbOrgSelected ? "会同" : "")) + xbOrgSelected 
						+ ((zbOrgSelected || xbOrgSelected ? "阅处" : "")) + "。";
		$("#transcontent").val(opinion);
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
// 		window.parent.selectPopWin(jQuery.parseJSON(d),$("#bjUserNames"),$("#bjCodes"), oOrgUser);
		// 人员选择树
		window.parent.selectPopWinTree(d,$("#bjUserNames"),$("#bjCodes"),oOrgUser);
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

$("#engineUserNames").click(function(){
	var d = '${engineUserJson}';
	if(d.trim().length){
		window.parent.selectPopWinTree(d,$("#engineUserNames"),$("#engineCodes"), oOrgUser);
	};
});

$('input:radio[name="ideacode"]').click(function(e){
	
	clickMethod=$.trim($('input:radio[name="ideacode"]:checked').attr("method"));
		_getSelectedItemLabel() ;
	changeIdeacode();
					});
function getOptBaseInfoJson(){	
	return getOptCommonBizJson();
}

function getOptCommonBizJson(){
	return ${optCommonBizJson};
}

//文件摘要文本框输入字数的提示
$("#transcontent").keyup(
		function() {
			CommonUtils.showTextAreaLimited("transcontent",
					"transcontentMessage", 150);
			return false;
		});
		
$('#isSendMsgView').click(function(){
	var $this = $(this);
	if($this.attr('checked') == 'checked'){
		$('#isSendMessage').val('T');
	}else{
		$('#isSendMessage').val('F');
	}
});

</script>
	<script type="text/javascript">
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
</html>