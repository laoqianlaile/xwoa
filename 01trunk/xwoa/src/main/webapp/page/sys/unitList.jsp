<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>部门列表</title>
	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
	</head>
	<body>
	<a href='unit!built.do' >新建部门</a>	
 	<ec:tree identifier="unitcode" parentAttribute="parentunit" items="objList"
		action="unit!list.do" 
		view="org.extremecomponents.tree.TreeView" filterable="false"
		sortable="false"  var="unit" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		>
		<ec:row>
			<ec:column property="unitname" title="部门名"	 cell="org.extremecomponents.tree.TreeCell" />

			<%-- <ec:column property="unitcode" title="部门代码" />
			<ec:column property="depno" title="部门编码" /> --%>
			<ec:column property="unitorder" title="部门排序" />
			<ec:column property="unittype" title="部门类型">${cp:MAPVALUE("UnitType",unit.unittype)}</ec:column>
			<ec:column property="parentunit" title="上级部门">
				${cp:MAPVALUE("unitcode",unit.parentunit)}
				</ec:column>
			<ec:column property="isvalid " title="状态" sortable="false" value="${USE_STATE[unit.isvalid]}"/>
			
			<ec:column property="unitopt" title="操作" sortable="false" >
				<a href='unit!edit.do?unitcode=${unit.unitcode}'>
				 	编辑
				</a>
				<a href='unit!editUnitPower.do?unitcode=${unit.unitcode}' >
				 	编辑机构权限
				</a>
				<a href='unit!view.do?unitcode=${unit.unitcode}'>
				 	成员明细
				</a>
				
				<c:if test="${unit.isvalid eq 'F'}">
					<a href='unit!renew.do?unitcode=${unit.unitcode}'>
					 	启动 
					</a>
				</c:if>
				<c:if test="${unit.isvalid eq 'T'}">
					<a href='unit!delete.do?unitcode=${unit.unitcode}'
						onclick='return confirm("是否禁用该部门?");'>
						禁用
				   </a>
				</c:if>
				<a href='unit!builtNext.do?parentunit=${unit.unitcode}' >
						添加下层机构
				</a>
							
			</ec:column>
		</ec:row>

	</ec:tree>

</body>
</html>
