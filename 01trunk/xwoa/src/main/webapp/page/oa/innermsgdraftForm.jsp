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
<link href="${pageContext.request.contextPath}/newStatic/css/alertDiv.css"
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
<title>我的消息</title>
</head>
<body class="sub-flow">
	<fieldset class="form">
		<legend>
		编辑${cp:MAPVALUE("msgtype",s_msgtype)}
		</legend>

		<%@ include file="/page/common/messages.jsp"%>

		<form id="innermsg_form"
			action="${pageContext.request.contextPath}/oa/innermsg!saveMsg.do"
			method="post" data-validate="true">
<div class="formButtonOfMail">
				<input type="hidden" id="isShow" value="${isShow}">
				
				<input type="hidden" id="originate" name="originate" value="${originate }" />
				
				<!-- 存放当前选择的人员选择框是哪个 -->
				<input type="hidden" id="boxType" />
				
				<c:if test="${empty from }">	
				<c:if test="${s_msgtype eq 'P' }">
					<button type="button"  class="btn btn-primary" id="drafts">保存草稿</button>
				</c:if>
				<button type="button" id="send" class="btn btn-primary">发送${cp:MAPVALUE("msgtype",object.msgtype)
					}</button>
					<!-- <input type="button" name="back" Class="btn"
						onclick="history.back(-1);" value="返回" /> -->
				</c:if>
				<c:if test="${not empty from and from=='public'}">
					<input type="hidden" id="cla" value="${from}" />
					<c:if test="${s_msgtype eq 'P' }">
					<input type="button"  class="whiteBtnWide" id="drafts" value="保存草稿"></input>
				</c:if>
					<input type="button" id="send" class="whiteBtnWide" value="发送${cp:MAPVALUE('msgtype',object.msgtype)}"></input>
				</c:if>

			</div>
			<input id="hid_innermsg_mail_add" type="hidden" name="filecodes" />

			<input type="hidden" name="msgcode" value="${object.msgcode}" /> <input
				type="hidden" id="msgtype" name="msgtype" value="${s_msgtype}" /> <input
				type="hidden" id="s_msgtype" name="s_msgtype" value="${s_msgtype }" />
			<input type="hidden" name="s_mailtype" value="${s_mailtype}" />

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			
				<tr id="tr_receive">
					<td class="addTd" ><font color='red'>*</font>收件人：</td>
					<td align="left"><input type="text" readonly="readonly"  data-rule-maxlength="1200" 
						id="txa_innermsg_receive_name" name="receivename" rows="1"
						cols="1" style="width: 80%;" value="${object.receivename }"
						 style="width: 80%;" /> 
						<input id="txt_innermsg_receive_usercode" type="hidden" name="to"
						 value="${toUserVaule }" /> <br />
						</td>
				</tr>
				<tr id="tr_cc">
					<td class="addTd">抄送：</td>
					<td align="left"><input type="text" id="txa_cc_name" readonly="readonly"  data-rule-maxlength="1200" 
						name="ccName" rows="1" cols="1" style="width: 80%;"
						value="${object.ccName }" style="width: 80%;" /> <input
						id="txt_cc_usercode" type="hidden" name="cc"
						 value="${ccUserVaule }" /></td>
				</tr>

				<tr id="tr_bcc">
					<td class="addTd">密送：</td>
					<td align="left"><input type="text" id="txa_bcc_name" readonly="readonly"  data-rule-maxlength="1200" 
						name="bccName" rows="1" cols="1" style="width: 80%;"
						value="${object.bccName }" style="width: 80%;" /> 
						<input id="txt_bcc_usercode" type="hidden" name="bcc"
						value="${bccUserVaule }" /></td>
				</tr>
				<c:if test="${s_msgtype eq 'P'}" >
				<tr><td  class="addTd"></td>
					<td  align="left">
						 <span id="span_add_cc">添加抄送</span>-<span
						id="span_add_bcc">添加密送</span>
					</td></tr>
	             <tr></c:if>
					<td class="addTd"><font color='red'>*</font>标题：</td>
					<td align="left"><input type="text" name="msgtitle"
						id="msgtitle" value="${object.msgtitle }"
					data-rule-maxlength="40" style="width: 80%;" /></td>
				</tr>
				<tr>
					<td class="addTd">附件：</td>
					<td align="left" colspan="3"><c:if
							test="${not empty object.msgannexs }">
							<c:forEach var="fi" items="${object.msgannexs }">
								<div style="margin-bottom: 10px;">
									<a target="download" data-filecode="${fi.fileinfo.filecode}">${fi.fileinfo.filename}.${fi.fileinfo.fileext}</a>
									<a
										href="${pageContext.request.contextPath}/oa/innermsg!deletefile.do?filecode=${fi.fileinfo.filecode}
								&msgcode=${object.msgcode}&s_msgtype=${object.msgtype}&s_mailtype=${s_mailtype}"
										class="delete" inputid="hid_innermsg_mail_add">删除</a>
								</div>
							</c:forEach>
						</c:if> <input type="file" id="upload-fileinfo" data-opt-id="MSG"
						data-input-id="hid_innermsg_mail_add" /></td>
				</tr>
				
				<tr style="height:450px">
					<td class="addTd">内容：</td>
					<td align="left"><textarea name="msgcontent" id="msgcontent"
							cols="40" rows="2" style="width: 100%;height:499px">${object.msgcontent}</textarea>
					</td>
				</tr>
				<tr>

			</table>
			

			<!-- 选择人员的模块 -->
			<div id="attAlert" class="alert"
				style="width: 600px; height: 375px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
				<h4>
					<span id="selectTT">选择分类</span><span id="close2"
						style="float: right; margin-right: 8px;" class="close"
						onclick="closeAlert('attAlert');">关闭</span>
				</h4>
				
				<!-- <div class="userTabDiv">
					<div onclick="tab(this,'unit')" class="select">部门</div>
					<div onclick="tab(this,'station')">岗位</div>
					<div onclick="tab(this,'unitLeader')">分管领导组</div>
				</div> -->

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
						<b class="btns"> <input id="save" class="btn" type="button"
							value="保     存" /><input id="clear" class="btn" type="button"
							value="取     消" style="margin-top: 5px;" /></b>
					</div>
				</div>
			</div>

		</form>
	</fieldset>
</body>
<script type="text/javascript">
	  //抄送
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
	  //密送
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
				$('#boxType').val('tr_receive');
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
				$('#boxType').val('tr_cc');
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d), $("#txa_cc_name"),
							$("#txt_cc_usercode"));
				}
				;
			});

	$("#txa_bcc_name").click(
			function() {
				$('#boxType').val('tr_bcc');
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
	
	//tab切换
	function tab(th, type){
		$(th).siblings('div').removeClass('select');
		$(th).addClass('select');
		
		var boxType = $('#boxType').val();
		if('station' == type){
			
			if('tr_cc' == boxType){
				groupBy('station', $("#txa_cc_name"),$("#txt_cc_usercode"));
			}else if('tr_bcc' == boxType){
				groupBy('station', $("#txa_bcc_name"),$("#txt_bcc_usercode"));
			}else{
				groupBy('station', $("#txa_innermsg_receive_name"),$("#txt_innermsg_receive_usercode"));				
			}
		}else if('unitLeader' == type){
			if('tr_cc' == boxType){
				groupBy('unitLeader', $("#txa_cc_name"),$("#txt_cc_usercode"));
			}else if('tr_bcc' == boxType){
				groupBy('unitLeader', $("#txa_bcc_name"),$("#txt_bcc_usercode"));
			}else{
				groupBy('unitLeader', $("#txa_innermsg_receive_name"),$("#txt_innermsg_receive_usercode"));				
			}
		}else{
			if('tr_cc' == boxType){
				groupBy('unit', $("#txa_cc_name"),$("#txt_cc_usercode"));
			}else if('tr_bcc' == boxType){
				groupBy('unit', $("#txa_bcc_name"),$("#txt_bcc_usercode"));
			}else{
				groupBy('unit', $("#txa_innermsg_receive_name"),$("#txt_innermsg_receive_usercode"));				
			}
		}
	}
	
	// 点击按钮后分别展示的数据
	function groupBy(type, o1, o2){
		
		if('unit' == type){
			createList(jQuery.parseJSON('${userjson}'),o1,o2);
		}else if('station' == type){
			createList(jQuery.parseJSON('${stationUsers}'),o1,o2);
		}if('unitLeader' == type){
			createList(jQuery.parseJSON('${unitLeaderuserjson}'),o1,o2);
		}
	}
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
		
		// 默认页面人员选择框是收件人选择框
		$('#boxType').val('tr_receive');

		var originate = $('#originate').val();
		$('#drafts').click(function() {
			if ('checked' == $('#isAuto').attr("checked")&&$('#senddate').val()=='') {
				alert("定时发送日期不可为空！");
				$('#senddate').focus();
				return ;
			}else{
				
				var srForm = document.getElementById("innermsg_form");
				srForm.action = 'innermsg!saveDraft.do';
				editor.sync();
				if($("#cla").val()!=null||$("#isShow").val()!=null){
					 if(window.confirm("是否确认保存邮件？", "确认")){
						srForm.submit();
						if("usersOnline" != originate){
							DialogUtil.close();							
						}
					 }else{
						 return;
					 }
						 
				}else{
					srForm.submit();
				}
			}
			
		});
	
	$('#send').click(function() {
		
		if ($.trim($('#txa_innermsg_receive_name').val())=='') {
			alert("收件人不可为空！");
			return ;
		}else if($.trim($('#msgtitle').val())==''){
			alert("标题不可为空！");
			return ;
		}else{
			var srForm = document.getElementById("innermsg_form");
			if($("#cla").val()!=null||$("#isShow").val()!=null){
				 if(window.confirm("是否确认发送邮件？", "确认")){
					srForm.submit();
					if("usersOnline" != originate){
						DialogUtil.close();							
					}
				 }else{
					 return;
				 }
					 
			}else{
				srForm.submit();
			}
		}
		
	});
	});
</script>
</body>

</html>