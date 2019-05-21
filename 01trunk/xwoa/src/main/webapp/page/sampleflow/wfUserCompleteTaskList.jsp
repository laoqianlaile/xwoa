<!DOCTYPE html>
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
			<s:form id="ioDocTasksExcuteForm" action="ioDocTasksExcute" name="ioDocTasksExcuteForm" namespace="/dispatchdoc" method="post">
			<div class="searchArea" >
				<table style="width: auto;">
					<tr >
						  <td class="searchTitleArea">
						<label class="searchCondTitle" style=" white-space:nowrap;">办件名称:</label>
						</td>
						<td class="searchCountArea">
						<input class="searchCondContent" type="text" name="s_flowOptName" value="${s_flowOptName }" />
						</td>
						 <td class="fenggexian" style="padding-left: 0;padding-right: 0"> </td>
						<td class="searchTitleArea"><label class="searchCondTitle" style="width:80px;">完成时间:</label></td>
						<td class="searchCountArea">
						<input type="text" class="Wdate searchCondContent"  id="lastUpdateTime" <c:if test="${not empty s_lastUpdateTime }"> value="${s_lastUpdateTime}" </c:if>
	                            <c:if test="${empty s_lastUpdateTime  }">value="${param['s_lastUpdateTime'] }"</c:if> name="s_lastUpdateTime"  
	                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"placeholder="选择日期">
						 </td> 
						 <td class="searchCondArea" ><div class="clickDiv" onclick="sub();">搜索</div></td>
					</tr>
				</table>
				</div>
			</s:form>
			</div>
			</fieldset>
		<ec:table action="dispatchdoc/ioDocTasksExcute!listUserCompTask.do" items="userCompNodesList" var="wfNodeInstance"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit"> 
			<ec:row 
				 style="cursor:pointer;">
				 <ec:column title="序号" property="rowcount" cell="rowCount" sortable="false" width="4%"/>	
				 <ec:column property="flowOptName" title="办件名称" style="text-align:center" >
				 <input type="hidden" value="${wfNodeInstance.flowOptName}"/>      
						<c:choose>
						<c:when test="${fn:length(wfNodeInstance.flowOptName) > 35}">
							<c:out
								value="${fn:substring(wfNodeInstance.flowOptName, 0, 35)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${wfNodeInstance.flowOptName}" />
						</c:otherwise>
					</c:choose>		
				</ec:column>
				 <ec:column property="flowOptTag" title="业务标记" style="text-align:center" width="15%"/>
				<ec:column property="createTime" title="创建时间" style="text-align:center" width="10%">
					<fmt:formatDate value="${wfNodeInstance.createTime}" pattern="yyyy-MM-dd HH:mm"/>
				</ec:column>
				<ec:column property="lastUpdateTime" title="完成时间" style="text-align:center" width="10%">
					<fmt:formatDate value="${wfNodeInstance.lastUpdateTime}" pattern="yyyy-MM-dd HH:mm"/>
				</ec:column>
				<ec:column property="nodeName" title="当前步骤" style="text-align:center" width="15%"/>
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center" width="8%">
					<a class="check_email" onclick="viewDetail('${wfNodeInstance.flowOptTag}','${cp:MAPVALUE('optType',fn:substringBefore(wfNodeInstance.flowOptTag, '0'))}')"
						href="javascript:void(0)">查看</a>
					<c:if test="${wfNodeInstance.isRecycle eq 'yes'}">
					&nbsp;<a class="chehui" title="${wfNodeInstance.nodeName}:${wfNodeInstance.nodeId}:${wfNodeInstance.nodeInstId}"  onclick="return chehui('${wfNodeInstance.flowOptTag}','${wfNodeInstance.nodeInstId}','${wfNodeInstance.nodeId}');" >撤回</a>
					</c:if>
				</ec:column>

			</ec:row> 
		</ec:table>
		
	</body>
	<script type="text/javascript">
	function sub(){
		$("#ioDocTasksExcuteForm").attr("action","ioDocTasksExcute!listUserCompTask.do");
		$("#ioDocTasksExcuteForm").submit();   
	
	} 
	function viewDetail(djid,menthods) {
		var url="${contextPath }/"+menthods+"!generalOptView.do?djId="+djid+"&nodeInstId=0&s_itemtype="+djid.substring(0, 2);
		window.location.href=url;
	}
	function chehui(djId,nodeinstid,nodeid){
		var m= true;
			$.ajax({
				url:"<%=request.getContextPath()%>/sys/userbizoptLog!getProcessInfo.do",
				type:"POST",
				data:{"djId":djId,"nodeinstid":nodeinstid,"nodeid":nodeid},
				async: false,
				dataType:"json",
				success:function(data){
				   if(data=="noread"){//未被阅读，直接撤消
					   m= true;
						 DialogUtil.confirm("确定要撤回任务?",function () {
							 var url="${contextPath }/dispatchdoc/ioDocTasksExcute!reclaimUserTask.do?nodeInstId="+nodeinstid+"&djId="+djId;
							 window.location.href=url;
						}); 
					}else if(data=="haveread"){//已阅读，未办理
						m= true;
						  DialogUtil.confirm("该办件正在办理中，确定要撤回任务?",function () {
							 var url="${contextPath }/dispatchdoc/ioDocTasksExcute!reclaimUserTask.do?nodeInstId="+nodeinstid+"&djId="+djId;
							 window.location.href=url;
					     }); 
					}else if(data=="haveprocess"){//已被办理，无法撤消
						DialogUtil.alert("当前办件已被办理，无法撤回！");
						m=false;
					}else if(data=="havedone"){//已被办理，无法撤消
						DialogUtil.alert("多人操作步骤结束，无法撤回！");
						m=false;
					}else{
						m=false;
					}
				},
				error:function(a,b,c){
					DialogUtil.alert("撤回出错，请联系管理员。");
					m=false;
				}
			});
			
		return m;
	}
	
	</script>
</html>
