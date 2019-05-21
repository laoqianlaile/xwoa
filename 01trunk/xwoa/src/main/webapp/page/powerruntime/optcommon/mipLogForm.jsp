<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="mipLog.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="mipLog.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="mipLog"  method="post" namespace="/powerruntime" id="mipLogForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="mipLog.mipid" />
					</td>
					<td align="left">
	
  
							<s:textfield name="mipid" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="mipLog.infmethods" />
					</td>
					<td align="left">
	
  
						<s:textfield name="infmethods"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="mipLog.accparameters" />
					</td>
					<td align="left">
	
  
						<s:textfield name="accparameters"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="mipLog.returnddata" />
					</td>
					<td align="left">
	
  
						<s:textfield name="returnddata"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="mipLog.exceptioninfo" />
					</td>
					<td align="left">
	
  
						<s:textfield name="exceptioninfo"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="mipLog.creater" />
					</td>
					<td align="left">
	
  
						<s:textfield name="creater"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="mipLog.createtime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createtime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="mipLog.remarkMethods" />
					</td>
					<td align="left">
  
						<s:textarea name="remarkMethods" cols="40" rows="2"/>
	
	
					</td>
				</tr>

</table>


</s:form>
