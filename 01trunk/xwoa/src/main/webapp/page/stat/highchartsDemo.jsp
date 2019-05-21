<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<%@ include file="childs/search.jsp"%>

<div class="pageContent">
	<table class="list" width="100%">
		
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
	
	<div id="ChartContainer" style="min-width: 400px; height: 400px; margin: 20 auto"></div>
</div>
	
<script>
	var formData = $.parseJSON('${jsonFormData}');
	var project = $('select[name=project] option:selected', navTab.getCurrentPanel()).text();
	var name = $('input[name=name]', navTab.getCurrentPanel()).val();
	var year = $('select[name=year] option:selected', navTab.getCurrentPanel()).text();
	var month = $('select[name=month] option:selected', navTab.getCurrentPanel()).text();
	
/* 	console.log(project);
	console.log(name);
	console.log(formData); */
	var temp = formData.fromData;
	var chartData = parseData(temp);
//	console.log(chartData);
	
	function parseData(data) {
		var result = [];
		var test = null;
		var text = null;
		
		// 只有一条记录不画图
		if (!data || data.length <= 1) {
			return null;
		}
		
		var firstProject = data[0][2];
		var firstName = data[0][3];
		var testProject = data[1][2];
		var testName = data[1][3];
		
		// 项目相同，统计不同人员工作量
		if (firstProject == testProject) {
			test = 'project';
		}
		// 人员相同，统计不同项目工作量
		else if (firstName == testName) {
			test = 'name';
		}
		// 多个项目或人员不统计
		else {
			return null;
		}
		
		for (var i=0; i<data.length; i++) {
			var temp = data[i];
			var project = temp[2];
			var name = temp[3];
			var work = parseFloat(temp[5]);
			
			if (test == 'project') {
				if (project != firstProject) return null;
				result.push([name, work]);
			}
			else {
				if (name != firstName) return null;
				result.push([project, work]);
			}
		}
		
		if (test == 'project') {
			text = firstProject;
		}
		else {
			text = firstName;
		}
		
		return {text:text, data: result};
	}

  	$(function() {
		$('a', navTab.getCurrentPanel()).each(function() {
			$this = $(this);
			
			var href = $this.attr('href');
			href=encodeURI(encodeURI(href));
			$this.attr('href', href);
		});
		
//		console.log(chartData);
		// 无需画图
		if (!chartData) return;
		
		$('#ChartContainer', navTab.getCurrentPanel()).highcharts({
	            chart: {
	                plotBackgroundColor: null,
	                plotBorderWidth: null,
	                plotShadow: false
	            },
	            
	            title: {
	                text: year + month + chartData.text + '工作量分布'
	            },
	            tooltip: {
	        	    pointFormat: '{series.name}: <b>{point.y}（天）</b>',
	            	percentageDecimals: 1
	            },
	            plotOptions: {
	                pie: {
	                    allowPointSelect: true,
	                    cursor: 'pointer',
	                    dataLabels: {
	                        enabled: true,
	                        color: '#000000',
	                        connectorColor: '#000000',
	                        formatter: function() {
	                            return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(1) +' %';
	                        }
	                    }
	                }
	            },
	            series: [{
	                type: 'pie',
	                name: chartData.text,
	                data: chartData.data
	            }]
	        });
	}); 
</script>