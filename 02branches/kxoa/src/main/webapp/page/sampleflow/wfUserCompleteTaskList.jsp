<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			已办事项
		</title>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />
<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
		<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
	<%-- 	<script src="${pageContext.request.contextPath}/scripts/suggest.js" type="text/javascript"></script> --%>
	<style type="text/css">
	.eXtremeTable{margin-top:0!important}
	.searchDiv{margin-bottom:0!important;padding-bottom:0px!important}
	</style>
	</head>
	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="search">
			<legend class="headTitle">
				 我的已办事项
			</legend>
			<div class="searchDiv" >
			<s:form id="flowUserTask" action="flowUserTask" namespace="/sampleflow">
			<div class="searchArea" >
				<table style="width: auto;">
					<tr >
						<!-- <td  class="addTd">所属机构:</td>
						<td> -->
<!-- 							 <select name="s_unitcode" style="width:180px"> -->
<!-- 							 	<option value="">--请选择--</option> -->
<%-- 								<c:forEach var="row" items="${cp:SUBUNITS('d00001','YW,NY')}"> --%>
<%-- 									<option value="${row.unitcode}"  <c:if test="${param.s_unitcode==row.unitcode}">selected="selected"</c:if>> --%>
<%-- 									<c:out value="${row.unitname}" /> --%>
<!-- 									</option> -->
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
						<!-- </td> -->
						<td class="searchTitleArea" ><label class="searchCondTitle" style="width:80px;">创建时间:</label></td>
						<td class="searchCountArea">
							<input type="text" class="Wdate searchCondContent"  id="s_createtime" <c:if test="${not empty s_createtime }"> value="${s_createtime}" </c:if>
	                            <c:if test="${empty s_createtime  }">value="${param['s_createtime'] }"</c:if> name="s_createtime"  
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						 </td>
						 <td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea"><label class="searchCondTitle" style="width:80px;">完成时间:</label></td>
						<td class="searchCountArea">
						<input type="text" class="Wdate searchCondContent"  id="lastUpdateTime" <c:if test="${not empty s_lastUpdateTime }"> value="${s_lastUpdateTime}" </c:if>
	                            <c:if test="${empty s_lastUpdateTime  }">value="${param['s_lastUpdateTime'] }"</c:if> name="s_lastUpdateTime"  
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd '})"placeholder="选择日期">
						<%-- <sj:datepicker id="lastUpdateTime" style="width:180px" 
							name="s_lastUpdateTime" value="%{#parameters['s_lastUpdateTime']}"
							yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true" />
						 --%>
						 </td>
						 <td class="searchCondArea" ><div class="clickDiv" onclick="sub();"></div></td>
					</tr>
				</table>
				</div>
			</s:form>
			</div>
			</fieldset>
		<ec:table action="../sampleflow/flowUserTask!listUserCompTask.do" items="userCompNodesList" var="wfNodeInstance"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit"> 
			<ec:row>
				<ec:column property="flowOptName" title="办件名称" style="text-align:center" />
				<ec:column property="flowOptTag" title="业务标记" style="text-align:center" />
				<ec:column property="createTime" title="创建时间" style="text-align:center" >
					<fmt:formatDate value="${wfNodeInstance.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</ec:column>
				<ec:column property="lastUpdateTime" title="完成时间" style="text-align:center" >
					<fmt:formatDate value="${wfNodeInstance.lastUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</ec:column>
				<ec:column property="unitCode" title="所属机构" style="text-align:center" >
				${cp:MAPVALUE("unitcode",wfNodeInstance.unitCode)}
				</ec:column>
				<ec:column property="nodeName" title="当前步骤" style="text-align:left" />
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<c:if test="${wfNodeInstance.isRecycle eq 'yes'}">
					&nbsp;<a class="chehui" title="${wfNodeInstance.nodeName}:${wfNodeInstance.nodeInstId}"  onclick="return chehui('${wfNodeInstance.flowOptTag}','${wfNodeInstance.nodeInstId}');" href='../sampleflow/flowUserTask!reclaimUserTask.do?nodeInstId=${wfNodeInstance.nodeInstId}'>撤回</a>
					</c:if>
					<%-- &nbsp;<a class="guanli" title="${wfNodeInstance.nodeName}:${wfNodeInstance.nodeInstId}"  href='../sampleflow/sampleFlowManager!listNodeInstLogs.do?nodeInstId=${wfNodeInstance.nodeInstId}'>节点日志</a> --%>
				</ec:column>

			</ec:row>
		</ec:table>
		
	</body>
	<script type="text/javascript">
	function sub(){
		$("#flowUserTask").attr("action","flowUserTask!listUserCompTask.do");
		$("#flowUserTask").submit();
	
}
	function chehui(djId,nodeinstid){
		var m=false;
			
			$.ajax({
				url:"<%=request.getContextPath()%>/sys/userbizoptLog!getProcessInfo.do",
				type:"POST",
				data:{"djId":djId,"nodeinstid":nodeinstid},
				async: false,
				dataType:"json",
				success:function(date){
					if(date=="noread"){//未被阅读，直接撤消
						if(confirm("确定要撤回任务?")){
							m=true;
						}else{
							m=false;
						}
					}else if(date=="haveread"){//已阅读，未办理
						if(confirm("该办件正在办理中，确定要撤回任务?")){
							m=true;
						}else{
							m=false;
						}
					}else if(date="haveprocess"){//已被办理，无法撤消
						alert("当前办件已被办理，无法撤回！");
						m=false;
					}
				},
				error:function(a,b,c){
					alert("撤回出错，请联系管理员。");
					m=false;
				}
			});
			
		return m;
	}
	
	</script>
</html>
