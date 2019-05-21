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
	 <div id="chart"></div>	
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
	        	
	        });
   
//       var height=250;
//       var width=1024;
      var type = 'column';
      highcharts(1);
    
    function highcharts(target){
    	 var title = []; //交叉报表各统计项类（大类）   
         var bj = []; //办结项data   
         var bjurl = []; //办结项data   
         var bl = []; //办理中项data  
         var blurl = []; //办理中项data 
    	
         for(var n=0;n<8;n++){
      	    $('#statTable>thead>tr:nth-child('+target+')>th').eq(n+1).each(function() {
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
                text: 'OA各部门办件总量统计'
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
}); 


</script>

</html>