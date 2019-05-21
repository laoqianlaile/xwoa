<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<title>执法案例查看</title>
	<script language="JavaScript" src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgcore.min.js" type="text/JavaScript"></script>
    <script language="JavaScript" src="${pageContext.request.contextPath}/page/powerbase/lhgdialog/lhgdialog.js" type="text/JavaScript"></script>
</head>

<body >

	<%@ include file="/page/common/messages.jsp"%>
	<fieldset style="padding: 10px;">
		<legend class="ctitle" style="width: auto; margin-bottom: 5px;">
			案例查看
		</legend>
		<s:form action="lawenforecase" method="post" namespace="/powerbase"  enctype="multipart/form-data">
			
			<input type="button" class="btn" value="返回" onclick="javascript:history.go(-1);" />
				<table border="0" cellpadding="0" cellspacing="0" class="viewTable">
	
			<tr>
				<td class="addTd"><s:text name="lawenforecase.caseno" /></td>
				<td align="left" colspan="3"><s:property value="%{caseno}" /></td>
				
			</tr>
		<tr>
				<td class="addTd"><s:text name="lawenforecase.casetitle" /></td>
				<td align="left" colspan="3"><s:property value="%{casetitle}" /></td></td>
			
			</tr>
			<tr>
				<td class="addTd"><s:text name="lawenforecase.orgId" /></td>
				<td align="left">${cp:MAPEXPRESSION
			        ("depno",phobject.orgId)}</td>

			</tr>
			<tr>
		    <td class="addTd" width="130">案例正文</td>
					<td align="left" colspan="3"><s:file name="casedoc_" size="30%"/> <c:if
							test="${not empty object.docName}">&nbsp;
				<input type="button" name="built" value="查看" class="btn"
								onclick="downFile('${phobject.caseno}','casedoc')">&nbsp;
				<%-- <input type="button" name="built" value="删除" class="btn"
								onclick="deleteFile('${object.caseno}','casedoc')"> --%>
						</c:if></td>
				</tr>
			<tr>
				<td class="addTd"><s:text name="lawenforecase.bookoperator" /></td>
				<td align="left" colspan="3"><s:property value="%{bookoperator}" /></td>
			</tr>

			<tr>
				<td class="addTd"><s:text name="lawenforecase.bookdate" /></td>
				<td align="left" colspan="3"><fmt:formatDate value='${phobject.bookdate}' pattern='yyyy-MM-dd hh:mm:ss' /></td>
			</tr>
			</table>
	
		</s:form>
	</fieldset>
	<script type="text/javascript">
	function downFile(caseno, fileType) {
		var url = "lawenforecase!downloadStuff.do?caseno=" + caseno + "&fileType="
				+ fileType;
		document.location.href = url;
	}

	</script>
</body>
</html>
