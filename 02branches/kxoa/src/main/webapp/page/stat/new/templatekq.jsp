<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/page/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>${formNameFormat}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
 <style type="text/css">
        td,th
        {
            white-space: nowrap;
        }
    </style>

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
<body >
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
	<div class="container" style="padding-top: 5px;width:97%">
		<span style="display:none;">
		<%@include file="/page/stat/childs/search-new.jsp"%>
		</span>

		<%@include file="/page/stat/childs/toolbar-new1.jsp"%>

		<%@include file="/page/stat/childs/normalTable-new.jsp"%>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="${pageContext.request.contextPath}/themes/bootstrap3/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/themes/bootstrap3/js/mustache.js"></script>
	<!-- bootstrap日期控件 -->
	<script src="${pageContext.request.contextPath}/themes/bootstrap3/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/themes/bootstrap3/js/bootstrap-datetimepicker.zh-CN.js"></script>

	<!-- 树形表格js -->
<%-- 	<script src="${pageContext.request.contextPath}/scripts/jquery/jquery.treetable/jquery.treetable.js" type="text/javascript"></script> --%>
	
	<!-- 统计相关js -->
	<script src="${pageContext.request.contextPath}/scripts/stat/stat.js"></script>
	<%@include file="/page/stat/childs/stat-scripts.jsp" %>
</body>
<script type="text/javascript">
	$.STAT.addRenderTableCallback(function(tds, headers, params) {
		var td, href, qlstate = params['qlstate'], link;
		
		if ('N' === qlstate) return;
		
		for (var i=2; i<tds.length; i++) {
			td = tds[i];
			
			link = td.find('a');
			if (link.length) {
				href = link.attr('href');
			}
			// 没有链接，跳过
			else {
				continue;
			}
			
			// 根据qlstate值，改变链接中参数
			if (!qlstate || 'A' === qlstate) {
				href = href.replace('isSuspend=1', 'isSuspend=0');
				href = href.replace('isDisuse=1', 'isDisuse=0');
			}
			else if ('X' === qlstate) {
				href = href.replace('isSuspend=1', 'isSuspend=0');
			}
			else if ('T' === qlstate) {
				href = href.replace('isDisuse=1', 'isDisuse=0');
			}
			
			link.attr('href', href);
		}
	});
	
	$.STAT.renderTable();
</script>
</html>