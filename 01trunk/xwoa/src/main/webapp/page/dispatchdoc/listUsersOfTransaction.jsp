<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
		<title>
			 办理人员状态
		</title>
	</head>

<body class="sub-flow">
		<%@ include file="/page/common/messages.jsp"%>
	<h3 class="sub-flow-title">当前步骤：${currNodeName }</h3>

	<ec:table action="dispatchdoc/vuserTaskListOA!listUsersOfOperate.do" items="transactList" var="transact"
			imagePath="${STYLE_PATH}/images/table/*.gif" showPagination="false" showStatusBar="false" showTitle="false">
		<ec:row >
			<ec:column property="unitname" title="部门名称" style="text-align:center" sortable="false" width="30%">${transact.unitname}</ec:column>
			<ec:column property="usercode" title="办理人员姓名" style="text-align:center" sortable="false">
				${cp:MAPVALUE('usercode',transact.usercode)}
			</ec:column>
		    <ec:column property="nodename" title="办理步骤" style="text-align:center" sortable="false">${transact.nodename}</ec:column>
		    <ec:column property="taskState" title="办理状态"
				style="text-align:center" sortable="false">
				<c:choose>
					<c:when
						test="${'T' eq transact.taskState or 'W' eq transact.taskState }">办理中</c:when>
					<c:when
						test="${'C' eq transact.taskState }">已处理</c:when>
				</c:choose>
			</ec:column>
		</ec:row>		
		</ec:table>		
		
	<table>
	
	</table>
	</fieldset>
	
	</body>

</html>
