<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
	<head>
		<title>部门列表</title>
          <script language="javascript" type="text/javascript" src="<s:url value="/scripts/addressBook.js"/>" charset="utf-8"></script>
			<script type="text/javascript">
			//获取父页面对象
			var parentDocument = window.opener.document;
			
			function selectTemplate(){
				var templates =  document.getElementsByName("unitcode");
				
				var tempIds = "";
				var tempNames = "";
				for(var i = 0 ; i < templates.length; i++){
					if(templates[i].checked){
						isChecked= true;
						var tempArr = templates[i].value.split('|');
						tempIds += tempArr[0] + ",";
						tempNames +=  tempArr[1] + ",";
					}
				}
					parentDocument.getElementById('unitcode').value = tempIds.substring(0,tempIds.length-1);
					parentDocument.getElementById('uninName').value = tempNames.substring(0,tempNames.length-1);
			}
		
		</script>
	
	
	</head>
	<body>		
 	<ec:tree identifier="unitcode" parentAttribute="parentunit" items="listSelectOrgList"
		action="powerruntime/powerOrgInfo!listSelectOrg.do" 
		view="org.extremecomponents.tree.TreeView" filterable="false"
		sortable="false"  var="unit" imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
		>
		<ec:row>
			<ec:column property="unitname" title="部门名"	 cell="org.extremecomponents.tree.TreeCell" />

			<ec:column property="unitcode" title="部门代码" />
			<ec:column property="parentunit" title="上级部门">
				${cp:MAPVALUE("unitcode",unit.parentunit)}
				</ec:column>
			
			<ec:column property="unitopt" title="操作" sortable="false" >
			<input type="checkbox" id="unitcode" name="unitcode${unit.unitcode}" onclick="selectTemplate()" value="${unit.unitcode}|${unit.unitname}">						
			</ec:column>
		</ec:row>

	</ec:tree>

</body>
	<script type="text/javascript">
		var ids = parentDocument.getElementById('unitcode').value;
		if(ids != null && ids != ''){
			var idArr = ids.split(',');
			for(var i = 0 ; i< idArr.length; i++ ){
				document.getElementsByName("unitcode"+idArr[i])[0].checked =true;
			}
		}
	</script>
</html>
