<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta name="decorator" content='${LAYOUT}' />
<title></title>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
<script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">

function selectPopWinTree(ja,o1,o2){
	new treePerson($.parseJSON(ja), o1, o2).init();/* 此处人员限制为一人 */
	setAlertStyle("attAlert",($(window).height()-136)/2+($(document).scrollTop()==0?($("#firstPage",window.parent.document).scrollTop()-200==0?-200:$("#firstPage",window.parent.document).scrollTop()-200):$(document).scrollTop()));
	 $("#attAlert").removeAttr("top");
}



function autoHeight(obj){
	//document.getElementById("itemFrame").style.height=window.frames['itemFrame'].document.body.scrollHeight;
	if(parseInt(window.frames['itemFrame'].document.body.scrollHeight)>10){
		obj.height=window.frames['itemFrame'].document.body.scrollHeight;
	}
}

	function openTemplate(val){
		if(val  == '' || val == null){
			return;
		}
		var curTemplateId = document.getElementById("curTemplateId").value;
		if (curTemplateId != "" && curTemplateId != null) {
			if(window.confirm("重新选择模板会生成新的文件，已保存的内容将被覆盖，是否确定？")){
				openDocEdit(val);
			}
		}else{
			openDocEdit(val);
		}
	}

	function submitItemFrame(subType){
		var srEXForm  = document.getElementById("srPermitApplyForm");
		var itemType  = document.getElementById("applyItemType").value;
		var oaMuduleType='${vPowerUserInfo.oaModuleType}'; 
		var oaApplyItemType ='${vPowerUserInfo.applyItemType}';
		if(docheck()){
			var b=true;
			// B 业务模块 C基础模块
			if('B'==oaMuduleType){
				var srForm  = window.frames['itemFrame'];
				var djid = srForm.document.getElementsByTagName("form")[0].djId.value;
				//optApplyInfo基础信息保存后跳转标志subType
				if(subType == 'SAVE'){
					srEXForm.action= 'optApply!saveCommon.do?djId='+djid+"&applyItemType="+oaApplyItemType+"&s_itemtype=SQ"+"&subType="+subType;
					$.ajax({
						url: '${vPowerUserInfo.applyItemType}!savePermitReg.do',
						data: $(srForm.document.forms[0]).serialize(),
						type: "POST",
						async: true
						}
						);
				}
				if(subType == 'SUB'){
					srEXForm.action= 'optApply!saveCommon.do?djId='+djid+"&applyItemType="+oaApplyItemType+"&s_itemtype=SQ"+"&subType="+subType;
					$.ajax({
						url: '${vPowerUserInfo.applyItemType}!saveAndSubmit.do',
						data: $(srForm.document.forms[0]).serialize(),
						type: "POST",
						async: true
						}
					);
				}
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
					if("${startDjId}"!=null&&"${startDjId}"!=("")){
						if(confirm("您是否确认发起关联业务？")){
							if("${itemType}"=='SQ'){//类型为事权登记走事权登记的保存方法
								srEXForm.action = 'optApply!saveAndSubmitPermitSQ.do';
							}else{
								srEXForm.action = 'optApply!saveAndSubmitPermit.do';
							}
						}else{
							b=false;
						}
					}else
						{
						if("${itemType}"=='SQ'){//类型为事权登记走事权登记的保存方法
							srEXForm.action = 'optApply!saveAndSubmitPermitSQ.do';
						}else{
							srEXForm.action = 'optApply!saveAndSubmitPermit.do';
						}
						}
				}
				if(b){
				srEXForm.submit();
				}
			}
		}
	}

	//选择模版上传文档
	function openDocEdit(val){
		//document.getElementById("remark").focus();
		if(val==null || val==''){
			alert("操作失败，对应模版没有配置!");
		}else{
		var uri = "<%=request.getContextPath()%>/iWebOffice/DocumentEdit.jsp";
		var param = "flowStep=ZW_EDIT&archiveType=01&RecordID=${djId}&Template=" + val
		 			+"&NeedBookMark=1";
		openNewWindow(uri + "?"+ param,'editForm','');
		}
	}
	
	function setIFrameVal(applyItem,djId,riskType){	
		
	if(riskType!=''){
		document.getElementById('resultID').style.display = 'block';
		document.getElementById('riskDescID').style.display = 'block';
		document.getElementById('riskTypeID').style.display = 'block';		
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
		var srForm  = window.frames['itemFrame'];
		if(srForm!=null && srForm!=undefined){
			return srForm.checkForm();
		} else {
			if('${s_itemtype}' == 'SQ'){
				if($("#transaffairname").val()==''){
						alert("事项标题不可为空！");
						$('#transaffairname').focus();
						return false;
				}
				if($("#transaffairname").length >200){
					alert("事项标题超过最大长度限制！");
					$('#transaffairname').focus();
					return false;
			    }
				if($("#content").val()==''){
					alert("事项内容不可为空！");
					$('#content').focus();
					return false;
			    }
				if($("#content").val().length >200){
						alert("事项内容超过最大长度限制！");
						return false;
				}
				if($("#orgName").val()==''){
					alert("请选择接收部门");
					return false;
				}
			}else{
				if($("#transaffairname").val()==''){
						alert("签报标题不可为空！");
						$('#transaffairname').focus();
						return false;
				}
				if($("#transaffairname").length >200){
					alert("签报标题超过最大长度限制！");
					$('#transaffairname').focus();
					return false;
			    }
				if($("#content").val()==''){
					alert("签报内容不可为空！");
					$('#content').focus();
					return false;
			    }
				if($("#content").val().length >200){
						alert("签报内容超过最大长度限制！");
						return false;
				}
				if($("#userName").val()==''){
					alert("请选择人员");
					return false;
				}
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
		<input id="itemType" type="hidden" name="itemType" value="${itemType}">
		<input id="fqqb" type="hidden" name="fqqb" value="${fqqb}">
		<input id="s_djId" type="hidden" name="s_djId" value="${s_djId}">
		<input id="s_nodeInstId" type="hidden" name="s_nodeInstId" value="${s_nodeInstId}">
		<%-- <input id="poweridq" type="hidden" name="powerid" value="${object.optBaseInfo.powerid}" > --%>
		<!-- <center> -->
	<%-- 	<c:if test="${ empty dashboard }">
		<input type="button" class="btn" onclick="window.history.back();"
			value="返回" />
		</c:if> --%>
<%-- 		<c:if test="${empty startDjId }"> --%>
<!-- 		<input type="button" class="btn" onclick="submitItemFrame('SAVE');" -->
<!-- 			value="保存" /> -->
<%-- 		</c:if> --%>
		<input type="button" class="btn btnWide" id="saveAndSubmit"
			onclick="submitItemFrame('SUB');" value="提交" />
		<!-- </center> -->
		<c:if test="${vPowerUserInfo.oaModuleType ne 'B' }">
			<%@ include
				file="/page/powerruntime/optcommon/optBaseInfoNbsqForm.jsp"%>
		</c:if>
		<c:if test="${vPowerUserInfo.oaModuleType eq 'B' }">
			<iframe id="itemFrame" name="itemFrame"
				src="${pageContext.request.contextPath}/${vPowerUserInfo.applyItemType }!edit.do?flowCode=${flowCode}&itemId=${itemId}&djId=${object.djId}&s_itemType=${itemType}&s_applyItemType=${object.applyItemType}"
				width="100%" style="margin-bottom: 10px;" frameborder="no"
				scrolling="no" border="0" marginwidth="0"
				onload="this.height=window.frames['itemFrame'].document.body.scrollHeight" ></iframe>
		</c:if>
				
		
		<c:if test="${vPowerUserInfo.oaModuleType ne 'B'  and  ( not empty powerOptInfo and powerOptInfo.isGeneralModule eq 'T')}">
        <jsp:include page="/page/powerruntime/optcommon/optTransInfo.jsp" flush="true"/>
		</c:if>
		<div id="slfieldset" style="display: none;">
			<fieldset style="padding: 10px; display: block; margin-bottom: 10px;" >
				<legend style="padding: 4px 8px 3px;">
					<b>材料上传</b>
				</legend>
				<input type="hidden" id="groupId" name="groupId" value="" />
				<iframe id="basicsj" name="sjfj" height="" src="" width="100%"
					style="overflow: hidden;" frameborder="no" scrolling="false"
					border="0" marginwidth="0" marginheight="0" ></iframe>
			</fieldset>
		</div>
     <!-- 选择人员的模块 -->
		<div id="attAlert" class="alert" style="width: 600px; height: 335px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
			<h4>
				<span>选择</span><span id="close2" style="float: right; margin-right: 8px;" class="close" onclick="closeAlert('attAlert');">关闭</span>
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
					<b class="btns"> <input id="save" class="btn" type="button"
						value="保     存" /><input id="clear" class="btn" type="button"
						value="取     消" style="margin-top: 5px;" /></b>
				</div>
			</div>
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
	if(riskType!=''){
		document.getElementById('resultID').style.display = 'block';
		document.getElementById('riskDescID').style.display = 'block';
		document.getElementById('riskTypeID').style.display = 'block';		
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
			var url2='<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId='+ djid+ '&stuffInfo.nodeInstId=0&stuffInfo.groupid='+ data.groupidByitemid;
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
