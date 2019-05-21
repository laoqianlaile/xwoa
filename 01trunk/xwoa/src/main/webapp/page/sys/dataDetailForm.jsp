<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head><meta name="decorator" content='${LAYOUT}'/>
		<title>字典明细编辑</title>
	</head>
<body class="sub-flow">
<fieldset class="form">
		<legend>
			<p class="ctitle">
			字典明细编辑
			</p>
		</legend>
		<s:form theme="simple" action="dictionary" namespace="/sys"	>
		<br>
				<s:submit name="method_saveDetail"  method="saveDetail"  cssClass="btn" value="保存" />
				<input type="button" value="返回" class="btn" onclick="window.history.go(-1)" />
			<s:hidden name="roid" />
			<table cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">
						字典代码
					</td>
					<td align="left">
						<s:textfield name="datadictionary.id.catalogcode" value="%{datadictionary.catalogcode}"  style="height:30px;" readonly="true" />
					</td>
				</tr>
				<tr>
					<td class="addTd">
						<c:out value="${fdesc[0]}" />
					</td>
					<td align="left">
						<s:textfield name="datadictionary.id.datacode" value="%{datadictionary.datacode}"  style="height:30px;"/>
					</td>
				</tr>
				<tr>
					<td class="addTd">
						<c:out value="${fdesc[1]}" />
					</td>
					<td align="left">
						<s:textfield name="datadictionary.extracode"  value="%{datadictionary.extracode}"  style="height:30px;"/>
					</td>
				</tr>				<tr>
					<td class="addTd">
						<c:out value="${fdesc[2]}" />
					</td>
					<td align="left">
						<s:textfield name="datadictionary.extracode2"  value="%{datadictionary.extracode2}"  style="height:30px;"/>
					</td>
				</tr>				
				<tr>
					<td class="addTd">
						<c:out value="${fdesc[3]}" />
					</td>
					<td align="left">
					<%-- 	<s:textfield name="datadictionary.datatag"  value="%{datadictionary.datatag}"  style="height:30px;"/> --%>
					<s:radio name="datadictionary.datatag" list="#{'T':'启用','F':'停用'}"/>
					</td>
				</tr>
				<tr>
					<td class="addTd">
						<c:out value="${fdesc[4]}" />
					</td>
					<td align="left">
						<s:textfield name="datadictionary.datavalue"  value="%{datadictionary.datavalue}"  style="height:30px;width:50%;"/>
					</td>
				</tr>				
				
				<tr>
					<td class="addTd">
						<c:out value="${fdesc[5]}" />
					</td>
					<td align="left">
						<s:textfield name="datadictionary.datastyle"  value="%{datadictionary.datastyle}"  style="height:30px;"/>
					</td>
				</tr>
				<tr>
					<td class="addTd">
						<c:out value="${fdesc[6]}" />
					</td>
					<td align="left">
						<s:textfield name="datadictionary.datadesc"  value="%{datadictionary.datadesc}"  rows="2" cols="40" style="height:30px;width:50%;"/>
					</td>
				</tr>

			</table>

		</s:form>
		</fieldset>
	</body>
</html>
