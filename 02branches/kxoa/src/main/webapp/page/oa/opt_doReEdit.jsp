<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<%-- <sj:head locale="zh_CN" /> --%>
<title><s:text name="militarycases.edit.title" /></title>
</head>
<body>
<fieldset class="_new">
		<legend>
			<p class="ctitle">会议室申请</p>
		</legend>
<%@ include file="/page/common/messages.jsp"%>
<s:form action="oaMeetApply"  method="post" namespace="/oa" id="oaMeetApplyForm" target="_parent" onsubmit="return checkForm();">
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />		
        <input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}">
        <input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">
        <input type="hidden" id="flowCode" name="flowCode"  value="000857" />
         <input type="hidden" id="moduleCode" name="moduleCode" value="${moduleCode}" />
           <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
 
					<tr>
					<td class="addTd">
						申请单号
					</td>
					<input type="hidden" id="applyNo" name="applyNo"  value="${object.applyNo}" />
					<td align="left" colspan="3" >
                        ${object.applyNo}
					</td>
					
				</tr>

				<tr>
					<td class="addTd">
						会议室主题<span style="color:red">*</span>
					</td>
					<td align="left" colspan="3">

						<s:textfield name="title"  value="%{object.title}" id="title" style="width:100%;"/>

					</td>
					
				</tr>

				<tr>
					<td class="addTd">
						会议室<span style="color:red">*</span>
					</td>
				
					<td align="left">
	              <select id="meetingNo" name="meetingNo" style="width:200px;height:25px;">
						<c:forEach var="dt" items="${oaMeetinfolist}">
							<option value="${dt.djid}"
								<c:if test="${dt.djid==meetingNo}"> selected="selected"</c:if>>${dt.meetingName
								}</option>
						</c:forEach>
				   </select>
<%-- 						<s:textfield name="meetingNo"  value="%{object.meetingNo}" id="meetingNo"/> --%>
	
					</td>
					   <td class="addTd">
						申请时间<span style="color:red">*</span>
					</td>
	                 <td align="left" >
					 <input type="text" id="createtime" class="Wdate" style="width:200px;height:25px;border-radius:3px;border: 1px solid #cccccc;" 
					 value='<fmt:formatDate value="${object.createtime}" pattern="yyyy-MM-dd"/>'
					 name="createtime"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期" />			                 
<%-- 						<sj:datepicker id="createtime" value="%{object.createtime}" --%>
<%-- 							name="createtime"  style="width:120px" --%>
<%-- 							yearRange="2000:2024" displayFormat="yy-mm-dd" changeYear="true" readonly="true"/> --%>
					</td>
				</tr>

				

				<tr>
					<td class="addTd">
						预计开始时间<span style="color:red">*</span>
					</td>
					 <td align="left" >
					 <input type="text" class="Wdate" style="width:200px;height:25px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.planBegTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="planBegTime" name="planBegTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />		
						<%-- <sj:datepicker id="planBegTime" value="%{object.planBegTime}"
							name="planBegTime"  style="width:120px"
							yearRange="2000:2024" displayFormat="yy-mm-dd" changeYear="true" readonly="true"/> --%>
					</td>
					<td class="addTd">
						预计结束时间<span style="color:red">*</span>
					</td>
					 <td align="left" >
					 <input type="text" class="Wdate" style="width:200px;height:25px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.planEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="planEndTime" name="planEndTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />		
						<%-- <sj:datepicker id="planEndTime" value="%{object.planEndTime}"
							name="planEndTime"  style="width:120px"
							yearRange="2000:2024" displayFormat="yy-mm-dd" changeYear="true" readonly="true"/> --%>
					</td>
				</tr>

				<tr>
					<td class="addTd">
						联系电话
					</td>
					<td align="left" >
	               <s:textfield name="telephone"  value="%{object.telephone}" id="telephone" style="width:200px;height:25px;" />
					</td>
					<td class="addTd">
						参会人数
					</td>
					<td align="left" >
	               <s:textfield name="meetingPersionsNum"  value="%{object.meetingPersionsNum}" id="meetingPersionsNum" style="width:200px;height:25px;"/>
					</td>
				</tr>
                <tr>
					<td class="addTd">
						参会人员
					</td>
					<td align="left" colspan="3">
  
					<s:textarea name="meetingPersions" style="width:100%;height:50px;" id="meetingPersions"/> 
	
					</td>
				</tr>
				  <tr>
					<td class="addTd">
						外部参会人员
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="outerPersions" style="width:100%;height:50px;" id="outerPersions"/>
	
					</td>
				</tr>
				<tr>
					<td class="addTd">
					需求描述
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="reqRemark" style="width:100%;height:50px;" id="reqRemark" />
	
					</td>
				</tr>
				
				<tr>
					<td class="addTd">
						备注
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="remark" style="width:100%;height:50px;" id="remark" />

					</td>
				</tr>

				

</table>
<!-- 附件上传-->
<table>
			<iframe  id="basicsj" name="sjfj" src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotoCFsqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&suppowerstuffinfo.groupId=285" width="100%"
							frameborder="no" scrolling="false" border="0" marginwidth="0"
							marginheight="0" onload="this.height=window.frames['sjfj'].document.body.scrollHeight"></iframe>	
			<!-- 附件上传-->	
				

	<center style="margin-top: 10px;">
		<span style="display:none;" >
			<s:submit name="saveAndSubmit" method="submitReEdit" cssClass="btn" value="提 交" id="submitBtn"/>
		</span>
		<span style="display:none;" >	
			<s:submit name="save" method="saveReEdit" cssClass="btn" value="保 存" id="saveBtn"/>
		</span>	
		<span style="display:none;" >	
			<input type="button" value="返回" class="btn"  onclick="window.history.go(-1);" id="backBtn"/>	
		</span>
		</center>

</table>
</s:form>
</fieldset>

</body>
<script type="text/javascript">	
	function checkForm() {
		if($("#doTime").val()==''){
			alert("安排时间不可为空！");
			$('#doTime').focus();
			return false;
		}	

		if($("#doCreater").val()==''){
			alert("安排人员不可为空！");
			$('#doCreater').focus();
			return false;
		}	
		if($("#doDepno").val()==''){
			alert("安排部门不能为空！");
			$('#doDepno').focus();
			return false;
		}		
		
		return true;	
	}
</script>
</html>