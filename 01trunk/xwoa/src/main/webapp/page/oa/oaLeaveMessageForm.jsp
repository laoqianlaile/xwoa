<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaLeaveMessage.edit.title" /></title>
</head>

<body>
<%-- <p class="ctitle"><s:text name="oaLeaveMessage.edit.title" /></p> --%>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaLeaveMessage"  method="post" namespace="/oa" id="oaLeaveMessageForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
<%-- 	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/> --%>
	<input type="hidden" id="no" name="no" value="${object.no}" />
	<input type="hidden" id="s_djid" name="s_djid" value="${s_djid}" />
	<input type="hidden" id="s_infotype" name="s_infotype" value="${s_infotype}" />
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaveMessage.messagecomment" />
					</td>
					<td align="left" colspan="3">
  
						<s:textarea name="messagecomment" cols="40" rows="2" style="width: 100%;"/>
	
	
					</td>
				</tr>
</table>


</s:form>
