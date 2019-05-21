<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head><meta name="decorator" content='${LAYOUT}'/>
<title>字典类别编辑</title>



</head>

<body class="sub-flow">
<fieldset class="form">
		<legend>
			<p class="ctitle">
			字典类别编辑
			</p>
		</legend>
<s:form theme="simple" action="dictionary!save.do" namespace="/sys"
	cssClass="dictionaryForm"><br>
	<s:submit method="save" cssClass="btn" value="保存" />
			<input type="button" name="back" type="button" class="btn"  value="返回" onclick="window.history.back()" />
	<table cellpadding="0" cellspacing="0" class="viewTable">

		<tr>
			<td class="addTd">字典代码</td>
			<td align="left"><s:textarea name="catalogcode" style="height:30px;"/></td>
		</tr>
		<tr>
			<td class="addTd">字典名称</td>
			<td align="left"><s:textarea name="catalogname" style="height:30px;"/></td>
		</tr>
		<tr>
			<td class="addTd">字典类别</td>
			<td align="left"><s:radio name="catalogstyle"
				list="#{'U':'用户字典','S':'系统字典','G':'国标字典','L':'同步字典'}"/>
			</td>
		</tr>
		<tr>
			<td class="addTd">字典形式</td>
			<td align="left"><s:radio name="catalogtype"
				list="#{'L':'列表字典','T':'树形字典'}" />
		</tr>
		<tr>
				<td class="addTd">业务分类：</td>
				<td align="left">
                <select name="dictionarytype">
                		<option value=""></option>
                    <c:forEach var="dt" items="${cp:DICTIONARY('DICTIONARYTYPE')}">
                        <option value="${dt.id.datacode}"
                        <c:if test="${object.dictionarytype eq dt.id.datacode}">selected="selected" </c:if>
                        >${dt.datavalue }</option>
                    </c:forEach>
                </select>
                (此字段通过数据字典DICTIONARYTYPE进行配置)</td>
			</tr>
		<tr>
			<td class="addTd">字段描述</td>
			<td align="left"><s:textarea name="fielddesc" style="width:600px;height:50px;" />
			</td>
		</tr>
		<tr>
			<td class="addTd">字典描述</td>
			<td align="left"><s:textarea name="catalogdesc" style="width:600px;height:50px;" /></td>
		</tr>

	</table>
</s:form>
</fieldset>
</body>
</html>
