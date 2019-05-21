<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<sj:head locale="zh_CN" />
<!--以上为选择申请人组件使用的文件  -->
<title><s:text name="oaDriverInfo.edit.title" /></title>
</head>

<body>
   <fieldset class="form">
		<legend>
		<p class="ctitle">
			添加请假
	    </p>
		</legend> 

	<%@ include file="/page/common/messages.jsp"%>
	<form action="" method="post"
		id="oaLeaveOverTimeForm">
			<input type="hidden" id="loroNo" name="loroNo" value="${dbObject.loroNo }" /><!-- object的主键 -->
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<input type="hidden"  name="createId"  value="${dbObject.createId }" />
			<tr>
				<td class="addTd">请假类型：<span style="color: red">*</span></td>
				<td align="left">
				<select id="reasonType" name="reasonType" style="width:200px;height:22px;">
								<option value="" >---请选择---</option>
								<c:forEach var="v"  items="${cp:LVB('LeaveOvertime')}">
								<option <c:if test="${ dbObject.reasonType eq v.value}">selected="selected"</c:if> value="${v.value }"><c:out value="${v.label}" /></option>
								</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
			<td class="addTd">申请人：<span style="color: red">*</span></td>
				<td align="left">
				<select id="usercode" name="usercode"  style="width:200px;height:22px;">
									<option <c:if test="${ empty dbObject.usercode}">selected="selected"</c:if> value="">---请选择---</option>
									<c:forEach items="${userCodes }"  var="user">
									<option <c:if test="${user eq dbObject.usercode }">selected="selected"</c:if>value="${user }">${cp:MAPVALUE('usercode',user) }</option>
									</c:forEach>
								</select>
				</td>
			</tr>
			<tr>
				<td class="addTd">原因描述：</td>
				<td align="left">
				<textarea id="reasonDesc" name="reasonDesc" class="span6" rows="5" cols="50">${dbObject.reasonDesc }</textarea>
				</td>
			</tr>
			<tr>
				<td class="addTd">开始时间：<span style="color: red">*</span></td>
				<td align="left">
						<input type="text" class="Wdate" id="beginTime"
								value='<fmt:formatDate value="${dbObject.beginTime}" pattern="yyyy-MM-dd"/>'
								name="beginTime"
								style="height: 20px; width: 145px; border-radius: 3px; border: 1px solid #cccccc;"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
								placeholder="选择日期">
				</td>
			</tr>

			<tr>
				<td class="addTd">结束时间：<span style="color: red">*</span></td>
				<td align="left">
						<input type="text" class="Wdate" id="endTime"
								value='<fmt:formatDate value="${dbObject.endTime}" pattern="yyyy-MM-dd"/>'
								name="endTime"
								style="height: 20px; width: 145px; border-radius: 3px; border: 1px solid #cccccc;"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
								placeholder="选择日期">
				</td>
			</tr>
			<tr>
			<td colspan="2"><span style="color: red">温馨提示：请假周期（开始时间-结束时间）中具体请假日期与登记时考勤日历设置一致。</span></td>
			</tr>
		</table>
     <div class="formButton">
		<input type="button" class="btn" id="saveAndSubmit" value="提交"
			onclick="submitItemFrame('SUB');" />
		<input type="button" name="back" Class="btn"
			onclick="history.back(-1);" value="返回" />
		</div>
	</form>
</fieldset>
</body>
<script type="text/javascript">
function submitItemFrame(subType) {
	//校验是否已存在同一用户同一时间已存在的请假信息
		var isExist="";
		var loroNo=$("#loroNo").val();
		if(loroNo==""||loroNo==undefined){//新增时才检测是否有同一用户，同一时间请过假
			var usercode=$('#usercode').find("option:selected").val();
			var beginTime=$("#beginTime").val();
			var endTime=$("#endTime").val() ;
			var url="${pageContext.request.contextPath}/oa/oaLeaveOverTime!isExist.do?usercode="+usercode
					+"&beginTime="+beginTime+"&endTime="+endTime;
			$.ajax({
				url:url,
				async:false,
				dataType:"json",
				success:function(data){
					if(data!=undefined&&data.result!=undefined){
						var failure=data.result;
						if(failure!="1"){//同一请假时间已存在
							isExist=failure;
						}
					}
				}
			});
		}
	if(isExist!=""){//同一请假时间已存在
			question = confirm("已存在请假时间为："+isExist+","+"继续请假吗？");
			if (question!=true)
				return;		
	}
	if (docheck() == false) {
		return;
	} else {
		var srForm = document.getElementById("oaLeaveOverTimeForm");
		if (subType == 'SUB') {
			srForm.action = 'oaLeaveOverTime!saveDetail.do';
		}
		srForm.submit();
	}
}
function docheck() {
	var flag = true;
	var index=document.getElementById("usercode").selectedIndex;
	if(index==0){
		alert("申请人不为空");
		$("#usercode").focus();
		flag=false;
		return flag;
	}
	var index1=document.getElementById("reasonType").selectedIndex;
	if(index1==0){
		alert("请假类型不为空");
		$("#reasonType").focus();
		flag=false;
		return flag;
	}
	if ($("#beginTime").val() == '') {
		alert("开始时间不能为空！");
		$('#beginTime').focus();
		flag = false;
		return flag;
	}
/* 	var date=new Date();
	if ($("#beginTime").val() != '') {
	var dt = new Date($("#beginTime").val().replace(/-/,"/"));  
	if (dt <date) {
		alert("所选计划开始时间发生在过去，请重新选择时间段！");
		$("#beginTime").focus();
		flag = false;
		return flag;
	   }
	} */
	if ($("#endTime").val() == '') {
		alert("结束时间不能为空！");
		$('#endTime').focus();
		flag = false;
		return flag;
	}
	if ($("#endTime").val() != '') {
		/* var dt = new Date($("#endTime").val().replace(/-/,"/"));  
		if (dt <date) {
			alert("所选计划结束时间发生在过去,请确认提交！");
			$('#endTime').focus();
			flag = false;
			return flag;
		   } */
		if ($("#beginTime").val() > $("#endTime").val()) {
			alert("开始时间不能大于结束时间！");
			$("#endTime").focus();
			flag = false;
			return flag;
		}/* else{
			var sDate1=$("#beginTime").val();
			var sDate2=$("#endTime").val();
			var between_days=this.DateDiff(sDate1,sDate2);
			if(between_days<=0){
				alert("结束时间和开始时间至少相差一天");
				flag = false;
				return flag;
			}
		} */
		}

	return flag;
}
/* function DateDiff(sDate1, sDate2) {  //sDate1和sDate2是yyyy-MM-dd格式
	 
    var aDate, oDate1, oDate2, iDays;
    aDate = sDate1.split("-");
    oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);  //转换为yyyy-MM-dd格式
    aDate = sDate2.split("-");
    oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
    iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数
 
    return iDays;  //返回相差天数
} */
</script>
</html>
