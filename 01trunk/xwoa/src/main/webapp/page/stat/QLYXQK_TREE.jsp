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
	<table class="list stat" id="treetable" layoutH=".pageHeader 1" style="width:100%; table-layout: fixed;">
		
		<c:if test="${not empty tablePanel.thead }">
			<thead align="center">
				<c:forEach var="line" items="${tablePanel.thead.lines }" varStatus="status">
					<tr>
						<c:forEach var="cell" items="${line.cells }" varStatus="cellStatus">
							<c:if test="${cell.display == false}">
								<c:set var="display" value="display:none;"></c:set>
							</c:if>
							<c:if test="${cell.display}">
								<c:set var="display" value=""></c:set>
							</c:if>
							
							
							
							<c:choose>
								<%-- 第一层：权力类别 --%>
								<c:when test="${cellStatus.index == 0 }">
									<th colspan="${cell.colspan }" rowspan="${cell.rowspan }" style="width:250px; ${display };">
										${cell.value}	
									</th>
								</c:when>
								<c:when test="${cellStatus.last }">
									<th colspan="${cell.colspan }" rowspan="${cell.rowspan }" style="width:60px; ${display };">
										${cell.value}	
									</th>
								</c:when>
								<c:otherwise>
									<th colspan="${cell.colspan }" rowspan="${cell.rowspan }" style="width:60px; ${display };" stat="T">
										${cp:MAPVALUE("ITEM_TYPE", cell.value)}	
									</th>
								</c:otherwise>
									
							</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
			</thead>
		</c:if>
		
		<c:if test="${not empty tablePanel.tbody }">
			<tbody align="left">
			
				<c:forEach var="line" items="${tablePanel.tbody.lines }">
					<c:set var="depno" value="${line.cells[0].value }"></c:set>
					<c:set var="supdepno" value="${line.cells[1].value }"></c:set>
					
					<%-- 父节点不为空 --%>
					<c:if test="${not empty supdepno}">
						<tr data-tt-id="${depno }" data-tt-parent-id="${supdepno }">
					</c:if>
					
					<%-- 父节点为空 顶级节点 --%>
					<c:if test="${empty supdepno}">
						<tr data-tt-id="${depno }">
					</c:if>
					
						<%-- 每行单元格数量 --%>
						<c:set var="cellLength" value="${fn:length(line.cells) }"></c:set>
						
						<c:forEach var="cell" items="${line.cells }" varStatus="status">
							<c:if test="${cell.display == false}">
								<c:set var="display" value="display:none;"></c:set>
							</c:if>
							<c:if test="${cell.display}">
								<c:set var="display" value=";"></c:set>
							</c:if>
							
							<c:choose>
								<%-- 第一列显示单位 --%>
								<c:when test="${status.first }">
									<td colspan="${cell.colspan }" rowspan="${cell.rowspan }" style="text-align: left;${display}">
										${cp:MAPVALUE("depno",cell.value)}
									</td>
								</c:when>
								
								<%-- 第二列显示单位 --%>
								<c:when test="${status.count == 2 }">
									<td colspan="${cell.colspan }" rowspan="${cell.rowspan }" style="${display}">
										${cp:MAPVALUE("depno",cell.value)}
									</td>
								</c:when>
								
								<%-- 合计“废止”添加链接 --%>
								<c:when test="${status.count == cellLength }">
									<td colspan="${cell.colspan }" rowspan="${cell.rowspan }" style="${display}">
										${fn:indexOf(cell.value, '.') gt '0' ? fn:substringBefore(cell.value, '.') : cell.value }
									</td>
								</c:when>
								
								<%-- 含有链接 --%>
								<c:when test="${not empty cell.href && not empty cell.value}">
									<td colspan="${cell.colspan }" rowspan="${cell.rowspan }" style="${display}">
										<%-- 标题和REL自行修改 --%>
										<a href="${cell.href }" target="navTab" external="true" title="" rel="">${cell.value }</a>
									</td>
								</c:when>
								
								<c:otherwise>
									<td colspan="${cell.colspan }" rowspan="${cell.rowspan }" style="${display}">
										${cell.value }
									</td>
								</c:otherwise>
							</c:choose>
								
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</c:if>
	</table>
	
</div>
 <!--endprint--> 
</body>

<script type="text/javascript">

	$(function() {
		if ($('#treetable td').length == 0) {
			$('#treetable tbody tr').remove();
		};
		
		var code = $('#treetable tbody tr:first').attr('data-tt-id');
		var text = $('#treetable tbody td:first').text().trim();
		
	    // 展开顶级
		$('#treetable').treetable({ expandable: true }).treetable("expandNode", code);
	    
	    $.myChart.init('#treetable', text ,{
	    	//button: false,
	    	stat: [{
	    		chartType: 'column',
	    		type: 'line',
	    		td: text,
	    		show:true
	    	}]
	    });
	}); 
    
</script>

</html>