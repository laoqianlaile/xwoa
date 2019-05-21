<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
<%@ include file="/page/common/css.jsp"%>
</head>
<body>
	<div class="fix">
					<i class="i-l r-l"></i>
				<div class="childBlock">
					<div class="childBlockTile">
						<div class="childBlockTile_left">
							<div id="rcap-div"
								class="childBlockTile_left1 document-switch-hover"
								onclick="switchBlock('rcap');">日程安排</div>
							<i></i>
							<div id="tx-div" class="childBlockTile_left1"
								onclick="switchBlock('tx');">提醒</div>
							<i></i>
						</div>
					</div>
					<input type="hidden" id="newtime" value="${newtime }">
					<% String newtime=request.getParameter("newtime"); %>
					<div id="rcap" style="width: 98%;" >
						<iframe id="rcap_table" frameborder="0" height="500px" width="100%" src="<%=request.getContextPath()%>/oa/oaSchedule!addOaSchedule.do?&planBegTime_s=<%=newtime %> 11:00:00&planEndTime_s=<%=newtime %> 20:00:00&s_sehType=1&s_canAdd=T&xzrc_sy=T"></iframe>
					</div>
					 <div id="txs" style="width: 98%;display:none; " >
					<iframe id="tx_table" frameborder="0" height="500px" width="100%" src="<%=request.getContextPath()%>/oa/oaRemindInformation!builtV2.do?xzrc_sy=T"></iframe>
					</div> 
					
					
					
				</div>
				<i class="i-r r-r"></i>
				</div>

</body>
<script type="text/javascript">

function switchBlock(id){
	if(id=="rcap"){
    	 $("#rcap").show();
		$("#txs").hide(); 
		$("#tx-div").removeClass("document-switch-hover");
		$("#rcap-div").addClass("document-switch-hover");
	}
	else if(id=="tx"){
		 $("#txs").show();
		$("#rcap").hide(); 
		$("#rcap-div").removeClass("document-switch-hover");
		$("#tx-div").addClass("document-switch-hover");
	}
}

</script>
</html>