<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>权利材料信息</title>
</head>

<body >
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="form">
	<legend>编辑权利材料信息</legend>
	<s:form action="generalOperator" namespace="/powerruntime"	 >
		<s:hidden name="suppowerstuffinfo.sortId" value="%{suppowerstuffinfo.sortId}"></s:hidden>
		<s:hidden name="suppowerstuffinfo.groupId" value="%{suppowerstuffinfo.groupId}"></s:hidden>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="viewTable">
		<tr>
		<td class="addTd">材料类别</td>
		<td>
		<select id="suppowerstuffinfo.stuffType" name="suppowerstuffinfo.stuffType" style="width:180px">
			<option value="">---请选择---</option>
			<c:forEach var="row" items="${cp:DICTIONARY('FILETYPE')}">
				<option value="${row.key}" label="${row.value}" ${suppowerstuffinfo.stuffType eq row.key ? "selected" : ""}>
				<c:out value="${row.value}" /></option>
			</c:forEach>
		</select>
		</td>
		<td class="addTd">文书类别</td>
		<td>
		<select id="suppowerstuffinfo.archivetype" name="suppowerstuffinfo.archivetype" style="width:180px">
			<option value="">---请选择---</option>
			<c:forEach var="row" items="${cp:DICTIONARY('TEMPLATE_TYPE')}">
				<option value="${row.key}" label="${row.value}" ${suppowerstuffinfo.archivetype eq row.key ? "selected" : ""}>
				<c:out value="${row.value}" /></option>
			</c:forEach>
		</select>
		</td>
		</tr>
		<tr>
		<td class="addTd">材料名称</td>
		<td colspan="3"><s:textfield name="suppowerstuffinfo.stuffName" value="%{suppowerstuffinfo.stuffName}" style="width: 100%;hight"/>   </td>
		</tr>
		<tr>
		<td class="addTd">是否必须</td>
		<td><s:radio name="suppowerstuffinfo.isNeed" value="%{suppowerstuffinfo.isNeed}" listKey="key" listValue="value"  list="#{'1':'是','0':'否'}"></s:radio> </td>
		
		<td class="addTd">是否必须电子</td>
		<td><s:radio name="suppowerstuffinfo.isElectron" value="%{suppowerstuffinfo.isElectron}" listKey="key" listValue="value"  list="#{'1':'是','0':'否'}"></s:radio> </td>
		</tr>
	<tr>
		
		<td class="addTd">材料排序</td>
		<td colspan="3"><s:textfield name="suppowerstuffinfo.stuffOrder" value="%{suppowerstuffinfo.stuffOrder}" ></s:textfield>   </td>
		</tr>
		</table>
		<div class="formButton">
		<s:submit name="save" method="saveStuffinfo" cssClass="btn" value="保存"/>
		<input type="button"  value="返回" Class="btn" onclick="window.history.back()" />
		</div>
	</s:form>
</fieldset>
</body>
</html>
