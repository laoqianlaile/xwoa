<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>权利材料信息</title>
</head>

<body >
	<p class="ctitle">
			编辑权利材料信息
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="generalOperator" namespace="/powerruntime"	 >
		<s:submit name="save" method="saveStuffinfo" cssClass="btn" value="保存"></s:submit>
		<s:hidden name="suppowerstuffinfo.sortId" value="%{suppowerstuffinfo.sortId}"></s:hidden>
		<s:hidden name="suppowerstuffinfo.groupId" value="%{suppowerstuffinfo.groupId}"></s:hidden>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="viewTable">
		<tr>
		<td>材料类别</td>
		<td><s:textfield name="suppowerstuffinfo.stuffType" value="%{suppowerstuffinfo.stuffType}" ></s:textfield>   </td>
		<tr>
		<tr>
		<td>材料名称</td>
		<td><s:textfield name="suppowerstuffinfo.stuffName" value="%{suppowerstuffinfo.stuffName}" ></s:textfield>   </td>
		<tr>
		<tr>
		<td>是否必须</td>
		<td><s:radio name="suppowerstuffinfo.isNeed" value="%{suppowerstuffinfo.isNeed}" listKey="key" listValue="value"  list="#{'1':'是','0':'否'}"></s:radio> </td>
		<tr>
		<tr>
		<td>是否必须电子</td>
		<td><s:radio name="suppowerstuffinfo.isElectron" value="%{suppowerstuffinfo.isElectron}" listKey="key" listValue="value"  list="#{'1':'是','0':'否'}"></s:radio> </td>
		<tr>

		</table>
	</s:form>


</body>
</html>
