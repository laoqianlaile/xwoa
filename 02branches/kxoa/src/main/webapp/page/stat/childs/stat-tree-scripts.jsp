<%@ page contentType="text/html;charset=UTF-8"%>

<script>
window.Config = {};
window.Config.contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/themes/bootstrap3/js/all_js_min.js"></script>

<script src="${pageContext.request.contextPath}/scripts/plugin/charisma/js/jquery.chosen.min.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<%-- <script src="${pageContext.request.contextPath}/scripts/centitui/jquery-1.7.2.min.js" type="text/javascript"></script> --%>

<script src="${pageContext.request.contextPath}/scripts/plugin/charisma/js/centit.core.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<%-- <script src="${pageContext.request.contextPath}/themes/bootstrap3/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/themes/bootstrap3/js/mustache.js"></script> --%>

<!-- bootstrap日期控件 -->
<%-- <script src="${pageContext.request.contextPath}/themes/bootstrap3/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/themes/bootstrap3/js/bootstrap-datetimepicker.zh-CN.js"></script> --%>

<!-- 树形表格js -->
<script src="${pageContext.request.contextPath}/scripts/plugin/jquery.treetable/jquery.treetable.js" type="text/javascript"></script>

<!-- 报表 -->
<%-- <script src="${pageContext.request.contextPath}/scripts/plugin/highcharts3.0.4/highcharts.js" type="text/javascript"></script> --%>
<script src="${pageContext.request.contextPath}/scripts/sys/ui/centit.charts.js" type="text/javascript"></script>
<%-- <script src="${pageContext.request.contextPath}/scripts/plugin/dataTable/jquery.dataTables.min.js" type="text/javascript"></script> --%>
<script src="${pageContext.request.contextPath}/scripts/plugin/dataTable/bootstrap.pagination.js" type="text/javascript"></script>

<!-- 统计相关js -->
<script src="${pageContext.request.contextPath}/scripts/stat/stat.js"></script>