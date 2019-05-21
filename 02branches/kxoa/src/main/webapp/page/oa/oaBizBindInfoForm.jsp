<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="oaBizBindInfo.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaBizBindInfo.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaBizBindInfo"  method="post" namespace="/oa" id="oaBizBindInfoForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.no" />
					</td>
					<td align="left">
	
  
							<s:textfield name="no" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.startDjid" />
					</td>
					<td align="left">
	
  
						<s:textfield name="startDjid"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.endDjid" />
					</td>
					<td align="left">
	
  
						<s:textfield name="endDjid"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.bizType" />
					</td>
					<td align="left">
	
  
						<s:textfield name="bizType"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.createtime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createtime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.createuser" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createuser"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.nodeinstid" />
					</td>
					<td align="left">
	
  
						<s:textfield name="nodeinstid"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaBizBindInfo.nodename" />
					</td>
					<td align="left">
  
						<s:textarea name="nodename" cols="40" rows="2"/>
	
	
					</td>
				</tr>

</table>


</s:form>
