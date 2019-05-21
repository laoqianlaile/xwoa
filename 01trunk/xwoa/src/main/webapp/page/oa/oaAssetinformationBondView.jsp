<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaAssetinformationBond.view.title" /></title>
</head>

<body>
<p class="ctitle"><s:text name="oaAssetinformationBond.view.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<a href='oa/oaAssetinformationBond!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}' property="none">
	<s:text name="opt.btn.back" />
</a>
<p>	
	
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationBond.djid" />
					</td>
					<td align="left">
						<s:property value="%{djid}" />
					</td>
				</tr>
  
				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationBond.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>


				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationBond.creater" />
					</td>
					<td align="left">
						<s:property value="%{creater}" />
					</td>
				</tr>	

				<tr>
					<td class="TDTITLE">
						<s:text name="oaAssetinformationBond.createtime" />
					</td>
					<td align="left">
						<s:property value="%{createtime}" />
					</td>
				</tr>	

</table>



</body>
</html>
