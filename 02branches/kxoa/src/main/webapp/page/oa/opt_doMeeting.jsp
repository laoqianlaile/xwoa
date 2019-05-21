<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/rtx/js/browinfo.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/rtx/js/rtxint.js"></script>
<html>
<head>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css" rel="stylesheet" type="text/css"/>
<!-- 新样式文件 -->
<link
	href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js" type="text/javascript" ></script>
	<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/artDialog.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/artDialog4.1.7/plugins/iframeTools.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
  var begtime='${object.planBegTime}'.substring(0, 19);
  var endtime ='${object.planEndTime}'.substring(0, 19);
  if($("#doBegTime").val()==''&&$("#doEndTime").val()==''){
	  $("#doBegTime").val(begtime);
	  $("#doEndTime").val(endtime);
   }
});
</script>
<script src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
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
    
    var ideacode = $("#ideacode").val();
	    if(ideacode=='F' || ideacode==''){//
	    	_get("com_0").style.display = "none";
	    	_get("com_1").style.display = "none";
	    	_get("com_2").style.display = "none";
	    	_get("com_4").style.display = "none";
		}else{	    	
			_get("com_0").style.display = "block";
	    	_get("com_1").style.display = "block";
	    	_get("com_2").style.display = "block";
	    	_get("com_4").style.display = "block";
		}
	    
	    adjustHeight();
}

function adjustHeight() {//自动调整页面高度
	//alert(window.parent.document.getElementById("transFrame").style.height);
	if (window.parent.document.getElementById("transFrame")) {
		window.parent.document.getElementById("transFrame").style.height = document.body.scrollHeight
				+ "px";
	}
	//alert(window.parent.document.getElementById("transFrame").style.height);	
}
</script>
</head>
<body class="sub-flow">
		<div class="group-title">
	     <div class="title-ico"></div>
	     <div class="title-name">
	           	会议室安排
	     </div>
	     <hr class="title-split-line" style="position: absolute;width: 100%;height: 1px;border:none;border-top:1px solid #CCC;padding: 0!important;top:10px;z-index:0"/>
         </div>
<%@ include file="/page/common/messages.jsp"%>
<s:form action="oaMeetApply"  method="post" namespace="/oa" id="oaMeetApplyForm" target="_parent" onsubmit="return checkForm();">
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<%-- <input type="hidden" id="meetingNo" name="meetingNo" value="${object.meetingNo}" /> --%>
		
		<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />		
        <input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}">
        <input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">
        <input type="hidden" id="flowCode" name="flowCode"  value="000857" />
         <input type="hidden" id="moduleCode" name="moduleCode" value="${moduleCode}" />
         <input type="hidden" id="isBM" name="isBM" value="" />
		 <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
		<c:if test="${moduleParam.hasIdea eq 'T' }">
				<tr id="tr_ideacode">
					<td class="addTd" width="13%">${moduleParam.ideaLabel}<font
						color="red">*</font></td>
					<td align="left" width="87%" colspan="3"><input type="hidden"
						name="transidea" value="" id="transidea">
						<select	id="ideacode" name="ideacode" onchange="_getSelectedItemLabel(this)"
						style="width: 200px; height: 25px;">
							<option VALUE="">---请选择---</option>
							<c:forEach var="row"
								items="${cp:DICTIONARY(moduleParam.ideaCatalog)}">		
								<option value="${row.key}" label="${row.value}">
										<c:out value="${row.value}" />
									</option>					
							</c:forEach>
					</select>
					&nbsp;&nbsp;<input class="btn" type="button" value="安排记录" onclick="plan();" />
					</td>
				</tr>
			</c:if>
			
					<%-- 	<c:if test="${moduleParam.hasOrgRole eq 'T' }"> --%>
			
				<tr id="com_0">	
						<td class="addTd" width="18%">${moduleParam.xbOrgRoleName}<span style="color:red">*</span></td>
						<td colspan="3"><input type="text" id="xbOrgNames"
							name="xbOrgNames" style="width: 100%;height: 35px" value="${object.recomUnitNames}"
							readonly="readonly" /> <input type="hidden" id="xbOrgCodes"
							name="xbOrgCodes" value="${object.recomUnits}" /> <input type="hidden"
							id="xbOrgRoleCode" name="xbOrgRoleCode"
							value="${moduleParam.xbOrgRoleCode}" /></td>
				</tr>
			<%-- 	</c:if>	 --%>		 
			
				<tr id="com_1">
					<td class="addTd">
						安排会议室<span style="color:red">*</span>
					</td>
					 <td align="left" colspan="3">
				 <select  style="height:25px;width:200px;"
					id="meetingNo" name="meetingNo" onchange="changeMeet(this.value);">
						<c:forEach var="dt" items="${oaMeetinfolist}" >
							<option value="${dt.djid}"
								<c:if test="${dt.djid==meetingNo}"> selected="selected"</c:if>>${dt.meetingName
								}</option>
						</c:forEach>
				   </select>
					</td>
				</tr>
               <tr id="com_2">
					<td class="addTd">
						安排开始时间<span style="color:red">*</span>
					</td>
					 <td align="left" >
					<input type="text" class="Wdate" style="width:200px;height:25px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.doBegTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="doBegTime" name="doBegTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />		
					
					</td>
						<td class="addTd">
						安排结束时间<span style="color:red">*</span>
					</td>
					 <td align="left" >
	    			<input type="text" class="Wdate" style="width:200px;height:25px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.doEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					id="doEndTime" name="doEndTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:''})" placeholder="选择日期" />		
					
					</td>
					</tr>					
					<tr id="com_3">
					<td class="addTd">
						安排备注
					</td>
					 <td align="left" colspan="3">
						<s:textarea name="doRemark" style="width:100%;height:50px;" id="transcontent" rows="50"/>
						<div id="com_4">辅助录入&nbsp;&nbsp;<input type="text" id="yjUserNames"
							name="yjUserNames" value=""
							readonly="readonly" style="width: 30%"/> <input type="hidden" id="yjCodes"
							name="teamUserCodes1" value="${teamUserCodes}" /> <input
							type="hidden" id="yjCode" name="teamRoleCode1"
							value="${moduleParam.teamRoleCode}"/>
					<input type="button" name="procollys" id="procollys"  class="btn" onclick="insertAtCursor('yjUserNames');" value="加入人员"/>
					<input type="text" id="xbOrgNames_"
								name="xbOrgNames_"  value=""
								readonly="readonly" style="width: 35%"/> <input type="hidden" id="xbOrgCodes_"
								name="xbOrgCodes_" value="" /> <input type="hidden"
								id="xbOrgRoleCode_" name="xbOrgRoleCode_"
								value="" />
								<input type="button" name="procollys1" id="procollys1"  class="btn" onclick="insertAtCursor('xbOrgNames_');" value="加入部门"/>
								</div>
					</td>			
					</tr>
					
									  <!-- 					温馨提示	 -->
			<c:if test="${not empty moduleParam.tips}">	
			<tr>
			    <td colspan="4" align="left">
				<img class="wxtx_img" src="${pageContext.request.contextPath}/newStatic/image/wxtx.png" /> <span class="wxtx_span" >温馨提示：</span><span class="wxtx_count"> ${moduleParam.tips}</span>
				</td>
			 </tr>
		    </c:if>
				<!-- 	<tr id="com_4">
					<td class="addTd">
						辅助录入
					</td>
					<td colspan="3">
						
					</td>
					</tr> -->
</table>
	<center style="margin-top: 10px;">
		<span style="display:none;" >
			<s:submit name="saveAndSubmit" method="submitdoMeeting" cssClass="btn" value="提 交" id="submitBtn"/>
		</span>
		<span style="display:none;" >	
			<s:submit name="save" method="savedoMeeting" cssClass="btn" value="保 存" id="saveBtn"/>
		</span>	
		<span style="display:none;" >	
			<input type="button" value="返回" class="btn"  onclick="window.history.go(-1);" id="backBtn"/>	
		</span>
		</center>
</s:form>


 <!--快捷意见窗口  -->
<!-- <div id="alertUnOp" class="alert" style="width:430px; height: 190px">
			<h4>
				<span>快捷内容</span><span id="close" class="close"
					style="margin-left: 230px;" onclick="closeAlert('alertUnOp');">关闭</span>
			</h4>
			<p>
				<label>选择人员:</label>
	
			</p>
			<p>
				<label>信息汇总:</label>
			<textarea name="transidea" id="transidea2" style="width: 220px;"></textarea>		
			</p>
			<p align="center">
				<input type="button" id="sub" value="确定添加" class="sub"
					onclick="insertAtCursor();" />&nbsp;&nbsp;
			</p>
			<br>		
			</p>
	</div> -->
</body>
<script type="text/javascript">	


$(document).ready(function() {
	/* $("#isReady").val("ok"); */
	nodeCode = $("#nodeCode").val();
var ideacode = $("#ideacode").val();
if(ideacode=='F' || ideacode==''){//
	_get("com_0").style.display = "none";
	_get("com_1").style.display = "none";
	_get("com_2").style.display = "none";
	_get("com_4").style.display = "none";
}else{	    	
	_get("com_0").style.display = "block";
	_get("com_1").style.display = "block";
	_get("com_2").style.display = "block";
	_get("com_4").style.display = "block";
}

adjustHeight();

});


function unDoPass(unitCode) {
	setAlertStyle('alertUnOp');

}
function openmeeting(){
	
	var meetno=$("#meetingNo").val();
    var winUrl='<%=request.getContextPath()%>/oa/oaMeetApply!meetplan.do?s_meetingNo='+meetno; 
    var winProps='';
    var winName='meetingno';
	openNewWindow(winUrl,winName,winProps);
	
}

function openNewWindow(winUrl,winName,winProps){
	if(winProps =='' || winProps == null){
		winProps = 'height=550px,width=800px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
	}
	window.open(winUrl, winName, winProps);
}
    function checkForm(){
    	
    	//alert(checkFormBase());
    	if(checkFormBase() == true&&docheckBase2()==true){
    		var flag = true;
    		$.ajax({
    			type : "POST",
    			async: false,
    			dataType : "json",
    			url : "oaMeetApply!isTFree.do?djId="+$("#djId").val() +"&doBegTime="+$("#doBegTime").val()+"&doEndTime="+$("#doEndTime").val()+"&meetingNo="+$("#meetingNo").val(),
    			success : function(json) {
    				if(!json){
    					/* alert("申请时间已被占用,请选择其他时间段"); */
    					$('#doBegTime').focus();
    					//ajax直接返回无效
    					flag = false;
    				}
    			},
    			error : function() {
    				alert("失败");
    				flag = false;
    			}
    		}); 
    		
    		if(!flag){
    			if(window.confirm("申请的会议室已被占用,确认提交原申请者申请会议室会被顶替!")){
    				return true;
    			}else{
    				return false;
    			}
    		}	
    	}else{
    		return false;
    	}
    }
    
    function docheckBase2(){
		var flag = true;
		var date=new Date();
		if(ideacode!='F'){
			if ($("#doBegTime").val() != '') {
				var dt = new Date($("#doBegTime").val().replace(/-/,"/"));  
				if (dt <date) {
					if(window.confirm("所选计划开始时间发生在过去,是否确认?")){
						flag = true;
					}else{
						$("#doBegTime").focus();
						flag = false;
					}
		   		}
				return flag;
			}
	
			if ($("#doEndTime").val() != '') {
				var dt = new Date($("#doEndTime").val().replace(/-/,"/"));  
				if (dt <date) {
					if(window.confirm("所选计划结束时间发生在过去,是否确认?")){
						flag = true;
					}else{
						$("#doEndTime").focus();
						flag = false;
					}
		  		 }
				return flag;
			}
		}
	}
    
	function checkFormBase() {
// 		if($("#doTime").val()==''){
// 			alert("安排时间不可为空！");
// 			$('#doTime').focus();
// 			return false;
// 		}	
// 		if(null!=$("#doDepno").val()&&$("#doDepno").val().length>13){
// 			alert("安排部门长度不可大于13！");
// 			$('#doDepno').focus();
// 			return false;
// 		}	
// 		if(null!=$("#doCreater").val()&&$("#doCreater").val().length>3){
// 			alert("安排人长度不可大于3！");
// 			$('#doCreater').focus();
// 			return false;
// 		}	
// 		if($("#doCreater").val()==''){
// 			alert("安排人员不可为空！");
// 			$('#doCreater').focus();
// 			return false;
// 		}	
// 		if($("#doDepno").val()==''){
// 			alert("安排部门不能为空！");
// 			$('#doDepno').focus();
// 			return false;
// 		}	

        var flag = true;
        var ideacode = $("#ideacode").val();
        if(ideacode==''){
        	alert("请确定是否同意会议安排！");
			$('#ideacode').focus();
			flag = false;
			return flag;
        }
        
        if(ideacode!='F'){
        	
        	if ($("#xbOrgCodes").val() == '') {
    			alert("请选择会议保障部门！");
    			$('#xbOrgNames').focus();
    			flag = false;
    			return flag;
    		}	
        	
        if ($("#doBegTime").val() == '') {
			alert("开始时间不能为空！");
			$('#doBegTime').focus();
			flag = false;
			return flag;
		}
		
		var date=new Date();
		/* if ($("#doBegTime").val() != '') {
		var dt = new Date($("#doBegTime").val().replace(/-/,"/"));  
		if (dt <date) {
			alert("所选时间发生在过去，确定此项操作吗？");
			$('#doBegTime').focus();
			flag = false;
			return flag;
		   }
		} */
// 		if ($("#doBegTime").val() < $("#temp_hys_doBegTime").val()) {
// 			alert("开始时间不能小于会议室申请开始时间！");
// 			$('#doBegTime').focus();
// 			return false;
// 		}
	
	
		if ($("#doEndTime").val() == '') {
			alert("结束时间不能为空！");
			$('#doEndTime').focus();
			flag = false;
			return flag;
		}

		/* if ($("#doEndTime").val() != '') {
			var dt = new Date($("#doEndTime").val().replace(/-/,"/"));  
			if (dt <date) {
				alert("所选时间发生在过去，确定此项操作吗？");
				$('#doEndTime').focus();
				flag = false;
				return flag;
			   }
			} */
// 		if ($("#doEndTime").val() > $("#temp_hys_doEndTime").val()) {
// 			alert("结束时间不能大于会议室申请结束时间！");
// 			$('#doEndTime').focus();
// 			return false;
// 		}	


		if($("#doBegTime").val()>$("#doEndTime").val()){
			alert("开始时间不能大于结束时间！");
			$('#doBegTime').focus();
			flag = false;
			return flag;
		}
		
		if($("#xbOrgCodes").val()!=''){        	
        	document.getElementById("isBM").value="T";
        }
        }
		return flag;
	}
	
	
	function changeMeet(value){
		var flag = true;
		$.ajax({
			type : "POST",
			async: false,
			dataType : "json",
			url : "oaMeetApply!isTFree.do?djId="+$("#djId").val() +"&doBegTime="+$("#doBegTime").val()+"&doEndTime="+$("#doEndTime").val()+"&meetingNo="+$("#meetingNo").val(),
			success : function(json) {
				if(!json){
					alert("申请时间已被占用,请选择其他时间段");
					$('#doBegTime').focus();
					//ajax直接返回无效
					flag = false;
				}
			},
			error : function() {
				alert("失败");
				flag = false;
			}
		}); 
		
		return flag;	
	}
	var _get = function (id) {
		return document.getElementById(id);
	};
	
	
	var oOrgUser = new Object();
	oOrgUser["yjUserNames"] = $("#yjUserNames");
	oOrgUser["yjCode"] = $("#yjCode");
	oOrgUser["xbOrgNames_"] = $("#xbOrgNames_");
	oOrgUser["xbOrgRoleCode_"] = $("#xbOrgRoleCode_");
	oOrgUser["nodeCode"] = $("#nodeCode");
	
	oOrgUser["xbOrgRoleCode"] = $("#xbOrgRoleCode");
	oOrgUser["xbOrgNames"] = $("#xbOrgNames");
	$("#xbOrgNames").click(function(){
		var d = '${unitsJson}';
		if(d.trim().length){
			window.parent.selectPopWin(jQuery.parseJSON(d),$("#xbOrgNames"),$("#xbOrgCodes"), oOrgUser);
		};		
	});
	
	$("#yjUserNames").click(function(){	
		var d = '${userjson}';
		if(d.trim().length){
			// 人员选择树
			//top.popUserTreeWin(d,$("#bjUserNames"),$("#bjCodes"),oOrgUser);		
			window.parent.selectPopWinTree2(d,$("#yjUserNames"),$("#yjCodes"),oOrgUser);
		};
	});
	
	$("#xbOrgNames_").click(function(){
		var d = '${unitsJsonExp}';
		if(d.trim().length){
			window.parent.selectPopWin(jQuery.parseJSON(d),$("#xbOrgNames_"),$("#xbOrgCodes_"), oOrgUser);
		};
	});
	</script>
	<script type="text/javascript">	

	// 在光标处插入字符串 
	// myField 文本框对象 
	// 要插入的值 

	function insertAtCursor(demo) {
		var xbOrgNames1=document.getElementById(demo).value;
		//if(xbOrgNames0 != xbOrgNames1){
//}
		if(xbOrgNames1==''){
			alert("加入内容为空，请确认！");
			return;
		}
		var myField = document.getElementById("transcontent");
		var myValue =xbOrgNames1;
		//IE support 
		if (document.selection) {
			myField.focus();
			sel = document.selection.createRange();
			sel.text = myValue;
			sel.select();
		}
		//MOZILLA/NETSCAPE support 
		else if (myField.selectionStart || myField.selectionStart == '0') {
			var startPos = myField.selectionStart;
			var endPos = myField.selectionEnd;
			// save scrollTop before insert 
			var restoreTop = myField.scrollTop;
			myField.value = myField.value.substring(0, startPos) + myValue
					+ myField.value.substring(endPos, myField.value.length);
			if (restoreTop > 0) {
				// restore previous scrollTop 
				myField.scrollTop = restoreTop;
			}
			myField.focus();
			myField.selectionStart = startPos + myValue.length;
			myField.selectionEnd = startPos + myValue.length;
		} else {
			myField.value += myValue;
			myField.focus();
		}
	}
	function plan(){
		art.dialog
		.open('${pageContext.request.contextPath}/oa/oaMeetApply!meetApplyPanel.do?from=doMeeting',
				 {title: '', width: 1050, height: 640},false);
		/* window.document.URL="${pageContext.request.contextPath}/oa/oaMeetApply!meetApplyPanel.do"; */
	}
</script>
</html>