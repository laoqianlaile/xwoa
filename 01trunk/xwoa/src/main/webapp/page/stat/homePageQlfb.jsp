<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta name="decorator" content='${LAYOUT}' />


<title>${formName}</title>

<script>
	$(function() {
		$.myChart.init('#ec_table', $.evalJSON('${jsonFormData}'), {
			renderTo:'test',		/*因为图标和表格并行排列，所以这里要指定一个DIV容器，填写ID*/
			button:false,			/*是否展示按钮，如果固定的话就不用按钮了*/
			stat:[{ 				/*单独统计某一行或某一列*/
				cell:'合计',			/*以统计某一行为列，这里填写该行第一个TD的内容*/
				type:'row' 		/*row:统计某一行  column:统计某一列*/
			}],
			defaultType:'pie',
			auto:false				/*关闭原来自动对每一行、每一列的统计图*/
		},{chart:{width:450, height:315}});
	});
	
</script>
</head>
<body>
	
<div class="eXtremeTable">

	<table id="ec_table" border="0" cellspacing="0" cellpadding="0" class="tableRegion" style="width:56%;float:left;">

		<jsp:include page="childs/normalTableHead.jsp"></jsp:include>
		
		<jsp:include page="childs/normalTableBody.jsp"></jsp:include>
	
	</table>
	
	<div id="test" style="float:left;width:43%;boader:1px soldi black;"></div>
</div>
</body>
</html>