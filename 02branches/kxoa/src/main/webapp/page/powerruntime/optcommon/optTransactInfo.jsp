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
	if(trim(_get('ideacode').value).length==0){
		alert("请选择处理结果。");
		_get('ideacode').focus();
		return false;
	}
	if(trim(_get('transcontent').value).length==0){
		alert("意见不能为空。");
		_get('transcontent').focus();
		return false;
	}
	
	if(_get('bjCodes') != undefined && '${moduleParam.assignTeamRole}' == 'T'){
		if(trim(_get('bjCodes').value).length==0){
			alert("${moduleParam.teamRoleName}不能为空。");			
			return false;
		}
		
	}
	
	if(_get('curTemplateId') != undefined && '${moduleParam.hasDocument}' == 'T' ){
		if(trim(_get('curTemplateId').value).length==0){
			alert("${moduleParam.documentLabel}不能为空。");
			return false;
		}
	}
	
	submitBizData();
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

function openNewWindow(winUrl,winName,winProps){
	if(winProps =='' || winProps == null){
		winProps = 'height='+(window.screen.availHeight-50) +',width='+window.screen.availWidth
		+',directories=false,location=false,top=0,left=0,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
	}
	window.open(winUrl, winName, winProps);
}

function openTemplate(val,documentType){
	if(val  == '' || val == null){
		return;
	}
	var curTemplateId = document.getElementById("curTemplateId").value;
	if (curTemplateId != "" && curTemplateId != null) {
		if(window.confirm("重新选择模板会生成新的文件，已保存的内容将被覆盖，是否确定？")){
			openDocEdit(val,documentType);
		}
	}else{
		openDocEdit(val,documentType);
	}
}

function getArchiveType(documentType){
	var archiveType='';
	
	//受理步骤
	if(documentType == 'sl'){
		var doc = document.getElementById("recordId");
		var docText=doc.options[doc.selectedIndex].text;
		if(docText.indexOf('受理')>-1){
			archiveType='02';
		}
		
		if(docText.indexOf('补正')>-1){
			archiveType='03';
		}
	}
	
	//申请表
	if(documentType == 'zw'){
		archiveType='01';
	}
	
	//核查意见书
	if(documentType == 'hc'){
		archiveType='05';
	}
	
	//核查意见书附表
	if(documentType=='idea'){
		archiveType='06';
	}
	
	//审查意见书
	if(documentType == 'sc'){
		archiveType='07';
	}
	
	//许可决定书
	if(documentType == 'jds'){
		archiveType='08';
	}
	

	return archiveType;
}
//选择模版上传文档
function openDocEdit(val,documentType){
	var archiveType = getArchiveType(documentType);
	var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
	var param = "flowStep=ZW_EDIT&RecordID=${djId}&Template=" + val +"&archiveType="+archiveType
	 			+"&NeedBookMark=1";
	openNewWindow(uri + "?"+ param,'editForm','');
}

//修改文档
function updtDoc(documentType){
	var archiveType = getArchiveType(documentType);
	var curTemplateId = document.getElementById("curTemplateId").value;
	if (curTemplateId != "" && curTemplateId != null) {
		var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
		var param = "flowStep=ZW_EDIT&RecordID=${djId}&EditType=2,1&ShowType=1&Template=" + curTemplateId+"&archiveType="+archiveType;
		openNewWindow(uri + "?"+ param,'editForm','');
	} else {
		alert("请生成您需要的文书！！");
	}
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
	window.parent.frames['viewFrame'].document.forms[0].submit();
}
</script>
<body>
<s:form action="generalOperator" method="post" namespace="/powerruntime" id="generalOperatorForm" target="_parent" onsubmit="return checkForm();">
<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />
<input type="hidden" id="djId" name="djId"  value="${djId}" />
<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">

<fieldset  class="_new">
			<legend>
				<b>办理信息</b>
			</legend>
			<table border="0" cellpadding="0" cellspacing="0" id="tb" class="viewTable" style="margin-top: 20px;">
				<tr>
					<td class="addTd" width="13%">${moduleParam.ideaLabel}<font color="red">*</font></td>
					<td align="left" width="87%">
						<input type="hidden" name="transidea" value="" id="transidea">
						<select id="ideacode"  name="ideacode" onchange="_getSelectedItemLabel(this)">
						<option VALUE="" >---请选择---</option>
							<c:forEach var="row" items="${cp:LVB(moduleParam.ideaCatalog)}">
								<option value="${row.value}" label="${row.label}" <c:if test="${object.ideacode==row.value}">selected="selected"</c:if>>
									<c:out value="${row.label}" />
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<td class="addTd" width="140">${moduleParam.ideaContent}<font color="red">*</font></td>
					<td align="left">
						<s:textarea id="transcontent"
								name="transcontent" style="width:100%; height:40px;" value="%{object.transcontent}"/>
					</td>
				</tr>
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
		        <!-- 办件角色 -->
				<c:if test="${moduleParam.assignTeamRole eq 'T' }">
					<tr>
					<td class="addTd" width="140">${moduleParam.teamRoleName}<font color="red">*</font></td>
					<td align="left">
							<input type="text" id="bjUserNames" name="bjUserNames" style="width:100%;" value="${bjUserNames}"  readonly="readonly" />
							<input type="hidden" id="bjCodes" name="teamUserCodes" value="${teamUserCodes}" />
							<input type="hidden" id="roleCode" name="teamRoleCode" value="${moduleParam.teamRoleCode}" />
							<!-- <input type="hidden" id="attType" name="optProcAttention.attType" value="1" /> -->
					</td>
					</tr>
				</c:if>
				
				<c:if test="${moduleParam.hasDocument eq 'T' }">
					<tr>
						<td class="addTd" width="140">${moduleParam.documentLabel}</td>
						<td align="left">
							<select id="recordId" name="recordId" onchange="openTemplate(this.value,'${moduleParam.documentType}');">
							 	<option value="">---请选择---</option>
								<c:forEach var="temp" items="${templateList}">
									<option value="${temp.recordId}" <c:if test="${object.recordId==temp.recordId}">selected="selected"</c:if>>
									<c:out value="${temp.descript}" /></option>
								</c:forEach>
							</select>
							&nbsp;&nbsp;<input type="button" id="modifyDoc" onclick="updtDoc('${moduleParam.documentType}');" value="修改正文" class="btn processBtn" />
							<input type="hidden" id="curTemplateId" name="curTemplateId"  value="${object.recordId}" />
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
			</table>
		</fieldset>
		
		<center style="margin-top: 10px;display:none;" >
			<s:submit id="submitBtn" name="submitBtn" method="submitOpt" cssClass="btn" value="发 送" />
			<c:if test="${moduleParam.canDefer eq 'T' }">
				<s:submit id="suspendBtn" name="suspendBtn" method="suspendNodeInstance" cssClass="btn" value="暂 缓" />
			</c:if>
			<c:if test="${moduleParam.canRollback eq 'T' }">
				<s:submit id="rollBackBtn" name="rollBackBtn" method="rollBackOpt" cssClass="btn" value="回 退" />
			</c:if>
			<c:if test="${moduleParam.canClose eq 'T' }">
				<s:submit id="endFlowBtn" name="endFlowBtn" method="endFlow" cssClass="btn" value="办 结" />
			</c:if>
			<s:submit id="saveBtn" name="saveBtn" method="saveOpt" cssClass="btn" value="保 存" />
			<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"  onclick="window.history.go(-1);"/>	
		</center>
		</s:form>
	
</body>
<c:if test="${moduleParam.hasStuff eq 'T' }">
<script type="text/javascript">
var url='powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${djId}&stuffInfo.nodeInstId=${nodeInstId}&stuffInfo.groupid=${moduleParam.stuffGroupId}';
var obj = document.getElementById("basicsj");
obj.src = url;
obj.onload = function(){
	obj.style.height = window.frames['sjfj'].document.body.scrollHeight+"px";
};
</script>
</c:if>

<script type="text/javascript">


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

</script>

<script type="text/javascript">
//var optBaseInfoJson=window.parent.optBaseInfoJson;
function getOptBaseInfoJson(){
	return window.parent.getOptBaseInfoJson();
}
//var optBaseInfoJson=window.parent.getOptBaseInfoJson();
//alert(window.opener.optBaseInfoJson);
</script>
</html>