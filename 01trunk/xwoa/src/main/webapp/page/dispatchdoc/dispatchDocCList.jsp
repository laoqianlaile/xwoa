<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
<head>
<title></title>
</head>

<body>
	<%@ include file="/page/common/messages.jsp"%>
	<fieldset>
		<legend>
			<b>关联的收文</b>
		</legend>
		<s:form method="post" namespace="/dispatchdoc" name="docRelativeForm"
			id="docRelativeForm" action="dispatchDoc" target="_blank">
			<c:forEach var="incomeDoc" items="${incomeDocList}">
				<input type="hidden" name="incomeNo" value="${incomeDoc.djId}" />
			</c:forEach>
			<c:if test="${'edit' eq docRelativeFrameType }">
				<center>
					<button id="btn_incomeDocList" name="incomeDocList" class="btn">关联收文</button>
				</center>
			</c:if>
		</s:form>

		<ec:table action="dispatchdoc/dispatchDoc!incomeDocList.do?"
			items="incomeDocList" var="incomeDoc"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif"
			retrieveRowsCallback="limit" showPagination="false">
			<ec:row>
				<ec:column property="djId" title="办件编号" style="text-align:center" />
				<ec:column property="transaffairname" title="办件名称"
					style="text-align:center" />

				<ec:column property="createdate" title="更新时间"
					style="text-align:center" format="yyyy-MM-dd HH:mm" cell="date" />

				<ec:column property="nodeName" title="流程状态"
					style="text-align:center" />
				<c:set var="optlabel">
					<s:text name="opt.btn.collection" />
				</c:set>
				<ec:column property="opt" title="${optlabel}" sortable="false"
					style="text-align:center">

					<c:if test="${'edit' eq docRelativeFrameType }">
						<a href="delete_${incomeDoc.djId}">删除</a>
					</c:if>


					<a
						href='<%=request.getContextPath()%>/sampleflow/sampleFlowManager!viewxml.do?flowInstId=${incomeDoc.flowInstId}'>查看流程图</a>
				</ec:column>

			</ec:row>
		</ec:table>

	</fieldset>

</body>

<script type="text/javascript">
	var dispatchNo = "${dispatchNo}";
	
	$('#btn_incomeDocList').click(function(event) {
		var url = "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!incomeDocList.do?dispatchNo=${dispatchNo}";
		
		var incomeNos = $("input:hidden[name='incomeNo']");
		for (var i=0; i<incomeNos.length; i++) {
			url += "&djId=" + $(incomeNos[i]).val();
		}
		
		window.open(url);
// 		return false;
// 		event.preventDefault();
						
// 		var retValue = window.showModalDialog(url, window, "dialogHeight:600px;dialogWidth:800px;center:yes;help:no;scroll:yes;status:no;edge:raised");
		
		url = "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!docRelativeList.do?dispatchNo=${dispatchNo}&docRelativeFrameType=edit";

// 		if ("refresh" == retValue) {
// 			window.location.href = url;
// 		}
		
		return false;
	});
	
	$("a").click(function() {
		var href = $(this).attr("href");
		if (href && href.indexOf("delete") > -1) {
			var array = href.split("_");
			if (array.length == 2) {
				var incomeNo = array[1];
				
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!docRelativeDel.do?dispatchNo=" + dispatchNo + "&incomeNo=" + incomeNo,
					dataType : "json",
					success : function(data) {
						if ("success" == data.status) {
							url = "<%=request.getContextPath()%>/dispatchdoc/dispatchDoc!docRelativeList.do?dispatchNo=${dispatchNo}&docRelativeFrameType=edit";
													window.location.href = url;
												} else {
													alert("删除失败！");
												}
											},
											error : function() {
												alert("删除失败！");
											}
										});
							} else {
								alert("删除失败！");
							}
						}

						return false;
					});

	function replaceUrl(a) {

		var doOptUrl = a.href;

		doOptUrl = doOptUrl.replaceAll("amp%3B", "");

		a.href = doOptUrl;

		return false;
	}
</script>

</html>
