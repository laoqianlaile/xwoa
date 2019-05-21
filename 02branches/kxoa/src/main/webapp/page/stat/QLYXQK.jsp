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
<div id="QLYXQK_PAGE" class="pageContent">
	<table class="list stat" layoutH=".pageHeader 1" id="treetable">
		<c:if test="${not empty tablePanel.thead }">
			<thead align="center">
				<c:forEach var="line" items="${tablePanel.thead.lines }" varStatus="status">
					<tr>
						<c:forEach var="cell" items="${line.cells }" varStatus="cellStatus">
								<c:choose>
									<%-- 第一列和最后一列合计不做统计 --%>
									<c:when test="${not (cellStatus.first || cellStatus.last) }">
										<th colspan="${cell.colspan }" rowspan="${cell.rowspan }" stat="T">
											${cp:MAPVALUE("ITEM_TYPE", cell.value)}		
										</th>
									</c:when>
									<c:when test="${cellStatus.first}">
										<th colspan="${cell.colspan }" rowspan="${cell.rowspan }" style="width:200px;">
											${cp:MAPVALUE("ITEM_TYPE", cell.value)}		
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
						<%-- 每行单元格数量 --%>
						<c:set var="cellLength" value="${fn:length(line.cells) }"></c:set>
						
						<c:forEach var="cell" items="${line.cells }" varStatus="status">
							
								<c:choose>
									<%-- 第一列显示单位 --%>
									<c:when test="${status.first }">
										<td colspan="${cell.colspan }" rowspan="${cell.rowspan }" style="text-align: left;">
											${cp:MAPVALUE("depno",cell.value)}
										</td>
									</c:when>
									
									<%-- 合计“废止”添加链接 --%>
									<c:when test="${status.last }">
										<td colspan="${cell.colspan }" rowspan="${cell.rowspan }">
										${fn:indexOf(cell.value, '.') gt '0' ? fn:substringBefore(cell.value, '.') : cell.value }
										</td>
									</c:when>
									
									<%-- 含有链接 --%>
									<c:when test="${not empty cell.href && not empty cell.value}">
										<td colspan="${cell.colspan }" rowspan="${cell.rowspan }">
										<%-- 标题和REL自行修改 --%>
										<a href="${cell.href }" target="navTab" external="true" title="" rel="">${cell.value }</a>
										</td>
									</c:when>
									
									<c:otherwise>
										<td colspan="${cell.colspan }" rowspan="${cell.rowspan }">
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
	    $.myChart.init('#treetable', '权力库统计' ,{
	    	auto: true,
	    //	button: false,
	    	stat: [{
	    		chartType: 'column',
	    		type: 'line',
	    		td: '江苏省',
	    		show:true
	    	}]
	    });
	    
	    var width = $('#QLYXQK_PAGE').width();
	    var parent = window.parent.frames['fzjdFrm'] || window.parent.frames['dzjcFrm'];
	    
	    $(parent).width(width);
    });
</script>
<%@ include file="./childs/stat-scripts.jsp"%>

</html>
	