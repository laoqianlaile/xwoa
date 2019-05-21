<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<%-- <%@ include file="/page/common/charisma-css.jsp"%> --%>
<html>
<head>
<%-- <title><s:text name="incomeDocTask.list.title" /></title> --%>
</head>
<style>
td.tableHeader {
	word-break: keep-all;
	white-space: nowrap;
}
</style>

<body>
	<%@ include file="/page/common/messages.jsp"%>		
	<input type="button" class="btn" onclick="window.location.href='${pageContext.request.contextPath}/oa/equipmentInfo!list.do?s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}'" value="返回">
 	<ec:tree identifier="datacode" parentAttribute="extracode" items="dictDetails"  
		action="equipmentInfo!equipmentInfoType.do"
		view="org.extremecomponents.tree.TreeView" filterable="false"
		sortable="false"  var="detail" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif">
		<ec:row>
		
			<ec:column property="datavalue" title="类别名称" cell="org.extremecomponents.tree.TreeCell" style="text-align:center" />

			<ec:column property="extracode" title="上级分类名称" style="text-align:center" >
			${cp:MAPVALUE("equipment",detail.extracode)}
			</ec:column>
			<ec:column property="unitopt" title="操作" sortable="false"  style="text-align:center">
					<a href="'${pageContext.request.contextPath}/app/addressBook!oaView.do?bodycode='+ treeNode.id + '&bodytype=' + treeNode.p"
							title="查看">
						添加下级类别</a>

			</ec:column>
		</ec:row>

	</ec:tree>
</body>
<%@ include file="/page/common/scripts.jsp"%> 
</html>