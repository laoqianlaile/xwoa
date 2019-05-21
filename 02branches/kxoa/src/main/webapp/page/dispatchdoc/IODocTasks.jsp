<!-- 这个作为收发文的待办页面 -->

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
			<legend>公文待办查询</legend>
			
			<s:form action="ioDocTasks" namespace="/dispatchdoc" method="post">
				<table cellpadding="0" cellspacing="0" align="left">
				<tr >
					    <td class="addTd">登记号:</td>
						<td width="180"><s:textfield id="s_transAffairNo" name="s_transAffairNo" value="" style="width:180px" /> </td>
						<td class="addTd">标题:</td>
						<td width="180"><s:textfield id="s_transaffairname" name="s_transaffairname" value="" style="width:180px"/></td>
						
					</tr>
					<tr >
					    <td class="addTd">公文类型:</td>
						<td width="180" colspan="2">
							<select name="s_flowCode">
							<option value="">---请选择---</option>							
								<option value="000535">办（阅办）件 </option>
								<option value="000533">阅件</option>
								<option value="000536">会签件 </option>
								<option value="000711">督查件</option>
								<option value="000730">人大代表建议、政协委员提案</option>
								<option value="000530">委内督查件</option>
								<option value="000534">主动拟发文</option>
								<option value="000692">代拟拟发文</option>
								<option value="000531">专报件</option>
							</select>
</td>
						<td><s:submit method="listIncomeDocTasks_Common" key="opt.btn.query" cssClass="btn"/>&nbsp;</td>
					</tr>		
                 </table>
             </s:form>
          </fieldset>
		<ec:table action="dispatchdoc/ioDocTasks!listIncomeDocTasks_Common.do" items="commonDocList" var="userTask"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>

			<ec:column property="acceptArchiveNo" title="阅办文编号" style="text-align:center" />
				<ec:column property="powername" title="公文类型" style="text-align:left"/>
				<ec:column property="transaffairname" title="标题" style="text-align:left">
					<c:if test="${not empty userTask.timeLimit and userTask.timeLimit<0}"><img alt="环节已超期" src="${pageContext.request.contextPath}/styles/images/warning/hjcq.png" ></c:if>
					<c:if test="${not empty userTask.timeLimit and userTask.timeLimit>0 and userTask.timeLimit<240}"><img alt="环节即将超期" src="${pageContext.request.contextPath}/styles/images/warning/hjyj.png" ></c:if>
					<c:if test="${userTask.timeLimit == 0 or userTask.timeLimit >= 240}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
					<a href='<%=request.getContextPath()%>/dispatchdoc/ioDocTasksExcute!transaffairView.do?djId=${userTask.djId}&flowInstId=${userTask.flowInstId}&nodeInstId=${userTask.nodeInstId}'>${userTask.transaffairname}</a>
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
