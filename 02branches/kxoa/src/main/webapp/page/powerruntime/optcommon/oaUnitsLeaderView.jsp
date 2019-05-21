<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaUnitsLeader.view.title" /></title>
</head>

<body>
	<p class="ctitle">
		<s:text name="oaUnitsLeader.view.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<input id="backBtn" name="backBtn" type="button" value="返回" class="btn" onclick="window.history.go(-1);" />
	<input type="button" class='btn' name="powerBtn"
					onClick="openNewWindow('<%=request.getContextPath()%>/powerruntime/oaUnitsLeaderLog!list.do?s_leadercode=${object.leadercode}','orgWindow',null);"
					value="配置记录">
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

		<tr>
			<td class="addTd"><s:text name="oaUnitsLeader.leadercode" />
			</td>
			<td align="left">${cp:MAPVALUE("usercode",object.leadercode)}</td>
		</tr>


		<tr>
			<td class="addTd"><s:text name="oaUnitsLeader.isuse" /></td>
			<td align="left">${cp:MAPVALUE("TrueOrFalse",object.isuse)}</td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaUnitsLeader.unitcodes" /></td>
			<td align="left"><s:property value="%{unitNames}" /></td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaUnitsLeader.createtime" />
			</td>
			<td align="left"><s:date name="createtime" format="yyyy-MM-dd HH:mm" /></td>
		</tr>
		<tr>
			<td class="addTd"><s:text name="oaUnitsLeader.createuser" />
			</td>
			<td align="left">${cp:MAPVALUE("usercode",object.createuser)}</td>
		</tr>
		<tr>
			<td class="addTd"><s:text name="oaUnitsLeader.lastmodifytime" />
			</td>
			<td align="left"><s:date name="lastmodifytime" format="yyyy-MM-dd HH:mm" /></td>
		</tr>
		<tr>
			<td class="addTd"><s:text name="oaUnitsLeader.updateuser" />
			</td>
			<td align="left">${cp:MAPVALUE("usercode",object.updateuser)}</td>
		</tr>

		<tr>
			<td class="addTd"><s:text name="oaUnitsLeader.remark" /></td>
			<td align="left"><s:property value="%{remark}" /></td>
		</tr>
	</table>
</body>
<script type="text/javascript">
function openNewWindow(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'height=600px,width=900px,directories=false,location=false,top=100,left=300,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
</script>
</html>
