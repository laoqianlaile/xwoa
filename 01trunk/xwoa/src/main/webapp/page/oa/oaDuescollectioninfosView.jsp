<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><s:text name="oaDuescollectioninfos.edit.title" /></title>
</head>
<script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/newStatic/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
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
	KindEditor
			.ready(function(K) {
				editor = K
						.create(
								'textarea[id="msgcontent"]',
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
													"innermsg_form").submit();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
											document.getElementById(
													"innermsg_form").submit();
										});
									},
									afterBlur: function(){
										this.sync();
									}
								
								});
				prettyPrint();
			});
</script>
<body class="sub-flow">
	<fieldset class="form">
		<legend>
			<p class="ctitle">党费收缴明细</p>
		</legend>
		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaDuescollectioninfos" method="post" namespace="/oa"
			id="oaDuescollectioninfosForm">
		<input type=button value="  返回  " onclick="history.back()" class="btn">
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd"><s:text
							name="oaDuescollectioninfos.usercode" /></td>
					<td align="left"><s:property value="%{usercode}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="oaDuescollectioninfos.unitcode" /></td>
					<td align="left"><s:property value="%{unitcode}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="oaDuescollectioninfos.amount" /></td>
					<td align="left"><s:property value="%{amount}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="oaDuescollectioninfos.isfinish" /></td>
					<td align="left"><s:property value="%{isfinish}" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="oaDuescollectioninfos.createtime" /></td>
					<td align="left"><s:property value="%{createtime}" /></td>
				</tr>
			</table>

		</s:form>
	</fieldset>
