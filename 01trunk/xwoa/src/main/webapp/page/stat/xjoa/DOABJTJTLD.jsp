<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>${formNameFormat}</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

<%@include file="/page/stat/childs/stat-css.jsp"%>

</head>
<body>

	<div class="container">
<%-- 		<%@include file="/page/stat/childs/search-new.jsp"%> --%>
       
		<%@include file="/page/stat/childs/normalTable-new.jsp"%>
	</div>
<%@include file="/page/stat/childs/stat-scripts2.jsp" %>
<script src="${pageContext.request.contextPath}/scripts/common.js"></script>
	
</body>

<script type="text/javascript">
	$(function() {
		
// 		setTimeout(function() {
			
// 			window.SHOW_TABLE = false;
			$.myChart.init('#statTable', '${formNameFormat}', {
				
				height:160,
				auto : true,
				button:false,
				stat : [ {
					chartType : 'pie',
					type : 'column',
// 					td : '数量',
					show : true
				} ]
			,
			highcharts:{
				
				title: {
                text:  ''
                 },
				  plotOptions : {
					pie : {
						point : {
							events : {
								click : function() {
									var link = this.link;
									if (link) {
										parent.navTab.openTab('PIE_TAB', link,
												{
													title : this.name,
													external : true
												});
									}

								}
							}
						}
					},

					column : {
						point : {
							events : {
								click : function() {
									var link = this.link;
									if (link) {
										parent.navTab.openTab('PIE_TAB', link,
												{
													title : this.name,
													external : true
												});
									}

								}
							}
						}
					}
				}
			}
		});
		
		
			
//   			var text = $('#statTable tbody tr:first td:first').click();
            //隐藏最后一列统计
            $('#statTable thead tr:first th:last').hide();
            $('#statTable>tbody>tr').find('td:eq(2)').hide();
            //交叉报表进入页面自动触发显示图表
            var text = $('#statTable thead tr:first').find('th:eq(1)').click();
		        	
 			$('#statTable').hide();
//  			$(".highcharts-title").css('visibility','hidden');
//         if (!window.SHOW_TALBE) {
        	
// 			$('#statTable').hide();
			
//         }else{
        	///$('#chartType').hide();
//         } 
        
// 		}, 20);
		
		
  		
		init = setInterval("FrameUtils.initialize(window, init)", MyConstant.initTimeForAdjustHeight);
		
		
			
	});
	

	 
	   

</script>

</html>