<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<html>

<head>
<!-- IFRAME模式下需要导入的内容BEGIN -->
<%@ include file="./childs/stat-taglibs.jsp"%>
<!-- IFRAME模式下需要导入的内容END -->
</head>

<body>
<%@ include file="childs/search.jsp"%>
	<!--startprint-->	
<div class="pageContent">
	<table class="list stat" layoutH=".pageHeader 1" width="100%">
		
		<c:if test="${not empty tablePanel.thead }">
			<thead align="center">
				<c:forEach var="line" items="${tablePanel.thead.lines }" varStatus="status">
					<tr>
						<c:forEach var="cell" items="${line.cells }">
							<th colspan="${cell.colspan }" rowspan="${cell.rowspan }" width="150px;">
							
								<c:choose>
									<%-- 第一层：权力类别 --%>
									<c:when test="${status.index == 0 }">
										<c:choose>
											<c:when test="${modelName == 'YBJTJBJ' }">
												${cp:MAPVALUE("MONITOR_TYPE", cell.value)}
											</c:when>
											<c:when test="${modelName == 'QLSXHZ_B' }">
												${cp:MAPVALUE("ITEM_TYPE", cell.value)}
											</c:when>
											<c:otherwise>
												${cell.value }
											</c:otherwise>
										</c:choose>
									</c:when>
									
									<%-- 第二层：权力状态 --%>
									<c:when test="${status.index == 1 }">
										<c:choose>
											<c:when test="${modelName == 'YBJTJBJ' }">
												${cp:MAPVALUE("MONITOR_STYLE", cell.value)}
											</c:when>
											<c:when test="${modelName == 'QLSXHZ_B' }">
												${cp:MAPVALUE("QL_State",cell.value)}
											</c:when>
											<c:otherwise>
												${cell.value }
											</c:otherwise>
										</c:choose>
										
										
									</c:when>
									
									<c:otherwise>
										${cell.value }
									</c:otherwise>
									
								</c:choose>
							
							</th>
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
									
									<%-- 合计“在用”添加链接 --%>
									<c:when test="${status.count == (cellLength - 2) }">
										${fn:indexOf(cell.value, '.') gt '0' ? fn:substringBefore(cell.value, '.') : cell.value }
									</c:when>
									
									<%-- 合计“挂起”添加链接 --%>
									<c:when test="${status.count == (cellLength - 1) }">
										${fn:indexOf(cell.value, '.') gt '0' ? fn:substringBefore(cell.value, '.') : cell.value }
									</c:when>
									
									<%-- 合计“废止”添加链接 --%>
									<c:when test="${status.count == cellLength }">
										${fn:indexOf(cell.value, '.') gt '0' ? fn:substringBefore(cell.value, '.') : cell.value }
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
	 <!--endprint--> 
</body>
<%@ include file="./childs/stat-scripts.jsp"%>
</html>