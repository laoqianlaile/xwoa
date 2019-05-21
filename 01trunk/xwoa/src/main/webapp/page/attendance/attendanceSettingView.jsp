<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="attendanceSetting.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="attendanceSetting.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='attendance/attendanceSetting!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.djid" />
					</td>
					<td align="left">
						<s:property value="%{djid}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.attcode" />
					</td>
					<td align="left">
						<s:property value="%{attcode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.createdate" />
					</td>
					<td align="left">
						<s:property value="%{createdate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.lastcodedate" />
					</td>
					<td align="left">
						<s:property value="%{lastcodedate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.schedulingtype" />
					</td>
					<td align="left">
						<s:property value="%{schedulingtype}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.statetime" />
					</td>
					<td align="left">
						<s:property value="%{statetime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.onetype" />
					</td>
					<td align="left">
						<s:property value="%{onetype}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.twotype" />
					</td>
					<td align="left">
						<s:property value="%{twotype}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.threetype" />
					</td>
					<td align="left">
						<s:property value="%{threetype}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.fourtype" />
					</td>
					<td align="left">
						<s:property value="%{fourtype}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.fivetype" />
					</td>
					<td align="left">
						<s:property value="%{fivetype}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.sixtype" />
					</td>
					<td align="left">
						<s:property value="%{sixtype}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.onetime" />
					</td>
					<td align="left">
						<s:property value="%{onetime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.twotime" />
					</td>
					<td align="left">
						<s:property value="%{twotime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.threetime" />
					</td>
					<td align="left">
						<s:property value="%{threetime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.fourtime" />
					</td>
					<td align="left">
						<s:property value="%{fourtime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.fivetime" />
					</td>
					<td align="left">
						<s:property value="%{fivetime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.sextime" />
					</td>
					<td align="left">
						<s:property value="%{sextime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.notworkweek" />
					</td>
					<td align="left">
						<s:property value="%{notworkweek}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.notworkdate" />
					</td>
					<td align="left">
						<s:property value="%{notworkdate}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.personnelcode" />
					</td>
					<td align="left">
						<s:property value="%{personnelcode}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.intermissiontime" />
					</td>
					<td align="left">
						<s:property value="%{intermissiontime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.state" />
					</td>
					<td align="left">
						<s:property value="%{state}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.saattendancetime" />
					</td>
					<td align="left">
						<s:property value="%{saattendancetime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="attendanceSetting.xaattendancetime" />
					</td>
					<td align="left">
						<s:property value="%{xaattendancetime}" />
					</td>
				</tr>	

</table>



</body>
</html>
