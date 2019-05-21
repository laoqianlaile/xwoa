<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="" /></title>
<%-- <sj:head locale="zh_CN" /> --%>

<script src="${pageContext.request.contextPath}/scripts/arrowTree.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrow.js"
	type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />

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
			var djId=$("#djId").val();
			var archiveType = $("#archiveType").val();
			$.ajax({
				type : "POST",
				url : "generalOperator!deleteStuffByArchiveType.do?djId="+djId +"&archiveType="+archiveType+"&nodeInstId=0",
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
			var djId=$("#djId").val();
// 			var archiveType = "sq";
			var nodeInstId = "1386585483609";
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
			$("#addDn").show();
			$("#dnedit").hide();
		
			$("#dnTemplateId").attr("value","");
			
			adjustHeight();
		}
	}
</script>
</head>

<body class="sub-flow" style=" text-align: center;">
	<%@ include file="/page/common/messages.jsp"%>
	<s:form method="post" namespace="/dispatchdoc" action="dispatchDoc"
		id="dispatchDocForm">
		
		
		<input type="hidden" id="roleCode" name="roleCode" value="zbcshq" />
		<input type="hidden" id="moduleCode" name="moduleCode" value="${moduleParam.moduleCode}">
		<ipnut type="hidden" id="curNodeInstId" name="curNodeInstId" value="${curNodeInstId}">
		
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="startDjId" name="startDjId" value="${startDjId}" />
		<input type="hidden" id="sd" name="sd" value="${sd}" />
		<input type="hidden" id="nodeId" name="nodeId" value="${nodeInstId}" />
		<input type="hidden" id="itemtype" name="itemtype" value="FW"/>
		<input type="hidden" id="optId" name="optBaseInfo.optId"
			value="${object.optBaseInfo.optId}" />
		<input type="hidden" id="wfcode" name="optBaseInfo.flowCode"
			value="${object.optBaseInfo.flowCode}" />
		<input type="hidden" id="powerid" name="optBaseInfo.powerid"
			value="${object.itemId}" />
		<input type="hidden" id="powername" name="optBaseInfo.powername"
			value="${cp:MAPVALUE('suppowerId', object.itemId)}" />
		<input type="hidden" id="internalNo" name="internalNo"
			value="${object.internalNo}" />
		<input type="hidden" id="optUnitName" name="optUnitName"
			value="${object.optUnitName}" />
		<fieldset >
			<legend style=" text-align: left;">
			拟文单       [ ${object.optBaseInfo.powername}]
			</legend>
			<div align="left">
			<c:if test="${empty dashboard}">
			<c:if test="${not empty sd }">
				<input type="button" class="btn" onclick="window.history.back();"
					value="返回" />
			</c:if>
			<c:if test="${ empty sd }">
				<input type="button" class="btn" id="saveBtn" value="保存" />
			</c:if>
			<input type="button" class="btn" id="submitBtn" value="提交" />
		</c:if>
		<c:if test="${not empty dashboard }">
			<input type="button" class="btn" id="saveBtn"   value="保存"/>
			<input type="button" class="btn btnWide" id="submitBtn"  value="提交"/>
		</c:if>
		</div>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable" style="  position:relative;width:90%;margin-left: auto;margin-right: auto;">
				<tr>
							<td class="addTd">
								发文类别<font color="#ff0000">*</font>
							</td>
							<td  align="left">
							 	<input type="radio" name="dispatchDocType" id="dispatchDocType" value="PT" checked />普通
							 	<input type="radio" name="dispatchDocType" id="dispatchDocType" value="ZG"  />中共
						    </td>
						    <td class="addTd">
								发文文种</font>
							</td>
							<td  align="left">
							 		<select id="fwwz" style="width:200px;height:30px;" name="fwwz">
							 			<option value="">--请选择--</option>
										<c:forEach var="row" items="${cp:DICTIONARY('fw_fwwz') }">
											<option value="${row.datacode}"
												<c:if test="${row.datacode==object.fwwz}"> selected="selected"</c:if>>
												<c:out value="${row.datavalue}" />
											</option>
										</c:forEach>
								   </select>
						    </td>
				</tr>
				
				<tr>
					<td class="addTd">文件标题<font color="#ff0000">*</font>
					</td>
					<td align="left" colspan="3" ><input type="text"
						id="dispatchDocTitle" name="dispatchDocTitle" maxlength="66"
						value="${object.dispatchDocTitle}" style="width: 100%; height: 30px"/>
					</td>

				</tr>

				<tr>
					<td class="addTd">发文代<span style="font-family:Simsun !important">字</span><font color="#ff0000">*</font>
					</td>
					<td align="left" class="hidden"><select id="dispatchFileType"
						name="dispatchFileType" style="width: 200px;height: 30px;">
							<option value="">--请选择--</option>
							<c:forEach var="row" items="${dictionaryZWH}">
								<option value="${row.key}"
									${(object.dispatchFileType eq row.key or (empty object.dispatchFileType and row.key eq '0')) ? 'selected = "selected"' : ''}>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
					<td class="addTd">经办人<font color="#ff0000">*</font>
					</td>
					<td align="left"><input type="text" id="draftUserName"
						name="draftUserName" maxlength="100"
						style="width: 50%;; height: 30px" value="${object.draftUserName}" />
					</td>
				</tr>
				
				<tr>
					<td class="addTd">是否信息公开<font color="#ff0000">*</font>
					</td>
					
					<td align="left" >
					<c:forEach var="row" items="${cp:DICTIONARY('FW_CAN_OPEN')}" varStatus="status">
					         <input type="radio"  name="dispatchCanOpen" value="${row.key}" ${(object.dispatchCanOpen eq row.key or (empty object.dispatchCanOpen and status.index eq '0')) ? 'checked = "checked"' : ''}/><c:out value="${row.value}" />
					</c:forEach>
                     </td>
					<td class="addTd">紧急程度</td>
					<td align="left" >
					<c:forEach var="row" items="${cp:DICTIONARY('critical_level')}" varStatus="status">
					         <input type="radio"  name="criticalLevel" value="${row.key}" ${(object.criticalLevel eq row.key or (empty object.criticalLevel and status.index eq '0')) ? 'checked = "checked"' : ''}/><c:out value="${row.value}" />
					</c:forEach>
					
					</td>
				</tr>
				<tr id="tr_notOpenReason" style="display: none;">
					<td class="addTd">不予公开理由
					</td>
					<td align="left" colspan="3">
						<c:forEach var="row" items="${cp:DICTIONARY('FW_NOT_OPEN')}" varStatus="status">
					         <input type="radio" id="notOpenReason"  name="notOpenReason" value="${row.key}" ${(object.notOpenReason eq row.key ) ? 'checked = "checked"' : ''}/><c:out value="${row.value}" />
						</c:forEach>
					
                     </td>
				</tr>
				
				<tr>
					<td class="addTd">拟文日期<font color="#ff0000">*</font>
					</td>
					<td align="left"><input type="text" class="Wdate"
						id="draftDate"
						style="height: 30px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${object.draftDate}" pattern="yyyy-MM-dd"/>'
						name="draftDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
						placeholder="选择日期"></td>
                    <td class="addTd">办结截止日期
					</td>
					<td align="left"><input type="text" class="Wdate"
						id="toBeanfinishedDate"
						style="height: 30px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${object.toBeanfinishedDate}" pattern="yyyy-MM-dd"/>'
						name="toBeanfinishedDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
						placeholder="选择日期"></td>
					
					
				</tr>
				
				<tr>
						<td class="addTd">印数<!-- <font color="#ff0000">*</font> -->
					</td>
					<td align="left" ><input type="text" id="dispatchCopies" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
						name="dispatchCopies" style="height: 30px; width: 100%;"
						value="${object.dispatchCopies}" /></td>
					<%-- <td class="addTd">印数(维)<!-- <font color="#ff0000">*</font> -->
					</td>
					<td align="left"><input type="text" id="dispatchcopiew"
						name="dispatchcopiew" style="height: 30px; width: 100%;"
						value="${object.dispatchcopiew}" /></td> --%>
						
					<td class="addTd">密级<!-- <font color="#ff0000">*</font> -->
					</td>
					<td align="left">
					<select id="secretsDegree" style="width:200px;height:30px;" name="secretsDegree">
							<c:forEach var="row" items="${cp:DICTIONARY('GDMJ') }">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==object.secretsDegree}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select></td> 
				</tr>
				
				<tr>
					<td class="addTd">主送单位<font color="#ff0000">*</font>
					</td>
					<td align="left" colspan="3" class="hidden"><input type="hidden"
						id="mainNotifyUnitCode" name="mainNotifyUnitCode" /> <input
						type="text" id="mainNotifyUnit" name="mainNotifyUnit"
						maxlength="100" style="width: 100%;; height: 30px"
						value="${object.mainNotifyUnit}" /></td>
				</tr>
				<tr>
					<td class="addTd">抄送单位</td>
					<td align="left" colspan="3" class="hidden"><input type="hidden"
						id="otherUnitCodes" name="otherUnitCodes" /> <input type="text"
						id="otherUnits" name="otherUnits" maxlength="100"
						style="width: 100%;; height: 30px" value="${object.otherUnits}" />
					</td>
				</tr>
				<%-- <tr>
					<td class="addTd">主题词<!-- <font color="#ff0000">*</font> -->
					</td>
					<td align="left" colspan="3" class="hidden"><input type="text"
						id="dispatchTitle" name="dispatchTitle"
						style="height: 30px; width: 100%;" value="${object.dispatchTitle}" />
					</td>
				</tr> --%>
				<tr>
					<td class="addTd">事由
					</td>
					<td align="left" colspan="3" class="hidden"><textarea id="dispatchDocSummary"
							name="dispatchDocSummary" style="width: 100%; height: 50px;">${object.dispatchDocSummary}</textarea>
						<span id="dispatchDocSummaryMessage">&nbsp;</span></td>
				</tr>
				<tr>
					<td class="addTd" width="140">正文</td>
					<td align="left" colspan="3" class="hidden"><input type="hidden"
						id="curTemplateId" name="curTemplateId" value="${object.recordId}" />
						<input type="hidden" id="archiveType" name="archiveType"
						value="zw" /> <span id="addDoc"> <select id="recordId" style="height:30px;"
							name="recordId" onchange="openTemplate(this.value,'zw');">
								<option value="">--请选择--</option>
								<c:forEach var="temp" items="${templateList}">
									<option value="${temp.recordId}"
										${object.recordId eq temp.recordId ? 'selected' : ''}>
										<c:out value="${temp.descript}" /></option>
								</c:forEach>
						</select>
					</span> <span id="zwedit" style="display: none;"> <span id="fwname"></span>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="viewdc" onclick="updtDoc();"
							value="修改正文" class="btn processBtn" /> <input type="button"
							id="deletedc" onclick="delDoc();" value="删除正文"
							class="btn flowBtn" />
					</span> <script type="text/javascript">
							var curTemplateId = document.getElementById("curTemplateId").value;
							if(curTemplateId != null && curTemplateId != ''){
								document.getElementById("zwedit").style.display="";
								document.getElementById("addDoc").style.display="none";
								
						        var indexx=document.getElementById('recordId').selectedIndex ;
						        var textt=document.getElementById("recordId").options[indexx].text;
             
						        // document.getElementById("fwname").innerHTML="发文拟稿正文(" + textt + ").doc";
                                 //正文标题为文件标题
                                 
                                textt=document.getElementById("dispatchDocTitle").value;
						        document.getElementById("fwname").innerHTML=textt + ".doc";
							}
							
						</script></td>
						</tr>
				 <!-- 办件角色  登记页面选择办件角色：对应通用模块--fw_dj -->
				<c:if test="${moduleParam.assignTeamRole eq 'T' }">
				    
				    <tr id="zbOrgRoleCodeTr" >
					<td class="addTd" width="140">${moduleParam.teamRoleName}<font color="red">*</font></td>
					<td align="left" colspan="3">
							<input type="text" id="bjUserNames" name="bjUserNames" style="width:100%;height: 30px;" value="${bjUserNames}"  readonly="readonly" />
							<input type="hidden" id="bjCodes" name="teamUserCodes" value="${teamUserCodes}" />
							<input type="hidden" id="roleCode" name="teamRoleCode" value="${moduleParam.teamRoleCode}" />
					</td>
					</tr>
				 </c:if>
				<tr>
					<td class="addTd">办理备注
					</td>
					<td align="left" colspan="3"><textarea id="transcontent"
							name="transcontent"  style="width: 90%; height: 50px; value=">${transcontent}</textarea>
					</td>
				</tr>
				
				<!-- 是否发送短信	短信功能开发且配置为发送 -->
			<c:if test="${'T' eq cp:MAPVALUE('sendMSg','isopen')}">	
			<tr>
			   <td class="addTd">短信提醒&nbsp;</td>
			   <td align="left" colspan="3">
				<input id="isSendMsgView" class="checkboxClass" type="checkbox"
						name="isSendMsgView" />短信提醒下一步操作人员
				<input type="hidden" id="isSendMessage" name="isSendMessage" />
					
				</td>
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
				
				
			</table>
									<div id="attAlert" class="alert"
							style="width: 600px; height: 330px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
							<h4>
								<span>选择</span><span id="close2"
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
									<b class="btns"><input id="save" class="btn" type="button"
										value="保      存" /><input id="clear" class="btn" type="button"
										value="取      消" style="margin-top: 5px;" /></b>
								</div>
							</div>
						</div>
		</fieldset>
		<fieldset>
			<legend>材料上传</legend>
			<!-- 附件上传-->
			<iframe id="basicsj" name="sjfj"
				src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&stuffInfo.groupid=${groupid}"
				width="100%" frameborder="no" scrolling="false" border="0"
				marginwidth="0" marginheight="0"
				onload="this.height=window.frames['sjfj'].document.body.scrollHeight"></iframe>
			<!-- 附件上传-->
		</fieldset>
	</s:form>
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
		
		if ("1" == $.trim($('input:radio[name="isCountersign"]:checked').val())) {
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
				$('#dispatchDocTitle').focus();
				return false;
			}
			if ("" == $("#dispatchFileType").val()) {
				alert("请选择拟发文代字");
				$('#dispatchFileType').focus();
				return false;
			}
			
			/* if ("" == $.trim($("#dispatchDocType").val())) {
				alert("发文类别不可为空！");
				$('#dispatchDocType').focus();
				return false;
			} */
			
			if ("1" == $.trim($('input:radio[name="isCountersign"]:checked').val()) ) {
				if ("" == $.trim($("#userCodes").val())) {				
				alert("请选择会签处室经办人");
				return false;
				}
			}
			if ("" == $.trim($('input:radio[name="dispatchCanOpen"]:checked').val())) {
				alert("请选择是否信息公开");
				$('#dispatchCanOpen').focus();
				return false;
			}
			/* if ("1" == $("#dispatchCanOpen").val() && "" == $("#dispatchOpenType").val()) {
				alert("请选择公开方式");
				$('#dispatchCanOpen').focus();
				return false;
			} */
			/* if ("" == $.trim($("#dispatchDocSummary").val())) {
				alert("请输入文件摘要");
				$('#dispatchDocSummary').focus();
				return false;
			} */
			if ($("#dispatchDocSummary").val().length > 500) {
				alert("文件摘要超出最大长度");
				return false;
			}
			if ("" == $.trim($("#draftDate").val())) {
				alert("请选择拟文日期");
				$('#draftDate').focus();
				return false;
			}
			if ("" == $.trim($("#draftUserName").val())) {
				alert("请输入经办人");
				$('#draftUserName').focus();
				return false;
			}
			/* if ("" == $("#dispatchDocType").val()) {
				alert("请选择公文种类");
				
				return false;
			} */
			/* if ("" == $("#dispatchCopies").val()) {
				alert("请输入印数(汉)");
				$('#dispatchCopies').focus();
				return false;
			} */
			
			var r="^[0-9]\\d*$";
			if($("#dispatchCopies").val()!=''){
			var isvalid= (new RegExp(r)).test($("#dispatchCopies").val());
			if(!isvalid){
				alert("输入的印数(汉)格式不正确,只能输入正整数 ");
				$("#dispatchCopies").focus();
				return false;
			}
			}
			
			/* if ("" == $("#dispatchcopiew").val()) {
				alert("请输入印数(维)");
				$('#dispatchcopiew').focus();
				return false;
			} */
			
			/* if($("#dispatchcopiew").val()!=''){
				var isvalid= (new RegExp(r)).test($("#dispatchcopiew").val());
				if(!isvalid){
					alert("输入的印数(维)格式不正确,只能输入正整数 ");
					$("#dispatchcopiew").focus();
					return false;
				}
				} */
			
			/* if ("" == $("#dispatchTitle").val()) {
				alert("请输入主题词");
				$("#dispatchTitle").focus();
				return false;
			} */
			if ("" == $("#mainNotifyUnit").val()) {
				alert("请输入主送单位");
				$("#mainNotifyUnit").focus();
				return false;
			}
			/* if ("" == $("#transcontent").val()) {
				alert("请输入办理意见");
				$("#transcontent").focus();
				return false;
			} */
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
				src : "${pageContext.request.contextPath}/dispatchdoc/dispatchDoc!incomeDocList.do?subitemtype=GW&dispatchNo=" + $("#djId").val(),
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
		
		$('input:radio[name="isCountersign"]').click(function() {
			checkCountersignUnits();
		});
		
		
		$("#saveBtn,#submitBtn").click(function() {
			var id = $(this).attr("id");
			if ("saveBtn" == id) { // 保存
				if (doCheck()) {
					//cloneProjectInfo();
					$("#dispatchDocForm").attr("action", "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!saveDispatchDoc.do");
					 openLoadIng(true);
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
				if ($("#powerid").val() == 'SD370000FG-GW-0008') {
					if ("" == $("#dnTemplateId").val()) {
						alert("请保存会签说明书");
						return false;
					}
				}
				
               // 				办件角色验证
				if(_get('bjCodes') != undefined && '${moduleParam.assignTeamRole}' == 'T'
					&& !$("#zbOrgRoleCodeTr").is(":hidden")){
				if(trim(_get('bjCodes').value).length==0){
					alert("${moduleParam.teamRoleName}不能为空。");			
					return false;
				}
			    }
				
				if (doCheck()) { // 校验通过
					$("#dispatchDocForm").attr("action", "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!saveAndSubmitDispatchDoc.do");
					if("${sd}"!=null&&"${sd}"!=("")){
						if(confirm("您是否确认发起关联拟发文业务？")){
							openLoadIng(true);
							$("#dispatchDocForm").submit();
						}
					}else{
						openLoadIng(true);
						$("#dispatchDocForm").submit();
						}
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
		
		
		
	     //是否信息公开联动
	    $('input:radio[name="dispatchCanOpen"]').bind("click",
				function(e) {
	    	       var dispatchCanOpen = $.trim($('input:radio[name="dispatchCanOpen"]:checked').val()); // 取radio
	    	       if('2'==dispatchCanOpen){
	    	    	   $("#tr_notOpenReason").show();
	    	    	   
	    	       }else{
	    	    	   $("#tr_notOpenReason").hide();
	    	    	   $("input[name='notOpenReason']").removeAttr('checked');
	    	       }
				});	
	});
	

	$("#orgName").click(function(){
		var d = '${unitsJson}';
		$('#attAlert').show();
		if(d.trim().length){
			selectPopWinTemp(jQuery.parseJSON(d),$("#orgName"),$("#orgCode"));
		};
	});

	$("#bjUserNames2").click(
			function() {
				var d = '${userjson2}';
				if (d.trim().length) {
					selectPopWinPersonTree(jQuery.parseJSON(d),
							$("#bjUserNames2"),
							$("#userCodes"));
				}
				;
			});
	
	$("#bjUserNames").click(
			function() {
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWinPersonTree(jQuery.parseJSON(d),
							$("#bjUserNames"),
							$("#bjCodes"));
				}
				;
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
	function selectPopWinPersonTree(json, o1, o2, oShow) {
// 		new treePerson(json, o1, o2, oShow,1).init();/* 此处人员限制为一人 */
        new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert",($(window).height()-136)/2-110);
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
						buffer.append("<a href='#' onclick='showTransAffair(\"" + obj["dId"] + "\",\"" + obj["tFlowinstid"] + "\");'>");
						buffer.append((obj["tName"] ? obj["tName"] : "无标题") + " [" + (obj["tNo"] ? obj["tNo"] : "无文号"));
						buffer.append("</a>");
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
			url : "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!deleteDocRelative.do?incomeNo="
							+ incomeNo + "&dispatchNo=" + $("#djId").val(),
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

	$("#attUserNames").click(
			function() {
				var s = '${attentionJson}';
				if (s.trim().length) {
					selectPopWin(jQuery.parseJSON(s), $("#attUserNames"),
							$("#attUserCodes"));
				}
			});

	function selectPopWinTemp(json, o1, o2) {
		new person(json, o1, o2).init();
		setAlertStyle("attAlert");
	}
	function showTransAffair(djid, flowinstid) {
		var url = "${pageContext.request.contextPath}/dispatchdoc/ioDocTasksExcute!transaffairView.do?djId="
				+ djid + "&flowInstId=" + flowinstid;
		window.open(url);
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