<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="attendanceSetting.edit.title" /></title>
</head>

<body class="sub-flow">
<fieldset class="form">
	<legend>
		设置上下班时间
	</legend> 

<%@ include file="/page/common/messages.jsp"%>

<s:form action="attendanceSetting"  method="post" namespace="/attendance" id="attendanceSettingForm" >
	<%-- <s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" /> --%>
	<input type="button" class="btn" value="保存" onclick="save()">
	<input type="button" class="btn" value="返回" onclick="back()">
		
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
 
<%-- 				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.djid" />
					</td>
					<td align="left">
							<s:textfield type="hidden" style="width:200px;height:25px" name="djid" size="40" />
					</td>
				</tr> --%>

<%-- 
				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.attcode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="attcode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.createdate" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createdate"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.lastcodedate" />
					</td>
					<td align="left">
	
  
						<s:textfield name="lastcodedate"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.schedulingtype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="schedulingtype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.statetime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="statetime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.onetype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="onetype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.twotype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="twotype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.threetype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="threetype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.fourtype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="fourtype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.fivetype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="fivetype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.sixtype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="sixtype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.onetime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="onetime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.twotime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="twotime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.threetime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="threetime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.fourtime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="fourtime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.fivetime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="fivetime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.sextime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="sextime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.notworkweek" />
					</td>
					<td align="left">
	
  
						<s:textfield name="notworkweek"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.notworkdate" />
					</td>
					<td align="left">
  
						<s:textarea name="notworkdate" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.personnelcode" />
					</td>
					<td align="left">
  
						<s:textarea name="personnelcode" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.intermissiontime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="intermissiontime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.state" />
					</td>
					<td align="left">
	
  
						<s:textfield name="state"  size="40"/>
	
					</td>
				</tr> --%>

				<tr>
					<td class="addTd">
						<s:text name="attendanceSetting.saattendancetime" />
					</td>
 					<td align="left">
						<input id="djid" name="djid" type="hidden" value="${object.djid}">
						<input type="text" class="Wdate" style="height: 25px;width: 200px"  id="stime" name="saattendancetime" 
						onclick="WdatePicker({dateFmt:'HH:mm:ss'})" value="${object.saattendancetime}" placeholder="选择时间" >
					</td>
					<td class="addTd">
						<s:text name="attendanceSetting.xaattendancetime" />
					</td>
					<td align="left">
						<input type="text" class="Wdate" style="height: 25px;width: 200px"  id="xtime" name="xaattendancetime" 
						onclick="WdatePicker({dateFmt:'HH:mm:ss'})" placeholder="请选择时间" value="${object.xaattendancetime}">
					</td>
				</tr>
</table>
</s:form>
</fieldset>
<script type="text/javascript">
	function save(){
		   var stime=$("#stime").val();
		   var xtime=$("#stime").val();
		   if(stime==null || stime==''){
		    	alert("上班时间不能为空！");
		  	    return;
		   }else if(xtime==null || xtime==''){
		    	alert("下班时间不能为空！");
		  	    return;
		   }else{
			    var srEXForm  = document.getElementById("attendanceSettingForm");//获取form表单id
		        srEXForm.action= 'attendanceSetting!save.do';
		        srEXForm.submit();
		   }
	}
	function back(){
		window.location.href='${pageContext.request.contextPath}/attendance/attendanceDetailed!list.do?';
	}
	var currentDate = new Date();
</script>
