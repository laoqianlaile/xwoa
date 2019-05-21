<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title><s:text name="oaSurveydetail.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaSurveydetail.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaSurveydetail"  method="post" namespace="/oa" id="oaSurveydetailForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table width="200" border="0" cellpadding="1" cellspacing="1">		
 
				<tr>
					<td class="TDTITLE">
						<s:text name="oaSurveydetail.itemid" />
					</td>
					<td align="left">
	
  
							<s:textfield name="itemid" size="40" />
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSurveydetail.creater" />
					</td>
					<td align="left">
	
  
							<s:textfield name="creater" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaSurveydetail.no" />
					</td>
					<td align="left">
	
  
						<s:textfield name="no"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSurveydetail.title" />
					</td>
					<td align="left">
  
						<s:textarea name="title" cols="40" rows="2"/>
	
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSurveydetail.djid" />
					</td>
					<td align="left">
	
  
						<s:textfield name="djid"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaSurveydetail.createtime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createtime"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
