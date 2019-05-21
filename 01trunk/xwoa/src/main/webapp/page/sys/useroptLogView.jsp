<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="useroptLog.view.title" /></title>
</head>

<body class="sub-flow">
	<p class="ctitle">
		<s:text name="useroptLog.view.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<a
		href='sys/useroptLog!list.do?ec_p=${param.ec_p}&ec_crd=${param.ec_crd}'
		property="none"> <s:text name="opt.btn.back" />
	</a>
	<p>
	<table cellpadding="0" cellspacing="0" class="viewTable">

		<tr>
			<td class="TDTITLE"><s:text name="useroptLog.id" /></td>
			<td align="left"><s:property value="%{id}" /></td>
		</tr>


		<tr>
			<td class="TDTITLE"><s:text name="useroptLog.createtime" /></td>
			<td align="left"><s:property value="%{createtime}" /></td>
		</tr>

		<tr>
			<td class="TDTITLE"><s:text name="useroptLog.createuser" /></td>
			<td align="left"><s:property value="%{createuser}" /></td>
		</tr>

		<tr>
			<td class="TDTITLE"><s:text name="useroptLog.bizname" /></td>
			<td align="left"><s:property value="%{bizname}" /></td>
		</tr>

		<tr>
			<td class="TDTITLE"><s:text name="useroptLog.biztype" /></td>
			<td align="left"><s:property value="%{biztype}" /></td>
		</tr>

		<tr>
			<td class="TDTITLE"><s:text name="useroptLog.runerrortype" /></td>
			<td align="left"><s:property value="%{runerrortype}" /></td>
		</tr>

		<tr>
			<td class="TDTITLE"><s:text name="useroptLog.createrip" /></td>
			<td align="left"><s:property value="%{createrip}" /></td>
		</tr>

		<tr>
			<td class="TDTITLE"><s:text name="useroptLog.remark" /></td>
			<td align="left"><s:property value="%{remark}" /></td>
		</tr>

	</table>



</body>
</html>
