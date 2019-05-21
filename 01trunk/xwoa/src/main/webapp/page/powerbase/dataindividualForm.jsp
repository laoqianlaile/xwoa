<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="dataindividual.edit.title" /></title>
<script src="<s:url value='/scripts/centit_validator.js'/>"
	type="text/javascript"></script>
</head>

<body>
	<p class="ctitle">
		<s:text name="dataindividual.edit.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="dataindividual" method="post" namespace="/powerbase"
		id="dataindividualForm" theme="simple" validator="true">
		<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
		<input type="button" name="back" Class="btn"
			onclick="history.back(-1);" value="返回" />
		<input type="hidden" id="individualid" name="individualid"
			value="${individualid }" />
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
				<td class="addTd">人员姓名</td>
				<td align="left"><s:textfield name="applicant" size="40"
						validator="input" min="4" errorshow="请输入人员姓名" /><span
					style="color: red">*</span></td>
				<td class="addTd">是否使用</td>
				<td align="left"><select name="isInuse" validator="input"
					min="1" errorshow="请选择是否使用">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('ISINUSE')}">
							<option value="${row.key}"
								<c:if test="${object.isInuse eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach>
				</select><span style="color: red">*</span></td>

			</tr>

			<tr>
				<td class="addTd">证件类型</td>
				<td align="left"><select name="applicantPaperType" min="1"
					errorshow="请选择证件类型">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('PaperType_ZRR')}">
							<option value="${row.key}"
								<c:if test="${object.applicantPaperType eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach>
				</select><span style="color: red">*</span></td>
				<td class="addTd">证件号码</td>
				<td align="left"><s:textfield name="applicantPaperNumber"
						size="40" validator="input" min="4" errorshow="请输入证件号码" /><span
					style="color: red">*</span></td>
			</tr>

			<tr>
				<td class="addTd">人员电话</td>
				<td align="left"><s:textfield name="applicantPhone" size="40" /></td>
				<td class="addTd">人员手机</td>
				<td align="left"><s:textfield name="applicantMobile" size="40" /></td>
			</tr>

			<tr>
				<td class="addTd">人员地址</td>
				<td align="left"><s:textfield name="applicantAddress" size="40" /></td>
			</tr>

			<tr>
				<td class="addTd">邮政编码</td>
				<td align="left"><s:textfield name="applicantZipcode" size="40"/></td>
				<td class="addTd">人员EMAIL</td>
				<td align="left"><s:textfield name="applicantEmail" size="40" /></td>
			</tr>
			<tr>
				<td class="addTd">工作单位</td>
				<td align="left" colspan="3"><s:textfield name="workUnit"
						size="40" /></td>
			</tr>
			<tr>
				<td class="addTd">性别</td>
				<td align="left" colspan="3"><select name="sex">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('sex')}">
							<option value="${row.key}"
								<c:if test="${object.sex eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
	</s:form>
</body>
</html>