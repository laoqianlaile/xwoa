<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%> 
<html>
<head>
<title><s:text name="文号预留" /></title>
</head>
<body>
	<s:form action="ioDocArchiveNo!viewZwhReserved.do" method="post" namespace="/dispatchdoc" id="ioDocArchiveNoForm">
		<input type="hidden" id="wjlx" name="wjlx" value="object.wjlx" />
		<fieldset style="padding: 10px; display: block; margin-bottom: 10px;">
			<legend style="padding:4px 8px 3px;">
				<b>预留文号查看</b>
			</legend>
			<br/>
			<c:if test="${empty object.wjlx}">
				年份<select id="lshYear" name="lshYear" style="width: 65px;">
					<c:forEach var="option" items="${yearOptions}">
						<option value="${option.value}" ${option.value eq object.lshYear ? "selected" : ""}>
							<c:out value="${option.label}" />
						</option>
					</c:forEach>
				</select>
			</c:if>
			
			<ec:table action="ioDocArchiveNo!viewZwhReserved.do" items="zwhReservedList" var="zwhReserved" imagePath="${STYLE_PATH}/images/table/*.gif" filterRowsCallback="limit"
				retrieveRowsCallback="limit" sortRowsCallback="limit">
				<ec:row>
					<ec:column property="datavalue" title="文件类型" style="text-align: left;">
						${cp:MAPVALUE("WJLX",zwhReserved.wjlx)}
					</ec:column>
					<ec:column property="lsh" title="预留文号" style="text-align: right;" />
					<ec:column property="createDate" title="预留时间" style="text-align:center;" format="yyyy-MM-dd HH:mm" cell="date" />
					<ec:column property="useDate" title="使用时间" style="text-align:center;" format="yyyy-MM-dd HH:mm" cell="date" />
					<ec:column property="reservedReason" title="预留原因" style="text-align: left;" />
					<ec:column property="djId" title="状态" style="text-align: center;">
						<c:choose>
							<c:when test="${empty zwhReserved.djId}">
								未用
<%-- 								<a href="###" onclick="deleteZwhReserved(this,'${zwhReserved.reservedId}');">删除</a> --%>
							</c:when>
							<c:otherwise>
								已用
							</c:otherwise>
						</c:choose>
					</ec:column>
				</ec:row>
			</ec:table>
		</fieldset>
	</s:form>
</body>
<script type="text/javascript">
function deleteZwhReserved(self, reservedId) {
	$.ajax({
		type : "POST",
		url : "${contextPath}/dispatchdoc/ioDocArchiveNo!deleteZwhReserved.do?reservedId=" + reservedId,
		dataType : "json",
		success : function(data) {
			if ("success" == data.status) {
				$(self).parent().parent().remove();
			} else if ("unavailable" == data.status) {
				$(self).parent().html("已用");
				alert("该预留号已被使用！");
			} else {
				alert("删除失败！");
			}
		},
		error : function() {
			alert("删除失败！");
		}
	});
}

$("#lshYear").change(function() {
	$("#ioDocArchiveNoForm").submit();
});
</script>
</html>

