<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaUnitsLeaderLog.view.title" /></title>
</head>

<body>
	<p class="ctitle">
		<s:text name="oaUnitsLeaderLog.view.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"
		onclick="window.history.go(-1);" />


	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

		<tr>
			<td class="addTd"><s:text name="oaUnitsLeaderLog.no" /></td>
			<td align="left"><s:property value="%{no}" /></td>
		</tr>


		<tr>
			<td class="addTd"><s:text name="oaUnitsLeaderLog.leadercode" />
			</td>
			<td align="left">${cp:MAPVALUE("usercode",object.leadercode)}</td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaUnitsLeaderLog.isuse" /></td>
			<td align="left">${cp:MAPVALUE("TrueOrFalse",object.isuse)}</td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaUnitsLeaderLog.unitcodes" />
			</td>
			<td align="left"><s:property value="%{unitNames}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaUnitsLeaderLog.createtime" />
			</td>
			<td align="left"><s:date name="createtime" format="yyyy-MM-dd HH:mm" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaUnitsLeaderLog.createuser" />
			</td>
			<td align="left">${cp:MAPVALUE("usercode",object.createuser)}</td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaUnitsLeaderLog.remark" /></td>
			<td align="left"><s:property value="%{remark}" /></td>
		</tr>
	</table>
</body>
</html>
