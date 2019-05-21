<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<%-- <sj:head locale="zh_CN" /> --%>
<title><s:text name="oaSchedule.edit.title" /></title>
<link href="${pageContext.request.contextPath}/themes/css/alertDiv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/alertDiv.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/arrowTree.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/themes/css/arrow.css"
	rel="stylesheet" type="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>通讯录</title>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="addressbooks.edit.title" />
			</p>
		</legend>


		<%@ include file="/page/common/messages.jsp"%>

		<form
			action="${pageContext.request.contextPath}/oa/addressbooks!save.do"
			method="post" id="addressbooksForm" data-validate="true">
			<input type="hidden" id="addrbookid" name="addrbookid"
				value="${addrbookid }" /> <input type="hidden" id="type"
				name="type" value="C" />
				<input type="hidden" id="unitcode"
				name="unitcode" value="${object.unitcode}" />
                <input type="hidden" id="bodycode"
				name="bodycode" value="${bodycode}" />
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
						<td class="addTd">姓名</td>
						<td align="left">
						<input type="text"
							name="userName" id="userName"  style="width:200px;height:25px;" class="focused required" value="${userName }" /> <span style="color: red">*</span></td>
				    <td class="addTd">性别</td>
						<td align="left"><select id="sex"  style="width:200px;height:25px;"
							name="sex">
								<option value="">---请选择---</option>
								<c:forEach var="row" items="${cp:DICTIONARY('sex')}">
									<option value="${row.datacode}"
										<c:if test="${row.datacode==sex}"> selected="selected"</c:if>>
										<c:out value="${row.datavalue}" />
									</option>
								</c:forEach>
						</select></td>
					
						
				</tr>
					<tr class='tr_addBookType_C'>
						<td class="addTd">部门</td>
						<td align="left" ><span>${deptName }</span>
						<input type="hidden" name="deptName" value="${deptName }" />
						</td>
						<td class="addTd">手机</td>
						<td align="left"><input type="text" name="mobilephone"  class="focused isphone"
							 style="width:200px;height:25px;" id="mobilephone" value="${mobilephone }" /></td>
						
					</tr>
			
				<tr>

					<td class="addTd">固定电话</td>
					<td align="left"><input type="text" name="telphone" class="focused isTelphone"
						 style="width:200px;height:25px;" id="telphone" value="${telphone }" /></td>
						<td class="addTd">Email</td>
					<td align="left" ><input type="text" name="email" class="email"
						id="email"  style="width:200px;height:25px;" value="${email }" /></td>
					
				</tr>
				<tr>
				<td class="addTd">其他联系方式</td>
					<td align="left"><input type="text"  style="width:200px;height:25px;"
						name="otherphone" id="otherphone" value="${otherphone }" /></td>
				
						<td class="addTd">备注</td>
					<td align="left" >
					  <input type="text" name="remark" 
						id="remark"  style="width:200px;height:25px;" value="${remark }" />
					  </td>

				</tr>
			</table>

			<div class="formButton">
				<button type="submit" class="btn btn-primary">保存</button>
				<input type="button" name="back" Class="btn"
					onclick="history.back(-1);" value="返回" />
			</div>
		</form>
	</fieldset>

</body>

<%@ include file="/page/common/scripts.jsp"%>
<script type="text/javascript">
	$(function() {

		$('#a_href').attr('height', window.screen.availHeight - 200);
		//是否共享
		function isShare() {
			if ('checked' == $('#isshare').attr("checked")) {
				$('#tr_share').show();
			} else {
				$('#tr_share').hide();
			}
		}

		$('#isshare').live("click", function() {
			isShare();
		});

		isShare();

		//通讯录类别

		function bodyType() {

			var v = $('#type').find('option:selected').val();

			if (v == '') {
				$('.tr_addBookType_P').hide();
				$('.tr_addBookType_O').hide();
				$('.tr_addBookType_C').hide();

			}

			if (v == 'P') {
				$('.tr_addBookType_P').show();
				$('.tr_addBookType_O').hide();
				$('.tr_addBookType_C').hide();

			}
			if (v == 'C') {
				$('.tr_addBookType_C').show();
				$('.tr_addBookType_O').hide();
				$('.tr_addBookType_P').hide();

			}
			if (v == 'O') {
				$('.tr_addBookType_O').show();
				$('.tr_addBookType_P').hide();
				$('.tr_addBookType_C').hide();

			}
		}

		$('#type').change(function() {
			bodyType();
		});

		bodyType();

	});

	$("#txa_innermsg_share_name").click(function() {
		var d = '${userjson}';
		if (d.trim().length) {
			alertTree();
		}
		;
	});

	function alertTree() {
		var selectNodeId = '${userjson}';
		new treePerson(jQuery.parseJSON(selectNodeId),
				$("#txa_innermsg_share_name"),
				$("#txt_innermsg_share_usercode")).init();
		setAlertStyle("attAlert");
	}
</script>
</html>