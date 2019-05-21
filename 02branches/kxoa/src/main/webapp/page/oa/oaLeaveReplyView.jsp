<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaLeaveReply.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaLeaveReply.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/oaLeaveReply!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveReply.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveReply.ino" />
					</td>
					<td align="left">
						<s:property value="%{ino}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveReply.creater" />
					</td>
					<td align="left">
						<s:property value="%{creater}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveReply.creatertime" />
					</td>
					<td align="left">
						<s:property value="%{creatertime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveReply.messagecomment" />
					</td>
					<td align="left">
						<s:property value="%{messagecomment}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveReply.perno" />
					</td>
					<td align="left">
						<s:property value="%{perno}" />
					</td>
				</tr>	

</table>



</body>
</html>
