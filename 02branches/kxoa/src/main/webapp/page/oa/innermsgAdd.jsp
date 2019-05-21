<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<script>
	if (!window.Config) {
		window.Centit = {
			contextPath : '${contextPath}'
		};
	}
</script>
<html>
<head>

<%-- <sj:head locale="zh_CN" /> --%>
<!-- <script type="text/javascript" -->
<%-- 	src="${pageContext.request.contextPath }/scripts/jquery-1.6.2.min.js"></script> --%>
<script type="text/javascript" data-main="../scripts/frame/main_old"
	src="${pageContext.request.contextPath }/scripts/frame/components/requirejs/require.js"></script>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
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
									}
								});
				prettyPrint();
			});
</script>
<title>我的消息</title>
</head>
<body class="sub-flow">


	<!-- <fieldset> -->
	<!-- 		<legend  style="width: auto; margin-bottom: 10px;"> -->
	<%-- 		撰写新${cp:MAPVALUE("msgtype",s_msgtype) } --%>
	<!-- 		</legend> -->

	<fieldset class="form">
		<legend>
				撰写新${cp:MAPVALUE("msgtype",s_msgtype) }
		</legend>


		<%@ include file="/page/common/messages.jsp"%>

		<form id="innermsg_form"
			action="${pageContext.request.contextPath}/oa/innermsg!saveMsg.do"
			method="post" data-validate="true">
	<div class="formButtonOfMail">
			  <input type="hidden" name="mailtype" value="O" /> <input
					type="button" name="back" Class="btn" onclick="history.back(-1);"
					value="返回" />
				<c:if test="${s_msgtype eq 'P' }">
					<button type="button" class="btn btn-primary" id="drafts">保存草稿</button>
				</c:if>
				<button type="submit" class="btn btn-primary">发送${cp:MAPVALUE("msgtype",s_msgtype)
					}</button>

			</div>
			<input id="hid_innermsg_mail_add" type="hidden" name="filecodes" />

			<input type="hidden" id="msgtype" name="msgtype"
				value="${s_msgtype }" /> <input type="hidden" id="s_mailtype"
				name="s_mailtype" value="${s_mailtype }" /> <input type="hidden"
				id="s_msgtype" name="s_msgtype" value="${s_msgtype }" />


			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">


				<%-- 				<c:if test="${s_msgtype ne 'F'}"> --%>

				<!-- 				<tr> -->
				<!-- 					<td class="addTd">公告：</td> -->
				<!-- 					<td > -->
				<!-- 					<input id="chk_msgtype_a" type="checkbox"   /> -->
				<!-- 					</td> -->
				<!-- 				</tr> -->

				<%-- 				</c:if>	 --%>


				<!--为公告时不用选择收件人-->
				<%-- 			<c:if test="${msgtype ne 'A' }"> --%>

				<tr id="tr_receive">
					<td class="addTd">收件人 ：</td>
					<td align="left"><textarea id="txa_innermsg_receive_name"
							name="receivename" rows="6" cols="50" class="focused { required: true,  maxlength: 1200 }"
							style="width: 80%; "></textarea> <%-- 					<a id="a_href" href="${pageContext.request.contextPath}/oa/innermsg!getUnitUsers.do?s_msgtype=${s_msgtype }" width="400" height="" role="button" class="btn" target="dialog">添加收件人</a> --%>
						<input id="txt_innermsg_receive_usercode" type="hidden"
						name="to" value=""  /></td>
				</tr>
				<tr>
					<td class="addTd">标题：</td>
					<td align="left"><input type="text" name="msgtitle"
						id="msgtitle" class="focused required " data-rule-maxlength="40"
						style="width: 80%; " /></td>
				</tr>
				<%-- 			</c:if>		 --%>

				<tr>
					<td class="addTd">附件：</td>
					<td align="left"><input type="file" id="upload-fileinfo"
						data-opt-id="MSG" data-input-id="hid_innermsg_mail_add" /></td>
				</tr>
				<tr>
					<td class="addTd">内容：</td>
					<td align="left"><textarea name="msgcontent" id="msgcontent"
							cols="40" rows="2" style="width: 100%;"
							value="${object.msgcontent }">${object.msgcontent }</textarea></td>
				</tr>
			</table>
		

			<!-- 选择人员的模块 -->
			<div id="attAlert" class="alert"
				style="width: 600px; height: 330px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
				<h4>
					<span id="selectTT">选择</span><span id="close2"
						style="float: right; margin-right: 8px;" class="close"
						onclick="closeAlert('attAlert');">关闭</span>
				</h4>
				<div class="fix">
					<div id="leftSide"></div>
					<div id="l-r-arrow">
						<div class="lb"></div>
						<div class="rb"></div>
					</div>
					<div id="rightSide">
						<ul></ul>
					</div>
					<div id="t-b-arrow">
						<!-- <div class="tb"></div>
	            <div class="bb"></div> -->
						<b class="btns"> <input id="save" class="btn" type="button"
							value="保     存" /><input id="clear" class="btn" type="button"
							value="取     消" style="margin-top: 5px;" /></b>
					</div>
				</div>
			</div>

		</form>
	</fieldset>
</body>
<%-- <%@ include file="/page/common/scripts.jsp"%> --%>
<%-- <%@ include file="/page/common/charisma-js.jsp" %> --%>
<script type="text/javascript">
	$(function() {
		$('#a_href').attr('height', window.screen.availHeight - 200);

		$('#chk_msgtype_a').change(function() {
			if ('checked' == $(this).attr('checked')) {
				$('#tr_receive').hide();
				$('#msgtype').val('A');

			} else {
				$('#tr_receive').show();
				$('#msgtype').val("${s_msgtype}");
			}
		});
	});
	function getStringLen(Str) {
		var i, len, code;
		if (Str == null || Str == "")
			return 0;
		len = Str.length;
		for (i = 0; i < Str.length; i++) {
			code = Str.charCodeAt(i);
			if (code > 255) {
				len++;
			}
		}
		return len;
	}
	$("#txa_innermsg_receive_name").click(
			function() {
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d),
							$("#txa_innermsg_receive_name"),
							$("#txt_innermsg_receive_usercode"));
				}
				;
			});

	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert");
	}
	$(function() {
		$('#drafts')
				.click(
						function() {
							var sd = document.getElementsByTagName("iframe")[0].contentDocument.body.innerHTML;
							$("#msgcontent").val(sd);
							var url = $('#innermsg_form').attr('action')
									.replace('saveMsg', 'saveDraft');
							;
							$('#innermsg_form').attr('action', url);
							$('#innermsg_form').submit();
						});
	});
</script>
</body>

</html>





