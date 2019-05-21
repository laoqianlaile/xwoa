<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		if (_get("punishtype").value = "2") {
			_get("punishtype").checked = true;
		}
	});
</script>
<title></title>
<%-- <sj:head locale="zh_CN" /> --%>
</head>
<c:if test="${isPcpunishStandard eq 1}">
	<body onload="changPaperType(); doChange();">
</c:if>
<c:if test="${isPcpunishStandard eq 0}">
	<body>
</c:if>

<%@ include file="/page/common/messages.jsp"%>
<input id="isPcpunishStandard" type="hidden" name="isPcpunishStandard"
	value="${isPcpunishStandard}">

<c:if test="${isPcpunishStandard eq 1}">
	<s:form action="pcfreeumpiretype!save.do" method="post"
		namespace="/powerbase" name="pcfreeumpiretypeForm"
		id="pcfreeumpiretypeForm">
		<input type="hidden" name="itemId" id="itemId"
			value="${pcfreeumpiredegree.itemId}" />
		<input type="hidden" name="version" id="version"
			value="${pcfreeumpiredegree.version}" />
		<input type="hidden" name="degreeno" id="degreeno"
			value="${object.degreeno}" />
		<input type="hidden" name="standardNo" id="standardNo"
			value="${pcfreeumpiredegree.standardNo}" />
		<%--  <input type="hidden"  name="israte"  id="israte"  value="${pcpunishStandard.israte}" />  --%>
		<input id="isEdit" type="hidden" name="isEdit" value="${isEdit}" />
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

			<c:forEach var="row" items="${cp:DICTIONARY('punishType')}">
				<c:if test="${object.punishtypeid eq row.key}">
					<c:set var="punishtypename" value="${row.value}" />
				</c:if>
			</c:forEach>
			<tr>
				<td class="addTd">处罚种类选择</td>
				<td align="left"><c:if test="${empty object.punishtypeid }">
						<select name="punishtypeid" id="punishtypeid"
							onchange="changPaperType();">
							<option value="">---请选择---</option>
							<c:forEach var="row" items="${flowDescribesList}">
								<option value="${row.value}"
									<c:if test="${object.punishtypeid eq row.value}"> selected = "selected" </c:if>>
									<c:out value="${row.label}" />
								</option>
							</c:forEach>
						</select>
						<span style="color: red">*</span>
					</c:if> <c:if test="${not empty object.punishtypeid }">
						<input type="hidden" name="punishtypeid" id="punishtypeid"
							value="${object.punishtypeid}" />
				${punishtypename}
				</c:if>

					<div id="israteDiv" style="display: none;">
						<s:checkbox name="punishTypeStr" fieldValue="%{object.punishtype}"
							onclick="doChange();" id="israte">按比例基数计算 </s:checkbox>
					</div></td>

				</td>
			</tr>
			<tr id="lowlimit_tr">
				<td class="addTd">处罚下限</td>
				<td align="left"><s:textfield name="lowlimit"
						value="%{object.lowlimit}" /></td>
			</tr>
			<tr id="lowlimit_unit_tr">
				<td class="addTd">处罚下限单位</td>
				<td class="addTd"><s:textfield name="lowlimitUnit"
						value="%{object.lowlimitUnit}" /></td>
			</tr>
			<tr id="toplimit_tr">
				<td class="addTd">处罚上限</td>
				<td align="left"><s:textfield name="toplimit"
						value="%{object.toplimit}" /></td>
			</tr>
			<tr id="toplimit_unit_tr">
				<td class="addTd">处罚上限单位</td>
				<td align="left"><s:textfield name="toplimitUnit"
						value="%{object.toplimitUnit}" /></td>
			</tr>
			<tr id="base_Name_tr">
				<td class="addTd">基数名称</td>
				<td align="left"><s:textfield property="baseName"
						value="%{object.baseName}" /></td>
			</tr>
			<tr id="base_lowlimit_tr">
				<td class="addTd">基数倍数下限</td>
				<td align="left"><s:textfield name="baseLowlimit"
						value="%{object.baseLowlimit}" /></td>
			</tr>
			<tr id="base_toplimit_tr">
				<td class="addTd">基数倍数上限</td>
				<td align="left"><s:textfield name="baseToplimit"
						value="%{object.baseToplimit}" /></td>
			</tr>

			<tr id="base_unit_tr">
				<td class="addTd">基数单位</td>
				<td class="addTd"><s:textfield name="baseUnit"
						value="%{object.baseUnit}" /></td>
			</tr>


			<tr>
				<td class="addTd">处罚内容</td>
				<td align="left" colspan="2"><s:textarea name="remark"
						value="%{object.remark}" /></td>
			</tr>
		</table>

		<%--  <s:submit  value="保存"  method="save" onclick="submitview();" cssClass="btn"  />   --%>
		<input type="button" onclick="saveForm()" Class="btn" value="保存" />
		<input type="button" Class="btn" onclick="back()" value="返回" />
	</s:form>
</c:if>




</body>


<script>
	var _get = function(id) {
		return document.getElementById(id);
	};

	//保存业务数据，在提交办理的时候，同时刷新两个iframe
	function submitview() {
		/*  window.parent.frames['view'].document.forms[0].submit(); */

		window.parent.frames['view'].location.reload();
		/*   parent.view.location.reload(); */

	}
	function saveForm() {
		$('#pcfreeumpiretypeForm').submit();
		//document.pcfreeumpiretypeForm.submit();
		var itemId= _get("itemId").value;
		var version = _get("version").value;
		var degreeno = _get("degreeno").value;
		var isPcpunishStandard = _get("isPcpunishStandard").value;
		var url;
		if (isPcpunishStandard == 1) {
			url = "powerbase/pcfreeumpiretype!list.do?itemId="
					+ itemId + "&version=" + version + "&degreeno=" + degreeno;
		}
		
		parent.document.location.href = url;
	}

	function docheck() {
		if ($("#punishtypeid").val() == '') {
			alert("请选择处罚种类");
			$('#punishtypeid').focus();
			return false;
		}
		return true;
	}

	//对不同的处罚种类对应不同的文本框
	function changPaperType() {

		var punishtypeid = document.getElementById("punishtypeid");

		if (punishtypeid.value == "0000000037"
				|| punishtypeid.value == "0000000038"
				|| punishtypeid.value == "0000000040") { //罚款、责令停产停业、行政拘留
			/* document.getElementById("punishDiv").style.display = 'block'; */
			_get("lowlimit_tr").style.display = "block";
			_get("toplimit_tr").style.display = "block";
			_get("toplimit_unit_tr").style.display = "block";
			_get("lowlimit_unit_tr").style.display = "block";
			_get("base_Name_tr").style.display = "none";
			_get("base_lowlimit_tr").style.display = "none";
			_get("base_toplimit_tr").style.display = "none";
			_get("base_unit_tr").style.display = "none";
			if (punishtypeid.value == "0000000037") {
				_get("israteDiv").style.display = "block";
			}
			if (punishtypeid.value != "0000000037") {
				_get("israteDiv").style.display = "none";
			}
		} else {
			_get("toplimit_tr").style.display = "none";
			_get("lowlimit_tr").style.display = "none";
			_get("toplimit_unit_tr").style.display = "none";
			_get("lowlimit_unit_tr").style.display = "none";
			_get("base_Name_tr").style.display = "none";
			_get("base_unit_tr").style.display = "none";
			_get("israteDiv").style.display = "none";
			/* _get("punishmax_tr").style.display = 'none'; */
		}

		parent.document.all("edit").style.height = document.body.scrollHeight;

	}
	function doChange() {

		if (_get('israte').value == 2) {
			_get('israte').checked = "checked";
		}
		if (_get('israte').checked) {
			_get("base_lowlimit_tr").style.display = "block";
			_get("base_toplimit_tr").style.display = 'block';
			_get("base_Name_tr").style.display = "block";
			_get("base_unit_tr").style.display = 'block';
		} else {
			_get("base_lowlimit_tr").style.display = "none";
			_get("base_toplimit_tr").style.display = 'none';
			_get("base_Name_tr").style.display = "none";
			_get("base_unit_tr").style.display = 'none';
		}
	}

	function back() {
		var itemId= _get("itemId").value;
		var version = _get("version").value;
		var href = "powerbase/pcpunishStandard!view.do?itemId=" + itemId + "&version=" + version;
		parent.location.href = href;
	}
</script>
</html>
