<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaPowergroupDetail.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaPowergroupDetail.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaPowergroupDetail"  method="post" namespace="/oa" id="oaPowergroupDetailForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="oaPowergroupDetail.id" />
					</td>
					<td align="left">
	
  
							<s:textfield name="id" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaPowergroupDetail.no" />
					</td>
					<td align="left">
	
  
						<s:textfield name="no"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaPowergroupDetail.usercode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="usercode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaPowergroupDetail.creatertime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="creatertime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaPowergroupDetail.depid" />
					</td>
					<td align="left">
	
  
						<s:textfield name="depid"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
