<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title>办(阅办)件</title>
</head>


<body>
   <%@ include file="/page/common/messages.jsp"%>
	<fieldset>
	
		<legend>公文待办查询</legend>

		<s:form action="ioDocTasks" namespace="/dispatchdoc">
			<table cellpadding="0" cellspacing="0" align="left">
			<input type="hidden" id="flowCode" name="s_flowCode" value="${flowCode}" />
			<input type="hidden" id="flowCode1" name="flowCode" value="${flowCode}" />		
				<tr >
					    <td class="addTd">登记号:</td>
						<td width="180"><s:textfield id="s_transAffairNo" name="s_transAffairNo" value="" style="width:180px" /> </td>
						<td class="addTd">项目名称:</td>
						<td width="180"><s:textfield id="s_transaffairname" name="s_transaffairname" value="" style="width:180px"/></td>
						<td><s:submit method="listIncomeDocTasks_by" key="opt.btn.query" cssClass="btn"/>&nbsp;</td>
					</tr>	
                 </table>
             </s:form>
	</fieldset>

	<ec:table action="dispatchdoc/ioDocTasks!listIncomeDocTasks_by.do" items="incomeDocList" var="userTask"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

				<ec:column property="acceptArchiveNo" title="阅办文编号" style="text-align:center" />
				<ec:column property="powername" title="权力事项名称" style="text-align:center"/>
				<ec:column property="transaffairname" title="办件名称" style="text-align:center">
					<a href='<%=request.getContextPath()%>/dispatchdoc/ioDocTasksExcute!transaffairView.do?djId=${userTask.flowOptTag}&flowInstId=${userTask.flowInstId}&nodeInstId=${userTask.nodeInstId}'>${userTask.transaffairname}</a>
				</ec:column>				
				<ec:column property="headUnitCode" title="主办处室" style="text-align:left">
					${cp:MAPVALUE("unitcode",userTask.headunitcode)}
				</ec:column>				
			<ec:column property="nodeCreateTime" title="更新时间"
						style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date">
			<c:if test="${!empty userTask.lastUpdateTime}"><fmt:formatDate value="${userTask.lastUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></c:if>
			<c:if test="${empty userTask.lastUpdateTime}">
			<fmt:formatDate value="${userTask.nodeCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</c:if>						
			</ec:column>
				<ec:column property="inststate" title="流程状态"
				style="text-align:center">
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
