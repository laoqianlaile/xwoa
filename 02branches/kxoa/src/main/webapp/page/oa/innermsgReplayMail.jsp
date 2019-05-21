<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
<style type="text/css">
.bg{	
width:600px;	
height:100px;	
border:0px solid #000;	
color:#fff;		
background:#CCCCff; /* 备用属性，当浏览器不支持渐变时，背景蓝色 */	
background:-moz-linear-gradient(top,#CCCCff,white); /* 火狐渐变 */	
background:-webkit-gradient(linear,0 0,0 100%,from(#CCCCff),to(white));
/* Chrome,Safari渐变 */	
filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#CCCCff', endColorstr='white'); /* IE6,IE7渐变 */	
-ms-filter:"progid:DXImageTransform.Microsoft.gradient(startColorstr='#CCCfff', endColorstr='white')"; /* IE8渐变 */
}
</style>
<title>我的消息</title>
</head>
<body class="sub-flow">
	<fieldset class="form">
		<legend>
			 撰写新${cp:MAPVALUE("msgtype",s_msgtype) }
		</legend>


		<%@ include file="/page/common/messages.jsp"%>

		<form id="innermsg_form"
			action="${pageContext.request.contextPath}/oa/innermsg!saveMsg.do"
			method="post" data-validate="true">

			<input type="hidden" id="msgtype" name="msgtype"
				value="${s_msgtype }" /> <input type="hidden" id="s_msgtype"
				name="s_msgtype" value="${s_msgtype }" /> <input type="hidden"
				id="s_mailtype" name="s_mailtype" value="${s_mailtype }" />
				<input id="hid_innermsg_mail_add" type="hidden" name="filecodes" />
				<input id="topMsgcode" type="hidden" name="topMsgcode"  value="${object.topMsgcode}"/>
				<input id="topType" type="hidden" name="topType"  value="${object.topType}"/>
	<div class="formButtonOfMail">
		<input type="button" name="back" Class="btn"
					onclick="history.back(-1);" value="返回" />
				<c:if test="${s_msgtype eq 'P' }">
					<button type="button" class="btn btn-primary" id="drafts">保存草稿</button>
				</c:if>
				<button type="submit" class="btn btn-primary">发送${cp:MAPVALUE("msgtype",s_msgtype)
					}</button>
				
			</div>

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				

				<tr id="tr_receive">
					<td class="addTd">收件人：</td>
					<td align="left"><input type="text" readonly="readonly"
						id="txa_innermsg_receive_name" name="receivename" rows="1" class="focused  required" data-rule-maxlength="1200" 
						cols="1" style="width: 80%;" value="${object.receivename }"
						style="width: 80%;" />
						<input id="txt_innermsg_receive_usercode" type="hidden" name="to"
						  value="${toUserVaule }" /> <br />
					</td>
				</tr>
				<tr id="tr_cc">
					<td class="addTd">抄送：</td>
					<td align="left"><input type="text" id="txa_cc_name" readonly="readonly" data-rule-maxlength="1200" 
						name="ccName" rows="1" cols="1" style="width: 80%;"
						value="${object.ccName }" style="width: 80%;" /> <input
						id="txt_cc_usercode" type="hidden" name="cc"
						value="${ccUserVaule }" /></td>
				</tr>

				<tr id="tr_bcc">
					<td class="addTd">密送：</td>
					<td align="left"><input type="text" id="txa_bcc_name" readonly="readonly" data-rule-maxlength="1200" 
						name="bccName" rows="1" cols="1" style="width: 80%;"
						value="" style="width: 80%;" /> <%-- <textarea id="txa_bcc_name"
							name="bccName" 
							style="width: 80%;" value="${object.bccName }">${object.bccName }</textarea> --%>

						<input id="txt_bcc_usercode" type="hidden" name="bcc"
						 value="" /></td>
				</tr>
				<c:if test="${s_msgtype eq 'P'}" >
				<tr><td  class="addTd"></td>
					<td  align="left">
						 <span id="span_add_cc">添加抄送</span>-<span
						id="span_add_bcc">添加密送</span>		
						</td>
				</tr></c:if>
				<tr>
					<td class="addTd">标题：</td>
					<td align="left" ><input type="text"
						value="答复 :${object.msgtitle }" name="msgtitle" id="msgtitle"
						class="focused required "  data-rule-maxlength="40" style="width: 80%;" /></td>
				</tr>
				<tr>
					<td class="addTd">附件：</td>
					<td align="left"><input type="file" id="upload-fileinfo"
						data-opt-id="MSG" data-input-id="hid_innermsg_mail_add" /></td>
				</tr>
				<tr>
					<td class="addTd">内容：</td>
					<td align="left"><textarea name="msgcontent" id="msgcontent"
							cols="40" rows="3" style="width: 100%;height:550px">
			<br>
			<br>						
						--------------------------------------------------原邮件--------------------------------------------------------------------------</br>
			<div style="background:url(${pageContext.request.contextPath}/themes/blue/images/jbs.png)  !important;" >

			<b>发件人：</b>${cp:MAPVALUE('usercode', object.sender) }</br>
			<b>发送时间：</b><fmt:formatDate value="${object.senddate}"
										pattern="yyyy-MM-dd HH:mm:ss" /><br>
		    <b>收件人：</b>${object.receivename }<br>
			<c:if test="${not empty object.ccName }">
			 <b>抄送：</b>${object.ccName }<br>
			</c:if>
			<b>主题：</b>${object.msgtitle }</br>	
			</div>
			
							${object.msgcontent }</textarea>
					</td>
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
	
	  //抄送，密送
	function isShowCc()  {
		if ('checked' == $('#span_add_cc').attr("class")) {
			$('#tr_cc').hide();
			$('#txt_cc_usercode').val("");
			$('#txa_cc_name').val("");
			$('#span_add_cc').removeAttr("class");
			$("#span_add_cc").html("添加抄送");
		} else {
			$('#tr_cc').show();
			$('#span_add_cc').attr("class","checked");
			$("#span_add_cc").html("取消抄送");
			
		}
		
	}
	function isShowBcc()  {
		if ('checked' == $('#span_add_bcc').attr("class")) {
			$('#tr_bcc').hide();
			$('#txt_bcc_usercode').val("");
			$('#txa_bcc_name').val("");
			$('#span_add_bcc').removeAttr("class");
			$("#span_add_bcc").html("添加密送");
		} else {
			$('#tr_bcc').show();
			$('#span_add_bcc').attr("class","checked");
			$("#span_add_bcc").html("取消密送");
			
		}
	}
	$('#span_add_cc').live("click", function() {
		isShowCc();
	});
	$('#span_add_bcc').live("click", function() {
		isShowBcc();
	});
	
	if($("#txt_cc_usercode").val()==''){
		$('#tr_cc').hide();
	}
	if($("#txt_bcc_usercode").val()==''){
		$('#tr_bcc').hide();
	}
	
	//是否定时发送
	function isAuto() {
		if ('checked' == $('#isAuto').attr("checked")) {
			$('#tr_isAuto').show();
			$('#send').hide();
		} else {
			$('#tr_isAuto').hide();
			$('#send').show();
		}
	}

	$('#isAuto').live("click", function() {
		isAuto();
	});

	isAuto();
	
	
	
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
				/* debugger; */
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d),
							$("#txa_innermsg_receive_name"),
							$("#txt_innermsg_receive_usercode"));
				}
				;
			});
	$("#txa_cc_name").click(
			function() {
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d), $("#txa_cc_name"),
							$("#txt_cc_usercode"));
				}
				;
			});

	$("#txa_bcc_name").click(
			function() {
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d), $("#txa_bcc_name"),
							$("#txt_bcc_usercode"));
				}
				;
			});
	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert");
	}

	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert");
	}
	$(function() {
		$('#drafts')
				.click(
						function() {
							if ('checked' == $('#isAuto').attr("checked")&&$('#senddate').val()=='') {
								alert("定时发送日期不可为空！");
								$('#senddate').focus();
								return ;
							}else{
								
								var srForm = document.getElementById("innermsg_form");
								srForm.action = 'innermsg!saveDraft.do';
								editor.sync();
								srForm.submit();
							}
						});
	});
</script>
</body>

</html>





