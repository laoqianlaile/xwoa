<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="" /></title>
	<link href="${contextPath}/themes/css/alertDiv.css" rel="stylesheet" type="text/css" />
		<script src="${contextPath}/scripts/alertDiv.js" type="text/javascript"></script>
		<script src="${contextPath}/scripts/arrow.js" type="text/javascript"></script>
		
		<link href="${contextPath}/themes/css/arrow.css" rel="stylesheet" type="text/css" />		
<%-- <sj:head locale="zh_CN" /> --%>

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
		if (curTemplateId != null && curTemplateId != val) {
			if(window.confirm("重新选择模板会生成新的文件，已保存的内容将被覆盖，是否确定？")){
				docAction = "ZW_ADD";
			}	
		}
			
		var param = "flowStep=" +  docAction +"&RecordID=${djId}&Template=" + val +"&archiveType="+archiveType +"&NeedBookMark=1";
		
		openNewWindow(uri + "?"+ param,'editForm','');
	}
	
	//修改文档
	function updtDoc(){
		var archiveType =$("#archiveType").val();
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
			var nodeInstId = 0;
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
			//$("#archiveType").attr("value","zw");
			$("#curTemplateId").attr("value","");
			
			adjustHeight();
		}
	}
</script>
</head>

<body class="sub-flow" style=" text-align: center;">
	<%@ include file="/page/common/messages.jsp"%>
	<s:form method="post" namespace="/dispatchdoc" action="dispatchDoc!saveDispatchDocByIncomeDoc.do" name="dispatchDocForm" id="dispatchDocForm">
		
	
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />
		<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
		
		<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
			<legend style="padding: 4px 8px 3px;">
				<b>拟文单</b>
			</legend>
			
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable" style="  position:relative;width:98%;margin-left: auto;margin-right: auto;">
				<tr>
					<td class="addTd">文件标题<font color="#ff0000">*</font>
					</td>
					<td align="left" colspan="3" ><input type="text"
						id="dispatchDocTitle" name="dispatchDocTitle" maxlength="200"
						value="${object.dispatchDocTitle}" style="width: 100%; height: 24"/>
					</td>

				</tr>

				<tr>
					<td class="addTd">发文代<span style="font-family:Simsun !important">字</span><font color="#ff0000">*</font>
					</td>
					<td align="left" class="hidden"><select id="dispatchFileType"
						name="dispatchFileType" style="width: 180px">
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
						style="width: 50%;; height: 24" value="${object.draftUserName}" />
					</td>
				</tr>
				<tr>
					<td class="addTd">是否信息公开<font color="#ff0000">*</font>
					</td>
					<td align="left">
					<c:forEach var="row" items="${cp:DICTIONARY('FW_CAN_OPEN')}" varStatus="status">
					         <input type="radio"  name="dispatchCanOpen" value="${row.key}" ${(object.dispatchCanOpen eq row.key or (empty object.dispatchCanOpen and status.index eq '0')) ? 'checked = "checked"' : ''}/><c:out value="${row.value}" />
					</c:forEach>
					
                     </td>
					<td class="addTd">紧急程度</td>
					<td align="left">
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
							<td class="addTd">
								发文类别<font color="#ff0000">*</font>
							</td>
							<td colspan="3">
						 	<input type="radio" name="dispatchDocType" id="dispatchDocType" value="PT"  />普通
						 	<input type="radio" name="dispatchDocType" id="dispatchDocType" value="ZG"  />中共
						    </td>
						</tr>
				<tr>
					<td class="addTd">拟文日期<font color="#ff0000">*</font>
					</td>
					<td align="left"><input type="text" class="Wdate"
						id="draftDate"
						style="height: 24px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${object.draftDate}" pattern="yyyy-MM-dd"/>'
						name="draftDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
						placeholder="选择日期"></td>
                    <td class="addTd">办结截止日期
					</td>
					<td align="left"><input type="text" class="Wdate"
						id="toBeanfinishedDate"
						style="height: 24px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${object.toBeanfinishedDate}" pattern="yyyy-MM-dd"/>'
						name="toBeanfinishedDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
						placeholder="选择日期"></td>
					
					
				</tr>
				
				<tr>
						<td class="addTd">印数<!-- <font color="#ff0000">*</font> -->
					</td>
					<td align="left" ><input type="text" id="dispatchCopies"
						name="dispatchCopies" style="height: 24px; width: 100%;"
						value="${object.dispatchCopies}" /></td>
					<%-- <td class="addTd">印数(维)<!-- <font color="#ff0000">*</font> -->
					</td>
					<td align="left"><input type="text" id="dispatchcopiew"
						name="dispatchcopiew" style="height: 24px; width: 100%;"
						value="${object.dispatchcopiew}" /></td> --%>
						
					<td class="addTd">密级<!-- <font color="#ff0000">*</font> -->
					</td>
					<td align="left">
					<select id="secretsDegree" style="width:200px;height:25px;" name="secretsDegree">
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
						maxlength="100" style="width: 100%;; height: 24"
						value="${object.mainNotifyUnit}" /></td>
					
				</tr>
				</tr>
				<tr>
					<td class="addTd">抄送单位</td>
					<td align="left" colspan="3" class="hidden"><input type="hidden"
						id="otherUnitCodes" name="otherUnitCodes" /> <input type="text"
						id="otherUnits" name="otherUnits" maxlength="100"
						style="width: 100%;; height: 24" value="${object.otherUnits}" />
					</td>
				</tr>
				<tr>
					<td class="addTd">主题词<!-- <font color="#ff0000">*</font> -->
					</td>
					<td align="left" colspan="3" class="hidden"><input type="text"
						id="dispatchTitle" name="dispatchTitle"
						style="height: 24px; width: 100%;" value="${object.dispatchTitle}" />
					</td>
				</tr>
				<tr>
					<td class="addTd">事由
					</td>
					<td align="left" colspan="3" class="hidden"><textarea id="dispatchDocSummary"
							name="dispatchDocSummary" style="width: 100%; height: 50px;">${object.dispatchDocSummary}</textarea>
						<span id="dispatchDocSummaryMessage">&nbsp;</span></td>
				</tr>
				<tr id="templateTr">
					<td class="addTd">正文模板<font color="#ff0000">*</font></td>
					<td align="left" colspan="3">
						<input type="hidden" id="curTemplateId" name="curTemplateId" value="${object.recordId}" />
						<input type="hidden" id="archiveType" name="archiveType" value="zw" />
						
						<div id="addDoc">
							<select id="recordId" name="recordId" onchange="openTemplate(this.value,'zw');">
							 	<option value="">---请选择---</option>
								<c:forEach var="temp" items="${templateList}">
									<option value="${temp.recordId}" ${object.recordId eq temp.recordId ? 'selected' : ''}><c:out value="${temp.descript}" /></option>
								</c:forEach>
							</select>
						</div>
						
						<div id="zwedit" style="display:none;">
							<span id="fwname"></span>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="viewdc" onclick="updtDoc();" value="修改正文" class="btn processBtn" />
							<input type="button" id="deletedc" onclick="delDoc();" value="删除正文" class="btn flowBtn" />
						</div>
						
						
						<script type="text/javascript">
							var curTemplateId = document.getElementById("curTemplateId").value;
							if(curTemplateId != null && curTemplateId != ''){
								document.getElementById("zwedit").style.display="";
								document.getElementById("addDoc").style.display="none";
								
						        var indexx=document.getElementById('recordId').selectedIndex ;
						        var textt=document.getElementById("recordId").options[indexx].text;
                                 
                                textt=document.getElementById("dispatchDocTitle").value;
						        document.getElementById("fwname").innerHTML=textt + ".doc";
							}
							
						</script>
				   </td>
				</tr>
			</table>
		</fieldset>
			
		<fieldset class="_new">
			<legend style="padding:4px 8px 3px;">
				<b>材料上传</b>
			</legend>
			<iframe id="basicsj" name="sjfj" width="100%" height="" frameborder="no" scrolling="false" border="0" marginwidth="0" marginheight="0"
				src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&stuffInfo.groupid=${groupid}"
				onload="this.height=window.frames['sjfj'].document.body.scrollHeight">
			</iframe>
		</fieldset>
		<div align="center" style="margin-right: 20px;margin-top: 20px;">
			<input type="button" class="btn" id="saveBtn" value="保存" onclick="submitItemFrame();" />
			<input type="button" class="btn" id="back" value="取消"  onclick="cancel();" />
		</div>



	</s:form>
</body>
<script
src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
   <script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>
	
<script type="text/javascript">

	function cancel() {
		DialogUtil.prototype.close();
	}

	
	function submitItemFrame() {
		
		if (doCheck() == false) {
			return;
		} else {
			var srForm = document.getElementById("dispatchDocForm");
			srForm.action = 'dispatchDoc!saveDispatchDocByIncomeDoc.do';
			var win = art.dialog.open.origin;//来源页面
			if(win){
			$.ajax({
                type: "post",
                url: srForm.action,     
                data: $("#dispatchDocForm").serialize(),
                async: false,
                success: function(data) {
               		// 如果父页面重载或者关闭其子对话框全部会关闭
               		win.location.reload(true);
    				
                },
                error: function(data) {
                    alert("修改信息失败，请重新尝试！");
                }
            });
			}
		}
	}


	var init = null;
	var canAdjustHeight = true;
	var flowInstId = "";
	var unitString = "${unitString}";
	var formEditParams = ["zbcsfzrsh", "FW_CSSH", "bgsfgsh", "bgssh","nw","hqxgcs"];
	var nodeCode = "";
	var checkMainUnit = true;
	var checkOtherUnits = true;
	
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
	
	function doCheck() {
		if (!(validateEmpty("dispatchDocTitle", "请输入文件标题") && validateEmpty("dispatchFileType", "请输入文件类型"))) {
			return false;
		}
		/* if ("1" == $("#isUnionDispatch").val() && !(validateEmpty("unionDispatchUnits", "请输入联合发文部门")
				&& validateLength("unionDispatchUnits", 500, "联合发文部门超出最大长度"))) {
			return false;
		} */
		if ("1" == $("#isCountersign").val() && !validateEmpty("orgCode", "请选择会签处室")) {
			return false;
		}
		if (!(  validateLength("dispatchDocSummary", 500, "事由超出最大长度")
				&& validateEmpty("draftDate", "请选择拟文日期")
// 				&& validateEmpty("optUnitName", "请输入主办处室")
// 				&& validateEmpty("draftUserName", "请输入拟稿人")
				//&& validateEmpty("dispatchDocType", "请选择公文种类")
				&& validateEmpty("mainNotifyUnit", "请选择主送单位"))) {
			return false;
		}
		
		if ("" == $.trim($('input:radio[name="dispatchCanOpen"]:checked').val())) {
			alert("请选择是否信息公开");
			$('#dispatchCanOpen').focus();
			return false;
		}
// 		if ("" == $("#curTemplateId").val()) {
// 			alert("请选择正文模板");
// 			return false;
// 		}
		return true;
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
					FrameUtils.adjustParentHeight(window,0);
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
	
	function setUnions() {
		if (!$("input[name='isUnionOther']").attr("checked")) {
			var selIncomeNos = $("input[type='hidden'][name='selIncomeNo']");
			for (var i=0; i<selIncomeNos.length; i++) {
				if (!deleteIncomeNo($(selIncomeNos[i]).val())) {
					alert("删除关联收文时出错！");
					return false;
				}
			}
		}
		if ("1" != $("#isCountersign").val()) {
			$("#orgCode").val("");
		}
		if ("1" != $("#isUnionDispatch").val()) {
			$("#unionDispatchUnits").val("");
		}
		return true;
	}
	
	function doSave() {
		
		if ("" != $("#incomeNo").val() && setUnions()) { // 收文转拟文
			$("#dispatchDocForm").submit();
		}
	}
	
	function doSubmit() {
		if ("" == $("#curTemplateId").val()) {
// 			var wfCode = $("#flowCode");
// 			if ("000530" == wfCode && $("#wfcode", window.parent.frames["transFrame"].document).length > 0) {
// 				$("#noZw", window.parent.frames["transFrame"].document).remove();
// 				if (!confirm("确认不生成正文吗？")) {
// 					return false;
// 				}
// 				$("#wfcode", window.parent.frames["transFrame"].document).after("<input type='hidden' id='noZw' name='noZw' value='T' />");
// 			} else {
				alert("请选择正文模板");
	 			return false;
// 			}
		}
		if ($("#curTemplateId").val() != $("#recordId").val()) {
			alert("当前正文与选择模板不一致，你保存当前模板所对应的正文");
			return false;
		}
		if (doCheck() && setUnions()) {
			/* if (window.parent.document.frames["projectInfoApprovalFrame"]
				&& !window.parent.window.frames["projectInfoApprovalFrame"].window.doSubmit()) {
				return false;
			} */
			$("#isCountersign").attr("disabled", false);
			$("#dispatchDocForm").submit();
			return true;
		}
		return false;
	}
	
	function setCanSubmit(flag) {
// 		$("#mainNotifyUnitNamePop,#otherUnitNamesPop").attr("disabled", flag ? false : true);
// 		if (window.frameElement) {
// 			$("input[type=button][name=subFrame]", window.parent.document).attr("disabled", flag ? false : true);
// 			$("input[type=button][name=saveFrame]", window.parent.document).attr("disabled", flag ? false : true);
// 		} else {
// 			// 如果是拟文是发起的第一步，请在此添加设置按钮无效的代码
// 		}
	}
	
	$("#mainNotifyUnit,#otherUnits").change(function() {
// 		setCanSubmit(false);
	});
	
	$("#mainNotifyUnit,#otherUnits").blur(function() {
		return false;
		var isMain = ("mainNotifyUnit" == $(this).attr("id"));
		var $code = isMain ? $("#mainNotifyUnitCode") : $("#otherUnitCodes");
		var $name = isMain ? $("#mainNotifyUnit") : $("#otherUnits");
		
		var unitNames = $name.val().split(/\s|[,]|[，]/);
		if (0 == unitNames.length) {
			$code.val("");
			$name.val("");
			setCanSubmit(true);
		} else {
			if (isMain && unitNames.length > 1) {
				alert("主送单位只能为一个，请确认输入是否正确（输入的单位中间不能包含空格等无关字符）");
				return false;
			}
			$.ajax({
				type : "POST",
				url : "${contextPath}/dispatchdoc/dispatchDoc!getSelectedUnitsAjax.do?unitNames=" + unitNames.join(","),
				dataType : "text",
				success : function(info) {
					if ("" == info) {
						$code.val("");
						$name.val("");
						alert("没有有效的单位信息！");
					} else {
						var array = info.split("],[");
						
						$code.val(array[0].substring(1).replace(/\s/g, ""));
						$name.val(array[1].substring(0, array[1].length-1).replace(/\s/g, ""));
					}
					setCanSubmit(true);
				},
				error : function() {
					alert(isMain ? "检查主送单位失败！" : "检查抄送单位失败！");
				}
			});
		}
		
		return false;
	});

	$(document).ready(function() {
		nodeCode = $("#nodeCode", window.parent.frames["transFrame"].document).val();
		flowInstId = $("#flowInstId").val();
		
		checkUnionDispatchUnits();
		checkCountersignUnits();
		checkIsUnionOther();
		if ($("div[id^='relative_']").length > 0) {
			$("#isUnionOther").attr("checked", true);
			$("#isUnionOther").parent().parent().next().show();
		}
		
		if ("" != nodeCode) {
			for (var i=0; i<formEditParams.length; i++) {
				if (nodeCode == formEditParams[i]) {
					//$("#templateTr").hide();
					$("#formEditControlArea").show();
					FrameUtils.adjustParentHeight(window,0);
					break;
				}
			}
		}
		
		init = setInterval("FrameUtils.initialize(window, init)", MyConstant.initTimeForAdjustHeight);
		
		$("#isUnionOther").click(function() {
			checkIsUnionOther();
		});
		
		$("#isUnionDispatch").click(function() {
			checkUnionDispatchUnits();
		});
		
		$("#isCountersign").click(function() {
			checkCountersignUnits();
		});
		
		$("#addCountersignUnits").click(function() {
			JDialog.open({
				src : "${contextPath}/dispatchdoc/ioDocTasksExcute!selectOrganize.do?roleCode=zbcshq&flowInstId=" + $("#flowInstId").val() + "&unitString=" + unitString,
				title : "选择机构",
				width : 408,
				height : 340,
				scrolling : "no",
				oDocument: window.parent.document
			});
		});
		
		
		$("#addRelative").click(function() {
			var buffer = new StringBuffer();
			var incomeNos = $("input[name='selIncomeNo']");
			for (var i=0; i<incomeNos.length; i++) {
				buffer.append("&djId=" + $(incomeNos[i]).val());
			}
			JDialog.open({
				src : "${contextPath}/dispatchdoc/dispatchDoc!incomeDocList.do?dispatchNo=" + $("#djId").val() + buffer.toString(),
				width : 800,
				height : 350,
				oDocument : window.parent.document
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
		
		$("#completeFormBtn").click(function() {
			if (doCheck && setUnions()) {
				var $form = $("#dispatchDocForm");
				$form.attr("action", $form.attr("action") + "?retVal=view");
				$("#dispatchDocForm").submit();
			}
		});
	});
	
	 //是否信息公开联动绑定
    $('input:radio[name="dispatchCanOpen"]').bind("click",
			function(e) {
    	      initdispatchCanOpen();
			});	
	 //初始化页面点开直接触发联动
    	    initdispatchCanOpen();
	 
	  function  initdispatchCanOpen(){
		  var dispatchCanOpen = $.trim($('input:radio[name="dispatchCanOpen"]:checked').val()); // 取radio
	       if('2'==dispatchCanOpen){
	    	   $("#tr_notOpenReason").show();
	    	   
	       }else{
	    	   $("#tr_notOpenReason").hide();
	    	   $("input[name='notOpenReason']").removeAttr('checked');
	       }
	  } 
	  
	 
	
	$("#orgName").click(function(){
		var d = '${unitsJson}';
		$('#attAlert').show();
		if(d.trim().length){
			window.parent.window.selectPopWinTemp(jQuery.parseJSON(d),$("#orgName"),$("#orgCode"));
		};
	});
	
	$("#mainNotifyUnitPop,#otherUnitsPop").click(function() {
		var id = $(this).attr("id");
		var selectType;
		if ("mainNotifyUnitPop" == id) {
			selectType = "radio";
		} else if ("otherUnitsPop" == id) {
			selectType = "checkbox";
		} else {
			return false;
		}
		
		var frameId = window.frameElement.id;
		JDialog.open({
			src : "${contextPath}/dispatchdoc/applyUnitInfo!selectList.do?JDialogLink=yes&selectType=" +selectType + "&frameId=" + frameId,
			width : 800,
			height : 350,
			oDocument: window.parent.document
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
</script>
</html>