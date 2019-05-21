<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>论坛帖子主题查看</title>
</head>

<body>

	<%@ include file="/page/common/messages.jsp"%>



	<s:form action="oaBbsTheme" method="post" namespace="/bbs"
		id="oaBbsThemeForm">
		<fieldset class="_new">
			<legend> 论坛帖子主题查看 </legend>
			<!-- <div class="formButton"> -->
			<input type="button" onclick="window.history.go(-1);" class="btn"
				value="返回" />
		<!-- </div> -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="viewTable">

				<tr>
					<td class="addTd">主题标题</td>
					<td align="left" colspan="3">${object.sublayouttitle }</td>
				</tr>
				<tr>
					<td class="addTd">作者</td>
					<td align="left">${cp:MAPVALUE('usercode',object.creater)}</td>
					<td class="addTd">状态
					<td align="left">${cp:MAPVALUE('ThemeState',object.state)}</td>
				</tr>
				<tr>
					<td class="addTd">创建时间</td>
					<td align="left"><fmt:formatDate value="${object.createtime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td class="addTd">最后回复时间</td>
					<td align="left"><fmt:formatDate
							value="${object.lastmodifytime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="_new">
			<legend> 论坛帖子审核信息 </legend>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="viewTable">
				<tr>
					<td class="addTd">审核结果</td>
					<td align="left" colspan="3"><c:if test="${'T' eq object.approvalresults}">通过</c:if>
						<c:if test="${'F' eq object.approvalresults}">不通过</c:if></td>
				</tr>
                <tr>
					<td class="addTd">审核人</td>
					<td align="left">${cp:MAPVALUE('usercode',object.approval)}</td>
					<td class="addTd">审核时间</td>
					<td align="left"><fmt:formatDate
							value="${object.approvaltime }" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
				<tr>
					<td class="addTd">审核意见</td>
					<td align="left" colspan="3">${object.approvalremark }</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="_new">
			<legend> 帖子信息 </legend>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="viewTable">
				<tr>
					<td class="addTd">帖子回复数</td>
					<td align="left">
					${empty object.postsnum ? 0 : object.postsnum}
					</td>

					<td class="addTd">帖子查看数</td>
					<td align="left">
					${empty object.postsviewnum ? 0 : object.postsviewnum}
					</td>
				</tr>

				<tr>
					<td class="addTd">帖子类型</td>
					<td align="left">${cp:MAPVALUE('themeset',object.themeset) }</td>

					<td class="addTd">关注数</td>
					<td align="left">
					${empty object.attentionum ? 0 : object.attentionum}
					</td>
				</tr>
				<tr>
					<td class="addTd">帖子内容</td>
					<td align="left" colspan="3">${object.bodycontent}</td>
				</tr>
			</table>
		</fieldset>
	</s:form>
</body>
</html>
