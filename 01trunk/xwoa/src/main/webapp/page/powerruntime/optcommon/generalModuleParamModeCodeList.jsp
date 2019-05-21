<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%> 
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title>
			通用运行模块配置
		</title>
			<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
	</head>

	<body>
		<fieldset  class="search">
			<legend>
				 查询条件
			</legend>
			
			<s:form action="generalModuleParam" namespace="/powerruntime" style="margin-top:0;margin-bottom:5">
				<table cellpadding="0" cellspacing="0" align="center">
						<s:hidden name="s_flowCode" value="%{#parameters['s_flowCode']}"/>
						<s:hidden name="s_version" value="%{#parameters['s_version']}"/>
						<input type="hidden" id="flowCode" name="flowCode" value="${flowCode}" />
						<input type="hidden" id="version" name="version" value="${version}" />
						
					<tr >
						<td  class="addTd">模块代码:</td>
						<td><s:textfield name="s_moduleCode" value="%{#parameters['s_moduleCode']}"/></td>
						<td  class="addTd">环节名称:</td>
						<td><s:textfield name="s_nodeName" value="%{#parameters['s_nodeName']}"/>
						</td>
					</tr>	
					<tr  class="searchButton">
						<td colspan="4">
							<s:submit method="listModeCode"  key="opt.btn.query" cssClass="btn"/>
							<s:submit method="built"  key="opt.btn.new" cssClass="btn"/>
						</td>
					</tr>
				</table>
			</s:form>
		</fieldset>

		<ec:table action="powerruntime/generalModuleParam!listModeCode.do" items="objList" var="generalModuleParam"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="moduleCode" title="模块代码" style="text-align:center" />

				<ec:column property="nodeName" title="环节名称" style="text-align:center" />

				<ec:column property="ideaLabel" title="结果标签" style="text-align:center" />

				<ec:column property="ideaCatalog" title="结果代码" style="text-align:center" />

				<ec:column property="ideaContent" title="内容标签" style="text-align:center" />

				
				<ec:column property="opt" title="操作" sortable="false"
					style="text-align:center">				
					
					<a href='powerruntime/generalModuleParam!edit.do?moduleCode=${generalModuleParam.moduleCode}&flowCode=${flowCode}&version=${version}'>编辑</a>
					<a href='powerruntime/generalModuleParam!copy.do?moduleCode=${generalModuleParam.moduleCode}&flowCode=${flowCode}&version=${version}'>复制</a>
					<a href='powerruntime/generalModuleParam!delete.do?moduleCode=${generalModuleParam.moduleCode}&s_flowCode=${flowCode}&s_version=${version}' 
							onclick='return confirm("确定该操作?");'>删除</a>
				<a href='#' onclick="doIsInUse('${generalModuleParam.moduleCode}','${generalModuleParam.isInUse}');">
					<c:if test="${generalModuleParam.isInUse eq 'T' or empty generalModuleParam.isInUse}">禁用</c:if>
					<c:if test="${generalModuleParam.isInUse eq 'F'}"><font style="color: red;font-weight: bold;">启用</font></c:if>
					</a>
				</ec:column>
			</ec:row>
		</ec:table>

	</body>
	
<script type="text/javascript">
	function doIsInUse(moduleCode,isInUse){
		if(window.confirm("是否确定此操作?")){
		var url = "powerruntime/generalModuleParam!editIsInUse.do?moduleCode="+moduleCode+"&isInUse="+isInUse+'&s_flowCode=${flowCode}&s_version=${version}';
		document.location.href = url;
		}
	}

</script>
</html>
