<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaBbsAttention.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaBbsAttention.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaBbsAttention"  method="post" namespace="/bbs" id="oaBbsAttentionForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="oaBbsAttention.themeno" />
					</td>
					<td align="left">
	
  
							<s:textfield name="themeno" size="40" />
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBbsAttention.usercode" />
					</td>
					<td align="left">
	
  
							<s:textfield name="usercode" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaBbsAttention.createtime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createtime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBbsAttention.laytype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="laytype"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
