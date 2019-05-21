<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title><s:text name="oaFeedback.view.title" /></title>
</head>

<body>
<%-- <c:if test="${ empty show_type }"> --%>
				<c:if test="${flag ne 'edit' }">
	<fieldset class="form">
		<legend>
			<p class="ctitle">
				情况反馈
			</p>
		</legend>
		</c:if>
<%-- </c:if> --%>
<form>
		<s:hidden name="show_type" value="%{show_type}"></s:hidden>
		
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable"
			style="margin-top: 0px;">
			<tr>
				<td class="addTd"><s:text name="oaFeedback.djid" /></td>
				<td align="left" colspan="3"><s:property value="%{djid}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaFeedback.title" /></td>
				<td align="left" colspan="3"><s:property value="%{title}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaFeedback.remark" /></td>
				<td align="left" colspan="3">${object.remark }</td>

			</tr>
			
			<tr>
				<td class="addTd"><s:text name="oaFeedback.depno" /></td>
				<td align="left">${cp:MAPVALUE("unitcode",depno)}</td>
				<td class="addTd"><s:text name="oaFeedback.reception" /></td>
				<td align="left">${cp:MAPVALUE("usercode",reception)}</td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="oaFeedback.creater" /></td>
				<td align="left">
			<c:if test="${isanonymous eq 1}">
				匿名
			</c:if>
			<c:if test="${isanonymous ne 1}">
			   ${cp:MAPVALUE("usercode",creater)}
			</c:if>
				
				</td>
				<td class="addTd"><s:text name="oaFeedback.creatertime" /></td>
				<td align="left"><s:date name="creatertime" format="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td class="addTd"><s:text name="oaFeedback.isopen" /></td>
				<td align="left">${cp:MAPVALUE("IS_BOOLEAN",isopen) }</td>
				<td class="addTd"><s:text name="oaFeedback.feedFile" /></td>
				<td align="left" colspan="3"><a
					href="<%=request.getContextPath()%>/oa/oaFeedback!downStuffInfo.do?djid=${object.djid }">${feedFileName}</a>
				</td>
			</tr>
		</table>
		<br />
		<c:if test="${ object.isAnswer eq '1' }">
			<table border="0" cellpadding="0" cellspacing="0" class="viewTable"
				style="margin-top: 0px;">
				<tr>
					<td class="addTd"><s:text name="oaFeedback.replyTime" /></td>
					<td align="left"><s:date name="replyTime" format="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td class="addTd"><s:text name="oaFeedback.replyInformation" />
					</td>
					<td align="left" colspan="3">${object.replyInformation}</td>
				</tr>
			</table>
		</c:if>
		
		</form>
		<c:if test="${ empty show_type }">
			<c:if test="${flag ne 'edit' }">
				<div class="formButton">

					<input type="button" value="返回" onclick="window.history.back();"
						class="btn">

				</div>
	    </c:if>
	</fieldset>
</c:if>
</body>
</html>
