<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title>阅件查看</title>
<%-- <sj:head locale="zh_CN" /> --%>
</head>

<body>
	<p class="ctitle">
		阅件查看
	</p>

	<%@ include file="/page/common/messages.jsp"%>

	<s:form action="readInfo" method="post" namespace="/dispatchdoc"
		id="readInfoForm" enctype="multipart/form-data">
		<input type="button" value="返回" Class="btn" onclick="window.history.back()" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="viewTable">
			<tr>
				<td class="addTd">文件标题
				</td>
				<td align="left" colspan="3">
				${object.incomeDocTitle}
				</td>
			</tr>
			<tr>
				<td class="addTd">来文单位
				</td>
				<td align="left">
				${object.incomeDeptName}
				</td>
				<td class="addTd">制发日期
				</td>
				<td align="left">
				<fmt:formatDate value="${object.incomeDate}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td class="addTd">来文文号</td>
				<td align="left">
				${object.incomeDocNo }
				</td>
				<td class="addTd">正文</td>
				<td align="left">
				<a  style="text-decoration:none;color: blue;" href="<%=request.getContextPath()%>/dispatchdoc/readInfo!downloadstuff.do?readNo=${object.readNo}">${object.incomeDocName }</a>
				</td>
			</tr>
			<tr>
				<td class="addTd">登记日期</td>
				<td align="left">
				<fmt:formatDate value="${object.createDate}" pattern="yyyy-MM-dd"/>
				</td>
			
				<td class="addTd">登记人</td>
				<td align="left">
				${cp:MAPVALUE("userCode",object.createName)}
				</td>
			</tr>
			<tr>
				<td class="addTd">文件类型</td>
				<td align="left" colspan="3">
				${cp:MAPVALUE("DOC_FILE_TYPE",object.docFileType)}
				</td>
			</tr>
		</table>
</s:form>
</body>

</html>
