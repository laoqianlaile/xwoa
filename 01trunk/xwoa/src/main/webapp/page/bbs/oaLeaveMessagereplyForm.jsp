<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaLeaveMessagereply.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaLeaveMessagereply.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaLeaveMessagereply"  method="post" namespace="/bbs" id="oaLeaveMessagereplyForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessagereply.lyno" />
					</td>
					<td align="left">
	
  
							<s:textfield name="lyno" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessagereply.lno" />
					</td>
					<td align="left">
	
  
						<s:textfield name="lno"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessagereply.creater" />
					</td>
					<td align="left">
	
  
						<s:textfield name="creater"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessagereply.creatertime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="creatertime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessagereply.messagecomment" />
					</td>
					<td align="left">
	
  
						<s:textfield name="messagecomment"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
