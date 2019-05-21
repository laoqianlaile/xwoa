<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title><s:text name="dataindividual.edit.title" /></title>


<script
	src="${pageContext.request.contextPath}/scripts/SelectTree_V1.0.js"
	type="text/javascript"></script>
<link
	href="${pageContext.request.contextPath}/styles/default/css/SelectTree.css"
	rel="stylesheet" type="text/css" />
<script src="<s:url value='/scripts/centit_validator.js'/>"
	type="text/javascript"></script>
</head>

<body>
	 <fieldset class="form">
		<legend>
		<p class="ctitle">
		${cp:MAPVALUE("msgtype",s_msgtype)}收件箱
	    </p>
		</legend> 



	<%@ include file="/page/common/messages.jsp"%>

	<s:form action=" " method="post" namespace="/oa" id="innermsg_form"
		validator="true">
	

		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			<tr>
				<td class="addTd">标题：</td>
				<td align="left">${object.innermsg.msgtitle }</td>

			</tr>

			<tr>
				<td class="addTd">发件人：</td>
				<td align="left">${cp:MAPVALUE('usercode',
					object.innermsg.sender) }</td>
			</tr>
			<tr>
				<td class="addTd">收件人：</td>
				<td align="left">${cp:MAPVALUE('usercode', object.receive) }</td>
			</tr>

			<c:if test="${not empty object.innermsg.msgannexs }">

				<tr>
					<td class="addTd">附件：</td>
					<td align="left" colspan="3"><c:forEach var="fi"
							items="${object.innermsg.msgannexs }">
							<div style="margin-bottom: 10px;">
								<a target="download" filecode="${fi.fileinfo.filecode}">${fi.fileinfo.filename}.${fi.fileinfo.fileext}</a>
							</div>
						</c:forEach></td>

				</tr>
			</c:if>

			<tr>
				<td class="addTd">内容：</td>
				<td align="left" colspan="3">${object.innermsg.msgcontent }</td>
			</tr>


		</table>
		<div class="formButton">
		<input type="button" name="back" Class="btn"
			onclick="javascript:window.location.href='oa/innermsgRecipient!list.do?s_msgtype=${s_msgtype}&s_mailtype=${s_mailtype}';"
			value="返回" />
		</div>
	</s:form>
		</fieldset>
</body>
<%@ include file="/page/common/scripts.jsp"%>

</html>