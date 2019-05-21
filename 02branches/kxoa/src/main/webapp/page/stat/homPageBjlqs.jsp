<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta name="decorator" content='${LAYOUT}' />


<title>${formName}</title>

<script>
	
	$(function() {
		$.myChart.init(null, $.evalJSON('${jsonFormData}'), {
			button:false,			/*是否展示按钮，如果固定的话就不用按钮了*/
			stat:[{ 				/*单独统计某一行或某一列*/
				cell:'办件量',			/*以统计某一行为列，这里填写该行第一个TD的内容*/
				type:'column', 		/*row:统计某一行  column:统计某一列*/
				show:true			/*是否一进页面就展示统计图*/
			}],
			defaultType:'line',	/*line:折线图  column:柱状图 pie:饼状图*/
			auto:false				/*关闭原来自动对每一行、每一列的统计图*/
		},{chart:{height:290}});
	});
	
</script>
</head>
<body>
</body>
</html>