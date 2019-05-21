<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<link href='${pageContext.request.contextPath}/scripts/plugin/charisma/css/jquery.cleditor.css' rel='stylesheet'>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>

<%-- <sj:head locale="zh_CN" /> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/scripts/jquery-1.6.2.min.js"></script>
<script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrow.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<script>
	var editor;
	KindEditor.ready(function(K) {
				editor = K.create('textarea[id="cleditor"]',
								{
									cssPath : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/plugins/code/prettify.css',
									uploadJson : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/jsp/upload_json.jsp',
									fileManagerJson : '${pageContext.request.contextPath}/scripts/kindeditor-4.1.7/jsp/file_manager_json.jsp',
									allowFileManager : true,
									afterCreate : function() {
										var self = this;
										K.ctrl(document, 13, function() {
											self.sync();
											document.getElementById(
													"dictionaryForm").submit();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
											document.getElementById(
													"dictionaryForm").submit();
										});
									}
								});
				prettyPrint();
			});
</script>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title>内部讨论版</title>
</head>

<body>
	<fieldset  class="form">
		<legend  style="width: auto; margin-bottom: 10px;">
		<c:if test="${'INFO_REL' eq object.forum.boardcode}">
		创建新闻
	   </c:if>
		<c:if test="${'INFO_REL' ne object.forum.boardcode}">
		创建帖子
	    </c:if>
		</legend>

		<%@ include file="/page/common/messages.jsp"%>

		<form id="dictionaryForm" action="${pageContext.request.contextPath}/app/thread1!save.do"
			method="post" data-validate="true">
			<input type="hidden" name="forum.forumid" value="${object.forum.forumid }"/>
			<input id="hid_thread_annex" type="hidden" name="threadAnnexId" />
			
			<input type="hidden" name="pageBoardCood" value="${pageBoardCood }" />

<!-- 			<button type="submit" class="btn">保存提交</button> -->
<!-- 			<input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" /> -->

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">标题：</td>
					<td align="left"><input type="text" name="titol" id="titol" class="focused required " /></td>
				</tr>
				<c:if test="${not empty object.forum.forumTypes }">
				<tr>
					<td class="addTd">类别：</td>
					<td align="left"><select name="threadType">
											<c:forEach var="type" items="${object.forum.forumTypes }">
												<option value="${type.type }">${type.type }</option>
											</c:forEach>
										</select>
					</td>
				</tr>
				</c:if>
				<c:if test="${'INFO_REL' eq object.forum.boardcode}">
				<tr>
					<td class="addTd">锁定：</td>
					<td align="left"><input type="checkbox" name="threadLock" id="threadLock" checked="checked" value="T" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="addTd">是否可以回复：</td>
					<td align="left"><input type="checkbox" name="threadReply" id="threadReply" checked="checked" value="T" /></td>
				</tr>
				
				<tr>
					<td class="addTd">附件：</td>
					<td align="left"><input type="file" id="upload-forum-thread" data-opt-id="FORUM" data-input-id="hid_thread_annex" /></td>
				</tr>
				<tr>
					<td class="addTd">内容：</td>
					<td align="left">
<%-- 					<textarea name="content" id="cleditor" class="cleditor" rows="5" cols="80">${object.content }</textarea> --%>
					<textarea name="content" id="cleditor"
							cols="40" rows="2" style="width: 100%;"
							value="${object.content }">${object.content }</textarea>
					</td>
				</tr>
			</table>
			<div class="formButton">
				<button type="submit" class="btn">保存提交</button>
				<input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
			</div>
		</form>
	</fieldset>
</body>


<%@ include file="/page/common/scripts.jsp"%>


</html>