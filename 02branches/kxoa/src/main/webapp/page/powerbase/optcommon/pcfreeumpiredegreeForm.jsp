<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<title><c:out value="pcfreeumpiretype.edit.title" /></title>

</head>

<body>
<p class="ctitle"><c:out value="pcfreeumpiretype.edit.title" /></p>

<%@ include file="/page/common/messages.jsp"%>

<s:form action="pcfreeumpiredegree" method="post" namespace="/punish"   styleId="pcfreeumpiredegreeForm" onsubmit="return validatepcfreeumpiretypeForm(this);">
	<s:submit method="save" cssClass="btn" key="opt.btn.save"></s:submit>
	<input type="button" Class="btn" onclick="window.history.back()" value="返回"/>
    <input id="itemId" type="hidden" name="itemId"
			value="${object.itemId}" />
		<input id="version" type="hidden" name="version"
			value="${object.version}" />
<fieldset style="padding:10px;display:block;margin-bottom:10px;">
	<legend style="padding:4px 8px 3px;"><b>违法事实程度设置</b></legend>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
 
				
				<tr>
					<td class="addTd">
					违法程度名称
					</td>
					<td align="left">
							<s:textfield name="punishfactgrade" value="%{object.punishfactgrade}" size="40" />
					</td>
				</tr>

				<tr>
					<td class="addTd">
						自由裁量编码
					</td>
					<td align="left">
							<s:textfield  name="standardNo" value="%{object.standardNo}"  />
					</td>
				</tr>


				<tr>
					<td class="addTd">
						法律依据
					</td>
					<td align="left" colspan="3">
						<s:textarea  name="punishbasis"  value="%{object.punishbasis}" />
					</td>
				</tr>

</table>

</fieldset>
</s:form>

</body>
</html>
