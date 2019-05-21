<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaLeaveMessagereply.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaLeaveMessagereply.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='bbs/oaLeaveMessagereply!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table width="200" border="0" cellpadding="1" cellspacing="1">		
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessagereply.lyno" />
					</td>
					<td align="left">
						<s:property value="%{lyno}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessagereply.lno" />
					</td>
					<td align="left">
						<s:property value="%{lno}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessagereply.creater" />
					</td>
					<td align="left">
						<s:property value="%{creater}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessagereply.creatertime" />
					</td>
					<td align="left">
						<s:property value="%{creatertime}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessagereply.messagecomment" />
					</td>
					<td align="left">
						<s:property value="%{messagecomment}" />
					</td>
				</tr>	

</table>



</body>
</html>
