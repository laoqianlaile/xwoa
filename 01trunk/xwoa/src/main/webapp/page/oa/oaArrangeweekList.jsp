<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<title></title>
<link
	href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css"
	rel="stylesheet" type="text/css" />
	<%--<script  src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js"></script>--%>
	<script
			src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>
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
td {
text-align:center;
}
.tdName{
	word-break: keep-all;
	white-space: nowrap;
}

</style>
</head>
<body>
	<div class="container">
		<%@ include file="/page/common/messages.jsp"%><input
			type="hidden" id="listType" name="listType" value="${listType}" /> <input
			type="hidden" id="beginDate" name="beginDate" value="${beginDate}" /> <input
	        type="hidden" id="meetType" name="meetType" value="${meetType}" />
		<input type="hidden" id="endDate" name="endDate" value="${endDate}" /><input
			type="hidden" id="curDepno" name="curDepno" value="${curDepno}" /> <input
			type="hidden" id="dateHidden" />
		<fieldset class="search">
			<legend class="headTitle">一周安排</legend>
			<br />
			<div class="searchArea">
				<table cellpadding="0" class="tableBody">
					<tr style="height: 30px;">

						<td width="20%">日期范围 <input type="text" class="Wdate"
							id="weekTitle" name="weekTitle"
							style="width: 250px; height: 25px; line-height: 25px; border: 1px solid #ccc; vertical-align: middle;"
							onclick="datePickerClick()" /></td>
						<td width="35%">显示范围
						<input type="radio"
							style="vertical-align: middle;" id="showType" name="showType"
							value="all" checked="checked" onclick="radio_click('all');">所有
						<!-- <input style="vertical-align: middle;" type="radio" id="showType"
							name="showType" value="workday" onclick="radio_click('workday');">周一至周五 -->
						<!-- <input style="vertical-align: middle;" type="radio" id="showType"
							name="showType" value="havePlan"
							onclick="radio_click('havePlan');">有安排 -->


						
						<input style="vertical-align: middle;" type="radio" id="showType"
							name="showType" value="owner"
							onclick="radio_click('owner');">本人&nbsp;&nbsp;
							姓名:
							<input type="text" class="searchCondContent" name="s_username"  id="username" />
							
						</td>
						<!-- <td class="tdName" width="10%">
							
							
						</td>
 -->
						<c:if test="${'0' eq listType }">
							<td width="40%">责任处室
						 		 <select name="depno" id="depno">
									<option value="">--选择全部--</option>
									<c:forEach items="${unitlist}" var="u">
										<option value="${u.unitcode }">${u.unitname}</option>
									</c:forEach>
								</select>
								<input type="checkbox" style="vertical-align: middle;"
									   id="only_curdepno" name="only_curdepno"
									   onclick="checkBox_click();">仅显示本部门
								</select>

								<input type="button" value="搜  索"   class="whiteBtnWide" />&nbsp;&nbsp;
								<input type="button" value="新   增" onclick="AddNew();"  class="whiteBtnWide" />&nbsp;&nbsp;
								<%--<input type="button" value="打印" onclick="print();" class="whiteBtnWide" /> &nbsp;&nbsp;--%>
								<input type="button" value="导出Excel" onclick="exportExcel('');   " class="whiteBtnWide" />
							</td>

						</c:if>
						<c:if test="${'1' eq listType }">
							<td width="25%">
							<%--责任部门 <select name="depno" id="depno">
									<option value="">---请选择---</option>
									<c:forEach items="${unitlist}" var="u">
										<option value="${u.unitcode }">${u.unitname}</option>
									</c:forEach>
							</select> --%>
									<input type="button" value="搜  索"   class="whiteBtnWide" />&nbsp;&nbsp;
									<input type="button" value="新  增" onclick="AddNew();"class="whiteBtnWide" />&nbsp;&nbsp;
									<input type="button" value="导出Excel" onclick="exportExcel('');   " class="whiteBtnWide" />
							</td>
						</c:if>
						
						<c:if test="${'3' eq listType || '2' eq listType}">
							<td width="40%">
							<input id="depno" type="hidden" value="">
							<input type="hidden" style="vertical-align: middle;"
								id="only_curdepno" name="only_curdepno"
								onclick="checkBox_click();">
								<input type="button" value="搜  索"   class="whiteBtnWide" />&nbsp;&nbsp;
								<%--<input type="button" value="打印" onclick="print();" class="whiteBtnWide" /> &nbsp;&nbsp;--%>
								<input type="button" value="导出Excel" onclick="exportExcel('');   " class="whiteBtnWide" />
							</td>

						</c:if>
							<%-- <c:if test="${'0' eq listType }">
							<td width="20%">领导：<input type="text" id="leader" name="leader"/></td>
						</c:if>
						<c:if test="${'1' eq listType }">
							<td width="20%">领导：<input type="text" id="leader" name="leader"/></td>
						</c:if> --%>
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
						style="font-size: 15px; width: 18%; background: #eff5fb; color: #786262; font-weight: bolder;"
						colspan="2">时间</th>
					<th
						style="font-size: 15px; width: 8%; background: #eff5fb; color: #786262; font-weight: bolder;">活动类型</th>
					<th
						style="font-size: 15px; width: 10%; background: #eff5fb; color: #786262; font-weight: bolder;">地点</th>
					<th
						style="font-size: 15px; width: 23%; background: #eff5fb; color: #786262; font-weight: bolder;">工作内容</th>
					<c:if test="${'1' eq listType }">
					<th
						style="font-size: 15px; width: 15%; background: #eff5fb; color: #786262; font-weight: bolder;">参加领导</th>
					</c:if>
					<c:if test="${'3' eq listType }">
					<th
						style="font-size: 15px; width: 15%; background: #eff5fb; color: #786262; font-weight: bolder;">参加领导</th>
					</c:if>
					
					<c:if test="${'0' eq listType}">
					<th
						style="font-size: 15px; width: 15%; background: #eff5fb; color: #786262; font-weight: bolder;">处室负责人</th>
					</c:if>
					<c:if test="${'2' eq listType}">
					<th
						style="font-size: 15px; width: 15%; background: #eff5fb; color: #786262; font-weight: bolder;">处室负责人</th>
					</c:if>
					<th
						style="font-size: 15px; width: 15%; background: #eff5fb; color: #786262; font-weight: bolder;">参加人员</th>
					<c:if test="${'0' eq listType || '2' eq listType}">
					<th
						style="font-size: 15px; width: 10%; background: #eff5fb; color: #786262; font-weight: bolder;">责任处室</th>
					</c:if>
					<th id="hc" class="hc"
						style="font-size: 15px; width: 20%; background: #eff5fb; color: #786262; font-weight: bolder;">操作</th>
				</tr>
			</table>
		</div>
	</div>
</body>
<%@ include file="/page/common/print.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
        openLoadIng(false);
		initmonitorValue($("#beginDate").val(), $("#endDate").val());
		initWeekTitleValue($("#beginDate").val(), $("#endDate").val());
		<c:if test="${'0' eq listType}">
		$("#depno").val('${curDepno}');
		$(".tdName").hide();
		$("#depno").attr("disabled", "disabled");
		$("#only_curdepno").attr("checked", "checked");//打勾
		</c:if>
		$("#depno").change(function() {//为selected标签添加change事件。
			startLoadData("");
		});
		$("#leader").change(function() {//为selected标签添加change事件。
			startLoadData("");
		});
		$("#username").change(function (){
            startLoadData("");
		})
		startLoadData("showType:all");
	});



	/**
	 *为checkBox添加click事件
	 */
	function checkBox_click() {
	    debugger;
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
			otherparams = otherparams+ ";leader:" + $("#leader").val();
		} else {
            <c:if test="${'0' eq listType}">
			if ("" != $("#depno").val()) {
				otherparams = otherparams + ";depno:" + $("#depno").val();
			}
			</c:if>
			if ("" != $("#leader").val()) {
				otherparams = otherparams + ";leader:" + $("#leader").val();
			}
		}
		/*增加username   模糊查询*/
        var username = $("#username").val();
        if("" != username){
            otherparams += ";susername:"+username;
        }
        var href = document.location.href;
        href = ";meetType:"+href.substring(href.lastIndexOf("&")+10,href.length).toUpperCase();
		var urls = "${pageContext.request.contextPath}/oa/oaArrangeweek!getWorkSummariesJsons.do?otherparams="
				+ encodeURI(encodeURI(otherparams+href));
		$.ajax({
			type : "post",
			url : urls,
			async : false,
			success : function(resp) {
				rendTable(resp);
			}
		});
	}
	function rendTable(datas) {//画表格
		var table = $(".dataArea").find("table");
		table.find("tr").each(function(index) {//删除表格其他的行（表头行除外）
			if (index > 0) {
				$(this).remove();
			}
		});
		var tabelBody = "";
        var myDate = new Date();
        var arr = new Array("周日","周一","周二","周三","周四","周五","周六");
        myDate =  myDate.getMonth() + 1 +"月"+myDate.getDate()+"日"+"<br/>" +arr[myDate.getDay()];
        var color = "#c1d9f3";
		$
				.each(
						datas,
						function(i, data) {
						    var flag = false;
							var value = data.value;
							var id = data.id;
							var size = data.size;
							if(value == myDate){
                                flag = true ;
							}
							if (data.checkempty) {
							    var trvalue ;
							    if(flag){
                                    trvalue = "<tr height=\"50px\" style=\"background: "+color+";\" >";
								}else{
									trvalue = "<tr height=\"50px\" >";
								}
                                trvalue +=
									"<td style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;' width=\"5%\">"
										+ value
										+ "<\/td><td style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;' width=\"4%\">-<\/td><td style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;border-right: 1px solid #cacaca' colspan=\"7\" style=\"color: #786262;\"><\/td><\/tr>";
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
													var tdvalue = "<td style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;'>"
															+ StringUils(arrangeweek.showTimeValue)
															+ "<\/td><td style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;border-right: 1px solid #cacaca'>"
															+ StringUilsType(arrangeweek.itemtype)
															+ "<\/td><td style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;border-right: 1px solid #cacaca'>"
															+ StringUils(arrangeweek.address)
															+ "<\/td><td style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;border-right: 1px solid #cacaca'>"
															+StringUils2(arrangeweek.remark,arrangeweek.colorType,arrangeweek.office_djid)
															+ "<\/td>"
															+"<td style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;border-right: 1px solid #cacaca'>"
															+ StringUils(arrangeweek.attendleaders)
															+ "<\/td>"
															+"<td style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;border-right: 1px solid #cacaca'>"
															+ StringUils(arrangeweek.attendusers)
															+ "<\/td>"
														<c:if test="${'0' eq listType || '2' eq listType}">
															+"<td style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;border-right: 1px solid #cacaca'>"
															+ StringUils(arrangeweek.depname)+"</td>"
														</c:if>
														<c:if test="${'1' eq listType}">
															+"<td class='hd' style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;border-right: 1px solid #cacaca'>"
															+getDealWithHtml(arrangeweek)
															+ "</td>";
														</c:if>	
														<c:if test="${'3' eq listType}">
															+"<td class='hd' style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;border-right: 1px solid #cacaca'>"
															+getDealWithHtml3(arrangeweek)
															+ "</td>";
														</c:if>	
														<c:if test="${'0' eq listType}">
															+"<td class='hd' style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;border-right: 1px solid #cacaca'>"
															+getDealWithHtml0(arrangeweek)
															+ "</td>";
														</c:if>	
														<c:if test="${'2' eq listType}">
															+"<td class='hd' style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;border-right: 1px solid #cacaca'>"
															+getDealWithHtml2(arrangeweek)
															+ "</td>";
														</c:if>

                                                    var   trvalue2 ;
                                                    if(flag){
                                                        trvalue2 = "<tr height=\""+trsize+"\" style=\"background:"+ color+";\" >";
                                                    }else{
                                                        trvalue2 = "<tr height=\""+trsize+"\"  >";
                                                    }
													if (j == 0) {
														tabelBody = tabelBody
																+ trvalue2
																+"<td style='border-bottom: 1px solid #cacaca;border-left: 1px solid #cacaca;' width=\"5%\" rowspan=\""
																+ size + "\">"
																+ value
																+ "<\/td>"
																+ tdvalue
																+ "<\/tr>";
													} else {
														tabelBody = tabelBody
																+ trvalue2
																+ tdvalue
																+ "<\/tr>";
													}
												});
							}
						});
		table.append(tabelBody);
	}

	function exportExcel(otherparams){
        openLoadIng(true);
        setTimeout(function()
        {
            downLoadExcel(otherparams);
            openLoadIng(false);
        }, 1000);
	}

	function downLoadExcel(otherparams){
        if ("" == otherparams) {
            otherparams = "showType:" + $('input[name=showType]:checked').val();
        }
        otherparams = otherparams + ";beginDate:" + $("#beginDate").val()
            + ";listType:" + $("#listType").val();
        if ("0" == $("#listType").val()) {
            otherparams = otherparams + ";curDepno:" + $("#curDepno").val()
                + ";depno:" + $("#depno").val();
            otherparams = otherparams+ ";leader:" + $("#leader").val();
        } else {
            <c:if test="${'0' eq listType}">
            if ("" != $("#depno").val()) {
                otherparams = otherparams + ";depno:" + $("#depno").val();
            }
            </c:if>
            if ("" != $("#leader").val()) {
                otherparams = otherparams + ";leader:" + $("#leader").val();
            }
        }
        var username = $("#username").val();
        if("" != username){
            otherparams += ";susername:"+username;
        }
        var href = document.location.href;
        href = ";meetType:"+href.substring(href.lastIndexOf("&")+10,href.length);
        var urls = "${pageContext.request.contextPath}/oa/oaArrangeweek!exportExcel.do?otherparams="
            + encodeURI(encodeURI(otherparams+href));
        var browser=navigator.appName;
        var b_version=navigator.appVersion;
        var version=b_version.split(";");
        var trim_Version=version[1].replace(/[ ]/g,"");
        //针对ie8   导出
        if (browser=="Microsoft Internet Explorer" && trim_Version=="MSIE8.0") {
            document.write("<form action="+urls+" method=post name=formx1 style='display:none'>");
            document.write("<input type='input' style='display:none' name='pointID' ");
            document.write("</form>");
            document.formx1.submit();
        }else{
            var form = $('<form method="post"  action="' + urls + '">' );
            $('body').append(form);
            form.submit(); //自动提交
        }
	}

	//委领导一周安排可编辑删除
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
	
	//委领导一周安排只可查看不可编辑
	function getDealWithHtml3(arrangeweek) {
		var canUpdated = arrangeweek.canUpdated;
		var canDeleted = arrangeweek.canDeleted;
		var djId = arrangeweek.djId;
		var dealwithHtml = "<a class=\"check_email\" href=\"#\" onclick=\"view(\'"
				+ djId + "\')\">查看<\/a>";
		if ("0" == canUpdated) {
			dealwithHtml = dealwithHtml
					+ "   <a class=\"bianji\" href=\"#\" onclick=\"edit(\'"
					+ djId + "\')\">编辑<\/a>";
		}
		if ("0" == canDeleted) {
			dealwithHtml = dealwithHtml
					+ "   <a class=\"delete_email\" href=\"#\" onclick=\"deleted(\'"
					+ djId + "\')\">删除</a>";
		}
		return dealwithHtml;
	}
	
	//各处室一周安排编辑删除
	function getDealWithHtml0(arrangeweek) {
		var canUpdated = arrangeweek.canUpdated;
		var canDeleted = arrangeweek.canDeleted;
		var djId = arrangeweek.djId;
		var dealwithHtml = "<a class=\"check_email\" href=\"#\" onclick=\"view(\'"
				+ djId + "\')\">查看<\/a>";
			dealwithHtml = dealwithHtml
					+ "   <a class=\"bianji\" href=\"#\" onclick=\"edit(\'"
					+ djId + "\')\">编辑<\/a>";
			dealwithHtml = dealwithHtml
					+ "   <a class=\"delete_email\" href=\"#\" onclick=\"deleted(\'"
					+ djId + "\')\">删除</a>";
		return dealwithHtml;
	}
	
	//委领导一周安排只可查看不可编辑
	function getDealWithHtml2(arrangeweek) {
		var canUpdated = arrangeweek.canUpdated;
		var canDeleted = arrangeweek.canDeleted;
		var djId = arrangeweek.djId;
		var dealwithHtml = "<a class=\"check_email\" href=\"#\" onclick=\"view(\'"
				+ djId + "\')\">查看<\/a>";
		if ("0" == canUpdated) {
			dealwithHtml = dealwithHtml
					+ "   <a class=\"bianji\" href=\"#\" onclick=\"edit(\'"
					+ djId + "\')\">编辑<\/a>";
		}
		if ("0" == canDeleted) {
			dealwithHtml = dealwithHtml
					+ "   <a class=\"delete_email\" href=\"#\" onclick=\"deleted(\'"
					+ djId + "\')\">删除</a>";
		}
		return dealwithHtml;
	}
	
	function StringUils(obj) {
		if ("undefined" == typeof (obj) || null == obj || "null" ==  obj) {
			return "";
		} else if(obj==1){
			return "<font style='color:red'>"+obj+"<\/font>";
		}else {
			return obj;
		}
	}
	function StringUils2(obj,ob,djId) {
		if ("undefined" == typeof (obj) || null == obj || "null" ==  obj) {
			return "";
		} else if(ob=='1'){
			var dealwithHtml = "<a class=\"\" href=\"#\" onclick=\"viewMeet(\'"
				+ djId + "\')\">"+"<div style='color:#0098ff;size:14px;'>"+obj+"<\/div>"+"<\/a>";
			return dealwithHtml
		}else {
			return obj;
		}
	}
	
	function StringUilsType(obj) {
		if ("undefined" == typeof (obj) || null == obj) {
			return "";
		} else {
			if(obj == '1'){
				return "会议";
			}if(obj == '2'){
				return "调研";
			}if(obj == '3'){
				return "检查";
			}if(obj == '4'){
				return "出差";
			}if(obj == '5'){
				return "休假";
			}if(obj == '6'){
				return "其他";
			}
		}
	}

	function AddNew() {
		var url = "${pageContext.request.contextPath}/oa/oaArrangeweek!regiseter.do?popPage=true&listType="
				+ $("#listType").val() + "&meetType=" + $("#meetType").val();
        DialogUtil.open("添加新工作安排", url, 1200, 488);
	}
	function view(djId) {
		var url = "${pageContext.request.contextPath}/oa/oaArrangeweek!view.do?djId="
				+ djId + "&listType=" + $("#listType").val() + "&meetType=" + $("#meetType").val();
        DialogUtil.open("查看工作安排", url, 850, 390);
	}
	function viewMeet(djId) {
		var url = "${pageContext.request.contextPath}/dispatchdoc/incomeDoc!generalOptView.do?djId="
				+ djId;
		var winProps = 'height=800px,width=1400px,directories=false,location=false,top=100,left=250,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
		var winName = '查看会议通知';
		window.open(url, winName, winProps);
	}
	function edit(djId) {
		var url = "${pageContext.request.contextPath}/oa/oaArrangeweek!regiseter.do?popPage=true&djId="
				+ djId + "&listType=" + $("#listType").val() + "&meetType=" + $("#meetType").val();
        DialogUtil.open("编辑工作安排", url, 1200, 508);
	}

	function deleted(djId) {
        DialogUtil
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
						});
	}
	function print(){
		printView(function(LODOP){
			LODOP.PRINT_INIT("发文拟文单打印");
			LODOP.SET_PRINT_PAGESIZE(1, 0, 0, "A4");
			$("th.hc").hide();//隐藏操作标题
			$("td.hd").hide();//隐藏操作内容
			LODOP.ADD_PRINT_HTM(10,0,"100%","100%",$(".dataArea").html());
			$("#hc").show();//显示操作标题
			$("td.hd").show();//显示操作内容
		});
	};
</script>
</html>