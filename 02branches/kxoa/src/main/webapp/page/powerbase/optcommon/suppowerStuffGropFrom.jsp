<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>新建材料分组</title>
</head>
<body>
<s:form action="generalOperator" id="from1">
	<s:submit method="saveStuffGroup" cssClass="btn" value="保存"></s:submit>
	<input type="button"  value="返回" Class="btn"  onclick="window.history.back()"/>
	<s:hidden name="suppowerstuffgroup.groupId" value="%{suppowerstuffgroup.groupId}"></s:hidden>
<table width="200" border="0" cellpadding="1" cellspacing="1" class="viewTable">
	<tr>
	<td>材料分组名称</td>
	<td><s:textfield name="suppowerstuffgroup.stuffGroup" value="%{suppowerstuffgroup.stuffGroup}"></s:textfield> </td>
	</tr>
	<tr>
	<td>材料分组说明</td>
	<td><s:textarea name="suppowerstuffgroup.groupDesc" value="%{suppowerstuffgroup.groupDesc}"></s:textarea> </td>
	</tr>
</table>
</s:form>

</body>
</html>