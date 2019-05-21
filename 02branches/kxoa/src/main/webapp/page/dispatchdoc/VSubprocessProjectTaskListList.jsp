<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
			<s:text name="wfActionTask.list.title" />
		</title>
	</head>

	<body>
		<fieldset>
		<legend> 待办查询 </legend>
		<s:form action="VSubprocessProjectTaskList" namespace="/dispatchdoc">
			<table cellpadding="0" cellspacing="0" align="left">
				<tr>
				<td class="addTd">办件编号:</td>
				<td width="180"><s:textfield id="s_wfOptTag" name="s_wfOptTag"
							value="%{#parameters['s_wfOptTag']}" style="width:180px" />
							</td>
					<td class="addTd">办件名称:</td>
					<td width="180"><s:textfield id="s_wfOptName"
							name="s_wfOptName" value="%{#parameters['s_wfOptName']}"
							style="width:180px" /> </td>
					<td><s:submit method="list" key="opt.btn.query"
							cssClass="btn" />&nbsp;</td>
				</tr>
			</table>
		</s:form>
	</fieldset>
	
	
		<ec:table action="../dispatchdoc/VSubprocessProjectTaskList!list.do" items="objList" var="userTask"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="nodeInstId"  title="编号" style="text-align:center" />
				<ec:column property="djId" title="办件编号" style="text-align:center" />
				<ec:column property="transaffairname" title="办件名称" style="text-align:center" />
				
				<ec:column property="nodeCreateTime" title="更新时间"
						style="text-align:center" format="yyyy-MM-dd HH:mm:ss" cell="date" />
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
