<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>
<title><s:text name="militarycases.edit.title" /></title>
<script
	src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<fieldset class="_new">
		<legend>
			<p class="ctitle">会议室使用反馈</p>
		</legend>
		<%@ include file="/page/common/messages.jsp"%>
		<s:form action="oaMeetApply" method="post" namespace="/oa"
			id="oaMeetApplyForm" target="_parent" onsubmit="return checkForm();">
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<input type="hidden" id="flowInstId" name="flowInstId"
				value="${flowInstId}" />
			<input type="hidden" id="nodeCode" name="nodeCode"
				value="${nodeCode}">
			<input type="hidden" id="nodeInstId" name="nodeInstId"
				value="${nodeInstId}">
			<input type="hidden" id="flowCode" name="flowCode" value="000857" />
			 <input type="hidden" id="moduleCode" name="moduleCode" value="${moduleCode}" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<!--会议室使用反馈  -->

			<%-- 	<tr>
					<td class="addTd">是否实际使用<span style="color: red">*</span>
					</td>
					<td colspan="3"><select name="isUse"
						style="width: 200px; height: 25px;" id="isUse">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${cp:DICTIONARY('qystate')}">
								<option value="${row.key}"
									<c:if test="${object.isUse  eq row.key}"> selected = "selected" </c:if>
									<c:if test="${empty object.isUse  and row.key eq '01'}"> selected = "selected" </c:if>>
									<c:out value="${row.value}" />
								</option>
							</c:forEach>
					</select></td>
				</tr> --%>
				<tr>
					<td class="addTd">实际开始时间</td>
					<td align="left">
						<%-- 	<sj:datepicker id="begTime" value="%{object.begTime}"
							name="begTime"  style="width:120px"
							yearRange="2000:2024" displayFormat="yy-mm-dd" changeYear="true" readonly="true"/>
					 --%> <input type="text" class="Wdate"
						style="width: 200px; height: 25px; border-radius: 3px; border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${object.begTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
						id="begTime" name="begTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						placeholder="选择日期" />

					</td>
					<td class="addTd">实际结束时间</td>
					<td align="left">
						<%-- 	<sj:datepicker id="endTime" value="%{object.endTime}"
							name="endTime"  style="width:120px"
							yearRange="2000:2024" displayFormat="yy-mm-dd" changeYear="true" readonly="true"/>
					  --%>
						<input type="text" class="Wdate"
						style="width: 200px; height: 25px; border-radius: 3px; border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${object.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
						id="endTime" name="endTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						placeholder="选择日期" />

					</td>
				</tr>


				<tr>
					<td class="addTd">备注</td>
					<td align="left" colspan="3"><s:textarea name="todoremark"
							style="width:100%;height:50px;" id="todoremark" /></td>

				</tr>
			<%-- 	<c:if test="${nodeCode eq 'hys_fk'}">
					<tr>
						<td colspan="4"><input type="button" class='btn' id="sub1"
							onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaMeetMinutes!edit.do?djid=${object.djId}&edittype=T','powerWindow',null);"
							value="会议纪要" /></td>
					</tr>
				</c:if> --%>
			</table>
			<center style="margin-top: 10px;">
				<span style="display: none;"> <s:submit name="saveAndSubmit"
						method="submitdoBack" cssClass="btn" value="提 交" id="submitBtn" />
				</span> <span style="display: none;"> <s:submit name="save"
						method="savedoBack" cssClass="btn" value="保 存" id="saveBtn" />
				</span> <span style="display: none;"> <input type="button"
					value="返回" class="btn" onclick="window.history.go(-1);"
					id="backBtn" />
				</span>
			</center>
		</s:form>
	</fieldset>
</body>
<script type="text/javascript">
	function checkForm() {
		/* if ($("#isUse").val() == '') {
			alert("是否实际使用不可为空！");
			$('#isUse').focus();
			return false;
		} */
		if ($("#begTime").val() > $("#endTime").val()) {
			alert("开始时间不能大于结束时间！");
			$('#begTime').focus();
			return false;
		}
		return true;
	}
	function openNewWindow(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'height=600px,width=800px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}
</script>
</html>