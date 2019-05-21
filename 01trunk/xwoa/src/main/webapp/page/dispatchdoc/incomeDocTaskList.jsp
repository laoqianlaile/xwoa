<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title><s:text name="incomeDocTask.list.title" /></title>
</head>
<style>
td.tableHeader{word-break: keep-all; white-space: nowrap;}
</style>

<body>
   <%@ include file="/page/common/messages.jsp"%>
	<fieldset>
	
		<legend>收文待办查询</legend>

		<s:form action="ioDocTasks" namespace="/dispatchdoc">
			<input type="hidden" id="flowCode" name="s_flowCode" value="${flowCode}" />
			<input type="hidden" id="flowCode1" name="flowCode" value="${flowCode}" />
			<table cellpadding="0" cellspacing="0" align="left">
				<tr>
				    <td class="addTd">登记号:</td>
					<td width="180"><s:textfield id="s_transAffairNo" name="s_transAffairNo" value="" style="width:180px" /> </td>
					<td class="addTd">项目名称:</td>
					<td width="180"><s:textfield id="s_transaffairname" name="s_transaffairname" value="" style="width:180px"/></td>
					<td><s:submit method="listIncomeDocTasks" key="opt.btn.query" cssClass="btn"/>&nbsp;</td>
				</tr>	
            </table>
        </s:form>
	</fieldset>

	<ec:table action="dispatchdoc/ioDocTasks!listIncomeDocTasks.do" items="incomeDocList" var="userTask"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:exportXls fileName="dispatchDocTasks.xls" ></ec:exportXls>
			<ec:exportPdf fileName="dispatchDocTasks.pdf" headerColor="blue" headerBackgroundColor="white"></ec:exportPdf>
			<ec:row>

				<ec:column property="transAffairNo" title="登记号" style="text-align:center" />

				<ec:column property="transaffairname" title="项目名称" style="text-align:left">
					<c:if test="${not empty userTask.timeLimit and userTask.timeLimit<0}"><img alt="环节已超期" src="${pageContext.request.contextPath}/styles/images/warning/hjcq.png"></c:if>
					<c:if test="${not empty userTask.timeLimit and userTask.timeLimit>0 and userTask.timeLimit<240}"><img alt="环节即将超期" src="${pageContext.request.contextPath}/styles/images/warning/hjyj.png"></c:if>
					<c:if test="${userTask.timeLimit == 0 or userTask.timeLimit >= 240}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
					<a href='<%=request.getContextPath()%>/dispatchdoc/ioDocTasksExcute!transaffairView.do?djId=${userTask.flowOptTag}&flowInstId=${userTask.flowInstId}&nodeInstId=${userTask.nodeInstId}'>${userTask.transaffairname}</a>
				</ec:column>
				
				<ec:column property="powername" title="权力名称" style="text-align:left" />
				
				<ec:column property="headUnitCode" title="主办处室" style="text-align:left；">
					${cp:MAPVALUE("unitcode",userTask.headunitcode)}
				</ec:column>
				
				<ec:column property="nodeCreateTime" title="更新时间" style="text-align:center" format="yyyy-MM-dd HH:mm:ss" cell="date">
					<c:if test="${!empty userTask.lastUpdateTime}"><fmt:formatDate value="${userTask.lastUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></c:if>
					<c:if test="${empty userTask.lastUpdateTime}"><fmt:formatDate value="${userTask.nodeCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></c:if>
				</ec:column>

				<ec:column property="inststate" title="流程状态"
				style="text-align:center;">
				${cp:MAPVALUE("WFInstType",userTask.inststate)}
				</ec:column>

				<ec:column property="nodeName" title="当前步骤" style="text-align:left" >
					${userTask.nodeName}
					<c:if test="${userTask.grantor ne null }">
						 (委托)
					</c:if>
			    </ec:column>
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<a href="<c:url value='${userTask.nodeOptUrl}' />" >办理</a>
					<a href='<%=request.getContextPath()%>/sampleflow/sampleFlowManager!viewxml.do?flowInstId=${userTask.flowInstId}'>查看流程图</a>
				</ec:column>
			</ec:row>
	</ec:table>

</body>
</html>
