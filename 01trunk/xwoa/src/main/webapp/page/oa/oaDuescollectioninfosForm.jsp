<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaDuescollectioninfos.edit.title" /></title>
</head>

<body class="sub-flow">
<p class="ctitle"><s:text name="oaDuescollectioninfos.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaDuescollectioninfos"  method="post" namespace="/oa" id="oaDuescollectioninfosForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="oaDuescollectioninfos.djId" />
					</td>
					<td align="left">
	
  
							<s:textfield name="djId" size="40" />
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaDuescollectioninfos.usercode" />
					</td>
					<td align="left">
	
  
							<s:textfield name="usercode" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaDuescollectioninfos.unitcode" />
					</td>
					<td align="left">
	
  
						<s:textfield name="unitcode"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaDuescollectioninfos.createtime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createtime"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaDuescollectioninfos.amount" />
					</td>
					<td align="left">
	
  
						<s:textfield name="amount"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaDuescollectioninfos.isfinish" />
					</td>
					<td align="left">
	
  
						<s:textfield name="isfinish"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
