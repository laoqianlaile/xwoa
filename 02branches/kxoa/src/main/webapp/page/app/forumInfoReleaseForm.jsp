<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<script>
document.createElement('template');
</script>
<title>新闻平台管理</title>
</head>

<body>
	
		<legend class="ctitle" style="width: auto; margin-bottom: 10px;">
			<c:if test="${ empty forumid}">
		新增新闻平台
	</c:if>
			<c:if test="${ not empty forumid}">
		编辑新闻平台
	</c:if>
		</legend>

		<%@ include file="/page/common/messages.jsp"%>

		<form id="forumEditForm"
			action="${pageContext.request.contextPath}/app/forum!saveInfoRelease.do"
			method="post" validator="true">
			<fieldset>
<%-- 			<c:if test="${ empty forumid}"> --%>
		            <input type="hidden" name="forumid" value="${object.forumid }" /> 
 						<input type="hidden" name="boardcode" value="INFO_REL" /> 
<%--  				</c:if>		 --%>
 						
						<input type="hidden" name="joinright" value="1" />
						<input type="hidden" name="viewright" value="0" />
						<input type="hidden" name="postright" value="0" />
						<input type="hidden" name="isforumer" value="1" />
						
						

			<button type="submit" class="btn btn-primary" id="submitBtn">保存提交</button>
			<input type="button" name="back" Class="btn"
				onclick="history.back(-1);" value="返回" />

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">新闻版块名称：</td>
					<td align="left">
					<input name="forumname" type="text" id="forumname" value="${object.forumname }" style="width:140px;height:25px"/>
					</td>
					<td class="addTd">新闻评论权限：</td>
					<td align="left"><select name="replyright" id="replyright" data-value="${object.replyright }" style="width:150px;height:25px">
										<option value="1">不可回复</option>
										<option value="0">所有人均可回复</option>
									</select></td>

				</tr>
				<tr>
					<td class="addTd">新闻版块属性：</td>
					<td align="left"><select name="forumstate" id="forumstate" data-value="${object.forumstate }" style="width:150px;height:25px">
										<option value="1">启用</option>
										<option value="2">隐藏</option>
										<option value="0">停用</option>
									</select></td>
			
			</table>
		
	</fieldset>


	<fieldset>
		<legend>编辑新闻版块类别</legend>
			
			<input type="button" id="addBtn" class="btn" value="添加数据" />
				<ec:table action="${pageContext.request.contextPath}/app/forum!saveInfoRelease.do" items="object.forumTypes" var="ftype" showPagination="false" tableId="editable" 
					imagePath="${contextPath}/themes/css/images/table/*.gif" sortable="false" showStatusBar="false" showTitle="false" autoIncludeParameters="false">
					<ec:row>
						<ec:column property="type" title="类别" styleClass="editable">
						<input type="text" name=type value="${ftype.cid.type }"  class="required"/>
					</ec:column>
					<ec:column property="opt"  title="操作" sortable="false"
				   style="text-align:center">
				  <a class="btn btn-success deleteBtn" title="删除数据" >
					删除数据
					</a> 
				   </ec:column>
					</ec:row>
				</ec:table>
	</fieldset>
</form>

		<template data-ref="#editable_table" data-property='["type"]' 
				data-name="newForumTypes" data-add-button="#addBtn" data-submit-button="#submitBtn">
				<tr>
					<td class="E">
						<input type="text"  class="required" />
					</td>
					
					<td>
						      {deleteBtn}
					</td>
				</tr>
			</template>
</body>
<%@ include file="/page/common/scripts.jsp"%>

</html>