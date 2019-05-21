<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>个人通讯录</title>
</head>

<body class="sub-flow">
	<fieldset >
		<legend>
				<s:text name="addressbooks.view.title" />
		</legend>


		<%@ include file="/page/common/messages.jsp"%>

		<form
			action="${pageContext.request.contextPath}/oa/addressbooks!save.do"
			method="post" id="addressbooksForm" data-validate="true">
			<input type="hidden" id="addrbookid" name="addrbookid"
				value="${addrbookid }" /> <input id="txt_innermsg_share_usercode"
				type="hidden" name="shareUserCode" value="${usercodes }" /> <input
				type="hidden" id="type" name="type" value="${type}" />


			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			
					<tr>
						<td class="addTd">姓名</td>
						<td align="left">${userName }</td>

						<td class="addTd">性别</td>
						<td align="left">${cp:MAPVALUE("sex",sex)}</td>
					</tr>



					<tr>
						<c:if test="${'P' eq  type}">
							<td class="addTd">单位</td>
							<td align="left">${unitName}</td>
						</c:if>
						<c:if test="${'C' eq  type}">
							<td class="addTd">部门</td>
							<td align="left">${deptName}</td>
						</c:if>
						<td class="addTd">手机</td>
						<td align="left">${mobilephone }</td>

					</tr>
					<tr>
					    <td class="addTd">固定电话</td>
						<td align="left">${telphone }</td>
						<td class="addTd">Email</td>
						<td align="left">${email }</td>
					</tr>
					<tr>
						
						<td class="addTd">其他联系方式</td>
						<td align="left">${otherphone }</td>
						<td class="addTd">备注</td>
						<td align="left">${remark }</td>
					</tr>
			</table>
		</form>
		<div class="formButton">
			<input type="button" name="back" Class="btn"
				onclick="history.back(-1);" value="返回" />
		</div>
	</fieldset>

</body>

<%@ include file="/page/common/scripts.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#a_href').attr('height', window.screen.availHeight - 200);
		function isShare() {
			if ('checked' == $('#isshare').attr("checked")) {
				$('#tr_share').show();
			} else {
				$('#tr_share').hide();
			}
		}
		isShare();
	});
</script>
</html>