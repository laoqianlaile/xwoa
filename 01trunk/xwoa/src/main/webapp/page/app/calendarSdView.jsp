<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人待办事项</title>

<link href="${pageContext.request.contextPath}/themes/css/extremecomponents.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/css/am.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/themes/gray/style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/fullcalendar/fullcalendar.css">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath }/scripts/bootstrap/css/bootstrap.min.css"> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/scripts/My97DatePicker/skin/WdatePicker.css">


</head>
<body>

<c:set var="ajax" value="${pageContext.request.contextPath }/app/calendar!ajax.do?tasktype=${param['tasktype'] }" />



<!-- 个人用户平台调用时不显示 -->
<div style="margin-top: 20px; display: none;">
	<span>选择业务:</span>
	<span id="span_calendar_instance">
		<c:forEach var="value" items="${values }">
			<c:if test="${not ('oaWorkDayManager' eq value.key) }">
				<label style="display: inline;">
					${value.value } <input style="width: 15px;" class="calendar_instance" type="checkbox" <c:if test="${inst[value.key] }">checked='checked'</c:if> value="${value.key }" />
				</label>
			</c:if>
		</c:forEach>
	</span>
</div>

<c:set var="pageTasktype" value="TaskList${param.tasktype }"/>

<div id="calendar" style="width: 40%; height: 50%; float: left;" ></div>

<div style="width: 55%; float:left; margin-left:10px;">

		<ec:table action="app/calendar!view.do" items="todoes" var="todo" 
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:parameter name="day" value="${param.day }" />
			<ec:parameter name="tasktype" value="${param.tasktype }" />
			<ec:parameter name="instance" value="${param.instance }" />
			
			<ec:row>
				<ec:column property="tasktitle" title="标题" style="text-align:center">
					<a href="${pageContext.request.contextPath }/app/taskList!view.do?taskid=${todo.taskid}&view=1&tasktype=${param.tasktype }" title="${todo.tasktitle }">
						<c:choose>
							<c:when test="${10 < fn:length(todo.tasktitle) }">
								${fn:substring(todo.tasktitle, 0, 10) }...
							</c:when>
							<c:otherwise>
								${todo.tasktitle }
							</c:otherwise>
						</c:choose>
					</a>
				</ec:column>

				<ec:column property="taskowner" title="人员" style="text-align:center">
					${cp:MAPVALUE("usercode", todo.taskowner)}
				</ec:column>
				
				<ec:column property="planbegintime" title="开始时间" style="text-align:center" >
					<fmt:formatDate value="${todo.planbegintime }" pattern="yyyy-MM-dd"/>
				</ec:column>
				<ec:column property="planendtime" title="结束时间" style="text-align:center" >
					<fmt:formatDate value="${todo.planendtime }" pattern="yyyy-MM-dd"/>
				</ec:column>
		
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					
					
					<a href='${pageContext.request.contextPath }/app/taskList!view.do?taskid=${todo.taskid}&tasktype=${param.tasktype }&day=<fmt:formatDate value="${todo.planbegintime }" pattern="yyyy-MM-dd"/>'>查看</a>
					
					<c:if test="${cp:CHECKUSEROPTPOWER(pageTasktype, 'edit') }">
						<a href='${pageContext.request.contextPath }/app/taskList!edit.do?taskid=${todo.taskid}&tasktype=${param.tasktype }&day=<fmt:formatDate value="${todo.planbegintime }" pattern="yyyy-MM-dd"/>'>编辑</a>
					</c:if>
					<c:if test="${cp:CHECKUSEROPTPOWER(pageTasktype, 'delete') }">
						<a href='${pageContext.request.contextPath }/app/taskList!delete.do?taskid=${todo.taskid}&tasktype=${param.tasktype }&day=<fmt:formatDate value="${todo.planbegintime }" pattern="yyyy-MM-dd"/>' 
							onclick='return confirm("是否确定删除？");'>删除</a>
					</c:if>
				</ec:column>

			</ec:row>
		</ec:table>
</div>
</body>

<script src="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<%-- <script src="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/jquery/jquery-ui-1.10.2.custom.min.js"></script> --%>
<script src="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/fullcalendar/fullcalendar.js"></script>
<script src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
<script src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>
<script src="${pageContext.request.contextPath }/scripts/sugar-1.3.9.min.js"></script>
<script src="${pageContext.request.contextPath }/scripts/My97DatePicker/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath }/scripts/selectCalendar.js"></script>
<script>

$(function(){
	
var calendar;
var clickHandle;
	
	var options = {
		param : {
			id : 'calendar',
			url :'${ajax }',
			noneTitle : false,
			events : '${ajax }&ac=getCalendar&instance=${param["instance"] }',
			header : {
				left : 'prev,next',
				center : 'title',
				right : ''
			}
		},
		callback : {
			select : function(start, end, allDay){
			
				//如果对话框存在，则先关闭
				isallday = 0;
				//debugger;
				if (clickHandle) {
		            clearTimeout(clickHandle);
		            clickHandle = null;
		            
// 		            <c:if test="${cp:CHECKUSEROPTPOWER(pageTasktype, 'edit') }">
			            window.location = '${contextPath}/app/taskList!edit.do?' + 'day=' + Date.create(start).format('{yyyy}-{MM}-{dd}') + '&start=' + Date.create(start).format('{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}') + '&end=' + Date.create(end).format('{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}') + '&isallday=' + isallday + '&instance=taskListManager&tasktype=${param.tasktype}';
// 			            return;
// 		            </c:if>
		            
		        }

		        clickHandle = setTimeout(function() {
		            clickHandle = null;
					
					
					window.location = '${contextPath}/app/calendar!view.do?' + 'day=' + Date.create(start).format('{yyyy}-{MM}-{dd}') + '&isallday=' + isallday + '&instance=taskListManager&tasktype=${param.tasktype}';
					return;
		            
		        }, 300);

				if(1 == 1) {
					
					return;
				}
				
				//创建对话框
				$.dialog({
					id: 'selectDialog',
					lock: false,
					title: '创建日程',
					content: '<table><tr><td style="width:50px;height:30px;vertical-align:middle">时间：</td><td style="vertical-align:middle">' + content + '</td></tr><tr><td style="height:30px;vertical-align:middle">标题：</td><td style="vertical-align:middle"><input type="text" id="title" style="margin-bottom:0"></td></tr><tr><td></td><td style="height:30px;vertical-align:middle">例如：下午 4 点在 星巴克 喝下午茶</td></tr></table>',
					button: [
						{
							name: '创建',
							callback: function(){
								if($.trim(document.getElementById('title').value) != ''){
									$.ajax({
										type: 'POST',
										url: '${ajax }',
										//data: 'ac=quick&do=add&title=' + document.getElementById('title').value + '&start=' + Date.create(start).format('{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}') + '&end=' + Date.create(end).format('{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}') + '&isallday=' + isallday + '&instance=${param["instance"] }',
										//创建日程只适用于企业工作日历
										data: 'ac=quick&do=add&title=' + document.getElementById('title').value + '&start=' + Date.create(start).format('{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}') + '&end=' + Date.create(end).format('{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}') + '&isallday=' + isallday + '&instance=taskListManager',
										success: function(){
											//添加成功后刷新日历
											calendar.fullCalendar('refetchEvents');
										}
									});
								}else{
									$.dialog.tips('请填写活动标题');
									return false;
								}
							},
							focus: true
						}/* ,
						{
							name: '编辑',
							callback: function(){
								$('#calendar').hide();
								$('#editbox').show();
								//清空表单
								clearEditForm();
								//初始化表单
								$('#editbox input[name="tasktitle"]').val(document.getElementById('title').value);
								$('#editbox input[name="startd"]').val(Date.create(start).format('{yyyy}-{MM}-{dd}'));
								$('#editbox input[name="endd"]').val(Date.create(end).format('{yyyy}-{MM}-{dd}'));
								$('#editbox input[name="startt"]').val(Date.create(start).format('{H}:{m}:{s}'));
								$('#editbox input[name="endt"]').val(Date.create(end).format('{H}:{m}:{s}'));
								if(isallday == 0){
									$('#editbox input[name="isallday"]:eq(1)').click();
								}
								//window.open('${contextPath }' + event.taskCatalog.formUrl + '&instance=' + event.instance, '_self');
							}
						} */
					]
				});
				calendar.fullCalendar('unselect');
			},
			
			
			eventClick :  function(event){
				var start = new Date(event._start), end = new Date(event._end), content = '';
				var startdText = Date.create(start).format('{M}月{dd}日 ') + getMyDay(start.getDay());
				var enddText = Date.create(end).format('{M}月{dd}日 ') + getMyDay(end.getDay());
				var starttText = Date.create(start).format(' {H}:{m}');
				var endtText = Date.create(end).format(' {H}:{m}');
				//debugger;
				if(!event.allDay){
					content += startdText + starttText + '&nbsp;&nbsp;–&nbsp;' + endtText;
				}else{
					if(startdText == enddText){
						content += startdText;
					}else{
						content += startdText + '&nbsp;&nbsp;–&nbsp;&nbsp;' + enddText;
					}
				}
				//console.debug(event);
				
				
				var btnClosure = {
						name: '完成任务',
						callback: function(){
							$.dialog({
								id: 'closureTaskDialog',
								lock: false,
								title: '完成任务',
								content: '确定是否完成任务',
								button: [
									{
										name: '确定',
										callback: function(){
											//ZENG.msgbox.show('正在提交中，请稍后...', 6, 100000);
											$.ajax({
												type: 'POST',
												url: '${ajax }',
												data: 'ac=quick&do=closure&id=' + event._id + '&instance=' + event.instance,
												success: function(){
													//ZENG.msgbox._hide();
													calendar.fullCalendar('removeEvents', event._id);
												}
											});
										},
										focus: true
									},
									{
										name: '取消'
									}
								]
							});
						}
					};
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
											//ZENG.msgbox.show('正在更新中，请稍后...', 6, 100000);
											$.ajax({
												type: 'POST',
												url: '${ajax }',
												data: 'ac=quick&do=del&id=' + event._id + '&instance=' + event.instance,
												success: function(){
													//ZENG.msgbox._hide();
													calendar.fullCalendar('removeEvents', event._id);
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
							window.open('${contextPath }' + event.taskCatalog.formUrl + event.id + '&instance=' + event.instance, '_self');
						},
						disabled: typeof event.taskCatalog.formUrl == 'undefined' ? true : false
					};
				
				//根据权限设置当前可见的按钮
				var button = [];
				if(!event.editable) {
					//button = [btnClosure];
				}else if(typeof event.url == 'undefined') {
					button = [btnDel, btnEditor];
				}else{
					button = [btnDel, btnEditor];
				}
				$.dialog({
					title: event.title,
					content: content,
					width: 350,
					button: button
				});
				return false;
			}
		}
	};
	
	calendar = new SelectCalendar(options).getCalendar();
	//calendar.fullCalendar('month', 1);
	
	<c:if test="${not empty param.day}">
		var currentDay = '${param.day}'.split('-');
		var cmonth = currentDay[1];
		
		cmonth = parseInt(cmonth);
		
		//alert(cmonth);
		
		calendar.fullCalendar('gotoDate', currentDay[0], cmonth - 1);
	</c:if>
	
	
	
	showBackground(calendar);
	
	
	
	$(calendar).resize(function(){
		//parent.initLayout();
		//showBackground(calendar);
		//alert(1);
	});
	
	
	$('#span_calendar_instance :input:checkbox.calendar_instance').change(function() {
		//默认值，显示企业工作日历
		var instances = ['oaWorkDayManager'];
		$('#span_calendar_instance :input:checkbox:checked.calendar_instance').each(function() {
			instances.push($(this).val());
		});
		
		window.location = '${contextPath }/app/calendar!view.do?instance=' + instances.join(',');
	});
	
	
});


var showBackground = function(object) {//debugger;
	var $selectDay = object.find('td.fc-day').filter(function(){
		return $(this).attr('data-date') == '${param.day}';
	});
	
	if(0 == $selectDay.length) {
		return;
	}
	
	
	$selectDay.css({'background' : '#bce8f1'});
	
	/* var top = $selectDay.position().top;
	var left = $selectDay.position().left;
	var width = $selectDay.width();
	var height = $selectDay.height(); */
	
	//console.debug($selectDay);
	//console.debug($selectDay.width() + '  ' + $selectDay.height());
	
	
	/* var showDiv = '<div class="fc-cell-overlay" style="position: absolute; z-index: 3; top: '+top+'px; left: '+left+'px; width: '+(width+1)+'px; height: '+height+'px;"></div>';
	
	$('table.fc-border-separate').next('div.fc-cell-overlay').empty();
	
	$('table.fc-border-separate').after(showDiv); */
	
	
};

/* function clearEditForm(){
	$('#editbox input:text, textarea').val('');
	$('#editbox input[name="startd"], #editbox input[name="endd"]').val('');
	$('#editbox input[name="startt"], #editbox input[name="endt"]').val('').hide();
	$('#editbox input[name="isallday"]:eq(0)').prop('checked', true);
	$('#editbox input[name="isallday"]:eq(1)').prop('checked', false);
} */

/* function setEnabledForm(flag) {
	if(!flag){
		$('#editbox input:text, select, textarea').attr('disabled', 'disabled');
		$('#btn-submit').unbind('click').attr('disabled', 'disabled');
	}
} */
</script>

</html>