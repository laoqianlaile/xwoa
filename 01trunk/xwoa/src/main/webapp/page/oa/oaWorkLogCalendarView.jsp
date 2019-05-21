<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作日志</title>
<style type="text/css">
.themeList li {
	float: left;
	padding: 0 3px;
	list-style: none;
	cursor: default
}
</style>
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/fullcalendar/fullcalendar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/scripts/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/scripts/My97DatePicker/skin/WdatePicker.css">

</head>
<body>
<fieldset  class="search">
	<s:form action="oaWorkLog" namespace="/oa"
		style="margin-top:0;margin-bottom:5">
		</br>
                    <table cellpadding="0" cellspacing="0" align="center">
					<tr>
					<td class="addTd">
					<s:text name="oaWorkLog.title" />：
					</td>
					<td>
					<s:textfield
							name="s_title" value="%{#parameters['s_title']}"/>
					</td>
				
					<td class="addTd">&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="oaWorkLog.infoType" />：
					<select
						id="s_infoType" name="s_infoType"
						style="width: 120px;">
							<option value="">全部</option>
							<c:forEach var="row" items="${cp:DICTIONARY('OAInfoType')}">
								<option value="${row.datacode}"
									<c:if test="${row.datacode==s_infoType}"> selected="selected"</c:if>>
									<c:out value="${row.datavalue}" />
								</option>
							</c:forEach>
					</select>
					</td>
					<td class="addTd">${owner}
					<input type="checkbox" id="s_owner"
						name="s_owner" value="true"
						<c:if test="${s_owner=='true' }"> checked="checked" </c:if>>
						只看自己&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td class="addTd">
					<s:submit method="calendarList" key="opt.btn.query" cssClass="btn" style="margin-bottom:10px;"/>
					</td>
					</tr>
					</table>
		
	</s:form>
	</fieldset>
	<div style="margin-top: 20px;">
			<span id="span_calendar_instance">
				<ul class="themeList" id="themeList">
					<!-- <li>业务类型:工作日志 </li> -->
					<li>颜色说明：</li>
					<li>个人:</li>
					<li style="background-color: #fdfd95; width: 10px; height: 15px;"></li>
					<li>工作:</li>
					<li style="background-color: #9fda9f; width: 10px; height: 15px;"></li>
<!-- 					<li>共享:</li> -->
<!-- 					<li style="background-color: #e6c2e6; width: 10px; height: 15px;"></li> -->
						</ul>
			</span>
	</div>
	<%-- 	<s:form action="oaMeetApply" method="post" namespace="/oa" --%>
	<%-- 		id="oaMeetApplyForm" > --%>

	<!-- 		<table border="0" cellpadding="0" cellspacing="0" class="viewTable"> -->
	<!-- 		<tr> -->
	<!-- 				<td class="addTd">会议室 -->
	<!-- 				</td> -->

	<!-- 				<td align="left"> <select -->
	<!-- 					id="meetingNo" name="meetingNo" class="combox"> -->
	<%-- 						<c:forEach var="dt" items="${oaMeetinfolist}"> --%>
	<%-- 							<option value="${dt.djid}" --%>
	<%-- 								<c:if test="${dt.djid==meetingNo}"> selected="selected"</c:if>>${dt.meetingName --%>
	<%-- 								}</option> --%>
	<%-- 						</c:forEach> --%>
	<!-- 				</select></td> -->
	<!-- 		</tr> -->
	<!-- 			</table> -->
	<%-- 	</s:form> --%>

	<div id="calendar"></div>
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
	$(function() {
		var calendar;
		//中文参数
		var ajax = "${pageContext.request.contextPath }/oa/oaWorkLog!ajax.do?infoType=${param['s_infoType']}&title="
				+ encodeURI(encodeURI("${param['s_title']}"))
				+ "&s_owner=${param['s_owner']}";
		var options = {
			param : {
				id : 'calendar',
				url : '${ajax }',
				noneTitle : false,
				disableDragging : true,
				allDayDefault : true,//全天日程
				weekMode:'liquid',
				events : ajax,//param参数怎么传过来
				editable : false,
				timeFormat : { 
					'' : 'MM-dd HH:mm' // default
				}
			},
			callback : {
				select : function(start, end) {
					//如果对话框存在，则先关闭
					if($.dialog.list['selectDialog'] != null){
						$.dialog.list['selectDialog'].close();
					}
					if (Date.create(start).format('{yyyy}-{MM}-{dd}')>Date.create(new Date()).format('{yyyy}-{MM}-{dd}') 
								&& Date.create(end).format('{yyyy}-{MM}-{dd}')> Date.create(new Date()).format('{yyyy}-{MM}-{dd}') ) {
							alert("只能创建当天或以前的日志，请重新选择时间！");
							return;
						}
						else {
							window.location = '${contextPath}/oa/oaWorkLog!addNew.do?no=&releaseDate='+Date.create(start).format('{yyyy}-{MM}-{dd}');
							return;
						}
					
// 					if (Date.create(start).format('{yyyy}-{MM}-{dd}') ==Date.create(new Date()).format('{yyyy}-{MM}-{dd}') 
// 							&& Date.create(end).format('{yyyy}-{MM}-{dd}') == Date.create(new Date()).format('{yyyy}-{MM}-{dd}') ) {
// 						window.location = '${contextPath}/oa/oaWorkLog!addNew.do?no=&releaseDate='+Date.create(start).format('{yyyy}-{MM}-{dd}');
// 						return;
// 					}
// 					else {
// 						alert("只能创建当天日志，请重新选择时间！");
// 						return;
// 					}

					calendar.fullCalendar('unselect');
				},
				eventClick : function(event) {
					var btnDel = {
							name: '删除',
							disabled : !event.editable,
							callback: function(){
								$.dialog({
									id: 'selectDialog',
									lock: false,
									title: '删除日程',
									content: '删除之后将无法恢复',
									button: [
										{
											name: '确定',
											callback: function(){
// 												ZENG.msgbox.show('正在更新中，请稍后...', 6, 100000);
												$.ajax({
													type: 'POST',
													url: '${pageContext.request.contextPath }/oa/oaWorkLog!delecte.do',
													data: 'no=' + event._id ,
													success: function(){
// 														ZENG.msgbox._hide();
														calendar.fullCalendar('removeEvents', event.id);
													}
												});
											},
											focus: true
										},
										{
											name: '取消',
											callback: function(){
												
											}
										}
									]
								});
								
							}
						};
					var btnEditor = {
							name: '编辑',
							callback: function(){
								window.location ='${contextPath }/oa/oaWorkLog!edit.do?no=' + event.id ;
							},
							disabled: typeof event.id == 'undefined' ? true : false
						};
					var btnView = {
							name: '查看',
							callback: function(){
								window.location ='${contextPath }/oa/oaWorkLog!view.do?no=' + event.id ;
							},
							disabled: typeof event.id == 'undefined' ? true : false
						};
					
					
					//根据权限设置当前可见的按钮
					var button = [];
					if(!event.editable) {
						window.location ='${contextPath }/oa/oaWorkLog!view.do?no=' + event.id ;
					}else{
						button = [btnView,btnEditor,btnDel];
						$.dialog({
// 							closeOnEscape :true,//为true的时候，点击键盘ESC键关闭dialog，默认为true；
							title : event.title,
							content : event.content,
							width : 350,
							button : button
						});
					}
					
					
					
					
					
					
					
					return false;
				}
			}
		};

		calendar = new SelectCalendar(options).getCalendar();

	});
</script>

</html>