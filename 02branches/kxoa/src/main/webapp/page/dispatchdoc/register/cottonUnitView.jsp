<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="建设项目信息" />
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
		<style type="text/css">
			.addTd { width:130px; height:16px; line-height:16px; text-align:right; padding:4px 10px 4px 0; }
		</style>
	</head>
	<body>
		<s:form action="incomeProject" method="post" namespace="/dispatchdoc" id="incomeProjectForm">
			<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
				<legend style="padding:4px 8px 3px;">
					<b>企业单位信息</b>
				</legend>
				<table width="200" border="0" cellpadding="0" cellspacing="0" class="viewTable">
					<tr>
						<td class="addTd">
							企业名称<font color="#ff0000">*</font>
						</td>
						<td align="left" colspan="7">
							<c:out value="${object.projectUnitName}" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							组织机构代码<font color="#ff0000">*</font>
						</td>
						<td align="left">
							<c:out value="${object.projectOrgCode}" />
						</td>
						<td class="addTd">
							经济类型<font color="#ff0000">*</font>
						</td>
						<td>
							<c:forEach var="row" items="${cp:DICTIONARY('ECONOMIC_TYPE')}">
								<c:if test="${object.economicType eq row.key or (empty object.economicType and row.key eq '0')}">
									<c:out value="${row.value}" />
								</c:if>
							</c:forEach>
						</td>
						<td class="addTd">
							所属行业<font color="#ff0000">*</font>
						</td>
						<td>
							<c:forEach var="row" items="${cp:DICTIONARY('INDUSTRY_FIELD')}">
								<c:if test="${object.industryField eq row.key or (empty object.industryField and row.key eq '0')}">
									<c:out value="${row.value}" />
								</c:if>
							</c:forEach>
						</td>
						<td class="addTd">
							类属关系<font color="#ff0000">*</font>
						</td>
						<td>
							<c:forEach var="row" items="${cp:DICTIONARY('MEMBERSHIP')}">
								<c:if test="${object.membership eq row.key or (empty object.membership and row.key eq '0')}">
									<c:out value="${row.value}" />
								</c:if>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td class="addTd">
							项目单位类型
						</td>
						<td align="left" ${"qy" eq object.projectUnitType ? "" : "colspan='5'"}>
							${cp:MAPVALUE("projectUnitType", object.projectUnitType)}
						</td>
						<c:if test="${'qy' eq object.projectUnitType}">
							<td class="addTd">
								经营范围
							</td>
							<td align="left">
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
								<c:out value="${object.registeredCapital}" />(万元)
							</td>
						</c:if>
						<td class="addTd">
							国别或地区<font color="#ff0000">*</font>
						</td>
						<td>
							<c:forEach var="row" items="${cp:DICTIONARY('COUNTRY_AREA')}">
								<c:if test="${object.countryArea eq row.key or (empty object.countryArea and row.key eq '0')}">
									<c:out value="${row.value}" />
								</c:if>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td class="addTd">
							注册地址<font color="#ff0000">*</font>
						</td>
						<td align="left" colspan="7">
							<c:out value="${object.registeredAddr}" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							行政区划代码<font color="#ff0000">*</font>
						</td>
						<td colspan="3">
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
							<c:out value="${object.adminAreaZip}" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							联系人<font color="#ff0000">*</font>
						</td>
						<td align="left">
							<c:out value="${object.contactName}" />
						</td>
						<td class="addTd">
							电话<font color="#ff0000">*</font>
						</td>
						<td align="left">
							<c:out value="${object.contactPhone}" />
						</td>
						<td class="addTd">
							电子邮件
						</td>
						<td align="left" colspan="3">
							<c:out value="${object.contactEmail}" />
						</td>
					</tr>
				</table>
			</fieldset>
			
			<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
				<legend style="padding:4px 8px 3px;">
					<b>项目信息</b>
				</legend>
				<table width="200" border="0" cellpadding="0" cellspacing="0" class="viewTable">
					<tr>
						<td class="addTd">
							项目名称<font color="#ff0000">*</font>
						</td>
						<td align="left" colspan="7">
							<c:out value="${object.projectName}" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							建设地址<font color="#ff0000">*</font>
						</td>
						<td align="left" colspan="7">
							<c:out value="${object.projectBuildAddr}" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							主要建设及变更内容<font color="#ff0000">*</font>
						</td>
						<td align="left" colspan="7">
							<c:out value="${object.buildContent}" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							总投资<font color="#ff0000">*</font>
						</td>
						<td align="left" colspan="7">
							<c:out value="${object.officialTotalInvestment}" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							中央投资
						</td>
						<td align="left">
							<c:out value="${object.centreInvestment}" />
						</td>
						<td class="addTd">
							省级投资
						</td>
						<td align="left">
							<c:out value="${object.provinceInvestment}" />
						</td>
						<td class="addTd">
							市级投资
						</td>
						<td align="left">
							<c:out value="${object.cityInvestment}" />
						</td>
						<td class="addTd">
							县级投资
						</td>
						<td align="left">
							<c:out value="${object.countyInvestment}" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							单位自筹
						</td>
						<td align="left" colspan="7">
							<c:out value="${object.unitSelfRaise}" />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							其中
						</td>
						<td class="addTd">
							单位自有
						</td>
						<td align="left">
							<c:out value="${object.unitSelfOwner}" />
						</td>
						<td class="addTd">
							银行贷款
						</td>
						<td align="left">
							<c:out value="${object.bankAdvance}" />
						</td>
						<td class="addTd">
							其他贷款
						</td>
						<td align="left">
							<c:out value="${object.otherAdvance}" />
						</td>
					</tr>
				</table>
			</fieldset>
		</s:form>
	</body>
	<script type="text/javascript">
		var init = null;
		
		$(document).ready(function() {
			init = setInterval("FrameUtils.initialize(window, init)", MyConstant.initTimeForAdjustHeight);
		});
	</script>
</html>

