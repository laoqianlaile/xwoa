<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>OA帮助平台编辑</title>
<script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>
	
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
	KindEditor
			.ready(function(K) {
				
				editor = K
						.create(
								'textarea[id="remark"]',
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
													"oaHelpinfoForm").submit();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
											document.getElementById(
													"oaHelpinfoForm").submit();
										});
									}
								});
				prettyPrint();
			});
</script>

</head>

<body>

	<fieldset class="form">
		<legend>
			<p class="ctitle">编辑OA帮助平台</p>
		</legend>

		<%@ include file="/page/common/messages.jsp"%>

		<form
			action="${pageContext.request.contextPath}/oa/oaHelpinfo!saveModify.do"
			method="post" id="oaHelpinfoForm" enctype="multipart/form-data" data-validate="true">

			<input type="hidden" name="djid" value="${object.djid }" />
			<input type="hidden" id="fileDocname" name="fileDocname" value="${object.fileDocname }" />

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				<tr>
				    <td class="addTd">名称<font color="#ff0000">*</font></td>
					<td align="left" colspan="3"><input type="text" name="infoName" value="${object.infoName }" style="width:99%;" class="focused required"/></td>
				</tr>
				
				<tr>
					<td class="addTd">栏目类型</td>
					<td align="left"><select name="columnType" style="width: 200px;" class="focused required">
							<c:forEach items="${cp:LVB('columntype')}" var="u">
								<option value="${u.value }"
									<c:if test="${u.value eq object.columnType }" >selected="selected" </c:if>>
									${u.label }</option>
							</c:forEach>
					</select></td>
					
					<td class="addTd">是否精华</td>
					<td align="left"><select name="isgood" class="focused "
						style="width: 200px;">
							<option value="">---请选择---</option>
							<option value="0"
								<c:if test="${'0' eq object.isgood }">selected="selected"</c:if>>普通帖</option>
							<option value="1"
								<c:if test="${'1' eq object.isgood }">selected="selected"</c:if>>精华帖</option>
					</select></td>
					
				</tr>
				<tr>
					<td class="addTd">启用状态</td>
					<td align="left"><select name="state" class="focused "
						style="width: 200px;">
							<option value="">---请选择---</option>
							<option value="T"
								<c:if test="${'T' eq object.state }">selected="selected"</c:if>>启用</option>
							<option value="F"
								<c:if test="${'F' eq object.state }">selected="selected"</c:if>>停用</option>
					</select></td>

					<td class="addTd">是否可回复留言</td>
					<td align="left"><select name="isallowcomment" class="focused "
						style="width: 200px;">
							<option value="">---请选择---</option>
							<option value="Y"
								<c:if test="${'Y' eq object.isallowcomment }">selected="selected"</c:if>>可以</option>
							<option value="F"
								<c:if test="${'N' eq object.isallowcomment }">selected="selected"</c:if>>不可以</option>
					</select></td>
				</tr>
								
				<c:if test="${not empty object.fileDocname}">			 	
				    <tr>
				        <td class="addTd">附件</td>
				        <td align="left" colspan="3">
				        <a id="showFile" href="#" onclick="downFile('${object.djid}')" style="font-size:10px; color:blue; text-decoration: underline"> ${object.fileDocname} </a>
				        &nbsp;&nbsp;&nbsp;<a href="#" id="deleteFile" style="color:red;">[删除]</a>
				        </td>
				    </tr>
			    </c:if>
			    
			    <c:if test="${empty object.fileDocname }">
				    <tr id="uploadFile">
					    <td class="addTd">上传附件</td>
					    <td colspan="3"><s:file name="fileDoc_" size="40" style="width: 100%;"/></td>
				    </tr>
			    </c:if>

				<tr>
					<td class="addTd">描述</td>
					<td align="left" colspan="3"><textarea name="remark" id="remark" cols="40"
							rows="10" style="width: 100%; height: 200"/>${object.remark }</textarea></td>
				</tr>

			</table>

			<div class="formButton">
				<input type="button" id="saveBtn" name="save" class="btn" value="保存"/>
				<input type="button" class="btn" value="返回" onclick="window.history.back();" />
			</div>
		</form>

	</fieldset>

	<script type="text/javascript">
	
	$(function(){
		$('#saveBtn').click(function(){
			
			editor.sync();
			$("#oaHelpinfoForm").submit();
		});
	    
		if(null == $('#fileDocname').val()){
   			$('#uploadFile').hide();
		}
		
		$('#deleteFile').click(function(){
			$('#fileDocname').val(null);
			$('#showFile').html(null);
			$(this).hide();
			$('#uploadFile').show();
		});
	});
	
	function downFile(id) {
		var url = "oaHelpinfo!downStuffInfo.do?djid=" + id;
		document.location.href = url;
	}
	</script>


</body>
<%-- <%@ include file="/page/common/scripts.jsp"%> --%>
</html>
