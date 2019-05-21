<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="incomeProject.view.title" /></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	
	<p>	
	<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding:4px 8px 3px;"><b>项目信息查看</b></legend>
		<table width="200" border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
				<td class="addTd">
					企业名称
				</td>
				<td align="left" colspan="7">
					<s:property value="%{object.projectUnitName}" />
				</td>
			</tr>
			<tr>
				<td class="addTd">
					组织机构代码
				</td>
				<td align="left">
					<s:property value="%{object.projectOrgCode}" />
				</td>
				<td class="addTd">
					经济类型
				</td>
				<td align="left">
					<c:forEach var="row" items="${cp:DICTIONARY('ECONOMIC_TYPE')}">
						<c:if test="${object.economicType eq row.key or (empty object.economicType and row.key eq '0')}">
							<c:out value="${row.value}" />
						</c:if>
					</c:forEach>
				</td>
				<td class="addTd">
					所属行业
				</td>
				<td align="left">
					<c:forEach var="row" items="${cp:DICTIONARY('INDUSTRY_FIELD')}">
						<c:if test="${object.industryField eq row.key or (empty object.industryField and row.key eq '0')}">
							<c:out value="${row.value}" />
						</c:if>
					</c:forEach>
				</td>
				<td class="addTd">
					类属关系
				</td>
				<td align="left">
					<c:forEach var="row" items="${cp:DICTIONARY('MEMBERSHIP')}">
						<c:if test="${object.membership eq row.key or (empty object.membership and row.key eq '0')}">
							<c:out value="${row.value}" />
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="addTd">
					企业经营范围
				</td>
				<td align="left" colspan="3">
					<c:forEach var="row" items="${cp:DICTIONARY('BUSINESS_SCOPE')}">
						<c:if test="${object.businessScope eq row.key or (empty object.businessScope and row.key eq '0')}">
							<c:out value="${row.value}" />
						</c:if>
					</c:forEach>
				</td>
				<td class="addTd">
					注册资本
				</td>
				<td align="left">
					<s:property value="%{object.registeredCapital}" />
				</td>
				<td class="addTd">
					国别或地区
				</td>
				<td align="left">
					<c:forEach var="row" items="${cp:DICTIONARY('COUNTRY_AREA')}">
						<c:if test="${object.countryArea eq row.key or (empty object.countryArea and row.key eq '0')}">
							<c:out value="${row.value}" />
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="addTd">
					注册地址
				</td>
				<td align="left" colspan="7">
					<s:property value="%{object.registeredAddr}" />
				</td>
			</tr>
			<tr>
				<td class="addTd">
					行政区划代码
				</td>
				<td align="left" colspan="3">
					<c:forEach var="row" items="${cp:DICTIONARY('ADMIN_AREA_CODE')}">
						<c:if test="${object.adminAreaCode eq row.key or (empty object.adminAreaCode and row.key eq '0')}">
							<c:out value="${row.value}" />
						</c:if>
					</c:forEach>
				</td>
				<td class="addTd">
					邮政编码
				</td>
				<td align="left" colspan="3">
					<s:property value="%{object.adminAreaZip}" />
				</td>
			</tr>
			<tr>
				<td class="addTd">
					联系人
				</td>
				<td align="left">
					<s:property value="%{object.contactName}" />
				</td>
				<td class="addTd">
					电话
				</td>
				<td align="left" colspan="2">
					<s:property value="%{object.contactPhone}" />
				</td>
				<td class="addTd">
					电子邮件
				</td>
				<td align="left" colspan="2">
					<s:property value="%{object.contactEmail}" />
				</td>
			</tr>
			<tr>
				<td class="addTd">
					项目名称
				</td>
				<td align="left" colspan="7">
					<s:property value="%{object.projectName}" />
				</td>
			</tr>
			<tr>
				<td class="addTd">
					建设地址
				</td>
				<td align="left" colspan="7">
					<s:property value="%{object.projectBuildAddr}" />
				</td>
			</tr>
			<tr>
				<td class="addTd">
					主要建设及变更内容
				</td>
				<td align="left" colspan="7">
					<s:property value="%{object.buildContent}" />
				</td>
			</tr>
			<tr>
				<td class="addTd">
					总投资
				</td>
				<td align="left" colspan="6">
					<s:property value="%{object.totalInvestment}" />
				</td>
				<td align="left">
					万元
				</td>
			</tr>
		</table>
	</fieldset>
</body>
</html>
