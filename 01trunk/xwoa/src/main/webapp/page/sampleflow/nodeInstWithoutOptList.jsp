<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="wfNodeInstance.list.title" />
		</title>
		<script src="${pageContext.request.contextPath}/scripts/jquery-1.6.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/scripts/poshytip/MoveTitle.js" type="text/javascript"></script>
		<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset>
			<legend>
				 流程实例节点信息（无操作用户）
			</legend>	
			<input type="hidden" id="nodeInstId" name="nodeInstId" value="">
			<span id="errorInfo" style="color:red"></span>
		<!-- <div id="alert" class="alert" style="width: 300px;height: 100px;"> -->
		<%-- <s:form name="nodeForm" method="post" onsubmit="return checkUnit();" action="sampleFlowManager!modifyNodeInst.do" namespace="/sampleflow" style="margin-top:0;margin-bottom:5">
		<input type="hidden" id="nodeInstId" name="nodeInstId" value="">
			<h4><span>机构选择</span><span id="close" class="close"  onclick="closeAlert('alert');" >关闭</span></h4>
			<p>
				<select name="unitCode">   
					<option value="">   
							<c:out value="-- 请选择 --"/>   
					</option>    
					<c:forEach var="row" items="${cp:LVB('unitcode')}">
									<option value="${row.value}"  <c:if test="${param.s_unitcode==row.value}">selected="selected"</c:if>>
									<c:out value="${row.label}" />
									</option>
					</c:forEach>
				</select>
			&nbsp;&nbsp;<input type="submit" id="sub" value="确定" class="sub" />
			<br>
				&nbsp;&nbsp;<span id="errorInfo" style="color:red"></span>
			</p>
		</s:form> --%>
		<!-- </div> -->
		</fieldset>
		<ec:table tableId="nd" action="../sampleflow/sampleFlowManager!listNoOptNodes.do" items="nodeList" var="wfNodeInstance"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" >
			<ec:row>
				<ec:column property="flowOptName" title="业务名称" style="text-align:center">
 					<input type="hidden" value="${wfNodeInstance.flowOptName}"/>      
			          <a onmouseover="this.style.cursor='hand'" onclick="doShow('${wfNodeInstance.flowOptTag}','${cp:MAPVALUE('optType',fn:substring(wfNodeInstance.flowOptTag, 0, 2))}');" ><c:choose>
						<c:when test="${fn:length(wfNodeInstance.flowOptName) > 18}">
							<c:out
								value="${fn:substring(wfNodeInstance.flowOptName, 0, 18)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${wfNodeInstance.flowOptName}" />
						</c:otherwise>
					</c:choose></a>
				</ec:column>
				<ec:column property="flowOptTag" title="业务号" style="text-align:center" />
				<ec:column property="createTime" title="创建时间" style="text-align:center" >
				<fmt:formatDate value="${wfNodeInstance.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</ec:column>
				<ec:column property="nodeState" title="状态" style="text-align:center" >
					${cp:MAPVALUE("WFInstType",wfNodeInstance.nodeState)}
				</ec:column>
				<ec:column property="timeLimitStr" title="剩余时间" style="text-align:center" />
				<ec:column property="unitCode" title="所属机构" style="text-align:center" >
				${cp:MAPVALUE("unitcode",wfNodeInstance.unitCode)}
				</ec:column>
		<%-- 		<ec:column property="roleCode" title="角色" style="text-align:center" >
					<c:if test="${wfNodeInstance.roleType eq 'gw' }">
						${cp:MAPVALUE("StationType",wfNodeInstance.roleCode)}
					</c:if>
					<c:if test="${wfNodeInstance.roleType eq 'bj' }">
						${cp:MAPVALUE("WFTeamRole",wfNodeInstance.roleCode)}
					</c:if>
					<c:if test="${wfNodeInstance.roleType eq 'xz' }">
						${cp:MAPVALUE("RankType",wfNodeInstance.roleCode)}
					</c:if>
				</ec:column> --%>
				<ec:column property="roleType" title="角色类别" style="text-align:center" >
				${cp:MAPVALUE("WFRoleType",wfNodeInstance.roleType)}
				</ec:column>
				<ec:column property="nodeName" title="当前步骤" style="text-align:left" />
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">
					<%-- <a href="javascript:showDivAlert('${wfNodeInstance.nodeInstId}')">更改机构</a> --%>
					<a href='sampleFlowManager!listFlowInstNodes.do?flowInstId=${wfNodeInstance.flowInstId}'>流程管理</a>
							</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">
		function showDivAlert(val)
		{
			document.getElementById("nodeInstId").value = val;
			document.getElementById("errorInfo").innerHTML="";
			setAlertStyle('alert');;
		}
		
		function checkUnit(){
			if(document.getElementById("unitCode").value.length == 0){
				document.getElementById("errorInfo").innerHTML="* 请选择机构";
				return false;
			}
			return true;
		}
		function doShow(djid,menthods) {
			var url="${contextPath }/"+menthods+"!generalOptView.do?djId="+djid+"&nodeInstId=0&s_itemtype="+djid.substring(0, 2);
			window.location.href=url;
			/* art.dialog
					.open(url,
							 {title: '业务信息', width: 1050, height: 640});  */
							//DialogUtil.open('业务信息',url,1200,700);				 
							 //window.showModalDialog(url, "", "dialogWidth=1200px;dialogHeight=700px");
							 //openNewWindow(url,"业务信息",null);
		}
		
		function openNewWindow(winUrl, winName, winProps) {
			if (winProps == '' || winProps == null) {
				winProps = 'height=700px,width=1200px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
			}
			window.open(winUrl, winName, winProps);
		}
	</script>
</html>
