<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<html>
<head>
<title><s:text name="optProcCollection.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="optProcCollection.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="optProcCollection"  method="post" namespace="/powerruntime" id="optProcCollectionForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="optProcCollection.djId" />
					</td>
					<td align="left">
	
  
							<s:textfield name="djId" size="40" />
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optProcCollection.usercode" />
					</td>
					<td align="left">
	
  
							<s:textfield name="usercode" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="optProcCollection.atttype" />
					</td>
					<td align="left">
	
  
						<s:textfield name="atttype"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optProcCollection.attsettime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="attsettime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optProcCollection.isatt" />
					</td>
					<td align="left">
	
  
						<s:textfield name="isatt"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="optProcCollection.removesettime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="removesettime"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
