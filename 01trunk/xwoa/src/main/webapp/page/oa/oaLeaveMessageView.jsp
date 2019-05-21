<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaLeaveMessage.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaLeaveMessage.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/oaLeaveMessage!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessage.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessage.infoType" />
					</td>
					<td align="left">
						<s:property value="%{infoType}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessage.djid" />
					</td>
					<td align="left">
						<s:property value="%{djid}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessage.creater" />
					</td>
					<td align="left">
						<s:property value="%{creater}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessage.creatertime" />
					</td>
					<td align="left">
						<s:property value="%{creatertime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessage.messagecomment" />
					</td>
					<td align="left">
						<s:property value="%{messagecomment}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessage.state" />
					</td>
					<td align="left">
						<s:property value="%{state}" />
					</td>
				</tr>	

</table>



</body>
</html>
