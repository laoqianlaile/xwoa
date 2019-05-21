<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="dataenterprise.edit.title" /></title>
<script src="<s:url value='/scripts/centit_validator.js'/>" type="text/javascript" ></script>	
</head>
<body>
	<p class="ctitle">
		<s:text name="dataenterprise.edit.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="dataenterprise" method="post" namespace="/powerbase"
		id="dataenterpriseForm"  theme="simple"  validator="true">
		<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
		<input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回"/>
		<input type="hidden" id="enterpriseid" name="enterpriseid" value="${enterpriseid }" />
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
				<td class="addTd">组织机构名称</td>
				<td align="left"><s:textfield name="applicant" size="40" validator="input" min="5" errorshow="请输入组织机构名称，至少5个字" /><span
					style="color: red">*</span></td>
				<td class="addTd">是否使用</td>
				<td align="left"><select name="isInuse" validator="input" min="1" errorshow="请选择是否使用" >
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('ISINUSE')}">
							<option value="${row.key}"
								<c:if test="${object.isInuse eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach>
				</select><span
					style="color: red">*</span></td>
			</tr>

			<tr>
				<td class="addTd">组织机构证件类型</td>
				<td align="left"><select name="applicantPaperType" min="1" errorshow="请选择组织机构证件类型">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('PaperType')}">
							<option value="${row.key}"
								<c:if test="${object.applicantPaperType eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach>
				</select><span
					style="color: red">*</span></td>
				<td class="addTd">组织机构证件号码</td>
				<td align="left"><s:textfield name="applicantPaperNumber"
						size="40" validator="input" min="4" errorshow="请输入组织机构证件号码，至少4位" /><span
					style="color: red">*</span></td>
			</tr>

			<tr>
				<td class="addTd">组织机构电话</td>
				<td align="left"><s:textfield name="applicantPhone" size="40" />

				</td>
				<td class="addTd">组织机构手机</td>
				<td align="left"><s:textfield name="applicantMobile" size="40" /></td>
			</tr>

			<tr>
				<td class="addTd">组织机构地址</td>
				<td align="left" colspan="3"><s:textfield name="applicantAddress" size="40" /></td>
			</tr>

			<tr>
				<td class="addTd">组织机构邮编</td>
				<td align="left"><s:textfield name="applicantZipcode" size="40" /></td>
				<td class="addTd">组织机构EMAIL</td>
				<td align="left"><s:textfield name="applicantEmail" size="40" /></td>
			</tr>

			<tr>
				<td class="addTd">组织机构性质</td>
				<td align="left"><select name="unitType">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('unitType')}">
							<option value="${row.key}"
								<c:if test="${object.unitType eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td class="addTd">所在行业</td>
				<td align="left"><select name="corpDomain">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('corpDomain')}">
							<option value="${row.key}"
								<c:if test="${object.corpDomain eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach></select></td>
				<td class="addTd">所有制形式</td>
				<td align="left"><select name="regType">
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('regType')}">
							<option value="${row.key}"
								<c:if test="${object.regType eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach></select></td>
			</tr>

			<tr>
				<td class="addTd">传真</td>
				<td align="left"><s:textfield name="fax" size="40" /></td>
				<td class="addTd">法人</td>
				<td align="left"><s:textfield name="linkman" size="40" /></td>
			</tr>

			<tr>
				<td class="addTd">联系人姓名</td>
				<td align="left"><s:textfield name="linkmanName" size="40" validator="input" min="4" errorshow="请输入联系人姓名" /><span
					style="color: red">*</span></td>
			</tr>

			<tr>
				<td class="addTd">联系人证件类型</td>
				<td align="left"><select name="linkmanPaperType" validator="input" min="1" errorshow="请选择联系人证件类型" >
						<option value="">---请选择---</option>
						<c:forEach var="row" items="${cp:DICTIONARY('PaperType_ZRR')}">
							<option value="${row.key}"
								<c:if test="${object.linkmanPaperType eq row.key}"> selected = "selected" </c:if>>
								<c:out value="${row.value}" />
							</option>
						</c:forEach>
				</select><span
					style="color: red">*</span></td>
				<td class="addTd">联系人证件号码</td>
				<td align="left"><s:textfield name="linkmanPaperCode" size="40" validator="input" min="4" errorshow="请输入证件号码" /><span
					style="color: red">*</span></td>
			</tr>

			<tr>
				<td class="addTd">联系人电话</td>
				<td align="left"><s:textfield name="linkmanPhone" size="40" validator="input" min="4" errorshow="请输入联系人电话" /><span
					style="color: red">*</span></td>
				<td class="addTd">联系人手机</td>
				<td align="left"><s:textfield name="linkmanMobile" size="40" /></td>
			</tr>

			<tr>
				<td class="addTd">联系人地址</td>
				<td align="left" colspan="3"><s:textfield name="linkmanAddress" size="40" /></td>
			</tr>

			<tr>
				<td class="addTd">联系人邮编</td>
				<td align="left"><s:textfield name="linkmanZipcode" size="40" /></td>
				<td class="addTd">联系人EMAIL</td>
				<td align="left"><s:textfield name="linkmanEmail" size="40" /></td>
			</tr>

		</table>
	</s:form>
</body>
</html>