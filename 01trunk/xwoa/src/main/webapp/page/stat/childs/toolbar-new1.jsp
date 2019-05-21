<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<div class="row tool-bar">
	<div class="col-xs-12">
<!-- 		<div class="btn-group btn-group-sm size"> -->
<%-- 		  <button class="btn btn-default btn-sm ${thLargeActive }" title="舒展"><i class="glyphicon glyphicon-th-large"></i></button> --%>
<%-- 		  <button class="btn btn-default btn-sm ${thSmallActive }" title="紧凑"><i class="glyphicon glyphicon-th"></i></button> --%>
<!-- 		</div> -->
		
		<button type="button" class="btn btn-success btn-sm excel" style="float: right" title="导出Excel文件" onclick="exportTableToExcel()">
			导出考勤表
		</button>
		
		<form method="post" id="dataForm" action="../oa/oaLeaveOverTime!doExportByModel.do?auditDate=${auditDate}&selUnitcode=${param.selUnitcode}&f=1">
			<input name="formName" id="formName" value="${formName }" type="hidden" ></input>
			<input name="exportData" id="exportData" type="hidden" ></input>
		</form>
	</div>
</div>