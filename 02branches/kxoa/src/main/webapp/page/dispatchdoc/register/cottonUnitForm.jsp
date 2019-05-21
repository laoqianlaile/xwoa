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
							<input type="text" id="projectUnitName" name="incomeProject.projectUnitName" maxlength="100" value="${object.projectUnitName}" style="width: 100%;" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							组织机构代码<font color="#ff0000">*</font>
						</td>
						<td align="left">
							<input type="text" id="projectOrgCode" name="incomeProject.projectOrgCode" maxlength="20" value="${object.projectOrgCode}" />
						</td>
						<td class="addTd">
							经济类型<font color="#ff0000">*</font>
						</td>
						<td>
							<input type="text" id="economicType" onclick="inputTree('economicType','ECONOMIC_TYPE');" value="${cp:MAPVALUE('ECONOMIC_TYPE',object.economicType)}" />
							<input type="hidden" id="economicType_submit" name="incomeProject.economicType" value="${object.economicType}" />							
						</td>
						<td class="addTd">
							所属行业<font color="#ff0000">*</font>
						</td>
						<td>
							<input type="text" id="industryField" onclick="inputTree('industryField','INDUSTRY_FIELD');" value="${cp:MAPVALUE('INDUSTRY_FIELD',object.industryField)}" />
							<input type="hidden" id="industryField_submit" name="incomeProject.industryField" value="${object.industryField}" />							
						</td>
						<td class="addTd">
							类属关系<font color="#ff0000">*</font>
						</td>
						<td>
							<select id="membership" name="incomeProject.membership" style="width: 120px">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:DICTIONARY('MEMBERSHIP')}">
									<option value="${row.key}" ${(object.membership eq row.key or (empty object.membership and row.key eq '0')) ? 'selected = "selected"' : ''}>
										<c:out value="${row.value}" />
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="addTd">
							项目单位类型<font color="#ff0000">*</font>
						</td>
						<td align="left" ${"qy" eq object.projectUnitType ? "" : "colspan='5'"}>
							<select id="projectUnitType" name="incomeProject.projectUnitType" style="width: 120px">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:DICTIONARY('projectUnitType')}">
									<option value="${row.key}" ${(object.projectUnitType eq row.key or (empty object.projectUnitType and row.key eq '0')) ? 'selected = "selected"' : ''}>
										<c:out value="${row.value}" />
									</option>
								</c:forEach>
							</select>
						</td>
						<td class="addTd" ${"qy" eq object.projectUnitType ? "" : "style='display: none;'"}>
							企业经营范围<font color="#ff0000">*</font>
						</td>
						<td align="left" ${"qy" eq object.projectUnitType ? "" : "style='display: none;'"}>
							<input type="text" id="businessScope" onclick="inputTree('businessScope','BUSINESS_SCOPE');" value="${cp:MAPVALUE('BUSINESS_SCOPE',object.businessScope)}" />
							<input type="hidden" id="businessScope_submit" name="incomeProject.businessScope" value="${object.businessScope}" />							
						</td>
						<td class="addTd" ${"qy" eq object.projectUnitType ? "" : "style='display: none;'"}>
							注册资本<font color="#ff0000">*</font>
						</td>
						<td align="left" ${"qy" eq object.projectUnitType ? "" : "style='display: none;'"}>
							<input type="text" id="registeredCapital" name="incomeProject.registeredCapital" maxlength="20" value="${object.registeredCapital}" />
						</td>
						<td class="addTd">
							国别或地区<font color="#ff0000">*</font>
						</td>
						<td>
							<select id="countryArea" name="incomeProject.countryArea" style="width: 120px">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:DICTIONARY('COUNTRY_AREA')}">
									<option value="${row.key}" ${(object.countryArea eq row.key or (empty object.countryArea and row.key eq '0')) ? 'selected = "selected"' : ''}>
										<c:out value="${row.value}" />
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td class="addTd">
							注册地址<font color="#ff0000">*</font>
						</td>
						<td align="left" colspan="7">
							<input type="text" id="registeredAddr" name="incomeProject.registeredAddr" maxlength="100" value="${object.registeredAddr}" style="width: 100%;" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							行政区划代码<font color="#ff0000">*</font>
						</td>
						<td colspan="3">
							<input type="text" id="adminAreaCode" onclick="inputTree('adminAreaCode','ADMIN_AREA_CODE');" value="${cp:MAPVALUE('ADMIN_AREA_CODE',object.adminAreaCode)}" />
							<input type="hidden" id="adminAreaCode_submit" name="incomeProject.adminAreaCode" value="${object.adminAreaCode}" />							
						</td>
						<td class="addTd">
							邮政编码
						</td>
						<td align="left" colspan="3">
							<input type="text" id="adminAreaZip" name="incomeProject.adminAreaZip" maxlength="6" value="${object.adminAreaZip}" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							联系人<font color="#ff0000">*</font>
						</td>
						<td align="left">
							<input type="text" id="contactName" name="incomeProject.contactName" maxlength="50" value="${object.contactName}" />
						</td>
						<td class="addTd">
							电话<font color="#ff0000">*</font>
						</td>
						<td align="left">
							<input type="text" id="contactPhone" name="incomeProject.contactPhone" maxlength="20" value="${object.contactPhone}" />
						</td>
						<td class="addTd">
							电子邮件
						</td>
						<td align="left" colspan="3">
							<input type="text" id="contactEmail" name="incomeProject.contactEmail" maxlength="50" value="${object.contactEmail}" />
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
							<input type="text" id="projectName" name="incomeProject.projectName" maxlength="100" value="${object.projectName}" style="width: 100%;" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							建设地址<font color="#ff0000">*</font>
						</td>
						<td align="left" colspan="7">
							<input type="text" id="projectBuildAddr" name="incomeProject.projectBuildAddr" maxlength="100" value="${object.projectBuildAddr}" style="width: 100%" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							主要建设及变更内容<font color="#ff0000">*</font>
						</td>
						<td align="left" colspan="7">
							<textarea id="buildContent" name="incomeProject.buildContent" style="width: 100%; hright: 80px;">${object.buildContent}</textarea>
							<span id="buildContentMessage">&nbsp;</span>
						</td>
					</tr>
					<tr>
						<td class="addTd">
							总投资<font color="#ff0000">*</font>
						</td>
						<td align="left" colspan="7">
							<input type="text" id="officialTotalInvestment" name="incomeProject.officialTotalInvestment" maxlength="20" value="${object.officialTotalInvestment}" />万元
						</td>
					</tr>
					<tr>
						<td class="addTd">
							中央投资
						</td>
						<td align="left">
							<input type="text" id="centreInvestment" name="incomeProject.centreInvestment" maxlength="20" value="${object.centreInvestment}" />
						</td>
						<td class="addTd">
							省级投资
						</td>
						<td align="left">
							<input type="text" id="provinceInvestment" name="incomeProject.provinceInvestment" maxlength="20" value="${object.provinceInvestment}" />
						</td>
						<td class="addTd">
							市级投资
						</td>
						<td align="left">
							<input type="text" id="cityInvestment" name="incomeProject.cityInvestment" maxlength="20" value="${object.cityInvestment}" />
						</td>
						<td class="addTd">
							县级投资
						</td>
						<td align="left">
							<input type="text" id="countyInvestment" name="incomeProject.countyInvestment" maxlength="20" value="${object.countyInvestment}" />
						</td>
					</tr>
					<tr>
						<td class="addTd">
							单位自筹
						</td>
						<td align="left" colspan="7">
							<input type="text" id="unitSelfRaise" name="incomeProject.unitSelfRaise" maxlength="20" value="${object.unitSelfRaise}" />
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
							<input type="text" id="unitSelfOwner" name="incomeProject.unitSelfOwner" maxlength="20" value="${object.unitSelfOwner}" />
						</td>
						<td class="addTd">
							银行贷款
						</td>
						<td align="left">
							<input type="text" id="bankAdvance" name="incomeProject.bankAdvance" maxlength="20" value="${object.bankAdvance}" />
						</td>
						<td class="addTd">
							其他贷款
						</td>
						<td align="left">
							<input type="text" id="otherAdvance" name="incomeProject.otherAdvance" maxlength="20" value="${object.otherAdvance}" />
						</td>
					</tr>
				</table>
			</fieldset>
		</s:form>
	</body>
	
	<script type="text/javascript">
		var init = null;
		
		function showBuildContent() {
			CommonUtils.showTextAreaLimited("buildContent", "buildContentMessage", 150);
		}
		
		$("#projectUnitType").change(function() {
			var $this = $(this);
			var $td = $this.parent();
			if ("qy" == $this.val()) { // show
				$td.attr("colspan", "1");
				$td.next().show();
				$td.next().next().show();
				$td.next().next().next().show();
				$td.next().next().next().next().show();
			} else { // hide
				$td.next().next().next().next().hide();
				$td.next().next().next().hide();
				$td.next().next().hide();
				$td.next().hide();
				$td.attr("colspan", "5");
			}
		});
		
		
		function checkBind() {
			if ("qy" == $("#projectUnitType").val()) {
				return validateEmpty("businessScope", "请选择企业经营范围")
						&& validateEmpty("registeredCapital", "请输入注册资本")
						&& validateNumber("registeredCapital", "注册资本为数字")
						&& validateNumberRange("registeredCapital", 12, 4, "注册资本整数位不超过8位，小数位不超过4位");
			}
			return true;
		}
		
		function doCheck() {
			if ("qy" != $("#projectUnitType").val()) { // clear businessScope and registeredCapital
				$("#businessScope").val("");
				$("#registeredCapital").val("");
			}
			
			return (validateEmpty("projectUnitName", "请输入企业名称")
					&& validateEmpty("projectOrgCode", "请输入组织机构代码")
					&& validateEmpty("economicType", "请选择经济类型")
					&& validateEmpty("industryField", "请选择所属行业")
					&& validateEmpty("membership", "请选择隶属关系")
					&& validateEmpty("projectUnitType", "请选择项目单位性质")
					&& checkBind()
					&& validateEmpty("countryArea", "请国别或地区")
					&& validateEmpty("registeredAddr", "请输入注册地址")
					&& validateEmpty("adminAreaCode", "请选择行政区划代码")
// 					&& validateRegExp("adminAreaZip", "zipCode", "邮政编码格式错误")
					&& validateEmpty("contactName", "请输入联系人")
					&& validateEmpty("contactPhone", "请输入联系人电话")
// 					&& (validateRegExp("contactPhone", "mobilePhone", "联系人电话格式错误")
// 					|| (validateRegExp("contactPhone", "landline", "联系人电话格式错误"))
// 					&& validateRegExp("contactEmail", "email", "联系人电子邮件格式错误")
					&& validateEmpty("projectName", "请输入项目名称")
					&& validateEmpty("projectBuildAddr", "请输入建设地址")
					&& validateEmpty("buildContent", "请输入主要建设及变更内容")
					&& validateLength("buildContent", 500, "项目建设内容及规模超出最大长度")
					&& validateEmpty("officialTotalInvestment", "请输入总投资")
					&& validateNumber("officialTotalInvestment", "总投资为数字")
					&& validateNumberRange("officialTotalInvestment", 12, 4, "总投资整数位不超过8位，小数位不超过4位")
					&& validateNumber("centreInvestment", "中央投资为数字")
					&& validateNumberRange("centreInvestment", 12, 4, "中央投资整数位不超过8位，小数位不超过4位")
					&& validateNumber("provinceInvestment", "省级投资为数字")
					&& validateNumberRange("provinceInvestment", 12, 4, "省级投资整数位不超过8位，小数位不超过4位")
					&& validateNumber("cityInvestment", "市级投资为数字")
					&& validateNumberRange("cityInvestment", 12, 4, "市级投资整数位不超过8位，小数位不超过4位")
					&& validateNumber("countyInvestment", "县级投资为数字")
					&& validateNumberRange("countyInvestment", 12, 4, "县级投资整数位不超过8位，小数位不超过4位")
					&& validateNumber("unitSelfRaise", "单位自筹为数字")
					&& validateNumberRange("unitSelfRaise", 12, 4, "单位自筹整数位不超过8位，小数位不超过4位")
					&& validateNumber("unitSelfOwner", "单位自有为数字")
					&& validateNumberRange("unitSelfOwner", 12, 4, "单位自有整数位不超过8位，小数位不超过4位")
					&& validateNumber("bankAdvance", "银行贷款为数字")
					&& validateNumberRange("bankAdvance", 12, 4, "银行贷款整数位不超过8位，小数位不超过4位")
					&& validateNumber("otherAdvance", "其他贷款为数字")
					&& validateNumberRange("otherAdvance", 12, 4, "其他贷款整数位不超过8位，小数位不超过4位")
					&& validateCompare("#officialTotalInvestment==#centreInvestment+#provinceInvestment+#cityInvestment+#countyInvestment+#unitSelfRaise",
							"总投资必须等于中央投资、省级投资、市级投资、县级投资及单位自筹之和")
					&& validateCompare("#unitSelfRaise==#unitSelfOwner+#bankAdvance+#otherAdvance",
							"单位自筹必须等于单位自有、银行贷款及其他贷款之和"));
		}
		
		$(document).ready(function() {
			init = setInterval("FrameUtils.initialize(window, init)", MyConstant.initTimeForAdjustHeight);
			
			$("#buildContent").keyup(function() {
				showBuildContent();
				$("tr[rel='MHJG'] #applyDemo", window.parent.document).val($(this).val());
				window.parent.window.showApplyDemo();
			});
		});
	</script>
</html>

