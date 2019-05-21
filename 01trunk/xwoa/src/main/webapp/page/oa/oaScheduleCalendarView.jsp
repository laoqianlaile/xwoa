<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%-- <%@ include file="/page/common/css.jsp"%> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/sky/style.css"
	rel="stylesheet" type="text/css" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <%-- <sj:head locale="zh_CN" /> --%> --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人待办事项</title>

<link
	href="${pageContext.request.contextPath}/themes/css/extremecomponents.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/css/am.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/gray/style.css"
	rel="stylesheet" type="text/css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/fullcalendar/fullcalendar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/scripts/My97DatePicker/skin/WdatePicker.css">
<script
	src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>
	<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>				
</head>
<style type="text/css">
.navPoint {
	COLOR: black;
	CURSOR: hand;
	FONT-FAMILY: Webdings;
	FONT-SIZE: 15pt
}

.a2 {
	BACKGROUND-COLOR: A4B6D7;
}

a ,a.hover{
  text-decoration:none !important;
  cursor:pointer !important;
}
</style>

<script>
	function switchSysBar(num) {

		if (num == 4) {
			$('#left').show();
			$('#right').hide();
			$('#left').attr("style", "width: 100%;height: 100%;");
			// 	$('span.fc-button-today').click();
			calendar.fullCalendar('render');
			// 	$(".selector").fullCalendar('today');

			//     $('#switchPointL').text(3);
			//     $('#switchPointL').attr("onClick","switchSysBar(3)");
		} else if (num == 3) {
			$('#right').show();
			$('#left').hide();
			// 	$('#switchPoint').hide();
			// 	$('#switchPointR').text(4);
			//     $('#switchPointR').attr("onClick","switchSysBar(4)");
		} else if (num == 0) {
			$('#left').show();
			$('#right').show();
			$('#left').attr("style", "width: 40%");
			calendar.fullCalendar('render');
		}

	}
</script>
<body>

	<%-- 	<c:set var="ajax" --%>
	<%-- 		value="${pageContext.request.contextPath }/oa/oaSchedule!ajax.do?s_sehType=${param['s_sehType']}&s_itemtype=${param['s_itemtype']}&s_planBeginTimeBegin=${param['s_planBeginTimeBegin']}&s_planBeginTimeEnd=${param['s_planBeginTimeEnd']}&s_title=${param['s_title']}" /> --%>
	<table border="0" cellPadding="0" cellSpacing="0" height="100%"
		width="100%">
		<tr>
			<td style="width: 40%; height: 100%;" id="left">
				<div style="margin-top: 20px;">
					<span>业务类型:</span> <span id="span_calendar_instance"> <c:forEach
							var="value" items="${values }">
							<label style="display: inline;"> ${value.value } </label>

						</c:forEach>
					</span>
				</div>

				<div id="calendar" style="width: 100%; height: 100%; float: left;"></div>
			</td>
			<!--同志们请注意下面这个TD，它的颜色就是中间跑来跑去分栏部分的颜色，你可以在这里将颜色改成与你页面融洽的颜色-->
			<td width="46" bgcolor="#DFC5A4" style="WIDTH: 9pt">
				<!--哈哈，看到了吧，中间的那个跑来跑去的栏实际上去一个TABLE哦！TABle的宽度就是那条栏的宽度-->
				<table width="9" height="100%" border="0" cellpadding="0"
					cellspacing="0">
					<tr height="30%">
						<td width="200" height="100%" class="navPoint" id="switchPointL"
							title="关闭左侧左栏" onClick="switchSysBar(3)">3</td>
					</tr>

					<tr height="30%">
						<td width="200" height="100%" class="navPoint" id="switchPoint"
							title="双栏" onClick="switchSysBar(0)">0</td>
					</tr>
					<tr height="30%">
						<td width="200" height="100%" class="navPoint" id="switchPointR"
							title="关闭右侧左栏" onClick="switchSysBar(4)">4</td>
					</tr>
					<tr height="10%">
						<td width="200" height="100%"><font style="color: black;">屏幕切换
						</font></td>
					</tr>
				</table>
			</td>
			<td style="WIDTH: 100%; vertical-align: top;" id="right">
				<div style="width: 100%; float: left; margin-left: 10px;">
 					<fieldset>			
 						<legend>
				 			<s:text name="label.list.filter" />
						</legend>			
					<s:form action="oaSchedule" namespace="/oa"
						style="margin-top:0;margin-bottom:5">
						<table cellpadding="0" cellspacing="0" align="center">
							<s:hidden id="s_sehType" name="s_sehType" value="%{s_sehType}"></s:hidden>
							<s:hidden id="hid_ids" name="ids" />

							<tr>
								<td>时间:</td>
								<td><input type="text" class="Wdate"
									style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
									value="${s_planBeginTimeBegin}" id="s_planBeginTimeBegin"
									name="s_planBeginTimeBegin"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
									placeholder="选择日期" /> &nbsp;&nbsp;至&nbsp;&nbsp; <input
									type="text" class="Wdate"
									style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
									value="${s_planBeginTimeEnd}" id="s_planBeginTimeEnd"
									name="s_planBeginTimeEnd"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
									placeholder="选择日期" /></td>
							</tr>

							<tr>
								<td><s:text name="oaSchedule.title" />:</td>
								<td><input type="text" name="s_title" id="s_title"
									value="${s_title}"> <%-- 					<s:textfield name="s_title" id="s_title"  /> --%>
								</td>
							</tr>
							<c:if test="${2 eq s_sehType }">
								<tr>
									<td>领导:</td>
									<td><input type="text" name="s_leaders" id="s_leaders"
										value="${s_leaders}"> <%-- 					<s:textfield name="s_leaders" id="s_leaders"  /> --%>
									</td>
								</tr>
							</c:if>

							<tr>
								<td><s:text name="oaSchedule.itemtype" />:</td>
								<td><select id="s_itemtype"
									name="s_itemtype"  style="width: 120px;">
										<option value="">---请选择---</option>
										<c:forEach var="row" items="${cp:DICTIONARY('oaItemType')}">
											<option value="${row.datacode}"
												<c:if test="${row.datacode==s_itemtype}"> selected="selected"</c:if>>
												<c:out value="${row.datavalue}" />
											</option>
										</c:forEach>
								</select></td>
							</tr>

							<tr>
								<td><s:submit method="list" key="opt.btn.query"
										cssClass="btn" /></td>
								<td><s:submit method="built" key="opt.btn.new"
										cssClass="btn" /> <c:if test="${not empty objList }">
										<s:submit method="deleteIds"
											onclick="return confirm('是否确定批量删除？')"
											id="a_list_ids_delete" cssClass="btn  btnDelete" value="批量删除"></s:submit>
									</c:if></td>
							</tr>
						</table>
					</s:form>
										
					</fieldset>
					<ec:table action="oa/oaSchedule!list.do" items="objList"
						var="oaSchedule" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
						retrieveRowsCallback="limit">
						<ec:row>
							<ec:column property="schno"
							title='全选<input id="chk_all" type="checkbox"  value="全选" name="quanxuan"  />'
							sortable="false" width="2%">
							<c:if
									test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode==oaSchedule.creater and '1' eq oaSchedule.sehType  or '2' eq oaSchedule.sehType }">
							<input class="chk_one" type="checkbox" id="${oaSchedule.schno}"
								class="ecbox" value="${oaSchedule.schno}">
							</c:if> &nbsp;
	                        </ec:column>
							<%-- 			<c:set var="tschno"> --%>
							<%-- 				<s:text name='oaSchedule.schno' /> --%>
							<%-- 			</c:set> --%>
							<%-- 			<ec:column property="schno" title="${tschno}" --%>
							<%-- 				style="text-align:center" /> --%>
							<%--
	         <c:set var="titemtype">
				<s:text name='oaSchedule.itemtype' />
			</c:set>
			<ec:column property="itemtype" title="${titemtype}"
				style="text-align:center" >
				${cp:MAPVALUE('oaItemType',oaSchedule.itemtype)}
				</ec:column>


			--%>
							<%--<c:set var="tplanBegTime">
				<s:text name='oaSchedule.planBegTime' />
			</c:set>
			--%>
							<ec:column property="planBegTime" title="开始时间"
								style="text-align:center">
								<fmt:formatDate value='${oaSchedule.planBegTime}'
									pattern='yyyy-MM-dd  HH:mm' />
							</ec:column>

							<%--<c:set var="tplanEndTime">
				<s:text name='oaSchedule.planEndTime' />
			</c:set>
			--%>
							<%-- <ec:column property="planEndTime" title="结束时间"
								style="text-align:center">
								<fmt:formatDate value='${oaSchedule.planEndTime}'
									pattern='yyyy-MM-dd  HH:mm' />
							</ec:column> --%>
							<c:set var="ttitle">
								<s:text name='oaSchedule.title' />
							</c:set>
							<ec:column property="title" title="${ttitle}"
								style="text-align:center">
				[${cp:MAPVALUE('oaItemType',oaSchedule.itemtype)}]${oaSchedule.title}
			</ec:column>
							<%--	
			<c:set var="taddress">
				<s:text name='oaSchedule.address' />
			</c:set>
			<ec:column property="address" title="${taddress}"
				style="text-align:center" />	
				--%>
							<%--
			<c:set var="tremark">
				<s:text name='oaSchedule.remark' />
			</c:set>
			<ec:column property="remark" title="${tremark}"
				style="text-align:center" />--%>



							<c:set var="optlabel">
								<s:text name="opt.btn.collection" />
							</c:set>
							<ec:column property="opt" title="${optlabel}" sortable="false"
								style="text-align:center">
								<c:set var="deletecofirm">
									<s:text name="label.delete.confirm" />
								</c:set>
								<a
									href='oa/oaSchedule!view.do?schno=${oaSchedule.schno}&ec_p=${ec_p}&ec_crd=${ec_crd}&s_sehType=${s_sehType}'><s:text
										name="opt.btn.view" /></a>

								<!-- 							编辑自己的日程安排（个人），领导部分只能在领导菜单下编辑 -->

								<c:if
									test="${session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode==oaSchedule.creater and '1' eq oaSchedule.sehType}">
									<a
										href='oa/oaSchedule!edit.do?schno=${oaSchedule.schno}&s_sehType=${s_sehType}'><s:text
											name="opt.btn.edit" /></a>
									<a
										href='oa/oaSchedule!delete.do?schno=${oaSchedule.schno}&s_sehType=${s_sehType}'
										onclick='return confirm("${deletecofirm}?");'><s:text
											name="opt.btn.delete" /></a>
								</c:if>
								<c:if test="${'2' eq oaSchedule.sehType}">
									<a
										href='oa/oaSchedule!edit.do?schno=${oaSchedule.schno}&s_sehType=${s_sehType}'><s:text
											name="opt.btn.edit" /></a>
									<a
										href='oa/oaSchedule!delete.do?schno=${oaSchedule.schno}&s_sehType=${s_sehType}'
										onclick='return confirm("${deletecofirm}?");'><s:text
											name="opt.btn.delete" /></a>
								</c:if>

							</ec:column>

						</ec:row>
					</ec:table>					
 				</div> 
			</td>
		</tr>
	</table>
		
</body>
<script
	src="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/jquery/jquery-1.9.1.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/jquery/jquery-ui-1.10.2.custom.min.js"></script>
<script
	src="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/fullcalendar/fullcalendar.js"></script>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
<script
	src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>
<script
	src="${pageContext.request.contextPath }/scripts/sugar-1.3.9.min.js"></script>
<script
	src="${pageContext.request.contextPath }/scripts/My97DatePicker/WdatePicker.js"></script>
<script
	src="${pageContext.request.contextPath }/scripts/selectCalendar.js"></script>
<script>

var LISTMAIL = {
		init : function() {
			for ( var e in LISTMAIL) {
				if ('init' != e && $.isFunction(LISTMAIL[e])) {
					LISTMAIL[e]();
				}
			}
		},
	initCheckbox : function() {
		$('#chk_all').change(
				function() {
					var $checked = $(this).prop('checked');
					$.each($('input:checkbox.chk_one'), function(index,
							checkbox) {
						$(this).prop('checked', $checked);
						if ($checked) {
							$(this).parent('span').addClass('checked');
						} else {
							$(this).parent('span')
									.removeClass('checked');
						}
					});
				});
	},

	bindCheckboxClick : function() {
		disabledBtn($('#a_list_ids_delete'), true);
		$('#chk_all, input:checkbox.chk_one').change(function() {
			var msgcodes = LISTMAIL.getSelected();
			$('#hid_ids').val(msgcodes.join(','));
			if (0 < msgcodes.length) {

				disabledBtn($('#a_list_ids_delete'), false);
			} else {
				disabledBtn($('#a_list_ids_delete'), true);
			}
		});
	},

	getSelected : function() {
		var msgcodes = [];
		$.each($('input:checkbox.chk_one:checked'), function(index,
				checkbox) {
			msgcodes.push($(this).val());
		});

		return msgcodes;
	}
};

var disabledBtn = function($btn, disabled) {
	if (disabled) {
		$btn.addClass('disabled');
		$btn.removeClass('btn-danger');
		$btn.hide();

	} else {
		$btn.removeClass('disabled');
		$btn.addClass('btn-danger');
		$btn.show();
	}

};
$(document).ready(function() {
LISTMAIL.init();
});



	var calendar;
	var ajax = "${pageContext.request.contextPath }/oa/oaSchedule!ajax.do?s_sehType=${param['s_sehType']}&s_itemtype=${param['s_itemtype']}&s_planBeginTimeBegin=${param['s_planBeginTimeBegin']}&s_planBeginTimeEnd=${param['s_planBeginTimeEnd']}&s_title="
			+ encodeURI(encodeURI("${param['s_title']}"))
			+ "&s_leaders="
			+ encodeURI(encodeURI("${param['s_leaders']}"));

	$(function() {

		var options = {
			param : {
				id : 'calendar',
				url : '${ajax }',
				noneTitle : false,
				disableDragging : true,

				events : ajax,//param参数怎么传过来
				editable : false,

				header : {
					left : 'today',
					center : 'prev,title,next',
// 					right : 'month,agendaWeek,agendaDay'
                     right : 'month'

				},
				timeFormat : { // for event elements

					'' : 'MM-dd HH:mm' // default
				}
			},
			callback : {
				select : function(start, end) {
					if (start <= new Date()) {
						alert("所选时间发生在过去，确定此项操作吗？");
						return;
					}
					if (end < new Date()) {
						alert("所选时间发生在过去，确定此项操作吗？");
						return;
					}
					url= '${contextPath}/oa/oaSchedule!addNew.do?'+ 'planBegTime='
						+ Date.create(start).format('{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}')
						+ '&planEndTime='
						+ Date.create(end).format(	'{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}')
						+ '&s_sehType=${param.s_sehType}';
					art.dialog
					.open(url,
							 {title: '车辆申请', width: 1050, height: 800});
					/* window.location = '${contextPath}/oa/oaSchedule!addNew.do?'
							+ 'planBegTime='
							+ Date.create(start).format(
									'{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}')
							+ '&planEndTime='
							+ Date.create(end).format(
									'{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}')
							+ '&s_sehType=${param.s_sehType}'; */
					return;
				},

				eventClick : function(event) {

					$.dialog({
						title : event.title,
						content : content,
						width : 350,
						button : button
					});
					return false;
				}
			}
		};

		calendar = new SelectCalendar(options).getCalendar();

	});

</script>

</html>