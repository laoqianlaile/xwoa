<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaAssetinformationBond.edit.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaAssetinformationBond.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="oaAssetinformationBond"  method="post" namespace="/oa" id="oaAssetinformationBondForm" >
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<s:submit type="button" name="back" cssClass="btn" key="opt.btn.back"/>
		
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
 
				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationBond.djid" />
					</td>
					<td align="left">
	
  
							<s:textfield name="djid" size="40" />
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationBond.no" />
					</td>
					<td align="left">
	
  
							<s:textfield name="no" size="40" />
	
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationBond.creater" />
					</td>
					<td align="left">
	
  
						<s:textfield name="creater"  size="40"/>
	
					</td>
				</tr>

				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationBond.createtime" />
					</td>
					<td align="left">
	
  
						<s:textfield name="createtime"  size="40"/>
	
					</td>
				</tr>

</table>


</s:form>
