<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>
		</title>
	</head>

	<body>
	<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>发文待办</legend>
			
			<s:form action="ioDocTasks" namespace="/dispatchdoc" method="post">
				<table cellpadding="0" cellspacing="0" align="left">
					<tr >
					    <td class="addTd">办件编号:</td>
						<td width="180"><s:textfield id="s_djId" name="s_djId" value="%{#parameters['s_djId']}" style="width:180px" /> </td>
						<td class="addTd">办件名称:</td>
						<td width="180"><s:textfield id="s_flowOptName" name="s_flowOptName" value="%{#parameters['s_flowOptName']}" style="width:180px"/></td>
						<td><s:submit method="listDispatchDocTasks" key="opt.btn.query" cssClass="btn"/>&nbsp;</td>
					</tr>	
                 </table>
             </s:form>
          </fieldset>
		<ec:table action="dispatchdoc/ioDocTasks!listDispatchDocTasks.do" items="dispatchDocList" var="dispatchDocTask"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

				<ec:column property="nodeInstId"  title="编号" style="text-align:center" />	
				<ec:column property="djId" title="办件编号" style="text-align:center" />

				<ec:column property="flowOptName" title="办件名称" style="text-align:center" />	
				
				<ec:column property="nodeCreateTime" title="更新时间"
						style="text-align:center" format="yyyy-MM-dd HH:mm:ss" cell="date" />

				<ec:column property="inststate" title="流程状态"
				style="text-align:center">
				${cp:MAPVALUE("WFInstType",dispatchDocTask.inststate)}
				</ec:column>

				<ec:column property="nodeName" title="当前步骤" style="text-align:left" >
				${dispatchDocTask.nodeName}
				<c:if test="${dispatchDocTask.grantor ne null }">
					 (委托)
				</c:if>
			    </ec:column>
		
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<a href="<c:url value='${dispatchDocTask.nodeOptUrl}' />">办理</a>
					
					<a href='<%=request.getContextPath()%>/sampleflow/sampleFlowManager!viewxml.do?flowInstId=${dispatchDocTask.flowInstId}'>查看流程图</a>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
	
	<script type="text/javascript">
	
			function replaceUrl(a) {
									
				var doOptUrl = a.href; 									
									
				doOptUrl = doOptUrl.replaceAll("amp%3B","");
			
				a.href=doOptUrl;
				
				return false;
			}
</script>

</html>
