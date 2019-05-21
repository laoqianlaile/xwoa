﻿<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title></title>
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrow.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
	
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>		
	<script type="text/javascript">
function checkMeetType(t){
	if(t!=null||t!=''){
		if(t=='O'){
			$(".checkMeetType").each(function(i){
			    $(this).css("display","none");
		   });
		}else{
			$(".checkMeetType").each(function(i){
				  $(this).css("display","block");
		   });
		}
	}
}
</script>
	</head>

<body onload="window.status='完毕';" class="sub-flow">
<fieldset class="_new">
		<legend>
			<p>会议室申请登记</p>
		</legend>

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="oaMeetApply" method="post" namespace="/oa"
		id="oaMeetApplyForm" >
     <div align="left">
		<input type="button" class="btn" value="保存"
			onclick="submitItemFrame('SAVE');" />
		<input type="button" class="btn" id="saveAndSubmit" value="提交"
			onclick="submitItemFrame('SUB');" />
		<!-- <input type="button" name="back" class="btn" onclick="history.back(-1);" value="返回"> -->
	 </div>
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" name="show_type" value="${show_type }"/>
		<input type="hidden" id="flowinstid" name="flowinstid"
			value="${flowinstid}" />
		<input type="hidden" id="flowCode" name="flowCode" value="000857" />
	    <input type="hidden" id="supDjid" name="supDjid" value="${object.supDjid}" />
	    <input type="hidden" id="applyNo" name="applyNo" value="${object.applyNo}" />
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
		    <tr style="display: none;">
		        <td class="addTd">会议类型</td>
				<td align="left" colspan="3" id="meetType" >
		        <s:radio name="meetType" id="checkMeetType" onclick="checkMeetType(this.value);" list="#{'O':'一般会议' ,'P':'视频会议'}" listKey="key" listValue="value" ></s:radio>
		        </td>
		    </tr>
			<tr class="checkMeetType">
			<td class="addTd">发言单位</td> 
				 <td align="left">
					        <select name="units" id="s_units">
							<option value="">--请选择--</option>
							<c:forEach var="row" items="${unitlist}">
								<option value="${row.unitcode}"
									<c:if test="${row.unitcode==units}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					        </select>
			   </td>
				<td class="addTd">会议范围
				</td>
				<td align="left"><s:radio name="meeting_rang" id="s_meeting_rang" onclick="checkMeetType(this.value);" list="#{'S':'交通部会议','M':'自治区系统会议' }" listKey="key" listValue="value" ></s:radio></td>
                
			</tr>
			
			<tr class="checkMeetType">
			    <td class="addTd">使用会场</td> 
				<td align="left"><s:radio name="use_venue" id="s_use_venue" list="#{'4':'厅四楼会议室','8':'厅配八楼会议室','0':'其他'}" onclick="checkMeetType(this.value);"   listKey="key" listValue="value" ></s:radio> </td>
				 <td class="addTd">使用其他会场
				</td>
				<td align="left"><s:textfield name="otheruse_venue" 
						value="%{object.otheruse_venue}" id="otheruse_venue" style="height: 27px;width:200px;" /></td> 
			</tr>
		   <tr class="checkMeetType">
				<td class="addTd">是否双流会议
				</td>
				<td align="left"  >
		        <s:radio name="isSLmeeting" id="s_isSLmeeting" onclick="checkMeetType(this.value);" list="#{'N':'否' ,'P':'是'}" listKey="key" listValue="value" ></s:radio>
		        </td>
		        <td class="addTd">联调时间
				</td>
				<td align="left">
		        <input type="text" class="Wdate" style="height:25px;width:200px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${Alignment_time}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="s_Alignment_time" name="Alignment_time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />				
			    </td>
			</tr>
 
			<tr>
				<td class="addTd">会议名称<span style="color: red">*</span>
				</td>
				<td align="left" ><s:textfield name="title"
						value="%{object.title}" id="title" style="width: 100%;height: 27px;" /></td>
				<td class="addTd">会议部门</td>
				      <td align="left">
					        <select name="depno" id="s_depno">
							<option value="">--请选择--</option>
							<c:forEach var="row" items="${unitlist}">
								<option value="${row.unitcode}"
									<c:if test="${row.unitcode==depno}">selected="selected"</c:if>>
									<c:out value="${row.unitname}" />
								</option>
							</c:forEach>
					        </select>
					     </td>                
			</tr>
			<tr>
				<td class="addTd">预计开始时间<span style="color: red">*</span>
				</td>
				<td align="left">						
				<input type="text" class="Wdate" style="height:25px;width:200px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.planBegTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="planBegTime" name="planBegTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />				
						</td>
				<td class="addTd">预计结束时间<span style="color: red">*</span>
				</td>
				<td align="left">
				<input type="text" class="Wdate" style="height:25px;width:200px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.planEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="planEndTime" name="planEndTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />				
						</td>
			</tr>
            <tr>
                      
			<td class="addTd">联系人</td>
				<td align="left">${cp:MAPVALUE("usercode",usercode)}</td>
			<td class="addTd">联系电话</td> 
				<td align="left"><s:textfield name="telephone" 
						value="%{object.telephone}" id="telephone" style="width: 200px;height: 27px;"/></td> 
           </tr>

			<tr>
                <td class="addTd">参会单位</td> 
				<td align="left" colspan="3">
						<input type="hidden" id="unitcode" name="attendingUnits" value="${attendingUnits}"/>
						<s:textarea name="uninName" id="uninName" style="width:85%;height:40px;" value="%{retValue}" readonly="true"/>
			        <input type="button" class='btn' name="powerBtn"
					onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaMeetApply!listSelectOrg.do?ec_o_001709=true&ec_o_1=true','orgWindow',null);"
					value="选择">		</td> 	
			</tr>
			<tr>
                <td class="addTd"><s:text name="oaMeetApply.otherUnits"/></td> 
				<td align="left" colspan="3">
				<s:textarea name="otherUnits" id="otherUnits" style="width:85%;height:40px;"/>
			</tr>
			<tr>
                <td class="addTd"><s:text name="oaMeetApply.attendingLeaderNames"/><span style="color: red">*</span></td> 
				<td align="left" colspan="3">
				<input type="hidden" id="attendingLeadercodes" name="attendingLeadercodes" value="${attendingLeadercodes}" />
				<s:textarea name="attendingLeaderNames" id="attendingLeaderNames" style="width:85%;height:40px;"/><input type="button" class='btn' id="attendingLeader" name="attendingLeader" value="选择"><br>(<span style="color: blue">温馨提示：如果是外单位领导成员请手工输入，人员之间请用“,”隔开;如果没有请填“无”。</span>)
			</tr>
			<tr>
                <td class="addTd"><s:text name="oaMeetApply.recomUnitNames"/></td> 
				<td align="left" colspan="3">
				<input type="hidden" id="recomUnits" name="recomUnits" value="${recomUnits}" />
			    <s:textarea name="recomUnitNames" id="recomUnitNames" style="width:85%;height:40px;" readonly="readonly"/>
			</tr>
			<tr>
				<td class="addTd">会议地点<span style="color: red">*</span>
				</td>

				<td align="left">			
		<s:select id="meetingNo" name="meetingNo" list="meetings" listKey="id" listValue="name" headerKey="" headerValue="--请选择--" onchange="doshowmeetinfo();"/>		
			
					</td>
					<td class="addTd">参会人数<span style="color: red">*</span></td>
          <td>
				<s:textfield name="meetingPersionsNum"
						value="%{object.meetingPersionsNum}" id="meetingPersionsNum" style="width: 200px;height: 27px;"/> </td>
			<tr>
				<td class="addTd">是否有基层单位参加<span style="color: red">*</span></td>
				<td align="left">
			<s:radio name="isBasicUnit" id="isBasicUnit"  list="#{'T':'是','F':'否'}" listKey="key" listValue="value"   value="%{isBasicUnit}" style="width:20px;height:20px;"></s:radio>
				</td>
				<td class="addTd">是否需要停车<span style="color: red">*</span></td>
				<td align="left">
				<s:radio name="isStopCar" id="isStopCar"  list="#{'T':'是','F':'否'}" listKey="key" listValue="value"   value="%{isStopCar}" style="width:20px;height:20px;"></s:radio>
				</td>
			</tr>
			<tr>
				<td class="addTd">会议标准</td>
				<td align="left" colspan="3" id="reqRemark" style="height:60px;">
				<s:checkboxlist  list="#request.dataMap" name="reqRemark" onclick="dataMap(this);"  listKey="key" listValue="value" value="%{reqRemarkList}" cssStyle="width:20px;height:20px;"></s:checkboxlist>(<span style="color: red">温馨提醒：请务必详细描述会议要求，如桌签的内容等。</span>)	
				<input type="text" name="otherItem" id="otherItem" value="${object.otherItem }" style="display:none;width:340px;height:50px;"/>
				</td>
			</tr>

			<tr>
				<td class="addTd">会议要求</td>
				<td align="left" colspan="3"><s:textarea name="remark"
						style="width:100%;height:50px;" id="remark" /></td>
			</tr>
			
				  <!-- 					温馨提示	 -->
			<c:if test="${not empty moduleParam.tips}">	
			<tr>
			    <td colspan="4" align="left">
				<img class="wxtx_img" src="${pageContext.request.contextPath}/newStatic/image/wxtx.png" /> <span class="wxtx_span" >温馨提示：</span><span class="wxtx_count"> ${moduleParam.tips}</span>
				</td>
			 </tr>
		    </c:if>



		</table>
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
		<!-- 附件上传-->
		<table>
			<iframe id="basicsj" name="sjfj"
				src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&stuffInfo.groupid=285"
				width="100%" frameborder="no" scrolling="false" border="0"
				marginwidth="0" marginheight="0"
				onload="this.height=window.frames['sjfj'].document.body.scrollHeight"></iframe>
			<!-- 附件上传-->
        </table>
			</s:form>
</fieldset>
</body>
<script type="text/javascript">
//切换不同会议室时候，显示对应会议室的配置说明
function doshowmeetinfo(){
	//验证件号是否重复
	   var result = $.ajax({
			url: "${contextPath}/oa/oaMeetinfo!checkMeetinfo.do?djid=" + $("#meetingNo").val(),
			async: false
		}).responseText;
		if ("false" == result) {
			return false;
		} else {
		alert(result);
		return true;
		}
}


function dataMap(t){
	var ev = $("input[name='reqRemark']:checkbox");
	if(ev.eq(5).is(":checked")==true  ){
	    $("#otherItem").css("display","block");
	}else{
		$("#otherItem").css("display","none");
	}
	
}

$(function() {
	
	//初始化会议类型类型为P:视频会议室O:一般会议室(默认选中)
	var val = $("input[name='meetType']:radio:checked").val();
	if(val==undefined||val==null){
		$("input[name='meetType']:radio").eq(0).attr("checked","checked");
		$(".checkMeetType").each(function(){
			  $(this).css("display","none");
	   });
	}else{checkMeetType(val);}
	//查看会议标准其他
	var ev = $("input[name='reqRemark']:checkbox");
	if(ev.eq(5).is(":checked")==true  ){
	    $("#otherItem").css("display","block");
	}else{
		$("#otherItem").css("display","none");
	}
	//查看安排记录
	if($('#meetingNo').val()==''){
		$('#meeting_button').attr("disabled","disabled");
	}
	if($("input[name='isBasicUnit']:checked").val()=='F'){
		$("input[name='isStopCar']").attr("disabled","disabled");
		
		
	}
// 	$('#planBegTime').attr("onclick","WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '"+document.getElementById('temp_hys_doBegTime' ).value+"',maxDate: '"+document.getElementById('temp_hys_doEndTime').value+"'})");	
// 	$('#planEndTime').attr("onclick","WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '"+document.getElementById('temp_hys_doBegTime' ).value+"',maxDate: '"+document.getElementById('temp_hys_doEndTime').value+"'})");	
	
});
//联动 根据是否有基层单位参加，判断是否需要停车的前置
$("input[name='isBasicUnit']").click(function(){
	//alert();
	if($("input[name='isBasicUnit']:checked").val()=='F'){
		$("input[name='isStopCar']").attr("disabled","disabled");		
	}else{
		$("input[name='isStopCar']").attr("disabled",false);
	}
});


function checkIsFree() {
	return {
		planEndTime: $.trim($("#planEndTime").val().trim()),
		planBegTime: $.trim($("#planBegTime").val()),
		meetingNo: $.trim($("#meetingNo").val()),
		djId: $.trim($("#djId").val())
	};
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
		winProps = 'height=550px,width=780px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=false,scrollbars=yes,toolbar=false';
	}
	window.open(winUrl, winName, winProps);
}
function change() {
	var djId = $.trim($("#djId").val());

	$.ajax({
		type : "POST",
		url :  "${contextPath}/oa/oaMeetApply!checkdjid.do?djId="+djId,
		dataType : "json",
		success : function(data) {
			if ("none" == data.status) {
				alert("人员设置成功！");
			} else if ("exist" == data.status) {
				alert("人员设置成功失败！");
			} 
		},
		error : function() {
			alert("申请失败！");
		}
	});

}

	function submitItemFrame(subType) {
		if (!docheck()) {
			return;
		} else {
           var srForm = document.getElementById("oaMeetApplyForm");
			
			if (subType == 'SAVE') {
			
				srForm.action = 'oaMeetApply!savePermitReg.do';
			}

			if (subType == 'SUB') {
				srForm.action = 'oaMeetApply!saveAndSubmit.do';
			}
			var win = art.dialog.open.origin;//来源页面
			if(win){
			$.ajax({
                type: "post",
                url: srForm.action,     
                data: $("#oaMeetApplyForm").serialize(),    
                success: function(data) {
                	if (subType == 'SAVE') {
        				alert("此申请已被暂存");
        			}
               		// 如果父页面重载或者关闭其子对话框全部会关闭
               		win.location.reload();
               		art.dialog.close();
    				
                },
                error: function(data) {
                    alert("提交失败，请重新尝试或联系管理员！");
                }
            })
			}
			else{
				srForm.submit();
			}
			
			
			
			
		}
	}
	
	function docheckBase2(){
		var flag = true;
		var date=new Date();
		if ($("#planBegTime").val() != '') {
		var dt = new Date($("#planBegTime").val().replace(/-/,"/"));  
		if (dt <date) {
			if(window.confirm("所选计划开始时间发生在过去,是否确认?")){
				flag = true;
			}else{
				$("#planBegTime").focus();
				flag = false;
			}
		   }
			return flag;
		}
	
	if ($("#planEndTime").val() != '') {
		//弹出效果 2014-06-26 12:00:00    2014/06-26 12:00:00 
       // alert($("#planBegTime").val()+"======"+$("#planBegTime").val().replace(/-/,"/"));
		var dt = new Date($("#planEndTime").val().replace(/-/,"/"));  
		if (dt <date) {
			if(window.confirm("所选计划结束时间发生在过去,是否确认?")){
				flag = true;
			}else{
				$("#planEndTime").focus();
				flag = false;
			}
		   }
			return flag;
		}
	}
	
	
	function docheckBase(){
		var flag = true;
		
		if ($("#title").val() == '') {
			alert("会议名称不可为空！");
			$('#title').focus();
			flag = false;
			return flag;
		}

		var reg = /^((0\d{2,3})([-])?)?(\d{1,11})$/;
		var val1=$("#telephone").val();
		if (val1!= '') {
            if (!reg.test(val1)) {
				alert("联系电话格式不对！");
				$('#telephone').focus();
				flag = false;
				return flag;
			}
		}
	
		if ($("#meetingNo").val() == '') {
			alert("会议室不可为空！");
			$('#meetingNo').focus();
			flag = false;
			return flag;
		}
		if ($("#planBegTime").val() == '') {
			
			alert("开始时间不能为空！");
			$('#planBegTime').focus();
			flag = false;
			return flag;
		}
		
		
		//弹出效果 2014-06-26 12:00:00    2014/06-26 12:00:00 
       // alert($("#planBegTime").val()+"======"+$("#planBegTime").val().replace(/-/,"/"));
		
		
		
		/* if ($("#planBegTime").val() < $("#temp_hys_doBegTime").val()) {
			alert("开始时间不能小于会议室申请开始时间,请确认提交！");
			flag = false;
			return flag;
		} */
		if ($("#planEndTime").val() == '') {
			alert("结束时间不能为空！");
			$('#planEndTime').focus();
			return false;
		}
		

		
		/* if ($("#planEndTime").val() > $("#temp_hys_doEndTime").val()) {
			alert("结束时间不能大于会议室申请结束时间,请确认提交！");
			$("#planEndTime").focus();
			flag = false;
			return flag;
		}	 */
		
		if ($("#planBegTime").val() > $("#planEndTime").val()) {
			alert("开始时间不能大于结束时间！");
			$('#doBegTime').focus();
			flag = false;
			return flag;
		}
		/* if ($("#createtime").val() == '') {
			alert("申请时间不可为空！");
			$('#createtime').focus();
			flag = false;
			return flag;
		} */
		if ($("#attendingLeaderNames").val() == '') {
			alert("参会领导不可为空！");
			$('#attendingLeaderNames').focus();
			flag = false;
			return flag;
		}
		if($("#meetingPersionsNum").val() ==''){
			alert("参会人数不可为空！");
			$('#meetingPersionsNum').focus();
			flag = false;
			return flag;
		}
		
		
		var r="^[1-9]\\d*$";
		if($("#meetingPersionsNum").val()!=''){
		var isvalid= (new RegExp(r)).test($("#meetingPersionsNum").val());
		if(!isvalid){
			alert("输入的参会人数格式不正确");
			$("#meetingPersionsNum").focus();
			flag = false;
			return flag;
		}
		}
		return flag;
	}

	function docheck() {
		if(docheckBase() == true&&docheckBase2() == true){
			var flag = true;
			$.ajax({
				type : "POST",
				async: false,
				dataType : "json",
				url : "oaMeetApply!isTFree.do?djId="+$("#djId").val() +"&planBegTime="+$("#planBegTime").val()+"&planEndTime="+$("#planEndTime").val()+"&meetingNo="+$("#meetingNo").val(),
				success : function(json) {
					if(!json){
						$('#planBegTime').focus();
						
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
    			if(window.confirm("申请的会议室已被占用,是否确认提交!")){
    				return true;
    			}else{
    				return false;
    			}
    		}else{
    			return flag;
    		}	
			
		}else{
    		return false;
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
	var oOrgUser = new Object();
	
	oOrgUser["meetingPersions"] = $("#meetingPersions");
	
	$("#meetingPersions").click(function(){
		var d = '${userjson}';
		
		if(d.trim().length){
			selectPopWin(jQuery.parseJSON(d),$("#meetingPersions"),$("#userValue"));
		};
	});
	function selectPopWin(json,o1,o2){
		new treePerson(json,o1,o2).init();
		setAlertStyle("attAlert");
	}


// 	$(function() {
	
// 	 $('#oaMeetApplyForm').validate(

// 				{
// 					rules : {
// 						planEndTime : {
// 							remote : {
// 								url : '${contextPath}/oa/oaMeetApply!isFree.do',
// 								type : "get",
// 								dataType : "json",
// 								data : { //要传递的数据
// 									planEndTime : function() {
// 										return $.trim($("#planEndTime").val().trim());

// 									},
// 									planBegTime : function() {
// 										return $.trim($("#planBegTime").val().trim());

// 									},
// 									meetingNo : function() {
// 										return $.trim($("#meetingNo").val().trim());

// 									},
// 									djId: function() {
// 										return $.trim($("#djId").val().trim());

// 									},

// 								}
// 							}
// 						} 
// 					}, 

// 					messages : {
// 						planEndTime : {
// 							remote : "预计时间已被申请"
// 						},
// 						planBegTime : {
// 							remote : "预计时间已被申请"
// 						},
// 					},
					
// 				}); 
// 	})
var oOrgUser = new Object();
$("#attendingLeader").click(function(){
	 var d = '${userjson}';
     if (d.trim().length) {
    	 //不要在这进行字符串转json对象
		selectPopWin(jQuery.parseJSON(d),$("#attendingLeaderNames"),$("#attendingLeadercodes"));
	} 
});

$("#recomUnitNames").click(function(){
	var d = '${unitsJson}';
	if(d.trim().length){
		selectPopWinTemp(jQuery.parseJSON(d),$("#recomUnitNames"),$("#recomUnits"));
	};
});

function selectPopWinTemp(json, o1, o2) {
	new person(json, o1, o2).init();
	setAlertStyle("attAlert");
}

function selectPopWin(json, o1, o2, oShow) {
	new treePerson(json, o1, o2, oShow,100).init();/* 此处人员限制为100人 */
	setAlertStyle("attAlert");
}
</script>
</html>
