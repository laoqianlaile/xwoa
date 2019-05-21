<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>会议室信息</title>
</head>

<body>
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				<s:text name="oaCommonType.view.title" />
			</p>
		</legend>

		<%@ include file="/page/common/messages.jsp"%>

		<form
			action="${pageContext.request.contextPath}/oa/oaCommonType!save.do"
			method="post" id="oaCommonTypeForm" data-validate="true">


			<input type="hidden" id="no" name="no" value="${no }" />

			<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
				<tr>
					<td class="addTd">类别</td>
					<td align="left">${cp:MAPVALUE('publicType',publicType)}</td>
					<td class="addTd">启用状态</td>
					<td align="left">${USE_STATE[state]}</td>

				</tr>

				<tr>
					<td class="addTd">编码</td>
					<td align="left">${coding }</td>
					<td class="addTd">排序</td>
					<td align="left">${orderno }</td>
				</tr>
				<tr>
					<td class="addTd">名称</td>
					<td align="left" colspan="3">${comName }</td>
					<!-- 			<td class="addTd">是否公开</td> -->
					<!-- 			 <td align="left" > -->
					<%-- 			 ${cp:MAPVALUE('IS_BOOLEAN ',isopen)} --%>
					<!-- 			  </td> -->
				</tr>
				<tr>
					<td class="addTd">描述</td>
					<td align="left" colspan="3">${remark }</td>
				</tr>
			</table>
			<div class="formButton">
				<input type="button" name="back" Class="btn"
					onclick="history.back(-1);" value="返回" />
			</div>
		</form>
	</fieldset>
</body>
<%@ include file="/page/common/scripts.jsp"%>

</html>