<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<link
	href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/charisma-app.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/scripts/plugin/charisma/css/bootstrap-classic.css"
	rel="stylesheet" />

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
<style type="text/css">
a {
	text-decoration: none !important;
}

.top-title {
	height: 30px;
	margin-top: 10px;
	margin-bottom: 10px;
	background: #f0f0f0;
}

#oaBbsThemeForm {
	border: 1px solid #CCCCCC;
	padding-bottom: 20px;
}

.ksft {
	border-bottom: 1px solid #CCCCCC;
	background: #F0F0EE;
	height: 30px;
	margin-bottom: 10px;
	line-height: 30px;
	padding-left: 20px
}
</style>

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

<title><s:text name="oaBbsTheme.edit.title" /></title>
</head>

<body class="sub-flow">
	<div class="top-title">
		<a
			href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!showBbsMainPage.do'>
			当前位置：讨论版</a>
		<c:if test="${not empty bbsDiscussion.oaBbsDashboard}">
		                       >
		            <a
				href='${pageContext.request.contextPath}/bbs/oaBbsDashboard!showBbsDisMainPage.do?layoutcode=${bbsDiscussion.oaBbsDashboard.layoutcode}'>
				${bbsDiscussion.oaBbsDashboard.layoutname} </a>
		</c:if>
		<c:if test="${not empty s_layoutno}">
						>
					<a
				href='${pageContext.request.contextPath}/bbs/oaBbsTheme!showThemeMainPage.do?s_layoutno=${bbsDiscussion.layoutno}'>
				${bbsDiscussion.sublayouttitle} </a>
		</c:if>

	</div>
	<div class="body">
		<div class="content">

			<!-- 				<legend> -->

			<!-- 				</legend> -->

			<%@ include file="/page/common/messages.jsp"%>

			<s:form action="oaBbsTheme" method="post" namespace="/bbs"
				id="oaBbsThemeForm" enctype="multipart/form-data"
				onsubmit="return checkForm();">
				<div class="ksft">快速发帖</div>

				<table width="200" border="0" cellpadding="1" cellspacing="1">
					<c:if test="${empty  s_layoutno}">
						<c:if test="${empty  s_layoutcode}">
							<tr>

								<td class="addTd">模块</td>
								<td align="left"><select class="focuse required"
									style="width: 200px; height: 30px" ref="#layoutno"
									data-value="${s_layoutcode }"
									refUrl="${pageContext.request.contextPath}/bbs/bbs/oaBbsTheme!option.do?s_layoutcode={value}"
									name="s_layoutcode">
										<option value="">---请选择---</option>
										<c:forEach var="row" items="${oaBbsDashboards}">
											<c:if test="${'T' eq row.isShowTime}">
												<option value="${row.layoutcode}">
													<c:out value="${row.layoutname}" />
												</option>
											</c:if>
										</c:forEach>
								</select></td>
							</tr>
						</c:if>

						<tr>
							<td class="addTd">子模块<font color='red'>*</font>
							</td>
							<td align="left"><select id="layoutno" class="layoutno"
								style="width: 200px; height: 30px" name="layoutno"
								data-value="${s_layoutno }">
									<c:forEach var="row" items="${oaBbsDiscussions }">
										<c:if test="${'T' eq row.isShowTime}">
											<option value="${row.layoutno}">
												<c:out value="${row.sublayouttitle}" />
											</option>
										</c:if>
									</c:forEach>
							</select></td>
							</tr>
					</c:if>
					
					<c:if test="${not empty  s_layoutno}">
						<input type="hidden" name="layoutno" class="layoutno"
							value="${s_layoutno}" />

					</c:if>
					<input type="hidden" id="s_layoutno" name="s_layoutno"
						value="${s_layoutno}" />

					<tr>
						<td class="addTd"><s:text name="oaBbsTheme.sublayouttitle" />
						</td>
						<td style="padding-bottom:10px"><s:textfield name="sublayouttitle" size="40" cssStyle="width:89.5%;margin-bottom:0"/> <%-- 						<s:textarea name="sublayouttitle" cols="40" rows="2"/> --%>
						   <span id="titleMsg" style="display:block"></span>
						</td>
					</tr>

					<tr>
						<td class="addTd"><s:text name="oaBbsTheme.bodycontent" /></td>
						<td><textarea name="bodycontent" id="bodycontent" rows="20"
								style="width:90%;">${object.bodycontent }
						</textarea>
					</tr>
				</table>
				<div class="formButton">
					<s:submit name="save" method="save" cssClass="btn"
						key="opt.btn.save" />

					<input type="button" Class="btn"
						onclick="javascript:window.location.href='${pageContext.request.contextPath}/bbs/oaBbsTheme!showThemeMainPage.do?s_layoutno=${s_layoutno}';"
						value="返回" />
				</div>
			</s:form>
			<!-- 			</fieldset> -->
		</div>
	</div>
	</div>

</body>
<%@ include file="/page/common/scripts.jsp"%>
<script type="text/javascript">
	function checkForm() {
		if (null == $(".layoutno").val() || '' == $(".layoutno").val()) {
			alert("子模块不可为空!");
			return false;
		}
		if ($("#oaBbsThemeForm_sublayouttitle").val() == '') {
			alert("主题标题不可为空！");
			$('#oaBbsThemeForm_sublayouttitle').focus();
			return false;
		}

		$("#s_layoutno").val($(".layoutno").val());
		return true;
	}
	$(function(){
		//文件摘要文本框输入字数的提示
		$("#oaBbsThemeForm_sublayouttitle").keyup(
				function() {
					CommonUtils.showTextAreaLimited("oaBbsThemeForm_sublayouttitle",
							"titleMsg", 150);
					return false;
				});
	});
</script>
</html>
