<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>资源管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/scripts/plugin/treetable/Treetable_files/jqtreetable.css" />

</head>
<body data-controller="EquipmentUsingController"
	data-deps='["jquery", "Validate"]'>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<c:if test="${ empty useRequestId}">
		新增使用信息
	        </c:if>
				<c:if test="${ not empty useRequestId}">
		使用信息
	        </c:if>
			</p>
		</legend>


		<%@ include file="/page/common/messages.jsp"%>
		<form method="post"
			action="${pageContext.request.contextPath}/oa/equipmentUsing!save.do"
			class="form-horizontal" id="equipmentUsingForm" data-validate="true">

			<input type="hidden" id=equipmentState name="equipmentState"
				value="${equipmentState}" /> <input type="hidden" id=useRequestId
				name="useRequestId" value="${useRequestId}" /> <input type="hidden"
				id=supEquipmentType name="s_supEquipmentType"
				value="${s_supEquipmentType}" /> <input type="hidden"
				id=equipmentType name="s_equipmentType" value="${s_equipmentType}" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">资产名称：</td>
					<td align="left" colspan="3"><c:if
							test="${not empty equipmentId}">
							<input type="hidden" id=equipmentId name="equipmentId"
								value="${equipmentId}" />
						${object.equipmentInfo.equipmentName }
					</c:if> <c:if test="${ empty equipmentId}">
							<input type="text" id="equipmentName" value="${equipmentName }" />
							<input type="hidden" id="equipmentId" value="${equipmentId }" name="equipmentId" />
							<!-- <span class="add-on clearInput"
								refInput="#equipmentId,#equipmentName" title="点击清空">清空</span> -->
								<input type="button" class='btn' name="powerBtn" onClick="openNewWindow('${pageContext.request.contextPath}/oa/equipmentInfo!listSmall.do?s_supEquipmentType=${s_supEquipmentType}&s_equipmentType=${s_equipmentType}','powerWindow',null);" value="选择">
						</c:if>
						</td>
				</tr>
				<c:if test="${not empty equipmentState}">
					<tr>
						<td colspan="4" align="left">申请信息</td>
					</tr>
					<tr>
						<td class="addTd">申请人：</td>
						<td align="left" colspan="3">${cp:MAPVALUE('usercode',applicant)}</td>
					</tr>
					<tr>
						<td class="addTd">申请时间：</td>
						<td align="left"><fmt:formatDate value='${applicantTime}'
								pattern='yyyy-MM-dd HH:mm:ss' /></td>
						<td class="addTd">用途类别：</td>
						<td align="left">${cp:MAPVALUE("oaPurType",purposeType)}</td>
					</tr>
					<tr>
						<td class="addTd">预计开始时间：</td>
						<td align="left"><fmt:formatDate value='${planBeginTime}'
								pattern='yyyy-MM-dd HH:mm:ss' /></td>
						<td class="addTd">预计结束时间：</td>
						<td align="left"><fmt:formatDate value='${planEndTime}'
								pattern='yyyy-MM-dd HH:mm:ss' /></td>
					</tr>
					<tr>
						<td class="addTd">用途说明：</td>
						<td align="left" colspan="3">${purposeDesc }</td>
					</tr>

				</c:if>
				<c:if test="${ equipmentState>='2'}">
					<tr>
						<td colspan="4" align="left">确认信息</td>
					</tr>
					<tr>
						<td class="addTd">确认时间：</td>
						<td align="left"><fmt:formatDate value='${auditTime}'
								pattern='yyyy-MM-dd HH:mm:ss' /></td>
						<td class="addTd">是否接受申请：</td>
						<td align="left"><c:if
								test="${beAccepted=='0' || empty beAccepted}">否</c:if> <c:if
								test="${beAccepted=='1' }">是</c:if></td>
					</tr>
					<tr>
						<td class="addTd">确认人：</td>
						<td align="left" colspan="3">${cp:MAPVALUE('usercode',auditor)}</td>
					</tr>

				</c:if>
				<c:if test="${ equipmentState>='3'}">
					<c:if test="${ beAccepted=='1'}">
						<tr>
							<td colspan="4" align="left">反馈信息</td>
						</tr>
						<tr>
							<td class="addTd">反馈时间：</td>
							<td align="left"><fmt:formatDate value='${feedbackTime}'
									pattern='yyyy-MM-dd HH:mm:ss' /></td>
							<td class="addTd">使用反馈人：</td>
							<td align="left">${cp:MAPVALUE('usercode',feedbackUser)}</td>
						</tr>
						<tr>
							<td class="addTd">是否实际使用：</td>
							<td align="left"><c:if
									test="${beUsed=='0' || empty beUsed}">否</c:if> <c:if
									test="${beUsed=='1' }">是</c:if></td>
							<td class="addTd">使用费用：</td>
							<td align="left"><fmt:formatNumber
									value="${yearlyDepreciation }" type="number" /></td>
						</tr>
						<tr>
							<td class="addTd">实际开始时间：</td>
							<td align="left"><fmt:formatDate value='${beginTime}'
									pattern='yyyy-MM-dd HH:mm:ss' /></td>
							<td class="addTd">实际结束时间：</td>
							<td align="left"><fmt:formatDate value='${endTime}'
									pattern='yyyy-MM-dd HH:mm:ss' /></td>
						</tr>
						<tr>
							<td class="addTd">实际使用说明：</td>
							<td align="left" colspan="3">${useDesc }</td>
						</tr>

					</c:if>
				</c:if>
			</table>
			<c:if test="${ empty equipmentState}">
				<legend class="ctitle" style="width: auto; margin-bottom: 10px;">使用申请</legend>
				<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
					<tr>
						<td class="addTd">用途类别：</td>
						<td align="left" colspan="3"><select id="purposeType"
							name="purposeType">
								<c:forEach var="dt" items="${cp:DICTIONARY('oaPurType')}">
									<option value="${dt.id.datacode}">${dt.datavalue }</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td class="addTd">预计开始时间：</td>
						<td align="left"><input type="text" name="planBeginTime"
							id="planBeginTime"
							value='<c:choose><c:when test="${not empty param['planBeginTime'] }">${param["planBeginTime"] }</c:when><c:otherwise><fmt:formatDate value='${planBeginTime}' pattern='yyyy-MM-dd HH:mm:ss'/></c:otherwise></c:choose>'
							class="required Wdate " data-type="time"
							data-rule-remote="${contextPath}/oa/equipmentUsing!isTFree.do"
							data-remote-param="checkIsFree" /></td>
						<td class="addTd">预计结束时间：</td>
						<td align="left"><input type="text" name="planEndTime"
							id="planEndTime"
							value='<c:choose><c:when test="${not empty param['planEndTime'] }">${param["planEndTime"] }</c:when><c:otherwise><fmt:formatDate value='${planEndTime}' pattern='yyyy-MM-dd HH:mm:ss'/></c:otherwise></c:choose>'
							class="required Wdate" data-type="time"
							data-rule-gtdate="#planBeginTime"
							data-rule-remote="${contextPath}/oa/equipmentUsing!isTFree.do"
							data-remote-param="checkIsFree" /></td>
					</tr>
					<tr>
						<td class="addTd">用途说明：</td>
						<td align="left" colspan="3"><textarea id="purposeDesc"
								name="purposeDesc" rows="5" cols="50">${purposeDesc}
										</textarea></td>
					</tr>
				</table>
			</c:if>
			<c:if test="${ equipmentState=='1'}">
				<legend class="ctitle" style="width: auto; margin-bottom: 10px;">申请确认</legend>
				<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
					<tr>
						<td class="addTd">是否接受申请：</td>
						<td align="left"><input type="radio" name="beAccepted"
							value="0"
							<c:if test="${beAccepted=='0' || empty beAccepted}"> checked="checked"</c:if> />否
							<input type="radio" name="beAccepted" value="1"
							<c:if test="${beAccepted=='1' }">checked="checked"</c:if> />是</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${ equipmentState=='2'}">

				<legend class="ctitle" style="width: auto; margin-bottom: 10px;">使用反馈</legend>
				<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
					<tr>
						<td class="addTd">是否实际使用：</td>
						<td align="left" id="td_beUsed"><input type="radio"
							id="radio_unbeUsed" name="beUsed" value="0"
							<c:if test="${beUsed=='0' || empty beUsed}" > checked="checked"</c:if> />否
							<input type="radio" id="radio_beUsed" name="beUsed" value="1"
							<c:if test="${beUsed=='1'  }">checked="checked"</c:if> />是</td>
						<td class="addTd">使用费用：</td>
						<td align="left"><input type="text" name="useCost"
							id="useCost" class="focused" value="${useCost }" /></td>
					</tr>
					<tr id="tr_beginTime">
						<td class="addTd">实际开始时间：</td>
						<td align="left"><input type="text" name="beginTime"
							id="beginTime"
							value="<fmt:formatDate value='${beginTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
							class="Wdate focused {required: true}" data-type="time" /></td>
						<td class="addTd">实际结束时间：</td>
						<td align="left"><input type="text" name="endTime"
							id="endTime"
							value="<fmt:formatDate value='${endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
							class="Wdate {required: true}" data-type="time"
							data-rule-gtdate="#beginTime" /></td>
					</tr>
					<tr>
						<td class="addTd">实际使用说明：</td>
						<td align="left" colspan="3"><textarea id="useDesc"
								name="useDesc" rows="5" cols="50">${useDesc }</textarea></td>
					</tr>
				</table>
			</c:if>
			<div class="formButton">
				<input type="button" name="back" Class="btn"
					onclick="history.back(-1);" value="返回" />
				<c:if test="${ equipmentState !='3'}">
					<button type="submit" class="btn btn-primary">确认</button>
				</c:if>
			</div>
		</form>

	</fieldset>
</body>

<%-- <%@ include file="/page/common/charisma-js.jsp" %> --%>
<%@ include file="/page/common/scripts.jsp"%>
<script type="text/javascript">
function openNewWindow(winUrl,winName,winProps){
	if(winProps =='' || winProps == null){
		winProps = 'height=600px,width=700px,directories=false,location=false,top=100,left=500,menubar=false,Resizable=yes,scrollbars=yes,toolbar=false';
	}
	window.open(winUrl, winName, winProps);
}
	function checkIsFree() {
		return {
			planEndTime : $.trim($("#planEndTime").val().trim()),
			planBeginTime : $.trim($("#planBeginTime").val()),
			equipmentId : $.trim($("#equipmentId").val())
		};
	}

	$(function() {
		initHide();
	});

	$("planBeginTime").change(function() {
		$('#planEndTime').val($('#planEndTime').val() + ' ');
	});

	var initHide = function() {
		$("#tr_beginTime").hide();
		$('#beginTime').attr('disabled', 'disabled');
		$('#endTime').attr('disabled', 'disabled');
	};

	$("#radio_unbeUsed").click(initHide);
	$("#radio_beUsed").click(function() {
		$("#tr_beginTime").show();
		$('#beginTime').removeAttr('disabled');
		$('#endTime').removeAttr('disabled');
	});
</script>
</html>
