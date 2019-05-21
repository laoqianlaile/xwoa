<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>${formNameFormat}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

<!-- Bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/themes/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/themes/bootstrap3/css/bootstrap-datetimepicker.min.css">

<!-- 统计修改样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/scripts/stat/stat.css">

<!-- 树形表格样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/scripts/jquery/jquery.treetable/stylesheets/jquery.treetable.css" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="${pageContext.request.contextPath}/themes/bootstrap3/js/html5shiv.min.js"></script>
        <script src="${pageContext.request.contextPath}/themes/bootstrap3/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<%-- 默认紧凑型 --%>
	<c:if test="${isThLarge == 1 }">
		<c:set var="thLargeDisplay" value=""></c:set>
		<c:set var="thLargeActive" value="active"></c:set>
		<c:set var="thSmallActive" value=""></c:set>
	</c:if>
	<c:if test="${isThLarge == 0 || empty isThLarge}">
		<c:set var="thLargeDisplay" value="table-condensed"></c:set>
		<c:set var="thLargeActive" value=""></c:set>
		<c:set var="thSmallActive" value="active"></c:set>
	</c:if>
		<%@include file="/page/stat/childs/search-new.jsp"%>

		<%@include file="/page/stat/childs/toolbar-new.jsp"%>

		<%@include file="/page/stat/childs/treeTable-new.jsp"%>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="${pageContext.request.contextPath}/themes/bootstrap3/js/jquery.1.10.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="${pageContext.request.contextPath}/themes/bootstrap3/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/themes/bootstrap3/js/mustache.js"></script>
	<!-- bootstrap日期控件 -->
	<script src="${pageContext.request.contextPath}/themes/bootstrap3/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/themes/bootstrap3/js/bootstrap-datetimepicker.zh-CN.js"></script>

	<!-- 树形表格js -->
	<script src="${pageContext.request.contextPath}/scripts/jquery/jquery.treetable/jquery.treetable.js" type="text/javascript"></script>
	
	<!-- 统计相关js -->
	<script src="${pageContext.request.contextPath}/scripts/stat/stat.js"></script>

</body>
<script type="text/javascript">
	$.STAT.addRenderTableCallback(function(tds, header, params) {
		var td = tds[tds.length - 1], value = tds[0].data('value'), name = tds[2].data('value'), type = tds[5].data('value');
				
		// 如果是报表，添加按钮
		if ('R' === type) {
			td.html(Mustache.render(BUTTONS_TEMPLATE, {
				modelName: value,
				name: name
			}));
		}
		
		// 如果有查询条件，去除TR中的tt-parent-id值，防止树形表格生产时找不到父节点报错
		if (params.modelCode || params.modelNamer) {
			this.data('ttParentId', null);
		}
	});
	
	var BUTTONS_TEMPLATE = '<a class="btn btn-success btn-xs edit" href="${pageContext.request.contextPath }/stat/queryModel!edit.do?modelName={{modelName}}" target="navTab" rel="statDefForm" title="编辑{{name}}"><i class="glyphicon glyphicon-edit"></i></a>';
	BUTTONS_TEMPLATE += '<a class="btn btn-info btn-xs copy" href="${pageContext.request.contextPath}/stat/queryModel!toCopy.do?modelName={{modelName}}" title="复制为新模板"><i class="glyphicon glyphicon-magnet"></i></a>';
	BUTTONS_TEMPLATE += '<a class="btn btn-info btn-xs link" href="${pageContext.request.contextPath}/stat/twodimenform!doStat.do?modelName={{modelName}}" title="查看{{name}}" target="navTab" rel="statShow" external="true"><i class="glyphicon glyphicon-link"></i></a>';
	BUTTONS_TEMPLATE += '<a class="btn btn-danger btn-xs delete" href="${pageContext.request.contextPath }/stat/queryModel!delete.do?modelName={{modelName}}" title="删除{{name}}" target="ajaxTodo"><i class="glyphicon glyphicon-trash"></i></a>';
	
	var ADD_BUTTON = '<a class="btn btn-primary btn-sm add" href="${pageContext.request.contextPath }/stat/queryModel!built.do" target="navTab" rel="statDefForm" title="新建统计模板"><i class="glyphicon glyphicon-plus"></i> 新建</a>';;
	
	$.STAT.addButtonOnToolBar(ADD_BUTTON);
	$.STAT.removeButtonOnToolBar('button.excel');
	$.STAT.renderTable();
</script>
</html>