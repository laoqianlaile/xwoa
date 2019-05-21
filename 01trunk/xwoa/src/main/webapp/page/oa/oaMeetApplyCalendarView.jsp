<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议室安排</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/fullcalendar/fullcalendar.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/scripts/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/scripts/My97DatePicker/skin/WdatePicker.css">
<style type="text/css"> 
 .themeList li { float:left; padding:0 3px; list-style:none;cursor:default}
</style>
</head>
<body>

<c:set var="ajax" value="${pageContext.request.contextPath }/oa/oaMeetApply!ajax.do?meetingNo=${param['meetingNo'] }" />

<div style="margin-top: 20px;">
<%-- 	<span>业务类型:${value.value }</span> --%>
	<span id="span_calendar_instance">
		<c:forEach var="value" items="${values }">
				<label style="display: inline;">
<%-- 					${value.value }------[颜色说明：申请  黄色;调配  绿色;使用  紫色。] --%>
	
					<ul class="themeList" id="themeList">
					<li>颜色说明： </li>
					<!-- <li >申请:</li>
					<li  style=" background-color: #fdfd95; width: 15px;height: 15px; "></li>
					<li >安排:</li>
					<li  style=" background-color: #9fda9f; width: 15px;height: 15px; "></li> -->
					<!-- <li >暂存:</li>
					<li style=" background-color: #E77236;; width: 15px;height: 15px; "></li> -->
					<li >申请:</li>
					<li  style=" background-color: #fdfd95; width: 15px;height: 15px; "></li>
					<li >安排:</li>
					<li  style=" background-color: #9fda9f; width: 15px;height: 15px; "></li>
					<!-- <li >其他:</li>
					<li  style=" background-color: #ade2fc; width: 15px;height: 15px; "></li> -->
				<!-- 	<li >使用:</li>
					<li style=" background-color: #e6c2e6; width: 15px;height: 15px; "></li> -->
                   </ul>
				</label>
		
		</c:forEach>
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
			
<div id="calendar" ></div>
</body>

<script src="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/jquery/jquery-ui-1.10.2.custom.min.js"></script>
<script src="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/fullcalendar/fullcalendar.js"></script>
<script src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/jquery.artDialog.js?skin=default"></script>
<script src="${pageContext.request.contextPath }/scripts/artDialog4.1.7/plugins/iframeTools.js"></script>
<script src="${pageContext.request.contextPath }/scripts/sugar-1.3.9.min.js"></script>
<script src="${pageContext.request.contextPath }/scripts/My97DatePicker/WdatePicker.js"></script>
<script src="${pageContext.request.contextPath }/scripts/selectCalendar.js"></script>
<script>
$(function(){

	
	
	
	
var calendar;
	
	var options = {
		param : {
			id : 'calendar',
			url :'${ajax }',
			noneTitle : false,
			disableDragging: true,
		
			events : '${ajax }',//param参数怎么传过来
			editable: false//,
			
				/* timeFormat: { // for event elements
					
					'': 'MM-dd HH:mm' // default
				} */
		},
		callback : {
			select : function(start, end){
			var yesterday = new Date();
			var date = yesterday.getDate();
			date = date - 1;
			yesterday.setDate(date);	
			if(start <=yesterday){
				alert("所选时间发生在过去，请选择其他时间段！");
				return;
			}
			if(end <yesterday){
				alert("所选时间发生在过去，请选择其他时间段！");
				return;
			}
			url='${contextPath}/oa/oaMeetApply!addNew.do?' + 'planBegTime=' + Date.create(start).format('{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}') + '&planEndTime=' + Date.create(end).format('{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}')  + '&meetingNo=${param.meetingNo}';
			art.dialog
			.open(url,
					 {title: '会议申请', width: 1050, height: 800});
// 			window.location = '${contextPath}/oa/oaMeetApply!addNew.do?' + 'planBegTime=' + Date.create(start).format('{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}') + '&planEndTime=' + Date.create(end).format('{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}')  + '&meetingNo=${param.meetingNo}';
 			return;
			},
			
			
			
			
			
			eventClick :  function(event){
				
				 $.dialog({
					title: event.title,
					content: content,
					width: 350,
					button: button
				}); 
// 				art.dialog.open("${pageContext.request.contextPath}/"+event.url,{title: '会议查看', width: 1050, height: 800});
				return false;
			}
		}
	};
	
	calendar = new SelectCalendar(options).getCalendar();
	
});

</script>

</html>