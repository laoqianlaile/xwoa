<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<%-- <sj:head locale="zh_CN" /> --%>
<title><s:text name="militarycases.edit.title" /></title>
<script src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<%@ include file="/page/common/messages.jsp"%>
<s:form action="oaMeetroomApply"  method="post" namespace="/oa" id="oaMeetroomApplyForm" target="_parent" onsubmit="return checkForm();">
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="meetingNo" name="meetingNo" value="${object.meetingNo}" />
		
		<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />		
        <input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}">
        <input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">
        <input type="hidden" id="flowCode" name="flowCode"  value="000860" />
         <input type="hidden" id="moduleCode" name="moduleCode" value="${moduleCode}" />
           <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			 <c:if test="${nodeCode eq 'hys_hysap'}">
			 <div >
		          <input type="button" class='btn' id="sub1" onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaMeetroomApply!meetplan.do?s_meetingNo=${object.meetingNo} ','powerWindow',null);" value="安排记录" />
		         
			 </div>
			 </c:if>
			
				<tr>
					<td class="addTd">
						安排时间<span style="color:red">*</span>
					</td>
					 <td align="left" colspan="3">
						<%-- <sj:datepicker id="doTime" value="%{object.doTime}"
							name="doTime"  style="width:120px"
							yearRange="2000:2024" displayFormat="yy-mm-dd" changeYear="true" readonly="true"/> --%>
					<input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.doTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="doTime" name="doTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />		
					
					</td>
				</tr>
               <tr>
					<td class="addTd">
						安排开始时间<span style="color:red">*</span>
					</td>
					 <td align="left" >
						<%-- <sj:datepicker id="doBegTime" value="%{object.doBegTime}"
							name="doBegTime"  style="width:120px"
							yearRange="2000:2024" displayFormat="yy-mm-dd" changeYear="true" readonly="true"/> --%>
					<input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.doBegTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="doBegTime" name="doBegTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />		
					
					</td>
						<td class="addTd">
						安排结束时间<span style="color:red">*</span>
					</td>
					 <td align="left" >
					<%-- 	<sj:datepicker id="doEndTime" value="%{object.doEndTime}"
							name="doEndTime"  style="width:120px"
							yearRange="2000:2024" displayFormat="yy-mm-dd" changeYear="true" readonly="true"/>
					 --%>
	<input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.doEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="doEndTime" name="doEndTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />		
					
					
					</td>
					</tr>
				
				        <tr>
					<td class="addTd">
						安排部门
					</td>
					 <td align="left" >
						<s:textfield name="doDepno"  value="%{object.doDepno}" id="doDepno" style="width: 100%;height: 27px;"/>
					</td>
						<td class="addTd">
						安排人
					</td>
					 <td align="left" >
						<s:textfield name="doCreater"  value="%{object.doCreater}" id="doCreater" style="width: 100%;height: 27px;"/>
					</td>
					</tr>
					        <tr>
					<td class="addTd">
						安排备注
					</td>
					 <td align="left" colspan="3">
						<s:textarea name="doRemark" style="width:100%;height:50px;" id="doRemark" rows="50"/>
					</td>
			
					</tr>
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

</body>
<script type="text/javascript">	
	function checkForm() {
		if($("#doTime").val()==''){
			alert("安排时间不可为空！");
			$('#doTime').focus();
			return false;
		}	
		
		

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


        if ($("#doBegTime").val() == '') {
			alert("开始时间不能为空！");
			$('#doBegTime').focus();
			return false;
		}
		
		var date=new Date();
		if ($("#doBegTime").val() != '') {
		var dt = new Date($("#doBegTime").val().replace(/-/,"/"));  
		if (dt <date) {
			alert("所选时间发生在过去，确定此项操作吗？");
			$('#doBegTime').focus();
			return false;
		   }
		}
		
	
	
		if ($("#doEndTime").val() == '') {
			alert("结束时间不能为空！");
			$('#doEndTime').focus();
			return false;
		}

		if ($("#doEndTime").val() != '') {
			var dt = new Date($("#doEndTime").val().replace(/-/,"/"));  
			if (dt <date) {
				alert("所选时间发生在过去，确定此项操作吗？");
				$('#doEndTime').focus();
				return false;
			   }
			}
			


		if($("#doBegTime").val()>$("#doEndTime").val()){
			alert("开始时间不能大于结束时间！");
			$('#doBegTime').focus();
			return false;
		}	
		
	 	var flag = true;
		$.ajax({
			type : "POST",
			async: false,
			dataType : "json",
			url : "oaMeetroomApply!isTFree.do?djId="+$("#djId").val() +"&doBegTime="+$("#doBegTime").val()+"&doEndTime="+$("#doEndTime").val()+"&meetingNo="+$("#meetingNo").val(),
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
	function openNewWindow(winUrl,winName,winProps){
		if(winProps =='' || winProps == null){
			winProps = 'height=550px,width=800px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
</script>
</html>