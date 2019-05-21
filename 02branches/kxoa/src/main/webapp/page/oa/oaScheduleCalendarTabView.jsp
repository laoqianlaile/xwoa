<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%-- <%@ include file="/page/common/css.jsp"%> --%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日程安排</title>

 <script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/fullcalendar/fullcalendar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/scripts/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/scripts/My97DatePicker/skin/WdatePicker.css"> 
	
<script type="text/javascript" data-main="${pageContext.request.contextPath }/scripts/frame/main_old"  
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>
	
<%--  <script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>	 --%>
	
<link href="${pageContext.request.contextPath}/scripts/frame/components/jquery/jquery.tokeninput/styles/token-input.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/scripts/frame/components/jquery/jquery.tokeninput/styles/token-input-facebook.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/scripts/frame/components/jquery/jquery.tokeninput/styles/token-input-mac.css" rel="stylesheet" type="text/css" /> 
	<script>
	if (!window.Config) {
		window.Centit = {
			contextPath : '${contextPath}'
		};
	}
	</script>
</head>
<body>
<br>
<!-- <div style="position:relative;"> -->
<div style="position:absolute; right:0; ">


	<s:form action="oaSchedule" namespace="/oa"
		style="margin-top:0;margin-bottom:5">
		<input type="hidden" name="s_sehType" value="${s_sehType }"/>
		<input type="hidden" name="s_canAdd" value="${s_canAdd }"/>
		<c:if test="${'2' eq s_sehType}">
                    <table cellpadding="0" cellspacing="0" align="center">
					<tr>
					<td class="addTd">
					领导:
					</td>
					<td>
					<input type="text" class="autocomplete" style="width:200px;"  name="leaders"  data-token-limit='1'  
							      	data-pre-populate='${populate}' 
									data-url="${pageContext.request.contextPath}/oa/oaSchedule!selectUser.do" />
								
					</td>
					<td class="addTd">
					<s:submit method="calendarView" key="opt.btn.query" cssClass="btn" />
					</td>
					</tr>
					</table>
					</c:if>
	     </s:form>
	</div>
	
<%-- 	<c:set var="ajax" --%>
<%-- 		value="${pageContext.request.contextPath }/oa/oaSchedule!ajaxTab.do?s_sehType=${param['s_sehType']}&s_canAdd=${param.s_canAdd}" /> --%>

	
	<div id="calendar"></div>
<!-- 	</div> -->
</body>

 <script
	src="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/jquery/jquery-1.9.1.min.js"
	type="text/javascript"></script>
	<script
	src="${pageContext.request.contextPath }/scripts/fullcalendar-1.6.1/jquery/jquery-migrate-1.2.1.js"
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

// value="${pageContext.request.contextPath }/oa/oaSchedule!ajaxTab.do?s_sehType=${param['s_sehType']}&s_canAdd=${param.s_canAdd}" /> --%>

	$(function() {
		
		var calendar;
		var ajax = "${pageContext.request.contextPath }/oa/oaSchedule!ajaxTab.do?s_sehType=${param['s_sehType']}&s_itemtype=${param['s_itemtype']}&s_canAdd=${param.s_canAdd}&leaders="
				+ encodeURI(encodeURI("${param['leaders']}"));
		var options = {
			param : {
				id : 'calendar',
				url : '${ajax }',
				noneTitle : false,
				disableDragging : true,
				events : ajax//,//param参数怎么传过来'${ajax }'
// 				editable : false,
				
			},
			callback : {
				select : function(start, end) {
					var iTop = (window.screen.availHeight-30-300)/2;       //获得窗口的垂直位置;
					var iLeft = (window.screen.availWidth-10-1000)/2;  
					var option = "height=400,width=1024,left="+iLeft+",top="+iTop+",toolbar=no, menubar=no, scrollbars=no, resizable=no,status=no";
					var url = '${contextPath}/oa/oaSchedule!addOaSchedule.do?'
							+ 'planBegTime='
							+ Date.create(start).format(
									'{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}')
							+ '&planEndTime='
							+ Date.create(end).format(
									'{yyyy}-{MM}-{dd} {HH}:{mm}:{ss}')
							+ '&s_sehType=${param.s_sehType}&s_canAdd=${param.s_canAdd}';
					if ('T' == '${param.s_canAdd}') {
						var yesterday = new Date();
						var date = yesterday.getDate();
						date = date - 1;
						yesterday.setDate(date);
						if (start < yesterday) {
							var flag = confirm("所选时间发生在过去，确定此项操作吗？");
							if (!flag) {
								return flag;
							} else {
								art.dialog
								.open(url,
										 {title: '日程维护', width: 1050, height: 400});
								return false;
							}
						}
						if (end < yesterday) {
							var flag = confirm("所选时间发生在过去，确定此项操作吗？");
							if (!flag) {
								return flag;
							} else {
								art.dialog
								.open(url,
										 {title: '', width: 1050, height: 400});
								return false;
							}
						}
					
						art.dialog
						.open(url,
								 {title: '', width: 1050, height: 400});
						
					}
				},

				eventClick : function(event) {

					 art.dialog.open("${pageContext.request.contextPath}/"+event.url,{title: '日程查看', width: 1050, height: 400});
						
					return false;
				}
			}
		};

		calendar = new SelectCalendar(options).getCalendar();

	});
</script>

</html>