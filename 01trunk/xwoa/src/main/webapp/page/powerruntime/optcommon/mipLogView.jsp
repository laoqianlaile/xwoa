<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<title><s:text name="mipLog.view.title" /></title>
</head>

<body class="sub-flow">
	<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"
		onclick="window.history.go(-1);" />
	<p>
	<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding: 4px 8px 3px;">
			<b>MIP日志</b>
		</legend>
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
				<td class="addTd"><s:text name="mipLog.infmethods" /></td>
				<td align="left"><s:property value="%{infmethods}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="mipLog.accparameters" /></td>
				<td align="left"><s:property value="%{accparameters}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="mipLog.returnddata" /></td>
				<td align="left"><s:property value="%{returnddata}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="mipLog.exceptioninfo" /></td>
				<td align="left"><s:property value="%{exceptioninfo}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="mipLog.creater" /></td>
				<td align="left">${cp:MAPVALUE('usercode',creater)}</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="mipLog.createtime" /></td>
				<td align="left"><s:date name="%{createtime}"
						format="yyyy-MM-dd HH:mm" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="mipLog.remarkMethods" /></td>
				<td align="left"><s:property value="%{remarkMethods}" /></td>
			</tr>

		</table>
	</fieldset>


</body>
</html>
