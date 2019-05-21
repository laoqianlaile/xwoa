<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><s:text name="oaMeetingmaterial.edit.title" /></title>
</head>

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



<body class="sub-flow">
	<fieldset class="form">
		<legend class="headTitle">
			编辑会议资料
		</legend>
		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaMeetingmaterial" method="post" namespace="/oa"
			id="oaMeetingmaterialForm">
			<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
			<input type="button" class="btn" value="返回" onclick="renturns()">
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">议程名称<span
						style="color: red">*</span></td>
					<td align="left" colspan="3"><s:textfield name="meetingName"
							style="width:100%;height:30px;" /></td>
				</tr>

				<%-- <tr>
					<td class="addTd"><s:text
							name="oaMeetingmaterial.meetingAgenda" /><span
						style="color: red">*</span></td>
					<td align="left" colspan="3"><s:textarea name="meetingAgenda"
							style="width:100%;height:150px;" /></td>
				</tr> --%>
					<tr id="tr_receive">
					<td class="addTd"><s:text
							name="oaMeetingmaterial.meetingAttendees" /><span
						style="color: red">*</span></td>
					<td align="left" colspan="3"><input type="text" readonly="readonly"
						id="meetingAttendees" name="meetingAttendees" value="${object.meetingAttendees }"
						class="focused" style="width:100%;height:30px;" > 
						<input id="meetingAttendeesCodes" type="hidden" name="meetingAttendeesCodes"
						 value="${meetingAttendeesCodes }" />
						</td>
				</tr>				
				<%-- <tr>
					<td class="addTd"><s:text
							name="oaMeetingmaterial.meetingUnitnames" /></td>
					<td align="left" colspan="3"><s:textarea
							name="meetingUnitnames" style="width:100%;height:30px;" /></td>
				</tr>
        
				<tr>
					<td class="addTd"><s:text
							name="oaMeetingmaterial.meetingComein" /></td>
					<td align="left"><s:textarea name="meetingComein"
							style="height:30px;" /></td>
					<td class="addTd"><s:text
							name="oaMeetingmaterial.meetingComeindeps" /></td>
					<td align="left"><s:textarea name="meetingComeindeps"
							style="height:30px;" /></td>
				</tr>

				<tr>
					<td class="addTd"><s:text
							name="oaMeetingmaterial.meetingAddress" /><span
						style="color: red">*</span></td>
					<td align="left"><s:textarea name="meetingAddress"
							style="height:30px;" /></td>
					<td class="addTd"><s:text name="oaMeetingmaterial.meetingTime" /><span
						style="color: red">*</span></td>
					<td align="left"><input type="text" class="Wdate"
						id="meetingTime" readonly="readonly"
						value='<fmt:formatDate value="${object.meetingTime}" pattern="yyyy-MM-dd"/>'
						name="meetingTime"
						style="height: 30px; width: 300px!important; border: 1px solid #cccccc;"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss '})" placeholder="选择日期">
					</td>
				</tr>
				<tr>
					<td class="addTd"><s:text
							name="oaMeetingmaterial.meetingRemark" /></td>
					<td align="left" colspan="3"><s:textarea name="meetingRemark"
							style="height:100px;width:100%" /></td>
				</tr> --%>
			</table>
			
						<!-- 选择人员的模块 -->
			<div id="attAlert" class="alert"
				style="width: 600px; height: 375px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
				<h4>
					<span id="selectTT">选择分类</span><span id="close2"
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
						<b class="btns"> <input id="save" class="btn" type="button"
							value="保     存" /><input id="clear" class="btn" type="button"
							value="取     消" style="margin-top: 5px;" /></b>
					</div>
				</div>
			</div>
		</s:form>
	</fieldset>
	<fieldset class="_new">
			<legend style="padding:4px 8px 3px;">
				<b>材料上传</b>
			</legend>
			<iframe id="basicsj" name="sjfj" width="100%" height="" frameborder="no" scrolling="false" border="0" marginwidth="0" marginheight="0"
				src="<%=request.getContextPath()%>/powerruntime/generalOperator!gotosqclOfBookPage.do?stuffInfo.djId=${object.djId}&stuffInfo.nodeInstId=0&stuffInfo.groupid=394"
				onload="this.height=window.frames['sjfj'].document.body.scrollHeight">
			</iframe>
	</fieldset>
	<script type="text/javascript">
	$(function(){
		$("#meetingAttendees").click(
				function() {
					var d = '${userjson}';
					if (d.trim().length) {
						selectPopWin(jQuery.parseJSON(d),
								$("#meetingAttendees"),
								$("#meetingAttendeesCodes"));
					}
					;
				});
	});
	
	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert");
	}
	
	//返回按钮
	function renturns(){
		window.location.href='${pageContext.request.contextPath}/oa/oaMeetingmaterial!list.do';
	}
</script>