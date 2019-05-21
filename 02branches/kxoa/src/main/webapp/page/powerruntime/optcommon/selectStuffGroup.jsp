<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 

<html>
<head>
<title>材料分组</title>
<script type="text/javascript">
	function xuanzhong(rd){
	window.opener.document.getElementById('stuffGroupName').value = rd.name;
	window.opener.document.getElementById('stuffGroupId').value = rd.id;	
	window.close();	
	}
</script>
</head>

<body>


<fieldset>
	<legend>查询条件</legend>
<s:form action="generalOperator" namespace="/powerruntime" styleId="roleForm" theme="simple" >
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td width="250">材料分组名称:<s:textfield name="s_stuffGroup" theme="simple"></s:textfield> </td>
			
			<td>
			<s:submit  value="查询" cssClass="btn" method="stuffdivide"></s:submit>
			<%-- <s:submit   value="新建" cssClass="btn" method="groupbuilt" ></s:submit></td> --%>
		</tr>
	</table>
</s:form>
</fieldset>


<ec:table action="generalOperator!selectstuffGroup.do" items="stuffgroups" var="group"
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
			<a href="#">查看</a>
			<input name="${group.stuffGroup}" id="${group.groupId}" value="1" type="radio" onclick="xuanzhong(this)" >
			</ec:column>
	</ec:row>
</ec:table>



</body>
</html>
