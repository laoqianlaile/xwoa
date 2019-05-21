<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><c:out value="pcpunishStandard.edit.title" /></title>
<meta name="decorator" content='${LAYOUT}' />
<%-- <sj:head locale="zh_CN" /> --%>
<script src="${pageContext.request.contextPath}/scripts/suggest.js"
	type="text/javascript"></script>
</head>

<body onload="changPaperType();doChange();">
	<p class="ctitle">
		<c:out value="pcpunishStandard.edit.title" />
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="pcpunishStandard" method="post" namespace="/powerbase"
		styleId="pcpunishStandardForm"
		>
		<s:submit method="save" cssClass="btn" key="opt.btn.save"></s:submit>
		<input type="button" Class="btn" onclick="window.history.back()"
			value="返回" />
		<input id="itemId" type="hidden" name="itemId"
			value="${object.itemId}" />
		<input id="version" type="hidden" name="version"
			value="${object.version}" />
		<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
			<legend style="padding: 4px 8px 3px;">
				<b>处罚项目管理</b>
			</legend>
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">

				
				<tr>
					<td class="addTd">处罚种类选择</td>
					<td align="left"><c:if test="${ empty object.punishtypeid  }">
							<select name="punishtypeid" onchange="changPaperType();">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:DICTIONARY('punishType')}">
									<option value="${row.key}"
										<c:if test="${object.punishtypeid eq row.value}">selected="selected"</c:if>>
										<c:out value="${row.value}" />
									</option>
								</c:forEach>
							</select>
							<span style="color: red">*</span>
						</c:if> <c:if test="${not empty object.punishtypeid }">
							<c:forEach var="row" items="${cp:DICTIONARY('punishType')}">
								<c:if test="${object.punishtypeid eq row.key}">
									<c:set var="punishtypename" value="${row.value}" />
								</c:if>
							</c:forEach>
				   	 ${punishtypename }
				   	 <input type="hidden" name="punishtypeid" id="punishtypeid"
								value="${object.punishtypeid}" />
						</c:if>
						<div id="israteDiv" style="display: none;">
							<s:checkbox name="punishTypeStr" fieldValue="%{object.punishtype}"
								onclick="doChange();" id="israte">按比例基数计算 </s:checkbox>
						</div></td>
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
					<td align="left" colspan="3"><s:textarea name="remark"
							value="%{object.remark}" /></td>
				</tr>
			</table>
		</fieldset>

	</s:form>

	<script type="text/javascript">
		var _get = function(id) {
			return document.getElementById(id);
		};

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
				if(punishtypeid.value =="0000000037" ){
    				_get("israteDiv").style.display = "block";

    			}
    			if(punishtypeid.value !="0000000037" ){
    				_get("israteDiv").style.display = "none";
    			}
			} else {
				_get("toplimit_tr").style.display = "none";
				_get("lowlimit_tr").style.display = "none";
				_get("toplimit_unit_tr").style.display = "none";
				_get("lowlimit_unit_tr").style.display = "none";
				_get("base_Name_tr").style.display = "none";
				_get("base_lowlimit_tr").style.display = "none";
				_get("base_toplimit_tr").style.display = "none";
				_get("base_unit_tr").style.display = "none";
				_get("israteDiv").style.display = "none";
				/* _get("punishmax_tr").style.display = 'none'; */
			}
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
	</script>
</body>
</html>
