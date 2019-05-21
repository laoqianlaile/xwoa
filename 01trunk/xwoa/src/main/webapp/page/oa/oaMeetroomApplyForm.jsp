
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>
<title></title>

		<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/scripts/alertDiv.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/arrowTree.js" type="text/javascript"></script>
		<link href="${pageContext.request.contextPath}/themes/css/arrow.css" rel="stylesheet" type="text/css" />		
	</head>

<body>


	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="oaMeetroomApply" method="post" namespace="/oa"
		id="oaMeetroomApplyForm" >
		<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back" />
		<input type="button" class="btn" value="保存"
			onclick="submitItemFrame('SAVE');" />
		<input type="button" class="btn" id="saveAndSubmit" value="提交"
			onclick="submitItemFrame('SUB');" />
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="flowinstid" name="flowinstid"
			value="${flowinstid}" />
		<input type="hidden" id="flowCode" name="flowCode" value="000860" />
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

			<tr>
				<td class="addTd">申请单22号</td>
				<input type="hidden" id="applyNo" name="applyNo"
					value="${object.applyNo}" />
				<td align="left" colspan="3">${object.applyNo}</td>

			</tr>

			<tr>
				<td class="addTd">会议室<span style="color: red">*</span>
				</td>

				<td align="left"> <select
					id="meetingNo" name="meetingNo">
						<c:forEach var="dt" items="${oaMeetinfolist}">
							<option value="${dt.djid}"
								<c:if test="${dt.djid==meetingNo}"> selected="selected"</c:if>>${dt.meetingName
								}</option>
						</c:forEach>
				</select></td>
				<td class="addTd">申请时间<span style="color: red">*</span>
				</td>
				<td align="left">
<%-- 			<sj:datepicker id="createtime" name="createtime" value="%{object.createtime}" style="width: 120px;"
							 yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true" readonly="true" cssStyle="width: 100px;" />			
						 --%>
						 <input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="createtime" name="createtime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />		
						</td>
			</tr>



			<tr>
				<td class="addTd">预计开始时间<span style="color: red">*</span>
				</td>
				<td align="left"><%-- <sj:datepicker id="planBegTime"
						value="%{object.planBegTime}" name="planBegTime"
						style="width:120px" yearRange="2000:2024" displayFormat="yy-mm-dd" timepickerFormat="HH:mm"  timepicker="true"
						changeYear="true" readonly="true" 
						/> --%>
						<input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.planBegTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="planBegTime" name="planBegTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />		
						</td>
				<td class="addTd">预计结束时间<span style="color: red">*</span>
				</td>
				<td align="left"><%-- <sj:datepicker id="planEndTime"
						value="%{object.planEndTime}" name="planEndTime"
						style="width:120px" yearRange="2000:2024" displayFormat="yy-mm-dd" timepickerFormat="HH:mm"  timepicker="true"
						changeYear="true" readonly="true" /> --%>
						<input type="text" class="Wdate" style="height:28px;border-radius:3px;border: 1px solid #cccccc;" value='<fmt:formatDate value="${object.planEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					  		id="planEndTime" name="planEndTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" placeholder="选择日期" />		
						</td>
			</tr>

			<tr>
				<td class="addTd">联系电话</td>
				<td align="left" colspan="3"><s:textfield name="telephone"
						value="%{object.telephone}" id="telephone" style="width: 100%;height: 27px;"/></td>
		
			</tr>
			
			<tr>
				<td class="addTd">需求描述</td>
				<td align="left" colspan="3"><s:textarea name="reqRemark"
						style="width:100%;height:50px;" id="reqRemark" /></td>
			</tr>

			<tr>
				<td class="addTd">备注</td>
				<td align="left" colspan="3"><s:textarea name="remark"
						style="width:100%;height:50px;" id="remark" /></td>
			</tr>



		</table>

		<!-- 附件上传-->
		<table>
			<iframe id="basicsj" name="sjfj"
				src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotoCFsqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&suppowerstuffinfo.groupId=285"
				width="100%" frameborder="no" scrolling="false" border="0"
				marginwidth="0" marginheight="0"
				onload="this.height=window.frames['sjfj'].document.body.scrollHeight"></iframe>
			<!-- 附件上传-->
        </table>
			</s:form>
</body>
<%-- <%@ include file="/page/common/scripts.jsp"%> --%>
<script type="text/javascript">
function checkIsFree() {
	return {
		planEndTime: $.trim($("#planEndTime").val().trim()),
		planBegTime: $.trim($("#planBegTime").val()),
		meetingNo: $.trim($("#meetingNo").val()),
		djId: $.trim($("#djId").val())
	};
}



	function submitItemFrame(subType) {
		if (!docheck()) {
			return;
		} else {
			var srForm = document.getElementById("oaMeetroomApplyForm");
			if (subType == 'SAVE') {
			
				srForm.action = 'oaMeetroomApply!savePermitReg.do';
			}

			if (subType == 'SUB') {
				srForm.action = 'oaMeetroomApply!saveAndSubmit.do';
			}
			srForm.submit();
		}
	}

	function docheck() {
		
		if ($("#meetingNo").val() == '') {
			alert("会议室不可为空！");
			$('#meetingNo').focus();
			return false;
		}
		if ($("#planBegTime").val() == '') {
			
			alert("开始时间不能为空！");
			$('#planBegTime').focus();
			return false;
		}
		
		var date=new Date();
		if ($("#planBegTime").val() != '') {
		//弹出效果 2014-06-26 12:00:00    2014/06-26 12:00:00 
       // alert($("#planBegTime").val()+"======"+$("#planBegTime").val().replace(/-/,"/"));
		
		var dt = new Date($("#planBegTime").val().replace(/-/,"/"));  
		if (dt <date) {
			alert("所选时间发生在过去，确定此项操作吗？");
			$('#planBegTime').focus();
			return false;
		   }
		}
		
	
	
		if ($("#planEndTime").val() == '') {
			alert("结束时间不能为空！");
			$('#planEndTime').focus();
			return false;
		}

		if ($("#planEndTime").val() != '') {
			//弹出效果 2014-06-26 12:00:00    2014/06-26 12:00:00 
	       // alert($("#planBegTime").val()+"======"+$("#planBegTime").val().replace(/-/,"/"));
			var dt = new Date($("#planEndTime").val().replace(/-/,"/"));  
			if (dt <date) {
				alert("所选时间发生在过去，确定此项操作吗？");
				$('#planEndTime').focus();
				return false;
			   }
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

		if ($("#telephone").val() != null
				&& getStringLen($("#telephone").val()) > 12) {
			alert("手机号不可 超过11位！");
			$('#telephone').focus();
			return false;
		}
		
		var flag = true;
		$.ajax({
			type : "POST",
			async: false,
			dataType : "json",
			url : "oaMeetroomApply!isTFree.do?djId="+$("#djId").val() +"&planBegTime="+$("#planBegTime").val()+"&planEndTime="+$("#planEndTime").val()+"&meetingNo="+$("#meetingNo").val(),
			success : function(json) {
				if(!json){
					alert("申请时间已被占用,请选择其他时间段!");
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
		
		return flag;
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
</script>
</html>
