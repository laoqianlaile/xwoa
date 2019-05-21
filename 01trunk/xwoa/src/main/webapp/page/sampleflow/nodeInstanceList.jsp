<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>节点管理</title>
</head>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"  type="text/javascript" />
<body>
<%@ include file="/page/common/messages.jsp"%>
<fieldset style="padding:10px;">
	<legend>
		 流程管理操作
	</legend>
	<s:form id="sampleFlowForm" name="sampleFlowManager" method="post" action="#" namespace="/sampleflow" style="margin-top:0;margin-bottom:5">
		<input type="hidden" name="flowInstId" value="${flowInstId}">
		<table cellpadding="0" cellspacing="0" align="left">
			<tr>
				<td>
					<c:if test="${object.instState eq 'N'}">
						<input type="button" onclick="operFlow('suspendInstance')" value="挂起" class="btn"/>
					</c:if>
					<c:if test="${(object.instState eq 'S') or (object.instState eq 'X') or (object.instState eq 'I')or (object.instState eq 'F') }">
						<input type="button" onclick="showDivAlert('awakeInstance')" value="唤醒" class="btn"/>
					</c:if>
					<c:if test="${(object.instState ne 'F') and (object.instState ne 'X')}">
						<input type="button" onclick="operFlow('stopInstance')" value="停止" class="btn"/>
					</c:if>
					
					<%-- <a class="btn btnWide" onclick="viewxml('${flowInstId}');" href="###"><span class="btn"></span></a> 
					<a class="btn btnWide" onclick="popWin('${flowInstId}','workTeam')" href="###"><span class="btn">办件角色管理</span></a>
				 	<a class="btn btnWide" onclick="popWin('${flowInstId}','organize')" href="###"><span class="btn">流程机构管理</span></a>
				 	<a class="btn btnWide" onclick="popWin('${flowInstId}','variable')" href="###"><span class="btn">流程变量维护</span></a>
				 	<a class="btn btnWide" onclick="popWin('${flowInstId}','stage')" href="###"><span class="btn">流程阶段信息</span></a>
					<a class="btn btnWide" href="javascript:createNodeInst();" ><span class="btn">创建节点实例</span></a>
					<a class="btn"
					 href="${pageContext.request.contextPath}/sampleflow/sampleFlowManager!list.do"><span class="btn">返回</span>
					 </a>	
					 --%>
					 <input type="button" value="查看流程图" class="btn btnWide" onclick="viewxml('${flowInstId}');"/>
					 <input type="button" value="办件角色管理" class="btn btnWide" onclick="popWin('${flowInstId}','workTeam')"/>
					 <input type="button" value="流程机构管理" class="btn btnWide" onclick="popWin('${flowInstId}','organize')"/>
					 <input type="button" value="流程变量维护" class="btn btnWide" onclick="popWin('${flowInstId}','variable')"/>
					 <input type="button" value="流程阶段信息" class="btn btnWide" onclick="popWin('${flowInstId}','stage')"/>
					 <input type="button" value="创建节点实例" class="btn btnWide" onclick="javascript:createNodeInst();"/>
					 <input type="button" value="返回" class="btn" onclick="location.href='${pageContext.request.contextPath}/sampleflow/sampleFlowManager!list.do'"/>
					
				</td>
			</tr>
		</table>
		<div id="alert" class="alert" style="width:340px;height:200px">
			<h4><span>流程管理操作</span><span id="close" class="close" style="margin-left: 220px;" onclick="closeAlert('alert');">关闭</span></h4>
			<p><label>超时期限:</label><s:textfield id="timeLimit" name="timeLimit" value="%{timeLimit}"
								yearRange="2000:2020" displayFormat="yy-mm-dd" changeYear="true" /></p>
			<p><label>操作描述:</label><textarea name="authDesc" style="width:220px;"></textarea></p>
			<p align="center" ><input type="submit" id="sub" value="确定" class="sub" />&nbsp;&nbsp;<input type="reset" value="重置" class="sub" /></p>
		</div>
		<div id="stop" class="alert" style="width:340px;height:150px">
			<h4><span>流程管理操作</span><span id="close" class="close" style="margin-left: 220px;" onclick="closeAlert('stop');">关闭</span></h4>
			<p><label>操作描述:</label><textarea name="stopDesc" style="width:220px;"></textarea></p>
			<p align="center" ><input type="submit" id="sub1" value="确定" class="sub" />&nbsp;&nbsp;<input type="reset" value="重置" class="sub" /></p>
		</div>
		<div id="nodeCreate" class="alert" style="width: 350px;height: 200px;">
			<h4><span>节点创建</span><span id="close" class="close" style="margin-left: 260px;"  onclick="closeAlert('nodeCreate');" >关闭</span></h4>
			<p><label>选择节点:</label>
				<select id="nodeId" name="nodeId">   
					<option value="">   
						<c:out value="-- 请选择 --"/>   
					</option>    
					<c:forEach var="nodeInfo" items="${flowNodeList}">     
						<option value="${nodeInfo.nodeId}">   
							<c:out value="${nodeInfo.nodeName}"/>   
						</option>
					</c:forEach> 
				</select>
			</p>
			<p><label>指定机构:</label>
				<select name="unitCode">   
					<option value="">   
						<c:out value="-- 请选择 --"/>   
					</option>    
					<c:forEach var="row" items="${cp:LVB('unitcode')}">     
						<option value="${row.value}">   
							<c:out value="${row.label}"/>   
						</option>
					</c:forEach> 
				</select>
			</p>
			<p align="center" >
			<input type="button" id="sub2" value="确定" onclick="submitNode();" class="sub" />&nbsp;&nbsp;<input type="button" value="取消" class="sub"   onclick="closeAlert('nodeCreate');"/>
				<br>&nbsp;&nbsp;<span id="errorInfo" style="color:red"></span>
			</p>
		</div>
	</s:form>
</fieldset>
<br/>
<fieldset style="padding:10px;">
	<legend>
		 流程实例所有节点
	</legend>
	<ec:table tableId="nd" action="../sampleflow/sampleFlowManager!listFlowInstNodes.do" items="nodeList" var="wfNodeInstance"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" showPagination="false"> 
		<ec:row>
			<ec:column property="nodeName" title="当前步骤" style="text-align:left" />
			<ec:column property="createTime" title="创建时间" style="text-align:center" >
				<fmt:formatDate value="${wfNodeInstance.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</ec:column>
			<ec:column property="lastUpdateTime" title="完成时间" style="text-align:center" >
				<fmt:formatDate value="${wfNodeInstance.lastUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</ec:column>
			<ec:column property="timeLimitStr" title="剩余时间" style="text-align:center" />
			<ec:column property="nodeState" title="状态" style="text-align:center" >
				${cp:MAPVALUE("WFInstType",wfNodeInstance.nodeState)}
			</ec:column>
			<ec:column property="runToken" title="令牌" style="text-align:center" />
			<ec:column property="subFlowInstId" title="子流程编号" style="text-align:center" />
			<ec:column property="unitCode" title="所属机构" style="text-align:center" >
				${cp:MAPVALUE("unitcode",wfNodeInstance.unitCode)}
			</ec:column>
			<ec:column property="opt" title="操作" sortable="false" style="text-align:center">
				<c:if test="${wfNodeInstance.nodeState eq 'N' }">
					&nbsp;<a onclick='return confirm("确定要挂起此节点?");' href='sampleFlowManager!suspendNodeInst.do?nodeInstId=${wfNodeInstance.nodeInstId}&flowInstId=${flowInstId}'>挂起</a>
					&nbsp;<a onclick='return confirm("确定要强制提交此节点?");' href='sampleFlowManager!forceCommit.do?nodeInstId=${wfNodeInstance.nodeInstId}&flowInstId=${flowInstId}'>提交</a>
					&nbsp;<a onclick='return confirm("确定要回退此节点?");' href='sampleFlowManager!rollbackOpt.do?nodeInstId=${wfNodeInstance.nodeInstId}&flowInstId=${flowInstId}'>回退</a>
					&nbsp;<a onclick='return confirm("确定要游离此节点?");' href='sampleFlowManager!forceDissociate.do?nodeInstId=${wfNodeInstance.nodeInstId}&flowInstId=${flowInstId}'>强制游离</a>
					&nbsp;<a href='flowUserTask!listNodeOpers.do?nodeInstId=${wfNodeInstance.nodeInstId}'>操作人员</a>
				</c:if>
				<c:if test="${wfNodeInstance.nodeState eq 'R' }">
					&nbsp;<a onclick='return confirm("确定要停止此节点游离?");' href='sampleFlowManager!stopDissociate.do?nodeInstId=${wfNodeInstance.nodeInstId}&flowInstId=${flowInstId}'>停止游离</a>
				</c:if>
				<c:if test="${wfNodeInstance.nodeState eq 'C' }">
					&nbsp;<a onclick='return confirm("确定从该节点重新运行流程?");' href='sampleFlowManager!resetToCurrent.do?nodeInstId=${wfNodeInstance.nodeInstId}&flowInstId=${flowInstId}'>重新运行</a>
					&nbsp;<a onclick='return confirm("确定从创建游离节点实例?");' href='sampleFlowManager!createDissociate.do?nodeInstId=${wfNodeInstance.nodeInstId}&flowInstId=${flowInstId}'>创建游离</a>
				</c:if>
				<c:if test="${wfNodeInstance.nodeState eq 'S' or wfNodeInstance.nodeState eq 'I' }">
					&nbsp;<a onclick='return confirm("确定要唤醒此节点?");' href='sampleFlowManager!awakeNodeInst.do?nodeInstId=${wfNodeInstance.nodeInstId}&flowInstId=${flowInstId}'>唤醒</a>
				</c:if>
				<c:if test="${wfNodeInstance.nodeState eq 'X'}">
					&nbsp;<a onclick="nodeManage('sampleFlowManager!awakeNodeInst.do?nodeInstId=${wfNodeInstance.nodeInstId}&flowInstId=${flowInstId}');" href="javascript:void(0);">唤醒</a>
				</c:if>
				&nbsp;<a href='../sampleflow/sampleFlowManager!listNodeInstTasks.do?nodeInstId=${wfNodeInstance.nodeInstId}'>任务管理</a>
				&nbsp;<a href='../sampleflow/sampleFlowManager!listNodeInstLogs.do?nodeInstId=${wfNodeInstance.nodeInstId}'>节点日志</a>
			</ec:column>
		</ec:row>
	</ec:table>
	<s:form name="nodeManager" method="post" action="#" namespace="/sampleflow" style="margin-top:0;margin-bottom:5">
		<input type="hidden" name="flowInstId" value="${flowInstId}">
		<div id="nodeDiv" class="alert" style="width:300px;height:150px">
			<h4><span>节点管理操作</span><span id="close" class="close" style="margin-left:180px;" onclick="closeAlert('nodeDiv');">关闭</span></h4>
			<p><label>超时期限:</label><s:textfield id="timeLimit" name="timeLimit" value="%{timeLimit}"/></p>
			<p align="center" ><input type="submit" id="sub" value="确定" class="sub" />&nbsp;&nbsp;<input type="reset" value="重置" class="sub" /></p>
		</div>
	</s:form>
</fieldset>
<fieldset style="padding:10px;">
	<legend>
		 流程管理操作日志
	</legend>
	<ec:table tableId="wf" action="../sampleflow/sampleFlowManager!listFlowInstNodes.do" items="flowLogList" var="wfManageAction"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" showPagination="false" >
		<ec:row>
			<ec:column property="userCode" title="操作用户" style="text-align:center" >
				${cp:MAPVALUE("usercode",wfManageAction.userCode)}
			</ec:column>
			<ec:column property="actionType" title="管理活动类别" style="text-align:center" >
				${cp:MAPVALUE("WfManageType",wfManageAction.actionType)}
			</ec:column>
			<ec:column property="adminDesc" title="操作描述" style="text-align:center" />
			<ec:column property="actionTime" title="活动时间" style="text-align:center" format="yyyy-MM-dd HH:mm:ss" cell="date"/>

			<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
		</ec:row>
	</ec:table>
</fieldset>
</body>
<script type="text/javascript">
function nodeManage(uri){
	document.getElementById("nodeManager").action=uri;
	setAlertStyle('nodeDiv');
}

function operFlow(method){
	var formObj =document.getElementById("sampleFlowManager");
	formObj.action="sampleFlowManager!"+method+".do";
	setAlertStyle('stop');
}

function showDivAlert(method)
{
	var formObj =document.getElementById("sampleFlowManager");
	formObj.action="sampleFlowManager!"+method+".do";
	setAlertStyle('alert');
}

function createNodeInst(){
	var formObj =document.getElementById("sampleFlowManager");
	formObj.action="sampleFlowManager!createFlowNode.do";
	setAlertStyle('nodeCreate');
}

function checkNode(){
	if(document.getElementById("nodeId").value == ""){
		document.getElementById("errorInfo").innerHTML="* 请选择节点";
		return false;
	}
	return true;
}

function submitNode(){
	if(checkNode()){
		var formObj =document.getElementById("sampleFlowManager");
		formObj.submit();
	}
}

function viewxml(flowInstId) {
	window.open("${contextPath}/sampleflow/sampleFlowManager!viewxml.do?flowInstId=" + flowInstId, "_blank");
}

function popWin(flowInstId, type) {
	var url = "";
	if ("workTeam" == type) {
		url = "${contextPath}/sampleflow/sampleFlowManager!viewWorkTeam.do?pop=pop&flowInstId=" + flowInstId;
	} else if ("organize" == type) {
		url = "${contextPath}/sampleflow/sampleFlowManager!viewOrganize.do?pop=pop&flowInstId=" + flowInstId;
	} else if ("variable" == type) {
		url = "${contextPath}/sampleflow/sampleFlowManager!listVariable.do?pop=pop&flowInstId=" + flowInstId;
	} else if ("stage" == type) {
		url = "${contextPath}/sampleflow/sampleFlowManager!listStage.do?pop=pop&flowInstId=" + flowInstId;
	}
	JDialog.open({
		src : url,
		width : 800,
		height : 350
	});
}
</script>
</html>
