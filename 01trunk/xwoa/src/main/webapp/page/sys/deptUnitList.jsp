<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>

<head><meta name="decorator" content='${LAYOUT}'/>
<title><c:out value="${cp:MAPVALUE('unitcode',unitcode)}" /> :下属部门列表</title>
	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
<link href="${pageContext.request.contextPath}/newStatic/css/dashboardV2.css" rel="stylesheet" type="text/css" />		
</head>
<body onload="clearNote()">
 	<ec:tree identifier="unitcode" parentAttribute="parentunit" items="objList"
		action="deptManager!listunit.do"
		view="org.extremecomponents.tree.TreeView" filterable="false"
		sortable="false"  var="unit" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif">
	  	<!-- ec:exportXls fileName="1.xls" -->
		<ec:row>
			<ec:column property="unitname" title="部门名" cell="org.extremecomponents.tree.TreeCell" style="text-align:center" />

			<ec:column property="unitcode" title="部门代码" style="text-align:center" />
			<ec:column property="unittype" title="部门类型" style="text-align:center">${cp:MAPVALUE("UnitType",unit.unittype)}</ec:column>
			<ec:column property="parentunit" title="上级部门" style="text-align:center">
				${cp:MAPVALUE("unitcode",unit.parentunit)}
				</ec:column>
			<ec:column property="isvalid " title="状态" sortable="false" value="${USE_STATE[unit.isvalid]}" style="text-align:center" />
			
			<ec:column property="unitopt" title="操作" sortable="false"  style="text-align:center">
				
				<a href='deptManager!editDeptPower.do?unitcode=${unit.unitcode}&underUnit=T'>
			 		编辑机构权限
				</a>
				
				<a href='unit!edit.do?unitcode=${unit.unitcode}&underUnit=T'>
				 	编辑
				
				<a href='unit!view.do?unitcode=${unit.unitcode}'>
				 	成员明细
				</a>
				
				<c:if test="${unit.isvalid eq 'F'}">
					<a href='unit!renew.do?unitcode=${unit.unitcode}&underUnit=T'>
					 	启动 
					</a>
				</c:if>
				<c:if test="${unit.isvalid eq 'T'}">
					<a href='unit!delete.do?unitcode=${unit.unitcode}&underUnit=T'
						onclick='return confirm("是否禁用该部门?");'>
						禁用
				   </a>
				</c:if>
				<a href='unit!builtNext.do?parentunit=${unit.unitcode}&underUnit=T' >
						添加下层机构
				</a>
			</ec:column>
		</ec:row>

	</ec:tree>
<script type="text/javascript">
	function clearNote(){
		$("td.statusBar").text('');
	}
</script>
</body>
</html>
