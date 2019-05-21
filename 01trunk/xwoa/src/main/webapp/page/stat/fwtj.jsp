<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>




<!-- IFRAME模式下需要导入的内容BEGIN -->
<%@ include file="./childs/stat-taglibs.jsp"%>
<!-- IFRAME模式下需要导入的内容END -->

<%@ include file="childs/search.jsp"%>
<div class="pageContent" >
		<table class="list stat" id="treetable" layoutH=".pageHeader 1" >
		<c:if test="${not empty tablePanel.thead }">
			<thead align="center">
				<c:forEach var="line" items="${tablePanel.thead.lines }">
					<tr>
						<c:forEach var="cell" items="${line.cells }">
								<c:choose>
									<%-- 第一列和最后一列合计不做统计 --%>
									<c:when test="${not (cellStatus.first || cellStatus.last) }">
										<th colspan="${cell.colspan }" rowspan="${cell.rowspan }" stat="T">
											${cp:MAPVALUE("WJLX", cell.value)}		
										</th>
									</c:when>
									<c:when test="${cellStatus.first}">
										<th colspan="${cell.colspan }" rowspan="${cell.rowspan }" style="width:200px;">
											${cp:MAPVALUE("WJLX", cell.value)}		
										</th>
									</c:when>
									<c:otherwise>
										<th colspan="${cell.colspan }" rowspan="${cell.rowspan }">
											${cell.value }
										</th>
									</c:otherwise>
								</c:choose>		
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
<%@ include file="./childs/stat-scripts.jsp"%>	
<script>
$(function() {


	$('#treetable>thead>tr:first>th').eq(0).remove();
	$('#treetable>thead>tr:first>th:last').remove();
	var text = $('#treetable tbody td:first').text().trim();

    $.myChart.init('#treetable', "发文数量统计" ,{
    	auto: true,
    	button: false,
    	stat: [{
    		chartType: 'column',
    		type: 'line',
    		td: text,
    		show:true
    	}]
    });
    
    
    $('#treetable').hide();//先画图在隐藏
});
</script>