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
			<p>首页模块编辑</p>
		</legend>

	<s:form action="optDashboardModule" method="post" namespace="/oa"
		id="optDashboardModuleForm" >
     <div align="left">
		<input type="button" class="btn" value="保存"
			onclick="save();" />
		<input class="btn" id="back" type="button"  value="返回"/>
	 </div>
		<input type="hidden" id="id" name="id" value="${object.id}" />
		
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
			    <td class="addTd">模块名称<span style="color: red">*</span></td> 
				<td align="left"><input type="text" name="moduleName" id="moduleName" value="${object.moduleName}" style="width: 220px; height: 25px;"/> </td>
				<td class="addTd">模块编码<span style="color: red">*</span></td>
				<td align="left"><input type="text" name="moduleCode" id="moduleCode" value="${object.moduleCode}" style="width: 220px; height: 25px;"/> </td> 
			</tr>
		   <tr>
				<td class="addTd">数据展示地址<span style="color: red">*</span></td>
				<td align="left">
		          <input type="text" name="displayUrl" id="displayUrl" value="${object.displayUrl}" style="width: 220px;  height: 25px;"/>
		        </td>
		        <td class="addTd">更多链接地址</td>
				<td align="left">
		         <input type="text" name="linkUrl" id="linkUrl" value="${object.linkUrl}" style="width: 220px; height: 25px;"/>			
			    </td>
			</tr>
 
			<tr>
				<td class="addTd">是否使用<span style="color: red">*</span></td>
				<td align="left">
				   <input type="radio" name="isUsed" value="T" <c:if test="${object.isUsed=='T'}">checked="checked"</c:if>>是
				   <input type="radio" name="isUsed" value="F" <c:if test="${object.isUsed=='F'}">checked="checked"</c:if>>否
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
