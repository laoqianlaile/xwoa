<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<style>
a {
	text-decoration: none !important;
}
td{
white-space:nowrap}
</style>

	<div class="pageContent">
	<table class="list stat" id="statTable"  layoutH=".pageHeader 1" style="width:100%;" >
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
				<c:forEach var="line" items="${tablePanel.tbody.lines }">
					<tr>
						<c:forEach var="cell" items="${line.cells }">
							${cell.html }					
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</c:if>
	</table>
	</div>
