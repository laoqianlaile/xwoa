<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title></title>
<link
	href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	font-family: Microsoft YaHei !important;
}

.container .search .searchArea {
	background: #c1d9f3;
	margin: 0px 1% 0px 1%;
}

.container {
	width: 99%;
	margin: 0 0.5%;
}

.container table th {
	border: 1px solid #ccc;
	text-align: center;
}

.container table th {
	font-size: 13px;
	font-weight: lighter;
}

.container table th a {
	text-decoration: none;
	color: #4b555b;
	font-weight: bold;
}

.container .dateControl {
	height: 30px;
	text-align: center;
	line-height: 30px;
}

.container .dateControl span {
	display: inline-block;
}

.container .dateControl span.monitor {
	width: 400px;
	font-size: 20px;
	font-weight: bolder;
	font-size: 20px;
}

.container .dateControl span.right {
	width: 0px;
	height: 0px;
	border-top: 7px solid transparent;
	border-bottom: 7px solid transparent;
	border-left: 10px solid black;
	font-size: 0px;
	line-height: 0px;
	margin-left: 10px;
	cursor: pointer;
}

.container .dateControl span.left {
	width: 0px;
	height: 0px;
	border-top: 7px solid transparent;
	border-bottom: 7px solid transparent;
	border-right: 10px solid black;
	font-size: 0px;
	line-height: 0px;
	margin-right: 10px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="container">
		<%@ include file="/page/common/messages.jsp"%><input
			type="hidden" id="listType" name="listType" value="${listType}" /> <input
			type="hidden" id="beginDate" name="beginDate" value="${beginDate}" />
		<input type="hidden" id="endDate" name="endDate" value="${endDate}" /><input
			type="hidden" id="curDepno" name="curDepno" value="${curDepno}" /> <input
			type="hidden" id="dateHidden" />
		<fieldset class="search">
			<legend class="headTitle">一周安排</legend>
			<br />
			<div class="searchArea">
				<table cellpadding="0" cellspacing="0">
					<tr style="height: 30px;">
						<td width="33%">日期范围 <input type="text" class="Wdate"
							id="weekTitle" name="weekTitle"
							style="width: 250px; height: 25px; line-height: 25px; border: 1px solid #ccc; vertical-align: middle;"
							onclick="datePickerClick()" /></td>
						<td width="33%">显示范围 <input type="radio"
							style="vertical-align: middle;" id="showType" name="showType"
							value="all" checked="checked" onclick="radio_click('all');">整周
							<input style="vertical-align: middle;" type="radio" id="showType"
							name="showType" value="workday" onclick="radio_click('workday');">周一至周五
							<input style="vertical-align: middle;" type="radio" id="showType"
							name="showType" value="havePlan"
							onclick="radio_click('havePlan');">有安排
						</td>
						<c:if test="${'0' eq listType }">
							<td width="33%">责任部门 <select name="depno" id="depno">
									<option value="">--选择全部--</option>
									<c:forEach items="${unitlist}" var="u">
										<option value="${u.unitcode }">${u.unitname}</option>
									</c:forEach>
							</select> <input type="checkbox" style="vertical-align: middle;"
								id="only_curdepno" name="only_curdepno"
								onclick="checkBox_click();">仅显示本部门
							</td>
						</c:if>
						<c:if test="${'1' eq listType }">
							<td width="33%">责任部门 <select name="depno" id="depno">
									<option value="">---请选择---</option>
									<c:forEach items="${unitlist}" var="u">
										<option value="${u.unitcode }">${u.unitname}</option>
									</c:forEach>
							</select> <input type="button" value="新   增" onclick="AddNew();"
								class="whiteBtnWide" /></td>
						</c:if>
					</tr>
				</table>
			</div>
		</fieldset>
		<div class="dateControl">
			<span class="left" onclick="prepareForTurn(-7);"></span><span
				id="monitor" class="monitor"></span><span class="right"
				onclick="prepareForTurn(7);"></span>
		</div>
		<div class="dataArea" align="center">
			<table style="width: 98%; border-collapse: collapse;">
				<tr height="30px">
					<th
						style="font-size: 15px; width: 8%; background: #eff5fb; color: #786262; font-weight: bolder"
						colspan="2">时间</th>
					<th
						style="font-size: 15px; width: 15%; background: #eff5fb; color: #786262; font-weight: bolder">内容</th>
					<th
						style="font-size: 15px; width: 10%; background: #eff5fb; color: #786262; font-weight: bolder">参加人员</th>
					<th
						style="font-size: 15px; width: 10%; background: #eff5fb; color: #786262; font-weight: bolder">参加领导</th>
					<th
						style="font-size: 15px; width: 15%; background: #eff5fb; color: #786262; font-weight: bolder">地点</th>
					<th
						style="font-size: 15px; width: 6%; background: #eff5fb; color: #786262; font-weight: bolder">责任部门</th>
					<th
						style="font-size: 15px; width: 10%; background: #eff5fb; color: #786262; font-weight: bolder">操作</th>
				</tr>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		initmonitorValue($("#beginDate").val(), $("#endDate").val());
		initWeekTitleValue($("#beginDate").val(), $("#endDate").val());
		<c:if test="${'0' eq listType}">
		$("#depno").val('${curDepno}');
		$("#depno").attr("disabled", "disabled");
		$("#only_curdepno").attr("checked", "checked");//打勾
		</c:if>
		$("#depno").change(function() {//为selected标签添加change事件。
			startLoadData("");
		});
		startLoadData("showType:all");
	});
	/**
	 *为checkBox添加click事件
	 */
	function checkBox_click() {
		if (!($("#only_curdepno").attr('checked') == undefined)) {//判断是否已经打勾
			$("#depno").attr("disabled", "disabled");
			$("#depno").val($("#curDepno").val());
			startLoadData("");
		} else {
			$("#depno").removeAttr("disabled");
		}
	}
	function radio_click(showType) {
		startLoadData("showType:" + showType);
	}
	function prepareForTurn(diffDays) {
		initAfterRending(addDate($("#beginDate").val(), diffDays), addDate($(
				"#endDate").val(), diffDays));
	}
	function datePickerClick() {
		WdatePicker({
			el : "dateHidden",
			dateFmt : 'yyyy-MM-dd',
			onpicked : function() {
				var week = $dp.cal.getP('W', 'WW');
				var currDate = $dp.cal.getP('y') + "-" + $dp.cal.getP('M')
						+ "-" + $dp.cal.getP('d');
				var weekDay = $dp.cal.getP('w').replace('00', '07');
				initAfterRending(getBeginDate(currDate, weekDay), getEndDate(
						currDate, weekDay));
			}
		});
	}
	function initAfterRending(beginDate, endDate) {
		$("#beginDate").val(beginDate);
		$("#endDate").val(endDate);
		initmonitorValue(beginDate, endDate);
		initWeekTitleValue(beginDate, endDate);
		startLoadData("");
	}
	function initmonitorValue(beginDate, endDate) {
		$("#monitor").html(
				dateFormat(newDate(beginDate), 'yyyy年M月d日') + "~"
						+ dateFormat(newDate(endDate), 'yyyy年M月d日'));
	}
	function initWeekTitleValue(beginDate, endDate) {
		$("#weekTitle").val(
				dateFormat(newDate(beginDate), 'yyyy年MM月dd日') + "~"
						+ dateFormat(newDate(endDate), 'yyyy年MM月dd日'));
	}
	/* 以下是对相关表格进行数据取与画 */
	function startLoadData(otherparams) {
		if ("" == otherparams) {
			otherparams = "showType:" + $('input[name=showType]:checked').val();
		}
		otherparams = otherparams + ";beginDate:" + $("#beginDate").val()
				+ ";listType:" + $("#listType").val();
		if ("0" == $("#listType").val()) {
			otherparams = otherparams + ";curDepno:" + $("#curDepno").val()
					+ ";depno:" + $("#depno").val();
		} else {
			if ("" != $("#depno").val()) {
				otherparams = otherparams + ";depno:" + $("#depno").val();
			}
		}
		var urls = "${pageContext.request.contextPath}/oa/oaArrangeweek!getWorkSummariesJsons.do?otherparams="
				+ encodeURI(encodeURI(otherparams));
		$.ajax({
			type : "post",
			url : urls,
			async : false,
			success : function(resp) {
				rendTable(resp);
			}
		});
	}
	function rendTable(datas) {
		var table = $(".dataArea").find("table");
		table.find("tr").each(function(index) {
			if (index > 0) {
				$(this).remove();
			}
		});
		var tabelBody = "";
		$
				.each(
						datas,
						function(i, data) {
							var value = data.value;
							var id = data.id;
							var size = data.size;
							if (data.checkempty) {
								var trvalue = "<tr height=\"50px\"><th width=\"4%\">"
										+ value
										+ "<\/th><th width=\"4%\">-<\/th><th colspan=\"6\" style=\"color: #786262;\">暂无工作安排<\/th><\/tr>";
								tabelBody = tabelBody + trvalue;
							} else {
								$
										.each(
												data.arrangeweekJsons,
												function(j, arrangeweek) {
													var trsize = "50px";
													if (size > 1) {
														trsize = "40px";
													}
													var tdvalue = "<th width=\"4%\">"
															+ StringUils(arrangeweek.showTimeValue)
															+ "<\/th><th>"
															+ StringUils(arrangeweek.remark)
															+ "<\/th><th>"
															+ StringUils(arrangeweek.attendusers)
															+ "<\/th><th>"
															+ StringUils(arrangeweek.attendleaders)
															+ "<\/th><th>"
															+ StringUils(arrangeweek.address)
															+ "<\/th><th>"
															+ StringUils(arrangeweek.depname)
															+ "</th><th>"
															+ getDealWithHtml(arrangeweek)
															+ "</th>";
													if (j == 0) {
														tabelBody = tabelBody
																+ "<tr height=\""+trsize+"\"><th width=\"4%\" rowspan=\""
																+ size + "\">"
																+ value
																+ "<\/th>"
																+ tdvalue
																+ "<\/tr>";
													} else {
														tabelBody = tabelBody
																+ "<tr height=\""+trsize+"\">"
																+ tdvalue
																+ "<\/tr>";
													}
												});
							}
						});
		table.append(tabelBody);
	}
	function getDealWithHtml(arrangeweek) {
		var canUpdated = arrangeweek.canUpdated;
		var canDeleted = arrangeweek.canDeleted;
		var djId = arrangeweek.djId;
		var dealwithHtml = "<a class=\"check_email\" href=\"#\" onclick=\"view(\'"
				+ djId + "\')\">查看<\/a>";
		if ("1" == canUpdated) {
			dealwithHtml = dealwithHtml
					+ "   <a class=\"bianji\" href=\"#\" onclick=\"edit(\'"
					+ djId + "\')\">编辑<\/a>";
		}
		if ("1" == canDeleted) {
			dealwithHtml = dealwithHtml
					+ "   <a class=\"delete_email\" href=\"#\" onclick=\"deleted(\'"
					+ djId + "\')\">删除</a>";
		}
		return dealwithHtml;
	}
	function StringUils(obj) {
		if ("undefined" == typeof (obj) || null == obj) {
			return "";
		} else {
			return obj;
		}
	}
	function AddNew() {
		var url = "${pageContext.request.contextPath}/oa/oaArrangeweek!regiseter.do?popPage=true&listType="
				+ $("#listType").val();
		DialogUtil.open("添加新工作安排", url, '80%', '70%', null, null, function() {
			startLoadData("");
		});
	}
	function view(djId) {
		var url = "${pageContext.request.contextPath}/oa/oaArrangeweek!view.do?djId="
				+ djId + "&listType=" + $("#listType").val();
		DialogUtil.open("查看工作安排", url, "40%", "35%");
	}
	function edit(djId) {
		var url = "${pageContext.request.contextPath}/oa/oaArrangeweek!regiseter.do?popPage=true&djId="
				+ djId + "&listType=" + $("#listType").val();
		DialogUtil.open("编辑工作安排", url, '80%', '70%', null, null, function() {
			startLoadData("");
		});
	}
	function deleted(djId) {
		top.art.dialog
				.confirm(
						'是否确删除此安排？',
						function() {
							var urls = "${pageContext.request.contextPath}/oa/oaArrangeweek!delete.do?djId="
									+ djId;
							$.ajax({
								type : "post",
								url : urls,
								async : false,
								success : function(resp) {
									if (resp == "OK")
										startLoadData("");
								}
							});
						}, function() {
						});
	}
</script>
</html>