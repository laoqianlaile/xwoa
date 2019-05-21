<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<%@ include file="childs/search.jsp"%>

<style>
table.stat {table-layout: fixed;}
table.stat th {width:120px;}
table.stat a,table.stat a:VISITED,table.stat a:HOVER {text-decoration: none;}
 {text-decoration: none;}
</style>

<div class="pageContent">
	<table class="list stat" layoutH=".pageHeader 1">
		
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
						<%-- 每行单元格数量 --%>
						<c:set var="cellLength" value="${fn:length(line.cells) }"></c:set>
						
						<c:forEach var="cell" items="${line.cells }" varStatus="status">
							<td colspan="${cell.colspan }" rowspan="${cell.rowspan }">
							
								<c:choose>
									
									<%-- 第一列显示单位 --%>
									<c:when test="${status.first }">
										${cp:MAPVALUE("depno",cell.value)}
									</c:when>
									
									<%-- 含有链接 --%>
									<c:when test="${not empty cell.href && not empty cell.value}">
									
										<%-- 标题和REL自行修改 --%>
										<a href="${cell.href }" target="navTab" external="true" title="" rel="">${cell.value }</a>
									</c:when>
									
									<c:otherwise>
										${cell.value }
									</c:otherwise>
								</c:choose>
							
								
							</td>				
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</c:if>
	</table>
</div>
	