<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<script>
	document.createElement('template');
</script>
<title>内部讨论版</title>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<c:if test="${ empty forumid}">
		新增内部讨论版信息
	         </c:if>
				<c:if test="${ not empty forumid}">
		编辑内部讨论版信息
	        </c:if>
			</p>
		</legend>
		<%@ include file="/page/common/messages.jsp"%>

		<form id="forumEditForm"
			action="${pageContext.request.contextPath}/app/forum!save.do"
			method="post" data-validate="true">

            <input type="hidden" name="forumid" value="${object.forumid }" />
			<input type="hidden" name="boardcode" value="FORUM" />	
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
<!-- 					<td class="addTd">所属版块代码：</td> -->
<!-- 					<td align="left"><select name="boardcode" id="boardcode"> -->
<%-- 							<c:forEach var="f" items="${cp:DICTIONARY('FORUM_BELONG')}"> --%>
<%-- 								<option value="${f.id.datacode}" --%>
<%-- 									<c:if test="${(empty object.boardcode and 'FORUM' eq f.id.datacode) or f.id.datacode eq object.boardcode}">selected="selected" </c:if>>${f.id.datacode}</option> --%>
<%-- 							</c:forEach> --%>
<!-- 					</select></td> -->
					<td class="addTd">讨论版名称：</td>
					<td align="left" colspan="3"><input name="forumname" type="text" style="width:190px;heigth:25px;"
						class="required" id="forumname"
						<c:if test="${!empty forumForm.map.forumname }">readonly="readonly"</c:if>
						value="${object.forumname }" /></td>

				</tr>
				<tr>
					<td class="addTd">加入权限：</td>
					<td align="left"><select name="joinright" style="width:200px;heigth:25px;"
						data-value="${object.joinright }" id="joinright">
							<option value="0">公开</option>
							<option value="1">审批</option>
					</select></td>
					<td class="addTd">浏览权限：</td>
					<td align="left"><select name="viewright" id="viewright" style="width:200px;heigth:25px;"
						data-value="${object.viewright }">
							<option value="0">公开 所有人可见</option>
							<option value="1">封闭 只有讨论版成员可见</option>
					</select></td>
				</tr>

				<tr>
					<td class="addTd">发帖权限：</td>
					<td align="left"><select name="postright" id="postright" style="width:200px;heigth:25px;"
						data-value="${object.postright }">
							<option value="0">所有人可发表帖子</option>
							<option value="1">讨论版成员可发表帖子</option>
					</select></td>
					<td class="addTd">回帖权限：</td>
					<td align="left"><select name="replyright" id="replyright" style="width:200px;heigth:25px;"
						data-value="${object.replyright }">
							<option value="0">所有人均可回复</option>
							<option value="1">讨论版成员可回复帖子</option>
					</select></td>
				</tr>
				<tr>
					<td class="addTd">是否招纳版主：</td>
					<td align="left"><select name="isforumer" id="isforumer" style="width:200px;heigth:25px;"
						data-value="${object.isforumer }">
							<option value="0">开启</option>
							<option value="1">关闭</option>
					</select></td>
					<td class="addTd">讨论版属性：</td>
					<td align="left"><select name="forumstate" id="forumstate" style="width:200px;heigth:25px;"
						data-value="${object.forumstate }">
							<option value="1">启用</option>
							<option value="2">隐藏</option>
							<option value="0">停用</option>
					</select></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td class="addTd">封面图片：</td> -->
<!-- 					<td align="left" colspan="3"><input name="forumpic" -->
<%-- 						type="text" id="forumpic" value="${object.forumpic }" --%>
<%-- 						<c:if test="${!empty forumForm.map.forumpic }">readonly="readonly"</c:if> /> --%>
<!-- 					</td> -->


<!-- 				</tr> -->
				<tr>
					<td class="addTd">讨论版公告：</td>
					<td align="left" colspan="3"><textarea name="announcement"
							id="announcement" rows="5" cols="50">${object.announcement }</textarea></td>
				</tr>

			</table>

			<div class="formButton">
				<input type="button" name="back" Class="btn"
					onclick="history.back(-1);" value="返回" /> 
				 <input type="submit"
					id="submitBtn" class="btn" value="提交" />
			</div>


			<fieldset>
				<legend>编辑内部讨论版类别</legend>
				<input type="button" id="addBtn" class="btn" value="添加数据" />
				<ec:table
					action="${pageContext.request.contextPath}/app/forum!save.do"
					items="object.forumTypes" var="ftype" showPagination="false"
					tableId="editable"
					imagePath="${contextPath}/themes/css/images/table/*.gif"
					sortable="false" showStatusBar="false" showTitle="false"
					autoIncludeParameters="false">
					<ec:row>
						<ec:column property="type" title="类别" styleClass="editable">
							<input type="text" name="type" value="${ftype.cid.type }"
								class="required" />
						</ec:column>
						<ec:column property="opt" title="操作" sortable="false"
							style="text-align:center">

							<a class="btn btn-success deleteBtn" title="删除数据"> 删除数据 </a>
						</ec:column>
					</ec:row>
				</ec:table>
			</fieldset>
		</form>

		<template data-ref="#editable_table" data-property='["type"]'
			data-name="newForumTypes" data-add-button="#addBtn"
			data-submit-button="#submitBtn">
		<tr>
			<td class="E"><input type="text" class="required" /></td>
			<td>{deleteBtn}</td>
		</tr>
		</template>
</body>
<%@ include file="/page/common/scripts.jsp"%>

</html>