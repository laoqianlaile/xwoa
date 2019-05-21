<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head>
<title>材料分组</title>


</head>

<body>


<fieldset>
	<legend>查询条件</legend>
	<s:form action="generalOperator" namespace="/powerruntime"  >
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td width="250">材料名称:<s:textfield name="s_stuffName" theme="simple"></s:textfield> </td>
			<s:hidden name="s_groupId" value="%{#parameters['s_groupId']}"/>
			<td>
			<s:submit  value="查询" cssClass="btn" method="viewGroupInfo"></s:submit>
			<s:submit   value="新建" cssClass="btn" method="stuffinfobuilt" ></s:submit>
			<input type="button"  value="返回" Class="btn" onclick="window.history.back()" />
			</td>
		</tr>
	</table>
</s:form>
</fieldset>


<ec:table action="generalOperator!viewGroupInfo.do" items="suppowerstuffinfos" var="stuffinfo"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"   
		rowsDisplayed="15"
		filterRowsCallback="limit" 
		retrieveRowsCallback="limit"
		sortRowsCallback="limit">
	<ec:row>
		<ec:column property="stuffName" title="材料名称" style="text-align:center" />
		
		<ec:column property="stuffType" title="材料类型"  style="text-align:center" >
		${cp:MAPVALUE("FILETYPE", stuffinfo.stuffType)}
		</ec:column>	
		<ec:column property="archivetype" title="文书类型"  style="text-align:center" >
		${cp:MAPVALUE("TEMPLATE_TYPE", stuffinfo.archivetype)}
		</ec:column>
		<ec:column property="stuffOrder" title="材料排序"  style="text-align:center" />	
		<ec:column property="isneed" title="是否必须"  style="text-align:center" >
			<c:if test="${stuffinfo.isNeed eq '1' }">是</c:if>
			<c:if test="${stuffinfo.isNeed eq '0' }">否</c:if>
		</ec:column>	
		<ec:column property="isElectron" title="是否电子"  style="text-align:center" >
			<c:if test="${stuffinfo.isElectron eq '1' }">是</c:if>
			<c:if test="${stuffinfo.isElectron eq '0' }">否</c:if>
		</ec:column>		
		<ec:column property="caozuo" title="操作"  style="text-align:center" >		
			<a href="powerruntime/generalOperator!stuffinfobuilt.do?suppowerstuffinfo.sortId=${stuffinfo.sortId}">编辑</a>
		<%-- 	<a href="powerruntime/generalOperator!viewGroupInfo.do?suppowerstuffinfo.groupId=${group.groupId}">查看详细信息</a> --%>
			</ec:column>
	</ec:row>
</ec:table>



</body>
</html>
