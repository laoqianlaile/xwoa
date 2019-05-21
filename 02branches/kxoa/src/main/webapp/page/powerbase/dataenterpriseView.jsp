<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="dataenterprise.view.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding:10px;">
		<legend style="margin-bottom:10px;"><s:text name="dataenterprise.view.title"/></legend>
		<input type="button" value="返回" Class="btn" onclick="window.history.back()" />
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
		<tr>
			<td class="addTd">组织机构名称</td>
			<td align="left"><s:property value="%{applicant}" /></td>
			<td class="addTd">是否使用</td>
			<td align="left">${cp:MAPVALUE("ISINUSE",isInuse) }</td>
		</tr>

		<tr>
			<td class="addTd">组织机构证件类型</td>
			<td align="left">${cp:MAPVALUE("PaperType",applicantPaperType) }</td>
			<td class="addTd">组织机构证件号码</td>
			<td align="left"><s:property value="%{applicantPaperNumber}" /></td>
		</tr>

		<tr>
			<td class="addTd">组织机构电话</td>
			<td align="left"><s:property value="%{applicantPhone}" /></td>
			<td class="addTd">组织机构手机</td>
			<td align="left"><s:property value="%{applicantMobile}" /></td>
		</tr>

		<tr>
			<td class="addTd">组织机构地址</td>
			<td align="left" colspan="3"><s:property value="%{applicantAddress}" /></td>
		</tr>

		<tr>
			<td class="addTd">组织机构邮编</td>
			<td align="left"><s:property value="%{applicantZipcode}" /></td>
			<td class="addTd">组织机构EMAIL</td>
			<td align="left"><s:property value="%{applicantEmail}" /></td>
		</tr>

		<tr>
			<td class="addTd">组织机构性质</td>
			<td align="left">${cp:MAPVALUE("unitType",unitType) }</td>
		</tr>

		<tr>
			<td class="addTd">所在行业</td>
			<td align="left">${cp:MAPVALUE("corpDomain",corpDomain) }</td>
			<td class="addTd">所有制形式</td>
			<td align="left">${cp:MAPVALUE("regType",regType) }</td>
		</tr>

		<tr>
			<td class="addTd">传真</td>
			<td align="left"><s:property value="%{fax}" /></td>
			<td class="addTd">法人</td>
			<td align="left"><s:property value="%{linkman}" /></td>
		</tr>

		<tr>
			<td class="addTd">联系人姓名</td>
			<td align="left"><s:property value="%{linkmanName}" /></td>
		</tr>

		<tr>
			<td class="addTd">联系人证件类型</td>
			<td align="left">${cp:MAPVALUE("PaperType_ZRR",linkmanPaperType) }</td>
			<td class="addTd">联系人证件号码</td>
			<td align="left"><s:property value="%{linkmanPaperCode}" /></td>
		</tr>

		<tr>
			<td class="addTd">联系人电话</td>
			<td align="left"><s:property value="%{linkmanPhone}" /></td>
			<td class="addTd">联系人手机</td>
			<td align="left"><s:property value="%{linkmanMobile}" /></td>
		</tr>

		<tr>
			<td class="addTd">联系人地址</td>
			<td align="left" colspan="3"><s:property value="%{linkmanAddress}" /></td>
		</tr>

		<tr>
			<td class="addTd">联系人邮编</td>
			<td align="left"><s:property value="%{linkmanZipcode}" /></td>
			<td class="addTd">联系人EMAIL</td>
			<td align="left"><s:property value="%{linkmanEmail}" /></td>
		</tr>

	</table>
	</fieldset>
</body>
</html>
