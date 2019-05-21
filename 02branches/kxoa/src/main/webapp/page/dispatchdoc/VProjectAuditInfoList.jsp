<%@ page contentType="text/html;charset=UTF-8"  import="java.util.*" %>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			项目审批、核准、备案情况查询
		</title>
		<%-- <sj:head locale="zh_CN" /> --%>
	</head>

	<body>
	<script type="text/javascript">
	function SWPrint() {
		var uri = "<%=request.getContextPath()%>/iWebOffice/DIRPrintEdit.jsp";
		var param = "FileType=.doc&flowStep=SP_DIR&EditType=1,0&ShowType=1" +"&Template=SPTJ";
		//var winProp = 'height='	+window.screen.availHeight+',width='+window.screen.availWidth
		//		+',directories=false,location=false,menubar=false,resizeable=true,scrollbars=yes,toolbar=false';
		//window.open(uri + "?"+ param,'editForm',winProp);
		openNewWindow(uri + "?"+ param,'editForm','');
}
		</script>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				 项目审批、核准、备案情况查询
			</legend>
			
			<s:form action="VProjectAuditInfo" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
				<s:hidden name="s_bizstate" value="%{#parameters['s_bizstate']}"/>
				<table cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td class="addTd">建设单位名称:</td>
						<td width="180"><s:textfield name="s_projectUnitName"  value="%{#parameters['s_projectUnitName']}" /></td>
						<td class="addTd">建设项目名称:</td>
						<td width="180"><s:textfield name="s_projectName"  value="%{#parameters['s_projectName']}" /></td>
						<td class="addTd">建设地址:</td>
						<td width="180"><s:textfield name="s_registeredAddr"  value="%{#parameters['s_registeredAddr']}" /></td>
					</tr>
					<tr>
						<td class="addTd">行业类别:</td>
						<td width="180">
							<input type="text" id="s_industryField" onclick="inputTree('s_industryField','INDUSTRY_FIELD');" />
							<input type="hidden" id="s_industryField_submit" name="s_industryField" />
						</td>
						<td class="addTd">建设单位法人:</td>
						<td width="180"><s:textfield name="s_buildLegal"  value="%{#parameters['s_buildLegal']}" /></td>
						<td class="addTd">项目联系人:</td>
						<td width="180"><s:textfield name="s_contactName"  value="%{#parameters['s_contactName']}" /></td>
					</tr>
					<tr>
						<td class="addTd">文号:</td>
						<td width="180"><s:textfield name="s_sendArchiveNo"  value="%{#parameters['s_sendArchiveNo']}" /></td>
						<td class="addTd">形式:</td>
						<td width="180"><select id="s_projectType" name="s_projectType" style="width: 120px">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:DICTIONARY('PROJECT_TYPE')}">
									<option value="${row.key}" ${(object.projectType eq row.key or (empty object.projectType and row.key eq '0')) ? 'selected = "selected"' : ''}>
										<c:out value="${row.value}" />
									</option>
								</c:forEach>
							</select></td>
						<td class="addTd">审批单位:</td>
						<td width="180"><s:textfield name="s_auditUnit"  value="%{#parameters['s_auditUnit']}" /></td>
					</tr>
					<tr>
						<td class="addTd">权力类型:</td>
						<td colspan="3">
							<select id="s_powerid" name="s_powerid">
								<option value="">---请选择---</option>
								<c:forEach var="bean" items="${powerList}">
									<option value="${bean.value}" ${object.powerid eq bean.value ? "selected = 'selected'" : ""}>
										<c:out value="${bean.label}" />
									</option>
								</c:forEach>
							</select>
						</td>
						<td class="addTd">行政区划代码:</td>
						<td>
							<input type="text" id="s_projectAreaCode" onclick="inputTree('s_projectAreaCode','ADMIN_AREA_CODE');" />
							<input type="hidden" id="s_projectAreaCode_submit" name="s_projectAreaCode" />
						</td>
					</tr>
					<tr>
						<td class="addTd">受理时间:</td>
						<td colspan="5">
							<input type="text" id="s_begAcceptDate"  class="Wdate"name="s_begAcceptDate" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value="${params['s_begAcceptDate']}"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 						<sj:datepicker name="s_begAcceptDate" id="s_begAcceptDate"  --%>
<%-- 						readonly="true" value="%{#parameters['s_begAcceptDate']}"  --%>
<%-- 						yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true" /> --%>
						至:
							<input type="text" id="s_endAcceptDate"  class="Wdate"name="s_endAcceptDate" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value="${params['s_endAcceptDate']}"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">						
<%-- 						<sj:datepicker name="s_endAcceptDate" id="s_endAcceptDate"  --%>
<%-- 						readonly="true" value="%{#parameters['s_endAcceptDate']}"  --%>
<%-- 						yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true" /> --%>
						</td>
					</tr>
					<tr>
						<td class="addTd">办结时间:</td>
						<td colspan="5">
							<input type="text" id="s_begTime"  class="Wdate"name="s_begTime" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value="${params['s_begTime']}"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">						
<%-- 						<sj:datepicker name="s_begTime" id="s_begTime" readonly="true" --%>
<%-- 						 value="%{#parameters['s_begTime']}" yearRange="2000:2020"  --%>
<%-- 						 displayFormat="yy-mm-dd" changeYear="true" /> --%>
						至:
							<input type="text" id="s_endTime"  class="Wdate"name="s_endTime" 
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value="${params['s_endTime']}"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">						
<%-- 						<sj:datepicker name="s_endTime" id="s_endTime" readonly="true" --%>
<%-- 						value="%{#parameters['s_endTime']}" yearRange="2000:2020" --%>
<%-- 						displayFormat="yy-mm-dd" changeYear="true" /> --%>
					</td>
					</tr>
					<tr>
						<td colspan="6">
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
							<input type="button" onclick="SWPrint();" value="目录结构打印" class="btn infoBtn" style="padding-left:0; padding-right:0;" /> 
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="dispatchdoc/VProjectAuditInfo!list.do" items="objList" var="VProjectAuditInfo"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="VProjectAuditInfos.xls" ></ec:exportXls>
			<ec:exportPdf fileName="VProjectAuditInfos.pdf" headerColor="blue" headerBackgroundColor="white" ></ec:exportPdf>
			<ec:row>
				<ec:column property="projectId" title="项目序号" style="text-align:center"/>
				<ec:column property="projectUnitName" title="建设单位名称" style="text-align:center" />
				<ec:column property="projectName" title="建设项目名称" style="text-align:center" />
				<ec:column property="registeredAddr" title="建设地址" style="text-align:center" />
				<ec:column property="eiaApprovalType" title="行业类别" style="text-align:center" />
				<ec:column property="buildLegal" title="建设单位法人" style="text-align:center" />
				<ec:column property="buildContent" title="主要建设内容" style="text-align:center" />
				<ec:column property="totalInvestment" title="计划总投资（万元）" style="text-align:center" />
				<ec:column property="officialTotal" title="资金来源（万元）" style="text-align:center" />
				<ec:column property="projectType" title="形式" style="text-align:center" />
				<ec:column property="auditUnit" title="单位" style="text-align:center" />
				<ec:column property="sendArchiveNo" title="文号" style="text-align:center" />
				<ec:column property="solvetimeStr" title="时间" style="text-align:center" />
				<ec:column property="contactName" title="项目联系人姓名" style="text-align:center" />
				<ec:column property="contactPhone" title="项目联系人电话" style="text-align:center" />
			</ec:row>
		</ec:table>
		
	</body>
	
</html>