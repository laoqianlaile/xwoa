<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			登记信息
		</title>
		<style type="text/css">
			.addTd { width:130px; height:16px; line-height:16px; text-align:right; padding:4px 10px 4px 0;}
			.hide {display: none;}
		</style>
	</head>
	<body>
		<s:form action="incomeDoc" method="post" namespace="/dispatchdoc" id="incomeDocForm">
			<input type="hidden" id="applyItemType" name="applyItemType" value="${object.applyItemType}" />
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<fieldset class="_new">
				<legend style="padding:4px 8px 3px;">
					<b>登记信息</b>
				</legend>
				<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
					<tr>
						<td class="addTd">
							权力事项
						</td>
						<td align="left" colspan="5">
							<c:out value="${object.optBaseInfo.powername}" />
						</td>
					</tr>
					<!-- 一般项目（YBXM）：建设项目审批、核准、备案登记界面 -->
					<tr ${"YBXM" eq object.applyItemType ? "" : "class='hide'"} rel="YBXM">
						<td class="addTd">
							项目名称
						</td>
						<td align="left" colspan="3">
							<c:out value="${object.projectName}" />
						</td>
						<td class="addTd">
							登记号
						</td>
						<td align="left">
							<c:out value="${object.optBaseInfo.transAffairNo}" />
						</td>
					</tr>
					<tr ${"YBXM" eq object.applyItemType ? "" : "class='hide'"} rel="YBXM">
						<td class="addTd">
							项目申请人(来文单位)
						</td>
						<td align="left" colspan="5">
							<c:out value="${object.incomeDeptName}" />
						</td>
					</tr>
					<tr ${"YBXM" eq object.applyItemType ? "" : "class='hide'"} rel="YBXM">
						<td class="addTd">
							来文文号
						</td>
						<td align="left" colspan="3">
							<c:out value="${object.incomeDocNo}" />
						</td>
						<td class="addTd">
							制发日期
						</td>
						<td align="left">
							<fmt:formatDate value='${object.docCreateDate}' pattern='yyyy-MM-dd' />
						</td>
					</tr>
					<tr ${"YBXM" eq object.applyItemType ? "" : "class='hide'"} rel="YBXM">
						<td class="addTd">
							来文单位分类
						</td>
						<td align="left" colspan="3">
							<c:forEach var="row" items="${cp:DICTIONARY('INCOME_DEPT_TYPE')}">
								<c:if test="${object.incomeDeptType eq row.key or (empty object.incomeDeptType and row.key eq '0')}">
									<c:out value="${row.value}" />
								</c:if>
							</c:forEach>
							<c:if test="${'7' eq object.incomeDeptType}">
								&nbsp;&nbsp;&nbsp;&nbsp;省政府文号:<c:out value="${object.provincialGovDocNo}" />
							</c:if>
						</td>
						<td class="addTd">
							申报日期
						</td>
						<td align="left">
							<fmt:formatDate value='${object.incomeDate}' pattern='yyyy-MM-dd' />
						</td>
					</tr>
					<tr ${"YBXM" eq object.applyItemType ? "" : "class='hide'"} rel="YBXM">
						<td class="addTd">
							申报人
						</td>
						<td align="left" colspan="3">
							<c:out value="${object.applyUser}" />
						</td>
						<td class="addTd">
							申报人手机号码
						</td>
						<td align="left">
							<c:out value="${object.applyUserPhone}" />
						</td>
					</tr>
					<!-- 一般权力（YBQL）：行政权力登记界面 -->
					<tr ${"YBQL" eq object.applyItemType ? "" : "class='hide'"} rel="YBQL">
						<td class="addTd">
							来文单位
						</td>
						<td align="left" colspan="3">
							<c:out value="${object.incomeDeptName}" />
						</td>
						<td class="addTd">
							来文单位分类
						</td>
						<td align="left">
							<c:forEach var="row" items="${cp:DICTIONARY('INCOME_DEPT_TYPE')}">
								<c:if test="${object.incomeDeptType eq row.key or (empty object.incomeDeptType and row.key eq '0')}">
									<c:out value="${row.value}" />
								</c:if>
							</c:forEach>
							<c:if test="${'7' eq object.incomeDeptType}">
								&nbsp;&nbsp;&nbsp;&nbsp;省政府文号:<c:out value="${object.provincialGovDocNo}" />
							</c:if>
						</td>
					</tr>
					<tr ${"YBQL" eq object.applyItemType ? "" : "class='hide'"} rel="YBQL">
						<td class="addTd">
							文件标题
						</td>
						<td align="left" colspan="3">
							<c:out value="${object.incomeDocTitle}" />
						</td>
						<td class="addTd">
							阅办文编号
						</td>
						<td align="left">
							<c:out value="${object.optBaseInfo.transAffairNo}" />
						</td>
					</tr>
					<tr ${"YBQL" eq object.applyItemType ? "" : "class='hide'"} rel="YBQL">
						<td class="addTd">
							来文文号
						</td>
						<td align="left">
							<c:out value="${object.incomeDocNo}" />
						</td>
						<td class="addTd">
							制发日期
						</td>
						<td align="left">
							<fmt:formatDate value='${object.docCreateDate}' pattern='yyyy-MM-dd' />
						</td>
						<td class="addTd">
							申报日期
						</td>
						<td align="left">
							<fmt:formatDate value='${object.incomeDate}' pattern='yyyy-MM-dd' />
						</td>
					</tr>
					<tr ${"YBQL" eq object.applyItemType ? "" : "class='hide'"} rel="YBQL">
						<td class="addTd">
							申报人
						</td>
						<td align="left" colspan="3">
							<c:out value="${object.applyUser}" />
						</td>
						<td class="addTd">
							申报人手机号码
						</td>
						<td align="left">
							<c:out value="${object.applyUserPhone}" />
						</td>
					</tr>
					<!-- 棉花加工（MHJG）：棉花加工资格认定及变更登记界面 -->
					<tr ${"MHJG" eq object.applyItemType ? "" : "class='hide'"} rel="MHJG">
						<td class="addTd">
							文件标题
						</td>
						<td align="left" colspan="3">
							<c:out value="${object.incomeDocTitle}" />
						</td>
						<td class="addTd">
							 登记号
						</td>
						<td align="left">
							<c:out value="${object.optBaseInfo.transAffairNo}" />
						</td>
					</tr>
					<tr ${"MHJG" eq object.applyItemType ? "" : "class='hide'"} rel="MHJG">
						<td class="addTd">
							项目申请人(来文单位)
						</td>
						<td align="left" colspan="3">
							<c:out value="${object.incomeDeptName}" />
						</td>
						<td class="addTd">
							来文单位分类
						</td>
						<td align="left">
							<c:forEach var="row" items="${cp:DICTIONARY('INCOME_DEPT_TYPE')}">
								<c:if test="${object.incomeDeptType eq row.key or (empty object.incomeDeptType and row.key eq '0')}">
									<c:out value="${row.value}" />
								</c:if>
							</c:forEach>
							<c:if test="${'7' eq object.incomeDeptType}">
								&nbsp;&nbsp;&nbsp;&nbsp;省政府文号:<c:out value="${object.provincialGovDocNo}" />
							</c:if>
						</td>
					</tr>
					<tr ${"MHJG" eq object.applyItemType ? "" : "class='hide'"} rel="MHJG">
						<td class="addTd">
							来文文号
						</td>
						<td align="left" colspan="3">
							<c:out value="${object.incomeDocNo}" />
						</td>
						<td class="addTd">
							制发日期
						</td>
						<td align="left">
							<fmt:formatDate value='${object.docCreateDate}' pattern='yyyy-MM-dd' />
						</td>
					</tr>
					<tr ${"MHJG" eq object.applyItemType ? "" : "class='hide'"} rel="MHJG">
						<td class="addTd">
							申报日期
						</td>
						<td align="left" colspan="5">
							<fmt:formatDate value='${object.incomeDate}' pattern='yyyy-MM-dd' />
						</td>
					</tr>
					<tr ${"MHJG" eq object.applyItemType ? "" : "class='hide'"} rel="MHJG">
						<td class="addTd">
							主要建设及变更内容
						</td>
						<td align="left" colspan="5">
							<c:out value="${object.applyDemo}" />
						</td>
					</tr>
					<tr ${"MHJG" eq object.applyItemType ? "" : "class='hide'"} rel="MHJG">
						<td class="addTd">
							申报人
						</td>
						<td align="left" colspan="3">
							<c:out value="${object.applyUser}" />
						</td>
						<td class="addTd">
							申报人手机号码
						</td>
						<td align="left">
							<c:out value="${object.applyUserPhone}" />
						</td>
					</tr>
					<!-- 设备免税（SBMS）：建设项目进口设备免税确认单登记界面 -->
					<tr ${"SBMS" eq object.applyItemType ? "" : "class='hide'"} rel="SBMS">
						<td class="addTd">
							项目名称
						</td>
						<td align="left" colspan="5">
							<c:out value="${object.projectName}" />
						</td>
					</tr>
					<tr ${"SBMS" eq object.applyItemType ? "" : "class='hide'"} rel="SBMS">
						<td class="addTd">
							项目申请人(来文单位)
						</td>
						<td align="left" colspan="3">
							<c:out value="${object.incomeDeptName}" />
						</td>
						<td class="addTd">
							登记号
						</td>
						<td align="left">
							<c:out value="${object.optBaseInfo.transAffairNo}" />
						</td>
					</tr>
					<tr ${"SBMS" eq object.applyItemType ? "" : "class='hide'"} rel="SBMS">
						<td class="addTd">
							来文单位分类
						</td>
						<td align="left">
							<c:forEach var="row" items="${cp:DICTIONARY('INCOME_DEPT_TYPE')}">
								<c:if test="${object.incomeDeptType eq row.key or (empty object.incomeDeptType and row.key eq '0')}">
									<c:out value="${row.value}" />
								</c:if>
							</c:forEach>
							<c:if test="${'7' eq object.incomeDeptType}">
								&nbsp;&nbsp;&nbsp;&nbsp;省政府文号:<c:out value="${object.provincialGovDocNo}" />
							</c:if>
						</td>
						<td class="addTd">
							来文文号
						</td>
						<td align="left">
							<c:out value="${object.incomeDocNo}" />
						</td>
						<td class="addTd">
							制发日期
						</td>
						<td align="left">
							<fmt:formatDate value='${object.docCreateDate}' pattern='yyyy-MM-dd' />
						</td>
					</tr>
					<tr ${"SBMS" eq object.applyItemType ? "" : "class='hide'"} rel="SBMS">
						<td class="addTd">
							申报日期
						</td>
						<td align="left">
							<fmt:formatDate value='${object.incomeDate}' pattern='yyyy-MM-dd' />
						</td>
						<td class="addTd">
							申报人
						</td>
						<td align="left">
							<c:out value="${object.applyUser}" />
						</td>
						<td class="addTd">
							申报人手机号码
						</td>
						<td align="left">
							<c:out value="${object.applyUserPhone}" />
						</td>
					</tr>
					<tr ${"SBMS" eq object.applyItemType ? "" : "class='hide'"} rel="SBMS">
						<td class="addTd">
							通讯地址
						</td>
						<td align="left" colspan="3">
							<c:out value="${object.applyUserAddr}" />
						</td>
						<td class="addTd">
							邮政编码
						</td>
						<td align="left">
							<c:out value="${object.applyUserZip}" />
						</td>
					</tr>
				</table>
			</fieldset>
		</s:form>
	</body>
	<script type="text/javascript">
		var init = null;
		
		function genFrame() {
			var buffer = new StringBuffer();
			buffer.append("<iframe id='" + applyItemType + "Frame' name='" + applyItemType + "Frame' width='100%' height='100%' ");
			buffer.append("frameborder='no' scrolling='false' border='0' marginwidth='0' marginheight='0'></iframe>");
			
			if ("YBXM" == applyItemType || "MHJG" == applyItemType) {
				alert(11);
				$("#incomeDocForm").after(buffer.toString());
				var retVal = "";
				if ("YBXM" == applyItemType && (retVal = "projectInfoView"));
				if ("MHJG" == applyItemType && (retVal = "cottonUnitView"));
				$("#" + applyItemType + "Frame").attr("src", "<%=request.getContextPath()%>/dispatchdoc/incomeProject!view.do?djId=" 
						+ $("#djId").val() + "&retVal=" + retVal);
			}
		}
		
		$(document).ready(function() {
			$("tr[rel='" + $("#applyItemType").val() + "']").show();
// 			genFrame();
			
			init = setInterval("FrameUtils.initialize(window, init)", MyConstant.initTimeForAdjustHeight);
		});
	</script>
</html>
