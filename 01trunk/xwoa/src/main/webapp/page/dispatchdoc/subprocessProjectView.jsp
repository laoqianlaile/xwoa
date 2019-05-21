<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<title>委托咨询</title>

<script type="text/javascript">
  function openSupInfo(){
		var url = "<%=request.getContextPath()%>/dispatchdoc/ioDocTasksExcute!transaffairView.do?djId=${object.supDjId}&flowInstId=${object.incomeDocTask.flowInstId}&nodeInstId=${object.incomeDocTask.nodeInstId}";
		openNewWindow(url);
  }
</script>
</head>

<body>
	

	<%@ include file="/page/common/messages.jsp"%>


	<p>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewTable">
		
		<tr>
			<td class="addTd"><s:text name="subprocessProject.no" /></td>
			<td align="left"><c:out value="${object.djId}" /></td>
			<td class="addTd">父流程号</td>
			<td align="left"><a href='#' onclick="openSupInfo();"><c:out value="${object.supDjId}" /></a></td>
		</tr>
		 
		<tr>
			<td class="addTd"><s:text name="subprocessProject.headunitcode" /></td>
			<td align="left">${cp:MAPVALUE("unitcode", object.headunitcode)}</td>
		
			<td class="addTd"><s:text name="subprocessProject.accpetDate" /></td>
			<td align="left"><fmt:formatDate value='${object.accpetDate}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
		</tr>
		
		<tr>
			<td class="addTd"><s:text name="subprocessProject.projectUnitName" /></td>
			<td align="left" colspan="3"><c:out value="${object.projectUnitName}" /></td>
		</tr>
		
		<tr>
			<td class="addTd"><s:text name="subprocessProject.projectName" /></td>
			<td align="left" colspan="3"><c:out value="${object.projectName}" /></td>
		</tr>
		
		<tr>
			<td class="addTd"><s:text name="subprocessProject.projectType" /></td>
			<td align="left" colspan="3">${cp:MAPVALUE("PROJECT_TYPE", object.projectType)}</td>
		</tr>
		
		<tr>
			<td class="addTd"><s:text name="subprocessProject.evaluationReason" /></td>
			<td align="left" colspan="3"><c:out value="${object.evaluationReason}" /></td>
		</tr>
		
		<tr>
			<td class="addTd"><s:text name="subprocessProject.evaluationContent" /></td>
			<td align="left" colspan="3"><c:out value="${object.evaluationContent}" /></td>
		</tr>
		
		<tr>
			<td class="addTd"><s:text name="subprocessProject.opinions" /></td>
			<td align="left" colspan="3"><c:out value="${object.opinions}" /></td>
		</tr>
	</table>

	

</body>
</html> 
