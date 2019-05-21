<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="权力事项登记" />
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
		<style type="text/css">
			.addTd { width:130px; height:16px; line-height:16px; text-align:right; padding:4px 10px 4px 0; }
			.hide {display: none;}
		</style>
	</head>
	<body>
		<s:form id="incomeDocForm" name="incomeDocForm" namespace="/dispatchdoc" action="incomeDoc" method="post" target="_parent">
			<input type="hidden" id="isReady" name="isReady" />
			<input type="hidden" id="itemSource" name="itemSource" value="${empty object.itemSource ? 'N' : object.itemSource}" />
			<input type="hidden" id="applyItemType" name="applyItemType" value="${object.applyItemType}" />
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="optId" name="optBaseInfo.optId" value="${object.optBaseInfo.optId}" />
			<input type="hidden" id="flowCode" name="optBaseInfo.flowCode" value="${object.optBaseInfo.flowCode}" />
			<input type="hidden" id="nodeInstId" name="nodeInstId" value="${nodeInstId}">
			<input type="hidden" id="flowInstId" name="optBaseInfo.flowInstId" value="${flowInstId}" />
			<input type="hidden" id="transAffairNo" name="optBaseInfo.transAffairNo" value="${object.optBaseInfo.transAffairNo}" />
			
			<div id="projectInfoArea" style="position: absolute; top: -9999px; left: -9999px"></div>
			
			<div style="display: none;">
				<input type="button" class="btn" id="saveBtn" value="保存" onclick="doSave();" />
				<input type="button" class="btn btnWide" id="submitBtn" value="保存并提交" onclick="doSubmit();" />
				<input id="backBtn" name="backBtn" type="button" value="返回" class="btn"  onclick="window.history.go(-1);"/>
			</div>
			
			<fieldset class="_new">
				<legend style="padding:4px 8px 3px;">
					<b>登记信息</b>
				</legend>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">
					<tr>
						<td class="addTd">
							权力事项<font color="#ff0000">*</font>
						</td>
						<td align="left" colspan="5">
							<input type="hidden" id="powerid" name="optBaseInfo.powerid" value="${object.optBaseInfo.powerid}" />
							<input type="hidden" id="powername" name="optBaseInfo.powername" value="${object.optBaseInfo.powername}" />
							<c:out value="${object.optBaseInfo.powername}" />
						</td>
					</tr>
					<!-- 一般项目（YBXM）：建设项目审批、核准、备案登记界面 -->
					<c:if test="${'YBXM' eq object.applyItemType}">
						<tr rel="YBXM">
							<td class="addTd">
								项目名称<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="3">
								<input type="text" id="projectName" name="projectName" style="width: 400px;" maxlength="100" value="${object.projectName}" />
							</td>
							<td class="addTd">
								登记号
							</td>
							<td align="left">
								${object.optBaseInfo.transAffairNo}
							</td>
						</tr>
						<tr rel="YBXM">
							<td class="addTd">
								项目申请人(来文单位)<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="5">
								<input type="text" id="incomeDeptName" name="incomeDeptName" style="width: 400px;" maxlength="200" value="${object.incomeDeptName}" />
							</td>
						</tr>
						<tr rel="YBXM">
							<td class="addTd">
								来文文号<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="3">
								<input type="text" id="incomeDocNo" name="incomeDocNo" maxlength="50" value="${object.incomeDocNo}" />
							</td>
							<td class="addTd">
								制发日期
							</td>
							<td align="left">
							<input type="text" class="Wdate"  id="docCreateDate_YBXM" name="docCreateDate" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value='<fmt:formatDate value="${object.docCreateDate}" pattern="yyyy-MM-dd"/>'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 								<sj:datepicker id="docCreateDate_YBXM" name="docCreateDate"  --%>
<%-- 								value="%{object.docCreateDate}" style="width: 120px;" --%>
<%-- 								yearRange="2000:2020" displayFormat="yy-mm-dd" --%>
<%-- 								 changeYear="true" readonly="true" cssStyle="width: 100px;" /> --%>
							</td>
						</tr>
						<tr rel="YBXM">
							<td class="addTd">
								来文单位分类<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="3">
								<select id="incomeDeptType" name="incomeDeptType">
									<option value="">---请选择---</option>
									<c:forEach var="row" items="${cp:DICTIONARY('INCOME_DEPT_TYPE')}">
										<option value="${row.key}" ${(object.incomeDeptType eq row.key or (empty object.incomeDeptType and row.key eq '0')) ? 'selected = "selected"' : ''}>
											<c:out value="${row.value}" />
										</option>
									</c:forEach>
								</select>
								<span id="provincialGovDocNoArea" ${"7" eq object.incomeDeptType ? "" : "style='display: none;'"}>
									&nbsp;&nbsp;&nbsp;&nbsp;省政府文号:<input type="text" id="provincialGovDocNo" name="provincialGovDocNo" value="${object.provincialGovDocNo}" maxlength="50" />
								</span>
							</td>
							<td class="addTd">
								申报日期<font color="#ff0000">*</font>
							</td>
							<td align="left">
							<input type="text" class="Wdate"  id="incomeDate_YBXM" name="incomeDate" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value='<fmt:formatDate value="${object.incomeDate}" pattern="yyyy-MM-dd"/>'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 								<sj:datepicker id="incomeDate_YBXM" name="incomeDate" --%>
<%-- 								value="%{object.incomeDate}" style="width: 120px;" --%>
<%-- 								yearRange="2000:2020" displayFormat="yy-mm-dd"  --%>
<%-- 								changeYear="true" readonly="true" cssStyle="width: 100px;" /> --%>
							</td>
						</tr>
						<tr rel="YBXM">
							<td class="addTd">
								申报人
							</td>
							<td align="left" colspan="3">
								<input type="text" id="applyUser" name="applyUser" maxlength="10" value="${object.applyUser}" />
							</td>
							<td class="addTd">
								申报人手机号码
							</td>
							<td align="left">
								<input type="text" id="applyUserPhone" name="applyUserPhone" maxlength="11" value="${object.applyUserPhone}" />
							</td>
						</tr>
					</c:if>
					<!-- 一般权力（YBQL）：行政权力登记界面 -->
					<c:if test="${'YBQL' eq object.applyItemType}">
						<tr rel="YBQL">
							<td class="addTd">
								来文单位<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="3">
								<input type="text" id="incomeDeptName" name="incomeDeptName" style="width: 400px;" maxlength="200" value="${object.incomeDeptName}" />
							</td>
							<td class="addTd">
								来文单位分类<font color="#ff0000">*</font>
							</td>
							<td align="left">
								<select id="incomeDeptType" name="incomeDeptType">
									<option value="">---请选择---</option>
									<c:forEach var="row" items="${cp:DICTIONARY('INCOME_DEPT_TYPE')}">
										<option value="${row.key}" ${(object.incomeDeptType eq row.key or (empty object.incomeDeptType and row.key eq '0')) ? 'selected = "selected"' : ''}>
											<c:out value="${row.value}" />
										</option>
									</c:forEach>
								</select>
								<span id="provincialGovDocNoArea" ${"7" eq object.incomeDeptType ? "" : "style='display: none;'"}>
									&nbsp;&nbsp;&nbsp;&nbsp;省政府文号:<input type="text" id="provincialGovDocNo" name="provincialGovDocNo" value="${object.provincialGovDocNo}" maxlength="50" />
								</span>
							</td>
						</tr>
						<tr rel="YBQL">
							<td class="addTd">
								文件标题<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="3">
								<input type="text" id="incomeDocTitle" name="incomeDocTitle" style="width: 400px;" maxlength="100" value="${object.incomeDocTitle}" />
							</td>
							<td class="addTd">
								阅办文编号
							</td>
							<td align="left">
								${object.optBaseInfo.transAffairNo}
							</td>
						</tr>
						<tr rel="YBQL">
							<td class="addTd">
								来文文号<font color="#ff0000">*</font>
							</td>
							<td align="left">
								<input type="text" id="incomeDocNo" name="incomeDocNo" maxlength="50" value="${object.incomeDocNo}" />
							</td>
							<td class="addTd">
								制发日期
							</td>
							<td align="left">
							<input type="text" class="Wdate"  id="docCreateDate_YBQL" name="docCreateDate" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value='<fmt:formatDate value="${object.docCreateDate}" pattern="yyyy-MM-dd"/>'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 								<sj:datepicker id="docCreateDate_YBQL" name="docCreateDate"  --%>
<%-- 								value="%{object.docCreateDate}" style="width: 120px;" --%>
<%-- 								yearRange="2000:2020" displayFormat="yy-mm-dd" --%>
<%-- 								 changeYear="true" readonly="true" cssStyle="width: 100px;" /> --%>
							</td>
							<td class="addTd">
								申报日期<font color="#ff0000">*</font>
							</td>
							<td align="left">
							<input type="text" class="Wdate"  id="incomeDate_YBQL" name="incomeDate" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value='<fmt:formatDate value="${object.incomeDate}" pattern="yyyy-MM-dd"/>'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 								<sj:datepicker id="incomeDate_YBQL" name="incomeDate"  --%>
<%-- 								value="%{object.incomeDate}" style="width: 120px;" --%>
<%-- 								yearRange="2000:2020" displayFormat="yy-mm-dd"  --%>
<%-- 								changeYear="true" readonly="true" cssStyle="width: 100px;" /> --%>
							</td>
						</tr>
						<tr rel="YBQL">
							<td class="addTd">
								申报人
							</td>
							<td align="left" colspan="3">
								<input type="text" id="applyUser" name="applyUser" maxlength="10" value="${object.applyUser}" />
							</td>
							<td class="addTd">
								申报人手机号码
							</td>
							<td align="left">
								<input type="text" id="applyUserPhone" name="applyUserPhone" maxlength="11" value="${object.applyUserPhone}" />
							</td>
						</tr>
					</c:if>
					<!-- 棉花加工（MHJG）：棉花加工资格认定及变更登记界面 -->
					<c:if test="${'MHJG' eq object.applyItemType}">
						<tr rel="MHJG">
							<td class="addTd">
								文件标题<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="3">
								<input type="text" id="incomeDocTitle" name="incomeDocTitle" style="width: 400px;" maxlength="100" value="${object.incomeDocTitle}" />
							</td>
							<td class="addTd">
								 登记号
							</td>
							<td align="left">
								${object.optBaseInfo.transAffairNo}
							</td>
						</tr>
						<tr rel="MHJG">
							<td class="addTd">
								项目申请人(来文单位)<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="3">
								<input type="text" id="incomeDeptName" name="incomeDeptName" style="width: 400px;" maxlength="200" value="${object.incomeDeptName}" />
							</td>
							<td class="addTd">
								来文单位分类<font color="#ff0000">*</font>
							</td>
							<td align="left">
								<select id="incomeDeptType" name="incomeDeptType">
									<option value="">---请选择---</option>
									<c:forEach var="row" items="${cp:DICTIONARY('INCOME_DEPT_TYPE')}">
										<option value="${row.key}" ${(object.incomeDeptType eq row.key or (empty object.incomeDeptType and row.key eq '0')) ? 'selected = "selected"' : ''}>
											<c:out value="${row.value}" />
										</option>
									</c:forEach>
								</select>
								<span id="provincialGovDocNoArea" ${"7" eq object.incomeDeptType ? "" : "style='display: none;'"}>
									&nbsp;&nbsp;&nbsp;&nbsp;省政府文号:<input type="text" id="provincialGovDocNo" name="provincialGovDocNo" value="${object.provincialGovDocNo}" maxlength="50" />
								</span>
							</td>
						</tr>
						<tr rel="MHJG">
							<td class="addTd">
								来文文号<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="3">
								<input type="text" id="incomeDocNo" name="incomeDocNo" maxlength="50" value="${object.incomeDocNo}" />
							</td>
							<td class="addTd">
								制发日期
							</td>
							<td align="left">
							<input type="text" class="Wdate"  id="docCreateDate_MHJG" name="docCreateDate" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value='<fmt:formatDate value="${object.docCreateDate}" pattern="yyyy-MM-dd"/>'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 								<sj:datepicker id="docCreateDate_MHJG" name="docCreateDate"  --%>
<%-- 								value="%{object.docCreateDate}" style="width: 120px;" --%>
<%-- 								yearRange="2000:2020" displayFormat="yy-mm-dd"  --%>
<%-- 								changeYear="true" readonly="true" cssStyle="width: 100px;" /> --%>
							</td>
						</tr>
						<tr rel="MHJG">
							<td class="addTd">
								申报日期<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="5">
							<input type="text" class="Wdate"  id="incomeDate_MHJG" name="incomeDate" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value='<fmt:formatDate value="${object.incomeDate}" pattern="yyyy-MM-dd"/>'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 								<sj:datepicker id="incomeDate_MHJG" name="incomeDate"  --%>
<%-- 								value="%{object.incomeDate}" style="width: 120px;" --%>
<%-- 	 							yearRange="2000:2020" displayFormat="yy-mm-dd" --%>
<%-- 	 							 changeYear="true" readonly="true" cssStyle="width: 100px;" /> --%>
							</td>
						</tr>
						<tr rel="MHJG">
							<td class="addTd">
								主要建设及变更内容
							</td>
							<td align="left" colspan="5">
								<textarea id="applyDemo" name="applyDemo" style="width: 100%; hright: 80px;">${object.applyDemo}</textarea>
								<span id="applyDemoMessage">&nbsp;</span>
							</td>
						</tr>
						<tr rel="MHJG">
							<td class="addTd">
								申报人
							</td>
							<td align="left" colspan="3">
								<input type="text" id="applyUser" name="applyUser" maxlength="10" value="${object.applyUser}" />
							</td>
							<td class="addTd">
								申报人手机号码
							</td>
							<td align="left">
								<input type="text" id="applyUserPhone" name="applyUserPhone" maxlength="11" value="${object.applyUserPhone}" />
							</td>
						</tr>
					</c:if>
					<!-- 设备免税（SBMS）：建设项目进口设备免税确认单登记界面 -->
					<c:if test="${'SBMS' eq object.applyItemType}">
						<tr rel="SBMS">
							<td class="addTd">
								项目名称<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="5">
								<input type="text" id="projectName" name="projectName" style="width: 400px;" maxlength="100" value="${object.projectName}" />
							</td>
						</tr>
						<tr rel="SBMS">
							<td class="addTd">
								项目申请人(来文单位)<font color="#ff0000">*</font>
							</td>
							<td align="left" colspan="3">
								<input type="text" id="incomeDeptName" name="incomeDeptName" style="width: 400px;" maxlength="200" value="${object.incomeDeptName}" />
							</td>
							<td class="addTd">
								登记号
							</td>
							<td align="left">
								${object.optBaseInfo.transAffairNo}
							</td>
						</tr>
						<tr rel="SBMS">
							<td class="addTd">
								来文单位分类<font color="#ff0000">*</font>
							</td>
							<td align="left">
								<select id="incomeDeptType" name="incomeDeptType">
									<option value="">---请选择---</option>
									<c:forEach var="row" items="${cp:DICTIONARY('INCOME_DEPT_TYPE')}">
										<option value="${row.key}" ${(object.incomeDeptType eq row.key or (empty object.incomeDeptType and row.key eq '0')) ? 'selected = "selected"' : ''}>
											<c:out value="${row.value}" />
										</option>
									</c:forEach>
								</select>
								<span id="provincialGovDocNoArea" ${"7" eq object.incomeDeptType ? "" : "style='display: none;'"}>
									&nbsp;&nbsp;&nbsp;&nbsp;省政府文号:<input type="text" id="provincialGovDocNo" name="provincialGovDocNo" value="${object.provincialGovDocNo}" maxlength="50" />
								</span>
							</td>
							<td class="addTd">
								来文文号<font color="#ff0000">*</font>
							</td>
							<td align="left">
								<input type="text" id="incomeDocNo" name="incomeDocNo" maxlength="50" value="${object.incomeDocNo}" />
							</td>
							<td class="addTd">
								制发日期
							</td>
							<td align="left">
							<input type="text" class="Wdate"  id="docCreateDate_SBMS" name="docCreateDate" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value='<fmt:formatDate value="${object.docCreateDate}" pattern="yyyy-MM-dd"/>'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 								<sj:datepicker id="docCreateDate_SBMS" name="docCreateDate" --%>
<%-- 								 value="%{object.docCreateDate}" style="width: 120px;" --%>
<%-- 									 yearRange="2000:2020" displayFormat="yy-mm-dd"  --%>
<%-- 									 changeYear="true" readonly="true" cssStyle="width: 100px;" /> --%>
							</td>
						</tr>
						<tr rel="SBMS">
							<td class="addTd">
								申报日期<font color="#ff0000">*</font>
							</td>
							<td align="left">
							<input type="text" class="Wdate"  id="incomeDate_SBMS" name="incomeDate" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value='<fmt:formatDate value="${object.incomeDate}" pattern="yyyy-MM-dd"/>'
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 								<sj:datepicker id="incomeDate_SBMS" name="incomeDate" --%>
<%-- 								 value="%{object.incomeDate}" style="width: 120px;" --%>
<%-- 								yearRange="2000:2020" displayFormat="yy-mm-dd"  --%>
<%-- 								changeYear="true" readonly="true" cssStyle="width: 100px;" /> --%>
							</td>
							<td class="addTd">
								申报人
							</td>
							<td align="left">
								<input type="text" id="applyUser" name="applyUser" maxlength="10" value="${object.applyUser}" />
							</td>
							<td class="addTd">
								申报人手机号码
							</td>
							<td align="left">
								<input type="text" id="applyUserPhone" name="applyUserPhone" maxlength="11" value="${object.applyUserPhone}" />
							</td>
						</tr>
						<tr rel="SBMS">
							<td class="addTd">
								通讯地址
							</td>
							<td align="left" colspan="3">
								<input type="text" id="applyUserAddr" name="applyUserAddr" style="width: 400px;" maxlength="100" value="${object.applyUserAddr}" />
							</td>
							<td class="addTd">
								邮政编码
							</td>
							<td align="left">
								<input type="text" id="applyUserZip" name="applyUserZip" maxlength="6" value="${object.applyUserZip}" />
							</td>
						</tr>
					</c:if>
				</table>
			</fieldset>
		</s:form>
	
		<fieldset>
			<legend>材料上传</legend>			
			<!-- 附件上传-->
			<iframe  id="basicsj" name="sjfj" src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqcl.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&stuffInfo.groupid=${groupid}" width="100%"
							frameborder="no" scrolling="false" border="0" marginwidth="0"
							marginheight="0" onload="this.height=window.frames['sjfj'].document.body.scrollHeight"></iframe>	
			<!-- 附件上传-->	
		</fieldset>
		
		<div id="frameArea">
			<c:if test="${'YBXM' eq object.applyItemType}">
				<iframe id='YBXMFrame' name='YBXMFrame' width='100%' height='100%'
					src="<%=request.getContextPath()%>/dispatchdoc/incomeProject!edit.do?djId=${object.djId}&retVal=projectInfoEdit"
					frameborder='no' scrolling='false' border='0' marginwidth='0' marginheight='0'></iframe>
			</c:if>
			<c:if test="${'MHJG' eq object.applyItemType}">
				<iframe id='MHJGFrame' name='MHJGFrame' width='100%' height='100%'
					src="<%=request.getContextPath()%>/dispatchdoc/incomeProject!edit.do?djId=${object.djId}&retVal=cottonUnitEdit"
					frameborder='no' scrolling='false' border='0' marginwidth='0' marginheight='0'></iframe>
			</c:if>
		</div>
	</body>
	<script type="text/javascript">
		var applyItemTypes = new Array("YBXM","YBQL","MHJG","SBMS"); // 一般项目，一般权力，棉花加工，设备免税
		var applyItemType = "${object.applyItemType}";
		var init = null;
		var canSubmit = false;
		
		function cloneProjectInfo() {
			applyItemType = $("#applyItemType").val();
			
			if (!FormUtils.cloneFormElements("projectInfoArea", "${object.applyItemType}" + "Frame", "incomeProjectForm")) {
				return false;
			}
			return true;
		}
		
		function doCheck() { // arguments:id,message,[check]applyItemType...
			if (arguments.length < 2 || (arguments.length > 2 && "" == applyItemType)) {
				alert("检查失败");
				return false;
			}
			if (arguments.length == 2) {
				return validateEmpty(arguments[0], arguments[1]);
			} else if (arguments.length > 2 && "" != applyItemType) {
				for (var i=2; i<arguments.length; i++) {
					if (applyItemType == arguments[i]) {
						if ("" == $.trim($("tr[rel='" + applyItemType + "'] #" + arguments[0]).val())) {
							alert(arguments[1]);
							return false;
						}
						break;
					}
				}
			}
			return true;
		}
		
		function checkSave() {
			if (!canSubmit) {
				alert("来文文号和来文单位已存在");
				return false;
			}
			return (doCheck("powerid", "请选择权力事项")
					&& doCheck("applyItemType", "未取得有效的申请事项类别，请刷新页面")
					&& doCheck("flowCode", "未取得有效的流程信息，请刷新页面"));
		}
		
		function checkIncomeDeptType() {
			if ("7" == $("#incomeDeptType").val() && !$.trim($("#provincialGovDocNo").val())) {
				alert("请输入省政府文号");
				return false;
			}
			return true;
		}
		
		function checkSubmit() {
			applyItemType = $("#applyItemType").val();

			var result = (checkSave() && doCheck("projectName", "请输入项目名称", "YBXM", "SBMS")
					&& doCheck("incomeDocTitle", "请输入文件标题", "YBQL", "MHJK")
					&& doCheck("incomeDeptName", "请输入来文单位", "YBXM", "YBQL", "MHJK", "SBMS")
					&& doCheck("incomeDocNo", "请输入来文文号", "YBXM", "YBQL", "MHJK", "SBMS")
// 					&& doCheck("docCreateDate_" + applyItemType, "请输入制发日期", "YBXM", "YBQL", "MHJK", "SBMS")
					&& doCheck("incomeDeptType", "请选择来文单位分类", "YBXM", "YBQL", "MHJK", "SBMS")
					&& checkIncomeDeptType()
					&& doCheck("incomeDate_" + applyItemType, "请输入申报日期", "YBXM", "MHJK", "SBMS"));
// 			result = result && validateRegExp("applyUserPhone", "mobilePhone", "申报人手机号码格式错误");
			if (result) {
				if ("YBXM" == applyItemType || "MHJG" == applyItemType) {
					result = window.frames[applyItemType + "Frame"].window.doCheck();
				}
			}
			return result;
		}
		
		function showApplyDemo() {
			CommonUtils.showTextAreaLimited("applyDemo", "applyDemoMessage", 500);
		}
		
		$("tr[rel='YBXM'] #projectName").keyup(function() {
			$("#YBXMFrame").contents().find("#projectName").val($(this).val());
		});
		
		$("#applyDemo").keyup(function() {
			showApplyDemo();
			$("#MHJGFrame").contents().find("#buildContent").val($(this).val());
			window.frames["MHJGFrame"].window.showBuildContent();
		});
		
		$("#incomeDocNo,#incomeDeptName").change(function() {
			canSubmit = false;
			var incomeDocNo = $.trim($("#incomeDocNo").val());
			var incomeDeptName = $.trim($("#incomeDeptName").val());
			if (incomeDocNo && incomeDeptName) {
				$.ajax({
					type : "POST",
					url :  "${contextPath}/dispatchdoc/incomeDoc!checkProjectExist.do?djId=" + $("#djId").val()
							+ "&incomeDocNo=" + incomeDocNo + "&incomeDeptName=" + incomeDeptName,
					dataType : "json",
					success : function(data) {
						if ("none" == data.status) {
							$("incomeDocNo").val(incomeDocNo);
							$("incomeDeptName").val(incomeDeptName);
							canSubmit = true;
						} else if ("exist" == data.status) {
							alert("该文件已申报！");
						} else {
							alert("检查文件是否已申报失败");
						}
					},
					error : function() {
						alert("检查文件是否已申报失败");
					}
				});
			}
			
			return false;
		});
		
		function doSave() {
			if (checkSave()) {
				if (!cloneProjectInfo()) {
					alert("页面出错！请刷新页面！");
					return false;
				}
				$("#incomeDocForm").attr("action", "incomeDoc!reSaveIncomeDocAndProjectInfo.do");
				$("#incomeDocForm").submit();
			}
			
		}
		
		function doSubmit() {
			if (checkSubmit()) {
				if (!cloneProjectInfo()) {
					alert("页面出错！请刷新页面！");
					return false;
				}
				$("#incomeDocForm").attr("action", "incomeDoc!reSubmitIncomeDocAndProjectInfo.do");
				$("#incomeDocForm").submit();
			}
		}
		
// 		$("#saveBtn,#submitBtn").click(function() { // 保存，提交
// 			var id = $(this).attr("id");
// 			if (("saveBtn" == id && checkSave())
// 				|| ("submitBtn" == id && checkSubmit())) {
// 				if (!cloneProjectInfo()) {
// 					alert("页面出错！请刷新页面！");
// 					return false;
// 				}
// 				$("#incomeDocForm").attr("action", "incomeDoc!" + ("saveBtn" == id ? "saveIncomeDocAndProjectInfo" : "reSubmitIncomeDocAndProjectInfo") + ".do");
// 					$("#incomeDocForm").submit();
// 			}
// 			return false;
// 		});
		
		$(document).ready(function() {
			$("#isReady").val("ok");
			
			init = setInterval("FrameUtils.initialize(window, init)", MyConstant.initTimeForAdjustHeight);
			
			if ($.trim($("#incomeDocNo").val()) && $.trim($("#incomeDeptName").val())) {
				canSubmit = true;
			}
		});
	</script>
</html>