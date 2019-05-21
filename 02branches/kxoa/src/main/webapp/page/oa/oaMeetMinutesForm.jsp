
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>

<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
</head>

<body>


	<%@ include file="/page/common/messages.jsp"%>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaMeetMinutes.edit.title" />
			</p>
		</legend>
		<s:form action="oaMeetMinutes" method="post" namespace="/oa"
			id="oaMeetMinutesForm" data-validate="true"
			enctype="multipart/form-data">

			<input type="hidden" id="version" name="version"
				value="${object.version}" />

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
					<td class="addTd">会议室申请ID</td>
					<td colspan="3"><c:if test="${not empty object.djid}">
						 ${object.djid}
						 <input type="hidden" id="djid" name="djid" value="${object.djid}" />
						</c:if> <c:if test="${empty object.djid}">
							<s:textfield id="djid" name="djid" value="%{object.djid}"
								style="width: 80%;height: 25px;" readonly="readonly"></s:textfield>

							<input type="button" class='btn' name="powerBtn"
								onClick="openNewWindow('<%=request.getContextPath()%>/oa/oaMeetApply!selectlist.do?','powerWindow',null);"
								value="选择">
						</c:if></td>

				</tr>

				<tr>
					<td class="addTd">会议主题</td>
					<td><s:textfield id="title" name="title"
							value="%{object.title}" style="width:200px;height: 25px;"></s:textfield>

					</td>


					<td class="addTd">会议室<span style="color: red">*</span>
					</td>

					<td align="left"><select id="meetingNo" name="meetingNo"
						  style="width:200px;height: 25px;">
							<c:forEach var="dt" items="${oaMeetinfolist}">
								<option value="${dt.djid}"
									<c:if test="${dt.djid==meetingNo}"> selected="selected"</c:if>>${dt.meetingName
									}</option>
							</c:forEach>
					</select></td>
				</tr>


				<tr>
					<td class="addTd">开始时间<span style="color: red">*</span>
					</td>
					<td align="left"><input type="text" class="Wdate"
						id="begTime" name="begTime"
						style="height: 25px; width: 200px; border-radius: 3px; border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${object.begTime}" pattern="yyyy-MM-dd  HH:mm"/>'
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd  HH:mm:ss'})"
						placeholder="选择日期"> <%-- 						<sj:datepicker id="begTime" value="%{object.begTime}" --%>
						<%-- 							name="begTime"  style="width:120px" --%> <!-- 							yearRange="2000:2024" displayFormat="yy-mm-dd" changeYear="true"  -->
						<!--							readonly="true" timepickerFormat="hh:mm"  timepicker="true"/>  -->
					</td>
					<td class="addTd">结束时间<span style="color: red">*</span>
					</td>
					<td align="left"><input type="text" class="Wdate"
						id="endTime" name="endTime"
						style="height: 25px; width:200px; border-radius: 3px; border: 1px solid #cccccc;"
						value='<fmt:formatDate value="${object.endTime}" pattern="yyyy-MM-dd  HH:mm"/>'
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd  HH:mm:ss'})"
						placeholder="选择日期"> <%-- 						<sj:datepicker id="endTime" value="%{object.endTime}" --%>
						<%-- 							name="endTime"  style="width:120px" --%> <%-- 							yearRange="2000:2024" displayFormat="yy-mm-dd" changeYear="true" readonly="true" timepickerFormat="hh:mm"  timepicker="true"/> --%>
					</td>
				</tr>
				<tr>
					<td class="addTd">使用部门</td>
					<td align="left"><s:textfield name="doDepno" id="doDepno"
							style="width:200px;height: 25px;" /></td>
					<td class="addTd">使用人</td>
					<td align="left"><s:textfield name="doCreater" id="doCreater"
							style="width: 200px;height: 25px;" /></td>
				</tr>
				<tr>

					<td class="addTd">参会人员<span style="color: red">*</span>
					</td>
					<td align="left" colspan="3"><input type="text"
						id="meetingPersions" name="meetingPersions"
						style="width: 100%; height: 25px;" value="${meetingPersions}"
						readonly="readonly" /> <input type="hidden" id="userValue"
						name="userValue" value="${userValue}" /></td>
				</tr>
				<tr>

					<td class="addTd">抄送人员
					</td>
					<td align="left" colspan="3">
					<input type="text" id="ccPersonnel" name="ccPersonnel" style="width: 100%; height: 25px;" value="${ccPersonnel}"
						readonly="readonly" />
					<input type="hidden" id="csuserValue" name="csuserValue" value="${csuserValue}" /></td>
				</tr>
				<tr>
					<td class="addTd">主持人</td>
					<td align="left" colspan="3"><s:textarea name="meetingHost"
							style="width:100%;height:50px;" value="%{object.meetingHost}"
							id="meetingHost" /></td>
				</tr>
				<tr>
					<td class="addTd">正文</td>
					<td align="left" colspan="3"><s:file name="docFile_" size="40"
							style="width: 90%;height: 27px;" /> <c:if
							test="${not empty object.docFileName}">&nbsp;
				<input type="button" name="built" value="查看" class="btn"
								onclick="downFile('${object.djid}','${object.version }','docFile_')">&nbsp;</c:if></td>

				</tr>

				<tr>
					<td class="addTd">备注</td>
					<td align="left" colspan="3"><s:textarea name="remark"
							style="width:100%;height:50px;" id="remark" /></td>
				</tr>



			</table>

			<div class="formButton">
				<s:submit type="button" name="back" cssClass="btn"
					key="opt.btn.back" />
				<input type="button" class="btn" value="保存"
					onclick="submitItemFrame('SAVE');" />
			</div>

			<!-- 选择人员的模块 -->
			<div id="attAlert" class="alert"
				style="width: 600px; height: 330px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
				<h4>
					<span id="selectTT">选择</span><span id="close2"
						style="float: right; margin-right: 8px;" class="close"
						onclick="closeAlert('attAlert');">关闭</span>
				</h4>
				<div class="fix">
					<div id="leftSide"></div>
					<div id="l-r-arrow">
						<div class="lb"></div>
						<div class="rb"></div>
					</div>
					<div id="rightSide">
						<ul></ul>
					</div>
					<div id="t-b-arrow">
						<!-- <div class="tb"></div>
	            <div class="bb"></div> -->
						<b class="btns"> <input id="save" class="btn" type="button"
							value="保     存" /><input id="clear" class="btn" type="button"
							value="取     消" style="margin-top: 5px;" /></b>
					</div>
				</div>
			</div>
		</s:form>
	</fieldset>
</body>
<script type="text/javascript">
	function submitItemFrame(subType) {
		if (docheck() == false) {
			return;
		} else {
			var srForm = document.getElementById("oaMeetMinutesForm");
			if (subType == 'SAVE') {
				srForm.action = 'oaMeetMinutes!savePermitReg.do';
			}

			srForm.submit();
		}
	}

	function docheck() {
		if ($("#title").val() == '') {
			alert("会议室主题不可为空！");
			$('#title').focus();
			return false;
		}

		if ($("#meetingNo").val() == '') {
			alert("会议室不可为空！");
			$('#meetingNo').focus();
			return false;
		}
		var today = new Date();
		var dt = new Date($("#begTime").val().replace(/-/,"/")); 
		if ($("#begTime").val() == '') {
			alert("开始时间不能为空！");
			$('#begTime').focus();
			return false;
		}else if(dt<today){
			alert("开始时间发生在过去");
		}
		if ($("#endTime").val() == '') {
			alert("结束时间不能为空！");
			$('#endTime').focus();
			return false;
		}
		if ($("#meetingPersions").val() == '') {
			alert("参会人员不能为空！");
			$('#meetingPersions').focus();
			return false;
		}
		/* if($("#ccPersonnel").val()==''){
			alert("抄送人员不能为空！");
			$('#ccPersonnel').focus();
			return false;
		} */
		if ($("#begTime").val() > $("#endTime").val()) {
			alert("开始时间不能大于结束时间！");
			$('#begTime').focus();
			return false;
		}

	}
	function getStringLen(Str) {
		var i, len, code;
		if (Str == null || Str == "")
			return 0;
		len = Str.length;
		for (i = 0; i < Str.length; i++) {
			code = Str.charCodeAt(i);
			if (code > 255) {
				len++;
			}
		}
		return len;
	}

	function downFile(id, v, fileType) {
		var url = "oaMeetMinutes!downStuffInfo.do?djid=" + id + "&version=" + v
				+ "&fileType=" + fileType;
		document.location.href = url;
	}
	function openNewWindow(winUrl, winName, winProps) {
		if (winProps == '' || winProps == null) {
			winProps = 'height=600px,width=700px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		}
		window.open(winUrl, winName, winProps);
	}

	var oOrgUser = new Object();

	oOrgUser["meetingPersions"] = $("#meetingPersions");

	$("#meetingPersions").click(
			function() {
				var d = '${userjson}';

				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d), $("#meetingPersions"),
							$("#userValue"), oOrgUser);
				}
				;
			});
	$("#ccPersonnel").click(
			function() {
				var d = '${userjson}';

				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d), $("#ccPersonnel"),
							$("#csuserValue"), oOrgUser);
				}
				;
			});
	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert");
	}
</script>
</html>
