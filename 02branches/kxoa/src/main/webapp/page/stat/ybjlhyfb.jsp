<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="/page/common/taglibs.jsp"%>
<%@ include file="/page/common/css.jsp"%>

<html>
<head>
<meta name="decorator" content='${LAYOUT}' />

<script type="text/javascript">
$(function() {
	$.myChart.init('#ec_table', $.evalJSON('${jsonFormData}'), {
		defaultType:'pie', 
		stat:[{cell:'合计', type:'row'}]
	});
});
</script>

<title>${formName}</title>


</head>
<body>
	<jsp:include page="childs/search.jsp"></jsp:include>
	
	<div class="eXtremeTable">
		<table id="ec_table" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%">
		
			<jsp:include page="childs/normalTableHead.jsp"></jsp:include>
			
			<jsp:include page="childs/normalTableBody.jsp"></jsp:include>
			
		</table>
	</div>
	
</body>
</html>