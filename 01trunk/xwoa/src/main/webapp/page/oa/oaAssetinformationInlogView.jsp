<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaAssetinformationInlog.view.title" /></title>
</head>

<body>
<%@ include file="/page/common/messages.jsp"%>

<p>	
	
<table border="0" cellpadding="0" cellspacing="0" class="viewTable">	
  
				<tr>
					<td class="addTd">
						<s:text name="oaAssetinformationInlog.djid" />
					</td>
					<td align="left">
						<s:property value="%{djid}" />
					</td>
					<td class="addTd">
						<s:text name="oaAssetinformationInlog.no" />
					</td>
					<td align="left">
						<s:property value="%{no}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaAssetinformationInlog.assetnum" />
					</td>
					<td align="left">
						<s:property value="%{assetnum}" />
					</td>
					<td class="addTd">
						<s:text name="oaAssetinformationInlog.assetunit" />
					</td>
					<td align="left">
						<s:property value="%{assetunit}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaAssetinformationInlog.creater" />
					</td>
					<td align="left">
						<s:property value="%{creater}" />
					</td>
					<td class="addTd">
						<s:text name="oaAssetinformationInlog.createtime" />
					</td>
					<td align="left">
						<s:property value="%{createtime}" />
					</td>
				</tr>	

				<tr>
					<td class="addTd">
						<s:text name="oaAssetinformationInlog.createRemark" />
					</td>
					<td align="left">
						<s:property value="%{createRemark}" />
					</td>
				</tr>	

</table>



</body>
</html>
