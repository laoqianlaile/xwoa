<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="" /></title>
<%-- <sj:head locale="zh_CN" /> --%>
		

		<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/scripts/alertDiv.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/arrow.js" type="text/javascript"></script>
		
		<link href="${pageContext.request.contextPath}/themes/css/arrow.css" rel="stylesheet" type="text/css" />		
<script type="text/javascript">
	function openTemplate(val,documentType){
		if(val  == '' || val == null){
			return;
		}
		var tempArr =val.split(',');
		var recordId=tempArr[0];
		var tempType=tempArr[1];
		
		openDocEdit(recordId,tempType);
	}
	function updtDoc(){
		var archiveType = 'zw';
		var curTemplateId = document.getElementById("curTemplateId").value;
		if (curTemplateId != "" && curTemplateId != null) {
			var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
			var param = "flowStep=ZW_EDIT&RecordID=${djId}&EditType=2,1&ShowType=1&Template=" + curTemplateId+"&archiveType="+archiveType+ "&nodeCode=" + $("#nodeCode").val();
			openNewWindow(uri + "?"+ param,'editForm','');
		} else {
			alert("请生成您需要的文书！！");
		}
	}
	//选择模版上传文档
	function openDocEdit(val,archiveType){
		$("#archiveType").attr("value",archiveType);
		var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
		var docAction = "ZW_EDIT";
		var curTemplateId = document.getElementById("curTemplateId").value;
		if (curTemplateId != "" && curTemplateId != null && curTemplateId != val) {
			if(window.confirm("重新选择模板会生成新的文件，已保存的内容将被覆盖，是否确定？")){
				docAction = "ZW_ADD";
			}	
		}
			
		var param = "flowStep=" +  docAction +"&RecordID=${object.djId}&Template=" + val +"&archiveType="+archiveType +"&NeedBookMark=1";
		
		openNewWindow(uri + "?"+ param,'editForm','');
	}
	
	//修改文档
	function updtDoc(){
		var archiveType =$("#archiveType").val();
		var curTemplateId = document.getElementById("curTemplateId").value;
		if (curTemplateId != "" && curTemplateId != null) {
			var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
			var param = "flowStep=ZW_EDIT&RecordID=${object.djId}&EditType=2,1&ShowType=1&Template=" + curTemplateId+"&archiveType="+archiveType;
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
			
			adjustHeight();
		}
	}
	/****送签说明书****/
	function editDN(archiveType){
		var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
		var param = "flowStep=SQ_EDIT&RecordID=${object.djId}&EditType=2,1&ShowType=1&Template=1386585483609&archiveType=" + archiveType;
		openNewWindow(uri + "?"+ param,'editForm','');
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
</script>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<s:form method="post" namespace="/dispatchdoc"  target="_parent" action="dispatchDoc" id="dispatchDocForm">
		<input type="hidden" id="roleCode" name="roleCode" value="zbcshq" />
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="optId" name="optBaseInfo.optId" value="${object.optBaseInfo.optId}" />
		<input type="hidden" id="wfcode" name="optBaseInfo.flowCode" value="${object.optBaseInfo.flowCode}" />
		<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">
		<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />
		<input type="hidden" id="powerid" name="powerid" value="${object.itemId}" />
		<input type="hidden" id="powername" name="powername" value="${cp:MAPVALUE('suppowerId', object.itemId)}" />	
		<input type="hidden" id="internalNo" name="internalNo" value="${object.internalNo}" />	
		<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
			<legend style="padding: 4px 8px 3px;">
				<b>拟文</b>
			</legend>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">
						文件标题<font color="#ff0000">*</font>
					</td>
					<td align="left" colspan="3">
						<input type="text" id="dispatchDocTitle" name="dispatchDocTitle" maxlength="200" value="${object.dispatchDocTitle}" style="width: 100%;height: 24"/>
					</td>
					<td class="addTd">
						文件类型<font color="#ff0000">*</font>
					</td>
					<td align="left">
						<select id="dispatchFileType" name="dispatchFileType" style="width: 180px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('WJLX')}">
								<option value="${row.key}" ${(object.dispatchFileType eq row.key or (empty object.dispatchFileType and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<c:if test="${object.itemId eq 'SD370000FG-GW-0009' }">
				<tr>
			
					<td class="addTd">
						是否处室会签文
					</td>
					<td align="left">
					<input type="hidden" id="isUnionDispatch" name="isUnionDispatch" value="2" />
						<select id="isCountersign" name="isCountersign" style="width: 180px" ${empty editIsCountersign ? "" : "disabled"}>
							<c:forEach var="row" items="${cp:DICTIONARY('YES_NO')}">
								<option value="${row.key}" ${(object.isCountersign eq row.key or (empty object.isCountersign and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
					<td class="addTd">
						是否委内公开<font color="#ff0000">*</font>
					</td>
					<td align="left"  colspan="3">
						<select id="commissionCanOpen" name="commissionCanOpen" style="width: 180px">
							<c:forEach var="row" items="${cp:DICTIONARY('YES_NO')}">
								<option value="${row.key}" ${(object.commissionCanOpen eq row.key or (empty object.commissionCanOpen and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				</c:if>
				<c:if test="${object.itemId ne 'SD370000FG-GW-0009' }">
				<tr>
					<td class="addTd">
						是否部门联合拟发文
					</td>
					<td align="left">
						<select id="isUnionDispatch" name="isUnionDispatch" style="width: 180px">
							<c:forEach var="row" items="${cp:DICTIONARY('YES_NO')}">
								<option value="${row.key}" ${(object.isUnionDispatch eq row.key or (empty object.isUnionDispatch and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
					<td class="addTd">
						是否处室会签文
					</td>
					<td align="left">
						<select id="isCountersign" name="isCountersign" style="width: 180px" ${empty editIsCountersign ? "" : "disabled"}>
							<c:forEach var="row" items="${cp:DICTIONARY('YES_NO')}">
								<option value="${row.key}" ${(object.isCountersign eq row.key or (empty object.isCountersign and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
					<td class="addTd">
						是否委内公开<font color="#ff0000">*</font>
					</td>
					<td align="left">
						<select id="commissionCanOpen" name="commissionCanOpen" style="width: 180px">
							<c:forEach var="row" items="${cp:DICTIONARY('YES_NO')}">
								<option value="${row.key}" ${(object.commissionCanOpen eq row.key or (empty object.commissionCanOpen and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				</c:if>
				<tr id="unionDispatchUnitsTr">
					<td class="addTd">
						联合拟发文部门
					</td>
					<td align="left" colspan="5">
						<textarea id="unionDispatchUnits" name="unionDispatchUnits" style="width: 100%; height: 80px;" >${object.unionDispatchUnits}</textarea>
						<span id="unionDispatchUnitsMessage">&nbsp;</span>
					</td>
				</tr>
				<tr id="countersignUnitsTr">
					<td class="addTd">
						会签处室
					</td>
					<td align="left" colspan="5">
						<div id="countersignUnitsHiddenArea" style="display: none;"></div>
						<div>
			
			<input size="90" type="text" name="orgName" id="orgName" value="${selUnitName }">
			<input type="hidden" name="orgCode" id="orgCode"  value="${selUnitCode }"/>
			
						
					</td>
				</tr>
				<tr>
					<td class="addTd">
						是否信息公开<font color="#ff0000">*</font>
					</td>
					<td align="left">
						<select id="dispatchCanOpen" name="dispatchCanOpen" style="width: 180px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('FW_CAN_OPEN')}">
								<option value="${row.key}" ${(object.dispatchCanOpen eq row.key or (empty object.dispatchCanOpen and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
						<input type="hidden" id="unitsJson" name="unitsJson" value='${unitsJson}' />	
						<div id="attAlert" class="alert"
		style="width: 600px; height: 330px; position:absolute;top:20px;left:20%;overflow: hidden;">
		<h4>
			<span>选择机构</span><span id="close2"
				style="float: right; margin-right: 8px;" class="close"
				onclick="closeAlert('attAlert');">关闭</span>
		</h4>
		<div class="fix">
			<div id="leftSide"></div>
			<div id="l-r-arrow">
				<div class="lb"></div>
				<div class="rb"></div>
			</div>
			<div id="rightSide">
				<ul></ul>
			</div>
			<div id="t-b-arrow">
				<!-- <div class="tb"></div>
	            <div class="bb"></div> -->
				<b class="btns"><input id="save" class="btn" type="button"
					value="保      存" /><input id="clear" class="btn" type="button"
					value="取      消" style="margin-top: 5px;" /></b>
			</div>
		</div>
	</div>
					</td>
					<td class="addTd">
						公开方式<font color="#ff0000">*</font>
					</td>
					<td align="left">
						<select id="dispatchOpenType" name="dispatchOpenType" style="width: 180px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('FW_OPEN_TYPE')}">
								<option value="${row.key}" ${(object.dispatchOpenType eq row.key or (empty object.dispatchOpenType and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
					<td class="addTd">
						紧急程度
					</td>
					<td align="left">
						<select id="criticalLevel" name="criticalLevel" style="width: 180px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('critical_level')}">
								<option value="${row.key}" ${(object.criticalLevel eq row.key or (empty object.criticalLevel and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="addTd">
						文件摘要<font color="#ff0000">*</font>
					</td>
					<td align="left" colspan="5">
						<textarea id="dispatchDocSummary" name="dispatchDocSummary" style="width: 100%; height: 80px;" >${object.dispatchDocSummary}</textarea>
						<span id="dispatchDocSummaryMessage">&nbsp;</span>
					</td>
				</tr>
				<tr>
					<td class="addTd">
						拟文日期<font color="#ff0000">*</font>
					</td>
					<td align="left">
								<input type="text" class="Wdate"  id="draftDate" name="draftDate" 
								style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
								value='<fmt:formatDate value="${object.draftDate}" pattern="yyyy-MM-dd"/>'
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 						<sj:datepicker id="draftDate" name="draftDate"  --%>
<%-- 						value="%{object.draftDate}" style="width: 120px;" --%>
<%-- 						 yearRange="2000:2020" displayFormat="yy-mm-dd"  --%>
<%-- 						 changeYear="true" readonly="true" cssStyle="width: 100px;" /> --%>
					</td>
					<td class="addTd">
						主办处室<font color="#ff0000">*</font>
					</td>
					<td align="left">
						<input type="text" id="optUnitName" name="optUnitName" maxlength="100" value="${object.optUnitName}" ${empty zbcs ? "" : "disabled=true"}/>
					</td>
					<td class="addTd">
						拟稿人<font color="#ff0000">*</font>
					</td>
					<td align="left">
						<input type="text" id="draftUserName" name="draftUserName" maxlength="100" value="${object.draftUserName}" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						公文种类<font color="#ff0000">*</font>
					</td>
					<td align="left">
						<select id="dispatchDocType" name="dispatchDocType" style="width: 180px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('FW_DOC_TYPE')}">
								<option value="${row.key}" ${(object.dispatchDocType eq row.key or (empty object.dispatchDocType and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
					<td class="addTd">
						密级
					</td>
					<td align="left">
						<select id="secretsDegree" name="secretsDegree" style="width: 180px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('FW_SECRETS_LEVEL')}">
								<option value="${row.key}" ${(object.secretsDegree eq row.key or (empty object.secretsDegree and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
					<td class="addTd">
						保管期限<!-- <font color="#ff0000">*</font> -->
					</td>
					<td align="left">
						<select id="retentionPeriod" name="retentionPeriod" style="width: 180px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('RETENTION_PERIOD')}">
								<option value="${row.key}" ${(object.retentionPeriod eq row.key or (empty object.retentionPeriod and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
					<tr>
					<td class="addTd">
						主送单位<font color="#ff0000">*</font>
					</td>
					<td align="left" colspan="5">
						<input type="hidden" id="mainNotifyUnitCode" name="mainNotifyUnitCode" />
						<input type="text" id="mainNotifyUnit" name="mainNotifyUnit" maxlength="100" style="width: 100%;" value="${object.mainNotifyUnit}" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						抄送单位
					</td>
					<td align="left" colspan="5">
						<input type="hidden" id="otherUnitCodes" name="otherUnitCodes" />
						<input type="text" id="otherUnits" name="otherUnits" maxlength="100" style="width: 100%;" value="${object.otherUnits}" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						关联其他阅办文
					</td>
					<td align="left" colspan="5">
						<input type="checkbox" id="isUnionOther" name="isUnionOther" value="1" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						已关联公文
					</td>
					<td align="left" colspan="5">
						<div id="unionOthersHiddenArea" style="display: none;"></div>
						<div>
							<input type="button" class="btn" id="addRelative" value="关联收文" />
						</div>
						<div id="unionOthersArea">
							<c:forEach items="${incomeDocList}" var="incomeDoc">
								<div id="relative_${incomeDoc.djId}">
									<input type="hidden" id="incomeNo_${incomeDoc.djId}" name="selIncomeNo" value="${incomeDoc.djId}" />
									&nbsp;&nbsp;
									${(empty incomeDoc.optBaseInfo.transaffairname) ? "无标题" : incomeDoc.optBaseInfo.transaffairname}
									[${(empty incomeDoc.optBaseInfo.transAffairNo) ? "无文号" : incomeDoc.optBaseInfo.transAffairNo}]
									<a href="#" onclick="deleteIncomeNo('${incomeDoc.djId}');">
										<img border="0" src="../images/close.png">
									</a>&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</c:forEach>
						</div>
					</td>
				</tr>
				<tr>
						<td class="addTd" width="140">正文模板</td>
						<td align="left" colspan="5">
						<input type="hidden" id="curTemplateId" name="curTemplateId" value="${object.recordId}" />
						<input type="hidden" id="archiveType" name="archiveType" value="zw" />
							
							<span id="addDoc">
								<select id="recordId" name="recordId" onchange="openTemplate(this.value,'zw');">
								 	<option value="">---请选择---</option>
									<c:forEach var="temp" items="${templateList}">
										<option value="${temp.recordId}" <c:if test="${object.recordId==temp.recordId}">selected="selected"</c:if>>
										<c:out value="${temp.descript}" /></option>
									</c:forEach>
								</select>
							</span>
							
							<span id="zwedit"  style="display:none;">
								<span id="fwname"></span>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" id="editdc" onclick="updtDoc();" value="修改正文" class="btn processBtn" />
								<!-- 
								<input type="button" id="deletedc" onclick="delDoc();" value="删除正文" class="btn flowBtn" />
								 -->
							</span>
							
							<script type="text/javascript">
								var curTemplateId = document.getElementById("curTemplateId").value;
								if(curTemplateId != null && curTemplateId != ''){
									document.getElementById("zwedit").style.display="";
									document.getElementById("addDoc").style.display="none";
									
							        var indexx=document.getElementById('recordId').selectedIndex ;
							        var textt=document.getElementById("recordId").options[indexx].text;
									document.getElementById("fwname").innerHTML="拟发文拟稿正文(" + textt + ").doc";
								}
								
							</script>
							
							<c:if test="${object.itemId eq 'SD370000FG-GW-0008' }">
				   <span id="addDn">
				   <input type="hidden" id="dnTemplateId" name="dnTemplateId" value="1386585483609"/>
				  <input type="button" id="viewDn" onclick="editDN();" value="送签说明书" class="btn processBtn" />
					</span>
						<span id="dnedit" style="display:none;">
							<span id="dnname"></span>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="viewdn" onclick="updtDNDoc();" value="修改送签说明书" class="btn processBtn" />
							
						</span>
						
						
						<script type="text/javascript">
							var curTemplateId = document.getElementById("dnTemplateId").value;
							if(curTemplateId != null && curTemplateId != ''){
								document.getElementById("dnedit").style.display="";
								document.getElementById("addDn").style.display="none";
								
								document.getElementById("dnname").innerHTML="送签说明书.doc";
							}
						</script>
					</c:if>
					   </td>
					</tr>
				<tr>
					<td class="addTd">
						办理意见<font color="#ff0000">*</font>
					</td>
					<td align="left" colspan="5">
						<textarea id="transcontent" name="transcontent" style="width: 90%; height: 80px;" ></textarea>
					</td>
				</tr>
				<!-- <tr>
					<td class="addTd">
						附件
					</td>
					<td align="left" colspan="5">
						
					</td>
				</tr> -->
			</table>
		</fieldset>
		<fieldset>
			<legend>材料上传</legend>			
			<!-- 附件上传-->
			<iframe  id="basicsj" name="sjfj" src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&stuffInfo.groupid=${groupid}" width="100%"
							frameborder="no" scrolling="false" border="0" marginwidth="0"
							marginheight="0" onload="this.height=window.frames['sjfj'].document.body.scrollHeight"></iframe>	
			<!-- 附件上传-->	
		</fieldset>
			<center style="margin-top: 10px;display:none;" >
			<s:submit id="submitBtn" name="submitBtn" method="saveAndSubmitFW" cssClass="btn" value="发 送" />
			<s:submit id="saveBtn" name="saveBtn" method="saveFW" cssClass="btn" value="保 存" />
			<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"  onclick="window.history.go(-1);"/>	
		</center>
	</s:form>

<!-- 	<fieldset> -->
<!-- 		<legend>材料上传</legend> -->
<!-- 		<iframe id="basicsj" name="sjfj" width="100%" height="" -->
<!-- 			frameborder="no" scrolling="false" border="0" marginwidth="0" -->
<!-- 			marginheight="0" -->
<%-- 			src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&stuffInfo.groupid=155"> --%>
<!-- 		</iframe> -->
<!-- 	</fieldset> -->
</body>
<script type="text/javascript">
	var canAdjustHeight = true;
	var flowInstId = "";
	var unitString = "${unitString}";
	
	function getOptBaseInfoJson() {
		return ${optCommonBizJson};
	}

	function adjustHeight() {
		if (canAdjustHeight) {
			if (window.parent.document.getElementById("editFrame")) {
				window.parent.document.getElementById("editFrame").style.height = document.body.scrollHeight + "px";
			}
			
			if (window.parent.document.getElementById("d_000_shadow")) {
				window.parent.document.getElementById("d_000_shadow").style.height = window.parent.document.body.scrollHeight + "px";
			}
		}
	}
	

	function checkUnionDispatchUnits() {
		if ("1" == $("#isUnionDispatch").val()) {
			$("#unionDispatchUnitsTr").show();
		} else {
			$("#unionDispatchUnitsTr").hide();
		}
		adjustHeight();
	}
	
	function checkCountersignUnits() {
		if ("1" == $("#isCountersign").val()) {
			$("#countersignUnitsTr").show();
		} else {
			$("#countersignUnitsTr").hide();
		}
		adjustHeight();
	}

	function checkIsUnionOther() {
		if ($("#isUnionOther").attr("checked")) {
			$("#isUnionOther").parent().parent().next().show();
		} else {
			$("#isUnionOther").parent().parent().next().hide();
		}
		adjustHeight();
	}
	
	
	
	function deleteOrganize(orgCode) {
		$.ajax({
			type : "POST",
			url : "<%=request.getContextPath()%>/dispatchdoc/ioDocTasksExcute!deleteFlowOrganizeUnitAjax.do?flowInstId=" + flowInstId + "&roleCode=zbcshq&orgCode=" + orgCode,
			dataType : "json",
			success : function(data) {
				if ("success" == data.status) {
					$("#orgCode_" + orgCode).remove();
					adjustHeight();
				} else {
					alert("删除失败！");
				}
			},
			error : function() {
				alert("删除失败！");
			}
		});
		
		return false;
	}
	

	$(document).ready(function() {
		checkIsUnionOther();
		if ($("div[id^='relative_']").length > 0) {
			$("#isUnionOther").attr("checked", true);
			$("#isUnionOther").parent().parent().next().show();
		}
		
		
		function doCheck() {
			if ("" == $.trim($("#dispatchDocTitle").val())) {
				alert("请输入文件标题");
				return false;
			}
			if ("" == $("#dispatchFileType").val()) {
				alert("请选择文件类型");
				return false;
			}
			if ("1" == $("#isUnionDispatch").val() && $("#unionDispatchUnits").val().length > 500) {
				alert("联合拟发文部门超出最大长度");
				return false;
			}
			if ("" == $.trim($("#dispatchCanOpen").val())) {
				alert("请选择是否信息公开");
				return false;
			}
			if ("1" == $("#dispatchCanOpen").val() && "" == $("#dispatchOpenType").val()) {
				alert("请选择公开方式");
				return false;
			}
			if ("" == $.trim($("#dispatchDocSummary").val())) {
				alert("请输入文件摘要");
				return false;
			}
			if ($("#dispatchDocSummary").val().length > 500) {
				alert("文件摘要超出最大长度");
				return false;
			}
			if ("" == $.trim($("#draftDate").val())) {
				alert("请选择拟文日期");
				return false;
			}
			if ("" == $.trim($("#optUnitName").val())) {
				alert("请输入主办处室");
				return false;
			}
			if ("" == $.trim($("#draftUserName").val())) {
				alert("请输入拟稿人");
				return false;
			}
			if ("" == $("#dispatchDocType").val()) {
				alert("请选择公文种类");
				return false;
			}
//	 		if ("" == $("#retentionPeriod").val()) {
//	 			alert("请选择保管期限");
//	 			return false;
//	 		}
			if ("" == $.trim($("#mainNotifyUnit").val())) {
				alert("请选择主送单位");
				return false;
			}
			
			return true;
		}
		
		function doSubmitCheck() {
			if (!doCheck()) {
				return false;
			}
			
			
			return true;
		}
		$("#addRelative").click(function() {
			JDialog.open({
				src : "${pageContext.request.contextPath}/dispatchdoc/dispatchDoc!incomeDocList.do?dispatchNo=" + $("#djId").val(),
				width : 800,
				height : 350
			});
		});
		
		// 文件摘要文本框输入字数的提示
		$("#unionDispatchUnits").keyup(function() {
			CommonUtils.showTextAreaLimited("unionDispatchUnits", "unionDispatchUnitsMessage", 500);
			return false;
		});
		
		// 文件摘要文本框输入字数的提示
		$("#dispatchDocSummary").keyup(function() {
			CommonUtils.showTextAreaLimited("dispatchDocSummary", "dispatchDocSummaryMessage", 500);
			return false;
		});
		checkUnionDispatchUnits();
		checkCountersignUnits();
		checkIsUnionOther();
		
		$("#isUnionOther").click(function() {
			checkIsUnionOther();
		});
		
		$("#isUnionDispatch").click(function() {
			checkUnionDispatchUnits();
		});
		
		$("#isCountersign").click(function() {
			checkCountersignUnits();
		});
		
		function cloneProjectInfo() { // 将iframe里的字段（除_djId外）克隆到当前页面
			//$("#opinions").val() = null;
			//$("#evaluationContent").val() = null;
			//$("#evaluationReason").val() = null;
		}
		
		$("#saveBtn,#submitBtn").click(function() {
			var id = $(this).attr("id");
			if ("saveBtn" == id) { // 保存
				if (doCheck()) {
					//cloneProjectInfo();
					$("#dispatchDocForm").attr("action", "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!saveFW.do");
					$("#dispatchDocForm").submit();
				}
			} else if ('submitBtn' == id) { // 提交
				if ("" == $("#curTemplateId").val()) {
					alert("请选择正文模板");
					return false;
				}
				if ($("#curTemplateId").val() != $("#recordId").val()) {
					alert("当前正文与选择模板不一致，你保存当前模板所对应的正文");
					return false;
				}
				if (doCheck()) { // 校验通过
					$("#dispatchDocForm").attr("action", "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!saveAndSubmitFW.do");
					$("#dispatchDocForm").submit();
				}
			}
			
			return false;
		});
		
		$("#addCountersignUnits").click(function() {
			JDialog.open({
				src : "${pageContext.request.contextPath}/dispatchdoc/ioDocTasksExcute!selectOrganize.do?roleCode=zbcshq",
				title : "选择机构",
				width : 408,
				height : 340,
				scrolling : "no",
				oDocument: window.parent.document
			});
		});
	});
	

	$("#orgName").click(function(){
		var d = '${unitsJson}';
		$('#attAlert').show();
		if(d.trim().length){
			selectPopWinTemp(jQuery.parseJSON(d),$("#orgName"),$("#orgCode"));
		};
	});

	$("#mainNotifyUnitName,#otherUnitNames").click(function() {
		var id = $(this).attr("id");
		var selectType;
		if ("mainNotifyUnitName" == id) {
			selectType = "radio";
		} else if ("otherUnitNames" == id) {
			selectType = "checkbox";
		} else {
			return false;
		}
		
		var frameId = window.frameElement.id;
		JDialog.open({
			src : "${pageContext.request.contextPath}/dispatchdoc/applyUnitInfo!selectList.do?JDialogLink=yes&selectType=" 
					+selectType + "&frameId=dispatchDocForm",
			width : 800,
			height : 350,
			oDocument: window.document
		});
		
		return false;
	});
	
	function selectPopWin(json,o1,o2 ){
		new person(json,o1,o2).init();
		setAlertStyle("attAlert");
	}
	function selectPopWinTemp(json,o1,o2 ){
		new person(json,o1,o2).init();
		setAlertStyle("attAlert");
	}
	
	function refreshDocRelativeArea() {
		$.ajax({
			type : "POST",
			url : "${contextPath}/dispatchdoc/dispatchDoc!getDocRelatives.do?dispatchNo=" + $("#djId").val(),
			dataType : "json",
			success : function(data) {
				if ("success" == data.status) {
					var buffer = new StringBuffer();
					var array = data.jsonData;
					for (var i=0; i<array.length; i++) {
						var obj = array[i];
						buffer.append("<div id='relative_" + obj["dId"] + "'><input type='hidden' id='incomeNo_" + obj["dId"]);
						buffer.append("' name='selIncomeNo' value='" + obj["dId"] + "' /> &nbsp;&nbsp; ");
						buffer.append((obj["tName"] ? obj["tName"] : "无标题") + " [" + (obj["tNo"] ? obj["tNo"] : "无文号"));
						buffer.append("]<a href='#' onclick='deleteIncomeNo(\"" + obj["dId"] + "\");'>");
						buffer.append("<img border='0' src='../images/close.png'></a>&nbsp;&nbsp;&nbsp;&nbsp;</div>");
					}
					$("#unionOthersArea").empty();
					$(buffer.toString()).appendTo("#unionOthersArea");
					FrameUtils.adjustParentHeight(window);
				} else {
					alert("刷新收文关联列表失败！");
				}
			},
			error : function() {
				alert("刷新收文关联列表失败！");
			}
		});
	}
	
	function deleteIncomeNo(incomeNo) {
		$.ajax({
			type : "POST",
			url : "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!deleteDocRelative.do?incomeNo=" + incomeNo + "&dispatchNo=" + $("#djId").val(),
			dataType : "json",
			success : function(data) {
				if ("success" == data.status) {
					$("#relative_" + incomeNo).remove();
					return true;
				} else {
					alert("刷新收文关联列表失败！");
					return false;
				}
			},
			error : function() {
				alert("刷新收文关联列表失败！");
				return false;
			}
		});
	}
</script>
</html>