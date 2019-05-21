<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<script>
	top.document.getElementById("progressBar").style.display = "block";
	top.document.getElementById("background").style.display = "block";
</script>
<div>
	<div>
		<table
			style="width: 100%; border-left: 1px solid #CACACA; border-bottom: 1px solid #CACACA; margin-top: 10px;"
			id="statTable" cellpadding="0" border="0" cellspacing="0">
			<caption>${formNameFormat }</caption>
			<c:if test="${not empty tablePanel.thead }">
				<thead align="center">
					<c:forEach var="line" items="${tablePanel.thead.lines }">
						<tr>
							<c:forEach var="cell" items="${line.cells }">
							${cell.html }
						</c:forEach>
						</tr>
					</c:forEach>
				</thead>
			</c:if>

			<c:if test="${not empty tablePanel.tbody }">
				<tbody align="center">
					<c:forEach var="line" items="${tablePanel.tbody.lines }"
						varStatus="trStatus">
						<c:set var="depno" value="${line.cells[0].value }"></c:set>
						<c:set var="supdepno" value="${line.cells[1].value }"></c:set>

						<%-- 父节点为空 顶级节点 --%>
						<c:if test="${empty supdepno or trStatus.index == 0}">
							<tr data-tt-id="${depno }">
						</c:if>

						<%-- 父节点不为空 --%>
						<c:if test="${not empty supdepno and trStatus.index != 0}">
							<tr data-tt-id="${depno }" data-tt-parent-id="${supdepno }">
						</c:if>

						<c:forEach var="cell" items="${line.cells }">
							${cell.html }					
						</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</c:if>
		</table>
	</div>
</div>