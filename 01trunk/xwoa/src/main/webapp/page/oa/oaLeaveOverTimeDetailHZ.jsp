<%@ page contentType="text/html; charset=UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<%@ include file="/page/common/taglibs.jsp"%>
<html>
<head>

<style type="text/css">
.table {
	border-right: 1px solid #CACACA;
	font-size: 12px;
	line-height: 16px;
	padding: 5px;
	vertical-align: middle;
}
</style>


<title>人事考勤统计表样表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/themes/bootstrap3/css/bootstrap.min.css">
</head>
<body>
	<%@ include file="/page/common/messages.jsp"%>
		<div class="row tool-bar">
	    <div class="col-xs-12">
					<button type="button" class="btn btn-success btn-sm excel" style="float: right" title="导出Excel文件" onclick="window.location.href='oaLeaveOverTime!editDraft3.do?selUnit=${param.selUnit}&&type=D&&ReasonType=${ReasonType}&&auditDate=${auditDate}'">
			导出
		</button>
	    </div>
        </div>
        <div style="overflow-x:auto;overflow-y:hidden">
	   <table class="table table-bordered table-striped table-hover table-condensed">
      <tr>
      <td align="center" style="font-weight:bold;">姓名</td>
      <td style="font-weight:bold;" >类型</td>
      <td style="font-weight:bold;">时间</td>
      <td style="font-weight:bold;">已休</td>
      </tr> 
		<c:forEach items = "${objList}" var = "obj" varStatus="count">
		<c:forEach items = "${obj.oaLeaveOverTime}" var = "ob" varStatus="index">
		<tr>
 		 <c:if test="${index.count eq 1}"><td rowspan="${rolnum}" style="text-align:center;width:20%;">${cp:MAPVALUE('usercode',obj.usercode) }</td></c:if>  
	     <td >${cp:MAPVALUE('LeaveOvertime',ob.reasonType)}</td>
	     <td >${ob.duringDates}</td>
	     <td >${ob.num}天</td></tr>
	     </c:forEach>
        </c:forEach>
        <tr><td align="center" style="font-weight:bold;">总计</td><td ></td><td ></td><td>${nums}天</td></tr>
        
	</table> 
	</div>
</body>

</html>