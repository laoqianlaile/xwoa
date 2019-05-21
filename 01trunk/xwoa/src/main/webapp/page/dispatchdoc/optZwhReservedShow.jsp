<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title><s:text name="文号预留" /></title>
</head>
<body>
	<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding:4px 8px 3px;">
			<b>文号预留</b>
		</legend>
		<br/>
		<s:form action="ioDocArchiveNo!showZwhReserved.do" method="post" namespace="/dispatchdoc" id="ioDocArchiveNoForm">
			年份<select id="lshYear" name="lshYear" style="width: 65px;">
				<c:forEach var="option" items="${yearOptions}">
					<option value="${option.value}" ${option.value eq object.lshYear ? "selected" : ""}>
						<c:out value="${option.label}" />
					</option>
				</c:forEach>
			</select>
		</s:form>
		
		<ec:table action="ioDocArchiveNo!showZwhReserved.do" items="dictionaryList" var="dictionary" imagePath="${STYLE_PATH}/images/table/*.gif" filterRowsCallback="limit"
			retrieveRowsCallback="limit" sortRowsCallback="limit" showPagination="false">
			<ec:row>
				<ec:column property="datavalue" title="文件类型" style="text-align: left;" />
				<ec:column property="extracode2" title="即将使用文号" style="text-align: right;" />
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>
				<ec:column property="opt" title="${optlabel}" sortable="false" style="text-align:center">
					<input type="hidden" name="wjlxDataCode" value="${dictionary.datacode}" />
					&nbsp;&nbsp;
					<a href="###" onclick="setZwhReserved('${dictionary.datacode}')">预留文号设置</a>
					&nbsp;&nbsp;
					<a href="###" onclick="viewZwhReserved('${dictionary.datacode}')">查看</a>
					&nbsp;&nbsp;
				</ec:column>
			</ec:row>
		</ec:table>
	</fieldset>
</body>
<script type="text/javascript">
function setZwhReserved(dataCode) {
	JDialog.open({
		src : "${contextPath}/dispatchdoc/ioDocArchiveNo!setZwhReserved.do?wjlx=" + dataCode 
				+ "&lshYear=" + $("#lshYear").val(),
		width : 800,
		height : 350
	});
}

function viewZwhReserved(dataCode) {
	JDialog.open({
		src : "${contextPath}/dispatchdoc/ioDocArchiveNo!viewZwhReserved.do?wjlx=" + dataCode 
				+ "&lshYear=" + $("#lshYear").val(),
		width : 800,
		height : 350
	});
}

$("#lshYear").change(function() {
	$("#ioDocArchiveNoForm").submit();
});
</script>
</html>

