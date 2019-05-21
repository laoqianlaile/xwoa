<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaLeaveReply.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaLeaveReply.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaLeaveReply"  method="post" namespace="/oa" id="oaLeaveReplyForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveReply.no" />
					</td>
					<td align="left">
	
  
							<s:textfield name="no" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveReply.ino" />
					</td>
					<td align="left">
	
  
						<s:textfield name="ino"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveReply.creater" />
					</td>
					<td align="left">
	
  
						<s:textfield name="creater"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveReply.creatertime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="creatertime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveReply.messagecomment" />
					</td>
					<td align="left">
	
  
						<s:textfield name="messagecomment"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveReply.perno" />
					</td>
					<td align="left">
	
  
						<s:textfield name="perno"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
