<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta name="decorator" content='${LAYOUT}' />
<title><s:text name="srPermitApply.edit.title" /></title>
<%-- <sj:head locale="zh_CN" /> --%>
<script src="${pageContext.request.contextPath}/scripts/suggest.js"
	type="text/javascript"></script>
<script type="text/javascript">
function autoHeight(obj){
	//document.getElementById("itemFrame").style.height=window.frames['itemFrame'].document.body.scrollHeight;
	if(parseInt(window.frames['itemFrame'].document.body.scrollHeight)>10){
		obj.height=window.frames['itemFrame'].document.body.scrollHeight;
	}
}


function submitItemFrame(subType){
	var srEXForm  = document.getElementById("srPermitApplyForm");
	var itemType  = document.getElementById("applyItemType").value;
	var oaMuduleType='${vPowerUserInfo.oaModuleType}'; 
	var oaApplyItemType ='${vPowerUserInfo.applyItemType}';
	if(docheck()){
		// B 业务模块 C基础模块
		if('B'==oaMuduleType){
			var srForm  = window.frames['itemFrame'];
			var djid = srForm.document.oaComplaintForm.djId.value;
			if(subType == 'SAVE'){
				srForm.document.forms[0].action = '${vPowerUserInfo.applyItemType}!savePermitReg.do';
			}
			if(subType == 'SUB'){
				srForm.document.forms[0].action = '${vPowerUserInfo.applyItemType}!saveAndSubmit.do';
			}
			srForm.document.forms[0].submit(); 
			//optApplyInfo基础信息保存后跳转标志subType
			srEXForm.action= 'optApply!saveCommon.do?djId='+djid+"&applyItemType="+oaApplyItemType+"&subType="+subType;
			srEXForm.submit();
		}else{
			if(itemType != null && itemType != ''){
				var frmObj = window.frames['itemFrame'];
				frmObj.document.forms[0].submit();
			}
			if(subType == 'SAVE'){
				srEXForm.action = 'optApply!savePermitReg.do';
			}
			if(subType == 'SUB'){
				if("${itemType}"=='SQ'){//类型为事权登记走事权登记的保存方法
					srEXForm.action = 'optApply!saveAndSubmitPermitSQ.do';
				}else{
					srEXForm.action = 'optApply!saveAndSubmitPermit.do';
				}
			}
			srEXForm.submit();
		}
	}
}
	function setIFrameVal(applyItem,djId,riskType){	
	if(applyItem!=''){
		//显示div
		document.getElementById('itemFrameDiv').style.display="";
		/*****************业务数据页面跳转begin*********/
		var itemFrameObj = document.getElementById('itemFrame');		
		itemFrameObj.src='../wwd/srPermitApply!editItem.do?applyItemType='+applyItem+"&djId="+djId;
		}
} 
    //初始化材料
    function openStuff(){
    	var powerid='${object.optBaseInfo.powerid}';
    	var djid='${object.djId}';
    	if(powerid!=''){
    		$.ajax({
    		type:"POST",
    		url: "<%=request.getContextPath()%>/powerruntime/powerOptInfo!getGroupIDByItemid.do?object.itemId="+powerid,
    		contentType:"text/html",					
    		dataType:"json",
    		processData:false,
    		async: false,
    		success:function(data){
    			var obj = document.getElementById("slfieldset");
    			obj.style.display="block";
    			var url2='<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId='+djid+'&stuffInfo.nodeInstId=0&stuffInfo.groupid='+data.groupidByitemid;
    			var objfram=document.getElementById('basicsj');
    			objfram.src=url2;					
    		},
    		error:function(){
    			alert("系统提交失败");
    		}			
    	});
    	}
    }
	//申请者证件类别：区别自然人和法人
	
	function changPaperType() {
		var proposerType = document.getElementById("proposerType");
		if(proposerType!=undefined){
		var proposerUnitcode_tr = document.getElementById("proposerUnitcode_tr");
		if (proposerType.value =="02") { //自然人
			document.getElementById("zrrDiv").style.display = 'block';
			_get("unitDiv").style.display = "none";
			proposerUnitcode_tr.style.display = "none";		
		} else {//法人和其他
			_get("zrrDiv").style.display = "none";
			_get("unitDiv").style.display = "block";
			proposerUnitcode_tr.style.display = "table-row";
		}
		}
	}
	
	
	var _get = function (id) {
		return document.getElementById(id);
	};
	function docheck() {
		if($("#powerid").val()==''){
			alert("请选择权力事项");
			return false;
		}
		
		if($("#transaffairname").val()==''){
				alert("办件名称不可为空！");
				$('#transaffairname').focus();
				return false;
		}
		var ev='${isapplyuser}';
        if(ev=="T"){
        	
        
		if($("#proposerName").val()==''){
			
			alert("申请者名称不可为空！");
			$('#proposerName').focus();
			return false;
		}
		if($("#proposerPaperCode").val()==''){
			alert("证件号不可为空！");
			$('#proposerPaperCode').focus();
			return false;
		}
		if($("#proposerPhone").val()==''&& $("#proposerMobile").val()==''){
		alert('请输入移动或者固定电话');
		return false;
		}
		if($("#proposerPhone").val()!=''|| $("#proposerMobile").val()!=''){
			
		var reg = /^((0\d{2,3})-)?(\d{1,11})$/;
		var val1 = $("#proposerPhone").val();
		var val2 = $("#proposerMobile").val();
		if(val1!=''){
		if (!reg.test(val1)) {
			
			alert("申请者固定电话格式不对！");
			$('#proposerPhone').focus();
			return false;
		}
		}
		if(val2!=''){
        if (!reg.test(val2)) {
			
			alert("申请者手机电话格式不对！");
			$('#proposerMobile').focus();
			return false;
		}
		}
	    }
	    	
		if($("#proposerZipcode").val() !=''){
		var reg = /^[1-9]\d{5}$/;
		var val = $("#proposerZipcode").val();
		if (!reg.test(val)) {
			alert("邮编格式不正确");
			$("#proposerZipcode").focus();
			return ;
		}
		}
		if($("#proposerEmail").val()!=''){
			var val = $("#proposerEmail").val();
			var reg = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/;
			if (!reg.test(val)){
				alert("邮件格式不正确");
				$("#proposerEmail").focus();
				return ;
		}
		}
	 
		if($("#agentUnitcode").val() !='' &&  getStringLen($("#agentUnitcode").val())>6){
			alert("代理人机构代码 长度不可超过6位！");
			$('#agentUnitcode').focus();
			return false;
		}
/* 以下为后补验证 */
		var agentPaperCodeVal=$('#agentPaperCode').val();
		if(undefined!=agentPaperCodeVal&&agentPaperCodeVal.length>32){
			alert("代理人证件号码长度不超过32！");
			$('#agentPaperCode').focus();
			return false;
		}		
		var agentPhoneVal=$('#agentPhone').val();
		if(undefined!=agentPhoneVal&&agentPhoneVal!=""){
			if(agentPhoneVal.length>10){
				alert("代理人固定电话长度不能大于10！");
				$('#agentPhone').focus();
				return false;
			}
			var reg = /^((0\d{2,3})-)?(\d{1,11})$/;
			if(!reg.test(agentPhoneVal)){
				alert("代理人固定电话格式不对！");
				$('#agentPhone').focus();
				return false;
			}
		}
		var agentMobileVal=$('#agentMobile').val();
		if(undefined!=agentMobileVal&&agentMobileVal!=""){
			if(agentMobileVal.length>12){
				alert("代理人移动电话长度不能大于20！");
				$('#agentMobile').focus();
				return false;
			}
			var reg = /^((0\d{2,3})-)?(\d{1,11})$/;
			if(!reg.test(agentMobileVal)){
				alert("代理人移动电话格式不对！");
				$('#agentMobile').focus();
				return false;
			}
		}
		var agentEmailVal=$('#agentEmail').val();
		if(undefined!=agentEmailVal&&agentEmailVal!=""){
			if(agentEmailVal.length>42){
				alert("代理人电子邮件长度不能大于42！");
				$('#agentEmail').focus();
				return false;
			}
			var reg = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/;
			if(!reg.test(agentEmailVal)){
				alert("代理人电子邮件格式不对！");
				$('#agentEmail').focus();
				return false;
			}
		}
		var agentZipcodeVal=$('#agentZipcode').val();
		if(undefined!=agentZipcodeVal&&agentZipcodeVal!=""){
			if(agentZipcodeVal.length>6){
				alert("代理人邮编长度不能大于6！");
				$('#agentZipcode').focus();
				return false;
			}
			var reg = /^[1-9]\d{5}(?!\d)$/;
			if(!reg.test(agentZipcodeVal)){
				alert("代理人邮编格式不对！");
				$('#agentZipcode').focus();
				return false;
			}
		}
}
		
		return  true;
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
</script>
</head>

<body
	onload="setIFrameVal('${object.applyItemType}','${object.djId}','${object.optBaseInfo.riskType}');changPaperType();openStuff();" class="sub-flow">
	<%@ include file="/page/common/messages.jsp"%>
	<s:form action="optApply" method="post" namespace="/powerruntime"
		name="srPermitApplyForm" id="srPermitApplyForm">
		<input id="applyItemType" type="hidden" name="applyItemType"
			value="${object.applyItemType}">
		<%-- <input id="poweridq" type="hidden" name="powerid" value="${object.optBaseInfo.powerid}" > --%>
		<!-- <center> -->
		<input type="button" class="btn" onclick="window.history.back();"
			value="返回" />
		<input type="button" class="btn" onclick="submitItemFrame('SAVE');"
			value="保存" />
		<input type="button" class="btn btnWide" id="saveAndSubmit"
			onclick="submitItemFrame('SUB');" value="保存并提交" />
		<!-- </center> -->
		<c:if test="${vPowerUserInfo.oaModuleType ne 'B' }">
	       <%@ include file="/page/powerruntime/optcommon/optBaseInfoNbsqForm.jsp"%>
       </c:if>
		<c:if test="${isapplyuser eq 'T' }">
			<fieldset style="padding: 10px; display: block; margin-bottom: 10px;"
				class="_new">
				<legend style="padding: 4px 8px 3px;">
					<b>申请信息</b>
				</legend>
				<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

					<tr>
						<td class="addTd">申请方式</td>
						<td align="left"><select name="applyWay"
							style="width: 200px; height: 25px;">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:DICTIONARY('bjsqfs')}">
									<option value="${row.key}"
										<c:if test="${object.applyWay eq row.key}"> selected = "selected" </c:if>
										<c:if test="${empty object.applyWay and row.key eq '0'}"> selected = "selected" </c:if>>
										<c:out value="${row.value}" />
									</option>
								</c:forEach>
						</select></td>
						<td class="addTd">申请日期</td>
						<td align="left"><input type="text" class="Wdate"
							id="applyDate"
							value="<fmt:formatDate value='${object.applyDate}' pattern='yyyy-MM-dd' />"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="applyDate"
							style="width: 200px; height: 25px;" readonly="readonly" /></td>

					</tr>
					<tr>
						<td class="addTd">申请原因</td>
						<td align="left" colspan="3" width="85%"><s:textarea
								name="applyReason" style="width:100%;"
								value="%{object.applyReason}" /></td>
					</tr>
					<tr>
						<td class="addTd">申请者类别</td>
						<td align="left"><select id="proposerType"
							name="proposerType" style="width: 200px; height: 25px;"
							onchange="changPaperType();">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:DICTIONARY('PROPOSER_TYPE')}">
									<option value="${row.key}"
										<c:if test="${object.proposerType eq row.key}"> selected = "selected" </c:if>
										<c:if test="${empty object.proposerType and row.key eq '01'}"> selected = "selected" </c:if>>
										<c:out value="${row.value}" />
									</option>
								</c:forEach>
						</select></td>
						<td class="addTd">申请者名称<span style="color: red">*</span>
						</td>

						<td width="30%">
							<div id="userDiv" style="z-index: 1;">
								<input type="text" name="proposerName" id="proposerName"
									value="${object.proposerName}" style="width: 200px;">
								<ul id="list"></ul>
							</div> <script type="text/javascript">
						$(function(){
							initValue_($("input[name=proposerName]"),$("#list"),"${pageContext.request.contextPath}/wwd/srPermitApply!getProposerNames.do",$("input[name=proposerName]"));
						})
						</script>
						</td>


					</tr>
					<tr>

						<td class="addTd">申请者证件类型</td>
						<td align="left">
							<div id="unitDiv" style="display: none;">
								<select name="proposerPaperType"
									style="width: 200px; height: 25px;" id="PaperType_ZJJG">
									<option value="">---请选择---</option>
									<c:forEach var="row" items="${cp:DICTIONARY('PaperType')}">
										<option value="${row.key}"
											<c:if test="${object.proposerPaperType eq row.key}"> selected = "selected" </c:if>
											<c:if test="${empty object.proposerPaperType and row.key eq '0'}"> selected = "selected" </c:if>>
											<c:out value="${row.value}" />
										</option>
									</c:forEach>
								</select>
							</div>
							<div id="zrrDiv" style="display: none;">
								<select name="proposerPaperTypes"
									style="width: 200px; height: 25px;" id="PaperType_ZRR">
									<option value="">---请选择---</option>
									<c:forEach var="row" items="${cp:DICTIONARY('PaperType_ZRR')}">
										<option value="${row.key}"
											<c:if test="${object.proposerPaperType eq row.key}"> selected = "selected" </c:if>
											<c:if test="${empty object.proposerPaperType and row.key eq '0'}"> selected = "selected" </c:if>>
											<c:out value="${row.value}" />
										</option>
									</c:forEach>
								</select>
							</div>
						</td>

						<td class="addTd">申请者证件号码<span style="color: red">*</span>
						</td>
						<td align="left"><s:textfield name="proposerPaperCode"
								id="proposerPaperCode" style="width:200px;height:25px;"
								value="%{object.proposerPaperCode}" /></td>
					</tr>
					<tr>

						<td class="addTd">申请者固定电话<span style="color: red">*</span>
						</td>
						<td align="left"><s:textfield id="proposerPhone"
								name="proposerPhone" value="%{object.proposerPhone}"
								style="width:200px;height:25px;" /></td>

						<td class="addTd">申请者移动电话</td>
						<td align="left"><s:textfield id="proposerMobile"
								name="proposerMobile" value="%{object.proposerMobile}"
								style="width:200px;height:25px;" /></td>
					</tr>
					<tr>
						<td class="addTd">申请者电子邮件</td>
						<td align="left"><s:textfield name="proposerEmail"
								id="proposerEmail" value="%{object.proposerEmail}"
								style="width:200px;height:25px;" /></td>
						<td class="addTd">申请者邮编</td>
						<td align="left"><s:textfield name="proposerZipcode"
								id="proposerZipcode" value="%{object.proposerZipcode}"
								style="width:200px;height:25px;" /></td>
					</tr>
					<tr id="proposerUnitcode_tr">
						<td class="addTd">申请者机构代码</td>
						<td align="left"><s:textfield name="proposerUnitcode"
								value="%{object.proposerUnitcode}"
								style="width:200px;height:25px;" /></td>
						<td class="addTd">法定代表人/负责人</td>
						<td align="left"><s:textfield name="legal_person"
								value="%{object.legal_person}" style="width:200px;height:25px;" /></td>
					</tr>
					<tr>
						<td class="addTd">申请者地址</td>
						<td align="left" colspan="3"><s:textarea name="proposerAddr"
								value="%{object.proposerAddr}" style="width:200px;height:25px;" />
						</td>
					</tr>
					<tr>
						<td class="addTd">代理人姓名</td>
						<td align="left"><s:textfield name="agentName"
								value="%{object.agentName}" style="width:200px;height:25px;" />
						</td>
						<td class="addTd">代理人机构代码</td>
						<td align="left"><s:textfield name="agentUnitcode"
								id="agentUnitcode" value="%{object.agentUnitcode}"
								style="width:200px;height:25px;" /></td>
					</tr>

					<tr>
						<td class="addTd">代理人证件类型</td>
						<td align="left"><select name="agentPaperType"
							style="width: 200px; height: 25px;">
								<option value=""></option>
								<c:forEach var="row" items="${cp:DICTIONARY('PaperType')}">
									<option value="${row.key}"
										<c:if test="${object.agentPaperType eq row.key}"> selected = "selected" </c:if>>
										<c:out value="${row.value}" />
									</option>
								</c:forEach>
						</select></td>
						<td class="addTd">代理人证件号码</td>
						<td align="left"><s:textfield id="agentPaperCode"
								name="agentPaperCode" value="%{object.agentPaperCode}"
								style="width:200px;height:25px;" /></td>
					</tr>

					<tr>
						<td class="addTd">代理人固定电话</td>
						<td align="left"><s:textfield id="agentPhone"
								name="agentPhone" value="%{object.agentPhone}"
								style="width:200px;height:25px;" /></td>
						<td class="addTd">代理人移动电话</td>
						<td align="left"><s:textfield id="agentMobile"
								name="agentMobile" value="%{object.agentMobile}"
								style="width:200px;height:25px;" /></td>
					</tr>

					<tr>
						<td class="addTd">代理人电子邮件</td>
						<td align="left"><s:textfield id="agentEmail"
								name="agentEmail" value="%{object.agentEmail}"
								style="width:200px;height:25px;" /></td>
						<td class="addTd">代理人邮编</td>
						<td align="left"><s:textfield id="agentZipcode"
								name="agentZipcode" value="%{object.agentZipcode}"
								style="width:200px;height:25px;" /></td>
					</tr>

					<tr>
						<td class="addTd">代理人地址</td>
						<td align="left" colspan="3"><s:textarea name="agentAddr"
								style="width:100%" value="%{object.agentAddr}" /></td>
					</tr>
					<tr>
						<td class="addTd">申请备注</td>
						<td align="left" colspan="3"><s:textarea name="applyMemo"
								value="%{object.applyMemo}" style="width:100%" /></td>
					</tr>
				</table>
			</fieldset>
		</c:if>
        <c:if test="${vPowerUserInfo.oaModuleType eq 'B' }">
			<iframe id="itemFrame" name="itemFrame"
				src="${pageContext.request.contextPath}/${vPowerUserInfo.applyItemType }!edit.do?flowCode=${flowCode}&itemId=${itemId}&djId=${object.djId}"
				width="100%" style="margin-bottom: 10px;" frameborder="no"
				scrolling="no" border="0" marginwidth="0"
				onload="this.height=window.frames['itemFrame'].document.body.scrollHeight"
				target="_parent"></iframe>
		</c:if>
		<div id="slfieldset" style="display: none;">
			<fieldset style="padding: 10px; display: block; margin-bottom: 10px;"
				class="_new">
				<legend style="padding: 4px 8px 3px;">
					<b>材料上传</b>
				</legend>
				<input type="hidden" id="groupId" name="groupId" value="" />
				<iframe id="basicsj" name="sjfj" height="" src="" width="100%"
					style="overflow: hidden;" frameborder="no" scrolling="false"
					border="0" marginwidth="0" marginheight="0" />
			</fieldset>
		</div>
	</s:form>
</body>
<script type="text/javascript">
function setIFrameVal(applyItem,djId,riskType){	
	if(applyItem!=''){
		//显示div
		document.getElementById('itemFrameDiv').style.display="";
		/*****************业务数据页面跳转begin*********/
		var itemFrameObj = document.getElementById('itemFrame');	
		itemFrameObj.src='../wwd/srPermitApply!editItem.do?applyItemType='+applyItem+"&djId="+djId;
		}
}	
	
</script>


<script type="text/javascript">
	var powerid='${object.optBaseInfo.powerid}';
	
	var djid='${object.djId}';
	if(powerid!=''){
		$.ajax({
		type:"POST",
		url: "<%=request.getContextPath()%>/powerruntime/powerOptInfo!getGroupIDByItemid.do?object.itemId="+powerid,
		contentType:"text/html",					
		dataType:"json",
		processData:false,
		async: false,
		success:function(data){
			var obj = document.getElementById("slfieldset");
			obj.style.display="block";
			var url2='<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId='
								+ djid
								+ '&stuffInfo.nodeInstId=0&stuffInfo.groupid='
								+ data.groupidByitemid;
						var objfram = document.getElementById('basicsj');
						objfram.src = url2;
					},
					error : function() {
						alert("系统提交失败");
					}
				});
	}
	//var optBaseInfoJson='${optBaseInfoJson}';
	function getOptBaseInfoJson() {
		return $
		{
			optBaseInfoJson
		}
		;
	}
</script>
</html>
