<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<!-- IFRAME模式下需要导入的内容BEGIN -->
<%@ include file="./childs/stat-taglibs.jsp"%>
<!-- IFRAME模式下需要导入的内容END -->
<%@ include file="childs/search.jsp"%>
<div class="pageContent" >
		<table class="list stat" id="treetable" layoutH=".pageHeader 1" >
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


    $.myChart.init('#treetable', "阅办文数量统计" ,{
    	auto: true,
    	button: true,
    	stat: [{
    		chartType: 'column',
    		type: 'line',
    		td: "数量",
    		show:true
    	}]
    });
    
    

});
</script>