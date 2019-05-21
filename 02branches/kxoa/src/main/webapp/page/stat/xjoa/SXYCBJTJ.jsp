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
		<%@include file="/page/stat/childs/search-new.jsp"%>
      
		<%@include file="/page/stat/childs/normalTable-new.jsp"%>
	</div>
<%@include file="/page/stat/childs/stat-scripts2.jsp" %>

	
</body>

<script type="text/javascript">
	$(function() {
		
		
		setTimeout(function() {
			$.myChart.init('#statTable', '${formNameFormat}', {
				height:160,
				auto : true,
 				button:false,
				stat : [ {
					chartType : 'line',
					type : 'column',
// 					td : '数量',
					show : true
				} ]
			,
				highcharts:{
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
           var text = $('#statTable tbody tr:first td:first').click();
		}, 20);
		
		
		
		
		 
	});
	

	
	 
	   

</script>

</html>