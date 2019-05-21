<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>系统日志详细信息</title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset  class="form">
	<legend><b>系统日志详细信息</b></legend>
	<table border="0" cellpadding="1" cellspacing="1" class="viewTable">
		<tr>
			<td class="addTd">日志序列号</td>
			<td align="left"><s:property value="%{logid}" /></td>
		</tr>

		<tr>
			<td class="addTd">操作人员</td>
			<td align="left"><c:out value="${cp:MAPVALUE('usercode',usercode)}"/></td>
		</tr>

		<tr>
			<td class="addTd">操作时间</td>
			<td align="left">
				<fmt:formatDate value="${object.opttime }" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
		</tr>

		<tr>
			<td class="addTd">项目模块</td>
			<td align="left"><c:out value="${cp:MAPVALUE('optid',object.optid)}"/>
			</td>
		</tr>

		<tr>
			<td class="addTd">操作内容</td>
			<td align="left"><s:textarea value="%{optcontent}" disabled="true"/></td>
		</tr>

		<tr>
			<td class="addTd">更改前原值</td>
			<td align="left">
				<s:textarea value="%{oldvalue}" disabled="true"/>
			</tr>
	</table>
	<div  class="formButton">
	<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" />
	</div>
	</fieldset>
</body>
</html>
