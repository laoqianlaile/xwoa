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
			<input type="hidden" id="loroNo" name="loroNo" value="${object.loroNo }" /><!-- object的主键 -->
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

			<tr>
				<td class="addTd">请假类型：</td>
				<td align="left">
				<select id="reasonType" name="reasonType" style="width:200px;height:22px;">
								<option value="" >---请选择---</option>
								<c:forEach var="v" items="${cp:LVB('LeaveOvertime')}">
								<option <c:if test="${ leaveOrOverTime eq v.value}">selected="selected"</c:if> value="${v.value }"><c:out value="${v.label}" /></option>
								</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
			<td class="addTd">申请人：</td>
				<td align="left">${cp:MAPVALUE('usercode',object.usercode) }
					<input id="usercode" type="hidden" name="usercode" value="${object.usercode}"/> 
				</td>
			</tr>
			<tr>
				<td class="addTd">原因描述：</td>
				<td align="left">
				<textarea id="reasonDesc" name="reasonDesc" class="span6" rows="5" cols="50">${reasonDesc }</textarea>
				</td>
			</tr>
			<tr>
				<td class="addTd">开始时间：</td>
				<td align="left">
						<input type="text" class="Wdate" id="beginTime"
								value='<fmt:formatDate value="${object.beginTime}" pattern="yyyy-MM-dd HH:mm"/>'
								name="beginTime"
								style="height: 20px; width: 145px; border-radius: 3px; border: 1px solid #cccccc;"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
								placeholder="选择日期">
				</td>
			</tr>

			<tr>
				<td class="addTd">结束时间：</td>
				<td align="left">
						<input type="text" class="Wdate" id="endTime"
								value='<fmt:formatDate value="${object.endTime}" pattern="yyyy-MM-dd HH:mm"/>'
								name="endTime"
								style="height: 20px; width: 145px; border-radius: 3px; border: 1px solid #cccccc;"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" 
								placeholder="选择日期">
				</td>
			</tr>
		</table>
     <div class="formButton">
<!-- 		<input type="button" class="btn" id="save" value="保存" -->
<!-- 			onclick="submitItemFrame('SAVE');" /> -->
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
	if (docheck() == false) {
		return;
	} else {
		var srForm = document.getElementById("oaLeaveOverTimeForm");
		if (subType == 'SUB') {
			srForm.action = 'oaLeaveOverTime!save.do';
		}
		srForm.submit();
	}
}
function docheck() {
	var flag = true;

	if($("#reasonType").val==null||$("#reasonType").val==''){
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
	var date=new Date();
	if ($("#beginTime").val() != '') {
	var dt = new Date($("#beginTime").val().replace(/-/,"/"));  
	if (dt <date) {
		alert("所选计划开始时间发生在过去，请重新选择时间段！");
		$("#beginTime").focus();
		flag = false;
		return flag;
	   }
	}
	if ($("#endTime").val() == '') {
		alert("结束时间不能为空！");
		$('#endTime').focus();
		flag = false;
		return flag;
	}
	if ($("#endTime").val() != '') {
		var dt = new Date($("#endTime").val().replace(/-/,"/"));  
		if (dt <date) {
			alert("所选计划结束时间发生在过去,请确认提交！");
			$('#endTime').focus();
			flag = false;
			return flag;
		   }
		}
	if ($("#beginTime").val() > $("#endTime").val()) {
		alert("开始时间不能大于结束时间！");
		$("#endTime").focus();
		flag = false;
		return flag;
	}else{
		var sDate1=$("#beginTime").val();
		var sDate2=$("#endTime").val();
		var between_days=this.DateDiff(sDate1,sDate2);
		if(between_days<=0){
			alert("结束时间和开始时间至少相差一天");
			flag = false;
			return flag;
		}
	}
	return flag;
}
function DateDiff(sDate1, sDate2) {  //sDate1和sDate2是yyyy-MM-dd格式
	 
    var aDate, oDate1, oDate2, iDays;
    aDate = sDate1.split("-");
    oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);  //转换为yyyy-MM-dd格式
    aDate = sDate2.split("-");
    oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);
    iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数
 
    return iDays;  //返回相差天数
}
</script>
</html>
