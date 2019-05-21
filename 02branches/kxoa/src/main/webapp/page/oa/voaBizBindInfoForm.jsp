<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="voaBizBindInfo.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="voaBizBindInfo.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="voaBizBindInfo"  method="post" namespace="/oa" id="voaBizBindInfoForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.djId" />
					</td>
					<td align="left">
	
  
							<s:textfield name="djId" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.transaffairname" />
					</td>
					<td align="left">
  
						<s:textarea name="transaffairname" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.orgcode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="orgcode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.powerid" />
					</td>
					<td align="left">
  
						<s:textarea name="powerid" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.powername" />
					</td>
					<td align="left">
  
						<s:textarea name="powername" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.bizstate" />
					</td>
					<td align="left">
	
  
						<s:textfield name="bizstate"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.biztype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="biztype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.createdate" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createdate"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="voaBizBindInfo.itemtype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="itemtype"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
