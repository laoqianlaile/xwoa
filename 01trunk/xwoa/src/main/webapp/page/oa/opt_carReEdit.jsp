<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
	<%-- <sj:head locale="zh_CN" /> --%>
<title><s:text name="militarycases.edit.title" /></title>
</head>
<body>
<%@ include file="/page/common/messages.jsp"%>
<s:form action="oaCarApply"  method="post" namespace="/oa" id="oaCarApplyForm" target="_parent" onsubmit="return checkForm();">
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="flowInstId" name="flowInstId" value="${flowInstId}" />		
        <input type="hidden" id="nodeCode" name="nodeCode" value="${nodeCode}">
        <input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">
        <input type="hidden" id="flowCode" name="flowCode"  value="000858" />
         <input type="hidden" id="moduleCode" name="moduleCode" value="${moduleCode}" />
           <table border="0" cellpadding="0" cellspacing="0" class="viewTable">
 
				<tr>
				<td class="addTd">申请单号</td>
				<input type="hidden" id="applyNo" name="applyNo"
					value="${object.applyNo}" />
				<td align="left" colspan="3">${object.applyNo}</td>

			</tr>

				<td class="addTd">申请时间<span style="color: red">*</span>
				</td>
				<td align="left">
								<input type="text" class="Wdate"  id="createtime" name="createtime" 
								style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
								value='<fmt:formatDate value="${object.createtime}" pattern="yyyy-MM-dd"/>'
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">				
<%-- 				<sj:datepicker id="createtime" --%>
<%-- 						value="%{object.createtime}" name="createtime" style="width:120px" --%>
<%-- 						yearRange="2000:2024" displayFormat="yy-mm-dd" changeYear="true" --%>
<%-- 						readonly="true" /> --%>
						</td>
						<td class="addTd">乘车人数</td>
				 <td align="left"><s:textfield name="meetingPersionsNum"
						value="%{object.meetingPersionsNum}" id="meetingPersionsNum" /></td>
			
			</tr>



			<tr>
				<td class="addTd">预计开始时间<span style="color: red">*</span>
				</td>
				<td align="left">
					<input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value="${object.planBegTime}"
					  			name="planBegTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期" />		
			<%-- 	<sj:datepicker id="planBegTime"
						value="%{object.planBegTime}" name="planBegTime"
						style="width:120px" yearRange="2000:2024" displayFormat="yy-mm-dd"
						changeYear="true" readonly="true" /> --%></td>
				<td class="addTd">预计结束时间<span style="color: red">*</span>
				</td>
				<td align="left">
					<input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value="${object.planEndTime}"
					  	id="planEndTime"		name="planEndTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期" />		
				<%-- <sj:datepicker id="planEndTime"
						value="%{object.planEndTime}" name="planEndTime"
						style="width:120px" yearRange="2000:2024" displayFormat="yy-mm-dd"
						changeYear="true" readonly="true" /> --%></td>
			
			</tr>

			<tr>
				<td class="addTd">联系电话</td>
				<td align="left"><s:textfield name="telephone"
						value="%{object.telephone}" id="telephone" /></td>
		
				<td class="addTd">目的地</td>
				<td align="left"><s:textfield name="destination"
						id="destination" /></td>
			</tr>
			<tr>
				<td class="addTd">是否往返</td>
				<td align="left" ><s:textfield name="meetingNo"
						 id="meetingNo" /></td>
		     	<td class="addTd">用车是由<span style="color: red">*</span></td>
				<td align="left" ><s:textfield name="reqRemark"
						id="reqRemark" /></td>
			</tr>
	
			<tr>
				<td class="addTd">行车路线</td>
				<td align="left" colspan="3"><s:textarea name="meetingPersions"
						style="width:100%;height:50px;" id="meetingPersions" /></td>
			</tr>

			<tr>
				<td class="addTd">备注</td>
				<td align="left" colspan="3"><s:textarea name="remark"
						style="width:100%;height:50px;" id="remark" /></td>
			</tr>

</table>
<!-- 附件上传-->
<table>
			<iframe  id="basicsj" name="sjfj" src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotoCFsqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&suppowerstuffinfo.groupId=286" width="100%"
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
</s:form>

</body>
<script type="text/javascript">	
	function checkForm() {

		if ($("#planBegTime").val() == '') {
			alert("开始时间不能为空！");
			$('#planBegTime').focus();
			return false;
		}
		if ($("#planEndTime").val() == '') {
			alert("结束时间不能为空！");
			$('#planEndTime').focus();
			return false;
		}

		if ($("#planBegTime").val() > $("#planEndTime").val()) {
			alert("开始时间不能大于结束时间！");
			$('#planBegTime').focus();
			return false;
		}
		if ($("#createtime").val() == '') {
			alert("申请时间不可为空！");
			$('#createtime').focus();
			return false;
		}
		if ($("#reqRemark").val() == '') {
			alert("用车是由不可为空！");
			$('#reqRemark').focus();
			return false;
		}
		
		if ($("#telephone").val() != null
				&& getStringLen($("#telephone").val()) > 12) {
			alert("手机号不可 超过11位！");
			$('#telephone').focus();
			return false;
		}
		return true;	
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
</html>