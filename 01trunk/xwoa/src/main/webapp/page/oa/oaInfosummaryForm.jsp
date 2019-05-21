<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaInfosummary.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaInfosummary.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaInfosummary"  method="post" namespace="/oa" id="oaInfosummaryForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.id" />
					</td>
					<td align="left">
	
  
							<s:textfield name="id" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.no" />
					</td>
					<td align="left">
	
  
						<s:textfield name="no"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.creater" />
					</td>
					<td align="left">
	
  
						<s:textfield name="creater"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.unitcode" />
					</td>
					<td align="left">
  
						<s:textarea name="unitcode" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.telephone" />
					</td>
					<td align="left">
	
  
						<s:textfield name="telephone"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.sex" />
					</td>
					<td align="left">
	
  
						<s:textfield name="sex"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.remark" />
					</td>
					<td align="left">
	
  
						<s:textfield name="remark"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaInfosummary.creatertime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="creatertime"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
