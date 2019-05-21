<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			<s:text name="oaUnitsLeaderLog.list.title" />
		</title>
		<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
		<fieldset
			style="border: hidden 1px #000000; ">
			<legend>
				领导分管部门设置记录
			</legend>			
		</fieldset>

		<ec:table action="powerruntime/oaUnitsLeaderLog!list.do" items="objList" var="oaUnitsLeaderLog"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				
				<c:set var="tno"><s:text name='oaUnitsLeaderLog.no' /></c:set>	
				<ec:column property="no" title="${tno}" style="text-align:center" sortable="false"/>


				<c:set var="tleadercode"><s:text name='oaUnitsLeaderLog.leadercode' /></c:set>	
				<ec:column property="leadercode" title="${tleadercode}" style="text-align:center" sortable="false">
				${cp:MAPVALUE("usercode",oaUnitsLeaderLog.leadercode)}
				</ec:column>

				<c:set var="tunitNames"><s:text name='oaUnitsLeaderLog.unitcodes' /></c:set>	
				<ec:column property="unitNames" title="${tunitNames}" style="text-align:center" sortable="false">
					<c:choose>
					<c:when test="${fn:length(oaUnitsLeaderLog.unitNames) > 30}">
						<c:out value="${fn:substring(oaUnitsLeaderLog.unitNames, 0, 30)}..." />
					</c:when>
					<c:otherwise>
						<c:out value="${oaUnitsLeaderLog.unitNames}" />
					</c:otherwise>
				</c:choose>
				</ec:column>

				<c:set var="tcreatetime"><s:text name='oaUnitsLeaderLog.createtime' /></c:set>	
				<ec:column property="createtime" title="${tcreatetime}" style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date" sortable="false"/>

				<c:set var="tcreateuser"><s:text name='oaUnitsLeaderLog.createuser' /></c:set>	
				<ec:column property="createuser" title="${tcreateuser}" style="text-align:center" sortable="false">
				${cp:MAPVALUE("usercode",oaUnitsLeaderLog.createuser)}
				</ec:column>
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>	
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">
					<c:set var="deletecofirm"><s:text name="label.delete.confirm"/></c:set>
					<a href='powerruntime/oaUnitsLeaderLog!view.do?no=${oaUnitsLeaderLog.no}&ec_p=${ec_p}&ec_crd=${ec_crd}'><s:text name="opt.btn.view" /></a>
				</ec:column>
			</ec:row>
		</ec:table>

	</body>
</html>
