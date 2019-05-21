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
	<table class="list stat" id="treetable" layoutH=".pageHeader 1" style="table-layout: fixed;">
		
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
										
											<c:when test="${modelName == 'OAXTBJLTJ' }">
												${cp:MAPVALUE("oa_ITEM_TYPE", cell.value)}
											</c:when>
											<c:otherwise>
												${cell.value }
											</c:otherwise>
										</c:choose>
									</c:when>
									
									<%-- 第二层：状态 --%>
									<c:when test="${status.index == 1 }">
										<c:choose>
								
											<c:when test="${modelName == 'OAXTBJLTJ' }">
												${cp:MAPVALUE("oa_bizstate",cell.value)}
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
	 
	 <div id="chart"></div>	

</body>
<%@ include file="./childs/stat-scripts.jsp"%>
<script type="text/javascript">

function parseNumber(num) {
	if (num) {
		return parseInt(num);
	}
	
	return 0;
}

$(function() {
	
	//$('#treetable>thead>tr:first>th').eq(9).remove();
	//$('#treetable>thead>tr:').eq(2).remove();
	//$('#treetable>thead>tr:first>th').eq(9).remove();
      var title = []; //交叉报表各统计项类（大类）   
      for(var n=0;n<8;n++){
    	    $('#treetable>thead>tr:first>th').eq(n+1).each(function() {
    	    	 title.push($(this).text().trim());
    	     });    
      }

   // console.log(title);
  
    var bj = []; //办结项data   
    var bl = []; //办理中项data   
    var ddsl = []; //等待受理项data 
    for(var i=0;i<8;i++){  	 
     $('#treetable>tbody>tr:last>td').eq(3*i+1).each(function() {
    	 bj.push(parseNumber($(this).text().replace(/[‘&\|\\\*^%$#@\-“]/g,"").trim()));
     });   
    }
    for(var j=0;j<8;j++){  	
     $('#treetable>tbody>tr:last>td').eq(3*j+2).each(function() {
    	 bl.push(parseNumber($(this).text().replace(/[‘&\|\\\*^%$#@\-“]/g,"").trim()));
     }); 
    }
    for(var k=1;k<9;k++){  	 
    	$('#treetable>tbody>tr:last>td').eq(3*k).each(function() {
    		ddsl.push(parseNumber($(this).text().replace(/[‘&\|\\\*^%$#@\-“]/g,"").trim()));
    	     }); 
    }

      var height=250;
      var width=1024;
      var type = 'column';
    new Highcharts.Chart({
    
 		 chart: {
 			 type: type,
             renderTo: 'chart',
             defaultSeriesType: 'line',
             height:height,
            width:width
         },
         legend: {
             enabled: true
         },
         title: {
             text: 'OA各部门办件总量统计'
         }, 
         xAxis: {
        	    categories: title ? title : []

         },
         yAxis: {
             title: {
                 text: '数量'
             }
         },
         series:  [{
             name: '办结',
              data:bj ? bj : [] 

         }, {
             name: '办理中',
                data:bj ? bl : [] 
 
         },{
             name: '等待受理',
            data:ddsl ? ddsl:[]         

         }
         
         ]
 	});
}); 

</script>



</html>