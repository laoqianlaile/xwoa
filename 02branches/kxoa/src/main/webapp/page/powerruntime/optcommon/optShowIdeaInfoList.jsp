<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			 办件过程
		</title>
	</head>

	<body class="sub-flow">
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset class="_new">
			<legend>
				 <b>办理意见</b>
			</legend>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<s:iterator value="showIdealogs" status="status" var="i" >
				<tr>
					<td class="addTd">
						${i.nodeLabel} 
					</td>
					<td colspan="3" >
						${i.transcontent} <br>
						<div class="r" align="right">${i.username}
						&nbsp;&nbsp;<fmt:formatDate value="${i.transdate}" pattern="yyyy-MM-dd hh:mm" /></div>
						
					</td>
					
				</tr>
		
			    </s:iterator>
			</table>

	<%-- 	<ec:table action="powerruntime/generalOperator!listShowIdeaLogs.do" items="showIdealogs" var="optIdeaInfo"
			imagePath="${STYLE_PATH}/images/table/*.gif" showPagination="false" showStatusBar="false" showTitle="false" >
			<ec:row>
				<ec:column property="nodeLabel" title="环节名称" style="text-align:center" sortable="false"/>
				<ec:column property="unitname" title="部门名称" style="text-align:center" sortable="false"/>
				<ec:column property="username" title="办理人员姓名" style="text-align:center" sortable="false" />
				<ec:column property="transcontent" title="办理意见" style="text-align:center" sortable="false"/>

			</ec:row>
		</ec:table> --%>
		
		
	</fieldset>
	</body>
</html>
