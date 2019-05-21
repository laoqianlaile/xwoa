<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
		<title>
			 办件过程
		</title>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/rtx/js/browinfo.js"></script>				
		<style type="text/css">
		.eXtremeTable .odd td, .eXtremeTable .even td{white-space: normal;}
		.eXtremeTable .highlight td{white-space: normal;}
		/* #ec_table{table-layout: fixed} */
		</style>
	</head>

<body class="sub-flow">
		<%@ include file="/page/common/messages.jsp"%>
	<c:set var="notitle" value="${not empty param['notitle'] ? 'notitle' : ''}"/>
	<h3 class="sub-flow-title ${notitle }">办件过程详细信息</h3>

	<!-- <div style="float: right;">
		<input type="button" value="导出excel" class="btn btnWide"
			onclick="doExcel();return false;"/>
	</div> -->
	<ec:table action="powerruntime/generalOperator!listIdeaLogDetails.do" items="ideaLogs" var="optIdeaInfo"
			imagePath="${STYLE_PATH}/images/table/*.gif" showPagination="false" showStatusBar="false" showTitle="false">
			<ec:row >
				<ec:column property="nodename" title="环节名称" style="text-align:center" sortable="false"/>
				<ec:column property="unitname" title="部门名称" style="text-align:center" sortable="false">${cp:MAPVALUE('unitcode2JC',optIdeaInfo.unitcode) }</ec:column>
				<ec:column property="username" title="办理人员姓名" style="text-align:center" sortable="false">
				${optIdeaInfo.username}
				</ec:column>
				<ec:column property="transdate" title="办理时间" style="text-align:center" sortable="false">
				<fmt:formatDate value="${optIdeaInfo.transdate}" pattern="yyyy-MM-dd HH:mm"/>
				</ec:column>
				<ec:column property="transidea" title="办理决定" style="text-align:center" sortable="false"/>
				<ec:column property="transcontent" title="办理意见" sortable="false" style="width:40%;">				
				${optIdeaInfo.transcontent}
				</ec:column>
			</ec:row>		
		</ec:table>		
	</fieldset>
	
	</body>
	<script type="text/javascript">
	function doExcel(){		
		window.location.href='${pageContext.request.contextPath}/powerruntime/generalOperator!exportIdeaLogs.do?djId=${object.djId }&notitle=1';
	}
	</script>
</html>
