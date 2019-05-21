<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="attendanceDetailed.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="attendanceDetailed.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='attendance/attendanceDetailed!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceDetailed.djid" />
					</td>
					<td align="left">
						<s:property value="%{djid}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceDetailed.createdate" />
					</td>
					<td align="left">
						<s:property value="%{createdate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceDetailed.usercode" />
					</td>
					<td align="left">
						<s:property value="%{usercode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceDetailed.username" />
					</td>
					<td align="left">
						<s:property value="%{username}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceDetailed.unitcount" />
					</td>
					<td align="left">
						<s:property value="%{unitcount}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceDetailed.unitname" />
					</td>
					<td align="left">
						<s:property value="%{unitname}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceDetailed.workdate" />
					</td>
					<td align="left">
						<s:property value="%{workdate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceDetailed.saattendancetime" />
					</td>
					<td align="left">
						<s:property value="%{saattendancetime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceDetailed.xaattendancetime" />
					</td>
					<td align="left">
						<s:property value="%{xaattendancetime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceDetailed.latein" />
					</td>
					<td align="left">
						<s:property value="%{latein}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceDetailed.earlyout" />
					</td>
					<td align="left">
						<s:property value="%{earlyout}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceDetailed.overtimehours" />
					</td>
					<td align="left">
						<s:property value="%{overtimehours}" />
					</td>
				</tr>	

</table>



</body>
</html>
