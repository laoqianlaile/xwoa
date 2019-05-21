<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>查看工作安排</title>
</head>
<body class="sub-flow">
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="_new">
		<legend>工作安排信息 </legend>
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
				<td class="addTd">时间</td>
				<td align="left" colspan="3"><fmt:formatDate
						value="${object.createtime}" pattern="yyyy年M月d日 H时mm分" /></td>
			</tr>
			<c:if test="${not(empty object.depname)}">
				<tr>
					<td class="addTd">责任部门</td>
					<td align="left" colspan="3">${object.depname}</td>
				</tr>
			</c:if>
			<tr>
				<td class="addTd">参加人员</td>
				<td align="left" colspan="3">${object.attendusers}</td>
			</tr>
			<tr>
				<td class="addTd">参加领导</td>
				<td align="left" colspan="3">${object.attendleaders}</td>
			</tr>
			<tr>
				<td class="addTd">地点</td>
				<td align="left" colspan="3">${object.address}</td>
			</tr>
			<tr>
				<td class="addTd">工作内容</td>
				<td align="left" colspan="3">${object.remark}</td>
			</tr>
		</table>
	</fieldset>
</body>
</html>