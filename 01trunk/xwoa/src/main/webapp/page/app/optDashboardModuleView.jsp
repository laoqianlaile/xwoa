﻿<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title></title>
</head>

<body>
<fieldset class="_new">
		<legend>
			<p>首页模块查看</p>
		</legend>

	<s:form action="optDashboardModule" method="post" namespace="/oa"
		id="optDashboardModuleForm" >
     <div align="left">
		<input class="btn" id="back" type="button"  value="返回"/>
	 </div>
		
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
			    <td class="addTd">模块名称</td> 
				<td align="left">${object.moduleName} </td>
				<td class="addTd">模块编码</td>
				<td align="left">${object.moduleCode} </td> 
			</tr>
		   <tr>
				<td class="addTd">数据展示地址</td>
				<td align="left">
		         ${object.displayUrl}
		        </td>
		        <td class="addTd">更多链接地址</td>
				<td align="left">
		         ${object.linkUrl}	
			    </td>
			</tr>
 
			<tr>
				<td class="addTd">是否使用</td>
				<td align="left">
				<c:if test="${object.isUsed=='T'}">是</c:if>
				<c:if test="${object.isUsed=='F'}">否</c:if>
				</td>
				              
			</tr>
			
		</table>
	 </s:form>
</fieldset>
</body>
<script type="text/javascript">
$(function(){
	$('#back').click(function(){
		var srForm = document.getElementById("optDashboardModuleForm");
		srForm.action = 'optDashboardModule!list.do';
		srForm.submit();
	});
});
function save(){
	var srForm = document.getElementById("optDashboardModuleForm");
	srForm.action = 'optDashboardModule!save.do';
	srForm.submit();
}
</script>
</html>
