<%@ page contentType="text/html;charset=UTF-8"%>


<!-- jQUERY (NECESSARY FOR BOOTSTRAP'S JAVASCRIPT PLUGINS) -->
<script src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/themes/bootstrap3/js/mustache.js"></script>

<!-- 报表 -->
<script src="${pageContext.request.contextPath}/scripts/plugin/highcharts3.0.4/highcharts.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/sys/ui/centit.charts.js" type="text/javascript"></script>

<!-- 树形表格js -->
<script src="${pageContext.request.contextPath}/scripts/plugin/jquery.treetable/jquery.treetable.js" type="text/javascript"></script>
<%-- <script src="${pageContext.request.contextPath}/scripts/jquery/treeTable/jquery.treeTable.js" type="text/javascript"></script> --%>
<script src="${pageContext.request.contextPath}/scripts/jquery/jquery.chosen/jquery.chosen.min.js" type="text/javascript"></script>
<!-- 导出EXCEL -->
<script src="${pageContext.request.contextPath}/scripts/print.js" type="text/javascript"></script>

<!-- 统计相关js -->
<script src="${pageContext.request.contextPath}/scripts/stat/stat.js"></script>

<script>

$.fn.extend({
	loadUrl: function(url, data, clear, callback) {
		var $this = $(this);
		
		$.ajax({
			type: 'post',
			url: url,
			data: data,
			cache: false,
			success: function(response) {
				$this.html(response);
			}
		});
	}
});

</script>

