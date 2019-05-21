<!DOCTYPE html>
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
		<title>
			<s:text name="vuserTaskListOA.list.title" />
		</title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
			<legend>
					<c:choose>
			<%-- 		<c:when  test="${s_flowCode eq '000856' or s_itemtype eq 'QB' }">签报待办</c:when>
					<c:when  test="${s_itemtype eq 'SQ' }">事项办理</c:when>
					<c:when  test="${s_flowCode eq '000392' or s_itemtype eq 'FW'}">发文待办</c:when>
					<c:when  test="${s_flowCode eq '000394' or s_itemtype eq 'SW' }">收文待办</c:when>
					<c:when  test="${s_itemtype eq 'QB' }">签报待办</c:when>
					<c:when  test="${s_flowCode eq '000857' or s_itemtype eq 'HYSQ'}">会议申请待办</c:when>
					<c:when  test="${s_flowCode eq '000858' or s_itemtype eq 'CARSQ'}">车辆申请待办</c:when>
					<c:when  test="${s_flowCode eq '000859' or s_itemtype eq 'DCDB'}">督办待办</c:when>
					<c:when  test="${s_flowCode eq '000860' or s_itemtype eq 'HYSSQ'}">会议室申请待办</c:when> --%>
					<c:when  test="${ empty s_flowCode and empty s_itemtype   }">待办事项</c:when>
				<c:otherwise>
				${cp:MAPVALUE("oa_ITEM_TYPE",s_itemtype)}待办
				</c:otherwise>
				</c:choose>				
			</legend>
			
			<s:form action="vuserTaskListOA" namespace="/dispatchdoc" style="margin-top:0;margin-bottom:5">
			 <s:hidden name="s_flowCode" value="%{s_flowCode}"/>
			 <input type="hidden" name="ec_crd" value="${param['ec_crd'] }">
			 <input type="hidden" name="s_itemtype" value="${s_itemtype}"/>
				<table cellpadding="0" cellspacing="0" align="center">

					<tr>
					<c:choose>
				<c:when  test="${s_itemtype eq 'SQ' }">
						<td class="addTd">事项:</td>
						<td><s:textfield name="s_powername" value="%{#parameters['s_powername'] }" /> </td>
				</c:when>
				<c:otherwise>
				
				       <!-- 判断是否会议室待办 -->
				       <c:if test="${'000857' ne s_flowCode }">
							<td class="addTd">流水号:</td>
							<td><s:textfield name="s_djId" value="%{#parameters['s_djId'] }" /> </td>
						</c:if>
				</c:otherwise>
				
				</c:choose>	
				        <c:if test="${'000857' ne s_flowCode }">	
						<td class="addTd">办件名称:</td>
						<td><s:textfield name="s_flowOptName" value="%{#parameters['s_flowOptName'] }" /> </td>
						</c:if>
						  <c:if test="${'000857' eq s_flowCode }">	
						<td class="addTd">会议主题:</td>
						<td><s:textfield name="s_flowOptName" value="%{#parameters['s_flowOptName'] }" /> </td>
						</c:if>
					</tr>	
			
					<tr>
						<td class="addTd">当前步骤:</td>
						<td><s:textfield name="s_nodeName"  value="%{#parameters['s_nodeName'] }" /> </td>
						<td class="addTd">登记时间:</td>
						<td>
						<input type="text" class="Wdate" id="s_begTime"
						<c:if test="${not empty s_begTime }"> value="${s_begTime}" </c:if>
						<c:if test="${empty s_begTime  }">value="${param['s_begTime'] }"</c:if>
						name="s_begTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						至 <input type="text" class="Wdate" id="s_endTime"
						<c:if test="${not empty s_endTime }"> value="${s_endTime }" </c:if>
						<c:if test="${empty s_endTime  }"> value="${param['s_endTime'] }" </c:if>
						name="s_endTime"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})" placeholder="选择日期">
						</td>
					</tr>

				<tr class="searchButton">
					<td colspan="4"><s:submit method="list" key="opt.btn.query" cssClass="btn"
							onclick="return checkFrom();" />
							</td>
				</tr>
			</table>
				
			</s:form>
		</fieldset>

		<ec:table action="dispatchdoc/vuserTaskListOA!list.do" items="objList" var="vuserTaskListOA"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			
			<ec:row>
				<c:choose>
				<c:when  test="${s_itemtype eq 'SQ' }">
				<ec:column property="djId" title="事项" style="text-align:center" >	
				 <c:if test="${vuserTaskListOA.isSuprised eq '1'}">
					<i class="icon  icon-blue icon-edit" style="margin-left: 15px;"title="已督办" ></i>
					</c:if>
				  <c:if test="${vuserTaskListOA.warnType eq '0'}">
					<i class="icon  icon-blue icon-clock" style="margin-left: 15px;"title="提醒" ></i>
					</c:if>
					<c:if test="${vuserTaskListOA.warnType eq '1'}">
				<span class="icon icon-red  icon-alert"  style="margin-left: 15px;" title="超时"></span>
					</c:if>
					<c:if test="${vuserTaskListOA.warnType ne '1' and vuserTaskListOA.warnType ne '0' and vuserTaskListOA.isSuprised ne '1'}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					${vuserTaskListOA.powername}
				</ec:column>	
				</c:when>
				<c:otherwise>

                    <!-- 判断是否会议室待办 -->
					<c:if test="${'000857' ne s_flowCode }">
						<ec:column property="djId" title="流水号" style="text-align:center" width="8%">
							<c:if test="${vuserTaskListOA.isSuprised eq '1'}">
								<i class="icon  icon-blue icon-edit" style="margin-left: 15px;"
									title="已督办"></i>
							</c:if>
							<c:if test="${vuserTaskListOA.warnType eq '0'}">
								<i class="icon  icon-blue icon-clock" style="margin-left: 15px;"
									title="提醒"></i>
							</c:if>
							<c:if test="${vuserTaskListOA.warnType eq '1'}">
								<span class="icon icon-red  icon-alert"
									style="margin-left: 15px;" title="超时"></span>
							</c:if>
							<c:if
								test="${vuserTaskListOA.warnType ne '1' and vuserTaskListOA.warnType ne '0' and vuserTaskListOA.isSuprised ne '1'}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					${vuserTaskListOA.djId}
						</ec:column>
					</c:if>
				</c:otherwise>
				</c:choose>

				<ec:column property="itemtype" title="业务类别" style="text-align:center" width="8%">	
				${cp:MAPVALUE("optTypeName",vuserTaskListOA.itemtype) }
				</ec:column>
			<c:choose>
				<c:when test="${'000857' eq s_flowCode  }">
				
	               <ec:column property="flowOptName" title="会议主题" style="text-align:center">	
	               	<c:choose>
						<c:when test="${fn:length(vuserTaskListOA.transaffairname) > 30}">
							<c:out value="${fn:substring(vuserTaskListOA.transaffairname, 0, 30)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${vuserTaskListOA.transaffairname}" />
						</c:otherwise>
					</c:choose>
	               </ec:column>	

				</c:when>
				
				<c:otherwise>
				
               <ec:column property="flowOptName" title="办件名称" style="text-align:center">	
               	<c:choose>
					<c:when test="${fn:length(vuserTaskListOA.flowOptName) > 30}">
						<c:out value="${fn:substring(vuserTaskListOA.flowOptName, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${vuserTaskListOA.flowOptName}" />
					</c:otherwise>
				</c:choose>
               </ec:column>	
				</c:otherwise>
			</c:choose>


			<ec:column property="createdate" title="登记时间" style="text-align:center" format="yyyy-MM-dd" cell="date">
				<fmt:formatDate value="${vuserTaskListOA.createdate}"
					pattern="yyyy-MM-dd" />
				</ec:column>
				<ec:column property="createuser" title="登记人" style="text-align:center" width="8%">	
				${cp:MAPVALUE("usercode",vuserTaskListOA.createuser) }
				</ec:column>
				
				<ec:column property="nodeName" title="当前步骤" style="text-align:center">	
				${vuserTaskListOA.nodeName}<c:if test="${vuserTaskListOA.grantor ne null }">
					 (<%-- ${cp:MAPVALUE("usercode",vuserTaskListOA.grantor) } --%>委托)
				</c:if>
				</ec:column>
				
				<ec:column property="readstate" title="状态" style="text-align:center">	
				${cp:MAPVALUE("readstate",vuserTaskListOA.readstate)}
				</ec:column>
		    
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center" width="8%">
					<a href="<c:url value='${vuserTaskListOA.nodeOptUrl}' />&s_itemtype=${s_itemtype}" >办理</a>
					<%-- <a href='<%=request.getContextPath()%>/sampleflow/sampleFlowManager!viewxml.do?flowInstId=${vuserTaskListOA.flowInstId}'>查看流程图</a> --%>
				</ec:column>

			</ec:row>
		</ec:table>

	</body>
	<script type="text/javascript">
// 	$("#s_begTime,#s_endTime").mouseup( function() {
// 		var begD = $("#s_begTime").val();
// 		var endD = $("#s_endTime").val();
// 		if(begD>endD){
// 			alert("结束时间不能早于开始时间。");
// 			}
		 
// 		});
	
	 function checkFrom(){
		var begD = $("#s_begTime").val();
		var endD = $("#s_endTime").val();
		if(endD!=""&&begD>endD){
			alert("结束时间不能早于开始时间。");
// 			$("#s_endTime").focus();
			return false;
		}
		return true;
	} 
	 
	function recipient(){
		
	}
	</script>
</html>
