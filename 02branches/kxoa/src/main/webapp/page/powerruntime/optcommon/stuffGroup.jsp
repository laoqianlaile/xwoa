<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head>
<title>材料分组</title>
	<!-- 新样式文件 -->
<link href="${pageContext.request.contextPath}/newStatic/css/mailStyle.css" rel="stylesheet" type="text/css" />		
</head>

<body>


<fieldset class="form">
	<legend>查询条件</legend>
<s:form action="generalOperator" namespace="/powerruntime" styleId="roleForm" theme="simple" >
	<table cellpadding="0" cellspacing="0">
		<tr class="searchButton">
			<td width="250">材料分组名称：<s:textfield name="s_stuffGroup" theme="simple" value="%{#parameters.s_stuffGroup}" ></s:textfield> </td>
			
			<td>
			<s:submit  value="查询" cssClass="btn" method="stuffdivide"></s:submit>
			<s:submit   value="新建" cssClass="btn" method="groupbuilt" ></s:submit></td>
		</tr>
	</table>
</s:form>
</fieldset>


<ec:table action="generalOperator!stuffdivide.do" items="stuffgroups" var="group"
		imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"   
		rowsDisplayed="15"
		filterRowsCallback="limit" 
		retrieveRowsCallback="limit"
		sortRowsCallback="limit">
	<ec:row>
		<ec:column property="groupId" title="材料分组编号" style="text-align:center" />
		<ec:column property="stuffGroup" title="材料分组名称" style="text-align:center" />
		
		<ec:column property="groupDesc" title="材料分组说明"  style="text-align:center" />		
		
		<ec:column property="caozuo" title="操作"  style="text-align:center" >
			<a href="powerruntime/generalOperator!deleteStuffgroup.do?suppowerstuffgroup.groupId=${group.groupId}" onclick='return confirm("删除分组信息(如存在材料明细信息也将被删除)，确定此操作吗？");'>删除</a>		
			<a href="powerruntime/generalOperator!groupbuilt.do?suppowerstuffgroup.groupId=${group.groupId}">编辑</a>
			<a href="powerruntime/generalOperator!viewGroupInfo.do?s_groupId=${group.groupId}">查看详细信息</a>
			</ec:column>
	</ec:row>
</ec:table>



</body>
</html>
