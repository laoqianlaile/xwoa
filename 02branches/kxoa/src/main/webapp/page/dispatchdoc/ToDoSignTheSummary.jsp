<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			 办件过程
		</title>
	</head>

	<body style="width:100%;">
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset style="display: block; padding: 10px;">
			<legend>
				 <b>会签相关处室信息</b>
				 <!-- 通用运行模块维护入口 开始 -->
				<c:if test="${cp:CHECKUSEROPTPOWER('generalModuleParam', 'real-time-maintain') }"> 
				<img src='${contextPath }/styles/images/menu/page.gif' width="20px" title="维护通用运行模块"
					height="20px" id="edit-module" />
				</c:if>
				<!-- 通用运行模块维护入口 结束 -->
			</legend>

		<ec:table action="" items="optIdeaInfolist" var="optIdeaInfo"
			imagePath="${STYLE_PATH}/images/table/*.gif" showPagination="false" showStatusBar="false" showTitle="false" >
			<ec:row>
				<ec:column property="unitcode" title="部门名称" style="text-align:center" sortable="false">
				 ${cp:MAPVALUE("unitcode",optIdeaInfo.unitcode)}
				</ec:column>
				<ec:column property="usercode" title="办理人员" style="text-align:center" sortable="false">
				${cp:MAPVALUE("usercode",optIdeaInfo.usercode)}
				</ec:column>
				<ec:column property="transdate" title="办理时间" style="text-align:center" sortable="false">
				<fmt:formatDate value="${optIdeaInfo.transdate}" pattern="yyyy-MM-dd HH:mm"/>
				</ec:column>
				<%-- <ec:column property="transidea" title="办理意见" style="text-align:center" sortable="false"/> --%>
				<ec:column property="transcontent" title="会签内容" style="text-align:center" sortable="false"/>

			</ec:row>
		</ec:table>
	</fieldset>
	</body>
	<script type="text/javascript">
	// 	<!-- 通用运行模块维护入口 开始 -->
	$("#edit-module")
			.click(
					function() {
						url = "${contextPath}/powerruntime/powerruntime/generalModuleParam!edit.do?moduleCode="
								+ $("#moduleCode").val()+"&isAutoClose=T&r=" + Math.random(); ;
								
						var returnValue=window.showModalDialog(url,window,"dialogWidth=800px;dialogHeight=800px;scroll:yes"); 
								if(returnValue == "T"){
							            window.location.reload();
							        }
					});
// 	<!-- 通用运行模块维护入口 结束 -->
</script>
</html>
