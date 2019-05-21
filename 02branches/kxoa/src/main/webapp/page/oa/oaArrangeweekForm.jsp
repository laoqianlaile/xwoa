<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title></title>
</head>
<body class="sub-flow">
	<%@ include file="/page/common/messages.jsp"%>
	<s:form action="oaArrangeweek" method="post" namespace="/oa"
		id="oaArrangeweekForm">
		<input type="hidden" id="popPage" name="popPage" value="${popPage}" />
		<input type="hidden" id="djId" name="djId" value="${object.djId}" />
		<input type="hidden" id="state" name="state" value="${object.state}" />
		<input type="hidden" id="begindate" name="begindate" />
		<input type="hidden" id="enddate" name="enddate" />
		<c:if
			test="${('0' eq listType) and ('0' eq object.state || empty object.state ) }">
			<input type="button" class="btn" id="save" name="saveBtn"
				onclick="doSubmitCheck('SAVE');" value="暂  存" />
		</c:if>
		<input type="button" class="btn" id="saveAndSubmit" name="submitBtn"
			onclick="doSubmitCheck('SUB');" value="提  交" />
		<fieldset class="_new">
			<c:if test="${empty object.djId }">
				<legend>工作安排填报</legend>
			</c:if>
			<c:if test="${!(empty object.djId) }">
				<legend>编辑工作安排</legend>
			</c:if>
		</fieldset>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="viewTable">
			<tr>
				<td class="addTd">时间<font color="#ff0000">*</font>
				</td>
				<td align="left" colspan="3"><input type="text" class="Wdate"
					readonly="readonly" style="width: 200px; height: 30px;"
					id="selectTime" name="selectTime"
					value='<fmt:formatDate value="${object.createtime}"  pattern="yyyy-MM-dd HH:mm"/>'
					onclick="Wdate()" placeholder="选择日期" /></td>
			</tr>
			<c:if test="${('true' eq popPage) and (empty object.djId) }">
				<tr>
					<td class="addTd">责任部门</td>
					<td align="left" colspan="3"><select name="depno" id="depno">
							<option value="">---请选择---</option>
							<c:forEach items="${unitlist}" var="u">
								<option value="${u.unitcode }">${u.unitname}</option>
							</c:forEach>
					</select></td>
				</tr>
			</c:if>
			<tr>
				<td class="addTd">参加人员<font color="#ff0000">*</font></td>
				<td align="left" colspan="3"><input type="text"
					id="attendusers" name="attendusers" maxlength="200"
					value="${object.attendusers}" style="width: 100%; height: 30px;" /></td>
			</tr>
			<tr>
				<td class="addTd">参加领导<font color="#ff0000">*</font></td>
				<td align="left" colspan="3"><input type="text"
					id="attendleaders" name="attendleaders" maxlength="200"
					value="${object.attendleaders}" style="width: 100%; height: 30px;" /></td>
			</tr>
			<tr>
				<td class="addTd">地点<font color="#ff0000">*</font></td>
				<td align="left" colspan="3"><input type="text" id="address"
					name="address" maxlength="200" value="${object.address}"
					style="width: 100%; height: 30px;" /></td>
			</tr>
			<tr>
				<td class="addTd">工作内容<font color="#ff0000">*</font></td>
				<td align="left" colspan="3"><s:textarea name="remark"
						id="remark" cols="40" rows="6" style="width: 100%;height:150px" value="%{object.remark}">
					</s:textarea></td>
			</tr>
		</table>
	</s:form>
</body>
<script type="text/javascript">
	function Wdate() {
		WdatePicker({
			dateFmt : 'yyyy-MM-dd HH:mm',
			minDate : '#F{getminDate()}',
			onpicked : function(date) {
				if ("" != $("#djId").val()) {
					var weekDay = ("0" + new Date().getDay()).replace('00',
							'07');
					var today = dateFormat(newDate(), "yyyy-MM-dd");
					var today_begin = getBeginDate(today, weekDay);
					if ((newDate(date.cal.getDateStr())).getTime() < (newDate(today_begin))
							.getTime()) {
						top.art.dialog.confirm('您选择的日期为'
								+ date.cal.getDateStr() + '，已经过期，确认覆盖原来时间吗?',
								function() {
								}, function() {
									var t = '${object.createtime}';
									if ("" != t) {
										$("#selectTime")
												.val(t.substring(0, 16));
									} else {
										$("#selectTime").val("");
									}
								});
					}
				}
			}
		});
	}
	function getminDate() {
		if ("" == $("#djId").val()) {
			var weekDay = ("0" + newDate().getDay()).replace('00', '07');
			return getBeginDate(dateFormat(newDate(), 'yyyy-MM-dd'), weekDay);
		}
		return '2000-01-01';
	}
	function docheck(subType) {
		if ("" == $("#selectTime").val()) {
			return "时间不能为空！";
		}
		if (subType == 'SUB') {
			if ("" == $("#attendusers").val()) {
				return "参加人员不能为空！";
			}
			if ("" == $("#attendleaders").val()) {
				return "参加领导不能为空！";
			}
			if ("" == $("#address").val()) {
				return "地点不能为空！";
			}
			if ("" == $("#remark").val()) {
				return "工作内容不能为空！";
			}
		}
		return "";
	}
	function doSubmitCheck(subType) {
		var result = docheck(subType);
		if ("" != result) {
			DialogUtil.alert(result);
			return false;
		} else {
			var selectTime = newDate(($("#selectTime").val()).substring(0, 10));
			var weekDay = ("0" + selectTime.getDay()).replace('00', '07');
			$("#begindate")
					.val(
							getBeginDate(dateFormat(selectTime, 'yyyy-MM-dd'),
									weekDay));
			$("#enddate").val(
					getEndDate(dateFormat(selectTime, 'yyyy-MM-dd'), weekDay));
			if ("true" == $("#popPage").val()) {//对于从列表弹出的编辑页面
				var saveParams = "selectTime:" + $("#selectTime").val()
						+ ";begindate:" + $("#begindate").val() + ";enddate:"
						+ $("#enddate").val() + ";attendusers:"
						+ $("#attendusers").val() + ";attendleaders:"
						+ $("#attendleaders").val() + ";address:"
						+ $("#address").val() + ";remark:" + $("#remark").val();
				if ("" == $("#djId").val()) {
					saveParams = saveParams + ";depno:" + $("#depno").val();
				} else {
					saveParams = saveParams + ";djId:" + $("#djId").val();
				}
				if (subType == 'SUB') {
					DialogUtil.confirm("确定要提交相关工作安排吗？", function() {
						ajaxsubmitInfo(saveParams + ";state:1");
					});
				}
				if ("SAVE" == subType) {
					DialogUtil.confirm("确定要保存相关工作安排吗？", function() {
						ajaxsubmitInfo(saveParams + ";state:0");
					});
				}
			} else {
				if (subType == 'SUB') {
					DialogUtil
							.confirm(
									"确定要提交相关工作安排吗？",
									function() {
										$("#state").val("1");
										$("#oaArrangeweekForm")
												.attr("action",
														"${pageContext.request.contextPath}/oa/oaArrangeweek!saveOaArrange.do");
										$("#oaArrangeweekForm").submit();
									});
				}
				if ("SAVE" == subType) {
					DialogUtil
							.confirm(
									"确定要保存相关工作安排吗？",
									function() {
										$("#state").val("0");
										$("#oaArrangeweekForm")
												.attr("action",
														"${pageContext.request.contextPath}/oa/oaArrangeweek!saveOaArrange.do");
										$("#oaArrangeweekForm").submit();
									});
				}
			}
		}
	}
	function ajaxsubmitInfo(saveParams) {
		var urls = "${pageContext.request.contextPath}/oa/oaArrangeweek!saveOaArrange.do?saveParams="
				+ encodeURI(encodeURI(saveParams));
		$.ajax({
			type : "post",
			url : urls,
			async : false,
			success : function(resp) {
				if (resp == "OK") {
					DialogUtil.close();
				} else {
					DialogUtil.alert("保存失败！", "", 150, 100);
				}

			}
		});
	}
</script>
</html>