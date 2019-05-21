<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/plugin/flatlab/js/html5shiv.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/themes/css/icon-css.css"
	rel="stylesheet" type="text/css" />

<html>
	<head>
		<%-- <sj:head locale="zh_CN" /> --%>
		<title>
			<s:text name="vuserTaskListOA.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				 <s:text name="label.list.filter" />
			</legend>
			
			<s:form action="vuserTaskListOA" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
			 <s:hidden name="s_flowCode" value="%{s_flowCode}"/>
				<table cellpadding="0" cellspacing="0" align="center">

						<tr >
						<td>办件名称:</td>
						<td><s:textfield name="s_flowOptName" /> </td>
				     	<td>当前步骤:</td>
						<td><s:textfield name="s_nodeName" /> </td>
					</tr>	
			
						<tr>
						  <td>开始时间:</td>
						<td>
							<input type="text" class="Wdate"name="s_begTime"
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value="${params['s_begTime']}"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 						<sj:datepicker name="s_begTime" readonly="true"  --%>
<%-- 						value="%{#parameters['s_begTime']}" yearRange="2000:2020"  --%>
<%-- 						displayFormat="yy-mm-dd" changeYear="true" />  --%>
				    	至
				    		<input type="text" class="Wdate"name="s_endTime"
							style="height: 20px; width: 150px; border-radius: 3px; border: 1px solid #cccccc;"
							value="${params['s_endTime']}"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="选择日期">
<%-- 						<sj:datepicker name="s_endTime" readonly="true"  --%>
<%-- 						value="%{#parameters['s_endTime']}" yearRange="2000:2020"  --%>
<%-- 						displayFormat="yy-mm-dd" changeYear="true" />  --%>
						</td>
						<td>
							<s:submit method="list"  key="opt.btn.query" cssClass="btn"/>
						</td>
						<td>
						</td>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="dispatchdoc/vuserTaskListOA!list.do" items="objList" var="vuserTaskListOA"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			
			<ec:row>

	            <ec:column property="warnType" title="提醒类型" style="text-align:center" sortable="false">
	            <c:if test="${vuserTaskListOA.warnType eq '0'}">
			
					<i class="icon  icon-blue icon-clock" style="margin-left: 15px;"title="提醒" ></i></c:if>
					<c:if test="${vuserTaskListOA.warnType eq '1'}">
				<span class="icon icon-red  icon-alert"  style="margin-left: 15px;" title="超时"></span>
					</c:if>
	            </ec:column>	
	          
				<ec:column property="djId" title="流水号" style="text-align:center" />		
               <ec:column property="flowOptName" title="办件名称" style="text-align:center">	
               	<c:choose>
					<c:when test="${fn:length(vuserTaskListOA.flowOptName) > 22}">
						<c:out value="${fn:substring(vuserTaskListOA.flowOptName, 0, 22)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${vuserTaskListOA.flowOptName}" />
					</c:otherwise>
				</c:choose>
               
               </ec:column>		
			
				<ec:column property="createdate" title="登记时间" style="text-align:center" format="yyyy-MM-dd" cell="date">
				<fmt:formatDate value="${vuserTaskListOA.createdate}"
					pattern="yyyy-MM-dd" />
				</ec:column>
				<ec:column property="createuser" title="申请人" style="text-align:center" >	
				${cp:MAPVALUE("usercode",vuserTaskListOA.createuser) }
				</ec:column>
				<ec:column property="nodeName" title="当前步骤" style="text-align:center">	
				${vuserTaskListOA.nodeName}<c:if test="${vuserTaskListOA.grantor ne null }">
					 (<%-- ${cp:MAPVALUE("usercode",vuserTaskListOA.grantor) } --%>委托)
				</c:if>
				</ec:column>
		    
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
						<a href="<c:url value='${vuserTaskListOA.nodeOptUrl}' />" >办理</a>
					<a href='<%=request.getContextPath()%>/sampleflow/sampleFlowManager!viewxml.do?flowInstId=${vuserTaskListOA.flowInstId}'>查看流程图</a>
					<c:if test="${vuserTaskListOA.flowCode != '000859' }">
					<c:if test="${cp:CHECKUSEROPTPOWER('DBCBFQ', 'oaSupervise') }">
					<a href='<%=request.getContextPath()%>/oa/oaSupervise!edit.do?supDjid=${vuserTaskListOA.djId}'>督办发起</a>
					</c:if>
					</c:if>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
</html>
