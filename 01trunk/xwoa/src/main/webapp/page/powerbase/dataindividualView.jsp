<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="dataindividual.view.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding:10px;">
		<legend style="margin-bottom:10px;"><s:text name="dataindividual.view.title"/></legend>
		<input type="button" value="返回" Class="btn" onclick="window.history.back()" />
	<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
		<tr>
			<td class="addTd">人员姓名</td>
			<td align="left"><s:property value="%{applicant}" /></td>
			<td class="addTd">是否使用</td>
			<td align="left">${cp:MAPVALUE("ISINUSE",isInuse) }</td>

		</tr>

		<tr>
			<td class="addTd">证件类型</td>
			<td align="left">${cp:MAPVALUE("PaperType_ZRR",applicantPaperType)
				}</td>
			<td class="addTd">证件号码</td>
			<td align="left"><s:property value="%{applicantPaperNumber}" /></td>
		</tr>

		<tr>
			<td class="addTd">人员电话</td>
			<td align="left"><s:property value="%{applicantPhone}" /></td>
			<td class="addTd">人员手机</td>
			<td align="left"><s:property value="%{applicantMobile}" /></td>
		</tr>

		<tr>
			<td class="addTd">人员地址</td>
			<td align="left"><s:property value="%{applicantAddress}" /></td>
		</tr>

		<tr>
			<td class="addTd">人员邮编</td>
			<td align="left"><s:property value="%{applicantZipcode}" /></td>
			<td class="addTd">人员EMAIL</td>
			<td align="left"><s:property value="%{applicantEmail}" /></td>
		</tr>
		<tr>
			<td class="addTd">工作单位</td>
			<td align="left" colspan="3"><s:property value="%{workUnit}" /></td>
		</tr>
		<tr>
			<td class="addTd">性别</td>
			<td align="left" colspan="3">${cp:MAPVALUE("sex",sex) }</td>
		</tr>
	</table>
</fieldset>
</body>
</html>
