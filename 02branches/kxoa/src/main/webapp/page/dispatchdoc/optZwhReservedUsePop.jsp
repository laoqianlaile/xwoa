<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title><s:text name="预留文号" /></title>
</head>
<body>
	<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
		<legend style="padding:4px 8px 3px;">
			<b>预留文号</b>
		</legend>
			
		<ec:table action="dispatchdoc/ioDocArchiveNo!useReserved.do" items="zwhReservedList" var="zwhReserved"
			imagePath="${STYLE_PATH}/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<ec:column property="lsh"  title="预留流水号" style="text-align: left;" />
				<ec:column property="reservedReason" title="预留原因" style="text-align: left;" />
				<c:set var="optlabel"><s:text name="opt.btn.collection"/></c:set>
				<ec:column property="opt" title="${optlabel}" sortable="false" style="text-align:center">
					<input type="radio" name="curLsh" onclick="afterSelect(${zwhReserved.lsh});" />
				</ec:column>
			</ec:row>
		</ec:table>
	</fieldset>
</body>
<script type="text/javascript">
function afterSelect(lsh) {
	if (confirm("确定要使用 " + lsh + " 作为流水号吗？")) {
		window.parent.frames["transFrame"].window.getNewFwh(3, lsh);
		window.parent.window.JDialog.close();
	}
}
</script>
</html>

