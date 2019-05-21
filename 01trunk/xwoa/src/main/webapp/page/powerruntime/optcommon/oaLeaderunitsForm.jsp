<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaLeaderunits.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaLeaderunits.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaLeaderunits"  method="post" namespace="/powerruntime" id="oaLeaderunitsForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaderunits.leadercode" />
					</td>
					<td align="left">
	
  
							<s:textfield name="leadercode" size="40" />
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaLeaderunits.unitcode" />
					</td>
					<td align="left">
	
  
							<s:textfield name="unitcode" size="40" />
	
					</td>
				</tr>


</table>


</s:form>
