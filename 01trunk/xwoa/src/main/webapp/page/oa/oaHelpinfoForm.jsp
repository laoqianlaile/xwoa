<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>

<%-- <%-- <sj:head locale="zh_CN" /> --%> 
<link href="${pageContext.request.contextPath}/themes/oaHelpInfo/style.css" rel="stylesheet" type="text/css" />
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath }/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script> --%>

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
	KindEditor.ready(function(K) {
				editor = K.create('textarea[id="remark"]',
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
<title><s:text name="oaHelpinfo.edit.title" /></title>
</head>

<body>
<div class="body">
<div class="content">
<%@ include file="/page/common/messages.jsp"%>
<fieldset class="form">
	<c:choose>
	<c:when test="${not empty object.djid }">
		<legend>
		<s:text name="oaHelpinfo.edit.title" />
		</legend>
	</c:when>
	<c:otherwise>
		<legend>
		新增OA帮助平台
		</legend>
	</c:otherwise>
	</c:choose>
<s:form action="oaHelpinfo"  method="post" namespace="/oa" id="oaHelpinfoForm" enctype="multipart/form-data" onsubmit="return checkForm();" >
<table width="100%" border="0" cellpadding="1" cellspacing="1">		
 			<input type="hidden" name="djid" value="${object.djid}"/>
				<tr>
					<td class="addTd">
						<s:text name="oaHelpinfo.columnType" />
					</td>
					<td>
						<select name="columnType" style="height: 24px">
						 <c:forEach var="row" items="${cp:DICTIONARY('columntype')}">
			                <option value="${row.datacode}"
			                 <c:if test="${row.datacode eq object.columnType}"> selected="selected"</c:if>>
			                 <c:out value="${row.datavalue}" />
			             </option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td  class="addTd">
						<s:text name="oaHelpinfo.infoName" />
					</td>
					<td>
							<s:textfield name="infoName"  size="40" style="height: 27px;width:100%"/>
					</td>
				</tr>
				<tr>
					<td  class="addTd">
						<s:text name="oaHelpinfo.remark" />
					</td>
					<td>
						<textarea name="remark" id="remark"
							cols="40" rows="5" style="width: 100%;">${object.remark }
						</textarea>
					</td>
				</tr>
				<tr>
					<td  class="addTd">
						<s:text name="oaHelpinfo.fileDoc" />
					</td>
					<td>	
  						<s:file name="fileDoc_" size="40" style="width: 100%;"/>
					</td>
				</tr>

</table>
<div  class="formButton">
	<s:submit name="save"  method="save" cssClass="btn" key="opt.btn.save" />
	<input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
</div>
</s:form>
</fieldset>
</div>
   </div>
</div>
</body>
<script type="text/javascript">
	function checkForm() {
		 if ($("#oaHelpinfoForm_infoName").val() == '') {
			alert("名称不可为空！");
			$('#infoName').focus();
			return false;
		} 
		
		return true;
	}
</script>
</html>
