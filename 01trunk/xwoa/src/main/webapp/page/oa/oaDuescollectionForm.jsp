<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><s:text name="oaDuescollection.edit.title" /></title>
</head>
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

<body class="sub-flow">
	<fieldset class="form">
		<legend>
			<s:text name="oaDuescollection.edit.title" />
		</legend>
		<%@ include file="/page/common/messages.jsp"%>

		<s:form action="oaDuescollection" method="post" namespace="/oa"
			id="oaDuescollectionForm" onsubmit="return checkform();">
			<s:submit name="save" method="save" cssClass="btn" key="opt.btn.save" />
			<input type="button" name="back" Class="btn" onclick="history.back(-1);" value="返回" />
			<%-- <s:submit type="button" name="back" cssClass="btn" key="opt.btn.back" /> --%>
			<input type="hidden" id="djId" name="djId" value="${object.djId}" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd"><s:text
							name="oaDuescollection.transaffairname" /><span style="color: red">*</span></td>
					<td align="left" colspan="3"><s:textfield
							name="transaffairname" id="transaffairname" style="width:100%;height:30px;" /></td>
					</tr>
					<tr>
					<td class="addTd"><s:text name="oaDuescollection.endtime" /><span style="color: red">*</span></td>
					<td align="left" colspan="3"><input type="text" class="Wdate"
						id="endtime" readonly="readonly"
						value='<fmt:formatDate value="${object.endtime}" pattern="yyyy-MM-dd"/>'
						name="endtime"
						style="height: 30px; width: 145px; border-radius: 3px; border: 1px solid #cccccc;"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss '})" placeholder="选择日期">
					</td>
					
				</tr>
				
				<tr>
					<td class="addTd"><s:text name="oaDuescollection.remark" /><span style="color: red">*</span></td>
					<td align="left" colspan="3"><s:textarea name="remark" id="remark"
							style="height:200px;width:100%" /></td>
				</tr>
					<tr id="tr_sendper">
					<td class="addTd"><s:text name="oaDuescollection.sendpersens" /><span style="color: red">*</span></td>
					<td colspan="3"><input type="text" readonly="readonly"
						id="send_persen_name" name="sendpersennames" value="${object.sendpersennames}"
						class="focused" style="width:100%;height:40px;" > 
						<input id="send_pers_usercode" type="hidden" name="sendpersens" value="${object.sendpersens}"/>
						</td>
				</tr>
				<c:if test="${empty object.djId}">
				<tr>
				    <td class="addTd">事件选择</td>
				    <td colspan="3">
				    <input type="checkbox"  name="sendinfo" value="T"
					<c:if test="${object.sendinfo eq 'T' }">checked="checked"</c:if> />发送通知
					<input type="checkbox"  name="sendshortnew" value="T"
					<c:if test="${object.sendshortnew eq 'T' }">checked="checked"</c:if> />发送短信
					<input type="checkbox"  name="sendnotice" value="T"
					<c:if test="${object.sendnotice eq 'T' }">checked="checked"</c:if> />发送提醒
					<input type="checkbox"  name="sendemail" value="T"
					<c:if test="${object.sendemail eq 'T' }">checked="checked"</c:if> />发送邮件
				    </td>
				    </tr>
				</c:if>
			</table>
			<!-- 选择人员的模块 -->
			<div id="attAlert" class="alert" style="width: 600px; height: 335px; position: absolute; top: 20px; left: 20%; overflow: hidden;">
				<h4>
					<span>选择</span><span id="close2" style="float: right; margin-right: 8px;" class="close" onclick="closeAlert('attAlert');">关闭</span>
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
	<script type="text/javascript">
	function checkform(){
		var flag = true;
		if ($("#transaffairname").val() == '') {
			alert("标题不可为空！");
			$('#transaffairname').focus();
			flag = false;
			return flag;
		}
		if ($("#endtime").val() == '') {
			alert("截止时间不可为空！");
			$('#endtime').focus();
			flag = false;
			return flag;
		}
		if ($("#remark").val() == '') {
			alert("正文说明不可为空！");
			$('#remark').focus();
			flag = false;
			return flag;
		}
		if ($("#send_persen_name").val() == '') {
			alert("发送人员不可为空！");
			$('#send_persen_name').focus();
			flag = false;
			return flag;
		}
		return flag;
	}
	
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
	$("#send_persen_name").click(
			function() {
				var d = '${userjson}';
				if (d.trim().length) {
					selectPopWin(jQuery.parseJSON(d),
							$("#send_persen_name"),
							$("#send_pers_usercode"));
				}
				;
			}); 
	function selectPopWin(json, o1, o2, oShow) {
		new treePerson(json, o1, o2, oShow).init();
		setAlertStyle("attAlert");
	}

	function groupBy(type, o1, o2){
		
		if('unit' == type){
			createList(jQuery.parseJSON('${userjson}'),o1,o2);
		}else if('station' == type){
			createList(jQuery.parseJSON('${stationUsers}'),o1,o2);
		}if('unitLeader' == type){
			createList(jQuery.parseJSON('${unitLeaderuserjson}'),o1,o2);
		}
	}

</script>