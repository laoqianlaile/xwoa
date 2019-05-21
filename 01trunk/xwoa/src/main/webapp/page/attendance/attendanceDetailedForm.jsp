<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="attendanceDetailed.edit.title" /></title>
</head>
<body class="sub-flow">

<fieldset class="form">
	<legend>
		编辑考勤信息
	</legend> 

<%@ include file="/page/common/messages.jsp"%>

<s:form action="attendanceDetailed"  method="post" namespace="/attendance" id="attendanceDetailedForm" onsubmit="return doCheck();">
	<s:submit name="save"  method="newsave" cssClass="btn" key="opt.btn.save" />
	<input type=button value="返回  " onclick="history.back()" class="btn">
		
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
 
				<tr>
					<td class="addTd">
						<s:text name="attendanceDetailed.username" /><span style="color:red;">*</span>
					</td>
					<td align="left">
						<input type="text" style="height: 25px;width: 300px" name="username" value="${object.username}">
					</td>
					<td class="addTd">
						<s:text name="attendanceDetailed.unitname" /><span style="color:red;">*</span>
					</td>
					<td align="left">
						<input type="text" style="height: 25px;width: 300px" name="unitname" value="${object.unitname}">
					</td>
				</tr>
				<tr>
					<td class="addTd">
						<s:text name="attendanceDetailed.saattendancetime" /><span style="color:red;">*</span>
					</td>
					<td align="left">
						<input type="text" class="Wdate" style="height: 25px;width: 200px"  id="saattendancetime" name="saattendancetime"  onchange="upovtime()"
						onclick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm:ss'})" value="${object.saattendancetime}" placeholder="选择时间" >
					</td>
					<td class="addTd">
						<s:text name="attendanceDetailed.xaattendancetime" /><span style="color:red;">*</span>
					</td>
					<td align="left">
						<input type="text" class="Wdate" style="height: 25px;width: 200px"  id="xaattendancetime" name="xaattendancetime"  onchange="upovtime()"
						onclick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm:ss'})" value="${object.xaattendancetime}" placeholder="选择时间" >
					</td>
				</tr>
				<tr>
					<td class="addTd">
						<s:text name="attendanceDetailed.overtimehours" />
					</td>
					<td align="left">
						<input placeholder="自动计算" readonly="readonly" type="text" style="height: 25px;width: 300px" id="overtimehours" name="overtimehours" value="${object.overtimehours}">
					</td>
					<td class="addTd">
						<s:text name="出勤月份" />
					</td>
					<td align="left">
						<input type="text" class="Wdate" style="height: 25px;width: 200px"  id="workdate" name="workdate" 
						onclick="WdatePicker({dateFmt:'yyyy/MM/dd'})" value="${object.workdate}" placeholder="选择时间" >
					</td>
				</tr>
				<tr>
					<td class="addTd">
						备注信息
					</td>
					<td align="left" colspan="3">
						<textarea  style="height: 50px;width: 100%" name="remarks" >${object.remarks}</textarea>
					</td>
			   </tr>
</table>


</s:form>
</fieldset>
<script type="text/javascript">
	function upovtime (){
		//获取上班时间，获取下班时间
		var stime = $("#saattendancetime").val();
		var xtime = $("#xaattendancetime").val();
		var jime1=stime.substring(0,10);//截取上班时间年月日
		var jime2=xtime.substring(0,10);//截取下班时间年月日
		var date1 = new Date(stime);
		var date2 = new Date(xtime);
		var date3=date2.getTime()-date1.getTime(); //时间差秒
		var leave1=date3%(24*3600*1000);  //计算天数后剩余的毫秒数
		var hours=Math.floor(leave1/(3600*1000));
		var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
		var minutes=Math.floor(leave2/(60*1000));
		var leave3=leave2%(60*1000);    //计算分钟数后剩余的毫秒数
		var seconds=Math.round(leave3/1000);
		if(hours<10){
			hours="0"+hours;
		}
		if(minutes<10){
			minutes="0"+minutes;
		}
		if(seconds<10){
			seconds="0"+seconds;
		}
		var ovtime=hours+":"+minutes+":"+seconds;//得到工作时长
		if(stime!='' && xtime!='' && jime1 == jime2){//当上班时间跟下班时间都不为空的时候，加上选择的日期都是同一天的
			$("#overtimehours").val(ovtime);//给工作时长赋值
			$("#workdate").val(jime2);//给考勤日期赋值
		}
		if(stime!='' && xtime!='' && jime1 != jime2){//当上班日期跟下班日期不同的时候
			alert("请选择同一天日期！");
			$("#xaattendancetime").val('');//将下班时间设置为空
		}
	}
	var currentDate = new Date();
	function doCheck(){
		 if ($.trim($('#username').val()) == '') {
			 alert("姓名不能为空！");
				$('#username').focus();
				return false;
		 }
		 if ($.trim($('#unitname').val()) == '') {
			 alert("部门不能为空！");
				$('#unitname').focus();
				return false;
		 }
		 if ($('#saattendancetime').val() == '') {
			 alert("请选择上班时间！");
				$('#saattendancetime').focus();
				return false;
		 }
		 if ($('#xaattendancetime').val() == '') {
			 alert("请选择下班时间！");
				$('#xaattendancetime').focus();
				return false;
		 }
		 return true;
	
		
	}
</script>