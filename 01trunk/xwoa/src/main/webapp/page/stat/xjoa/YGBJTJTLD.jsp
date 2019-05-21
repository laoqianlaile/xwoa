<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>${formNameFormat}</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />


<%@include file="/page/stat/childs/stat-css.jsp" %>
<%-- <script src="${pageContext.request.contextPath}/scripts/plugin/highcharts3.0.4/jquery.highchartTable.min.js" type="text/javascript"></script> --%>

</head>
<body>
	
	<div class="container">
	<%@include file="/page/stat/childs/search-new.jsp"%>
    <%@include file="/page/stat/childs/normalTable-new.jsp"%>
	</div>
	 <div id="chart" style="width:50%;float:left; height: 600px"></div>
	 <div id="pieChart" style="width:50%;float:left;">
	    <div id="zbPieChart" style="height:300px"></div>
	    <div id="ybPieChart" style="height:300px"></div>
	 </div>
	 	
	<%@include file="/page/stat/childs/stat-scripts2.jsp" %>
</body>

<script type="text/javascript">

function parseNumber(num) {
	if (num) {
		return parseInt(num);
	}
	
	return 0;
}

$(function() {
	       //格式化合计数据格式
	         $('#statTable>tbody>tr>td:nth-child(18)').each(function() {
	        	$(this).text(parseNumber(parseInt($(this).text())));
	        });  
	      
	        $('#statTable>tbody>tr>td:first-child').bind('click',function() {
	        	highcharts($(this).closest('tr').index()+1);
	            highchartForPie('ZB',$(this).closest('tr').index()+1);
	        	highchartForPie('YB',$(this).closest('tr').index()+1);
	        });
   
//       var height=250;
//       var width=1024;
      var type = 'column';
      highcharts(1);
      highchartForPie('ZB',1);
      highchartForPie('YB',1);
      //柱状图
    function highcharts(target){
    	 var title = []; //交叉报表各统计项类（大类）   
         var bj = []; //办结项data   
         var bjurl = []; //办结项data   
         var bl = []; //办理中项data  
         var blurl = []; //办理中项data 
    	
         for(var n=0;n<8;n++){
      	    $('#statTable>thead>tr:eq(0)>th').eq(n+1).each(function() {
      	    	 title.push($.trim($(this).text()));
      	     });    
             }
     	 for(var i=0;i<8;i++){  
     		
     	     $('#statTable>tbody>tr:nth-child('+target+') td').eq(2*i+1).each(function() {
     	    	 bj.push({y:parseNumber($.trim($(this).text().replace(/[‘&\|\\\*^%$#@\-“]/g,""))),link:$(this).find("a").attr('href')});
//      	    	 bjurl.push($(this).html());
     	     });   
     	    }
     	    for(var j=0;j<8;j++){  	
     	     $('#statTable>tbody>tr:nth-child('+target+') td').eq(2*j+2).each(function() {
     	    	 bl.push({y:parseNumber($.trim($(this).text().replace(/[‘&\|\\\*^%$#@\-“]/g,""))),link:$(this).find("a").attr('href')});
//      	    	 blurl.push($(this).html());
     	     }); 
     	    }
    	new Highcharts.Chart({
    	    
    		 chart: {
    			 type: type,
                renderTo: 'chart',
                defaultSeriesType: 'line',
//                 height:height,
//                 width:width
            },
            legend: {
                enabled: true
            },
            title: {
                text: '个人办件总量统计'
            }, 
            xAxis: {
           	    categories: title ? title : [],
            },
            yAxis: {
                title: {
                    text: '数量'
                }
            },
            series:  [{
                 name: '办结',
                 data:bj ? bj : [] ,
              

            }, {
                   name: '办理中',
                   data:bl ? bl : [] ,
                 
            }
          
            
            ],
        	
            
	    		 plotOptions: {
	                pie: {
	                	point: {
	                        events: {
	                            click: function(e) {
	                                var link = this.link;
	                                if (link) {
	                                	parent.navTab.openTab('PIE_TAB', link,{title:"办件统计", external:true});
	                                }
	                                
	                            }
	                        }
	                    }
	                },
	                
	                column: {
	                	point: {
	                        events: {
	                            click: function(e) {
	                            	debugger;
	                                var link = this.link;
	                                if (link) {
	                                	parent.navTab.openTab('PIE_TAB', link,{title:"办件统计", external:true});
	                                } 
	                                
	                            }
	                        }
	                    }
	                }
	    		} 
        	
    	});
    }
    
    function highchartForPie(type,target){
    	var data = [],title,cantain;
    	if(type=="YB"){
    		data = getYBPieData(target);
    		title = "个人办结案件统计";
    		cantain = "ybPieChart";
    	}
    	if(type=="ZB"){
    		data = getZBPieData(target);
    		title = "个人办理中案件统计";
    		cantain = "zbPieChart";
    	}
    	if(data.length==0){
    		$('#'+cantain).html("");
    	}
    	$('#'+cantain).highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: title
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '占比',
                data:  data
            }]
        });
    }
    /**
    *构建办结数据
    */
    function getYBPieData(target){
    	var bj = []; //办结项data 
    	var isAllZero = true;
        for(var i=0;i<8;i++){
        	 var name = $('#statTable>thead>tr:eq(0)>th').eq(i+1).text();//获取列头
    	     $('#statTable>tbody>tr:nth-child('+target+') td').eq(2*i+1).each(function() {
    	    	 var v = parseNumber($.trim($(this).text().replace(/[‘&\|\\\*^%$#@\-“]/g,"")));
    	    	 bj.push({name:$.trim(name),y:v,link:$(this).find("a").attr('href')});
    	    	 isAllZero = isAllZero&(v==0);
    	     });   
    	    }
        if(isAllZero)
        	return [];
        return bj;
    }
    /**
    *构建办理中数据
    */
    function getZBPieData(target){
        var bl = []; //办理中项data  
        var isAllZero = true;
        for(var j=0;j<8;j++){  	
    	    	var name = $('#statTable>thead>tr:eq(0)>th').eq(j+1).text();//获取列头
    	       $('#statTable>tbody>tr:nth-child('+target+') td').eq(2*j+2).each(function() {
	    	    	 var v = parseNumber($.trim($(this).text().replace(/[‘&\|\\\*^%$#@\-“]/g,"")));
    	    	 bl.push({name:$.trim(name),y:v,link:$(this).find("a").attr('href')});
    	    	 isAllZero = isAllZero&(v==0);
    	     }); 
    	    }
        if(isAllZero)
        	return [];  
    	 return bl;
    }
}); 


</script>

</html>