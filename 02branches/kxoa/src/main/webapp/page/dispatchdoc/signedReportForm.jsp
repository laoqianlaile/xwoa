<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="签报登记" />
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
		<script src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	function openTemplate(recordId,archiveType){
		if(recordId  == "" || recordId == null){
			return;
		}
		openDocEdit(recordId,archiveType);
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
	
	/****会签说明书****/
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
	function delDoc(){
		if(window.confirm("确认删除当前正文？")){
			var djId=$("#djid").val();
			var archiveType = $("#archiveType").val();
			var nodeInstId = $("#nodeInstId").val();
			$.ajax({
				type : "POST",
				url : "generalOperator!deleteStuffByArchiveType.do?djid="+djId +"&archiveType="+archiveType+"&nodeInstId="+nodeInstId,
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
// 			$("#archiveType").attr("value","");
			$("#curTemplateId").attr("value","");
			
			adjustHeight();
		}
	}
	
	function delDNDoc(archiveType){
		if(window.confirm("确认删除会签说明书？")){
			var djId=$("#djid").val();
// 			var archiveType = "sq";
			var nodeInstId = "1386585483609";
			$.ajax({
				type : "POST",
				url : "generalOperator!deleteStuffByArchiveType.do?djid="+djId +"&archiveType="+archiveType+"&nodeInstId="+nodeInstId,
				success : function(data) {
					alert("删除成功");
				},
				error : function() {
					alert("失败");
				}
			}); 
			$("#addDn").show();
			$("#dnedit").hide();
		
			$("#dnTemplateId").attr("value","");
			
			adjustHeight();
		}
	}
</script>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<s:form action="signedReport" method="post" namespace="/dispatchdoc" id="signedReportForm">
		    <c:if test="${empty show_type}">
			<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
			</c:if>
			<input type="button" class="btn" id="save" name="saveBtn"  onclick="doSubmitCheck('SAVE');" value="保存"/>
			<input type="button" class="btn btnWide" id="saveAndSubmit" name="submitBtn" onclick="doSubmitCheck('SUB');" value="保存并提交"/>
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="optId" name="optId" value="${optId}" />
			<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
			<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">
			<input type="hidden" id="flowInstId" name="optBaseInfo.flowInstId" value="${flowInstId}" />	
			<input type="hidden" id="m" name="m"/>							
			<fieldset class="_new">
					<legend style="padding:4px 8px 3px;">
						<b>签报登记</b>
					</legend>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<%-- 		<tr>
							<td class="addTd">
						权力事项
					</td>
					<td align="left" colspan="3">
						<input type="hidden" id="itemId" name="itemId" value="${object.optBaseInfo.powerid}">
						<input type="text" id="powername" name="optBaseInfo.powername" value="${object.optBaseInfo.powername}" style="width: 100%;height: 24" readonly="readonly" disabled="disabled">
  						<input type="button" class='btn' name="powerBtn" onClick="openNewWindow('<%=request.getContextPath()%>/powerbase/supPower!listSupPower_Sd.do?itemType=XK&s_itemType=XK&s_orgId=${session.SPRING_SECURITY_CONTEXT.authentication.principal.primaryUnit}','powerWindow',null);" value="选择">
					</td>
						</tr> --%>
						<input type="hidden" id="itemId" name="itemId" value="${object.optBaseInfo.powerid}">
						<tr>						
							<td class="addTd">
							文件标题<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="3">
						<input type="text" id="signedReportTitle" name="signedReportTitle" maxlength="200" value="${object.signedReportTitle}" style="width: 100%;height: 24"/>
							</td>							
						</tr>
						<tr>
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
						紧急程度
					</td>
					<td align="left">
						<select id="criticalLeveal" name="criticalLeveal" style="width: 180px">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('critical_level')}">
								<option value="${row.key}" ${(object.criticalLeveal eq row.key or (empty object.criticalLeveal and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
						</select>
					</td>	

						</tr>
						<tr>
							<td class="addTd">
								签报号<font color="#ff0000">*</font>
							</td>
							<td align="left">
								<input type="text" id="signedReportNo" name="signedReportNo" maxlength="200" value="${object.signedReportNo}" style="width: 100%;height: 24"/>
							</td>
								<td class="addTd">
								签报日期<font color="#ff0000">*</font>
							</td>
							<td align="left">
				<input type="text" class="Wdate"id="signedDate" name="signedDate" 
				style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
				value='<fmt:formatDate value="${object.signedDate}" pattern="yyyy-MM-dd"/>'
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
							</td>
						</tr>
						<tr>
							<td class="addTd">
								签报人<font color="#ff0000">*</font>
							</td>
							<td align="left">
								<input type="text" id="signedPersion" name="signedPersion" maxlength="50" value="${object.signedPersion}" style="width: 100%;height: 24"/>
							</td>
							<td class="addTd">
								会签部门
							</td>
							<td align="left">
				         	<input type="text" id="otherDep" name="otherDep" maxlength="200" value="${object.otherDep}" style="width: 100%;height: 24"/>

							</td>
						
						</tr>
						<tr>
							<td class="addTd">
						       主办部门<font color="#ff0000">*</font>
						</td>
						<td align="left" colspan="3">
							<input type="text" id="mainSignedDep" name="mainSignedDep" maxlength="100" value="${object.mainSignedDep}" ${empty zbcs ? "" : "disabled=true"} style="width: 100%;height: 24"/>
						</td>
						</tr>
						<tr>
					<td class="addTd">
						拟稿人<font color="#ff0000">*</font>
					</td>
					<td align="left">
						<input type="text" id="draftUserName" name="draftUserName" maxlength="100" value="${object.draftUserName}" style="width: 100%;height: 24"/>
					</td>
							<td class="addTd">
						拟稿人电话
					</td>
					<td align="left">
						<input type="text" id="draftTelephone" name="draftTelephone" maxlength="100" value="${object.draftTelephone}" style="width: 100%;height: 24"/>
					</td>
						</tr>
              	<tr>
				<td class="addTd">
							保管期限
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
					<td class="addTd" width="140">正文</td>
					<td align="left" colspan="5">
						<input type="hidden" id="curTemplateId" name="curTemplateId" value="${object.recordId}" />
						<input type="hidden" id="archiveType" name="archiveType" value="zw" />
						
						<span id="addDoc">
							<select id="recordId" name="recordId" onchange="openTemplate(this.value,'zw');">
							 	<option value="">---请选择---</option>
								<c:forEach var="temp" items="${templateList}">
									<option value="${temp.recordId}" ${object.recordId eq temp.recordId ? 'selected' : ''}>
									<c:out value="${temp.descript}" /></option>
								</c:forEach>
							</select>
						</span>
						
						<span id="zwedit" style="display:none;">
							<span id="fwname"></span>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="viewdc" onclick="updtDoc();" value="修改正文" class="btn processBtn" />
							<input type="button" id="deletedc" onclick="delDoc();" value="删除正文" class="btn flowBtn" />
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
				
				</table>
				</fieldset>			
		</s:form>		
		<br/><br/>
		<fieldset class="_new">
			<legend style="padding:4px 8px 3px;">
				<b>材料上传</b>
			</legend>
			<iframe id="basicsj" name="sjfj" width="100%" height="" frameborder="no" scrolling="false" border="0" marginwidth="0" marginheight="0"
				src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&stuffInfo.groupid=${groupid}"
				onload="this.height=window.frames['basicsj'].document.body.scrollHeight">
			</iframe>
		</fieldset>
	</body>
	<script type="text/javascript">

	
		function doSubmitCheck(subType) {	
			if ("" == $.trim($("#signedReportTitle").val())) {
				alert("文件标题不可为空！");
				$('#signedReportTitle').focus();
				return false;
			}
			if ("" == $.trim($("#signedReportNo").val())) {
				alert("签报号不可为空！");
				$('#signedReportNo').focus();
				return false;
			}
			if ("" == $.trim($("#signedDate").val())) {
				alert("签报日期不可为空！");
// 				$('#signedDate').focus();
				return false;
			}
			if ("" == $.trim($("#signedPersion").val())) {
				alert("签报人不可为空！");
				$('#signedPersion').focus();
				return false;
			}
	
			if ("" == $.trim($("#mainSignedDep").val())) {
				alert("主办部门不可为空！");
				$('#mainSignedDep').focus();
				return false;
			}
			if ("" == $.trim($("#draftUserName").val())) {
				alert("拟稿人不可为空！");
				$('#draftUserName').focus();
				return false;
			}
			if($("#draftTelephone").val()!=null&&  getStringLen($("#draftTelephone").val())>12){
				alert("拟稿人手机号不可 超过11位！");
				$('#draftTelephone').focus();
				return false;
			}
			if ("" == $("#curTemplateId").val()) {
				alert("请选择正文模板");
				return false;
			}
			if ($("#curTemplateId").val() != $("#recordId").val()) {
				alert("当前正文与选择模板不一致，你保存当前模板所对应的正文");
				return false;
			}
			if ($("#transcontent").val() =="") {
				alert("请填写办理意见！");
				return false;
			}
			var srForm = document.getElementById("signedReportForm");
			if (subType == 'SAVE') {
				srForm.action = 'signedReport!saveRegisterDoOrRead.do';
			}

			if (subType == 'SUB') {
				srForm.action = 'signedReport!saveAndSubmitRegisterDoOrRead.do';
			}
			srForm.submit();
		}
		
		function getStringLen(Str) {
			var i, len, code;
			if (Str == null || Str == "")
				return 0;
			len = Str.length;
			for (i = 0; i < Str.length; i++) {
				code = Str.charCodeAt(i);
				if (code > 255) {
					len++;
				}
			}
			return len;
		}
	
		function getOptBaseInfoJson() {
			return ${optCommonBizJson};
		}

	</script>
</html>

