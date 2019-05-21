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
				<c:forEach var="line" items="${tablePanel.thead.lines }" varStatus="status">
					<tr>
						<c:forEach var="cell" items="${line.cells }">
					
							<th colspan="${cell.colspan }" rowspan="${cell.rowspan }" width="150px;">
						
								<c:choose>
									<%-- 第一层：类别 --%>
									<c:when test="${status.index == 0 }">
										<c:choose>
										
											<c:when test="${modelName == 'SWSLTJ' }">
											
												${cp:MAPVALUE("INCOME_DEPT_TYPE", cell.value)}
											</c:when>
											<c:otherwise>
												${cell.value }
											</c:otherwise>
										</c:choose>
									</c:when>
									
									<%-- 第二层：状态 --%>
									<c:when test="${status.index == 1 }">
										<c:choose>
									
											<c:when test="${modelName == 'SWSLTJ' }">
												${cp:MAPVALUE("INCOME_DEPT_TYPE",cell.value)}
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

    $.myChart.init('#treetable', "阅办文数量统计" ,{
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