<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>

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
	if (!window.Config) {
		window.Centit = {
			contextPath : '${contextPath}'
		};
	}
	var editor;
	KindEditor
			.ready(function(K) {
				editor = K
						.create(
								'textarea[id="bodycontent"]',
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
													"oaBbsThemeForm").submit();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
											document.getElementById(
													"oaBbsThemeForm").submit();
										});
									}
								});
				prettyPrint();
			});
</script>
<title><s:text name="oaBbsDiscussion.edit.title" /></title>
</head>

<body class="sub-flow">

	<fieldset class="form" style="width:97%;">
		<legend>
			<p class="ctitle">编辑帖子信息</p>
		</legend>

		<%@ include file="/page/common/messages.jsp"%>

		<form action="${pageContext.request.contextPath}/bbs/oaBbsTheme!themeSave.do" method="post"
		      id="oaBbsThemeForm" enctype="multipart/form-data" data-validate="true">
		    <!-- <div class="formButton"> -->
				<input type="submit" id="saveBtn" name="save" class="btn" value="保存"/>
				<input type="button" id="backBtn" name="back" class="btn" value="返回" onclick="window.history.back();"/>
			<!-- </div> -->
		    <input type="hidden" name="themeno" value="${object.themeno }" />
		    <input type="hidden" name="layoutno" value="${object.layoutno }" />

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
				    <td class="addTd">主题标题：<font color="#ff0000">*</font></td>
				    <td align="left" colspan="3"><input type="text" name="sublayouttitle" value="${object.sublayouttitle }" 
					    style="width: 90%; height: 24px;" class="focused rquired" /></td>				
				
					
				</tr>

				<tr>
					<td class="addTd">帖子类型：</td>
					<td align="left" ><select name="themeset" style="width: 200px;height: 30px;">
							<c:forEach items="${cp:LVB('themeset') }" var="v">
								<option value="${v.value }"
									<c:if test="${v.value eq object.themeset }">selected="selected" </c:if>>${v.label}</option>
							</c:forEach>
					</select></td>
					<td class="addTd">状态：</td>
					<td align="left">
						<select name="state" style="width: 200px;height: 30px;">
							<option value="T" <c:if test="${'T' eq object.state }"> selected="selected"</c:if>>公开</option>
							<option value="F" <c:if test="${'F' eq object.state }"> selected="selected"</c:if>>不公开</option>
						</select></td>
						
				
					    
				</tr>
				
				<c:if test="${'Y' eq edit }">
				<tr>
					<td class="addTd">审核结果：</td>
						<td align="left" colspan="3">
							<select name="approvalresults" style="width: 200px;height: 30px;">							
								<option value="T" <c:if test="${'T' eq object.approvalresults }"> selected="selected"</c:if>>通过</option>
								<option value="F" <c:if test="${'F' eq object.approvalresults }"> selected="selected"</c:if>>不通过</option>
						    </select></td>
				</tr>
					<tr>
					    <td class="addTd">审核意见：</td>
						<td colspan="3"><input type="text" name="approvalremark"
							id="approvalremark" value="${object.approvalremark }" style="width: 100%; height: 30px" /></td>
					</tr>
				</c:if>

				<tr>
					<td class="addTd">正文：</td>
					<td colspan="3"><textarea name="bodycontent" id="bodycontent" cols="40"
							rows="7" style="width: 98%;height: 300px">${object.bodycontent }
						</textarea> </td>
				</tr>
				
			</table>

			

		</form>
	</fieldset>

	<script type="text/javascript">
		
	</script>


</body>
<%@ include file="/page/common/scripts.jsp"%>
</html>
