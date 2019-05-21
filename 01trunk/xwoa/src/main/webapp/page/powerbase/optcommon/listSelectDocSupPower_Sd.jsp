<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>
<html>
	<head>
		<title><s:text name="suppower.list.title" /></title>
	</head>

	<body>
		<%@ include file="/page/common/messages.jsp"%>
			<!-- <div class="flowTitle" align="right">
				<br/>
				<span style="font-size:18pt;float:center;padding-right:460px;">办件登记</span>
			</div> -->
		<br/>
	
		<ec:table action="vsuppowerinusing!listDocPower.do" items="orgPowerList" var="power"
			imagePath="${pageContext.request.contextPath}/themes/css/images/table/*.gif" retrieveRowsCallback="limit">
			<ec:row>
				<c:if test="${declare ne 'yes'}">
					<ec:column property="itemId" title="权力编号" style="text-align:center" sortable="false"/>
				</c:if>
				<ec:column property="itemName" title="权力名称" style="text-align:left" sortable="false" />	
				<ec:column property="opt" title="操作" sortable="false" style="text-align:center">
					<c:if test="${declare eq 'yes'}">
						<a href="${pageContext.request.contextPath}/dispatchdoc/incomeDoc!edit.do?applyItemType=${power.applyItemType}&optBaseInfo.optId=IO_DOC
								&optBaseInfo.flowCode=${power.optFlowCode}&optBaseInfo.powerid=${power.itemId}">申报</a>
					</c:if>
					<c:if test="${declare ne 'yes'}">
						<input type="radio" name="itemId" onclick="setParentVal('${power.itemId}', '${power.itemName}', '${power.applyItemType}', '${power.optFlowCode}')" />
					</c:if>
				</ec:column>
			</ec:row>
		</ec:table>
	</body>
	<script type="text/javascript">	
		var parentDocument = window.parent.document;//获取父页面对象
	
		//设置返回值
		function setParentVal(itemId, itemName, applyItemType, wfcode) {
			if (window.confirm("确认选择此项权力吗？选择后窗口将自动关闭。")) {
				parentDocument.getElementById('powerid').value = itemId;
				parentDocument.getElementById('powername').value = itemName;
				parentDocument.getElementById('applyItemType').value = applyItemType;
				parentDocument.getElementById('flowCode').value = wfcode;
				window.parent.window.changeApplyItemType();
				window.parent.window.JDialog.close();
			}
			/*****************业务数据页面跳转end*********/
		}
	</script>
</html>
