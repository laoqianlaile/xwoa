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
		${cp:MAPVALUE("msgtype",s_msgtype)}
	    </p>
		</legend> 
<%-- <p class="ctitle">
<s:text name='innermsg.view.title' />
</p> --%>
	<%@ include file="/page/common/messages.jsp"%>

	<s:form action=" " method="post" namespace="/oa" id="innermsg_form"
		validator="true">
		<input type="hidden" name="s_mailtype" value="${s_mailtype }" />
		<input type="hidden" name="s_msgtype" value="${s_msgtype }" />
		<input type="hidden" name="s_msgstate" value="${s_msgstate }" />
		<input type="hidden" name="s_NP_senddate" value="${s_NP_senddate }" />
		<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
			
			<tr>
				<td class="addTd">发件人：</td>
				<td align="left">${cp:MAPVALUE('usercode', object.sender) }</td>
			</tr>
			<%-- 				<c:if test="${empty object.msgtype }"> --%>
			<tr>
				<td class="addTd">收件人：</td>
				<td align="left">${object.receivename }</td>
			</tr>
			<c:if test="${not empty object.ccName }">
			<tr>
				<td class="addTd">抄送：</td>
				<td align="left">${object.ccName }</td>
			</tr>
			</c:if>
			<c:if test="${not empty object.bccName and flag}">
			<tr>
				<td class="addTd">密送：</td>
				<td align="left">${cp:MAPVALUE('usercode', session.SPRING_SECURITY_CONTEXT.authentication.principal.usercode )}</td>
			</tr>
			</c:if>
			<tr>
				<td class="addTd">标题：</td>
				<td align="left">${object.msgtitle }</td>

			</tr>
			
			<%-- 			  </c:if> --%>
			<c:if test="${not empty object.msgannexs }">

				<tr>
					<td class="addTd">附件：</td>
					<td align="left" colspan="3"><c:forEach var="fi"
							items="${object.msgannexs }">
							<div style="margin-bottom: 10px;">
								<a target="download" data-filecode="${fi.fileinfo.filecode}">${fi.fileinfo.filename}.${fi.fileinfo.fileext}</a>
							</div>
						</c:forEach></td>

				</tr>
			</c:if>

			<tr>
				<td class="addTd">内容：</td>
				<td align="left" colspan="3">${object.msgcontent }</td>
			</tr>
		</table>
		<div class="formButton">
		<c:choose>
		<c:when test="${s_mailtype eq 'I' }"><!-- 文件群发的收件箱下，查看返回 -->
					<input type="button" class="btn" target="submit" style="cursor:pointer;" 
 						data-form="#innermsg_form" 
						data-href="${pageContext.request.contextPath}/oa/innermsgRecipient!list.do" 
 						value='返回'>
		</c:when>
		<c:when test="${s_newmsgtype eq 'I' or ! empty s_msgstate}"><!-- 个人邮件的收件箱或"我的首页"下，查看返回 -->
					<input type="button" class="btn" target="submit" style="cursor:pointer;" 
			 						data-form="#innermsg_form" 
									data-href="${pageContext.request.contextPath}/oa/innermsg!recList.do" 
			 						value='返回'>
		</c:when>
		<c:otherwise><!-- 个人邮件的发件箱，草稿箱或者文件群发的发件箱下或"我的首页"下，查看返回 -->
					<input type="button" class="btn" target="submit" style="cursor:pointer;" 
			 						data-form="#innermsg_form" 
									data-href="${pageContext.request.contextPath}/oa/innermsg!list.do" 
			 						value='返回'>
		</c:otherwise>
		</c:choose>
<!--         <input type="button" name="back" Class="btn" onclick="history.go(-1);" -->
<!-- 			value="返回" /> -->

		</div>
		
	</s:form>
		</fieldset>
</body>
<%@ include file="/page/common/scripts.jsp"%>

</html>