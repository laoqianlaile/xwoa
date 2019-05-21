<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<%-- <%@ include file="/page/common/charisma-css.jsp"%> --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
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
	<fieldset    class="search">
		<legend>
		资产类别管理
		</legend>
		</fieldset>	
	<%-- <input type="button" class="btn" onclick="window.location.href='${pageContext.request.contextPath}/oa/equipmentInfo!list.do?s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}'" value="返回"> --%>
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
			
					<a href="${pageContext.request.contextPath}/oa/equipmentInfo!dictionaryEdit.do?catalogcode=equipment&extracode=${detail.datacode}&s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}"
							title="添加下级类别">
						添加下级类别</a>
				<c:if test="${detail.datacode  ne s_supEquipmentType }">
					<a href="${pageContext.request.contextPath}/oa/equipmentInfo!dictionaryEdit.do?catalogcode=equipment&datacode=${detail.datacode}&extracode=${s_supEquipmentType}&s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}"
							 title="编辑类别"> 
						编辑</a>
                  <c:if test="${not s.first }">
                  	<a  href="#"
						onclick="doDelete('${detail.datacode}','${s_supEquipmentType}');" class="delete" title="删除本级类别"> 
						删除</a>				
			      </c:if>
			      </c:if>

			</ec:column>
		</ec:row>

	</ec:tree>
</body>
<%@ include file="/page/common/scripts.jsp"%> 
<script type="text/javascript">
function doDelete(datacode,supEquipmentType){
	if(window.confirm("本级及下级类别内容将移植上级，是否依旧执行删除操作?")){
		
		var url = "${pageContext.request.contextPath}/oa/equipmentInfo!dictionaryDelete.do?catalogcode=equipment&datacode="+datacode+"&s_supEquipmentType="+supEquipmentType;
		
			document.location.href = url;
	}
	
	
	
	
}
</script>

</html>